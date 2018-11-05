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

<title>流量套餐</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/echarts1_new.js"></script>
<script type="text/javascript" src="js/echarts-zlbpack.js"></script>
<script type="text/javascript" src="js/d3.min.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
<script type="text/javascript" src="js/velocity.ui.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="js/jquery.ui.draggable.js"></script>

<link rel="stylesheet" href="css/flowandpack/flowandpack.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link><style>

@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1517206449464'); /* IE9*/
  src: url('iconfont.eot?t=1517206449464#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('iconfont.woff?t=1517206449464') format('woff'), /* chrome, firefox */
  url('iconfont.ttf?t=1517206449464') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1517206449464#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.iconfont1{
	font-size:26px;
	margin-left: 10px;
	color:#2265F1;
	cursor: pointer;
}

*{font-family: Microsoft YaHei;}
html{width: 100%;height: 100%;}
body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0;text-align: center; position: relative;}

.packtext{
	fill:#fff;
	font-size:20px;
}
.packtext1{
	fill:#fff;
	font-size:20px;
}
.flowpackhsitoty{
	background: url(image/pack/historyback.png);
	background-size:100% 100%;
}
.flowpackhsitoty .shutdown{
	background: url(image/pack/shutdowm.png);
	background-size:100% 100%;
}
</style>
</head>

<body>
	<div class="flowandpack">
		<div class="top">
			<div class="flowTopTen">
				<!-- 标题部分 -->
				<div class="flowTopBack">
					<div class="BgRedAndTitle">
						<div class="divBgRed"></div>
						<div class="divTitle"><span>流量包排行榜TOP10</span>&nbsp;&nbsp;<!-- <i class="iconfont  font1" >&#xe617;</i> --></div>
					</div>
				</div>
				<!-- 数据部分 -->
				<div id="divflowTopTenData" class="flowTopTenData">
					<div class="flowdata"><div class="diviconfont"><i class="iconfont color1">&#xe61b;</i></div><div class="divpackname"></div></div>
					<div class="flowdata"><div class="diviconfont"><i class="iconfont color2">&#xe624;</i></div><div class="divpackname"></div></div>
					<div class="flowdata"><div class="diviconfont"><i class="iconfont color3">&#xe61c;</i></div><div class="divpackname"></div></div>
					<div class="flowdata"><div class="diviconfont"><i class="iconfont color4">&#xe61e;</i></div><div class="divpackname"></div></div>
					<div class="flowdata"><div class="diviconfont"><i class="iconfont color4">&#xe621;</i></div><div class="divpackname"></div></div>
					<div class="flowdata"><div class="diviconfont"><i class="iconfont color4">&#xe625;</i></div><div class="divpackname"></div></div>
					<div class="flowdata"><div class="diviconfont"><i class="iconfont color4">&#xe626;</i></div><div class="divpackname"></div></div>
					<div class="flowdata"><div class="diviconfont"><i class="iconfont color4">&#xe620;</i></div><div class="divpackname"></div></div>
					<div class="flowdata"><div class="diviconfont"><i class="iconfont color4">&#xe623;</i></div><div class="divpackname"></div></div>
					<div class="flowdata"><div class="diviconfont"><i class="iconfont color4">&#xe622;</i></div><div class="divpackname"></div></div>
				</div>
				
				
			</div>
			<div class="packMainPush">
				<!-- 标题部分 -->
				<div class="flowTopBack">
					<div class="BgRedAndTitle">
						<div class="divTitle">主推流量包&nbsp;&nbsp;<span style="font-size: 16px;color:#ee456c">(点击柱状图查看历史信息)</span><i class="iconfont iconfont1" onclick="dowlaodMainPush()">&#xe63b;</i></div> 
					</div>
				</div>
				<!-- 数据部分 -->
				<div class="mainPushPack" id="echartsdiv">
				</div>
				<!-- 背景颜色 -->
				<div class="mainPushPackback">
				</div>
			</div>
		</div>
		<div class="bottom">
			<!-- 标题部分 -->
			<div class="flowTopBack">
					<div class="BgRedAndTitle">
						<div class="divBgRed"></div>
						<div class="divTitle">流量包线下办理情况</div>
						<div id="divDetailedDescr" class="detailedDescr">线下办理占比大于<input id="yuzhiinput" class="yuzhiinput" type="text" value="10">%的流量包</div>
					</div>
			</div>
			<!-- 数据部分 -->
			<div id="flowPackUnderLine" class="flowPackUnderLine">
				<div class="sildername" id="sildername">
  				</div>
				<div class="d3data" id="d3data">
  				</div> 
			</div>
		</div>
	</div>
	<div id="popDivTitle" class="popDivTitle"></div>
	<div id="flowpackhsitoty" class="flowpackhsitoty">
		<div class="shutdown" onclick="historyshutdown()"></div>
		<div class="historyname"></div>
		<div id="historydata" class="historydata"></div>
	</div>
