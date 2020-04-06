package com.ly.service;

import java.util.List;

import com.ly.entity.Smslog;
import com.ly.entity.app.WeixinBinding;
import com.ly.entity.background.Account;

public interface WeixinService {

	WeixinBinding getWeixinInfoUserTell(String userPhone);

	void delete(String username);

	int add(WeixinBinding weixinBinding);

	List<Smslog> testCode(String tellPhone, String code);

	void addSmslog(Smslog smslog);

	int weixinDelete(String phone);

	List<Account> loginAgainInWeixinBinDing(String username, String password);

	void updateLogin(String username, String token);


}
