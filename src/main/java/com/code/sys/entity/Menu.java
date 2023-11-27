package com.code.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_menu")
public class Menu {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 父菜单ID字段
    private Integer pid;

    private String title;

    private String href;
    // 展开状态字段
    private Integer spread;



    private String icon;

    private Integer available;

    public Menu() {
    }

    public Menu(Integer id, Integer pid, String title, String href, Integer spread, String icon, Integer available) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.href = href;
        this.spread = spread;
        this.icon = icon;
        this.available = available;
    }
}
