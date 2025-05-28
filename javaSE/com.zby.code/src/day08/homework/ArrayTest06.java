package day08.homework;

import java.lang.reflect.Array;
import java.util.Arrays;
public class ArrayTest06 {
/*
分析以下需求，并用代码实现
	1.定义一个方法equals(int[] arr1,int[] arr2),功能:比较两个数组是否相等(长度和内容均相等则认为两个数组是相同的)
	2.定义一个方法fill(int[] arr,int value),功能:将数组arr中的所有元素的值改为value
	3.定义一个方法fill(int[] arr,int fromIndex,int toIndex,int value),功能:将数组arr中的元素从索引fromIndex开始到toIndex(不包含toIndex)对应的值改为value
	4.定义一个方法copyOf(int[] arr, int newLength),功能:将数组arr中的newLength个元素拷贝到新数组中,并将新数组返回,从索引为0开始
	5.定义一个方法copyOfRange(int[] arr,int from, int to),功能:将数组arr中从索引from(包含from)开始到索引to结束(不包含to)的元素复制到新数组中,并将新数组返回

 */
	public static void main(String[] args) {
		//1
		int[] arr1 = {1,2,3};
		int[] arr2 = {1,2,3,4,5};
//		System.out.println(equals(arr1, arr2));
		
//		fill(arr1, 6);
//		System.out.println(Arrays.toString(arr1));
		
//		fill(arr2, 1, 3, 6);
//		System.out.println(Arrays.toString(arr2));
		
//		int[] newArr = copyOf(arr2, 3);
//		System.out.println(Arrays.toString(newArr));
		
		int[] newArr = copyOfRange(arr2, 1, 3);
		System.out.println(Arrays.toString(newArr));
	}
	
	// 功能:比较两个数组是否相等(长度和内容均相等则认为两个数组是相同的)
	public static boolean equals(int[] arr1, int[] arr2) {
		// 先判断长度是否不同
		if (arr1.length != arr2.length) {
			// 不相同
			return false;
		}
		// 如果能执行到这里, 说明长度相同, 继续判断内容是否相同
		for (int i = 0; i < arr1.length; i++) {
			// 获取两个数组元素比较是否相等
			if (arr1[i] != arr2[i]) {
				// 有一个元素不同则认为内容不同
				return false;
			}
		}
		// 如果能执行到这里, 说明长度和内容都相同
		return true;
	}
	
	// 功能:将数组arr中的所有元素的值改为value
	public static void fill(int[] arr,int value) {
		// 遍历数组, 赋值
		for (int i = 0; i < arr.length; i++) {
			// 赋值
			arr[i] = value;
		}
	}
	
	// 功能:将数组arr中的元素从索引fromIndex开始到toIndex(不包含toIndex)对应的值改为value
	public static void fill(int[] arr,int fromIndex,int toIndex,int value) {
		// 遍历数组, 赋值
		for (int i = fromIndex; i < toIndex; i++) {
			// 赋值
			arr[i] = value;
		}
	}
	
	// 功能:将数组arr中的newLength个元素拷贝到新数组中,并将新数组返回,从索引为0开始
	public static int[] copyOf(int[] arr, int newLength) {
		// 创建新数组
		int[] newArr = new int[newLength];
		// for循环遍历, 从0到<newLength
		for (int i = 0; i < newArr.length; i++) {
			// 将原数组的值赋值到新数组
			newArr[i] = arr[i];
		}
		// 返回新数组
		return newArr;
	}
	
	// 功能:将数组arr中从索引from(包含from)开始到索引to结束(不包含to)的元素复制到新数组中,并将新数组返回
	public static int[] copyOfRange(int[] arr,int from, int to) { 
		// 创建一个新数组
		int[] newArr = new int[to - from];
		// for循环遍历, 将原数组中的元素赋值给新数组
		int index = 0;
		for (int i = from; i < to; i++) {
			newArr[index] = arr[i];
			index++;
		}
		// 返回新数组
		return newArr;
	}
}
