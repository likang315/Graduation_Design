package com.ly.service.appimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.entity.app.APPStoreSupplies;
import com.ly.mapper.APPStroreSuppliesMapper;
import com.ly.mapper.AppFillingSingleMapper;
import com.ly.service.APPStroreSuppliesService;

@Service
public class APPStroreSuppliesServiceImpl implements APPStroreSuppliesService {
    @Autowired
    private APPStroreSuppliesMapper appStroreSuppliesMapper;

    @Autowired
    private AppFillingSingleMapper appFillingSingleMapper;

    @Override
    public boolean addmaterial(APPStoreSupplies appStoreSupplies, HttpSession session) {
        boolean flag = true;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mytime = dateFormat.format(new Date());
            appStoreSupplies.setReport_time(dateFormat.parse(mytime));
            List<Map<String, Object>> storeMaterials = (List<Map<String, Object>>) session.getAttribute("storeMaterial");
            for (int i = 0; i < storeMaterials.size(); i++) {
                Map<String, Object> storeMaterial = storeMaterials.get(i);
                if (appStoreSupplies.getMaterialContent() != null && appStoreSupplies.getMaterialContent() != "") {
                    // 表示不存在
                    if (appStoreSupplies.getMaterialContent().indexOf(storeMaterial.get("materialId").toString()) <= -1) {
                        appStoreSupplies.setMaterialContent(appStoreSupplies.getMaterialContent() + "," + storeMaterial.get("materialName").toString());
                        appStoreSupplies.setMaterialNumber(appStoreSupplies.getMaterialNumber() + "," + storeMaterial.get("num").toString());
                    }
                } else {
                    appStoreSupplies.setMaterialContent(storeMaterial.get("materialName").toString());
                    appStoreSupplies.setMaterialNumber(storeMaterial.get("num").toString());
                }

            }
            appStroreSuppliesMapper.addmaterial(appStoreSupplies);
            session.removeAttribute("storeMaterial");
        } catch (ParseException e) {
            System.out.println("日期转换异常");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("添加需求上报信息失败");
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Map<String, Object>> findtotal(Map<String, Object> m) {

        return appStroreSuppliesMapper.findtotal(m);
    }

    @Override
    public List<Map<String, Object>> StoreAllTotal(Map<String, Object> m) {

        return appStroreSuppliesMapper.StoreAllTotal(m);
    }

    @Override
    public Integer getStoreAllTotalCount(Map<String, Object> m) {
        return appStroreSuppliesMapper.getStoreAllTotalCount(m);
    }

    @Override
    public List<Map<String, Object>> findStoreList(Map<String, Object> m) {
        return appStroreSuppliesMapper.findStoreList(m);
    }

    @Override
    public Map<String, Object> findStoreListDetails(Map<String, Object> m) {
        return appStroreSuppliesMapper.findStoreListDetails(m);
    }

    @Override
    public Map<String, Object> findStoreAllDetails(Map<String, Object> m) {
        return appStroreSuppliesMapper.findStoreAllDetails(m);
    }

    @Override
    public Integer getStorelistCount(Map<String, Object> m) {
        return appStroreSuppliesMapper.getStorelistCount(m);
    }

    @Override
    public List<Map<String, Object>> getMaterials() {
        return appFillingSingleMapper.getMaterial();
    }

    /**
     * 根据物资的名称获取到物资的id
     */
    @Override
    public Integer getMaterialsId(String material) {

        return appFillingSingleMapper.getMaterialsId(material);
    }
}
