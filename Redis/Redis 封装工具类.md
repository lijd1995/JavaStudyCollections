```java
package com.msds.eureka.api.util;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {
	
	@SuppressWarnings("unchecked")
	private static RedisTemplate<String, Object> redisTemplate	= (RedisTemplate<String, Object>) ActiveApplicationContextUtil.getBean("redisTemplate");

//	@SuppressWarnings("unchecked")
//	private static RedisTemplate<String, Object> redisTemplateInt = (RedisTemplate<String, Object>) ActiveApplicationContextUtil.getBean("redisTemplate");
	
	private RedisUtil() {
		
	}

	@SuppressWarnings("unchecked")
	public static <E> Set<E> keys(String pattern) {
		return (Set<E>) redisTemplate.keys(pattern);
	}

	public static void setValue(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public static void setValue(String key, Object value, long timeout, TimeUnit timeType) {
	//	redisTemplate.setKeySerializer(new StringRedisSerializer());
//		//redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.opsForValue().set(key, value);
		expire(key, timeout, timeType);
	}

	public static void setMapValue(String key, String hashKey, Object value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	public static void setMapValue(String key, String hashKey, Object value, long timeout, TimeUnit timeType) {
		redisTemplate.opsForHash().put(key, hashKey, value);
		expire(key, timeout, timeType);
	}

	public static <T> T getValue(String key) {
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		if (null == redisTemplate.opsForValue().get(key)) {
			return null;
		}
		return (T) redisTemplate.opsForValue().get(key);
	}

	public static <T> T getMapValue(String key, String hashKey) {
		return (T) redisTemplate.opsForHash().get(key, hashKey);
	}

	public static void delMapValue(String key, String hashKey) {
		redisTemplate.opsForHash().delete(key, hashKey);
	}

	public static void delValue(String key) {
		redisTemplate.delete(key);
	}

	public static void expire(String key, long timeout, TimeUnit timeType) {
		redisTemplate.expire(key, timeout, timeType);
	}

	public static boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	public static boolean zsetAadd(String key, Object value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	public static Long zsetDel(String key, Object... value) {
		return redisTemplate.opsForZSet().remove(key, value);
	}

	public static Long zsetSize(String key) {
		return redisTemplate.opsForZSet().size(key);
	}

	public static void decrement(String key, Long count) {
		redisTemplate.opsForValue().decrement(key, count);
	}

	/**
	 * 按照score正序分页查找zset
	 * 
	 * @param key
	 * @param start 起始条数（包含）
	 * @param end   结束条数（包含）
	 * @return
	 */
	public static Set zsetRange(String key, int start, int end) {
		return redisTemplate.opsForZSet().range(key, start, end);
	}

	/**
	 * 按照score倒序分页查找zset
	 * 
	 * @param key
	 * @param start 起始条数（包含）
	 * @param end   结束条数（包含）
	 * @return
	 */
	public static Set zsetRevRange(String key, int start, int end) {
		return redisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	/**
	 * 放入列表尾部
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static <V> Long rpush(String key, V value) {
	//	redisTemplate.setKeySerializer(new StringRedisSerializer());
		return redisTemplate.opsForList().rightPush(key, value);
	}

	public static <V> void rpushExpire(String key, V value, Long outTime) {
		// redisTemplate.setKeySerializer(new StringRedisSerializer());
		expire(key, outTime, TimeUnit.MILLISECONDS);
		redisTemplate.opsForList().rightPush(key, value);
		
	}

	/**
	 * 查看列表大小
	 * 
	 * @param key
	 * @return
	 */
	public static Long llen(String key) {
	//	redisTemplate.setKeySerializer(new StringRedisSerializer());
		return redisTemplate.opsForList().size(key);
	}

	/**
	 * 查看列表数据
	 * 
	 * @param key
	 * @param start 起始(包含)
	 * @param end   结束(包含)
	 * @return
	 */
	public static <V> List<V> lrange(String key, int start, int end) {
	//	redisTemplate.setKeySerializer(new StringRedisSerializer());
		return (List<V>) redisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * 从队头出队，先进先出
	 * 
	 * @param key
	 * @return
	 */
	public static <V> V lpop(String key) {
	//	redisTemplate.setKeySerializer(new StringRedisSerializer());
		return (V) redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 删除指定的值
	 * 
	 * @param key
	 * @param value 指定的值
	 * @return
	 */
	public static <V> V lremove(String key, V value) {
		return (V) redisTemplate.opsForList().remove(key, 0, value);
	}

	/**
	 * 请求锁
	 * 
	 * @param lockName
	 * @param expire
	 * @return
	 */
	private static Long acquireLock(final String lockName, final long expire) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] lockBytes = redisTemplate.getStringSerializer().serialize(lockName);
				boolean locked = connection.setNX(lockBytes, lockBytes);
				connection.expire(lockBytes, expire);
				if (locked) {
					return 1L;
				}
				return 0L;
			}
		});
	}

	public static void releaseLock(String lockName) {
		final String lockKey = "Lock:{" + lockName + "}";
		final byte[] lockBytes = redisTemplate.getStringSerializer().serialize(lockKey);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.del(lockBytes);
				return null;
			}
		});
	}

	public static boolean lock(String name, Integer timeout, final Long expire) {
		if (name == null || "".equals(name)) {
			return false;
		}
		Long time = System.currentTimeMillis();
		// 设置默认值
		timeout = timeout == null ? 0 : timeout;
		Long timeoutAt = time + timeout * 1000;
		final String lockKey = "Lock:{" + name + "}";
		while (true) {
			Long lockResult = acquireLock(lockKey, expire);
			// 锁请求成功
			if (lockResult.compareTo(1L) == 0) {
				return true;
			}

			try {
				time = System.currentTimeMillis();
				if (time > timeoutAt) {
					RedisUtil.releaseLock(name);
					return false;
				}
				// 休眠200毫秒
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * @auth：pijunling
	 * @Time: 2019年5月16日 下午7:11:45
	 * @Descript:XXX 获取库存的特殊操作，不提倡放到这里 这里是已领取的库存
	 */
	public static Integer getProStock(String key) {
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Long>(Long.class));
		Object stocks = redisTemplate.opsForValue().get(key);
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		if (null == stocks) {
			return 0;
		}
		return Integer.parseInt(stocks.toString());
	}
	
	public static long incr(String key, int delta) {
		ValueOperations operations = redisTemplate.opsForValue();
		Long incrId = operations.increment(key, delta);
		expire(key, 10, TimeUnit.HOURS);
		return incrId;
	}
	
	public static long incr(String key, int delta,Long timeout) {
		ValueOperations operations = redisTemplate.opsForValue();
		Long incrId = operations.increment(key, delta);
		expire(key, timeout, TimeUnit.MILLISECONDS);
		return incrId;
	}
	
	
	public static long decr(String key, int delta) {
		ValueOperations operations = redisTemplate.opsForValue();
		Long incrId = operations.decrement(key, delta);		
		return incrId;
	}

	public static void setValueInc(String key, Integer value) {
		redisTemplate.opsForValue().set(key, value.toString());
	}
}
```

