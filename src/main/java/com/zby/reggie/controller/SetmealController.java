package com.zby.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zby.reggie.common.R;
import com.zby.reggie.dto.DishDto;
import com.zby.reggie.dto.SetmealDto;
import com.zby.reggie.entity.Category;
import com.zby.reggie.entity.Setmeal;
import com.zby.reggie.service.CategoryService;
import com.zby.reggie.service.DishService;
import com.zby.reggie.service.SetmealDishService;
import com.zby.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DishService dishService;

    /**
     * 新增套餐
     *
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {

        setmealService.saveWithDish(setmealDto);


        return R.success("新增套餐成功");

    }
    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto) {
        setmealService.updateWithDish(setmealDto);
        return R.success("修改套餐成功");
    }

    /**
     * 分页查询套餐信息
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        //分页的构造器
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //模糊查询
        lambdaQueryWrapper.like(name != null, Setmeal::getName, name);
        //排序
        lambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealService.page(pageInfo, lambdaQueryWrapper);

        Page<SetmealDto> dtoPage = new Page<>(page, pageSize);
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");

        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            //是套餐的id值
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String name1 = category.getName();
                setmealDto.setCategoryName(name1);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);

        return R.success(dtoPage);

    }


    /**
     * 删除套餐
     *
     * @param ids 套餐id列表
     * @return R<String>
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("删除套餐，ids：{}", ids);

        try {
            // 判断套餐状态，确保不能删除在售套餐
            LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Setmeal::getId, ids);
            queryWrapper.eq(Setmeal::getStatus, 1);
            int count = setmealService.count(queryWrapper);

            if (count > 0) {
                return R.error("套餐正在售卖中，不能删除");
            }

            // 调用服务层删除套餐及关联数据
            setmealService.removeWithDish(ids);

            return R.success("删除成功");

        } catch (Exception e) {
            log.error("删除套餐失败：", e);
            return R.error("删除失败");
        }
    }

    @PostMapping("/status/{status}")
    public R<String> updateStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
        log.info("修改套餐状态，status：{}，ids：{}", status, ids);

        try {
            // 1. 参数校验
            if (status != 0 && status != 1) {
                return R.error("状态参数错误");
            }
            if (ids == null || ids.isEmpty()) {
                return R.error("套餐ID不能为空");
            }

            // 2. 查询套餐信息
            List<Setmeal> setmeals = setmealService.listByIds(ids);
            if (setmeals.size() != ids.size()) {
                return R.error("部分套餐不存在");
            }

            // 3. 如果是起售，检查套餐内的菜品状态
            if (status == 1) {
                if (setmealService.checkSetmealContainDisabledDish(ids)) {
                    return R.error("套餐内包含已停售菜品，不能起售");
                }
            }

            // 4. 更新套餐状态
            setmealService.updateSetmealStatus(status, ids);

            return R.success(status == 1 ? "起售成功" : "停售成功");

        } catch (Exception e) {
            log.error("修改套餐状态失败：", e);
            return R.error("系统繁忙，请稍后重试");
        }
    }

    /**
     * 通过id获取菜品信息和口味信息 DishDto
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<SetmealDto> get(@PathVariable Long id) {
        SetmealDto setmealDto = setmealService.getByIdWithDish(id);
        return R.success(setmealDto);
    }
}
