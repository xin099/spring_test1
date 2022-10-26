package com.example.Factory.share;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: spring_test1
 * @description: 分享工厂
 * @author: XX
 * @create: 2022-10-26 14:50
 **/
@Component
public class ShareFactory {
    @Autowired
    private List<Share> shareFunctionList;

    /**
    * @Description: 根据分享类型获取对应的分享处理方式
    * @Param: type
    * @return:
    * @Author: XX
    */
    public Share getShareFunction(String type){
        for (Share shareFunction :
                shareFunctionList) {
            if (shareFunction.getShareFunctionType().equals(type)){
                return shareFunction;
            }
        }
        return null;
    }

    public enum EnumShareType {
        SUCCESS_ORDER("successOrder");
        private String name;

        EnumShareType(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
