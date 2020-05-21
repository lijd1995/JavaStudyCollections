import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        recur(nums,result,new ArrayList<Integer>(),visited);
        return result;
    }

    private void recur(int[] nums, List<List<Integer>> result, ArrayList<Integer> list, boolean[] visited) {
        if (list.size() == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if (visited[i]) continue;
            if (i>0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            list.add(nums[i]);
            visited[i] = true;
            recur(nums,result,list,visited);
            visited[i] = false;
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        //permute(nums);
    }
}
