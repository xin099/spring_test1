package com.example.model;

import lombok.Data;

/**
 * @program: spring_test1
 * @description: MatchResponse
 * @author: XX
 * @create: 2022-09-08 15:20
 **/
@Data
public class MatchResponse {
    private boolean ok;
    private String reason;
    private String message;
}