package com.zhoukang.y2022.stack;

import java.util.Stack;

public class Main {
    public static void main(String[] args){
        /**
         * 判断指定字符串中括弧是否匹配
         */
        String str = "sahicsbfvyhc[][][][[[]]]";
        boolean match = isMatch(str);
        System.out.println("match :" + match);
    }


    private static boolean isMatch(String str){
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<str.length(); i++){
            Character c = str.charAt(i);
            if (c == '['){
                stack.push(c);
            } else if (c == ']'){
                if (stack.empty()){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.empty();
    }
}
