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

<title>2017河北移动新版战略地图</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/main/main.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="js/main/main.js"></script>
<style type="text/css">
@font-face {font-family: "iconfont";
  src: url('css/iconfont/iconfont.eot?t=1497250656883'); /* IE9*/
  src: url('css/iconfont/iconfont.eot?t=1497250656883#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('css/iconfont/iconfont.woff?t=1497250656883') format('woff'), /* chrome, firefox */
  url('css/iconfont/iconfont.ttf?t=1497250656883') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('css/iconfont/iconfont.svg?t=1497250656883#iconfont') format('svg'); /* iOS 4.1- */
}
.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
*{font-family: Microsoft YaHei;font-size: 14px;}
html{width: 100%;height: 100%;}
body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0;}
</style>
</head>

<body>
	<div class="topnav">
		<img alt="" src="image/logo.png" width="50" height="50">
		<div class="title">河北移动新版战略地图</div>
		<div class="indexpoolbnt" onclick="showIndexPool()">
			<i class="iconfont">&#xe600;</i>
			<span>查看指标池</span>
		</div>
	</div>
	<div class="ifrdiv">
		<iframe id="ifrMain" class="ifr" src="" frameborder="no"></iframe>
	</div>
	<div id="pop_divIndexPool" class="indexpooldiv draggable">
		<div class="title">指标池<i class="iconfont" onclick="closeIndexPool()" title="关闭">&#xe63f;</i></div>
		<iframe id="ifrIndexPool" class="ifr" src="" frameborder="no"></iframe>
	</div>
</body>
<script type="text/javascript">
	var lvlId="<%=session.getAttribute("lvlId")%>";
	var regionId="<%=session.getAttribute("regionId")%>";
	var regionParentId="<%=session.getAttribute("regionParentId")%>";
	var regionDesc="<%=session.getAttribute("regionDesc")%>";
	var regionName="<%=session.getAttribute("regionName")%>";
	$(function(){
		adjust();
		if(lvlId=="3"){
			$("#ifrMain").attr("src","interestpointrank.do");
		}else{
			$("#ifrMain").attr("src","provincecityrank.do");
		}
		$("#pop_divIndexPool").draggable({
			cursor:"pointer"
		});
	});
	/* 调整布局 */
	function adjust(){
		var domheight = $(window).height();
		var domwidth = $(window).width();
		$("#ifrMain").height(domheight-$(".topnav").height());
		$("#ifrIndexPool").height($("#pop_divIndexPool").height()-$("#pop_divIndexPool .title").height());
	}
</script>
</html>