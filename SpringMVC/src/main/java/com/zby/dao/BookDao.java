package com.zby.dao;

import com.zby.domain.Book2;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BookDao {
    // 保存图书
    @Select("insert into tbl_book values(null,#{type}, #{name}, #{description})")
    void save(Book2 book);
    @Update("update tbl_book set type = #{type}, name = #{name}, description = #{description} where id = #{id}")
        // 更新图书
    void update(Book2 book);
    // 根据id删除图书
    @Delete("delete from tbl_book where id = #{id}")
    void delete(Integer id);
    // 根据id查询图书
    @Select("select * from tbl_book where id = #{id}")
    Book2 getById(Integer id);
    // 查询所有图书
    @Select("select * from tbl_book")
    List<Book2> getAll();
}
