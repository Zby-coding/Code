package com.zby.mapper;

import com.zby.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    // 查询所有
    @Select("select * from tb_brand")
    // 结果集映射 类名和字段名绑定
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    @Insert("INSERT INTO tb_brand(brand_name, company_name, ordered, description, status) " +
            "VALUES(#{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(Brand brand);
    // 批量删除
    void deleteByIds(@Param("ids") int[] ids);
    // 分页查询
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);
    @Select("select count(*) from tb_brand")
    int selectTotalCount();
    // 条件分页查询
    @ResultMap("brandResultMap")
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand")Brand brand);
    // 符合某个条件的数据
    int selectTotalCountByCondition(Brand brand);

}
