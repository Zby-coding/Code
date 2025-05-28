package day15.homework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// 统计一个文件calcCharNum.txt中字母'A'和'a'出现的总次数
public class Test02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day15\\homework\\calcCharNum.txt"));
        String str = br.readLine();
        int count_a = 0;
        int count_A = 0;
        System.out.println("文件里的字符串为:" + str);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') {
                count_a++;
            }
        }
        System.out.println("字母a出现的次数为：" + count_a);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'A') {
                count_A++;
            }
        }
        System.out.println("字母A出现的次数为：" + count_A);
        br.close();
    }
}
