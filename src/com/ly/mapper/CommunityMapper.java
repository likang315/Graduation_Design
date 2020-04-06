package com.ly.mapper;

import java.util.List;
import java.util.Map;

import com.ly.base.BaseMapper;
import com.ly.entity.foreground.Community;

public interface CommunityMapper extends BaseMapper<Community>{

	List<Community> getByGroupId(String groupId);

	List<Community> getParentById(String id);

	List<Community> getCommuByGroupId(String id);

	List<Community> getByIds(List<String> list);

	List<Community> getCommuByIds(Map<String, Object> map);

	List<Community> getAllComm();




}
