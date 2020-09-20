package com.zhoukang.algorithms.list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 列表基本功能：增、删、改、查、大小
 * 1、单链表
 * 2、双向列表
 * 3、循环列表
 * 4、循环双向列表
 * 5、单链表反转
 * 6、链表中环的检测
 *
 *
 *
 *
 */
public class Main {
    public static void main(String[] agrs){

    }

    //单链表反转
    public static <T> Node<T> reversal(Node<T> head){
        if (head == null || head.next == null){
            return head;
        }
        Node<T> pre = null;
        while (head != null){
            Node<T> node = head.next;
            head.next = pre;
            pre = head;
            head = node;
        }
        head = pre;
        return head;
    }

    //1 -> 2 -> 3 -> 4 -> 5   2 -> 1 -> 4->3 ->5
    //列表中相邻元素两两翻转
    public static <T> void reversalAdjacent(Node<T> head){
        if (head == null || head.next == null){
            return;
        }
        Node<T> pre = null;
        Node<T> node = head;
        while (node != null && node.next != null){
            if (pre == null){
                Node n = node.next;
                node.next = n.next;
                n.next = node;
                pre = node;
                node = node.next;
            } else {
                pre.next = node.next;
                node.next = node.next.next;
                pre.next.next = node;
                pre = node;
                node = node.next;
            }
        }
    }

    //链表中环的检测
    public static <T> boolean hasRing0(Node<T> head){
        Set<Node<T>> set = new HashSet<>();
        Node<T> node = head;
        while (node != null){
            if (set.contains(node)){
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;

    }

    public static <T> boolean hasRing1(Node<T> head){
        Node<T> node = head;
        int count = 0;
        while (node != null){
            Node<T> n = head;
            for (int i=0; i<count ;i++){
                if (n == node){
                    return true;
                }
                n = n.next;
            }
            count++;
            node = node.next;
        }
        return false;
    }

    public static <T> boolean hasRing2(Node<T> head){
        Node<T> h = head;
        Node<T> l = head;
        while (h != null && h.next !=null && h.next.next != null){
            h = h.next.next;
            l = l.next;
            if (h == l){
                return true;
            }
        }
        return false;
    }

    //求链表的中间结点
    public static <T> Node<T> getMiddle(Node<T> head){
        Node<T> h = head;
        Node<T> l = head;
        while (h != null && h.next !=null && h.next.next != null){
            h = h.next.next;
            l = l.next;
        }
        return l;
    }

    //两个有序的链表合并
    public static Node mergeSortList(Node<Integer> head1, Node<Integer> head2){
        Node<java.lang.Integer> head = null;
        Node<java.lang.Integer> last = null;
        while (head1 != null && head2 !=null){
            if (head1 == null){
                if (head == null){
                    head = head2;
                    return head;
                }
                last.next = head2;
                return head;
            }
            if (head2 == null){
                if (head == null){
                    head = head1;
                    return head;
                }
                last.next = head1;
                return head;
            }
            if (head1.data < head2.data){
                if (head == null){
                    head = head1;
                    last = head1;
                } else {
                    last.next = head1;
                }
                head1 = head1.next;
            } else {
                if (head == null){
                    head = head2;
                    last = head2;
                } else {
                    last.next = head2;
                }
                head2 = head2.next;
            }
        }
        return head;
    }

    //1 2
    //1 2 3 4 5 6 7 8
    //删除链表倒数第n个结点
    public static <T> T indexOfLastN(Node<T> head, int n){
        if (head == null){
            return null;
        }
        Node<T> pre = null;
        Node<T> f = head;
        int count = 0;
        while (f != null){
            if (count < n){
                f = f.next;
            } else {
                f = f.next;
                if (pre == null){
                    pre = head;
                } else{
                    pre = pre.next;
                }
            }
            count++;
        }
        T value = null;
        if (count >= n){
            if (count == n){
                value = head.data;
                head = head.next;
            } else {
                value = pre.next.data;
                pre.next = pre.next.next;
            }

        }
        return value;
    }

    //如果字符串是通过单链表来存储的，那该如何来判断是一个回文串呢？你有什么好的解决思路呢？相应的时间空间复杂度又是多少呢？
    public static <T> void isPlalindrome(Node<T> head){

    }

}


//1、单列表
class LinkedList<T>{
    Node<T> first;
    Node<T> last;
    public LinkedList(){
        first = null;
        last = null;
    }
    public void add(T value){
        Node<T> node = new Node<>();
        node.data = value;
        if (first == null){
            first = node;
            last = node;
            return;
        }
        last.next = node;
        last = node;
    }

    public T delete(int pos){
        if (first == null){
            return null;
        }
        if (pos == 0){

        }

        return null;
    }

    public T update(int pos, T value){
        Node<T> n = first;
        int index = 0;
        T v = null;
        while (n!=null){
            if (index == pos){
                v = n.data;
                n.data = value;
                break;
            }
            index++;
            n = n.next;
        }
        return v;
    }

    public T get(int pos){
        Node<T> n = first;
        int index = 0;
        while (n!=null){
            if (index == pos){
                return  n.data;
            }
            index++;
            n = n.next;
        }
        return null;
    }

    public int size(){
        Node<T> n = first;
        int index = 0;
        while (n!=null){
            index++;
            n = n.next;
        }
        return index;
    }
}

class DNode<T>{
    public T data;
    public DNode<T> next;
    public DNode<T> pre;
}
//2、双向列表
class BiDirectionalLinkedList<T>{
    DNode<T> first;
    DNode<T> last;
    public BiDirectionalLinkedList(){
        first = null;
        last = null;
    }
    public void add(T value){
        DNode<T> node = new DNode<>();
        node.data = value;
        if (first == null){
            first = node;
            last = node;
            return;
        }
        last.next = node;
        node.pre = last;
        last = node;

    }

    public T delete(int pos){
        if (first == null){
            return null;
        }
        DNode<T> n = first;
        int index = 0;
        while (n != null){
            if (pos == index){
                if (n.next == null){
                    n.pre.next = null;
                    return n.data;
                }
                n.next.pre = n.pre;
                n.pre.next = n.next;
            }
            index++;
            n = n.next;
        }
        return null;
    }

    public T update(int pos, T value){
        DNode<T> n = first;
        int index = 0;
        T v = null;
        while (n!=null){
            if (index == pos){
                v = n.data;
                n.data = value;
                break;
            }
            index++;
            n = n.next;
        }
        return v;
    }

    public T get(int pos){
        DNode<T> n = first;
        int index = 0;
        while (n!=null){
            if (index == pos){
                return  n.data;
            }
            index++;
            n = n.next;
        }
        return null;
    }

    public int size(){
        DNode<T> n = first;
        int index = 0;
        while (n!=null){
            index++;
            n = n.next;
        }
        return index;
    }
}

//循环列表

//双向循环列表


//使用数组实现列表
class ArrayList{
    int head = 0;
    int tail = 0;
    int[] elements;
    public ArrayList(){
        elements = new int[16];
    }

    public void add(){

    }

}

//基于链表实现LRU缓存淘汰算法
class LinkedLRUCache<T>{
    DNode<T> head = null;
    DNode<T> tail = null;
    public void put(T t){
        DNode<T> node = new DNode<>();
        node.data = t;
        if (head == null){
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.pre = node;
            head = node;
        }
    }
}
//基于数组实现LRU缓存淘汰算法

