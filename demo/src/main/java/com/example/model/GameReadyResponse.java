package com.example.model;

import lombok.Data;

/**
 * @program: spring_test1
 * @description: GameReadyResponse
 * @author: XX
 * @create: 2022-09-20 15:00
 **/
// 客户端链接成功后, 返回的响应
@Data
public class GameReadyResponse {
    private String message;
    private int status;
    private String roomId;
    private int thisUserId;
    private int thatUserId;
    private int whiteUser;
}
