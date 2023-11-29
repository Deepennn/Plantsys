package com.code.sys.service.impl;

import com.code.sys.entity.Book;
import com.code.sys.mapper.BookMapper;
import com.code.sys.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  图书 服务实现类
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
}
