package com.zby.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zby.reggie.common.R;
import com.zby.reggie.entity.User;
import com.zby.reggie.service.UserService;
import com.zby.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取手机验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            //生成四位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code {}",code);
            //调用阿里云提供的短信服务 完成发送短信功能
         //  SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,code);

            //需要将验证码保存到session中

            session.setAttribute(phone,code);


            return R.success("手机短信发送成功");


        }
        return R.error("短信发送失败");

    }

    /**
     * 登录
     * @param map
     * @param session
     * @return
     */

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpSession session){
        log.info("user:{}",map.toString());
        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //从session中回去验证码
        Object sessionCode = session.getAttribute(phone);
        //对比验证
        if(sessionCode != null && sessionCode.equals(code)){
            //如果一样 登录成功
            LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(lambdaQueryWrapper);
            if(user==null){
                //判断手机号是是否是新用户 自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);

            }

            session.setAttribute("user",user.getId());
            return R.success(user);

        }




        return R.error("登录失败");

    }


}
