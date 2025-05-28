package day10.homework;
/*
分析以下需求，并用代码实现
	1.键盘录入一个大字符串,再录入一个小字符串
	2.统计小字符串在大字符串中出现的次数
	3.代码运行打印格式:
		请输入大字符串: woailaohan,laohanbutongyubaima,wulunlaohanhaishibaima,zhaodaogongzuojiushihaoma
		请输入小字符串:laohan
		控制台输出:小字符串laohan,在大字符串woailaohan,laohanbutongyubaima,wulunlaohanhaishibaima,zhaodaogongzuojiushihaoma中共出现3次
 */
import java.util.Scanner;
public class Test04 {

	public static void main(String[] args) {
		// 键盘录入
		Scanner scanner = new Scanner(System.in);
		// 提示输入
		System.out.println("请输入大写字符串:");
		String upperStr = scanner.nextLine();
		System.out.println("请输入小写字符串:");
		String lowerStr = scanner.nextLine();
		// 判断
		int count = count(upperStr, lowerStr);
		// 输出结果
		System.out.println("小字符串" + lowerStr + "在大字符串" + upperStr + "中共出现"+count+"次");
	}
	
	// 统计小字符串在大字符串中出现的次数
	public static int count(String upperStr, String lowerStr) {
		int count = 0;
		// 通过将小字符串替换为特殊字符的方式, 来判断
		// 由于不知道有多少次, 要用死循环
		while (upperStr.contains(lowerStr)) {
			// 替换
			upperStr = upperStr.replaceFirst(lowerStr, "-");
			// 计数器增加
			count++;
		}
		return count;
	}
}
