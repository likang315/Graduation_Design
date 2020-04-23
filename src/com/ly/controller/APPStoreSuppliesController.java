package com.ly.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ly.entity.app.APPStoreSupplies;
import com.ly.service.APPStroreSuppliesService;
import com.ly.util.Common;

/**
 * app门店需求控制器
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-10 11:05
 */
@RequestMapping("/app/storeSupplies/")
@Controller
public class APPStoreSuppliesController {

    @Autowired
    private APPStroreSuppliesService appStroreSuppliesService;

    /**
     * 01:店主登录index，跳转订单统计页面
     */
    @RequestMapping(value = "toStoreList")
    public String tocourierList(String state, Model model) {
        return Common.APP_PATH + "/storelist";
    }

    /**
     * 02:查找订单列表数据，时间，订单号查询都是此接口
     *
     */
    @RequestMapping(value = "storeList")
    @ResponseBody
    public Object storeList(String channel_code,
                            String state,
                            String searchMsg,
                            String startTime,
                            String endTime,
                            Integer jiazai) {

        if (StringUtils.isNotEmpty(state) && "0".equals(state)) {
            state = null;
        }
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("state", state);
        m.put("channel_code", channel_code);
        m.put("searchMsg", searchMsg);
        m.put("startTime", startTime);
        m.put("endTime", endTime);
        m.put("jiazai", jiazai);
        // 获取此门店的订单数量 chanel_code
        Integer count = appStroreSuppliesService.getStorelistCount(m);
        List<Map<String, Object>> map = appStroreSuppliesService.findStoreList(m);

        Map<String, Object> pamer = new HashMap<String, Object>();
        pamer.put("state", state);
        pamer.put("searchMsg", searchMsg);
        pamer.put("startTime", startTime);
        pamer.put("endTime", endTime);
        pamer.put("map", map);
        pamer.put("jiazai", jiazai);
        pamer.put("count", count);

        return pamer;
    }

    /**
     * 03.显示订单详情
     *
     */
    @RequestMapping(value = "storelistDetails")
    public String storelistDetails(Model model, String id, String channel_code) {

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("id", id);
        m.put("channel_code", channel_code);
        Map<String, Object> map = appStroreSuppliesService.findStoreListDetails(m);

        if (map != null) {
            String[] materialContents = map.get("materialContent").toString().split(",");// 物资名称
            String[] materialNumbers = map.get("materialNumber").toString().split(",");// 物资数量
            for (int i = 0; i < materialContents.length; i++) {
                if (map.get("materialNameAndNumber") != null) {
                    map.put("materialNameAndNumber", map.get("materialNameAndNumber").toString() + ","
                                    + materialContents[i] + "("
                                    + materialNumbers[i] + ")");
                } else {
                    map.put("materialNameAndNumber", materialContents[i] + "("
                            + materialNumbers[i] + "件)");
                }
            }

        }

        model.addAttribute("maps", map);

        return Common.APP_PATH + "/storelistDetails";
    }


    /**
     * 04.跳转到报需index页面
     *
     */
    @RequestMapping("toReport")
    public String toReport() {
        return Common.APP_PATH + "/reportTo";
    }

    /**
     * 05.跳转需求上报页面
     *
     */
    @RequestMapping(value = "tostoreSupplies")
    public String tostoreSupplies(Model model, HttpSession session) {
        session.removeAttribute("storeMaterial");
        // 获取所有物资列表
        List<Map<String, Object>> materials = appStroreSuppliesService.getMaterials();
        model.addAttribute("materials", materials);
        return Common.APP_PATH + "/storeSupplies";

    }

    /**
     * 06.需求上报，添加或者删除物资到列表中
     *
     * @param materialId   物资id
     * @param materialName 物资名称
     * @param num          数量
     * @param operation    操作(新增或删除)
     */
    @RequestMapping("appendOrRemove")
    @ResponseBody
    public Object appendOrRemove(String materialId, String materialName,
                                 String num, String operation, HttpSession session) {
        List<Map<String, Object>> mList;
        mList = (List<Map<String, Object>>) session.getAttribute("storeMaterial");
        if ("append".equals(operation)) {// 拼接操作
            int a = 0;
            if (mList != null && mList.size() > 0) {
                for (int i = 0; i < mList.size(); i++) {
                    Map<String, Object> m = mList.get(i);
                    if (materialId.equals(m.get("materialId").toString())) {
                        m.put("num", Integer.parseInt(m.get("num").toString()) + Integer.parseInt(num));
                        a++;
                    }
                }
                if (a == 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("num", num);
                    map.put("materialId", materialId);
                    map.put("materialName", materialName);
                    mList.add(map);
                }
            } else {
                mList = new ArrayList<Map<String, Object>>();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("num", num);
                map.put("materialId", materialId);
                map.put("materialName", materialName);
                mList.add(map);
            }
            session.setAttribute("storeMaterial", mList);
        } else if ("remove".equals(operation)) {// 删除操作
            for (int i = 0; i < mList.size(); i++) {
                Map<String, Object> map = mList.get(i);
                if (materialId.equals(map.get("materialId").toString())) {
                    mList.remove(i);
                }
            }
        }
        return mList;
    }

