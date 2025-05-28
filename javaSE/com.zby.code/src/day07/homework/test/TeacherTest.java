package day07.homework.test;

import day07.homework.entiy.Teacher;

public class TeacherTest {
    public static void main(String[] args) {
        // 创建Teacher对象
        Teacher t1 = new Teacher(); // 使用空参构造
        t1.setName("张三");
        t1.setId("001");
        // 调用方法
        t1.teach();
        System.out.println("-------------");
        Teacher t2 = new Teacher("李四","002"); // 使用带参构造
        // 调用方法
        t2.teach();
    }
}