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
<title>摄影家详情</title>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
</style>
</head>
<body class="layui-layout-body">
	
</body>
<script type="text/javascript">
	
	$(function(){
		
		adjust();
		$(window).resize(adjust);
		
	});
	
	
	//调整页面布局
	function adjust(){
	
	};
	
</script>
</html>