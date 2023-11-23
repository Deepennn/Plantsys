package com.code.sys.service.impl;

import com.code.sys.entity.Book;
import com.code.sys.entity.BookBorrow;
import com.code.sys.mapper.BookBorrowMapper;
import com.code.sys.service.BookBorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.sys.vo.BookVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 图书借阅表 服务实现类
 * @author
 * @since 2023-11-18
 */
@Service
public class BookBorrowServiceImpl extends ServiceImpl<BookBorrowMapper, BookBorrow> implements BookBorrowService {

    @Override
    public List<BookBorrow> findPage(Integer userId, Integer deptId) {
        return this.baseMapper.findPage(userId, deptId);
    }

    @Override
    public List<BookVo> echarts(Integer deptId, Integer type) {
        if (type == 1) {
            return this.baseMapper.echartsYear(deptId);
        } else {
            return this.baseMapper.echartsMonth(deptId);
        }
    }
}
