package com.ly.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.ly.service.BackReportService;
import com.ly.util.Common;


/**
 * 订单统计
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-17 09:56
 */
@Controller
@RequestMapping ("/background/report")
public class BackReportController{
	@Autowired
	private BackReportService backReportService;
	
	/**
	 * 订单配送统计报表
	 * @author 殷瑜泰 2017年3月30日上午10:11:27
	 *
	 * @return
	 */
	@RequestMapping("/orderInit")
	public String orderInit(Model model,@RequestParam Map m){
		//查询订单信息
		List<Map<String,Object>> ls = backReportService.getOrder(m);
		model.addAttribute("ls", JSON.toJSON(ls));
		model.addAttribute("startDate", m.get("startDate"));
		model.addAttribute("endDate", m.get("endDate"));
		return Common.BACKGROUND_PATH + "/report/orderReport" ;
	}
	
	
	
	/**
	 * 订单统计
	 * */
	@RequestMapping("/toReportTotal")
	public String toMarketingOrderTotalView(){
		return Common.BACKGROUND_PATH + "/report/toReportTotal";
	}
	
	
	/**
	 * @author lfy 
	 * 营销中心统计报表
	 * @param userPhone
	 * @param startTime
	 * @param endTime
	 * @param model
	 * @return
	 */
	@RequestMapping("/getReportTotal")
	public String getMarketingOrderTotal(String startTime, String endTime, Model model){
		if(startTime=="" ){
			startTime=null;
		}
		if(endTime=="" ){
			endTime=null;
		}
		
		//获取到报表所需要的数据
		 List<Map<String,Object>> ls =  backReportService.getReportDatas(startTime,endTime);
		 //当ls不为空的时候将数据传到页面
		 if(ls != null){
			List<Map<String,Object>> ls1 = (List<Map<String, Object>>) ls.get(0).get("materialAndNumLs");
			 model.addAttribute("ls", ls);
			 model.addAttribute("size", ls1.size());
		 } else {
			 model.addAttribute("ls", null);
		 }
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return Common.BACKGROUND_PATH + "/report/reportTotal";
		
		
	}
	
	
	 
    /**
     * 下载模板
     */
    @RequestMapping("/downDomeFile")
	public String downDomeFile(String fileName, HttpServletRequest request,HttpServletResponse response){
    	try {
			fileName = java.net.URLDecoder.decode(fileName,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String path = request.getServletContext().getRealPath("/") + "file/template/";
    	try {
			downloadFile(fileName,path,request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 下载文件
     * @param fileName 文件名加后缀
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public static void downloadFile(String fileName,String downPath, HttpServletRequest request,HttpServletResponse response) throws Exception{
		
        response.setContentType("application/octet-stream;charset=utf-8");   
        //request.setCharacterEncoding("UTF-8");   
        response.setCharacterEncoding("UTF-8");   
        java.io.BufferedInputStream bis = null;   
        java.io.BufferedOutputStream bos = null;   
        String ctxPath = "";
        if(downPath != ""){
        	ctxPath = downPath;
        }else{
        	ctxPath = request.getSession().getServletContext().getRealPath("/")+"download"+File.separator;   
        }
        
        String downLoadPath = ctxPath + fileName;   
        
        System.out.println("生成路径:"+downLoadPath); 
			
			 try {   
		            long fileLength = new File(downLoadPath).length();   
		            response.setContentType("application/x-msdownload;");   
		            response.addHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));   
		            response.addHeader("Content-Length", String.valueOf(fileLength));   
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
	}
}
