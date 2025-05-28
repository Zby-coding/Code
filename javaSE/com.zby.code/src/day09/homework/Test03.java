package day09.homework;

import java.util.Scanner;

/*
3.1． 训练描述
        键盘录入一个学生成绩(int类型),如果成绩大于等于60输出”及格”,如果成绩小于60输出”不及格”
        3.2． 操作步骤描述

        1.	创建键盘录入对象
        2.	调用方法获取输入的成绩
        3.	使用三元运算符如果成绩大于等于60返回"及格",否则返回"不及格"
        4.	输出结果
        */
public class Test03 {
    public static void main(String[] args) {
        System.out.println("请输入一个学生成绩:");
        Scanner sc = new Scanner(System.in);
        int score = sc.nextInt();
        String result = (score >=0 && score <=100)?((score >= 60) ? "及格" : "不及格"):"成绩不符合录入要求";
        System.out.println(result);
    }
}
