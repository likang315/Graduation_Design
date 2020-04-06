package com.ly.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class Common {
	//后台访问
	public static final String BACKGROUND_PATH = "/WEB-INF/jsp/background";
	//微信端访问
	public static final String APP_PATH = "/WEB-INF/jsp/app";
	//前台访问
	public static final String WEB_PATH = "/WEB-INF/jsp/app";
	
	public static final String DYNAMIC_EXTENSION_URL = "http://test.makalu.cc/WCS/app/transaction/list.html";
	//默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    public static final String STATE = "state";
    public static final String OK = "ok";
    public static final String NO = "no";
    
    
    /**
     * 新闻发布状态
     */
    /**流程处理状态\r\n
	 * INIT  草稿\r\n
	 * CHECK  提交审核中\r\n
	 * ALLOW  允许待发布 待发布状态\r\n
	 * REJECT  拒绝 驳回状态\r\n
	 * END 发布'*/
    /**待发布*/
	public static final String NEW_INIT = "INIT";
	/**提交审核中*/
	public static final String NEW_CHECK = "CHECK";
	/**允许待发布 待发布状态*/
	public static final String NEW_ALLOW = "ALLOW";
	/**拒绝 驳回状态*/
	public static final String NEW_REJECT = "REJECT";
	/**发布*/
	public static final String NEW_END = "END";
	
    
	/**
	 * 公告发布状态
	 */
	/**待发布*/
	public static final String NOTICE_INIT = "INIT";
	/**提交审核中*/
	public static final String NOTICE_CHECK = "CHECK";
	/**允许待发布 待发布状态*/
	public static final String NOTICE_ALLOW = "ALLOW";
	/**拒绝 驳回状态*/
	public static final String NOTICE_REJECT = "REJECT";
	/**发布*/
	public static final String NOTICE_END = "END";
	
	
	
	/**
	 * 报修状态
	 */
	public static final String REPAIR_INIT = "INIT";
	public static final String REPAIR_PROCESS = "PROCESS";
	public static final String REPAIR_END = "END";
	
	
	/**
	 * 业主 物业
	 */
	/**物业*/
	public static final String REPLY_W = "W";
	/**业主*/
	public static final String REPLY_Y = "Y";
	
	
	/**
	 * 客户端统一返回标示
	 */
	/**统一返回  主体数据数组*/
	public static final String APP_RESULT_DATA = "data";
	/**返回状态码 error success*/
	public static final String APP_RESULT_STATE = "state";
	/**统一返回  附件信息*/
	public static final String APP_RESULT_INFO = "info";
	/**统一返回  标示码 成功(0) 失败(-1)*/
	public static final String APP_RESULT_CODE = "code";
	
	/**统一返回  错误编码*/
	public static final String APP_RESULT_ERROR_CODE = "error_code";
	
	
	/**返回标示码 0 -1*/
	public static final int APP_RESULT_CODE_0 = 0;
	public static final int APP_RESULT_CODE_1 = -1;
	
	/**失败*/
	public static final String APP_RESULT_ERROR = "error";
	/**成功*/
	public static final String APP_RESULT_SUCCESS = "success";
	
	/**Y/N**/
	public static final String Y = "Y";
	public static final String N = "N";
	
	/**0 1标示码*/
	public static final String CODE_0 = "0";//
	public static final String CODE_1 = "1";//
	public static final String CODE_2 = "2";//草稿箱
	public static final String CODE_3 = "3";//下架
	public static final String CODE_4 = "4";
	public static final String CODE_5 = "5";
	public static final String CODE_6 = "6";
	public static final String CODE_7 = "7";
	public static final String CODE_8 = "8";
	public static final String CODE_9 = "9";
	
	/**投诉建议  操作编码C:create,R:reply,F:finish,A:abnormal(finish)*/
	public static final String FEEBACK_CODE_C = "C";
	public static final String FEEBACK_CODE_R = "R";
	public static final String FEEBACK_CODE_F = "F";
	public static final String FEEBACK_CODE_A = "A";
	
	/**
	 * 统一错误编码
	 */
	public static final String ERROR_CODE_TEST = "HL_ERR_10001"; //测试，预留
	
	
	
	/**
	 * 图片保存路径
	 */
	public static final String REPAIR_IMG_FILE_PATH = "repair_img_file_path";
	public static final String NEWS_IMG_FILE_PATH = "news_img_file_path";
	public static final String NOTICE_IMG_FILE_PATH = "notice_img_file_path";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * String转换double
	 * @param string
	 * @return double
	 */
	public static double convertSourData(String dataStr) throws Exception{
		if(dataStr!=null&&!"".equals(dataStr)){
			return Double.valueOf(dataStr);
		}
		throw new NumberFormatException("convert error!");
	}
	
	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 使用率计算
	 * 
	 * @return
	 */
	public static String fromUsage(long free, long total) {
		Double d = new BigDecimal(free * 100 / total).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(d);
	}
	/**
	 * 返回当前时间　格式：yyyy-MM-dd hh:mm:ss
	 * @return String
	 */
	public static String fromDateH(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format1.format(new Date());
	}
	/**
	 * 返回当前时间　格式：yyyy-MM-dd
	 * @return String
	 */
	public static String fromDateY(){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}
	/**
	 * 时间格式化
	 * 格式:yyyy-MM-dd HH:mm:ss:SSS
	 */
	public static String formatDate(Date date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(date);
	}
	/**
	 * 用来去掉List中空值和相同项的。
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}

	/**
	 * 返回用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String toIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
		}
		return ip;
		}

	/**
	 * 传入原图名称，，获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            　原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 取得html网页内容 UTF8编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlUTF8(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "UTF-8");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * 取得html网页内容 GBK编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlGBK(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "GBK");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * @param inputStream
	 * @param uncode
	 *            编码 GBK 或 UTF-8
	 * @return
	 * @throws Exception
	 */
	public static String readHtml(InputStream inputStream, String uncode) throws Exception {
		InputStreamReader input = new InputStreamReader(inputStream, uncode);
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		return contentBuf.toString();
	}

	/**
	 * 
	 * @return 返回资源的二进制数据 @
	 */
	public static byte[] readInputStream(InputStream inputStream) {

		// 定义一个输出流向内存输出数据
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 定义一个缓冲区
		byte[] buffer = new byte[1024];
		// 读取数据长度
		int len = 0;
		// 当取得完数据后会返回一个-1
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				// 把缓冲区的数据 写到输出流里面
				byteArrayOutputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		// 得到数据后返回
		return byteArrayOutputStream.toByteArray();

	}
	/**
	 * 修改配置　
	 * 
	 * @param request
	 * @param nodeId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/modifySer")
	public static Map<String, Object> modifySer(String key, String value) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			PropertiesUtils.modifyProperties(key, value);
		} catch (Exception e) {
			dataMap.put("flag", false);
		}
		dataMap.put("flag", true);
		return dataMap;
	}

	/**
	 * 获取当前认证通过的用户名
	 * @return
	 */
	public static String findAuthenticatedUsername() { 
	    String username = null; 
	    Object principal = SecurityContextHolder.getContext() 
	        .getAuthentication().getPrincipal(); 
	    if (principal instanceof UserDetails) { 
	        username = ((UserDetails) principal).getUsername(); 
	    } else { 
	        username = principal.toString(); 
	    } 
	    return username; 
	 }
	
	/**
	 * 获取登录账号的ID
	 * @param request
	 * @return
	 */
	public static String findUserSessionId(HttpServletRequest request) { 
		return request.getSession().getAttribute("userSessionId").toString();
	 }
	
	/**
	 * 获取登录账号的的对象
	 * @param request
	 * @return Object 返回是Object..需要转型为(Account)Object
	 */
	public static Object findUserSession(HttpServletRequest request) { 
	    return (Object)request.getSession().getAttribute("userSession");
	 }
	/**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
	public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    } 
	 /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
 
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1,double v2){
        return div(v1,v2,DEF_DIV_SCALE);
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    
    
    /**
     * 验证手机号码 是否由数字组成
     * @param phoneNum
     * @return
     */
    public static boolean validatePhoneNum(String phoneNum){
    	
    	
    	
    	boolean flag = true;
    	
    	if(null == phoneNum || phoneNum == ""){
    		flag = false;
    		return flag;
    	}
    	
    	char[] p = phoneNum.toCharArray();
    	for (char c : p) {
			if(c<'0' || c>'9'){
				flag = false;
				return flag;
			}
		}
    	return flag;
    }
    
    /**
     * 简单验证密码的合法性(空，长度至少为6)
     * @param password
     * @return
     */
    public static boolean validatePassword(String password){
    	boolean flag = false;
    	if(null == password || password == "" || password.length() < 6){
    		return flag;
    	}else{
    		flag = true;
    	}
    	return flag;
    }
    
    
    /**
     * 报修图片存储路径
     * @param request
     * @return
     */
    public static String getProjectPath(String imgpath){
		StringBuffer sb = new StringBuffer();
		sb.append(PropertiesUtils.findPropertiesKey(imgpath)+"/");
		return sb.toString();
    }
    
    
    /**
     * 订单编号 生成
     * @return
     */
    public static String generateOrderSn(){
    	SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
    	Random rd = new Random();
    	String s = sm.format(new Date());
    	int k = rd.nextInt(10000);
    	if(k<10){
    		return s + "000"+k;
    	}
    	if(k < 100){
    		return s + "00"+k;
    	}
    	if(k <1000 ){
    		return s+ "0" +k;
    	}
    	else{
    		return s + k;
    	}
    }
    
    public static boolean isMobileNO(String mobiles){  
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");  
        Matcher m = p.matcher(mobiles);  
       // System.out.println(m.matches()+"---");  
        return m.matches();  
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
	/**
	 * 格式化
	 * @author Yutai.Yin 2017年3月8日下午4:26:29
	 * @param dateStr
	 * @return
	 */
	 public static String formatDateToDate(String dateStr) {
		    SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		    Date date = null;
		    try {
		      date = sf.parse(dateStr);
		    } catch (ParseException e) {
		      e.printStackTrace();
		    }
		    SimpleDateFormat sff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    return sff.format(date);
		  }
    
    public static void main(String[] args) {
    	/*String s = "E:\\java\\apache-tomcat-7.0.42-windows-x64\\apache-tomcat-7.0.42\\webapps\\homelife2\\";
    	String []str = s.split(File.separator+File.separator);
    	System.out.println(str[str.length-1]);
    	System.out.println(File.separator);*/
    	/*for(int i=0;i<999;i++){
    		String s = generateOrderSn();
    		System.out.println(s);
    		if(s.length() < 22){
    			System.out.println(s);
    		}
    	}*/
    	
    	
	}
    
    /**
	 * 生成随机数
	 * @param length 随机数长度
	 * @param number 是否包含数字
	 * @param isSpecial 是否包含特殊字符
	 * @param isCase 是否大写
	 * @param isCapital 是否小写
	 * @param size 同时生成个数
	 * @return
	 */
	public static List<String> getRandom(Integer length,boolean number,boolean isSpecial,boolean isCase,boolean isCapital,Integer size){
		String num = "0123456789";
		String lowercase = "abcdefghijklmnopqrstuvwxyz";
		String capital = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String special = ",.*-_+!@#$%^&=";
		length = length == 0?1:length;
		size = size == 0?1:size;
		
		String total = "";
		if(!number && !isCase && !isCapital && !isSpecial){
			total += num;
		}else{
			if(number)
				total += num;
			if(isCase)
				total += lowercase;
			
			if(isCapital)
				total += capital;
			
			if(isSpecial)
				total += special;
		}
		
		List<String> resultL = new ArrayList<String>();
		
		for(int i=0;i<size;i++){
			StringBuffer result = new StringBuffer();
			for (int j = 0; j < length; j++) {
				result.append(total.charAt(new Random().nextInt(total.length())));
			}
			System.out.println("第"+ i + "个随机字符串------->" + result.toString());
			resultL.add(result.toString());
		}
		System.out.println("");
		return resultL;
	}
    
}
