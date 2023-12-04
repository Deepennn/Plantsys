package com.plantsys.controller;

import com.plantsys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.plantsys.entity.*;
import com.plantsys.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {


    @Autowired
    UserService userService;


    /*
    * 对所有Controller参数的说明:
    * _XXX为从前端获取的/Spring注入 的 变量/对象
    * XXX为从数据库获取的/局部设定 的 变量/对象
    * # # # 不要混淆!!!不要混淆!!!不要混淆!!! # # #
    * */


    /*
    * 角色:
    * 1. "系统管理员" - admin
      2. "主管人员" - manager
      3. "养护人员" - maintainer
      4. "监测人员" - monitor
    * */


    //用户登录
    @RequestMapping("login")
    @ResponseBody
    public String login(@RequestParam("username")String loginName,
                        @RequestParam("password")String password,
                        @RequestParam("type")String type,
                        HttpSession session) {
        session.removeAttribute("admin");
        session.removeAttribute("manager");
        session.removeAttribute("maintainer");
        session.removeAttribute("monitor");
        String message="error"; // 标识符用于判断是否登录成功
        Map<String,Object> map = new HashMap<>();
        map.put("loginName",loginName);
        map.put("password",password);
        map.put("rid",type);
        List<User> userlist = userService.selectByMap(map); // 匹配账号
        User user = userlist.size()!=0 ? userlist.get(0) : null;
        if(user!=null){
            switch (user.getRid()) {
                case 1:
                    session.setAttribute("admin", user);
                    message = "adminok";
                    break;
                case 2:
                    session.setAttribute("manager", user);
                    message = "managerok";
                    break;
                case 3:
                    session.setAttribute("maintainer", user);
                    message = "maintainerok";
                    break;
                case 4:
                    session.setAttribute("monitor", user);
                    message = "monitorok";
                    break;
                default:
                    break;
            }
        }
        return message;
    }


    //前往注册页面
    @RequestMapping("toAddUser.do")
    public ModelAndView toAddUser() {
        return new ModelAndView("view/user/add");
    }


    //添加用户
    @RequestMapping("addUser")
    @ResponseBody
    public boolean addUser(User _user, HttpServletRequest request){
        boolean re= false;
        Map<String,Object> map = new HashMap<>();
        map.put("loginName",_user.getLoginName());
        List<User> userlist = userService.selectByMap(map); // 匹配账号
        if(userlist.size()==0){

            // TODO

            re = true;
            return  re;
        }else{
            return re;
        }
    }


    //删除用户
    @RequestMapping("deleteUser")
    @ResponseBody
    public boolean deleteUser(Integer userId){
        boolean re= false;
        userService.deleteByUserId(userId);
        re = true;
        return  re;
    }


    //查询用户
    @RequestMapping("userList.do")
    public ModelAndView userList(HttpSession session, Map<String,Object> _map){
        Map<String,Object> map = new HashMap<>();
        List<User> userlist = userService.selectByMap(map);
        for(User data:userlist) {

            // TODO

        }
        _map.put("userlist", userlist);
        return new ModelAndView("view/user/list");
    }

}
