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

<title>兴趣点详细分析</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/highcharts-zlb.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>



<link rel="stylesheet" href="css/pointdetailanalysis/pointdetailanalysis.css" type="text/css"></link>
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
*{font-family: Microsoft YaHei;font-size: 12px;}
</style>
</head>

<body>
	<div class="maxDiv">
		<div class="first">
			<div class="first-one">&nbsp;&nbsp;<i class="iconfont"style="color:skyblue">&#xe687;</i>&nbsp;年龄分布</div>
			<div id="ageDiv"  class="first-two"></div>
		</div>
		<div class="second">
			<div class="first-one">&nbsp;&nbsp;<i class="iconfont"style="color:#0DCDC0">&#xe616;</i>&nbsp;性别分布</div>
			<div id="sexDiv"  class="first-two"></div>
		</div>
		<div class="third">
			<div class="third-one">&nbsp;&nbsp;<i id="liTitle" class="iconfont"style="color:#0DCDC0">&#xe72d;</i></div>
			<div id="peopleflowDiv"  class="third-two"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var pointType="<%=request.getAttribute("pointType")%>";
	var dateType="<%=request.getAttribute("dateType")%>";
	var date="<%=request.getAttribute("date")%>";
	var result1=<%=request.getAttribute("result1")%>;
	var result2=<%=request.getAttribute("result2")%>;
	$(function(){
		createcolumn(result1.ageDistribution,"ageDiv");
		createpiequan(result1.sexDistribution,"sexDiv");
		if(dateType=="M"){
			var str=date.substring(0,4)+"年"+date.substring(4,6)+"月";
			$("#liTitle").after(" "+str+"人流量变化趋势");
		}else{
			$("#liTitle").after(" 人流量变化趋势");
		}
		createLine(result2,"peopleflowDiv");	
	});
</script>
</html>
