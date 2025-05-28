package com.zby.controller;

import com.zby.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * 传入参数时需注意：
 *      若名字一样 直接传参数
 *      如名字不一样 加@requestParam进行参数绑定
 *
 */

@RequestMapping("user")
@Controller
public class UserController {
    @RequestMapping("/save") // 设置当前请求的访问路径
    @ResponseBody // 设置相应内容的响应体 返回json数据
    public String save(){
        System.out.println("user controller save ...");
        return "{'info':'springmvc'}";
    }
    @RequestMapping("/commonParam")
    @ResponseBody
    public String commonParam(String name ,int age){
        System.out.println("普通参数传递 name ==> "+name);
        System.out.println("普通参数传递 age ==> "+age);
        return "{'module':'common param'}";
    }

    @RequestMapping("/commonParamDifferentName")
    @ResponseBody
    public String commonParamDifferentName(@RequestParam("name") String userName , int age){
        System.out.println("普通参数传递 userName ==> "+userName);
        System.out.println("普通参数传递 age ==> "+age);
        return "{'module':'common param different name'}";
    }

    @RequestMapping("/pojoParam")
    @ResponseBody
    public String pojoParam(User user){
        System.out.println("pojo参数传递 user ==> "+user);
        System.out.println("pojo参数传递 username ==> "+user.getName());
        return "{'module':'pojo param'}";
    }

    @RequestMapping("/pojoContainPojoParam")
    @ResponseBody
    public String pojoContainPojoParam(User user){
        System.out.println("pojo嵌套pojo参数传递 user ==> "+user);
        System.out.println("pojo嵌套pojo参数传递 user ==> "+user.getAddress().getCity());
        return "{'module':'pojo contain pojo param'}";
    }

    @RequestMapping("/arrayParam")
    @ResponseBody
    public String arrayParam(String[] likes){
        System.out.println("数组参数传递 likes ==> "+ Arrays.toString(likes));
        return "{'module':'array param'}";
    }

    @RequestMapping("/listParam")
    @ResponseBody
    public String listParam(@RequestParam List<String> likes){
        System.out.println("集合参数传递 likes ==> "+ likes);
        return "{'module':'list param'}";
    }

    /*
    1.json依赖
    2.开启json数据格式自动转换 @EnableWebMvc
    3.使用@RequestBody注解将外部传递的数组数据映射发哦形参的集合对象中作为数据
     */

    @RequestMapping("/listParamForJson")
    @ResponseBody
    public String listParamForJson(@RequestBody List<String> likes){
        System.out.println("list common(json)参数传递 list ==> "+likes);
        return "{'module':'list common for json param'}";
    }

    @RequestMapping("/pojoParamForJson")
    @ResponseBody
    public String pojoParamForJson(@RequestBody User user){
        System.out.println("pojo(json)参数传递 user ==> "+user.getAddress().getProvince());

        return "{'module':'pojo for json param'}";
    }


    @RequestMapping("/listPojoParamForJson")
    @ResponseBody
    public String listPojoParamForJson(@RequestBody List<User> list){
        System.out.println("list pojo(json)参数传递 list ==> "+list);
        System.out.println("list pojo(json)参数传递 list ==> "+list.get(1).getAddress().getCity());
        return "{'module':'list pojo for json param'}";
    }


    @RequestMapping("/dataParam")
    @ResponseBody
    public String dataParam(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date date2){
        System.out.println("参数传递 date ==> "+date);
        System.out.println("参数传递 date2 ==> "+date2);
        return "{'module':'data param'}";
    }
    @RequestMapping("/toJumpPage")
    public String toJumpPage(){
        System.out.println("跳转页面");
        return "/page.jsp";
    }

    @RequestMapping("/toText")
    @ResponseBody
    public String toText(){
        System.out.println("返回纯文本数据");
        return "response text";
    }

    @RequestMapping("/toJsonPOJO")
    @ResponseBody
    public User toJsonPOJO(){
        System.out.println("返回json对象数据");
        User user = new User();
        user.setName("zby");
        user.setAge(15);
        return user;
    }
    // HttpMessageConverter
    @RequestMapping("/toJsonList")
    @ResponseBody
    public List<User> toJsonList(){
        System.out.println("返回json集合数据");
        User user1 = new User();
        user1.setName("张三");
        user1.setAge(15);

        User user2 = new User();
        user2.setName("李四");
        user2.setAge(12);

        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);

        return userList;
    }

}
