package com.example.builder;

/**
 * @program: spring_test1
 * @description: 导演类
 * @author: XX
 * @create: 2022-10-27 09:07
 **/
public class ItemDirector {
    private ItemBuilder itemBuilder;

    public ItemDirector(ItemBuilder itemBuilder){
        this.itemBuilder = itemBuilder;
    }

    public Item normalConstruct(){
        itemBuilder.buildNormal();
        return itemBuilder.getResult();
    }

    public Item cardConstruct(){
        itemBuilder.buildCard();
        return itemBuilder.getResult();
    }

    public Item videoConstruct(){
        itemBuilder.buildVideo();
        return itemBuilder.getResult();
    }
}
