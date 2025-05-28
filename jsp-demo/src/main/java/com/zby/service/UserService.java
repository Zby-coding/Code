package com.zby.service;

import com.zby.mapper.UserMapper;
import com.zby.pojo.User;
import com.zby.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public User Login(String username, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(username, password);
        sqlSession.close();
        return user;
    }

    public boolean register(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user2 = mapper.selectByUsername(user.getUsername());
        if (user2 == null) {
            mapper.add(user);
        }
        sqlSession.close();
        return user2 == null;
    }
}