package com.zhoukang.y2022.threads;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args){
        Resource resource = new Resource();
        new Thread(new Productor(resource)).start();
        new Thread(new Custormer(resource)).start();
    }

    static class Productor implements Runnable{
        private Resource resource;
        public Productor(Resource resource){
            this.resource = resource;
        }

        @Override
        public void run() {
            int count = 0;
            while (true){
                count++;
                resource.push("name: "  + count);
                if (count>=1000){
                    count = 0;
                }
            }
        }
    }

    static class Custormer implements Runnable{
        private Resource resource;
        public Custormer(Resource resource){
            this.resource = resource;
        }


        @Override
        public void run() {
            while (true){
                resource.pop();
            }
        }
    }

    static class Resource {
        private Lock lock = new ReentrantLock();
        private Condition pcondition = lock.newCondition();
        private Condition ccondition = lock.newCondition();
        private String[] resources = new String[10];
        private int count = 0;
        public void push(String name){
            lock.lock();
            try {
                while (count>=resources.length){
                    try {
                        pcondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("生产产品：" + name);
                resources[count] = name;
                count++;
                ccondition.signal();
            }finally {
                lock.unlock();
            }
        }

        public String pop(){
            lock.lock();
            try {
                while (count <= 0){
                    try {
                        ccondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String name = resources[count-1];
                System.out.println("消费产品：" + name);
                count--;
                pcondition.signal();
                return name;
            }finally {
                lock.unlock();
            }
        }

    }
}
