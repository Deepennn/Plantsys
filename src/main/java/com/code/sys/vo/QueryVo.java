package com.code.sys.vo;


import lombok.Data;

/**
 */
@Data
public class QueryVo {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    private String name;
    private Integer id;
    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String press;

}
