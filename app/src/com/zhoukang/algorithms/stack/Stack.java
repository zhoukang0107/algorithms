package com.zhoukang.algorithms.stack;

import com.zhoukang.algorithms.list.Node;

import java.util.Iterator;

public class Stack<T>  implements Iterable<T> {
    Node<T> head;

    public void push(T data){
        Node<T> item = new Node<>();
        item.data = data;
        item.next = head;
        head = item;
    }

    public T pop(){
        if (head == null){
            return null;
        }
        Node<T> node = head;
        head = head.next;
        return node.data;
    }


    @Override
    public Iterator<T> iterator() {
        return new StackIterator<T>(head);
    }

    static  class StackIterator<D> implements Iterator<D>{
        private Node<D> head;

        public StackIterator(Node<D> head) {
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public D next() {
            Node<D> node = head;
            head = head.next;
            return node.data;
        }
    }
}
