package com.ly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.AppUserService;
import com.ly.service.GroupService;
import com.ly.util.Common;

/**
 * 
 * @Author 文军
 * @Date 2016年1月29日 下午1:58:43 
 * @E-mail wenjun_chen@sohu.com
 * @content app 基础控制器
 *
 */
public class AppBaseController {
	
	@Autowired
	private AppUserService userService;
	
	@Autowired
	private GroupService groupService;
	/**
	 * 返回结果集
	 */
	protected HashMap<String, Object> result = new HashMap<String,Object>();
	
	
	PageView pageView = null;
	
	
	/**
	 * @return UUID
	 */
	public String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	
	/**
	 * 分页信息
	 * @param pageNow
	 * @param pagesize
	 * @return
	 */
	public PageView getPageView(String pageNow, String pagesize) {
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		if (Common.isEmpty(pagesize)) {
			pagesize = "10";
		}
		pageView.setPageSize(Integer.parseInt(pagesize));
		return pageView;
	}
	
	
	
	
	
	
	/**
	 * <p>
	 * 从 HTTP Header 中截取客户端连接 IP 地址。如果经过多次反向代理， 在请求头中获得的是以“,&lt;SP&gt;”分隔 IP
	 * 地址链，第一段为客户端 IP 地址。
	 * </p>
	 *
	 * @param xforwardIp
	 *            从 HTTP 请求头中获取转发过来的 IP 地址链
	 * @return 客户端源 IP 地址
	 */
	private static String getRemoteIpFromForward(String xforwardIp) {
		int commaOffset = xforwardIp.indexOf(',');
		if (commaOffset < 0) {
			return xforwardIp;
		}
		return xforwardIp.substring(0, commaOffset);
	}
	
	
	
	/**
	 * 设置返回数据
	 * 
	 */
	protected void setResult(String stateCode) {
		
		result.put(Common.STATE, stateCode);
		
	}

	protected Account checkLogin(String username,String password,String token){
		
		Account user = this.userService.checkLogin(username,password,token);
		
		return user;
	}
	
	
	/**
	 * 查询组织机构下所有子组织机构ID
	 * @param groupId
	 * @return
	 */
	protected List<Long> getGroups(String groupId){
		List<Long> g = new ArrayList<Long>();
		g.add(Long.parseLong(groupId));
		getGroupId(Long.parseLong(groupId), g);
		getPreIds(Long.parseLong(groupId), g);
		return g;
	}
	
	private void getGroupId(Long rootid,List<Long> li){
		List<Long> chi = this.groupService.getAllGroupId(rootid);
		if(chi != null && chi.size() > 0){
			for(Long l :chi){
				li.add(l);
				getGroupId(l, li);
			}
		}
	}
	
	private void getPreIds(Long rootid,List<Long> li){
		Group g = this.groupService.getById(rootid+"");
		if(g!=null) {
			li.add(Long.parseLong(g.getId()));
			if(g.getParentId() > 0){
				getPreIds((long) g.getParentId(),li);
			}
		}
	}
}
