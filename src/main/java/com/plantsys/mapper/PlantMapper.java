package com.plantsys.mapper;
import org.apache.ibatis.annotations.Param;

import com.plantsys.entity.Plant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
* @author JIALIANGLI
* @description 针对表【plant】的数据库操作Mapper
* @createDate 2023-12-04 15:33:36
* @Entity com.plantsys.entity.Plant
*/
public interface PlantMapper extends BaseMapper<Plant> {
    int addSelective(Plant plant);

    int updateSelective(Plant plant);

    List<Plant>  selectByMap(@Param("map") Map<String, Object> map);

    int deleteByPlantId(@Param("plantId") Integer plantId);
}




