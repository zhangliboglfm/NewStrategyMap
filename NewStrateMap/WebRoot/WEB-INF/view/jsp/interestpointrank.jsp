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

<title>2017河北移动新版战略地图--兴趣点分析</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- CSS引入 -->
<link rel="stylesheet" href="css/interestpointrank/interestpointrank.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/jedate/skin/jedate-common.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/jedate/skin/jedate.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/selectlist/css/selectlist.default.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="http://www.hebmcbass.com/service/arcgis_js_api/library/3.14/3.14/esri/css/esri.css">
<!-- JS引入 -->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="plugin/jedate/jedate.js"></script>
<script type="text/javascript" src="plugin/selectlist/jquery.nicescroll.js"></script>
<script type="text/javascript" src="plugin/selectlist/miapsoft.autohide.js"></script>
<script type="text/javascript" src="plugin/selectlist/miapsoft.selectlist.core.js"></script>
<script type="text/javascript" src="js/interestpointrank/interestpointrank.js"></script>
<script type="text/javascript" src="js/miapsoft.input.core.js"></script>
<script type="text/javascript" src="http://www.hebmcbass.com/service/arcgis_js_api/library/3.14/3.14/init.js"></script>

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
*{font-family: Microsoft YaHei;font-size: 12px;}
html{width: 100%;height: 100%;}
body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0;position: relative;}
/* 地图悬浮框样式 */
.esriPopup .titlePane{background-color: #00AFFF !important;}
.esriPopup  .maximize.titleButton{display: none !important;}
</style>
</head>

<body id="body">
	<div class="filter">
		<div class="datetype">
			<div id="divDayType" class="eachdatetype cur_eachdatetype" datetype="D" onclick="ChangeDateType(this)">日</div>
			<div id="divMonthType" class="eachdatetype" datetype="M" onclick="ChangeDateType(this)">月</div>
		</div>
		<div class="div_datepiker"><input id="datepiker" type="text" readonly="readonly"/><i id="iconDatepiker" class="iconfont">&#xe610;</i></div>
		<div id="dishiselect" class="div_select"></div>
		<div id="xianquselect" class="div_select"></div>
	</div>
	<div id="divIprtype" class="iprtype">
		<div id="HOME" class="eachtype1 noleftboder cur_eachtype" type="HOME" onclick="ChangeIPRType(this)"><i class="iconfont" fcolor="#DDB756">&#xe60d;</i>住宅(<span>0</span>)</div>
		<div id="SHOP" class="eachtype1" type="SHOP" onclick="ChangeIPRType(this)"><i class="iconfont" fcolor="#17FFEB">&#xe65e;</i>商圈(<span>0</span>)</div>
		<div id="EDUCATION" class="eachtype1" type="EDUCATION" onclick="ChangeIPRType(this)"><i class="iconfont" fcolor="#A783E3">&#xe61f;</i>学校(<span>0</span>)</div>
		<div id="HEALTH_CARE" class="eachtype2 noleftboder" type="HEALTH_CARE" onclick="ChangeIPRType(this)"><i class="iconfont" fcolor="red">&#xe63d;</i>医院(<span>0</span>)</div>
		<div id="GOVERNMENT" class="eachtype2 " type="GOVERNMENT" onclick="ChangeIPRType(this)"><i class="iconfont" fcolor="#5CC451">&#xe691;</i>机关(<span>0</span>)</div>
		<div id="TRADING_ESTATE" class="eachtype2" type="TRADING_ESTATE" onclick="ChangeIPRType(this)"><i class="iconfont" fcolor="#C5529D">&#xe659;</i>企业(<span>0</span>)</div>
		<div id="ORTHER" class="eachtype2" type="ORTHER" onclick="ChangeIPRType(this)"><i class="iconfont" fcolor="#2D81B0">&#xe642;</i>其他(<span>0</span>)</div>
	</div>
	<div id="divMapCurIndex" class="indexselect">
		<div class="title">
			<span>地图当前地域指标信息:</span>
			<div class="icon" onclick="downloadIndex()"><i class="iconfont FONT-1">&#xe624;</i>下载</div>
			<div class="icon leftboder" onclick="switchIndex()"><i class="iconfont">&#xe628;</i>切换</div>
		</div>
		<div class="main"></div>
		<input id="iptCurIndexId" type="hidden" defaultindexname="移动用户数" value="LTE001"/>
	</div>
	<div id="divSearchIndex" class="searchindex">
		<div class="title">搜索指标<i class="iconfont" title="关闭" onclick="CloseSearchDiv()">&#xe63f;</i></div>
		<div class="main">
			<input id="iptIndexKey" class="indexipt" type="text" title="指标搜索" defaulttxt="请输入 指标关键字/指标分类"/>
			<i class="iconfont" onclick="queryForIndex()">&#xe617;</i>
		</div>
	</div>
	<div id="divCurMapRelIndexInfo" class="curmaprelindexinfo">
		<div class="title"><span class="maprelindexname"></span><span>--相关影响指标</span><i class="iconfont" title="关闭" onclick="closeMapCurRelIndexInfo()">&#xe63f;</i></div>
		<div class="main"></div>
	</div>
	<div class="navigation">
		<div class="nav-first">图例<span id="spnLegendUnit" class="legendunit"></span></div>
		<div class="nav-left">
			<div class="nav-left-first">
				<div>
					<div id="CITY" class="province-city-point" onclick="navigatorChange(this)">全省</div>
					<div id="COUNTY" class="province-city-point" onclick="navigatorChange(this)">全市</div>
					<div id="POINT" class="province-city-point setColor" onclick="navigatorChange(this)">兴趣点</div>
				</div>
			</div>
			<div class="nav-left-second">
				<div class="nav-left-second-one">&nbsp;<i  class="iconfont font3">&#xe644;</i></div>
				<div class="nav-left-second-one">&nbsp;<i  class="iconfont font3">&#xe644;</i></div>
				<div class="nav-left-second-one">&nbsp;<i  class="iconfont font3">&#xe644;</i></div>
			</div>
			<div class="nav-left-third">
				<li>
					<i class="iconfont bigger" onclick="bigger()">&#xe633;</i><br>
					<i class="iconfont ">&#xe688;</i><br>
					<i class="iconfont ">&#xe688;</i><br>
					<i class="iconfont ">&#xe688;</i><br>
					<i class="iconfont ">&#xe688;</i><br>
					<i class="iconfont ">&#xe688;</i><br>
					<i class="iconfont ">&#xe688;</i><br>
					<i class="iconfont font2 smaller " onclick="smaller()">&#xe631;</i>
				</li>
			</div>
		</div>
		<div class="nav-right">
			<div class="nav-right-one"><div class="nav-right-one-color1"></div><div id="divKeyValue1" class="nav-right-two-second"></div></div>
			<div class="nav-right-one"><div class="nav-right-one-color2"></div><div id="divKeyValue2" class="nav-right-two-second"></div></div>
			<div class="nav-right-one"><div class="nav-right-one-color3"></div><div id="divKeyValue3" class="nav-right-two-second"></div></div>
		</div>
	</div>
	<div id="divMapSearch" class="mapSearch">
		<div class="inputdiv">
			<input id="iptMapSearch" type="text" defaulttxt="请输入兴趣点关键字"/>
			<i class="iconfont" onclick="clearinput()" title="取消">&#xe640;</i>
		</div>
		<div class="bntdiv"><i class="iconfont" onclick="queryForPoint()">&#xe606;</i></div>
	</div>
	<div id="divMapSearchResult" class="mapsearchresult"></div>
	<div class="scenelist">
		<i class="iconfont icon1">&#xe609;</i>
		<span class="text">场景定制<span id="cur_scene" class=""></span></span>
		<i id="iSceneListBnt" class="iconfont icon2" onclick="switchSceneList(this)">&#xe666;</i>
	</div>
	<div id="divSceneList" class="scenelistdiv">
		<div class="recommendscene">
			<div class="title"><i class="iconfont">&#xe60e;</i>推荐场景</div>
			<div id="divRSList" class="mainlist"></div>
		</div>
		<div class="myscene">
			<div class="title"><i class="iconfont">&#xe65d;</i>我的场景</div>
			<div id="divMSList"  class="mainlist"></div>
		</div>
		<div id="divCustomScene" class="clickbnt" onclick="SceneChange()">场景定制</div>
	</div>
	<div id="divCurSceneInfo" class="cursceneinfodiv">
		<div class="title">
			<div class="curscene">当前场景：<span id="spnCurSceneName">--</span><span class="icon" onclick="downloadSceneIndex()"><i class="iconfont downloadscene" title="下载场景">&#xe624;</i>下载</span></div>
			<div class="curindex">当前指标：<span id="spnCurSceneIndexName">--</span><span class="icon" onclick="sceneSwitchIndex()"><i class="iconfont switchindex" title="切换指标">&#xe628;</i>切换</span></div>
		</div>
		<div class="quitbnt" onclick="quitScene()">退出当前场景</div>
	</div>
	<div id="divRegionInfo" class="defaultallinfo">
		<div class="titlediv">
			<div id="divRegionName" class="regiontitle">
				<i class="iconfont font1">&#xe634;</i>&nbsp;<span>--</span>
			</div>
			<div id="divPointTitle" class="pointtitle">
				<div class="baseinfo">
					<div class="name"></div>
					<div class="detail" onclick="showPointDetailPop(this)">详情</div>
					<div class="star"></div>
					<div class="contrats" onclick="addToContratsDiv(this)">添加对比</div>
					<div class="type"></div>
					<div class="data"><span class="text"></span><span class="value"></span></div>
				</div>
				<div class="otherbntdiv">
					<div class="left_bnt" pointid="" curstate="hide" onclick="togglePointNearYYTData(this)"><i class="iconfont">&#xe60c;</i><span>显示附近营业厅</span></div>
					<div class="right_bnt" pointid="" curstate="hide" onclick="togglePointNearBTSData(this)"><i class="iconfont">&#xe7da;</i><span>显示附近基站</span></div>
				</div>
			</div>
		</div>
		<div id="divRegionIndex" class="indexdiv"></div>
	</div>
	<div id="divQueryIndexResult" class="searchindexresult"></div>
	<div id="divmap" class="map"></div>
	<div id="scaleBarDiv" class="scalediv"></div>
	<div id="pop_divPointDetailInfo" class="pointdetaildiv">
		<div class="title"><span>--</span><i class="iconfont" title="关闭" onclick="closePointDetailPop()">&#xe63f;</i></div>
		<div class="main">
			<iframe id="ifrPointDetail" class="detailifr" frameborder="no"></iframe>
		</div>
	</div>
	<div id="divSelContratsBnt" class="contratslistdiv" onclick="showContratsList(this)">
		<span>已选对比(<span class="num">0</span>)</span><i class="iconfont">&#xe601;</i>
	</div>
	<div id="divSelPointList" class="selectcontrats">
		<div class="title">已选&nbsp;<span class="num">0</span>&nbsp;兴趣点</div>
		<div class="list"></div>
		<div class="bnt"><input class="startbnt" type="button" value="开始对比" onclick="startPointContrats()"/></div>
	</div>
	<div id="pop_divAddRelIndex" class="addrelindex">
		<div class="title"><span></span><i class="iconfont" title="关闭" onclick="closeAddRelIndexPop()">&#xe63f;</i></div>
		<div class="main">
			<iframe id="ifrAddRelIndex" class="detailifr" frameborder="no"></iframe>
		</div>
	</div>
	<div id="divMapTypeSwicth" class="maptypeswicth">
 		<div class="point curmodel" mapmodel="POINT" onclick="switchMapModel(this)">兴趣点模式</div>
		<div class="grid" mapmodel="GRID" onclick="switchMapModel(this)">网格模式</div>
	</div>
	<div id="pop_divGridIndexInfo" class="gridindexinfo">
		<div class="title">
			<span id="spnGridId"></span>
			<i class="iconfont" title="关闭" onclick="hideGridInfoPopDiv()">&#xe63f;</i>
		</div>
		<div id="divGridIndexInfo" class="mian"></div>
	</div>
</body>
<script type="text/javascript">
	var dateType="D";
	var dateD="";
	var dateM="";
	
	var lvlId="<%=session.getAttribute("lvlId")%>";
	var regionId="<%=session.getAttribute("regionId")%>";
	
	var curdishiid = "<%=request.getAttribute("curdishiid")%>";
	var curxianquid = "<%=request.getAttribute("curxianquid")%>";
	var curindexid = "<%=request.getAttribute("curindexid")%>";
	var curdate = "<%=request.getAttribute("curdate")%>";
	var curdatetype = "<%=request.getAttribute("curdatetype")%>";
	if(curdatetype=="D"){
		curdate = curdate.substring(0,4)+"."+curdate.substring(4,6)+"."+curdate.substring(6,8);
	}else if(curdatetype=="M"){
		curdate = curdate.substring(0,4)+"."+curdate.substring(4,6);
	}
	
	var maxDateD = "<%=request.getAttribute("maxDateD")%>";
	var minDateD = "<%=request.getAttribute("minDateD")%>";
	var maxDateM = "<%=request.getAttribute("maxDateM")%>";
	var minDateM = "<%=request.getAttribute("minDateM")%>";
	if(maxDateD!="null"){
		dateD = maxDateD.substring(0,4)+"."+maxDateD.substring(4,6)+"."+maxDateD.substring(6,8);
	}
	if(maxDateM!="null"){
		dateM = maxDateM.substring(0,4)+"."+maxDateM.substring(4,6);
	}
	var dishiData=<%=request.getAttribute("dishijson")%>;
	var xianquData=<%=request.getAttribute("xianqujson")%>;
	var allxinquData=<%=request.getAttribute("allxinqujson")%>;
	
	/* 当前地域id */
	var cur_regionid=regionId;
	var cur_regionname="";
	
	if(lvlId=="1"){
		cur_regionid=dishiData[0].regionId;
		cur_regionname=dishiData[0].regionName;
	}else if(lvlId=="2"){
		cur_regionid=dishiData[0].regionId;
		cur_regionname=dishiData[0].regionName;
	}else if(lvlId=="3"){
		cur_regionid=xianquData[0].regionId;
		cur_regionname=xianquData[0].regionName;
	}
	
	/* 当前兴趣点类型 */
	var cur_pointtype="HOME";
	/* 当前地图指标 */
	var cur_index="<%=request.getAttribute("cur_index")%>";
	if(cur_index == "null"){
		cur_index = $("#iptCurIndexId").val();
	}
	
	var isOrNotScene="N";
	var cur_SceneId="";
	var cur_SceneName="";
	var cur_SceneType="";
	
	var MM_LEVEL="POINT";
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
	
	var myMap;//地图全局变量
	var cur_mapmodel="POINT";
	var PointGraphicsLayer;//兴趣点图层
	var GridGraphicsLayer;// 网格图层
	var CurPointGraphicsLayer;//当前兴趣点图层
	var PointNearBTSOrYYTGraphicsLayer;//兴趣点附近基站或营业厅图层
	var BTSRadius=300;//基站覆盖范围半径
	/* 地图当前可视区域 */
	var cur_mapRingJson={};
	
	/* var pointColor = {"L":[33,207,184],"M":[184,157,231],"H":[247,192,59]}; */
	var pointColor = {"L":[154, 205, 50],"M":[255, 215, 0],"H":[255, 99, 71]};
	
	var datetypeformat = "YYYY.MM.DD";
	if(curdatetype!="null"){
		if(curdatetype=="M"){
			datetypeformat = "YYYY.MM";
			$(".cur_eachdatetype").removeClass("cur_eachdatetype");
			$("#divMonthType").addClass("cur_eachdatetype");
		}else if(curdatetype=="D"){
			datetypeformat = "YYYY.MM.DD";
			$(".cur_eachdatetype").removeClass("cur_eachdatetype");
			$("#divDayType").addClass("cur_eachdatetype");
		}
		dateType=curdatetype;
	}
	/* 日期选择控件配置JSON */
	var jedateoption = {
		dateCell:"#datepiker",
		format:datetypeformat,
		choosefun:function(elem, datas) {
			if(cur_mapmodel=="POINT"){
				MainInit();
				//重新加载兴趣点
				getPointPolygonData();
			}else if(cur_mapmodel=="GRID"){
				//重新加载网格
				getGridPolygonData();
			}
		}
	};
	if(curdate!="null"){
		$("#datepiker").val(curdate);
	}else{
		$("#datepiker").val(dateD);
	}
	/* 日期控件初始化 */
	jeDate(jedateoption);
	inputOptimize($("#iptIndexKey"));
	inputOptimize($("#iptMapSearch"));
	
	/* **********初始化********** */
	$(function(){
		adjust();
		initSceneList();
		$("#divSelPointList").find(".list").niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "3px",
			cursorborder: "0px solid #fff",
			autohidemode:true
		});
		$("#divRSList").niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "3px",
			cursorborder: "0px solid #fff"
		});
		$("#divMSList").niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "3px",
			cursorborder: "0px solid #fff"
		});
		$("#divRegionIndex").niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "3px",
			cursorborder: "0px solid #fff"
			/*autohidemode:false*/
		});
		$(document).keypress(function(e){
			var e = e || window.event;
			if(e.which == 13){
				if($("input:focus").attr("id")=="iptIndexKey"){
					queryForIndex();
				}else if($("input:focus").attr("id")=="iptMapSearch"){
					queryForPoint();
				}
			}
		});
		$("#iconDatepiker").click(function(){
			jeDate(jedateoption);
		});
		if(lvlId != "null"){
			if(lvlId=="1"){
				//加载地市下拉
				initDishiSelect(regionDishiJsonTransform(dishiData,false),(curdishiid=="null"? null:curdishiid));
				//获取地市Key值
				var dishipid=regionNameTransform($("#dishiselect").getSeletedText());
				//加载县区下拉
				initXianquSelect(regionDishiJsonTransform(allxinquData[dishipid],true),(curxianquid=="null"? null:curxianquid));
			}else if(lvlId=="2"){
				//加载地市下拉
				initDishiSelect(regionDishiJsonTransform(dishiData,false),(curdishiid=="null"? null:curdishiid));
				//加载县区下拉
				initXianquSelect(regionDishiJsonTransform(xianquData,true),(curxianquid=="null"? null:curxianquid));
			}else if(lvlId=="3"){
				//加载地市下拉
				initDishiSelect(regionDishiJsonTransform(dishiData,false));
				//加载县区下拉
				initXianquSelect(regionDishiJsonTransform(xianquData,false));
			}
		}
		if(lvlId=="1"){
			if(curdishiid!="all"&&curxianquid!="all"){
				cur_regionid = curxianquid;
				cur_regionname = $("#xianquselect").getSeletedText();
			}else if(curdishiid!="all"&&curxianquid=="all"){
				$("#dishiselect").setSeletedWithValue(curdishiid);
				cur_regionid = curdishiid;
				cur_regionname = $("#dishiselect").getSeletedText();
			}
		}else if(lvlId=="2"){
			if(curdishiid!="all"&&curxianquid!="all"){
				cur_regionid = curxianquid;
				cur_regionname = $("#xianquselect").getSeletedText();
			}else if(curdishiid!="all"&&curxianquid=="all"){
				cur_regionid = curdishiid;
				cur_regionname = $("#dishiselect").getSeletedText();
			}
		}
		MainInit();
		//加载地图
		getDefaultCenter();
	});
	/* **********END********** */
	/* 调整布局 */
	function adjust(){
		var winWidth=$(window).width();
		var indexselect_width=$(".indexselect").width();
		var indexselect_left=(winWidth*0.5)-(indexselect_width*0.5);
		var filter_left=$(".filter").width()+10;
		//判断指标选框左浮动距离与日期地域筛选框位置关系
		if(indexselect_left<filter_left){
			$(".indexselect").css("left",filter_left+10);
		}else{
			$(".indexselect").css("left",indexselect_left);
		}
		var cursceneinfo_width=$("#divCurSceneInfo").width();
		var cursceneinfo_left=(winWidth*0.5)-(cursceneinfo_width*0.5);
		if(cursceneinfo_left<filter_left){
			$("#divCurSceneInfo").css("left",filter_left+10);
		}else{
			$("#divCurSceneInfo").css("left",cursceneinfo_left);
		}
		$(".searchindex").width($(".indexselect").width());
		$("#divCurMapRelIndexInfo").css("left",$(".indexselect").css("left"));
		$("#pop_divPointDetailInfo,#pop_divAddRelIndex,#pop_divGridIndexInfo").draggable({
			cursor:"pointer"
		});
		$(".nav-right-one-color1").css("background-color","rgb("+pointColor.H[0]+","+pointColor.H[1]+","+pointColor.H[2]+")");
		$(".nav-right-one-color2").css("background-color","rgb("+pointColor.M[0]+","+pointColor.M[1]+","+pointColor.M[2]+")");
		$(".nav-right-one-color3").css("background-color","rgb("+pointColor.L[0]+","+pointColor.L[1]+","+pointColor.L[2]+")");
		
	}
	/* 主加载 */
	function MainInit(){
		$("#divRegionName").find("span").html(cur_regionname);
		//查询默认地域指标数据
		initDefaultInfo();
		//查询地图当前指标
		initMapCurIndex(cur_index, dateType, $("#datepiker").val(), cur_regionid);
		//加载兴趣点个数
		initPointType();
		/* 加载场景列表 */
		initSceneList();
	}
	/* 加载兴趣点类型 */
	function initPointType(){
		var dishiid = $("#dishiselect").getSeletedValue();
		var xinaquid = $("#xianquselect").getSeletedValue();
		if("N"==isOrNotScene){
			$.ajax({
				url:"getpointtype.do",
				type:"post",
				dataType:"json",
				data:{"dishiid":dishiid,"xinaquid":xinaquid},
				success:function(data){
					LoadIPRTypeCount(data);
				}
			});	
		}else if("Y"==isOrNotScene){
			$.ajax({
				url:"getscenepointtype.do",
				type:"post",
				dataType:"json",
				data:{"dishiid":dishiid,"xinaquid":xinaquid,"sceneid":cur_SceneId},
				success:function(data){
					LoadIPRTypeCount(data);
				}
			});	
		}
	}
	/* 切换兴趣点类型事件 */
	function ChangeIPRType(e){
		$(".cur_eachtype").find("i").css("color",$(".cur_eachtype").find("i").attr("fcolor"));
		$(e).find("i").css("color","#fff");
		$(".cur_eachtype").removeClass("cur_eachtype");
		$(e).addClass("cur_eachtype");
		var IPRType=$(e).attr("type");
		cur_pointtype = IPRType;
		clearinput();
		/* 触发地图按类型加载兴趣点方法 */
		CurPointGraphicsLayer.clear();
		PointGraphicsLayer.clear();
		getPointPolygonData();
	}
	/* 地图切换 */
	function MapChange(MM_LEVEL){
		var dishiid = $("#dishiselect").getSeletedValue();
		if(MM_LEVEL=="CITY" || MM_LEVEL=="COUNTY"){
			if(lvlId=="3"){
				alert("您的权限不足！");
				return false;
			}else if(lvlId=="2"){
				if(MM_LEVEL=="CITY"){
					alert("您的权限不足！");
					return false;
				}else{
					window.location.href("provincecityrank.do?mm_leve="+MM_LEVEL+"&dishiid="+dishiid);
				}
			}else if(lvlId=="1"){
				window.location.href("provincecityrank.do?mm_leve="+MM_LEVEL);
			}
		}else if(MM_LEVEL=="POINT"){
			window.location.href("interestpointrank.do");
		}
	}
	/* 场景定制切换 */
	function SceneChange(){
		window.open("scenecustomization.do?type=creat");
	}
	/* 切换日期类型 */
	function ChangeDateType(e){
		$(".cur_eachdatetype").removeClass("cur_eachdatetype");
		$(e).addClass("cur_eachdatetype");
		dateType=$(e).attr("datetype");
		if(dateType=="D"){
			jedateoption.format="YYYY.MM.DD";
			$("#datepiker").val(dateD);
		}else if(dateType=="M"){
			jedateoption.format="YYYY.MM";
			$("#datepiker").val(dateM);
		}
		if(cur_mapmodel=="POINT"){
			/* 触发切换日月重新加载地图的方法 */
			MainInit();
			//重新加载兴趣点
			getPointPolygonData();
		}else if(cur_mapmodel=="GRID"){
			//重新加载网格
			getGridPolygonData();
		}
	}
	/* 加载地市 */
	function initDishiSelect(data,firstval){
		//加载地市地域选框
		$("#dishiselect").miapSelect({
			"data":data,
			"eachLiClick":function(i,t,v){
				cur_regionid=v;
				cur_regionname=t;
				$("#divRegionName").find("span").html(cur_regionname);
				if(lvlId==1){
					var cur_xinaqu=regionDishiJsonTransform(allxinquData[regionNameTransform(t)],true);
					$("#xianquselect").reloadListData(cur_xinaqu);
				}else if(lvlId==2){
				}
				//地图定位
				switchRegionMapCenterAt("dishi",t,$("#xianquselect").getSeletedText());
				if(cur_mapmodel=="POINT"){
					//重新加载地图当前指标
					initMapCurIndex(cur_index, dateType, $("#datepiker").val().replace(".","").replace(".",""), cur_regionid);
					//重新加载界面地域指标信息
					queryRegionIndex($("#datepiker").val().replace(".","").replace(".",""),cur_regionid);
					//切换地市时重新加载兴趣点个数
					initPointType();
					//重新加载兴趣点
					getPointPolygonData();
					initDefaultInfo();
				}else if(cur_mapmodel=="GRID"){
					//重新加载网格
					getGridPolygonData();
				}
				
			}
		});
		if(firstval!=undefined&&firstval!=null){
			$("#dishiselect").setSeletedWithValue(firstval);
		}
	}
	/* 加载县区 */
	function initXianquSelect(data,firstval){
		//加载县区地域选框
		$("#xianquselect").miapSelect({
			"listwidth":150,
			"data":data,
			"eachLiClick":function(i,t,v){
				if(v!="All"){
					cur_regionid=v;
					cur_regionname=t;
					//地图定位
					switchRegionMapCenterAt("xianqu",$("#dishiselect").getSeletedText(),t);
				}else{
					cur_regionid=$("#dishiselect").getSeletedValue();
					cur_regionname=$("#dishiselect").getSeletedText();
					//地图定位
					switchRegionMapCenterAt("dishi",$("#dishiselect").getSeletedText(),t);
				}
				if(cur_mapmodel=="POINT"){
					$("#divRegionName").find("span").html(cur_regionname);
					//重新加载地图当前指标
					initMapCurIndex(cur_index, dateType, $("#datepiker").val().replace(".","").replace(".",""), cur_regionid);
					//重新加载界面地域指标信息
					queryRegionIndex($("#datepiker").val().replace(".","").replace(".",""),cur_regionid);
					//切换地市时重新加载兴趣点个数
					initPointType();
					//重新加载兴趣点
					getPointPolygonData();
					initDefaultInfo();
				}else if(cur_mapmodel=="GRID"){
					//重新加载网格
					getGridPolygonData();
				}
			}
		});
		if(firstval!=undefined&&firstval!=null){
			$("#xianquselect").setSeletedWithValue(firstval);
		}
	}
	/* 下载指标数据 */
	function downloadIndex(){
		var date = $("#datepiker").val().replace(".","").replace(".","");
		var dishiid=$("#dishiselect").getSeletedValue();
		var xianquid=$("#xianquselect").getSeletedValue();
		window.open("downloadcurmapindex.do?date="+date+"&datetype="+dateType+"&dishiid="+dishiid+"&xianquid="+xianquid+"&pointtype="+cur_pointtype+"&indexid="+cur_index);
	}
	/* 下载场景指标数据 */
	function downloadSceneIndex(){
		var date = $("#datepiker").val().replace(".","").replace(".","");
		var dishiid=$("#dishiselect").getSeletedValue();
		var xianquid=$("#xianquselect").getSeletedValue();
		window.open("downloadcursceneindex.do?sceneid="+cur_SceneId+"&scenename="+encodeURI(encodeURI(cur_SceneName))+
		"&date="+date+"&datetype="+dateType+
		"&dishiid="+dishiid+"&xianquid="+xianquid+"&pointtype="+cur_pointtype+
		"&indexid="+cur_index);
	}
	/* 切换指标 */
	function switchIndex(){
		$("#divCurMapRelIndexInfo").hide();
		var top=$(".indexselect").offset().top+$(".indexselect").height()+5;
		var left=$(".indexselect").offset().left;
		$("#divSearchIndex").css("top",top).css("left",left);
		if($("#divSearchIndex").is(":hidden")){
			$("#divSearchIndex").show();
		}else{
			CloseSearchDiv();
		}
	}
	/* 场景切换指标 */
	function sceneSwitchIndex(){
		var top=$("#divCurSceneInfo").offset().top+$("#divCurSceneInfo").height()+8;
		var left=$("#divCurSceneInfo").offset().left;
		$("#divSearchIndex").css("top",top).css("left",left);
		if($("#divSearchIndex").is(":hidden")){
			$("#divSearchIndex").show();
		}else{
			CloseSearchDiv();
		}
	}
	/* 搜索指标 */
	function queryForIndex(){
		var indexKey=$("#iptIndexKey").val();
		if(indexKey == $("#iptIndexKey").attr("defaulttxt")){
			indexKey="";
		}
		var top=$("#divSearchIndex").offset().top+$("#divSearchIndex").height()+3;
		var left=$("#divSearchIndex").offset().left;
		$("#divQueryIndexResult").css("top",top).css("left",left);
		//查询并加载查询结果
		queryIndex(indexKey,"divQueryIndexResult");
		//加载滚动条
		$("#divQueryIndexResult").niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "3px",
			cursorborder: "0px solid #fff",
			autohidemode:false
		});
		$("#divQueryIndexResult").show();
	}
	/* 关键字查询指标数据 */
	function queryIndex(key,domid){
		var queryDateType=dateType;
		$("#"+domid).html("");
		$.ajax({
			url:"queryindexbykey.do",
			type:"post",
			dataType:"json",
			data:{"datatype":queryDateType,"keystr":$.trim(key)},
			success:function(data){
				var resultjson = data;
				if(resultjson!=null&&resultjson!=undefined){
					$("#"+domid).html(mosaicIndexResultCode(resultjson));
				}
			}
		});	
	}
	/* 查询地图当前指标的相关影响指标数据 */
	function initMapRelIndexInfo(name,id){
		CloseSearchDiv();
		var dom = $("#divCurMapRelIndexInfo").find(".main");
		var indexid = $("#iptCurIndexId").val();
		$("#divCurMapRelIndexInfo").find(".title").find(".maprelindexname").html(name);
		if($("#divCurMapRelIndexInfo").is(":hidden")){
			$.ajax({
				url:"queryrelindex.do",
				type:"post",
				dataType:"json",
				data:{"indexid":id,"date":$("#datepiker").val().replace(".","").replace(".",""),"datetype":dateType,"regionid":cur_regionid,"mapmodel":cur_mapmodel},
				success:function(data){
					var resultjson = data;
					if(resultjson.length!=0){
						dom.html(mosaicMapRelIndexDiv(resultjson));
						$("#divCurMapRelIndexInfo").show();
					}else{
						alert("该指标暂无相关影响指标！");
					}
				}
			});
			dom.getNiceScroll().resize().show();
		}else{
			$("#divCurMapRelIndexInfo").hide();
			dom.getNiceScroll().hide();
		}
		dom.niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "2px",
			cursorborder: "0px solid #fff",
			//autohidemode:false
		});
	}
	/* 加载选择的指标 */
	function loadThisIndex(e){
		var indexid=$(e).attr("indexid");
		var date=$("#datepiker").val();
		cur_index=indexid;
		//刷新指标池选框
		initMapCurIndex(indexid, dateType, date, cur_regionid);
		//刷新地图
		if(cur_mapmodel=="POINT"){
			getPointPolygonData();
		}else if(cur_mapmodel=="GRID"){
			getGridPolygonData();
		}
		//关闭指标搜索框
		closeIndexSearchDiv($(e).find(".name").text(),$(e).attr("indexid"));
	}
	/* 地图当前指标数据查询 */
	function initMapCurIndex(indexid,datetype,date,regionid){
		$.ajax({
			url:"getmapcurindexdata.do",
			type:"post",
			dataType:"json",
			data:{"indexid":indexid,"datatype":datetype,"date":date.replace(".","").replace(".",""),"regionid":regionid,"mapmodel":cur_mapmodel},
			success:function(data){
				mosaicCurIndexInfoCode(data);
				$("#spnLegendUnit").html("单位："+data.pointunit);
				$("#divKeyValue1").html("["+data.HMIN+",∞)");
				$("#divKeyValue2").html("["+data.MMIN+","+data.MMAX+")");
				$("#divKeyValue3").html("["+data.LMIN+","+data.LMAX+")");
			}
		});	
		
	}
	/* 加载场景列表 */
	function initSceneList(){
		initRSlist();
		initMSlist();
	}
	/* 加载推荐场景 */
	function initRSlist(){
		var scenelisttype="R";
		$.ajax({
			url:"queryscenelist.do",
			type:"post",
			dataType:"json",
			data:{"scenelisttype":scenelisttype,"dateType":dateType},
			success:function(data){
				var resultjson = data;
				$("#divRSList").html("");
				$("#divRSList").html(mosaicSecneList(scenelisttype,resultjson));
			}
		});	
	}
	/* 加载我的场景 */
	function initMSlist(){
		var scenelisttype="M";
		$.ajax({
			url:"queryscenelist.do",
			type:"post",
			dataType:"json",
			data:{"scenelisttype":scenelisttype,"dateType":dateType},
			success:function(data){
				var resultjson = data;
				$("#divMSList").html("");
				$("#divMSList").html(mosaicSecneList(scenelisttype,resultjson));
			}
		});	
	}
	/* 查询地域指标信息 */
	function queryRegionIndex(date,regionId){
		$.ajax({
			url:"queryregionindex.do",
			type:"post",
			dataType:"json",
			data:{"date":date,"datetype":dateType,"regionid":regionId},
			success:function(data){
				var resultjson = data;
				if(resultjson.length!=0){
					mosaicRegionIndexDiv(resultjson);
				}
			}
		});	
	}
	/* 查询地域信息框关联指标数据 */
	function queryRelIndex(domid,indexid,regionid){
		var dom=$("#"+domid).find(".relmain").html("");
		$.ajax({
			url:"queryrelindex.do",
			type:"post",
			dataType:"json",
			data:{"indexid":indexid,"date":$("#datepiker").val().replace(".","").replace(".",""),"datetype":dateType,"regionid":regionid,"mapmodel":cur_mapmodel},
			success:function(data){
				var resultjson = data;
				if(resultjson.length!=0){
					dom.html(mosaicRelIndexDiv(resultjson));
				}
			}
		});	
	}
	//加载左侧兴趣点默认信息
	function initDefaultInfo(){
		$("#iptMapSearch").val("");
		$("#divMapSearchResult").hide();
		if(CurPointGraphicsLayer!=null){
			CurPointGraphicsLayer.clear();
		}
		if(PointNearBTSOrYYTGraphicsLayer!=null){
			PointNearBTSOrYYTGraphicsLayer.clear();
		}
		iptdefaultInit($("#iptMapSearch"));
		var dishiid=$("#dishiselect").getSeletedValue();
		var xianquid=$("#xianquselect").getSeletedValue();
		queryRegionIndex($("#datepiker").val().replace(".","").replace(".",""),cur_regionid);
		$("#divPointTitle").hide();
		$("#divRegionName").show();
		$("#divRegionInfo").show();
	}
	/* 查询兴趣点 */
	function queryForPoint(){
		var indexKey=$("#iptMapSearch").val();
		if(indexKey==""){
			return false;
		}
		if(indexKey == $("#iptMapSearch").attr("defaulttxt")){
			indexKey="";
			return false;
		}
		var top=$("#divMapSearch").offset().top+$("#divMapSearch").height()+3;
		var left=$("#divMapSearch").offset().left;
		$("#divMapSearchResult").css("top",top).css("left",left);
		//查询数据
		queryMapPointByKey(indexKey, "divMapSearchResult");
		//加载滚动条
		$("#divMapSearchResult").niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "3px",
			cursorborder: "0px solid #fff",
			autohidemode:false
		});
		$("#divMapSearchResult").show();
		$("#divRegionInfo").hide();
		
	}
	/* 查询地图兴趣点 */
	function queryMapPointByKey(key,domid){
		$("#"+domid).html("");
		$.ajax({
			url:"querymappointbykey.do",
			type:"post",
			dataType:"json",
			data:{"keystr":key,"pointtype":cur_pointtype,"dishiid":$("#dishiselect").getSeletedValue(),"xianquid":$("#xianquselect").getSeletedValue()},
			success:function(data){
				if(data!=null&&data!=undefined){
					$("#"+domid).html(mosaicPointSearchResultCode(data));
				}
			}
		});	
	}
	/* 加载兴趣点数据 */
	function loadThisPointInfo(id,name,e){
		var pointid;
		if(id==undefined || id==null || id==""){
			pointid = $(e).attr("pointid");	
		}else{
			pointid = id;
		}
		$("#iptMapSearch").css("color","#000");
		$("#iptMapSearch").val(name);
		$("#divPointTitle").find(".otherbntdiv").find("div").each(function(i,e){
			$(e).attr("curstate","hide");
		});
		$("#divPointTitle").find(".otherbntdiv").find("div.left_bnt").find("span").html("显示附近营业厅");
		$("#divPointTitle").find(".otherbntdiv").find("div.right_bnt").find("span").html("显示附近基站");
		var date = $("#datepiker").val().replace(".","").replace(".","");
		//隐藏查询结果
		$("#divMapSearchResult").hide();
		queryPointBaseInfo(date, pointid);
		//显示小区信息
		$("#divRegionName").hide();
		$("#divPointTitle").show();
		$("#divRegionInfo").show();
		queryRegionIndex(date,pointid);
		PointNearBTSOrYYTGraphicsLayer.clear();
		//地图定位
		if(e!=undefined){
			mapCenterAtPoint($(e).attr("pointlong"),$(e).attr("pointlat"));
			mapCenterToThisPoint(name,$(e).attr("pointlong"),$(e).attr("pointlat"));
		}
	}
	/* 查询兴趣点基本信息 */
	function queryPointBaseInfo(date,pointid){
		$.ajax({
			url:"querypointbaseinfo.do",
			type:"post",
			dataType:"json",
			data:{"date":date.replace(".","").replace(".",""),"datetype":dateType,"pointtype":cur_pointtype,"pointid":pointid},
			success:function(data){
				var json = data;
				initpointBaseInfo(json);
			}
		});	
	}
	/*清空搜索框*/
	function clearinput(){
		initDefaultInfo();
		CurPointGraphicsLayer.clear();
		/* iptdefaultInit($("#iptMapSearch")); */
		/* $("#iptMapSearch").focus(); */
	}
	/* 获取兴趣点轮廓数据 */
	function getPointPolygonData(){
		var date = $("#datepiker").val().replace(".","").replace(".","");
		var dishiid=$("#dishiselect").getSeletedValue();
		var xianquid=$("#xianquselect").getSeletedValue();
		PointGraphicsLayer.clear();
		if("N"==isOrNotScene){
			$.ajax({
				url:"querypointpolygondata.do",
				type:"post",
				dataType:"json",
				data:{
					"date":date,
					"datetype":dateType,
					"pointtype":cur_pointtype,
					"dishiid":dishiid,
					"xianquid":xianquid,
					"indexid":cur_index,
					"extentjson":JSON.stringify(cur_mapRingJson)
				},
				success:function(data){
					var json = data;
					for(var i=0;i<json.length;i++){
						var obj = json[i];
						var graphicattr={"pointid":obj.pointId,
										 "pointname":obj.pointName,
										 "pointtype":obj.pointType,
										 "indexid":obj.indexId,
										 "indexname":obj.indexName,
										 "indexunit":obj.indexUnit,
										 "indexvalue":obj.indexValue,
										 "pointlong":obj.pointLong,
										 "pointlat":obj.pointLat};
						var color = [];
						color=pointColor[obj.pointColor];
						if(color == undefined){
							color = [146,222,238];
						}
						drawPointPolygon(obj.pointRings,color,graphicattr);
					}
					GridGraphicsLayer.clear();
				}
			});	
		}else if("Y"==isOrNotScene){
			$.ajax({
				url:"queryscenepointpolygondata.do",
				type:"post",
				dataType:"json",
				async:false,
				data:{
					"date":date,
					"datetype":dateType,
					"pointtype":cur_pointtype,
					"dishiid":dishiid,
					"xianquid":xianquid,
					"indexid":cur_index,
					"extentjson":JSON.stringify(cur_mapRingJson),
					"isScene":isOrNotScene,
					"sceneid":cur_SceneId
				},
				success:function(data){
					var json = data;
					for(var i=0;i<json.length;i++){
						var obj = json[i];
						var graphicattr={"pointid":obj.pointId,
										 "pointname":obj.pointName,
										 "indexid":obj.indexId,
										 "indexname":obj.indexName,
										 "indexunit":obj.indexUnit,
										 "indexvalue":obj.indexValue,
										 "pointlong":obj.pointLong,
										 "pointlat":obj.pointLat};
						var color = [];
						color=pointColor[obj.pointColor];
						if(color == undefined){
							color = [146,222,238];
						}
						drawPointPolygon(obj.pointRings,color,graphicattr);
					}
					GridGraphicsLayer.clear();
				}
			});	
		}
	}
	//获取兴趣点附近营业厅数据
	function togglePointNearYYTData(e){
		var pointid = $(e).parent().attr("pointid");
		var curstate = $(e).attr("curstate");
		if(curstate=="hide"){
			PointNearBTSOrYYTGraphicsLayer.clear();
			
			$(e).find("span").html("隐藏附近营业厅");
			$(e).attr("curstate","show");
			$(e).parent().find(".right_bnt").find("span").html("显示附近基站");
			$(e).parent().find(".right_bnt").attr("curstate","hide");
		}else if(curstate=="show"){
			
			$(e).find("span").html("显示附近营业厅");
			$(e).attr("curstate","hide");
		}
	}
	//获取兴趣点附近基站数据
	function togglePointNearBTSData(e){
		var pointid = $(e).parent().attr("pointid");
		var curstate = $(e).attr("curstate");
		var date = $("#datepiker").val().replace(".","").replace(".","");
		if(curstate=="hide"){
			PointNearBTSOrYYTGraphicsLayer.clear();
			$.ajax({
				url:"querypointnearbtsdata.do",
				type:"post",
				dataType:"json",
				data:{"date":date,"pointid":pointid},
				success:function(data){
					if(data.length!=0){
						for(var i=0;i<data.length;i++){
							var obj = data[i];
							drawBTSPoint(obj.btsLong, obj.btsLat,obj,"BTS");
						}
					}
				}
			});	
			$(e).find("span").html("隐藏附近基站");
			$(e).attr("curstate","show");
			$(e).parent().find(".left_bnt").find("span").html("显示附近营业厅");
			$(e).parent().find(".left_bnt").attr("curstate","hide");
		}else if(curstate=="show"){
			PointNearBTSOrYYTGraphicsLayer.clear();
			$(e).find("span").html("显示附近基站");
			$(e).attr("curstate","hide");
		}
	}
	/* 获取网格数据 */
	function getGridPolygonData(){
		var date = $("#datepiker").val().replace(".","").replace(".","");
		var dishiid=$("#dishiselect").getSeletedValue();
		var xianquid=$("#xianquselect").getSeletedValue();
		GridGraphicsLayer.clear();
		$.ajax({
			url:"querygridpolygondata.do",
			type:"post",
			dataType:"json",
			data:{
				"date":date,
				"datetype":dateType,
				"dishiid":dishiid,
				"xianquid":xianquid,
				"indexid":cur_index,
				"extentjson":JSON.stringify(cur_mapRingJson)
			},
			success:function(data){
				var json = data;
				for(var i=0;i<json.length;i++){
					var obj = json[i];
					var graphicattr={"gridid":obj.gridId,
									 "indexid":obj.indexId,
									 "indexname":obj.indexName,
									 "indexunit":obj.indexUnit,
									 "indexvalue":obj.indexValue};
					var color = [];
					color=pointColor[obj.gridColor];
					if(color == undefined){
						color = [146,222,238];
					}
					drawMapGridPolygon(obj.gridRings,color,graphicattr);
				}
				PointGraphicsLayer.clear();
			}
		});
	}
	/* 获取网格指标信息数据 */
	function queryGridIndexInfoData(gridid){
		var date = $("#datepiker").val().replace(".","").replace(".","");
		$.ajax({
			url:"querygridindex.do",
			type:"post",
			dataType:"json",
			data:{"date":date,"datetype":dateType,"gridid":gridid},
			success:function(data){
				var resultjson = data;
				if(resultjson.length!=0){
					mosaicGirdIndexDiv(resultjson);
				}
			}
		});
	}
	/* ***********************地图Function********************************* */
	//加载地图
	function InitMap(defaultpoint){
		myMap = new esri.Map("divmap",{
			"center":defaultpoint,
			"zoom":11,
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
		
		/* 兴趣点图层 */
		PointGraphicsLayer = new esri.layers.GraphicsLayer();
		myMap.addLayer(PointGraphicsLayer);
		myMap.reorderLayer(PointGraphicsLayer,15);
		/* 网格图层 */
		GridGraphicsLayer = new esri.layers.GraphicsLayer();
		myMap.addLayer(GridGraphicsLayer);
		myMap.reorderLayer(GridGraphicsLayer,15);
		/* 当前兴趣点标识图层 */
		CurPointGraphicsLayer = new esri.layers.GraphicsLayer();
		myMap.addLayer(CurPointGraphicsLayer);
		myMap.reorderLayer(CurPointGraphicsLayer,2);
		/* 兴趣点附近营业厅或基站标识图层 */
		PointNearBTSOrYYTGraphicsLayer = new esri.layers.GraphicsLayer();
		myMap.addLayer(PointNearBTSOrYYTGraphicsLayer);
		myMap.reorderLayer(PointNearBTSOrYYTGraphicsLayer,3);
		
		//绑定事件
		dojo.connect(myMap, "onExtentChange", InitMapPointOrGrid);
		dojo.connect(myMap, "onZoomEnd", function(){
			var lvl = myMap.getLevel();
			if(lvl>=11){
				CurPointGraphicsLayer.show();
				PointNearBTSOrYYTGraphicsLayer.show();
			}else{
				CurPointGraphicsLayer.hide();
				PointNearBTSOrYYTGraphicsLayer.hide();
			}
		});
		/* dojo.connect(myMap, "onClick", function(evt){
			console.log("x:"+evt.mapPoint.x+",y:"+evt.mapPoint.y);
		}); */
		/* ------------------ */
		dojo.connect(PointGraphicsLayer,"onMouseOver",showPointInfo);
		dojo.connect(PointGraphicsLayer,"onMouseOut",hideInfo);
		dojo.connect(PointGraphicsLayer,"onClick",mapPointClickInitInfoData);
		/* ------------------ */
		dojo.connect(GridGraphicsLayer,"onMouseOver",showGridInfo);
		dojo.connect(GridGraphicsLayer,"onMouseOut",hideInfo);
		dojo.connect(GridGraphicsLayer,"onClick",mapGridClickInitInfoData);
		/* ------------------ */
		/* dojo.connect(CurPointGraphicsLayer,"onClick",function(evt){
			var graphicAttr = evt.graphic.attributes;
			var point = esri.geometry.Point(new esri.geometry.Point({"x": graphicAttr.pointlong,"y": graphicAttr.pointlat,"spatialReference": {"wkid": 4326}}));
			myMap.setZoom(11);
			myMap.centerAt(point);
		}); */
		/* ---------------------- */
		dojo.connect(PointNearBTSOrYYTGraphicsLayer,"onMouseOver",showBTSOrYYTInfo);
		dojo.connect(PointNearBTSOrYYTGraphicsLayer,"onMouseOut",hideInfo);
	}
	/* 加载地图兴趣点 */
	function InitMapPointOrGrid(){
		var lvl = myMap.getLevel();
		var extent = myMap.extent;
		var mapRingJson = {"xMax":extent.xmax,"xMin":extent.xmin,"yMax":extent.ymax,"yMin":extent.ymin};
		cur_mapRingJson = mapRingJson;
		if(cur_mapmodel=="POINT"){
			PointGraphicsLayer.clear();
			if(lvl>=11){
				getPointPolygonData();
			}
		}else if(cur_mapmodel=="GRID"){
			/* GridGraphicsLayer.clear();
			getGridPolygonData(); */
		}
	}
	/* 绘制兴趣点轮廓及染色 */
	function drawPointPolygon(ring,color,attrdata){
		var thiscolor = [];
		thiscolor.push(color[0],color[1],color[2],0.8);
		var pointsymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, 
			new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([255,255,255,0.8]), 1), new dojo.Color(thiscolor));
		var str="{\"rings\":[[";
		for(var j=0;j<ring.length;j++){
			str+="["+ring[j][0]+", "+ring[j][1]+"]";
			if(j!=ring.length-1){
  		 			str+=",";
  		 		}
		}
 		str+="]],\"spatialreference\":{\"wkid\":4326}}";
 		var polygonjson=eval('('+str+')');
		var mygraphic=new esri.Graphic(new esri.geometry.Polygon(polygonjson),pointsymbol,attrdata);
		PointGraphicsLayer.add(mygraphic);
	}
	/* 绘制网格轮廓及染色 */
	function drawMapGridPolygon(ring,color,attrdata){
		var thiscolor = [];
		thiscolor.push(color[0],color[1],color[2],0.6);
		var gridsymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, 
			new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([255,255,255,0.8]), 1), new dojo.Color(thiscolor));
		var str="{\"rings\":[[";
		for(var j=0;j<ring.length;j++){
			str+="["+ring[j][0]+", "+ring[j][1]+"]";
			if(j!=ring.length-1){
  		 			str+=",";
  		 		}
		}
 		str+="]],\"spatialreference\":{\"wkid\":4326}}";
 		var polygonjson=eval('('+str+')');
		var mygraphic=new esri.Graphic(new esri.geometry.Polygon(polygonjson),gridsymbol,attrdata);
		GridGraphicsLayer.add(mygraphic);
	}
	/* 显示兴趣点信息框 */
	function showPointInfo(evt){
		var infoWindow = myMap.infoWindow;
		var graphicAttr = evt.graphic.attributes;
		var pointid = graphicAttr.pointid;
		var pointtype = graphicAttr.pointtype;
		var pointname = graphicAttr.pointname;
		var indexname = graphicAttr.indexname;
		var indexvalue = graphicAttr.indexvalue;
		var indexunit = graphicAttr.indexunit;
		var htmlcode = "<div class='pointinfowindow'>"+
						"<div class='eachindexinfo'>"+
						"<span class='text'>"+indexname+"：</span>"+
						"<span class='value'>"+indexvalue+indexunit+"</span>"+
						"</div>"+
						"</div>";
		infoWindow.setContent(htmlcode);
		infoWindow.setTitle(pointname);			
		infoWindow.resize(220,100);
		infoWindow.show(evt.screenPoint, myMap.getInfoWindowAnchor(evt.screenPoint));
	}
	/* 显示网格信息框 */
	function showGridInfo(evt){
		var infoWindow = myMap.infoWindow;
		var graphicAttr = evt.graphic.attributes;
		var gridid = graphicAttr.gridid;
		var indexname = graphicAttr.indexname;
		var indexvalue = graphicAttr.indexvalue;
		var indexunit = graphicAttr.indexunit;
		var htmlcode = "<div class='pointinfowindow'><div class='eachindexinfo'><span class='text'>"+indexname+"：</span><span class='value'>"+indexvalue+indexunit+"</span></div></div>";
		infoWindow.setContent(htmlcode);
		infoWindow.setTitle("网格ID："+gridid);	
		infoWindow.resize(220,100);
		infoWindow.show(evt.screenPoint, myMap.getInfoWindowAnchor(evt.screenPoint));
	}
	/* 地图定位置选中的兴趣点 */
	function mapCenterToThisPoint(name,long,lat){
		CurPointGraphicsLayer.clear();
		var pointSymbolRed = new esri.symbol.PictureMarkerSymbol("image/location.png",40,40);
		var point = esri.geometry.Point(new esri.geometry.Point({"x": long,"y": lat,"spatialReference": {"wkid": 4326}}));
		var attrdata = {"pointname":name,"pointlong":long,"pointlat":lat};
		var cur_graphic = new esri.Graphic(point,pointSymbolRed,attrdata);
		CurPointGraphicsLayer.add(cur_graphic);
	}
	/* 地图定位 */
	function mapCenterAtPoint(long,lat){
		var centerPoint = new esri.geometry.Point({"x": long,"y": lat,"spatialReference": {"wkid": 4326}});
		myMap.setZoom(11);
		myMap.centerAt(centerPoint);
	}
	/* 地图兴趣点点击加载兴趣点数据 */
	function mapPointClickInitInfoData(evt){
		var graphicAttr = evt.graphic.attributes;
		var pointid = graphicAttr.pointid;
		var pointname = graphicAttr.pointname;
		var pointlong = graphicAttr.pointlong;
		var pointlat = graphicAttr.pointlat;
		loadThisPointInfo(pointid,pointname);
		mapCenterToThisPoint(pointname,pointlong,pointlat);
		PointNearBTSOrYYTGraphicsLayer.clear();
	}
	/* 地图网格点击弹出框事件 */
	function mapGridClickInitInfoData(evt){
		var graphicAttr = evt.graphic.attributes;
		var gridid = graphicAttr.gridid;
		showGridInfoPopDiv(gridid);
	}
	//地图定位
	function Maptopoint(result){
		var features = result.features;
		if(features.length>0){
			var gra=features[0];
			if(cur_mapmodel=="POINT"){
				myMap.setZoom(11);
			}
			myMap.centerAt(new esri.geometry.Point(gra.geometry.x, gra.geometry.y));
		}
		else{
			alert("定位失败!");
		}
	}
	/* 切换地市后地图定位 */
	function switchRegionMapCenterAt(lvl,dishiname,xianquname){
		var queryTask;
		var query = new esri.tasks.Query();
		query.returnGeometry = true;
		if(lvl=="dishi"){
			queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/13");
			query.where = "data='" + dishiname+"市"+ "'";
			queryTask.execute(query, Maptopoint);
		}else if(lvl=="xianqu"){
			queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/10");
			query.where = "CITY='"+dishiname+"市"+"' and data like '%"+xianquname+"%'";
			queryTask.execute(query, function(result){
				var features = result.features;
				if(features.length>0){
					var gra=features[0];
					if(cur_mapmodel=="POINT"){
						myMap.setZoom(11);
					}
					myMap.centerAt(new esri.geometry.Point(gra.geometry.x, gra.geometry.y));
				}
				else{
					var point = getUnKonwnCenterPoint(dishiname,xianquname);
					if(cur_mapmodel=="POINT"){
						myMap.setZoom(11);
					}
					myMap.centerAt(point);
					/* queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/13");
					query.where = "data='" + dishiname+"市"+ "'";
					queryTask.execute(query, function(result){
						var features = result.features;
						if(features.length>0){
							var gra=features[0];
							var point = [gra.geometry.x, gra.geometry.y];
							if(cur_mapmodel=="POINT"){
								myMap.setZoom(11);
							}
							myMap.centerAt(point);
						}
					}); */
				}
			});
		}
	}
	/* 获取默认定位 */
	function getDefaultCenter(){
		var queryTask;
		var query = new esri.tasks.Query();
		query.returnGeometry = true;
		var defaultpoint = [];
		var dishiname = $("#dishiselect").getSeletedText();
		var xianquname = $("#xianquselect").getSeletedText();
		if(lvlId=="3"){
			queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/10");
			query.where = "CITY='"+dishiname+"市"+"' and data like '%"+xianquname+"%'";
			queryTask.execute(query, function(result,defaultpoint){
				var features = result.features;
				if(features.length>0){
					var gra=features[0];
					defaultpoint = [gra.geometry.x, gra.geometry.y];
					InitMap(defaultpoint);
				}
				else{
					var point = getUnKonwnCenterPoint(dishiname,xianquname);
					defaultpoint = [point.x, point.y];
					InitMap(defaultpoint);
					/* queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/13");
					query.where = "data='" + dishiname+"市"+ "'";
					queryTask.execute(query, function(result,defaultpoint){
						var features = result.features;
						if(features.length>0){
							var gra=features[0];
							defaultpoint = [gra.geometry.x, gra.geometry.y];
							InitMap(defaultpoint);
						}
					}); */
				}
			});
		}else if(lvlId=="2"){
			if(curxianquid!="all"){
				queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/10");
				query.where = "CITY='"+dishiname+"市"+"' and data like '%"+xianquname+"%'";
				queryTask.execute(query, function(result,defaultpoint){
					var features = result.features;
					if(features.length>0){
						var gra=features[0];
						defaultpoint = [gra.geometry.x, gra.geometry.y];
						InitMap(defaultpoint);
					}
					else{
						var point = getUnKonwnCenterPoint(dishiname,xianquname);
						defaultpoint = [point.x, point.y];
						InitMap(defaultpoint);
						/* queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/13");
						query.where = "data='" + dishiname+"市"+ "'";
						queryTask.execute(query, function(result,defaultpoint){
							var features = result.features;
							if(features.length>0){
								var gra=features[0];
								defaultpoint = [gra.geometry.x, gra.geometry.y];
								InitMap(defaultpoint);
							}
						}); */
					}
				});
			}else{
				queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/13");
				query.where = "data='" + dishiname+"市"+ "'";
				queryTask.execute(query, function(result,defaultpoint){
					var features = result.features;
					if(features.length>0){
						var gra=features[0];
						defaultpoint = [gra.geometry.x, gra.geometry.y];
						InitMap(defaultpoint);
					}
				});
			}
		}else if(lvlId=="1"){
			if(curxianquid!="all"){
				queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/10");
				query.where = "CITY='"+dishiname+"市"+"' and data like '%"+xianquname+"%'";
				queryTask.execute(query, function(result,defaultpoint){
					var features = result.features;
					if(features.length>0){
						var gra=features[0];
						defaultpoint = [gra.geometry.x, gra.geometry.y];
						InitMap(defaultpoint);
					}
					else{
						var point = getUnKonwnCenterPoint(dishiname,xianquname);
						defaultpoint = [point.x, point.y];
						InitMap(defaultpoint);
						/* queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/13");
						query.where = "data='" + dishiname+"市"+ "'";
						queryTask.execute(query, function(result,defaultpoint){
							var features = result.features;
							if(features.length>0){
								var gra=features[0];
								defaultpoint = [gra.geometry.x, gra.geometry.y];
								InitMap(defaultpoint);
							}
						}); */
					}
				});
			}else{
				queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/13");
				query.where = "data='" + dishiname+"市"+ "'";
				queryTask.execute(query, function(result,defaultpoint){
					var features = result.features;
					if(features.length>0){
						var gra=features[0];
						defaultpoint = [gra.geometry.x, gra.geometry.y];
						InitMap(defaultpoint);
					}
				});
			}
		}else{
			defaultpoint=[114.50874900026633, 38.04169000039337];
			InitMap(defaultpoint);
		}
		return defaultpoint;
	}
	/* 绘制基站覆盖范围 */
	function drawBTSCircle(long,lat,attr,type){
		var btssymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, 
			new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([0,0,0,0.4]), 1), new dojo.Color([188,233,239,0.6]));
		var BTSCircleGraphic=new esri.Graphic(new esri.geometry.Circle([long,lat],{radius:BTSRadius}),btssymbol);
		BTSCircleGraphic.type=type;
		PointNearBTSOrYYTGraphicsLayer.add(BTSCircleGraphic);
	}
	/* 绘制基站点 */
	function drawBTSPoint(long,lat,attr,type){
		var btsSymbolRed = new esri.symbol.PictureMarkerSymbol("image/bts.png",32,32);
		var point = esri.geometry.Point(new esri.geometry.Point({"x": long,"y": lat,"spatialReference": {"wkid": 4326}}));
		var btsgraphic = new esri.Graphic(point,btsSymbolRed,attr);
		btsgraphic.type=type;
		PointNearBTSOrYYTGraphicsLayer.add(btsgraphic);
	}
	/* 显示营业厅悬浮框 */
	function showBTSOrYYTInfo(evt){
		var infoWindow = myMap.infoWindow;
		var graphictype = evt.graphic.type;
		var graphicAttr = evt.graphic.attributes;
		var htmlcode = "";
		if(graphictype=="BTS"){
			var btsName = graphicAttr.btsName;
			var btsType = graphicAttr.btsType;
			htmlcode = "<div class='pointinfowindow'>"+
						   "<div class='btsnamediv'>基站名称: "+btsName+"</div>"+
						   "<div class='btstypediv'>基站类型: "+btsType+"</div>"+
						   "</div>";
			infoWindow.setContent(htmlcode);
			infoWindow.setTitle("基站基本信息");			
		}else if(graphictype=="YYT"){
			
		}
		infoWindow.resize(220,100);
		infoWindow.show(evt.screenPoint, myMap.getInfoWindowAnchor(evt.screenPoint));
	}
	/* 隐藏悬浮框 */
	function hideInfo(){
		var infoWindow = myMap.infoWindow;
		infoWindow.hide();
	}
	/* 切换地图展示模式 */
	function switchMapModel(e){
		cur_mapmodel = $(e).attr("mapmodel");
		$(".curmodel").removeClass("curmodel");
		$(e).addClass("curmodel");
		PointGraphicsLayer.clear();
		PointNearBTSOrYYTGraphicsLayer.clear();
		GridGraphicsLayer.clear();
		initMapCurIndex(cur_index, dateType, $("#datepiker").val(), cur_regionid);
		if(cur_mapmodel=="GRID"){
			/* 隐藏其他功能 */
			$(".scenelist").hide();
			$("#divSceneList").hide();
			$("#iSceneListBnt").html("&#xe666;");
			$("#divIprtype").hide();
			$("#divMapSearch").hide();
			$("#divRegionInfo").hide();
			$("#divMapSearchResult").hide();
			$("#divSelPointList").hide();
			$("#divSelContratsBnt").hide();
			$("#divSelContratsBnt").find("i").html("&#xe601;");
			/* --------- */
			getGridPolygonData();
		}else if(cur_mapmodel=="POINT"){
			/* 显示其他功能 */
			$(".scenelist").show();
			$("#divIprtype").show();
			$("#divMapSearch").show();
			$("#divRegionInfo").show();
			$("#divSelContratsBnt").show();
			/* --------- */
			$("#pop_divGridIndexInfo").hide();
			getPointPolygonData();
		}
	}
	/* ***********************地图Function********************************* */
</script>
</html>