## Redis 锁

```java
public static boolean lock(String name, Integer timeout, final Long expire) {
    if (name == null || "".equals(name)) {
        return false;
    }
    Long time = System.currentTimeMillis();
    // 设置默认值
    timeout = timeout == null ? 0 : timeout;
    Long timeoutAt = time + timeout * 1000;
    final String lockKey = "Lock:{" + name + "}";
    while (true) {
        Long lockResult = acquireLock(lockKey, expire);
        // 锁请求成功
        if (lockResult.compareTo(1L) == 0) {
            return true;
        }

        try {
            time = System.currentTimeMillis();
            if (time > timeoutAt) {
                RedisUtil.releaseLock(name);
                return false;
            }
            // 休眠200毫秒
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
	 * 请求锁
	 * 
	 * @param lockName
	 * @param expire
	 * @return
	 */
private static Long acquireLock(final String lockName, final long expire) {
    return redisTemplate.execute(new RedisCallback<Long>() {
        @Override
        public Long doInRedis(RedisConnection connection) throws DataAccessException {
            byte[] lockBytes = redisTemplate.getStringSerializer().serialize(lockName);
            boolean locked = connection.setNX(lockBytes, lockBytes);
            connection.expire(lockBytes, expire);
            if (locked) {
                return 1L;
            }
            return 0L;
        }
    });
}

// 释放锁
public static void releaseLock(String lockName) {
    final String lockKey = "Lock:{" + lockName + "}";
    final byte[] lockBytes = redisTemplate.getStringSerializer().serialize(lockKey);
    redisTemplate.execute(new RedisCallback<Object>() {
        @Override
        public Object doInRedis(RedisConnection connection) throws DataAccessException {
            connection.del(lockBytes);
            return null;
        }
    });
}
	
	
```

## RedisTemplate

