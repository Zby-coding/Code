package day08.homework;

import java.util.Scanner;
/*
3.1． 训练考核知识点
        数组，循环，if
        3.2． 训练描述
        从键盘上输入10个整数，合法值为1、2或3，不是这3个数则为非法数字。试编程统计每个整数和非法数字的个数。

        打印格式：
        请输入10个整数：
        1
        2
        3
        3
        2
        1
        3
        3
        5
        6
        数字1的个数：2
        数字2的个数：2
        数字3的个数：4
        非法数字的个数：2
        3.3． 操作步骤描述
        1.创建键盘录入对象
        2.定义长度为10的int类型的数组
        3.定义int类型的变量count1（统计1的个数）、count2（统计2的个数）、count3（统计3的个数）、count4（统计非法数字的个数）
        4.利用for循环给数组元素赋值
        5.利用for循环遍历数组
        (1)获取当前元素
        (2)使用if进行判断
        a.如果等于1 count1++
        b.否则，如果等于2 count2++
        c.否则，如果等于3 count3++
        d.否则 count4++

        6.打印统计结果

 */



public class ArrayTest03 {

    public static void main(String[] args) {
        // 1.创建键盘录入对象
        Scanner scanner = new Scanner(System.in);
        // 2.定义长度为10的int类型的数组
        int[] arr = new int[10];
        // 循环录入
        System.out.println("请输入10个整数：");
        for (int i = 0; i < arr.length; i++) {
            // 录入值保存到数组索引对应的元素上
            arr[i] = scanner.nextInt();
        }
        // 统计
        int count1 = 0;  // 1
        int count2 = 0;  // 2
        int count3 = 0;  // 3
        int count4 = 0;  // 非123
        // 遍历
        for (int i = 0; i < arr.length; i++) {
            // 判断元素的值是否为123
			/*if (arr[i] == 1) {
				count1++;
			} else if (arr[i] == 2) {
				count2++;
			} else if (arr[i] == 3) {
				count3++;
			} else {
				count4++;
			}*/

            switch (arr[i]) {
                case 1:
                    count1++;
                    break;
                case 2:
                    count2++;
                    break;
                case 3:
                    count3++;
                    break;
                default:
                    count4++;
                    break;
            }
        }
        // 打印结果
        System.out.println("数字1的个数：" + count1);
        System.out.println("数字2的个数：" + count2);
        System.out.println("数字3的个数：" + count3);
        System.out.println("非法数字的个数：" + count4);
    }
}
