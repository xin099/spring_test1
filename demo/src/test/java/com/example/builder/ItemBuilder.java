package com.example.builder;

/**
 * @program: spring_test1
 * @description: 抽象建造者类
 * @author: XX
 * @create: 2022-10-27 08:53
 **/
public abstract class ItemBuilder {
    //创建产品对象
    protected Item item = new Item();

    public abstract void buildNormal();

    public abstract void buildCard();

    public abstract void buildVideo();


    //返回产品对象
    public Item getResult(){
        return item;
    }
}
