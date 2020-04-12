package com.ly.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.background.Account;
import com.ly.entity.background.RoleAccount;
import com.ly.entity.background.Roles;
import com.ly.service.RolesService;
import com.ly.util.Common;

/**
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-06 11:05
 */
@Controller
@RequestMapping("/background/role/")
public class RoleController extends BaseController {

    @Inject
    private RolesService roleService;

    /**
     * 01.系统基础管理 角色管理-index
     *
     * @param model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model,
                        Roles role,
                        String pageNow,
                        String pagesize,
                        HttpServletRequest request) {
        // Account [id=1, accountName=root, password=4QrcOUm6Wau+VuBX8g+IPg==,description=根账号, state=1
        Account account = (Account) request.getSession().getAttribute("userSession");
        role.setGroupId(account.getGroupId());
        pageView = roleService.query(getPageView(pageNow, pagesize), role);
        model.addAttribute("pageView", pageView);
        return Common.BACKGROUND_PATH + "/role/roleList";
    }

    /**
     * 02.跳转到roleAdd.jsp
     *
     * @return
     */
    @RequestMapping("addUI")
    public String addUI() {
        return Common.BACKGROUND_PATH + "/role/roleAdd";
    }

    /**
     * 03. 添加角色，保存数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("add")
    public String add(Roles role, HttpServletRequest request) {
        try {
            roleService.add(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:query.html";
    }

    /**
     * 04. 批量删除角色
     * 实现方式较为低级，并没有真正的实现批量
     *
     * @param check
     * @param model
     * @return
     */
    @RequestMapping("deleteAll")
    public String deleteAll(String[] check, Model model) {
        for (String id : check) {
            try {
                roleService.delete(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:query.html";
    }


    /**
     * 06. 角色—修改
     * 获取角色信息
     *
     * @param model
     * @return
     */
    @RequestMapping("editUI")
    public String editUI(Model model, String roleId) {
        Roles role = roleService.getById(roleId);
        model.addAttribute("role", role);
        return Common.BACKGROUND_PATH + "/role/roleModify";
    }

    /**
     * 07. 角色修改之后保存
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("update")
    public String update(Model model, Roles role) {
        try {
            roleService.update(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:query.html";
    }

    /**
     * 查询角色列表
     *
     * @param model
     * @param role
     * @param pageNow
     * @param pagesize
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping("queryforuser")
    public String queryforuser(Model model, Roles role, String pageNow,
                               String pagesize, HttpServletRequest request, String userId) {
        pageView = roleService.query(getPageView(pageNow, pagesize), role);
        model.addAttribute("pageView", pageView);
        model.addAttribute("userId", userId);
        return Common.BACKGROUND_PATH + "/account/showRole";
    }

    // -------------------------------------------------------------------------
    @ResponseBody
    @RequestMapping("queryAll")
    public Map<String, Object> queryAll(Roles role) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roles", roleService.queryAll(role));
        return map;
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
        Roles role = roleService.isExist(name);
        if (role == null) {
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
                    roleService.delete(string);
                }
            }
            map.put("flag", "true");
        } catch (Exception e) {
            map.put("flag", "false");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("findbyAccountRole")
    public Roles findbyAccountRole(Model model, String accountId) {
        return roleService.findbyAccountRole(accountId);
    }

    @ResponseBody
    @RequestMapping("addAccRole")
    public String addAccRole(Model model, String userId, String roleId) {
        RoleAccount userRoles = new RoleAccount();
        userRoles.setAccountId(Integer.parseInt(userId));
        userRoles.setRoleId(Integer.parseInt(roleId));
        String errorCode = "1000";
        try {
            roleService.addAccRole(userRoles);
        } catch (Exception e) {
            e.printStackTrace();
            errorCode = "1001";
        }
        return errorCode;
    }

}