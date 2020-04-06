package com.ly.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @Author casually	
 * @Date 2016年04月20日 14:07:27 
 * @E-mail casually@casually.link
 * @content xml文件处理，进行检查内容，将xml信息插入数据库
 *
 */
public class XmlRead {

	public static void main(String[] args) {
/*
		String filePath = "C:\\Users\\hlf001\\Desktop\\营销中心渠道信息.xls";
		
		Object obj = new domeName();//bean类
		
		List<Object> parameterNames =  new ArrayList<Object>();//定义需要操作的属性名集合
		parameterNames.add("name");
		parameterNames.add("password");
		
		List<HashMap<Object,Object>> listMap = getAllByExcel(filePath,parameterNames);//获取所有的数据
		HashMap<Object,Object> parameterValues = new HashMap<Object,Object>();//取出其中的值
		
		System.out.println("=========================分割线======================================");
		
		//循环进行bean属性set注入dome
		for(int i = 0;i<listMap.size();i++){
			parameterValues = listMap.get(i);
			getPamarter(obj,parameterNames,parameterValues);
		}
		
		System.out.println("=========================分割线======================================");
		
		//检索dome
		List<String> lists = isExist(filePath, parameterNames, "password", "QD西安华中通讯广场有限公司(北大街华中王者合作厅)", "name");
		for(String string : lists) {
			System.out.println(string);
		}*/
		
	}
	
