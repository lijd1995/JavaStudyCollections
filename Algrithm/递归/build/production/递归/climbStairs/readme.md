> 本文章已收录到 [Github](https://github.com/lijd1995/JavaStudyCollections/tree/master/Algrithm)

题目链接：[LeetCode 70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)

## 解题思路
- 爬一步或者爬两步，肯定可以使用递归。但会产生重复递归，时间复杂度高。
- 优化递归，将递归的结果缓存起来，来减少重复递归，降低时间复杂度，是剪枝的思想。
- 该题是典型的斐波那契数列。通过计算递推公式，我们发现爬 3楼就是爬 1楼的结果集+爬 2楼的结果集，爬 4楼就是爬 2楼的结果集+爬 3楼的结果集。我们就可以使用循环操作了。
是一种动态规划的思想。
- 推荐视频 [神奇的斐波那契数列](https://open.163.com/newview/movie/free?pid=M9HKRT25D&mid=M9HNA0UNO),数学可以求解，但还要能指出为什么。

## 我的题解

### 递归
```java
 public int climbStairs(int n) {
    if (n == 1) return 1;
    if (n == 2) return 2;
    return climbStairs(n-1) + climbStairs(n-2);
}
```

### 剪纸
```java
 public int climbStairs(int n) {
    int[] memo= new int[n+1];
    return climb_stairs(0,n,memo);
}
private int climb_stairs(int stair, int n, int[] memo) {
    if (stair == n) return 1;
    if (stair > n)  return 0;
    if (memo[stair] > 0) return memo[stair];
    memo[stair] = climb_stairs(stair+1,n,memo) +  climb_stairs(stair+2,n,memo);
    return memo[stair];
}
```

### 动态规划
```java
public int climbStairs2(int n) {
    if (n == 1) return 1;
    if (n == 2) return 2;
    int f1=1,f2=2,f3=f1+f2;
    for (int i=3;i<=n;i++){
        f3 = f1+f2;
        f1 = f2;
        f2 = f3;
    }
    return f3;
}
```
## 复杂度分析

### 时间复杂度
递归时间复杂度 O(2^n),树形递归的叶子节点个数。

剪纸，通过数组作为存储器，递归的时间复杂度降为 O(n)

动态规划，循环次数就是时间复杂度 O(n)
### 空间复杂度
使用递归，调用递归栈的层数，也就是 O(n)

动态规划没有开辟新的数据，空间复杂度 O(1)

## 优秀题解

> 我们不要为了 AC 而写算法，写完了还需要通过看国外站找到优秀的题解
### phython
```phython
def climbStairs(self, n):
    a = b = 1
    for _ in range(n):
        a, b = b, a + b
    return a
```

### java
光头哥的这段代码真的惊呆了我。
```java
public int climbStairs(int n) {
    int a = 1, b = 1;
    while (n-- > 0)
        // a = 1 b=1 -> a = 1,b = 2 -> a = 2,b = 3 -> a = 3,b = 5
        // 所以就有了下面这段代码，精彩
        a = (b += a) - a;
    return a;
}
```

### 总结
一道典型的递归题，经过优化得出两个方案，缓存+剪枝和动态规划，又根据空间复杂度，最优解还是动态规划。还有类似的题目：
- [91. 解码方法](https://leetcode.com/problems/decode-ways/)
- [62. 不同路径](https://leetcode-cn.com/problems/unique-paths/)

这两道题我也会在后面进行讲解。


