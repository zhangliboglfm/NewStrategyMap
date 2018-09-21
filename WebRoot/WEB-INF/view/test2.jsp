<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>My JSP 'test1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
  <script type="text/javascript" src="js/raphael.js"></script>
  <script type="text/javascript" src="js/cycle.js"></script>
  <style type="text/css">
		#list {
			width:100%;
			height:60px;
			background-color: #00A3D7;
			position: absolute;
			 background: -webkit-linear-gradient(left, #00A3D7 , #00D3DD,#006CF3); /* Safari 5.1 - 6.0 */
			  background: -o-linear-gradient(right, #00A3D7, #00D3DD,#006CF3); /* Opera 11.1 - 12.0 */
			  background: -moz-linear-gradient(right, #00A3D7, #00D3DD,#006CF3); /* Firefox 3.6 - 15 */
			  background: linear-gradient(to right, #00A3D7, #00D3DD 30%,#006CF3); /* 标准的语法 */
filter:progid:DXImageTransform.Microsoft.Alpha(opacity=100,finishopacity=0,style=1,startx=0,starty=100,finishx=100,finishy=100);

		}
		#list2 {
			width:100%;
			height:60px;
			background-color: #00D3DD;
			position: absolute;
			 background: -webkit-linear-gradient(left, #00A3D7 , #00D3DD,#006CF3); /* Safari 5.1 - 6.0 */
			  background: -o-linear-gradient(right, #00A3D7, #00D3DD,#006CF3); /* Opera 11.1 - 12.0 */
			  background: -moz-linear-gradient(right, #00A3D7, #00D3DD,#006CF3); /* Firefox 3.6 - 15 */
			  background: linear-gradient(to right, #00A3D7, #00D3DD 30%,#006CF3); /* 标准的语法 */
			filter:progid:DXImageTransform.Microsoft.Alpha(opacity=0,finishopacity=100,style=1,startx=0,starty=100,finishx=100,finishy=100);
		}
		#list3 {
			width:100%;
			height:60px; 
			background-color: #006CF3;
			position: absolute;
			 background: -webkit-linear-gradient(left, #00A3D7 , #00D3DD,#006CF3); /* Safari 5.1 - 6.0 */
			  background: -o-linear-gradient(right, #00A3D7, #00D3DD,#006CF3); /* Opera 11.1 - 12.0 */
			  background: -moz-linear-gradient(right, #00A3D7, #00D3DD,#006CF3); /* Firefox 3.6 - 15 */
			 background: linear-gradient(to right, #00A3D7, #00D3DD 30%,#006CF3); /* 标准的语法 */
			filter:progid:DXImageTransform.Microsoft.Alpha(opacity=0,finishopacity=100,style=1,startx=30,starty=100,finishx=100,finishy=100);
		}
		.processingbar {
  text-align: center;
  margin: 14px 28px 0 0;
  position: relative;
  width: 100px;
}
.processingbar font {
  color: #dfb14f;
  display: block;
  width: 100px;
  height: 100px;
  line-height: 100px;
  font-size: 12px;
  text-align: center;
  position: absolute;
  left: 0;
  top: 0;
  margin: 0px 0 0 0px;
}
</style>
  </head>
  <body>
  	  <div id="list">
  	  </div>
  	  <div id="list2">
  	  </div>
  	  <div id="list3">
  	  </div>
  	  <div id="canvas"></div>
  	  <div class="processingbar"><font>10%</font></div>  
  	  <script type="text/javascript">
  	  	var a = "<%=request.getAttribute("test")%>";
  	  	var paper = Raphael("canvas", 1200, 800);
  	  	var row = 0;
  	  	/* for (var i = 0;i < 2000; i++) {
  	  		if(i%30==0){
  	  			row += 1;
  	  		}
	    	paper.circle(10 + 30 * (i%30), 10 + row*30 , 10)
	         .attr({fill: "#000",cursor:"pointer"})
	         .data("i", i)
	         .click(function () {
	            alert(this.data("i"));
	            this.attr({fill: "#fff"});
	         });
		};
		M 26.00 0.00 L 38.01 0.00 C 40.15 10.26 33.72 23.49 40.71 32.31 C 254.23 31.48 468.79 32.42 682.69 31.83 C 690.00 23.50 684.15 10.03 686.00 0.00 L 698.00 0.00 C 697.87 13.66 698.33 27.32 697.74 40.97 C 695.44 84.80 699.09 128.84 695.56 172.59 C 691.22 181.10 687.88 190.15 683.82 198.77 C 675.02 204.97 665.27 211.67 653.98 210.90 C 505.65 211.14 357.33 210.93 209.00 211.00 C 158.81 210.47 108.56 212.32 58.42 209.55 C 50.48 206.88 42.13 202.75 37.14 195.94 C 34.71 187.16 25.50 181.48 26.25 171.71 C 26.47 114.53 25.65 57.24 26.00 0.00 M 285.39 84.52 C 275.52 84.91 265.54 93.11 268.97 104.00 C 267.26 112.40 277.22 114.00 282.31 117.66 C 291.85 119.20 306.31 111.49 303.00 100.00 C 304.15 90.62 294.06 83.26 285.39 84.52 M 357.30 84.27 C 347.71 84.96 337.64 92.30 339.97 102.99 C 338.27 111.74 347.99 113.64 353.29 117.70 C 363.26 119.27 378.41 111.53 374.01 99.01 C 375.66 89.54 365.20 84.04 357.30 84.27 M 428.30 84.27 C 422.91 85.51 416.37 87.32 413.14 92.15 C 410.50 98.51 408.42 110.19 416.99 113.01 C 422.08 115.62 427.24 120.31 433.36 117.37 C 438.07 113.20 446.69 111.82 446.05 104.01 C 448.87 92.81 438.58 83.73 428.30 84.27 Z
		paper.text(10,10,"测试").attr({fill: "#fff"});	 */
		paper.circle(100,100,50).attr({stroke: "#000",fill:"red"}).data("value",25);
		paper.path("M 26.00 0.00 L 38.01 0.00 C 40.15 10.26 33.72 23.49 40.71 32.31 C 254.23 31.48 468.79 32.42 682.69 31.83 C 690.00 23.50 684.15 10.03 686.00 0.00 L 698.00 0.00 C 697.87 13.66 698.33 27.32 697.74 40.97 C 695.44 84.80 699.09 128.84 695.56 172.59 C 691.22 181.10 687.88 190.15 683.82 198.77 C 675.02 204.97 665.27 211.67 653.98 210.90 C 505.65 211.14 357.33 210.93 209.00 211.00 C 158.81 210.47 108.56 212.32 58.42 209.55 C 50.48 206.88 42.13 202.75 37.14 195.94 C 34.71 187.16 25.50 181.48 26.25 171.71 C 26.47 114.53 25.65 57.24 26.00 0.00 M 285.39 84.52 C 275.52 84.91 265.54 93.11 268.97 104.00 C 267.26 112.40 277.22 114.00 282.31 117.66 C 291.85 119.20 306.31 111.49 303.00 100.00 C 304.15 90.62 294.06 83.26 285.39 84.52 M 357.30 84.27 C 347.71 84.96 337.64 92.30 339.97 102.99 C 338.27 111.74 347.99 113.64 353.29 117.70 C 363.26 119.27 378.41 111.53 374.01 99.01 C 375.66 89.54 365.20 84.04 357.30 84.27 M 428.30 84.27 C 422.91 85.51 416.37 87.32 413.14 92.15 C 410.50 98.51 408.42 110.19 416.99 113.01 C 422.08 115.62 427.24 120.31 433.36 117.37 C 438.07 113.20 446.69 111.82 446.05 104.01 C 448.87 92.81 438.58 83.73 428.30 84.27 Z").attr({fill:"#f00"}).scale(0.6)
		.click(function(){
			paper.forEach(function (el) {
			    el.scale(0.8);
			});
		});
  	  </script>
  </body>
</html>
