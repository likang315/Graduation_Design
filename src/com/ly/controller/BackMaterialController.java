package com.ly.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ly.entity.NowPage;
import com.ly.service.BackMaterialService;
import com.ly.util.Common;

/**
 * 物资的后台管理
 * 
 * @author 殷瑜泰 2017年3月26日下午10:14:28
 *
 */
@Controller
@RequestMapping("/background/Material")
public class BackMaterialController {
	
	@Autowired
   	private BackMaterialService backMaterialService;
	
	/**
	 * 查询物资清单信息，并跳转
	 * 
	 * @author 殷瑜泰 2017年3月26日下午10:17:17
	 *
	 * @param pageNow
	 * @param info
	 * @param model
	 * @param modifyState
	 * @param deleteinfo
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/materialList")
	public String materialList(String addInfo, String modifyInfo,String deleteInfo, String pageNow, Model model) throws UnsupportedEncodingException {
		if(pageNow == null || "".equals(pageNow)){
		pageNow = "0";
		}
		//传参map
		Map<String,Object> parame = new HashMap<String,Object>();
		//获取的物资的总条数
		Integer totalPage = backMaterialService.getMaterialCount();
		NowPage<Map<String,Object>> page = new NowPage<>(pageNow, totalPage, 10);
		parame.put("size", 10);
		parame.put("start", page.getStart());
		List<Map<String, Object>> materialList = backMaterialService.list(parame);
		page.setItems(materialList);
		model.addAttribute("page", page);
		if(modifyInfo != null && modifyInfo != ""){
			model.addAttribute("modifyInfo", new String(modifyInfo.getBytes("ISO-8859-1"),"UTF-8"));
		}
		if(deleteInfo != null && deleteInfo != "") {
			model.addAttribute("deleteMaterial", new String(deleteInfo.getBytes("ISO-8859-1"),"UTF-8"));
		}
		if(addInfo != null && addInfo != "") {
			model.addAttribute("addInfo", new String(addInfo.getBytes("ISO-8859-1"),"UTF-8"));
		}
		
		// 跳转到list页面
		return Common.BACKGROUND_PATH + "/material/materialList";
	}
	
	/**
	 * 跳转到修改页面
	 * @author 殷瑜泰 2017年3月27日上午6:57:32
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/permissio")
	public String permissio(String id, Model model) {
		//根据id查询出当前的物资信息
		Map<String,Object> materialInfo = backMaterialService.getMaterialInfo(id);
		model.addAttribute("materialInfo", materialInfo);
		
		return Common.BACKGROUND_PATH + "/material/materialmodify";
		
	}
	/**
	 * 修改物资信息
	 * @author 殷瑜泰 2017年3月27日上午7:16:45
	 *
	 * @param material
	 * @return
	 */
	@RequestMapping("/modify")
	public String modify(@RequestParam Map material,RedirectAttributes attributes) {
		//根据传入的物资信息执行修改
		if(backMaterialService.modify(material)){
			attributes.addAttribute("modifyInfo", "物资信息修改成功!");
		} else {
			attributes.addAttribute("modifyInfo", "物资信息修改失败!");
		}
		return "redirect:materialList.html";
	}
	
	
	@RequestMapping("/deleteMaterial")
	public String deleteMaterial(String id,RedirectAttributes attributes) {
		//删除信息物资信息
		if(backMaterialService.deleteMaterial(id)) {
			attributes.addAttribute("deleteInfo", "删除信息成功");
		} else {
			attributes.addAttribute("deleteInfo", "删除信息失败");
		}
		return "redirect:materialList.html";
	}
	
	@RequestMapping("/addUI")
	public String addUI(Model model) {
		//
		model.addAttribute("code", this.getRandomString(8)); 
		//跳转到新增物资页面
		return Common.BACKGROUND_PATH + "/material/materialadd";
		
	}
	
	/**
	 * 新增物资信息
	 * @author 殷瑜泰 2017年3月27日上午8:23:02
	 *
	 * @return
	 */
	@RequestMapping("/add")
	public String add(@RequestParam Map m,RedirectAttributes attributes,HttpServletRequest request){
		
		if(backMaterialService.add(m,request)){
			attributes.addAttribute("addInfo", "新增物资信息成功！");
		} else {
			attributes.addAttribute("addInfo", "新增物资信息失败！");
		}
		return "redirect:materialList.html";
	}
	
	/**
	 * 
	 * @author 殷瑜泰 2017年4月5日下午2:56:08
	 *
	 * @param length
	 * @return
	 */
	public String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString().toUpperCase();    
	 }     
	
	
		

	
		
		 

}
