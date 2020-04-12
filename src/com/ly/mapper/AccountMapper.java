package com.ly.mapper;

import java.util.List;
import java.util.Map;

import com.ly.base.BaseMapper;
import com.ly.entity.app.CodeInfo;
import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.service.vo.Apply;

/**
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-01 11:05
 */
public interface AccountMapper extends BaseMapper<Account> {
    Account querySingleAccount(String accountName);

    Account isExist(String accountName);

    /**
     * 验证用户登陆
     *
     * @return
     */
    public Account countAccount(Account account);

    public List<Account> queryNoMatch(Map<String, Object> map);

    /**
     * @param username
     * @param password
     * @param token
     * @return app
     */
    public Account checkLogin(String username, String password, String token);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public Account login(String username, String password);

    /**
     * 更新令牌
     *
     * @param username
     * @param token
     */
    public void updateLogin(String username, String token);

    public Account loginByToken(String username, String password, String token);

    public int changePassword(String md5, String username);

    public int getCounts(List<Integer> gid);

    public List<String> getAccountByGroupId(String preid);

    public List<Account> getAllByType(Map<String, Object> map);

    public List<Account> getAll(Map<String, Object> map);

    /**
     * 注册
     *
     * @param account
     */
    @Override
    void add(Account account);
    /**
     * 查询县级
     *
     * @param parentId
     * @return
     */
    public List<Group> selectCounty(String parentId);

    //查询手机号是否注册
    public List<Account> validatePhoneNum(String accountName);

    public List<Account> getAllManagerUsers(String managerid);

    //查询当前用户经纬度
    public List<Account> getLngLat(String accountName);

    //设置当前用户的经纬度
    public void setLngLat(String accountName, String longitude, String latitude);

    //查询身份证号是否已经注册
    public List<Account> getIdCar(String idCar);

    //查询所有用户
    public List<Account> getSysAccountAll();

    //查询id对应用的所有信息
    public List<Account> getByIdAll(String id);

    //修改审核
    public void setByIdShenHe(String employeesNum, String auth_reason, String auth_flag, String id);

    //将用户角色默认添加到店员
    public void addRole(Map<String, Object> map);

    //根据渠道编码获取渠道经理的信息
    public List<Account> getTell(String code);

    //员工提交申请
    public void apply(Apply apply);

    //根据员工编号及审核状态查询员工是否重复提交
    public List<Apply> getApply(Apply apply);

    //根据当前登录渠道编码查询所有申请调整信息
    List<Apply> getApplyAll(Map<String, Object> map);

    /**
     * 处理申请分两步
     * 1将原有额渠道编码更改问现有的
     * 2.将申请表中当前申请变更为已处理
     */
    //1原有额渠道编码更改问现有的
    public void updateCode(Apply apply);

    //2将申请表中当前申请变更为已处理
    public void updateIsSuccess(String is, String id);

    //根据用户登录名来查找用户信息
    public List<Account> getAccountNameInfo(String accountName);

    //根据用户登录名查找用户信息 (未删除切可以正常登陆)
    public List<Account> getAccountNameInfoSuccess(String accountName);


    //根据员工编码查询信息
    public List<Account> getDianyuanNumInfo(String dianyuanNum);

    //验证验证码是否正确
    public List<com.ly.entity.app.Smslog> testCode(String tellPhone, String code);

    public List<Map<String, String>> testCode2(String tellPhone, String code, String time);

    //修改密码
    void updatePassword(String tellPhone, String password);

    //模糊查询
    List<CodeInfo> getInfo(int fengongsiNum, String codeName);

    //批量插入
    void addAll(List<Account> listC);

    //重写后的登录
    List<Account> loginModify(String accountName);

    //查询code信息，根据分公司和渠道名称
    public List<CodeInfo> getCodeInfo(int fengongsiNum, String codeName);

    //查询下一级组织机构
    public List<Group> getGroupInfo(Integer id);


    public List<Account> getAreaAdministratorInfo(String areaName);

    List<Map<String, Object>> getFengongsiInfo(int fengongsiNum);

    List<Map<String, Object>> getIdZuzhiInfo(String name);

    List<Map<String, Object>> getLaseZuzhiInfo(String id);


    //根据片区获取管理员信息2
    public List<Map<String, Object>> getAreaAdministratorTell(String area);

    Map<String, Object> findByUser(String accountName);

    public Map<String, Object> findPidByGroupId(Integer groupId);

    public List<Account> findAccount(String managerPhone);

    public int getApplyAllCount(Map<String, Object> map);

    public List<Map<String, Object>> getApplyUser(Map<String, Object> parame);

    public int getAuthCount(Map<String, Object> map);

    public List<Map<String, Object>> getAuthList(Map<String, Object> parame);


    public List<Map<String, Object>> findUserByPage(Map<String, Object> parame);

    public int findUserCount(Map<String, Object> map);

    public List<CodeInfo> mohuchaxunByArea(int fengongsiNum);

    public List<CodeInfo> getCodeInfoById(int fengongsiNum);

    public String getAccountName(String userId);
}
