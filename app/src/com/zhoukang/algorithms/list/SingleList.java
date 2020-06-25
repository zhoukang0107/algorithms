package com.zhoukang.algorithms.list;


import java.util.Iterator;

public class SingleList<T> implements List<T>{
    Node<T> first;
    Node<T> last;
    @Override
    public void add(T data) {
        Node<T> node = new Node<>();
        node.data = data;
        node.next = null;
        if (last == null){
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        //last.next = first;//循环列表
    }

    @Override
    public void add(int pos, T data) {
        Node<T> node = new Node<>();
        node.data = data;
        node.next = null;
        if (pos == 0){
            node.next = first;
            first = node;
            return;
        }
        int c = 0;
        Node<T> pre = first;
        Node<T> f = first;
        while (f!=null){
            pre = f;
            f = f.next;
            c++;
            if (c == pos){
                node.next = f;
                pre.next = node;
                if (f == null){
                    last = node;
                }
                break;
            }
        }
    }

    @Override
    public T delete(T data) {
        if (first == null){
            return null;
        }
        Node<T> curr = first;
        Node<T> pre = first;
        Node<T> hit =null;
        while (curr!=null){
            if (curr.data.equals(data)){
                hit = curr;
                break;
            }
            pre = curr;
            curr = curr.next;
        }
        if (hit == null){
            return null;
        }

        if (hit == first){
            if (first == last){
                last = null;
            }
            first = first.next;
        } else {
            if (hit==last){
                last = pre;
            }
            pre.next = hit.next;

        }
        return hit.data;
    }

    @Override
    public void update(T data, T newData) {
        Node<T> node = first;
        while (node!= null){
            if (node.data.equals(data)){
                node.data = newData;
                break;
            }
        }
    }

    @Override
    public T get(int pos) {
        Node<T> curr = first;
        int count = 0;
        while (curr != null){
            if (count == pos){
                return curr.data;
            }
            count++;
            curr = curr.next;
        }
        return null;
    }

    /**
     * 单列表翻转
     */
    @Override
    public void reversal() {
        if (first == last){
            return;
        }
        Node<T> curr = first.next;
        Node<T> pre = first;
        pre.next = null;
        Node<T> temp;
        while (curr != null){
            temp = curr;
            curr = curr.next;
            temp.next = pre;
            pre = temp;
        }
        last = first;
        first = pre;
    }

    /**
     * 链表中环检测
     * @return
     */
    @Override
    public boolean checkRing() {
        if (first==null || first.next == null){
            return false;
        }
        Node<T> f = first;
        Node<T> s = first;
        while (f != null){
            if ((f = f.next)==null){
                return false;
            }
            if (f == s || f.next == s){
                return true;
            }
            f = f.next;
            s = s.next;
        }
        return false;
    }

    /**
     * 删除链表倒数第n个节点
     * @param pos
     * @return
     */
    @Override
    public T deleteLast(int pos) {
        if (pos<0){
            return null;
        } else if (pos == 0){
            return last.data;
        }
        Node<T> f = first;
        Node<T> s = first;
        Node<T> pre = first;
        int c = 0;
        while (f != null){
            if (c > pos){
                pre = s;
                s = s.next;
            }
            f = f.next;
            c++;
        }
        if (c <= pos) {
            return null;
        } else if (c == pos+1){
            first = first.next;
        } else {
            pre.next = s.next;

        }
        return s.data;
    }

    /**
     * 求链的中间表结点
     * @return
     */
    @Override
    public T getMid() {
        if (first == null){
            return null;
        } else if (first.next == null){
            return first.data;
        }
        Node<T> f = first;
        Node<T> s = first;
        while (f != null){
            if ((f = f.next) != null){
                f = f.next;
            } else {
                break;
            }
            s = s.next;
        }
        return s.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator<T>(first);
    }

    public static class SIterator<T> implements Iterator<T>{
        Node<T> node;
        SIterator(Node<T> node){
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T data = node.data;
            node = node.next;
            return data;
        }
    }
}
