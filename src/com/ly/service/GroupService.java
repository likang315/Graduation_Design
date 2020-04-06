package com.ly.service;

import java.util.List;

import com.ly.base.BaseService;
import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.vo.TreeVo;

/**
 * 组织架构service
 * @author wenjun
 *
 */
public interface GroupService extends BaseService<Group>{
	
	/**
	 * 查询组织架构
	 * @param user 用户
	 * @param pageview 分页
	 * @return
	 */
	public List<Group> queryAll(Account user);
	
	/**
	 * 获取组织机构层级结构
	 * @return
	 */
	public List<TreeVo> getTreeGroup(int groupId);
	
	/**
	 * 获取组织机构
	 * @param groupId
	 * @return
	 */
	public List<TreeVo> getTreeGroup(int groupId,int userGroupid);
	
	public PageView queryGroup(PageView pageView,Group group,int groupId);

	public List<Group> getGroupsById(String id);
	
	public List<Integer> getAllGroupsId(int groupId);
	
	public List<Long> getAllGroupId(Long rootid);
	
	//根据组织机构名称获取信息
	List<Group> getNameToInfo(String name);
	
	//根据组织机构名称获取信息
	List<Long> getChildId(Long parentId);

}
