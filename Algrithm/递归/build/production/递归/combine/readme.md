> 本文章已收录到 [Github](https://github.com/lijd1995/JavaStudyCollections/tree/master/Algrithm)

题目链接：[LeetCode 77.组合](https://leetcode-cn.com/problems/combinations/)

## 解题思路
- 全排列Ⅱ是上一题的加强版，在全排列不包含重复数字，这题的难度就是在包含重复数字。所以这道题关键点在于如何去重。
- 也可以选择全排列，通过 Set 进行去重，但时间复杂度高，不推荐。
- 这个是别人的解题思路，我也是看了视频才明白为什么需要 !visited[i-1]。[参考题解](https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/)

## 我的题解
```java
    public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);
            boolean[] visited = new boolean[nums.length];
            recur(nums,result,new ArrayList<Integer>(),visited);
            return result;
        }
    }
    private void recur(int[] nums, List<List<Integer>> result, ArrayList<Integer> list, boolean[] visited) {
        if (list.size() == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if (visited[i]) continue;
            // 通过回溯+剪枝
            if (i>0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            list.add(nums[i]);
            visited[i] = true;
            recur(nums,result,list,visited);
            visited[i] = false;
            list.remove(list.size()-1);
        }
    }
```
## 复杂度分析

### 时间复杂度
递归每层都要循环 nums.length 次，所以复杂度是 O(n^n)
### 空间复杂度
使用递归，调用递归栈的层数，也就是 O(n)

## 优秀题解

> 我们不要为了 AC 而写算法，写完了还需要通过看国外站找到优秀的题解
### phython
光头哥用了库函数
```phython
def permuteUnique(self, nums):
    return reduce(lambda perms, n: [p[:i] + [n] + p[i:]
                                    for p in perms
                                    for i in xrange((p + [n]).index(n) + 1)],
                  nums, [[]])
```

## 总结
对于递归中出现的重复问题，我们需要通过找到重复条件的规律，然后去剪掉它，就可以不做重复操作，增加代码的执行效率。

