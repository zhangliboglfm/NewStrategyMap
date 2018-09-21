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

<title>2017河北移动新版战略地图--省市地图</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- CSS引入 -->
<link rel="stylesheet" href="css/provincecitytrank/provincecitytrank.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/jedate/skin/jedate-common.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/jedate/skin/jedate.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/selectlist/css/selectlist.default.css" type="text/css"></link>
<!-- JS引入 -->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="plugin/jedate/jedate.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="plugin/selectlist/jquery.nicescroll.js"></script>
<script type="text/javascript" src="plugin/selectlist/miapsoft.autohide.js"></script>
<script type="text/javascript" src="plugin/selectlist/miapsoft.selectlist.core.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript" src="js/provincecitytrank/provincecitytrank.js"></script>
<script type="text/javascript" src="js/EchartsExampleV2.js"></script>
<script type="text/javascript" src="js/miapsoft.input.core.js"></script>


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
.font1{
	font-size:18px;
}
.font2{
	font-size:13px;
}
.font3{
	color:#AFAFAF;
}
.font4{
	font-size:20px;
}
</style>
</head>

<body>
	<div class="filter">
		<div class="datetype">
			<div id="divDayType" class="eachdatetype cur_eachdatetype" datetype="D" onclick="ChangeDateType(this)">日</div>
			<div id="divMonthType" class="eachdatetype" datetype="M" onclick="ChangeDateType(this)">月</div>
		</div>
		<div class="div_datepiker">
			<input id="datepiker" type="text" readonly="readonly"/>
			<i id="iconDatepiker" class="iconfont">&#xe610;</i>
		</div>
		<div id="dishiselect" class="div_select"></div>
		<div id="xianquselect" class="div_select"></div>
	</div>
	
		<div class="left-second">
			<div id="divRegionName" class="left-second-first"><i class="iconfont font1">&#xe634;</i>&nbsp;河北省</div>
			<div id="divNiceScroll">
			<!-- 左侧指标加载 -->
			<div id="divRegionIndex" class="indexdiv"></div>
			<!-- 各地域兴趣点数量 -->
			<div id="divInterstingPoint">
			<div class="left-second-second">
				<div class="left-second-second-first">
					<div id="HOME"><i class="iconfont" style="color:#DDB756">&#xe60d;</i>&nbsp;&nbsp;住宅:&nbsp;&nbsp;<span>0</span>个</div>
				</div>
				<div class="left-second-second-first">
					<div id="SHOP"><i class="iconfont" style="color:#17FFEB">&#xe65e;</i>&nbsp;&nbsp;商圈:&nbsp;&nbsp;<span>0</span>个</div>
				</div>
			</div>
			<div class="left-second-second">
				<div class="left-second-second-first">
					<div id="HEALTH_CARE"><i class="iconfont" style="color:red">&#xe63d;</i>&nbsp;&nbsp;医院:&nbsp;&nbsp;<span>0</span>个</div>
				</div>
				<div class="left-second-second-first">
					<div id="GOVERNMENT" ><i class="iconfont" style="color:#5CC451">&#xe691;</i>&nbsp;&nbsp;机关:&nbsp;&nbsp;<span>0</span>个</div>
				</div>
			</div>
			<div class="left-second-second">
				<div class="left-second-second-first">
					<div id="TRADING_ESTATE" ><i class="iconfont" style="color:#C5529D">&#xe659;</i>&nbsp;&nbsp;企业:&nbsp;&nbsp;<span>0</span>个</div>
				</div>
				<div class="left-second-second-first">
					<div id="ORTHER"><i class="iconfont" style="color:#2D81B0">&#xe642;</i>&nbsp;&nbsp;其他:&nbsp;&nbsp;<span>0</span>个</div>
				</div>
			</div>
			</div>
			</div>
		</div>
		
