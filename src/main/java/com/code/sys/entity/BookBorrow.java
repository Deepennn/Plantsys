package com.code.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 图书借阅表
 * </p>
 *
 * @author
 * @since 2023-11-18
 */
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
    /**
     * 归还审核状态
     */
    private Integer returnStatus;
    private Integer staffId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(Integer borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

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
