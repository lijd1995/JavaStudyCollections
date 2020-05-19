package permuteUnique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: 递归
 * @description:
 * @author: ljd
 * @create: 2020-05-19 11:45
 */
public class PermuteUnique {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        // 定义一个桶
        boolean[] visited = new boolean[nums.length];
        permute(result,new ArrayList<Integer>(),nums,visited);
        return result;
    }

    private static void permute(List<List<Integer>> result, ArrayList<Integer> current, int[] nums, boolean[] visited) {
        if (current.size() == nums.length){
            result.add(new ArrayList<>(current));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if (visited[i]) continue;
            // nums[i] == nums[i - 1] && !visited[i - 1] 回溯搜索 + 剪枝
            // 参考：https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
//                if (i > 0 && nums[i] == nums[i - 1]) continue;
            visited[i] = true;
            current.add(nums[i]);
            permute(result, current, nums, visited);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        permuteUnique(nums);
    }
}
