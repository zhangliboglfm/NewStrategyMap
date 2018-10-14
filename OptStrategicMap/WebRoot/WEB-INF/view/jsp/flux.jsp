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

<title>流量</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" href="css/flux.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/regionquery/css/regionquery.blue.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/jedate/skin/jedate.lightblue.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jsp/bsh.flux.fn.js"></script>
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript" src="js/jsp/bsh.echarts.flux.fn.js"></script>
<script type="text/javascript" src="js/jsp/bsh.echarts.map.js"></script>
<script type="text/javascript" src="plugin/regionquery/js/regionquery.core.js"></script>
<script type="text/javascript" src="plugin/jedate/jedate.js"></script>
<script type="text/javascript" src="js/d3.min.js"></script>
<script type="text/javascript" src="js/jsp/zlb.d3.map.js"></script>

<style type="text/css">
*{font-family: "Microsoft YaHei";}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0px;}
.middle .regiondiv div.regionselect:AFTER{
	content: "[切换]";
	color: #007EB9;
	cursor: pointer;
	margin-left: 10px;
	font-size: 12px
}
.middle .regiondiv div.IndexSelect:AFTER{
	content: "[切换]";
	color: #007EB9;
	cursor: pointer;
	margin-left: 10px;
	font-size: 12px
}

</style>
</head>

<body>
	<div class="datadiv left">
		<div id="divLT" class="left_top">
			<div class="title"><span id="spnLT">总流量</span></div>
			<div class="chart" id="divLTChart"></div>
		</div>
		<div id="divLM" class="left_middle">
			<div class="title"><span id="spnLM">全省流量分析</span></div>
			<div class="chart" id="divLMChart">
			</div>
		</div>
		<div id="divLB" class="left_bottom">
			<div class="title"><span id="spnLB">高低流量用户占比</span></div>
			<div class="chart" id="divLBChart"></div>
		</div>
	</div>
	<div class="datadiv middle">
		<div class="regiondiv">
			<i class="iconfont">&#xe647;</i>
			<div id="divRegion" class="regionselect"></div>
			<div id="IndexSelect" class="IndexSelect" onclick="getIndex(this)" data-index="默认指标" ><i class="iconfont" style="font-size: 14px;cursor: pointer">&#xe604;</i>&nbsp;<span>4G用户数</span></div>
		</div>
		<div class="dateselectdiv">
			<div class="DMdiv">
				<div id="divDayType" datetype="D" class="typediv curdiv" onclick="ChangeDateType(this)">日</div>
				<div id="divMonthType" datetype="M" class="typediv" onclick="ChangeDateType(this)">月</div>
			</div>
			<div class="datepickerdiv">
				<input id="datepiker" type="text" readonly="readonly"/><i id="iconDatepiker" class="iconfont">&#xe621;</i>
			</div>
		</div>
		<div class="mapdiv">
			<div id="svgMap" class="svgMap"></div> 
			<div id="echartsMap" class="echartsMap"></div>
			<div class="mapright">
				<div class="title"><span class="desc">图例</span><span class="unit">(万MB)</span></div>
				<div class="onepart">
					<span class="spancolor spancolor1"></span><span class="spantext">300以上</span>
				</div>
				<div class="onepart">
					<span class="spancolor spancolor2"></span><span class="spantext">150~300</span>
				</div>
				<div class="onepart">
					<span class="spancolor spancolor3"></span><span class="spantext">150以下</span>
				</div>
			</div>
		</div>
	</div>
	<div class="datadiv right">
		<div id="divRT" class="right_top">
			<div class="title"><span id="spnRT">总流量近期趋势</span></div>
			<div class="chart" id="divRTChart"></div>
		</div>
		<div id="divRM" class="right_middle">
			<div class="title"><span id="spnRM">4G新增用户数</span></div>
			<div class="chart" id="divRMChart"></div>
		</div>
		<div id="divRB" class="right_bottom">
			<div class="title"><span id="spnRB">4G渗透率</span></div>
			<div class="chart" id="divRBChart"></div>
		</div>
	</div>
	
	<!-- 指标切换部分 -->
	<div class="indexbox">
		<p>流量类</p>
		<ul id="ulIndex">
		</ul>
	</div>
