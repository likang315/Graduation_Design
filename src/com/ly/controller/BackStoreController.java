package com.ly.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ly.entity.NowPage;
import com.ly.service.BackStoreService;
import com.ly.util.Common;
import com.ly.util.CourierNumber;

/**
 * 物流门店的后台管理
 */
@Controller
@RequestMapping("/background/Store")
public class BackStoreController {
    @Autowired
    private BackStoreService backStoreService;

    /**
     * 01.查询店面信息，并跳转
     *
     * @param model
     * @return
     */
    @RequestMapping("/storeList")
    public String storeList(String pageNow, String info, Model model, String modifyState, String deleteinfo) {
        if (pageNow == null || "".equals(pageNow)) {
            pageNow = "0";
        }
        Map<String, Object> parame = new HashMap<String, Object>();

        // 获取到总条数
        Integer totalPage = backStoreService.getStoreCount();
        NowPage<Map<String, Object>> page = new NowPage<>(pageNow, totalPage, 10);
        parame.put("size", 10);
        parame.put("start", page.getStart());

        // 门店清单信息
        List<Map<String, Object>> storeList = backStoreService.list(parame);
        page.setItems(storeList);
        model.addAttribute("page", page);

        try {
            if (info != null && info != "") {
                System.err.println(info);
                model.addAttribute("info", new String(info.getBytes("ISO-8859-1"), "UTF-8"));
            }
            if (modifyState != null && modifyState != "") {
                model.addAttribute("modifyState", new String(modifyState.getBytes("ISO-8859-1"), "UTF-8"));
            }
            if (deleteinfo != null && deleteinfo != "") {
                model.addAttribute("deleteinfo", new String(deleteinfo.getBytes("ISO-8859-1"), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 跳转到list页面
        return Common.BACKGROUND_PATH + "/store/storeList";
    }

    /**
     * 02.跳转到到新增页面
     *
     * @return 新增页面
     */
    @RequestMapping("/addUI")
    public String addUI(Model model) {
        // 查询出品牌厂商信息
        List<Map<String, Object>> vendorList = backStoreService.getVendor();
        model.addAttribute("vendorList", vendorList);
        return Common.BACKGROUND_PATH + "/store/storeAdd";
    }

    /**
     * 03. 新增门店信息入库 上传图片只能是png或者jpg
     *
     * @param storeInfo
     * @param file
     * @param redirectAttributes
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/add")
    public String add(@RequestParam Map<String, Object> storeInfo,
                      @RequestParam(value = "file", required = false) MultipartFile file,
                      RedirectAttributes redirectAttributes,
                      HttpServletRequest request) throws IOException {

        redirectAttributes.addAttribute("info", backStoreService.addStore(storeInfo, file, request));
        return "redirect:storeList.html";
    }

    /**
     * 04.根据传入id删除店面信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteStore")
    public String deleteStore(String id, RedirectAttributes attributes) {
        // 根据id删除店面
        if (backStoreService.deleteStore(id)) {
            attributes.addFlashAttribute("deleteinfo", "删除成功！");
        } else {
            attributes.addFlashAttribute("deleteinfo", "删除失败！");
        }

        return "redirect:storeList.html";

    }

    /**
     * 05.跳转到修改页面
     *
     * @return
     */
    @RequestMapping("/permission")
    public String permission(String storeId, Model model) {
        // 根据id查询该条记录的门店信息
        Map<String, Object> map = backStoreService.getStoreInfoOfId(storeId);
        // 查询出厂商信息
        List<Map<String, Object>> vendorList = backStoreService.getVendor();

        model.addAttribute("vendorList", vendorList);
        model.addAttribute("storeInfo", map);

        return Common.BACKGROUND_PATH + "/store/storeModify";
    }


    /**
     * 06.修改门店信息
     *
     * @param storeInfo
     * @param file
     * @param attributes
     * @return
     */
    @RequestMapping("/modify")
    public String modify(@RequestParam Map storeInfo,
                         @RequestParam(value = "file", required = false) MultipartFile file,
                         RedirectAttributes attributes) {
        // 修改门店信息
        if (backStoreService.modifyStore(storeInfo, file)) {
            attributes.addAttribute("modifyState", "Successful");
        } else {
            attributes.addAttribute("modifyState", "The store has exist!");
        }
        return "redirect:storeList.html";
    }


    /**
     * 07.查找电话是否重复，规范
     *
     * @param store_shopowner_phone
     * @return
     */
    @RequestMapping("/findPhone")
    @ResponseBody
    public Map<String, Object> findPhone(String store_shopowner_phone) {
        List<Map<String, Object>> list = backStoreService.findPhone(store_shopowner_phone);
        Map<String, Object> m = new HashMap<String, Object>();
        if (list.size() < 1) {
            m.put("state", "ok");
        } else {
            m.put("id", list.get(0).get("id"));
            m.put("state", "no");
        }
        return m;
    }

    /**
     * 08.检查地址是否重复
     *
     * @param store_address
     * @return
     * @throws IOException
     */
    @RequestMapping("/checkAddress")
    @ResponseBody
    public Map<String, Object> checkAddress(String store_address) throws IOException {
        // 先查询是否存在
        List<Map<String, Object>> list = backStoreService.checkAddress(store_address);
        Map<String, Object> m = new HashMap<String, Object>();
        if (list.size() < 1) {
            Map<String, Object> mapa = CourierNumber.Getcontext2(store_address);
            if ("ok".equals(mapa.get("state").toString())) {
                m.put("state", "ok");
            } else {
                //如果获取经纬度信息时出错时，则去获取下一条数据
                m.put("state", "no");
                m.put("id", list.get(0).get("id"));
                m.put("message", "请填写详细地址");
            }
        } else {
            m.put("state", "no");
            m.put("id", list.get(0).get("id"));
            m.put("message", "地址已存在");
        }
        return m;
    }
}
