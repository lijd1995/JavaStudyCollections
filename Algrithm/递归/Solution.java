import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> numsList = new ArrayList<>();
        for (int num:nums)  numsList.add(num); // 这块可以优化
        recur(0,numsList,new ArrayList<Integer>(),list);
        return list;
    }

    private static void recur(int level, List<Integer> numsList, ArrayList<Integer> list, List<List<Integer>> resultList) {
        if (numsList.size() == 0){
            resultList.add(list);
            return;
        }
        // 这块也可以优化
        for (int i=0;i<numsList.size();i++){
            list.add(numsList.get(i));
            Integer oldValue = numsList.remove(i);
            recur(level+1,numsList,new ArrayList<>(list),resultList);
            list.remove(list.size()-1);
            numsList.add(i,oldValue);
        }
    }

    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        permute(nums);
    }
}
