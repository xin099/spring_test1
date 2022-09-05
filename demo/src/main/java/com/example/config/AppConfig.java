package com.example.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: spring_test1
 * @description: AppConfig
 * @author: XX
 * @create: 2022-09-03 21:34
 **/
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login.html")
                .excludePathPatterns("/**/register.html")
                .excludePathPatterns("/**/css/**.css")
                .excludePathPatterns("/**/images/**")
                .excludePathPatterns("/**/js/**.js")
                .excludePathPatterns("/**/login")
                .excludePathPatterns("/**/register")
                .excludePathPatterns("/**/logout");
    }

}
