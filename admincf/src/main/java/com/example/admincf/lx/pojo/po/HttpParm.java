package com.example.admincf.lx.pojo.po;

import java.io.Serializable;
/**
 * 
* 项目名称：admin   
* 类名称：HttpParm   
* 类描述：接口参数实体类
* 创建人：Administrator   
* 创建时间：2019年4月26日 下午4:00:04   
* 修改人：Administrator   
* 修改时间：2019年4月26日 下午4:00:04   
* 修改备注： 
* @version
 */
public class HttpParm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String url;
	
	private String csgs;
	
	private String csfs;
	
	private String cookieparm;
	
	private String sjc;
	
	private String sjcgs;
	
	private String keyparm;
	
	private String vueparm;
	
	private String msgcode;
	
	private String msg;
	
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCsgs() {
		return csgs;
	}

	public void setCsgs(String csgs) {
		this.csgs = csgs;
	}

	public String getCsfs() {
		return csfs;
	}

	public void setCsfs(String csfs) {
		this.csfs = csfs;
	}

	public String getCookieparm() {
		return cookieparm;
	}

	public void setCookieparm(String cookieparm) {
		this.cookieparm = cookieparm;
	}

	public String getSjc() {
		return sjc;
	}

	public void setSjc(String sjc) {
		this.sjc = sjc;
	}

	public String getSjcgs() {
		return sjcgs;
	}

	public void setSjcgs(String sjcgs) {
		this.sjcgs = sjcgs;
	}

	public String getKeyparm() {
		return keyparm;
	}

	public void setKeyparm(String keyparm) {
		this.keyparm = keyparm;
	}

	public String getVueparm() {
		return vueparm;
	}

	public void setVueparm(String vueparm) {
		this.vueparm = vueparm;
	}

	public String getMsgcode() {
		return msgcode;
	}

	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
