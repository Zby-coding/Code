package com.zby.reggie.service;

import  com.baomidou.mybatisplus.extension.service.IService;
import com.zby.reggie.dto.DishDto;
import com.zby.reggie.dto.SetmealDto;
import com.zby.reggie.entity.Setmeal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);

    boolean checkSetmealContainDisabledDish(List<Long> ids);

    // 获取套餐及其包含的菜品信息
    SetmealDto getByIdWithDish(Long id);

    void updateSetmealStatus(Integer status, List<Long> ids);

    void updateWithDish(SetmealDto setmealDto);


}
