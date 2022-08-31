package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //往数据里插入一个用户,用于注册功能
    int insert(User user);

    //根据用户名,来查询用户的详细信息,用于登录功能
    User selectByName(String username);

    // 总比赛场数 + 1, 获胜场数 + 1, 天梯分数 + 30
    int userWin(int userId);

    // 总比赛场数 + 1, 获胜场数 不变, 天梯分数 - 30
    int userLose(int userId);

}
