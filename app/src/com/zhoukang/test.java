package com.zhoukang;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args){
        //System.out.println(test(0));
        //System.out.println(test(1));
//        String str = new String("abc");
//        String str1 = "abc";
//        System.out.println (str == str1);
       //test();
        LRUCache cache1 = new LRUCache(2);
        cache1.put(9,2);
        cache1.put(2,1);
        cache1.put(1,1);
        cache1.put(2,3);
        cache1.put(4,1);
        System.out.println("" + cache1.get(1));
        System.out.println("" + cache1.get(2));
//                ["LRUCache","put","put","put","put","get","get"]
//[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
    }

    private static void test() {
        String str = new StringBuilder("abc").append("def").toString();//堆空间
        System.out.println(str == str.intern()); //true    intern()复制"abcdef"至常量池
    }

    public static int test(int b){
        int x = 0;
        try{
            x = 2/b;
            return x;
        } catch (Exception e){
            x = 3;
            return x;
        } finally {
            x = 4;
           throw new RuntimeException("test");
           //return x;
        }
    }
}

class LRUCache {
    static class Node{
        Node(int key, int value){
            this.key = key;
            this.val = value;
        }
        int key;
        int val;
        Node next = null;
        Node pre = null;
    }

    Node head = null;
    Node tail = null;
    Map<Integer, Node> cache = new HashMap<Integer, Node>();
    int size;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node != null){
            deleteNode(node);
            appendNode(node);
            return node.val;
        }
        return -1;
    }

    private void appendNode(Node node) {
        node.pre = null;
        node.next = head;
        if (head == null){
            tail = node;
        } else {
            head.pre = node;
        }
        head = node;
    }

    private void deleteNode(Node node) {
        if (capacity == 1){
            head = null;
            tail = null;
            return;
        }
        if (node.pre != null){ //node != head
            node.pre.next = node.next;
        } else {
            head = head.next;
        }
        if (node.next != null){
            node.next.pre = node.pre;
        } else {
            tail = tail.pre;
        }
    }

    public void put(int key, int value) {
       Node node = cache.get(key);
       if (node != null){
           node.val = value;
           deleteNode(node);
           appendNode(node);
           return;
       }
       node = new Node(key, value);
       if (size == 0){
           appendNode(node);
           cache.put(key, node);
           size++;
           return;
       }
       if (size>=capacity){
           cache.remove(tail.key);
           deleteNode(tail);
           size--;
       }
       size++;
       cache.put(key, node);
       appendNode(node);
    }
}

