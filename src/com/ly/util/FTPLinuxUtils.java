package com.ly.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import sun.net.ftp.FtpClient;




public  class  FTPLinuxUtils {
	/**
	 * 文件上传
	 * @param host 主机
	 * @param port 端口
	 * @param username 用户名
	 * @param password 密码
	 * @param localImageAddress 图片在本地的路径
	 * @return
	 * 
	 * */
	public boolean uploadFile(String host, String port, String username, String password, String remoteFileName, String localImageAddress,String uploadAddress){
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(host, new Integer(port).intValue());
			ftpClient.login(username, password);

			int code = ftpClient.getReplyCode();
			System.out.println(code);//如果reply返回230就算成功了，如果返回530密码用户名错误或当前用户无权限
			if (!FTPReply.isPositiveCompletion(code)) {
				ftpClient.disconnect();
				System.out.println("连接失败");
				return flag;
			}
			System.out.println("连接成功");
			//设置上传路径
			ftpClient.changeWorkingDirectory(uploadAddress);
			ftpClient.setBufferSize(1024);
			ftpClient.setControlEncoding("GBK");
			
			//设置文件上传类型(二进制)
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			
			//创建文件夹
			//ftpClient.makeDirectory("");
			File file = new File(localImageAddress);
			InputStream input = new FileInputStream(file);
			flag = ftpClient.storeFile(remoteFileName, input);//remote:表示到服务器后的名字(即改名)
			System.out.println(flag);
			System.out.println("上传成功");
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("FTP客户端出错");
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("关闭FTP连接发生异常");
			}
		}
		return flag;
	}
	

	/**
	 * 文件上传
	 * @param host 主机
	 * @param port 端口
	 * @param username 用户名
	 * @param password 密码
	 * @param localImageAddress 图片在本地的路径
	 * @return
	 * 
	 * */
	public boolean uploadFile(String host, Integer port, String username, String password, String remoteFileName, InputStream localInputStream,String uploadAddress){
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(host,port);
			ftpClient.login(username, password);
			
			int code = ftpClient.getReplyCode();
			System.out.println(code);//如果reply返回230就算成功了，如果返回530密码用户名错误或当前用户无权限
			if (!FTPReply.isPositiveCompletion(code)) {
				ftpClient.disconnect();
				System.out.println("连接失败");
				return flag;
			}
			System.out.println("连接成功");
			//设置上传路径
			ftpClient.changeWorkingDirectory(uploadAddress);
			ftpClient.setBufferSize(1024);
			//ftpClient.setControlEncoding("GBK");
			ftpClient.setControlEncoding("UTF-8");
			
			//设置文件上传类型(二进制)
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			//客户端告诉服务器开通一个端口来传输数据
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);
			
			//创建文件夹
			//ftpClient.makeDirectory("");
			flag = ftpClient.storeFile(new String(remoteFileName.getBytes("UTF-8"),"iso-8859-1"), localInputStream);//remote:表示到服务器后的名字(即改名)
			System.out.println(flag);
			System.out.println("上传成功");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("FTP客户端出错");
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("关闭FTP连接发生异常");
			}
		}
		return flag;
	}
	
	/**
	 * 删除
	 * 
	 * */
	public boolean deleteFile(String host, String port, String username, String password, String remoteAddress, String deleteFileName){
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(host, new Integer(port).intValue());
			ftpClient.login(username, password);

			int code = ftpClient.getReplyCode();
			System.out.println(code);//如果reply返回230就算成功了，如果返回530密码用户名错误或当前用户无权限
			if (!FTPReply.isPositiveCompletion(code)) {
				ftpClient.disconnect();
				System.out.println("连接失败");
				return flag;
			}
			System.out.println("连接成功");
			if(remoteAddress != null && remoteAddress != ""){
				ftpClient.changeWorkingDirectory(remoteAddress);//切换到该目录下
			}
			FTPFile[] fs = ftpClient.listFiles();
			if (fs != null) {
				for (int i = 0; i < fs.length; i++) {
					FTPFile file = fs[i];
					String f=new String(file.getName().getBytes("GBK"), "ISO-8859-1");
					if(deleteFileName.equals(f)){
						ftpClient.deleteFile(file.getName());
						System.out.println("删除成功");
						flag = true;
						break;
					}
				}
			} else {
				System.out.println("该文件夹是空的");
				return flag;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
	/**
	 * 下载
	 * @param host 主机
	 * @param port 端口
	 * @param username 用户名
	 * @param password 密码
	 * @param remoteAddress 文件地址
	 * @param downFileName 需要下载的文件名
	 * @param localAddress 保存路径
	 * @return 返回图片下载后所在路径
	 * */
	public boolean downFile(String host, String port, String username, String password, String remoteAddress, String downFileName, String localAddress){
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		OutputStream out = null;
		try {
			ftpClient.connect(host, new Integer(port).intValue());
			ftpClient.login(username, password);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.out.println("登录失败");
				ftpClient.disconnect();
			}
			System.out.println("登录成功");
			//ftpClient.changeWorkingDirectory(remoteAddress);//切换目录
			FTPFile[] fs = ftpClient.listFiles();
			for (int i = 0; i < fs.length; i++) {
				//解决转码
				FTPFile file = fs[i];
				String f = new String(file.getName().getBytes("GBK"), "ISO-8859-1");
				//String fn = new String(bytes, "UTF-8");
				if (downFileName.equals(f)) {
					//写操作,将其写到本地中
					File localfile = new File(localAddress+file.getName());
					out = new FileOutputStream(localfile);
					flag = ftpClient.retrieveFile(file.getName(), out);
					System.out.println("下载成功");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
				ftpClient.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
}
