package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Plant;
import com.plantsys.service.PlantService;
import com.plantsys.mapper.PlantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author JIALIANGLI
* @description 针对表【plant】的数据库操作Service实现
* @createDate 2023-12-04 15:33:36
*/
@Service
public class PlantServiceImpl extends ServiceImpl<PlantMapper, Plant>
    implements PlantService{
    @Autowired
    PlantMapper mapper;

    @Override
    public int addSelective(Plant plant) {
        return mapper.addSelective(plant);
    }

    @Override
    public int updateSelective(Plant plant) {
        return mapper.updateSelective(plant);
    }

    @Override
    public List<Plant> selectByMap(Map<String, Object> map) {
        return mapper.selectByMap(map);
    }

    @Override
    public int deleteByPlantId(Integer plantId) {
        return mapper.deleteByPlantId(plantId);
    }
}




