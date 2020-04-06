package com.ly.pulgin.mybatis.plugin;

import java.util.List;

/**
 * //分页封装函数
 * 
 * @param <T>
 */
public class PageView {
	/**
	 * 分页数据
	 */
	@SuppressWarnings(value = { "unchecked" })
	private List records;

	/**
	 * 总页数 这个数是计算出来的
	 * 
	 */
	private long pageCount;

	/**
	 * 每页显示几条记录
	 */
	private int pageSize = 10;

	/**
	 * 默认 当前页 为第一页 这个数是计算出来的
	 */
	private int pageNow = 1;

	/**
	 * 总记录数
	 */
	private long rowCount;

	/**
	 * 从第几条记录开始
	 */
	private int startPage;

	private final int displayPage = 2;

	private int prePageStart;
	private int prePageEnd;

	private int pnexPageStart;
	private int pnexPageEnd;

	public int getPrePageStart() {
		return prePageStart;
	}

	public void setPrePageStart(int prePageStart) {
		this.prePageStart = prePageStart;
	}

	public int getPrePageEnd() {
		return prePageEnd;
	}

	public void setPrePageEnd(int prePageEnd) {
		this.prePageEnd = prePageEnd;
	}

	public int getPnexPageStart() {
		return pnexPageStart;
	}

	public void setPnexPageStart(int pnexPageStart) {
		this.pnexPageStart = pnexPageStart;
	}

	public int getPnexPageEnd() {
		return pnexPageEnd;
	}

	public void setPnexPageEnd(int pnexPageEnd) {
		this.pnexPageEnd = pnexPageEnd;
	}

	public int getDisplayPage() {
		return displayPage;
	}

	/**
	 * 规定显示5个页码
	 */
	private int pagecode = 10;

	public PageView() {
	}

	/**
	 * 要获得记录的开始索引　即　开始页码
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (this.pageNow - 1) * this.pageSize;
	}

	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}

	/**
	 * 使用构造函数，，强制必需输入 每页显示数量　和　当前页
	 * 
	 * @param pageSize
	 *            　　每页显示数量
	 * @param pageNow
	 *            　当前页
	 */
	public PageView(int pageSize, int pageNow) {
		this.pageSize = pageSize;
		this.setPageNow(pageNow);
	}

	/**
	 * 使用构造函数，，强制必需输入 当前页
	 * 
	 * @param pageNow
	 *            　当前页
	 */
	public PageView(int pageNow) {
		this.setPageNow(pageNow);

	}

	/**
	 * 查询结果方法 把　记录数　结果集合　放入到　PageView对象
	 * 
	 * @param rowCount
	 *            总记录数
	 * @param records
	 *            结果集合
	 */
	@SuppressWarnings(value = { "unchecked" })
	public void setQueryResult(long rowCount, List records) {
		setRowCount(rowCount);
		setRecords(records);
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
		setPageCount(this.rowCount % this.pageSize == 0 ? this.rowCount
				/ this.pageSize : this.rowCount / this.pageSize + 1);
		this.prePageStart = this.pageNow - this.displayPage;
		this.prePageEnd = this.pageNow;

		if (prePageStart <= 0)
			this.prePageStart = 1;

		if (pageNow == pageCount) {
			pnexPageStart = 1;
			pnexPageEnd = 0;
		}else{
			this.pnexPageStart = (int) (this.pageCount - this.displayPage);
			
			if (pnexPageStart <= pageNow)
				pnexPageStart = pageNow + 1;
			
			pnexPageEnd=pnexPageStart+displayPage;
			if(pnexPageEnd>this.pageCount)
				pnexPageEnd=(int) this.pageCount;
		}

		

	}

	@SuppressWarnings(value = { "unchecked" })
	public List getRecords() {
		return records;
	}

	@SuppressWarnings(value = { "unchecked" })
	public void setRecords(List records) {
		this.records = records;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
		startPage = (this.pageNow - 1) * this.pageSize;
	}

	public long getPageCount() {
		return pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		startPage = (this.pageNow - 1) * this.pageSize;
	}

	public long getRowCount() {
		return rowCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

}
