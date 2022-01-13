package com.zhoukang.y2022.stack;

import java.util.LinkedList;
import java.util.List;

public class MyQueue {
    private LinkedList<Integer> q1 = new LinkedList<>();
    private LinkedList<Integer> q2 = new LinkedList<>();
    public MyQueue() {

    }

    public void push(int x) {
        q2.add(x);
        while (!q1.isEmpty()){
            q2.add(q1.poll());
        }
        LinkedList<Integer> t = q2;
        q2 = q1;
        q1 = t;
    }

    public int pop() {
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
