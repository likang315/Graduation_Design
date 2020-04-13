package com.ly.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.Params;
import com.ly.entity.background.Account;
import com.ly.entity.background.Resources;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.ResourcesService;
import com.ly.util.Common;
import com.ly.util.PropertiesUtils;
import com.ly.util.TreeObject;
import com.ly.util.TreeUtil;

/**
 * 无批量删除功能，每次操作一条数据
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-08 11:05
 */
@Controller
@RequestMapping("/background/resources/")
public class ResourcesController extends BaseController {

    @Inject
    private ResourcesService resourcesService;

    /**
     * role_05. 设置角色权限
     *
     * @return
     */
    @RequestMapping(value = "permissionRole")
    public String permissioRole(Model model, String roleId, String groupId,
                                HttpServletRequest request) {
        List<Resources> resources = resourcesService.getResourcesByRoleId(roleId);
        List<Resources> allRes = null;
        Account acc = getAccount(request);
        if (acc.getAccountName().equals(PropertiesUtils.findPropertiesKey("rootName"))) {
            allRes = resourcesService.findAll();
        } else {
            allRes = resourcesService.getResourcesByGroupId(groupId);
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
        model.addAttribute("roleId", roleId);
        model.addAttribute("resources", sb);
        return Common.BACKGROUND_PATH + "/resource/permissionUser";
    }

    /**
     * role_06. 建立角色和权限之间的关系
     *
     * @param roleId
     * @param rescId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveRoleResource")
    public String saveRoleRescours(String roleId, String rescId) {
        String errorCode = "1000";
        List<String> list = Common.removeSameItem(Arrays.asList(rescId
                .split(",")));
        try {
            resourcesService.saveRoleRescours(roleId, list);
        } catch (Exception e) {
            e.printStackTrace();
            errorCode = "1001";
        }
        return errorCode;
    }

    /**
     * 01.查询资源列表
     *
     * @param model
     * @param resources
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("resourceList")
    public String resourcess(Model model, Resources resources, HttpServletRequest request) throws Exception {
        List<Resources> rs;
        if (PropertiesUtils.findPropertiesKey("rootName").equals(Common.findAuthenticatedUsername())) {
            rs = resourcesService.queryAll(resources);
        } else {
            rs = resourcesService.findAccountResourcess(Common.findUserSessionId(request));
        }
        List<TreeObject> treeObjects = new ArrayList<TreeObject>();
        for (Resources res : rs) {//转换为树对象
            TreeObject t = new TreeObject();
            PropertyUtils.copyProperties(t, res);
            treeObjects.add(t);
        }
        List<TreeObject> ns = TreeUtil.getChildResourcess(treeObjects, 0);
        model.addAttribute("resourceList", ns);
        model.addAttribute("pageView", pageView);

        return Common.BACKGROUND_PATH + "/resource/resourceList";
    }

    /**
     * 02.跳转到新增界面
     *
     * @return
     */
    @RequestMapping("addUI")
    public String addUI(Model model) {
        List<Resources> resources = resourcesService.queryAll(new Resources());
        Resources r = new Resources();
        r.setId(0);
        r.setName("system");
        resources.add(r);
        model.addAttribute("resources", resources);
        return Common.BACKGROUND_PATH + "/resource/resourceAdd";
    }

    /**
     * 03.新增资源入库
     *
     * @param resources
     * @return Map
     */
    @RequestMapping("add")
    public String add(Resources resources) {
        try {
            // 判断是否为目录(目录的parentId为0)
            if (-1 == resources.getParentId()) {
                resources.setParentId(0);
            }
            resourcesService.add(resources);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:resourceList.html";
    }

    /**
     * 04.跳转到修改资源列表界面
     *
     * @param model
     * @param resourcesId 修改资源信息ID
     * @return
     */
    @RequestMapping("editUI")
    public String editUI(Model model, String resourcesId) {
        Resources resources = resourcesService.getById(resourcesId);
        model.addAttribute("resources", resources);
        return Common.BACKGROUND_PATH + "/resource/resourceModify";
    }

    /**
     * 05.修改资源入库
     *
     * @return
     */
    @RequestMapping("edit")
    public String update(Resources resources) {
        try {
            resourcesService.update(resources);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:resourceList.html";
    }

    /**
     * 06.删除资源列表时 先查找父资源，看是否关联
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("findResourceById")
    public String findResourceById(String id) {
        String key = "";
        Resources resource = resourcesService.findResourceById(id);
        if (resource != null) {
            key = resource.getName();
        }
        return key;
    }

    /**
     * 07.删除资源列表
     *
     * @param id 资源ID
     * @return
     */
    @RequestMapping("delete")
    public String delete(String id) {
        try {
            resourcesService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:resourceList.html";
    }







    @ResponseBody
    @RequestMapping("perm")
    public Map<String, Object> perm(Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        /*
         * List<Department> dp =
         * DepartmentUtil.getChildDeparts(departmentService.queryAll(new
         * Department()), 0); map.put("records", dp);
         */
        return map;
    }

    @RequestMapping("aution")
    public String aution(Model model) throws Exception {
        List<Resources> rs = resourcesService.queryAll(new Resources());
        List<TreeObject> treeObjects = new ArrayList<TreeObject>();
        for (Resources res : rs) {// 转换为树对象
            TreeObject t = new TreeObject();
            PropertyUtils.copyProperties(t, res);
            treeObjects.add(t);
        }
        List<TreeObject> ns = TreeUtil.getChildResourcess(treeObjects, 0);
        model.addAttribute("permissions", ns);
        return Common.BACKGROUND_PATH + "/resources/permissions";
    }

    @ResponseBody
    @RequestMapping("findRoleRes")
    public List<Resources> findRoleRes(String roleId) {
        return resourcesService.findRoleRes(roleId);
    }


    /**
     * @param model 存放返回界面的model
     * @return
     */
    @RequestMapping("query")
    public String query(Model model, Resources resources, String pageNow) {
        PageView pageView = null;
        if (Common.isEmpty(pageNow)) {
            pageView = new PageView(1);
        } else {
            pageView = new PageView(Integer.parseInt(pageNow));
        }
        pageView = resourcesService.query(pageView, resources);
        model.addAttribute("pageView", pageView);
        return Common.BACKGROUND_PATH + "/resources/list";
    }

    /**
     * @param model 存放返回界面的model
     * @return
     */
    @RequestMapping("list")
    public String list(Model model, Resources resources, String pageNow) {
        return Common.BACKGROUND_PATH + "/resources/list";
    }



    @ResponseBody
    @RequestMapping("queryAll")
    public List<Resources> queryAll(HttpServletRequest request) {
        if (PropertiesUtils.findPropertiesKey("rootName").equals(
                Common.findAuthenticatedUsername())) {// 根据账号拥有所有权限
            return resourcesService.queryAll(new Resources());
        } else {
            return resourcesService.queryAll(new Resources());
        }
    }

    @ResponseBody
    @RequestMapping("queryParentId")
    public List<Resources> queryParentId(Resources resources) {
        return resourcesService.queryByParentId(resources);
    }

    @ResponseBody
    @RequestMapping("/updateResourcesOrder")
    public void updateResourcesOrder(Params params) {
        // resourcesService.updateMeunOrder(params.getResourcess());
    }


    /**
     * 权限分配页面
     *
     * @param model
     * @return
     */
    @RequestMapping("permissions")
    public String permissions(Model model) {
        return Common.BACKGROUND_PATH + "/resources/permissions";
    }



    /**
     * 根据ID删除菜单
     *
     * @param model
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteById")
    public Map<String, Object> deleteById(Model model, String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String id[] = ids.split(",");
            for (int i = 0, inv = id.length; i < inv; i++) {
                if (!Common.isEmpty(id[i])) {
                    resourcesService.delete(id[i]);
                }
            }
            map.put("flag", "true");
        } catch (Exception e) {
            map.put("flag", "false");
        }
        return map;
    }

    /**
     * 验证菜单是否存在
     *
     * @param name
     * @return
     */
    @RequestMapping("isExist")
    @ResponseBody
    public boolean isExist(String name) {
        // Account account = accountService.isExist(name);
        Resources resources = resourcesService.isExist(name);
        if (resources == null) {
            return true;
        } else {
            return false;
        }
    }

    @ResponseBody
    @RequestMapping("addRoleRes")
    public Map<String, Object> addRoleRes(String roleId, Params params) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> list = params.getId();
        try {
            if (null != list && list.size() > 0) {
                resourcesService.addRoleRes(roleId, list);
                map.put("flag", "true");
            } else {
                map.put("flag", "false");
            }
        } catch (Exception e) {
            map.put("flag", "false");
        }

        return map;
    }


}