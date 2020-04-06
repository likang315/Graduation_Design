package com.ly.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.entity.ActivityInfo;
import com.ly.entity.ActivityRead;
import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.mapper.ActivityMapper;
import com.ly.mapper.GroupMapper;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.ActivityService;
import com.ly.service.GroupService;
import com.ly.util.ExportExcelUtil;


@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Autowired
	private GroupService groupService;
	
	@Override
	public int findActivityCount() {
		return  activityMapper.findActivityCount();
	}
	
	@Override
	public List<Map<String, Object>> findActivityByPage(Map<String, Object> parame) {
		return  activityMapper.findActivityByPage(parame);
	}
	
	@Override
	public int findActivityCountByTime(String starttime, String endtime) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		return  activityMapper.findActivityCountByTime(map);
	}
	
	@Override
	public List<Map<String, Object>> findActivityByPageByTime(Map<String, Object> parame) {
		return  activityMapper.findActivityByPageByTime(parame);
	}
	
	@Override
	public void delete(String id) {
		try {
			this.activityMapper.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void top(String id) {
		try {
			this.activityMapper.top(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void cancelTop(String id) {
		try {
			this.activityMapper.cancelTop(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 新增发布政策
	 */
	@Override
	public void add(ActivityInfo info) {
		try {
			this.activityMapper.add(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 读取政策信息
	 */
	@Override
	public ActivityInfo getActivityInfo(String id) {

		ActivityInfo info = this.activityMapper.getActivityInfo(id);
		
		return info;
	}
	
	@Override
	public String getAllGroupId(int groupId) {
		return  activityMapper.getAllGroupId(groupId);
	}
	
	@Override
	public int readAll(String gid) {
		return  activityMapper.readAll(gid);
	}
	
	@Override
	public List<Map<String, Object>> findPolicyByPage(Map<String, Object> parame) {
		return  activityMapper.findPolicyByPage(parame);
	}
	
	@Override
	public int findCountActivityByTime(String gid, String starttime, String endtime) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("gid",gid);
		map.put("starttime", starttime);
		map.put("endtime", endtime);
		return  activityMapper.findCountActivityByTime(map);
	}
	
	@Override
	public List<Map<String, Object>> findActivityByTime(Map<String, Object> parame) {
		return  activityMapper.findActivityByTime(parame);
	}
	
	@Override
	public ActivityInfo getById(String id) {
		ActivityInfo activity = this.activityMapper.getById(id);
		if(activity != null){
			long count = activity.getCounts()+1;
			this.activityMapper.updateReadCounts(activity.getId(),count);
			
			String etime= activity.getEnd_time().substring(0, 18);//2016-02-20 18:17:18
			
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = sf.parse(etime);
				boolean flag = date.after(new Date());
				if(!flag){
					this.activityMapper.isHand(id);//过期
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return activity;
		}else{
			return null;
		}
	}
	
	
	@Override
	public PageView getReadInfo(PageView p,String groupid,ActivityInfo activity) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		List<Group> groups = this.groupMapper.getGroupByParentId(Integer.parseInt(groupid));
		if(groups == null || groups.size() <=0 ){
			p.setRecords(null);
			return p;
		}
		if(groups != null && groups.size() > 0){
			for(Group g:groups){
				if(g.getId() != null){
					List<Long> ids = new ArrayList<Long>();
					ids.add(Long.parseLong(g.getId()));
					getNextLeveGroupsId(g.getId(), ids);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", activity.getId());
					map.put("groupsid", ids);
					map.put("groupsid2", ids);
					map.put("currentid", g.getId());
					if(ids != null && ids.size() > 0){
						Map<String,String> re = this.activityMapper.getReadInfo(map);
						if(!re.isEmpty()){
							result.add(re);
						}
					}else{
						continue;
					}
				}
			}
		}else{
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("id", activity.getId());
			map1.put("paging", p);
			map1.put("groupid", groupid);
			result.add(this.activityMapper.getReadList(map1));
			Map<String, String> flag = new HashMap<String,String>();
			if(!flag.isEmpty()){
				flag.put("tail", groupid);
				result.add(flag);
			}
			
		}
		if(result != null && result.size() > 0){
			p.setRecords(result);
			return p;
		}
		//pageView.setRecords(re);
		return null;
	}
	
	
	
	/**
	 * 生成清单
	 */
	@Override
	public String writeFile(String tail,String id,String path,String flag,Account user) {
		System.out.println("fileName:"+path);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("groupid", tail);
		map.put("id", id);
		List<Map<String,String>> r = this.activityMapper.getAeraData(map);
		
		if(flag != null && flag.equals("excel")){
			exportExcel(tail,id,path,user);
			return path;
		}
		
		exportCsv(new File(path),r);
		
		return path;
	}
	
	private void getNextLeveGroupsId(String id, List<Long> ids) {
		List<Group> groups = this.groupMapper.getGroupByParentId(Integer.parseInt(id));
		if(groups != null && groups.size() > 0){
			for(Group g :groups){
				if(g.getId() != null){
					ids.add(Long.parseLong(g.getId()));
					getNextLeveGroupsId(g.getId(),ids);
				}
			}
		}
	}
	
	
	 private boolean exportExcel(String tail,String id,String path,Account user){
	    	
   	  HSSFWorkbook workbook = new HSSFWorkbook();  
   	  HSSFSheet sheet = workbook.createSheet();  
   	  ExportExcelUtil eeu = new ExportExcelUtil(workbook, sheet);  
   	  
   	  com.ly.entity.ActivityInfo ac = this.activityMapper.getById(id);
   	  
   	  List<Integer> gs = this.groupService.getAllGroupsId(Integer.parseInt(tail));
   	  
   	  Map<String, Object> map = new HashMap<>();
   	  map.put("gs", gs);
   	  map.put("id", id);
   	  List<Map<String,String>> datas =  this.activityMapper.getReadReport(map);
   	  
   	  
   	  String[][] columnDatas = new String[datas.size()][7];
   	  for(int i=0;i<datas.size();i++){
   		  Map<String,String> m = datas.get(i);
   		  columnDatas[i][0] = ac.getTitle();
   		  columnDatas[i][1] = ac.getStart_time();
   		  columnDatas[i][2] = ac.getEnd_time();
   		  columnDatas[i][3] = m.get("accountName");
   		  columnDatas[i][4] = m.get("real_name");
   		  columnDatas[i][5] = m.get("groupname");
   		  
   		  if(m.get("read_time") == null || m.get("read_time") == ""){
   			  columnDatas[i][6] = " ";
   		  }else{
   			  System.out.println(m.get("read_time"));
   			  columnDatas[i][6] =m.get("read_time")+"";//2016-03-01 14:10:11
   		  }
   	  }
   	  
   	  
   	  String strArr[] = new String[]{"政策/活动标题","开始时间","结束时间","用户账户","姓名","所属","阅读时间"};
   	  //String[] strArr = new String[] { "序号", "姓名", "性 别", "出生年月", "民族", "籍贯", "备注" };  
   	  int colNum = strArr.length;  
   	    
   	  int rowNO = 0;  
   	  //1. titleCaption  
   	  eeu.createExcelRow(workbook, sheet, rowNO, 400, colNum, "政策阅读统计报表"); // , 250, "bold", "center"  
   	  //2.  
   	  rowNO++;  
   	  eeu.createExcelRow(workbook, sheet, rowNO, 400, colNum," 制 表 人: "+user.getReal_name()+"       制 表 日 期: " + new java.text.SimpleDateFormat("yyyy-MM-dd hh:MM:ss").format(new java.util.Date()), 180, "normal", "right");  
   	  //3.columnTitleHeader  
   	  rowNO++;  
   	  eeu.createColumnHeader(sheet, rowNO, 400, strArr);  
   	    
   	  
   	  
   	  //4.数据行     循环创建中间的单元格的各项的值  
   	  rowNO++;  
   	  
   	  
   	 /* String[][] columnData = new String[][]{{ "1", "zhangsan", "男", "1985-10-06 21:00:00", "汉族", "西安", "学生" },  
   	    { "2", "猪猪", "女", "出生年月", "民族", "籍贯", "备注" },  
   	    { "3", "明明", "男", "1980-07-08", "汉族", "西安", "学生" },  
   	    { "4", "光光", "女", "1985-06-30", "汉族", "西安", "学生" }} ;  */
   	 
   	  sheet = eeu.createColumnData(sheet, rowNO,  columnDatas, -1);  
   	  eeu.createSummaryRow(workbook, sheet, colNum, "合计：" + columnDatas.length, 180, "normal", "right");  
   	  eeu.exportExcel(path);  
   	return true;
   }
	 
	 
	 /**
	     * 导出
	     * 
	     * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
	     * @param dataList 数据
	     * @return
	     */
	    private static boolean exportCsv(File file, List<Map<String,String>> dataList){
	        boolean isSucess=false;
	        
	        FileOutputStream out=null;
	        OutputStreamWriter osw=null;
	        
	        BufferedWriter bw=null;
	        try {
	            out = new FileOutputStream(file);
	            osw = new OutputStreamWriter(out);
	            
	            	bw =new BufferedWriter(osw);
	                bw.append("\"政策标题\",\"用户名\",\"阅读时间\"\r");
	                if(dataList!=null && !dataList.isEmpty()){
	                    for(Map<String,String> data : dataList){
	                    	Iterator<String> s = data.keySet().iterator();
	                    	String d = "";
	                    	while(s.hasNext()){
	                    		d += "\""+data.get(s.next())+"\",";
	                    	}
	                    	
	                        bw.append(d.substring(0, d.length()-1)).append("\r");
	                    }
	                }
	            isSucess=true;
	        } catch (Exception e) {
	            isSucess=false;
	        }finally{
	            if(bw!=null){
	                try {
	                    bw.close();
	                    bw=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	            if(osw!=null){
	                try {
	                    osw.close();
	                    osw=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	            if(out!=null){
	                try {
	                    out.close();
	                    out=null;
	                } catch (IOException e) {
	                    e.printStackTrace();
	                } 
	            }
	        }
	        
	        return isSucess;
	    }
	    
	    
	    
	    
	    @Override
		public List<ActivityInfo> queryGovAll(String activitytype, String groupId,
				String userid) {
			if(userid == null || userid == ""){
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("groupid", groupId);
			map.put("userid", userid);
			List<ActivityInfo> activity = this.activityMapper.getGovAll(map);
			if(activity != null && activity.size() > 0){
				return activity;
			}else{
				return null;
			}
		}
	    
	    
	    @Override
		public List<ActivityInfo> getKnowledge(String activitytype, String groupId, String userid) {
			if(userid == null || userid == ""){
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("groupid", groupId);
			map.put("userid", userid);
			List<ActivityInfo> activity = this.activityMapper.getKnowledge(map);
			if(activity != null && activity.size() > 0){
				return activity;
			}else{
				return null;
			}
		}
	    
	    @Override
		public ActivityRead getReadByUserid(String userid,String id) {
			ActivityRead read = this.activityMapper.getByUserid(userid,id);
			
			if(read != null){
				return read;
			}
			return null;
		}
	    
		@Override
		public void updateRecord(ActivityRead read) {
			this.activityMapper.updateRead(read);
		}
		
		@Override
		public List<Map<String,Object>> search(Map<String, Object> map) {
			List<Map<String,Object>>  r = this.activityMapper.search(map);
			return r;
		}
		
		@Override
		public String getGroups(String groupId) {
			return  activityMapper.getGroup(groupId);
		}
	    
	    
	
	

}
