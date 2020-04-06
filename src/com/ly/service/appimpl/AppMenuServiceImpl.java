package com.ly.service.appimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.entity.app.AppWxMenu;
import com.ly.entity.app.AppWxMenuShare;
import com.ly.mapper.AppMenuMapper;
import com.ly.service.AppMenuService;

@Service
public class AppMenuServiceImpl implements AppMenuService {

	@Autowired
	private AppMenuMapper appMenuMapper;
	
	//菜单管理
	@Override
	public List<AppWxMenu> getAll_wxMenu() {
		// TODO Auto-generated method stub
		return appMenuMapper.getAll_wxMenu();
	}

	@Override
	public Map<String,Object> addWxMenu(AppWxMenu wxMenu) {
		Map<String,Object> result = new HashMap<String,Object>();
		wxMenu.setCreateTime(new Date());
		/*if(vendorMapper.getInfoByName(wxMenu.getName()).size()>0){
			result.put("state","no");
			result.put("info","请勿重复添加");
			return result;
		}*/
		try {
			appMenuMapper.addWxMenu(wxMenu);
			result.put("state","ok");
			result.put("info","添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state","no");
			result.put("info","添加失败，请重新操作");
		}
		return result;
	}

	@Override
	public Map<String,Object> updateWxMenu(AppWxMenu wxMenu) {
		wxMenu.setCreateTime(new Date());
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			appMenuMapper.updateWxMenu(wxMenu);
			result.put("state","ok");
			result.put("info","修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state","no");
			result.put("info","修改失败，请重新操作");	
		}
		return result;
	}

	@Override
	public Map<String,Object> delWxMenu(String ids) {
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			appMenuMapper.delWxMenu(ids);
			result.put("state","ok");
			result.put("info","删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state","no");
			result.put("info","删除失败，请重新操作");	
		}
		return result;
	}

	
	//获取当前用户组的所有菜单
	public List<AppWxMenu> getWxMenuByAll(Map<String,Object> parameter){
		return appMenuMapper.getWxMenuByAll(parameter);
	}

	@Override
	public AppWxMenuShare getWxMenuByAllInfo(Map<String, Object> parameters) {
		parameters.put("groupId", appMenuMapper.getParentId(parameters.get("groupId").toString()));
		List<AppWxMenuShare> lawms = appMenuMapper.getWxMenuByAllInfo(parameters);
		return lawms.size()>0?lawms.get(0):null;
	}

	@Override
	public boolean updateWxMenuByAll(AppWxMenuShare wxMenuS) {
		boolean flag = true;
		try {
			appMenuMapper.updateWxMenuByAll(wxMenuS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean addWxMenuByAll(AppWxMenuShare wxMenuS) {
		boolean flag = true;
		try {
			appMenuMapper.addWxMenuByAll(wxMenuS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean updateIndex(AppWxMenuShare wxMenuS) {
		boolean flag = true;
		try {
			appMenuMapper.updateIndex(wxMenuS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean updateBottonMenu(AppWxMenuShare wxMenuS) {
		boolean flag = true;
		try {
			appMenuMapper.updateBottonMenu(wxMenuS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
