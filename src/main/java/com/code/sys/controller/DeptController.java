package com.code.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.code.sys.entity.User;
import com.code.sys.service.UserService;
import com.code.sys.utils.DataGridView;
import com.code.sys.utils.ResultObj;
import com.code.sys.vo.QueryVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.code.sys.service.DeptService;
import com.code.sys.entity.Dept;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 单位表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;
    @Resource
    private UserService userService;

    /**
    * 加载列表返回DataGridView
    * @return
    */
    @RequestMapping("findPage")
    public DataGridView findPage(QueryVo queryVo){
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getName()), "name", queryVo.getName());
        List<Dept> data = this.deptService.list(queryWrapper);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加/编辑
     * @return
     */
    @RequestMapping("save")
    public ResultObj save(Dept dept){
        try{
            this.deptService.saveOrUpdate(dept);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
    * 删除
    * @return
    */
    @RequestMapping("delete")
    public ResultObj delete(Integer id){
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("dept_id", id);
            int count = this.userService.count(queryWrapper);
            if (count > 0) {
                return new ResultObj(-1, "该单位下有所属员工，不允许删除");
            }
            this.deptService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有
     */
    @RequestMapping("loadAllForSelect")
    public DataGridView loadAllForSelect() {
        List<Dept> list = this.deptService.list(null);
        return new DataGridView(list);
    }
}
