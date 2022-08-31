package com.example.Service;

import com.example.mapper.UserMapper;
import com.example.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: spring_test1
 * @description: 调用Mapper层
 * @author: XX
 * @create: 2022-08-31 14:41
 **/
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    //往数据里插入一个用户,用于注册功能
    public int insert(User user){
        return userMapper.insert(user);
    }
}
