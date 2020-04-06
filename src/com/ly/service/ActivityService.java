package com.ly.service;

import java.util.List;
import java.util.Map;

import com.ly.entity.ActivityInfo;
import com.ly.entity.ActivityRead;
import com.ly.entity.background.Account;
import com.ly.pulgin.mybatis.plugin.PageView;

/**
 * 政策
 * @author Yutai.Yin 2017年3月8日上午11:16:45
 *
 */
public interface ActivityService {
	
	int findActivityCount();
	
	List<Map<String, Object>> findActivityByPage(Map<String, Object> parame);
	
	int findActivityCountByTime(String starttime, String endtime);
	
	List<Map<String, Object>> findActivityByPageByTime(Map<String, Object> parame);
	
	//删除政策
	void delete(String id);
	
	//置顶政策
	void top(String id);
	
	//取消政策置顶
	void cancelTop(String id);
	
	//新增发布政策
	void add(ActivityInfo info);
	
	//读取政策信息
	ActivityInfo getActivityInfo(String id);
	
	String getAllGroupId(int groupId);
	
	int readAll(String gid);
	
	List<Map<String, Object>> findPolicyByPage(Map<String, Object> parame);
	
	String writeFile(String tail,String id,String path,String flag,Account user);
	
	int findCountActivityByTime(String gid, String starttime, String endtime);
	
	List<Map<String, Object>> findActivityByTime(Map<String, Object> parame);
	
	ActivityInfo getById(String id);
	
	PageView getReadInfo(PageView p,String groupid,ActivityInfo activity);
	
	/*******************************政策前台方法****************************************/
	//读取到政策列表
	List<ActivityInfo> queryGovAll(String activitytype, String groupId,
			String userid);
	//知识库列表
	List<ActivityInfo> getKnowledge(String activitytype, String groupId,
			   String userid);
	
	ActivityRead getReadByUserid(String username, String id);
	
	void updateRecord(ActivityRead read);
	
	List<Map<String,Object>> search(Map<String, Object> map);
	
	String getGroups(String groupId);
	
	
	
	
	
	

}
