package com.zby.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional // 事务管理员->业务层 事务管协调员->业务层/数据层
public interface AccountService2{
    /**
     * 转账操作
     * @param out 传出方
     * @param in 转入方
     * @param money 金额
     */

    public void transfer(String out, String in, Double money) ;
}
