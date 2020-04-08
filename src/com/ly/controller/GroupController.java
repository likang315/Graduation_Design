package com.ly.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.entity.background.Resources;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.GroupService;
import com.ly.service.ResourcesService;
import com.ly.service.vo.TreeVo;
import com.ly.util.Common;
import com.ly.util.PropertiesUtils;

/**
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-06 11:05
 */
@Controller
@RequestMapping("/background/group/")
public class GroupController extends BaseController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ResourcesService resourcesService;

    /**
     * 01.组织架构管理-index
     *
     * @param mode
     * @param pageNow
     * @param pageSize
     * @param t
     * @param request
     * @return
     */
    @RequestMapping("list")
    public String list(Model mode, String pageNow, String pageSize, Group t, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("userSession");
        PageView pageView = groupService.queryGroup(getPageView(pageNow, pageSize), t, account.getGroupId());
        mode.addAttribute("pageView", pageView);
        return Common.BACKGROUND_PATH + "/group/groupList";
    }

    /**
     * 02.新增组织机构
     *
     * @param model
     * @return
     */
    @RequestMapping("addGroup")
    public String addGroup(Model model) {
        return Common.BACKGROUND_PATH + "/group/groupAdd";
    }

    /**
     * 03. 新增组织机构-入库
     *
     * @param group
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("add")
    public String add(Group group, Model model) throws Exception {
        groupService.add(group);
        return "redirect:list.html";
    }

    /**
     * 04.删除组织机构
     *
     * @param id 组织编号
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public String delete(String id) {
        String result = "ok";
        try {
            groupService.delete(id);
        } catch (Exception e) {
            result = "删除失败";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 05.组织机构-分配资源
     * 获取原有资源展示
     *
     * @param model
     * @param groupId
     * @param request
     * @return
     */
    @RequestMapping(value = "permissionRole")
    public String permissioRole(Model model, String groupId,
                                HttpServletRequest request) {
        List<Resources> resources = resourcesService.getResourcesByGroupId(groupId);
        List<Resources> allRes = null;

        Account acc = getAccount(request);
        if (acc.getAccountName().equals(
                PropertiesUtils.findPropertiesKey("rootName"))) {
            allRes = resourcesService.findAll();
        } else {
            allRes = resourcesService.getResourcesByGroupId(String.valueOf(acc
                    .getGroupId()));
        }

        StringBuffer sb = new StringBuffer();
        sb.append("var data = [];");
        for (Resources r : allRes) {
            boolean flag = false;
            if (resources != null) {
                for (Resources ur : resources) {// 用户拥有的权限
                    if (ur.getId().equals(r.getId())) {
                        sb.append("data.push({ fid: '" + r.getId()
                                + "', pfid: '" + r.getParentId()
                                + "', fname: '" + r.getName()
                                + "',ischecked: true});");
                        flag = true;
                    }
                }
            }
            if (!flag) {
                sb.append("data.push({ fid: '" + r.getId() + "', pfid: '"
                        + r.getParentId() + "', fname: '" + r.getName()
                        + "'});");

            }
        }
        model.addAttribute("groupId", groupId);
        model.addAttribute("resources", sb);
        return Common.BACKGROUND_PATH + "/group/permissionUser";
    }

    /**
     * 06.组织机构-修改信息
     *
     * @param model
     * @param groupId
     * @return
     */
    @RequestMapping("editUI")
    public String editUI(Model model, String groupId) {
        Group g = groupService.getById(groupId);
        model.addAttribute("group", g);
        return Common.BACKGROUND_PATH + "/group/groupModify";
    }

    /**
     * 07.组织机构-修改信息-修改入库信息
     * 重定向到组织机构—index
     *
     * @param group
     * @return
     * @throws Exception
     */
    @RequestMapping("edit")
    public String edit(Group group) throws Exception {
        groupService.update(group);
        return "redirect:list.html";
    }


    @RequestMapping("tree")
    @ResponseBody
    public Object getGroopTree(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(
                "userSession");
        List<TreeVo> res = groupService.getTreeGroup(account.getGroupId());
        return res;
    }

    @RequestMapping("treeUser")
    @ResponseBody
    public Object getUserGroupTree(HttpServletRequest request, int groupId) {
        Account account = (Account) request.getSession().getAttribute(
                "userSession");
        List<TreeVo> res = groupService.getTreeGroup(groupId,
                account.getGroupId());
        return res;
    }




    @ResponseBody
    @RequestMapping(value = "saveGroupRescours")
    public String saveRoleRescours(String groupId, String rescId) {
        String errorCode = "1000";
        List<String> list = Common.removeSameItem(Arrays.asList(rescId
                .split(",")));
        try {
            resourcesService.saveGroupRescours(groupId, list);
        } catch (Exception e) {
            e.printStackTrace();
            errorCode = "1001";
        }
        return errorCode;
    }






}
