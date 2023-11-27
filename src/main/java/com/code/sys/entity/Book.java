package com.code.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 图书表
 */
@Data
@TableName("sys_book")
public class Book implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id")
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 出版时间
     */
    private String publishTime;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String press;

    /**
     * 图书分类
     */
    private String type;

    /**
     * 页数
     */
    private String pages;

    /**
     * 图片
     */
    private String img;

    /**
     * 价格
     */
    private String price;

    /**
     * 借阅状态
     */
    private Integer status;

    private Integer isOpen;

    private Integer deptId;

    /**
     * 借阅时间
     */
    @TableField(exist = false)
    private String borrowTime;

    /**
     * 归还时间
     */
    @TableField(exist = false)
    private String returnTime;

    /**
     * 借阅审核状态
     */
    @TableField(exist = false)
    private Integer borrowStatus;

    @TableField(exist = false)
    private String deptName;
    @Override
    public String toString() {
        return "Book{" +
        "id=" + id +
        ", name=" + name +
        ", publishTime=" + publishTime +
        ", author=" + author +
        ", press=" + press +
        ", type=" + type +
        ", pages=" + pages +
        ", img=" + img +
        "}";
    }
}
