package com.example.admincf.lx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

/**
 * 
* @Title: HttpUtil.java  
* @Package com.lx.util  
* @Description: TODO远程帮助类 
* @author lbk  
* @date 2019年4月25日 上午1:17:34  
* @version V1.0
 */
public class HttpUtil {

	private static final Logger logger=Logger.getLogger(HttpUtil.class);
	
	
	/**
	 * 远程GET调用
	 * @param url
	 * @param parm
	 * @return
	 */
	public static Map<String, Object> httpGetConnection(String url){
		long startTime = System.currentTimeMillis();    //获取开始时间
		Map<String, Object> map=new HashMap<String, Object>();
		CloseableHttpClient	client=null;
		CloseableHttpResponse response=null;
		try {
			client= HttpClients.createDefault();
			/* 2.使用get方法 */
			HttpGet httpGet = new HttpGet(url);
			/* 3.执行请求，获取响应 */
			response = client.execute( httpGet );
			HttpEntity  entity=response.getEntity();
		    long endTime = System.currentTimeMillis();    //获取结束时间
		    map.put("code", response.getStatusLine().getStatusCode());
		    map.put("conten", EntityUtils.toString(entity));
		    map.put("date",(endTime - startTime) + "ms");
			EntityUtils.consume( entity );
		} catch (Exception e) {
			map.put("code","999");
		    map.put("conten","----远程get调用"+url+"服务异常");
			logger.error("-----远程get调用"+url+"服务异常-----",e);
		}
		return map;
	}
	
	
	/**
	 * 远程GET调用
	 * @param url
	 * @param parm
	 * @return
	 */
	public static Map<String, Object> httpGetConnection(String url,String parm){
		long startTime = System.currentTimeMillis();    //获取开始时间
		Map<String, Object> map=new HashMap<String, Object>();
		CloseableHttpClient	client=null;
		CloseableHttpResponse response=null;
		try {
			client= HttpClients.createDefault();
			/* 2.使用get方法 */
			HttpGet httpGet = new HttpGet(url+parm);
			/* 3.执行请求，获取响应 */
			response = client.execute( httpGet );
			HttpEntity  entity=response.getEntity();
		    long endTime = System.currentTimeMillis();    //获取结束时间
		    map.put("code", response.getStatusLine().getStatusCode());
		    map.put("conten", EntityUtils.toString(entity));
		    map.put("date",(endTime - startTime) + "ms");
			EntityUtils.consume( entity );
		} catch (Exception e) {
			map.put("code","999");
		    map.put("conten","----远程get调用"+url+"服务异常");
			logger.error("-----远程get调用"+url+"服务异常-----",e);
		}
		return map;
	}
	
	
	/**
	 * 远程GET调用
	 * @param url
	 * @param parm
	 * @param cookie
	 * @return
	 */
	public static Map<String, Object> httpGetConnection(String url,String parm,String cookie){
		long startTime = System.currentTimeMillis();    //获取开始时间
		Map<String, Object> map=new HashMap<String, Object>();
		CloseableHttpClient	client=null;
		CloseableHttpResponse response=null;
		try {
			client= HttpClients.createDefault();
			/* 2.使用get方法 */
			HttpGet httpGet = new HttpGet(url+parm);
	        httpGet.setHeader("Cookie",cookie);  
			/* 3.执行请求，获取响应 */
			response = client.execute( httpGet );
			HttpEntity  entity=response.getEntity();
		    long endTime = System.currentTimeMillis();    //获取结束时间
		    map.put("code", response.getStatusLine().getStatusCode());
		    map.put("conten", EntityUtils.toString(entity));
		    map.put("date",(endTime - startTime) + "ms");
			EntityUtils.consume( entity );
		} catch (Exception e) {
			map.put("code","999");
		    map.put("conten","----远程get调用"+url+"服务异常");
			logger.error("-----远程get调用"+url+"服务异常-----",e);
		}
		return map;
	}
	
	
	/**
	 * 远程Post调用
	 * @param url
	 * @return
	 */
	public static Map<String, Object> httpPostConnection(String url,List<NameValuePair> params){
		long startTime = System.currentTimeMillis();    //获取开始时间
		CloseableHttpClient	client=null;
		Map<String, Object> map=new HashMap<String, Object>();
		CloseableHttpResponse response=null;
		try {
			client= HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));	
			response = client.execute(httpPost);
			HttpEntity entity=response.getEntity();
		    long endTime = System.currentTimeMillis();    //获取结束时间
		    map.put("code", response.getStatusLine().getStatusCode());
		    map.put("conten", EntityUtils.toString(entity));
		    map.put("date",(endTime - startTime) + "ms");
			EntityUtils.consume( entity );
		} catch (Exception e) {
			logger.error("-----远程Post表单方式调用"+url+"服务异常-----", e);
		}
		return map;
	}
	
	
	/**
	 * 远程Post调用
	 * @param url
	 * @param cookie
	 * @return
	 */
	public static Map<String, Object> httpPostConnection(String url,List<NameValuePair> params,String cookie){
		long startTime = System.currentTimeMillis();    //获取开始时间
		CloseableHttpClient	client=null;
		Map<String, Object> map=new HashMap<String, Object>();
		CloseableHttpResponse response=null;
		try {
			client= HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "*/*");  
			httpPost.setHeader("Accept-Encoding", "gzip, deflate");  
			httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");  
			httpPost.setHeader("Connection", "keep-alive");  
			httpPost.setHeader("Origin", "XMLHttpRequest"); 
			httpPost.setHeader("X-Requested-With", "XMLHttpRequest"); 
			httpPost.setHeader("Cookie",cookie);  
			httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));	
			response = client.execute(httpPost);
			HttpEntity entity=response.getEntity();
		    long endTime = System.currentTimeMillis();    //获取结束时间
		    map.put("code", response.getStatusLine().getStatusCode());
		    map.put("conten", EntityUtils.toString(entity));
		    map.put("date",(endTime - startTime) + "ms");
			EntityUtils.consume( entity );
		} catch (Exception e) {
			logger.error("-----远程Post表单方式调用"+url+"服务异常-----", e);
		}
		return map;
	}
	
	
	/**
	 * 远程Post调用
	 * @param url
	 * @return
	 */
	public static Map<String, Object> httpPostConnection(String url,JsonObject jsonObject){
		long startTime = System.currentTimeMillis();    //获取开始时间
		CloseableHttpClient	client=null;
		Map<String, Object> map=new HashMap<String, Object>();
		CloseableHttpResponse response=null;
		try {
			client= HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			StringEntity se = new StringEntity(jsonObject.toString());
		    se.setContentEncoding("UTF-8");  
		    se.setContentType("application/json");  
		    httpPost.setEntity(se); 
			response = client.execute(httpPost);
			HttpEntity entity=response.getEntity();
			long endTime = System.currentTimeMillis();    //获取结束时间
			map.put("code", response.getStatusLine().getStatusCode());
			map.put("conten", EntityUtils.toString(entity));
			map.put("date",(endTime - startTime) + "ms");
			EntityUtils.consume(entity);
		} catch (Exception e) {
			logger.error("-----远程PostJSON方式调用"+url+"服务异常-----", e);
		}
		return map;
	}

	/**
	 * 远程Post调用
	 * @param url
	 * @param cookie
	 * @return
	 */
	public static Map<String, Object> httpPostConnection(String url,JsonObject jsonObject,String cookie){
		long startTime = System.currentTimeMillis();    //获取开始时间
		CloseableHttpClient	client=null;
		Map<String, Object> map=new HashMap<String, Object>();
		CloseableHttpResponse response=null;
		try {
			client= HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Cookie",cookie);  
			StringEntity se = new StringEntity(jsonObject.toString());
		    se.setContentEncoding("UTF-8");  
		    se.setContentType("application/json");  
		    httpPost.setEntity(se); 
			response = client.execute(httpPost);
			HttpEntity entity=response.getEntity();
			long endTime = System.currentTimeMillis();    //获取结束时间
			map.put("code", response.getStatusLine().getStatusCode());
			map.put("conten", EntityUtils.toString(entity));
			map.put("date",(endTime - startTime) + "ms");
			EntityUtils.consume(entity);
		} catch (Exception e) {
			logger.error("-----远程PostJSON方式调用"+url+"服务异常-----", e);
		}
		return map;
	}
	
	/**
	 * 拼接jsonParm
	 * @return
	 */
	public static JsonObject jsonParmUtil(Map<String,String> map){
		if(map==null||map.size()==0){
			return null;
		}
		JsonObject jsonObj=new JsonObject();
		for (Entry<String, String> vmap : map.entrySet()) {
			jsonObj.addProperty(vmap.getKey(),vmap.getValue());
		}
		return jsonObj;
	}
	
	/**
	 * 拼接表单Parm
	 * @return
	 */
	public static List<NameValuePair> nameParmUtil(Map<String,String> map){
		if(map==null||map.size()==0){
			return null;
		}
		List<NameValuePair> nameList=new ArrayList<NameValuePair>();
		for (Entry<String, String> vmap : map.entrySet()) {
			nameList.add(new BasicNameValuePair(vmap.getKey(),vmap.getValue()));
		}
		return nameList;
	}
	
	
	/**
	 * 拼接getstringParm
	 * @return
	 */
	public static String strParmUtil(Map<String,String> map){
		if(map==null||map.size()==0){
			return null;
		}
		StringBuilder str=new StringBuilder("?");
		for (Entry<String, String> vmap : map.entrySet()) {
			str.append(vmap.getKey()+"="+vmap.getValue()+"&");
		}
		return str.substring(0, str.length()-1).toString();
	}
}
