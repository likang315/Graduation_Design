package com.ly.entity.background;

import java.io.Serializable;

public class TradeLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8441218886138485041L;

	private int id;
	private String out_trade_no;

	private String trade_no;

	private String trade_status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

}
