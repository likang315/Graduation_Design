package com.ly.mapper;

import java.util.List;

import com.ly.base.BaseMapper;
import com.ly.entity.background.Group;

public interface GroupMapper extends BaseMapper<Group> {
	public List<Group> getGroupByParentId(Integer pId);

	public List<Group> getGroupsById(String id);
	
	public List<Long> getChildId(Long parentId);
	
	public List<Group> getNameToInfo(String name);
}
