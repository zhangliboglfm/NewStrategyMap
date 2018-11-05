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

<title>重点业务事实看板</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="plugin/jedate/skin/jedate.css" type="text/css"></link>
<link rel="stylesheet" href="css/main/main.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="plugin/jedate/jedate.js"></script>
<script type="text/javascript" src="js/miapsoft.date.js"></script>

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
	@font-face {font-family: "selIconfont";
	  src: url('plugin/selectlist/css/selectlist.iconfont.eot?t=1488875403519'); /* IE9*/
	  src: url('plugin/selectlist/css/selectlist.iconfont.eot?t=1488875403519#iefix') format('embedded-opentype'), /* IE6-IE8 */
	  url('plugin/selectlist/css/selectlist.iconfont.woff?t=1488875403519') format('woff'), /* chrome, firefox */
	  url('plugin/selectlist/css/selectlist.iconfont.ttf?t=1488875403519') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
	  url('plugin/selectlist/css/selectlist.iconfont.svg?t=1488875403519#selIconfont') format('svg'); /* iOS 4.1- */
	}
	.selIconfont {
	  font-family:"selIconfont" !important;
	  font-size:16px;
	  font-style:normal;
	  -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale;
	}
@font-face {
	font-family: 'LCD7';
	src: url('css/LCD7/Pixel LCD-7.eot');
	src: url('css/LCD7/Pixel LCD-7.ttf'), url('./css/Pixel LCD-7.fon'),
		url('css/LCD7/Pixel LCD-7.eot?#iefix') format('embedded-opentype'),
		url('css/LCD7/Pixel LCD-7.otf') format('otf'),
		url('css/LCD7/Pixel LCD-7.svg') format('svg'),
		url('css/LCD7/Pixel LCD-7.woff') format('woff');
}
*{font-family: Microsoft YaHei;}
html{width: 100%;height: 100%;}
body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0;}
.main{width: 100%;height: 100%;background: url('image/main/beijing.png');background-size: 100% 100%;}
.main .top{
width: 100%;
height: 100px;
background: url('image/main/daohang.png');
background-size: 100% 100%;
position: relative;
}
</style>
</head>

<body>
	<div class="main">
		<div id="divTop" class="top">
			<div id="divFilter" class="filter">
				<div class="timediv">
					<div class="div_datepiker">
						<input id="datepiker" class="inputdate" type="text" readonly="readonly"/>
						<i id="iconDatepiker" class="iconfont">&#xe762;</i>
					</div>
				</div>
				<div class="region">全省</div>
				<div class="bnt">
					<i id="iStartBnt" state="start" class="iconfont">&#xe69a;</i>
				</div>
			</div>
		</div>
		<div id="divNav" class="nav">
			<div class="eachnavdiv cur_eachnavdiv" targetURL="online.do" onclick="changeIfreamSrc(this)">线&nbsp;上</div>
			<div class="eachnavdiv marginwidth" targetURL="outline.do" onclick="changeIfreamSrc(this)">线&nbsp;下</div>
			<div class="eachnavdiv marginwidth" targetURL="linecontrast.do" onclick="changeIfreamSrc(this)">上下线对比</div>
			<div class="eachnavdiv marginwidth" targetURL="packrollout.do" onclick="changeIfreamSrc(this)">套餐转入转出</div>
						<div class="eachnavdiv marginwidth" targetURL="packrolloutdaydata.do" onclick="changeIfreamSrc(this)">套餐转入转出(日数据)</div>
			<div class="eachnavdiv marginwidth" targetURL="packdemocontroller.do" onclick="changeIfreamSrc(this)">4G套餐发展情况</div>
			<div class="eachnavdiv marginwidth" targetURL="packqipaocontroller.do" onclick="changeIfreamSrc(this)">套餐累计趋势</div>
			<div class="eachnavdiv marginwidth" targetURL="packqipaoCURcontroller.do" onclick="changeIfreamSrc(this)">套餐当日趋势</div>
			<div class="eachnavdiv marginwidth" targetURL="packqipaoincomecontroller.do" onclick="changeIfreamSrc(this)">套餐收入趋势</div>
			<div class="eachnavdiv marginwidth" targetURL="businessprocessduration.do" onclick="changeIfreamSrc(this)">业务办理平均耗时</div>	
			<div class="eachnavdiv marginwidth" targetURL="businessprocessdurationdifferent.do" onclick="changeIfreamSrc(this)">不同业务办理时长</div>	
			<div class="eachnavdiv marginwidth" targetURL="packprcontroller.do" onclick="changeIfreamSrc(this)">月人群趋势</div>
		</div>
		<div id="divIfream" class="ifreamdiv">	
			<iframe id="ifrMain" name="childifr" class="ifr" frameborder="no" src=""></iframe>
		</div>
	</div>
