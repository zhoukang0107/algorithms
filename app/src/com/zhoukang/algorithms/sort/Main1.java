package com.zhoukang.algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 排序算法需要关注的点：
 * 1、算法执行效率
 *    最好、最坏、平均情况时间复杂度
 *    时间复杂度的系数、常数、低阶
 *    比较次数和交换次数
 * 2、排序算法内存消耗
 * 3算法稳定性
 *
 * 冒泡排序：
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。如果不满足就让它俩互换。一次冒泡会让至少一个元素移动到它应该在的位置，重复n次，就完成了n个数据的排序工作。
 *
 * 插入排序：
 * 动态地往有序集合中添加数据，我们可以通过这种方法保持集合中的数据一直有序。
 * 首先，我们将数组中的数据分为两个区间， 已排序区间和未排序区间。初始已排序区间只有一个元素，就是数组的第一个元素。插入算法的核心思想是取未排序
 * 区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。重复这个过程，直到未排序区间中元素为空，算法结束。
 *
 * 选择排序：
 * 选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾。
 *
 * 插入排序与冒泡排序：
 * 冒泡排序不管怎么优化，元素交换的次数是一个固定值，是原始数据的逆序度。插入排序是同样的，不管怎么优化，元素移动的次数也等于原始数据的逆序度。
 * 但是，从代码实现上来看，冒泡排序的数据交换要比插入排序的数据移动要复杂，冒泡排序需要3个赋值操作，而插入排序只需要1个
 *
 * 上诉排序对于小规模数据的排序，用起来非常高效。但是在大规模数据排序的时候，这个时间复杂度还是稍微有点高，
 *
 * 希尔排序：
 *
 *
 * 归并排序：
 * 如果要排序一个数组，我们先把数组从中间分成前后两部分，然后对前后两部分分别排序，再将排好序的两部分合并在一
 * 起，这样整个数组就都有序了
 *
 *
 *
 *
 */
public class Main1 {

