package com.ly.service;

import java.util.List;
import java.util.Map;

import com.ly.base.BaseService;
import com.ly.entity.background.Account;
import com.ly.pulgin.mybatis.plugin.PageView;


/**
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-01 11:05
 */
public interface AccountService extends BaseService<Account> {

    Account querySingleAccount(String accountName);

    Account isExist(String accountName);

    Account countAccount(Account account);

    /**
     * @param account
     * @param pageView
     * @return
     */
    PageView queryNoMatch(Account account, PageView pageView);

    List<Map<String, Object>> getFengongsiInfo(int fengongsiNum);

    List<Map<String, Object>> getAreaAdministratorTell(String area);

    //验证验证码是否正确(没有超时限制)
    boolean testCode(String tellPhone, String code);

    //验证验证码是否正确(有超时限制)
    boolean testCode2(String tellPhone, String code);

    //修改密码
    boolean updatePassword(String tellPhone, String password);


}
