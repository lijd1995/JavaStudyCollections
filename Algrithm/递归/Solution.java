import java.util.*;

public class Solution {

    public static List<List<Integer>> permute(int[] nums) {
        List<Integer> numsList = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        for (int n:nums) numsList.add(n);
        recur(numsList,new ArrayList<Integer>(),set);
        ArrayList<List<Integer>> lists = new ArrayList<>(set);
        return lists;
    }

    private static void recur(List<Integer> numsList, ArrayList<Integer> temp, Set<List<Integer>> set) {
        // terminal
        if (numsList.size() == 0){
            set.add(new ArrayList<>(temp));
            return;
        }
        // process current logic
        for (int i=0;i<numsList.size();i++){
            temp.add(numsList.get(i));
            Integer oldValue = numsList.remove(i);
            recur(numsList,temp,set);
            temp.remove(temp.size()-1);
            numsList.add(i,oldValue);
        }
        // drill down

        // restore current status
    }

//    private void recur(int[] nums, ArrayList<Integer> temp, Set<List<Integer>> set) {
//        if (temp.size() == nums.length) {
//            list.add(new ArrayList<>(temp));
//            return;
//        }
//        for (int i=0;i<nums.length;i++){
//            if (i>0 && (nums[i] == nums[i-1])) continue;
//            temp.add(nums[i]);
//            recur(nums,temp,list);
//            temp.remove(temp.size()-1);
//        }
//    }


    public static void main(String[] args) {
        int[] nums = {1,1,2};
        permute(nums);
    }
}
