package com.example.controller;

import com.example.Service.UserService;
import com.example.model.ResponseBodyMessage;
import com.example.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


/**
 * @program: spring_test1
 * @description: 存储session字符串
 * @author: XX
 * @create: 2022-09-03 20:46
 **/
@RestController
@Log4j2
public class UserController {
    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//    @Resource
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @Description: 用户查询
     * @Param:
     * @return: User
     * @Author: XX
     * @Date:
     */
//    @RequestMapping("/login")
//    @ResponseBody
//    public Object login(String username, String password, HttpServletRequest request) {
//        User user = userService.selectByName(username);
//
//        if (user == null) {
//            log.info("登录失败！");
//            System.out.println("登录失败！");
//            return new User();
//        } else {
//            if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
//                return new User();
//            }
//            //匹配成功
//            request.getSession().setAttribute(Constant.USER_SESSION_KEY, user);
//            return user;
//        }
//    }
    @RequestMapping("/login")
    public ResponseBodyMessage<User> login(@RequestBody User user, HttpServletRequest request) {
        User truUser = userService.selectByName(user.getUsername());
        if (truUser == null) {
            System.out.println("登录失败!");
            return new ResponseBodyMessage<>(-1,"用户名密码错误!",user);
        }else {
            boolean flg = bCryptPasswordEncoder.matches(user.getPassword(),truUser.getPassword());
            if (!flg) {
                return new ResponseBodyMessage<>(-1,"用户名密码错误!",user);
            }
            System.out.println("登录成功!");
            HttpSession session = request.getSession(true);
            session.setAttribute(Constant.USER_SESSION_KEY,truUser);
            return new ResponseBodyMessage<>(1,"登录成功!",truUser);
        }
    }


    /**
     * @Description: 注册新用户
     * @Param:
     * @return: user
     * @Author: XX
     * @Date: 0908
     */
//    @RequestMapping("/register")
//    @ResponseBody
//    public Object register(String username, String password) {
//        User user1 = userService.selectByName(username);
//        if (user1 != null) {
//            log.error("用户已存在");
//            System.out.println("用户已存在");
//            return new User();
//        } else {
//            User user2 = new User();
//            user2.setUsername(username);
//            String password1 = bCryptPasswordEncoder.encode(password);
//            user2.setPassword(password1);
//            userService.insert(user2);
//            return user2;
//        }
//    }
    @RequestMapping("/register")
    public ResponseBodyMessage<User> register(@RequestBody User user) {
        User truUser = userService.selectByName(user.getUsername());
        if (truUser != null) {
            return new ResponseBodyMessage<>(-1,"当前用户名已经存在!",user);
        } else{
            String password = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(password);
            userService.insert(user);
            return new ResponseBodyMessage<>(1,"注册成功!",user);
        }
    }


    /**
     * @Description: 获取用户信息
     * @Param:
     * @return: User
     * @Author: XX
     * @Date: 0908
     */
//    @RequestMapping("/userinfo")
//    @ResponseBody
//    public Object getUserInfo(HttpServletRequest request) {
//        try {
//            HttpSession session = request.getSession(false);
//            User user = (User) session.getAttribute("user");
//            User newuser = userService.selectByName(user.getUsername());
//            return newuser;
//        } catch (NullPointerException e) {
//            log.error("没有该用户");
//            System.out.println("没有该用户");
//            return new User();
//        }
//    }
    @RequestMapping("/userInfo")
    public ResponseBodyMessage<User> getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(Constant.USER_SESSION_KEY);
        if (user == null) {
            return new ResponseBodyMessage<>(-1,"当前用户不存在",null);
        }else{
            return new ResponseBodyMessage<>(1,"查找成功!", user);
        }
    }


    /**
     * @Description: 退出登录
     * @Param:
     * @return:
     * @Author: XX
     * @Date: 0908
     */
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.removeAttribute(Constant.USER_SESSION_KEY);
        response.sendRedirect("login.html");
    }


    public static void main(String[] args) {
        String rawpassword = "wangwu";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(rawpassword);
        System.out.println("明文密码为：" + rawpassword);
        System.out.println("密文密码为：" + password);
//        String encodepassword = String.valueOf(passwordEncoder.upgradeEncoding("$2a$10$OMvfetv9G6dNKsoKyEhCguykoswjRSIb3e0Jq4v9ulkTDjX6t5k76"));
//        System.out.println("明文："+encodepassword);
    }
}
