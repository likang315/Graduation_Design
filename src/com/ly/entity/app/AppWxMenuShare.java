package com.ly.entity.app;

import java.util.Date;

public class AppWxMenuShare {
	
	private int id;//` int(11) NOT NULL,
	private int groupId;//` int(11) DEFAULT NULL COMMENT '组织Id',
	private int vendorId;//` int(11) NOT NULL COMMENT '厂商id',
	private int brandId;//` int(11) DEFAULT '0' COMMENT '品牌id',
	private String left_menu;//` varchar(255) NOT NULL COMMENT '菜单id，以逗号分隔',
	private int isDel;//` int(11) DEFAULT '0' COMMENT '是否删除（0正常，1删除）',
	private Date createTime;//` datetime DEFAULT NULL COMMENT '添加时间',
	private Date delTime;//` datetime DEFAULT NULL COMMENT '删除时间',
	private int index;//` int(11) DEFAULT NULL COMMENT '主页对应页面id',
	private String bottom_menu;//` varchar(255) DEFAULT NULL COMMENT '底部导航菜单（逗号分隔）',
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getLeft_menu() {
		return left_menu;
	}
	public void setLeft_menu(String left_menu) {
		this.left_menu = left_menu;
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
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getBottom_menu() {
		return bottom_menu;
	}
	public void setBottom_menu(String bottom_menu) {
		this.bottom_menu = bottom_menu;
	}
}
