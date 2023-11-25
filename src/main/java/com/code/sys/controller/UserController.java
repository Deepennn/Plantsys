package com.code.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.code.sys.constant.SysConstant;
import com.code.sys.entity.Book;
import com.code.sys.entity.Dept;
import com.code.sys.service.DeptService;
import com.code.sys.utils.AppFileUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.code.sys.entity.User;
import com.code.sys.service.UserService;
import com.code.sys.utils.DataGridView;
import com.code.sys.utils.ResultObj;
import com.code.sys.utils.WebUtils;
import com.code.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;
import nl.captcha.Captcha;
/**
 * 用户管理控制器
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;

    /**
     * 加载用户列表返回DataGridView
     * @param userVo
     * @return
     */
    @RequestMapping("findPage")
    public DataGridView findPage(UserVo userVo){
        Page<User> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> data = this.userService.list(queryWrapper);
        if (null != data) {
            data.stream().map(item -> {
                Dept dept = this.deptService.getById(item.getDeptId());
                item.setDeptName(null != dept ? dept.getName() : "");
                return item;
            }).collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(),data);
    }
    /**
     * 添加或修改用户
     * @param userVo
     * @return
     */
    @RequestMapping("save")
    public ResultObj save(UserVo userVo){
        try {
            System.out.println("userVo.getId():"+userVo.getId());
            if (null == userVo.getId()) {
                // 密码为工号（登录名称）后四位
                if (userVo.getLoginname().length() >= 4) {
                    String pwd = userVo.getLoginname().substring(userVo.getLoginname().length() - 4);
                    System.out.println("pwd:"+pwd);
                    userVo.setPwd(DigestUtils.md5DigestAsHex(pwd.getBytes()));
                } else {
                    userVo.setPwd(DigestUtils.md5DigestAsHex(userVo.getLoginname().getBytes()));
                }
                userVo.setImg("2023-11-23/111111.png");
                userVo.setCreateTime(DateUtil.now());

            }

            // 编号唯一
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("loginname", userVo.getLoginname());
            if (null != userVo.getId()) {
                wrapper.ne("id", userVo.getId());
            }
            int count = this.userService.count(wrapper);
            if (count > 0) {
                return new ResultObj(-1, "用户名已存在");
            }
//            User user=this.userService.getById(userVo.getId());
            userVo.setRid(2);
            userVo.setType(2);
            this.userService.saveOrUpdate(userVo);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo){

        try{
            // 加上ID
            User user=(User)WebUtils.getHttpSession().getAttribute("user");
            userVo.setId(user.getId());

            this.userService.saveOrUpdate(userVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /**
     * 删除用户
     * @param userVo
     * @return
     */
    @RequestMapping("delete")
    public ResultObj delete(UserVo userVo){
        try {
            this.userService.removeById(userVo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 重置密码
     * @param userVo
     * @return
     */
    @RequestMapping("resetUserPwd")
    public ResultObj resetUserPwd(UserVo userVo){
        try {
            this.userService.resetUserPwd(userVo.getId());
            return ResultObj.RESET_SUCCESS;
        }catch (Exception e){
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 修改密码
     * @param userVo
     * @return
     */
    @RequestMapping("updateUserPwd")
    public ResultObj updateUserPwd(UserVo userVo){
        try {
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            userVo.setId(user.getId());
            this.userService.updateUserPwd(userVo);
            return ResultObj.RESET_SUCCESS;
        }catch (Exception e){
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 加载用户管理的分配角色的数据
     * @param userVo
     * @return
     */
    @RequestMapping("initUserRole")
    public DataGridView initUserRole(UserVo userVo){
        return this.userService.queryUserRole(userVo.getId());
    }

    /**
     * 保存用户和角色的关系
     * @param userVo
     * @return
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(UserVo userVo){
        try {
            this.userService.saveUserRole(userVo);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping("getUser")
    public DataGridView getUser(){
        try {
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            user = this.userService.getById(user.getId());
            Dept dept = deptService.getById(user.getDeptId());
            user.setDeptName(null != dept ? dept.getName() : "");
            return new DataGridView(user);
        }catch (Exception e){
            e.printStackTrace();
            return new DataGridView();
        }
    }

}
