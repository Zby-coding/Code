package com.zby.service.impl;

import com.zby.dao.AccountDao2;
import com.zby.service.AccountService2;
import com.zby.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl2 implements AccountService2 {

    @Autowired
    private AccountDao2 accountDao;
    @Autowired
    private LogService logService;

@Transactional(rollbackFor = Exception.class)
    public void transfer(String out,String in ,Double money) {
        try{
            accountDao.outMoney(out,money);
            if (true){int i=100/0;}
            accountDao.inMoney(in,money);
        }finally {
            logService.log(out,in,money);
        }
    }

}
