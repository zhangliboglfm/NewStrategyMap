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

<title>业务办理平均耗时</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

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
	body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0;text-align: center ;position:relative;}
	.charts{
		margin:0 auto;
	}
	.leftpart{
		width: 50%;
		height: 100%;
		float: left;
	}
	
	.rightpart{
		width: 50%;
		height: 100%;
		float: left;
		color: white;
	
	}
	
	.xaxis .domain{
		fill:rgba(0,0,0,0);
	}
	.xaxis text{
		fill:#08C3CA;
		font-size:15px;
	}
	
	.yaxis text{
		fill:#08C3CA;
		font-size:15px;
	}
	.yaxis path, .yaxis line {
  		fill:none;
  		stroke: #08C3CA; 
  		/* shape-rendering: crispEdges */;
	}
	.updatebutton{
		top: 20 !important;
		left: 30 !important;
		position: absolute;
	}
</style>



<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>

<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript" src="js/echarts-zlb.js"></script>
<script type="text/javascript" src="js/d3.min.js"></script>
</head>

<body >
	<button class="updatebutton" onclick="update()">update</button>
	<div  class="charts">
		<div id="leftpart" class="leftpart"></div>
		<div id="rightpart" class="rightpart">
			
		</div>
	</div>
</body>
</html>

