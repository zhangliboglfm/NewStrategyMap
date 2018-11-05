<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
     <base href="<%=basePath%>">
    <title>角色基本信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
	<link rel="stylesheet" href="css/login.css" type="text/css"></link>
	<link rel="stylesheet" href="css/admin.css" type="text/css"></link>

	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="layui/layui.all.js"></script>
	<script type="text/javascript" src="layui/lay/modules/element.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <link rel="stylesheet" href="css/rolemanage.css" type="text/css"></link></head>
  
  <body>
  
  <div class="layui-fluid">   
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">角色Id</label>
            <div class="layui-input-block">
              <input type="text" id="roleId" name="ID" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-block">
              <input type="text" id="rolename" name="rolename" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-user-back-search" onclick="conditionSearch()">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      <div class="layui-card-body">
        <div class="layui-btn-group demoTable">
 		  <button class="layui-btn" data-type="getCheckData">删除</button>
		  <button class="layui-btn" onclick="addRole()">添加</button>
	    </div>
        <div class="table">
        	<table id="table1" class="layui-table" lay-filter="demo"></table>
        </div>
        <div id="pagingDiv" style="float:left;height:50px;width:99%;" align="right"></div>
      </div>
    </div>
  </div>
  <!-- <div class="layui-layer layui-layer-iframe" id="layui-layer0" type="iframe" times="1" showtime="0" contype="string" style="z-index: 19891015; width: 750px; height: 650px; top: 1px; left: 350px;display:none;">
     <div class="layui-layer-title" style="cursor: move;">模块分配</div>
     <div id="" class="layui-layer-content">
         <iframe scrolling="auto" allowtransparency="true" id="layui-layer-iframe0" name="layui-layer-iframe1" src="" style="height: 580px;" frameborder="0">
         </iframe>
     </div>
     <span class="layui-layer-setwin">
        <a id="close0" class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;">
        </a>
     </span>
  </div>  
  <div class="layui-layer layui-layer-iframe" id="layui-layer1" type="iframe" times="1" showtime="0" contype="string" style="z-index: 19891015; width: 700px; height: 650px; top: 1px; left: 350px;display:none;">
     <div class="layui-layer-title" style="cursor: move;">权限分配</div>
     <div id="" class="layui-layer-content">
         <iframe scrolling="auto" allowtransparency="true" id="layui-layer-iframe1" name="layui-layer-iframe1" src="" style="height: 580px;" frameborder="0">
         </iframe>
     </div>
     <span class="layui-layer-setwin">
        <a id="close1" class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;">
        </a>
     </span>
  </div>
 <div class="layui-layer layui-layer-iframe" id="layui-layer2" type="iframe" times="1" showtime="0" contype="string" style="z-index: 19891015; width: 650px; height: 350px; top: 30px; left: 350px;display:none;">
     <div class="layui-layer-title" style="cursor: move;">添加角色</div>
     <div id="" class="layui-layer-content">
		<form class="layui-form" action="" lay-filter="example">      
		  <div class="layui-form-item">
		    <label class="layui-form-label">角色标识</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="roleid" name="roleid" lay-verify="" autocomplete="off" placeholder="请输入角色标识" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">角色名称</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="rolename" name="rolename" lay-verify="" autocomplete="off" placeholder="请输入角色名称" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">角色描述</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="roledescribe" name="roledescribe" lay-verify="" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="demo2">立即提交</button>
		    </div>
		  </div>
		</form>  
     </div>
     <span class="layui-layer-setwin">
     <a id="close2" class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;">
     </a>
     </span>
  </div>      
 <div class="layui-layer layui-layer-iframe" id="layui-layer3" type="iframe" times="1" showtime="0" contype="string" style="z-index: 19891015; width: 650px; height:220px; top: 60px; left: 350px;display:none;">
     <div class="layui-layer-title" style="cursor: move;">编辑角色</div>
     <div id="" class="layui-layer-content">
		<form class="layui-form" action="" lay-filter="example">      
		  <div class="layui-form-item">
		    <label class="layui-form-label">描述</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="describe" name="describe" lay-verify="WB" autocomplete="off" placeholder="请输入要修改的描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label" style="display:none">隐藏标识</label>
		    <div class="layui-input-block">
		      <input id="flag" name="flag" lay-verify="WB" autocomplete="off" type="hidden">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="demo3">立即提交</button>
		    </div>
		  </div>
		</form>  
     </div>
     <span class="layui-layer-setwin">
     <a id="close3" class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;">
     </a>
     </span>
  </div>       -->
  </body>
  <script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  <a class="layui-btn layui-btn-normal" lay-event="powerAllot">权限分配</a>
