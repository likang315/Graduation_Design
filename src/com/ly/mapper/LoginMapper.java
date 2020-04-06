package com.ly.mapper;

import java.util.List;

import com.ly.entity.background.Account;

public interface LoginMapper {
	
	public List<Account> UserLogin(Account account);
	
	public List<Account> querySingleAccount(String accountName);

	public int userRegister(Account account);
	
}
