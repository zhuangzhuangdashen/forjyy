package com.example.admincf.lx.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;
/**
 * 
* @Title: EasyExcelUtil.java  
* @Package com.lx.util  
* @Description: 阿里巴巴操作EXCEL工具类
* @author lbk  
* @date 2019年4月29日 下午7:50:14  
* @version V1.0
 */
@Slf4j
public class EasyExcelUtil {
	
	public static void main(String[] args) {
		List<ZuHaoWanMode> list=EasyExcelUtil.readSmallExcel("D:\\cs.xlsx", ZuHaoWanMode.class);
		System.out.println(list.size());
	}

	/**
	 * 读取少于1000行的Excel数据
	 * @param filename 读取的文件路径
	 * @param t 需要映射的类
	 * @return <T> 映射的类集合
	 */
	@SuppressWarnings("unchecked")
	public static <T> T readSmallExcel(String filename,Class<? extends BaseRowModel> t){
		    InputStream inputStream = getInputStream(filename);
	        try {
	            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, t));
	            if(data==null||data.size()==0){
	            	return null;
	            }
	            return (T)(List<T>) data;
	        } catch (Exception e) {
	        	log.error("读取阿里excel少于1000行出错",e);
	            e.printStackTrace();
	        } finally {
	            try {
	                inputStream.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			return null;
	}
	
	/**
	 * 读取少于1000行的Excel数据
	 * @param inputStream 读取的流
	 * @param t 需要映射的类
	 * @return <T> 映射的类集合
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T readSmallExcel(InputStream inputStream,Class<? extends BaseRowModel> t){
        try {
            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, t));
            if(data==null||data.size()==0){
            	return null;
            }
            return (T)(List<T>) data;
        } catch (Exception e) {
        	log.error("读取阿里excel少于1000行出错",e);
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return null;
}
	
	/**
	 * 读取文件帮助类
	 * @param fileName
	 * @return
	 */
	private static InputStream getInputStream(String fileName) {
        try {
            return new FileInputStream(new File(fileName));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }  
	
}
