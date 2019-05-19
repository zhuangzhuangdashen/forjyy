layui.config({
    	base: '../static/js/'
    });
    layui.use(['table','form','notice'],function() {
    	 var notice=layui.notice;
         var table = layui.table;
         var form = layui.form;
        
         // 初始化配置，同一样式只需要配置一次，非必须初始化，有默认配置
         notice.options = {
         closeButton:true,//显示关闭按钮
         debug:false,//启用debug
         positionClass:"toast-top-center",//弹出的位置,
         showDuration:"300",//显示的时间
         hideDuration:"1000",//消失的时间
         timeOut:"2000",//停留的时间
         extendedTimeOut:"1000",//控制时间
         showEasing:"swing",//显示时的动画缓冲方式
         hideEasing:"linear",//消失时的动画缓冲方式
         iconClass: 'toast-info', // 自定义图标，有内置，如不需要则传空 支持layui内置图标/自定义iconfont类名
         onclick: null, // 点击关闭回调
         };
         table.render({
        	    id:'testReload'
        	    ,elem: '#test'
        	    ,url:'../menu/getlist.do'
        	    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        	    ,page:true
        	    ,limit:15
        	    ,limits:[15,20,30,40,50,60,70,80,90]
        	    ,toolbar: '#toolbarDemo'
        	    ,parseData: function(res){ //res 即为原始返回的数据
        	    	console.log(res)
        	        return {
        	          "code": res.msgcode, //解析接口状态
        	          "msg": res.msg, //解析提示文本
        	          "count": res.data.count, //解析数据长度
        	          "data": res.data.list //解析数据列表
        	        };
        	    },request: {
        	    	     pageName: 'page' //页码的参数名称，默认：page
        	    	    ,limitName: 'rows' //每页数据量的参数名，默认：limit
        	    },cols: [[
                   {type: 'checkbox',width:'5%',fixed: 'left'}
        	      ,{field:'id', width:'5%', title: 'id'}
        	      ,{field:'parent_id', width:'5%', title: '父级ID', sort: true,templet: function(res){
        	    	  return res.parent_id==0 ? "" : res.parent_id;
       	           }}
        	      ,{field:'menu_name', width:'15%', title: '菜单名称'}
        	      ,{field:'menu_href', width:'20%', title: '菜单地址'}
        	      ,{field:'menu_icon', title: '菜单图标', width: '10%',templet: function(res){
        	    	  return "<i class='iconfont left-nav-li'>"+res.menu_icon+"</i>";
        	       }} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
        	      ,{field:'menu_state', title: '菜单状态', width: '10%',sort: true,templet: function(res){
        	    	  return res.menu_state==0 ? "正常" : "禁用";
        	       }}
        	      ,{field:'createtime', title: '创建时间',width: '10%', sort: true}
        	      ,{field:'updatetime', title: '修改时间',width: '10%'}
        	      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:'10%'}
        	    ]]
          });  
         
         
         //监听提交
         form.on('submit(sreach)', function(data){
           //执行重载
           table.reload('testReload', {
             page: {
               curr: 1 //重新从第 1 页开始
             },where: {
            	 "t.userid":$("#username").val()
             }
           });
           return false;
         });
         
         //监听行工具事件
         table.on('tool(test)', function(obj){
           var data = obj.data;
           if(obj.event === 'del'){
             layer.confirm('真的删除行么', function(index){
               console.log(data.id)
               layer.close(index);
             });
           } else if(obj.event === 'edit'){
        	   
           }
         });
    });