</script>
<script type="text/javascript">
	var layer = layui.layer;
	var limitcount = 14;
	var curnum = 1;
	
	$(function () {
		remakeTable(curnum,limitcount)
	});
	//重制表格
	function remakeTable(curnum,limitcount) {
		var roleId = $("#roleId").val();
		var roleName = $("#rolename").val();
		layui.use(['table','laypage','laydate'], function() {	
			var table = layui.table;
			laypage = layui.laypage;
			table.render({
			    elem: '#table1'
			    ,url: 'getallrole.do?roleId='+roleId+'&roleName='+roleName+'&curnum='+curnum+'&limitcount='+limitcount //数据接口
			    ,page: false //开启分页
			    ,id:'idTest'
			    ,filter:'demo'
			    ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
			    ,cols: [[ //表头
			      {type: 'checkbox', fixed: 'left'}
			      ,{field: 'roleid', title: '角色ID',  fixed: 'left'}
			      ,{field: 'rolename', title: '角色名'}
			      ,{field: 'describe', title: '角色描述'}
			      ,{fixed: 'right', width:228, align:'center', toolbar: '#barDemo',title: '操作'}
			    ]]
			    ,height: 640
			    ,done: function(res, curr, count){
					//如果是异步请求数据方式，res即为你接口返回的信息。                    
					//如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度                    
					laypage.render({                        
						elem:'pagingDiv'                        
						,count:res.userNum                       
						,curr:curnum                        
						,limit:limitcount                        
						,layout: ['prev', 'page', 'next', 'skip','count']                        
						,jump:function (obj,first) {                           
							 if(!first){                                
							 	curnum = obj.curr;                                
							 	limitcount = obj.limit;                                
							 	remakeTable(curnum,limitcount);                            
							 }                        
						  }                    
					  })
				  }
			 });
		 });
	}
	function conditionSearch() {
		remakeTable(curnum,limitcount);
	}
	//对表格部分的操作情况
	layui.use(['layer','form','table'],function() {
		var table = layui.table;
		var layer3 = layui.layer;
		var layer2 = layui.layer;
		var form = layui.form;

		//监听工具条
		table.on('tool(demo)', function(obj) {
			var data = obj.data;
			var roleid=data.roleid;
			console.log(obj);	
			if (obj.event === 'del') {
				$.ajax({
					url : "deleterole.do",
					type : "post",
					dataType : "json",
					data : {
						flag : roleid
					},
					success : function(data) {
						layer.msg("删除成功");
					}
				});
				table.reload('idTest',remakeTable(curnum,limitcount));
			} else if (obj.event === 'edit') {
				//多窗口模式，层叠置顶
		        layer3.open({
			        type: 2 //此处以iframe举例
			        ,title: '编辑基本信息'
			        ,area: ['400px', '400px']
			        ,offset: 'auto'
			        ,content: 'editRoleInfo.do?roleId='+roleid+'&editFlag=2'
			        ,zIndex: layer3.zIndex //重点1 
			        ,success: function(layero){
			        	
			        }
			        ,end : function(){
			        	remakeTable(curnum,limitcount);
			        }
		        });
			} else if (obj.event === 'powerAllot') {
				//多窗口模式，层叠置顶
		        layer3.open({
			        type: 2 //此处以iframe举例
			        ,title: '权限分配'
			        ,area: ['600px', '700px']
			        ,offset: 'auto'
			        ,content: 'powerAllot.do?roleId='+roleid
			        ,zIndex: layer3.zIndex //重点1 
			        ,success: function(layero){
			        	
			        }
			        ,end : function(){
			        	remakeTable(curnum,limitcount);
			        }
		        });
			} 
		});
		var $ = layui.$, active = {
			getCheckData : function() { //获取选中数据
				var checkStatus = table.checkStatus('idTest'), data = checkStatus.data;
				console.log(checkStatus)
				if (data.length === 0) {
					return false;
				}
				for ( var i = 0; i < data.length; i++) {
					$.ajax({
						url : "deleterole.do",
						type : "post",
						dataType : "json",
						async : false,
						data : {
							"flag" : data[i].roleid
						},
						success : function(data) {
						}
					});
					table.reload('idTest', remakeTable(curnum,limitcount));
				}
			}

		};

		$('.demoTable .layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	//添加按钮添加新角色
	function addRole() {
		layui.use(['layer','form','table'],function() {
			var layer4 = layui.layer;
			layer4.open({
		        type: 2 //此处以iframe举例
		        ,title: '新加角色'
		        ,area: ['400px', '400px']
		        ,offset: 'auto'
		        ,content: 'editRoleInfo.do?editFlag=1'
		        ,zIndex: layer4.zIndex //重点1 
		        ,success: function(layero){
		        	
		        }
		        ,end : function(){
		        	remakeTable(curnum,limitcount);
		        }
	        });
	    });
	}
</script>
</html>