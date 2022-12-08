package com.example.builder;

import com.alibaba.fastjson.JSON;

/**
 * @program: spring_test1
 * @description: Demo
 * @author: XX
 * @create: 2022-10-27 09:12
 **/
public class testDemo {
    public static void main(String[] args) {
        ItemBuilder builder = new ItemConcreteBuilder();
        ItemDirector director = new ItemDirector(builder);
        Item item2 = director.normalConstruct();
        System.out.println(JSON.toJSONString(item2));
        Item item3 = director.cardConstruct();
        System.out.println(JSON.toJSONString(item3));

        //剔除导演类
        ItemBuilder builder2 = new ItemConcreteBuilder();
        builder2.buildNormal();
        Item result = builder2.getResult();
        System.out.println(JSON.toJSONString(result));
    }
}
