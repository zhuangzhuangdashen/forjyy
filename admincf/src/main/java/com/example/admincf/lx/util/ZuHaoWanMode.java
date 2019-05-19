package com.example.admincf.lx.util;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
/**
 * 
* @Title: ZuHaoWanMode.java  
* @Package com.lx.util  
* @Description: EXCEL映射帮助类 
* @author lbk  
* @date 2019年4月29日 下午8:01:00  
* @version V1.0
 */
public class ZuHaoWanMode extends BaseRowModel{
	@ExcelProperty(index =0)
	private String xuhao;
	@ExcelProperty(index =1)
	private String name;
	public String getXuhao() {
		return xuhao;
	}
	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