<!-- -----------------------------------------地图当前指标，设置搜索指标 -------------------------------------------------------->
	<div id="divMapCurIndex" class="indexselect"  MM_ID="LTE001">
	 <div class="title">
			<span>地图当前指标:</span>
			<div class="icon" onclick="downloadIndex()"><i class="iconfont FONT-1">&#xe624;</i>下载</div>
			<div class="icon leftboder" onclick="switchIndex()"><i class="iconfont">&#xe628;</i>切换</div>
		</div>
		<div class="main"></div>
		<input id="iptCurIndexId" type="hidden" value=""/> 
	</div>
	<div id="divSearchIndex" class="searchindex">
		<div class="title">搜索指标<i class="iconfont" title="关闭" onclick="CloseSearchDiv()">&#xe63f;</i></div>
		<div class="main">
			<input id="iptIndexKey" class="indexipt" type="text" title="指标搜索" defaulttxt="请输入 指标关键字/指标分类"/>
			<i class="iconfont" onclick="queryForIndex()">&#xe617;</i>
		</div>
	</div>
	<div id="divQueryIndexResult" class="searchindexresult"></div>
	<div id="divCurMapRelIndexInfo" class="curmaprelindexinfo">
		<div class="title"><span class="maprelindexname"></span><span>--相关影响指标</span><i class="iconfont" title="关闭" onclick="closeMapCurRelIndexInfo()">&#xe63f;</i></div>
		<div class="main"></div>
	</div>
	
	<div id="pop_divAddRelIndex" class="addrelindex">
		<div class="title"><span></span><i class="iconfont" title="关闭" onclick="closeAddRelIndexPop()">&#xe63f;</i></div>
		<div class="main">
			<iframe id="ifrAddRelIndex" class="detailifr" frameborder="no"></iframe>
		</div>
	</div>
	<!-- -----------------------------------------导航div拼接 ---------------------------------------------------------->
	<div class="navigation">
		<div class="nav-first">图例<span id="spnLegendUnit" class="legendunit"></span></div>
		<div class="nav-left">
			<div class="nav-left-first">
				<div>
					<div id="CITY" class="province-city-point" onclick="navigatorChange(this)">全省</div>
					<div id="COUNTY" class="province-city-point" onclick="navigatorChange(this)">全市</div>
					<div id="POINT" class="province-city-point" onclick="navigatorChange(this)">兴趣点</div>
				</div>
			</div>
			<div class="nav-left-second">
				<div class="nav-left-second-one">&nbsp;<i  class="iconfont font3">&#xe644;</i></div>
				<div class="nav-left-second-one">&nbsp;<i  class="iconfont font3">&#xe644;</i></div>
				<div class="nav-left-second-one">&nbsp;<i  class="iconfont font3">&#xe644;</i></div>
			</div>
			<div class="nav-left-third">
				<i class="iconfont bigger" onclick="bigger(this)">&#xe633;</i><br>
				<i class="iconfont ">&#xe688;</i><br>
				<i class="iconfont ">&#xe688;</i><br>
				<i class="iconfont ">&#xe688;</i><br>
				<i class="iconfont ">&#xe688;</i><br>
				<i class="iconfont ">&#xe688;</i><br>
				<i class="iconfont ">&#xe688;</i><br>
				&nbsp;<i class="iconfont font2 smaller" onclick="smaller(this)">&#xe631;</i>
			</div>
		</div>
		<div class="nav-right">
			<div class="nav-right-one"><div class="nav-right-one-color1" ></div><div id="divKeyValue1" class="nav-right-two-second"></div></div>
			<div class="nav-right-one"><div class="nav-right-one-color2"></div><div id="divKeyValue2" class="nav-right-two-second"></div></div>
			<div class="nav-right-one"><div class="nav-right-one-color3"></div><div id="divKeyValue3" class="nav-right-two-second"></div></div>
		</div>
	</div>
	<div class="back"></div>
	<div id="divmap" class="map"></div>
