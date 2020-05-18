> 本文章已收录到 [Github](https://github.com/lijd1995/JavaStudyCollections/tree/master/Algrithm)

题目链接：[LeetCode 22. 全排列](https://leetcode-cn.com/problems/permutations)

## 优秀题解

> 我们不要为了 AC 而写算法，写完了还需要通过看国外站找到优秀的题解
### phython
```phython
def permute(self, nums):
    return map(list, itertools.permutations(nums))
```