    /**
     * 07.需求上报时，点击提交调用，用于验证有没有添加物资
     *
     * @param session
     * @return
     */
    @RequestMapping("checkMaterial")
    @ResponseBody
    public Object checkMaterial(HttpSession session) {
        Map<String, Object> m = new HashMap<String, Object>();
        List<Map<String, Object>> list = (List<Map<String, Object>>) session.getAttribute("storeMaterial");
        if (list != null) {
            m.put("info", list.size());
        } else {
            m.put("info", 0);
        }
        return m;
    }

    /**
     * 08.需求上报提交，入库
     */
    @RequestMapping("addmaterial")
    public String addmaterial(APPStoreSupplies appStoreSupplies,
                              RedirectAttributes attributes,
                              HttpSession session) {
        if (appStroreSuppliesService.addmaterial(appStoreSupplies, session)) {
            attributes.addAttribute("info", "Requirements submitted successfully !");
        } else {
            attributes.addAttribute("info", "Failed to submit requirements !");
        }
        attributes.addAttribute("channel_code", appStoreSupplies.getChannel_code());
        attributes.addAttribute("state", "1");
        return "redirect:toStoreAllTotal.html";
    }

    /**
     * 09.跳转上报列表页面
     *
     */
    @RequestMapping("toStoreAllTotal")
    public String toStoreAllTotal(String info, Model model) {
        try {
            if (info != null && info != "") {
                info = new String(info.getBytes("ISO-8859-1"), "UTF-8");
                model.addAttribute("info", info);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return Common.APP_PATH + "/storeAllTotal";
    }

    /**
     * 10.查找上报列表数据
     *
     */
    @RequestMapping(value = "StoreAllTotal")
    @ResponseBody
    public Object StoreAllTotal(Model model, String channel_code, String state,
                                String info, String startTime, String endTime, Integer jiazai) {
        if (StringUtils.isNotEmpty(state) && "0".equals(state)) {
            state = null;
        }
        Map<String, Object> pamer = new HashMap<String, Object>();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("state", state);
        m.put("channel_code", channel_code);
        m.put("startTime", startTime);
        m.put("endTime", endTime);
        if (jiazai == null) {
            jiazai = 0;
        }
        m.put("jiazai", jiazai);
        Integer count = appStroreSuppliesService.getStoreAllTotalCount(m);
        List<Map<String, Object>> map = appStroreSuppliesService.StoreAllTotal(m);

        pamer.put("state", state);
        pamer.put("map", map);
        pamer.put("startTime", startTime);
        pamer.put("endTime", endTime);
        pamer.put("jiazai", jiazai);
        pamer.put("count", count);
        if (info != null && info != "") {
            try {
                model.addAttribute("info", new String(info.getBytes("ISO-8859-1"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return pamer;
    }

    /**
     * 11.查找不报订单详情
     *
     */
    @RequestMapping(value = "storeAllDetails")
    public String storeAllDetails(Model model, String id, String channel_code) {

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("id", id);
        m.put("channel_code", channel_code);
        Map<String, Object> map = appStroreSuppliesService.findStoreAllDetails(m);
        if (map != null) {
            String[] materialContents = map.get("materialContent").toString().split(",");
            String[] materialNumbers = map.get("materialNumber").toString().split(",");
            for (int i = 0; i < materialContents.length; i++) {
                if (map.get("materialNameAndNumber") != null) {
                    map.put("materialNameAndNumber",
                            map.get("materialNameAndNumber").toString() + ","
                                    + materialContents[i] + "("
                                    + materialNumbers[i] + ")");
                } else {
                    map.put("materialNameAndNumber", materialContents[i] + "("
                            + materialNumbers[i] + "件)");
                }
            }

        }

        model.addAttribute("maps", map);

        return Common.APP_PATH + "/storeAllDetails";

    }

    /**
     * 12.跳转途中加载图片
     *
     */
    @RequestMapping("goStoreTotal")
    public String goStoreTotal() {
        return Common.APP_PATH + "/toStoreTotal";
    }

    /**
     * 13.上报统计跳转到index
     *
     */
    @RequestMapping(value = "toStoreTotal")
    public String toStoreTotal(Model model, String channel_code,
                               String startTime, String endTime) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("channel_code", channel_code);
        m.put("startTime", startTime);
        m.put("endTime", endTime);
        List<Map<String, Object>> map = appStroreSuppliesService.findtotal(m);
        model.addAttribute("map", map);
        model.addAttribute("channel_code", channel_code);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);

        return Common.APP_PATH + "/storeTotal";

    }

}
