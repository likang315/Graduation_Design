package com.ly.entity.background;

import java.util.Date;

import com.ly.util.Common;


public class ReadTimes{
	
	private String readPeopleId;
	private String newsId;
	private String readTime;
	
	
	public String getReadPeopleId() {
		return readPeopleId;
	}
	public String getNewsId() {
		return newsId;
	}
	public String getReadTime() {
		return readTime;
	}
	public void setReadPeopleId(String readPeopleId) {
		this.readPeopleId = readPeopleId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public void setReadTime(Date readTime) {
		this.readTime = Common.formatDate(readTime);
	}
	
	
}
