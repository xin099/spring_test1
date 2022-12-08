CREATE TABLE student (
                         sid varchar(10) NOT NULL comment 'ID',
                         sName varchar(20) DEFAULT NULL comment '姓名',
                         sAge datetime DEFAULT '1980-10-12 23:12:36' comment '出生日期',
                         sSex varchar(10) DEFAULT NULL comment '性别',
                         PRIMARY KEY (sid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '学生表';
CREATE TABLE course (
                        cid varchar(10) NOT NULL,
                        cName varchar(10) DEFAULT NULL,
                        tid int(20) DEFAULT NULL,
                        PRIMARY KEY (cid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '课程表';
CREATE TABLE sc (
                    sid varchar(10) DEFAULT NULL,
                    cid varchar(10) DEFAULT NULL,
                    score int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '成绩表';
CREATE TABLE taacher (
                         tid int(10) DEFAULT NULL,
                         tName varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '教师表';


insert  into taacher(tid,tName) values (1,'李老师'),(2,'何以琛'),(3,'叶平');
insert  into student(sid,sName,sAge,sSex) values ('1001','张三丰','1980-10-12 23:12:36','男'),('1002','张无极','1995-10-12 23:12:36','男'),('1003','李奎','1992-10-12 23:12:36','女'),('1004','李元宝','1980-10-12 23:12:36','女'),('1005','李世明','1981-10-12 23:12:36','男'),('1006','赵六','1986-10-12 23:12:36','男'),('1007','田七','1981-10-12 23:12:36','女');
insert  into sc(sid,cid,score) values ('1','001',80),('1','002',60),('1','003',75),('2','001',85),('2','002',70),('3','004',100),('3','001',90),('3','002',55),('4','002',65),('4','003',60);
insert  into course(cid,cName,tid) values ('001','企业管理',3),('002','马克思',3),('003','UML',2),('004','数据库',1),('005','英语',1);

-- 查询001课程比002课程高的所有学生的学号

