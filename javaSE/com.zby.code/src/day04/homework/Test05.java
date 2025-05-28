package day04.homework;

public class Test05 {

/*
  在中国历法中有十二生肖年份,2019年是己亥猪年,请在控制台输出从1949年(包含)到2019年(包含)中所有是猪年的年份。

            参考步骤:

            1. 定义for循环,1949到2019的年份是循环次数.
            2. 对每个年份逐个判断,如果年份和2019的差值是12的倍数,说明这年是猪年.
            3. 打印符合条件的年份.
*/
    public static void main(String[] args) {
        for (int i = 1949; i <=2019 ; i++) {
            if ((i - 2019) % 12 == 0) {
                System.out.println("第" + i + "年，是猪年");
            }

        }

    }
}
