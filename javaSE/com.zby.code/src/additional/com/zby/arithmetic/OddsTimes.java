package com.zby.arithmetic;

public class OddsTimes {
    public static void main(String[] args) {
        // 找出数组中出现奇数次的数,假设只有一个数字出现奇数次，其余数字出现偶数次
        int[] arr = {6, 6, 37, 37, 38, 38, 39, 40, 40, 41, 41, 42, 42, 43, 43, 44, 44};
        // 找出数组中出现奇数次的数,假设有两个数字出现奇数次，其余数字出现偶数次，找出出现两次的奇数
        int[] arr2 = {6, 6, 37, 37, 38, 38, 39, 40, 40, 41, 41, 42, 42, 43, 44, 44};
        printOddsTimes1(arr);
        printOddsTimes2(arr2);
    }

    public static void printOddsTimes2(int[] arr2) {
        int eor = 0;
        // 遍历结果为出现两次的两个奇数的异或结果
        for (int cur : arr2) {
            eor ^= cur;
        }
        // 拿到最右边为1的数字
        int rightOne = eor & (~eor + 1);
        int eor2 = 0;
        // 遍历最右边为1的数字，得到出现两次的其中一个奇数
        for (int cur : arr2) {
            if ((cur & rightOne) != 0) {
                eor2 ^= cur;
            }
        }
        System.out.println("出现奇数次的其中一个数是：" + eor2 + "，另一个数是：" + (eor ^ eor2));
    }

    public static void printOddsTimes1(int[] arr) {
        int eor = 0;
        for (int cur : arr)
            eor ^= cur;
        System.out.println("出现奇数的数是：" + eor);
    }
}
