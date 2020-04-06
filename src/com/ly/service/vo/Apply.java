package com.ly.service.vo;

public class Apply {
	private Integer id;//
	private String proposerName;// 申请员工姓名',
	private String proposerTell;//申请人电话
	private String employeesNum;// 申请店员编码',
	private String oldCode;// 申请员工当前渠道编码',
	private String oldControlName;//原有渠道名称
	private String oldArea;//原片区
	private String newCode;// 申请员工转向渠道编码',
	private String newControlName;//变更后渠道名称
	private String newArea;//变更后片区
	private Integer isSuccess;// 状态（0处理中，1处理成功，2处理失败）',
	private String oldConductorTell;// 当前渠道经理电话
	private String oldConductorName;// 当前所属渠道经理姓名
	private String newConductorTell;// 调整后的渠道经理电话
	private String newConductorName;// 调整后的渠道经理的姓名
	private String time;//申请日期
	private Integer oldGroupId;//变更前的组织id
	private Integer newGroupId;//变更后的组织id

	
	
	
	public String getOldArea() {
		return oldArea;
	}

	public void setOldArea(String oldArea) {
		this.oldArea = oldArea;
	}

	public String getNewArea() {
		return newArea;
	}

	public void setNewArea(String newArea) {
		this.newArea = newArea;
	}

	public String getProposerTell() {
		return proposerTell;
	}

	public void setProposerTell(String proposerTell) {
		this.proposerTell = proposerTell;
	}

	public String getOldControlName() {
		return oldControlName;
	}

	public void setOldControlName(String oldControlName) {
		this.oldControlName = oldControlName;
	}

	public String getNewControlName() {
		return newControlName;
	}

	public void setNewControlName(String newControlName) {
		this.newControlName = newControlName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProposerName() {
		return proposerName;
	}

	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}

	public String getEmployeesNum() {
		return employeesNum;
	}

	public void setEmployeesNum(String employeesNum) {
		this.employeesNum = employeesNum;
	}

	public String getOldCode() {
		return oldCode;
	}

	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}

	public String getNewCode() {
		return newCode;
	}

	public void setNewCode(String newCode) {
		this.newCode = newCode;
	}

	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getOldConductorTell() {
		return oldConductorTell;
	}

	public void setOldConductorTell(String oldConductorTell) {
		this.oldConductorTell = oldConductorTell;
	}

	public String getOldConductorName() {
		return oldConductorName;
	}

	public void setOldConductorName(String oldConductorName) {
		this.oldConductorName = oldConductorName;
	}

	public String getNewConductorTell() {
		return newConductorTell;
	}

	public void setNewConductorTell(String newConductorTell) {
		this.newConductorTell = newConductorTell;
	}

	public String getNewConductorName() {
		return newConductorName;
	}

	public void setNewConductorName(String newConductorName) {
		this.newConductorName = newConductorName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getOldGroupId() {
		return oldGroupId;
	}

	public void setOldGroupId(Integer oldGroupId) {
		this.oldGroupId = oldGroupId;
	}

	public Integer getNewGroupId() {
		return newGroupId;
	}

	public void setNewGroupId(Integer newGroupId) {
		this.newGroupId = newGroupId;
	}
	
	
	
}
