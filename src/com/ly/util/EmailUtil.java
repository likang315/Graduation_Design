package com.ly.util;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;


/**
 * 用org.apache.commons.mail发送普通邮件 
 * @author 13545675856
 *
 */

public class EmailUtil {
	
	private final static String EMAILHOSTNAME = "smtp.exmail.qq.com";// 设置使用发电子邮件的邮件服务器，这里以qq邮箱为例（其它例如：【smtp.163.com】，【smtp.sohu.com】，【smtp.qq.com】）  
	private final static String EMAILADDRESS = "service@makalu.cc";
	private final static String EMAILPASSWORD = "Aa112233";
	private final static String EMAILNICKNAME = "马卡鲁科技有限公司";

    public static void main(String[] args) {  
    	sendCommonEmail("wangfeng@makalu.cc","这是一个测试邮件","<a href='www.casually.cc'>点击这里</a>");
    }  
    
    /**
     * 发送普通邮件
     * @param address 发件人地址
     * @param password 发件人密码
     * @param addressee 收件人地址
     * @param theme 主题
     * @param content 内容
     */
    public static boolean sendCommonEmail(String address,String password,String addressee,String theme,String content){
    	boolean sendType = true;
    	SimpleEmail email = new SimpleEmail();  
    	email.setHostName(EMAILHOSTNAME);// 设置使用发电子邮件的邮件服务器，这里以qq邮箱为例（其它例如：【smtp.163.com】，【smtp.sohu.com】）  
    	try {  
    		// 收件人邮箱  
    		email.addTo(addressee);  
    		// 邮箱服务器身份验证  
    		email.setAuthentication(address, password);  
    		// 发件人邮箱  
    		email.setFrom(address);  
    		// 邮件主题  
    		email.setSubject(theme);  
    		// 邮件内容  
    		email.setMsg(content);  
    		// 发送邮件  
    		email.send();  
    		System.out.println("邮件发送成功!");  
        }catch (EmailException ex) {  
    	 	ex.printStackTrace(); 
    	 	sendType = true;
    	 	System.out.println("邮件发送失败!");  
     	}  
    	return sendType;
    }
    
    /**
     * 系统发送普通邮件
     * @param addressee 收件人地址
     * @param theme 主题
     * @param content 内容
     */
    public static boolean sendCommonEmail(String addressee,String theme,String content){
    	boolean sendType = true;
    	SimpleEmail email = new SimpleEmail();  
    	email.setHostName(EMAILHOSTNAME);// 设置使用发电子邮件的邮件服务器，这里以qq邮箱为例（其它例如：【smtp.163.com】，【smtp.sohu.com】，【smtp.qq.com】）  
    	try {  
    		// 收件人邮箱  
    		email.addTo(addressee);  
    		// 邮箱服务器身份验证  
    		email.setAuthentication(EMAILADDRESS, EMAILPASSWORD);  
    		// 发件人邮箱  
    		email.setFrom(EMAILADDRESS);  
    		// 邮件主题  
    		email.setSubject(theme);  
    		// 邮件内容  
    		email.setMsg(content);  
    		// 发送邮件  
    		email.send();  
    		System.out.println("邮件发送成功!");  
        }catch (EmailException ex) {  
    	 	ex.printStackTrace(); 
    	 	sendType = true;
    	 	System.out.println("邮件发送失败!");  
     	}  
    	return sendType;
    }
    
    /**
     * 发送HTML邮件
     * @param address 发件人邮箱
     * @param password 发件人密码
     * @param nickname 发件人昵称
     * @param addressee 收件人邮箱
     * @param theme 主题
     * @param content 内容（HTML格式）
     * @return
     */
    public static boolean sendHtmlEmail(String address,String nickname,String password,String addressee,String theme,String content){
    	boolean sendType = false;
    	// 不要使用SimpleEmail,会出现乱码问题  
        HtmlEmail email = new HtmlEmail();  
        // SimpleEmail email = new SimpleEmail();  
        try {  
            // 这里是SMTP发送服务器的名字：qq的如下：  
            email.setHostName(EMAILHOSTNAME);  
            // 字符编码集的设置  
            email.setCharset("gbk");  
            // 收件人的邮箱  
            email.addTo(addressee);  
            // 发送人的邮箱  
            email.setFrom(address, nickname);  
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
            email.setAuthentication(address, password);  
            email.setSubject(theme);  
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签  
            email.setMsg(content); //String content = "<h1 style='color:red'>下午3：00会议室讨论</h1>" + " 请准时参加！"
            // 发送  
            email.send();  
  
            System.out.println("邮件发送成功!");  
        } catch (EmailException e) {  
            e.printStackTrace();  
            System.out.println("邮件发送失败!");  
        }  
    	return sendType;
    }
    
    /**
     * 默认服务发送HTML邮件
     * @param addressee 收件人邮箱
     * @param theme 主题
     * @param content 内容（HTML格式）
     * @return
     */
    public static boolean sendHtmlEmail(String addressee,String theme,String content){
    	boolean sendType = false;
    	// 不要使用SimpleEmail,会出现乱码问题  
        HtmlEmail email = new HtmlEmail();  
        // SimpleEmail email = new SimpleEmail();  
        try {  
            // 这里是SMTP发送服务器的名字：qq的如下：  
            email.setHostName(EMAILHOSTNAME);  
            // 字符编码集的设置  
            email.setCharset("gbk");  
            // 收件人的邮箱  
            email.addTo(addressee);  
            // 发送人的邮箱  
            email.setFrom(EMAILADDRESS, EMAILNICKNAME);  
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
            email.setAuthentication(EMAILADDRESS, EMAILPASSWORD);  
            email.setSubject(theme);  
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签  
            email.setMsg(content); //String content = "<h1 style='color:red'>下午3：00会议室讨论</h1>" + " 请准时参加！"
            // 发送  
            email.send();  
  
            System.out.println("邮件发送成功!");  
        } catch (EmailException e) {  
            e.printStackTrace();  
            System.out.println("邮件发送失败!");  
        }  
    	return sendType;
    }
}

