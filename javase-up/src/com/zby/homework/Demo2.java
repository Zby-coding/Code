package com.zby.homework;

import java.util.Arrays;
/**
 *请按以下要求顺序编写程序：
 * 1. 定义测试类，定义main()方法；
 * 1. 定义以下数组：int[] arr = {7, 6, 5, 4, 3};
 * 1. 用Arrays类打印此数组的所有元素；
 * 1. 用“排序”对数组元素“升序”排序；打印排序后的数组。
 *
 */
public class Demo2 {
    public static void main(String[] args) {
        int[] arr = {7, 6, 5, 4, 3};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 交换逻辑 异或实现 0 ^ N = N , N ^ N = 0 相同为假 不同为真 无进位相加
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
