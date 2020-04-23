package com.ly.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.background.Account;
import com.ly.service.AccountService;
import com.ly.service.AppUserService;
import com.ly.util.Common;


/**
 * app 我的个人设置
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-23 10:45
 */
@Controller
@RequestMapping("/app/user/")
public class UserController extends AppBaseController {

    @Autowired
    public AppUserService appUserService;

    @Autowired
    private AccountService accountService;

    /**
     * 01.跳转到修改密码页面
     *
     * @return
     */
    @RequestMapping("/toUpdatePasswordView")
    public String toUpdatePasswordView() {
        return Common.APP_PATH + "/updatePassword";
    }

    /**
     * 02.修改密码
     *
     * @param username
     * @param password
     * @param password1
     * @param password2
     * @return
     */
    @RequestMapping("changepassword")
    @ResponseBody
    public Object changePassword(String username,
                                 String password,
                                 String password1,
                                 String password2) {
        result.clear();
        if (!(password1.equals(password2))) {
            setResult(Common.NO);
            return result;
        }
        Account user = this.appUserService.login(username, password);
        if (user == null) {
            setResult(Common.NO);
        } else {
            Account user1 = this.appUserService.changePassword(username, password1);
            if (user1 == null) {
                setResult(Common.NO);
            } else {
                setResult(Common.OK);
                result.put("user", user1);
            }
        }
        return result;
    }


    /**
     * 跳转到忘记密码页面
     *
     * @return
     */
    @RequestMapping("forgetPassword")
    public String toForgetPasswordView() {
        return Common.APP_PATH + "/forgetPassword";
    }

    /**
     * 判断手机号是否注册
     */
    @RequestMapping("isTellPhone")
    @ResponseBody
    public Object isTellPhone(String accountName) {
        if (appUserService.validatePhoneNum(accountName)) {
            setResult(Common.OK);
            result.put("info", "此手机号已经注册，请勿重复注册！");
        } else {
            setResult(Common.NO);
            result.put("info", "此手机号尚未注册，可以注册！");
        }
        return result;
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public Object updatePassword(String tellPhone, String password) {
        if (accountService.updatePassword(tellPhone, password)) {
            setResult(Common.OK);
            result.put("info", "修改成功");
        } else {
            setResult(Common.NO);
            result.put("info", "账号不存在");
        }
        return result;
    }


}
