package day04.homework;

import java.util.Scanner;

public class Test04 {
/*    键盘录入一个月份数字,使用switch输出这个月份对应的季节.

            参考步骤:
    键盘录入月份并使用switch进行判断,可以使用case穿透来完成.

            实现步骤:

            1.创建键盘录入对象
2.录入数据
3.

    switch语句进行判断实现(最好使用case穿透)*/

    public static void main(String[] args) {
        while (true) {
            System.out.println("请输入月份:");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            if(666==num) break;
            switch (num) {
                case 3:
                case 4:
                case 5:
                    System.out.println("春季");
                    break;
                case 6:
                case 7:
                case 8:
                    System.out.println("夏季");
                    break;
                case 9:
                case 10:
                case 11:
                    System.out.println("秋季");
                    break;
                case 12:
                case 1:
                case 2:
                    System.out.println("冬季");
                    break;
                default:
                    System.out.println("输入有误");

            }
        }

    }
}
