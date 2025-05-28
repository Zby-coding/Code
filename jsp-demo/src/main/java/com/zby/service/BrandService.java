package com.zby.service;

import com.zby.mapper.BrandMapper;
import com.zby.pojo.Brand;
import com.zby.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 查询所有
    public List<Brand> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        return mapper.selectAll();
    }

    // 添加
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        sqlSession.close();
    }

    public Brand selectBrandById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        return mapper.selectBrandById(id);
    }

    public void update(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);
        sqlSession.close();
    }
    public void delete(int id){
        sqlSessionFactory.openSession(true).getMapper(BrandMapper.class).delete(id);
    }
}
