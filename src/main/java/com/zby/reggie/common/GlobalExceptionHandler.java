package com.zby.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常类
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.info(ex.getMessage());//Duplicate entry 'zhangsan' for key 'idx_username'
        if(ex.getMessage().contains("Duplicate entry")){
            String[] s=ex.getMessage().split(" ");
            String msg=s[2]+"已存在";
            return R.error(msg);
        }

        return R.error("未知错误");
    }
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex){
        log.info(ex.getMessage());//Duplicate entry 'zhangsan' for key 'idx_username'


        return R.error(ex.getMessage());
    }
}
