package com.ly.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.ly.entity.background.Account;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.util.Common;

public class BaseController {


	PageView pageView = null;

	// 返回结果
	protected Map<String, Object> map = new HashMap<String, Object>();

	public PageView getPageView(String pageNow, String pagesize) {
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		if (Common.isEmpty(pagesize)) {
			pagesize = "10";
		}
		pageView.setPageSize(Integer.parseInt(pagesize));
		return pageView;
	}

	public Account getAccount(HttpServletRequest request) {
		return (Account) request.getSession().getAttribute("userSession");
	}

	/**
	 * 生成uuid
	 * 
	 * @return
	 */
	public String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 设置返回数据
	 * 
	 * @param map
	 * @param data
	 *            返回数据
	 * @param state
	 *            状态
	 * @param info
	 *            备注信息
	 */
	protected void setMapValueForResult(Map<String, Object> map, Object data,
			String state, String info, int code) {
		map.put(Common.APP_RESULT_DATA, data);
		map.put(Common.APP_RESULT_STATE, state);
		map.put(Common.APP_RESULT_INFO, info);
		map.put(Common.APP_RESULT_CODE, code);
		map.put(Common.APP_RESULT_ERROR_CODE, Common.ERROR_CODE_TEST);
	}


	/**
	 * <p>
	 * Web 服务器反向代理中用于存放客户端原始 IP 地址的 Http header 名字， 若新增其他的需要增加或者修改其中的值。
	 * </p>
	 */
	private static final String[] PROXY_REMOTE_IP_ADDRESS = {
			"X-Forwarded-For", "X-Real-IP" };

	/**
	 * <p>
	 * 获取请求的客户端的 IP 地址。若应用服务器前端配有反向代理的 Web 服务器， 需要在 Web 服务器中将客户端原始请求的 IP 地址加入到
	 * HTTP header 中。 详见 {@link #PROXY_REMOTE_IP_ADDRESS}
	 * </p>
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		for (int i = 0; i < PROXY_REMOTE_IP_ADDRESS.length; i++) {
			String ip = request.getHeader(PROXY_REMOTE_IP_ADDRESS[i]);
			if (ip != null && ip.trim().length() > 0) {
				return getRemoteIpFromForward(ip.trim());
			}
		}
		return request.getRemoteHost();
	}

	/**
	 * <p>
	 * 从 HTTP Header 中截取客户端连接 IP 地址。如果经过多次反向代理， 在请求头中获得的是以“,&lt;SP&gt;”分隔 IP
	 * 地址链，第一段为客户端 IP 地址。
	 * </p>
	 *
	 * @param xforwardIp
	 *            从 HTTP 请求头中获取转发过来的 IP 地址链
	 * @return 客户端源 IP 地址
	 */
	private static String getRemoteIpFromForward(String xforwardIp) {
		int commaOffset = xforwardIp.indexOf(',');
		if (commaOffset < 0) {
			return xforwardIp;
		}
		return xforwardIp.substring(0, commaOffset);
	}

}
