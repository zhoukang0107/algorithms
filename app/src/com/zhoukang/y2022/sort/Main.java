package com.zhoukang.y2022.sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        /**
         * 冒泡
         */
        int[] arr = {2,3,1,4,6,8,2,34,6,2};
        //bubble(arr);
        //select(arr);
        //insert(arr);
        //quick(arr);
        //merge(arr);
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 冒泡
     */
    public static void bubble(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            boolean sorted = true;
            for (int j=0;j<arr.length-i-1; j++){
                if (arr[j] > arr[j+1]){
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                    sorted = false;
                }
            }
            if (sorted){
                return;
            }
        }
    }

    /**
     * 选择
     */
    public static void select(int[] arr){
        for (int i=0;i<arr.length;i++){
            int max = 0;
            for (int j=1;j<arr.length-i;j++){
                if (arr[max] < arr[j]){
                    max = j;
                }
            }
            if (max != arr.length-i-1){
                int t = arr[max];
                arr[max] = arr[arr.length-i-1];
                arr[arr.length-i-1] = t;
            }
        }
    }

    /**
     * 插入
     */
    public static void insert(int[] arr){
        for (int i=1;i<arr.length;i++){
            int t = arr[i];
            int j=i-1;
            for (;j>=0;j--){
                if (arr[j] > t){
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            if (j<i-1){
                arr[j+1] = t;
            }
        }

    }

    /**
     * 快速
     */
    public static void quick(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int begin, int end) {
        int v = arr[begin];
        int left = begin; //-1, 1, 2, 3, 0, 5
        int right = end;
        while (left<right){
            while (right > left && v<=arr[right]){
                right--;
            }
            if (left<right){
                arr[left] = arr[right];
                arr[right] = v;
                left++;
            }
            while (right>left && v >= arr[left]){
                left++;
            }
            if (left<right){
                arr[right] = arr[left];
                arr[left] = v;
                right--;
            }
        }
        if (left>begin){
            quickSort(arr, begin, left-1);
        }
        if (right<end){
            quickSort(arr, right+1, end);
        }
    }

    /**
     * 归并
     */
    public static void merge(int[] arr){
        mergeSort(arr, 0, arr.length-1);

    }

    private static void mergeSort(int[] arr, int begin, int end) {
        if (begin >= end){
            return;
        }
        int left1 = begin;
        int right1 = begin + (end - begin)/2;
        mergeSort(arr, left1, right1);
        int left2 = right1+1;
        int right2 = end;
        mergeSort(arr, left2, right2);
        mergeArr(arr, left1, right1, left2, right2);
    }

    private static void mergeArr(int[] arr, int left1, int right1, int left2, int right2) {
        int[] temp = new int[right2-left1 + 1];
        int index = 0;
        int i = left1,j = left2;
        while (i<=right1 && j<=right2){
            if (arr[i]<arr[j]){
                temp[index] = arr[i];
                i++;
            } else {
                temp[index] = arr[j];
                j++;
            }
            index++;
        }
        int k, m;
        if (i>right1){
            k = j;
            m = right2;
        } else{
            k = i;
            m = right1;
        }
        while (k<=m){
            temp[index] = arr[k];
            k++;
            index++;
        }
        for (i=0;i<temp.length;i++){
            arr[left1+i] = temp[i];
        }
    }

    /**
     * 堆排序
     */
    public static void heapSort(int[] arr){
        buildHeap(arr);
        for (int i=arr.length-1;i>0;i--){
            System.out.println(arr[i]);
            int t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;
            adjuestHead(arr, i - 1);
        }
    }

    private static void adjuestHead(int[] arr, int length) {
        int index = 0;
        while ((2 * index + 1)<=length){
            int max = getMaxChild(arr, index, length);
            if (arr[index] > arr[max]){
                int t = arr[index];
                arr[index] = arr[max];
                arr[max] = t;
                index = max;
            } else {
                break;
            }
        }
    }

    private static int getMaxChild(int[] arr, int parent, int length) {
        if (2*parent+2 <= length){
            return arr[2*parent + 1]<arr[2*parent + 2]? 2*parent + 1: 2*parent + 2;
        } else {
            return 2*parent+ 1;
        }
    }

    private static void buildHeap(int[] arr) {
        for (int i=1;i<arr.length; i++){
            int parent = (i-1)/2;
            int index = i;
            while (index > 0 && arr[parent]>=arr[index]){
                int t = arr[parent];
                arr[parent] = arr[index];
                arr[index] = t;
                index = parent;
                parent = (index-1)/2;
            }
        }
        System.out.println(arr[0]);
    }


}
