package day08.homework;
/*
训练描述
将数字1-10保存到一个长度为10的一维数组中
定义一个新数组,长度为3,取出原来数组中随机三个元素(不考虑是否重复)
给新数组的元素赋值
求新数组所有元素的和
1.3． 操作步骤描述

 */
import java.util.Arrays;
import java.util.Random;
public class ArrayTest01 {

    public static void main(String[] args) {
        //创建长度10的数组并赋值: 静态初始化
        int[] arr1 = {1,2,3,4,5,6,7,8,9,10};
        // 创建长度3的数组: 动态初始化
        int[] arr2 = new int[3];
        // 创建Random类的对象
        Random random = new Random();
        // 循环赋值随机元素
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr1[random.nextInt(10)];
        }
        System.out.println(Arrays.toString(arr2));
        // 新数组求和
        int sum = 0;
        for (int i = 0; i < arr2.length; i++) {
            sum += arr2[i];
        }
        System.out.println(sum);
    }
}
