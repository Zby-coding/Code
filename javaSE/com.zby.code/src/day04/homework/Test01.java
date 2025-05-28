package day04.homework;


public class Test01 {
    public static void main(String[] args) {
        int sum = 0;
        int i = 0;
        while (i <= 5) {
            if (i % 2 != 0) {
                sum += i;
            }
            i++;
        }
        System.out.println("sum = " + sum);
    }
}
