package day04.homework;
 		/*李雷想买一个价值7988元的新手机,她的旧手机在二手市场能卖1500元,而手机专卖店推出以旧换新的优惠,把 她的旧手机交给店家,新手机就能够打8折优惠。

                为了更省钱,李雷要不要以旧换新?请在控制台输出。

                思路分析:

                1.怎样计算不同方式的花费?

                2.用什么语句能够对不同购买方式的价格做出判断?


                参考步骤:

                1.计算不使用以旧换新时的花费。

                2.计算使用以旧换新时的花费。

                3
                .使用if..else语句判断哪种方式更省钱,并输出结果。
                */


public class Test02 {
	public static void main(String[] args) {
		double newPhonePrice = 7988;
		double oldPhonePrice = 1500;
		double discount = 0.8 * 7988;
		double cha = newPhonePrice - oldPhonePrice;
		if(discount < newPhonePrice) System.out.println("使用以旧换新更省钱,需要花费:" + discount);
		else if(discount < cha) System.out.println("不使用以旧换新更省钱，需要花费:" + cha);
		else System.out.println("原价购买更省钱，需要花费:" + newPhonePrice);
	}
}
