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

<title>重点区域</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" href="css/keyarea.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/regionquery/css/regionquery.blue.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/jedate/skin/jedate.lightblue.css" type="text/css"></link>
<!-- css 引入 -->
<link rel="stylesheet" type="text/css" href="http://www.hebmcbass.com/service/arcgis_js_api/library/3.14/3.14/esri/css/esri.css">

<!-- js引入 -->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
<script type="text/javascript" src="js/velocity.ui.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="plugin/regionquery/js/regionquery.core.js"></script>
<script type="text/javascript" src="plugin/jedate/jedate.js"></script>
<script type="text/javascript" src="http://www.hebmcbass.com/service/arcgis_js_api/library/3.14/3.14/init.js"></script>
<script type="text/javascript" src="js/jsp/bsh.keyarea.fn.js"></script>
<script type="text/javascript" src="plugin/regionquery/js/miapsoft.numberformate.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="js/jquery.ui.draggable.js"></script>

<style type="text/css">
*{font-family: "Microsoft YaHei";}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0px;overflow: hidden;}
@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1525740941519'); /* IE9*/
  src: url('iconfont.eot?t=1525740941519#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
  url('iconfont.ttf?t=1525740941519') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1525740941519#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.selectdiv .regiondiv div.regionselect:AFTER{content: "[切换]";color: #007EB9;cursor: pointer;margin-left: 10px;font-size: 12px}

</style>
</head>

<body>
	<div class="main">
		<div class="left">
			<div class="switchleftnav" data-status='1' onclick="switchleftnav(this)"><i class="iconfont" style="color:#008DFB;font-size: 30px;">&#xe659;</i></div>
			<div class="explain">区域类型</div>
			<div  id="divParentNav"  class="divParentNav">
				<div class="leftnav leftnavcur" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 30px;">&#xe63a;</i></div>
					<div class="leftnavtitle">500网格</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 24px;">&#xe639;</i></div>
					<div class="leftnavtitle">250网格</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 24px;">&#xe60a;</i></div>
					<div class="leftnavtitle">住宅区</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 28px;">&#xe68c;</i></div>
					<div class="leftnavtitle">商圈</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 24px;">&#xe7c0;</i></div>
					<div class="leftnavtitle">高校</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 24px;">&#xe630;</i></div>
					<div class="leftnavtitle">写字楼</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 24px;">&#xe66d;</i></div>
					<div class="leftnavtitle">景区</div>
				</div>
				<div class="leftnav" onclick="ChangeSubject(this)">
					<div class="leftnavicon"><i class="iconfont" style="font-size: 26px;">&#xe6c6;</i></div>
					<div class="leftnavtitle">枢纽</div>
				</div>
			</div>
		</div>
		<div class="bottom">
			<div class="switchBottom" data-status='1' onclick="switchBottom(this)"><i class="iconfont" style="color:#008DFB;font-size: 30px;">&#xe62e;</i></div>
			<div class="FilterAndRender">
				<div class="title">指标筛选<span class="titleRight"><i class="iconfont">&#xe622;</i>&nbsp;下载</span></div>
				<div class="IndexAndRange" id="IndexAndRange">
					<div class="div-last"  id="div-last"><div class="buttonSpan" onclick="showIndexDiv()"><i class="iconfont">&#xe61d;</i>&nbsp;&nbsp;<span>点击添加指标</span></div></div>
				</div>
				<div class="ActionButton">
					<span class="ActionSpan" style="margin-right: 50px;"><i class="iconfont iconfont1">&#xe606;</i>&nbsp;查询</span>
					<span class="ActionSpan" style="margin-right: 50px;" onclick="restart()"><i class="iconfont iconfont1">&#xe633;</i>&nbsp;重置</span>
					<span class="ActionSpan" onclick="saveThoseIndexs()" ><i class="iconfont iconfont1">&#xe60b;</i>&nbsp;保存</span>
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
		<!-- 地图div -->
		<div id="divmap" class="divmap">
		</div>
		<!-- 比例尺部分 -->
		<div id="scaleBarDiv" class="scalediv"></div>
		<div class="queryresultdiv"><span class="spn">共检索到<span class="spn_type">商圈</span><span class="spn_num">11</span>个</span></div>
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
		
		<!-- 选择查询指标 -->
		<div class="getAllIndex" id="getAllIndex">
			<div class="title"><span>选择查询指标</span><i class="iconfont" onclick="closeIndexDiv()">&#xe665;</i></div> 
			<div class="IndClassAndSear">
				<div class="IndClass">
					<span class="hasSelected" onclick="changeIndexType(this)">全部</span>
					<span onclick="changeIndexType(this)">流量</span>
					<span onclick="changeIndexType(this)">终端</span>
					<span onclick="changeIndexType(this)">应用</span>
					<span onclick="changeIndexType(this)">投诉</span>
					<span class="span-last" onclick="changeIndexType(this)">网络</span>
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
					<table class="table2" id="table2">
						<tr>
							<td>4G户均流量</td>
							<td>统计周期内，4G用户的户均流量。</td>
							<td>日</td>
							<td onclick="addIndex(this)" data-MMId="" data-MMName="4G户均流量"><div class="divAddIndex"><i class="iconfont">&#xe61d;</i><span>添加</span></div></td>
						</tr>
						<tr class="trcolor"> 
							<td>4G低流量用户数</td>
							<td>统计期末在网，且近30天内且产生volte主叫话单的客户数。(volte主叫话单包括volte语音主叫话单、volte视频话单)</td>
							<td>日</td>
							<td onclick="addIndex(this)" data-MMId="" data-MMName="4G低流量用户数"><div class="divAddIndex"><i class="iconfont">&#xe61d;</i><span>添加</span></div></td>
						</tr>
						<tr>
							<td>2/3G转4G用户数</td>
							<td>统计周期内，2/3G转为4G用户数。</td>
							<td>日</td>
							<td onclick="addIndex(this)" data-MMId="" data-MMName="2/3G转4G用户数"><div class="divAddIndex"><i class="iconfont">&#xe61d;</i><span>添加</span></div></td>
						</tr>
						<tr class="trcolor"> 
							<td>VOLTE服务用户数</td>
							<td>统计周期内，开通VOLTE服务的用户数。</td>
							<td>日</td>
							<td onclick="addIndex(this)" data-MMId="" data-MMName="VOLTE服务用户数"><div class="divAddIndex"><i class="iconfont">&#xe61d;</i><span>添加</span></div></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		
		<!-- 选择渲染指标 -->
		<div class="getAllIndex1" id="getAllRendaerIndex">
			<div class="title"><span>选择渲染指标</span><i class="iconfont" onclick="closeRenderIndexDiv()">&#xe665;</i></div> 
			<div class="IndClassAndSear">
				<div class="IndClass1">
					流量类指标:
				</div>
				<div class="IndSearch1">
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
		
		<!-- 场景保存div -->
		<div class="divSave">
			<div class="title"><span>自定义分析</span><i class="iconfont" onclick="undoCJ()">&#xe665;</i></div>
			<div class="oneline"><div class="name">名称:</div><div class="divcontext" contenteditable="true" id="CJName"></div></div>
			<div class="oneline"><div class="name">创建人电话:</div><div class="divcontext" contenteditable="true" id="PhoneNum"></div></div>
			<div class="oneline"><div class="name1">场景描述:</div></div>
			<div class="textarea" contenteditable="true" id="CJDesc"></div>
			<div class="buttonline"><span class="divButton" onclick="saveCJ()">确认</span><span class="divButton" onclick="undoCJ()">取消</span></div>
		</div>
	</div> 
</body>
<script type="text/javascript">

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
		 
		$("#indexTable,#IndexAndRange").niceScroll({
			cursorcolor : "#8FCAFF",//滚动条显示的颜色
			cursorborderradius: "1px",//滚动条边角圆弧
			cursorwidth: "1px",//滚动条宽度
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
		$("#getAllIndex,#getAllRendaerIndex").draggable({
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
	};
	/*调整页面布局*/
	function adjust(){
		$(".switchleftnav").css("top",($(".left").height()-200)/2);
		$(".left .divParentNav").height($(".left").height()-70);
		$(".left .divParentNav").css("max-height",$(".left .divParentNav").height()+"px");
		$(".main .bottom").width($(".main").width()-122);
		$(".switchBottom").css("right",($(".bottom").width()-100)/2);
		$(".divSave").css("left",($(".main").width()-300)/2);
	};
	
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
	};
	
	/*切换不同的专题*/
	function ChangeSubject(obj){
		$(".leftnavcur").removeClass("leftnavcur");
		console.log($(this));
		$(obj).addClass("leftnavcur");
	};
	
	/*切换查询指标大类*/
	function changeIndexType(obj){
		//ajax, 动态拼接table2内容
		//$("#table2").html();
		$(".hasSelected").removeClass("hasSelected");
		$(obj).addClass("hasSelected");
	};
	
	/*关闭查询指标*/
	function closeIndexDiv(){
	 	$("#getAllIndex").css("display","none");
	};
	
	/*打开查询指标*/
	function showIndexDiv(){
		//ajax, 动态拼接table2内容
		//$("#table2").html();
	 	$("#getAllIndex").css("display","block");
	};
	
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
	
	/*切换渲染指标*/
	function switchRenderIndex(obj){
		//获取指标id
		//var indexId=$(obj).attr("data-MMId");
		// 指标名称
		var indexName=$(obj).attr("data-MMName");
		$("#renderIndex").html(indexName);
	};
	/*添加指标*/
	function addIndex(obj){
		//获取指标id
		//var indexId=$(obj).attr("data-MMId");
		// 指标名称
		var indexName=$(obj).attr("data-MMName");
		var str="<div class='divAdd'>"+
					"<div class='IndexName'>"+indexName+"</div>"+
					"<div class='IndexRange'  contenteditable='true' data-num=''></div>"+
					"<div class='RenderLine'></div>"+
					"<div class='IndexRange' contenteditable='true' data-num=''></div>"+
					"<div class='buttonDelete' onclick='deleteThisIndex(this)'><i class='iconfont'>&#xe607;</i></div>"+											
				 "</div>";
		$(str).insertBefore($("#div-last"));
		/*数字格式化*/
		formateNumber1("IndexRange");
	};
	
	/*删除当前指标*/
	function deleteThisIndex(obj){
		$(obj).parent(".divAdd").remove();
	};
	
	/*指标重置*/
	function restart(){
		$(".divAdd").remove();
	};
	
	/*场景保存div展示*/
	function saveThoseIndexs(){
		$(".divSave").css("display","block");
	};
	
	/*取消场景保存*/
	function undoCJ(){
		$("#CJName,#PhoneNum,#CJDesc").html("");
		$(".divSave").css("display","none");
	}; 
	/*保存场景*/ 
	function saveCJ(){
		alert("保存成功");
		$("#CJName,#PhoneNum,#CJDesc").html("");
		$(".divSave").css("display","none");
	};
</script>
</html>
