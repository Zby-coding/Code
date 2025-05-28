package com.zby.DesignPattern.singleton;
/**
 * 懒汉式1 线程不安全
 * 提供一个私有的成员变量 一个私有的构造器 和一个共有的返回单例的方法
 * 当调用getInstance()方法时才会创建单例对象
 * 存在线程安全问题
 * 当多个线程同时调用getInstance()方法时，可能会创建多个单例对象
 * 解决方法：
 * 1. 加锁
 * 2. 双重检查锁
 * 3. 静态内部类
 */

public class Singleton {
    private static Singleton singleton;
    private Singleton(){}
    // 加锁 线程安全 但是每次调用getInstance()方法都；会加锁 效率低
    public synchronized static Singleton getInstance(){
        if(singleton == null) singleton = new Singleton();
        return singleton;
    }
}
