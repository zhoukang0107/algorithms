package com.zhoukang.y2022.dp;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        return minimumTotal(triangle, 0, 0);
    }

    public int minimumTotal(List<List<Integer>> triangle, int pos, int depth) {
        if (depth == triangle.size()-1){
            return triangle.get(depth).get(pos);
        }
        return triangle.get(depth).get(pos) + Math.min(minimumTotal(triangle, pos, depth+1), minimumTotal(triangle, pos+1, depth+1));
    }
    public static int climbStairs(int n) {
        if (n <=2 ){
            return n;
        }
        int[] df = new int[n+1];
        df[0] = 1;
        df[1] = 1;
        df[2] = 2;
        for (int i=3;i<=n;i++){
            df[i] = df[i-1] + df[n-2];
        }
        return df[n];
    }
}