package com.ly.mapper;

import java.util.List;

import com.ly.base.BaseMapper;
import com.ly.entity.app.Smslog;
import com.ly.entity.background.SmsSendlog;

public interface SmslogMapper extends BaseMapper<Smslog> {

	public List<Smslog> smsValidate(String deviceId, String code);
	
	public int addSmsSendLog(SmsSendlog sendlog);

	public void addSmslog(Smslog smslog);

}