	/**
	 * 查询指定目录中电子表格中所有的数据(根据对象查询)
	 * @param file 文件完整路径
	 * @param Obj 要查询的对象
	 * @return
	 */
	public static List<HashMap<Object,Object>> getAllByExcel(String file,Object Obj) {
		List<HashMap<Object,Object>> listMap = new ArrayList<HashMap<Object,Object>>();
		List<Object> parameterNames = new ArrayList<Object>();
		Field[] fields=Obj.getClass().getDeclaredFields(); 
	    for(int i=0;i<fields.length;i++){  
	        System.out.println(fields[i].getType());  
	        parameterNames.add(fields[i].getName());  
	    }  
		
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = rs.getRows();// 得到所有的行

			for (int i = 1; i < rows; i++) {// 第一个是行数，第二个是列数
				HashMap<Object,Object> mapValues = new HashMap<Object,Object>();//注意list会存放map地址，所以应每次存放后从新定义map
				for (int j = 0; j < clos; j++) {
					String data = rs.getCell(j, i).getContents();
					mapValues.put(parameterNames.get(j),data);//将取到的xml值出入到对应的属性
				}
				listMap.add(mapValues);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listMap;
	}
	
	
	/**
	 * 查询指定目录中电子表格中所有的数据(根据属性名集合查询)
	 * @param file 文件完整路径
	 * @param parameterNames 要写入的属性名集合
	 * @return
	 */
	public static List<HashMap<Object,Object>> getAllByExcel(String file,List<Object> parameterNames) {
		
		List<HashMap<Object,Object>> listMap = new ArrayList<HashMap<Object,Object>>();
		
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = rs.getRows();// 得到所有的行

			for (int i = 2; i < rows; i++) {// 第一个是行数，第二个是列数
				HashMap<Object,Object> mapValues = new HashMap<Object,Object>();//注意list会存放map地址，所以应每次存放后从新定义map
				for (int j = 0; j < clos; j++) {
					/*String data = rs.getCell(j, i).getContents();*/														
					if(j!=18){						
						String data = rs.getCell(j, i).getContents();
						if(j==1){
							data=data.replaceAll(" ", "");
						}
						try {
							mapValues.put(parameterNames.get(j),data);//将取到的xml值出入到对应的属性	
						} catch (Exception e) {
							e.printStackTrace();
							HashMap<Object,Object> errorMap = new HashMap<Object,Object>();
							errorMap.put("errorInfo","errorInfo");
							listMap.add(errorMap);
							return listMap;
						}	 
					}else{						
						 String data = rs.getCell(j, i).getContents();							
						 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						 Date date = sdf.parse(data);
						 data=sdf.format(date);
						 System.out.println(data);
						 try {
								mapValues.put(parameterNames.get(j),data);//将取到的xml值出入到对应的属性	
							} catch (Exception e) {
								e.printStackTrace();
								HashMap<Object,Object> errorMap = new HashMap<Object,Object>();
								errorMap.put("errorInfo","errorInfo");
								listMap.add(errorMap);
								return listMap;
							}	
					}
												
				}
				listMap.add(mapValues);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<Object,Object> errorMap = new HashMap<Object,Object>();
			errorMap.put("errorInfo","errorInfo");
			listMap.add(errorMap);
		}
		return listMap;
	}
	
	/**
	 * 检索xml中是否包含指定的数据，并返回特定的值
	 * @param filePath 文件路劲
	 * @param parameterNames 要检索的字段集合
	 * @param searchName 要检索的字段名
	 * @param searchContent 要检索的内容
	 * @param resultName 要返回的值得字段名
	 * @return
	 */
	public static List<String> isExist(String filePath,List<Object> parameterNames,String searchName,String searchContent,String resultName){
		List<String> listResult = new ArrayList<String>();
		
		List<HashMap<Object,Object>> listMap = getAllByExcel(filePath,parameterNames);//获取所有的数据
		
		/*for(int i = 0;i<listMap.size();i++){
			System.out.println(listMap.get(i).get("code"));
		}*/
		
		for (HashMap<Object, Object> hashMap : listMap) {
			if(hashMap.get(searchName).equals(searchContent)){
				listResult.add((String) hashMap.get(resultName));
			}
		}
		
		return listResult;
	}

	/**
	 * 获取bean类的所有属性及调用set和get方法
	 * @param Obj 传入的bean类
	 * @param parameterNames 传入的参数名集合(List)
	 * @param parameterValues 传入的参数和参数对应的值（调用set方法，进行赋值）
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public static void getPamarter(Object Obj, List<Object> parameterNames,HashMap<Object,Object> parameterValues) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		callSetFuction(Obj, parameterNames, parameterValues);//调用bean类的set方法
		
		callGetFuction(Obj);//调用bean类的get方法
		
	}
	
	/**
	 * 调用bean类中所有的set方法
	 * @param Obj
	 * @param parameterValues 
	 */
	public static void callAllSetFuction(Object Obj,List<Object> parameterValues){
		int num = 0;//计数，第几个set方法
		try {
			java.lang.reflect.Method[] method = Obj.getClass().getDeclaredMethods();// 获取对象所有方法

			// 执行set方法
			for (int i = 0; i < method.length; i++) {
				
				if (method[i].getName().startsWith("set")) {// 获取set方法
					method[i].invoke(Obj, parameterValues.get(i));
					num++;
				}

			}
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 调用bean类中用户指定的的set方法
	 * @param Obj 传入的bean类
	 * @param parameterNames 传入的参数名集合(List)
	 * @param parameterValues 传入的参数和参数对应的值（调用set方法，进行赋值）
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public static void callSetFuction(Object Obj, List<Object> parameterNames,HashMap<Object,Object> parameterValues) {
		int num = 0;//计数，第几个set方法
		Map<String, Object> M = null;
		try {
			M = reflectTest(Obj);
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			java.lang.reflect.Method[] method = Obj.getClass().getDeclaredMethods();// 获取对象所有方法

			// 执行set方法
			for (int i = 0; i < method.length; i++) {
				
				if (method[i].getName().startsWith("set")) {// 获取set方法；并获取对应的属性名
					String name = method[i].getName();//获取set方法名
					String type = M.get(name.split("set")[1]).toString();//类型
					char[] n = name.split("set")[1].toCharArray();//将set方法名去掉set后转换为字符数组
					
					name = name.valueOf(n[0]).toLowerCase();//将字得到的字符数组的第一个字符转化为小写（原因是set方法会以大写属性名头字母来命名set方法名）
					
					for(int cn = 1;cn<n.length;cn++){//重新组合属性名
						name += n[cn];
					}

					//调用对应属性名的set方法
					for(int pi=0;pi<parameterNames.size();pi++){
						Object paramaer = null;
						if(name.equals(parameterNames.get(pi))){
							switch (type) {
							case "Integer":
								paramaer = Integer.valueOf(parameterValues.get(parameterNames.get(pi)).toString());
								break;
							case "String":
								paramaer = parameterValues.get(parameterNames.get(pi));
								break;
							case "Date":
								if("".equals(parameterValues.get(parameterNames.get(pi)).toString())){
									continue;
								}else{
									try {
										SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:dd:ss");
										Date paramaer1=sdf.parse((String) parameterValues.get(parameterNames.get(pi)));
										paramaer=paramaer1;
									} catch (Exception e) {
										e.printStackTrace();
										paramaer = new Date();
									}
								}
								break;
							}
							method[i].invoke(Obj,paramaer );
							//num++;
							break;
						}
					}
				}

			}
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	
	public static void callGetFuction(Object Obj){
		
		try{
			java.lang.reflect.Method[] method = Obj.getClass().getDeclaredMethods();// 获取对象所有方法
			// 执行get方法
			for (java.lang.reflect.Method m : method) {
				if (m.getName().startsWith("get")) {// 获取get方法
					Object o = m.invoke(Obj);// 执行
					if (o == null || "".equals(o.toString())) {
						System.out.println(m.getName()+"========属性值为空");
					} else {
						System.out.println(m.getName()+"========"+o.toString());// 输出相应的属性值
					}
				}
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * 遍历实体类的属性和数据类型以及属性值 
	 * @param model 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws InvocationTargetException 
	 */  
	public static Map<String,Object> reflectTest(Object model) throws NoSuchMethodException,IllegalAccessException, IllegalArgumentException, InvocationTargetException {  
	    // 获取实体类的所有属性，返回Field数组  
	    Field[] field = model.getClass().getDeclaredFields(); 
	    Map<String,Object> M = new HashMap<String,Object>();
	    // 遍历所有属性  
	    for (int j = 0; j < field.length; j++) {  
	    		
	            // 获取属性的名字  
	            String name = field[j].getName();  
	            // 将属性的首字符大写，方便构造get，set方法  
	            name = name.substring(0, 1).toUpperCase() + name.substring(1);  
	            // 获取属性的类型  
	            String type = field[j].getGenericType().toString();  
	            // 如果type是类类型，则前面包含"class "，后面跟类名  
	            System.out.println("属性为：" + name);  
	            if (type.equals("class java.lang.String")) {  
	                    Method m = model.getClass().getMethod("get" + name);  
	                    // 调用getter方法获取属性值  
	                    String value = (String) m.invoke(model);  
	                    System.out.println("数据类型为：String");  
	                    if (value != null) {  
	                            System.out.println("属性值为：" + value);  
	                    } else {  
	                            System.out.println("属性值为：空");  
	                    } 
	                    M.put(name,"String");
	            }  
	            if (type.equals("class java.lang.Integer")) {  
	                    Method m = model.getClass().getMethod("get" + name);  
	                    Integer value = (Integer) m.invoke(model);  
	                    System.out.println("数据类型为：Integer");  
	                    if (value != null) {  
	                            System.out.println("属性值为：" + value);  
	                    } else {  
	                            System.out.println("属性值为：空");  
	                    }  
	                    M.put(name,"Integer");
	            }  
	            if (type.equals("class java.lang.Short")) {  
	                    Method m = model.getClass().getMethod("get" + name);  
	                    Short value = (Short) m.invoke(model);  
	                    System.out.println("数据类型为：Short");  
	                    if (value != null) {  
	                            System.out.println("属性值为：" + value);  
	                    } else {  
	                            System.out.println("属性值为：空");  
	                    } 
	                    M.put(name,"Short");
	            }  
	            if (type.equals("class java.lang.Double")) {  
	                    Method m = model.getClass().getMethod("get" + name);  
	                    Double value = (Double) m.invoke(model);  
	                    System.out.println("数据类型为：Double");  
	                    if (value != null) {  
	                            System.out.println("属性值为：" + value);  
	                    } else {  
	                            System.out.println("属性值为：空");  
	                    } 
	                    M.put(name,"Double");
	            }  
	            if (type.equals("class java.lang.Boolean")) {  
	                    Method m = model.getClass().getMethod("get" + name);  
	                    Boolean value = (Boolean) m.invoke(model);  
	                    System.out.println("数据类型为：Boolean");  
	                    if (value != null) {  
	                            System.out.println("属性值为：" + value);  
	                    } else {  
	                            System.out.println("属性值为：空");  
	                    }  
	                    M.put(name,"Boolean");
	            }  
	            if (type.equals("class java.util.Date")) {  
	                    Method m = model.getClass().getMethod("get" + name);  
	                    Date value = (Date) m.invoke(model);  
	                    System.out.println("数据类型为：Date");  
	                    if (value != null) {  
	                            System.out.println("属性值为：" + value);  
	                    } else {  
	                            System.out.println("属性值为：空");  
	                    } 
	                    M.put(name,"Date");
	            }
	    }
	    
	    return M;
	}  

	
	public static String isNull(Object obj){
		return (obj == ""||obj == null||"".equals(obj))?" ":obj.toString();
	}
	
}
