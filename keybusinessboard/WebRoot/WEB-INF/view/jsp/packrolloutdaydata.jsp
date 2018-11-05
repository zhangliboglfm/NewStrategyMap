<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!-- <html class="ocks-org do-not-copy"></html> -->
<head>
<!-- <link rel="stylesheet" href="css/d3Array.css" type="text/css"></link> -->
	<script type="text/javascript" src="js/d3.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
</head>
<meta charset="utf-8">
<title>转入转出套餐矩阵图(当天值)</title>
<style>

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

body{
	text-align: center;
}
svg {
  font: 10px sans-serif;
}
.left{
	float: left;
	margin-left: 250px;
}
/*设置悬浮框样式*/
.pop_titlediv{position: absolute;display: none;z-index: 999999 !important;}
.divhover{
	min-width:300px;
	height:32px;
	background: #13203F;
	border:1px solid #0B9AA8;
	border-top-right-radius:1em;
	border-bottom-left-radius:1em;
	border-bottom-right-radius:1em;
}
.divhover-title{
	background:#0B9AA8;
	color:white;
	font-size:15px;
	line-height:30px;
	height: 30px;
	width:120px;
	border-top-left-radius:0.5em;
	border-top-right-radius:0.5em;
}

.divhover-hang{
	min-width:300px;
	height:30px;
	padding-right: 10px;
}
.divhover-shuju{
	height: 32px;
	border:1px solid #0B9AA8;
	border-top-right-radius:1em;
	border-bottom-left-radius:1em;
	border-bottom-right-radius:1em;
}
.divhover-shuju1{
	margin-left:5px;
	font-size:13px;
	height: 30px;
	line-height:30px;
	float: left;
	color:white;
}
.divhover-shuju2{
	font-size:13px;
	margin-left:5px;
	height: 30px;
	line-height:30px;
	float: left;
	color:white;
}
.divhover-shuju3{
	font-size:20px;
	margin-left:5px;
	height: 30px;
	line-height:30px;
	float: left;
	font-weight:bold;
	color:red;
}
.divhover-shuju4{
	margin-left:5px;
	margin-top:7px;
	height: 30px;
	float: left;
	color:#00FFFF;
}
.win{
	margin: 0 auto;
}
.divsvg{
	padding:0;
	float:left;
	margin-left: 140px;
}
.right{
	float: left;
	margin-left:50px;
	position: relative;
}
.row{
	stroke:rgba(13,138,152,0.5);
	
}
.column{
	stroke:rgba(13,138,152,0.5);
}  

 /*最底下图例布局*/
