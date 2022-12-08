package com.example.builder;

/**
 * @program: spring_test1
 * @description: 基础Item类
 * @author: XX
 * @create: 2022-10-27 08:58
 **/
public class Item {
    // 必填
    private String itemName;
    // 必填
    private Integer type;
    // 卡券必填
    private String code;
    // 视频必填
    private String url;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
