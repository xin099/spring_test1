package com.example.config;

import com.example.controller.Constant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: spring_test1
 * @description: LoginInterceptor类
 * @author: XX
 * @create: 2022-09-03 21:30
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(Constant.USER_SESSION_KEY) != null){
            return true;
        }
        response.sendRedirect("/login.html");
        return false;
    }
}
