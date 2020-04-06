package com.ly.service.appimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.entity.app.Smslog;
import com.ly.entity.background.SmsSendlog;
import com.ly.mapper.SmslogMapper;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.SmslogService;

@Service
@Transactional
public class SmslogServiceImpl implements SmslogService {

	@Autowired
	private SmslogMapper smslogMapper;

	@Override
	public PageView query(PageView pageView, Smslog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Smslog> queryAll(Smslog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Smslog t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Smslog getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Smslog t) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 验证手机是否注册
	 */
	@Override
	public List<Smslog> smsValidate(String deviceId, String code) {
		List<Smslog> sms = this.smslogMapper.smsValidate(deviceId, code);
		return sms;
	}

	@Override
	public int addSmsSendLog(SmsSendlog sendlog) {
		return smslogMapper.addSmsSendLog(sendlog);
	}

	@Override
	public void addSmslog(Smslog smslog) {
		smslogMapper.addSmslog(smslog);
	}

}
