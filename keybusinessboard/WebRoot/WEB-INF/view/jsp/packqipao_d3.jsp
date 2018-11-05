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

<title>套餐气泡图</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/dragit.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
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
	var packdata = [];
	var displayYear;
	var timeintervl;
	var curdate = 20170501;
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
	// Chart dimensions.
	var margin = {top: 19.5, right: 19.5, bottom: 19.5, left: 100};
	var width = 1260 - margin.right;
	var height = 600 - margin.top - margin.bottom;
	// Various scales. These domains make assumptions of data, naturally.
	var xScale = d3.scale.log().domain([10, 15000000]).range([0, width]);
	var yScale = d3.scale.log().domain([10, 15000000]).range([height, 0]);
	var radiusScale = d3.scale.sqrt().domain([0, 600000]).range([0, 100]);
	var colorScale = d3.scale.category20();
	// The x & y axes.
	var xAxis = d3.svg.axis().orient("bottom").scale(xScale).ticks(5, d3.format(",d"));
	var yAxis = d3.svg.axis().scale(yScale).orient("left").ticks(5, d3.format(",d"));
	
	// Create the SVG container and set the origin.
	var svg = d3.select("#chart")
		.append("svg")
	    .attr("width", width + margin.left + margin.right)
	    .attr("height", height + margin.top + margin.bottom)
	  	.append("g")
	    .attr("transform", "translate(" + margin.left + "," + margin.top + ")")
	    .attr("class", "gRoot");
	    
	// Add the x-axis.
	svg.append("g")
	    .attr("class", "x axis")
	    .attr("transform", "translate(0," + height + ")")
	    .call(xAxis);
	    
	// Add the y-axis.
	svg.append("g")
	    .attr("class", "y axis")
	    .call(yAxis);
	    
	// Add an x-axis label.
	svg.append("text")
	    .attr("class", "x label")
	    .attr("text-anchor", "end")
	    .attr("x", width)
	    .attr("y", height - 6)
	    .text("降档收入");
	// Add a y-axis label.
	var ytext = svg.append("text")
	    .attr("class", "y label")
	    .attr("text-anchor", "end")
	    .attr("x", 20)
	    .attr("y", 6);
	ytext.selectAll("tspan")
		.data(["升","档","收","入"])
		.enter()
		.append("tspan")
		.attr("x",ytext.attr("x"))
		.attr("dy","0.95em")
		.text(function(d){
			return d;
		});  
	    /* .attr("dy", "1em")
	    .attr("transform", "rotate(-90)")
	    .attr("rotate", "90 90 90 90 0 90 0")
	    .text("降档收入(元)"); */
	//var dd = line([[100,200],[300,400],[500,600]]);
	var myline = d3.svg.line()
		.x(function(d){return xScale(d.x);})
		.y(function(d){return yScale(d.y);})
		.interpolate("basis");
	var dd=[{"x":10,"y":10},{"x":100,"y":100},{"x":1000,"y":1000},{"x":10000,"y":10000},{"x":100000,"y":100000},{"x":1000000,"y":1000000},{"x":10000000,"y":10000000},{"x":15000000,"y":15000000}];
	d3.select("g")
		.append("path")
		.attr("class", "myline")
		.attr("d",myline(dd));
	// Add the year label; the value is set on transition.
	var label = svg.append("text")
	    .attr("class", "year label")
	    .attr("text-anchor", "end")
	    .attr("y", height - 24)
	    .attr("x", width)
	    .text(20170501);
	    
	// Add the country label; the value is set on transition.
	var countrylabel = svg.append("text")
	    .attr("class", "country label")
	    .attr("text-anchor", "start")
	    .attr("y", 40)
	    .attr("x", 30)
	    .style("fill","#BCC4CE")
	    .style("fill-opacity","0.5")
	    .text("");
	    
	var first_time = true;
	
	// Load the data.
	d3.json("getpackqipaodata3.do", function(nations){
	  // A bisector since many nation's data is sparsely-defined.
	  var bisect = d3.bisector(function(d) { return d[0]; });
	  // Add a dot per nation. Initialize the data at 1800, and set the colors.
	  var dot = svg.append("g")
	  		.attr("class", "dots")
	  		.selectAll(".dot")
	  		.data(interpolateData(20170501))
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
	  			/* dot.classed("selected", false);
	  			d3.select(this).classed("selected", !d3.select(this).classed("selected"));
	  			dragit.trajectory.display(d, i, "selected"); */
	  		})
	      	.on("mouseenter", function(d, i) {
	      		  //dragit.trajectory.display(d, i);
		          countrylabel.text(d.name);
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
	  var dottext = svg.append("g")
	  		.attr("class", "dottext")
	  		.selectAll(".dottext")
	  		.data(interpolateData(20170501))
	  		.enter().append("text")
	  // Add a title.
	  dot.append("title")
	      .text(function(d) { return d.name; });
	  // Add an overlay for the year label.
	  var box = label.node().getBBox();
	
	  var overlay = svg.append("rect")
	        .attr("class", "overlay")
	        .attr("x", box.x)
	        .attr("y", box.y)
	        .attr("width", box.width)
	        .attr("height", box.height)
	        .on("mouseover", enableInteraction);
	
	  // Start a transition that interpolates the data based on year.
	  svg.transition()
	      .duration(30000)
	      .ease("linear")
	      .tween("year", tweenYear)
	      .each("end", enableInteraction);
	      
	      
	  // Positions the dots based on data.
	  function position(dot) {
	    dot.attr("cx", function(d){
	    		return xScale(x(d)+10); 
	    	})
	       .attr("cy", function(d){ 
	      		return yScale(y(d)+10);
	      	})
	       .attr("r", function(d){
	       		return radiusScale(radius(d)/10); 
	      	});
	  }
	  // Defines a sort order so that the smallest dots are drawn on top.
	  function order(a, b) {
	    return radius(b) - radius(a);
	  }
	  
	    // After the transition finishes, you can mouseover to change the year.
  function enableInteraction() {
    var yearScale = d3.scale.linear()
        .domain([20170501, 20170531])
        .range([box.x + 10, box.x + box.width - 10])
        .clamp(true);

    // Cancel the current transition, if any.
    svg.transition().duration(0);

    overlay
        .on("mouseover", mouseover)
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
	  function tweenYear() {
	    var year = d3.interpolateNumber(20170501, 20170531);
	    return function(t) { displayYear(year(t)); };
	  }
	  // Updates the display to show the specified year.
	    function displayYear(year) {
		    dot.data(interpolateData(year), key).call(position).sort(order);
		    label.text(Math.round(year));
		  }
	  // Interpolates the dataset for the given (fractional) year.
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
	  // Finds (and possibly interpolates) the value for the specified year.
	  function interpolateValues(values, year) {
	    var i = bisect.left(values, year, 0, values.length - 1),
	        a = values[i];
	    if (i > 0) {
	      var b = values[i - 1],
	          t = (year - a[0]) / (b[0] - a[0]);
	      return a[1] * (1 - t) + b[1] * t;
	    }
	    return a[1];
	  }
	  
	  init();
	  function update(v, duration) {
	    dragit.time.current = v || dragit.time.current;
	    displayYear(dragit.time.current);
	    d3.select("#slider-time").property("value", dragit.time.current);
	  }
	  function init() {
	    dragit.init(".gRoot");
	    dragit.time = {min:20170501, max:20170531, step:1, current:20170501};
	    dragit.data = d3.range(nations.length).map(function() { return Array(); });
	    for(var yy = 20170501; yy<20170532; yy++) {
	      interpolateData(yy).filter(function(d, i) { 
	        dragit.data[i][yy-dragit.time.min] = [xScale(x(d)), yScale(y(d))];
	      });
	    }
	    dragit.evt.register("update", update);
	    //d3.select("#slider-time").property("value", dragit.time.current);
	    d3.select("#slider-time")
	      .on("mousemove", function() {
	        updateSVG(this);
	      });
	    var end_effect = function() {
	      countrylabel.text("");
	      dot.style("opacity", 1);
	    };
	    dragit.evt.register("dragend", end_effect);
	  }
	function clear_demo() {
	  if(first_time) {
	     svg.transition().duration(0);
	    first_time = false;
	    window.clearInterval(demo_interval);
	    countrylabel.text("");
	    dragit.trajectory.removeAll();
	    d3.selectAll(".dot").style("opacity", 1);
	  }
	}
	function updateSVG(e){
		update(parseInt(e.value), 500);
	    clear_demo();
	}
		var demo_interval = null;
	});
</script>
</html>
