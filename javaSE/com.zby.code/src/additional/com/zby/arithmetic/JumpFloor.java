package com.zby.arithmetic;


public class JumpFloor {
    /**
     * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。
     * 求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     */
    public static void main(String[] args) {
        System.out.println(jumpFloor(4));
    }

    public static int jumpFloor(int n) {
        if(n<=2) return n;
        int result = 0, pre1 = 1, pre2 = 2;
        for (int i = 3; i <= n ; i++) {
            result = pre1 + pre2;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }


}
