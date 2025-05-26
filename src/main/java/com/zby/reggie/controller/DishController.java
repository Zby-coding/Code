package com.zby.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zby.reggie.common.BaseContext;
import com.zby.reggie.common.R;
import com.zby.reggie.dto.DishDto;
import com.zby.reggie.entity.Category;
import com.zby.reggie.entity.Dish;
import com.zby.reggie.entity.DishFlavor;
import com.zby.reggie.service.CategoryService;
import com.zby.reggie.service.DishFlavorService;
import com.zby.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品
     *
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info("{}", dishDto);
        dishService.saveWithFlavor(dishDto);

        return R.success("新增菜品成功");

    }

    /**
     * 分页查询菜品信息
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {

        //分页构造对象
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDto = new Page<>();

        //构造分页条件对象
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper();
        //条件设置
        lambdaQueryWrapper.like(name != null, Dish::getName, name);
        lambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);

        //执行
        dishService.page(pageInfo, lambdaQueryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dishDto, "records");//排除records
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto1 = new DishDto();
            BeanUtils.copyProperties(item, dishDto1);
            Long categoryId = item.getCategoryId();//分类id
            Category byId = categoryService.getById(categoryId);
            String categoryName = byId.getName();
            dishDto1.setCategoryName(categoryName);
            return dishDto1;

        }).collect(Collectors.toList());


        dishDto.setRecords(list);
        return R.success(dishDto);


    }

    /**
     * 通过id获取菜品信息和口味信息 DishDto
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id) {
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);

    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        dishService.updateWithFlavor(dishDto);
        return R.success("修改菜品成功");
    }

    /**
     * 查看所有的分类下的菜品信息 包含口味
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){
        log.info("dish {}",dish);
        //查询条件
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(dish.getCategoryId()!=null,Dish::getCategoryId,dish.getCategoryId());

        //只能显示在售状态
        lambdaQueryWrapper.eq(Dish::getStatus,1);
        lambdaQueryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(lambdaQueryWrapper);
        List<DishDto> dishDtoList = list.stream().map((item)->{
            DishDto dishDto1 = new DishDto();
            BeanUtils.copyProperties(item,dishDto1);
            Long categoryId = item.getCategoryId();//分类id
            Category byId = categoryService.getById(categoryId);
            String categoryName = byId.getName();
            dishDto1.setCategoryName(categoryName);

            //获取菜品的id
            Long id = item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(DishFlavor::getDishId,id);
            List<DishFlavor> list1 = dishFlavorService.list(lambdaQueryWrapper1);
            dishDto1.setFlavors(list1);
            return dishDto1;

        }).collect(Collectors.toList());
        return R.success(dishDtoList);

    }
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("删除菜品，ids：{}", ids);

        // 判断是否可以删除（在售卖中的菜品不能删除）
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dish::getId, ids);
        queryWrapper.eq(Dish::getStatus, 1);
        int count = dishService.count(queryWrapper);
        if (count > 0) {
            return R.error("菜品正在售卖中，不能删除");
        }

        // 删除菜品及其关联的口味数据
        dishService.removeByIds(ids);

        // 删除关联的口味数据
        LambdaQueryWrapper<DishFlavor> flavorQueryWrapper = new LambdaQueryWrapper<>();
        flavorQueryWrapper.in(DishFlavor::getDishId, ids);
        dishFlavorService.remove(flavorQueryWrapper);

        return R.success("删除成功");
    }

    @PostMapping("/status/{status}")
    public R<String> updateStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
        log.info("修改菜品状态，status：{}，ids：{}", status, ids);

        try {
            // 参数校验
            if (status != 0 && status != 1) {
                return R.error("状态参数错误");
            }
            if (ids == null || ids.isEmpty()) {
                return R.error("菜品ID不能为空");
            }

            // 查询菜品信息
            List<Dish> dishes = dishService.listByIds(ids);
            if (dishes.size() != ids.size()) {
                return R.error("部分菜品不存在");
            }

            // 构造更新条件
            LambdaUpdateWrapper<Dish> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.in(Dish::getId, ids);
            updateWrapper.set(Dish::getStatus, status);
            updateWrapper.set(Dish::getUpdateTime, LocalDateTime.now());
            updateWrapper.set(Dish::getUpdateUser, BaseContext.getCurrentId()); // 假设有获取当前用户ID的工具类

            // 更新菜品状态
            dishService.update(updateWrapper);

            // 如果停售，可能需要处理相关套餐
            if (status == 0) {
                // 处理包含该菜品的套餐逻辑
                // ...
            }

            return R.success(status == 1 ? "启售成功" : "停售成功");

        } catch (Exception e) {
            log.error("修改菜品状态失败：", e);
            return R.error("操作失败");
        }
    }


}
