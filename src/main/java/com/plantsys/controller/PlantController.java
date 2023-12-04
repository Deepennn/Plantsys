package com.plantsys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plantsys.entity.*;
import com.plantsys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PlantController {


    @Autowired
    PlantService plantService;


    //前往添加图书页面
    @RequestMapping("toAddPlant.do")
    public ModelAndView toAddPlant() {
        return new ModelAndView("view/plant/add");
    }


    //添加图书
    @RequestMapping("addPlant")
    @ResponseBody
    public boolean addPlant(Plant plant){
        boolean re= false;
        plantService.addSelective(plant);
        re = true;
        return re;
    }


    //删除图书
    @RequestMapping("deletePlant")
    @ResponseBody
    public boolean deletePlant(Integer plantId){
        boolean re= false;
        plantService.deleteByPlantId(plantId);
        re = true;
        return  re;
    }


    //查询图书
    @RequestMapping("plantList.do")
    public ModelAndView plantList(
            @RequestParam(defaultValue="1") Integer currentPage, HttpServletRequest request, HttpSession session, Map<String, Object> _map){
        String keyword = request.getParameter("keyword");

        Map<String,Object> map = new HashMap<>();
        map.put("keyword", keyword);

        List<Plant> rawplantlist = plantService.selectByMap(map);

        PageHelper.startPage(currentPage,10);
        PageInfo<Plant> plantlist =new PageInfo<Plant>(rawplantlist,8);

        _map.put("plantlist", plantlist);
        return new ModelAndView("view/plant/list");
    }


    //查询图书
    @RequestMapping("plantList2.do")
    public ModelAndView plantList2(HttpServletRequest request, HttpSession session, Map<String, Object> _map){
        String keyword = request.getParameter("keyword");
        Map<String,Object> map = new HashMap<>();
        map.put("keyword", keyword);
        List<Plant> plantlist = plantService.selectByMap(map);
        _map.put("plantlist", plantlist);
        return new ModelAndView("view/plant/list2");
    }

}
