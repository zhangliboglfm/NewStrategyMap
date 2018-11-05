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

<title>历史数据追踪</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>

<style type="text/css">
@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1501571476667'); /* IE9*/
  src: url('iconfont.eot?t=1501571476667#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('iconfont.woff?t=1501571476667') format('woff'), /* chrome, firefox */
  url('iconfont.ttf?t=1501571476667') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1501571476667#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

*{font-family: Microsoft YaHei;}
html{width: 100%;height: 100%;}
body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0; position:relative;}
.baseinfo{
	position: absolute;
	bottom: 10px;
	right: 10px;
	width: auto;
	height: 30px;
	font-size: 16px;
	cursor: pointer;
	color: #2bf3ff;
}
.baseinfo span{text-decoration: underline;}
</style>
</head>

<body>
	历史
	<div class="baseinfo" onclick="changepage()"><span>返回4G套餐信息</span></div>
</body>
<script type="text/javascript">
	$(function(){
				
	});
	function changepage(){
		window.parent.changepage(0);
	}
</script>
</html>
