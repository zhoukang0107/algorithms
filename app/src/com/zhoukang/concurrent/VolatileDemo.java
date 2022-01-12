package com.zhoukang.concurrent;

import com.zhoukang.algorithms.list.List;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;

public class VolatileDemo {
    //volatile可见性
    private static /*volatile*/ boolean running = true;
    private static volatile int count = 0;
    public static void main(String[] args){
        //volatile保证线程可见性
        test();
        //禁止指令重排序
        test1();
        //DCL单例

        //不具有原子性
        test3();
    }

    private static /*synchronized*/ void m(){
        for (int i=0;i<10000;i++){
            count++;
        }
    }

    private static void test3() {

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i=0;i<10;i++) {
            threads.add(new Thread(VolatileDemo::m,"thread - "+ i));
        }

        threads.forEach( t ->{
            t.start();
        });
        threads.forEach( t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("count = " + count);
    }

    private static void test1() {
        //反证法



    }

    private static void test() {
        //volatile保证线程可见性
        new Thread( () -> {
            while (running){

            }
            System.out.println("thread end");
        }
        ).start();

        try {
            Thread.sleep(1000);
            running = false;
            System.out.println("main thread end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
