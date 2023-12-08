package com.plantsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.plantsys.entity.MonitorValue;

/**
* @author Administrator
* @description 针对表【monitor_value】的数据库操作Service
* @createDate 2023-12-05 15:09:43
*/
public interface MonitorValueService extends IService<MonitorValue> {
    int deleteByPlantId(Integer plantId);
}