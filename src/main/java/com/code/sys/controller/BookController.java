package com.code.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.code.sys.entity.Dept;
import com.code.sys.entity.User;
import com.code.sys.service.DeptService;
import com.code.sys.utils.DataGridView;
import com.code.sys.utils.ResultObj;
import com.code.sys.utils.WebUtils;
import com.code.sys.vo.QueryVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import com.code.sys.service.BookService;
import com.code.sys.entity.Book;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;
    @Resource
    private DeptService deptService;

    /**
    * 加载列表返回DataGridView
    * @return
    */
    @RequestMapping("findPage")
    public DataGridView findPage(QueryVo queryVo){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");

        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (user.getType() == 2) {
            queryWrapper.eq("dept_id", user.getDeptId());
        }
        if (user.getType() == 3) {
            queryWrapper.eq("status", 1);
            queryWrapper.eq("is_open", 1);
        }
        queryWrapper.like(null != queryVo.getId(), "id", queryVo.getId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getName()), "name", queryVo.getName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getAuthor()), "author", queryVo.getAuthor());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getPress()), "press", queryVo.getPress());
        List<Book> data = this.bookService.list(queryWrapper);
        if (null != data) {
            data.stream().map(item -> {
                Dept dept = deptService.getById(item.getDeptId());
                item.setDeptName(null != dept ? dept.getName() : "");
                return item;
            }).collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 加载其他单位列表返回DataGridView
     * @return
     */
    @RequestMapping("findOtherPage")
    public DataGridView findOtherPage(QueryVo queryVo){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (user.getType() == 2) {
            queryWrapper.ne("dept_id", user.getDeptId());
            queryWrapper.eq("is_open", 1);
        }
        queryWrapper.like(null != queryVo.getId(), "id", queryVo.getId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getName()), "name", queryVo.getName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getAuthor()), "author", queryVo.getAuthor());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getPress()), "press", queryVo.getPress());
        List<Book> data = this.bookService.list(queryWrapper);
        if (null != data) {
            data.stream().map(item -> {
                Dept dept = deptService.getById(item.getDeptId());
                item.setDeptName(null != dept ? dept.getName() : "");
                return item;
            }).collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(),data);
    }
    /**
     * 添加/编辑
     * @return
     */
    @RequestMapping("save")
    public ResultObj save(Book book){
        try{
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            if (null == book.getId()) {
                book.setDeptId(user.getDeptId());
            }
            this.bookService.saveOrUpdate(book);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 添加/编辑
     * @return
     */
    @RequestMapping("update")
    public ResultObj update(Book book){
        try{
            this.bookService.updateById(book);
            return ResultObj.OPERATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }

    /**
    * 删除
    * @return
    */
    @RequestMapping("delete")
    public ResultObj delete(Integer id){
        try {
            Book book = this.bookService.getById(id);
            if (book.getStatus() == 2) {
                return new ResultObj(-1, "删除失败，图书正在借阅中");
            }
            this.bookService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