    public static void main(String[] agrs){
        int a[] = {43,23,12,54,65,23,768,23,767};
        //BubbleSort(a);
        //InsertionSort(a);
        //SelectionSort(a);
        //MergeSort(a, 0, a.length-1);
       // MergeSort(a);
        QuackSort(a);
        printArray(a);
        String res = longestPalindrome("abcda");
        System.out.println(res);
        System.out.println(isPalindrome(10));
        System.out.println(letterCombinations("23"));
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2},0));
    }


    public class ListNode {
      int val;
      ListNode next;
     ListNode(int x) { val = x; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode f = head;
        ListNode s = head;
        ListNode pre = null;
        int i = 0;
        while (f!=null){
            i++;
            if (i>n){
                pre = s;
                s = s.next;
            }
            f = f.next;
        }
        if (s==head){
            head = head.next;
        } else if (pre !=null){
            pre.next = s.next;
        }
        return head;
    }

    public boolean isValid(String s) {
        if (s==null||s==""){
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i=0;i<s.length();i++){
            Character c = s.charAt(i);
            Character top = stack.peek();
            switch(c){
                case ')':
                    if (top=='('){
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                    break;
                case ']':
                    if (top=='['){
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                    break;
                case '}':
                    if (top=='{'){
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                    break;
            }
        }
        if (stack.isEmpty()){
            return true;
        }
        return false;

    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums==null||nums.length<4){
            return res;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length-3;i++){
            for (int j=i+1;j<nums.length-2;j++){
                for (int m=j+1;m<nums.length-1;m++){
                    for (int n=m+1;n<nums.length;n++){
                        if (target-nums[i] - nums[j] - nums[m] - nums[n] == 0){
                            tryInsert(res, nums[i] , nums[j], nums[m], nums[n]);
                        }
                    }
                }
            }
        }
        return res;
    }

    static void  tryInsert(List<List<Integer>> list, int a , int b, int c, int d){
        List<Integer> t = new ArrayList<Integer>();
        if (list.size()<0){
            t.add(a);
            t.add(b);
            t.add(c);
            t.add(d);
            list.add(t);
            return;
        }
        for (List<Integer> s: list){
            if (s.get(0)==a&&s.get(1)==b&&s.get(2)==c&&s.get(3)==d){
                return;
            }
        }
        t.add(a);
        t.add(b);
        t.add(c);
        t.add(d);
        list.add(t);
    }

    public static List<String> letterCombinations(String digits) {
        char[][] d = {{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'},};
        List<String> res = new ArrayList<String>();
        if (digits==null||digits.length()<=0){
            return res;
        }
        if (digits.length()==1){
            char[] dd = d[digits.charAt(0)-'2'];
            for (int i=0;i<dd.length;i++){
                res.add(String.valueOf(dd[i]));
            }
            return res;
        }
        char[] dd = d[digits.charAt(0)-'2'];
        for (int i=0;i<dd.length;i++){
            char c = dd[i];
            List<String> sub = letterCombinations(digits.substring(1,digits.length()));
            for (String s : sub){
                res.add(c+s);
            }
        }
        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<strs[0].length();i++){
            char t = strs[0].charAt(i);
            for (int j=0;j<strs.length;j++){
                if (strs[j].length()<i){
                    return sb.toString();
                }
                if (strs[j].charAt(i) != t){
                    return sb.toString();
                }
            }
            sb.append(t);
        }
        return sb.toString();
    }
    public static String longestPalindrome(String s) {
        int length = s.length();
        for (int i=0;i<s.length();i++){
            for (int j=0;j<=i;j++){
                boolean flag = false;;
                int max = length - i + j;
                int index = 0;
                for (int k=j;k<=(j+max)/2;k++){
                    if (s.charAt(k)!=s.charAt(max-1-index)){
                        flag = true;
                        break;
                    }
                    index ++;
                }
                if (!flag){
                    return s.substring(j,max);
                }
            }
        }
        return null;

    }

    public static boolean isPalindrome(int x) {
        if (x<0){
            return false;
        }
        Stack<Integer> stack = new Stack();
        int t = x;
        while(t>0){
            stack.push(t%10);
            t = t/10;
        }

        while(x>0){
            if (stack.pop() != (x%10)){
                return false;
            }
            x = x/10;
        }
        return true;
    }

    private static void printArray(int[] a) {
        System.out.println("\n******************************************************");
        for (int i = 0;i<a.length;i++){
            System.out.print(a[i] + ", ");
        }
        System.out.println("\n******************************************************");
    }


    /**
     * 冒泡排序
     * 时间复杂度：O(n^2)
     * 最好情况（全部有序）：O(n)
     * 最坏情况（逆序）：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     *
     * @param a
     */
    public static void BubbleSort(int a[]){
        int temp;
        boolean flag = false;//如果未发生数据交换说明数据已经有序了
        for (int i=0;i<a.length-1 && !flag;i++){
            flag = true;
            for (int j=0;j<a.length-i-1;j++){
                if (a[j]>a[j+1]){
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = false;
                }
            }
        }
    }

    /**
     * 插入排序
     * 时间复杂度：O(n^2)
     * 最好情况（全部有序）：O(n)
     * 最坏情况（逆序）：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     * @param a
     */
    public static void InsertionSort(int a[]){
        int temp;
        for (int i=1;i<a.length;i++){
            temp = a[i];
            int j = i -1;
            for (;j>=0;j--){
                if (temp<a[j]){
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }
            a[j+1] = temp;
        }
    }

    /**
     * 选择排序：
     * 时间复杂度：O(n^2)
     * 最好情况：O(n^2)
     * 最坏情况：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定，交换元素时，造成相同元素位置变化
     * @param a
     */
    public static void SelectionSort(int[] a){
        int min;
        for (int i=0;i<a.length-1;i++){
            min = i;
            for (int j=i+1;j<a.length;j++){
                if (a[min]>a[j]){
                    min = j;
                }
            }
            int temp;
            if (min != i){
                temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
    }

    /**
     * 归并排序：
     * 时间复杂度：O(n^2)
     * 最好情况：O(n^2)
     * 最坏情况：O(n^2)
     * 空间复杂度：O(n)
     * 稳定性：不稳定，交换元素时，造成相同元素位置变化
     * @param a
     */
    public static void MergeSort(int[] a, int start, int end){
        if (start>=end){
            return;
        }
        int mid = (start + end)/2;
        MergeSort(a, start, mid);
        MergeSort(a, mid+1, end);
        MergeSort(a, start, mid, mid+1, end);
    }

    private static void MergeSort(int[] a, int start1, int end1, int start2, int end2) {
        int[] temp = new int[end1-start1 + end2-start2 + 2];
        int start = start1;
        int index = 0;
        while (start1<=end1&&start2<=end2){
            if (a[start1]>a[start2]){
                temp[index] = a[start2];
                start2++;
            } else {
                temp[index] = a[start1];
                start1++;
            }
            index++;
        }

        int s;
        int e;
        if (start1<=end1){
            s = start1;
            e = end1;
        } else {
            s = start2;
            e = end2;
        }

        for (;s<=e;s++){
            temp[index] = a[s];
            index++;
        }

        for (int i = 0;i<temp.length;i++){
            a[start]=temp[i];
            start++;
        }

    }

    public static void MergeSort(int[] a){
        int k = 1;
        while (k<a.length){
            MergeSort(a,k);
            k = 2*k;
        }
    }

    private static void MergeSort(int[] a, int k) {
        int[] temp = new int[a.length];
        int index = 0;
        int a1 = 0;
        int e1 = Math.min(a1 + k, a.length);
        int a2 = e1;
        int e2 = Math.min(a2 + k, a.length);
        while (a2<a.length){
            while (a1<e1&&a2<e2){
                if (a[a1]<=a[a2]){
                    temp[index++] = a[a1++];
                } else {
                    temp[index++] = a[a2++];
                }
            }
            int start;
            int end;
            if (a1<e1){
                start = a1;
                end = e1;
            } else {
                start = a2;
                end = e2;
            }
            while (start<end){
                temp[index++] = a[start++];
            }
            a1 = e2;
            e1 = a1 + k;
            a2 = e1;
            e2 = Math.min(a2 + k, a.length);
        }
        while (a1<a.length){
            temp[index] = a[a1++];
        }

        for (int i=0;i<temp.length;i++){
            a[i] = temp[i];
        }
    }


    /**
     * 快速排序
     * @param a
     */
    private static void QuackSort(int[] a) {
        quackSort(a, 0, a.length - 1);
    }

    private static void quackSort(int[] a, int start, int end) {
        if (start >= end){
            return;
        }

        int value = a[start];
        int index = start;
        int left = start + 1;
        int right = end;
        while (left <= right){
            while (a[right] >= value && right >= left){
                right--;
            }
            if (a[right]>=value){
                break;
            }
            a[left - 1] = a[right];
            index = right;
            right--;
            while (a[left] <= value && right >= left){
                left++;
            }
            if (a[left] <= value){
                break;
            }
            a[right+1] = a[left];
            index = left;
            left++;
        }
        a[index] = value;
        quackSort(a, start, index -1);
        quackSort(a, index+1, end);
    }


    private static void quackSort1(int[] a, int start, int end) {
        if (start >= end){
            return;
        }

        int value = a[start];
        int left = start;
        int right = end;
        while (left < right){
            while (a[right] >= value && right > left){
                right--;
            }
            if (right > left){
                a[left] = a[left];
                left++;
            }
            while (a[left] < value && right > left){
                left++;
            }
            if (right>left){
                a[right] = a[left];
                right--;
            }
        }
        a[right] = value;
        quackSort(a, start, right -1);
        quackSort(a, right+1, end);
    }
}