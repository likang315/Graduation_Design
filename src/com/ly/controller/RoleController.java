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
     * 01.系统基础管理 角色管理
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
        return Common.BACKGROUND_PATH + "/role/roleadd";
    }

    /**
     * @param model 存放返回界面的model
     * @return
     */
    @RequestMapping("queryforuser")
    public String queryforuser(Model model, Roles role, String pageNow,
                               String pagesize, HttpServletRequest request, String userId) {
		/*Account account = getAccount(request);
		role.setGroupId(account.getGroupId());*/
        pageView = roleService.query(getPageView(pageNow, pagesize), role);
        model.addAttribute("pageView", pageView);
        model.addAttribute("userId", userId);
        return Common.BACKGROUND_PATH + "/account/showrole";
    }

    @ResponseBody
    @RequestMapping("queryAll")
    public Map<String, Object> queryAll(Roles role) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roles", roleService.queryAll(role));
        return map;
    }

    /**
     * 获取未科组匹配账号
     *
     * @param account
     * @param pageNow
     * @param pagesize
     * @return
     */
    // @ResponseBody
    // @RequestMapping("queryNoMatch")
    // public PageView queryNoMatch(Roles role,String pageNow,String pagesize) {
    // pageView = roleService.queryNoMatch(role, getPageView(pageNow,pagesize));
    // return pageView;
    // }

    /**
     * 保存数据
     *
     * @param model
     * @param videoType
     * @return
     * @throws Exception
     */
    @RequestMapping("add")
    public String add(Roles role, HttpServletRequest request) {
        try {
			/*Account account = getAccount(request);
			role.setGroupId(account.getGroupId());*/
            roleService.add(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:query.html";
    }



    /**
     * 跑到新增界面
     *
     * @param model
     * @return
     */
    @RequestMapping("editUI")
    public String editUI(Model model, String roleId) {
        Roles role = roleService.getById(roleId);
        model.addAttribute("role", role);
        return Common.BACKGROUND_PATH + "/role/rolemodify";
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
     * 删除
     *
     * @param model
     * @param videoTypeId
     * @return
     * @throws Exception
     */
    // @ResponseBody
    // @RequestMapping("updateState")
    // public Map<String, Object> updateState(Model model, String ids,String
    // state) {
    // Map<String, Object> map = new HashMap<String, Object>();
    // try {
    // String id[] = ids.split(",");
    // for (String string : id) {
    // if(!Common.isEmpty(string)){
    // Account account = new Account();
    // account.setId(Integer.parseInt(string));
    // account.setState(state);
    // accountService.update(account);
    // }
    // }
    // map.put("flag", "true");
    // } catch (Exception e) {
    // map.put("flag", "false");
    // }
    // return map;
    // }

    /**
     * 更新类型
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