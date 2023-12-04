package com.plantsys.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.plantsys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author JIALIANGLI
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-12-04 15:23:22
* @Entity com.plantsys.entity.User
*/
public interface UserMapper extends BaseMapper<User> {
    int addSelective(User user);

    int updateSelective(User user);

    List<User> selectByMap(@Param("map") Map<String, Object> map);

    int deleteByUserId(@Param("userId") Integer userId);
}




