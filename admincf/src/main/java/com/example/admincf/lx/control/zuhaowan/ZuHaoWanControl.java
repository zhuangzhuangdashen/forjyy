package com.example.admincf.lx.control.zuhaowan;

import com.example.admincf.lx.pojo.vo.ZuHaoWanVo;
import com.example.admincf.lx.service.ZuHaoWanService;
import com.example.admincf.lx.util.EasyExcelUtil;
import com.example.admincf.lx.util.Result;
import com.example.admincf.lx.util.ZuHaoWanMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
/**
 * 
* 项目名称：admin   
* 类名称：ZuHaoWanControl   
* 类描述：暂无 
* 创建人：Administrator   
* 创建时间：2019年4月25日 上午9:01:26   
* 修改人：Administrator   
* 修改时间：2019年4月25日 上午9:01:26   
* 修改备注： 
* @version
 */
@Controller
@Slf4j
@RequestMapping("/zhw")
public class ZuHaoWanControl {

	@Autowired
	private ZuHaoWanService zuHaoWanService;
	
	@RequestMapping("/zlistview")
	public ModelAndView zListView(){
		ModelAndView mod=new ModelAndView();
		mod.setViewName("/zuhaowan/zuhaowan_list");
		return mod;
	}
	
	@RequestMapping("/zlist")
	@ResponseBody
	public Result zList(ZuHaoWanVo zuHaoWan){
		try {
			return new Result(true,"查询成功","0",this.zuHaoWanService.getZuHaoWanList(zuHaoWan));
		} catch (Exception ex) {
			log.error("查询失败，获取租号玩数据系统异常", ex);
			return new Result(true,"查询失败，系统异常","002",null);
		}
	}
	
	@RequestMapping("/getUploadExcel")
	@ResponseBody
	public Result getUploadExcel(MultipartFile file){
		try {
			List<ZuHaoWanMode> zlist= EasyExcelUtil.readSmallExcel(file.getInputStream(),ZuHaoWanMode.class);
			return new Result(true,"上传成功","0",zlist);
		} catch (Exception ex) {
			log.error("上传失败，系统异常", ex);
			return new Result(true,"上传失败，系统异常","002",null);
		}
	}
	
	
}
