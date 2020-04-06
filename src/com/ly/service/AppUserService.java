package com.ly.service;

import java.util.List;
import java.util.Map;

import com.ly.entity.app.CodeInfo;
import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.service.vo.Apply;

public interface AppUserService {

	
	/**
	 * 检查用户登录
	 * @param username
	 * @param password
	 * @param token
	 * @return
	 */
	Account checkLogin(String username, String password, String token);

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	Account login(String username, String password);

	Account loginByToken(String username, String password, String token);

	Account changePassword(String username, String password1);
	
	boolean register(Account account);
	
	List<Group> selectCounty(String parentId);

	List<Account> getAll(String managerid);
	
	List<Account> getLngLat(String accountName);
	
	//查询手机号是否注册
	public boolean validatePhoneNum(String accountName);

	void setLngLat(String accountName,String longitude,String latitude);
	//查询省份证号是否注册
	public boolean isIdCarRegister(String idCar);

	//根据渠道编码查询渠道经理信息；
	public String getTell(String code);
	
	//员工提交转区申请
	public boolean apply(Apply apply,int fengongsiName);
	
	//根据员工编号及审核状态查询员工是否重复提交
	public boolean getApply(Apply apply);
	
	//模糊查询
	public List<CodeInfo> getInfo(int fengongsiNum,String codeName);
	
	//根据渠道编码查询所属地区
	public String getPianqu(String code);
	
	//重写后的登录
	List<Object> loginModify(String accountName, String password, String token);

	List<CodeInfo> getCodeInfo(int fengongsiNum, String codeName);
	
	Map<String, Object> findByUser(String accountName);

	Map<String, Object> findPidByGroupId(Integer groupId);

	List<CodeInfo> mohuchaxunByArea(int fengongsiNum);

	List<CodeInfo> getCodeInfoById(int fengongsiNum);
	
}
