package com.zby.controller;

import com.zby.domain.Book2;
import com.zby.exception.SystemException;
import com.zby.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booksss")
public class BookController4 {
    @Autowired
    private BookService bookService;

    @PostMapping

    public Result save(@RequestBody Book2 book) {
        boolean save = bookService.save(book);
        return new Result(save ?Code.SAVE_OK: Code.SAVE_ERR,save);
    }

    @PutMapping
    public Result update(@RequestBody Book2 book) {
        boolean update =bookService.update(book);
        return new Result(update ? Code.UPDATE_OK:Code.UPDATE_ERR,update);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean delete = bookService.delete(id);
        return new Result(delete ? Code.DELETE_OK:Code.DELETE_ERR,delete);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Book2 book =  bookService.getById(id);
        Integer code = book != null ? Code.GET_OK :Code.GET_ERR;
        try {
            int i = 1/0;
        } catch (ArithmeticException ex) {
            throw new SystemException(Code.SYSTEM_ERR,"服务器访问超时，请重试",ex);
        }
        String msg = book != null ?"":"该id不存在查询失败，请重试！";
        return new Result(Code.SYSTEM_ERR,book,msg);
    }

    @GetMapping
    public Result getAll() {
        List<Book2> books = bookService.getAll();
        Integer code = books!= null? Code.GET_OK :Code.GET_ERR;
        String msg = books != null ?"":"查询失败，请重试！";
        return new Result(code,books,msg);
    }
}
