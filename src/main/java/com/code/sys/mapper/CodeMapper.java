package com.code.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.sys.entity.Book;
import com.code.sys.entity.BookBorrow;
import com.code.sys.entity.Code;
import com.code.sys.vo.BookVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 图书借阅表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2023-11-18
 */
public interface CodeMapper extends BaseMapper<Code> {
    Code getByCode(@Param("email") String email, @Param("code") String code);
}
