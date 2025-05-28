package com.zby.arithmetic;

// 求斐波那契数列的第 n 项，n <= 39。
public class Fib {

    private static int[] fib = new int[40];
    // 使用静态代码块，在类加载的时候做初始化操作，避免在每次调用方法时都进行计算
    static {
        fib[1] =1;
        for (int i = 2; i < fib.length; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
    }
    public static int Fibonacci(int n) {
        return fib[n];
    }


    public static void main(String[] args) {
        System.out.println(method(39));
        System.out.println(method1(5));
        System.out.println(Fibonacci(39));
    }



    // 时间复杂度O(n)
    public static int method1(int n) {
        int[] fib = new int[n + 1];
        int pre = 0;
        int next = 1;
        int fibs = 0;
        for (int i = 2; i <= n; i++) {
            fibs = pre + next;
            pre = next;
            next = fibs;
        }
        return fibs;
    }

    // 时间复杂度O(n)
    public static int method(int n) {
        int[] fib = new int[n + 1];
        if (n <= 1) return n;
        fib[1] = 1;
        for (int i = 2; i <= n; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
        return fib[n];
    }
}
