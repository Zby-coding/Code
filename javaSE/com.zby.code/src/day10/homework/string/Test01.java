package day10.homework.string;

import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        System.out.println("请输入一串手机号:");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String substrings = s.substring(3, 6);
        System.out.println(s.replace(substrings, "****"));


    }
}
