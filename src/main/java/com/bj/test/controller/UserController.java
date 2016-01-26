package com.bj.test.controller;

import com.bj.test.model.User;
import com.bj.test.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by baojun on 2016/1/19.
 */
@Controller
public class UserController {
    @Autowired
    private UserServiceI userService;
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<User> userList = userService.find();
        model.addAttribute("userList",userList);
        return "user";
    }
}
