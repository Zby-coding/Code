package day07.homework.test;

import day07.homework.entiy.Phone;

public class Demo1 {
    public static void main(String[] args) {
        //创建手机对象
        Phone p = new Phone();
        //调用set方法赋值
        p.brand = "小米";
        p.price = 3998;
        p.color = "黑色";
        //调用打电话功能
        p.call();
        //调用发短信功能
        p.sendMessage();
    }
}