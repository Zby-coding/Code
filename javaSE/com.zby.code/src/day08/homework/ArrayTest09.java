package day08.homework;

import java.util.Scanner;

/* 训练描述
		 1.键盘录入10个整数存入数组中
		 Scanner
		 int[] arr = new int[10]
		 for
		 2.定义一个方法将奇数放在数组的左侧,偶数放在数组的右侧
		 [1,2,3,4]
		 [0,0,0,0]
		 新建一个和原数组长度相同的新数组
		 定义开头索引start = 0, 结尾索引end=length-1
		 遍历原数组, 获取每个元素, 判断是否为奇数偶数
		 如果是奇数, 则放在新数组左边(开头), 然后把开头索引++
		 如果是偶数, 则放在新数组右边(结尾), 然后把结尾索引--
		 将新数组赋值给原数组变量
		 3.定义一个方法打印原数组和处理后的数组
		 打印数组
		 4.定义一个方法传入一个int类型数组，输出这个数组中只出现一次的数字及个数
		 [1,2,3,3]
		 1 次数为1, 则只出现一次
		 2
		 3
		 定义一个计数器变量统计只出现一次的元素的个数singleCount
		 定义一个计数器变量统计一个元素的出现次数count
		 取出一个元素, 遍历数组所有元素, 判断是否相等, 如果相等统计一下出现的次数


 */
public class ArrayTest09 {
    public static void main(String[] args) {
// 1.键盘录入10个整数存入数组中
        Scanner scanner = new Scanner(System.in);
        // 定义一个数组
        int[] arr = new int[10];
        // for循环实现录入
        for (int i = 0; i < arr.length; i++) {
            System.out.println("请输入第" + (i + 1) + "个元素:");
            arr[i] = scanner.nextInt();
        }
        print(arr);

        arr = swap(arr);
        print(arr);

        printSingleNum(arr);
    }

    //2.定义一个方法将奇数放在数组的左侧,偶数放在数组的右侧
    public static int[] swap(int[] arr) {
        // 根据原数组长度创建新数组, 用于存放排序后的数据
        int[] newArr = new int[arr.length];
        // 定义开始索引
        int start = 0;
        // 定义结尾索引
        int end = arr.length - 1;
        // 遍历原数组
        for (int i = 0; i < arr.length; i++) {
            // 判断取出的元素是奇数还是偶数
            if (arr[i] % 2 == 0) {
                // 偶数, 放在新数组的右边
                newArr[end] = arr[i];
                // 将结尾索引向左移动
                end--;
            } else {
                // 奇数, 放在新数组的左边
                newArr[start] = arr[i];
                // 将开始索引向右移动
                start++;
            }
        }
        // 将新数组返回
        return newArr;
    }

    // 3.定义一个方法打印原数组和处理后的数组
    public static void print(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.println(arr[i] + "]");
            } else {
                System.out.print(arr[i] + ", ");
            }
        }
    }

    // 4.定义一个方法传入一个int类型数组，输出这个数组中只出现一次的数字及个数
    public static void printSingleNum(int[] arr) {
        // 定义计数器变量, 用于保存只出现一次的数字的个数
        int singleCount = 0;
        // 遍历数组, 获取所有元素
        for (int i = 0; i < arr.length; i++) {
            // 定义一个计数器变量, 用来统计当前元素的出现次数
            int count = 0;
            // 取出当前遍历出的元素
            int num = arr[i];
            // 拿当前元素再次和数组中的所有元素对比是否相等
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素和num对比
                if (num == arr[j]) {
                    // 说明出现了一次
                    count++;
                }
            }
            // 判断count到底是几次
            if (count == 1) {
                // 只出现了一次
                // 打印该元素
                System.out.println(num);
                // 记录只出现一次的次数
                singleCount++;
            }
        }
        // 输出只出现一次的数字的个数
        System.out.println("只出现一次的数字的个数为:" + singleCount);
    }
}

