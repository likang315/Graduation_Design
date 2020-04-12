package com.ly.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.background.Account;
import com.ly.entity.background.Resources;
import com.ly.service.AccountService;
import com.ly.util.Common;
import com.ly.util.Md5Tool;
import com.ly.util.POIUtils;

/**
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-012 10:13
 */
@Controller
@RequestMapping("/background/account/")
public class AccountController extends BaseController {

    @Inject
    private AccountService accountService;

    /**
     * 01. 账户列表
     * 只获取sys_group  parent_id、ID 为112的账户
     *
     * @param model
     * @param account
     * @param pageNow
     * @param pagesize
     * @param request
     * @return
     */
    @RequestMapping("list")
    public String indexList(Model model,
                            Account account,
                            String pageNow,
                            String pagesize,
                            HttpServletRequest request) {
        Account a = getAccount(request);
        account.setGroupId(a.getGroupId());

        pageView = accountService.query(getPageView(pageNow, pagesize), account);
        model.addAttribute("pageView", pageView);
        return Common.BACKGROUND_PATH + "/account/userList";
    }

    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response, Account account) {
        List<Account> acs = accountService.queryAll(account);
        POIUtils.exportToExcel(response, "账号报表", acs, Account.class, "账号",
                acs.size());
    }

    /**
     * 02.跳转到新增界面
     *
     * @return
     */
    @RequestMapping("addUI")
    public String addUI() {
        return Common.BACKGROUND_PATH + "/account/userAdd";
    }

    /**
     * 04.新增营销人员，入库保存数据
     *
     * @param account
     * @return
     */
    @RequestMapping("add")
    public String add(Account account) {
        try {
            account.setPassword(Md5Tool.getMd5(account.getPassword()));
            Date CreateTime = new Date();
            account.setCreateTime(CreateTime);
            // 1:门店人员，2：快递员，3：营销中心人员
            account.setAccountType("3");
            account.setAuth_flag(2);
            account.setState("1");
            account.setReal_name(account.getReal_name());
            account.setId_car(account.getId_car());
            account.setSection_name(account.getSection_name());
            account.setEmployeesClass(0);
            account.setEmployeesType(0);
            accountService.add(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list.html";
    }

    /**
     * 05.批量删除营销账号
     *
     * @param check
     * @return
     */
    @RequestMapping("deleteAll")
    public String deleteAll(String[] check) {
        try {
            for (String string : check) {
                if (!Common.isEmpty(string)) {
                    accountService.delete(string);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list.html";
    }

    /**
     * 06.修改密码
     *
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping("modifyPassword")
    public String modifyPassword(Model model, String userId) {
        model.addAttribute("userId", userId);
        return Common.BACKGROUND_PATH + "/account/modifyPassword";
    }

    /**
     * 07. new password save database
     *
     * @param userId
     * @param newpassword
     * @return
     */
    @RequestMapping("savePassword")
    public String modifyPass(String userId, String newpassword) {
        Account ac = new Account();
        ac.setId(Integer.parseInt(userId));
        ac.setPassword(Md5Tool.getMd5(newpassword));
        try {
            accountService.update(ac);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:list.html";
    }

    /**
     * 08.跳转到修改信息页面
     *
     * @param model
     * @return
     */
    @RequestMapping("editUI")
    public String editUI(Model model, String accountId) {
        Account account = accountService.getById(accountId);
        model.addAttribute("account", account);
        return Common.BACKGROUND_PATH + "/account/userModify";
    }

    /**
     * 09.修改信息入库
     *
     * @param account
     * @return
     */
    @RequestMapping("update")
    public String update( Account account) {
        try {
            // 有啥改啥
            accountService.update(account);
        } catch (Exception e) {
        }
        return "redirect:list.html";
    }




    //------------------------------------------------------------------------------------
    /**
     * 账号角色页面
     *
     * @param model
     * @return
     */
    @RequestMapping("accRole")
    public String accRole(Model model, String accountName, String roleName) {

        try {
            accountName = java.net.URLDecoder.decode(accountName, "UTF-8");
            roleName = java.net.URLDecoder.decode(roleName, "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        model.addAttribute("accountName", accountName);
        model.addAttribute("roleName", roleName);

        return Common.BACKGROUND_PATH + "/account/acc_role";
    }


    /**
     * 验证账号是否存在
     *
     * @param name
     * @return
     */
    @RequestMapping("isExist")
    @ResponseBody
    public boolean isExist(String name) {
        Account account = accountService.isExist(name);
        if (account == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除
     *
     * @param model
     * @param videoTypeId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("deleteById")
    public Map<String, Object> deleteById(Model model, String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String id[] = ids.split(",");
            for (String string : id) {
                if (!Common.isEmpty(string)) {
                    accountService.delete(string);
                }
            }
            map.put("flag", "true");
        } catch (Exception e) {
            map.put("flag", "false");
        }
        return map;
    }


    /**
     * 删除
     *
     * @param model
     * @param videoTypeId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("updateState")
    public Map<String, Object> updateState(Model model, String ids, String state) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String id[] = ids.split(",");
            for (String string : id) {
                if (!Common.isEmpty(string)) {
                    Account account = new Account();
                    account.setId(Integer.parseInt(string));
                    account.setState(state);
                    accountService.update(account);
                }
            }
            map.put("flag", "true");
        } catch (Exception e) {
            map.put("flag", "false");
        }
        return map;
    }



}