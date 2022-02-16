package com.zhoukang.y2022;

class Solution1 {
    public double myPow(double x, int n) {
        return pow(x, n);
    }

    public double pow(double x, long n){
        if (n==0){
            return 1;
        }
        if (n < 0){
            return 1/pow(x, -n);
        }
        double d = pow(x,  n/2);
        return n %2 == 0? d*d : x * d*d;
    }
}
public class Main {
    public static void main(String[] args) {
//        Solution1 s = new Solution1();
//        double d = s.myPow(1.00000, -2147483648);
        Solution2 s = new Solution2();
        s.maxProfit(1, new int[]{2,4,1});
    }

    static class Solution2 {
        public int maxProfit(int k, int[] prices) {
            int[][][] MP = new int[prices.length][k][2];
            for (int i=0;i<k;i++){
                MP[0][i][0] = 0;
                MP[0][i][1] = -prices[0];
            }
            int max = 0;
            for (int i=1;i<prices.length; i++){
                for (int j=1;j<k && j<i;j++){
                    MP[i][j][0] = Math.max(MP[i-1][j][0], MP[i-1][j-1][1] + prices[i]);
                    MP[i][j][1] = Math.max(MP[i-1][j][1], MP[i-1][j-1][0] - prices[i]);
                    max = Math.max(MP[i][j][0], MP[i][j][1]);
                }
            }
            return max;
        }
    }

class IntConsumer{
    void accept(int n){
        System.out.print(n);
    }
}

class DiningPhilosophers {
    boolean[] forksUsed = new boolean[5];

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        synchronized (this) {
            while (forksUsed[philosopher] || forksUsed[4 - philosopher]) {
                wait();
            }
            forksUsed[philosopher] = true;
            forksUsed[4 - philosopher] = true;
        }
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        synchronized (this) {
            forksUsed[philosopher] = false;
            forksUsed[4 - philosopher] = false;
        }
    }
}
}