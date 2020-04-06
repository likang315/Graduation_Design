package com.ly.entity;

import java.io.Serializable;
import java.util.Date;

public class NewBaseBean implements Serializable{
	private static final long serialVersionUID = -1429700990578455589L;
	
	
	private String is_delete;
	private String create_user;
	private Date create_time;
	private Date modify_time;
	private String modify_user;
	private Date delete_time;
	private String delete_user;
	
	
	
	public String getIs_delete() {
		return is_delete;
	}
	public String getCreate_user() {
		return create_user;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public String getModify_user() {
		return modify_user;
	}
	public Date getDelete_time() {
		return delete_time;
	}
	public String getDelete_user() {
		return delete_user;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}
	public void setDelete_user(String delete_user) {
		this.delete_user = delete_user;
	}
	
	
	
	
}
