package com.ly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.entity.background.Account;
import com.ly.mapper.LoginMapper;
import com.ly.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;

	
	@Override
	public Map<String, Object> UserLogin(Account account) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("state", "no");
		
		List<Account> Luser = loginMapper.querySingleAccount(account.getAccountName());
		if(Luser.size() != 0){
			Luser = loginMapper.UserLogin(account);
			switch (Luser.size()) {
			case 0:
				result.put("info","密码错误");
				break;
			case 1:
				if(Luser.get(0).getAuth_flag() == 0)
					result.put("info","账号审核中");
				else{
					result.put("state","ok");
					Account account1 = Luser.get(0);
					account1.setCreateTime(null);
					result.put("userInfo",account1);
					result.put("info","登陆成功");
				}
				break;
			default:
				result.put("info","账号异常，请联系管理员");
				break;
			}
		}else{
			result.put("info","该账号尚未注册");
		}
		
		return result;
	}


	@Override
	public int userRegister(Account account) {
		return loginMapper.userRegister(account);
	}

}
