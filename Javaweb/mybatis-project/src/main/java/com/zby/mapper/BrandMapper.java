package com.zby.mapper;

import com.zby.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    List<Brand> selectAll();
    Brand selectById(int id);
    Brand selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);
    List<Brand> selectByConditionObject(Brand brand);
    List<Brand> selectByConditionMap(Map map);
    List<Brand> selectByDynamicSql(Brand brand);
    List<Brand> selectByDynamicSqlSingleQuery(Brand brand);
    void add(Brand brand);
    void updateAll(Brand brand);
    void dynamicUpdateAll(Brand brand);
    void dynamicUpdateSingle(Brand brand);
    void deleteById(@Param("id") int id);
    void deleteByIds(@Param("ids") int[] ids);
}
