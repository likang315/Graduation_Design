package com.ly.service;

import java.util.List;
import java.util.Map;

import com.ly.entity.background.Account;
import com.ly.entity.background.Group;

/**
 * app 前台初始化界面控制器
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-021 11:21
 */
public interface AppLoginService {

	Map<String,Object> login(Account account);

	List<Group> getSectionName();

	int add(Account account);

	int getZhiJuId(int groupId);

	int getQuDaoId(int idZJ);

	int getManager_id(int idQD);

	List<Account> getUserInfo(String accountName);

	Map<String, Object> getBycompanyId(String companyId);

}
