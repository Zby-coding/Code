package day06.homework;

/*
分析以下需求，并用代码实现：
        1.珠穆朗玛峰高度为8848米，有一张足够大的纸，厚度为0.0001米。
        2.请问，我折叠多少次，可以折成珠穆朗玛峰的高度。
        3.2． 操作步骤描述
        无

        分析步骤:
        定义2个变量保存山和纸的厚度
        double
        折纸
        使用循环模拟折纸
        for: 用于知道循环次数的情况
        while: 用于不知道循环次数的情况(选择这个)
        厚度改变: paper *= 2
        循环什么时候停止:
        纸的厚度 >= 山的高度停止循环
        纸的厚度 < 山的高度一直循环
		计算次数:
                定义变量count
                每折一次count++
                */
public class Test02 {
    public static void main(String[] args) {
        int hillHeight = 8848;
        double paperHeight = 0.0001;
        int count = 0;
        while(paperHeight <= hillHeight){
            paperHeight *= 2;
            count++;
        }
        System.out.println("折纸次数：" + count + "次能达到珠穆朗玛峰的高度\n" + "纸的高度为:" + paperHeight + "米");
    }
}
