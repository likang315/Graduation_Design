package com.ly.mapper;

import java.util.List;
import java.util.Map;

import com.ly.base.BaseMapper;
import com.ly.entity.background.RoleAccount;
import com.ly.entity.background.Roles;

public interface RolesMapper extends BaseMapper<Roles>{
	public Roles isExist(String name);
	
	public Roles findbyAccountRole(String accountId);
	
	public void addAccRole(RoleAccount roleAccount);
	
	public void deleteAccountRole(String accountId);
	
	public List<Roles> queryByGroupId(Map<String, Object> map);
	
	
}
