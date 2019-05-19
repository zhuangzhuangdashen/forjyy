package com.example.admincf.lx.task;

import com.example.admincf.lx.pojo.po.ZuHaoWan;
import com.example.admincf.lx.service.ZuHaoWanService;
import com.example.admincf.lx.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 
* @Title: ZuHaoWanTask.java  
* @Package com.lx.task  
* @Description: TODO租号玩爬数据  
* @author lbk  
* @date 2019年4月25日 上午1:23:13  
* @version V1.0
 */
@Component
public class ZuHaoWanTask {

	private final Logger logger=Logger.getLogger(ZuHaoWanTask.class);
	
	@Autowired
	private ZuHaoWanService zuHaoWanService;
	
	@Scheduled(cron="0 39 15 ? * *")
	public void getZuHaoWanHttpTask(){
	   logger.info("----------------定时任务开启-------");
	   for (int i = 1; i <8; i++) {
		   try {
			  Map<String,Object> sb= HttpUtil.httpGetConnection("https://www.zuhaowan.com/Search/appraiseOfAccount.html?actId=106186&page="+i+"&lx=0&isReply=0&tag=");
			  Gson gson=new Gson();
			  JsonObject jsonObject=gson.fromJson(sb.get("conten").toString(), JsonObject.class);
			  JsonArray jarry=jsonObject.getAsJsonArray("data");
			  for (JsonElement jsonElement : jarry) {
				ZuHaoWan zhu=gson.fromJson(jsonElement, ZuHaoWan.class);
				if(zhu!=null){
					this.zuHaoWanService.saveZuHaoWan(zhu);
				}
			  }
			  logger.info("----------------定时任务結束-------");
		   } catch (Exception e) {
			  logger.error("----------------定时任务異常-------",e);
		   }
	   }
	}
}
