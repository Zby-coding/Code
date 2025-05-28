package day15.homework;

import java.io.*;

/*
编写一个程序实现如下功能，文件fin.txt是无行结构（无换行符）的包含中文字符的文本文件，
从fin中读取字符，写入文件fou.txt中，每40个字符一行（最后一行可能少于40个字）

 */
public class Test01 {
    public static void main(String[] args) throws IOException {
        File file1 = new File("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day15\\homework\\fin.txt");
        File file2 = new File("E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day15\\homework\\fou.txt");
        if (!(file1.exists())) file1.createNewFile();
        if (!(file2.exists())) file2.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
        String str = "战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐战神张淇乐";
        System.out.println(str.length());
        bw.write(str);
        bw.flush();
        bw.close();
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(file2));
        BufferedReader br = new BufferedReader(new FileReader(file1));
        String str2 = br.readLine();
        int count = 0;
        while (count <= str.length() && count + 40 <= str.length()) {
            bw2.write(str2.substring(count, count + 40));
            count += 40;
            bw2.flush();
            bw2.newLine();
        }
        if ((str.length()) - (str2.length()) == 0) {
            String str3 = str.substring(count, str.length());
            System.out.println(str3);
            bw2.write(str3);
        }
        System.out.println(new BufferedReader(new FileReader(file2)).readLine());
        bw.close();
        bw2.close();
        br.close();
    }


}



