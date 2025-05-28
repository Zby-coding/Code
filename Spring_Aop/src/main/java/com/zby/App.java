package com.zby;

import com.zby.config.JdbcConfig;
import com.zby.config.MybatisConfig;
import com.zby.config.SpringConfig;
import com.zby.dao.AccountDao;
import com.zby.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class, MybatisConfig.class, JdbcConfig.class);
        BookDao bookDao = ctx.getBean("bookDao", BookDao.class);
        AccountDao accountDao = ctx.getBean(AccountDao.class);
        System.out.println(accountDao.findById(2));
        /**
         *MyBatis 使用动态代理机制来实现对 Mapper
         * 接口的调用。当你在代码中通过
         *  SqlSession.getMapper()
         * 方法获取一个 Mapper 接口的实例时，
         * 实际上返回的是一个 MapperProxy 对象。
         *
         */

        System.out.println(bookDao);
        /*
        * 使用 Spring AOP（面向切面编程）时，如果目标对象实现了接口，Spring
        * 会使用JDK 动态代理来创建代理对象，
        * 以便在方法调用前后插入额外的逻辑（如日志记录、事务管理等）。
        * */
        System.out.println(bookDao.getClass());
        bookDao.delete();
    }
}
