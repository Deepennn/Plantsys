package com.code.sys.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.sys.constant.SysConstant;
import com.code.sys.entity.User;
import com.code.sys.mapper.UserMapper;
import com.code.sys.service.UserService;
import com.code.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


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
}
