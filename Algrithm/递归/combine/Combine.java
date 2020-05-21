package combine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Combine {

    private static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || n < k){
            return res;
        }
        recur(n,k,1,new ArrayList<>());
        return res;
    }

    private static void recur(int n, int k, int begin, List<Integer> list) {
        if (list.size() == k){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i=begin;i<=n-(k-list.size())+1;i++){
            list.add(i);
            recur(n,k,i+1,list);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        combine(4,2);
    }
}
