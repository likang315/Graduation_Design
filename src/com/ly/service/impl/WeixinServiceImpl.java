package com.ly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.entity.Smslog;
import com.ly.entity.app.WeixinBinding;
import com.ly.entity.background.Account;
import com.ly.mapper.WeixinMapper;
import com.ly.service.WeixinService;

@Transactional
@Service("weixinService")
public class WeixinServiceImpl implements WeixinService {

	@Autowired
	private WeixinMapper weixinMapper;

	@Override
	public WeixinBinding getWeixinInfoUserTell(String userPhone) {
		return weixinMapper.getWeixinInfoUserTell(userPhone);
	}

	@Override
	public void delete(String username) {
		weixinMapper.delete(username);
	}

	@Override
	public int add(WeixinBinding weixinBinding) {
		return weixinMapper.add(weixinBinding);
	}

	@Override
	public List<Account> loginAgainInWeixinBinDing(String username, String password) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("accountName", username);
		map.put("password", password);
		return this.weixinMapper.loginAgainInWeixinBinDing(map);
	
	}

	@Override
	public void updateLogin(String username, String token) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("accountName", username);
		map.put("token", token);
		this.weixinMapper.updateLogin(map);
	}

}
