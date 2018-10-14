<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
<base href="<%=basePath%>">
<title>专题分析</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- css 引入 -->

<link rel="stylesheet" href="css/subject/subject.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/regionquery/css/regionquery.blue.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/jedate/skin/jedate.lightblue.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="http://www.hebmcbass.com/service/arcgis_js_api/library/3.14/3.14/esri/css/esri.css">


<!-- js引入 -->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
<script type="text/javascript" src="js/velocity.ui.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="plugin/jedate/jedate.js"></script>
<script type="text/javascript" src="plugin/regionquery/js/regionquery.core.js"></script>
<script type="text/javascript" src="plugin/regionquery/js/miapsoft.numberformate.js"></script>
<script type="text/javascript" src="js/jsp/zlb.subject.fn.js"></script>
<script type="text/javascript" src="http://www.hebmcbass.com/service/arcgis_js_api/library/3.14/3.14/init.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="js/jquery.ui.draggable.js"></script>


<style type="text/css">
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;padding: 0;margin: 0}
.main{width: 100%;height: 100%;position: relative;overflow: hidden;}
/*地图部分*/
.divmap{width: 100%;height: 100%;position: absolute;}
/*比例尺部分*/
.scalediv{position: absolute;bottom: 0px;left: 10px;z-index:5;width: auto;height: 30px;}

