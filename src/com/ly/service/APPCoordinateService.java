package com.ly.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ly.entity.app.APPCourierStore;

public interface APPCoordinateService {

    public List<Map<String, Object>> findIncomplete(String courierPhone);

    Map<String, Object> findbyList(String logistics);

    Map<String, Object> findStore(Map<String, Object> m);

    /**
     * 验证验证码是否正确
     */
    boolean testCode(String tellPhone, String code);

    public boolean testCode2(String tellPhone, String code);

    /**
     * 确认收货时修改状态
     *
     * @param m
     * @return
     */
    boolean updateOrder(Map<String, Object> m);

    boolean updateState(String id);

    boolean addCourierstore(APPCourierStore appCourierStore);

    boolean addCourierTime(Map<String, Object> m);

    boolean updateCourierstore(Map<String, Object> m);

    boolean addStoremail(Map<String, Object> m);

    boolean addStoreinfo(Map<String, Object> m);

    List<Map<String, Object>> findtotal(Map<String, Object> m);

    List<Map<String, Object>> CourierAllTotal(Map<String, Object> m);

    Integer getCourierlistCount(Map<String, Object> m);

    /**
     * 上传图片入库
     *
     * @param orderImg
     * @param storeImg
     * @param m
     * @return
     */
    boolean addImgForOrder(MultipartFile orderImg, MultipartFile storeImg, Map<String, Object> m);

}
