package com.zhoukang.algorithms.sort;

/**
 * 稳定排序使用：
 * 现在要给电商交易系统中的“订单”排序。订单有两个属性，一个是下单时间，另一个是订单金额。如果我们现在有10万条订单数据，我们希望按照金额从小到大对订单数据排序。对于金额相同的订单，我们希望按照下单时间从早
 * 到晚有序。对于这样一个排序需求，我们怎么来做呢？
 * 借助稳定排序算法，这个问题可以非常简洁地解决。解决思路是这样的：我们先按照下单时间给订单排序，注意是按照下单时间，不是金额。排序完成之后，我
 * 们用稳定排序算法，按照订单金额重新排序。两遍排序之后，我们得到的订单数据就是按照金额从小到大排序，金额相同的订单按照下单时间从早到晚排序的。
 * 为什么呢？
 * 稳定排序算法可以保持金额相同的两个对象，在排序之后的前后顺序不变。第一次排序之后，所有的订单按照下单时间从早到晚有序了。在第二次排序中，我们
 * 用的是稳定的排序算法，所以经过第二次排序之后，相同金额的订单仍然保持下单时间从早到晚有序。
 *
 *
 * 桶排序：
 * 要排序的元素有n个，我们把他们均匀的划分到m个桶内，每个桶内有k=n/m个元素，每个桶内部使用快速排序，时间复杂度尾O(k*log(k))
 * m个桶排序时间复杂度为O(nlog(k)),当桶的个数接近个数n时，log(n/m)是一个非常小的常量，这个时候桶排序的时间复杂度接近O(n)
 *
 *
 *
 */
public class Main {

    public static void main(String[] args){
        int a[] = {43,23,12,54,65,23,768,23,767};
        //heapSort(a);
        //quickSort(a, 0, a.length-1);
        //bubbleSort(a);
        //insertSort(a);
        //insertSort1(a);
        //selectSort(a);
        //quickSort1(a,0,a.length-1);
        //mergeSort(a,0, a.length-1);
        printArray(a);
    }
    private static void printArray(int[] a) {
        System.out.println("\n******************************************************");
        for (int i = 0;i<a.length;i++){
            System.out.print(a[i] + ", ");
        }
        System.out.println("\n******************************************************");
    }

   /* public static void mergeSort(int[] arr, int start, int end;){
        if (start>=end){
            return;
        }
        int mid = start + (end-start)/2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid+1, end);
        merge(arr, start, mid, mid+1, end);
    }

    private static void merge(int[] arr, int start1, int end1, int start2, int end2) {
        int[] temp = new int[end2-start1+1];
        int i = start1;
        int j = start2;
        int count = 0;
        for (i<=end1&&j<=end2){
            if (arr[i]<=arr[j]){
                temp[count] = arr[i];
                i++;
            } else {
                temp[count] = arr[j];
                j++;
            }
            count++;
        }

        while (i<=end1){
            temp[count] = arr[i];
            count++;
            i++;
        }

        while (j<=end2){
            temp[count] = arr[j];
            j++;
            count++;
        }
        count = 0;
        while (count<temp.length){
            arr[start1+count] = temp[count];
            count++;
        }
    }*/

    /**
     * 归并排序
     * 时间复杂度：O(nlog(n))
     * 空间复杂度：O(n)
     * 稳定性：稳定的排序算法
     */
    public static void mergeSort(int[] arr, int start, int end){
        if (start>= end){
            return;
        }
        int mid = start+(end-start)/2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid+1, end);
        merge(arr, start, mid, mid+1, end);
    }

    private static void merge(int[] arr, int start1, int end1, int start2, int end2) {
        int[] temp = new int[end2-start1+1];
        int i = start1;
        int j = start2;
        int count = 0;
        while (i<=end1&&j<=end2){
            if (arr[i]<=arr[j]){
                temp[count] = arr[i];
                i++;
            } else {
                temp[count] = arr[j];
                j++;
            }
            count++;
        }

        if (i>end1){
            while (j<=end2){
                temp[count] = arr[j];
                j++;
                count++;
            }
        }
        if (j>end2){
            while (i<=end1){
                temp[count] = arr[i];
                i++;
                count++;
            }
        }
        i = 0;
        while (i<count){
            arr[start1+i] = temp[i];
            i++;
        }
    }


    /**
     * 快速排序
     * 时间复杂度：O(nlog(n))
     * 空间复杂度：O(1)
     * 稳定性：非稳定排序
     */
    public static void quickSort1(int[] arr, int start, int end){
        if (start>= end){
            return;
        }
        int left = start;
        int right = end;
        int value = arr[start];
        while (left<right){
            while (arr[right]>=value&&left<right){
                right--;
            }
            if (left == right){
                break;
            }
            arr[left] = arr[right];
            left++;

            while (value>arr[left]&&left<right){
                left++;
            }
            if (left==right){
                break;
            }
            arr[right] = arr[left];
            right--;
        }
        arr[left] = value;
        quickSort1(arr, start, left-1);
        quickSort1(arr, left+1, end);

    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：稳定的排序算法
     */
    public static void bubbleSort(int[] arr){
        if (arr == null) return;
        for (int i=0;i<arr.length-1;i++){
            boolean isChanged = false;
            for (int j=0;j<arr.length-i-1;j++){
                if (arr[j]>arr[j+1]){
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                    isChanged = true;
                }
            }
            if (!isChanged){
                break;
            }
        }
    }

    /**
     * 插入排序
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：稳定的排序算法
     */
    public static void insertSort(int[] arr){
        for (int i=1;i<arr.length;i++){
            for (int j=i-1;j>=0;j--){
                if (arr[j]>arr[j+1]){
                    int value = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = value;
                } else {
                    break;
                }
            }
        }
    }

    public static void insertSort1(int[] arr){
        for (int i=1;i<arr.length;i++){
            int value = arr[i];
            int j = i-1;
            for (;j>=0;j--){
                if (arr[j]>value){
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            if (i != j+1){
                arr[j+1] = value;
            }
        }
    }

    /**
     * 选择排序
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：非稳定排序
     */
    public static void selectSort(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            int max = 0;
            for (int j=0;j<arr.length-i;j++){
                if (arr[j] > arr[max]){
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
