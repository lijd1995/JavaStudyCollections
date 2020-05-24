> 本文章已收录到 [Github](https://github.com/lijd1995/JavaStudyCollections/tree/master/Algrithm)

题目链接：[98. 验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/)

## 解题思路
通过理解题意，我们知道二叉搜索树的条件是左子树小于当前节点，右子树大于当前节点。且左右子树都满足该特性。划重点，左子树，右子树。而不是左节点和右节点。

- 方法1：递归
    - 关于树的题目肯定可以用递归，那这个的参数有下面几个
        - 当前节点
        - 两个边界，lower 和 higher
    - 如果递归到左子树，根据二叉搜索树的条件，左子树的节点都该小于当前节点，所以传过去的 higher 就是当前节点的值。
    - 如果递归到右子树，根据二叉搜索树的条件，那右子树的节点都该大于当前节点，所以传过去的 lower 就是当前节点的值。
    - 递归终结条件就是递归到节点为 null。说明走到最深处都是满足二叉搜索树的逻辑。
    - 开始处理当前层逻辑，当前节点小于等于 lower 或者大于等于 higher，返回 false。
- 方法2：中序遍历
    - 二叉搜索树是一个升序排列的树，中序遍历是按照左中右。
    - 通过中序遍历将当前节点的值和上一个节点比较，如果小于等于上一个节点，就不满足升序的条件，返回 false。
## Java 题解

### 递归
```java
 public boolean isValidBST(TreeNode root) {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
}
public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
    if (root == null) return true;
    if (root.val >= maxVal || root.val <= minVal) return false;
    return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
}
```
### 中序遍历
```java
public class Solution {
    // 存储上一步结果
    private TreeNode prev = null;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        // 走左子树
        if(!isValidBST(root.left)) return false;
        if(prev != null && root.val <= prev.val) return false;
        // 当前节点
        prev = root;
        // 走右子树
        return isValidBST(root.right);
    }
}
```
## 复杂度分析

### 时间复杂度
遍历树的每个节点，时间复杂度 O(n)
### 空间复杂度
使用递归，调用递归栈的层数，也就是 O(n)

## 总结
对于树的题目，一定要想到递归，然后就是前序、中序和后序遍历。再通过剪枝的方式来降低时间复杂度。再往后还会有 BFS 和 DFS。记得关注小李不秃，带你更简单高效刷 Leetcode、


