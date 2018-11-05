<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>弹出层</title>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/importphotographer.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>

<style>
html{width: 100%;height: 100%;margin: 0;padding: 0}
body{width: 100%;height: 100%;margin: 0;padding: 0}
</style>
</head>
<body class="layui-layout-body">
	<div id="divLogInfo" class="info">
		<div id="table1" class="table"></div>
	</div>
	<!-- <div id="shadow">
		<div id="loadIcon">
			<img src="image/loading.gif"></img>
		</div>
	</div>	 -->
</body>

<script type="text/javascript">
	$(function(){
		adjust();
		$(window).resize(adjust);
		$("#divLogInfo").niceScroll({
			cursorwidth : "5px",
			cursorcolor: "#1E9FFF",
			cursorborder: "0px",
			autohidemode:true
		}); var abc=window.parent.data2;
		var responsedata = eval(abc)
		getTable(responsedata);
	});
	//调整页面布局
	function adjust(){
		var domheight = $(window).height();
		$("#divLogInfo").height(domheight-$(".title").height()-15-$(".top").height()-15-40);
	};
	var tableIns;
	function getTable(data){
	layui.use('table', function(){
	var table = layui.table;
	//执行渲染
		tableIns = table.render({
		elem: '#table1' //指定原始表格元素选择器（推荐id选择器）
		,height: 315 //容器高度
		,data:data
		,cols:  [[ //标题栏
		{field: 'name', title: '姓名'}
		,{field: 'sex', title: '性别'}
		,{field: 'nation', title: '民族'}
		,{field: 'dynasty', title: '朝代'}
		,{field: 'birthday', title: '出生时间'}
		,{field: 'deathday', title: '去世时间'}
		,{field: 'word', title: '字'}
		,{field: 'number', title: '号'}
		,{field: 'ancestralhome', title: '祖籍'}
		,{field: 'birtharea', title: '出生地'}
		,{field: 'othername', title: '别称'}
		,{field: 'status', title: '地位'}
		,{field: 'importantworks', title: '重要作品'}
		,{field: 'importantdeeds', title: '重要事迹'}
		]]//设置表头
		//,…… //更多参数参考右侧目录：基本参数选项
	});
	});
}
</script>
</html>