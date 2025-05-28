package day03.homework;


/*
题目3:

         某小伙想定一份外卖,商家的优惠方式如下:鱼香肉丝单点24元,油炸花生米单点8元,米饭单点3元。

         订单满30元8折优惠。鱼香肉丝优惠价16元,但是优惠价和折扣不能同时使用,那么这个小伙要点这三样东西,最 少要花多少钱?请编写程序计算。

         思路分析:

         有两种购买方式,一种是不使用优惠价,另一种是使用优惠价。分别计算花费后,对两种方式的花费作对比。

         参考步骤:

         1.使用算术运算符求出不使用优惠时的总价.
         2.使用三元运算符判断总价是否满足打折条件,并求出折后总价.
         3.使用算术运算符求出使用优惠价时的总价.
         4.使用三元运算符判断最终更合算的购买方式和花费.*/



public class Test03 {
    public static void main(String[] args) {
        int fish = 24;  // 鱼香肉丝价钱
        int peanut = 8;  // 油炸花生米价钱
        int rice = 3; // 米饭价钱
        int total = fish + peanut + rice;  // 不使用优惠时的总价
        int total2 = fish + peanut + rice;
        System.out.println("不使用优惠时的总价为:" + total);
        int discount = (total >= 30) ? (total *= 0.8) : total;   // 使用三元运算符判断总价是否满足打折条件,并求出折后总价
        System.out.println("使用三目运算符计算优惠时的总价为:" + discount);
        double price = (fish + peanut + rice) * 0.8;  // 使用算术运算符求出使用优惠价时的总价
        System.out.println("使用算数运算符计算优惠价时的总价为:" + price);
        String str = (discount < total2 ) ? "折扣价更实惠" : "原价更实惠";  //使用三元运算符判断最终更合算的购买方式和花费
        System.out.println(str);
    }
}
