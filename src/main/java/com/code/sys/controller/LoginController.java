package com.code.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.code.sys.constant.SysConstant;
import com.code.sys.entity.Code;
import com.code.sys.entity.User;
import com.code.sys.service.CodeService;
import com.code.sys.service.UserService;
import com.code.sys.utils.DataGridView;
import com.code.sys.utils.EmailUtils;
import com.code.sys.utils.ResultObj;
import com.code.sys.utils.WebUtils;
import com.code.sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 用户登录控制器
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private CodeService codeService;

    /**
     * @return 跳转到登录页面的方法
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "system/main/login";
    }

    /**
     * @return 跳转到登录页面的方法
     */
    @RequestMapping("toRegister")
    public String toRegister() {
        return "system/main/register";
    }

    /**
     * @return 跳转到登录页面的方法
     */
    @RequestMapping("toRestPwd")
    public String toRestPwd() {
        return "system/main/restPwd";
    }

    /**
     * .登陆方法
     *
     * @param userVo
     * @param model
     * @return
     */
    @RequestMapping("login")
    public String login(UserVo userVo, Model model) {
        try {
            if(userVo.getType() == null) {
                model.addAttribute("error", SysConstant.USER_LOGIN_CODE_ERROR_MSG);
                return "system/main/login";
            }

            User user = this.userService.login(userVo);
            System.out.println("user = " + user);
            if (null != user) {
                // 上次登陆时间
                user.setLastTime(DateUtil.now());
                this.userService.updateById(user);
                //放入到session
                WebUtils.getHttpSession().setAttribute("user", user);
                return "system/main/index";
            } else {
                model.addAttribute("error", SysConstant.USER_LOGIN_ERROR_MSG);
                return "system/main/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", SysConstant.USER_LOGIN_ERROR_MSG);
            return "system/main/login";
        }
    }

    /**
     * 注册
     * @param userVo
     * @param model
     * @return
     */
    @RequestMapping("register")
    public String register(UserVo userVo, Model model) {
        userVo.setType(3);
        userVo.setRid(3);
        this.userService.addRegister(userVo);
        model.addAttribute("error", SysConstant.USER_REGISTER_CODE_SUCCESS_MSG);
        return "system/main/login";
    }

    /**
     * 发送邮件
     * @param model
     * @return
     */
    @RequestMapping("sendEmail")
    @ResponseBody
    public ResultObj sendEmail(String email, Model model) {
        // 生成6位随机数字验证码
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        String theme = "邮箱验证码:";
        String content = "您的验证码为：" + code + "，有效期5分钟请尽快输入，如不是本人操作请忽略。";
        try {
            EmailUtils.sendEmail(email, theme, content);
            Code codeObj = new Code();
            codeObj.setCode(code);
            codeObj.setEmail(email);
            codeObj.setCreateTime(new Date());
            this.codeService.save(codeObj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResultObj(0, "邮件发送成功");
    }

    /**
     * 重置
     * @param model
     * @return
     */
    @RequestMapping("restPwd")
    @ResponseBody
    public ResultObj restPwd(String email, String code, Model model) {
        try {
            Code byCode = this.codeService.getByCode(email, code);
            if (null == byCode) {
                return new ResultObj(-1, "邮箱或验证码输入错误");
            }
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email", email).last(" limit 1 ");
            User user = this.userService.getOne(queryWrapper);
            user.setPwd(DigestUtils.md5DigestAsHex(SysConstant.USER_DEFAULT_PWD.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResultObj(0, "重置成功，密码已重置为123456");
    }
}
