package com.ly.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SmsSend {
	public static void main(String[] args) {
		SmsSend api = new SmsSend();
		String httpResponse = api.testSend();
		try {
			JSONObject jsonObj = new JSONObject(httpResponse);
			int error_code = jsonObj.getInt("error");
			String error_msg = jsonObj.getString("msg");
			if (error_code == 0) {
				System.out.println("Send message success.");
			} else {
				System.out.println("Send message failed,code is " + error_code
						+ ",msg is " + error_msg);
			}
		} catch (JSONException ex) {
			Logger.getLogger(SmsSend.class.getName()).log(Level.SEVERE, null,
					ex);
		}

		httpResponse = api.testStatus();
		try {
			JSONObject jsonObj = new JSONObject(httpResponse);
			int error_code = jsonObj.getInt("error");
			if (error_code == 0) {
				int deposit = jsonObj.getInt("deposit");
				System.out.println("Fetch deposit success :" + deposit);
			} else {
				String error_msg = jsonObj.getString("msg");
				System.out.println("Fetch deposit failed,code is " + error_code
						+ ",msg is " + error_msg);
			}
		} catch (JSONException ex) {
			Logger.getLogger(SmsSend.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}

	public boolean sendSms(String mobile, int id, int type) {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api",
				"api"));
		WebResource webResource = client
				.resource("http://sms-api.luosimao.com/v1/send.json");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("mobile", mobile);
		formData.add("id", id);
		formData.add("type",type);
		//用 POST 请求来发送一个实体到指定的 Web 资源并且接收另一个实体
		ClientResponse response = webResource.type(
				MediaType.APPLICATION_FORM_URLENCODED).post(
				ClientResponse.class, formData);
		String textEntity = response.getEntity(String.class);
		
		
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(textEntity);
			int error_code = jsonObj.getInt("error");
			String error_msg = jsonObj.getString("msg");
			if (error_code == 0) {
				return true;
			} else {
				System.out.println("Send message failed,code is " + error_code
						+ ",msg is " + error_msg);
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}

	private String testSend() {
		// just replace key here
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api",
				"api"));
		WebResource webResource = client
				.resource("http://sms-api.luosimao.com/v1/send.json");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("mobile", "13110455370");
		ClientResponse response = webResource.type(
				MediaType.APPLICATION_FORM_URLENCODED).post(
				ClientResponse.class, formData);
		String textEntity = response.getEntity(String.class);
		int status = response.getStatus();
		// System.out.print(textEntity);
		// System.out.print(status);
		return textEntity;
	}

	private String testStatus() {
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api",
				"key-d609b769db914a4d959bae3414ed1f7X"));
		WebResource webResource = client
				.resource("http://sms-api.luosimao.com/v1/status.json");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		ClientResponse response = webResource.get(ClientResponse.class);
		String textEntity = response.getEntity(String.class);
		int status = response.getStatus();
		// System.out.print(status);
		// System.out.print(textEntity);
		return textEntity;
	}

	/**
	 * 发送短信
	 * @param mobile 接受电话
	 * @param message 接受的信息
	 * @return
	 */
	public boolean sendSmsCustomer(String mobile,String message) {
		Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api","api"));
        WebResource webResource = client.resource("http://sms-api.luosimao.com/v1/send.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("mobile", mobile);
        formData.add("message", message + "【XUPT】");
        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        System.out.print(textEntity);
        System.out.print(status);
        JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(textEntity);
			int error_code = jsonObj.getInt("error");
			String error_msg = jsonObj.getString("msg");
			if (error_code == 0) {
				return true;
			} else {
				System.out.println("Send message failed,code is " + error_code
						+ ",msg is " + error_msg);
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}

}
