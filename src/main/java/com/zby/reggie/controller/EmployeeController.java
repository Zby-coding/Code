package com.zby.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zby.reggie.common.R;
import com.zby.reggie.entity.Employee;
import com.zby.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        /**
         * 1、将页面提交的密码password进行md5加密处理
         * 2、根据页面提交的用户名username查询数据库
         * 3、如果没有查询到则返回登录失败结果
         * 4、密码比对，如果不一致则返回登录失败结果
         * 5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
         * 6、登录成功，将员工id存入Session并返回登录成功结果
         */
        //1、将页面提交的密码password进行md5加密处理
        String password=employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper =new LambdaQueryWrapper<Employee>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp= employeeService.getOne(queryWrapper);
        //3、如果没有查询到则返回登录失败结果
        if(emp==null){

            return R.error("登陆失败");
        }
        //4、密码比对，如果不一致则返回登录失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("登陆失败");
        }
        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if(emp.getStatus()==0){
            return R.error("账号禁用");
        }
        //6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("empId",emp.getId());

        return R.success(emp);


    }
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("empId");


        return R.success("退出成功");

    }
    @PostMapping
    public R<String> save (HttpServletRequest request,@RequestBody Employee employee){

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
       /* employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateTime(LocalDateTime.now());
*/
        Long empId=(Long) request.getSession().getAttribute("empId");

       /* employee.setCreateUser(empId);
        employee.setUpdateUser(empId);*/
        employeeService.save(employee);

        log.info("新增员工的信息：{}",employee.toString());
        return R.success("新增员工成功");

    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page={},pagesize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page pageInfo =new Page(page,pageSize);

        //写构造条件
        LambdaQueryWrapper<Employee> queryWrapper =new LambdaQueryWrapper<Employee>();
        //添加条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);


    }

    /**
     * 修改和状态更改
     * @param request
     * @param employee
     * @return
     */

    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());
       Long empId=(Long)request.getSession().getAttribute("empId");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);

        employeeService.updateById(employee);
        long id = Thread.currentThread().getId();
        log.info("线程id: {}",id);


        return R.success("员工修改成功");

    }
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息");
        Employee employee = employeeService.getById(id);
        if(employee!=null){
            return R.success(employee);
        }
        return R.error("没有查到对应的员工信息");

    }

}
