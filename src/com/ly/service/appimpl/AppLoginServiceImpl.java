package com.ly.service.appimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.mapper.AppLoginMapper;
import com.ly.service.AppLoginService;
import com.ly.util.Md5Tool;

@Service
public class AppLoginServiceImpl implements AppLoginService {

	@Autowired
	private AppLoginMapper appLoginMapper; 
	
	@Override
	public Map<String, Object> login(Account account) {
		Map<String,Object> result = new HashMap<String,Object>();
		account.setPassword(Md5Tool.getMd5(account.getPassword()));
		List<Account> la = appLoginMapper.login(account);
		switch (la.size()) {
		case 0:
			result.put("info","账号或密码错误！");
			result.put("type",0);
			break;
		case 1:
			if(la.get(0).getAuth_flag() == 1){
				result.put("info","账号审核中");
				result.put("type",3);
			}else{
				result.put("info",la.get(0));
				result.put("type",1);
			}
			break;

		default:
			result.put("info","账号异常！");
			result.put("type",2);
			break;
		}
		return result;
	}

	@Override
	public List<Group> getSectionName() {
		return appLoginMapper.getSectionName();
	}

	@Override
	public int add(Account account) {
		return appLoginMapper.add(account);
	}

	@Override
	public int getZhiJuId(int groupId) {
		return appLoginMapper.getZhiJuId(groupId);
	}

	@Override
	public int getQuDaoId(int idZJ) {
		return appLoginMapper.getQuDaoId(idZJ);
	}

	@Override
	public int getManager_id(int idQD) {
		return appLoginMapper.getManager_id(idQD);
	}

	@Override
	public List<Account> getUserInfo(String accountName) {
		return appLoginMapper.getUserInfo(accountName);
	}

	@Override
	public Map<String, Object> getBycompanyId(String companyId) {
		return appLoginMapper.getBycompanyId(companyId);
	}


}
