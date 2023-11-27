package com.code.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.sys.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenu(Menu menu);


    /**
     * 根据用户id查询菜单
     * @param available
     * @param userId
     * @return
     */
    List<Menu> queryMenuByUid(@Param("available") Integer available,@Param("uid") Integer userId);
}
