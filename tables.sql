-- 用户表
create table user (
                      userId int primary key auto_increment,
                      username varchar(50) unique,
                      password varchar(255),
                      score int,        -- 天梯积分
                      totalCount int,   -- 比赛总场数
                      winCount int      -- 获胜场数
);
-- 插入数据
INSERT INTO user VALUES(null,"zhangsan","zhangsan",1000,0,0);
INSERT INTO user VALUES(null,"lisi","lisi",1000,0,0);
INSERT INTO user VALUES(null,"wangwu","wangwu",1000,0,0);

select * from user;

