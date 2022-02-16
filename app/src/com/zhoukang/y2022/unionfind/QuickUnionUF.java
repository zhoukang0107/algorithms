package com.zhoukang.y2022.unionfind;

public class QuickUnionUF {
    private int[] roots;
    public QuickUnionUF(int N){
        this.roots = new int[N];
        for (int i=0;i<N;i++){
            this.roots[i] = i;
        }
    }

    public int findRoot(int i){
        int index = i;
        while (index != roots[index]){
            index = roots[index];
        }
        //优化，将所有元素指向root节点
        while (index != roots[i]){
            int t = roots[i];
            roots[i] = index;
            i = t;
        }
        return index;
    }

    public boolean connected(int p, int n){
        return findRoot(p) == findRoot(n);
    }

    public void union(int p, int q){
        int qRoot = findRoot(p);
        int pRoot = findRoot(p);
        roots[qRoot] = pRoot;
    }




    public static void main(String[] args){

    }
}
