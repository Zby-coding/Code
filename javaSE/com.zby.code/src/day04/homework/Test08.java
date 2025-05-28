package day04.homework;

public class Test08 {
    public static void main(String[] args) {
        int sum = 0;
        for(int i = 0; i < 10; i++){
           if (i % 3 != 0){
               sum += i;
           }
        }
        System.out.println("sum = " + sum);
    }
}