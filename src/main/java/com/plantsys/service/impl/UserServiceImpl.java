package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.User;
import com.plantsys.service.UserService;
import com.plantsys.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author JIALIANGLI
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-12-04 15:23:22
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    UserMapper mapper;

    @Override
    public int addSelective(User user) {
        return mapper.addSelective(user);
    }

    @Override
    public int updateSelective(User user) {
        return mapper.updateSelective(user);
    }

    @Override
    public List<User> selectByMap(Map<String, Object> map) {
        return mapper.selectByMap(map);
    }

    @Override
    public int deleteByUserId(Integer userId) {
        return mapper.deleteByUserId(userId);
    }
}




