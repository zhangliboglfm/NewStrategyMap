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
<title>文章信息导入</title>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/importphotographer.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/importarticle.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
</style>
</head>
<body class="layui-layout-body">
	<div class="top">
		<div class="filenamediv">
			<input id="iptFileName" type="text" name="filename" required lay-verify="required" title="选择文件" placeholder="请选择文件，上传文件仅限ZIP或RAR格式压缩包" autocomplete="off" class="layui-input" readonly="readonly" onclick="clickFileUpload()">
		</div>
		<div class="filediv">
			<button class="layui-btn layui-btn-normal bnt" onclick="clickFileUpload()">选择文件</button>
			<button class="layui-btn layui-btn-normal bnt" onclick="startUpload()">开始上传</button>
			<input id="iptFile" type="file" class="file" accept=".zip,.rar" onchange="getFileInfo()"/>
			
		</div>
		<div class="downloaddiv">
			<button class="layui-btn layui-btn-normal downloadbnt" onclick="downLoadModelFile()">模板下载</button>
		</div>
	</div>
	<div id="divLogInfo" class="info">
	</div>
</body>
<script type="text/javascript">
	var MyFile = new Object();
	MyFile.file = null;
	MyFile.name = null;
	MyFile.size = 0;
	var ot;
	var oloaded;
	$(function(){
		adjust();
		$(window).resize(adjust);
		$("#divLogInfo").niceScroll({
			cursorwidth : "5px",
			cursorcolor: "#1E9FFF",
			cursorborder: "0px",
			autohidemode:true
		});
	});
	//调整页面布局
	function adjust(){
	};
</script>
</html>