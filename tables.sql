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

update user set password='$2a$10$4Xrxx8LY/aMrDdPPVy9XqOykC0BmXUMtuB5MZVMPGAQx/Ue8Wq9gC' where username ='zhangsan';
update user set password='$2a$10$TrnJKNQudf1kg7g0shsi7eaDzC.lsHvIf8.WZimeO5mPzOikdnKl2' where username ='lisi';
update user set password='$2a$10$OMvfetv9G6dNKsoKyEhCguykoswjRSIb3e0Jq4v9ulkTDjX6t5k76' where username ='wangwu';
