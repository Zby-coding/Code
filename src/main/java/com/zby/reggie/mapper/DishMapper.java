package com.zby.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zby.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
