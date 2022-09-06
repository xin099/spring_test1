package com.example.controller;

import com.example.Service.UserService;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @program: spring_test1
 * @description: 存储session字符串
 * @author: XX
 * @create: 2022-09-03 20:46
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(String username, String password, HttpServletRequest request){
        System.out.println("login");
        User user = userService.selectByName(username);

        if (user == null){
            System.out.println("登录失败！");
            return new User();
        }else {
            if (!bCryptPasswordEncoder.matches(password,user.getPassword())){
                return new User();
            }
            //匹配成功
            request.getSession().setAttribute(Constant.USER_SESSION_KEY,user);
            return user;
        }

    }
}
