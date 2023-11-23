package com.code.sys.mapper;

import com.code.sys.entity.Book;
import com.code.sys.entity.BookBorrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.sys.vo.BookVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图书借阅表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2023-11-18
 */
public interface BookBorrowMapper extends BaseMapper<BookBorrow> {

    List<BookBorrow> findPage(@Param("userId") Integer userId, @Param("deptId") Integer deptId);

    List<BookVo> echartsYear(@Param("deptId") Integer deptId);
    List<BookVo> echartsMonth(@Param("deptId") Integer deptId);
}
