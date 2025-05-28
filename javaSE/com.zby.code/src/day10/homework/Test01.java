package day10.homework;
/*
 训练描述
根据传入的字符串，获取指定的字符串
5.2． 操作步骤描述
1.定义如下方法public static String getPropertyGetMethodName(String property)
(1)该方法的参数为String类型，表示用户给定的成员变量的名字，返回值类型为String类型，返回值为成员变量对应的get方法的名字
(2)如：用户调用此方法时给定的参数为"name",该方法的返回值为"getName"
2.定义如下方法public static String getPropertySetMethodName(String property)
	(1)该方法的参数为String类型，表示用户给定的成员变量的名字，返回值类型为String类型，返回值为成员变量对应的set方法的名字
	(2)如：用户调用此方法时给定的参数为"name",该方法的返回值为"setName"
 */
public class Test01 {
    // name -> getName setName

    public static void main(String[] args) {
        System.out.println(getPropertyGetMethodName("name"));
        System.out.println(getPropertySetMethodName("name"));
    }

    public static String getPropertyGetMethodName(String property) {
        // 获取首字母并转为大写
        String firstLetter = property.substring(0, 1).toUpperCase();
        // 获取其他字母并转为小写
        String otherLetters = property.substring(1).toLowerCase();
        // 拼接返回
        return "get" + firstLetter + otherLetters;
    }

    public static String getPropertySetMethodName(String property) {
        // 获取首字母并转为大写
        String firstLetter = property.substring(0, 1).toUpperCase();
        // 获取其他字母并转为小写
        String otherLetters = property.substring(1).toLowerCase();
        // 拼接返回
        return "set" + firstLetter + otherLetters;
    }
}