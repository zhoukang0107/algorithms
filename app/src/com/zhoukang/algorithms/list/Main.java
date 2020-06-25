package com.zhoukang.algorithms.list;

import java.util.Iterator;

/**
 * LRU缓存淘汰算法
 *
 * 单列表：
 * 于数组比较，内存结构不同，增删改查效率不同
 * 数组在删除元素时，为了保持内存连续性，需要做大量数据搬移工作，时间复制度为O(n)，而列表在删除元素时不存在内存连续性问题，只需要删除对应节点，效率较高，时间复杂度时O(1);
 * 数组随机访问效率O(1),列表随机访问时间复杂度O(n)
 *
 * 循环列表：
 * 与单列表比较，循环列表从列尾到列头比较方便，适合处理具有环形结构数据，比如约瑟夫问题
 *
 * 双向列表
 * 方便查找前驱节点，LinkedHashMap应用
 * 删除指定指针指向的节点，时间复杂度为O(1)
 * 空间换时间
 *
 * 双向循环列表
 *
 *
 * 数组与列表：
 * 1、数组简单易用，在实现上使用的是连续的内存空间，可以借助CPU的缓存机制，预读数组中的数据，所以访问效率更高。而链表在内存中并不是连续存储，所以对CPU缓存不友好，没办法有效预读。
 * 2、数组的缺点是大小固定，一经声明就要占用整块连续内存空间。如果声明的数组过大，系统可能没有足够的连续内存空间分配给它，导致“内存不足（out ofmemory） ”。如果声明的数组过小，则可能出现不够用的情况。这时只能再申请一个更大的内存空间，把原数组拷贝进去，非常费时。链表本身没有大小的限制，天然地支持动态扩容，这也是它与数组最大的区别。
 * 3、如果你的代码对内存的使用非常苛刻，那数组就更适合你。因为链表中的每个结点都需要消耗额外的存储空间去存储一份指向下一个结点的指针，所以内存消耗会翻倍。而且，对链表进行频繁的插入、删除操作，还会导致频繁的内存申请和释放，容易造成内存碎片，如果是Java语言，就有可能会导致频繁的GC（Garbage Collection，垃圾回收）。
 * 4、实际的开发中，针对不同类型的项目，要根据具体情况，权衡究竟是选择数组还是链表
 * 5、Java中的ArrayList容器，也可以支持动态扩容啊？我们上一节课讲过，当我们往支持动态扩容的数组中插入一个数据时，如果数组中没有空闲空间了，就会申请一个更大的空间，将数据拷贝过去，而数据拷贝的操作是非常耗时的
 *
 *
 * 问题：
 * 1、LRU缓存淘汰算法实现
 * 思路：我们维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。
 *      1.如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
 *      2.如果此数据没有在缓存链表中，又可以分为两种情况：
 *      如果此时缓存未满，则将此结点直接插入到链表的头部；
 *      如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
 * 时间复杂度O(n)
 * LRU优化：散列表
 *
 * 2、如何判断一个字符串是否是回文字符串的问题，我想你应该听过，我们今天的思题目就是基于这个问题的改造版本。如果字符串是通过单链表来存储的，那该如何来判断是一个回文串呢？你有什么好的解决思路呢？相应的时间空间复杂度又是多少呢？
 *
 * 3、单列表翻转
 *
 * 4、链表中环检测
 *
 * 5、两个有序链表合并
 *
 * 6、删除链表倒数第n个节点
 *
 * 7、求链的中间表结点
 *
 */
public class Main {
    public static void main(String[] args){
        /*SingleList<String> list = new SingleList<>();
        list.add("0123");
        list.add("1234");
        list.add("2345");
        list.add("3456");
        list.add("4567");
        list.add("8901");
        list.add("5678");
        list.add("7890");
        list.add("9012");
        System.out.println(" ring ————> " + list.checkRing());*/
        /*list.deleteLast(7);
        printList(list);
        list.deleteLast(0);
        printList(list);
        list.deleteLast(3);
        printList(list);
        list.delete("7890");
        printList(list);
        list.add("asdf");*/
        /*list.add(0,"0asdf");
        list.add(8,"10asdf");*/
        /*System.out.println(" mid ————> " + list.getMid());
        printList(list);
        list.reversal();
        printList(list);*/

        Node<String> head1 = new Node<>();
        head1.data = "9";
        Node<String> node1 = new Node<>();
        head1.next = node1;
        node1.data = "7";
        Node<String> node2 = new Node<>();
        node1.next = node2;
        node2.data = "5";
        node1 = new Node<>();
        node2.next = node1;
        node1.data = "3";
        node2 = new Node<>();
        node1.next = node2;
        node2.data = "1";
        node2.next = null;
        Node<String> head2 = new Node<>();
        head2.data = "8";
        node1 = new Node<>();
        head2.next = node1;
        node1.data = "6";
        node2 = new Node<>();
        node1.next = node2;
        node2.data = "4";
        node1 = new Node<>();
        node2.next = node1;
        node1.data = "2";
        node2 = new Node<>();
        node1.next = node2;
        node2.data = "0";
        node2.next = null;
        Node<String> list = mergeSortList(head1, head2);
        printList(list);
    }

    private static void printList(Node<String> list) {
        int index = 0;
        while (list!=null){
            System.out.println(index + " ————> " + list.data);
            list = list.next;
        }
    }

    /**
     * 两个有序链表合并
     * @param head1
     * @param head2
     * @return
     */
    private static Node<String> mergeSortList(Node<String> head1, Node<String> head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        Node<String> list = null;
        Node<String> curr = null;
        Node<String> temp;
        while (head1 != null && head2 != null){
            int res = head1.data.compareTo(head2.data);
            System.out.println(head1.data + " ---- > " + head2.data + "  = " + res);
            if (res>0){
                temp = head1;
                head1 = head1.next;
            } else {
                temp = head2;
                head2 = head2.next;
            }
            if (list == null){
                list = temp;
                curr = temp;
            } else {
                curr.next = temp;
                curr = curr.next;
            }
        }
        if (head1 != null){
            curr.next = head1;
        } else {
            curr.next = head2;
        }
        return list;
    }

    private static void printList(Iterator<String> list) {
        int index = 0;
        for (Iterator<String> it = list; it.hasNext(); ) {
            String value = it.next();
            System.out.println(index + " ————> " + value);
            index++;
        }
    }


}


