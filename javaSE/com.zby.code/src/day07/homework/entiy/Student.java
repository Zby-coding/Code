package day07.homework.entiy;
/*
	定义标准学生类，属性包括姓名和年龄，要求分别使用空参和有参构造方法创建对象，空参创建的对象通过setXxx赋值，有参创建的对象直接赋值，并通过show方法展示数据。

            训练提示

            1、学生类，属性有两个，姓名和年龄，定义为成员变量，姓名是字符串类型，年龄是整数

            2、提供空参构造、带参构造，提供两个成员变量的get和set方法

            3、学生类，有一个show方法，定义成员方法，输出相应的信息

            4、学生类定义完，在测试类中创建对象进行测试。

            操作步骤

            1、定义Student类，定义两个成员变量String name,int age，都使用private修饰

            2、提供空参构造方法和有参构造方法，提供get和set方法

            3、编写成员方法show()，按照要求的格式将属性信息进行输出。

            4、编写测试类StudentTest，在主方法中创建学生对象，空参创建的对象通过setXxx赋值，有参创建的对象直接赋值。

            5、调用show方法。
            */

public class Student {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void show() {
        System.out.println("姓名：" + name + "\n" + "年龄：" + age);
    }
}
