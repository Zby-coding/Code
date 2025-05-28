package day10.homework;
/*
	1.键盘录入一个字符串
	2.将该字符串变成字符数组(不能使用toCharArray()方法)
		char[字符串的长度]
		String -> char -> char[]
		手动创建char[], 遍历字符串获取所有字符, 将字符存入char[]
		返回char[]
	3.将字符数组中的所有大写字母变成小写字母(不能使用toLowerCase()方法)
	4.如果第一位和最后一位的内容不相同,则交换
	5.将字符数组中索引为偶数的元素变成'~'
	6.打印数组元素的内容
 */
public class Test02 {

	public static void main(String[] args) {
		char[] chs = toCharArray("Helloworld");
		print(chs);
		toLowerCase(chs);
		print(chs);
		swap(chs);
		print(chs);
		change(chs);
		print(chs);
	}
	
	public static char[] toCharArray(String s) {
		// 创建一个字符数组
		char[] chs = new char[s.length()];
		// 遍历字符串, 获取所有的字符
		for (int i = 0; i < chs.length; i++) {
			// 获取字符
			char ch = s.charAt(i);
			// 将字符存入字符数组
			chs[i] = ch;
		}
		// 返回数组
		return chs;
	}
	
	// 3.将字符数组中的所有大写字母变成小写字母(不能使用toLowerCase()方法)
	public static void toLowerCase(char[] chs) {
		// for循环遍历数组, 取出所有字符
		for (int i = 0; i < chs.length; i++) {
			// 获取每个字符
			char ch = chs[i];
			// 判断字符是否是大写字母: ch >= 'A' && ch <= 'Z'
			if (ch >= 'A' && ch <= 'Z') {
				// 如果是, 就转成小写char: 大写字符+=32就变小写
				chs[i] = (char)(ch + 32);
			}
		}
	}
	
	//4.如果第一位和最后一位的内容不相同,则交换
	public static void swap(char[] chs) {
		// 如果第一位和最后一位的内容不相同
		if (chs[0] != chs[chs.length - 1]) {
			// 交换
			char temp = chs[0];
			chs[0] = chs[chs.length - 1];
			chs[chs.length - 1] = temp;
		}
	}
	
	// 5.将字符数组中索引为偶数的元素变成'~'
	public static void change(char[] chs) {
		// 遍历数组
		for (int i = 0; i < chs.length; i++) {
			// 判断索引是否为偶数
			if (i % 2 == 0) {
				// 将该索引的元素变成~
				chs[i] = '~';
			}
		}
	}
	
	// 打印数组内容
	public static void print(char[] chs) {
		System.out.print("[");
		for (int i = 0; i < chs.length; i++) {
			if (i == chs.length - 1) {
				System.out.println(chs[i] + "]");
			} else {
				System.out.print(chs[i] + ", ");
			}
		}
	}
}
