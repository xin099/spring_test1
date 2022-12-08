package com.example.responsibility;

/**
 * @program: spring_test1
 * @description: Interview
 * @author: XX
 * @create: 2022-10-27 09:57
 **/
public class SecondInterview extends Handler {
    @Override
    public void handleRequest(Integer times) {
        //条件判断是否属于当前Handler的处理范围，不是则向下传递Handler处理器
        if (times == 2){
            //假设这里是业务逻辑代码
            System.out.println("第二次面试："+times);
        }
        handler.handleRequest(times);
    }
}
