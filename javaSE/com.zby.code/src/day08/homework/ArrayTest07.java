package day08.homework;

public class ArrayTest07 {
/*
*  训练描述
定义一个方法,去打印指定两个年份之间所有的闰年年份
如:将2000和2005传入方法,方法执行完毕会打印出这段之间的所有闰年年份
2000和2004
提示:
计算公历年闰年的算法: 四年一闰，百年不闰，四百年再闰
翻译:
	四年一闰: 能被4整除的是
百年不闰: 能被100整除的不是
四百年再闰: 能被400整除的是
实例: 2000 是整百数,并且是400的倍数所以是闰年; 2004年是4的倍数是闰年
2100 是整百的倍数但不是400的倍数,所以不是闰年
* */
	public static void main(String[] args) {
		printLeapYear(2000, 2100);
	}
	
	public static void printLeapYear(int start, int end) {
		// 获取这个范围的数组, for循环获取
		for (int i = start; i <= end; i++) {
			// 判断是否为闰年
			if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
				// 是闰年
				System.out.println(i);
			}
		}
	}
}
