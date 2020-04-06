package com.ly.service;


import java.util.List;

import com.ly.base.BaseService;
import com.ly.entity.background.Resources;
import com.ly.pulgin.mybatis.plugin.PageView;

public interface ResourcesService extends BaseService<Resources>{
	//<!-- 根据账号Id获取该用户的权限-->
	public List<Resources> findAccountResourcess(String accountId);
	
	public List<Resources> findRoleRes(String roleId);
	
	public List<Resources> queryByParentId(Resources resources);
	
	void updateSortOrder(List<Resources> menus);
	public void addRoleRes(String roleId,List<String> list);

	public Resources isExist(String menuName);
	public  int  getMaxLevel();
	public Resources findResourceById(String id);
	//<!-- 根据用户Id获取该用户的权限-->
	public List<Resources> getUserResources(String userId);
	//<!-- 根据用户Id获取该用户的权限-->
	public List<Resources> getRoleResources(String roleId);
	//<!-- 根据用户名获取该用户的权限-->
	public List<Resources> getResourcesByUserName(String username);
	public void saveRoleRescours(String roleId,List<String> list);
	public void saveGroupRescours(String groupId,List<String> list);
	
	public List<Resources> findAll();
	public List<Resources> getResourcesByRoleId(String role);
	
	/**
	 * 根据组织id获取资源
	 * @param groupId
	 * @return
	 */
	public List<Resources> getResourcesByGroupId(String groupId);
	
	public PageView queryAll(PageView pageView);
	

}
