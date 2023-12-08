package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.PlantInfo;
import com.plantsys.mapper.PlantInfoMapper;
import com.plantsys.service.PlantInfoService;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【plant_info】的数据库操作Service实现
* @createDate 2023-12-08 18:02:05
*/
@Service
public class PlantInfoServiceImpl extends ServiceImpl<PlantInfoMapper, PlantInfo>
    implements PlantInfoService{

}




