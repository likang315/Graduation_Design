package com.ly.mapper;

import java.util.List;
import java.util.Map;

/**
 * 审核门店上报的需求接口
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-17 10:05
 */
public interface BackDemandAuditMapper {
    Integer getCount(Map<String, Object> parameter);

    List<Map<String, Object>> getList(Map<String, Object> parameter);

    Integer updateAuditData(Map<String, Object> parameter);

    List<Map<String, Object>> getById(String[] checkDatas);

    /**
     * 获取时间戳获取审核通过需求列表
     *
     * @param paramter
     * @return
     */
    List<Map<String, Object>> getByState(Map<String, Object> paramter);

    /**
     * 查询出本次通过审核的信息
     *
     * @param paramter
     * @return
     */
    List<Map<String, Object>> getPassInfo(Map<String, Object> paramter);

    /**
     * 生成物流订单表中
     *
     * @param ls
     * @return
     */
    Integer addOrderInfo(List<Map<String, Object>> ls);
}
