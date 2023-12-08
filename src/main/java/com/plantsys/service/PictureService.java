package com.plantsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.plantsys.entity.Picture;

/**
* @author Administrator
* @description 针对表【picture】的数据库操作Service
* @createDate 2023-12-05 15:07:49
*/
public interface PictureService extends IService<Picture> {
    int deleteByPlantId(Integer plantId);
}
