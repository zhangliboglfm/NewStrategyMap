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

<title>fullpage</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/jquery.fullPage.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easings.min.js"></script>
<script type="text/javascript" src="js/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="js/jquery.fullPage.js"></script>
<style type="text/css">
*{font-family: Microsoft YaHei;font-size: 12px;color: #000;}
html{width: 100%;height: 100%;}
body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0;}
.divmain{width: 100px;height: 100px;}
.div0{background-color: red;font-size: 26px;text-align: center;}
.div1{background-color: orange;font-size: 26px;text-align: center;}
.div2{background-color: blue;font-size: 26px;text-align: center;}
.div3{background-color: green;font-size: 26px;text-align: center;}
</style>
</head>

<body>
	<div id="fullpage" class="divmain">
		<div class="div0 section">第一屏</div>
		<div class="div1 section active">第二屏</div>
		<div class="div2 section">第三屏</div>
		<div class="div3 section">第四屏</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		alert(11);
		$("#fullpage").fullpage({
			navigation:true
		});
	});
</script>
</html>
