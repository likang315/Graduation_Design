package com.ly.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.ly.entity.app.APPCourierStore;

public interface APPCoordinateMapper {

    public List<Map<String, Object>> findIncomplete(String courierPhone);

    Map<String, Object> findbyList(String logistics);

    Map<String, Object> findStore(Map<String, Object> m);

    //验证验证码是否正确
    List<com.ly.entity.app.Smslog> testCode(String tellPhone, String code);

    List<Map<String, String>> testCode2(String tellPhone, String code, String time);

    int updateOrder(Map<String, Object> m);

    int updateState(String id);

    int addCourierstore(APPCourierStore appCourierStore);

    int addCourierTime(Map<String, Object> m);

    int updateCourierstore(Map<String, Object> m);

    public List<Map<String, Object>> findtotal(Map<String, Object> m);

    List<Map<String, Object>> CourierAllTotal(Map<String, Object> m);

    Integer getCourierlistCount(Map<String, Object> m);

    int addStoremail(Map<String, Object> m);

    int addStoreinfo(Map<String, Object> m);

    //
    Integer addImgForOrder(Map<String, Object> m);
}
