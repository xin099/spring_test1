package com.example.model;

import lombok.Data;

/**
 * @program: spring_test1
 * @description:
 * @author: XX
 * @create: 2022-09-20 15:01
 **/
// 落子的请求
@Data
public class GameRequest {
    private String message;
    private int userId;
    private int row;
    private int col;
}

