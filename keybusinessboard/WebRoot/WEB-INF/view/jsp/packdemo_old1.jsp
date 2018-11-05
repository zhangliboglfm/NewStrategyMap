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
body {width: 100%;height: 100%;margin: 0;padding: 0;}
.main{width: 100%;height: 100%;}
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
	var mychart;
	var option;
	/* var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
			        '7a', '8a', '9a','10a','11a',
			        '12p', '1p', '2p', '3p', '4p', '5p',
			        '6p', '7p', '8p', '9p', '10p', '11p'];
	var days = ['Saturday', 'Friday', 'Thursday',
	        'Wednesday', 'Tuesday', 'Monday', 'Sunday']; */
	
	//var curdata = [[0,0,5],[0,1,1],[0,2,0],[0,3,0],[0,4,0],[0,5,0],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],[1,12,2],[1,13,6],[1,14,9],[1,15,11],[1,16,6],[1,17,7],[1,18,8],[1,19,12],[1,20,5],[1,21,5],[1,22,7],[1,23,2],[2,0,1],[2,1,1],[2,2,0],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,0],[2,10,3],[2,11,2],[2,12,1],[2,13,9],[2,14,8],[2,15,10],[2,16,6],[2,17,5],[2,18,5],[2,19,5],[2,20,7],[2,21,4],[2,22,2],[2,23,4],[3,0,7],[3,1,3],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,1],[3,9,0],[3,10,5],[3,11,4],[3,12,7],[3,13,14],[3,14,13],[3,15,12],[3,16,9],[3,17,5],[3,18,5],[3,19,10],[3,20,6],[3,21,4],[3,22,4],[3,23,1],[4,0,1],[4,1,3],[4,2,0],[4,3,0],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,2],[4,10,4],[4,11,4],[4,12,2],[4,13,4],[4,14,4],[4,15,14],[4,16,12],[4,17,1],[4,18,8],[4,19,5],[4,20,3],[4,21,7],[4,22,3],[4,23,0],[5,0,2],[5,1,1],[5,2,0],[5,3,3],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,2],[5,9,0],[5,10,4],[5,11,1],[5,12,5],[5,13,10],[5,14,5],[5,15,7],[5,16,11],[5,17,6],[5,18,0],[5,19,5],[5,20,3],[5,21,4],[5,22,2],[5,23,0],[6,0,1],[6,1,0],[6,2,0],[6,3,0],[6,4,0],[6,5,0],[6,6,0],[6,7,0],[6,8,0],[6,9,0],[6,10,1],[6,11,0],[6,12,2],[6,13,1],[6,14,3],[6,15,4],[6,16,0],[6,17,0],[6,18,0],[6,19,0],[6,20,1],[6,21,2],[6,22,2],[6,23,6]];
	$(function(){
		getPackData();
		//initOnePackSandianChart("divPackChart",curdata);
	});
	/* 获取套餐数据 */
	function getPackData(){
		$.ajax({
	  		url:"getpackdemodata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate,linetype:"Online"},
	  		success:function(data){
		  		initPackSandianChart("divPackChart",data);
	  		}
	  	});
	}
	function changePackData(){
		$.ajax({
	  		url:"getpackdemodata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate,linetype:"Online"},
	  		success:function(data){
		  		changePackSandianChart(data);
	  		}
	  	});
	}
	function initPackSandianChart(domid,ajaxdata){
		mychart = echarts.init(document.getElementById(domid));
		var hours = ajaxdata.dataX;
		var days = ajaxdata.dataZ;
		var data = ajaxdata.dataY;
		data = data.map(function (item) {
		    return [item[1], item[0], item[2]];
		});
		option = {
		    tooltip: {
		        position: 'top',
		        formatter: function (params) {
		            return params.value[2] + ':' + hours[params.value[0]] + ':' + days[params.value[1]];
		        }
		    },
		    grid: {
		        left: 2,
		        bottom: 10,
		        right: 10,
		        containLabel: true
		    },
		    xAxis: {
		        type: 'category',
		        data: hours,
		        boundaryGap: false,
		        position:"top",
		        splitLine: {
		            show: true,
		            lineStyle: {
		                color: '#fff',
		                type: 'dashed'
		            }
		        },
		        axisLine: {
		            show: false
		        },
		        axisLabel:{
		        	textStyle:{
		        		color:"#fff"
		        	}
		        },
		        axisTick:{
		        	show:false
		        }
		    },
		    yAxis: {
		        type: 'category',
		        data: days,
		        axisLine: {
		            show: false
		        },
		        axisLabel:{
		        	textStyle:{
		        		color:"#fff"
		        	}
		        }
		    },
		    series: [{
		        name: '占比',
		        type: 'scatter',
		        symbolSize: function (val) {
		            return val[2];
		        },
		        data: data,
		        animationDelay: function (idx) {
		            return idx * 5;
		        }
		    }]
		};
		mychart.setOption(option);
	}
	function changePackSandianChart(ajaxdata){
		
		var hours = ajaxdata.dataX;
		var days = ajaxdata.dataZ;
		var data = ajaxdata.dataY;
		console.log(days);
		option = {
		    tooltip: {
		        position: 'top',
		        formatter: function (params) {
		            return params.value[2] + ':' + hours[params.value[0]] + ':' + days[params.value[1]];
		        }
		    },
		    xAxis: {
		        data: hours,
		    },
		    yAxis: {
		        data: days,
		    },
		    series: [{
		        data: data,
		    }]
		};
		mychart.setOption(option);
	}
	function main(date){
		curdate=date;
		changePackData();
	}
</script>
</html>
