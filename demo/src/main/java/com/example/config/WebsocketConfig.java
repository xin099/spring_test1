package com.example.config;

import com.example.api.MatchAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @program: spring_test1
 * @description: WebsocketConfig
 * @author: XX
 * @create: 2022-09-19 17:16
 **/
@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    @Autowired
    private MatchAPI matchAPI;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

    }
}
