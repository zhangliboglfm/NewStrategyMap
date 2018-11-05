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

<title>套餐收入趋势</title>

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
	stroke: #B4B7B9;
	fill-opacity:0.7;
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
	var yheight=height*0.5;
	// Various scales. These domains make assumptions of data, naturally.
	var xScale = d3.scale.log().domain([10, 800]).range([0, width]);
	var yScale = d3.scale.linear().domain([-150, 150]).range([yheight, -yheight]);
	var radiusScale = d3.scale.sqrt().domain([0, 300]).range([0, 30]);
	var timeScale = d3.time.scale().domain([new Date(dateMinstr), new Date(dateMaxstr)]).range([1, days]);
	var colorScale = d3.scale.category20();
	// The x & y axes.
	var xAxis = d3.svg.axis().orient("bottom").scale(xScale).ticks(5, d3.format(",d"));
	var yAxis = d3.svg.axis().scale(yScale).orient("left").ticks(10, d3.format(",d"));
	
	// Create the SVG container and set the origin.
	var svg = d3.select("#chart")
		.append("svg")
	    .attr("width", width + margin.left + margin.right)
	    .attr("height", height + margin.top + margin.bottom)
	  	.append("g")
	    .attr("transform", "translate(" + margin.left + "," + (yheight+25) + ")")
	    .attr("class", "gRoot");
	    
	// Add the x-axis.
	svg.append("g")
	    .attr("class", "x axis")
	    .attr("transform", "translate(0,0)")
	    .call(xAxis);
	    
	// Add the y-axis.
	svg.append("g")
	    .attr("class", "y axis")
	    .attr("transform", "translate(0,0)")
	    .call(yAxis);
	    
	// Add an x-axis label.
	svg.append("text")
	    .attr("class", "x label")
	    .attr("text-anchor", "end")
	    .attr("x", width)
	    .attr("y", -10)
	    .text("户均流量(M)");
	// Add a y-axis	 label.
	var ytext = svg.append("text")
	    .attr("class", "y label")
	    .attr("text-anchor", "end")
	    .attr("transform", "rotate(-90)")
	    .attr("x", yheight)
	    .attr("y", 10)
	    .attr("dy","0.95em")
	    .text("累计收入(万元)");
	/* ytext.selectAll("tspan")
		.data(["累","计","收","入"])
		.enter()
		.append("tspan")
		.attr("x",ytext.attr("x"))
		.attr("dy","0.95em")
		.text(function(d){
			return d;
		});   */
	// Add the year label; the value is set on transition.
	var label = svg.append("text")
	    .attr("class", "year label")
	    .attr("text-anchor", "end")
	    .attr("y", yheight)
	    .attr("x", width)
	    .text(dateMin);
	    
	// Add the country label; the value is set on transition.
	var countrylabel = svg.append("text")
	    .attr("class", "country label")
	    .attr("text-anchor", "start")
	    .attr("y",-yheight+20)
	    .attr("x", 30)
	    .style("fill","#BCC4CE")
	    .style("fill-opacity","0.5")
	    .text("");
	    
	var first_time = true;
	
	// Load the data.
	d3.json("getpackqipaodata4.do", function(nations){
	  // A bisector since many nation's data is sparsely-defined.
	  var bisect = d3.bisector(function(d) { return d[0]; });
	  // Add a dot per nation. Initialize the data at 1800, and set the colors.
	  var dot = svg.append("g")
	  		.attr("class", "dots")
	  		.selectAll(".dot")
	  		.data(interpolateData(1))
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
	      		  var unit = "万";
	      		  /* if(d.lifeExpectancy*1000>10000000){
	      		  	unit="千万";
	      		  } */
		          countrylabel.text(d.name+"-累计收入:"+(d.lifeExpectancy)+unit);
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
	  var dotlabel = svg.append("g")
	  			.attr("class", "dotlabels")
				.selectAll(".dot")
				.data(interpolateData(1))
				.enter().append("text")
				.attr("class", "dotlabel")
				.call(labelinit);
	  var dottext = svg.append("g")
	  		.attr("class", "dottext")
	  		.selectAll(".dottext")
	  		.data(interpolateData(1))
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
	       		if(Math.abs(y(d))>=120){
	       			if(y(d)>0){
		       			return yScale(120);
	       			}else{
		       			return yScale(-120);
	       			}
	       		}else{
	       			return yScale(y(d));
	       		}
	      	})
	       .attr("r", function(d){
	       		return radiusScale(radius(d)); 
	      	});
	  }
	  function labelinit(label) {
	    label.attr("x", function(d){
	    		return xScale(x(d)+10); 
	    	})
	       .attr("y", function(d){ 
	      		if(Math.abs(y(d))>=120){
	       			if(y(d)>0){
		       			return yScale(120);
	       			}else{
		       			return yScale(-120);
	       			}
	       		}else{
	       			return yScale(y(d));
	       		}
	      	})
	      	.text( function(d){ 
	      		console.log(radius(d));
	      		return Math.floor(radius(d));
	      	});
	  }
	  // Defines a sort order so that the smallest dots are drawn on top.
	  function order(a, b) {
	    return radius(b) - radius(a);
	  }
	  
	// After the transition finishes, you can mouseover to change the year.//需要修改
  function enableInteraction() {
    var yearScale = d3.scale.linear()
        .domain([1, days])
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
  		//需要修改
	  function tweenYear() {
	    var year = d3.interpolateNumber(1, days);
	    return function(t) { displayYear(year(t)); };
	  }
	  // Updates the display to show the specified year.
	    function displayYear(year) {
		    dot.data(interpolateData(year), key).call(position).sort(order);
		    dotlabel.data(interpolateData(year), key).call(labelinit);
		    var curdate = new Date(timeScale.invert(Math.round(year)));
		    label.text(FormateDate(curdate));
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
	});
	function main(date){
	}
</script>
</html>
