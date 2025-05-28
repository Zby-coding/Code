package com.zby.controller;

import com.zby.exception.BusinessException;
import com.zby.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doException(SystemException ex){
        return new Result(ex.getCode(),null,ex.getMessage());
    }
    @ExceptionHandler(BusinessException.class)
    public Result doException(BusinessException ex){
        return new Result(ex.getCode(),null, ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        return new Result(Code.SYSTEM_UNKNOWN_ERR,null, "未知异常，请重试！");
    }
}
