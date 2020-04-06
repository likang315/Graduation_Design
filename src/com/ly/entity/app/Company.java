package com.ly.entity.app;

import java.util.Date;

/**
 * 快递公司信息
 * @author Administrator
 *
 */
public class Company {

	private Integer id;
	private String company_name;//门店名称
	private String company_address;
	private Date create_time;
	private String  create_phone;
	private Integer delete_flag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
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
