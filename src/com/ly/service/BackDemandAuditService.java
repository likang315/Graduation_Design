package com.ly.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 审核门店上报的需求
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-17 10:05
 */
public interface BackDemandAuditService {
    Integer getCount(String dataState, String startTime, String endTime);

    List<Map<String, Object>> getList(String dataState, String startTime, String endTime, int start, int size);

    /**
     * 批量审核需求列表
     *
     * @param checkDatas
     * @param examine
     * @param examine_reason
     * @param request
     * @return
     */
    boolean updateAuditData(String[] checkDatas, String examine, String examine_reason, HttpServletRequest request);

    void exportData(String startTime, String endTime, HttpServletResponse response);
}
