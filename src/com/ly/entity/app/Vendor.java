package com.ly.entity.app;

import java.util.Date;

/**
 * 门店信息表
 * @author Administrator
 *
 */
public class Vendor {

	private Integer id;
	private String vendor_name;//门店名称
	private Date create_time;
	private String create_phone;
	private Integer delete_flag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getCreate_phone() {
		return create_phone;
	}
	public void setCreate_phone(String create_phone) {
		this.create_phone = create_phone;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	
	
	
}
