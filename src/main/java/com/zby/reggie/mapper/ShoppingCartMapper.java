package com.zby.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zby.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}
