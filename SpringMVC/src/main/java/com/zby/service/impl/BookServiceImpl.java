package com.zby.service.impl;

import com.zby.dao.BookDao;
import com.zby.domain.Book2;
import com.zby.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Override
    public boolean save(Book2 book) {
        bookDao.save(book);
        return true;
    }

    @Override
    public boolean update(Book2 book) {
        bookDao.update(book);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        bookDao.delete(id);
        return true;
    }

    @Override
    public Book2 getById(Integer id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book2> getAll() {
        return bookDao.getAll();
    }
}
