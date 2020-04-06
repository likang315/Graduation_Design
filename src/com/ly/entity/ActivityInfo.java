package com.ly.entity;

/**
 * 政策信息表
 * @author wenjun
 *
 */
public class ActivityInfo extends NewBaseBean{
	/*
	 * create table myd_activity_info
(
   id                   char(36) not null comment '政策编号（PK）',
   title                varchar(500) comment '政策标题',
   content              text comment '政策内容',
   Start_time           datetime comment '开始时间',
   End_time             datetime comment '结束时间',
   Is_handle            char(1) comment '是否允许办理(1代表可以办理 2代表不能办理)',
   delete               char(1) default 'N' comment '删除标示',
   create_user          char(36) comment '创建人',
   create_time          datetime comment '创建时间',
   alter_time           datetime comment '修改时间',
   alter_user           char(36) comment '修改人',
   delete_time          datetime comment '删除时间',
   delete_user          char(36) comment '删除人',
   primary key (id)
);*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4189877955623526282L;
	private String id;
	private String title;
	private String content;
	private String end_time;
	private String start_time;
	private String is_handle;
	private long counts;
	private String description ; //简单描述
	private String activity_type;//活动类型'活动分类(A:套餐\B:充值\C:流量\D:购机\E:宽带)',
	private long groupId;
	
	private String flag;
	
	private String mold;
	
	private String priority;
	
	public ActivityInfo() {
		// TODO Auto-generated constructor stub
	}

	public ActivityInfo(String id, String title, String content, String end_time, String start_time, String is_handle,
			long counts, String description, String activity_type, long groupId, String flag, String mold,
			String priority) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.end_time = end_time;
		this.start_time = start_time;
		this.is_handle = is_handle;
		this.counts = counts;
		this.description = description;
		this.activity_type = activity_type;
		this.groupId = groupId;
		this.flag = flag;
		this.mold = mold;
		this.priority = priority;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getIs_handle() {
		return is_handle;
	}

	public void setIs_handle(String is_handle) {
		this.is_handle = is_handle;
	}

	public long getCounts() {
		return counts;
	}

	public void setCounts(long counts) {
		this.counts = counts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivity_type() {
		return activity_type;
	}

	public void setActivity_type(String activity_type) {
		this.activity_type = activity_type;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMold() {
		return mold;
	}

	public void setMold(String mold) {
		this.mold = mold;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ActivityInfo [id=" + id + ", title=" + title + ", content=" + content + ", end_time=" + end_time
				+ ", start_time=" + start_time + ", is_handle=" + is_handle + ", counts=" + counts + ", description="
				+ description + ", activity_type=" + activity_type + ", groupId=" + groupId + ", flag=" + flag
				+ ", mold=" + mold + ", priority=" + priority + "]";
	}
	
	
	
	
	
	
}
