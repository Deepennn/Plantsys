package com.code.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.code.sys.entity.User;
import com.code.sys.entity.Book;
import com.code.sys.service.BookService;
import com.code.sys.service.UserService;
import com.code.sys.utils.DataGridView;
import com.code.sys.utils.ResultObj;
import com.code.sys.utils.WebUtils;
import com.code.sys.vo.BookVo;
import com.code.sys.vo.QueryVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.code.sys.service.BookBorrowService;
import com.code.sys.entity.BookBorrow;

import org.springframework.web.bind.annotation.RestController;

/**
 * 图书借阅表 前端控制器
 */
@RestController
@RequestMapping("/bookBorrow")
public class BookBorrowController {

    @Resource
    private BookBorrowService bookBorrowService;
    @Resource
    private UserService userService;
    @Resource
    private BookService bookService;

    /**
    * 加载列表返回DataGridView
    * @return
    */
    @RequestMapping("findPage")
    public DataGridView findPage(QueryVo queryVo){
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        Integer userId = null;
        Integer deptId = null;

        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        if (user.getRid() == 2) {
            deptId = user.getDeptId();
        } else if (user.getRid() == 3) {
            userId = user.getId();
        }

        List<Book> data = this.bookBorrowService.findPage(userId, deptId);
        System.out.println(data.get(0).getBorrowStatus());
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 借阅
     * @return
     */
    @RequestMapping("save")
    public ResultObj save(BookBorrow bookBorrow){
        try{
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            user = userService.getById(user);
            Book book = bookService.getById(bookBorrow.getBookId());
            if (book.getStatus() == 2) {
                return new ResultObj(0, "该图书已被借阅");
            }
            book.setStatus(2);
            bookService.updateById(book);

            bookBorrow.setDeptId(book.getDeptId());
            bookBorrow.setUserId(user.getId());
            bookBorrow.setUserName(user.getRealname());
            bookBorrow.setPhone(user.getPhone());
            bookBorrow.setBorrowTime(DateUtil.now());
            bookBorrow.setBorrowStatus(0);
            this.bookBorrowService.saveOrUpdate(bookBorrow);
            return new ResultObj(0, "借阅申请成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultObj(0, "借阅申请失败");
        }
    }

    /**
    * 修改状态
    * @return
    */
    @RequestMapping("update")
    public ResultObj update(BookBorrow bookBorrow){
        try {
            BookBorrow borrow = this.bookBorrowService.getById(bookBorrow);
            // 借阅审核拒绝
            if (2 == bookBorrow.getBorrowStatus() || 4 == bookBorrow.getBorrowStatus()) {
                Book book = this.bookService.getById(borrow.getBookId());
                book.setStatus(1);
                this.bookService.updateById(book);
            }
            if (4 == bookBorrow.getBorrowStatus()) {
                Book book = this.bookService.getById(borrow.getBookId());
                book.setStatus(1);
                this.bookService.updateById(book);
                bookBorrow.setBorrowStatus(2);
            }
            this.bookBorrowService.updateById(bookBorrow);
            return new ResultObj(0, "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultObj(-1, "操作失败");
        }
    }

    @RequestMapping("echarts")
    public DataGridView echarts(Integer type){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");

        Map<String, Object> map = new HashMap<>();

        List<String> timeData = new ArrayList<>();
        List<Integer> numData = new ArrayList<>();
        List<BookVo> list = this.bookBorrowService.echarts(user.getDeptId(), type);
        if (null != list) {
            for (BookVo vo : list) {
                timeData.add(vo.getTime());
                numData.add(vo.getNum());
            }
        }
        map.put("timeData", timeData);
        map.put("numData", numData);
        return new DataGridView(0L, map);
    }

    @RequestMapping("echartsMonth")
    public DataGridView echartsMonth(){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");

        Map<String, Object> map = new HashMap<>();

        List<String> timeData = new ArrayList<>();
        List<Integer> numData = new ArrayList<>();
        List<BookVo> list = this.bookBorrowService.echarts(user.getDeptId(), 2);
        if (null != list) {
            for (BookVo vo : list) {
                timeData.add(vo.getTime());
                numData.add(vo.getNum());
            }
        }
        map.put("timeData", timeData);
        map.put("numData", numData);
        return new DataGridView(0L, map);
    }
}
