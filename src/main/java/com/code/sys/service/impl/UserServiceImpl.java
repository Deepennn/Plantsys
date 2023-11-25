package com.code.sys.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.sys.constant.SysConstant;
import com.code.sys.entity.Role;
import com.code.sys.entity.User;
import com.code.sys.mapper.RoleMapper;
import com.code.sys.mapper.UserMapper;
import com.code.sys.service.UserService;
import com.code.sys.utils.DataGridView;
import com.code.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
   private RoleMapper roleMapper;
    private UserVo userVo;

    @Override
    public User login(UserVo userVo) {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("loginname", userVo.getLoginname());
            //明文生成密文
            queryWrapper.eq("pwd", DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes()));
            queryWrapper.eq(null != userVo.getRid(), "rid", userVo.getRid());
            return userMapper.selectOne(queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addRegister(UserVo userVo) {
        //设置密码
        userVo.setPwd(DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes()));
        userVo.setCreateTime(DateUtil.now());
        this.userMapper.insert(userVo);
    }

    /**
     * 重置用户密码
     * @param userid
     */
    @Override
    public void resetUserPwd(Integer userid) {
        User user = new User();
        user.setId(userid);
        //设置默认密码
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstant.USER_DEFAULT_PWD.getBytes()));
        //设置完成后更新
        this.userMapper.updateById(user);
    }

    /**
     * 更新密码
     * @param userVo
     */
    @Override
    public void updateUserPwd(UserVo userVo) {
        this.userVo = userVo;
        User user = new User();
        user.setId(userVo.getId());
        //设置密码
        user.setPwd(DigestUtils.md5DigestAsHex(userVo.getPwd().getBytes()));
        //设置完成后更新
        this.userMapper.updateById(user);
    }
    /**
     * 加载用户管理的分配角色的数据
     * @param userid
     * @return
     */
    @Override
    public DataGridView queryUserRole(Integer userid) {
        //1.查询所有可用的角色
        Role role = new Role();
        role.setAvailable(SysConstant.AVAILABLE_TRUE);
        List<Role> allRole = this.roleMapper.queryAllRole(role);
        //2.根据用户ID查询已拥有的角色
        List<Role> userRole=this.roleMapper.queryRoleByUid(SysConstant.AVAILABLE_TRUE,userid);

        List<Map<String,Object>> data = new ArrayList<>();

        for (Role r1 : allRole){
            Boolean LAY_CHECKED=false;
            for (Role r2 : userRole) {
                if (r1.getRoleid()==r2.getRoleid()){
                    LAY_CHECKED=true;
                }
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("roleid",r1.getRoleid());
            map.put("rolename",r1.getRolename());
            map.put("roledesc",r1.getRoledesc());
            map.put("LAY_CHECKED",LAY_CHECKED);
            data.add(map);

        }

        return new DataGridView(data);
    }

    /**
     * 保存用户和角色的关系
     * @param userVo
     */
    @Override
    public void saveUserRole(UserVo userVo) {
        Integer userid = userVo.getId();
        Integer[] roleIds = userVo.getIds();
        //保存关系
        if (roleIds!=null&&roleIds.length>0){
            for (Integer rid : roleIds){
                User user = new User();
                user.setId(userid);
                user.setRid(rid);
                this.userMapper.updateById(user);
            }
        }
    }
}
