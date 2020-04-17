package com.ly.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.ly.entity.NowPage;
import com.ly.service.BackDemandAuditService;
import com.ly.util.Common;

/**
 * 审核门店上报的需求
 * 0：未审核，1审核通过，2审核驳回
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-17 10:05
 */
@Controller
@RequestMapping("/background/DemandAudit")
public class BackDemandAuditController {

    @Autowired
    public BackDemandAuditService backDemandAuditService;

    /**
     * 获取审核门店需求列表，时间搜索还是重定向到此接口
     *
     * @param pageNo    页码
     * @param dataState 数据状态
     * @param result    处理结果
     */
    @RequestMapping("/getList")
    public String getList(String pageNo,
                          String dataState,
                          String startTime,
                          String endTime,
                          String result,
                          Model model) {
        Integer count = backDemandAuditService.getCount(dataState, startTime, endTime);
        NowPage<Map<String, Object>> page = new NowPage<>(pageNo, count, 10);
        List<Map<String, Object>> auditDatas = backDemandAuditService.getList(dataState, startTime, endTime, page.getStart(), page.getSize());
        //page.setItems(auditDatas);
        model.addAttribute("page", page);
        if (StringUtils.isEmpty(dataState)) {
            dataState = "all";
        }
        model.addAttribute("dataState", dataState);
        if (StringUtils.isNotEmpty(result)) {
            try {
                result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
                model.addAttribute("result", result);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        model.addAttribute("ls", JSON.toJSON(auditDatas));
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("pageNo", pageNo);
        return Common.BACKGROUND_PATH + "/demand/demandAuditList";
    }

    /**
     * 审核结果入库且生成物流订单
     */
    @RequestMapping("/updateAuditData")
    public String updateAuditData(String[] checkDatas,
                                  String examine,
                                  String examine_reason,
                                  RedirectAttributes redirectAttributes,
                                  HttpServletRequest request) {
        boolean flag = backDemandAuditService.updateAuditData(checkDatas, examine, examine_reason, request);
        if (flag) {
            redirectAttributes.addAttribute("result", "Successful");
        } else {
            redirectAttributes.addAttribute("result", "Failture");
        }
        return "redirect:getList.html";
    }

    /**
     * 导出审核通过的清单数据
     */
    @RequestMapping("/exportData")
    public void exportData(String startTime, String endTime, HttpServletResponse response) {
        backDemandAuditService.exportData(startTime, endTime, response);
    }


}
