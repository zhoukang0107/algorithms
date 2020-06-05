package com.zhoukang.algorithms.base;

import edu.princeton.cs.algs4.StdOut;

public class Practice {
    public static void main(String[] args) {
        // write your code here
        //test1();
        test2();
    }

    /**
     *
     */
    private static void test2() {

    }

    /**
     * 1.1.6
     */
    static void test1(){
        int f= 0;
        int g = 1;
        for (int i=0;i<=15;i++){
            StdOut.println(f);
            f = f + g;
            g = f - g;
        }
    }
}
