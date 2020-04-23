package com.ly.service;

import java.util.List;
import java.util.Map;

import com.ly.entity.app.AppWxMenu;
import com.ly.entity.app.AppWxMenuShare;

public interface AppMenuService {

    //微信端页面管理
    public List<AppWxMenu> getAll_wxMenu();

    public Map<String, Object> addWxMenu(AppWxMenu wxMenu);

    public Map<String, Object> updateWxMenu(AppWxMenu wxMenu);

    public Map<String, Object> delWxMenu(String ids);

    /**
     * 获取当前用户组的所有菜单
     */
    List<AppWxMenu> getWxMenuByAll(Map<String, Object> parameter);

    AppWxMenuShare getWxMenuByAllInfo(Map<String, Object> parameters);

    boolean updateWxMenuByAll(AppWxMenuShare wxMenuS);

    public boolean addWxMenuByAll(AppWxMenuShare wxMenuS);

    //修改主页
    public boolean updateIndex(AppWxMenuShare wxMenuS);

    //修改底部菜单
    public boolean updateBottonMenu(AppWxMenuShare wxMenuS);
}
