package com.ly.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * 网路请求
 * @author mkl_wf
 *
 */
@SuppressWarnings("deprecation")
public class HttpClient {
	
	/**
	 * 模拟post请求
	 * @param url 请求地址
	 * @param param 请求参数
	 * @return
	 * @throws IOException 
	 */
	public static JSONObject HttpPostClient(String url,Map<String,String> param){
		
		JSONObject jsStr = null;
		String result = "";
		
		//建立HttpPost对象
		HttpPost httppost=new HttpPost(url);
		//建立一个NameValuePair数组，用于存储欲传送的参数
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		Iterator<String> iter = param.keySet().iterator();
		while(iter.hasNext()){
			String paramkey = iter.next();
			params.add(new BasicNameValuePair(paramkey,param.get(paramkey)));
		}
		try {
			//添加参数/设置编码
			httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			//发送Post,并返回一个HttpResponse对象
			HttpResponse response = new DefaultHttpClient().execute(httppost);
			if(response.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
				result=EntityUtils.toString(response.getEntity());
				jsStr = JSONObject.fromObject(result);
				return jsStr;
			}else{
				return jsStr;
			}
		} catch (Exception e) {
			result = "{'msg':'程序异常'}";
      	   	jsStr = JSONObject.fromObject(result);
			e.printStackTrace();
		}
		return jsStr;
	}

	/**
	 * 作用：模拟post请求
	 * 思路：主要是将参数写入body
	 * @param url 请求地址
	 * @param param 请求参数
	 * @return
	 *
	 */
	public static JSONObject HttpPostClient(String url,String param){
		
		JSONObject jsStr = null;
		String result = "";
		
		//建立HttpPost对象
		HttpPost httppost=new HttpPost(url);
		//建立一个NameValuePair数组，用于存储欲传送的参数
		try {
			//添加参数/设置编码
			httppost.setEntity(new StringEntity(param,"UTF-8"));
			//发送Post,并返回一个HttpResponse对象
			HttpResponse response = new DefaultHttpClient().execute(httppost);
			if(response.getStatusLine().getStatusCode()==200){//如果状态码为200,就是正常返回
				result=EntityUtils.toString(response.getEntity());
				jsStr = JSONObject.fromObject(result);
				return jsStr;
			}else{
				return jsStr;
			}
		} catch (Exception e) {
			result = "{'msg':'程序异常'}";
			jsStr = JSONObject.fromObject(result);
			e.printStackTrace();
		}
		return jsStr;
	}
	
	/**
	 * 作用：模拟get请求
	 * 思路：
	 * @param url 请求地址
	 * @return
	 * @throws IOException
	 *
	 */
	public static JSONObject HttpGetClient(String url) throws IOException{
		 String result="";
		 JSONObject jsStr = null;
         try {
               // 根据地址获取请求
               HttpGet request = new HttpGet(url);//这里发送get请求
               // 获取当前客户端对象
               DefaultHttpClient httpClient = new DefaultHttpClient();
               // 通过请求对象获取响应对象
               HttpResponse response = httpClient.execute(request);
               
               
               // 判断网络连接状态码是否正常(0--200都数正常)
               if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                   result= EntityUtils.toString(response.getEntity(),"utf-8");
                   jsStr = JSONObject.fromObject(result);
               } 
           } catch (Exception e) {
        	   result = "{'msg':'程序异常'}";
        	   jsStr = JSONObject.fromObject(result);
               e.printStackTrace();
           }
       return jsStr;
	}
	
	/**
	 * 作用：获取请求中的body
	 * 思路：
	 * @param request
	 * @param br
	 * @return
	 *
	 */
	public static String getBodyMsg(HttpServletRequest request, BufferedReader br){
		String inputLine;
		String body_str = "";

		try {
			while((inputLine = br.readLine()) != null){
				body_str += inputLine;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(body_str);
		return body_str;
	}
}
