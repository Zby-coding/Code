package com.zby.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zby.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}