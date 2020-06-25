package com.zhoukang.algorithms.list;

public interface List<T> extends Iterable<T>{
    void add(T data);

    void add(int pos, T data);

    T delete(T data);

    void update(T data, T newData);

    T get(int pos);

    void reversal();

    boolean checkRing();

    T deleteLast(int pos);

    T getMid();
}
