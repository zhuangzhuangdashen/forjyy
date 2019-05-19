package com.example.admincf.lx.util;

import java.util.HashMap;
import java.util.Map;

public class LxUtil {

	/**
	 * 判断字符串不能为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str!=null&&str!=""&&str.length()>=1){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断数字不能为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(int str){
		if(str!=0){
			return true;
		}
		return false;
	}
	
	/**
	 * 组装参数
	 * @return
	 */
	public static Map<String,String> parmUtil(String key,String vue){
		
		Map<String,String> map=null;
		
	    if(!LxUtil.isEmpty(key)&&!LxUtil.isEmpty(vue)){
	    	return null;
	    }
	    
		String[] keylist=key.split(",");
		String[] vuelist=vue.split(",");
		if(keylist.length!=vuelist.length){
			return null;
		}
		
		map=new HashMap<String, String>();
		for (int i = 0; i < keylist.length; i++) {
			 map.put(keylist[i], vuelist[i]);
		}
		
		return map;
	}
}
