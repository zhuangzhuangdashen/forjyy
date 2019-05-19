package com.example.admincf.lx.service;

import com.example.admincf.lx.pojo.po.ZuHaoWan;
import com.example.admincf.lx.pojo.vo.ZuHaoWanVo;

import java.util.Map;
/**
 * 
* @Title: ZuHaoWanService.java  
* @Package com.lx.service  
* @Description: TODO(租号玩业务接口)  
* @author lbk  
* @date 2019年4月25日 上午1:31:26  
* @version V1.0
 */
public interface ZuHaoWanService {

	public void saveZuHaoWan(ZuHaoWan zuHaoWan);

	public Map<String,Object> getZuHaoWanList(ZuHaoWanVo zuHaoWan);
}
