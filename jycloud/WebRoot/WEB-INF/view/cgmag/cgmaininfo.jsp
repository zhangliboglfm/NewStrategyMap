<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>书法家总览</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<style type="text/css">
*{font-size: 14px;font-family: 微软雅黑;}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
#ifrCgList{
	width: 100%;
	height: 100%;
}
.layui-tab{
	margin-top: 0px !important;
	height: 100% !important;
}
.layui-tab-item{
	width: 100%;
	height: 98%;
}
.ifr{
	width: 100%;
	height: 100%;
}
.solidTab .layui-icon{
	display: none !important;
}
</style>
</head>

<body class="layui-layout-body">
	<div class="layui-tab layui-tab-card" lay-filter="demo" lay-allowclose="true">
		<ul class="layui-tab-title">
			<li class="layui-this cantdele solidTab" >书法家列表</li>
		</ul>
		<div class="layui-tab-content"  style="margin-top:2px;position: relative;">
			<div class="layui-tab-item layui-show">
				<iframe id="ifrCgList" src="" frameborder="no" ></iframe>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var form = layui.form,laydate=layui.laydate,layer = layui.layer,laypage = layui.laypage,element=layui.element;
	$(function(){
		adjust();
		$(window).resize(adjust);
		$("#ifrCgList").attr("src","cginfolist.do");
	});
	//调整页面布局
	function adjust(){
		$(".layui-tab-content").height($(".layui-tab").height()-$(".layui-tab-title").height());
	};
	//添加tab切换页面
	function addTable(data){
		if($("li[lay-id='"+data["lay-id"]+"']").size()<=0){
			element.tabAdd('demo', {
		        title: data["title"] //用于演示
		        ,content:"<iframe frameborder='no' class='ifr'   src='"+data["src"]+"'></iframe>"
		        ,id:data["lay-id"] //实际使用一般是规定好的id，这里以时间戳模拟下
		      });
		}
		$(".layui-show").removeClass("layui-show");
		$(".layui-this").removeClass("layui-this");
		var index =$("li[lay-id='"+data["lay-id"]+"']").index();
		$(".layui-tab-content .layui-tab-item").eq(index).addClass("layui-show");
		$("li[lay-id='"+data["lay-id"]+"']").addClass("layui-this");
		//$(".layui-tab-content .layui-tab-item .ifr").attr("src",data["src"]);
	};
</script>
</html>
