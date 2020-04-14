package com.ly.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 物资后台处理service
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-04 11:05
 */
public interface BackMaterialService {

    //获取物资信息
    Integer getMaterialCount();

    //获取到物资信息的list
    List<Map<String, Object>> list(Map m);

    //根据id查询出来
    Map<String, Object> getMaterialInfo(String id);

    //修改物资信息
    boolean modify(Map m);

    //删除物资信息
    boolean deleteMaterial(String id);

    //新增物资信息
    boolean add(Map m, HttpServletRequest request);


}
