package com.ly.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ly.entity.app.APPCourierStore;
import com.ly.entity.app.MailInformation;

public interface APPCoordinateService {

    public List<Map<String, Object>> findIncomplete(String courierPhone);

    Map<String, Object> findbyList(String logistics);

    Map<String, Object> findStore(Map<String, Object> m);

    //验证验证码是否正确
    public boolean testCode(String tellPhone, String code);

    public boolean testCode2(String tellPhone, String code);

    public boolean updateOrder(Map<String, Object> m);

    boolean updateState(String id);

    boolean addCourierstore(APPCourierStore appCourierStore);

    boolean addCourierTime(Map<String, Object> m);

    boolean updateCourierstore(Map<String, Object> m);

    public boolean addStoremail(Map<String, Object> m);

    public boolean addStoreinfo(Map<String, Object> m);


    public List<Map<String, Object>> findtotal(Map<String, Object> m);

    List<Map<String, Object>> CourierAllTotal(Map<String, Object> m);

    Integer getCourierlistCount(Map<String, Object> m);

    //添加图片信息如order
    public boolean addImgForOrder(MultipartFile orderImg, MultipartFile storeImg,
                                  Map<String, Object> m);

}
