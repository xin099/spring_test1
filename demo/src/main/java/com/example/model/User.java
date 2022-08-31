package com.example.model;

import lombok.Data;

/**
 * @program: spring_test1
 * @description: User类
 * @author: XX
 * @create: 2022-08-31 14:30
 **/
@Data
public class User {
    private int userId;
    private String username;
    private String password;
    private int score;
    private int totalCount;
    private int winCount;

}
