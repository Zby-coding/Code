package com.zby.reggie.common;

public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    public static void setCurrentId(Long id){

        threadLocal.set(id);
    }

    public static  Long getCurrentId(){
        return  threadLocal.get();
    }
}
