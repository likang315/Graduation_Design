package com.ly.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.ActivityInfo;
import com.ly.entity.ActivityRead;
import com.ly.service.ActivityService;
import com.ly.util.Common;


/**
 * app 端 政策控制器
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-21 11:05
 */
@RequestMapping("/app/policy")
@Controller
public class ApppolicyController extends AppBaseController {
	
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 跳转到政策页面
	 * @author Yutai.Yin 2017年3月9日上午11:55:39
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping("policy")
	public String policy(String type,Model model){
		model.addAttribute("type", type);
		return Common.APP_PATH + "/policy";
	}
	
	/**
	 * 政策列表
	 * @author Yutai.Yin 2017年3月9日上午11:57:06
	 * @param activitytype
	 * @param groupId
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="govlist",method = {RequestMethod.POST})
	@ResponseBody
	public Object govList(String activitytype,String groupId,String userid){
		List<ActivityInfo> activity = this.activityService.queryGovAll(activitytype,groupId,userid);
		
		if((null != activity && activity.size() > 0)){
			result.put("activity", activity);
			setResult(Common.OK);
		}else{
			setResult(Common.NO);
		}
		return result;
	}
	
	/**
	 * 
	 * @author Yutai.Yin 2017年3月9日下午12:07:01
	 * @param activitytype
	 * @param groupId
	 * @param userid
	 * @return
	 */
	
	@RequestMapping(value="getKnow",method = {RequestMethod.POST})
	@ResponseBody
	public Object getKnow(String activitytype,String groupId,String userid){

		List<ActivityInfo> activity = this.activityService.getKnowledge(activitytype,groupId,userid);

		if((null != activity && activity.size() > 0)){
			result.put("activity", activity);
			setResult(Common.OK);
		}else{
			setResult(Common.NO);
		}
		return result;
	}
	
	/**
	 * 政策详情跳转
	 * @author Yutai.Yin 2017年3月9日下午4:08:02
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("Policydetails")
	public String Policydetails(String id,Model model){
		model.addAttribute("id", id);
		return Common.APP_PATH+"/Policydetails";
	}
	
	
	@RequestMapping(value="detail",method = {RequestMethod.POST})
	@ResponseBody
	public Object activityDetail(String id,String userid){
		
		ActivityInfo activity = this.activityService.getById(id);
		if(activity != null){
			/**
			 * 记录阅读
			 */
			if(userid != null){
				ActivityRead r = this.activityService.getReadByUserid(userid,id);
				if(r == null){
					ActivityRead read = new ActivityRead();
					read.setId(getUUID());
					read.setRead_time(Common.formatDate(new Date()));
					read.setRead_user(userid);
					read.setPolicy_id(id);
					this.activityService.updateRecord(read);
				}
			}
			result.put("activity", activity);
			setResult(Common.OK);
		}else{
			setResult(Common.NO);
		}
		return result;
	}
	
	/**
	 * 政策搜索
	 * @author Yutai.Yin 2017年3月9日下午4:50:25
	 * @param keyword
	 * @param groupId
	 * @param userid
	 * @return
	 */
	@RequestMapping("search")
	@ResponseBody
	public Object searchAc(String keyword,String groupId,String userid){
		String g=activityService.getGroups(groupId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("groupid", g);
		map.put("userid", userid);

		List<Map<String,Object>> activity = this.activityService.search(map);
		if(activity.size()!=0){
			for (int i = 0; i < activity.size(); i++) {
				String start_time=new SimpleDateFormat("yyyy-MM-dd").format(activity.get(i).get("start_time"));
				String end_time=new SimpleDateFormat("yyyy-MM-dd").format(activity.get(i).get("end_time"));
				activity.get(i).put("start_time",start_time);
				activity.get(i).put("end_time", end_time);
			}
		}
		if(activity!=null && activity.size() > 0){
			setResult(Common.OK);
			result.put("activity", activity);
		}else{
			setResult(Common.NO);
		}
		return result;
	}
	


}
