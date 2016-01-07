package com.bj.test.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by baojun on 2015/12/29.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("loginname") String username,@RequestParam("password") String password, Model model) {
        try {
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
            return "redirect:/greeting";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            model.addAttribute("error","用户名密码错误");
            return "login/login";
        }
    }
}
