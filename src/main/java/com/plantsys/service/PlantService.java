package com.plantsys.service;

import com.plantsys.entity.Plant;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author JIALIANGLI
* @description 针对表【plant】的数据库操作Service
* @createDate 2023-12-04 15:33:36
*/
public interface PlantService extends IService<Plant> {
    int addSelective(Plant plant);

    int updateSelective(Plant plant);

    List<Plant> selectByMap(@Param("map") Map<String, Object> map);;

    int deleteByPlantId(@Param("plantId") Integer plantId);
}
