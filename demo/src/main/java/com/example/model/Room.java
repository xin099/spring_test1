package com.example.model;

import com.example.manage.OnlineUserManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.UUID;

/**
 * @program: spring_test1
 * @description: Room
 * @author: XX
 * @create: 2022-09-08 15:02
 **/
@Data
public class Room {
    private String roomId;
    // 玩家1
    private User user1;
    // 玩家2
    private User user2;
    // 先手方的用户 id
    private int whiteUserId = 0;
    // 棋盘, 数字 0 表示未落子位置. 数字 1 表示玩家 1 的落子. 数字 2 表示玩家 2 的落子
    private static final int MAX_ROW = 15;
    private static final int MAX_COL = 15;
    private int[][] chessBoard = new int[MAX_ROW][MAX_COL];

    private ObjectMapper objectMapper = new ObjectMapper();

    private OnlineUserManager onlineUserManager;

    public Room() {
        // 使用 uuid 作为唯一身份标识
        roomId = UUID.randomUUID().toString();
    }

}
