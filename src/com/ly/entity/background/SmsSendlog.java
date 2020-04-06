package com.ly.entity.background;

import java.io.Serializable;

public class SmsSendlog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3508931930174012865L;

	private long id;
	private String phone;
	private String content;
	private String success;
	private String remoteAddr;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

}
