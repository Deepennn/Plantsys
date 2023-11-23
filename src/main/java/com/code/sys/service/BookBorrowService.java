package com.code.sys.service;

import com.code.sys.entity.Book;
import com.code.sys.entity.BookBorrow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.code.sys.vo.BookVo;

import java.util.List;
import java.util.Map;

/**
 * 图书借阅表 服务类
 * @author
 * @since 2023-11-18
 */
public interface BookBorrowService extends IService<BookBorrow> {

    List<Book> findPage(Integer userId, Integer deptId);

    List<BookVo> echarts(Integer deptId, Integer type);
}
