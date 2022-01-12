package com.zhoukang.algorithms;

//评测题目: 无

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class CharProcessor{
    private static final int CORE_COUNT = Runtime.getRuntime().availableProcessors(); //cpu核数
    public static void main(String[] args){
        String str = "qqqwwweeerrrtttyyyuuuiiioooppp[[[";
        Map<Character, Integer> map = process(str);
        System.out.println(map);
    }
    public static Map<Character, Integer> process(final String str){
        int remainder = str.length()%CORE_COUNT;
        int count = str.length()/CORE_COUNT;
        Map<Character,Integer>[] maps = new HashMap[CORE_COUNT];
        final CountDownLatch latch = new CountDownLatch(CORE_COUNT);
        int start = 0;
        for (int i=0;i<CORE_COUNT;i++){
            int charLenth = count;
            if (i<remainder){
                charLenth = charLenth + 1;
            }
            final int finalLength = charLenth;
            final int finalStart= start;
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int end = Math.min(finalStart+ finalLength, str.length());
                    maps[finalI] = process(str, finalStart, end);
                    latch.countDown();
                }
            }).start();
            start += charLenth;
        }
        return null;

    }
    public static  Map<Character, Integer> process(String str, int start, int end){
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i=start;i<end;i++){
            Character c = str.charAt(i);
            Integer count = map.get(c);
            if (count == null){
                count = 1;
                map.put(c, count);
            } else {
                count++;
                map.put(c,count);
            }
        }
        return map;
    }

    public static Map<Character, Integer> mergeChars(Map<Character, Integer>[] maps){
        if (maps.length==1){
            return maps[0];
        }
        Map<Character, Integer> res = new HashMap<>(maps[0]);
        for (int i=1;i<maps.length;i++){
            mergeMap(res, maps[i]);
        }
        return res;
    }

    public static void mergeMap(Map<Character, Integer> res, Map<Character, Integer> src){
        for (Character key :src.keySet()){
            Integer count = res.get(key);
            if (count == null){
                count = src.get(key);
                res.put(key, count);
            } else {
                count += src.get(key);
                res.put(key,count);
            }
        }
    }


}

