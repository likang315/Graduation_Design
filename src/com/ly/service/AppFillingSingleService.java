package com.ly.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import com.ly.entity.app.MailInformation;
import com.ly.entity.app.Store;
import com.ly.entity.app.Vendor;

public interface AppFillingSingleService {

	List<Vendor> getVendor();

	List<Map<String, Object>> getMaterial();

	List<Store> getStoreById(String vendor_id, String type);

	List<Store> getStoreByMohuchaxun(String vendor,String mohuchaxun, String type);

	List<Store> getStoreAll();

	List<MailInformation> getMailInfor(String accountName);

	List<Map<String, Object>> getCompany();

	List<Map<String, Object>> getCourier(String expressId, String condition);

	Map<String, Object> putInMaterialInfo(String courierPhones, String accountPhone, HttpSession session);

	List<Map<String, Object>> findByList(String userPhone, String searchMsg, String orderState, String startTime, String endTime, Integer size);

	Map<String, Object> getOrderDetail(String orderNumber);

	List<Map<String, Object>> getMarketingOrderTotal(String userPhone, String startTime, String endTime);

	Integer getByListCount(String userPhone, String searchMsg, String orderState, String startTime, String endTime);
	
	//根据时间筛选获取到报表所需要数据
	List getReportDatas(String startTime, String endTime);
	
}
