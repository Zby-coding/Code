package com.zby.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zby.pojo.User;

public class TestJSON {
    public static void main(String[] args) {
        //将对象转换为json字符串
        User user = new User();
        user.setId(1);
        user.setUsername("zql");
        user.setPassword("741");
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);
        // 将json字符串转换为java对象
        JSONObject jsonObject = JSON.parseObject(jsonString);
        System.out.println(jsonObject);
        // 将json字符串转换为User对象
        user = JSON.parseObject(jsonString, User.class);
        System.out.println(user.getUsername());


    }
}
