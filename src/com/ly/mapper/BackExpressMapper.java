package com.ly.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 快递公司，快递员管理
 * @author 殷瑜泰 2017年3月21日下午2:28:57
 *
 */
public interface BackExpressMapper{
	
	//获得快递公司清单
	List<Map<String,Object>> getCompanyList(Map param);
	
	//新增快递公司信息
	int addExpress(Map<String,Object> expressInfo);
	
	//判断当前填写的快递公司在库中是否存在
	int checkExise(Map<String,Object> expressInfo);
	
	//删除快递公司信息
	int deleteExpress(@Param("id") String id);
	
	//获取快递公司的总数量
	Integer getCompanycount();
	//获取快递员的总数量
	Integer getCourierCount();
	//获取快递员的清单信息
	List<Map<String,Object>> getCourierList(Map param);
	//获取到快递公司的信息
	List<Map<String,Object>> getCompany();
	//验证当前用户是否在库
	int cheackCourier(Map map);
	 //添加快递员信息
	int addCourier(Map map);
	
	//将快递员信息添加到临时表中
	int addCourierTemp(List<Map<String,Object>> addCourierTemp);
	
	//从临时表中查询剔除过自重的数据
	List<Map<String,Object>> getCourierGrpAcc(@Param("account") String account);
	//删除临时表中导入的数据，
	int deleteCourierTemp(@Param("account") String account);
	
	//临时表与正式表进行比对，获得最终可以插入到主表的数据,
	 List<Map<String,Object>> getFinalCourier(@Param("account") String account);
	 
	 //以list的形式添加快递员数据到主表
	 int addCourierList(List<Map<String,Object>> lsFinal);
	 
	 //删除快递员信息
	 int deleteCourier(@Param("id") String id);
	 //查询出快递员信息根据id
	 Map<String,Object> getCourierInfo(@Param("id") String id);
	 //修改快递员信息
	 int modifyCourier(Map m);
	
	
	
}
