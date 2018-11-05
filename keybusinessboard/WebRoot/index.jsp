<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="js/echarts_new.js"></script>
	
  <style>
  	.scatter{
  		width: 800px;
  		height: 500px;
  		background: rgba(0,0,0,0.1);
  	}
  </style>
  </head>
  <body>
	<div class="scatter" id="scatter"></div>
	<button onclick="chang()">123</button>
	<button onclick="changedata()">456</button>
  </body>
</html>
<script>
	
	var data =
		    [[-28604,-77,17096869,'Australia',1990],[31163,-77.4,27662440,'Canada',1990],[-1516,68,1154605773,'China',1990],[13670,74.7,10582082,'Cuba',1990],[28599,75,4986705,'Finland',1990],[29476,77.1,56943299,'France',1990],[31476,75.4,78958237,'Germany',1990],[28666,78.1,254830,'Iceland',1990],[1777,57.7,870601776,'India',1990],[29550,79.1,122249285,'Japan',1990],[2076,67.9,20194354,'North Korea',1990],[12087,72,42972254,'South Korea',1990],[24021,75.4,3397534,'New Zealand',1990],[43296,76.8,4240375,'Norway',1990],[10088,70.8,38195258,'Poland',1990],[19349,69.6,147568552,'Russia',1990],[10670,67.3,53994605,'Turkey',1990],[26424,75.7,57110117,'United Kingdom',1990],[37062,75.4,252847810,'United States',1990]];
		
 	$(function(){
 		initscatter("scatter",data);
 	});
 	
 	function chang(){
 		initscatter("scatter",null);
 	}
 	
 	function changedata(){
 		initscatter("scatter",data);
 	}
 	
 	function  initscatter(chartId,data){
 		var myChart = echarts.init(document.getElementById(chartId));

		option = {
		    backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
		        offset: 0,
		        color: '#f7f8fa'
		    }, {
		        offset: 1,
		        color: '#cdd0d5'
		    }]),
		    xAxis: {
		        splitLine: {
		            lineStyle: {
		                type: 'dashed'
		            }
		        },
		        axisLabel:{
		        	inside:true,
		        	margin:250
		        }
		    },
		    yAxis: {
		        splitLine: {
		            lineStyle: {
		                type: 'dashed'
		            }
		        },
		         axisLabel:{
		        	inside:true,
		        	margin:250
		        },
		        scale: true
		    },
		    series: [{
		        data: data,
		        type: 'scatter',
		        symbolSize: function (data) {
		            return Math.sqrt(data[2]) / 5e2;
		        },
		        label: {
		            emphasis: {
		                show: true,
		                formatter: function (param) {
		                    return param.data[3];
		                },
		                position: 'top'
		            }
		        },
		        itemStyle: {
		            normal: {
		                shadowBlur: 10,
		                shadowColor: 'rgba(120, 36, 50, 0.5)',
		                shadowOffsetY: 5,
		                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
		                    offset: 0,
		                    color: 'rgb(251, 118, 123)'
		                }, {
		                    offset: 1,
		                    color: 'rgb(204, 46, 72)'
		                }])
		            }
		        }
		    }]
		};
		
		myChart.setOption(option);
 	}
</script>