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
<title>信息导入</title>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
.ifr{width: 100%;height: 100%;border: 0}
.layui-tab{height: 100% !important;}
.layui-tab-item{height: 100% !important;}
</style>
</head>
<body class="layui-layout-body">
	<div class="layui-tab">
	  <ul class="layui-tab-title">
	    <li class="layui-this">摄影家</li>
	    <li>文章</li>
	  </ul>
	  <div class="layui-tab-content">
	    <div class="layui-tab-item layui-show">
	      	<iframe id="ifrPhotographer" class="ifr" src="importphotographer.do"></iframe>
	    </div>
	    <div class="layui-tab-item">
	    	<iframe id="ifrArticle" class="ifr" src="importarticle.do"></iframe>
	    </div>
	  </div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		adjust();
		$(window).resize(adjust);
	});
	//调整页面布局
	function adjust(){
		var domHeight = $(window).height();
		$(".layui-tab-content").height(domHeight-$(".layui-tab-title").height());
	};
</script>
</html>