package day07.homework.entiy;
/*
定义手机类，手机有品牌(brand),价格(price)和颜色(color)三个属性，有打电话call()和sendMessage()两个功能。

        定义测试类，在主方法中创建对象，直接给对象的属性赋值。

        调用对象的两个功能，打印效果如下：

        ```
        正在使用价格为3998元黑色的小米手机打电话....
        正在使用价格为3998元黑色的小米手机发短信....
        ```

         训练提示

        1. 类中的属性就是成员变量，类中的行为功能就是成员方法。

         操作步骤

        1. 定义手机类，手机类中定义String类型的品牌，int类型的价格，String类型的颜色，
        2. 编写打电话的成员方法，方法中对成员变量进行使用。
        3. 编写发短信的成员方法，方法中对成员变量进行使用。
        4. 在测试类中创建手机对象，直接给对象的属性赋值，分别调用各个方法。*/
public class Phone {
    public String brand;
    public int price;
    public String color;

    public Phone(String brand, int price, String color) {
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public Phone() {

    }

    public void call(){
        System.out.println("正在使用价格为"+price+"元"+color+brand+"手机打电话....");
    }
    public void sendMessage(){
        System.out.println("正在使用价格为"+price+"元"+color+brand+"手机发短信....");
    }
}
