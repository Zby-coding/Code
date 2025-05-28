package day09.homework;

import java.util.Scanner;

public class Test02 {
/*   
     键盘录入自己的姓名(String),年龄(int),身高(int)保存到对应的变量中,
     输出结果如:我的姓名是张三,年龄25岁,身高180CM(提示姓名是String类型,
     需要使用Scanner的next()方法.)
    */
    public static void main(String[] args) {
        System.out.println("请输入自己的姓名、年龄、身高:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int age = sc.nextInt();
        int height = sc.nextInt();
        System.out.println("我的姓名是:" + name + "\n"+  "年龄:" + age + "岁" + "\n" + "身高:" + height + "CM");



    }
}
