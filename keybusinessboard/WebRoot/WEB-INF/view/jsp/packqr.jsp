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

<title>套餐累计趋势</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/dragit.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/miapsoft.date.js"></script>
<script type="text/javascript" src="js/d3.min.js"></script>
<script type="text/javascript" src="js/dragit.js"></script>


<style>
body {
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  margin-left:40px;
  font-weight: 200;
  font-size: 14px;
}
html,body {
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
text {
  cursor: default;
}
h1 {
  font-weight: 400;
}
#chart {
  height: 506px;
}
text {
  font-size: 10px;
  fill:#fff
}
.dot {
  stroke: #000;
}
.dotlabel{
	stroke: #fff;
	stroke-width: 0;
	font-size: 14px !important;
}
.y .axis{
	color: #fff !important;
}
.x.label,.y.label{
	font-size: 14px !important;
}
.axis path, .axis line {
  fill: none;
  stroke: #fff;
  shape-rendering: crispEdges;
  
}
.label {
  fill: #fff;
}
.year.label {
  font: 500 40px "Helvetica Neue";
  fill: #B4B7B9;
  fill-opacity:0.1
}
.country.label  {
  font: 500 30px "Helvetica Neue";
  fill: #ddd;
  fill-opacity:0.1
}
.year.label.active {
  fill: #aaa;
}
circle.pointTrajectory {
   pointer-events: none;
   stroke: lightgray;
   stroke-width:0;
   fill: black;
   opacity: 0;
}
path.lineTrajectory {
  stroke-width: 2;
  stroke-opacity: 0.5;
  stroke: #B4B7B9;
  fill: none;
  pointer-events: none;
}
.selected {
  stroke-width: 4;
}
.overlay {
  fill: none;
  pointer-events: all;
  cursor: ew-resize;
}
.myline{
	fill:none;
	stroke-width: 2;
	stroke: #B4B7B9;
	 stroke-opacity: 0.1;
}
</style>
</head>

<body>
	<div id="chart" style="margin:0px"></div>
	<!-- <span id="min-time" style="color:#fff">20170501</span> 
		<input type="range" name="points" min="0" max="30" step="1" value="0" id="slider-time" style="width:900px">
	<span id="max-time" style="color:#fff">20170531</span> -->
