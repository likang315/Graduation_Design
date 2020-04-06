package com.ly.entity;

import java.io.Serializable;
import java.util.Date;

import com.ly.util.Common;
import com.ly.util.ExcelDataMapper;


/**
 * 基础实体bean
 * @author wenjun
 * @date 2015-10-19
 */
public class BaseBean implements Serializable{
	private static final long serialVersionUID = 1L;
	/**id*/
	private String id;
	/**创建时间*/
	private String createTime;
	/**更新时间*/
	private String modifyTime;
	/**删除标示* Y表示删除, N表示未删除*/
	private String removed;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@ExcelDataMapper(title="创建时间",order=1)
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = Common.formatDate(createTime);
	}
	
	@ExcelDataMapper(title="修改时间",order=2)
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = Common.formatDate(modifyTime);
	}
	
	@ExcelDataMapper(title="是否有效",order=3)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
}
