package com.zhoukang.y2022.sort;


import com.zhoukang.y2022.Single;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        int[] arr = new int[]{1,2,3,1,23,54,12,76,121,34,765,12};
        //quickSort(arr);
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        //健堆
        buildHeap(arr);
        //排序
        for (int i = 0;i<arr.length-1;i++){
            int v = arr[0];
            arr[0] = arr[arr.length - i -1];
            arr[arr.length - i-1] = v;
            rebuildHeap(arr, arr.length - i - 1);
        }
    }

    private static void rebuildHeap(int[] arr, int length) {
        int parent = 0;
        int maxChild = getMaxChildIndex(arr, parent, length);
        while (maxChild < length && arr[maxChild] > arr[parent]){
            int v = arr[parent];
            arr[parent] = arr[maxChild];
            arr[maxChild] = v;
            parent = maxChild;
            maxChild = getMaxChildIndex(arr, parent, length);
        }
    }

    private static int getMaxChildIndex(int[] arr, int parent, int length) {
        if (2 * parent + 2<length){
            return arr[2 * parent + 2] > arr[2 * parent + 1] ? 2 * parent + 2 : 2 * parent + 1;
        }
        return 2 * parent + 1;
    }

    private static void buildHeap(int[] arr) {
        for (int i=1; i<arr.length;i++){
            int child = i;
            int parent = (child-1)/2;
            do {
                if (arr[child] > arr[parent]){
                    int v = arr[child];
                    arr[child] = arr[parent];
                    arr[parent] = v;
                }
                child = parent;
                parent = (child-1)/2;
            } while (child != 0 && arr[parent] < arr[child]);
        }
    }

    static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int begin, int end) {
        int left = begin;
        int right = end;
        int value = arr[begin];
        while (left<right){
            while (left<right && value <= arr[right]){
                right--;
            }
            if (left<right){
                arr[left] = arr[right];
                left++;
            }
            while (left<right && value>= arr[left]){
                left++;
            }
            if (left < right){
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = value;
        if (begin < left-1){
            quickSort(arr, begin, left -1);
        }
        if (right+1 < end){
            quickSort(arr,right+1, end);
        }
    }
}
