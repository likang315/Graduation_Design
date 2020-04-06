package com.ly.mapper;

import java.util.List;
import java.util.Map;

import com.ly.base.BaseMapper;
import com.ly.entity.ActivityInfo;
import com.ly.entity.ActivityRead;

public interface ActivityMapper extends BaseMapper<ActivityInfo> {
	
	//查询出政策的条数 
	int findActivityCount();

	List<Map<String, Object>> findActivityByPage(Map<String, Object> parame);
	
	int findActivityCountByTime(Map<String, Object> map);
	
	List<Map<String, Object>> findActivityByPageByTime(Map<String, Object> parame);
	
	//置顶政策
	void top(String id);
	
	//取消置顶政策
	void cancelTop(String id);
	
	//读取政策信息
	ActivityInfo getActivityInfo(String id);
	
	String getAllGroupId(int groupId);
	
	int readAll(String gid);
	
	List<Map<String, Object>> findPolicyByPage(Map<String, Object> parame);
	
	int findCountActivityByTime(Map<String, Object> map);
	
	List<Map<String, Object>> findActivityByTime(Map<String, Object> parame);
	
	void updateReadCounts(String id, long count);
	
	void isHand(String id);
	
	Map<String, String> getReadInfo(Map<String, Object> map);
	
	Map<String, String> getReadList(Map<String,Object> map);
	
	List<Map<String, String>> getReadReport(Map<String, Object> map);
	
	List<Map<String, String>> getAeraData(Map<String, Object> map);
	
	
	List<ActivityInfo> getGovAll(Map<String, Object> map);
	
	List<ActivityInfo> getKnowledge(Map<String, Object> map);
	
	ActivityRead getByUserid(String userid, String id);
	
	void updateRead(ActivityRead read);
	
	List<Map<String,Object>> search(Map<String, Object> map);
	
	String getGroups(String groupId);
	//获取该组织流的id
	String getGroup(String groupId);
}
