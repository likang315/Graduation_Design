package com.ly.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ly.entity.NowPage;
import com.ly.service.BackExpressService;
import com.ly.util.Common;

/**
 * 快递员，快递公司后台控制
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-13 16:49
 */
@Controller
@RequestMapping("/background/Express")
public class BackExpressController {
    @Autowired
    private BackExpressService backExpressService;

    /**
     * 查询跳转到公司的清单
     *
     * @param model
     * @return
     */
    @RequestMapping("/companyList")
    public String companyList(String pageNow, String info, String deleteinfo, Model model) {
        if (pageNow == null || "".equals(pageNow)) {
            pageNow = "0";
        }
        Map<String, Object> parame = new HashMap<String, Object>();

        //查询出快递公司的总数量
        Integer totalPage = backExpressService.getCompanycount();

        NowPage<Map<String, Object>> page = new NowPage<>(pageNow, totalPage, 10);

        parame.put("size", 10);
        parame.put("start", page.getStart());


        model.addAttribute("page", page);

        //查询出快递公司的信息
        List<Map<String, Object>> ls = backExpressService.getCompanyList(parame);
        try {
            if (info != null && info != "") {
                model.addAttribute("info", new String(info.getBytes("ISO-8859-1"), "UTF-8"));
            }
            if (deleteinfo != null && deleteinfo != "") {
                model.addAttribute("deleteinfo", new String(deleteinfo.getBytes("ISO-8859-1"), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("companyList", ls);
        return Common.BACKGROUND_PATH + "/express/expressList";
    }

    /**
     * 跳转到新增快递公司页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/addUI")
    public String addUI() {
        return Common.BACKGROUND_PATH + "/express/expressadd";
    }

    @RequestMapping("/add")
    public String add(@RequestParam Map expressInfo, RedirectAttributes redirectAttributes, HttpSession session) {
        if (backExpressService.addExpress(expressInfo, session)) {
            redirectAttributes.addAttribute("info", "信息维护成功!");
        } else {
            redirectAttributes.addAttribute("info", "信息维护失败,请核对数据是否符合规范，或该信息是否存在!");
        }
        return "redirect:companyList.html";
    }

    /**
     * 删除快递信息
     *
     * @param id
     * @param attributes
     * @return
     * @author 殷瑜泰 2017年3月21日下午4:56:52
     */
    @RequestMapping("/deleteExpress")
    public String deleteExpress(String id, RedirectAttributes attributes) {
        //根据id删除快递信息
        if (backExpressService.deleteStore(id)) {
            attributes.addFlashAttribute("deleteinfo", "删除成功！");
        } else {
            attributes.addFlashAttribute("deleteinfo", "删除失败！");
        }
        return "redirect:companyList.html";
    }

    /**
     * 01.快递员清单信息管理
     *
     * @return
     * @throws UnsupportedEncodingException
     */

    @RequestMapping("/courierList")
    public String courierList(String pageNow,
                              String info,
                              String modifyInfo,
                              String deleteInfo,
                              Model model) throws UnsupportedEncodingException {

        if (pageNow == null || "".equals(pageNow)) {
            pageNow = "0";
        }
        Map<String, Object> parame = new HashMap<>(16);

        if (info != null && info != "") {
            model.addAttribute("info", new String(info.getBytes("ISO-8859-1"), "UTF-8"));
        }
        if (deleteInfo != null && deleteInfo != "") {
            model.addAttribute("deleteInfo", new String(deleteInfo.getBytes("ISO-8859-1"), "UTF-8"));
        }
        if (modifyInfo != null && modifyInfo != "") {
            model.addAttribute("modifyInfo", new String(modifyInfo.getBytes("ISO-8859-1"), "UTF-8"));
        }

        // 获取快递员的数量
        Integer totalPage = backExpressService.getCourierCount();

        NowPage<Map<String, Object>> page = new NowPage<>(pageNow, totalPage, 10);

        parame.put("size", 10);
        parame.put("start", page.getStart());

        model.addAttribute("page", page);

        List<Map<String, Object>> ls = backExpressService.getCourierList(parame);

        model.addAttribute("courierList", ls);

        return Common.BACKGROUND_PATH + "/express/courierList";
    }

    /**
     * 02.跳转到快递员信息维护页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/addCourierUI")
    public String addCourierUI(Model model) {
        // 查询出快递公司ID-object
        List<Map<String, Object>> companyls = backExpressService.getCompany();
        model.addAttribute("companyls", companyls);

        return Common.BACKGROUND_PATH + "/express/courierAdd";
    }


    /**
     * 03.新增快递员数据 注意编码格式
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/addCourier")
    public String addCourier(@RequestParam Map courierInfo,
                             @RequestParam(value = "file", required = false) MultipartFile file,
                             RedirectAttributes redirectAttributes, HttpSession session) {
        // 维护快递员信息
        redirectAttributes.addAttribute("info", backExpressService.addCourier(courierInfo, file, session));
        return "redirect:courierList.html";
    }

    /**
     * 04.根据id删除快递员信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteCourier")
    public String deleteCourier(String id, RedirectAttributes redirectAttributes) {
        // 执行删除操作
        if (backExpressService.deleteCourier(id)) {
            redirectAttributes.addAttribute("deleteInfo", "信息删除成功!");
        } else {
            redirectAttributes.addAttribute("deleteInfo", "信息删除失败!");
        }

        return "redirect:courierList.html";

    }

    /**
     * 05.跳转到Courier信息修改页面
     *
     * @return
     */
    @RequestMapping("/permissionCourier")
    public String permissionCourier(String id, Model model) {
        // 根据快递员的id查询出快递员的信息
        Map<String, Object> courierInfo = backExpressService.getCourierInfo(id);
        // 查询出快递公司的信息
        List<Map<String, Object>> company = backExpressService.getCompany();
        //快递员信息
        model.addAttribute("courierInfo", courierInfo);
        model.addAttribute("company", company);
        return Common.BACKGROUND_PATH + "/express/courierModify";
    }


    /**
     * 06.修改信息
     *
     * @return
     */
    @RequestMapping("/modifyCourier")
    public String modifyCourier(@RequestParam Map courierInfo, RedirectAttributes attributes) {
        if (backExpressService.modifyCourier(courierInfo)) {
            attributes.addAttribute("modifyInfo", "信息修改成功");
        } else {
            attributes.addAttribute("modifyInfo", "信息修改失败,请核实该信息是否已存在！");
        }

        return "redirect:courierList.html";
    }
}
