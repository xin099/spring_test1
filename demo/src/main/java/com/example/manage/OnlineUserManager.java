package com.example.manage;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: spring_test1
 * @description: 用户管理器
 * @author: XX
 * @create: 2022-09-08 14:53
 **/
@Component
public class OnlineUserManager {
    private ConcurrentHashMap<Integer, WebSocketSession> gameHall = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, WebSocketSession> gameRoom = new ConcurrentHashMap<>();

    public void enterGameHall(int userId, WebSocketSession session) {
        gameHall.put(userId, session);
    }

    // 只有当前页面退出的时候, 能销毁自己的 session
    // 避免当一个 userId 打开两次 游戏页面, 错误的删掉之前的会话的问题.
    public void exitGameHall(int userId) {
        gameHall.remove(userId);
    }

    public WebSocketSession getSessionFromGameHall(int userId) {
        return gameHall.get(userId);
    }

    public void enterGameRoom(int userId, WebSocketSession session) {
        gameRoom.put(userId, session);
    }

    public void exitGameRoom(int userId) {
        gameRoom.remove(userId);
    }

    public WebSocketSession getSessionFromGameRoom(int userId) {
        return gameRoom.get(userId);
    }

}
