package com.ly.entity.foreground;


import com.ly.entity.BaseBean;
import com.ly.util.ExcelDataMapper;


/**
 * 小区
 * @author wenjun
 *
 */
public class Community extends BaseBean{
	private static final long serialVersionUID = 1L;
	
	/**
	 * id` char(36) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `removed` char(1) NOT NULL DEFAULT 'N' COMMENT 'Y表示删除, N表示未删除',
  `name` varchar(36) DEFAULT NULL COMMENT '小区名称',
  `intro` varchar(1024) DEFAULT NULL COMMENT '小区介绍',
  `createPeopleId` char(36) DEFAULT NULL COMMENT '创建者ID',
  `groupId` int(11) DEFAULT NULL COMMENT '组织架构id',
	 */
	
	//小区名称
	private String name;
	//小区介绍
	private String intro;
	//创建者ID
	private String createPeopleId;
	//组织id
	private int groupId;
	
	private String groupName;
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@ExcelDataMapper(title="小区名称",order=4)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelDataMapper(title="小区介绍",order=5)
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public String getCreatePeopleId() {
		return createPeopleId;
	}
	public void setCreatePeopleId(String createPeopleId) {
		this.createPeopleId = createPeopleId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
