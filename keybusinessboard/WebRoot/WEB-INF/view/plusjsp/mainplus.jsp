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

<title>全渠道产品变更发展态势</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="plugin/jedate/skin/jedate.css" type="text/css"></link>
<link rel="stylesheet" href="css/mainplus/mainpuls.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/selectlist/css/selectlist.default1.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="js/miapsoft.date.js"></script>
<script type="text/javascript" src="plugin/jedate/jedate.js"></script>
<script type="text/javascript" src="plugin/selectlist/jquery.nicescroll.js"></script>
<script type="text/javascript" src="plugin/selectlist/miapsoft.autohide.js"></script>
<script type="text/javascript" src="plugin/selectlist/miapsoft.selectlist.core.js"></script>

<style type="text/css">
@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1505273676804'); /* IE9*/
  src: url('iconfont.eot?t=1505273676804#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
  url('iconfont.ttf?t=1505273676804') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1505273676804#iconfont') format('svg'); /* iOS 4.1- */
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
  font-size:20px;
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
html{width: 3840px;height: 1200px;}
body {width: 3840px;height: 1200px;margin: 0;padding: 0; }
.main{width: 3840px;height: 1200px;background-color: #171725;position: relative !important;}

/*顶部导航设置*/
.main .top{width: 100%;height: 151px;background: url('image/mainplus/dhplus.png');background-size: 100% 100%;position: relative;}
</style>
</head>

<body>
	<div class="main">
		<div id="divTop" class="top">
			<div id="divFilter" class="filter" >
				<div class="timediv">
					<div class="div_datepiker">
						<input id="datepiker" class="inputdate" type="text" readonly="readonly"/>
						<i id="iconDatepiker" class="iconfont">&#xe762;</i>
					</div>
				</div>
				<div  id="dishiselect" class="div_select"></div>
				<div class="bnt">
					<i id="iStartBnt" state="start" class="iconfont inconkaishi">&#xe635;</i>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="left">
				<iframe id="ifrMain0" name="childifr0" class="ifr" frameborder="no" src=""></iframe>
			</div>		
			<div class="middle">
				<iframe id="ifrMain1" name="childifr1" class="ifr" frameborder="no" src=""></iframe>
			</div>		
			<div class="right">
				<iframe id="ifrMain2" name="childifr2" class="ifr" frameborder="no" src=""></iframe>
			</div>		
		</div>	
		<div class="qipao4g" id="qipao4g">
			<div class="title"><span class="">套餐变化趋势</span><i class="iconfont closeicon" onclick="closeqipaodiv()">&#xe92a;</i></div>
			<div class="qipaocontent">
				<iframe id="ifrMain3" name="childifr3" class="ifr" frameborder="no" src=""></iframe>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var lvlId="<%=session.getAttribute("lvlId")%>";
	var regionId="<%=session.getAttribute("regionId")%>";
	var dishiData=<%=request.getAttribute("dishijson")%>;
	var maxDate="<%=request.getAttribute("maxDate")%>";
	var minDate="<%=request.getAttribute("minDate")%>";
	var curdate="";
	var state="start";
	//packqipaocontroller.do ifrMain3
	
/* 日期选择控件配置JSON */
	dateD=minDate.substring(0,4)+"-"+minDate.substring(4,6)+"-"+minDate.substring(6,8);
	datemax=maxDate.substring(0,4)+"-"+maxDate.substring(4,6)+"-"+maxDate.substring(6,8);
	var jedateoption = {
		dateCell:"#datepiker",
		format:"YYYY-MM-DD",
		minDate:dateD,
		maxDate:datemax, 
		choosefun:function(elem, datas) {
			curdate=datas.replace("-","").replace("-","");
			window.childifr0.main(datas.replace("-","").replace("-",""),regionId);
			window.childifr1.main(datas.replace("-","").replace("-",""),regionId);
			window.childifr2.main(datas.replace("-","").replace("-",""),regionId);
		}
	};
	/* 日期控件初始化 */
	$("#datepiker").val(datemax);
	jeDate(jedateoption);
	
	$(function(){
		adjust();
		/*加载地市下拉框*/
		if(lvlId=="1"){
			initDishiSelect(regionDishiJsonTransform(dishiData,true));
		}else if(lvlId=="2"){
			initDishiSelect(regionDishiJsonTransform(dishiData,false));
		}
		$("#iconDatepiker").click(function(){
				jeDate(jedateoption);
			});
		curdate=$("#datepiker").val().replace("-","").replace("-","");
		$("#ifrMain0").attr("src","situation4gplus.do?curdate="+curdate+"&regionId="+regionId+"&state="+state);
		$("#ifrMain1").attr("src","generalsituationplus.do?curdate="+curdate+"&regionId="+regionId+"&state="+state);
		$("#ifrMain2").attr("src","flowandpackplus.do?curdate="+curdate+"&regionId="+regionId+"&state="+state);
		
		/*jquery ui 插件*/
		$("#qipao4g").draggable({
			cursor:"pointer"
		});
		
			/*设置轮播开始结束*/
		$("#iStartBnt").click(function(){
		
			if(state=="start"){
				$(this).html("&#xe69a;");
				state="stop";
				/*子页面停止轮播*/
				window.childifr0.endlunbo();
				window.childifr1.endlunbo();
				window.childifr2.endlunbo();
			}else if(state=="stop"){
				$(this).html("&#xe635;");
				state="start";
				/*子页面开始轮播*/
				window.childifr0.beginlunbo(true);
				window.childifr1.beginlunbo(true);
				window.childifr2.beginlunbo(true);
			}
		}); 
	});
	
	
	/* 调整页面布局 */
	function adjust(){
		var domheight = $(window).height();
		var domwidth = $(window).width();
		/* $("#divIfream").height(domheight-$("#divTop").height()-25);
		$("#divFilter").css("left",($("#divTop").width()*0.5-$("#divFilter").width()*0.5));
		$("#divNav").css("left",($("#divTop").width()*0.5-$("#divNav").width()*0.5)); */
	};
	
	function closeqipaodiv(){
		$("#qipao4g").hide();
	}
	function openqipaodiv(){
		$("#qipao4g").show();
		$("#ifrMain3").attr("src","packqipaocontrollerplus.do?curdate="+curdate+"&regionId="+regionId);
	}
	
/*转化地域数据*/
function regionDishiJsonTransform(data,isProvince){
	var jsondata = [];
	if(isProvince){
		jsondata.push({"text":"全部","value":"1"});
	}
	if(data.length != 0){
		for ( var i = 0; i < data.length; i++) {
			var obj = data[i];
			var regionobj = {"text":obj.regionName,"value":obj.regionId};
			jsondata.push(regionobj);
		}
	}
	return jsondata;
}

	/* 加载地市 */
	function initDishiSelect(data,firstval){
		//加载地市地域选框
		$("#dishiselect").miapSelect({
			"data":data,
			"eachLiClick":function(i,t,v){
				regionId=v;
				window.childifr0.main(curdate,regionId);
				window.childifr1.main(curdate,regionId);
				window.childifr2.main(curdate,regionId);
			}
		});
		if(firstval!=undefined&&firstval!=null){
			$("#dishiselect").setSeletedWithIndex(firstval);
		}
	};
</script>
</html>
