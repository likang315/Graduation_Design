package com.ly.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ly.entity.app.AppWxMenu;
import com.ly.entity.app.AppWxMenuShare;
import com.ly.service.AppMenuService;
import com.ly.util.Common;
import com.ly.util.FTPLinuxUtils;

/**
 * app 菜单权限控制
 *
 */
@Controller
@RequestMapping("/app/wxmenu")
public class AppMenuController {
	
	@Autowired
	private AppMenuService appMenuService;

	/**
	 * 01.获取当前用户组所有的菜单
	 *
	 * @param groupId
	 * @param vendorId
	 * @param brandId
	 * @return
	 */
	@RequestMapping("/getWxMenuByAll")
	@ResponseBody
	public Object getWxMenuByAll(int groupId,int vendorId,int brandId){
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("groupId", groupId);
		parameter.put("vendorId", vendorId);
		parameter.put("brandId", brandId);
		AppWxMenuShare wxMenuS = appMenuService.getWxMenuByAllInfo(parameter);
		if( wxMenuS!= null){
			List<AppWxMenu> LWxMenu =  new ArrayList<AppWxMenu>();
			parameter.put("ids",wxMenuS.getLeft_menu());
			LWxMenu = appMenuService.getWxMenuByAll(parameter);
			parameter.put("info", LWxMenu);

			LWxMenu =  new ArrayList<AppWxMenu>();
			parameter.put("ids",wxMenuS.getIndex());
			LWxMenu = appMenuService.getWxMenuByAll(parameter);
			parameter.put("index_menu", LWxMenu);

			LWxMenu =  new ArrayList<AppWxMenu>();
			String bottom_id = wxMenuS.getBottom_menu();
			String[] bottom_ids =bottom_id.split(",");
			if(!bottom_ids[0].equals("0")){
				for (int i = 0; i < bottom_ids.length; i++) {
					parameter.put("ids",bottom_ids[i]);
					List<AppWxMenu> LWxMenus = appMenuService.getWxMenuByAll(parameter);
					LWxMenu.add(LWxMenus.get(0));
				}
				parameter.put("bottom_menu", LWxMenu);
			}
			parameter.put("bottom_menu", LWxMenu);
			parameter.put("state", "ok");
			parameter.put("menuId", wxMenuS.getId());
			parameter.put("groupId", wxMenuS.getGroupId());
		}else{
			parameter.put("state", "no");
		}
		return parameter;
	}



	@RequestMapping()
	public String jumpWXmenu(Model model){
		List<AppWxMenu> LWxMenu = appMenuService.getAll_wxMenu();
		model.addAttribute("LWxMenu", LWxMenu);
		return Common.BACKGROUND_PATH + "/AppMenu/showWxMenu";
	}
	
	@RequestMapping("/addWxMenu")
	@ResponseBody
	public Object addWxMenu(AppWxMenu wxMenu){
		return appMenuService.addWxMenu(wxMenu);
	}
	
	@RequestMapping("/updateWxMenu")
	@ResponseBody
	public Object updateWxMenu(AppWxMenu wxMenu){
		return appMenuService.updateWxMenu(wxMenu);
	}
	
	@RequestMapping("/delWxMenu")
	@ResponseBody
	public Object delWxMenu(String ids){
		return appMenuService.delWxMenu(ids);
	}
	

	//微信端页面访问权限开始
	@RequestMapping("/jumpWXmenuShior")
	public String jumpWXmenuShior(Model model){
		List<AppWxMenu> LWxMenu = appMenuService.getAll_wxMenu();
		model.addAttribute("LWxMenu", LWxMenu);
		return Common.BACKGROUND_PATH + "/AppMenu/menuShior";
	}


	@RequestMapping("/addWxMenuByAll")
	@ResponseBody
	public Object addWxMenuByAll(AppWxMenuShare wxMenuS){
		Map<String,Object> result = new HashMap<String,Object>();
		if(appMenuService.addWxMenuByAll(wxMenuS)){
			result.put("state","ok");
			result.put("info","添加成功");
		}else{
			result.put("state","no");
			result.put("info","添加失败");
		}
		return result;
	}
	
	@RequestMapping("/updateWxMenuByAll")
	@ResponseBody
	public Object updateWxMenuByAll(AppWxMenuShare wxMenuS){
		Map<String,Object> result = new HashMap<String,Object>();
		if(appMenuService.updateWxMenuByAll(wxMenuS)){
			result.put("state","ok");
			result.put("info","修改成功");
		}else{
			result.put("state","no");
			result.put("info","修改失败");
		}
		return result;
	}
	
	
	@RequestMapping("/updateIndex")
	@ResponseBody
	public Object updateIndex(AppWxMenuShare wxMenuS){
		Map<String,Object> result = new HashMap<String,Object>();
		if(wxMenuS.getId()==0){
			result.put("state","ok");
			result.put("info","请先添加侧边菜单");
			return result;
		}
		if(appMenuService.updateIndex(wxMenuS)){
			result.put("state","ok");
			result.put("info","修改成功");
		}else{
			result.put("state","no");
			result.put("info","修改失败");
		}
		return result;
	}
	
	@RequestMapping("/updateBottonMenu")
	@ResponseBody
	public Object updateBottonMenu(AppWxMenuShare wxMenuS){
		Map<String,Object> result = new HashMap<String,Object>();
		
		if(wxMenuS.getId()==0){
			result.put("state","ok");
			result.put("info","请先添加侧边菜单");
			return result;
		}
		
		if(appMenuService.updateBottonMenu(wxMenuS)){
			result.put("state","ok");
			result.put("info","修改成功");
		}else{
			result.put("state","no");
			result.put("info","修改失败");
		}
		return result;
	}
	
	//微信端页面访问权限结束
	
	//图片上传
	@RequestMapping("/uploadImg")
	@ResponseBody
	public Object saveFile(@RequestParam CommonsMultipartFile imgFile,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String path = "";
		System.out.println(imgFile.getOriginalFilename());
		String fileName1 = "";
	      if (imgFile != null) {
	          fileName1 = imgFile.getOriginalFilename();
	
	          String path1 = request.getServletContext().getRealPath("/") + "file/log/";
	          //  下面的加的日期是为了防止上传的名字一样
	          path = path1
	                  + new SimpleDateFormat("yyyyMMddHHmmss")
	                          .format(new Date()) + fileName1;
	          System.out.println("文件地址："+path);
	          File localFile = new File(path);
	
	          imgFile.transferTo(localFile);
	      }
		  FTPLinuxUtils fileUp = new FTPLinuxUtils();
		  String fileName = UUID.randomUUID().toString()+ fileName1;
		  boolean upFlag = fileUp.uploadFile("112.74.88.25", "21", "mkl_ftp", "makalu_ftp", fileName, path,"menuImg/");
		  Map<String,Object> result = new HashMap<String,Object>();
		  if(upFlag){
			  result.put("filePath", "http://file.makalu.cc/menuImg/"+fileName);
			  result.put("info", "上传成功");
			  result.put("state", "ok");
			  
		  }else{
			  result.put("info", "上传失败");
			  result.put("state", "no");
		  }
		  System.out.println("图片地址----->" + result.get("filePath"));
		return result;
	}
		
	
}