</body>
<script type="text/javascript">
	var state="<%=request.getAttribute("state")%>";
	var yuzhi=10;
	var dataname=null;
	var curdate="<%=request.getAttribute("curdate")%>";
	var maxDate="<%=request.getAttribute("maxDate")%>";
	var minDate="<%=request.getAttribute("minDate")%>";
	var regionId="<%=request.getAttribute("regionId")%>";
	adjust();
	
	/*轮播定义变量*/
	var timer; //定时器返回值，主要用于关闭定时器
 	var iNow=0; //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0
 	var iNowx=0;
 	/*d3画图定义变量*/
 	var divheight=$(".d3data").height();
	var divwidth=$(".d3data").width();
	var margin = {top: 5, right: 20, bottom: 5, left: 20};
	var radiusScale = d3.scale.sqrt().domain([0, 20000]).range([0, divwidth/2]);//构建一个平方根比例尺
	var yrange = d3.scale.ordinal().domain(d3.range(9)).rangeBands([0, divheight]);//将y轴方向均分为五等份  d3.range([start,]stop[,step]) 获得一个数列
	var colors=[
				["#f62cc6","#f62cc6","#e12bb7","#b9299c","#842578","#56235b"],
				["#006bfe","#016afb","#0365ed","#0858cb","#114291","#183163"],
				["#f84102","#fa4101","#ec3f04","#c73a0d","#852f1d","#5b2927"],
				["#00fdfe","#01f8f9","#03ecee","#08c7cc","#118691","#175969"],
				["#fff000","#f9eb01","#ecde05","#c0b60f","#827c1e","#5b5727"],
			];
	$(function(){
		initTopten();
		initd3underLine();
		initMainPushPack();
		/*jquery ui 插件*/
		$("#flowpackhsitoty").draggable({
			cursor:"pointer"
		});
		$("#yuzhiinput").blur(function(){
			var inputvalue=parseFloat($("#yuzhiinput").attr("value")); 
			if(inputvalue<=0||inputvalue>=100){
				alert("请输入0~100之间的值");
				$("#yuzhiinput").attr("value",yuzhi);
			}else{
				yuzhi=inputvalue;
				initd3underLine();
			};
		
		});
		Array.prototype.max=function(){
			return Math.max.apply({},this);
		};
		Array.prototype.min=function(){
			return Math.min.apply({},this);
		};
	});
	
	/*下载主推流量包*/
	function dowlaodMainPush(){
		window.open("dowlaodMainPush.do?date="+curdate+"&regionId="+regionId);
	}
	
	function main(date1,regionId1){
		curdate=date1;
		/*加载流量包TOP10*/
		regionId=regionId1;
		initTopten();
		initd3underLine();
		initMainPushPack1();
		$("#flowpackhsitoty").hide();
	}
	/*调整布局*/
	function adjust(){
		var domheight=$("html").height();
		$(".flowandpack").height(domheight-30);
		var divflowandpackheight=$(".flowandpack").height();
		$(".bottom").css("height",divflowandpackheight*0.49-10);
		
		var divTitlewidth=$(".divTitle").width();
		$(".divhot").css({"top":"-10px","left":divTitlewidth+5+"px"});
		var divd3data=$(".d3data").width();
	}
	
	/*主推流量包历史数据页面关闭*/
	function historyshutdown(){
		$("#flowpackhsitoty").hide();
	}
	/*底部轮播*/
	/* function beginlunbo(istrue){
		if(istrue){
	  		state="start";
	  	};
  		lunboTime();
	}; */
	
	function sildernameclick(obj){
			clearInterval(timer);
  			$(obj).addClass("active").siblings().removeClass("active"); //按钮点击时为这个按钮添加高亮状态，并且将其他按钮高亮状态去掉
  			var index=$(obj).index(); //获取哪个按钮被点击，也就是找到被点击按钮的索引值
  			$("#divcircle"+iNowx).velocity("transition.slideRightOut",{duration:1000,display:"none"});
  			iNow=index;
  			iNowx=index;
  			$("#divcircle"+iNow).velocity("transition.slideLeftIn",{duration:2000,delay:100,display:"block"});
 			/* if(state=="start"){
	  			lunboTime();
	  		}; */
 			
	};
	
	/* function lunboTime(){
 		timer=setInterval(function(){ //打开定时器
  			iNow++;    //让图片的索引值次序加1，这样就可以实现顺序轮播图片
  			if(iNow>$(".sildername div").length-1){ //当到达最后一张图的时候，让iNow赋值为第一张图的索引值，轮播效果跳转到第一张图重新开始
  				iNow=0;
  			}
  			$(".sildername div").eq(iNow).trigger("click"); //模拟触发数字按钮的click
 		},10000); //2000为轮播的时间   
	}; */
	
		
	/*子页面停止轮播*/
	/* function endlunbo(){
		state="stop";
		clearInterval(timer);
	} */
	
	
	function drawrings(data){
		for(var i=0;i<data.length;i++){
			var packdata=data[i];
			if(data[i].length==0){
				var str="<div class='nromalbusi'></div>";
				var id="divcircle"+i;
				$("#"+id).html(str);
				$(".nromalbusi").css("background-image","url('image/normalbusiness.png')");
				continue;
			};
			$("#divcircle"+i).html("");
			 /*均分x轴坐标*/
			var xrange = d3.scale.ordinal()
	    		.domain(d3.range(packdata.length))
	    		.rangeBands([0, divwidth]);
				 /*使用d3画图*/
			svg = d3.select("#divcircle"+i).append("svg")
	    		.attr("width", divwidth + margin.left + margin.right)
	    		.attr("height", divheight +margin.top + margin.bottom)
	  			.append("g")
	    		.attr("transform", "translate(" + margin.left + ",0)");
	    		/**/
	    		
	    		/*拼接文字*/
	    		svg.selectAll(".packtext")
	    			.data(packdata)
	    			.enter()
	    			.append("text")
	    			.attr("class","packtext")
	    			.attr("text-anchor","middle")
	    			.attr("x", function(d,i){
	    						return xrange(i)+0.5*xrange.rangeBand();
	    			})
	   	 			.attr("y",function(d,i){
	   	 					if((i%2)!=0){
	   	 						return yrange(4)+5;
	   	 					}else{
	   	 						return yrange(5)+5;
	   	 					}
	   	 			})
	    			.text(function(d){
	    				return d.rate1;
	    			});
	    		
	    	var multidata=[1.0,1.1,1.2,1.3,1.4,1.5];
	    		for(var j=0;j<6;j++){
	    			svg.selectAll(".circle"+j)// 插入的不是circle了，改为rect
		    			.data(packdata)
		    			.enter()
		    			.append("circle")
		    			.attr("class", "circle"+j)
		    			.attr("cx", function(d,i){
		    						return xrange(i)+0.5*xrange.rangeBand();
		    			})
		   	 			.attr("cy",function(d,i){
		   	 					if((i%2)!=0){
		   	 						return yrange(4);
		   	 					}else{
		   	 						return yrange(5);
		   	 					}
		   	 			})
		   	 			.attr("r",0)
		   	 			.transition(0)
		        		.duration(1000)
		    			.attr("r",function(d,i){
		    						return radiusScale(d.rate)*multidata[j]; 
		    			})
		    			.attr("stroke",function(d,i){
		    							var n=d.index%5;
		    						return colors[n][j];
		    			})
		        		.attr("fill", "rgba(0,0,0,0)");
	    		};
	    		
	    		svg.selectAll(".circle5").on("mouseover", mouseover).on("mouseout", mouseout); 
	    		
	    		function mouseover(d){
	    			pageX=window.event.pageX;
 					pageY=window.event.pageY;
					$("#popDivTitle").css("top",pageY-10+"px");
					$("#popDivTitle").css("left",pageX+30+"px");
					$("#popDivTitle").html("线下办理量为 :"+d.num);
					$("#popDivTitle").show();
	    		};
	    		
	    		function mouseout(d){
	    			$("#popDivTitle").css("top","0px");
					$("#popDivTitle").css("left","0px");
					$("#popDivTitle").html("");
					$("#popDivTitle").hide();
	    		};
	    		
	    		svg.selectAll(".packtext1")
	    			.data(packdata)
	    			.enter()
	    			.append("text")
	    			.attr("class","packtext1")
	    			.attr("text-anchor","middle")
	    			.attr("x", function(d,i){
	    						return xrange(i)+0.5*xrange.rangeBand();
	    			})
	   	 			.attr("y",function(d,i){
	   	 					if((i%2)!=0){
	   	 						return yrange(4)+radiusScale(d.rate)*2;
	   	 					}else{
	   	 						return yrange(5)+radiusScale(d.rate)*2;
	   	 					}
	   	 			}) 
	    			.text(function(d){
	    				return d.name;
	    			});  
		};		
	};
	
	

	
	
	function initTopten(){
		$.ajax({
				url:"gettableTopten.do",
				type:"post",
				dataType:"json",
				data:{"date":curdate,"regionId":regionId},
				success:function(data){
					if(data!=null&&data.length!=0){
						$(".flowdata").each(function(i){
							$(this).find(".divpackname").html(divConnectfluxandpack(data[i]));
							var divpacknameheight=$(".flowdata").height();
							/*调整文字垂直居中*/
								$(".divpackname,.diviconfont").css("line-height",divpacknameheight+"px");							
							$(this).mousemove(function(e){
								var pageX=e.pageX;
								var pageY=e.pageY;
								$("#popDivTitle").css("top",pageY-10+"px");
								$("#popDivTitle").css("left",pageX+30+"px");
								$("#popDivTitle").html("办理量 : "+data[i].num);
								$("#popDivTitle").show();
								
							});
							$(this).mouseout(function(e){
								$("#popDivTitle").css("top","0px");
								$("#popDivTitle").css("left","0px");
								$("#popDivTitle").html("");
								$("#popDivTitle").hide();
							});
						});
					}else{
						$(".flowdata").each(function(i){
							$(this).find(".divpackname").html("");							
						});
					}
				}
			});
		
	}
	
	function divConnectfluxandpack(data){
		var str="";
		if(data.imp=="1"){
			str="<span class='namespan'>"+data.name+"</span>&nbsp;<i class='iconfont font11' >&#xe617;</i>";
		}else{
			str="<span class='namespan2'>"+data.name+"</span>";
		};
		return str;
	}
	
	function initd3underLine(){
		clearInterval(timer);
		iNow=0; //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0
 		iNowx=0;
		$.ajax({
				url:"initd3underLine.do",
				type:"post",
				dataType:"json",
				data:{"date":curdate,"regionId":regionId,"yuzhi":yuzhi*0.01},
				success:function(data){
					if(data!=null&&data.name.length!=0){
						dishidivConnect(data);
					}else{
						var str="<div class='divcircle1'><div class='nromalbusi'></div><div>";
						$("#sildername").html("");
						$("#d3data").html(str);
						$(".nromalbusi").css("background-image","url('image/normalbusiness.png')");
					}
				}
			});
		
	}
	function dishidivConnect(data){
		dataname=data.name;
		var str1="";
		for(var i=0;i<data.name.length;i++){
			if(i==0){
				str1+="<div class='regionname active' onclick='sildernameclick(this)'>"+data.name[i].regionname+"</div>";
			}else{
				str1+="<div class='regionname' onclick='sildernameclick(this)' >"+data.name[i].regionname+"</div>";
			}
		}
		$("#sildername").html(str1);
		
		var regionnameheight=$(".regionname").height();
		$(".regionname").css("line-height",regionnameheight+"px");
		
		var str2="";
		for(var i=0;i<data.name.length;i++){
			var id="divcircle"+i;
			str2+="<div  id="+id+" class='divcircle'></div>";
		}
		$("#d3data").html(str2);
		drawrings(data.data);
		iNow=0; //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0
 		iNowx=0;
 		/* if(state=="start"){
 			lunboTime();
 		} */
	}
	
	
	
	function initMainPushPack(){
		$.ajax({
				url:"initMainPushPack.do",
				type:"post",
				dataType:"json",
				data:{"date":curdate,"regionId":regionId},
				success:function(data){
					if(data!=null){
						initAreaChart("echartsdiv",data);
					}else{
						initAreaChart("echartsdiv",null);
					}
				}
			});
		
	}
	function initMainPushPack1(){
		$.ajax({
				url:"initMainPushPack.do",
				type:"post",
				dataType:"json",
				data:{"date":curdate,"regionId":regionId},
				success:function(data){
					if(data!=null){
						var myecharts = echarts.getInstanceByDom(document.getElementById("echartsdiv"));
						var option={xAxis:{data: data.name},series: [{data:data.data1},{data:data.data},{data:data.data2}]};
						myecharts.setOption(option);
					}else{
						var myecharts = echarts.getInstanceByDom(document.getElementById("echartsdiv"));
						var option={xAxis:{data:null},series: [{data:null},{data:null},{data:null}]};
						myecharts.setOption(option);
					}
				}
			});
		
	}
	
	function inithistory(name){
		//alert(name);
		$.ajax({
				url:"mainPushBackHistoty.do",
				type:"post",
				dataType:"json",
				data:{"date":curdate.substring(0,6),"regionId":regionId,"name":encodeURI(name)},
				success:function(data){
					$("#flowpackhsitoty").show();
					$(".historyname").html(name)
					alldata = data.alldata;
					num = data.num.max();
					lenged = data.lenged;
					console.log(num);
					initHistoryLine("historydata",alldata,lenged,num);
				}
			});
	}
	
</script>
</html>
