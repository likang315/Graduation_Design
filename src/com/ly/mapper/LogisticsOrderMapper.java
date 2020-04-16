package com.ly.mapper;

import java.util.List;
import java.util.Map;

import com.ly.entity.background.Account;
import org.apache.ibatis.annotations.Param;

import com.ly.entity.app.MailInformation;

public interface LogisticsOrderMapper {


    List<Map<String, Object>> findOrderlist(Map<String, Object> map);

    int findOrdercount(Map<String, Object> m);

    Integer findOrderOfNullNum();

    List<Map<String, Object>> getSearchPhone(Map<String, Object> map);

    Map<String, Object> getDisplayMap(Map<String, Object> map);

    Map<String, Object> getDisplayMap1(Map<String, Object> map);


    public int getSearchCount(Map<String, Object> m);

    // 批量删除
    int deleteLogistics(String id);

    /**
     * 批量派送
     */
    int distribution(Map<String, Object> map);


    Map<String, Object> findByLogistics(String id);

    // 修改
    int updateLogistics(Map m);


    //下载清单(配送成功）
    public List<Map<String, Object>> findlogisticslist();

    //查询出快递员的姓名
    Map<String, Object> getcourierName(@Param("courierName") String courierName);

    //渠道编码获取渠道信息
    Integer getChannel(@Param("channel_code") String channel_code);

    //添加订单信息
    Integer addOrderInfo(List<Map<String, Object>> list);


    //获取快递员是否存在
    Integer checkcourierPhoneService(@Param("phone") String phone);

    //查询出快递员信息为空的列表
    List<Map<String, Object>> findOrderOfNulllist(Map<String, Object> m);

    //查询出快递公司的信息
    List<Map<String, Object>> getCompany();

    //
    List<Map<String, Object>> getCourierLs(String[] companyId);

    /**
     * 快递员姓名
     *
     * @param phone
     * @return
     */
    String getCourierName(@Param("phone") String phone);

    /**
     * 维护派送完成的订单
     *
     * @param ls
     * @return
     */
    Integer finishOrder(Map<String, Object> ls);


}




