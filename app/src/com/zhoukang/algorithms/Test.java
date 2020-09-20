package com.zhoukang.algorithms;

public class Test {

    public static void main(String[] args){
        System.out.println(new Test().abc());
    }

    private int abc() {
        try {
            return 0;
        } catch (Exception e){
            return 1;
        } finally {
            return 2;
        }
    }
}
