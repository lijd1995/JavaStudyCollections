> 本文章已收录到 [Github](https://github.com/lijd1995/JavaStudyCollections/tree/master/Algrithm)

题目链接：[LeetCode 22. 生成括号](https://leetcode-cn.com/problems/generate-parentheses)

## 解题思路：

- n 代表生成括号的对数，那么左括号和右括号加起来的数量应该是 2*n 个。
- 首先我们先把所有括号的情况排列组合出来。
- 然后在根据条件对代码进行优化。

## 所有括号的情况排列组合出来

这就是排列组合的问题，选择左括号和右括号，加起来总共是 2*n 个。

**代码如下**

```java
public List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<>();
    recur(0,2*n,"");
    return list;
}

// level 层级
// n 左右括号总共数量
// str 拼接的字符串
private void recur(int level, int n, String str) {
    // terminal 终结条件
    if (level == n && str.length() == n){
        System.out.println(str);
        return;
    }
    // 左右括号二选一
    recur(level+1,n,str+"(");
    recur(level+1,n,str+")");
}
```

## 对上述代码进行优化

我们能罗列出所有的组合了，那就加一些条件优化一下上面的代码。条件如下：

- 有效的括号是左右括号一一对应。有一个左括号就要有一个右括号。
- 第一个括号一定是左括号。
- 左括号和右括号的数量都是 n。
- 当左括号的数量大于右括号的数量，才加右括号。

通过这几个条件，我们可以写出来伪代码

```java
recur(int left,int right,int n,Strin str,List<String> list){
	// terminal 终结条件
	if(left == n && right == n){
		list.add(new String(str));
		return;
	}
	// 加左括号
	if(left < n){
		left +1,str+"(";
	}
	// 加右括号
	if(right < left){
		right + 1，str +")";
	}
}
```

通过伪代码，我们已经知道递归的这块应该怎么写了，那就直接开干吧。

```java
public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        recur(0,0,n,"",list);
        return list;
    }
    private static void recur(int left,int right,int n,String str,List<String> list) {
        if (left == n && right == n){
            list.add(str);
            return;
        }
        if(left < n)
            recur(left+1,right,n,str+"(",list);
        // 加右括号
        if(right < left)
            recur(left,right+1,n,str+")",list);
    }
```

## 复杂度分析

### 时间复杂度 

O(2^2n) ->O(2^n) 

### 空间复杂度

使用递归，调用递归栈的层数，也就是 O(n)

## 优秀题解

> 我们不要为了 AC 而写算法，写完了还需要通过看国外站找到优秀的题解

### phython

光头哥写的代码，我觉得该学一下 phython 了，有点简洁。

```java
def generateParenthesis(self, n, open=0):
    if n > 0 <= open:
        return ['(' + p for p in self.generateParenthesis(n-1, open+1)] + \
               [')' + p for p in self.generateParenthesis(n, open-1)]
    return [')' * open] * (not n)
```



## 总结

这是一道经典的递归题，类似于这种题型还有很多，我会在后面总结出这种题型的类似性。