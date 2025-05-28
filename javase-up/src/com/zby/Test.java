package com.zby;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        method1(); // 测试单列集合stream流操作
        method2(); // 测试双列集合stream流操作
        method3(); // 测试数组集合stream流操作
        method4(); // 相同数据类型stream流操作
        method5(); // filter、limit、skip、concat中间操作
        method6(); // collect方法测试
    }

    private static void method6() {
        System.out.println("---------------------");
        ArrayList<String> list = new ArrayList<>();
        list.add("张三，20");
        list.add("李四，22");
        list.add("王五，22");
        Map<String, Integer> collect = list.stream().filter(x -> {
            String[] split = x.split("，");
            return Integer.parseInt(split[1]) == 22;
        }).collect(Collectors.toMap(x -> x.split("，")[0], x -> Integer.parseInt(x.split("，")[1])));
        Set<String> strings = collect.keySet();
        System.out.println("姓名：" + strings +  "\n" + "年龄：" + collect.values());


    }

    private static void method5() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        System.out.println("---------------------");
        map.put("张淇乐", 24);
        map.put("段宇杰", 23);
        map.put("杜金璞", 24);
        map.put("张淇乐1", 24);
        map.put("段宇杰2", 23);
        map.put("杜金璞3", 24);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Set<Map.Entry<String, Integer>> entries2 = map.entrySet();
        Stream.concat(entries.stream(), entries2.stream()).forEach(x-> System.out.println(x.getKey() + ":" + x.getValue()));
        System.out.println("---------------------");
        entries.stream().limit(1).forEach(x -> System.out.println(x.getKey())); // 超过maxSize，则会全部打印
        System.out.println("---------------------");
        entries.stream().skip(1).forEach(x -> System.out.println(x.getKey()));// 若超过集合里的最大条数，则会全部跳过，不会输出
        System.out.println("---------------------");
        entries.stream().filter(x -> x.getValue().equals(24)).forEach(x -> System.out.println(x.getKey() + ":" + x.getValue()));


    }

    private static void method4() {
        System.out.println("---------------------");
        Stream.of(1, 2, 3, 4, new ArrayList<>().add("zhangsan")).forEach(x -> System.out.println(x));
        int a[] = {1, 2, 3};
        Stream.of(a).forEach(x -> System.out.println(Arrays.toString(a)));
    }

    private static void method3() {
        System.out.println("---------------------");
        String[] str = {"user1", "user2", "user3"};
        Arrays.stream(str).forEach(x -> System.out.println(x));
    }

    private static void method2() {
        // 双列集合转成stream必须先转为set集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        System.out.println("---------------------");
        map.put("张淇乐", 24);
        map.put("段宇杰", 23);
        map.put("杜金璞", 24);
        Set<String> keys = map.keySet();
        keys.stream().forEach(x -> System.out.println(x + ":" + map.get(x)));
        System.out.println("---------------------");
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        entries.stream().forEach(x -> System.out.println(x.getKey() + ":" + x.getValue()));


    }

    private static void method1() {
        ArrayList<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("张三");
        list.stream().forEach(x -> System.out.println(x));
    }
}
