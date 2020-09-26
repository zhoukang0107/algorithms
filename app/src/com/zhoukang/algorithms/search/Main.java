package com.zhoukang.algorithms.search;

public class Main {

    public static void main(String[] args){
        int[] arr = new int[]{0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9};
        System.out.println(bSearch(arr, 4));
        System.out.println(bSearchFirst(arr, 4));
        System.out.println(bSearchLast(arr, 4));
        System.out.println(bSearchFirstBiger(arr, 4));
        System.out.println(bSearchFirstSmaller(arr, 4));
        System.out.println(bSearchFirstBigerAndEqual(arr, 4));
        System.out.println(bSearchLastSmallerAndEqual(arr, 4));
        int[] arr2 = new int[]{0,1,2,3,4,5,6,7,8,9};
        System.out.println(bSearch(arr2, 4));
        System.out.println(bSearch(arr2, 0, arr.length-1,4));
    }

    public static int bSearch(int[] arr, int value){
        int low = 0;
        int high = arr.length-1;
        while (low<=high){
            int mind = low + (high-low)>>1;
            if (arr[mind]>value){
                high = mind-1;
            } else if (arr[mind]<value){
                low = mind+1;
            } else {
                return mind;
            }
        }
        return -1;
    }

    public static int bSearch(int[] arr, int low, int high, int value){
        if (low>high){
            return  -1;
        }
        int mind = low + (high-low)>>1;
        if (arr[mind] == value) {
            return mind;
        } else if (arr[mind] > value){
            return bSearch(arr, low, mind-1,value);
        } else {
            return bSearch(arr, mind+1, high, value);
        }
    }

    // 查找第一个值等于给定值的元素
    public static int bSearchFirst(int[] arr, int value){
        int low = 0;
        int high = arr.length-1;
        while (low<=high){
            int mind = low + (high-low)/2;
            if (arr[mind] > value){
                high = mind-1;
            } else if (arr[mind] < value){
                low = mind + 1;
            } else {
                if (mind == 0 || value > arr[mind-1]) return mind;
                high = mind-1;
            }
        }
        return -1;
    }


    // 查找最后一个值等于给定值的元素
    public static int bSearchLast(int[] arr, int value){
        int low = 0;
        int high = arr.length-1;
        while (low<=high){
            int mind = low + (high-low)>>1;
            if (arr[mind] > value){
                high = mind-1;
            } else if (arr[mind] < value){
                low = mind + 1;
            } else {
                if (mind == arr.length-1 || arr[mind+1] > value) return mind;
                high = mind+1;
            }
        }
        return -1;
    }

    // 查找第一个大于给定值的元素
    public static int bSearchFirstBiger(int[] arr, int value){
        int low = 0;
        int high = arr.length-1;
        while (low<=high){
            int mind = low + (high-low)/2;
            if (arr[mind] > value){
                if (mind == 0 ||arr[mind-1]==value) {
                    return high;
                }
                high = mind-1;
            } else {
                low = mind + 1;
            }
        }
        return -1;
    }
    // 查找最后一个小于给定值得元素
    public static int bSearchFirstSmaller(int[] arr, int value){
        int low = 0;
        int high = arr.length-1;
        while (low<=high){
            int mind = low + (high-low)/2;
            if (arr[mind] >= value){
                high = mind-1;
            } else {
                if (mind == 0 || arr[mind+1] >= value) return mind;
                low = mind + 1;
            }
        }
        return -1;
    }

    //变体三：查找第一个大于等于给定值的元素
    public static int bSearchFirstBigerAndEqual(int[] arr, int value){
        int low = 0;
        int heigh = arr.length - 1;
        while (low<=heigh){
            int mind = low + (heigh - low)/2;
            if (value<=arr[mind]){
                if (mind==0|| arr[mind-1]<value) return mind;
                heigh = mind - 1;
            } else {
                low = mind +1;
            }
        }
        return -1;
    }

    //变体四：查找最后一个小于等于给定值的元素
    public static int bSearchLastSmallerAndEqual(int[] arr, int value){
        int low = 0;
        int heigh = arr.length - 1;
        while (low <= heigh){
            int mind = low + (heigh-low)/2;
            if (value > arr[mind]){
                low = mind +1;
            } else {
                if (mind == 0|| arr[mind-1] <value) return mind;
                heigh = mind - 1;
            }
        }
        return -1;
    }
}
