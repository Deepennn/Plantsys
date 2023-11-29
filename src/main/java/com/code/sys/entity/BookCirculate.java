package com.code.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author
 * @since 2023-11-18
 */
@Data
@TableName("sys_book_circulate")
public class BookCirculate implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图书id
     */
    private Integer bookId;

    /**
     * 图书所属单位id
     */
    private Integer deptId;

    /**
     * 借阅单位用户id
     */
    private Integer circulateDeptId;

    /**
     * 流通时间
     */
    private String circulateTime;

    /**
     * 归还时间
     */
    private String returnTime;

    /**
     * 流通理由
     */
    private String reason;

    /**
     * 申请人人
     */
    private String userName;

    /**
     * 申请人联系方式
     */
    private String phone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 流通审核状态 0待审核 1已审核 2已结束 3归还待审核
     */
    private Integer circulateStatus;

    @TableField(exist = false)
    private String bookName;

    @TableField(exist = false)
    private String circulateDeptName;

    @TableField(exist = false)
    private String deptName;

    @Override
    public String toString() {
        return "BookCirculate{" +
        "id=" + id +
        ", bookId=" + bookId +
        ", deptId=" + deptId +
        ", circulateDeptId=" + circulateDeptId +
        ", circulateTime=" + circulateTime +
        ", returnTime=" + returnTime +
        ", reason=" + reason +
        ", userName=" + userName +
        ", phone=" + phone +
        ", remark=" + remark +
        ", circulateStatus=" + circulateStatus +
        "}";
    }
}
