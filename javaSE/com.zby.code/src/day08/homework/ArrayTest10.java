package day08.homework;

import java.util.Arrays;
public class ArrayTest10 {
/*
	分析以下需求，并用代码实现
	有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13... 定义一个方法求出这个数列的前20 项之和。
		分子: 2,3,5,8,13
		分母: 1,2,3,5,8,13
		创建2个数组, 分别保存分子和分母的值
		利用不死神兔规律进行推算
		取出对应的分子和分母,相除后累加结果
 */
public static void main(String[] args) {
	// 定义分子和分母的数组
		int[] fenzi = new int[20];
		int[] fenmu = new int[20];
		// 先给分子分母赋值
		fenzi[0] = 2;
		fenzi[1] = 3;
		fenmu[0] = 1;
		fenmu[1] = 2;
		// for循环推算
		for (int i = 2; i < fenzi.length; i++) {
			fenzi[i] = fenzi[i - 1] + fenzi[i - 2];
			fenmu[i] = fenmu[i - 1] + fenmu[i - 2];
		}
		// 查看一下分子分母
		System.out.println(Arrays.toString(fenzi));
		System.out.println(Arrays.toString(fenmu));
		
		// 定义求和变量
		double sum = 0.0;
//		int sum = 0;
		// 获取分子分母对应的值, 进行计算
		for (int i = 0; i < fenzi.length; i++) {
			double result = 1.0 * fenzi[i] / fenmu[i];
//			int result = fenzi[i] / fenmu[i];
			sum += result;
		}
		// 打印结果
		System.out.println(sum);
	}
}
