> 本文章已收录到 [Github](https://github.com/lijd1995/JavaStudyCollections/tree/master/Algrithm)

题目链接：[LeetCode 46. 全排列](https://leetcode-cn.com/problems/permutations)

## 解题思路
- 这种题想到的就是递归，从数组中先选出一个元素，剔除掉，再选择一个元素，再剔除，循环往复，是存在规律性的。

## 我的题解
```java
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // 进行递归
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        // 终结递归的条件
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
            return;
        } 
        // 循环做选择
        for(int i = 0; i < nums.length; i++){
            // 因为不重复，所以包含直接跳过
            if(tempList.contains(nums[i])) continue; // element already exists, skip
            // 添加元素
            tempList.add(nums[i]);
            // 进行下一层的递归
            backtrack(list, tempList, nums);
            // 把元素剔除，进行下一次的循环
            tempList.remove(tempList.size() - 1);
        }
    }
```
## 复杂度分析

### 时间复杂度
循环的情况类似于阶层 n*(n-1)*(n-2)...3*2*1 = n! -> O(n!)
### 空间复杂度
使用递归，调用递归栈的层数，也就是 O(n)

## 优秀题解

> 我们不要为了 AC 而写算法，写完了还需要通过看国外站找到优秀的题解
### phython
光头哥用了库函数
```phython
def permute(self, nums):
    return map(list, itertools.permutations(nums))
```

### java
这种方法理解不了，就还是使用递归吧。想要了解的可以自行 debug，这个思路还是不错的。
```java
public List<List<Integer>> permute(int[] num) {
    LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
    res.add(new ArrayList<Integer>());
    for (int n : num) {
        int size = res.size();
        for (; size > 0; size--) {
            List<Integer> r = res.pollFirst();
            for (int i = 0; i <= r.size(); i++) {
                List<Integer> t = new ArrayList<Integer>(r);
                t.add(i, n);
                res.add(t);
            }
        }
    }
    return res;
}
```

