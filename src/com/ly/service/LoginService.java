package com.ly.service;

import java.util.Map;

import com.ly.entity.background.Account;

/**
 * 登录
 * @author wf
 *
 */
public interface LoginService {

	public Map<String,Object> UserLogin(Account account);

	public int userRegister(Account account);
}