</body>
<script type="text/javascript">
	var dateType="D";
	var dateD="2018-01-01";
	var dateM="2018-01";

	var DTypeFormat = "YYYY-MM-DD";
	var MTypeFormat = "YYYY-MM";
	
	var LvlId ="<%=session.getAttribute("LvlId")%>";
	var regionId ="<%=session.getAttribute("regionId")%>";
	var regionName ="<%=session.getAttribute("regionName")%>";
	
	
	/* 日期选择控件配置JSON */
	var jedateoption = {
		dateCell: "#datepiker",
		format: DTypeFormat,
		choosefun: function(elem, datas) {
		}
	};
	
	jeDate(jedateoption);

	$("#datepiker").val(dateD);

	$(function(){
		ReLayOut();
		$("#divRegion").miapRegion({
			"queryurl":"regionObtain.do",
			"lvl":LvlId,
			"cur_region":regionId,
			"cur_regionname":regionName,
			"clickfn":function(data){
				if(data.regionLvl=="1"&&data.regionId=="1"){
					drawHeBeiMap("svgMap","echartsMap",{});
				}else if(data.regionLvl=="2"){
					HbeiCountyMap("echartsMap","svgMap",{},data.regionName);
				}else{
					window.location.href="keyarea.do";
				}
			}
		});
		
		
		$("#iconDatepiker").click(function(){
			jeDate(jedateoption);
		});
		InitEchartsColumn("divLTChart",{});
		InitEchartsLine("divRTChart",{});
		InitEchartsBar("divRMChart",{});
		InitEchartsPie("divLBChart",{});
		InitEchartsGauge("divRBChart",{});
		InitIconDivData("divLMChart",{});
		
		if(LvlId=="1"){
			drawHeBeiMap("svgMap","echartsMap",{})
		}else if(LvlId =="2"){
			document.getElementById("echartsMap").style.zIndex = 99999;
			document.getElementById("svgMap").style.zIndex =-1;
			HbeiCountyMap("echartsMap","svgMap",{},regionName);
		};
		
	});
	
	function clickRegion(){
		$("#divRegion").click();
	};
	//调整布局
	function ReLayOut(){
		var domHeight = $(window).height();
		var domWidth = $(window).width();
		$(".chart").each(function(i,e){
			$(e).height($(e).parent().height()-$(".title").height());
		});
		
	}
	/* 切换日期类型 */
	function ChangeDateType(e){
		$(".curdiv").removeClass("curdiv");
		$(e).addClass("curdiv");
		dateType=$(e).attr("datetype");
		if(dateType=="D"){
			jedateoption.format=DTypeFormat;
			$("#datepiker").val(dateD);
		}else if(dateType=="M"){
			jedateoption.format=MTypeFormat;
			$("#datepiker").val(dateM);
		}
	}
	
	/*获取指标*/
	function getIndex(obj){
		var left =$(obj).offset().left;
		var top=$(obj).offset().top+$(obj).height()+20;
		$(document).click(function(){
			$(".indexbox").css("display","none");
		});
		//阻止冒泡事件   分别为谷歌，火狐，IE后去event参数
		var ev = window.event || arguments.callee.caller.arguments[0] || event;
		ev.stopPropagation();
		
		$("#ulIndex").html("");
		var dataIndex =["4G用户数","4G新增用户数","高流量用户数","流量费占比","4G渗透率","低流量用户数"];
		var str ="";
		for(var i=0; i<dataIndex.length;i++){
			str+="<li onclick='changeIndex(this)'>"+dataIndex[i]+"</li>";
		}
		$("#ulIndex").html(str);
		$(".indexbox").css({"left":left,"top":top,"display":"block"});
	}
	
	/*地图指标切换*/
	function changeIndex(obj){
		$("#IndexSelect").find("span").html($(obj).html());
		$("#IndexSelect").attr("data-index",$(obj).html());
		$(".indexbox").css("display","none");
	}
	
</script>
</html>
