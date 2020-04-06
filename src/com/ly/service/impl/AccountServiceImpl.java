package com.ly.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.entity.background.Account;
import com.ly.mapper.AccountMapper;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.AccountService;
import com.ly.util.Md5Tool;

/**
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-04 11:05
 */
@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public PageView query(PageView pageView, Account account) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", account);
        List<Account> list = accountMapper.query(map);
        pageView.setRecords(list);
        return pageView;
    }


    public PageView queryNoMatch(Account account, PageView pageView) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", account);
        List<Account> list = accountMapper.queryNoMatch(map);
        pageView.setRecords(list);
        return pageView;
    }

    public List<Account> queryAll(Account t) {
        return accountMapper.queryAll(t);
    }

    public void delete(String id) throws Exception {
        accountMapper.delete(id);
    }

    public void update(Account t) throws Exception {
        accountMapper.update(t);
    }

    public Account getById(String id) {
        return accountMapper.getById(id);
    }


    public void add(Account t) throws Exception {
        accountMapper.add(t);
    }

    @Override
    public Account querySingleAccount(String accountName) {
        return accountMapper.querySingleAccount(accountName);
    }


    public Account isExist(String accountName) {
        return accountMapper.isExist(accountName);
    }


    public Account countAccount(Account account) {
        return accountMapper.countAccount(account);
    }

    @Override
    public List<Map<String, Object>> getFengongsiInfo(int fengongsiNum) {
        return accountMapper.getFengongsiInfo(fengongsiNum);
    }

    @Override
    public List<Map<String, Object>> getAreaAdministratorTell(String area) {
        return accountMapper.getAreaAdministratorTell(area);
    }

    //验证验证是否正确
    @Override
    public boolean testCode(String tellPhone, String code) {
        if (accountMapper.testCode(tellPhone, code).size() > 0) return true;
        else return false;
    }

    @Override
    public boolean testCode2(String tellPhone, String code) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if (accountMapper.testCode2(tellPhone, code, time).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //修改密码
    @Override
    public boolean updatePassword(String tellPhone, String password) {
        try {
            accountMapper.updatePassword(tellPhone, Md5Tool.getMd5(password));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
