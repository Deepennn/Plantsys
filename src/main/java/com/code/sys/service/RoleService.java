package com.code.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.sys.entity.Role;
import com.code.sys.utils.DataGridView;
import com.code.sys.vo.RoleVo;

/**
 *
 */
public interface RoleService extends IService<Role> {
    /**
     * 查询所有角色
     * @param roleVo
     * @return
     */
    public DataGridView queryAllRole(RoleVo roleVo);


    /**
     * 加载角色管理分配菜单的json
     * @param roleid
     * @return
     */
    public DataGridView initRoleMenuTreeJson(Integer roleid);


}
