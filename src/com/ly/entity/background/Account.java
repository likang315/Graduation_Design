package com.ly.entity.background;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.ly.util.ExcelDataMapper;
import com.ly.util.JsonDateSerializer;


/**
 * 账号实体表
 */
@SuppressWarnings("serial")
public class Account implements java.io.Serializable {
	
	private int id;

	private String accountName;//账号名
	
	private String roleName;//账号名

	private String password;//密码

	private String description;//说明

	private String state;//账号状态  0 表示停用  1表示启用

	private Date createTime; //创建时间
	
	private int groupId;
	
	private String groupName;
	
	private String area;
	
	private Integer areaId;
	
	private Integer auth_flag;
	
	private Integer manager_id;//` bigint(20) DEFAULT NULL COMMENT '代理商id(当user_flag此字段不可空)',
	private String accountType;//` varchar(20) DEFAULT NULL,
	private Integer deletestatus;//` int(1) DEFAULT '0' COMMENT '逻辑删除状态0:存在1:删除',
	private String token;//` char(36) DEFAULT NULL,
	private String real_name;//` varchar(50) DEFAULT NULL COMMENT '姓名',
	private String id_car;//` char(18) DEFAULT NULL COMMENT '身份证',
	private String code;//` varchar(50) DEFAULT NULL COMMENT '渠道编码',
	private String section_name;//` varchar(100) DEFAULT NULL COMMENT '渠道名称',
	private Integer employeesType;//` int(2) DEFAULT '0' COMMENT '0为内部店员，1为外部店员',
	private Integer employeesClass;//` int(2) DEFAULT '0' COMMENT '必填（0：营业人员；1：客户经理；2：代理商；3：社区直销；4：校园直销；5：其他；6：客服人员；7：地市客服人员）',
	private String employeesNum;//` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '员工编码',
	private Integer startIntegral;//` int(20) DEFAULT '0' COMMENT '初始积分',
	private Integer taskIntegral;//` int(50) DEFAULT NULL COMMENT '任务积分',
	private String baseStation1;//` varchar(20) DEFAULT NULL COMMENT '基站1',
	private String baseStation2;//` varchar(20) DEFAULT NULL COMMENT '基站2',
	private String baseStation3;//` varchar(20) DEFAULT NULL COMMENT '基站3',
	private String userName;//` varchar(60) DEFAULT NULL,
	private String dianyuanNum;//` varchar(20) DEFAULT NULL COMMENT '店员编码',
	private Integer user_flag;//` int(11) DEFAULT '0' COMMENT '用户标示(1 普通用 ,2 代理商)注册用户默认为0',
	private String longitude;//` varchar(20) DEFAULT '0' COMMENT '经度',
	private String latitude;//` varchar(20) DEFAULT '0' COMMENT '纬度',
	private String auth_reason;//` varchar(100) DEFAULT NULL COMMENT '审核通过提示',
	private String city;//` varchar(255) DEFAULT NULL COMMENT '地市',
	private String company;
	public Integer getAuth_flag() {
		return auth_flag;
	}

	public void setAuth_flag(Integer auth_flag) {
		this.auth_flag = auth_flag;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}


	/**小区id*/
	private String orgId;
	
	@ExcelDataMapper(title="id",order=1)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ExcelDataMapper(title="账号名称",order=2)
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@ExcelDataMapper(title="账号状态",order=3)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 时间格式化
	 * @return
	 */
	@ExcelDataMapper(title="创建时间",order=4)
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountName=" + accountName + ", password=" + password + ",description=" + description + ", state=" + state + ", createTime=" + createTime + "]";
	}
	@ExcelDataMapper(title="描述",order=5)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getManager_id() {
		return manager_id;
	}

	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(Integer deletestatus) {
		this.deletestatus = deletestatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getId_car() {
		return id_car;
	}

	public void setId_car(String id_car) {
		this.id_car = id_car;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	public Integer getEmployeesType() {
		return employeesType;
	}

	public void setEmployeesType(Integer employeesType) {
		this.employeesType = employeesType;
	}

	public Integer getEmployeesClass() {
		return employeesClass;
	}

	public void setEmployeesClass(Integer employeesClass) {
		this.employeesClass = employeesClass;
	}

	public String getEmployeesNum() {
		return employeesNum;
	}

	public void setEmployeesNum(String employeesNum) {
		this.employeesNum = employeesNum;
	}

	public Integer getStartIntegral() {
		return startIntegral;
	}

	public void setStartIntegral(Integer startIntegral) {
		this.startIntegral = startIntegral;
	}

	public Integer getTaskIntegral() {
		return taskIntegral;
	}

	public void setTaskIntegral(Integer taskIntegral) {
		this.taskIntegral = taskIntegral;
	}

	public String getBaseStation1() {
		return baseStation1;
	}

	public void setBaseStation1(String baseStation1) {
		this.baseStation1 = baseStation1;
	}

	public String getBaseStation2() {
		return baseStation2;
	}

	public void setBaseStation2(String baseStation2) {
		this.baseStation2 = baseStation2;
	}

	public String getBaseStation3() {
		return baseStation3;
	}

	public void setBaseStation3(String baseStation3) {
		this.baseStation3 = baseStation3;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDianyuanNum() {
		return dianyuanNum;
	}

	public void setDianyuanNum(String dianyuanNum) {
		this.dianyuanNum = dianyuanNum;
	}

	public Integer getUser_flag() {
		return user_flag;
	}

	public void setUser_flag(Integer user_flag) {
		this.user_flag = user_flag;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAuth_reason() {
		return auth_reason;
	}

	public void setAuth_reason(String auth_reason) {
		this.auth_reason = auth_reason;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
