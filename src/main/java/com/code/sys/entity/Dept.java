package com.code.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 
 * @since 2023-11-17
 */
@Data
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单位名称
     */
    private String name;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 单位性质
     */
    private String unitNature;


    @Override
    public String toString() {
        return "Dept{" +
        "id=" + id +
        ", name=" + name +
        ", contacts=" + contacts +
        ", phone=" + phone +
        ", email=" + email +
        ", address=" + address +
        ", unitNature=" + unitNature +
        "}";
    }
}
