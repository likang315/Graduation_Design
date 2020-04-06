package com.ly.entity.app;

public class CodeInfo {
	private int id;
	private String code;// '渠道编码',
	private String codeName;// '渠道名称',
	private String fengongsi;// '所属分公司',
	private int fengongsiNum;// '分公司代号(0:营销中心，1：高新区，2：未央区，3：莲湖区，4：小寨区，5：金花区，6：营业厅中心，7：中高端中心)',

	
	
	public CodeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public CodeInfo(int id, String code, String codeName, String fengongsi,
			int fengongsiNum) {
		super();
		this.id = id;
		this.code = code;
		this.codeName = codeName;
		this.fengongsi = fengongsi;
		this.fengongsiNum = fengongsiNum;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getFengongsi() {
		return fengongsi;
	}

	public void setFengongsi(String fengongsi) {
		this.fengongsi = fengongsi;
	}

	public int getFengongsiNum() {
		return fengongsiNum;
	}

	public void setFengongsiNum(int fengongsiNum) {
		this.fengongsiNum = fengongsiNum;
	}

}
