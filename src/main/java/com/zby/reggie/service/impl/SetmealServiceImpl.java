package com.zby.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zby.reggie.common.BaseContext;
import com.zby.reggie.common.CustomException;
import com.zby.reggie.dto.DishDto;
import com.zby.reggie.dto.SetmealDto;
import com.zby.reggie.entity.Dish;
import com.zby.reggie.entity.DishFlavor;
import com.zby.reggie.entity.Setmeal;
import com.zby.reggie.entity.SetmealDish;
import com.zby.reggie.mapper.SetmealMapper;
import com.zby.reggie.service.DishService;
import com.zby.reggie.service.SetmealDishService;
import com.zby.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private DishService dishService;

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     *
     * @param setmealDto
     */
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息，操作setmeal_dish,执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     *
     * @param ids
     */
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //select count(*) from setmeal where id in (1,2,3) and status = 1
        //查询套餐状态，确定是否可用删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        int count = this.count(queryWrapper);
        if (count > 0) {
            //如果不能删除，抛出一个业务异常
            throw new CustomException("套餐正在售卖中，不能删除");
        }

        //如果可以删除，先删除套餐表中的数据---setmeal
        this.removeByIds(ids);

        //delete from setmeal_dish where setmeal_id in (1,2,3)
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        //删除关系表中的数据----setmeal_dish
        setmealDishService.remove(lambdaQueryWrapper);
    }

    @Override
    public boolean checkSetmealContainDisabledDish(List<Long> ids) {
        // 1. 查询套餐包含的所有菜品
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SetmealDish::getSetmealId, ids);
        List<SetmealDish> setmealDishes = setmealDishService.list(queryWrapper);

        if (setmealDishes != null && !setmealDishes.isEmpty()) {
            // 获取所有菜品ID
            List<Long> dishIds = setmealDishes.stream()
                    .map(SetmealDish::getDishId)
                    .collect(Collectors.toList());

            // 查询这些菜品中是否有停售的
            LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
            dishWrapper.in(Dish::getId, dishIds);
            dishWrapper.eq(Dish::getStatus, 0);  // 0表示停售状态

            return dishService.count(dishWrapper) > 0;
        }

        return false;
    }

    /**
     * 根据id查询菜品信息
     * @param id
     * @return
     */
    @Override
    public SetmealDto getByIdWithDish(Long id) {
        // 获取套餐id
        Setmeal setmeal = this.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal, setmealDto);
        // 获取套餐中的菜品
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        // 根据套餐id查询套餐中的菜品
        queryWrapper.eq(SetmealDish::getSetmealId, setmeal.getId());
        // 执行查询
        List<SetmealDish> setmealDishes = setmealDishService.list(queryWrapper);
        setmealDto.setSetmealDishes(setmealDishes);
        return setmealDto;
    }


    /**
     * 批量更新套餐状态
     *
     * @param status 状态
     * @param ids    套餐ID列表
     */
    @Transactional
    public void updateSetmealStatus(Integer status, List<Long> ids) {
        LambdaUpdateWrapper<Setmeal> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Setmeal::getId, ids);
        updateWrapper.set(Setmeal::getStatus, status);
        updateWrapper.set(Setmeal::getUpdateTime, LocalDateTime.now());
        updateWrapper.set(Setmeal::getUpdateUser, BaseContext.getCurrentId());

        this.update(updateWrapper);

        // 如果使用了缓存，在这里清理缓存
        // cleanCache(ids);
    }
    @Transactional
    @Override
    public void updateWithDish(SetmealDto setmealDto) {
        //更新dish表基本信息
        this.updateById(setmealDto);

        //清理当前套餐对应的菜品数据---dish表的delete操作
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SetmealDish::getId, setmealDto.getId());

        setmealDishService.remove(queryWrapper);

        //添加当前套餐提交过来的菜品数据---dish表的insert操作
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setDishId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }


}
