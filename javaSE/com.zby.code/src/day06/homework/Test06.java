package day06.homework;
/*
 题目2:

        定义一个方法，该方法能够找出三个整数中的最大值并返回。在主方法中调用方法测试执行。

         训练提示

        1. 根据题意，方法中需要使用三个整数，所以方法参数应该是三个整数类型。
        2. 方法需要有返回值，返回值的类型也是整数类型。

        */


public class Test06 {
    public static void main(String[] args) {
        System.out.println("三个数字中的最大值为:" + getMax(1, 2, 3));
    }
    public static int getMax(int num1, int num2, int num3) {
        int max = num1;
        if (num2 > max) {
            max = num2;
        }
        if (num3 > max) {
            max = num3;
        }
        return max;
    }
}
