package com.ly.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串的编码判断以及处理类
 * @author fxf*/
public class EncodingUtil {
	
	/**判断字符串是否为中文乱码
	 * @param strName 需要判断的字符串
	 * @return true是乱码，false不是乱码
	 * @author fxf*/
	public static boolean isMessyCode(String strName) {
		if(strName == null) {
			return false;
		}
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = 0 ;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
                chLength++; 
            }
        }
        float result = count / chLength ;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }
    }
	
	/**判断字符是否为中文
	 * @param c 字符
	 * @author fxf*/
	public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
	
	/**将字符串的编码从ISO8859-1转为UTF-8
	 **/
	public static String strISO8859toUTF8 (String str) {
		try {
			str =  new String(str.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String[] args) {
		System.out.println(EncodingUtil.isMessyCode("***"));
	}
}
