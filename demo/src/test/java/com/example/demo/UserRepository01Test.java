package com.example.demo;

import com.example.Application;
import com.example.dataobject.UserDO;
import com.example.repository.UserRepository01;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @program: spring_test1
 * @description: UserRepository01Test
 * @author: XX
 * @create: 2022-10-12 09:57
 **/
@RunWith(SpringRunner.class) //@RunWith 就是一个运行器,可以加载Springboot测试注解
@SpringBootTest
public class UserRepository01Test {
    @Autowired
    private UserRepository01 userRepository01;

    @Test //插入一条记录
    public void testSave(){
        UserDO user = new UserDO().setUsername(UUID.randomUUID().toString())
                .setPassword("nicai").setCreateTime(new Date());
        userRepository01.save(user);
    }
    @Test // 更新一条记录
    public void testUpdate(){
        //先查询一条记录
        //Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
        Optional<UserDO> userDO = userRepository01.findById(4);
        Assert.isTrue(userDO.isPresent(),"记录不能为空");

        //更新一条记录
        UserDO updateUser = userDO.get();
        updateUser.setPassword("hehehe");
        userRepository01.save(updateUser);
    }
    @Test //根据ID删除一条记录
    public void testDelete(){
        userRepository01.deleteById(4);
    }
    @Test //根据ID查询一条记录
    public void testSelectById(){
        Optional<UserDO> userDO=userRepository01.findById(5);
        System.out.println(userDO.get());
    }
    @Test
    public void testSelectByIds(){
        Iterable<UserDO> users = userRepository01.findAllById(Arrays.asList(5,6));
        users.forEach(System.out::println);
    }


}
