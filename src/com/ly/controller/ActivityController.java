package com.ly.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ly.entity.ActivityInfo;
import com.ly.entity.NowPage;
import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.ActivityService;
import com.ly.service.GroupService;
import com.ly.util.Common;
import com.ly.util.ConverToHtml;
import com.ly.util.FTPLinuxUtils;

@Controller
@RequestMapping("/background/activity/")
public class ActivityController extends BaseController {
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private GroupService groupService;
	
	
	/**
	 * 政策列表页面
	 * 
	 * @author Yutai.Yin 2017年3月8日上午11:13:49
	 * @param model
	 * @param pageNow
	 * @param pagesize
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@RequestMapping("list")
	public String listActivity(HttpServletRequest request,Model model,String pageNo,String starttime,String endtime){
		//如果开始时间不为空，进行时间转换
		if(starttime == ""||starttime == null ){
			starttime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		//如果结束时间不为空，进行时间转换
		if(endtime == ""||endtime == null ){
			endtime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		//获取政策的数量
		int count=activityService.findActivityCount();
		NowPage<Map<String,Object>> page = new NowPage<>(pageNo, count, 10);
		Map<String,Object> parame =  new HashMap<String,Object>();
		parame.put("start", page.getStart());
		parame.put("size", page.getSize());
		List<Map<String,Object>> listActivity=activityService.findActivityByPage(parame);
		
		page.setItems(listActivity);
		model.addAttribute("start", page.getStart());
		model.addAttribute("size", page.getSize());
		model.addAttribute("page", page);
		model.addAttribute("listActivity", listActivity);
		model.addAttribute("starttime", starttime);
		model.addAttribute("endtime", endtime);
		return Common.BACKGROUND_PATH +"/activity/list";
	}
	
	/**
	 * 发布政策页面
	 * @author Yutai.Yin 2017年3月8日下午2:51:09 
	 * @return
	 */
	@RequestMapping("addUI")
	public String addActivityUI(){
		return Common.BACKGROUND_PATH +"/activity/add";
	}
	
	
	/**
	 * 通过时间筛选出来的清单
	 * @author Yutai.Yin 2017年3月8日下午2:53:20
	 * @param request
	 * @param model
	 * @param pageNo
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	
	@RequestMapping("listByTime")
	public String listByTime(HttpServletRequest request,Model model,String pageNo,String starttime,String endtime){		
		int count=activityService.findActivityCountByTime(starttime,endtime);
		NowPage<Map<String,Object>> page = new NowPage<>(pageNo, count, 10);
		Map<String,Object> parame =  new HashMap<String,Object>();
		parame.put("start", page.getStart());
		parame.put("size", page.getSize());
		parame.put("starttime", starttime);
		parame.put("endtime", endtime);
		List<Map<String,Object>> listActivity=activityService.findActivityByPageByTime(parame);
		
		page.setItems(listActivity);
		model.addAttribute("start", page.getStart());
		model.addAttribute("size", page.getSize());
		model.addAttribute("page", page);
		model.addAttribute("listActivity", listActivity);
		model.addAttribute("starttime", starttime);
		model.addAttribute("endtime", endtime);
		return Common.BACKGROUND_PATH +"/activity/list";
	}
	
	/**
	 * 删除政策
	 * @author Yutai.Yin 2017年3月8日下午3:03:04
	 * @param id
	 * @return
	 */
	@RequestMapping("del")
	public String delActivity(String id){
		
		this.activityService.delete(id);
		
		return "redirect:list.html";
	}
	
	/**
	 * 置顶政策
	 * @author Yutai.Yin 2017年3月8日下午3:12:09
	 * @param id
	 * @return
	 */
	@RequestMapping("top")
	public String topActivity(String id){
		
		this.activityService.top(id);
		
		return "redirect:list.html";
	}
	
