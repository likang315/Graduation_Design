package com.ly.entity.background;

import java.util.List;

import com.ly.entity.BaseBean;

/**
 * 组织架构
 * 
 * @author wenjun
 *
 */
public class Group extends BaseBean {
	private static final long serialVersionUID = 309385683376561127L;

	private int parentId;

	private String name;

	private String description;

	private String removeTime;

	private int level;
	private List<Group> child;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemoveTime() {
		return removeTime;
	}

	public void setRemoveTime(String removeTime) {
		this.removeTime = removeTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Group> getChild() {
		return child;
	}

	public void setChild(List<Group> child) {
		this.child = child;
	}

}
