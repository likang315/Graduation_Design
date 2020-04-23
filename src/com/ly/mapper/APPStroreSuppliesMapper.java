package com.ly.mapper;

import java.util.List;
import java.util.Map;

import com.ly.entity.app.APPStoreSupplies;

public interface APPStroreSuppliesMapper {


    List<Map<String, Object>> findtotal(Map<String, Object> m);

    List<Map<String, Object>> StoreAllTotal(Map<String, Object> m);

    List<Map<String, Object>> findStoreList(Map<String, Object> m);

    Integer getStoreAllTotalCount(Map<String, Object> m);

    Map<String, Object> findStoreListDetails(Map<String, Object> m);

    public Map<String, Object> findStoreAllDetails(Map<String, Object> m);

    Integer getStorelistCount(Map<String, Object> m);

    int addmaterial(APPStoreSupplies appStoreSupplies);
}
