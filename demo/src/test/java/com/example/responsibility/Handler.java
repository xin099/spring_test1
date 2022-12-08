package com.example.responsibility;

/**
 * @program: spring_test1
 * @description: 抽象Handler处理器
 * @author: XX
 * @create: 2022-10-27 09:55
 **/
public abstract class Handler {
    protected Handler handler;

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public abstract void handleRequest(Integer times);
}
