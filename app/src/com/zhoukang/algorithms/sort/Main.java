package com.zhoukang.algorithms.sort;

public class Main {

    public static void main(String[] args){
        int a[] = {43,23,12,54,65,23,768,23,767};
        heapSort(a);
        //quickSort(a, 0, a.length-1);
        printArray(a);
    }
    private static void printArray(int[] a) {
        System.out.println("\n******************************************************");
        for (int i = 0;i<a.length;i++){
            System.out.print(a[i] + ", ");
        }
        System.out.println("\n******************************************************");
    }

    public static void heapSort(int[] arr){
        if (arr.length<2){
            return;
        }
        for (int i=arr.length/2;i>0;i--){
            adjustHeap(arr, i, arr.length);
        }

        for (int i=1;i<arr.length;i++){
            int temp = arr[1];
            arr[1] = arr[arr.length- i];
            arr[arr.length-i] = temp;
            adjustHeap(arr, 1, arr.length - i);
        }
    }

    private static void adjustHeap(int[] arr, int i, int length) {
        int child;
        if (2*i+1<length){
            child = arr[2*i]>arr[2*i+1]?2*i:2*i+1;
        } else {
            child = 2*i;
        }

        int parent = i;
        while (child<length){
            if (arr[parent]>=arr[child]){
                break;
            }
            int t = arr[parent];
            arr[parent] = arr[child];
            arr[child] = t;
            parent = child;
            if (2*parent>=length){
                break;
            } else if (2*parent == length-1){
                child = 2*parent;
            } else {
                child = arr[2*parent]>arr[2*parent+1]?2*parent:2*parent+1;
            }
        }
    }

    public static void quickSort(int[] arr, int start, int end){
        if (start>=end){
            return;
        }
        int value= arr[start];
        int head = start;
        int tail = end;
        while (head<tail){
            //
            while (arr[tail]>=value&&tail>head){
                tail--;
            }
            if (tail == head){
                break;
            }
            arr[head] = arr[tail];
            head++;
            while (arr[head] < value&& head<tail){
                head++;
            }
            if (head==tail){
                break;
            }
            arr[tail] = arr[head];
            tail--;
        }
        arr[head] = value;
        quickSort(arr, start, head-1);
        quickSort(arr, tail+1, end);
    }

}
