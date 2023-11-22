package com.code.sys.mapper;

import com.code.sys.entity.BookCirculate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 图书流通表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2023-11-18
 */
public interface BookCirculateMapper extends BaseMapper<BookCirculate> {

    List<BookCirculate> findListPage(@Param("deptId") Integer deptId, @Param("type") Integer type);
}
