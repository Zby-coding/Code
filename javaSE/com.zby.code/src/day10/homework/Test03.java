package day10.homework;

import java.util.Scanner;
/*
训练描述：
分析以下需求,并用代码实现
	1.键盘录入一个源字符串由字符串变量srcStr接收
	2.键盘录入一个要删除的字符串由字符串变量delStr接收
	3.要求
		删除该字srcStr符串中的所有delStr字符串（最终的字符串中不能包含delStr），要求打印删除后的结果以及删除了几个delStr字符串
	4.代码运行打印格式:
		请输入源字符串:java woaijava,i like jajavava i enjoy java
		请输入要删除的字符串:java
		控制台输出结果:源字符串中总共包含:5 个 java 删除java后的字符串为: woai,i like  i enjoy
 */
public class Test03 {

	public static void main(String[] args) {
		// 键盘录入
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入源字符串:");
		String srcStr = scanner.nextLine();
		System.out.println("请输入要删除的字符串:");
		String delStr = scanner.nextLine();
		
		del(srcStr, delStr);
		
	}
	
	/*
	 * 返回值类型: void
	 * 参数列表: String srcStr, String delStr
	 */
	public static void del(String srcStr, String delStr) {
		// 定义一个计数器变量
		int count = 0;
		// 删除delStr
		while (srcStr.contains(delStr)) {
			// 删除小字符串
			srcStr = srcStr.replaceFirst(delStr, "");
			// 计数器增加
			count++;
		}
		// 打印结果
		System.out.println("源字符串中总共包含:"+count+" 个 "+delStr+" 删除"+delStr+"后的字符串为: " + srcStr);
	}
}
