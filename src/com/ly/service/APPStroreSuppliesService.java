package com.ly.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ly.entity.app.APPStoreSupplies;

/**
 *  app门店信息
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-04 11:05
 */
public interface APPStroreSuppliesService {

    List<Map<String, Object>> findtotal(Map<String, Object> m);

    List<Map<String, Object>> StoreAllTotal(Map<String, Object> m);

    Integer getStoreAllTotalCount(Map<String, Object> m);

    List<Map<String, Object>> findStoreList(Map<String, Object> m);

    Integer getStorelistCount(Map<String, Object> m);

    Map<String, Object> findStoreListDetails(Map<String, Object> m);

    public Map<String, Object> findStoreAllDetails(Map<String, Object> m);

    boolean addmaterial(APPStoreSupplies appStoreSupplies, HttpSession session);

    List<Map<String, Object>> getMaterials();

    public Integer getMaterialsId(String material);


}
