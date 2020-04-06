package com.ly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.entity.background.Resources;
import com.ly.entity.background.ResourcesRole;
import com.ly.entity.background.Roles;
import com.ly.mapper.ResourcesMapper;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.ResourcesService;
import com.ly.util.Common;

@Transactional
@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {
	@Autowired
	private ResourcesMapper resourcesMapper;

	public PageView query(PageView pageView, Resources resources) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", resources);
		List<Resources> list = resourcesMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	
	
	
	public List<Resources> queryAll(Resources t) {
		return resourcesMapper.queryAll(t);
	}

	public void delete(String id) throws Exception {
		this.resourcesMapper.delete(id);
	}

	public void update(Resources t) throws Exception {
		this.resourcesMapper.update(t);
	}

	public Resources getById(String id) {
		return this.resourcesMapper.getById(id);
	}

	public List<Resources> queryByParentId(Resources resources) {
		return resourcesMapper.queryByParentId(resources);
	}

	public void add(Resources t) throws Exception {
		this.resourcesMapper.add(t);
	}

	public void updateSortOrder(List<Resources> resourcess) {
		for (Resources m : resourcess) {
			resourcesMapper.updateSortOrder(m);
		}
	}

	public List<Resources> findAccountResourcess(String accountId) {
		return resourcesMapper.findAccountResourcess(accountId);
	}

	public List<Resources> findRoleRes(String roleId) {
		return resourcesMapper.findRoleRes(roleId);
	}

	public void addRoleRes(String roleId, List<String> list) {
		resourcesMapper.deleteResourcesRole(roleId);
		for (String string : list) {
			ResourcesRole rr = new ResourcesRole();
			rr.setRoleId(roleId);
			rr.setResId(string);
			resourcesMapper.addRoleRes(rr);
		}
	}

	public int getMaxLevel() {
		return resourcesMapper.getMaxLevel();
	}

	public Resources isExist(String resourcesName) {
		return resourcesMapper.isExist(resourcesName);
	}

	public Resources findResourceById(String id) {
		return resourcesMapper.findResourceById(id);
	}

	public List<Resources> getResourcesByUserName(String username) {
		return resourcesMapper.getResourcesByUserName(username);
	}

	public List<Resources> getRoleResources(String roleId) {
		return resourcesMapper.getRoleResources(roleId);
	}

	public List<Resources> getUserResources(String userId) {
		return resourcesMapper.getUserResources(userId);
	}

	public void saveRoleRescours(String roleId, List<String> list) {
		resourcesMapper.deleteResourcesRole(roleId);
		for (String rId : list) {
			if (!Common.isEmpty(rId)) {
				ResourcesRole resourceRoles = new ResourcesRole();
				resourceRoles.setResId(rId);
				resourceRoles.setRoleId(roleId);
				resourcesMapper.saveRoleRescours(resourceRoles);
			}
		}
	}

	public List<Resources> findAll() {
		return resourcesMapper.findAll();
	}

	@Override
	public List<Resources> getResourcesByRoleId(String role) {
		// TODO Auto-generated method stub
		return resourcesMapper.getResourcesByRoleId(role);
	}

	@Override
	public void saveGroupRescours(String groupId, List<String> list) {
		resourcesMapper.deleteResourcesGroup(groupId);
		for (String string : list) {
			ResourcesRole rr = new ResourcesRole();
			rr.setRoleId(groupId);
			rr.setResId(string);
			resourcesMapper.addGroupRes(rr);
		}

	}

	@Override
	public List<Resources> getResourcesByGroupId(String groupId) {

		return resourcesMapper.getResourcesByGroupId(groupId);
	}

	@Override
	public PageView queryAll(PageView pageView) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		List<Roles> list = resourcesMapper.queryAllPage(map);
		pageView.setRecords(list);
		return pageView;
	}
}
