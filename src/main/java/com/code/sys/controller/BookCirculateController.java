package com.code.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.code.sys.entity.User;
import com.code.sys.entity.Book;
import com.code.sys.service.BookService;
import com.code.sys.service.UserService;
import com.code.sys.utils.DataGridView;
import com.code.sys.utils.ResultObj;
import com.code.sys.utils.WebUtils;
import com.code.sys.vo.QueryVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.code.sys.service.BookCirculateService;
import com.code.sys.entity.BookCirculate;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 图书流通表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/bookCirculate")
public class BookCirculateController {

    @Resource
    private BookCirculateService bookCirculateService;
    @Resource
    private UserService userService;
    @Resource
    private BookService bookService;

    /**
     * 流入管理
    * 加载列表返回DataGridView
    * @return
    */
    @RequestMapping("findPage")
    public DataGridView findPage(QueryVo queryVo){
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        List<BookCirculate> data = this.bookCirculateService.findPage(user.getDeptId(), 0);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 流出管理
     * 加载列表返回DataGridView
     * @return
     */
    @RequestMapping("findPage1")
    public DataGridView findPage1(QueryVo queryVo){
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        List<BookCirculate> data = this.bookCirculateService.findPage(user.getDeptId(), 1);
        return new DataGridView(page.getTotal(),data);
    }
    /**
     * 流通
     * @return
     */
    @RequestMapping("save")
    public ResultObj save(BookCirculate bookCirculate){
        try{
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            user = userService.getById(user);
            Book book = bookService.getById(bookCirculate.getBookId());
            bookCirculate.setDeptId(book.getDeptId());
            bookCirculate.setCirculateDeptId(user.getDeptId());
//            bookCirculate.setUserName(user.getRealname());
//            bookCirculate.setPhone(user.getPhone());
            bookCirculate.setCirculateTime(DateUtil.now());
            bookCirculate.setCirculateStatus(0);
            this.bookCirculateService.saveOrUpdate(bookCirculate);
            return ResultObj.OPERATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }

    /**
     * 归还
     * @return
     */
    @RequestMapping("update")
    public ResultObj update(BookCirculate circulate) {
        try {
            // 归还申请
            if (3 == circulate.getCirculateStatus()) {
                circulate.setReturnTime(DateUtil.now());
            }

            this.bookCirculateService.updateById(circulate);
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
            this.bookCirculateService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
