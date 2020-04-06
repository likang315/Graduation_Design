package com.ly.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WorkSheet {
	public static void ExcelSheet(String[] header,String[] sheets,Workbook workbook,String name,List<Map<String,Object>> list){
		
			
		Sheet sheet = workbook.createSheet(name);
		
		   Row row = sheet.createRow(0);
		   
		   for (int i = 0; i < header.length; i++) {
			   row.createCell(i).setCellValue(header[i]);
			}
	
	        for(int j = 0;j<list.size();j++){
	        	Row row2 = sheet.createRow(j+1);
	        	 Map<String,Object> map = list.get(j) ;
	        	  for(int k = 0;k<sheets.length;k++){
	        		 
        			  if(map.get(sheets[k]) == null){
        				  map.put(sheets[k]," ");
        			  }
	        		  
	        		  row2.createCell(k).setCellValue(map.get(sheets[k]).toString());
	        	  }
	          }

		}
	
}
