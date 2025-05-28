package day06.homework;
/*
按要求打印数字
        5.2． 操作步骤描述
        1.打印1到100之内的整数，但数字中包含9的要跳过
        2.每行输出5个满足条件的数，之间用空格分隔
        3.如：1 2 3 4 5

        分析:
        获取1-100之内的整数: for循环
        判断数字是否包含9:
        9 19 29 ... 99: 个位是9
        90 91 92 ... 98: 十位是9
        获取该数字的个位和十位是否等于9
        i % 10
        i / 10 % 10
        两种情况用 ||
        当判断出包含9, 跳过打印的操作
        continue
        如果不包含9, 打印数字: i + " "
        当输出5个数字时, 换行
        定义计数器变量count
        当输出一个满足要求的数字后, count++
        每次count自增后, 要判断一下count是否能被5整除, 如果能, 说明打印了5个, 需要换行syso()
        */
public class Test03 {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i <= 100 ; i++) {
            if(i % 10 == 9 || i / 10 % 10 == 9) {
                continue;
            }else{
                System.out.print(i + " ");
                count++;
                if (count % 5 == 0)
                    System.out.println();
            }
        }
    }
}
