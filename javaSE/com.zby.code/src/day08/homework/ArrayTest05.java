
package day08.homework;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/*
5.1． 训练考核知识点
        数组和判断语句综合
        5.2． 训练描述
        键盘录入一个整数，创建一个该长度的数组，为每一个元素赋值为1-10的随机整数，最后打印数组中所有值大于5且为偶数的元素.
        5.3． 操作步骤描述
        1键盘录入一个整数
        2定义长度为该整数的数组
        3创建Random引用数据类型的变量
        4生成5个1-10的随机数,为每一个元素赋值(建议用循环)
        5遍历数组,输出满足条件的元素

 */

public class ArrayTest05 {

    public static void main(String[] args) {
        // 创建键盘录入对象
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数:");
        int length = scanner.nextInt();

        // 根据长度创建数组
        int[] arr = new int[length];

        // 为元素赋值
        // 创建一个随机数对象
        Random random = new Random();

        // for循环赋值
        for (int i = 0; i < arr.length; i++) {
            // 生成一个随机数
            int num = random.nextInt(10) + 1;
            // 将随机数赋值给数组的元素
            arr[i] = num;
        }

        // 查看一下数组的元素
        System.out.println(Arrays.toString(arr));

        // 打印数组中所有值大于5且为偶数的元素
        for (int i = 0; i < arr.length; i++) {
            // 要对每个元素判断是否符合条件
            if (arr[i] > 5 && arr[i] % 2 == 0) {
                // 打印
                System.out.println(arr[i]);
            }
        }
    }
}
