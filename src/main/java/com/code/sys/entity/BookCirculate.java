package com.code.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 图书流通表
 * </p>
 *
 * @author
 * @since 2023-11-18
 */
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

    /**
     * 归还审核状态 0待审核 1通过
     */
    private Integer returnStatus;

    @TableField(exist = false)
    private String bookName;

    @TableField(exist = false)
    private String circulateDeptName;

    @TableField(exist = false)
    private String deptName;

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

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getCirculateDeptId() {
        return circulateDeptId;
    }

    public void setCirculateDeptId(Integer circulateDeptId) {
        this.circulateDeptId = circulateDeptId;
    }

    public String getCirculateTime() {
        return circulateTime;
    }

    public void setCirculateTime(String circulateTime) {
        this.circulateTime = circulateTime;
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

    public Integer getCirculateStatus() {
        return circulateStatus;
    }

    public void setCirculateStatus(Integer circulateStatus) {
        this.circulateStatus = circulateStatus;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCirculateDeptName() {
        return circulateDeptName;
    }

    public void setCirculateDeptName(String circulateDeptName) {
        this.circulateDeptName = circulateDeptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

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
        ", returnStatus=" + returnStatus +
        "}";
    }
}
