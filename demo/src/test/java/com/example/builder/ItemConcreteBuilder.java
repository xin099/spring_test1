package com.example.builder;

/**
 * @program: spring_test1
 * @description: 具体建造者类
 * @author: XX
 * @create: 2022-10-27 09:01
 **/
public class ItemConcreteBuilder extends ItemBuilder {

    @Override
    public void buildNormal() {
        item.setItemName("普通商品");
        item.setType(1);
    }

    @Override
    public void buildCard() {
        item.setItemName("卡券商品");
        item.setCode("123456");
        item.setType(2);
    }

    @Override
    public void buildVideo() {
        item.setItemName("视频商品");
        item.setType(3);
        item.setUrl("http://wwww.baidu.com");
    }
}
