package com.zhoukang.algorithms;

import org.openjdk.jol.info.ClassLayout;

import java.io.*;
import java.time.Period;

public class Main {

    public static void main(String[] args) {
        // write your code here
        /*String str1 = new String("你好，我是Java");
        System.out.println(str1 == str1.intern()); //false*/

/*        String str2 = new StringBuilder("你好，我是Java").toString();
        System.out.println(str2 == str2.intern());  //false*/

      /* String str2 = new StringBuilder("你好，我是Java").append("Str").toString();
        System.out.println(str2 == str2.intern());  //true*/

     /*   String str2 = new StringBuilder("你好，我是JavaStr").toString();
        System.out.println(str2 == str2.intern());  //false

        String str3 = new StringBuilder("你好，我是Java").append("Str").toString();
        System.out.println(str3 == str3.intern());  //false
        */

    /*   String str3 = new StringBuilder("ja").append("va").toString();
        System.out.println(str3 == str3.intern());  //false*/


/*        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = "Hel" + "lo";  //常量池对象+常量池对象=常量池对象
        String s4 = "Hel" + new String("lo"); //常量池对象+堆对象=堆对象
        String s5 = new String("Hello");
        String s6 = s5.intern();
        String s7 = "H";
        String s8 = "ello";
        String s9 = s7 + s8;  //堆对象

        System.out.println(s1 == s2);  // true
        System.out.println(s1 == s3);  // true
        System.out.println(s1 == s4);  // false
        System.out.println(s1 == s9);  // false
        System.out.println(s4 == s5);  // false
        System.out.println(s1 == s6);  // true*/

        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);  //false

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4); //true

        serializableTest();
        unSerializableTest();

        exceptionTest();

        Supper son = new Son();
        son.test1();

    }

    private static void exceptionTest() {
        try {
            Class.forName("casasasas");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void unSerializableTest() {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("persion.txt"));
            Person person = (Person) inputStream.readObject();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void serializableTest() {
        Person person = new Person();
        person.name = "name";
        person.age = 10;
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("persion.txt"));
            outputStream.writeObject(person);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static class Supper{
        private void test1(){
            System.out.println("supper : test1");
        }

        private void voidtest2(){

        }
    }
    static class Son extends Supper{
        private void test1(){
            System.out.println("Son : test1");
        }

        private void voidtest2(){

        }
    }
   static class Person implements Serializable{
        String name;
        int age;
        public Person(){

        }

        final void xxage(){

        }
    }
/*
    static class SubPerson extends Person {
        String name;
        int age;
        public SubPerson(){

        }

        void xxage(){

        }
    }*/
}
