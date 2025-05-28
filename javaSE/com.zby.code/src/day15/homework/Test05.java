package day15.homework;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String gender;
    private int age;

    public Student() {
    }

    public Student(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (age != other.age)
            return false;
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", gender=" + gender + ", age=" + age + "]";
    }
}

public class Test05 {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("键盘录入6个学员的信息(录入格式:张三,男,25):");
            for (int i = 0; i < 6; i++) {
                System.out.println("请输入第 " + (i + 1) + " 个学员的信息(格式: 姓名,性别,年龄):");
                String content = sc.next();
                String[] split = content.split(",");
                if (split.length != 3) {
                    System.out.println("输入格式错误，请重新输入！");
                    i--; // 重新输入
                    continue;
                }
                try {
                    int age = Integer.parseInt(split[2]);
                    Student stu = new Student(split[0], split[1], age);
                    list.add(stu);
                } catch (NumberFormatException e) {
                    System.out.println("年龄格式错误，请重新输入！");
                    i--; // 重新输入
                }
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("studentInfo1.txt"))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentInfo1.txt"))) {
            Object o = ois.readObject();
            if (o instanceof ArrayList<?>) {
                ArrayList<?> tempList = (ArrayList<?>) o;
                if (!tempList.isEmpty() && tempList.get(0) instanceof Student) {
                    ArrayList<Student> students = (ArrayList<Student>) tempList;
                    for (Student stu : students) {
                        System.out.println(stu);
                    }

                    HashSet<Student> set = new HashSet<>(students);
                    try (PrintWriter pw = new PrintWriter("studentInfo2.txt")) {
                        for (Student stu : set) {
                            String s = stu.getName() + "-" + stu.getGender() + "-" + stu.getAge();
                            pw.println(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Use a relative path or pass the file path as a command-line argument
        String filePath = "E:\\OneDrive\\Desktop\\MD\\code\\javaSE\\com.zby.code\\src\\day15\\homework\\studentInfo2.txt"; // or args[0] if passing as argument

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            // Read the object from the file
            Object o = ois.readObject();

            // Check if the object is an ArrayList
            if (o instanceof ArrayList<?>) {
                ArrayList<?> tempList = (ArrayList<?>) o;

                // Check if the list is not empty and contains Student objects
                if (!tempList.isEmpty() && tempList.get(0) instanceof Student) {
                    @SuppressWarnings("unchecked")
                    ArrayList<Student> students = (ArrayList<Student>) tempList;

                    // Print each student's details
                    for (Student stu : students) {
                        System.out.println(stu);
                    }
                } else {
                    System.out.println("The list is either empty or does not contain Student objects.");
                }
            } else {
                System.out.println("The deserialized object is not an ArrayList.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }
    }
}