package com.ly.entity.app;

import java.util.Date;

/**
 * 物资信息
 * @author Administrator
 *
 */
public class Material {

	private Integer id;
	private String material_name;//门店名称
	private Date create_time;
	private String create_phone;
	private Integer delete_flag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
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
