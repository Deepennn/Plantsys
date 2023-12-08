package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Plant;
import com.plantsys.mapper.PlantMapper;
import com.plantsys.service.PlantService;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【plant】的数据库操作Service实现
* @createDate 2023-12-08 17:02:01
*/
@Service
public class PlantServiceImpl extends ServiceImpl<PlantMapper, Plant>
    implements PlantService{

}




