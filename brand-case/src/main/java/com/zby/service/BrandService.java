package com.zby.service;

import com.zby.pojo.Brand;
import com.zby.util.PageBean;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();
    void add(Brand brand);
    void deleteByIds(int[] ids);
    PageBean selectByPage(int page, int size);
    PageBean<Brand> selectByPageAndCondition(int page, int size,Brand brand);
    int selectTotalCountByCondition(Brand brand);
}
