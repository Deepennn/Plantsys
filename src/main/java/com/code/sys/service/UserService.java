package com.code.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.sys.entity.User;
import com.code.sys.utils.DataGridView;
import com.code.sys.vo.UserVo;

/**
 * 用户服务接口
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param userVo
     * @return
     */
    User login(UserVo userVo);

    /**
     * 用户注册
     * @param userVo
     * @return
     */
    void addRegister(UserVo userVo);
    /**
     * 重置密码
     * @param userid
     */
     void resetUserPwd(Integer userid);

    /**
     * 修改密码
     * @param userVo
     */
    void updateUserPwd(UserVo userVo);

    /**
     * 加载用户管理分配角色的数据
     * @param userid
     * @return
     */
    DataGridView queryUserRole(Integer userid);

    /**
     * 保存用户和角色的关系
     * @param userVo
     */
    void saveUserRole(UserVo userVo);
}
