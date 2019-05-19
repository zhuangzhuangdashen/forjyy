package com.example.admincf.lx.pojo.vo;
/**
* 
* 项目名称：admin   
* 类名称：PageVo   
* 类描述：分页
* 创建人：Administrator   
* 创建时间：2019年4月25日 上午9:27:50   
* 修改人：Administrator   
* 修改时间：2019年4月25日 上午9:27:50   
* 修改备注： 
* @version
*/
public class PageVo<T> {

	private T t;
	
	private Integer page;
	
	private Integer rows;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public Integer getPage() {
		return  (page!=null&&page>0 ? (page-1)*rows : 0);
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
}
