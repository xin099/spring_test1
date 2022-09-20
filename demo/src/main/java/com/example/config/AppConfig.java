package com.example.config;

import com.example.controller.GameController;
import com.example.controller.MatchController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @program: spring_test1
 * @description: AppConfig
 * @author: XX
 * @create: 2022-09-03 21:34
 **/
@Configuration
@EnableWebSocket
public class AppConfig implements WebSocketConfigurer,WebMvcConfigurer {
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

    @Autowired
    private MatchController matchController;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(matchController,"/findMatch")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }


}
