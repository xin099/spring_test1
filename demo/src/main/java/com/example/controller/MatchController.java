package com.example.controller;

import com.example.manage.Matcher;
import com.example.manage.OnlineUserManager;
import com.example.model.MatchRequest;
import com.example.model.MatchResponse;
import com.example.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @program: spring_test1
 * @description: 处理 websocket 请求
 * @author: XX
 * @create: 2022-09-08 14:38
 **/
@Component
public class MatchController extends TextWebSocketHandler {
    private ObjectMapper objectMapper =new ObjectMapper();

    @Autowired
    private OnlineUserManager onlineUserManager;
    @Autowired
    private Matcher matcher;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 玩家上线, 加入到 OnlineUserManager 中

        // 1. 先获取到当前用户的身份信息(谁在游戏大厅中, 建立的连接)
        try {
            User user = (User) session.getAttributes().get(Constant.USER_SESSION_KEY);

            // 2. 先判定当前用户是否已经登录过(已经是在线状态), 如果是已经在线, 就不该继续进行后续逻辑.
            if (onlineUserManager.getState(user.getUserId()) != null
                    || onlineUserManager.getState(user.getUserId()) != null) {
                // 当前用户已经登录了!!
                // 针对这个情况要告知客户端, 你这里重复登录了.
                MatchResponse response = new MatchResponse();
                response.setOk(true);
                response.setReason("当前禁止多开!");
                response.setMessage("repeatConnection");
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
                // 此处直接关闭有些太激进了, 还是返回一个特殊的 message , 供客户端来进行判定, 由客户端负责进行处理
                // session.close();
                return;
            }

            // 3. 拿到了身份信息之后, 就可以把玩家设置成在线状态了
            onlineUserManager.enterGameIndex(user.getUserId(), session);
            System.out.println("玩家 " + user.getUsername() + " 进入游戏大厅!");
        } catch (NullPointerException e) {
            System.out.println("[MatchAPI.afterConnectionEstablished] 当前用户未登录!");
            // e.printStackTrace();
            // 出现空指针异常, 说明当前用户的身份信息是空, 用户未登录呢.
            // 把当前用户尚未登录这个信息给返回回去~~
            MatchResponse response = new MatchResponse();
            response.setOk(false);
            response.setReason("您尚未登录! 不能进行后续匹配功能!");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
        }
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 实现处理开始匹配请求和处理停止匹配请求.
        User user = (User) session.getAttributes().get(Constant.USER_SESSION_KEY);
        // 获取到客户端给服务器发送的数据
        String payload = message.getPayload();
        // 当前这个数据载荷是一个 JSON 格式的字符串, 就需要把它转成 Java 对象. MatchRequest
        MatchRequest request = objectMapper.readValue(payload, MatchRequest.class);
        MatchResponse response = new MatchResponse();
        if (request.getMessage().equals("startMatch")) {
            // 进入匹配队列
            matcher.add(user);
            // 把玩家信息放入匹配队列之后, 就可以返回一个响应给客户端了.
            response.setOk(true);
            response.setMessage("startMatch");
        } else if (request.getMessage().equals("stopMatch")) {
            // 退出匹配队列
            matcher.remove(user);
            // 移除之后, 就可以返回一个响应给客户端了.
            response.setOk(true);
            response.setMessage("stopMatch");
        } else {
            response.setOk(false);
            response.setReason("非法的匹配请求");
        }
        String jsonString = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(jsonString));
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        try {
            // 玩家下线, 从 OnlineUserManager 中删除
            User user = (User) session.getAttributes().get("user");
            WebSocketSession tmpSession = onlineUserManager.getState(user.getUserId());
            if (tmpSession == session) {
                onlineUserManager.exitGameHall(user.getUserId());
            }
            // 如果玩家正在匹配中, 而 websocket 连接断开了, 就应该移除匹配队列
            matcher.remove(user);
        } catch (NullPointerException e) {
            System.out.println("[MatchAPI.handleTransportError] 当前用户未登录!");
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        try {
            // 玩家下线, 从 OnlineUserManager 中删除
            User user = (User) session.getAttributes().get(Constant.USER_SESSION_KEY);
            WebSocketSession tmpSession = onlineUserManager.getState(user.getUserId());
            if (tmpSession == session) {
                onlineUserManager.exitGameHall(user.getUserId());
            }
            // 如果玩家正在匹配中, 而 websocket 连接断开了, 就应该移除匹配队列
            matcher.remove(user);
        } catch (NullPointerException e) {
            System.out.println("[MatchAPI.afterConnectionClosed] 当前用户未登录!");
            // e.printStackTrace();
        }
    }


}