</body>
<script type="text/javascript">
	var dishiData=<%=request.getAttribute("dishijson")%>;
	var xianquData=<%=request.getAttribute("xianqujson")%>;
	var allxianquData=<%=request.getAttribute("allxianqujson")%>;
	var post_mm_leve="<%=request.getAttribute("mm_leve")%>";
	var date="<%=request.getAttribute("date")%>"
	var dateType="<%=request.getAttribute("dateType")%>"
	
	var indexId="<%=request.getAttribute("indexId")%>"
	/*dishiId*/
	var dishiId="<%=request.getAttribute("dishiId")%>"
	var xianquId="<%=request.getAttribute("xianquId")%>"
	
	var maxDateD="<%=request.getAttribute("maxDateD")%>"
	var maxDateM="<%=request.getAttribute("maxDateM")%>"
	
	var lvlId="<%=session.getAttribute("lvlId")%>";
	var regionId="<%=session.getAttribute("regionId")%>";
	
	/*设置地域类型  河北省地图显示为 CITY 显示某一个城市如石家庄为 COUNTY 兴趣点显示为 POINT*/
	var MM_LEVEL = (post_mm_leve=="null"?"CITY":post_mm_leve);
	
	
	/*设置接受传递过来的日期或者查询表格中的最大日期*/
	var dateD="";
	var dateM="";
	if(date!="null"&&dateType!="null"){
		if(dateType=="M"){
			dateM=date.substring(0,4)+"."+date.substring(4,6)
			dateD=date.substring(0,4)+"."+date.substring(4,6)+".01";
		}else{
			dateD=date.substring(0,4)+"."+date.substring(4,6)+"."+date.substring(6,8);
			dateM=date.substring(0,4)+"."+date.substring(4,6);
		}
	}else {
		dateType="D"
		dateD=maxDateD.substring(0,4)+"."+maxDateD.substring(4,6)+"."+maxDateD.substring(6,8);
		dateM=maxDateM.substring(0,4)+"."+maxDateM.substring(4,6);
	}
	
	/*设置查询指标*/
	if(indexId!="null"){
		$("#divMapCurIndex").attr("MM_ID",indexId);
	}
	
	/*设置导航显示的颜色*/
	$("#"+MM_LEVEL).addClass("setColor");
	
	/* 日期选择控件配置JSON */
	var jedateoption = {
		dateCell:"#datepiker",
		format:"YYYY.MM.DD",
		choosefun:function(elem, datas) {
			//加载左侧指标
			initLeftIndex();
			//拼接兴趣点数量类型
			initInterstingPointNumber();
			//加载默认指标
			loadThisIndex(null,true);
		}
	};
	
	/* 日期控件初始化 */
	if(dateType=="D"){
		$("#datepiker").val(dateD);
	}else{
		var obj=$(".cur_eachdatetype");
		obj.removeClass("cur_eachdatetype");
		obj.siblings().addClass("cur_eachdatetype");
		$("#datepiker").val(dateM);
	}
	jeDate(jedateoption);
	inputOptimize($("#iptIndexKey"));//设置input输入框的defaulttxt属性值。
	/*-------------------------------------延迟加载-----------------------------------------------------------*/
	$(function(){
		/*搜索框布局*/
		adjust();
		$("#iconDatepiker").click(function(){
			jeDate(jedateoption);
		});
		/*给该输入框加入回车事件*/
		$(document).keypress(function(e){
			if(e.which == 13){
				if($("input:focus").attr("id")=="iptIndexKey"){
					queryForIndex();
				}
			}
		});
		/*加载省市 县区 下拉框*/
		initProvinceCityOption();
		if(dishiId!="null"){
			if(dishiId!="null"){
				initdishiMap(dishiId,"dblclick");
			}
			if(xianquId!="null"){
				initxianquIndex(xianquId);
			}
		}else{
			//加载左侧指标
			initLeftIndex();
			//拼接兴趣点数量类型
			initInterstingPointNumber();
			//加载默认指标
			loadThisIndex(null,true);
		}
		
		/*滚动条切换*/
		/* $("#divNiceScroll").niceScroll({
			cursorcolor : "#00AFFF",//滚动条显示的颜色
			cursorborderradius: "0px",//滚动条边角圆弧
			cursorwidth: "0px",//滚动条宽度
			cursorborder: "0px solid #fff",//滚动条边线
			autohidemode:false//是否自动隐藏
		}); */	
	});
	/*---------------------------辅助方法---------------------------------------*/
	/*加载 省市 县区 下拉框*/
	function initProvinceCityOption(){
		if(lvlId != "null"){
			if(lvlId=="1"){
				//加载地市下拉
				initDishiSelect(regionDishiJsonTransform(dishiData,false,true));
				//获取地市Key值
				var dishipid=regionNameTransform($("#dishiselect").getSeletedText());
				//加载县区下拉
				initXianquSelect(regionDishiJsonTransform(allxianquData[dishipid],true,false));
				
			}else if(lvlId=="2"){
				//加载地市下拉
				initDishiSelect(regionDishiJsonTransform(dishiData,false,false));
				//获取地市Key值
				var dishipid=regionNameTransform($("#dishiselect").getSeletedText());
				//加载县区下拉
				initXianquSelect(regionDishiJsonTransform(allxianquData[dishipid],true,false));
				
				$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+$("#dishiselect").getSeletedText());
			}/* else if(lvlId=="3"){
				//加载地市下拉
				initDishiSelect(regionDishiJsonTransform(dishiData,false,false));
				//获取地市Key值
				var dishipid=regionNameTransform($("#dishiselect").getSeletedText());
				//加载县区下拉
				initXianquSelect(regionDishiJsonTransform(allxianquData[dishipid],false,false));
			} */
		}
	}
	/* 加载地市 */
	function initDishiSelect(data,firstval){
		//加载地市地域选框
		$("#dishiselect").miapSelect({
			"data":data,
			"eachLiClick":function(i,t,v){
				var b=true;
				if(lvlId=="3"){
					b=false;
				}
				var cur_xinaqu=regionDishiJsonTransform(allxianquData[regionNameTransform(t)],b,false);
				$("#xianquselect").reloadListData(cur_xinaqu);
				if(t=="全省"){
					t="河北省";
					MM_LEVEL="CITY";
				}else{
					MM_LEVEL="COUNTY";
				}
				$("#"+MM_LEVEL).addClass("setColor");
				$("#"+MM_LEVEL).siblings().removeClass("setColor");
				$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+t);
				
				//加载左侧指标
				initLeftIndex();
				//拼接兴趣点数量类型
				initInterstingPointNumber();
				//加载默认指标
				loadThisIndex(null,true);
			}
		});
		if(firstval!=undefined&&firstval!=null){
		
			$("#dishiselect").setSeletedWithIndex(firstval);
		}
	};
	
	/* 加载县区 */
	function initXianquSelect(data,firstval){
		//加载县区地域选框
		$("#xianquselect").miapSelect({
			"listwidth":100,
			"data":data,
			"eachLiClick":function(i,t,v){
			if(t=="全部"){
				t=$("#dishiselect").getSeletedText();
				}else{
				MM_LEVEL="COUNTY";
				};
				$("#"+MM_LEVEL).addClass("setColor");
				$("#"+MM_LEVEL).siblings().removeClass("setColor");
				$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+t);
				//加载左侧指标
				initLeftIndex();
				//拼接兴趣点数量类型
				initInterstingPointNumber();
				//加载默认指标
				loadThisIndex(null,true);
			}
			
		});
		if(firstval!=undefined&&firstval!=null){
			$("#xianquselect").setSeletedWithIndex(firstval);
		}
	};
	
	
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
		//加载左侧指标
		initLeftIndex();
		//拼接兴趣点数量类型
		initInterstingPointNumber();
		//加载默认指标
		loadThisIndex(null,true);
	}	
	/*加载地市地图*/
	function initdishiMap(regionId,eventType){
			var b="";
			if(eventType=="click"){
				b=false;
			}else{
				b=true;
			}
			$("#dishiselect").setSeletedWithValue(regionId);
			//加载县区下拉
			var t=$("#dishiselect").getSeletedText();
			var cur_xinaqu=regionDishiJsonTransform(allxianquData[regionNameTransform(t)],true);
			$("#xianquselect").reloadListData(cur_xinaqu);
			$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+t);
			//加载左侧指标
			initLeftIndex();
			//拼接兴趣点数量类型
			initInterstingPointNumber();
			//加载默认指标
			loadThisIndex(null,b);
	}
	
	/*加载县区所有指标*/
	function initxianquIndex(regionId){
			$("#xianquselect").setSeletedWithValue(regionId);
			//加载县区下拉
			var t=$("#xianquselect").getSeletedText();
			$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+t);
			//加载左侧指标
			initLeftIndex();
			//拼接兴趣点数量类型
			initInterstingPointNumber();
			//加载默认指标
			loadThisIndex(null,false);
	}
	
	

	
	
	/* 调整布局指标搜索框布局 */
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
		$(".searchindex").width($(".indexselect").width());
		$("#divCurMapRelIndexInfo").css("left",$(".indexselect").css("left"));
		$("#pop_divAddRelIndex").draggable({
			cursor:"pointer"
		});
		var divQuitScene_left = winWidth*0.5-$("#divQuitScene").width()*0.5;
		$("#divQuitScene").css("left",divQuitScene_left);
	}
	/* 下载指标数据 */
	function downloadIndex(){
		var date = $("#datepiker").val().replace(".","").replace(".","");
		var dishiid=$("#dishiselect").getSeletedValue();
		var xianquid=$("#xianquselect").getSeletedValue();
		var cur_index=$("#divMapCurIndex").attr("MM_ID");
		window.open("downloadIndexdata.do?date="+date+"&datetype="+dateType+"&dishiid="+dishiid+"&xianquid="+xianquid+"&indexid="+cur_index+"&MM_LEVEL="+MM_LEVEL);
	}
	/* 切换指标 */
	function switchIndex(){
		$("#divCurMapRelIndexInfo").hide();
		var dom = $("#divCurMapRelIndexInfo").find(".main");
		dom.getNiceScroll().hide();
		var top=$(".indexselect").offset().top+$(".indexselect").height()+5;
		var left=$(".indexselect").offset().left;
		$("#divSearchIndex").css("top",top).css("left",left);
		if($("#divSearchIndex").is(":hidden")){
			$("#divSearchIndex").show();
		}else{
			CloseSearchDiv();
		}
	}
	
	/* 查询地图当前指标的相关影响指标数据 */
	function initMapRelIndexInfo(name,id){
		CloseSearchDiv();
		var dom = $("#divCurMapRelIndexInfo").find(".main");
		var indexid = $("#divMapCurIndex").attr("MM_ID");
		$("#divCurMapRelIndexInfo").find(".title").find(".maprelindexname").html(name);
		if($("#divCurMapRelIndexInfo").is(":hidden")){
			var dishiRegionId=$("#dishiselect").getSeletedValue();
			var xianquRegionId=$("#xianquselect").getSeletedValue();
			$.ajax({
				url:"queryRelevantIndex.do",
				type:"post",
				dataType:"json",
				data:{"indexid":indexid,"date":$("#datepiker").val().replace(".","").replace(".",""),
						"dishiRegionId":dishiRegionId,"xianquRegionId":xianquRegionId,"datetype":dateType},
				success:function(data){
					var resultjson = data;
					if(resultjson.length!=0){
						dom.html(mosaicRelIndexDiv(resultjson));
						$("#divCurMapRelIndexInfo").show();
					}else{
						alert("该指标暂无相关影响指标！");
				}
			}
		});	
		
		dom.niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "1px",
			cursorborder: "0px solid #fff",
			autohidemode:false
		});
		dom.getNiceScroll().show();
		}else{
			$("#divCurMapRelIndexInfo").hide();
			dom.getNiceScroll().hide();
		}
	}
	/* 查询指标 */
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
			cursorwidth: "0px",
			cursorborder: "0px solid #fff",
			autohidemode:false
		});
		$("#divQueryIndexResult").show();
	}
	
	/* 加载选择的指标 指标切换*/
	function loadThisIndex(obj,b){
		//刷新地图
		var MM_ID=(obj==null ? $("#divMapCurIndex").attr("MM_ID"):$(obj).attr("indexid") );
		var date =$("#datepiker").val();
		var dishiRegionId=$("#dishiselect").getSeletedValue();
		var xianquRegionId=$("#xianquselect").getSeletedValue();
		$.ajax({
			url:"querySwitchIndex.do",
			type:"post",
			dataType:"json",
			data:{"MM_ID":MM_ID,"date":date,"dateType":dateType,
				"dishiRegionId":dishiRegionId,"xianquRegionId":xianquRegionId},
			success:function(data){
				/*拼接切换指标div*/
				mosaicCurIndexInfoCode(data["globalIndex"]);
				/*拼接图例位置数据*/
				$("#spnLegendUnit").html("单位："+data["globalIndex"][0]["MM_UNIT"]);
				$("#divKeyValue1").html("["+data["globalIndex"][0]["BLUE_COLOR_MIN"]+",∞)");
				$("#divKeyValue2").html("["+data["globalIndex"][0]["LIGHT_BLUE_MIN"]+","+data["globalIndex"][0]["LIGHT_BLUE_MAX"]+")");
				$("#divKeyValue3").html("["+data["globalIndex"][0]["LIGHT_COLOR_MIN"]+","+data["globalIndex"][0]["LIGHT_COLOR_MAX"]+")");
				
				/*加载地图数据*/
				if(data["all"]==1){
					if(b){
						$("#divmap").html("");
						//加载河北省地图
						heBeiMap(data,"divmap");
					}
					MM_LEVEL="CITY";
				}else if(data["all"]==2){
					if(b){
					$("#divmap").html("");
						//加载地市地图
					dishiMap(data,"divmap");
					}
					MM_LEVEL="COUNTY";
				}else if(data["all"]==3){
					if(b){
					$("#divmap").html("");
						//加载地市地图
					dishiMap(data,"divmap");
					}
					MM_LEVEL="COUNTY";
				}
				CloseSearchDiv();
				closeIndexSearchDiv();
				$("#divCurMapRelIndexInfo").hide();
				var dom = $("#divCurMapRelIndexInfo").find(".main");
				dom.getNiceScroll().hide();
				$("#"+MM_LEVEL).addClass("setColor");
				$("#"+MM_LEVEL).siblings().removeClass("setColor");
			}
		});	
		//关闭指标搜索框
		closeIndexSearchDiv($(obj).find(".name").text(),$(obj).attr("indexid"));
	}
	
	/* 查询指标 */
	function queryIndex(key,domid){
		var queryDateType=dateType;
		$("#"+domid).html("");
		$.ajax({
			url:"selectIndexByKey.do",
			type:"post",
			dataType:"json",
			data:{"datatype":queryDateType,"keystr":$.trim(key),"MM_LEVEL":MM_LEVEL},
			success:function(data){
				var resultjson = data;
				if(resultjson!=null&&resultjson!=undefined){
					$("#"+domid).html(mosaicIndexResultCode(resultjson));
				}
			}
		});	
	}
	
	/*加载左侧指标数据*/
	function initLeftIndex(){
		var date =$("#datepiker").val();
		var dishiRegionId=$("#dishiselect").getSeletedValue();
		var xianquRegionId=$("#xianquselect").getSeletedValue();		
		$.ajax({
			url:"initLeftQueryIndex.do",
			type:"post",
			dataType:"json",
			data:{"datetype":dateType,"date":date,"dishiRegionId":dishiRegionId,"xianquRegionId":xianquRegionId},
			success:function(data){
				var resultjson = data;
				if(resultjson.length!=0){
					mosaicRegionIndexDiv(resultjson);
				}
			}
		});	
	}
	
	/* 查询关联指标数据 */
	function queryRelIndex(domid,indexid,regionid){
		var dishiRegionId=$("#dishiselect").getSeletedValue();
		var xianquRegionId=$("#xianquselect").getSeletedValue();
		var dom=$("#"+domid).find(".relmain").html("");
		$.ajax({
			url:"queryRelevantIndex.do",
			type:"post",
			dataType:"json",
			data:{"indexid":indexid,"date":$("#datepiker").val().replace(".","").replace(".",""),
					"dishiRegionId":dishiRegionId,"xianquRegionId":xianquRegionId,"datetype":dateType,"regionid":regionid},
			success:function(data){
				var resultjson = data;
				if(resultjson.length!=0){
					dom.html(mosaicRelIndexDiv(resultjson));
				}
			}
		});	
	}
	/*获取该地域兴趣点的类型以及数量*/
	function initInterstingPointNumber(){
		var dishiRegionId=$("#dishiselect").getSeletedValue();
		var xianquRegionId=$("#xianquselect").getSeletedValue();		
		$.ajax({
			url:"initInterstingPointNumber.do",
			type:"post",
			dataType:"json",
			data:{"dishiRegionId":dishiRegionId,"xianquRegionId":xianquRegionId},
			success:function(data){
				if(data.length!=0){
					/* LoadIPRTypeDiv("divIprtype",data,"0"); */
					LoadIPRTypeCount(data);
				}
			}
		});	
	}
</script>
</html>