	/**
	 * 取消置顶政策
	 * @author Yutai.Yin 2017年3月8日下午3:16:55
	 * @param id
	 * @return
	 */
	@RequestMapping("canceltop")
	public String cancelTopActivity(String id){
		
		this.activityService.cancelTop(id);
		
		return "redirect:list.html";
	}
	
	
	/**
	 * 政策发布
	 * @author Yutai.Yin 2017年3月8日下午4:14:20
	 * @param files
	 * @param request
	 * @param model
	 * @param title
	 * @param starttime
	 * @param endtime
	 * @param desc
	 * @param groupId
	 * @param activity_type
	 * @param mold
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	
	@RequestMapping("add")
	public String addActivity(@RequestParam("file") MultipartFile[] files,
			HttpServletRequest request,
			Model model,
			String title,
			String starttime,
			String endtime,
			String desc,
			long groupId,
			String activity_type,
			String mold) throws IllegalStateException, IOException, ParserConfigurationException, TransformerException{

		List<String> resutl = new ArrayList<String>();
		String s="";
		
		Account  user = getAccount(request);
		ActivityInfo info = new ActivityInfo();
		
		String content = request.getParameter("context1");
			if(!"".equals(content) && null != content){
			System.out.println("此次政策发布通过富文本编辑框发布");
            //读取文件夹下的文件数量
            //读取文件的文件夹路径
            String path1 = request.getServletContext().getRealPath("/upload/activity");
            //获得文件名字
            this.getFileName(path1);
            //协议类型
            String mol = request.getScheme();
            //服务器名字
            String serverName = request.getServerName();
            //获得端口号
            int port = request.getServerPort();
            //应用名字
            String appPath = request.getContextPath();
            //图片在服务器的文件夹路径
            String path2 = mol +"://"+ serverName +":"+ port + appPath + "/upload/activity/";
			//将富文本编辑框的内容赋值给s；
			s = content.replace( path2, "http://112.74.88.25/Activity/upload/");
			
		} else if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
	                MultipartFile file = files[i];
					 // 判断文件是否为空
			        if (!file.isEmpty()) {
			        	String fileName = file.getOriginalFilename();			    				        
			    		String path = request.getSession().getServletContext().getRealPath("upload")+"/"+ fileName;
			    		/*File saveDir = new File(path);
		                file.transferTo(saveDir);*/
		                try {
		        			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
		        		} catch (IOException e) {
		        			e.printStackTrace();
		        		}
			    		try {
                            ConverToHtml converToHtml = new ConverToHtml();
                            s = converToHtml.convert2Html(path,request);
			    		} catch (TransformerException e) {
			    			e.printStackTrace();
			    		} catch (IOException e) {
			    			e.printStackTrace();
			    		} catch (ParserConfigurationException e) {
			    			e.printStackTrace();
			    		}
			        }
			 }
		}
		resutl.add(s);
		info.setId(getUUID());
		info.setTitle(title);
		info.setDescription(desc);
		info.setContent(resutl.get(0));
		info.setEnd_time(Common.formatDateToDate(endtime));
		info.setStart_time(Common.formatDateToDate(starttime));
		info.setGroupId(groupId);
		info.setActivity_type(activity_type);
		info.setCreate_user(user.getId()+"");
		info.setCounts(0);
		info.setIs_handle("1");
		info.setMold(mold);
		this.activityService.add(info);
		model.addAttribute("activitys", info);
		return "redirect:list.html";
	}
	
	/**
	 * 读取政策信息
	 * @author Yutai.Yin 2017年3月8日下午4:41:41
	 * @param id
	 * @param model
	 * @return
	 */
	
	@RequestMapping("info")
	public String getActivityInfo(String id,Model model){
		
		ActivityInfo info = this.activityService.getActivityInfo(id);
		
		model.addAttribute("info", info);
		
		return Common.BACKGROUND_PATH +"/activity/info";
	}

	
	/**
	 * 政策阅读
	 * @author Yutai.Yin 2017年3月8日下午4:54:39
	 * @param request
	 * @param pageNo
	 * @param pagesize
	 * @param model
	 * @return
	 */
	@RequestMapping("read")
	public String readActivity(HttpServletRequest request,String pageNo,String pagesize,Model model){
		
		Account user = getAccount(request);
		String starttime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String endtime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		String gid=this.activityService.getAllGroupId(user.getGroupId());
		int count=activityService.readAll(gid);
		
		NowPage<Map<String,Object>> page = new NowPage<>(pageNo, count, 10);
		Map<String,Object> parame =  new HashMap<String,Object>();
		parame.put("start", page.getStart());
		parame.put("size", page.getSize());
		parame.put("gid", gid);
		List<Map<String,Object>> listPolicy=activityService.findPolicyByPage(parame);
		page.setItems(listPolicy);
		model.addAttribute("start", page.getStart());
		model.addAttribute("size", page.getSize());
		model.addAttribute("page", page);
		model.addAttribute("listPolicy", listPolicy);
		model.addAttribute("starttime", starttime);
		model.addAttribute("endtime", endtime);		
		return Common.BACKGROUND_PATH + "/activity/read";
	}
	
	
	
	/**
	 * 政策阅读界面的查询
	 * @author Yutai.Yin 2017年3月8日下午5:08:04
	 * @param request
	 * @param pageNo
	 * @param model
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@RequestMapping("readsearch")
	public String readInfoSearch(
			HttpServletRequest request,
			String pageNo,
			Model model,
			String starttime,
			String endtime){
		
		Account user = getAccount(request);
		
		if(starttime == ""||starttime == null ){
			starttime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		
		if(endtime == ""||endtime == null ){
			endtime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		//List<Integer> gid = this.groupService.getAllGroupsId(user.getGroupId());
		String gid=this.activityService.getAllGroupId(user.getGroupId());
		int count=activityService.findCountActivityByTime(gid,starttime,endtime);
		NowPage<Map<String,Object>> page = new NowPage<>(pageNo, count, 10);
		Map<String,Object> parame =  new HashMap<String,Object>();
		parame.put("start", page.getStart());
		parame.put("size", page.getSize());
		parame.put("starttime",starttime);
		parame.put("endtime", endtime);
		parame.put("gid",gid);
		
		List<Map<String,Object>> listPolicy=activityService.findActivityByTime(parame);
		page.setItems(listPolicy);
		model.addAttribute("start", page.getStart());
		model.addAttribute("size", page.getSize());
		model.addAttribute("page", page);
		model.addAttribute("listPolicy", listPolicy);
		model.addAttribute("starttime", starttime);
		model.addAttribute("endtime", endtime);	
		return Common.BACKGROUND_PATH + "/activity/read";
	}
	
	/**
	 * 政策详情
	 * @author Yutai.Yin 2017年3月8日下午5:20:15
	 * @param id
	 * @param request
	 * @param model
	 * @param groupid
	 * @param pageNow
	 * @param pagesize
	 * @return
	 */
	@RequestMapping("readinfo")
	public String readInfo(String id,HttpServletRequest request,Model model,String groupid,String pageNow,String pagesize){
		
		ActivityInfo activity = this.activityService.getById(id);
		
		
		List<Map<String,String>> pageView = null;
		
		PageView p = null;
		String gid= "";
		Account user = getAccount(request);
		if(groupid == null){
			
			gid = user.getGroupId()+"";
			p = this.activityService.getReadInfo(getPageView(pageNow, pagesize),user.getGroupId()+"",activity);
			if(p != null){
				pageView = (List<Map<String,String>>)p.getRecords();
				model.addAttribute("pageView", pageView);
			}
			
		}else{
			
			gid= groupid;
			p = this.activityService.getReadInfo(getPageView(pageNow, pagesize),groupid,activity);
			if(p != null){
				pageView = (List<Map<String,String>>)p.getRecords();
				model.addAttribute("pageView", pageView);
			}
			
		}
		
		model.addAttribute("activity", activity);
		
		
		
		int flag = 0;
		if(pageView != null && pageView.size() > 0){
			for(Map<String,String> map:pageView){
				if(map.get("tail") != null){
					flag = 1;
					model.addAttribute("tail", map.get("tail"));
					model.addAttribute("pageView", p);
					break;
				}
					
			}
			
		}
		Group g = this.groupService.getById(gid);
		model.addAttribute("user", user);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		model.addAttribute("currentdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).substring(0, 10));
		if(flag == 1){
			model.addAttribute("qingdan",g.getName()+"_阅读明细"+sf.format(new Date())+".csv");
			return Common.BACKGROUND_PATH + "/activity/readlist";
		}
		model.addAttribute("qingdan",g.getName()+"_阅读明细"+sf.format(new Date())+".xls");
		model.addAttribute("tail", gid);
		model.addAttribute("exportexcel", "1");
		return Common.BACKGROUND_PATH + "/activity/readinfo";
	}
	
	
	/**
	 * 清单下载
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/download")   
    public ModelAndView download(  
    String fileName, HttpServletRequest request, HttpServletResponse response,String tail,String id,String flag)   
            throws Exception {   
		
		Account user = getAccount(request);
        response.setContentType("text/html;charset=utf-8");   
        request.setCharacterEncoding("UTF-8");   
        java.io.BufferedInputStream bis = null;   
        java.io.BufferedOutputStream bos = null;   
  
        String ctxPath = request.getSession().getServletContext().getRealPath("/")+"download"+File.separator;   
        String downLoadPath = ctxPath + fileName;   
        String fname = this.activityService.writeFile(tail,id,downLoadPath,flag,user);
        System.out.println(downLoadPath);   
        try {   
            long fileLength = new File(downLoadPath).length();   
            response.setContentType("application/x-msdownload;");   
            response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));   
            response.setHeader("Content-Length", String.valueOf(fileLength));   
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));   
            bos = new BufferedOutputStream(response.getOutputStream());   
            byte[] buff = new byte[2048];   
            int bytesRead;   
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
                bos.write(buff, 0, bytesRead);   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            if (bis != null) bis.close();   
            if (bos != null) bos.close();   
        }   
        return null;   
    }
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 读取文件夹下的文件
     * 并上传至服务器
	 * @author Yutai.Yin 2017年3月8日下午4:15:25
	 * @param path
	 */
	public  void getFileName(String path) {
		File f = new File(path);
		if (!f.exists()) {
			System.out.println(path + " not exists");
			return;
		}
		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
			File fs = fa[i];
			if (fs.isDirectory()) {
				System.out.println(fs.getName() + " [目录]");
			} else {
				System.out.println(fs.getName());
			}
			//将目录下文件上传至FPT服务器
			FTPLinuxUtils ftpLinux = new FTPLinuxUtils();
			String path1 = path + "/" + fs.getName();
			boolean upFlag = ftpLinux.uploadFile("112.74.88.25", "21", "mkl_ftp", "makalu_ftp", fs.getName(), path1,"Activity/upload/");
			//如果文件上传成功则删除文件
			if(upFlag) {
				//执行删除文件的方法
				boolean delState = this.deleteFile(path1);
                System.out.println("=============删除文件状态为:"+ delState +"本地路径为："+ path1 +"===================");
            }
			System.out.println("================文件上传的状态为："+ upFlag +"本地路径为：" + path1 + "=================");
		}
	}
	
	/**
	 * 删除单个文件件的方法
	 * @param   sPath    被删除文件的文件路径名
	 * @return 单个文件删除成功返回true
	 *			单个文件删除成功返回false
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	
	
	
	
	
	
	
}