</body>
<script type="text/javascript">

	var maxDate="<%=request.getAttribute("maxDate")%>";
	var minDate="<%=request.getAttribute("minDate")%>";
	var timeintervl;
	var state="stop";
/* 日期选择控件配置JSON */
	dateD=minDate.substring(0,4)+"-"+minDate.substring(4,6)+"-"+minDate.substring(6,8);
	datemax=maxDate.substring(0,4)+"-"+maxDate.substring(4,6)+"-"+maxDate.substring(6,8);
	var jedateoption = {
		dateCell:"#datepiker",
		format:"YYYY-MM-DD",
		minDate:dateD,
		maxDate:datemax, 
		choosefun:function(elem, datas) {
			clearInterval(timeintervl);
			window.childifr.main(datas.replace("-","").replace("-",""));
			if(state=="start"){
				timeintervl=setInterval(IntervalFun,5000);
			}else if(state=="stop"){
				clearInterval(timeintervl);
			}
		}
	};
	/* 日期控件初始化 */
	$("#datepiker").val(dateD);
	jeDate(jedateoption);
	
	$(function(){
		$("#iconDatepiker").click(function(){
				jeDate(jedateoption);
			});
		adjust();
		var curdate=$("#datepiker").val().replace("-","").replace("-","");
		$("#ifrMain").attr("src","online.do?curdate="+curdate);
		$("#iStartBnt").click(function(){
			state = $(this).attr("state");
			if(state=="start"){
				$(this).html("&#xe635;");
				$(this).attr("state","stop");
				timeintervl = setInterval(IntervalFun,5000);
			}else if(state=="stop"){
				$(this).html("&#xe69a;");
				$(this).attr("state","start");
				clearInterval(timeintervl);
			}
		});
	});
	var IntervalFun = function(){
			var date=$("#datepiker").val();
			var newdate = getNextDateStr(date,1);
			if(newdate==getNextDateStr(maxDate.substring(0,4)+"-"+maxDate.substring(4,6)+"-"+maxDate.substring(6,8),1)){
				$("#datepiker").val(minDate.substring(0,4)+"-"+minDate.substring(4,6)+"-"+minDate.substring(6,8));
				newdate = minDate;
			}else{
				$("#datepiker").val(newdate.substring(0,4)+"-"+newdate.substring(4,6)+"-"+newdate.substring(6,8));
			}
			window.childifr.main(newdate);
		};
	/* 调整页面布局 */
	function adjust(){
		var domheight = $(window).height();
		var domwidth = $(window).width();
		$("#divIfream").height(domheight-$("#divTop").height()-$("#divNav").height()-14);
		$("#divFilter").css("left",($("#divTop").width()*0.5-$("#divFilter").width()*0.5));
		$("#divNav").css("margin-left",(domwidth*0.5-$("#divNav").width()*0.5));
	}
	/* 切换子页面 */
	function changeIfreamSrc(e){
		var url = $(e).attr("targetURL");
		var curdate=$("#datepiker").val().replace("-","").replace("-","");
		$("#ifrMain").attr("src",url+"?curdate="+curdate);
		changeNavCss(e);
	}
	/* 切换导航样式 */
	function changeNavCss(e){
		$(".cur_eachnavdiv").removeClass("cur_eachnavdiv");
		$(e).addClass("cur_eachnavdiv");
	}
	
	function getCurTime(){
	}
</script>
</html>
