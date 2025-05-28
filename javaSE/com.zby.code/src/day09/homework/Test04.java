package day09.homework;

import java.util.Scanner;

/*
 训练描述
1.小明左、右手中分别拿两张纸牌（比如：10和8，数字10和8可通过键盘录入），要求编写代码交换小明手中的牌
2.程序运行的结果如下：

请输入小明左手中的纸牌：10
请输入小明右手中的纸牌：8

互换前小明手中的纸牌：
左手中的纸牌：10
右手中的纸牌：8

互换后小明手中的纸牌：
左手中的纸牌：8
右手中的纸牌：10
4.2． 操作步骤描述

1.	创建Scanner对象
2.	录入第一个数据赋值给变量left
3.	录入第二个数据赋值给变量right
4.	定义一个临时变量temp,保存left的值
5.	将right的值赋值给left
6.	将临时变量temp的值赋值给right
7.	按格式打印left和right中的值

 */
public class Test04 {
    public static void main(String[] args) {
        System.out.println("请输入小明左手中的纸牌：");
        Scanner sc = new Scanner(System.in);
        int left = sc.nextInt();
        int right = sc.nextInt();
        System.out.println("互换前小明手中的纸牌：" + left + " " + right);
        int temp = left;
        left = right;
        right = temp;
        System.out.println("互换后小明手中的纸牌：" + left + " " + right);
    }
}
