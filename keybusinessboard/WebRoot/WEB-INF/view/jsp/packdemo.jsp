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

<title>套餐散点图</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/echarts.js"></script>

<style type="text/css">
*{font-family: Microsoft YaHei;}
html{width: 100%;height: 100%;}
body {width: 100%;height: 100%;margin: 0;padding: 0;overflow: hidden;}
.main{width: 100%;height: 100%;/* background: url('image/main/beijing.png');background-size: 100% 100%; */}
.sandianchart{width: 100%;height: 100%;}
.sandianchart2{width: 100%;height: 100%;}
</style>
</head>

<body>
	<div class="main">
		<div id="divPackChart" class="sandianchart"></div>
		<!-- <div id="divPackChart2" class="sandianchart2"></div> -->
	</div>
</body>
<script type="text/javascript">
	var dateMax="<%=request.getAttribute("maxdate")%>";
	var dateMin="<%=request.getAttribute("mindate")%>";
	var curdate="<%=request.getAttribute("curdate")%>";
	curdate = (curdate=="null"? dateMin :curdate);
	$(function(){
		getPackData();
	});
	/* 获取套餐数据 */
	function getPackData(){
		$.ajax({
	  		url:"getpackdemodata2.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate,linetype:"Online"},
	  		success:function(data){
		  		initPackSandianChart("divPackChart",data);
	  		}
	  	});
	}
	function initPackSandianChart(domid,data){
		var mychart = echarts.init(document.getElementById(domid));
		var pack = data.dataX;
		var region = data.dataZ;
		var data = data.dataY;
		option = {
		    tooltip: {
		        position: 'left',
		        formatter: function (params) {
		            return pack[params.value[0]]+':'+params.value[1]+'%';
		        }
		    },
		    title: [],
		    singleAxis: [],
		    series: []
		};
		
		echarts.util.each(region, function (day, idx) {
		    option.title.push({
		        textBaseline: 'middle',
		        top: (idx + 0.5) * 100 / 11 + '%',
		        text: day,
		        textStyle:{
		        	color:"#fff"
		        }
		    });
		    option.singleAxis.push({
		        left: 150,
		        type: 'category',
		        boundaryGap: false,
		        data: pack,
		        top: (idx * 100 / 11 + 5) + '%',
		        height: (100 / 11 - 10) + '%',
		        axisLabel: {
		        	show:true,
		        	textStyle:{
		        		color:"#fff",
		        	}
		        },
		        axisLine: {
		            show: true,
		            lineStyle: {
		                color: '#fff',
		                width:1
		            }
		        },
		        axisTick:{
		        	show:false
		        }
		    });
		    option.series.push({
		        singleAxisIndex: idx,
		        coordinateSystem: 'singleAxis',
		        type: 'scatter',
		        data: [],
		        symbol: 'circle',
		        symbolSize: function (dataItem) {
		            return dataItem[1]*3;
		        }
		    });
		});
		echarts.util.each(data, function (dataItem) {
		    option.series[dataItem[0]].data.push([dataItem[1], dataItem[2]]);
		});
		mychart.setOption(option);
	}
	function main(date){
		curdate=date;
		getPackData();
	}
</script>
</html>