<script>
	var curdate="<%=request.getAttribute("curdate")%>";
	
	var domheight=$("#divIfream",window.parent.document).height()-10;
	$(".charts").css({"width":1366,"height":domheight});
	
	/*设置坐标轴的数据*/
	var datatext=["石家庄","秦皇岛","张家口","廊坊","唐山","衡水","保定","承德","邢台","邯郸","沧州"];
	
	var dataset = [ 11, 12, 15, 20, 18, 17, 16, 18, 23, 25, 8];
	var dataset2 = [ {"x":9,"y":4}, {"x":6,"y":3},{"x":10,"y":2}, {"x":16,"y":4}, {"x":15,"y":5}, {"x":10,"y":2}, {"x":9,"y":3}, {"x":8,"y":4}, {"x":18,"y":12}, {"x":15,"y":6}, {"x":4,"y":2}];
	var dataset3 = [ 5, 3, 8, 12, 10, 8, 6, 4, 6, 9, 2];
	/*设置填充*/
	var margin = {top: 20, right: 20, bottom: 30, left: 60};
	var width = 683 - margin.left - margin.right;
    var height = domheight - margin.top - margin.bottom;
    /*均分x轴坐标*/
	var xrange = d3.scale.ordinal()
    	.domain(d3.range(datatext.length))
    	.rangeBands([0, height], 0.6);
	var xshuzhi = d3.scale.ordinal()
    	.domain(datatext)
    	.rangeBands([0, height],0.6);
    	
    	
    /*设置y坐标轴数据*/
	var yscaler = d3.scale.linear()
    	.domain([0,25])
    	.range([0,width-margin.right]);
    
    var xAxis = d3.svg.axis()
    	.scale(xshuzhi)
    	.orient("left");
    var yAxis = d3.svg.axis()
    	.scale(yscaler)
    	.orient("bottom");
    
	$(function(){
		d3initLeft(); 
	});
	
	function d3initLeft(){
		svg = d3.select(".leftpart").append("svg")
    		.attr("width", width + margin.left)
    		.attr("height", height + margin.top)
  			.append("g")
    		.attr("transform", "translate(" + margin.left + ",0)");
		/*请求json数据*/
		d3.json("",function(){
			/*第一个条形图*/
			svg.selectAll(".stoptime")// 插入的不是circle了，改为rect
    			.data(dataset)
    			.enter()
    			.append("rect")
    			.attr("class","stoptime")
    			.attr("y", function(d,i) {
        			return xrange(i);
    			})
    			.attr("x", function(d) {
        			return 0;
    			})
    			.attr("height", xrange.rangeBand())
    			.attr("width", function(d) {
       		 		return  yscaler(d);
    			})
    			.attr("fill","#6EB6FF");
   				
   				/*第一个条形图的文字*/
   				svg.selectAll(".stoptimetext")// 插入的不是circle了，改为rect
    			.data(dataset)
    			.enter()
    			.append("text")
    			.attr("class","stoptimetext")
    			.attr("y",function(d,i) {
        			return xrange(i)+0.75*xrange.rangeBand();
    			})
    			.attr("x", function(d) {
       		 		return  yscaler(d)-15;
    			})
    			.text(function(d){
    				return d;
    			});
   				
   				
   				/*第二个条形图*/
   			svg.selectAll(".waittime")// 插入的不是circle了，改为rect
    			.data(dataset2)
    			.enter()
    			.append("rect")
    			.attr("class","waittime")
    			.attr("y", function(d,i) {
        			return xrange(i)+xrange.rangeBand()*0.25;
    			})
    			.attr("x", function(d) {
        			return 0;
    			})
    			.attr("height", xrange.rangeBand()*0.5)
    			.attr("width", function(d) {
       		 		return  yscaler(d.x);
    			})
    			.attr("fill","#78FF24");
   				
   			/*第二个条形图文字*/
   			svg.selectAll(".waittimetext")// 插入的不是circle了，改为rect
    			.data(dataset2)
    			.enter()
    			.append("text")
    			.attr("class","waittimetext")
    			.attr("y",function(d,i) {
        			return xrange(i)+0.75*xrange.rangeBand();
    			})
    			.attr("x", function(d) {
       		 		return  yscaler(d.x)-15;
    			})
    			.text(function(d){
    				return d.y;
    			});
   			/*第三个条形图*/
   			svg.selectAll(".accetime")// 插入的不是circle了，改为rect
    			.data(dataset3)
    			.enter()
    			.append("rect")
    			.attr("class","accetime")
    			.attr("y", function(d,i) {
        			return xrange(i)+xrange.rangeBand()*0.25;
    			})
    			.attr("x", function(d) {
        			return 0;
    			})
    			.attr("height", xrange.rangeBand()*0.5)
    			.attr("width", function(d) {
       		 		return  yscaler(d);
    			})
    			.attr("fill", "#FF0014");
   			/*第三个条形图文字*/
   			svg.selectAll(".accetimetext")// 插入的不是circle了，改为rect
    			.data(dataset3)
    			.enter()
    			.append("text")
    			.attr("class","accetimetext")
    			.attr("y",function(d,i) {
        			return xrange(i)+0.75*xrange.rangeBand();
    			})
    			.attr("x", function(d) {
       		 		return  yscaler(d)-15;
    			})
    			.text(function(d){
    				return d;
    			});
   
			svg.append("g")
      			.attr("class", "xaxis")
      			.call(xAxis);

  			 svg.append("g")
      			.attr("class", "yaxis")
      			.attr("transform", "translate(0," + (height) + ")")
      			.call(yAxis)
      			 
		})
	
	}
	
	function update(){
		var data = [ 16, 18, 20, 16, 15, 19, 20, 15, 19, 20, 16];
		var data2 = [ {"x":11,"y":2}, {"x":15,"y":5},{"x":18,"y":8}, {"x":12,"y":6}, {"x":10,"y":5}, {"x":16,"y":10}, {"x":15,"y":5}, {"x":10,"y":5}, {"x":15,"y":6}, {"x":16,"y":10}, {"x":10,"y":6}];
		var data3 = [ 9, 10, 10, 6, 5, 6, 10, 5, 9, 6, 4];
		
		/*第一个条形图*/
			svg.selectAll(".stoptime")// 插入的不是circle了，改为rect
    			.data(data)
    			.transition()
    			.duration(2000) 
    			.attr("width", function(d) {
       		 		return  yscaler(d);
    			});
   				
   				/*第一个条形图的文字*/
   				svg.selectAll(".stoptimetext")// 插入的不是circle了，改为rect
    			.data(data)
    			.transition()
    			.duration(2000) 
    			.attr("x", function(d) {
       		 		return  yscaler(d)-15;
    			})
    			.text(function(d){
    				return d;
    			});
   				
   				
   				/*第二个条形图*/
   			svg.selectAll(".waittime")// 插入的不是circle了，改为rect
    			.data(data2)
    			.transition()
    			.duration(2000) 
    			.attr("width", function(d) {
       		 		return  yscaler(d.x);
    			})
   				
   			/*第二个条形图文字*/
   			svg.selectAll(".waittimetext")// 插入的不是circle了，改为rect
    			.data(data2)
    			.transition()
    			.duration(2000) 
    			.attr("x", function(d) {
       		 		return  yscaler(d.x)-15;
    			})
    			.text(function(d){
    				return d.y;
    			});
   			/*第三个条形图*/
   			svg.selectAll(".accetime")// 插入的不是circle了，改为rect
    			.data(data3)
    			.transition()
    			.duration(2000) 
    			.attr("width", function(d) {
       		 		return  yscaler(d);
    			})
   			/*第三个条形图文字*/
   			svg.selectAll(".accetimetext")// 插入的不是circle了，改为rect
    			.data(data3)
    			.transition()
    			.duration(2000)
    			.attr("x", function(d) {
       		 		return  yscaler(d)-15;
    			})
    			.text(function(d){
    				return d;
    			});
	
	}
	
	
	
</script>
