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
<title>文章主页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
.layui-main{width: 100%;height: 100%;overflow: hidden;}
.layui-content{width: 100%;}
.layui-ifr{width: 100%;height: 100%;}
.layui-breadcrumb{width:100%;height:35px;font-size: 13px;}
.tagClass{float:left;width:95px;height:35px;line-height:35px;text-align:center;border-right:1px solid #F2F2F2;cursor: pointer; }
.tagClass:HOVER {color: #009688}
.tagClass_bk{color: #009688}
.abc{
	display: inline-block;
	width: 95px;
	height: 35px;
	font-size: 13px;
}
</style>
</head>

<body>
<div class="layui-main">
<!-- <span class="layui-breadcrumb" lay-separator="|">
  <a class="abc" href="">娱乐</a>
  <a class="abc" href="">八卦</a>
  <a class="abc" href="">体育</a>
</span> -->
	<div class="layui-breadcrumb">
	  <div id="ARTICLE" class="tagClass tagClass_bk" onclick="zhuanti()">专题文章</div>
	  <div id="KEY" class="tagClass" onclick="hexin()">核心介绍</div>
	  <div id="READ" class="tagClass" style="border-right:0px solid #F2F2F2;">作品解读</div>
	</div>
	<div class="layui-content">
		<iframe class="layui-ifr" id="myifr" src="" frameborder="no"></iframe>
	</div>
</div>
</body>
<script type="text/javascript">
	
	$(function(){
		adjust();
		$(window).resize(adjust);
		$("#myifr").attr("src","zhuanti.do");
		
	});
	$(".tagClass").click(function(){
		$(this).addClass("tagClass_bk");
		$(this).siblings().removeClass("tagClass_bk");
	})
	function zhuanti(){
		$("#myifr").attr("src","zhuanti.do");
	}
	function hexin(){
		$("#myifr").attr("src","hexin.do");
	}
	/* function zuopin(){
		$("#myifr").attr("src","zuopin.do");
	} */
	//调整页面布局
	function adjust(){
		var H = $(".layui-main").height();
		var W = $(".layui-main").width();
		$(".layui-content").height(H-$(".layui-breadcrumb").height());
	};	
</script>
</html>