package com.zby.mapper;

import com.zby.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    // 查询所有
    @Select("select * from tb_brand")
    @ResultMap("BrandResultMap")
    List<Brand> selectAll();

    // 添加
    @Insert("insert into  tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    //  查询一个
    @Select("select * from tb_brand where id = #{id}")
    @ResultMap("BrandResultMap")
    Brand selectBrandById(int id);
    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    void update(Brand brand);
    @Delete("delete from tb_brand where id = #{id}")
    void delete(int id);
}
