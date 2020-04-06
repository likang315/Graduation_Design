package com.ly.service.appimpl;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ly.entity.app.APPCourierStore;
import com.ly.entity.app.MailInformation;
import com.ly.mapper.APPCoordinateMapper;
import com.ly.service.APPCoordinateService;
import com.ly.util.FTPLinuxUtils;

@Service
public class AppCoordinateServiceImpl implements APPCoordinateService{

	@Autowired
	private APPCoordinateMapper  appCoordinateMapper;
	

	@Override
	public List<Map<String, Object>> findIncomplete(String courierPhone) {
		// TODO Auto-generated method stub
		return appCoordinateMapper.findIncomplete(courierPhone);
	}


	@Override
	public  Map<String, Object> findbyList(String logistics){
		return appCoordinateMapper.findbyList(logistics);
	}
	@Override
	public Map<String,	Object> findStore(Map<String, Object> m) {
		// TODO Auto-generated method stub
		return appCoordinateMapper.findStore(m);
	}

	//验证验证是否正确
		@Override
		public boolean testCode(String tellPhone,String code){
			if(appCoordinateMapper.testCode(tellPhone, code).size()>0)return true;
			else return false;
		}
	
		@Override
		public boolean testCode2(String tellPhone, String code) {
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date());
			if(appCoordinateMapper.testCode2(tellPhone, code,time).size()>0){
				return true;
			}else{ 
				return false;
			}
		}
		
		@Override
		public boolean updateOrder( Map<String, Object> m) {
			boolean flag = true;
			try {
				appCoordinateMapper.updateOrder(m);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				flag = false;
			}
			return flag;
		}


		@Override
		public boolean addCourierstore(APPCourierStore appCourierStore) {
			boolean flag = true;
			try {
				appCoordinateMapper.addCourierstore(appCourierStore);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				flag = false;
			}
			return flag;
		}	
		
		@Override
		public boolean addCourierTime(Map<String, Object> m) {
			boolean flag = true;
			try {
				appCoordinateMapper.addCourierTime(m);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				flag = false;
			}
			return flag;
		}	
		
		
		
		@Override
		public boolean updateState( String id) {
			boolean flag = true;
			try {
				appCoordinateMapper.updateState(id);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				flag = false;
			}
			return flag;
		}
		
		@Override
		public List<Map<String, Object>> findtotal(Map<String, Object> m) {
			
			return appCoordinateMapper.findtotal(m);
		}
		@Override
		public List<Map<String, Object>> CourierAllTotal(Map<String, Object> m) {
			
			return appCoordinateMapper.CourierAllTotal(m);
		}
		
		@Override
		public Integer getCourierlistCount(Map<String, Object> m) {
			
			return appCoordinateMapper.getCourierlistCount(m);
		}


		@Override
		public boolean addStoremail(Map<String, Object> m) {
			boolean flag = true;
			try {
				appCoordinateMapper.addStoremail(m);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				flag = false;
			}
			return flag;
		}


		@Override
		public boolean addStoreinfo(Map<String, Object> m) {
			boolean flag = true;
			try {
				appCoordinateMapper.addStoreinfo(m);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				flag = false;
			}
			return flag;
		}


		@Override
		public boolean updateCourierstore(Map<String, Object> m) {
			boolean flag = true;
			try {
				appCoordinateMapper.updateCourierstore(m);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				flag = false;
			}
			return flag;
		}

		//	
		@Override
		public boolean addImgForOrder(MultipartFile orderImg, MultipartFile storeImg,Map<String, Object> m) {
			boolean flag = false;
			//获取文件的后缀名
			String orderImgSuffix = this.getSuffix(orderImg);
			String storeImgSuffix = this.getSuffix(storeImg);
			//获取文件流
			InputStream orderImgInputStream = null;
			InputStream storeImgInputStream = null;
			try {
				orderImgInputStream = orderImg.getInputStream();
				 storeImgInputStream = storeImg.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//生成文件的名字
			String orderImgName = UUID.randomUUID().toString() + orderImgSuffix;
			String storeImgName = UUID.randomUUID().toString() + storeImgSuffix;
			
			//将图片信息上传到fpt服务器
			FTPLinuxUtils ftpLinux = new FTPLinuxUtils();
			
			boolean orderImgState =  ftpLinux.uploadFile("112.74.88.25", 21, "mkl_ftp", "makalu_ftp", orderImgName, orderImgInputStream, "logistics/upload/");;
			boolean storeImgState =  ftpLinux.uploadFile("112.74.88.25", 21, "mkl_ftp", "makalu_ftp", storeImgName, storeImgInputStream, "logistics/upload/");
			
			String ftpOrderImgName ="http://112.74.88.25/logistics/upload/"+ orderImgName;
			String ftpstoreImg ="http://112.74.88.25/logistics/upload/"+ storeImgName;
			m.put("ftpOrderImgName", ftpOrderImgName);
			m.put("ftpstoreImg", ftpstoreImg);
			//如果文件上传成功
			if(orderImgState && storeImgState){
				//维护订单的图片信息
				 if(appCoordinateMapper.addImgForOrder( m) > 0){
					 flag = true;
				 } else {
					 flag = false;
				 }
				 
			}
			return flag;
		}
		
		/**
		 * 获取文件的后缀名
		 * @author 殷瑜泰 2017年4月1日上午11:35:26
		 *
		 * @return
		 */
		public String getSuffix(MultipartFile file){
			//获取文件的名称
			String fileName =  file.getOriginalFilename();
			String Suffix = "";
			if(!"".equals(fileName)){
				//获取文件的后缀	
				Suffix  = fileName.substring(fileName.lastIndexOf("."));
			}
			return Suffix;
			
		}
		
}
