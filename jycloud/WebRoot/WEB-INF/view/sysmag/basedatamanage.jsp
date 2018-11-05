<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>模块管理</title>
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

<link rel="stylesheet" href="css/backstage.css" type="text/css"></link>
</head>

<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">模块ID</label>
						<div class="layui-input-block">
							<input type="text" id="moduleid" name="moduleid" placeholder="请输入模块ID" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">父模块ID</label>
						<div class="layui-input-block">
							<input type="text" id="moduleid2" name="fathermoduleid" placeholder="请输入父模块ID" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">模块名</label>
						<div class="layui-input-block">
							<input type="text" id="modulename" name="modulename" placeholder="请输入模块名" autocomplete="off" class="layui-input">
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
					<button class="layui-btn" onclick="add()">添加</button>
				</div>
				<div class="table">
					<table id="table1" class="layui-table"  lay-filter="demo"></table>
				</div>
				<div id="pagingDiv" style="float:left;height:50px;width:99%;" align="right"></div>
			</div>
		</div>
	</div>
	<!-- <div class="layui-layer layui-layer-iframe" id="layui-layer2" type="iframe" times="1" showtime="0" contype="string" style="z-index: 19891015; width: 650px; height: 500px; top: 30px; left: 350px;display:none;">
     <div class="layui-layer-title" style="cursor: move;">添加角色</div>
     <div id="" class="layui-layer-content">
		<form class="layui-form" action="" lay-filter="example">      
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块ID</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input  name="moduleid" lay-verify="" autocomplete="off" placeholder="请输入角色标识" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">父模块ID</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="fathermoduleid" lay-verify="mail" autocomplete="off" placeholder="请输入角色名称" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块名</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="modulename" lay-verify="WB" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块级别</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="modulelevel" lay-verify="WB" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块链接</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input name="modulelink" lay-verify="WB" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">是否目录</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input name="ismenu" lay-verify="WB" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块顺序</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="moduleby" lay-verify="WB" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>  
		  <div class="layui-form-item">
		    <div class="layui-input-block"  style="width:500px;">
		      <button class="layui-btn" id="btn1"  lay-submit="" lay-filter="demo2">立即提交</button>
		    </div>
		  </div>
		</form>  
     </div>
     <span class="layui-layer-setwin">
     <a id="close2" class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;">
     </a>
     </span>
  </div>      
 <div class="layui-layer layui-layer-iframe" id="layui-layer3" type="iframe" times="1" showtime="0" contype="string" style="z-index: 19891015; width: 650px; height: 250px; top: 60px; left: 350px;display:none;">
     <div class="layui-layer-title" style="cursor: move;">编辑模块</div>
     <div id="" class="layui-layer-content">
		<form class="layui-form" action="" lay-filter="example">      
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块名</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="modulename" name="modulename" lay-verify="WB" autocomplete="off" placeholder="请输入要修改的描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块链接</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="modulelink" name="modulelink" lay-verify="WB" autocomplete="off" placeholder="请输入要修改的描述" class="layui-input" type="text">
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
		      <button class="layui-btn" id="btn1"  lay-submit="" lay-filter="demo3">立即提交</button>
		    </div>
		  </div>
		</form>  
     </div>
     <span class="layui-layer-setwin">
     <a id="close3" class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;">
     </a>
     </span>
  </div> -->
</body>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript">
	var layer = layui.layer;
	var limitcount = 14;
	var curnum = 1;
	var userSex = "0";
	$(function () {	
		renderForm();
		remakeTable(curnum,limitcount)
	});
	function renderForm() {
		layui.use('form', function() {
			var form = layui.form;
		  	form.render('select'); //刷新select选择框渲染
			form.on('select', function(data){
				userSex = data.value;
			});
		});
	}
	//重制表格
	function remakeTable(curnum,limitcount) {
		var moduleid = $("#moduleid").val();
		var parentMId = $("#moduleid2").val();
		var modulename = $("#modulename").val();
		layui.use(['table','laypage','laydate'], function() {	
			var table = layui.table;
			laypage = layui.laypage;
			table.render({
			    elem: '#table1'
			    ,url: 'getallmodule.do?moduleid='+moduleid+'&parentMId='+parentMId+'&curnum='+curnum+'&limitcount='+limitcount+'&modulename='+modulename //数据接口
			    ,page: false //开启分页
			    ,id:'idTest'
			    ,filter:'demo'
			    ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
			    ,cols: [[ //表头
			      {type: 'checkbox', fixed: 'left'}
			      ,{field: 'moduleid', title: '模块ID',  fixed: 'left'}
			      ,{field: 'fathermoduleid', title: '父模块ID'}
			      ,{field: 'modulename', title: '模块名称'}
			      ,{field: 'modulelevel', title: '模块级别'}
			      ,{field: 'modulelink', title: '模块链接'}
			      ,{field: 'ismenu', title: '模块类型'}
			      ,{field: 'moduleby', title: '模块顺序'} 	
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
	layui.use(['layer','form','table'],function() {
		var table = layui.table;
		var layer3 = layui.layer;
		var layer2 = layui.layer;
		var form = layui.form;

		//监听工具条
		table.on('tool(demo)', function(obj) {
			var data = obj.data;
			var mouldeId=data.moduleid;
			console.log(obj);	
			if (obj.event === 'del') {
				$.ajax({
					url : "deletemodule.do",
					type : "post",
					dataType : "json",
					data : {
						flag : mouldeId
					},
					success : function(data) {
						
					}
				});
				table.reload('idTest',remakeTable(curnum,limitcount));
			} else if (obj.event === 'edit') {
				//多窗口模式，层叠置顶
		        layer3.open({
			        type: 2 //此处以iframe举例
			        ,title: '编辑模块信息'
			        ,area: ['400px', '500px']
			        ,offset: 'auto'
			        ,content: 'editMoudle.do?mouldeId='+mouldeId+'&editFlag=2'
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
						url : "deletemodule.do",
						type : "post",
						dataType : "json",
						async : false,
						data : {
							"flag" : data[i].moduleid
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
	function add() {
		layui.use(['layer','form','table'],function() {
			var layer4 = layui.layer;
			layer4.open({
		        type: 2 //此处以iframe举例
		        ,title: '新加模块'
		        ,area: ['400px', '500px']
		        ,offset: 'auto'
		        ,content: 'editMoudle.do?editFlag=1'
		        ,zIndex: layer4.zIndex //重点1 
		        ,success: function(layero){
		        	
		        }
		        ,end : function(){
		        	remakeTable(curnum,limitcount);
		        }
	        });
	    });
	}
	function conditionSearch() {
		remakeTable(curnum,limitcount);
	}
</script>
</html>