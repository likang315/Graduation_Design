package com.ly.mapper;

import java.util.List;
import java.util.Map;

import com.ly.entity.app.AppWxMenu;
import com.ly.entity.app.AppWxMenuShare;

public interface AppMenuMapper {
	
	//微信端页面管理
	public List<AppWxMenu> getAll_wxMenu();
	
	public void addWxMenu(AppWxMenu wxMenu);
	
	public void updateWxMenu(AppWxMenu wxMenu);
	
	public void delWxMenu(String ids);
	
	public List<AppWxMenu> getInfoByName_wxMenu(String name);

	/**
	 * 获取当前用户组的菜单
	 */
	List<AppWxMenu> getWxMenuByAll(Map<String,Object> parameters);

	/**
	 * 获取app定制菜单信息
	 *
	 * @param parameters
	 * @return
	 */
	List<AppWxMenuShare> getWxMenuByAllInfo(Map<String,Object> parameters);
	
	public void updateWxMenuByAll(AppWxMenuShare wxMenuS);
	
	public void addWxMenuByAll(AppWxMenuShare wxMenuS);
	
	//修改主页
	public void updateIndex(AppWxMenuShare wxMenuS);
	//修改底部菜单
	public void updateBottonMenu(AppWxMenuShare wxMenuS);

	/**
	 * 获取本身parentID
	 *
	 * @param groupId
	 * @return
	 */
	String getParentId(String groupId);
	
}
