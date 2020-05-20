package climbStairs;

import java.util.List;

public class ClimbStairs {
    public int climbStairs(int n) {
        int[] memo= new int[n+1];
        return climb_stairs(0,n,memo);
    }

    private int climb_stairs(int stair, int n, int[] memo) {
        // terminal
        if (stair == n){
            return 1;
        }
        if (stair > n){
            return 0;
        }
        // process current logic
        if (memo[stair] > 0) return memo[stair];

        // dirll down
        memo[stair] = climb_stairs(stair+1,n,memo) +  climb_stairs(stair+2,n,memo);
        // restore current status
        return memo[stair];
    }

    public int climbStairs2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int f1=1,f2=2,f3=f1+f2;
        for (int i=3;i<=n;i++){
            f3 = f1+f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }


}
