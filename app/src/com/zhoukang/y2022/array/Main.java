package com.zhoukang.y2022.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        int[] arr1 = {5,3,1,7,43,2,7,83,2, 8};
        sumN1(arr1, 10);
        sumN2(arr1, 10);
    }

    /**
     * 1、获取数组中和等于n的两个元素下标
     */
    public static void sumN1(int[] arr, int n){
        /**
         * 暴力算法
         */
        System.out.println("暴力算法");
        for (int i = 0; i<arr.length-1; i++){
            int v = n - arr[i];
            for (int j = i+1; j<arr.length; j++){
                if (arr[j] == v){
                    System.out.printf("%d + %d = %d\n", arr[i], arr[j], n);
                    System.out.printf("arr[%d] + arr[%d] = %d\n", i, j, n);
                    return;
                }
            }
        }
    }
    public static void sumN2(int[] arr, int n){
        /**
         * Set
         */
        System.out.println("借助Set");
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i<arr.length; i++){
            int v = n - arr[i];
            Integer index = map.get(v);
            if (null == index){
                map.put(arr[i], i);
            } else {
                System.out.printf("%d + %d = %d\n", v, arr[i], n);
                System.out.printf("arr[%d] + arr[%d] = %d\n", index, i, n);
                return;
            }
        }
    }

}
