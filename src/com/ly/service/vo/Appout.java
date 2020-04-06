package com.ly.service.vo;

import net.sf.json.JSONObject;

public class Appout {
	public int code;
	public String result;
	public Object data;

	@Override
	public String toString() {
		JSONObject object = JSONObject.fromObject(data);
		object.put("result", result);
		object.put("code", code);
		return object.toString();
	}

}