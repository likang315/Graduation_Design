package com.ly.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.background.Account;
import com.ly.entity.background.Resources;
import com.ly.service.AccountService;
import com.ly.util.Common;
import com.ly.util.Md5Tool;
import com.ly.util.POIUtils;

@Controller
@RequestMapping("/background/account/")
public class AccountController extends BaseController {
	@Inject
	private AccountService accountService;

	@RequestMapping("list")
	public String list(Model model, Resources menu, String pageNow) {
		return Common.BACKGROUND_PATH + "/account/list";
	}

	/**
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping("query")
	public String query(Model model, Account account, String pageNow,
			String pagesize, HttpServletRequest request) {
		Account a = getAccount(request);
		account.setGroupId(a.getGroupId());

		pageView = accountService
				.query(getPageView(pageNow, pagesize), account);
		model.addAttribute("pageView", pageView);
		return Common.BACKGROUND_PATH + "/account/userlist";
	}

	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletResponse response, Account account) {
		List<Account> acs = accountService.queryAll(account);
		POIUtils.exportToExcel(response, "账号报表", acs, Account.class, "账号",
				acs.size());
	}

	/**
	 * 跑到新增界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI() {
		return Common.BACKGROUND_PATH + "/account/useradd";
	}
	/**
	 * 保存数据
	 * 
	 * @param model
	 * @param videoType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String add(Account account,Date CreateTime) {
		try {
			account.setPassword(Md5Tool.getMd5(account.getPassword()));
			CreateTime=new Date();
			account.setCreateTime(CreateTime);
			account.setAccountType("3");
			account.setAuth_flag(2);
			account.setState("1");
			account.setReal_name(account.getReal_name());
			account.setId_car(account.getId_car());
			account.setSection_name(account.getSection_name());
			account.setEmployeesClass(0);
			account.setEmployeesType(0);
			accountService.add(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:query.html";
	}

	/**
	 * 账号角色页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("accRole")
	public String accRole(Model model, String accountName, String roleName) {

		try {
			accountName = java.net.URLDecoder.decode(accountName, "UTF-8");
			roleName = java.net.URLDecoder.decode(roleName, "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		model.addAttribute("accountName", accountName);
		model.addAttribute("roleName", roleName);

		return Common.BACKGROUND_PATH + "/account/acc_role";
	}

	/**
	 * 跑到新增界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model, String accountId) {
		Account account = accountService.getById(accountId);
		model.addAttribute("account", account);
		return Common.BACKGROUND_PATH + "/account/usermodify";
	}

	/**
	 * 验证账号是否存在
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String name) {
		Account account = accountService.isExist(name);
		if (account == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @param videoTypeId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteById")
	public Map<String, Object> deleteById(Model model, String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id[] = ids.split(",");
			for (String string : id) {
				if (!Common.isEmpty(string)) {
					accountService.delete(string);
				}
			}
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @param videoTypeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteAll")
	public String deleteAll(String[] check, Model model) {
		try {
			for (String string : check) {
				if (!Common.isEmpty(string)) {
					accountService.delete(string);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:query.html";
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @param videoTypeId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("updateState")
	public Map<String, Object> updateState(Model model, String ids, String state) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id[] = ids.split(",");
			for (String string : id) {
				if (!Common.isEmpty(string)) {
					Account account = new Account();
					account.setId(Integer.parseInt(string));
					account.setState(state);
					accountService.update(account);
				}
			}
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}

	/**
	 * 更新类型
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("update")
	public String update(Model model, Account account) {
		try {
			account.setPassword(Md5Tool.getMd5(account.getPassword()));
			accountService.update(account);
		} catch (Exception e) {
		}
		return "redirect:query.html";
	}
	
	@RequestMapping("modifyPass")
	public String modifyPass(String userId,String newpassword){
		Account ac=new Account();
		ac.setId(Integer.parseInt(userId));
		ac.setPassword(Md5Tool.getMd5(newpassword));
		try {
			accountService.update(ac);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:query.html";
	}

	@RequestMapping("modifyPassword")
	public String modifyPassword(Model model, String userId) {
		model.addAttribute("userId", userId);
		return Common.BACKGROUND_PATH + "/account/modifyPassword";
	}
	
}