package com.ly.service.vo;

import java.util.List;

public class CommuTreeVo  {


	public String id;
	
	public String text;

	public String state;

	public boolean checked;

	public List<CommuTreeVo> children;

	public CommuTreeVo(String id, String text) {
		this.id = id;
		this.text = text;
	}
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public CommuTreeVo(){
		
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

	public List<CommuTreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<CommuTreeVo> children) {
		this.children = children;
	}

	

}
