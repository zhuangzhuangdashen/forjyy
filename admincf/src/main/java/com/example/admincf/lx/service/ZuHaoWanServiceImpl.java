package com.example.admincf.lx.service;

import com.example.admincf.lx.dao.ZuHaoWanDao;
import com.example.admincf.lx.pojo.po.ZuHaoWan;
import com.example.admincf.lx.pojo.vo.ZuHaoWanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
* 项目名称：admin   
* 类名称：ZuHaoWanServiceImpl   
* 类描述：暂无 
* 创建人：Administrator   
* 创建时间：2019年4月25日 上午9:01:02   
* 修改人：Administrator   
* 修改时间：2019年4月25日 上午9:01:02   
* 修改备注： 
* @version
 */
@Service
public class ZuHaoWanServiceImpl implements ZuHaoWanService{

	@Autowired
	private ZuHaoWanDao zuHaoWanDao;
	
	@Override
	public void saveZuHaoWan(ZuHaoWan zuHaoWan) {
		this.zuHaoWanDao.saveZuHaoWan(zuHaoWan);
	}

	@Override
	public Map<String,Object> getZuHaoWanList(ZuHaoWanVo zuHaoWan) {
		Map<String,Object> map=new HashMap<String, Object>();
		Integer zcount=this.zuHaoWanDao.getZhuHaoWanlistCount(zuHaoWan);
		if(zcount==null||zcount==0){
			map.put("count", 0);
			map.put("list", null);
			return map;
		}
		List<ZuHaoWan> zlist=this.zuHaoWanDao.getZhuHaoWanlistAll(zuHaoWan);
		map.put("count", zcount);
		map.put("list", zlist);
		return map;
	}

}