.legend{position:absolute;left:0px;bottom:0px;padding:0;float:left;width:450px;height:160px;}
.tuli{width:150px;height:20px;color: white;float:left;margin-top: 12px;}
.fanwei{float: left;height: 20px; line-height:20px;font-size: 13px;}
.two{float: left;height: 20px; line-height:20px;color:gray;display:none;}
/*设置图例大小以及颜色*/
.one{float:left;width:20px;height: 20px;margin-right:5px; border:1px solid #08C3CA}
.color1{background: #6EB6FF;}
.color2{background: #4EBDFF;}
.color3{background: #29BEFF;}
.color4{background: #19C5EF;}
.color5{background: #0DD4E6;}
.color6{background: #09DCD5;}
.color7{background: #0BEFC2;}
.color8{background: #27FF90;}
.color9{background: #5AFF48;}
.color10{background: #98FF00;}
.color11{background: #FFF000;}
.color12{background: #ED6300;}
.color13{background: #FF0014;}
</style>
<body>

	
	<div class="win">
		<div class="divsvg">
			<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
				 <defs>
    				<radialGradient id="grad1" cx="50%" cy="50%" r="50%" fx="50%" fy="50%">
      					<stop  offset="0%" stop-color="#98FF00" stop-opacity="1" />
      					<stop  offset="80%" stop-color=#98FF00 stop-opacity="1" />
      					<stop  offset="80%" stop-color=#98FF00 stop-opacity="1" />
      					<stop  offset="100%" stop-color="#98FF00" stop-opacity="1" />
    				</radialGradient>
  				</defs>
  				<defs>
    				<radialGradient id="grad2" cx="50%" cy="50%" r="50%" fx="50%" fy="50%">
      					<stop offset="0%" stop-color="#FFF000" stop-opacity="1" />
      					<stop offset="80%" stop-color="#FFF000" stop-opacity="1" />
      					<stop offset="100%" stop-color="#FFF000" stop-opacity="1" />
    				</radialGradient>
  				</defs>
  				<defs>
    				<radialGradient id="grad3" cx="50%" cy="50%" r="50%" fx="50%" fy="50%">
      					<stop offset="0%" stop-color="#ED6300" stop-opacity="1" />
      					<stop offset="80%" stop-color="#ED6300" stop-opacity="1" />
      					<stop offset="100%" stop-color="#ED6300" stop-opacity="1" />
    				</radialGradient>
  				</defs>
  				<defs>
    				<radialGradient id="grad4" cx="50%" cy="50%" r="50%" fx="50%" fy="50%">
      					<stop offset="0%" stop-color="#FF0014" stop-opacity="1" />
      					<stop offset="80%" stop-color="#FF0014" stop-opacity="1" />
      					<stop offset="100%" stop-color="#FF0014" stop-opacity="1" />
    				</radialGradient>
  				</defs>
			</svg>
		</div>
		<div id="popDivTitle" class="pop_titlediv"></div>
		<div class="right">
			<!-- <div class="legend">
				<div class="tuli"><div class="one color1" rank="COL_LVL1" ></div><div id="COL_LVL1" num="0" class="fanwei">(0~500)</div></div>
				<div class="tuli"><div class="one color2" rank="COL_LVL2" ></div><div id="COL_LVL2" num="1" class="fanwei">[500~1,000)</div></div>
				<div class="tuli"><div class="one color3" rank="COL_LVL3" ></div><div id="COL_LVL3" num="2" class="fanwei">[1,000~2,000)</div></div>
				<div class="tuli"><div class="one color4" rank="COL_LVL4" ></div><div id="COL_LVL4" num="3" class="fanwei">[2,000~3,000)</div></div>
				<div class="tuli"><div class="one color5" rank="COL_LVL5" ></div><div id="COL_LVL5" num="4" class="fanwei">[3,000~4,000)</div></div>
				<div class="tuli"><div class="one color6" rank="COL_LVL6" ></div><div id="COL_LVL6" num="5" class="fanwei">[4,000~5,000)</div></div>
				<div class="tuli"><div class="one color7" rank="COL_LVL7" ></div><div id="COL_LVL7" num="6" class="fanwei">[5,000~7,500)</div></div>
				<div class="tuli"><div class="one color8" rank="COL_LVL8" ></div><div id="COL_LVL8" num="7" class="fanwei">[7,500~10,000)</div></div>
				<div class="tuli"><div class="one color9" rank="COL_LVL9" ></div><div id="COL_LVL9" num="8" class="fanwei">[10,000~25,000)</div></div>
				<div class="tuli"><div class="one color10" rank="COL_LVL10" ></div><div id="COL_LVL10" num="9" class="fanwei">[25,000~50,000)</div></div>
				<div class="tuli"><div class="one color11" rank="COL_LVL11" ></div><div id="COL_LVL11" num="10" class="fanwei">[50,000~75,000)</div></div>
				<div class="tuli"><div class="one color12" rank="COL_LVL12" ></div><div id="COL_LVL12" num="11" class="fanwei">[75,000~1,000,000)</div></div>
				<div class="tuli"><div class="one color13" rank="COL_LVL13" ></div><div id="COL_LVL13" num="12" class="fanwei">[1,00,000~+∞)</div></div>
			</div> -->
		</div>
	</div>
</body>
<script>

var curdate="<%=request.getAttribute("curdate")%>";
var domheight=$("#divIfream",window.parent.document).height()-20;
	domheight=(domheight>683 ? 683:domheight);
var domwidth=domheight;
var gekuandu=domwidth/84;
var width=domheight;
var	height=domheight;
var lineshuzhu=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43];

var time1;
var time2;
var x = d3.scale.linear().domain([0,41]).range([0, width-gekuandu*2]);		//传入一个输入域中的值 x，返回对应输出范围中的值。
var z = d3.scale.linear().domain([0, 100000]).range([0.1,10]);	//domain 取得或设置比例尺定义域   clamp()启用或关闭值域的闭合
var colors=["#6EB6FF","#4EBDFF","#29BEFF","#19C5EF","#0DD4E6","#09DCD5","#0BEFC2","#27FF90","#5AFF48","#98FF00","#FFF000","#ED6300","#FF0014"];
/*设置绑定的数据*/
var svg;
$(function(){
	$(".win").css({"width":1366,"height":domheight});
	$(".divsvg").css({"width":domwidth,"height":domheight});
	$(".right").css({"width":1366-domwidth-190,"height":domwidth});
	d3array(curdate);
})

 function main(date1){
	d3array1(date1);
}

/*对数据进行重新封装*/

 function d3array(date1){
	svg = d3.select("svg")
	.attr("class","svgcolor")
    .attr("width", width)
    .attr("height", width)
    .append("g")
    .attr("transform", "translate(0,0)");
	d3.json("daydatamiserables.do?date="+date1, function(miserables) {
			row = svg.selectAll(".row")
      		.data(lineshuzhu)
      		.enter().append("g")
      		.attr("class", "row")
      		.attr("transform", function(d, i) { 
     	 		return "translate(0," + x(i) + ")"; 
      		}).append("line").attr("x2", width);

		 	column = svg.selectAll(".column")
      		.data(lineshuzhu)
      		.enter().append("g")
      		.attr("class", "column")
      		.attr("transform", function(d, i) { return "translate(" + x(i) + ")rotate(-90)"; }).append("line")
      		.attr("x1", -width); 
  		
		svg.selectAll("circle")// 插入的不是circle了，改为rect
    	.data(miserables.links)
    	.enter()
    	.append("circle")
    	.attr("class", function(d){
    				if(d.value>50000){
    					return "cell top5";
    				}
    				return "cell";
    	})
    	.attr("cx", function(d){ return x(d.source)+gekuandu})
   	 	.attr("cy", function(d){ return x(d.target)+gekuandu })
   	 	.attr("r",0)
   	 	.transition()
        .duration(1000)
    	.attr("r",function(d){
		    				if(z(d.value)>6){
    							return 6;
    						}
		    				return z(d.value)})
        	.attr("fill", function(d){
        						return colors[d.rank];
        					});
        						
         svg.selectAll(".top5").each(function(e,i){
        	svg
        	.data([{"source":e.source,"target":e.target,"value":e.value,"name1":e.name1,"name2":e.name2}])
        	.append("circle")
        	.attr("class","ropremove")
        	.attr("cx",  x(e.source)+gekuandu)
   	 		.attr("cy",  x(e.target)+gekuandu )
   	 		.attr("stroke", colors[e.rank])
   	 		.attr("fill","rgba(0,0,0,0)")
   	 		.attr("r","0")
   	 		.transition()
        	.duration(1500)
   	 		.attr("r","10");
        })
        svg.selectAll(".ropremove").on("mouseover", mouseover).on("mouseout", mouseout);; 					
    	svg.selectAll(".cell").on("mouseover", mouseover).on("mouseout", mouseout);
    	time1=setInterval(function(){
    		svg.selectAll(".ropremove").transition()
        	.duration(1500)
   	 		.attr("r","7");
    	},3000)
    	
    	var t=setTimeout(function(){
    		time2=setInterval(function(){
    		svg.selectAll(".ropremove").transition()
        	.duration(1500)
   	 		.attr("r","10");
    		},3000)
    	},1500)
 	});
}	
	
	function d3array1(date1){
		d3.json("daydatamiserables.do?date="+date1, function(miserables) {
			clearInterval(time1);
			clearInterval(time2);
			svg.selectAll(".ropremove").remove();
     		var datalink=miserables.links;
			svg.selectAll("circle")
		    .data(datalink)
		    .attr("class", function(d){
    				if(d.value>50000){
    					return "cell top5";
    				}
    				return "cell";
    		})
   	 		.transition()
        	.duration(1000)
		    .attr("r",function(d){
		    				if(z(d.value)>6){
    							return 6;
    						}
		    				return z(d.value)})
        	.attr("fill", function(d){
        						return colors[d.rank];
        					});
        	
        	svg.selectAll(".top5").each(function(e,i){
        	svg.data([{"source":e.source,"target":e.target,"value":e.value,"name1":e.name1,"name2":e.name2}])
        	.append("circle")
        	.attr("class","ropremove")
        	.attr("cx",  x(e.source)+gekuandu)
   	 		.attr("cy",  x(e.target)+gekuandu )
   	 		.attr("stroke", colors[e.rank])
   	 		.attr("fill","rgba(0,0,0,0)")
   	 		.attr("r","0")
   	 		.transition()
        	.duration(1500)
   	 		.attr("r","10");
        })
        time1=setInterval(function(){
    		svg.selectAll(".ropremove").transition()
        	.duration(1500)
   	 		.attr("r","7");
    	},3000)
    	
    	var t=setTimeout(function(){
    		time2=setInterval(function(){
    		svg.selectAll(".ropremove").transition()
        	.duration(1500)
   	 		.attr("r","10");
    		},3000)
    	},1500)
        svg.selectAll(".ropremove").on("mouseover", mouseover).on("mouseout", mouseout); 					
    	svg.selectAll(".cell").on("mouseover", mouseover).on("mouseout", mouseout);
		})
}
 
 function mouseover(p){
 		pageX=window.event.pageX;
 		pageY=window.event.pageY;
		if(pageY<100){
			$("#popDivTitle").css("top",pageY+5+"px");
			$("#popDivTitle").css("left",pageX+20+"px");
		}else{
			$("#popDivTitle").css("top",pageY-90+"px");
			$("#popDivTitle").css("left",pageX+20+"px");
		};
    	var str="";
			 str+="<div class='divhover-title'>转入转出情况</div>"+
			 		"<div class='divhover'>"+
						"<div class='divhover-shuju'>"+
							"<div class='divhover-hang'>"+
								"<div class='divhover-shuju1'>"+p.name1+"</div><div class='divhover-shuju4'><i class='iconfont'>&#xe637;</i></div><div class='divhover-shuju2'>"+p.name2+":</div><div class='divhover-shuju3'>"+(p.value==undefined ? p.z:p.value)+"</div>"+	
							"</div>"+
						"</div>"+
					"</div>";
		$("#popDivTitle").html(str);
		$("#popDivTitle").show();
 	 	}

function mouseout() {
		$("#popDivTitle").css("top","0px");
		$("#popDivTitle").css("left","0px");
		$("#popDivTitle").html("");
		$("#popDivTitle").hide();
  	} 

</script>
