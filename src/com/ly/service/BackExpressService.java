package com.ly.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

/**
 * 后台快递公司，快递员管理
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-13 16:49
 */
public interface BackExpressService {
    /**
     * 获取快递公司的清单
     */
    List<Map<String, Object>> getCompanyList(Map param);

    /**
     * 获取快递公司的清单
     */
    boolean addExpress(Map<String, Object> expressInfo, HttpSession session);

    /**
     * 删除快递信息
     *
     * @param id
     * @return
     */
    boolean deleteStore(String id);

    /**
     * 获取快递公司的总数量
     *
     * @return
     */
    Integer getCompanycount();

    /**
     * 获取快递员的数量
     */
    Integer getCourierCount();

    /**
     * 获取快递员的清单
     */
    List<Map<String, Object>> getCourierList(Map param);

    /**
     * 获取到快递公司的信息
     */
    List<Map<String, Object>> getCompany();

    /**
     * 新增快递员信息
     */
    String addCourier(Map map, MultipartFile file, HttpSession session);

    /**
     * 根据id删除快递员信息
     *
     * @param id
     * @return
     */
    boolean deleteCourier(String id);

    /**
     * 根据快递员的id信息查询出快递员的信息
     */
    Map<String, Object> getCourierInfo(String id);

    /**
     * 修改快递员信息
     *
     * @param courierInfo
     * @return
     */
    boolean modifyCourier(Map courierInfo);
}
