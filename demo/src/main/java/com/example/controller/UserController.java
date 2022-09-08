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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
    @RequestMapping("/login")
    @ResponseBody
    public Object login(String username, String password, HttpServletRequest request) {
        User user = userService.selectByName(username);

        if (user == null) {
            System.out.println("登录失败！");
            return new User();
        } else {
            if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return new User();
            }
            //匹配成功
            request.getSession().setAttribute(Constant.USER_SESSION_KEY, user);
            return user;
        }

    }

    /**
    * @Description: 注册新用户
    * @Param:
    * @return: user
    * @Author: XX
    * @Date: 0908
    */
    @RequestMapping("/register")
    @ResponseBody
    public Object register(String username,String password){
        User user1 = userService.selectByName(username);
        if (user1 != null){
            System.out.println("用户已存在");
            return new User();
        }else {
            User user2 = new User();
            user2.setUsername(username);
            String password1 =bCryptPasswordEncoder.encode(password);
            user2.setPassword(password1);
            userService.insert(user2);
            return user2;
        }
    }

    /**
    * @Description: 获取用户信息
    * @Param:
    * @return: User
    * @Author: XX
    * @Date: 0908
    */
    @RequestMapping("/userinfo")
    @ResponseBody
    public Object getUserInfo(HttpServletRequest request){
        try {
            HttpSession session =request.getSession(false);
            User user =(User)session.getAttribute("user");
            User newuser =userService.selectByName(user.getUsername());
            return newuser;
        }catch (NullPointerException e){
            System.out.println("没有该用户");
            return new User();
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
