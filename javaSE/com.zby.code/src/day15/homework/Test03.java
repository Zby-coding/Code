package day15.homework;
// 统计一个文件calcCharNum.txt中各个字母出现次数：A(8),B(16),C(10)...,a(12),b(10),c(3)....，括号内代表字符出现次数
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Test03 {

    public static void main(String[] args) throws Exception {
        // 读取文件
        BufferedReader br = new BufferedReader(new FileReader("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day15\\homework\\calcCharNum2.txt"));
        String str = br.readLine();
        br.close();

        // 使用HashMap来存储字符及其出现次数
        HashMap<Character, Integer> charCountMap = new HashMap<>();

        // 遍历字符串，统计每个字符的出现次数
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if ((currentChar >= 'A' && currentChar <= 'Z') || (currentChar >= 'a' && currentChar <= 'z')) {
                charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + 1);
            }
        }

        // 输出每个字符及其出现次数
        for (char c : charCountMap.keySet()) {
            System.out.print(c + "(" + charCountMap.get(c) + "),");
        }
    }
}