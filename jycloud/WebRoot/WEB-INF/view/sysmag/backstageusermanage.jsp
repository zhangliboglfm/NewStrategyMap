<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>后台用户管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/login.css" type="text/css"></link>
<link rel="stylesheet" href="css/admin.css" type="text/css"></link>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" href="css/backstage.css" type="text/css"></link>

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
					      <label class="layui-form-label">用户名称</label>
					      <div class="layui-input-inline">
					        <input type="text" id="serchPhotogName" autocomplete="off" class="layui-input">
					      </div>
				    </div>
					<div class="layui-inline cstDiv">
						<label class="layui-form-label cstIp">角色</label>
						<div class="layui-input-block">
							<select id="option1"></select>
							<div class="layui-unselect layui-form-select">
								<div class="layui-select-title roleselect">
									<input placeholder="请选择" value="不限" readonly="readonly"
										class="layui-input layui-unselect  selecttitle" type="text">
									<i class="icon1 layui-edge"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-admin" lay-submit
							data-type="getCheckData2">
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
				<div id="table1" class="table">
					<table class="layui-table" lay-data="{height: 700,url:'getallbackuser.do', page:false, id:'idTest',loading:false}" lay-filter="demo">
						<thead>
							<tr>
								<th lay-data="{type:'checkbox', fixed: 'left'}"></th>
								<th lay-data="{field:'id', fixed: true}">ID</th>
								<th lay-data="{field:'username', fixed: true}">用户名</th>
								<th lay-data="{field:'rolename', fixed: true}">角色名</th>
								<th lay-data="{field:'phone', fixed: true}">手机号</th>
								<th lay-data="{field:'mail', fixed: true}">电子邮箱</th>
								<th lay-data="{field:'describe', fixed: true}">描述</th>
								<th lay-data="{fixed: 'right', width:228, align:'center', toolbar: '#barDemo'}"></th>
							</tr>
						</thead>
					</table>
				</div>
				<div id="pagingDiv" style="float:left;height:50px;width:99%;" align="right"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  <a class="layui-btn layui-btn-warm" lay-event="fenpei">角色分配</a>
  </script>
<script type="text/javascript" src="layui/layui.js"></script>
<script>
	var editflag;
	var fenpeiflag;
	$(function() {
		init();
		resizeWindow();
	});
	function renderForm() {
		layui.use('form', function() {
			var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
			form.render();
		});
	}

	function init() {
		$.ajax({
			url : "getinitbackrole.do",
			type : "post",
			dataType : "json",
			async : false,
			data : {},
			success : function(data) {
				$("#option1").html("");
				$("#option1").append("<option value=\"0\" selected=\"selected\">不限</option>");
				for ( var i = 0; i < data.data.length; i++) {
					$("#option1").append("<option value=\""+data.data[i].id+"\">"+ data.data[i].rolename+ "</option>");
				}
				renderForm();
			}
		});
	}
	$(".layui-btn.layuiadmin-btn-admin").click(function() {
		var condition = $("#option1 option:selected").text();
		layui.use('table', function() {
			var table = layui.table;
			table.reload('idTest', {
				url : 'getselectbackuser.do',
				where : {
					condition : encodeURI(condition),
					roleId : $("#option1 option:selected").val()
				}
			//设定异步数据接口的额外参数
			//,height: 300
			});
		});

	});
	function add() {
		$("#layui-layer2").css("display", "block");
		$("#flag").val($(this).parent().parent().parent().find("td").eq(1).find("div").html());
		$("#layui-layer-iframe2").attr("src", "backappadd.do");
	}
	$("#layui-layer-close1").click(function() {
		$("#layui-layer1").css("display", "none");
	});
	$("#layui-layer-close4").click(function() {
		$("#layui-layer4").css("display", "none");
	});
	$("#layui-layer-close2").click(function() {
		$("#layui-layer2").css("display", "none");
	});
	layui.use([ 'form', 'layedit', 'laydate' ],function() {
		var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

		//日期
		laydate.render({
			elem : '#date'
		});
		laydate.render({
			elem : '#date1'
		});
		form.on('submit(demo2)', function(data) {
			table.reload('idTest', {
				url : 'getallbackuser.do',
				where : {}
			});
		});
		//创建一个编辑器
		var editIndex = layedit.build('LAY_demo_editor');
		//自定义验证规则
		form.verify({
			title : function(value) {
				if (value.length < 5) {
					return '标题至少得5个字符啊';
				}
			},
			pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
			content : function(value) {
				layedit.sync(editIndex);
			}
		});

		//监听提交
		form.on('submit(demo3)', function(data) {
			$.ajax({
				url : " get3.do",
				type : "post",
				dataType : "json",
				data : data.field,
				success : function(data) {
					table.reload('idTest', {
						url : 'getallbackuser.do',
						where : {}
					//设定异步数据接口的额外参数
					//,height: 300
					});
				}
			});
		});
	});

	layui.use('table',function() {
		var table = layui.table;

		//监听表格复选框选择
		table.on('checkbox(demo)', function(obj) {
			console.log(obj)
		});

		//监听工具条
		table.on('tool(demo)', function(obj) {
			var data = obj.data;
			if (obj.event === 'del') {
				$("#flag").val($(this).parent().parent().parent().find("td").eq(1).find("div").html());
				editflag = $("#flag").val();
				$.ajax({
					url : "deletebackuser.do",
					type : "post",
					dataType : "json",
					data : {
						flag : editflag
					},
					success : function(data) {

					}
				});
				table.reload('idTest', {
					url : 'getallbackuser.do',
					where : {}
				});
			} else if (obj.event === 'edit') {
				$("#layui-layer4").css("display", "block");
				$("#flag").val(
						$(this).parent().parent().parent()
								.find("td").eq(1).find("div")
								.html());
				$("#layui-layer-iframe4").attr("src","backappedit.do");

			} else if (obj.event === 'fenpei') {
				$("#layui-layer1").css("display", "block");
				$("#flag").val(
						$(this).parent().parent().parent()
								.find("td").eq(1).find("div")
								.html());
				$("#layui-layer-iframe1").attr("src","rolefenpei.do");
			}
		});
		var $ = layui.$, active = {
			getCheckData : function() { //获取选中数据
				var checkStatus = table.checkStatus('idTest'), data = checkStatus.data;
				if (data.length === 0) {
					return layer.msg('请选择数据');
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
					table.reload('idTest', {
						url : 'getallbackuser.do',
						where : {}
					});
				}
			}

		};

		$('.demoTable .layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});
	function reloadtable() {
		layui.use('table', function() {
			var table = layui.table;
			table.reload('idTest', {
				url : 'getallbackuser.do',
				where : {}
			//设定异步数据接口的额外参数
			//,height: 300
			});
		});
	}
	//重制表格
	/* function remakeTable() {
		layui.use('table', function() {
			var table = layui.table;
			table.render({
			    elem: '#table1'
			    ,url: 'getallbackuser.do' //数据接口
			    ,page: true //开启分页
			    ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
			    ,cols: [[ //表头
			      {type: 'checkbox', fixed: 'left'}
			      ,{field: 'id', title: 'ID',  fixed: 'left'}
			      ,{field: 'username', title: '用户名'}
			      ,{field: 'experience', title: '角色名'}
			      ,{field: 'sex', title: '手机号'}
			      ,{field: 'score', title: '电子邮箱'}
			      ,{field: 'city', title: '描述'} 
			      ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
			    ]]
			 });
		 });
	} */
	//
	function resizeWindow() {
		$(".layui-card-body").height($(".layui-card").height()-$(".layuiadmin-card-header-auto").height()-55);
	}
</script>
</html>