<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
<base href="<%=basePath%>">

<title>河北移动战略地图</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" href="css/main.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>

<style type="text/css">
*{font-family: "Microsoft YaHei"}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0px;padding: 0px;}
</style>
</head>

<body>
	<div class="mainnav">
		<i class="iconfont logo">&#xe61e;</i>
		<div class="title">河北移动战略地图</div>
		<div class="nav">
			<div id="divFlux" class="cnav" url="flux.do" onclick="ChangeNav(this)"><i class="iconfont">&#xe600;</i>流量</div>
			<div id="divTerminal" class="cnav" url="terminal.do" onclick="ChangeNav(this)"><i class="iconfont">&#xe626;</i>终端</div>
			<div id="divApp" class="cnav" url="app.do" onclick="ChangeNav(this)"><i class="iconfont">&#xe60d;</i>应用</div>
			<div id="divComplaint" class="cnav" url="complaint.do" onclick="ChangeNav(this)"><i class="iconfont">&#xe617;</i>投诉</div>
			<div id="divSetoff" class="cnav" url="setoff.do" onclick="ChangeNav(this)"><i class="iconfont">&#xe638;</i>集客</div>
			<div id="divNet" class="cnav" url="about:blank" onclick="ChangeNav(this)"><i class="iconfont">&#xe709;</i>网络</div>
			<div id="divSub" class="onav" url="subject.do" onclick="ChangeNav(this)">
				<div class="topicon"><i class="iconfont">&#xe625;</i></div>
				<div class="bottomspan">专题分析</div>
			</div>
			<div id="divMine" class="onav" url="analysis.do" onclick="ChangeNav(this)">
				<div class="topicon"><i class="iconfont">&#xe653;</i></div>
				<div class="bottomspan">我的分析</div>
			</div>
		</div>
	</div>
	<div class="ifrdiv">
		<iframe id="ifrMain" src=""></iframe>
	</div>
</body>
<script type="text/javascript">

	var LvlId ="<%=session.getAttribute("LvlId")%>";
	var regionId ="<%=session.getAttribute("regionId")%>";
	var regionName ="<%=session.getAttribute("regionName")%>";
	
	$(function(){
		ReLayOut();
		$("#divFlux").addClass("cur");
		
		if(LvlId=="3"){
			$("#ifrMain").attr("src","analysis.do");
		}else{
			$("#ifrMain").attr("src",$("#divFlux").attr("url"));
		}
		
	});
	//调整布局
	function ReLayOut(){
		var domHeight = $(window).height();
		var domWidth = $(window).width();
		$(".ifrdiv").height(domHeight-$(".mainnav").height());
		$("#ifrMain").height($(".ifrdiv").height());
	}
	function ChangeNav(e){
		if($(e).attr("id")=="divNet"){
			window.open($(e).attr("url"));			
		}else{
			$("#ifrMain").attr("src",$(e).attr("url"));
			$(".cur").removeClass("cur");
			$(e).addClass("cur");
		}
	
	}
</script>
</html>
