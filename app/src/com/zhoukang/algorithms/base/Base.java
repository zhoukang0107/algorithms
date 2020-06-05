package com.zhoukang.algorithms.base;

public class Base {
    public static void main(String[] args) {
        // write your code here
    }

    /**
     * 计算p和q的最大公约数
     */
    public static int gcb(int p, int q){
        if (q ==0 ){
            return p;
        }
        return gcb(q, p%q);
    }

    /**
     * 计算一个整数的绝对值
     */
    public static int abs(int x){
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }

    /**
     * 计算一个浮点数的绝对值
     */
    public static float abs(float x){
        if (x < 0.0){
            return -x;
        } else {
            return x;
        }
    }

    /**
     * 判断一个数是否是素数
     */
    public static boolean isPrime(int N){
        if (N < 2){
            return false;
        }
        for (int i = 2 ; i*i <= N ; i++){
            if (N%i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 计算平方根（牛顿迭代法）
     */
    public static double sqr(double c){
        if (c < 0){
            return Double.NaN;
        }
        double err = 1e-15;
        double t = c;
        while (Math.abs(t - c/t) > err*t){
            t = (c/t +t) /2.0;
        }
        return t;
    }

    /**
     * 计算调和级数
     */
    public static double H(int N){
        double sum = 0.0;
        for (int i = 1; i<=N; i++){
            sum += 1.0 / i;
        }
        return sum;
    }


}

