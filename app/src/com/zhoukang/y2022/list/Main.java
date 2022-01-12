package com.zhoukang.y2022.list;


import java.util.HashSet;
import java.util.Set;

class Node{
    int value;
    Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}


public class Main {
    public static void main(String[] args){
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        printList(node1);
        /**
         * 链表反转
         */
        Node n = reverseList(node1);
        printList(n);

        /**
         * 反转列表相邻节点
         */
        Node swap = swapNode(n);
        printList(swap);

        node1.next = node3;
        /**
         * 检测列表中是否有环
         */
        /**
         * 1、暴力
         */
        //boolean hasCycle = hasCycle1(swap);
        //System.out.println("暴力算法 hasCycle :" + hasCycle);
        /**
         * 2、借助Set缓存
         */
        boolean hasCycle = hasCycle2(swap);
        System.out.println("借助Set缓存 hasCycle :" + hasCycle);
        /**
         * 3、快慢指针
         */
        hasCycle = hasCycle3(swap);
        System.out.println("快慢指针 hasCycle :" + hasCycle);

    }



    private static boolean hasCycle3(Node node) {
        if (node == null || node.next == null){
            return false;
        }
        Node fast = node.next.next;
        while (fast != null && fast.next != null){
            if (node == fast){
                //System.out.println("Cycle :" + node.value);
                return true;
            }
            fast = fast.next.next;
            node = node.next;
        }
        return false;
    }

    /**
     * 借助Set缓存
     * @param node
     * @return
     */
    private static boolean hasCycle2(Node node) {
        Set<Node> ceched = new HashSet<>();
        while (node != null){
            if (ceched.contains(node)){
                System.out.println("Cycle :" + node.value);
                return true;
            }
            ceched.add(node);
            node = node.next;
        }
        return false;
    }

    /**
     * 暴力算法
     * @param node
     * @return
     */
    private static boolean hasCycle1(Node node) {
        while (node != null){
            Node n = node.next;
            while (n != null){
                if (n == node){
                    System.out.println("Cycle :" + node.value);
                    return true;
                }
                n = n.next;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 反转列表相邻节点
     * @param node
     * @return
     */
    private static Node swapNode(Node node) {
        if (node == null || node.next == null){
            return node;
        }
        Node newNode = null;
        Node pre = null;
        Node cur = node;
        while (cur != null && cur.next != null){
            Node next = cur.next.next;
            if (pre == null){
                newNode = cur.next;
            } else {
                pre.next = cur.next;
            }
            pre = cur;
            cur.next.next = cur;
            cur.next = next;
            cur = next;
        }
        return newNode;
    }

    private static void printList(Node node) {
        System.out.print("list =");
        while (node != null){
            System.out.print(" " + node.value);
            node = node.next;
        }
        System.out.print("\n");
    }

    /**
     * 列表反转  -> 将已有链表遍历插入新链表头
     * @param node
     * @return
     */
    private static Node reverseList(Node node) {
        Node newNode = null;
        while (null != node){
            Node n = node.next;
            node.next = newNode;
            newNode = node;
            node = n;
        }
        return newNode;
    }
}
