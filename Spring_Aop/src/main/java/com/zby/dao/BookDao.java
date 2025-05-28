package com.zby.dao;

import org.springframework.stereotype.Repository;

@Repository("bookDao")
public interface BookDao {
    public void save();

    public void update();
    public String delete();

    public void select();
}
