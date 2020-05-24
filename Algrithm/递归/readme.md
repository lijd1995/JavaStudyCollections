> **文本已收录至我的GitHub算法系列，欢迎Star**： https://github.com/lijd1995/JavaStudyCollections/tree/master/Algrithm

本周总共做了七道 LeetCode 题目

这些题都是递归的相关题目，并不难，我在这里稍微总结一下。

## 回溯

括号生成、全排列、全排列Ⅱ、组合等题目都用到了**回溯**的思想，什么是回溯呢？就是当前这一步往下走行不通了，返回到上一步，选择其它的路接着走。比如括号生成，我可以选择左括号，那我左括号不行了，我就调过头选择右括号。其实回溯就是递归，只是递归的其中一种分类。

更简单的说回溯就是穷举，而穷举肯定是指数级的时间复杂度，所以我们就需要进行**剪枝**，减少重复计算，降低时间复杂度。

**练习题目**

- [22. 括号生成](https://zhuanlan.zhihu.com/p/141599884)
- [46. 全排列](https://zhuanlan.zhihu.com/p/141899658)
- [47. 全排列Ⅱ](https://zhuanlan.zhihu.com/p/142169852)
- [77. 组合](https://mp.weixin.qq.com/s?__biz=MzIzMTQxNjgwOA==&mid=2247483730&idx=1&sn=5f099c7a9da34725d23962472f65058d&chksm=e8a537d5dfd2bec3f4ed939c84ebc30748c757b93f34d5ee0ea15dee4424fdc2146d08d8e071&token=81941226&lang=zh_CN#rd)

## 动态规划

递归是自上而下的计算，那动态规划就是自下而上的计算，通过动态规划，可以将递归的时间复杂度从指数级别降低到线性级别。动态规划的题目会在下周开始练习，请多多关注。

**练习题目**

- [70. 爬楼梯](https://zhuanlan.zhihu.com/p/142426778)

## 树的算法

一般树的题目都是与二叉树相关的，树如果想要遍历肯定需要递归。对于节点有三种遍历的方式：前序遍历、中序遍历和后序遍历，通过这几种遍历方式和题目中的要求进行结合，就可以得到最终结果。比如验证二叉搜索树，通过二叉搜索树的排列特性+中序遍历，就可以判断是否满足二叉搜索树的要求。

**练习题目**

- [98. 验证二叉搜索树](https://mp.weixin.qq.com/s?__biz=MzIzMTQxNjgwOA==&mid=2247483735&idx=1&sn=10f34fa99c307cf1ff3e2b21d494002c&chksm=e8a537d0dfd2bec66035d9459e9ca474799e05f81948ce47d9e956b066ebb688ba821e8ae5c3&token=81941226&lang=zh_CN#rd)
- [104&111.二叉树最大深度和最小深度](https://zhuanlan.zhihu.com/p/143037906)

## 总结

算法中有个重要的思想就是递归，通过递归延伸出分治、回溯、动态规划、BFS 和 DFS 等算法。所以掌握递归非常关键。

动态规划算法可以降低时间的复杂度，但是思考起来还是比较复杂，我会通过下面几道题，带大家了解动态规划。

- [1143. 最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)
- [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/)
- [53. 最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)
- [198. 打家劫舍](https://leetcode-cn.com/problems/house-robber/)
- [买卖股票的最佳时机系列问题](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/#/description)



如果大家想要**实时**关注我更新的文章以及分享的干货的话，微信搜索**小李不秃**