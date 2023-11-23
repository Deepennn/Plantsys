package com.code.sys.service;

import com.code.sys.entity.BookCirculate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 图书流通表 服务类
 * @author
 * @since 2023-11-18
 */
public interface BookCirculateService extends IService<BookCirculate> {

    List<BookCirculate> findPage(Integer circulateDeptId, Integer type);

}
