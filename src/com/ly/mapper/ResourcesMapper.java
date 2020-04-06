package com.ly.mapper;

import java.util.List;
import java.util.Map;

import com.ly.base.BaseMapper;
import com.ly.entity.background.Resources;
import com.ly.entity.background.ResourcesRole;
import com.ly.entity.background.Roles;

/**
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-05 10:05
 */
public interface ResourcesMapper extends BaseMapper<Resources> {

    void updateSortOrder(Resources resources);

    public Resources isExist(String name);

    public int getMaxLevel();

    // <!-- 根据账号Id获取该用户的权限-->
    public List<Resources> findAccountResourcess(String accountId);

    /**
     * @return
     */
    public List<Resources> findRoleRes(String roleId);

    public List<Resources> queryByParentId(Resources resources);

    /**
     * 更新菜单排序号
     */
    public void addRoleRes(ResourcesRole rr);

    public void deleteResourcesRole(String roleId);

    public Resources findResourceById(String id);

    //<!-- 根据用户Id获取该用户的权限-->
    public List<Resources> getUserResources(String userId);

    //<!-- 根据用户Id获取该用户的权限-->
    public List<Resources> getRoleResources(String roleId);

    //<!-- 根据用户名获取该用户的权限-->
    public List<Resources> getResourcesByUserName(String username);

    public void saveRoleRescours(ResourcesRole resourcesRole);

    public List<Resources> findAll();

    //根据角色id查找资源
    public List<Resources> getResourcesByRoleId(String roleId);

    /**
     * 根据组织id获取资源
     *
     * @param groupId
     * @return
     */
     List<Resources> getResourcesByGroupId(String groupId);

    /**
     * 根据组织管理id删除组织资源
     *
     * @param groupId
     */
    void deleteResourcesGroup(String groupId);

    /**
     * 添加组织管理的资源
     *
     * @param rr
     */
    void addGroupRes(ResourcesRole rr);

    List<Roles> queryAllPage(Map<String, Object> map);
}