</body>
<script type="text/javascript">
	var dateMax="<%=request.getAttribute("maxdate")%>";
	var dateMin="<%=request.getAttribute("mindate")%>";
	var days="<%=request.getAttribute("days")%>";
	var curdate="<%=request.getAttribute("curdate")%>";
	dateMaxstr=dateMax.substring(0,4)+"-"+dateMax.substring(4,6)+"-"+dateMax.substring(6,8);
	dateMinstr=dateMin.substring(0,4)+"-"+dateMin.substring(4,6)+"-"+dateMin.substring(6,8);
	days=(days=="null"?1:parseInt(days)+1);
	var packdata = [];
	var displayYear;
	var timeintervl;
	var curdate = 20170501;
	/* 获取数据方法 d是气泡节点上绑定的数据 */
	function x(d){ 
		return d.income;
	}
	function y(d){
		return d.lifeExpectancy;
	}
	function radius(d){
		return d.population;
	}
	function color(d){
		return d.region;
	}
	function key(d){
		return d.name;
	}
	/* svg图表的布局整体设定 */
	var margin = {top: 19.5, right: 19.5, bottom: 19.5, left: 100};
	var width = 1260 - margin.right;
	var height = 600 - margin.top - margin.bottom;
	/** 生成图表的坐标轴计算函数 注意：这里返回的的并不是对象！而是一个方法，通过传递数值返回点的坐标位置，例如：timeScale(new Date("2017-05-01"))=1
	 *	D3坐标轴分为好几种，常用的分为：
	 *	线性轴 ：d3.scale.linear() 
	 *	对数轴：d3.scale.log() 
	 *	线性轴允许有负值和零值
	 *	对数轴不允许有负值和零值，但是区间划分不均，对于一些数据跨度较大的业务适用
	 */
	var xScale = d3.scale.linear().domain([0, 200]).range([0, width]);//domain()函数定义值区间，range()函数定义域区间
	var yScale = d3.scale.linear().domain([0, 100]).range([height, 0]);
	var radiusScale = d3.scale.sqrt().domain([0, 80000]).range([0, 50]);
	var timeScale = d3.time.scale().domain([new Date("2017-05"), new Date("2017-06")]).range([1, 2]);
	var colorScale = d3.scale.category20();
	/* 生成图表的坐标轴  ticks()函数控制刻度个数*/
	var xAxis = d3.svg.axis().orient("bottom").scale(xScale).ticks(5, d3.format(",d"));
	var yAxis = d3.svg.axis().scale(yScale).orient("left").ticks(5, d3.format(",d"));
	/* 生成svg画布 */
	var svg = d3.select("#chart")
		.append("svg")
	    .attr("width", width + margin.left + margin.right)
	    .attr("height", height + margin.top + margin.bottom)
	  	.append("g")
	    .attr("transform", "translate(" + margin.left + "," + margin.top + ")")
	    .attr("class", "gRoot");
	/* svg上创建X轴 */   
	svg.append("g")
	    .attr("class", "x axis")
	    .attr("transform", "translate(0," + height + ")")
	    .call(xAxis);
	/* svg上创建Y轴 */
	svg.append("g")
	    .attr("class", "y axis")
	    .call(yAxis);
	/* 生成X轴文字 */
	svg.append("text")
	    .attr("class", "x label")
	    .attr("text-anchor", "end")
	    .attr("x", width)
	    .attr("y", height - 6)
	    .text("消费");
	/* 生成Y轴文字 */
	var ytext = svg.append("text")
	    .attr("class", "y label")
	    .attr("text-anchor", "end")
	    .attr("x", 20)
	    .attr("y", 6);
	//使Y轴文字竖向展示
	ytext.selectAll("tspan")
		.data(["套","餐","内","占","比"])
		.enter()
		.append("tspan")
		.attr("x",ytext.attr("x"))
		.attr("dy","0.95em")
		.text(function(d){
			return d;
		});
	/* 生成收入对角线 */  
	var myline = d3.svg.line()
		.x(function(d){return xScale(d.x);})
		.y(function(d){return yScale(d.y);})
		.interpolate("basis");
	var dd=[{"x":0,"y":0},{"x":200,"y":100}];
	d3.select("g")
		.append("path")
		.attr("class", "myline")
		.attr("d",myline(dd));
	/* 生成右下角当前时间文字区域 */
	var label = svg.append("text")
	    .attr("class", "year label")
	    .attr("text-anchor", "end")
	    .attr("y", height - 24)
	    .attr("x", width)
	    .text("201705");
	/* 生成左上角气泡当前信息文字区域 */
	var countrylabel = svg.append("text")
	    .attr("class", "country label")
	    .attr("text-anchor", "start")
	    .attr("y", 40)
	    .attr("x", 30)
	    .style("fill","#BCC4CE")
	    .style("fill-opacity","0.5")
	    .text("");
	/* 加载数据 */	
	d3.json("getpackqrdata.do", function(nations){
	  	/* 定义数组插入函数 */
	  	var bisect = d3.bisector(function(d) { return d[0]; });
	  	/* 生成气泡 */
	  	var dot = svg.append("g")
	  		.attr("class", "dots")
	  		.selectAll(".dot")
	  		.data(interpolateData(1))//绑定数据
	  		.enter().append("circle")
	  		.attr("class", "dot")
	  		.style("fill", function(d){
	  			return colorScale(color(d));
	  		})
	  		.style("stroke-width","0")
	  		.call(position)
	  		.sort(order)
	  		.on("mousedow", function(d, i){})
	  		.on("mouseup", function(d, i){
	  		})
	      	.on("mouseenter", function(d, i) {
	      		  //dragit.trajectory.display(d, i);
		          countrylabel.text("用户："+Math.floor(d.population)+"人");
		          dot.style("opacity", .4);
		          d3.select(this).style("opacity", 1);
		          d3.select(this).style("stroke","#80e1f1");
		          d3.select(this).style("stroke-width","2");
		          d3.select(this).style("cursor","pointer");
		          d3.selectAll(".selected").style("opacity", 1);
	     	})
	      	.on("mouseleave", function(d, i) {
		        countrylabel.text("");
		        dot.style("opacity", 1);
		        dot.style("stroke-width","0");
		        dot.style("stroke","");
		        dot.style("cursor","default");
	        	//dragit.trajectory.remove(d, i);
	      	})
	      	.call(dragit.object.activate);
	    /* 生成气泡上的数据 */  	
	  	var dotlabel = svg.append("g")
	  		.attr("class", "dotlabels")
			.selectAll(".dot")
			.data(interpolateData(1))
			.enter().append("text")
			.attr("class", "dotlabel")
			.call(labelinit);
	  	/* 添加气泡title */
	  	dot.append("title")
	      	.text(function(d){return d.name;});
	  	/* 添加数据周期滑块  右下角时间文字的鼠标移动效果区域*/
	  	var box = label.node().getBBox();
	 	var overlay = svg.append("rect")
	        .attr("class", "overlay")
	        .attr("x", box.x)
	        .attr("y", box.y)
	        .attr("width", box.width)
	        .attr("height", box.height)
	        .on("mouseover", enableInteraction);
	  	/* 开启svg平滑过渡效果 */
	  	svg.transition()
	      	.duration(1000)
	      	.ease("linear")
	      	.tween("year", tweenYear)
	      	.each("end", enableInteraction);
	  	/* 确定气泡位置与大小的函数 */
	  	function position(dot) {
	    	dot.attr("cx", function(d){
	    		return xScale(x(d)); 
	    	})
	       .attr("cy", function(d){ 
	      		return yScale(y(d));
	      	})
	       .attr("r", function(d){
	       		return radiusScale(radius(d)/2); 
	      	});
	  	}
	  	/* 确定气泡数据位置与大小的函数 */
	  	function labelinit(label) {
	    	label.attr("x", function(d){
	    		return xScale(x(d)); 
	    	})
	       .attr("y", function(d){ 
	      		return yScale(y(d));
	      	})
	      	.text( function(d){ 
	      		return Math.floor(radius(d)/2);
	      	});
	  	}
	  	/* 动画所需函数 */
	  	function order(a, b) {
	    	return radius(b) - radius(a);
	  	}
	    /* 过渡动画结束后，数据周期滑块区域拖动鼠标触发的函数 */
  		function enableInteraction() {
    		var yearScale = d3.scale.linear()
	        	.domain([1, 2])
	        	.range([box.x + 10, box.x + box.width - 10])
	        	.clamp(true);
    		svg.transition().duration(0);
    		//绑定数据周期滑块区域鼠标各类事件
		    overlay.on("mouseover", mouseover)
		        .on("mouseout", mouseout)
		        .on("mousemove", mousemove)
		        .on("touchmove", mousemove);
		    function mouseover() {
		      label.classed("active", true);
		    }
		    function mouseout() {
		      label.classed("active", false);
		    }
		    function mousemove() {
		      displayYear(yearScale.invert(d3.mouse(this)[0]));
		    }
	  	}
	  	/* 动态变化的数据区间函数 */
	  	function tweenYear() {
	    	var year = d3.interpolateNumber(1, 2);
	    	return function(t) { displayYear(year(t)); };
	  	}
	  	/* 动态变化关键函数 */
	    function displayYear(year) {
		    dot.data(interpolateData(year), key).call(position).sort(order);
		    dotlabel.data(interpolateData(year), key).call(labelinit);
		    var curdate = new Date(timeScale.invert(Math.round(year)));
		    label.text(FormateDate(curdate).substring(0,6));
		}
	  	/* 根据数据时间取数据 */
	  	function interpolateData(year) {
	    	return nations.map(function(d) {
	      		return {
			        name: d.name,
			        region: d.region,
			        income: interpolateValues(d.income, year),
			        population: interpolateValues(d.population, year),
			        lifeExpectancy: interpolateValues(d.lifeExpectancy, year)
		      	};
	    	});
	  	}
	  	/* 从数据集合中获取是定时间的数据 */
	  	function interpolateValues(values, year) {
	    	var i = bisect.left(values, year, 0, values.length - 1),a = values[i];
	    	if (i > 0) {
		     	var b = values[i - 1],
		        t = (year - a[0]) / (b[0] - a[0]);
		      	return a[1] * (1 - t) + b[1] * t;
	    	}
	    		return a[1];
	  	}
	});
	/* 防止主页面调用报错 */
	function main(date){
	}
</script>
</html>
