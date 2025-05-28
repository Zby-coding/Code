package com.zby.service.impl;

import com.zby.mapper.BrandMapper;
import com.zby.pojo.Brand;
import com.zby.service.BrandService;
import com.zby.util.PageBean;
import com.zby.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    @Override
    public List<Brand> selectAll() {
        // 获取mapper
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        // 释放资源
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        // 获取mapper
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        // 获取mapper
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.close();

    }
    @Override
    public PageBean selectByPage(int page, int size) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        // 计算begin
        int begin = (page - 1) * size;
        // 调用方法
        List<Brand> brands = mapper.selectByPage(begin, size);
        int count = mapper.selectTotalCount();
        // 存入对象中
        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(brands);
        brandPageBean.setTotalCount(count);
        sqlSession.close();
        return brandPageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int page, int size, Brand brand) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        // 计算begin
        int begin = (page - 1) * size;
        // 模糊查询
        String brandName = brand.getBrandName();
        String companyName = brand.getCompanyName();
        if(brandName!=null && brandName.length()!=0){
            brand.setBrandName("%" + brandName + "%");
        }
        if(companyName != null && companyName.length()!=0){
            brand.setCompanyName("%" + companyName + "%");
        }
        // 调用方法
        List<Brand> brands = mapper.selectByPageAndCondition(begin, size,brand);
        int count = mapper.selectTotalCountByCondition(brand);
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(count);
        return pageBean;
    }

    @Override
    public int selectTotalCountByCondition(Brand brand) {
        return 0;
    }
}
