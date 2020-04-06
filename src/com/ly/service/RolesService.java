package com.ly.service;

import java.util.List;

import com.ly.base.BaseService;
import com.ly.entity.background.RoleAccount;
import com.ly.entity.background.Roles;
import com.ly.pulgin.mybatis.plugin.PageView;

public interface RolesService extends BaseService<Roles> {
	public Roles isExist(String name);

	public Roles findbyAccountRole(String accountId);

	public void addAccRole(RoleAccount roleAccount);

	public void addAccRole(String accountId, List<String> ids);

	public PageView queryByGroupId(PageView pageView, Roles t);
	
	
}
