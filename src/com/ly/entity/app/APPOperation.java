package com.ly.entity.app;

import java.util.Date;

/**
 * 
 * @author lfy
 * 快递员操作记录
 *
 */
public class APPOperation {
	public int id ;// 主键
	public String order_number;//订单号
	public String postman_phone;//快递员电话
	public int order_state; //订单状态（0接受，1拒绝）
	public Date create_time;//操作时间
	public int delete_flag;//数据状态（0，正常，1删除）
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getPostman_phone() {
		return postman_phone;
	}
	public void setPostman_phone(String postman_phone) {
		this.postman_phone = postman_phone;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
	
	
}
