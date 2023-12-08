package com.plantsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plantsys.entity.Picture;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【picture】的数据库操作Mapper
* @createDate 2023-12-05 15:07:49
* @Entity com.plantsys.entity.Picture
*/
public interface PictureMapper extends BaseMapper<Picture> {
    int deleteByPlantId(@Param("plantId") Integer plantId);
}




