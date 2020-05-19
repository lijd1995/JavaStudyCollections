package permute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permute {
    public List<List<Integer>> permute1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        // 进行递归
        backtrack(list, new ArrayList<>(), nums,visited);
        return list;
    }
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums,boolean[] visited){
        // 终结递归的条件
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
            return;
        }
        // 循环做选择
        for(int i = 0; i < nums.length; i++){
            if (visited[i] || (i > 0 && visited[i] == visited[i-1] && !visited[i-1])) continue;
            visited[i] = true;
            tempList.add(nums[i]);
            backtrack(list, tempList, nums,visited);
            tempList.remove(tempList.size() - 1);
            visited[i] = false;
        }
    }


}
