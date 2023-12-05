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
import java.util.*;

@RestController
public class MaintenanceTaskController {

    @Autowired
    UserService userService;
    @Autowired
    MaintenanceTaskService maintenanceTaskService;


    /*
     * 状态码:
     * 0:待完成
     * 1:已完成
     * */

    //通过审核
    @RequestMapping("completeMaintenanceTask")
    @ResponseBody
    public boolean completeMaintenanceTask(Integer taskId){
        boolean re = false;
        Map<String,Object> map = new HashMap<>();
        map.put("taskId", taskId);
        MaintenanceTask maintenanceTask = maintenanceTaskService.selectByMap(map).get(0);
        if(maintenanceTask.getTaskStatus()==0){
            maintenanceTask.setTaskStatus(1);
            maintenanceTaskService.updateSelective(maintenanceTask);
        }
        re = true;
        return re;
    }


    //前往添加养护任务页面
    @RequestMapping("toAddMaintenanceTask.do")
    public ModelAndView toAddMaintenanceTask() {
        return new ModelAndView("view/maintenance_task/add");
    }


    //添加养护任务
    @RequestMapping("addMaintenanceTask")
    @ResponseBody
    public boolean addMaintenanceTask(MaintenanceTask maintenanceTask){
        boolean re= false;
        maintenanceTaskService.addSelective(maintenanceTask);
        re = true;
        return re;
    }


    //删除养护任务
    @RequestMapping("deleteMaintenanceTask")
    @ResponseBody
    public boolean deleteMaintenanceTask(Integer taskId){
        boolean re= false;
        maintenanceTaskService.deleteByTaskId(taskId);
        re = true;
        return  re;
    }


    //查询养护任务
    @RequestMapping("maintenanceTaskList.do")
    public ModelAndView maintenanceTaskList(HttpServletRequest request, HttpSession session, Map<String, Object> _map){
        Map<String,Object> map = new HashMap<>();
        String keyword = request.getParameter("keyword");
        map.put("keyword", keyword);
        List<MaintenanceTask> maintenancetasklist = maintenanceTaskService.selectByMap(map);
        _map.put("maintenancetasklist", maintenancetasklist);
        return new ModelAndView("view/maintenance_task/list");
    }

}
