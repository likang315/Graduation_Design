package com.ly.service;

import java.util.List;

import com.ly.base.BaseService;
import com.ly.entity.app.Smslog;
import com.ly.entity.background.SmsSendlog;


/**
 * 短信验证
 * @author wenjun
 *
 */
public interface SmslogService extends BaseService<Smslog>{

	/**
	 * 短信验证
	 * @param deviceId 设备id
	 * @param code 验证码
	 * @return
	 */
	List<Smslog> smsValidate(String deviceId, String code);
	
	public int addSmsSendLog(SmsSendlog sendlog);

	/**
	 * 发短信
	 * @author zhangzhi
	 * @date 2018年5月8日下午3:39:29
	 * @param
	 */
	void addSmslog(Smslog smslog);

}
