package com.code.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.sys.entity.Menu;
import com.code.sys.utils.DataGridView;
import com.code.sys.vo.MenuVo;

import java.util.List;

/**
 * 菜单管理的服务接口
 *
 */
public interface MenuService extends IService<Menu> {

    /**
     * 查询所有的菜单返回List
     * @param menuVo
     * @return
     */
     List<Menu> queryAllMenuForList(MenuVo menuVo);

     /**
      *根据用户id查询用户可用菜单
      * */
     List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);

}
