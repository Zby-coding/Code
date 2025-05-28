package com.zby.DesignPattern.singleton;

/**
 * 懒汉式 双重锁模式 线程安全
 */

public class Singleton2 {
    // volatile禁止指令重排
    private volatile static Singleton2 instance;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null)
                    instance = new Singleton2();
            }
        }
        return instance;
    }

}
