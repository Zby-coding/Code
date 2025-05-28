package com.zby.controller;

import com.zby.exception.BusinessException;
import com.zby.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doException(SystemException exception){
        //System.out.println("异常现身");
        //记录日志
        //发送信息给运维人员
        //发送信息给开发人员

        return new Result(exception.getCode(),null,exception.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doException(BusinessException exception){
        //System.out.println("异常现身");
        //记录日志
        //发送信息给运维人员

        return new Result(exception.getCode(),null,exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception exception){
        //System.out.println("异常现身");
        //记录日志
        //发送信息给运维人员

        return new Result(Code.SYSTEM_UNKNOWN_ERR,null,"未知错误！");
    }

}
