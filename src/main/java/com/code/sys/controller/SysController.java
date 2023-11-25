package com.code.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.code.sys.entity.BookBorrow;
import com.code.sys.entity.User;
import com.code.sys.entity.Book;
import com.code.sys.entity.BookCirculate;
import com.code.sys.service.BookBorrowService;
import com.code.sys.service.BookCirculateService;
import com.code.sys.service.BookService;
import com.code.sys.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 页面跳转控制器
 */
@Controller
@RequestMapping("sys")
public class SysController {

    @Resource
    private BookService bookService;
    @Resource
    private BookCirculateService bookCirculateService;
    @Resource
    private BookBorrowService bookBorrowService;

    /**
     * 跳转到菜单管理的右边的菜单树页面
     * @return
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft(){
        return "system/menu/MenuLeft";
    }

    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping("toUserManager")
    public String toUserManager(){
        return "system/user/userManager";
    }


    /**
     * 跳转到图书管理
     * @return
     */
    @RequestMapping("toBookManager")
    public String toBookManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getId());
        return "system/book/bookManager";
    }

    /**
     * 跳转到流通管理
     * @return
     */
    @RequestMapping("toBookOtherManager")
    public String toBookOtherManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getId());
        return "system/book/bookOtherManager";
    }
    /**
     * 跳转到流入管理
     * @return
     */
    @RequestMapping("toBookCirculateManager")
    public String toBookCirculateManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getId());
        return "system/circulate/bookCirculateManager";
    }
    /**
     * 跳转到流出管理
     * @return
     */
    @RequestMapping("toBookCirculateManager1")
    public String toBookCirculateManager1(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getId());
        return "system/circulate/bookCirculateManager1";
    }

    /**
     * 跳转到图书归还管理
     * @return
     */
    @RequestMapping("toBookBorrowManager")
    public String toBookBorrowManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getId());
        return "system/borrow/bookBorrowManager";
    }

    /**
     * 跳转到单位管理
     * @return
     */
    @RequestMapping("toDeptManager")
    public String toDeptManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getId());
        return "system/dept/deptManager";
    }

    /**
     * 跳转到统计管理
     * @return
     */
    @RequestMapping("toEchartsManager")
    public String toEchartsManager(Model model){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        model.addAttribute("role", user.getRid());
        model.addAttribute("uid", user.getId());

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", user.getDeptId());
        int totalNum = bookService.count(queryWrapper);//本单位总图书数

//        queryWrapper.eq("status", 1);
//        int num = bookService.count(queryWrapper);//当前在库总图书数

        QueryWrapper<BookCirculate> wrapper1 = new QueryWrapper<>();
        // 从外单位流入的数量
        wrapper1.eq("circulate_dept_id", user.getDeptId());
        wrapper1.eq("circulate_status",1);
        int addNum = bookCirculateService.count(wrapper1);// 流入

        QueryWrapper<BookBorrow> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("dept_id", user.getDeptId());// 借出
        wrapper2.ne("borrow_status", 2);
        int subNum = bookBorrowService.count(wrapper2);

        QueryWrapper<BookCirculate> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("dept_id", user.getDeptId());// 流出
        wrapper3.ne("circulate_status", 2);
        int subNum1 = bookCirculateService.count(wrapper3);

        model.addAttribute("totalNum", totalNum);
        model.addAttribute("num", totalNum+addNum-subNum1-subNum);
        model.addAttribute("addNum", addNum);
        model.addAttribute("subNum", subNum+subNum1);
//        model.addAttribute("subNum", subNum+subNum1);
        return "system/echarts/echartsManager";
    }
}
