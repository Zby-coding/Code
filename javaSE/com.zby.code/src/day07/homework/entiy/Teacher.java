package day07.homework.entiy;

public class Teacher {
    // 成员变量
    private String name;
    private String id;

    public Teacher() {
    }

    public Teacher(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // 教课的方法
    public void teach() {
        System.out.println("工号为" + id + "的" + name + "老师正在讲课");
    }
}

