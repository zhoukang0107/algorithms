package com.zhoukang.algorithms.queue;


import java.util.*;

public class Main {

    public static void main(String[] agrs){
        CircularQueue queue = new CircularQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);
    }
}


//使用栈实现队列
class StackQueue{
    Stack<Integer> input;
    Stack<Integer> output;
    /** Initialize your data structure here. */
    public StackQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        input.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (output.isEmpty()){
            while (!input.isEmpty()){
                output.push(input.pop());
            }
        }
        return output.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (output.isEmpty()){
            while (!input.isEmpty()){
                output.push(input.pop());
            }
        }
        return output.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return output.isEmpty() && input.isEmpty();
    }
}

//使用队列实现栈
class QueueStack {

    Queue<Integer> input;
    Queue<Integer> output;
    /** Initialize your data structure here. */
    public QueueStack() {
        input = new LinkedList();
        output = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        input.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while (!input.isEmpty()){
            output.offer(input.poll());
        }
        return output.poll();
    }

    /** Get the top element. */
    public int top() {
        while (!input.isEmpty()){
            output.offer(input.poll());
        }
        return output.element();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return output.isEmpty()&& input.isEmpty();
    }
}

class ArrayQueue{
    Integer[] elements;
    int count = 0;
    int head = 0;
    int tail = 0;
    public ArrayQueue(){
        elements = new Integer[8];
    }
    public void enqueue(Integer value) {
        if (tail >= elements.length){
            if (head <=0){
                Integer[] newArr = new Integer[2*elements.length];
                System.arraycopy(elements, head, newArr, 0,  count);
                elements = newArr;
            } else {
                System.arraycopy(elements, head, elements, 0, count);
            }
            head = 0;
            tail = head + count;
        }
        elements[tail] = value;
        tail++;
        count++;
    }

    public Integer dequeue(){
        if (count == 0){
            return null;
        }
        Integer v = elements[head];
        head ++;
        count--;
        return v;
    }
}

class CircularQueue{
    Integer[] elements;
    int count = 0;
    int head = 0;
    int tail = 0;
    public CircularQueue(){
        elements = new Integer[8];
    }

    public void enqueue(Integer value) {
        if (count == 0){
            elements[0] = value;
            head = 0;
            tail = 1;
            count++;
            return;
        }
        if (count == elements.length){
            //扩容
            Integer[] newArr = new Integer[2*elements.length];
            if (head<tail){
                System.arraycopy(elements,head, newArr , 0, count);
                elements = newArr;
            } else {
                System.arraycopy(elements,head, newArr , 0, elements.length-head);
                System.arraycopy(elements,0, newArr , count-tail, tail);
                elements = newArr;
            }
            head = 0;
            tail = count;
        }
        int index = tail%elements.length;
        elements[index] = value;
        tail=index;
        tail++;
        count++;
       /* if (head>=tail){
            int index = tail%elements.length;
            elements[index] = value;
            tail=index;
            tail = (tail+1)%elements.length;
            count++;
        } else {
            elements[tail] = value;
            tail++;
            count++;
        }*/
    }

    public Integer dequeue(){
        if (count == 0){
            return null;
        }
        Integer v = elements[head];
        head = (head + 1) % elements.length;
        count--;
        return v;
    }
}