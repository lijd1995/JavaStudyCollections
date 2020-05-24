>  本文章已收录到 https://github.com/lijd1995/JavaStudyCollections/tree/master/Algrithm

## 题目链接

二叉树最大深度：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
二叉树最小深度：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/

## 解读题意

两道非常相似的题，都是求二叉树的深度，一个是求最大深度，另一个是求最小深度。这里的深度指的是从根节点到叶子节点。

**注意**：叶子节点是指没有子节点的节点。

很关键的一句话，当求最小深度的时候，这句话成了一个决定性因素。请大家在心中默背 300 遍。

## 解题思路

### 二叉树最大深度

求根节点到叶子节点的最大深度，肯定不能一个一个去查，得从根节点往叶子节点进行**递归**。

分别递归节点的左子树和右子树，求得左子树的深度和右子树的深度。

返回 `Math.max(左子树深度，右子树深度) + 1`，就是当前二叉树的最大深度。

### 二叉树最小深度

同理，求二叉树的最小深度也需要使用到递归。

此刻优秀的你肯定会想到直接返回 `Math.min(左子树深度，右子树深度) + 1`，但是始终不能 AC 它，为啥呢？

还记得我让你注意的点没有？如果没记住，我带你回顾一遍。

> **注意**：叶子节点是指没有子节点的节点。

比如树是下面的结构，最小深度是 1 还是 2？

![](https://user-gold-cdn.xitu.io/2020/5/23/1723fe103cad54ca?w=203&h=147&f=png&s=4626)

当然是 2，因为根节点有右节点，所以它就不是叶子节点，记住题意，是到叶子节点的最大深度。

因此我们需要判断当前节点的左节点或者右节点是否为空，如果有一个为空，则将左子树和右子树的深度相加，结果再 +1。存在节点为空，该节点的深度肯定是 0 。所以两个深度相加其实返回的是节点不为空的深度。

> Talk is cheap. Show me the code.

## Java 题解

### 二叉树最大深度

```java
public int maxDepth(TreeNode root) {
    if (root == null){
        return 0;
    }
    int leftLength = maxDepth(root.left);
    int rightLength = maxDepth(root.right);
    return Math.max(leftLength,rightLength)+1;
}
```

代码简化一下

```java
public int maxDepth(TreeNode root){
    return root == null?0:Math.max(maxDepth(root.left),maxDepth(root.right))+1;
}
```

#### 复杂度分析

- **时间复杂度**

遍历树的每个节点，时间复杂度 O(n).

- **空间复杂度**

递归栈的层数，空间复杂度 O(n).

### 二叉树最小深度

```java
 public int minDepth(TreeNode root) {
     if (root == null) return 0;
     int leftDepth = minDepth(root.left);
     int rightDepth = minDepth(root.right);
     return root.left == null || root.right == null?leftDepth + rightDepth + 1:Math.min(leftDepth,rightDepth)+1;
 }
```

光头哥题解

```java
public int minDepth(TreeNode root) {
    if (root == null) return 0;
    int L = minDepth(root.left), R = minDepth(root.right);
    return 1 + (Math.min(L, R) > 0 ? Math.min(L, R) : Math.max(L, R));
}
```

#### 复杂度分析

- **时间复杂度**

遍历树的每个节点，时间复杂度 O(n).

- **空间复杂度**

递归栈的层数，空间复杂度 O(n).

## 总结

这两道题不难理解，但细节很重要，我再三说一次。

> **注意**：叶子节点是指没有子节点的节点。

不要因为细节的问题，让别人以为你是一个不细心的人，一定要严谨。

关注公众号：**小李不秃**，带你简单高效刷 LeetCode

