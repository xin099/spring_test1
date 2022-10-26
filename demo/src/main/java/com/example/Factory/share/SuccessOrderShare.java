package com.example.Factory.share;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: spring_test1
 * @description: 定义不同的类型实现分享图片
 * @author: XX
 * @create: 2022-10-26 15:17
 **/
public class SuccessOrderShare implements Share{

    @Override
    public String getShareFunctionType() {
        return ShareFactory.EnumShareType.SUCCESS_ORDER.getName();
    }

    @Override
    public String mainProcess(String shareName) {
        //这里写处理分享的业务逻辑代码
        return shareName;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        ShareFactory shareFactory = (ShareFactory) applicationContext.getBean("shareFactory");
        Share shareFunction = shareFactory.getShareFunction(ShareFactory.EnumShareType.SUCCESS_ORDER.getName());
        String success_order = shareFunction.mainProcess("Success order");
        System.out.println(success_order);
    }
}
