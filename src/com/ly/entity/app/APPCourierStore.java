package com.ly.entity.app;

import java.util.Date;

/**
 * 
 * @author lfy
 * 快递员实时坐标
 *
 */
public class APPCourierStore {
	public int id ;// 主键
	public String logistics;//快递员电话
	public String courier_Phone;//快递员电话
	public Double courier_longitude; //快递员经度
	public Double courier_latitude;//快递员当前纬度
	public Date actualTime;//实时时间
	
	
	
	public String getLogistics() {
		return logistics;
	}
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}
	public Date getActualTime() {
		return actualTime;
	}
	public void setActualTime(Date actualTime) {
		this.actualTime = actualTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourier_Phone() {
		return courier_Phone;
	}
	public void setCourier_Phone(String courier_Phone) {
		this.courier_Phone = courier_Phone;
	}
	public Double getCourier_longitude() {
		return courier_longitude;
	}
	public void setCourier_longitude(Double courier_longitude) {
		this.courier_longitude = courier_longitude;
	}
	public Double getCourier_latitude() {
		return courier_latitude;
	}
	public void setCourier_latitude(Double courier_latitude) {
		this.courier_latitude = courier_latitude;
	}
	
}
