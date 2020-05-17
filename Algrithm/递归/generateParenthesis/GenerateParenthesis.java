package generateParenthesis;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        recur(0,0,n,"",list);
        return list;
    }

    private static void recur(int left,int right,int n,String str,List<String> list) {
        if (left == n && right == n){
            System.out.println(str);
            list.add(str);
            return;
        }
        if(left < n)
            recur(left+1,right,n,str+"(",list);
        // 加右括号
        if(right < left)
            recur(left,right+1,n,str+")",list);
    }

    public static void main(String[] args) {
        generateParenthesis(1);
    }
}
