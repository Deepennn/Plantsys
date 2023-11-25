package com.code.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色表
 */
@Data
@TableName("sys_role")
public class Role {
    @TableId(value = "roleid", type = IdType.AUTO)
    private Integer roleid;

    private String rolename;

    private String ceateTime;

    private Integer status;
    private String note;
}
