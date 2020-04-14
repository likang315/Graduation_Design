package com.ly.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 门店的后台处理 Mapper
 */
public interface BackStoreMapper {
    //查询门店清单信息
    List<Map<String, Object>> selectList(Map parame);

    List<Map<String, Object>> selectVendorList();

    // 单条店面信息维护添加
    int insertSigleStore(Map insertSigleStore);

    // 单条店面信息维护添加
    int addStoreAccount(Map insertSigleStore);

    //单条店面信息维护添加
    List<Map<String, Object>> findPhone(String store_shopowner_phone);

    //获取厂商信息
    List<Map<String, Object>> getVendor();

    //插入list门店信息到用户表
    int inserAccountList(List<Map<String, Object>> list);

    //插入list门店信息
    int inserStoreListTemp(List<Map<String, Object>> list);

    //根据id查询出门店信息
    Map<String, Object> getStoreInfoOfId(@Param("id") String id);

    //修改门店信息
    int updateStoreInfo(Map storeInfo);

    //获取店面信息条数
    Integer getStoreCount();

    //查询当前门店在库中的个数
    Integer checkOne(Map m);

    //删除店面信息
    int deleteStore(@Param("id") String id);

    //对插入临时表中的数据进行剔自重
    List<Map<String, Object>> getStoreList1();

    //删除门店临时表的数据
    int deleteStoreListTemp(@Param("createUser") String createUser);

    //查询出最终的可以插入主表的数据
    List<Map<String, Object>> selectfinalStoreList(@Param("createUser") String createUser);

    //插入门店信息主表
    int inserStoreList(List<Map<String, Object>> list);

    /**
     * 查询门店地址是否存在
     *
     * @param
     * @author zhangzhi
     * @date 2018年6月20日下午3:18:57
     */
    List<Map<String, Object>> checkAddress(String store_address);

}
