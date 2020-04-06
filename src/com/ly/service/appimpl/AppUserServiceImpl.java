package com.ly.service.appimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.entity.app.CodeInfo;
import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.mapper.AccountMapper;
import com.ly.service.AccountService;
import com.ly.service.AppUserService;
import com.ly.service.GroupService;
import com.ly.service.vo.Apply;
import com.ly.util.Common;
import com.ly.util.Md5Tool;

/**
 * 
 * @Author 文军
 * @Date 2016年1月29日 下午2:59:51 
 * @E-mail wenjun_chen@sohu.com
 * @content user service
 *
 */
@Transactional
@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private GroupService groupService;
	
	@Override
	public Account checkLogin(String username, String password, String token) {
		Account user = accountMapper.checkLogin(username,password,token);
		if(user == null)
			return null;
		return user;
	}

	@Override
	public Account login(String username, String password) {
		if(Common.isEmpty(username) || Common.isEmpty(password)){
			System.out.println("用户名或者密码错误...");
			return null;
		}
		password=Md5Tool.getMd5(password);
		System.out.println(Md5Tool.getMd5(password));
		Account user = this.accountMapper.login(username,password);
		return user;
	}

	@Override
	public Account loginByToken(String username, String password, String token) {
		
		if(Common.isEmpty(username) || Common.isEmpty(password) || Common.isEmpty(token)){
			System.out.println("用户名或者密码错误，或设备唯一码为空...");
			return null;
		}
		
		Account user = this.accountMapper.loginByToken(username,password,token);
		if(user != null){
			return user;
		}else{
			return null;
		}
	}

	@Override
	public Account changePassword(String username, String password1) {
		Account user = null;
		int r = this.accountMapper.changePassword(Md5Tool.getMd5(password1),username);
		if(r > 0){
			user = this.accountMapper.login(username,Md5Tool.getMd5(password1));
		}
		return user;
	}

	@Override
	public boolean register(Account account) {
		List<Account> listA = accountMapper.validatePhoneNum(account.getAccountName());
		if(listA.size()>0){
			for(int i = 0;i<listA.size();i++){
				if(listA.get(i).getAuth_flag() != 3){
					return false;
				}
			}
			try {
				accountMapper.add(account);
				return true;
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}else{
			//通过渠道编码查询渠道经理手机号
			/*List<Account> list = accountMapper.getTell(account.getCode());
			if(list.size()<1){
				return true;
			}*/
			try {
				accountMapper.add(account);
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	}
	

	@Override
	public List<Group> selectCounty(String parentId) {
		List<Group> listG = accountMapper.selectCounty(parentId);
		return listG;
	}

	@Override
	public List<Account> getAll(String managerid) {
		
		List<Account> r =this.accountMapper.getAllManagerUsers(managerid);
		
		return r;
	}

	/**
	 * 查询当前用户经纬度
	 */
	@Override
	public List<Account> getLngLat(String accountName) {
		List<Account> listA = accountMapper.getLngLat(accountName);
		return listA;
	}

	/**
	 * 设置当前用户经纬度
	 */
	@Override
	public void setLngLat(String accountName, String longitude, String latitude) {
		accountMapper.setLngLat(accountName, longitude, latitude);
	}

	//查询身份证号是否已经注册
	@Override
	public boolean isIdCarRegister(String idCar) {
		List<Account> listA = accountMapper.getIdCar(idCar);
		if(listA.size()!=0) return true;
		else return false;
	}

	//根据渠道编码查询渠道经理手机号
	@Override
	public String getTell(String code) {
		List<Account> list = accountMapper.getTell(code);
		if(list.size()<1){
			return "";
		}else{
			return list.get(0).getAccountName();
		}
	}

	//员工提交申请
	@Override
	public boolean apply(Apply apply,int fengongsiName) {
		List<Account> listO = accountMapper.getTell(apply.getOldCode());
		
		String fengongsi = (String) accountService.getFengongsiInfo(fengongsiName).get(0).get("fengongsi");
		
		String newConductorTell = accountService.getAreaAdministratorTell(fengongsi).get(0).get("phone").toString();
		String newConductorName = accountService.getAreaAdministratorTell(fengongsi).get(0).get("userName").toString();
		apply.setOldConductorName(listO.get(0).getUserName());
		apply.setOldGroupId(listO.get(0).getGroupId());
		apply.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		apply.setNewConductorName(newConductorName);
		apply.setNewConductorTell(newConductorTell);
		apply.setNewGroupId(groupService.getChildId(Long.valueOf(groupService.getNameToInfo(fengongsi).get(0).getId())).get(0).intValue());
		
		
		try {
			accountMapper.apply(apply);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	//根据员工编号及审核状态查询员工是否重复提交
	@Override
	public boolean getApply(Apply apply) {
		if(accountMapper.getApply(apply).size()>0){
			return true;
		}else{
			return false;
		}
	}

	//模糊查询
	@Override
	public List<CodeInfo> getInfo(int fengongsiNum,String codeName) {
		return accountMapper.getInfo(fengongsiNum,codeName);
	}

	//查询身份证号是否已经注册
	@Override
	public boolean validatePhoneNum(String accountName) {
		List<Account> listA = accountMapper.validatePhoneNum(accountName);
		if(listA.size()!=0) return true;
		else return false;
	}

	@Override
	public String getPianqu(String code) {
		List<Account> list = accountMapper.getTell(code);
		String tell= "";
		switch(list.get(0).getArea()){
		case "高新":
			tell = "13991870872";
			break;
		case "金花":
			tell = "13772141670";
			break;
		case "小寨":
			tell = "13636715803";
			break;
		case "莲湖":
			tell = "13609249551";
			break;
		case "未央":
			tell = "13572969363";
			break;
		case "营业厅中心":
			tell = "13720539261";
			break;
		default:
			tell = "15902922626";
		}
		return tell;
	}

	/**
	 * 重写后的登录
	 */
	@Override
	public List<Object> loginModify(String accountName,String password,String token) {
		List<Account> listU = this.accountMapper.loginModify(accountName);
		List<Object> flag = new ArrayList<Object>();
		if(listU.size()<1){
			flag.add(0);
			flag.add("您好，您的手机号尚未注册，请先注册再登录。");
		}else if(listU.size()>1){
			flag.add(0);
			flag.add("您好，您的账号异常，请联系管理员");
		}else if(listU.get(0).getAuth_flag() == 1){
			flag.add(0);
			flag.add("您好，您的账号正在审核中，请耐心等待。");
		}else if(!Md5Tool.getMd5(password).equals(listU.get(0).getPassword())){
			flag.add(0);
			flag.add("您好，您输入的账号或密码有误，请核对后再次输入。");
		}else{
			this.accountMapper.updateLogin(accountName,token);
			flag.add(1);
			flag.add(listU.get(0));
		}
		return flag;
	}

	@Override
	public List<CodeInfo> getCodeInfo(int fengongsiNum, String codeName) {
		return accountMapper.getCodeInfo(fengongsiNum,codeName);
	}

	@Override
	public Map<String, Object> findByUser(String accountName) {
		return accountMapper.findByUser(accountName);
	}

	@Override
	public Map<String, Object> findPidByGroupId(Integer groupId) {
		
		return accountMapper.findPidByGroupId(groupId);
	}

	@Override
	public List<CodeInfo> mohuchaxunByArea(int fengongsiNum) {
		return accountMapper.mohuchaxunByArea(fengongsiNum);
	}

	@Override
	public List<CodeInfo> getCodeInfoById(int fengongsiNum) {
		return accountMapper.getCodeInfoById(fengongsiNum);
	}

}
