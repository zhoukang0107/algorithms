package com.zhoukang.y2022;

public class Single {
    public  volatile static Single sInstance = null;
    public static Single getInstance(){
        if (sInstance == null){
            synchronized (Single.class){
                if (sInstance == null){
                    sInstance = new Single();
                }
            }
        }
        return sInstance;
    }
}
