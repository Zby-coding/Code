package com.zby.DesignPattern.wrapper;

/**
 * 装饰器模式
 *
 * 要求装饰类和被装饰类要有共同的父类
 * 要将装饰类作为参数传入被装饰类的构造方法中
 * 在类中扩展要增强的功能
 * 对于不需要扩展的功能直接调用父类的方法
 *
 */
public class Test {
    public static void main(String[] args) {
        new LiuDeHuaWrapper(new LiuDeHua()).dance();
        new LiuDeHuaWrapper(new LiuDeHua()).sing();

    }
}
interface star{
    void sing();
    void dance();
}
// 被装饰类
class LiuDeHua implements star{
    public void sing(){
        System.out.println("刘德华在唱歌");
    }
    public void dance(){
        System.out.println("刘德华在跳舞");
    }
}
// 装饰类
class LiuDeHuaWrapper implements star{
    private LiuDeHua liuDeHua;

    public LiuDeHuaWrapper(LiuDeHua liuDeHua) {
        this.liuDeHua = liuDeHua;
    }

    @Override
    public void sing() {
        System.out.println("早起练嗓子...");
        liuDeHua.sing();
        System.out.println("演唱结束,下班干饭...");
    }

    @Override
    public void dance() {
        liuDeHua.dance();
    }
}
