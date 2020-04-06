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

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ly.entity.app.MailInformation;
import com.ly.entity.background.Account;
import com.ly.mapper.LogisticsOrderMapper;
import com.ly.service.LogisticsOrderService;
import com.ly.util.Common;

@Service
public class LogisticsOrderServiceImpl implements LogisticsOrderService{

	@Autowired
	private LogisticsOrderMapper logisticsOrderMapper;

	@Override
	public List<Map<String, Object>> findOrderlist(Map<String, Object> map) {
		return logisticsOrderMapper.findOrderlist(map);
	}

	@Override
	public int findOrdercount(Map<String, Object> m) {
		return logisticsOrderMapper.findOrdercount(m);
	}

	// 根据电话搜索
			@Override
			public List<Map<String, Object>> getSearchPhone(Map<String, Object> map) {
				return logisticsOrderMapper.getSearchPhone(map);
			}

			@Override
			public int getSearchCount(Map<String, Object> m) {
				return logisticsOrderMapper.getSearchCount(m);
			}
	
			//批量删除
			@Override
			public boolean deleteLogistics(Map<String, Object> map) {
				try {
					logisticsOrderMapper.deleteLogistics(map);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
			}
			//批量配送
			@Override
			public boolean distribution(Map<String, Object> map) {
				try {
					logisticsOrderMapper.distribution(map);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
			}

			@Override
			public Map<String, Object> getDisplayMap(Map<String, Object> map) {
				return logisticsOrderMapper.getDisplayMap(map);
				
			}
			
			
			@Override
			public Map<String, Object> getDisplayMap1(Map<String, Object> map) {
				return logisticsOrderMapper.getDisplayMap1(map);
			}

			@Override
			public Map<String, Object> findByLogistics(String id) {
				return logisticsOrderMapper.findByLogistics(id);
			}

			@Override
			public String updateLogistics(Map m) {
			String info = "";
			if(logisticsOrderMapper.updateLogistics(m) > 0){
				//更新成功 
				info = "订单信息更新成功";
			} else {
				info = "订单信息更新失败";
			}
			return info;
			}

			@Override
			public List<Map<String, Object>> findlogisticslist() {
				return logisticsOrderMapper.findlogisticslist();
			}
			
			/**
			 * 订单维护
			 */
			@Override
			public Map addOrder(MultipartFile file, HttpServletRequest request) {
				//信息回显map
				Map<String,Object> infoMap = new HashMap<String,Object>();
				
				String fileName = "";
				//因为快递信息不匹配计数初始化
				int failOfCourier = 0;
				//因为快递公司的渠道编码不匹配计数初始化
				int failOfQDBM  = 0;
				
				InputStream InputStream = null;
				//获取上传文件的文件名称
				fileName = file.getOriginalFilename();
				// 判断是否是xls或者是xlsx
				boolean isExcel = fileName.matches("^.+\\.(?i)((xls)|(xlsx))$");
				//上传的文件以流的方式
				try {
					InputStream = file.getInputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isExcel){
					
				// 判断文件名是否是03Excel
				boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
				// 1、读取工作簿
				Workbook workbook = null;
				try {
					workbook = is03Excel ? new HSSFWorkbook(InputStream) : new XSSFWorkbook(InputStream);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// 2、读取工作表(第一个sheet页)
				Sheet sheet = workbook.getSheetAt(0);
				// 3、读取行
				int sumRow = sheet.getPhysicalNumberOfRows();
				// 如果行数大于2
				if (sumRow > 2) {
					//4、读取到总列数
					int sumColumn = sheet.getRow(1).getPhysicalNumberOfCells();
					//获取Row对象
					Row row = null;
					//从session中获取到用户的信息
					Account ac = (Account)request.getSession().getAttribute("userSession");
					
					//获取发货人电话
					String shipper = ac.getAccountName();
					//获取发货人的姓名
					String shipperName = ac.getReal_name();
					//获取发货人的组织id
					int groupId = ac.getGroupId();
					

					//用于存放每一行数据
					Map<String, Object> map = null;
					//存放excel的数据
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					
					
					//解析物资信息(物资的名称)
 					StringBuffer materialContent = new StringBuffer();
					String materialContent1 = "";
					for(int j = 13; j < sumColumn; j ++ ){
						try {
							//获取物资的名称
							materialContent1 = sheet.getRow(1).getCell(j).getStringCellValue();
							} catch (Exception e) {
								double dmaterialContent1 = row.getCell(1).getNumericCellValue();
								materialContent1 = BigDecimal.valueOf(dmaterialContent1).toString();
							}
							materialContent.append(materialContent1 + ",");
						}
					// 逐行读取excel数据
					for (int k = 2; k < sumRow; k++) {
						map = new HashMap<String, Object>();
							// 4.读取单元格的数据
							 row = sheet.getRow(k);
							 
							//物资名称维护入map
							 map.put("materialContent", materialContent.toString());
							//发货人电话维护人map
							 map.put("shipper", shipper);
							 //发货人的姓名维护入map
							 map.put("shipperName", shipperName); 
							//发货人的组织id
							 map.put("groupId", groupId);
							 //订单生成时间
							map.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							 
							// 解析第一列（渠道编码信息）
							String channel_code = "";
							try {
								channel_code = row.getCell(0).getStringCellValue();
							} catch (Exception e) {
								double dchannel_code = row.getCell(0).getNumericCellValue();
								channel_code = BigDecimal.valueOf(dchannel_code).toString();
							}
							//根据渠道编码信息查询出店面渠道是否存在是否存在
							if(logisticsOrderMapper.getChannel(channel_code) < 1) {
								//由于当前渠道不在库，不执行插入操作，计数器+1；
								failOfQDBM = failOfQDBM + 1;
								//结束当次行的解析
								continue;
							}
							//渠道编码维护入map
							map.put("channel_code", channel_code);
							
							
							
							// 解析第三列（收货门店名称）
							String store = "";
							try {
								store = row.getCell(2).getStringCellValue();
							} catch (Exception e) {
								double dstore = row.getCell(2).getNumericCellValue();
								store = BigDecimal.valueOf(dstore).toString();
							}
							//收货门店名称维护入map
							map.put("store", store);
							
							// 解析第五列（门店归属品牌）
							String brand = "";
							try {
								brand = row.getCell(4).getStringCellValue();
							} catch (Exception e) {
								double dbrand = row.getCell(4).getNumericCellValue();
								brand = BigDecimal.valueOf(dbrand).toString();
							}
							//将门店归属品牌维护入map
							map.put("brand", brand);
							
							
							//解析第九列信息（收货地址）
							String address = "";
							try {
								address = row.getCell(8).getStringCellValue();
							} catch (Exception e) {
								double daddress = row.getCell(8).getNumericCellValue();
								address = BigDecimal.valueOf(daddress).toString();
							}
							
							//收货地址维护入map
							map.put("address", address);
							
							
							//解析第十列信息（店长姓名）
							String consignee = "";
							try {
								consignee = row.getCell(9).getStringCellValue();
							} catch (Exception e) {
								double dconsignee = row.getCell(9).getNumericCellValue();
								consignee = BigDecimal.valueOf(dconsignee).toString();
							}
							//收货人电话维护入map
							map.put("consignee", consignee);
							
							//解析第十一列数据（店长电话）
							String phone = "";
							try {
								phone = row.getCell(10).getStringCellValue();
							} catch (Exception e) {
								double dphone = row.getCell(10).getNumericCellValue();
								phone = BigDecimal.valueOf(dphone).toString();
							}
							//店长电话维护到map
							map.put("phone", phone);
							
							//解析第十三列数据（快递员电话）
							String  courierPhone = "";
							try {
								courierPhone = row.getCell(12).getStringCellValue();
							} catch (Exception e) {
								double dcourierPhone = row.getCell(12).getNumericCellValue();
								courierPhone = BigDecimal.valueOf(dcourierPhone).toString();
							}
							//店长电话维护到map
							map.put("courierPhone", courierPhone);
							
							//根据快递员的话信息查询快递员姓名
							Map<String, Object> courierMap = logisticsOrderMapper.getcourierName(courierPhone);
							 if(courierMap != null){
								 //如果数量大于 0 的话有当前用户
								 String courierName = (String) courierMap.get("real_name");
								 map.put("courierName", courierName);
							 } else {
								 //由于当前快递员不在库，不执行插入操作，计数器+1;
								 failOfCourier = failOfCourier + 1;
								 //快递员信息不存在，结束当条数据维护，
								 continue;
							 }
							//解析物资的数量
							StringBuffer materialNumber = new StringBuffer();
							String materialNumber1 = "";
							for(int p = 13; p < sumColumn; p++){
								try {
									materialNumber1 = row.getCell(p).getStringCellValue();
								} catch (Exception e) {
									if(!"".equals(row.getCell(p)) && row.getCell(p) != null){
										double dmaterialNumber1 = row.getCell(p).getNumericCellValue();
										materialNumber1 = BigDecimal.valueOf(dmaterialNumber1).toString();
									}else{
										//不赋值会解析错误
										materialNumber1="0";
									}
									/**
									 * 修改时间：2017年11月22日 15:53
									 * 修改原因：解决空指针的问题以及物资数量解析错误的问题
									 */
									/*double dmaterialNumber1 = row.getCell(p).getNumericCellValue();
									materialNumber1 = BigDecimal.valueOf(dmaterialNumber1).toString();*/
									
								}
								materialNumber.append(materialNumber1 + ",");
							}
							//物资数量信息维护入map
							map.put("materialNumber", materialNumber.toString());
							
							//预计邮寄时间默认2-3小时维护入map
							map.put("predictTime", "2~3");
							
							//订单的信息维护入map
							map.put("id", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + Common.getRandom(3, true, false, false, false, 1).get(0));
							
							//就将读取的单条订单的信息，放入list
							list.add(map);
						}
					//读取完毕后，将excel的信息插入到订单表中
					if(list.size() > 0 ){
						Integer s = logisticsOrderMapper.addOrderInfo(list);
						int counts = failOfCourier + failOfQDBM;
						if(s > 0){
							infoMap.put("addInfo", "成功维护信息"+ s +"条！其中有"+ counts +"条信息由于快递员信息或者门店信息不存在维护失败");
						} else {
							infoMap.put("addInfo", "信息维护失败，请核实模板或数据是否符合规范！");
						} 	
					} else {
						infoMap.put("addInfo", "信息维护失败，请核实模板或数据是否符合规范！");
					}
					
				}
				} else {
					//若果上传的文件不是excel
					infoMap.put("addInfo", "请核实上传文件格式是否符合规范");
				}
				return infoMap;	
				
			}
			/**
			 * 查询快递员信息是否存在
			 */
			@Override
			public String checkcourierPhoneService(String phone) {
				String info = "";
				if(logisticsOrderMapper.checkcourierPhoneService(phone) > 0){
					info = "ok";
				} else {
					info = "快递员信息不存在请核实!";
				}
				return info;
			}

			@Override
			public Integer findOrderOfNullNum() {
				return logisticsOrderMapper.findOrderOfNullNum();
			}

			@Override
			public List<Map<String, Object>> findOrderOfNulllist(Map<String, Object> map) {
				
				return logisticsOrderMapper.findOrderOfNulllist(map);
			}

			@Override
			public List<Map<String, Object>> getCompany() {
				
				return logisticsOrderMapper.getCompany();
			}

			@Override
			public List<Map<String, Object>> getCourierLs(String[] companyId) {
				
				return logisticsOrderMapper.getCourierLs(companyId);
			}

			@Override
			public Map<String, Object> sendOrder(String orderName, String[] seleCourier) {
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("orderInfo", "维护异常！");
				//转数组
				String[] orderId = orderName.split(",");
				if(orderId.length > 0){
				//遍历订单
				for(int i=0; i<orderId.length; i++ ){
					//创建map
					Map<String,Object> map = new HashMap<String, Object>();
					//获取订单数量最少的快递员
					List<Map<String,Object>> orderNum = logisticsOrderMapper.getMixOrderNum(seleCourier);
					//设置订单id
					map.put("id", orderId[i]);
					//为订单设置快递员电话
					map.put("courierPhone", orderNum.get(0).get("phone"));
					//添加发货时间
					map.put("shipTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					//为订单设置快递员姓名
					map.put("courierName", logisticsOrderMapper.getCourierName((String)orderNum.get(0).get("phone")));
					//将匹配完成的订单进行配送操作
					 logisticsOrderMapper.finishOrder(map);
					 m.put("orderInfo", "派送成功！");
				}
				} else {
					m.put("orderInfo", "未获取到需要维护的订单信息！");
				}
				
				
				return m;
			}
}