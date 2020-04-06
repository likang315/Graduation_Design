package com.ly.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Value;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ly.entity.background.Account;
import com.ly.mapper.BackStoreMapper;
import com.ly.service.BackStoreService;
import com.ly.util.CourierNumber;
import com.ly.util.EncodingUtil;
import com.ly.util.FTPLinuxUtils;

/**
 * 门店的后台处理 实现
 * 
 * @author 殷瑜泰 2017年3月16日上午11:06:51
 *
 */
@Transactional
@Service("backstoreService")
public class BackStoreServiceImpl implements BackStoreService {
	@Autowired
	private BackStoreMapper backStoreMapper;

	@Override
	// 查询门店清单信息
	public List<Map<String, Object>> list(Map parame) {
		// 查询返回出门店信息
		return backStoreMapper.selectList(parame);
	}

	@Override
	// 查询出厂商信息
	public List<Map<String, Object>> getVendor() {
		return backStoreMapper.selectVendorList();
	}

	@Override
	// 维护门店信息
	public String addStore(Map<String, Object> storeInfo, MultipartFile file,HttpServletRequest request) throws IOException {
		//获取当前登录的信息
		Account ac = (Account)request.getSession().getAttribute("userSession");
		//获取当前登录的用户账号
		String userName = ac.getAccountName();
		
		// 图片的路径
		String imgPath = null;
		// FTP服务器地址
		String host = null;
		// FTP端口号
		String port = null;
		// FTP账号
		String username = null;
		// FTP密码
		String password = null;
		// FTP上的文件名
		String remoteFileName = null;

		InputStream localInputStream = null;
		// 文件流
		try {
			localInputStream = file.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String uploadAddress = "upload/store_title";
		// 获取上传文件的文件名
		String fileName = file.getOriginalFilename();
		// 判断是否是png或者是jpg
		boolean isimg = fileName.matches("^.+\\.(?i)((jpg)|(png))$");
		// 判断是否是xls或者是xlsx
		boolean isExcel = fileName.matches("^.+\\.(?i)((xls)|(xlsx))$");
		// 方法返回值
		String info = "";
		int failCount = 0;
		
		if( storeInfo.get("channel_code") != null  && !this.checkOne(storeInfo)){
			String store_address = storeInfo.get("store_address").toString();
	        System.err.println(store_address);
	       
	        if(EncodingUtil.isMessyCode(store_address)){
	        	store_address = EncodingUtil.strISO8859toUTF8(store_address);
			}
	        Map<String, Object> mapa1 = CourierNumber.Getcontext2(store_address);
	        storeInfo.put("store_address", store_address);
	        if("no".equals(mapa1.get("state").toString())){
	        	info = "门店信息维护失败：无法获取经纬度信息";
	        	return info;
	        }else{
	        	 storeInfo.put("store_longitude", mapa1.get("lng"));
	             storeInfo.put("store_latitude", mapa1.get("lat"));
	             storeInfo.put("area", mapa1.get("area"));
	        }
			storeInfo.put("description", store_address);
			storeInfo.put("section_name", store_address);
			// 如果是图片
			if (isimg) {
				// FTP上的文件
				if (fileName.matches("^.+\\.(?i)((jpg))$")) {
					remoteFileName = new Date().getTime() + UUID.randomUUID().toString() + ".jpg";
				} else {
					remoteFileName = new Date().getTime() + UUID.randomUUID().toString() + ".png";
				}
	
				// 将该图片信息上传到FTP服务器
				// 1.创建FTP工具类对象
				FTPLinuxUtils ftpLinuxUtils = new FTPLinuxUtils();
				// 2.获取FTP中所有参数
				InputStream resourceAsStream = BackStoreServiceImpl.class
						.getResourceAsStream("/resources/FTPConnect.properties");
				Properties p = new Properties();
				try {
					p.load(resourceAsStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
				host = p.getProperty("host");
				port = p.getProperty("port");
				username = p.getProperty("username");
				password = p.getProperty("password");
	            
				boolean upsuccess = ftpLinuxUtils.uploadFile(host, Integer.parseInt(port), username, password,
						remoteFileName, localInputStream, uploadAddress);
				System.out.println("+++++++++++++++++++");
				System.out.println(upsuccess);
				// 当文件成功上传至FTP服务器
				if (upsuccess) {
					// 拼接出来图片的路径
					imgPath = "http://" + host + "/" + uploadAddress + "/" + remoteFileName;
					storeInfo.put("store_img", imgPath);
					
					storeInfo.put("create_phone",userName);
					storeInfo.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					// 当进行维护单个店面信息成功后
					if (backStoreMapper.insertSigleStore(storeInfo) > 0&&backStoreMapper.addStoreAccount(storeInfo) > 0) {
						info = "门店信息维护成功";
					} else {
						info = "门店信息维护失败";
					}
				} 
			} else if(fileName == null || fileName == "") {
				storeInfo.put("create_phone",userName);
				storeInfo.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				if (backStoreMapper.insertSigleStore(storeInfo) > 0 &&backStoreMapper.addStoreAccount(storeInfo) > 0) {
					info = "门店信息维护成功";
				} else {
					info = "门店信息维护失败";
				}
			} 
		} else {
			info = "门店信息维护失败,请核实该门店是否已经存在";
		}
		// 如果上传的文件是excel
		if (isExcel) {
			// 判断文件名是否是03Excel
			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
			// 1、读取工作簿
			Workbook workbook = null;
			try {
				workbook = is03Excel ? new HSSFWorkbook(localInputStream) : new XSSFWorkbook(localInputStream);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 2、读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			// 3、读取行
			int sumRow = sheet.getPhysicalNumberOfRows();
			// 如果行数大于1
			if (sumRow > 1) {
				Map<String, Object> map = null;
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
				// 逐行读取excel数据
				for (int k = 1; k < sumRow; k++) {
					map = new HashMap<String, Object>();
					// 4.读取单元格的数据
					Row row = sheet.getRow(k);

					// 解析第一列（门店名称）
					String storeName = "";
					try {
						storeName = row.getCell(0).getStringCellValue();
					} catch (Exception e) {
						double dstoreName = row.getCell(0).getNumericCellValue();
						storeName = BigDecimal.valueOf(dstoreName).toPlainString();
					}
					map.put("store_name", storeName);

					// 解析第二列（门店地址）
					String storeAddress = "";
					try {
						storeAddress = row.getCell(1).getStringCellValue();
					} catch (Exception e) {
						double dstoreAddress = row.getCell(1).getNumericCellValue();
						storeAddress = BigDecimal.valueOf(dstoreAddress).toPlainString();
					}
					map.put("store_address", storeAddress);
					
					Map<String, Object> mapa = CourierNumber.Getcontext2(storeAddress);
					if("ok".equals(mapa.get("state").toString())){
						// 解析第三列（门店经度）
						String store_longitude = mapa.get("lng").toString();
						map.put("store_longitude", store_longitude);

						// 解析第四列（门店纬度）
						String store_latitude = mapa.get("lat").toString();
						map.put("store_latitude", store_latitude);
						map.put("area", mapa.get("area").toString());
						map.put("description", storeAddress);
						map.put("section_name", storeAddress);
					}else{
						//如果获取经纬度信息时出错时，则去获取下一条数据
						failCount++;
						continue  ;
					}
					

					// 解析第五列（门店店长电话）
					String storePhone = "";
					try {
						storePhone = row.getCell(2).getStringCellValue();
					} catch (Exception e) {
						double dstorePhone = row.getCell(2).getNumericCellValue();
						storePhone = BigDecimal.valueOf(dstorePhone).toPlainString();
					}
					map.put("store_shopowner_phone", storePhone);

					// 解析第六列（门店店长姓名）
					String storeOwnerName = "";
					try {
						storeOwnerName = row.getCell(3).getStringCellValue();
					} catch (Exception e) {
						double dstoreOwnerName = row.getCell(3).getNumericCellValue();
						storeOwnerName = BigDecimal.valueOf(dstoreOwnerName).toPlainString();
					}
					map.put("store_shopowner_name", storeOwnerName);

					// 解析第七列（门店渠道编码）
					String storeChannerCode = "";
					try {
						storeChannerCode = row.getCell(4).getStringCellValue();
					} catch (Exception e) {
						double dstoreChannerCode = row.getCell(4).getNumericCellValue();
						storeChannerCode = BigDecimal.valueOf(dstoreChannerCode).toPlainString();
					}
					map.put("channel_code", storeChannerCode);
					//如果渠道编码已经存在
					if(this.checkOne(map)) {
						failCount++;
						continue;
					}

					// 解析第八列（归属厂家）
					String vendor = "";
					try {
						vendor = row.getCell(5).getStringCellValue();
					} catch (Exception e) {
						double dvendor = row.getCell(5).getNumericCellValue();
						vendor = BigDecimal.valueOf(dvendor).toPlainString();
					}
					

					String vendorId = "";
					// 获取厂商的对应信息
					List<Map<String, Object>> vendorList = backStoreMapper.getVendor();
					// 根据用户填写的厂商信息获取对应的id
					for (Map<String, Object> map2 : vendorList) {
						// 根据名称循环匹配,返回对应的厂商id
						if (map2.get("vendor_name").equals(vendor)) {
							vendorId = map2.get("id").toString();
						}
					}
					map.put("vendor_id", vendorId);
					
					// 解析第九列（门店的类型）
					String storetype = "";
					try {
						storetype = row.getCell(6).getStringCellValue();
					} catch (Exception e) {
						double dstoretype = row.getCell(6).getNumericCellValue();
						storetype = BigDecimal.valueOf(dstoretype).toPlainString();
					}
					map.put("type", storetype);
					//添加创建人信息
					map.put("create_phone", userName);
					//添加创建时间
					map.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					
					list.add(map);
				}
				for (int i = 0; i < list.size(); i++) {
					Map<String,Object> mapAccount = new HashMap<String, Object>();
					Map<String,Object> maps = list.get(i);
					String store_shopowner_phone= maps.get("store_shopowner_phone").toString();
					String store_shopowner_name= maps.get("store_shopowner_name").toString();
					String store_name= maps.get("store_name").toString();
					String channel_code= maps.get("channel_code").toString();
					String create_time= maps.get("create_time").toString();
					List<Map<String, Object>> findPhoneList =backStoreMapper.findPhone(store_shopowner_phone);
					if(findPhoneList.size()>0){
						list1.clear();
					}else{
						mapAccount.put("store_shopowner_phone", store_shopowner_phone);
						mapAccount.put("store_shopowner_name", store_shopowner_name);
						mapAccount.put("store_name", store_name);
						mapAccount.put("channel_code", channel_code);
						mapAccount.put("createTime", create_time);
						list1.add(mapAccount);
					}
				}
				
				if(list1.size() > 0){
					 backStoreMapper.inserAccountList(list1);
				}
				// 将读取完成的信息插入维护到门店信息表中间表，用于比对重复数据
				if (backStoreMapper.inserStoreListTemp(list) > 0) {
					//当插入临时表成功后，对数据进行去自重
					List<Map<String, Object>> storeList1 = backStoreMapper.getStoreList1();
					//删除临时表的数据
					if (backStoreMapper.deleteStoreListTemp(userName) > 0 ){
						//将剔重过的数据导入的临时表
						if(backStoreMapper.inserStoreListTemp(storeList1) > 0){
							//拿到最终可以入主表的数据
							 List<Map<String, Object>> finalStoreList = backStoreMapper.selectfinalStoreList(userName);
							 if(finalStoreList.size() > 0){
								//将最终数据入库
								 backStoreMapper.inserStoreList(finalStoreList);
							 } else {
								 //清除临时表的数据
								 backStoreMapper.deleteStoreListTemp(userName);
								 info = "门店信息维护失败,没有信息符合规范，请核实归属厂商信息是否在库!";
								 return info;
							 }
						}
					}
					//清除临时表的数据
					backStoreMapper.deleteStoreListTemp(userName);
					System.err.println(failCount);
					if(failCount == 0){
						info = "门店信息维护成功！";
					} else{
						info = "门店信息维护成功，其中有"+failCount+"条数据由于渠道编码重复或获取经纬度信息失败导致维护失败!";
					}
					
				} else {
					//清除零时表的数据
					backStoreMapper.deleteStoreListTemp(userName);
					info = "门店信息维护失败,没有信息符合规范，请核实归属厂商信息是否在库！";
				}

			}

		}
		System.err.println(info);
		return info;

	}

	/**
	 * 根据id查询出门店信息
	 */
	@Override
	public Map<String, Object> getStoreInfoOfId(String id) {
		return backStoreMapper.getStoreInfoOfId(id);
	}

	/**
	 * 修改门店信息
	 */
	@Override
	public boolean modifyStore(Map storeInfo, MultipartFile file) {
		// 图片的路径
		String imgPath = null;
		// FTP服务器地址
		String host = null;
		// FTP端口号
		String port = null;
		// FTP账号
		String username = null;
		// FTP密码
		String password = null;
		// FTP上的文件名
		String remoteFileName = null;
		// 方法返回状态
		boolean flag = false;

		InputStream localInputStream = null;
		
		//if(!this.checkOne(storeInfo)){
		// 文件流
		try {
			localInputStream = file.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// FTP服务器上文件路径
		String uploadAddress = "upload/store_title";
		// 获取上传文件的文件名
		String fileName = file.getOriginalFilename();
		// 判断是否是png或者是jpg
		boolean isimg = fileName.matches("^.+\\.(?i)((jpg)|(png))$");
		// 如果是图片
		if (isimg || fileName == null || fileName == "") {
			if (fileName != null && fileName != "") {
				// FTP上的文件
				if (fileName.matches("^.+\\.(?i)((jpg))$")) {
					remoteFileName = new Date().getTime() + UUID.randomUUID().toString() + ".jpg";
				} else {
					remoteFileName = new Date().getTime() + UUID.randomUUID().toString() + ".png";
				}

				// 将该图片信息上传到FTP服务器
				// 1.创建FTP工具类对象
				FTPLinuxUtils ftpLinuxUtils = new FTPLinuxUtils();
				// 2.获取FTP中所有参数
				InputStream resourceAsStream = BackStoreServiceImpl.class
						.getResourceAsStream("/resources/FTPConnect.properties");
				Properties p = new Properties();
				try {
					p.load(resourceAsStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
				host = p.getProperty("host");
				port = p.getProperty("port");
				username = p.getProperty("username");
				password = p.getProperty("password");

				boolean upsuccess = ftpLinuxUtils.uploadFile(host, Integer.parseInt(port), username, password,
						remoteFileName, localInputStream, uploadAddress);
				System.out.println("+++++++++++++++++++");
				System.out.println(upsuccess);
				// 当文件成功上传至FTP服务器
				if (upsuccess) {
					// 拼接出来图片的路径
					imgPath = "http://" + host + "/" + uploadAddress + "/" + remoteFileName;
					storeInfo.put("store_img", imgPath);
					// 当进行维护单个店面信息成功后
					if (backStoreMapper.updateStoreInfo(storeInfo) > 0) {
						flag = true;
					} else {
						flag = false;
					}
				}
			} else {
				if (backStoreMapper.updateStoreInfo(storeInfo) > 0) {
					flag = true;
				} else {
					flag = false;
				}
			}
		}
//		} else {
//			flag = false;
//		}

		return flag;
	}
	
	/**
	 * 获取店面信息条数
	 */
	@Override
	public Integer getStoreCount() {

		return backStoreMapper.getStoreCount();
	}
	/**
	 * 删除店面信息
	 */
	@Override
	public boolean deleteStore(String id) {
		boolean flag = false;
		int num = 0;
		String[] split = id.split(",");
		for(int i = 0; i < split.length; i++ ){
		   num = backStoreMapper.deleteStore(split[i]); 
		}
		if(num > 0 ){
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	/**
	 * 查找重复信息
	 */
	@Override
	public  List<Map<String, Object>> findPhone(String store_shopowner_phone){
		return backStoreMapper.findPhone(store_shopowner_phone);
	}
	
	
	
	/**
	 * 验证当前门店信息是否存在，根据渠道编码确定唯一性
	 * @author 殷瑜泰 2017年4月10日下午2:03:41
	 *
	 * @param m
	 * @return
	 */
	public boolean checkOne(Map m){
		boolean flag = false;
		if(backStoreMapper.checkOne(m) > 0){
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 查询门店地址是否存在
	 * @author zhangzhi
	 * @date 2018年6月20日下午3:18:03
	 * @param
	 */
	@Override
	public List<Map<String, Object>> checkAddress(String store_address) {
		return backStoreMapper.checkAddress(store_address);
	}
}
