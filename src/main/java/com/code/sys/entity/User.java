package com.code.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
@TableName("sys_user")
public class User implements Serializable {
    /**
     * TableId:主键注解
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String loginname;

    private String realname;

    private Integer sex;

    private String address;

    private String phone;

    private String pwd;

    private Integer rid;

    private String createTime;

    private String email;

    private Integer isRegister;

    private String img;

    private Integer status;

    private Integer deptId;

    private String lastTime;

    @TableField(exist = false)
    private String deptName;

}
