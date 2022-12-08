package com.example.responsibility;

/**
 * @program: spring_test1
 * @description: Interview
 * @author: XX
 * @create: 2022-10-27 09:57
 **/
public class ThreeInterview extends Handler{
    @Override
    public void handleRequest(Integer times) {
        if (times == 3){
            System.out.println("第三次面试：" + times +",!!!!!!!!!!!!");
        }
    }

}
