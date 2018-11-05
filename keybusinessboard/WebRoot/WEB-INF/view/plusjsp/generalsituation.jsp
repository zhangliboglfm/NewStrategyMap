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

<title>总体情况</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/generalsituationplus/generalsituation.css" type="text/css"></link>

<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/unslider.min.js"></script>
<script type="text/javascript" src="js/countUp.js"></script>
<script type="text/javascript" src="js/radialIndicator.js"></script>
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript" src="js/Echarts-generalplus.js"></script>

<style>
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
body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0;}
.dots{position: absolute; left: 165px; right: 0; bottom:20px;width: 90px;height: 15px;margin: 0;padding: 0;z-index: 999;}
.dots li{ 
	display:inline-block; 
	width: 6px; 
	height: 6px; 
	margin: 5px 4px; 
	text-indent: -999em; 
	border: 0px solid #fff; 
	border-radius: 8px; 
	cursor: pointer; 
	opacity: .4; 
	background-color:#7A7A86;
	-webkit-transition: background .5s, opacity .5s; 
	-moz-transition: background .5s, opacity .5s; 
	transition: background .5s, opacity .5s;
}
.dots li.active {background: #fff;opacity: 1;}
</style>
</head>

<body>
	<div class="main">
		<div class="top">
			<div class="eachinfodiv magrin1 color1">
				<i class="iconfont infoicon">&#xe8a4;</i>
				<span class="infoname">总办理量：</span>
				<span class="infonum"><span id="spnAllBusCnt" class="num">--</span><span class="unit">次</span></span>
				<i id="iAllBusCntSc" class="iconfont lifticon"></i>
			</div>
			<div class="eachinfodiv magrin2 color2">
				<i class="iconfont infoicon">&#xe667;</i>
				<span class="infoname">办理人：</span>
				<span class="infonum"><span id="spnAllUserCnt" class="num">--</span><span class="unit">人</span></span>
				<i id="iAllUserCntSc" class="iconfont lifticon"></i>
			</div>
			<div class="eachinfodiv magrin2 color3">
				<i class="iconfont infoicon">&#xe611;</i>
				<span class="infoname">重点业务占比：</span>
				<span class="infonum"><span id="spnImptBusRate" class="num">--</span><span class="unit">%</span></span>
				<i id="iImptBusRateSc" class="iconfont lifticon"></i>
			</div>
		</div>
		<div class="bottom">
			<div class="eachbusdiv eachbusdiv-first">
				<div class="title"><span class="titlename">4G类</span></div>
				<div class="infomain">
					<div class="progressdiv">
						<div class="rate">
							<div id="div4GProgress" class="radiadiv"></div>
							<div class="centerdiv">
								<div class="sdiv">占比</div>
								<div id="div4GRateNum" class="ndiv">--</div>
							</div>
						</div>
						<div class="cnt">
							<span class="spanname">办理量：</span>
							<span id="div4GCountCnt" class="spannumber">--</span>
							<i id="div4GCountCntSc" class="iconfont spanicon"></i>
							<span id="div4GCountCntScNum" class="spanscnum"></span>
						</div>
					</div>
					<div id="4gbanner" class="chartdiv">
						<ul>
							<li><div id="div4GRegionChart" class="buschartdiv"></div></li>
							<li><div id="div4GChannelChart" class="buschartdiv"></div></li>
							<li><div id="div4GKindChart" class="buschartdiv"></div></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="eachbusdiv">
				<div class="title"><span class="titlename">宽带类</span></div>
				<div class="infomain">
					<div class="progressdiv">
						<div class="rate">
							<div id="divKDProgress" class="radiadiv"></div>
							<div class="centerdiv">
								<div class="sdiv">占比</div>
								<div id="divKDRateNum" class="ndiv">--</div>
							</div>
						</div>
						<div class="cnt">
							<span class="spanname">办理量：</span>
							<span id="divKDCountCnt" class="spannumber">--</span>
							<i id="divKDCountCntSc" class="iconfont spanicon"></i>
							<span id="divKDCountCntScNum" class="spanscnum"></span>
						</div>
					</div>
					<div id="kdbanner" class="chartdiv">
						<ul>
							<li><div id="divKDRegionChart" class="buschartdiv"></div></li>
							<li><div id="divKDChannelChart" class="buschartdiv"></div></li>
							<li><div id="divKDKindChart" class="buschartdiv"></div></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="eachbusdiv">
				<div class="title"><span class="titlename">流量类</span></div>
				<div class="infomain">
					<div class="progressdiv">
						<div class="rate">
							<div id="divFlowProgress" class="radiadiv"></div>
							<div class="centerdiv">
								<div class="sdiv">占比</div>
								<div id="divFlowRateNum" class="ndiv">--</div>
							</div>
						</div>
						<div class="cnt">
							<span class="spanname">办理量：</span>
							<span id="divFlowCountCnt" class="spannumber">--</span>
							<i id="divFlowCountCntSc" class="iconfont spanicon"></i>
							<span id="divFlowCountCntScNum" class="spanscnum"></span>
						</div>
					</div>
					<div id="flowbanner" class="chartdiv">
						<ul>
							<li><div id="divFlowRegionChart" class="buschartdiv"></div></li>
							<li><div id="divFlowChannelChart" class="buschartdiv"></div></li>
							<li><div id="divFlowKindChart" class="buschartdiv"></div></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="eachbusdiv">
				<div class="title"><span class="titlename">其他</span></div>
				<div class="infomain">
					<div class="progressdiv">
						<div class="rate">
							<div id="divOtherProgress" class="radiadiv"></div>
							<div class="centerdiv">
								<div class="sdiv">占比</div>
								<div id="divOtherRateNum" class="ndiv">--</div>
							</div>
						</div>
						<div class="cnt">
							<span class="spanname">办理量：</span>
							<span id="divOtherCountCnt" class="spannumber">--</span>
							<i id="divOtherCountCntSc" class="iconfont spanicon"></i>
							<span id="divOtherCountCntScNum" class="spanscnum"></span>
						</div>
					</div>
					<div id="otherbanner" class="chartdiv">
						<ul>
							<li><div id="divOtherRegionChart" class="buschartdiv"></div></li>
							<li><div id="divOtherChannelChart" class="buschartdiv"></div></li>
							<li><div id="divOtherKindChart" class="buschartdiv"></div></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	<div>
</body>
<script type="text/javascript">
	var regionId = "<%=request.getAttribute("regionId")%>";
	var maxdate = "<%=request.getAttribute("maxDate")%>";
	var mindate = "<%=request.getAttribute("minDate")%>";
	var curdate = "<%=request.getAttribute("curdate")%>";
	var state = "<%=request.getAttribute("state")%>";
	curdate = (curdate=="null"? mindate : curdate);
	var sliderSpeedTime=1000;
	var sliderDelayTime=8000;
	
	var silder1;
	var silder2;
	var silder3;
	var silder4;
	
	regionId = (regionId=="null" ? "1" : regionId);
	
	//总体数据用到的全局变量
	var AllBusCntCountDom;
	var AllUserCntCountDom;
	var ImptBusRateCountDom;
	
	//4G业务用到的全局变量
	var radiaObj4G;
	var rate4GCountDom;
	var cnt4GCountDom;
	var cnt4GCountScNumDom;
	var regionchart4G;
	var channelchart4G;
	var kindchart4G;
	
	
	//宽带业务用到的全局变量
	var radiaObjKD;
	var rateKDCountDom;
	var cntKDCountDom;
	var cntKDCountScNumDom;
	var regionchartKD;
	var channelchartKD;
	var kindchartKD;
	
	//流量业务用到的全局变量
	var radiaObjFlow;
	var rateFlowCountDom;
	var cntFlowCountDom;
	var cntFlowCountScNumDom;
	var regionchartFlow;
	var channelchartFlow;
	var kindchartFlow;
	
	//其他业务用到的全局变量
	var radiaObjOther;
	var rateOtherCountDom;
	var cntOtherCountDom;
	var cntOtherCountScNumDom;
	var regionchartOther;
	var channelchartOther;
	var kindchartOther;
	var deom;
	$(function(){
		adjust();
		initGeneralData();
		init4GBus();
		initKDBus();
		initFlowBus();
		initOtherBus();
		
		silder1=$("#4gbanner").unslider({
				speed:sliderSpeedTime,
				delay:sliderDelayTime,
				dots: true,
			});
		silder2=$("#kdbanner").unslider({
				speed:sliderSpeedTime,
				delay:sliderDelayTime,
				dots: true,
			});
		silder3=$("#flowbanner").unslider({
				speed:sliderSpeedTime,
				delay:sliderDelayTime,
				dots: true
			});
		silder4=$("#otherbanner").unslider({
				speed:sliderSpeedTime,
				delay:sliderDelayTime,
				dots: true
			});
		if(state=="stop"){
			silder1.data("unslider").stop();
			silder2.data("unslider").stop();
			silder3.data("unslider").stop();
			silder4.data("unslider").stop();
		};
		
		$("#4gbanner,#kdbanner,#flowbanner,#otherbanner").mouseout(function(){
			if(state=="stop"){
				silder1.data("unslider").stop();
				silder2.data("unslider").stop();
				silder3.data("unslider").stop();
				silder4.data("unslider").stop();
			}
		});
	});
	
	/*开始轮播*/
	function beginlunbo(){
		state="start";
		silder1.data("unslider").start();
		silder2.data("unslider").start();
		silder3.data("unslider").start();
		silder4.data("unslider").start();
	};
	
		
	/*子页面停止轮播*/
	function endlunbo(){
		state="stop";
		silder1.data("unslider").stop();
		silder2.data("unslider").stop();
		silder3.data("unslider").stop();
		silder4.data("unslider").stop();
	}
	
	/* 调整布局 */
	function adjust(){
		var domheight = $(window).height();
		var domwidth = $(window).width();
		$(".bottom").height(domheight-$(".top").height()-15);
		$(".eachinfodiv").css("line-height",$(".top").height()+"px");
		$(".infonum").find(".num").css("line-height",$(".top").height()-4+"px");
		$(".infomain").height($(".eachbusdiv").height()-$(".eachbusdiv").find(".title").height());
		$(".progressdiv").find(".rate").width($(".progressdiv").find(".rate").height());
		$(".progressdiv").find(".rate").css("margin-left",$(".progressdiv").width()*0.5-$(".progressdiv").find(".rate").width()*0.5);
		$(".centerdiv").width($(".progressdiv").find(".rate").width()*0.5);
		$(".centerdiv").height($(".progressdiv").find(".rate").width()*0.5);
		$(".centerdiv").find(".sdiv").css("line-height",$(".centerdiv").find(".sdiv").height()+"px");
		var centerdivX=$(".progressdiv").find(".rate").width()*0.5-$(".centerdiv").width()*0.5;
		var centerdivY=$(".progressdiv").find(".rate").height()*0.5-$(".centerdiv").height()*0.5;
		$(".centerdiv").css("top",centerdivY);
		$(".centerdiv").css("left",centerdivX);
		$(".spannumber").css("line-height",$(".progressdiv").find(".cnt").height()+"px");
	}
	/* 查询总体数据 */
	function initGeneralData(type){
		$.ajax({
			url:"getallcntdataPlus.do",
			type:"post",
			dataType:"json",
			data:{"date":curdate,"regionId":regionId},
			success:function(data){
				var AllBusCntJson = data.prop;
				var AllUserCntJson = data.user;
				var ImptBusRateJson = data.rate;
				if(type!=undefined&&type!=null&&type=="update"){
					initAllBusCntNum(AllBusCntJson.num,AllBusCntJson.sc,type);
					initAllUserCntNum(AllUserCntJson.num,AllUserCntJson.sc,type);
					initImptBusRateNum(ImptBusRateJson.num,ImptBusRateJson.sc,type);
				}else{
					initAllBusCntNum(AllBusCntJson.num,AllBusCntJson.sc);
					initAllUserCntNum(AllUserCntJson.num,AllUserCntJson.sc);
					initImptBusRateNum(ImptBusRateJson.num,ImptBusRateJson.sc);
				}
			}
		});
	}
	/* 加载总办理量 */
	function initAllBusCntNum(num,sc,type){
		if(sc=="up"){
			initIconFont("iAllBusCntSc","&#xe665;");
		}else if(sc=="down"){
			initIconFont("iAllBusCntSc","&#xe666;");
		}else{
			initIconFont("iAllBusCntSc","");
		}
		if(type!=undefined&&type!=null&&type=="update"){
			AllBusCntCountDom.update(num);
		}else{
			AllBusCntCountDom=initCountNumber("spnAllBusCnt", 0, num, 0, 1, "");
		}
	}
	/* 加载总办理用户 */
	function initAllUserCntNum(num,sc,type){
		if(sc=="up"){
			initIconFont("iAllUserCntSc","&#xe665;");
		}else if(sc=="down"){
			initIconFont("iAllUserCntSc","&#xe666;");
		}else{
			initIconFont("iAllUserCntSc","");
		}
		if(type!=undefined&&type!=null&&type=="update"){
			AllUserCntCountDom.update(num);
		}else{
			AllUserCntCountDom=initCountNumber("spnAllUserCnt", 0, num, 0, 1, "");
		}
	}
	/* 加载重点业务占比 */
	function initImptBusRateNum(num,sc,type){
		if(sc=="up"){
			initIconFont("iImptBusRateSc","&#xe665;");
		}else if(sc=="down"){
			initIconFont("iImptBusRateSc","&#xe666;");
		}else{
			initIconFont("iImptBusRateSc","");
		}
		if(type!=undefined&&type!=null&&type=="update"){
			ImptBusRateCountDom.update(num);
		}else{
			ImptBusRateCountDom=initCountNumber("spnImptBusRate", 0, num, 2, 1, "");
		}
	}
	/* 查询4G业务数据 */
	function init4GBus(type){
		$.ajax({
			url:"get4GbusdataPlus.do",
			type:"post",
			dataType:"json",
			data:{"date":curdate,"regionId":regionId},
			success:function(data){
				var busrate = data.busrate;
				var buscnt = data.buscnt;
				var sc = data.sc;
				var scnum = data.scnum;
				var chartdata = data.chartdata;
				if(type!=undefined&&type!=null&&type=="update"){
					init4GBusData(busrate,buscnt,sc,scnum,chartdata,type);
				}else{
					init4GBusData(busrate,buscnt,sc,scnum,chartdata);
				}
			}
		});
	}
	/* 加载4G业务发展数据 */
	function init4GBusData(busrate,buscnt,sc,scnum,data,type){
		if(sc=="up"){
			initIconFont("div4GCountCntSc","&#xe60a;","#138061");
		}else if(sc=="down"){
			initIconFont("div4GCountCntSc","&#xe60b;","#d84d62");
		}else{
			initIconFont("div4GCountCntSc","");
		}
		if(type!=undefined&&type!=null&&type=="update"){
			radiaObj4G.animate(busrate);
			rate4GCountDom.update(busrate);
			cnt4GCountDom.update(buscnt);
			cnt4GCountScNumDom.update(scnum);
			var regionOption ={yAxis:{data:data.regiondata.datax},series:[{data:data.regiondata.datay.cnt},{data:data.regiondata.datay.rate}]};
			var channelOption ={yAxis:{data:data.channeldata.datax},series:[{data:data.channeldata.datay}]};
			var kindOption ={yAxis:{data:data.kinddata.datax},series:[{data:data.kinddata.datay}]};
			regionchart4G.setOption(regionOption);
			channelchart4G.setOption(channelOption);
			kindchart4G.setOption(kindOption);
			if(sc!="up"&&sc!="down"){
				$("#div4GCountCntScNum").hide();
			}else{
				$("#div4GCountCntScNum").show();
			}
		}else{
			radiaObj4G = initProgressRadia("div4GProgress",busrate);
			rate4GCountDom = initCountNumber("div4GRateNum",0,busrate,2,1,"%");
			cnt4GCountDom = initCountNumber("div4GCountCnt",0,buscnt,0,1,"");
			cnt4GCountScNumDom = initCountNumber("div4GCountCntScNum",0,scnum,2,1,"%");
			regionchart4G = initRegionCharts("div4GRegionChart",data.regiondata);
			channelchart4G = initChannelCharts("div4GChannelChart",data.channeldata);
			kindchart4G = initKindCharts("div4GKindChart",data.kinddata);
			if(sc!="up"&&sc!="down"){
				$("#div4GCountCntScNum").hide();
			}else{
				$("#div4GCountCntScNum").show();
			}
		}
	}
	/* 查询宽带业务数据 */
	function initKDBus(type){
		$.ajax({
			url:"getKDbusdataPlus.do",
			type:"post",
			dataType:"json",
			data:{"date":curdate,"regionId":regionId},
			success:function(data){
				var busrate = data.busrate;
				var buscnt = data.buscnt;
				var sc = data.sc;
				var scnum = data.scnum;
				var chartdata = data.chartdata;
				if(type!=undefined&&type!=null&&type=="update"){
					initKDBusData(busrate,buscnt,sc,scnum,chartdata,type);
				}else{
					initKDBusData(busrate,buscnt,sc,scnum,chartdata);
				}
			}
		});
	}
	/* 加载宽带业务发展数据 */
	function initKDBusData(busrate,buscnt,sc,scnum,data,type){
		if(sc=="up"){
			initIconFont("divKDCountCntSc","&#xe60a;","#138061");
		}else if(sc=="down"){
			initIconFont("divKDCountCntSc","&#xe60b;","#d84d62");
		}else{
			initIconFont("divKDCountCntSc","");
		}
		if(type!=undefined&&type!=null&&type=="update"){
			radiaObjKD.animate(busrate);
			rateKDCountDom.update(busrate);
			cntKDCountDom.update(buscnt);
			cntKDCountScNumDom.update(scnum);
			var regionOption ={yAxis:{data:data.regiondata.datax},series:[{data:data.regiondata.datay.cnt},{data:data.regiondata.datay.rate}]};
			var channelOption ={yAxis:{data:data.channeldata.datax},series:[{data:data.channeldata.datay}]};
			var kindOption ={yAxis:{data:data.kinddata.datax},series:[{data:data.kinddata.datay}]};
			regionchartKD.setOption(regionOption);
			channelchartKD.setOption(channelOption);
			kindchartKD.setOption(kindOption);
			if(sc!="up"&&sc!="down"){
				$("#divKDCountCntScNum").hide();
			}else{
				$("#divKDCountCntScNum").show();
			}
		}else{
			radiaObjKD = initProgressRadia("divKDProgress",busrate);
			rateKDCountDom = initCountNumber("divKDRateNum",0,busrate,2,1,"%");
			cntKDCountDom = initCountNumber("divKDCountCnt",0,buscnt,0,1,"");
			cntKDCountScNumDom = initCountNumber("divKDCountCntScNum",0,scnum,2,1,"%");
			regionchartKD = initRegionCharts("divKDRegionChart",data.regiondata);
			channelchartKD = initChannelCharts("divKDChannelChart",data.channeldata);
			kindchartKD = initKindCharts("divKDKindChart",data.kinddata);
			if(sc!="up"&&sc!="down"){
				$("#divKDCountCntScNum").hide();
			}else{
				$("#divKDCountCntScNum").show();
			}
		}
	}
	/* 查询流量业务数据 */
	function initFlowBus(type){
		$.ajax({
			url:"getFlowbusdataPlus.do",
			type:"post",
			dataType:"json",
			data:{"date":curdate,"regionId":regionId},
			success:function(data){
				var busrate = data.busrate;
				var buscnt = data.buscnt;
				var sc = data.sc;
				var scnum = data.scnum;
				var chartdata = data.chartdata;
				if(type!=undefined&&type!=null&&type=="update"){
					initFlowBusData(busrate,buscnt,sc,scnum,chartdata,type);
				}else{
					initFlowBusData(busrate,buscnt,sc,scnum,chartdata);
				}
			}
		});
	}
	/* 加载流量业务发展数据 */
	function initFlowBusData(busrate,buscnt,sc,scnum,data,type){
		if(sc=="up"){
			initIconFont("divFlowCountCntSc","&#xe60a;","#138061");
		}else if(sc=="down"){
			initIconFont("divFlowCountCntSc","&#xe60b;","#d84d62");
		}else{
			initIconFont("divFlowCountCntSc","");
		}
		if(type!=undefined&&type!=null&&type=="update"){
			radiaObjFlow.animate(busrate);
			rateFlowCountDom.update(busrate);
			cntFlowCountDom.update(buscnt);
			cntFlowCountScNumDom.update(scnum);
			var regionOption ={yAxis:{data:data.regiondata.datax},series:[{data:data.regiondata.datay.cnt},{data:data.regiondata.datay.rate}]};
			var channelOption ={yAxis:{data:data.channeldata.datax},series:[{data:data.channeldata.datay}]};
			var kindOption ={yAxis:{data:data.kinddata.datax},series:[{data:data.kinddata.datay}]};
			regionchartFlow.setOption(regionOption);
			channelchartFlow.setOption(channelOption);
			kindchartFlow.setOption(kindOption);
			if(sc!="up"&&sc!="down"){
				$("#divFlowCountCntScNum").hide();
			}else{
				$("#divFlowCountCntScNum").show();
			}
		}else{
			radiaObjFlow = initProgressRadia("divFlowProgress",busrate);
			rateFlowCountDom = initCountNumber("divFlowRateNum",0,busrate,2,1,"%");
			cntFlowCountDom = initCountNumber("divFlowCountCnt",0,buscnt,0,1,"");
			cntFlowCountScNumDom = initCountNumber("divFlowCountCntScNum",0,scnum,2,1,"%");
			regionchartFlow = initRegionCharts("divFlowRegionChart",data.regiondata);
			channelchartFlow = initChannelCharts("divFlowChannelChart",data.channeldata);
			kindchartFlow = initKindCharts("divFlowKindChart",data.kinddata);
			if(sc!="up"&&sc!="down"){
				$("#divFlowCountCntScNum").hide();
			}else{
				$("#divFlowCountCntScNum").show();
			}
		}
	}
	/* 查询其他业务数据 */
	function initOtherBus(type){
		$.ajax({
			url:"getOtherbusdataPlus.do",
			type:"post",
			dataType:"json",
			data:{"date":curdate,"regionId":regionId},
			success:function(data){
				var busrate = data.busrate;
				var buscnt = data.buscnt;
				var sc = data.sc;
				var scnum = data.scnum;
				var chartdata = data.chartdata;
				if(type!=undefined&&type!=null&&type=="update"){
					initOtherBusData(busrate,buscnt,sc,scnum,chartdata,type);
				}else{
					initOtherBusData(busrate,buscnt,sc,scnum,chartdata);
				}
			}
		});
	}
	/* 加载其他业务发展数据 */
	function initOtherBusData(busrate,buscnt,sc,scnum,data,type){
		if(sc=="up"){
			initIconFont("divOtherCountCntSc","&#xe60a;","#138061");
		}else if(sc=="down"){
			initIconFont("divOtherCountCntSc","&#xe60b;","#d84d62");
		}else{
			initIconFont("divOtherCountCntSc","");
		}
		if(type!=undefined&&type!=null&&type=="update"){
			radiaObjOther.animate(busrate);
			rateOtherCountDom.update(busrate);
			cntOtherCountDom.update(buscnt);
			cntOtherCountScNumDom.update(scnum);
			var regionOption ={yAxis:{data:data.regiondata.datax},series:[{data:data.regiondata.datay.cnt},{data:data.regiondata.datay.rate}]};
			var channelOption ={yAxis:{data:data.channeldata.datax},series:[{data:data.channeldata.datay}]};
			var kindOption ={yAxis:{data:data.kinddata.datax},series:[{data:data.kinddata.datay}]};
			regionchartOther.setOption(regionOption);
			channelchartOther.setOption(channelOption);
			kindchartOther.setOption(kindOption);
			if(sc!="up"&&sc!="down"){
				$("#divOtherCountCntScNum").hide();
			}else{
				$("#divOtherCountCntScNum").show();
			}
		}else{
			radiaObjOther =initProgressRadia("divOtherProgress",busrate);
			rateOtherCountDom =initCountNumber("divOtherRateNum",0,busrate,2,1,"%");
			cntOtherCountDom =initCountNumber("divOtherCountCnt",0,buscnt,0,1,"");
			cntOtherCountScNumDom =initCountNumber("divOtherCountCntScNum",0,scnum,2,1,"%");
			regionchartOther =initRegionCharts("divOtherRegionChart",data.regiondata);
			channelchartOther =initChannelCharts("divOtherChannelChart",data.channeldata);
			kindchartOther =initKindCharts("divOtherKindChart",data.kinddata);
			if(sc!="up"&&sc!="down"){
				$("#divOtherCountCntScNum").hide();
			}else{
				$("#divOtherCountCntScNum").show();
			}
		}
	}
	/* 加载iconfont */
	function initIconFont(domid,code,color){
		$("#"+domid).html("");
		$("#"+domid).html(code);
		if(color!=undefined){
			$("#"+domid).css("color",color);
		}
	}
	/* 加载数值 */
	function initCountNumber(id,startnum,endnum,decimalcnt,duration,suffixstr){
		var demo,option={separator:",",decimal:".",suffix:suffixstr};
		demo = new CountUp(id, startnum, endnum, decimalcnt, duration, option);
		demo.start();
		return demo;
	}
	/* 加载圆形进度条 */
	function initProgressRadia(id,value){
		var radius=($("#"+id).width())*0.5;
		var radiaObj = radialIndicator("#"+id,{
			radius:radius-10,
			roundCorner:true,
			initValue: 0,
			barWidth: 10,
			barBgColor:"#333947",
			barColor: {
                0: "#1041d8",
                33: "#3568ff",
                66: "#00b4ff",
                100: "#00ffff"
            },
            percentage: true,
            displayNumber:false
		});
		radiaObj.animate(value);
		return radiaObj;
	}
	function main(date,regionid){
		curdate = date;
		regionId = regionid;		
		initGeneralData("update");
		init4GBus("update");
		initKDBus("update");
		initFlowBus("update");
		initOtherBus("update");
	}
</script>
</html>
