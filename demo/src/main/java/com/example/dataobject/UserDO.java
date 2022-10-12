package com.example.dataobject;



import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.Date;


/**
 * @program: spring_test1
 * @description: 用户类
 * @author: XX
 * @create: 2022-10-12 09:19
 **/
@Entity //标识这个实体类时一个JPA实体
@Table(name="users") //自定义设置这个实体类在数据库所对应的表名
//@Data //lombok 中自动生成setget方法
@Log4j2
public class UserDO {

    /**
     * 用户编号
     */
    @Id //把这个类里面的变量设置为主键ID
    @GeneratedValue(strategy = GenerationType.IDENTITY,//strategy 设置使用数据库主键自增策略
    generator = "JDBC")//generator 设置插入完成后，查询最后生成的ID填充到该属性中。
    private Integer id;
    /**
     * 账号
     * @Column(nullable = "是否可以空")
     */
    @Column(nullable = false)
    private String username;
    /**
     * 密码
     *
     * ps：生成环境下，使用密文
     */
    @Column(nullable = false)
    private String password;
    /**
     * 创建时间
     * @Column(name = “自定义字段名”,nullable = "是否可以空")
     */
    @Column(name = "create_time",nullable = false)
    private Date createTime;



    /**
     * getset 方法
     * @return
     */

    public Integer getId() {
        return id;
    }

    public UserDO setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getUsername() {
        return username;
    }

    public UserDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDO setPassword(String password) {
        this.password = password;
        log.info("hhhhhhhhhhhhhhhhhhhhh");
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserDO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
