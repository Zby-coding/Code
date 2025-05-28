package com.zby.stream.inputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Test1 {
    public static void main(String[] args) throws Exception {
        writeFile();
        writeFile2();

    }


    // 利用字节输出流一次写一个字节数组的方式向D盘的b.txt文件输出内容:“i love java”。
    public static void writeFile() throws Exception {
        FileOutputStream fos = new FileOutputStream("b.txt");
        byte[] bytes = "i love java".getBytes();
        fos.write(bytes);
        fos.close();
    }

    /**
     * 在D盘下，有一c.txt 文件中内容为：HelloWorld
     * 在c.txt文件原内容基础上，添加五句 I love java，而且要实现一句一行操作(注：原文不可覆盖)。
     * 利用字节输出流对象往C盘下c.txt文件输出5句：”i love java”
     */
    public static void writeFile2() throws Exception {
        String str = "I love java \r\n";
        FileOutputStream fos = new FileOutputStream("c.txt", true);
        for (int i = 0; i < 5; i++) {
            fos.write(str.getBytes());
            fos.flush();
        }
        fos.close();

    }

}
