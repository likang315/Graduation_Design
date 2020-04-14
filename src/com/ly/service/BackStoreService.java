package com.ly.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 门店的后台处理 接口
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-04 11:05
 */
public interface BackStoreService {

    //查询store清单信息
    List<Map<String, Object>> list(Map parame);

    //查询出厂商信息
    List<Map<String, Object>> getVendor();

    //维护门店信息
    String addStore(Map<String, Object> storeInfo, MultipartFile file, HttpServletRequest request) throws IOException;

    //根据用户id查询出来门面信息
    Map<String, Object> getStoreInfoOfId(String id);

    //修改门店信息
    boolean modifyStore(Map storeInfo, MultipartFile file);

    //获取店面信息总条数
    Integer getStoreCount();

    // 删除店面信息
    boolean deleteStore(String id);

    //单条店面信息维护添加
    List<Map<String, Object>> findPhone(String store_shopowner_phone);

    /**
     * 查询门店地址是否已经存在
     */
    List<Map<String, Object>> checkAddress(String store_address);


}
