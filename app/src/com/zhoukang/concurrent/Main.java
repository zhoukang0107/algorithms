package com.zhoukang.concurrent;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Main {
    public static void main(String[] args){
        test1();
        test2();
        System.out.println((1 + 2) + 3 + "4" + (5 + 6) + 7);
    }

    private static void test2() {



    }

    private static void test1() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // String str = new String("abs");
        Object str = new Object();
        System.out.println(ClassLayout.parseInstance(str).toPrintable());
        synchronized (str){
            System.out.println(ClassLayout.parseInstance(str).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(new Sync()).toPrintable());
        Object[] obts = new Object[6];
        System.out.println(ClassLayout.parseInstance(obts).toPrintable());
    }

    static class Sync extends Object{
        static int x = 100;
        int i = 4;

    }
}
