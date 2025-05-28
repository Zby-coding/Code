package com.zby.service;

import com.zby.domain.Book2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public interface BookService {
    // 保存图书
    boolean save(Book2 book);
    // 更新图书
    boolean update(Book2 book);
    // 根据id删除图书
    boolean delete(Integer id);
    // 根据id查询图书
    Book2 getById(Integer id);
    // 查询所有图书
    List<Book2> getAll();
}
