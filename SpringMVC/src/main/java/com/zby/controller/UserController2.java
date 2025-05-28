package com.zby.controller;

import com.zby.domain.User;
import org.springframework.web.bind.annotation.*;

/*@Controller
@ResponseBody*/
@RestController
@RequestMapping("/users")
public class UserController2 {
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String save() {
        System.out.println("user save...");
        return "{'module':'user save'}";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        System.out.println("user delete..." + id);
        return "{'module':'user delete'}";
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody User user) {
        System.out.println("user update..." + user.getName() + ":" + user.getAge());
        return "{'module':'user update'}";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable Integer id) {
        System.out.println("user getById..." + id);
        return "{'module':'user getById'}";
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getAll() {
        System.out.println("user getAll...");
        return "{'module':'user getAll'}";
    }

}
