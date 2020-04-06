package com.ly.mapper;

import java.util.List;
import java.util.Map;

import com.ly.entity.Smslog;
import com.ly.entity.app.WeixinBinding;
import com.ly.entity.background.Account;

public interface WeixinMapper {

	WeixinBinding getWeixinInfoUserTell(String userPhone);

	List<Account> loginAgain(String username);

	void delete(String username);

	int add(WeixinBinding weixinBinding);

	List<Smslog> testCode(Map<String, Object> map);

	void addSmslog(Smslog smslog);

	int weixinDelete(String phone);

	List<Account> loginAgainInWeixinBinDing(Map<String,Object> map);

	void updateLogin(Map<String, Object> map);
	
}
