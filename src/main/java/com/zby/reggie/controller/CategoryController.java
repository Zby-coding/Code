package com.zby.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zby.reggie.common.R;
import com.zby.reggie.entity.Category;
import com.zby.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category :{}",category);
        categoryService.save(category);
        return  R.success("新增分类成功");

    }
    /**
     * 分页查询
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        Page<Category> pageInfo= new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Category>  lambdaQueryWrapper = new LambdaQueryWrapper();

        //sort进行排序
        lambdaQueryWrapper.orderByAsc(Category::getSort);
        //分页查询
        categoryService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);


    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id){
        log.info("删除分类的id：{}",id);
     //   categoryService.removeById(id);
        categoryService.remove(id);
        return R.success("分类删除成功");

    }

    @PutMapping
    public R<String> update(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("修改分类信息成功");

    }

    /**
     * 获取所有菜品类型信息
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        log.info("---{}",category);
        //条件构造器
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        lambdaQueryWrapper.eq(category.getType()!=null,Category::getType,category.getType());

        //排序条件
        lambdaQueryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(lambdaQueryWrapper);


        return R.success(list);

    }
}
