package com.code.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.sys.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询所有角色
     * @param role
     * @return
     */
    List<Role> queryAllRole(Role role);

    /**
     * 根据用户id查询角色
     * @param available
     * @param userid
     * @return
     */
    List<Role> queryRoleByUid(@Param("available") Integer available,@Param("uid") Integer userid);
}
