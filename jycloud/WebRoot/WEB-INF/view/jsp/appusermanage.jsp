<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>APP用户管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/login.css" type="text/css"></link>
<link rel="stylesheet" href="css/admin.css" type="text/css"></link>
<link rel="stylesheet" href="css/backstage.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/miapsoft.autohide.js"></script>
<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	html{width: 100%;height:100%;margin: 0;padding: 0;}
	body{width: 100%;height:100%;margin: 0;padding: 0;}
</style>

</head>

<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-block">
							<input id="serchUserName" type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">手机号</label>
						<div class="layui-input-block">
							<input id="searchPhone" type="text" name="telphone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline allotRole">
				      <label class="layui-form-label">性别</label>
				      <div class="layui-input-inline">
				      	<div class="layui-form">
				      		<select name="modules" lay-verify="required" id="roleList">
				      		  <option value="0">不限</option>
					          <option value="F">女</option>
					          <option value="M">男</option>
					        </select>
				      	</div>
				      </div>
				    </div>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin" onclick="conditionSearch()" lay-filter="LAY-user-front-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="layui-card-body">
				<div class="layui-btn-group demoTable">
					<button class="layui-btn" data-type="getCheckData">删除</button>
					<button class="layui-btn" data-type="getCheckLength" onclick="add()">添加</button>
				</div>
				<div class="table">
					<table id="table1" class="layui-table"  lay-filter="demo"></table>
				</div>
				<div id="pagingDiv" style="float:left;height:50px;width:99%;" align="right"></div>
			</div>
		</div>
	</div>
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
		var userName = $("#serchUserName").val();
		var phoneNum = $("#searchPhone").val();
		layui.use(['table','laypage','laydate'], function() {	
			var table = layui.table;
			laypage = layui.laypage;
			table.render({
			    elem: '#table1'
			    ,url: 'getallappuser.do?userName='+userName+'&phoneNum='+phoneNum+'&curnum='+curnum+'&limitcount='+limitcount+'&userSex='+userSex //数据接口
			    ,page: false //开启分页
			    ,id:'idTest'
			    ,filter:'demo'
			    ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
			    ,cols: [[ //表头
			      {type: 'checkbox', fixed: 'left'}
			      ,{field: 'id', title: 'ID',  fixed: 'left'}
			      ,{field: 'username', title: '用户名'}
			      ,{field: 'sex', title: '性别'}
			      ,{field: 'sfzzh', title: '身份证号'}
			      ,{field: 'phone', title: '手机号'}
			      ,{field: 'mail', title: '电子邮箱'}
			      ,{field: 'qqzh', title: 'QQ帐号'} 	
			      ,{field: 'wxzh', title: '微信帐号'} 
			      ,{field: 'wbzh', title: '微博帐号'} 
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
			var accUserId=data.id;
			console.log(obj);	
			if (obj.event === 'del') {
				$.ajax({
					url : "deletebackuser.do",
					type : "post",
					dataType : "json",
					data : {
						flag : accUserId
					},
					success : function(data) {
						
					}
				});
				table.reload('idTest',remakeTable(curnum,limitcount));
			} else if (obj.event === 'edit') {
				//多窗口模式，层叠置顶
		        layer3.open({
			        type: 2 //此处以iframe举例
			        ,title: '编辑基本信息'
			        ,area: ['400px', '700px']
			        ,offset: 'auto'
			        ,content: 'editBaseInfo.do?accUserId='+accUserId
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
						url : "deleteselectbackuser.do",
						type : "post",
						dataType : "json",
						async : false,
						data : {
							"flag" : data[i].id
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
		window.open("registAcc.do?jumpFlag=2");
	}
	function conditionSearch() {
		remakeTable(curnum,limitcount);
	}
</script>
</html>