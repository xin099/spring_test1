package com.example.model;

import lombok.Data;

/**
 * @program: spring_test1
 * @description: ResponseBodyMessage
 * @author: XX
 * @create: 2022-09-13 14:57
 **/
@Data
public class ResponseBodyMessage<T> {
    private int status;
    private String message;
    private T data;

    public ResponseBodyMessage(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

