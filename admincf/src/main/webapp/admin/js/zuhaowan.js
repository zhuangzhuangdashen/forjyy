layui.config({
    	base: '../static/js/'
    });
    layui.use(['table','form','notice','upload'],function() {
    	 var notice=layui.notice;
         var table = layui.table;
         var form = layui.form;
         var upload=layui.upload;
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
        	    ,url:'../zhw/zlist.do'
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
        	      ,{field:'hid', width:'5%', title: '货架号'}
        	      ,{field:'userid', width:'6%', title: '用户账号', sort: true}
        	      ,{field:'pn', width:'20%', title: '标题'}
        	      ,{field:'pm', width:'4%', title: '金额'}
        	      ,{field:'n', title: '评价内容', width: '25%', minWidth: 100} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
        	      ,{field:'t', title: '评价时间', width: '10%',sort: true}
        	      ,{field:'zone_name', title: '服务器',width: '5%', sort: true}
        	      ,{field:'server_name', title: '大区',width: '5%'}
        	      ,{field:'game_name', width: '5%', title: '游戏', sort: true}
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
           //初始化上传文件
           var uploadInst = upload.render({
               elem: '#test3' //绑定元素
              ,url: '../zhw/getUploadExcel.do' //上传接口
              ,accept:"file"
              ,acceptMime: 'file/xlx, file/xlsx'
              ,exts:"xlx|xlsx"	
              ,before: function(onj){//上传前
              	layer.load(); //上传loading
              },done: function(res){//上传完毕回调
              	layer.alert(res.msg,{icon:1})
              	layer.closeAll('loading'); //关闭loading
              },error: function(){//错误loading
              	layer.alert("上传失败，未知原因",{icon:2})
              	layer.closeAll('loading'); //关闭loading
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
         
         //监听头工具栏事件
         table.on('toolbar(test)', function(obj){
        	 var checkStatus = table.checkStatus(obj.config.id);
        	    switch(obj.event){
        	      case 'getCheckData':
        	        var data = checkStatus.data;
        	        layer.alert(JSON.stringify(data));
        	      break;
        	      case 'getCheckLength':
        	        var data = checkStatus.data;
        	        layer.msg('选中了：'+ data.length + ' 个');
        	      break;
        	      case 'isAll':
        	        layer.msg(checkStatus.isAll ? '全选': '未全选');
        	      break;
        	    };
         });
         
         //初始化上传文件
         var uploadInst = upload.render({
             elem: '#test3' //绑定元素
            ,url: '../zhw/getUploadExcel.do' //上传接口
            ,accept:"file"
            ,acceptMime: 'file/xlx, file/xlsx'
            ,exts:"xlx|xlsx"	
            ,before: function(onj){//上传前
            	layer.load(); //上传loading
            },done: function(res){//上传完毕回调
            	layer.alert(res.msg,{icon:1})
            	layer.closeAll('loading'); //关闭loading
            },error: function(){//错误loading
            	layer.alert("上传失败，未知原因",{icon:2})
            	layer.closeAll('loading'); //关闭loading
            }
         });
    });