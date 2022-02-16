package com.zhoukang.y2022;

import java.util.Collection;
import java.util.Map;

public class Util {
    public static <T> void print(Collection<T> collection){
        for (T c : collection){
            System.out.println(c);
        }
    }

    public static <T, N> void print(Map<T, N> map){
        for (T key : map.keySet()){
            System.out.println(key + " -> " + map.get(key));
        }
    }

    public static <T> void print(T[] arr){
        for (T a : arr){
            System.out.println(a);
        }
    }
}
