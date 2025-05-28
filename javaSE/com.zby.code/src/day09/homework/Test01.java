package day09.homework;

// 键盘录入一个长方形的长和宽(长和宽为int类型),计算长方形的面积和周长


import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        System.out.println("请输入一个长方形的长和宽(要求是整型):");
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int width = sc.nextInt();
        System.out.println("录入的该长方形的长为:" + length + "\n" + "录入的该长方形的宽为:" + width);
        System.out.println("该长方形的周长为:" + (length * 2 + width * 2));
        System.out.println("该长方形的面积为:" + (length * width));

    }
}