@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1523354414177'); /* IE9*/
  src: url('iconfont.eot?t=1523354414177#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
  url('iconfont.ttf?t=1523354414177') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1523354414177#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.explain{
	background: url(image/leftnav.png) no-repeat;
	background-position: center bottom;
	background-size:100% auto;
}
.iconfont1{
	font-size: 14px;
}
.main .selectdiv .regiondiv div.regionselect:AFTER{content: "[切换]";color: #007EB9;cursor: pointer;margin-left: 10px;font-size: 12px}
</style>
</head>
  
<body>
	<div class="main">
		<div class="queryresultdiv">
			共检测到<span class="keyname">商圈</span><span class="sp_num">172</span>个
			<span class ="gridType">
				<span data-gridType="1" class="onegridType" onclick="changeGridType(this)"><span class="spanpoint curspanpoint"></span>250米</span>
				<span data-gridType="2" class="onegridType" onclick="changeGridType(this)"><span class="spanpoint"></span>500米</span>
			</span>
		</div>
		<div class="left">
			<div class="switchleftnav" data-status='1' onclick="switchleftnav(this)"><i class="iconfont" style="color:#008DFB;font-size: 30px;">&#xe659;</i></div>
			
			<div class="explain">专题推荐</div>
			<div  id="divParentNav"  class="divParentNav">
				<div class="leftnav leftnavcur" onclick="ChangeSubject(this)">
					<div class="leftnavicon">&nbsp;&nbsp;&nbsp;<i class="iconfont" style="font-size: 24px;">&#xe619;</i></div>
					<div class="leftnavtitle">高流量4G小区</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon">&nbsp;&nbsp;&nbsp;&nbsp;<i class="iconfont" style="font-size: 24px;">&#xe61f;</i></div>
					<div class="leftnavtitle">低流量4G小区</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon">&nbsp;&nbsp;&nbsp;<i class="iconfont" style="font-size: 28px;">&#xe623;</i></div>
					<div class="leftnavtitle">新售终端用户</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon">&nbsp;<i class="iconfont" style="font-size: 38px;">&#xe629;</i></div>
					<div class="leftnavtitle">网格健康度分析</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon">&nbsp;<i class="iconfont" style="font-size: 40px;">&#xe70d;</i></div>
					<div class="leftnavtitle">重点客户分析</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon">&nbsp;<i class="iconfont" style="font-size: 27px;">&#xe60c;</i></div>
					<div class="leftnavtitle">套餐包推广</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 24px;">&#xe62a;</i></div>
					<div class="leftnavtitle">LTE网新建站</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 28px;">&#xe62f;</i></div>
					<div class="leftnavtitle">LTE倒流小区分析</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 26px;">&#xe62c;</i></div>
					<div class="leftnavtitle">VOLTE终端推广</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 34px;">&#xe636;</i></div>
					<div class="leftnavtitle">加宽用户分析</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon">&nbsp;&nbsp;<i class="iconfont" style="font-size: 30px;">&#xe637;</i></div>
					<div class="leftnavtitle">LTE用户锁网用户分析</div>
				</div>
			</div>
		</div>
		
		<div class="bottom">
			<div class="switchBottom" data-status='1' onclick="switchBottom(this)"><i class="iconfont" style="color:#008DFB;font-size: 30px;">&#xe62e;</i></div>
			<div class="FilterAndRender">
				<div class="title">指标筛选<span class="titleRight"><i class="iconfont">&#xe622;</i>&nbsp;下载</span></div>
				<div id="addIndex" class="IndexAndRange">
					<div class="OneIndex"></div>
				</div>
				<div class="ActionButton">
					<span class="ActionSpan" style="margin-right: 50px;"><i class="iconfont iconfont1">&#xe606;</i>&nbsp;查询</span>
					<span class="ActionSpan"><i class="iconfont iconfont1">&#xe633;</i>&nbsp;重置</span>
				</div>
			</div>	
			<div class="FilterAndRender">
				<div class="title">指标渲染<span class="titleRight" onclick="showRenderIndexDiv()"><i class="iconfont">&#xe604;</i>&nbsp;切换</span><span class="renderIndex" id="renderIndex">户均消费</span></div>
				<div class="IndexAndRange">  
					<div class="oneRender">
						<div class="RenderColor RenderColor1"></div>
						<div class="RenderNum"  contenteditable="true" data-num=""></div>
						<div class="RenderLine"></div>
						<div class="RenderNum" contenteditable="true" data-num=""></div>
					</div>
					<div class="oneRender">
						<div class="RenderColor RenderColor2"></div>
						<div class="RenderNum"  contenteditable="true" data-num=""></div>
						<div class="RenderLine"></div>
						<div class="RenderNum" contenteditable="true" data-num=""></div>
					</div>
					<div class="oneRender">
						<div class="RenderColor RenderColor3"></div>
						<div class="RenderNum"  contenteditable="true" data-num=""></div>
						<div class="RenderLine"></div>
						<div class="RenderNum" contenteditable="true" data-num=""></div>
					</div>
					<div class="oneRender">
						<div class="RenderColor RenderColor4"></div>
						<div class="RenderNum"  contenteditable="true" data-num=""></div>
						<div class="RenderLine"></div>
						<div class="RenderNum" contenteditable="true" data-num=""></div>
					</div>
					<div class="oneRender">
						<div class="RenderColor RenderColor5"></div>
						<div class="RenderNum"  contenteditable="true" data-num=""></div>
						<div class="RenderLine"></div>
						<div class="RenderNum" contenteditable="true" data-num=""></div>
					</div>
				</div>
				<div class="ActionButton">
					<span class="ActionSpan" style="margin-right: 50px;"><i class="iconfont iconfont1">&#xe744;</i>&nbsp;渲染</span>
					<span class="ActionSpan"><i class="iconfont iconfont1">&#xe633;</i>&nbsp;重置</span>
				</div>
			</div>
		</div>
		<!-- 右下角图例显示 -->
		<div class="rightbottom">
			<div class="title">图例</div>
			<div class="tulicontent">
				<div class="onpart"><div class="tulicolor tulicolor1"></div><div class="tuliRange">0-100</div></div>
				<div class="onpart"><div class="tulicolor tulicolor2"></div><div class="tuliRange">100-1000</div></div>
				<div class="onpart"><div class="tulicolor tulicolor3"></div><div class="tuliRange">1000-10000</div></div>
				<div class="onpart"><div class="tulicolor tulicolor4"></div><div class="tuliRange">10000-100000</div></div>
				<div class="onpart"><div class="tulicolor tulicolor5"></div><div class="tuliRange">100000-1000000</div></div>
			</div>
		</div>
		
		<!-- 地域日历搜索框 -->
		<div class="selectdiv">
			<div class="regiondiv">
				<i class="iconfont">&#xe647;</i>
				<div id="divRegion" class="regionselect"></div>
			</div>
			<div class="datetypediv">
				<div id="divDayType" datetype="D" class="typediv curdiv" onclick="ChangeDateType(this)">日</div>
				<div id="divMonthType" datetype="M" class="typediv" onclick="ChangeDateType(this)">月</div>			
			</div>
			<div class="datediv">
				<input id="datepikerStrat" type="text" readonly="readonly"/><i id="iconStratDatepiker" class="iconfont">&#xe621;</i>
			</div>
			<div class="splitdiv">-</div>
			<div class="datediv">
				<input id="datepikerEnd" type="text" readonly="readonly"/><i id="iconEndDatepiker" class="iconfont">&#xe621;</i>
			</div>
			<div class="searchdiv">
				<input id="iptSearch" type="text"/><i id="iSearchIcon" class="iconfont">&#xe632;</i>
			</div>
		</div>
		
		<!-- 地图div -->
		<div id="divmap" class="divmap"></div>
		<!-- 比例尺部分 -->
		<div id="scaleBarDiv" class="scalediv"></div>
		
		<!-- 选择渲染指标 -->
		<div class="getAllIndex1" id="getAllRendaerIndex">
			<div class="title"><span>选择渲染指标</span><i class="iconfont" onclick="closeRenderIndexDiv()">&#xe665;</i></div> 
			<div class="IndClassAndSear">
				<div class="IndClass">
					<span class="hasRenderSelected" onclick="changeRenderIndexType(this)">全部</span>
					<span onclick="changeRenderIndexType(this)">流量</span>
					<span onclick="changeRenderIndexType(this)">终端</span>
					<span onclick="changeRenderIndexType(this)">应用</span>
					<span onclick="changeRenderIndexType(this)">投诉</span>
					<span class="span-last" onclick="changeRenderIndexType(this)">网络</span>
				</div>
				<div class="IndSearch">
					<input type="text"/><i class="iconfont">&#xe632;</i>
				</div>
			</div>
			<div class="tableContent">
				<div class="tableTitle">
					<table class="table1">
						<tr>
							<td>指标名称</td>
							<td>指标口径</td>
							<td>数据周期</td>
							<td>操作</td>
						</tr>
					</table>
				</div>
				<div class="indexTable" id="indexTable">
					<table class="table2" id="table3">
						<tr>
							<td>4G户均流量</td>
							<td>统计周期内，4G用户的户均流量。</td>
							<td>日</td>
							<td onclick="switchRenderIndex(this)" data-MMId="" data-MMName="4G户均流量"><div class="divAddIndex"><i class="iconfont iconfont2">&#xe604;</i><span>切换</span></div></td>
						</tr>
						<tr class="trcolor"> 
							<td>4G低流量用户数</td>
							<td>统计期末在网，且近30天内且产生volte主叫话单的客户数。(volte主叫话单包括volte语音主叫话单、volte视频话单)</td>
							<td>日</td>
							<td onclick="switchRenderIndex(this)" data-MMId="" data-MMName="4G低流量用户数"><div class="divAddIndex"><i class="iconfont iconfont2">&#xe604;</i><span>切换</span></div></td>
						</tr>
						<tr>
							<td>2/3G转4G用户数</td>
							<td>统计周期内，2/3G转为4G用户数。</td>
							<td>日</td>
							<td onclick="switchRenderIndex(this)" data-MMId="" data-MMName="2/3G转4G用户数"><div class="divAddIndex"><i class="iconfont iconfont2">&#xe604;</i><span>切换</span></div></td>
						</tr>
						<tr class="trcolor"> 
							<td>VOLTE服务用户数</td>
							<td>统计周期内，开通VOLTE服务的用户数。</td>
							<td>日</td>
							<td onclick="switchRenderIndex(this)" data-MMId="" data-MMName="VOLTE服务用户数"><div class="divAddIndex"><i class="iconfont iconfont2">&#xe604;</i><span>切换</span></div></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
	</div>
</body>

<script>
	
	var LvlId ="<%=session.getAttribute("LvlId")%>";
	var regionId ="<%=session.getAttribute("regionId")%>";
	var regionName ="<%=session.getAttribute("regionName")%>";
	
	/* 模块化加载地图插件 */
	dojo.require("esri.map");
	dojo.require("dojo._base.event");
	dojo.require("esri.geometry.Point");
    dojo.require("esri.geometry.Polyline");
    dojo.require("esri.geometry.Polygon");
    dojo.require("esri.geometry.Extent");
    dojo.require("esri.geometry.Circle");
    dojo.require("esri.graphic");
    dojo.require("esri.symbols.SimpleMarkerSymbol");
    dojo.require("esri.symbols.SimpleLineSymbol");
    dojo.require("esri.symbols.SimpleFillSymbol");
    dojo.require("esri.SpatialReference");
    dojo.require("esri.layers.GraphicsLayer");
    dojo.require("esri.tasks.FindTask");
    dojo.require("esri.tasks.FindParameters");
    
    
	var defaultpoint=[114.50874900026633, 38.04169000039337];
	var myMap;
	
	var dateType="D";
	var dateStartD="2018-01-01";
	var dateStartM="2018-01";
	var dateEndD="2018-01-01";
	var dateEndM="2018-01";

	var DTypeFormat = "YYYY-MM-DD";
	var MTypeFormat = "YYYY-MM";
		
	/* 日期选择控件配置JSON */
	var jedateStartOption = {
		dateCell: "#datepikerStrat",
		format: DTypeFormat,
		choosefun: function(elem, datas) {
		}
	};
	var jedateEndOption = {
		dateCell: "#datepikerEnd",
		format: DTypeFormat,
		choosefun: function(elem, datas) {
		}
	};
	
	jeDate(jedateStartOption);
	jeDate(jedateEndOption);

	$("#datepikerStrat").val(dateStartD);
	$("#datepikerEnd").val(dateEndD);
	
	$(function(){
		adjust();
		$(window).resize(adjust);
		
		$("#divParentNav").niceScroll({
			cursorcolor : "#8FCAFF",//滚动条显示的颜色
			cursorborderradius: "2px",//滚动条边角圆弧
			cursorwidth: "4px",//滚动条宽度
			cursorborder: "0px solid #293d60",//滚动条边线
			autohidemode:true//是否自动隐藏
		}); 
		
		$("#divRegion").miapRegion({
			"queryurl":"regionObtain.do",
			"lvl":LvlId,
			"cur_region":regionId,
			"cur_regionname":regionName,
			"clickfn":function(data){
			}
		});
		
		$("#iconStratDatepiker").click(function(){
			jeDate(jedateStartOption);
		});
		$("#iconEndDatepiker").click(function(){
			jeDate(jedateEndOption);
		});
		/*数字格式化*/
		formateNumber1("RenderNum");
		
		dojo.addOnLoad(InitMap);
		
		/*jquery ui 插件*/
		$("#getAllRendaerIndex").draggable({
			cursor:"pointer"
		});
	});
	
	
	//加载地图的方法
	function InitMap(){
		myMap = new esri.Map("divmap",{
			"center":defaultpoint,
			"zoom":7,
			slider : true,
			sliderPosition:"bottom-left",
			sliderLabels:["放大","缩小"],
			logo : false
		});
		//加载比例尺
		require(["esri/dijit/Scalebar"],function() {
			var scalebar = new esri.dijit.Scalebar({ map: myMap, scalebarStyle:"line",scalebarUnit: "metric"},dojo.byId("scaleBarDiv"));
        });
		var MapServiceLayer = new esri.layers.ArcGISTiledMapServiceLayer("http://www.hebmcbass.com/arcgis/rest/services/HBMAP_NEW2016/MapServer");
		MapServiceLayer.id="MapServiceLayer";
		myMap.addLayer(MapServiceLayer);
	}
	
	/*调整页面布局*/
	function adjust(){
		$(".switchleftnav").css("top",($(".left").height()-200)/2);
		$(".left .divParentNav").height($(".left").height()-70);
		$(".left .divParentNav").css("max-height",$(".left .divParentNav").height()+"px");
		$(".main .bottom").width($(".main").width()-122);
		$(".switchBottom").css("right",($(".bottom").width()-100)/2);
	}
	
	/* 切换日期类型 */
	function ChangeDateType(e){
		$(".curdiv").removeClass("curdiv");
		$(e).addClass("curdiv");
		dateType=$(e).attr("datetype");
		if(dateType=="D"){
			jedateStartOption.format=DTypeFormat;
			jedateEndOption.format=DTypeFormat;
			$("#datepikerStrat").val(dateStartD);
			$("#datepikerEnd").val(dateEndD);
		}else if(dateType=="M"){
			jedateStartOption.format=MTypeFormat;
			jedateEndOption.format=MTypeFormat;
			$("#datepikerStrat").val(dateStartM);
			$("#datepikerEnd").val(dateEndM);
		}
	}
	
	
	/*打开渲染指标*/
	function showRenderIndexDiv(){
		//ajax, 动态拼接table3内容
		//$("#table3").html();
	 	$("#getAllRendaerIndex").css("display","block");
	};
	/*关闭渲染指标*/
	function closeRenderIndexDiv(){
	 	$("#getAllRendaerIndex").css("display","none");
	};
	
	
	/*切换渲染指标大类*/
	function changeRenderIndexType(obj){
		//ajax, 动态拼接table3内容
		//$("#table3").html();
		$(".hasRenderSelected").removeClass("hasRenderSelected");
		$(obj).addClass("hasRenderSelected");
	};
	
	/*切换渲染指标*/
	function switchRenderIndex(obj){
		//获取指标id
		//var indexId=$(obj).attr("data-MMId");
		// 指标名称
		var indexName=$(obj).attr("data-MMName");
		$("#renderIndex").html(indexName);
	};
	
</script>
</html>
