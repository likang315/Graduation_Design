package com.ly.entity.app;

import java.util.Date;

public class APPStoreSupplies {
	 public int  id ;//主键,
	 public String store_name;//门店名称
	 public String channel_code;//门店渠道编码',
	 public String store_shopowner_phone;//上报人电话',
	 public String store_shopowner_name;//上报人姓名',
	 public String materialContent;//物资名称',
	 public String materialNumber;//物资数量',
	 public String isdelete;//是否删除',
	 public Date report_time;//上报时间',
	 public Date delete_time;//删除时间',
	 public String expanding_demand;//扩展需求',
	 public String examine;//审核标示，0未审核，1审核通过，2审核不通过',
	 public String examine_reason;//驳回原因',
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getChannel_code() {
		return channel_code;
	}
	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}
	public String getStore_shopowner_phone() {
		return store_shopowner_phone;
	}
	public void setStore_shopowner_phone(String store_shopowner_phone) {
		this.store_shopowner_phone = store_shopowner_phone;
	}
	public String getStore_shopowner_name() {
		return store_shopowner_name;
	}
	public void setStore_shopowner_name(String store_shopowner_name) {
		this.store_shopowner_name = store_shopowner_name;
	}
	public String getMaterialContent() {
		return materialContent;
	}
	public void setMaterialContent(String materialContent) {
		this.materialContent = materialContent;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	public Date getReport_time() {
		return report_time;
	}
	public void setReport_time(Date report_time) {
		this.report_time = report_time;
	}
	public Date getDelete_time() {
		return delete_time;
	}
	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}
	public String getExpanding_demand() {
		return expanding_demand;
	}
	public void setExpanding_demand(String expanding_demand) {
		this.expanding_demand = expanding_demand;
	}
	public String getExamine() {
		return examine;
	}
	public void setExamine(String examine) {
		this.examine = examine;
	}
	public String getExamine_reason() {
		return examine_reason;
	}
	public void setExamine_reason(String examine_reason) {
		this.examine_reason = examine_reason;
	}
}
