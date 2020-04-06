package com.ly.entity.foreground;

import com.ly.entity.BaseBean;

/**
 * 房间
 * @author wenjun
 *
 */
public class House extends BaseBean {
	private static final long serialVersionUID = 1L;

	
/*
 * drop table if exists business_house;

   id                   char(36) not null,
   createTime           datetime comment '创建时间',
   modifyTime           datetime comment '修改时间',
   removed              char(1) not null default 'N' comment 'Y表示删除, N表示未删除',
   
   createPeopleId       char(36) comment '创建者ID',
   orgId                char(36) comment '小区ID',
   area                 double comment '面积',
   houseInfo            varchar(500) comment '房间详细信息',


 */
	
	
	//创建者ID
	private String createPeopleId;
	//小区ID
	private String orgId;
	//房间信息
	private String houseInfo;
	//面积
	private String area;
	
	//
	private String orgName;
	
	
	
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCreatePeopleId() {
		return createPeopleId;
	}
	public String getOrgId() {
		return orgId;
	}
	public String getHouseInfo() {
		return houseInfo;
	}
	public String getArea() {
		return area;
	}
	public void setCreatePeopleId(String createPeopleId) {
		this.createPeopleId = createPeopleId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public void setHouseInfo(String houseInfo) {
		this.houseInfo = houseInfo;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}
