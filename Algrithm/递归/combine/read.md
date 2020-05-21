> 本文章已收录到 [Github](https://github.com/lijd1995/JavaStudyCollections/tree/master/Algrithm)

题目链接：[LeetCode 77. 组合](https://leetcode-cn.com/problems/combinations/)

## 相似题型
- [LeetCode 22. 括号生成](https://zhuanlan.zhihu.com/p/141599884)
- [LeetCode 46. 全排列](https://zhuanlan.zhihu.com/p/141899658)

## 解题思路
- 这道题思路和括号生成和全排列很类似。从 1 到 n 个数，选择 k 个数进行排列。
- 括号生成我们可以选择左括号或者右括号，全排列我们可以先选择哪个数，或者选择另外一个数。
- 那这道题就是第一个 我们可以选择 1，也可以是 2 或者是 3。那我们就可以使用回溯的方式去求解（回溯也可以理解为穷举）
- 但是通过回溯的方法，会走一些行不通的路，比如 k 是 4，n 是 5 。如果从 3 开始，就是 3 4 5 ，数量是 3 个，不满足条件了。
- 我们就要开始**剪枝**，如何不让它走重复的路呢？for 循环的终止条件由 n -> n-k+1 。这个 k 每下一层就要传递 k-1，表示当前列表还需要 k-1 个数。
 
## 我的题解

### 回溯
   ```java
   private  List<List<Integer>> res = new ArrayList<>();
   public  List<List<Integer>> combine(int n, int k) {
       if (n <= 0 || k <= 0 || n < k) return res;
       recur(n,k,1,new ArrayList<>());
       return res;
   }
   private  void recur(int n, int k, int begin, List<Integer> list) {
       if (list.size() == k){
           res.add(new ArrayList<>(list));
           return;
       }
       for (int i=begin;i<=n;i++){
           list.add(i);
           recur(n,k,i+1,list);
           list.remove(list.size()-1);
       }
   }
```
### 回溯+剪枝
```java
private  List<List<Integer>> res = new ArrayList<>();
public  List<List<Integer>> combine(int n, int k) {
    if (n <= 0 || k <= 0 || n < k) return res;
    recur(n,k,1,new ArrayList<>());
    return res;
}
private  void recur(int n, int k, int begin, List<Integer> list) {
    if (k == 0){
        res.add(new ArrayList<>(list));
        return;
    }
    // n-k+1 剪枝条件
    for (int i=begin;i<=n-k+1;i++){
        list.add(i);
    // 传递 k-1
        recur(n,k-1,i+1,list);
        list.remove(list.size()-1);
    }
}
```
## 复杂度分析

### 时间复杂度
数量 n 个，循环 k 层。时间复杂度 O(n^k)。剪枝的时间复杂度比这个低很多。因为剪去了无用的分支。
### 空间复杂度
使用递归，调用递归栈的层数，也就是 O(n)

## 优秀题解

> 我们不要为了 AC 而写算法，写完了还需要通过看国外站找到优秀的题解
### Java
 C(n,k)=C(n-1,k-1)+C(n-1,k).题解来自：[A short recursive Java solution based on C(n,k)=C(n-1,k-1)+C(n-1,k)](https://leetcode.com/problems/combinations/discuss/27019/A-short-recursive-Java-solution-based-on-C(nk)
 
 使用递归，思路也很好。
```java
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        if (k == n || k == 0) {
            List<Integer> row = new LinkedList<>();
            for (int i = 1; i <= k; ++i) {
                row.add(i);
            }
            return new LinkedList<>(Arrays.asList(row));
        }
        List<List<Integer>> result = this.combine(n - 1, k - 1);
        result.forEach(e -> e.add(n));
        result.addAll(this.combine(n - 1, k));
        return result;
    }
}
```
## 总结
这道题也很经典，可以使用多种解题思路，回溯、递归、动态规划等。参考：[77. Combinations](https://leetcode.wang/leetCode-77-Combinations.html)。

