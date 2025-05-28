package com.zby.dao.impl;

import com.zby.dao.BrandDao;

public class BrandDaoImpl implements BrandDao {
    private String name;
    private int price;

    public BrandDaoImpl() {
    }

    public BrandDaoImpl(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void addBrand() {
        System.out.println(BrandDaoImpl.class.getName() + "addBrand" + "价格:" + price + "品牌名" + name);
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(BrandDaoImpl.class.getName());
    }
}
