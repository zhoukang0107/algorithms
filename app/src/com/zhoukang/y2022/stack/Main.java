package com.zhoukang.y2022.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args){
        /**
         * 判断指定字符串中括弧是否匹配
         */
        String str = "sahicsbfvyh(c[][][][[[]]]";
        boolean match = isMatch(str);
        System.out.println("match :" + match);
    }


    private static boolean isMatch(String str){
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('[',']');
        map.put('(',')');
        map.put('{','}');
        for (int i=0;i<str.length(); i++){
            Character c = str.charAt(i);
            if (map.containsKey(c)){
                stack.push(c);
            } else if (c == ']' || c == ')' || c == '}'){
                if (stack.empty() || c != map.get(stack.peek())){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.empty();
    }
}
