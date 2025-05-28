package day08.homework;

import java.util.Scanner;
public class ArrayTest08 {
/*
训练描述
1.键盘录入一个整数(正数或者负数都可以,但是符号位不算有效的数字位)
	2.定义一个方法,该方法的功能是计算该数字是几位数字,并将位数返回
	3.在main方法中打印该数字是几位数
	4.演示格式如下:
		(1)演示一:
			请输入一个整数:1234
			控制台输出:1234是4位数字
		(2)演示二:
			请输入一个整数:-34567
			控制台输出:-34567是5位数字
3.2． 操作步骤描述
思路：
	1位数 除1次10 等于0  5 / 10 = 0
	2位数 除2次10 等于0 55 / 10 = 5 / 10 = 0
	3位数 除3次10 等于0  555 / 10 = 55 / 10 = 5 / 10 =0
	4位数 除4次10 等于0
	方法参数：一个数字 int
返回值：几位数 int

1.定义一个计数器，用来记录位数
2.使用while循环，一直除10，每次除10，就把计数器+1，知道结果等于0，就跳出循环

 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入一个整数:");
		int num = scanner.nextInt();
		int weishu = calcWeiShu(num);
		System.out.println(num+"是"+weishu+"位数字");
	}
	
	public static int calcWeiShu(int num) {
		// 定义一个计数器
		int weishu = 0;
		// 循环/10
		while (true) {
//			num = num / 10;
			// 除一次10
			num /= 10;
			// 计数器就增加
			weishu++;
			// 判断num是否为0
			if (num == 0) {
				// 算完了, 不用再除了
				break;
			}
		}
		// 返回位数
		return weishu;
	}
}


