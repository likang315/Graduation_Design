package com.ly.entity;
/**
 * 政策阅读表
 * @author 文军
 *
 */
public class ActivityRead extends NewBaseBean{
	/**
	 * create table myd_activity_read
(
   id                   char(36) not null,
   Read_time            datetime comment '阅读时间',
   Policy_id            char(36) comment '阅读政策(FK）',
   Read_user            char(36) comment '阅读人',
   delete               char(1) default 'N' comment '删除标示',
   create_user          char(36) comment '创建人',
   create_time          datetime comment '创建时间',
   alter_time           datetime comment '修改时间',
   alter_user           char(36) comment '修改人',
   delete_time          datetime comment '删除时间',
   delete_user          char(36) comment '删除人',
   primary key (id)
);
	 */
	
	private String id;
	private String read_time;
	private String policy_id;
	private String read_user;
	
	public String getId() {
		return id;
	}
	public String getRead_time() {
		return read_time;
	}
	public String getPolicy_id() {
		return policy_id;
	}
	public String getRead_user() {
		return read_user;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setRead_time(String read_time) {
		this.read_time = read_time;
	}
	public void setPolicy_id(String policy_id) {
		this.policy_id = policy_id;
	}
	public void setRead_user(String read_user) {
		this.read_user = read_user;
	}
	
	
	
	
	
}
