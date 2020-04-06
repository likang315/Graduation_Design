package com.ly.entity.app;

import java.util.Date;

public class AppWxMenu {
	
	private int id;//` int(11) NOT NULL,
	private String name;//` varchar(255) NOT NULL COMMENT '菜单名称',
	private String img;//` varchar(255) DEFAULT NULL COMMENT '菜单图片地址',
	private String address;//` varchar(255) NOT NULL COMMENT '菜单地址',
	private int isDel;//` int(11) DEFAULT '0' COMMENT '是否删除（0正常，1删除）',
	private Date createTime;//` datetime DEFAULT NULL COMMENT '添加时间',
	private Date delTime;//` datetime DEFAULT NULL COMMENT '删除时间',
	private String ioc;
	private String phrase;
	public String getPhrase() {
		return phrase;
	}
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getDelTime() {
		return delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	public String getIoc() {
		return ioc;
	}
	public void setIoc(String ioc) {
		this.ioc = ioc;
	}
	
}
