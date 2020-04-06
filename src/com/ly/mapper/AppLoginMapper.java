package com.ly.mapper;

import java.util.List;
import java.util.Map;

import com.ly.entity.background.Account;
import com.ly.entity.background.Group;

public interface AppLoginMapper {
	
	List<Account> login(Account account);

	List<Group> getSectionName();

	int add(Account account);

	int getZhiJuId(int groupId);

	int getQuDaoId(int idZJ);

	int getManager_id(int idQD);

	List<Account> getUserInfo(String accountName);

	Map<String, Object> getBycompanyId(String companyId);
}
