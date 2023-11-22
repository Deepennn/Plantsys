package com.code.sys.service.impl;

import com.code.sys.entity.BookCirculate;
import com.code.sys.mapper.BookCirculateMapper;
import com.code.sys.service.BookCirculateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 图书流通表 服务实现类
 * </p>
 *
 * @author
 * @since 2023-11-18
 */
@Service
public class BookCirculateServiceImpl extends ServiceImpl<BookCirculateMapper, BookCirculate> implements BookCirculateService {

    @Override
    public List<BookCirculate> findPage(Integer circulateDeptId, Integer type) {
        return this.baseMapper.findListPage(circulateDeptId, type);
    }
}
