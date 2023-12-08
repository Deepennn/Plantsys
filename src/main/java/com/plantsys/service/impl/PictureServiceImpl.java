package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Picture;
import com.plantsys.mapper.PictureMapper;
import com.plantsys.service.PictureService;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【picture】的数据库操作Service实现
* @createDate 2023-12-05 15:07:49
*/
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture>
    implements PictureService{

    @Override
    public int deleteByPlantId(Integer plantId) {
        return this.baseMapper.deleteByPlantId(plantId);
    }
}




