package com.code.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 图书借阅表
 *
 * @author
 * @since 2023-11-18
 */
@Data
@TableName("sys_book_borrow")
public class BookBorrow implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图书id
     */
    private Integer bookId;

    /**
     * 借阅用户id
     */
    private Integer userId;

    /**
     * 所属单位id
     */
    private Integer deptId;

    /**
     * 借阅时间
     */
    private String borrowTime;

    /**
     * 归还时间
     */
    private String returnTime;

    /**
     * 借阅理由
     */
    private String reason;

    /**
     * 借阅人
     */
    private String userName;

    /**
     * 借阅人联系方式
     */
    private String phone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 借阅审核状态
     */
    private Integer borrowStatus;

//    @TableField(exist = false)
//    private String bookName;

    @Override
    public String toString() {
        return "BookBorrow{" +
        "id=" + id +
        ", bookId=" + bookId +
        ", userId=" + userId +
        ", borrowTime=" + borrowTime +
        ", returnTime=" + returnTime +
        ", reason=" + reason +
        ", userName=" + userName +
        ", phone=" + phone +
        ", remark=" + remark +
        "}";
    }
}
