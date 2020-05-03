package com.ly.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;

/**
 * TODO(likang):获取经纬度
 *
 */
public class CourierNumber {
	
	public static void main(String[] args) throws IOException {
		System.out.println(Getcontext2("西安市沣东新城建章路南段"));
	}
	
	public static Map<String, Object> Getcontext(String address) throws IOException {
		System.err.println(address);
		String address2 = URLDecoder.decode(address.trim(),"UTF-8");
		String url = "http://apis.map.qq.com/ws/geocoder/v1/?address=" + address2 + "&key=HLWBZ-LONHO-JIVWK-S4T5P-NPJBH-JTF3J";
		JSONObject xx = HttpClient.HttpGetClient(url);
		Map<String, Object> result = (Map<String, Object>)xx.get("result");
		Map<String, Object> temp = (Map<String, Object>)result.get("address_components");
		Map<String, Object> ll = (Map<String, Object>)result.get("location");
		ll.put("area", temp.get("district"));
		ll.put("city", temp.get("city"));
		System.err.println(ll);
		return ll;
	} 
	
	public static Map<String, Object> Getcontext2(String address) throws IOException {
		System.err.println(address);
		if("西安市沣东新城建章路南段".equals(address)){
			System.err.println("进来了");
		}
		//String address2 = URLDecoder.decode(address.trim(),"UTF-8");
		String url = "http://apis.map.qq.com/ws/geocoder/v1/?address=" + address.trim() + "&key=HLWBZ-LONHO-JIVWK-S4T5P-NPJBH-JTF3J";
		JSONObject xx = HttpClient.HttpGetClient(url);
		Map<String, Object> ll = new HashMap<>();
		if(!"347".equals(xx.get("status").toString())){
			Map<String, Object> result = (Map<String, Object>)xx.get("result");
			Map<String, Object> temp = (Map<String, Object>)result.get("address_components");
			ll = (Map<String, Object>)result.get("location");
			ll.put("area", temp.get("district"));
			ll.put("city", temp.get("city"));
			ll.put("state", "ok");
		}else{
			ll.put("state","no");
		}
		System.err.println(ll);
		return ll;
	} 
	

}
