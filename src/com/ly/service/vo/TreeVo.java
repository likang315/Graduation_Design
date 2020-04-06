package com.ly.service.vo;

import java.util.List;

public class TreeVo  {


	public int id;

	public String sId;
	
	public String text;

	public String state;

	public boolean checked;
	
	public boolean selected;

	public List<TreeVo> children;
	

	public TreeVo(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	
	
	public boolean isSelected() {
		return selected;
	}



	public void setSelected(boolean selected) {
		this.selected = selected;
	}



	public TreeVo(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<TreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<TreeVo> children) {
		this.children = children;
	}

	public String getsId() {
		return sId;
	}

	/**
	 * 字符串类型id
	 * @param sId
	 */
	public void setsId(String sId) {
		this.sId = sId;
	}
	
	

}
