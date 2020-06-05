package com.zhoukang.algorithms.base;


import edu.princeton.cs.algs4.In;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        // write your code here
        In in = new In();

    }

    /**
     *
     * @param key
     * @param a  数组默认从小到大排序
     * @return
     */
    public static int rank(int key, int[] a){
        int lo = 0;
        int hi = a.length;
        while (lo <= hi){
            int mid = (lo + hi) / 2;
            if (a[mid] > key){
                hi = mid - 1;
            } else if (a[mid] <key){
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

