package com.example.test;


import redis.clients.jedis.Jedis;

/**
 * @program: spring_test1
 * @description: redis
 * @author: XX
 * @create: 2022-12-04 19:01
 **/
public class redis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("152.136.181.118",6379);
        String pong = jedis.ping();
        System.out.println("连接成功"+pong);
        jedis.close();
    }
}
