package com.ly.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 物资后台处理mapper
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-04 11:05
 */
public interface BackMaterialMapper {

    // 获取物资的数量
    Integer getMaterialCount();

    // 获取物资的清单
    List<Map<String, Object>> list(Map m);

    // 获取物资信息
    Map<String, Object> getMaterialInfo(@Param("id") String id);

    // 修改物资信息
    Integer modify(Map m);

    // 删除物资信息
    Integer deleteMaterial(@Param("id") String id);

    Integer add(Map m);

    Integer checkOne(Map m);
}
