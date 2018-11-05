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

<title>4G</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="js/echarts_new.js"></script>
<script type="text/javascript" src="js/echarts-zlb.js"></script>
<script type="text/javascript" src="js/unslider.min.js"></script>
<script type="text/javascript" src="js/pagetransition/modernizr.custom.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="js/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
<script type="text/javascript" src="js/velocity.ui.js"></script>

<link rel="stylesheet" href="css/4Gpage/4Gpage.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="css/pagetransition/animations.css" />

<style>

@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1521701446439'); /* IE9*/
  src: url('iconfont.eot?t=1521701446439#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
  url('iconfont.ttf?t=1521701446439') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1521701446439#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

*{font-family: Microsoft YaHei;}
html{width: 100%;height: 100%;}
body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0; position:relative;}

.pt-perspective {
	position: relative;
	width: 100%;
	height: 100%;
	-webkit-perspective: 1200px;
	-moz-perspective: 1200px;
	perspective: 1200px;
}
.pt-page {
	width: 100%;
	height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	visibility: hidden;
	overflow: hidden;
	color:#000;
	-webkit-backface-visibility: hidden;
	-moz-backface-visibility: hidden;
	backface-visibility: hidden;
	-webkit-transform: translate3d(0, 0, 0);
	-moz-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0);
	-webkit-transform-style: preserve-3d;
	-moz-transform-style: preserve-3d;
	transform-style: preserve-3d;
}
.pt-page-current,.no-js .pt-page {
	visibility: visible;
	z-index: 1;
}

.dots{position: absolute; left: 0; right: 0; bottom: 0px;width: 90px;height: 15px;margin: 0;padding: 0;z-index: 999;}
.dots li{ 
	display: inline-block; 
	width: 6px; 
	height: 6px; 
	margin: 5px 4px; 
	text-indent: -999em; 
	border: 0px solid #fff; 
	border-radius: 8px; 
	cursor: pointer; 
	opacity: .4; 
	background-color:#7A7A86;
	-webkit-transition: background .5s, opacity .5s; 
	-moz-transition: background .5s, opacity .5s; 
	transition: background .5s, opacity .5s;
}
.dots li.active {background: #fff;opacity: 1;}

.iconfont1{
	font-size: 24px !important;
}
.iconfont2{
	font-size:26px;
	margin-left: 20px;
	color:#2265F1;
	cursor: pointer;
}


/*悬浮框设置*/
.divpackdesc{width:300px; padding:20px 10px; border:1px solid #0ff; background:#2B3741;position:absolute;border-radius:10px;display:none}
.divpackdesc .bot,.divpackdesc .top{width:0; height:0; font-size:0; overflow:hidden; position:absolute;}
.divpackdesc span.bot{
    border-width:15px; 
    border-style:solid dashed dashed; 
    border-color:#0ff transparent transparent; 
    left:80px; 
    bottom:-30px;
}
.divpackdesc .packdescr{
	word-wrap:break-word;
	color:white;
	
}
.divpackdesc span.top{
    border-width:15px; 
    border-style:solid dashed dashed; 
    border-color:#2B3741 transparent transparent; 
    left:80px; 
    bottom:-28.5px;
}

/*套餐包升档、降档、平迁分析*/
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
	<div class="maindiv">
		<!-- echarts桑葚图 -->
		<div id="maintop" class="maintop">
			<div class="topleft">
				<div class="title">
					<div class="packinfo" id="divPackName"></div>
					<div class="sildertop"></div>
				</div>
				<div class="main">
					<div class="echartsdiv" id="echartsdiv"></div>
					
				</div>
			</div>
			<div class="topright" id="topright">
				<div class="title">
					<div class="packinfo" id="radarpackname"></div>
					<div id="updwondang" class="updwondang" data-packid="" data-hisdate="" onclick="toxiangxiantu(this)"><i class="iconfont" style="font-size: 20px;">&#xe61d;</i><span>&nbsp;升降档分析</span></div>
				</div>
				<div id="pt-main" class="pt-perspective">
						<div class="pt-page" inid="1" id="DivnewHistory">
							<!-- <div class="divhistory">
								<div class="historycontent">
									<div class="title title1">降档</div>
									<div class="historydata">
										<div class="douarpu">
											<div class="name">DOU(MB)</div>
											<div class="hisdata">
												<div class="hisdataleft">产转前</div>
												<div class="hisdataright">12345.67</div>
												<div class="hisdataleft">产转前</div>
												<div class="hisdataright">456</div>
											</div>	
										</div>
										<div class="douarpu">
											<div class="name">ARPU(元)</div>
											<div class="hisdata">
												<div class="hisdataleft">产转前</div>
												<div class="hisdataright">123</div>
												<div class="hisdataleft">产转前</div>
												<div class="hisdataright">456</div>
											</div>
										</div>
									</div>
								</div>
								<div class="historycontent">
									<div class="title title2">升档</div>
									<div class="historydata"></div>
								</div>
								<div class="historycontent historycontent-last">
									<div class="title title3">总体</div>
									<div class="historydata"></div>
								</div>
							</div> -->
						</div>
						<div class="pt-page" inid="2" >
							<div class="divradar">
								<div id="radarechart" class="radarechart"></div>
								<div class="divfanhui" id="divfanhui" onclick="fanhuitable()"><i class="iconfont font3">&#xe6d6;</i></div>
							</div>
							
						</div>
				</div>
			</div>
		</div>
		<!-- 底部表格部分 -->
		<div class="mainbottom">
			<div class="title">
					<div class="packinfo" id="radarpackname">
						<span>各套餐详细情况</span>
					</div>
					
					<div class="allsum" id="allsum">
						<span class="sumName">总降档收入（元）:</span>
							<span class="sumNum"></span>
						<span class="sumName">总升档收入（元）:</span>
							<span class="sumNum"></span>
						<span class="sumName">金额（元）:</span>
							<span class="sumNum"></span>
						<i class="iconfont iconfont2" onclick="downloadPackDesc()">&#xe63b;</i>
					</div>
					
					<div id="changeqipao" class="qipaochangediv" onclick="toQiPaoPage()">
							<i class="iconfont">&#xe63e;</i>
							<span>套餐变化趋势</span>
					</div>
			</div>
			<div class="bottomtable">
					<div class="tableName">
						<!-- <div class="tabletitle"> -->
							<table class="table1">
								<tr>
									<th><div class="thright">名称</div></th>
									<th><div class="thright">办理量(户)</div></th>
									<th><div class="thright">升档办理量(户)</div></th>
									<th><div class="thright">降档办理量(户)</div></th>
									<th><div class="thright">平迁办理量(户)</div></th>
									<th><div class="thright">降档收入(元)</div></th>
									<th><div class="thright">升档收入(元)</div></th>
									<th><div class="thright">金额(元)</div></th>
									<th>详情</th>
								</tr>
							</table>
						<!-- </div> -->
					</div>
					<div class="tableshuju">
							<table class="table2">
								
							</table>
					</div>
			</div>	
		</div>
		<!-- 气泡图详情 -->
		<div id="echartsdesc" class="echartsdesc">
			<div class="title"><span></span><i class="iconfont closeicon" onclick="closeechdesc()">&#xe92a;</i></div>
			<div class="content" id="echartsdesc1"></div>
		</div>
		
		<!-- 套餐详细绍 -->
		<div  id="divpackdesc" class="divpackdesc">
	    	<span class="bot"></span>
	    	<span class="top"></span>
	   		<div id="packdescr" class="packdescr"></div>
		</div>
		
		<!-- 套餐升档，降档平迁分析 -->
		<div id="flowpackhsitoty" class="flowpackhsitoty">
			<div class="shutdown" onclick="historyshutdown()"></div>
			<div class="historyname"></div>
			<div id="packhisdata" class="packhisdata"></div>
			<div id="packhisdatanav" class="packhisdatanav">
				<div class="packdaohang">
					<div data-index="0" class="packdaohang1" onclick="changePackDang(this)">升档</div>
					<div data-index="1" onclick="changePackDang(this)">降档</div>
					<div data-index="2" onclick="changePackDang(this)">平迁</div>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>


<script type="text/javascript">
	var state="start";
	var iNow;
	var timer;
	var tableNum;
	var curdate="<%=request.getAttribute("curdate")%>";
	var regionId="<%=request.getAttribute("regionId")%>";
	var date1="";
	var lunbotime=false;
	
	/*升档、降档、平迁信息*/
	var packDangArray = new Array(3);
	/*动态切换div需要*/
	var $main = $('#pt-main'),
		$pages = $main.children( 'div.pt-page' ),
		animcursor = 57,
		pagesCount = $pages.length,
		current = 0,
		isAnimating = false,
		endCurrPage = false,
		endNextPage = false,
		animEndEventNames = {
			'WebkitAnimation' : 'webkitAnimationEnd',
			'OAnimation' : 'oAnimationEnd',
			'msAnimation' : 'MSAnimationEnd',
			'animation' : 'animationend'
		},
		// animation end event name
		animEndEventName = animEndEventNames[ Modernizr.prefixed( 'animation' ) ],
		// support css animations
		support = Modernizr.cssanimations;
	
	$(function(){

		adjust();
		main(curdate,regionId);
		$("#echartsdesc,#flowpackhsitoty").draggable({
			cursor:"pointer"
		});
		/*动态切换开始时显示第一个div*/
		$pages.each( function() {
			var $page = $( this );
			$page.data( 'originalClassList', $page.attr( 'class' ) );
		} );
		$pages.eq(current).addClass( 'pt-page-current' );
		
		Array.prototype.max=function(){
			return Math.max.apply({},this);
		};
		Array.prototype.min=function(){
			return Math.min.apply({},this);
		};
		
	});
	
	/*关闭echarts详情套餐页面*/
	function closeechdesc(){
		$("#echartsdesc").hide();
	};
	
	/*调用气泡图页面*/
	function toQiPaoPage(){
		window.parent.tochangepage1(1);
	};
	
	/*调整页面布局*/
	function adjust(){
		var domheight=$("html").height();
		$(".maindiv").height(domheight-30);
		divheight=$(".maindiv").height();
		$(".mainbottom").css("height",divheight*0.4-10);
		tabletitleheight=$(".tabletitle").height();
		$(".topleft").find(".title").find(".packinfo").css("margin-top",($(".topleft").find(".title").height()*0.5)-($(".topleft").find(".title").find(".packinfo").height()*0.5));
		$(".topright").find(".title").find(".packinfo").css("margin-top",($(".topright").find(".title").height()*0.5)-($(".topright").find(".title").find(".packinfo").height()*0.5));
		$(".topright").find(".title").find(".updwondang").css("margin-top",($(".topright").find(".title").height()*0.5)-($(".topright").find(".title").find(".updwondang").height()*0.5));
		$(".mainbottom").find(".title").find(".packinfo").css("margin-top",($(".mainbottom").find(".title").height()*0.5)-($(".mainbottom").find(".title").find(".packinfo").height()*0.5));
		$(".mainbottom").find(".title").find(".qipaochangediv").css("margin-top",($(".mainbottom").find(".title").height()*0.5)-($(".mainbottom").find(".title").find(".qipaochangediv").height()*0.5));
		$(".mainbottom").find(".title").find(".allsum").css("margin-top",($(".mainbottom").find(".title").height()*0.5)-($(".mainbottom").find(".title").find(".allsum").height()*0.5));
		$(".mainbottom").find(".title").find(".allsum").css("line-height",$(".mainbottom").find(".title").find(".allsum").height()+"px");
		$(".sildertop").css("margin-top",($(".topleft").find(".title").height()*0.5)-($(".topleft").find(".title").find(".sildertop").height()*0.5));
		$(".chartmaindiv").find(".title").find(".areacharttitle").css("margin-top",($(".chartmaindiv").find(".title").height()*0.5)-($(".chartmaindiv").find(".title").find(".areacharttitle").height()*0.5));
	};
	
	function main(date,regionId1){
		clearInterval(timer);
		iNow=0;
		tableNum=null;
		regionId=regionId1;
		date1=date;
		getTabledata(date);
		if($(".pt-page-current").attr("inid")=="2"){
			changepage(0);
		};
		$("#echartsdesc").hide();
		$("#updwondang").show();
		$("#flowpackhsitoty").hide();
	};
	
	
	/*下载下方表格升降平迁档详情*/
	function downloadPackDesc(){
		window.open("downloadPackSJPDesc.do?date="+curdate+"&regionId="+regionId);	
	};
	
	
	/*详细气泡图*/
	function initDescrScatter(zcid,zrid,seriesIndex,date,regionid){
		$.ajax({
				url:"initDescrScatter.do",
				type:"post",
				dataType:"json",
				data:{"zcid":zcid,"zrid":zrid,"date":date,"regionid":regionid},
				success:function(data){
					if(data!=null&&data[0].length!=0){
						if(seriesIndex=="0"){
							initScatter1("echartsdesc1",data[0]);
						}else{
							initScatter2("echartsdesc1",data[0]);
						}
					}else{
						$("#echartsdesc1").html("");
					};
				}
			});
	
	};
	
	
	/*查询表格中的数据*/
	function getTabledata(date1){
		$.ajax({
				url:"getdatafortable.do",
				type:"post",
				dataType:"json",
				traditional:true,
				data:{"date":date1,"regionId":regionId},
				success:function(data){
					if(data!=null){
						tabeldataConnect(data.data);
						SumNum(data.sumdata);
					}else{
						alert("暂无数据！！");
					};
				}
			});
	}
	
	
	/*总办理量金额拼接*/
	function SumNum(data){
		if(data.length=4){
			$(".allsum .sumNum").each(function(i){
				$(this).html(data[i]);
			});
		}else{
			$(".allsum .sumNum").each(function(i){
				$(this).html("");
			});
		}
		
	}
	
	/*echarts表格数据*/
	function getechartsdata(id,name){
		$("#divPackName").html(name+" 转入情况");
		$.ajax({
				url:"getechartsdata.do",
				type:"post",
				dataType:"json",
				async:true,
				data:{"date":date1,"id":id,"regionId":regionId},
				success:function(data){
					if(data!=null&&data.links.length!=0){
						initSankey("echartsdiv",data);
					}else{
						var myecharts = echarts.getInstanceByDom(document.getElementById("echartsdiv"));
						/*清除Echart实例*/
						myecharts.clear();	
					};
				}
			});
	}
	
	function historydata(id,packname){
		$("#radarpackname").html("历史转入  "+packname+" 数据追踪");
		/*请求数据查询*/
		$.ajax({
					url:"gethistorydata.do",
					type:"post",
					dataType:"json",
					data:{"zrid":id,"regionId":regionId,"date":date1},
					success:function(data){
						if(data!=null&&data.length!=0){
							historytableconnect(id,data);
						}else{
							$("#DivnewHistory").html("");
						};
					}
				});
		
	}
	
	/*历史数据追踪*/
	function historytableconnect(id,data){
		var str="";
		var months=[];
		/*DANG 1降2升3整体*/
		for(var i=0;i<data.length;i++){
			var obj=data[i];
			if(obj!=null&&obj.length!=0){
				months.push(obj[0].date);
				var monthId="divhis"+obj[0].date;
				str+="<div class='divhistory' id='"+monthId+"'>";
				for(var j=0;j<obj.length;j++){
					if(j==(obj.length-1)){
						str+="<div class='historycontent historycontent-last'>";
					}else{
						str+="<div class='historycontent'>";
					};
					if(obj[j].DANG==1){
						str+="<div class='title title1'>降档转入</div>";
					}else if(obj[j].DANG==2){
						str+="<div class='title title2'>升档转入</div>";
					}else{
						str+="<div class='title title3'>总体转入</div>";
					};
					str+="<div class='historydata'>"+
										"<div class='douarpu'>"+
											"<div class='name'>DOU(MB)</div>"+
											"<div class='hisdata'>"+
												"<div class='hisdataleft'>产转前</div>"+
												"<div class='hisdataright'>"+obj[j].GPLA+"</div>"+
												"<div class='hisdataleft'>产转后</div>";
												if(obj[j].GPCOM==1){
													str+="<div class='hisdataright'><span>"+obj[j].GPNE+"</span><i class='iconfont font1'>&#xe6db;</i>&nbsp;</div>";
												}else{
													str+="<div class='hisdataright'><span>"+obj[j].GPNE+"</span><i class='iconfont font2'>&#xe6da;</i>&nbsp;</div>";
												};
										str+="</div>"+
										"</div>"+
										"<div class='douarpu'>"+
											"<div class='name'>ARPU(元)</div>"+
											"<div class='hisdata'>"+
												"<div class='hisdataleft'>产转前</div>"+
												"<div class='hisdataright'>"+obj[j].FELA+"</div>"+
												"<div class='hisdataleft'>产转后</div>";
												if(obj[j].FECOM==1){
													str+="<div class='hisdataright'><span>"+obj[j].FENE+"</span><i class='iconfont font1'>&#xe6db;</i>&nbsp;</div>";
												}else{
													str+="<div class='hisdataright'><span>"+obj[j].FENE+"</span><i class='iconfont font2'>&#xe6da;</i>&nbsp;</div>";
												};
										str+="</div>"+
										"</div>"+
									"</div>"+
								"</div>";
				}
				str+="</div>";
			}
		}
		$("#DivnewHistory").html(str);
		$(".historydata").height($(".historycontent").height()-50); 
		$(".douarpu").css({"margin-top":$(".historydata").height()*0.05+"px","margin-bottom":$(".historydata").height()*0.1+"px"});	
		$(".douarpu .name").css("line-height",$(".douarpu .name").height()+"px");	
		$(".hisdata div").css("line-height",$(".hisdata div").height()+"px");
		divmonthtuli(months.reverse());
	}
	
	
	/*拼接图例*/
	function divmonthtuli(data){
		if(data==null||data.length==0){
			return;
		}
		var str="<div class='tulis'>";
		if(data.length!=1){
			for(var i=0;i<data.length;i++){
				if(i==0){
					str+="<div class='tuli tuli-first' onclick='changemonth(this)'><div>"+data[i]+"</div></div>";
				}else if(i==(data.length-1)){
					str+="<div class='tuli tuli-last' onclick='changemonth(this)'><div>"+data[i]+"</div></div>";
				}else{
					str+="<div class='tuli' onclick='changemonth(this)'><div>"+data[i]+"</div></div>";
				}
			}
		
		}else{
				str+="<div class='tuli tulione' onclick='changemonth(this)'><div>"+data[0]+"</div></div>";
		}
		str+="</div>";
		
		$("#DivnewHistory").append(str);
		$(".tulis").css("left",($("#DivnewHistory").width()-$(".tulis").width())/2+"px");
		
		/*拼接套餐变化趋势*/
		
		$(".tuli").eq(0).trigger("click");
	}
	
	
	function changemonth(obj){
		$(obj).addClass("monthselect");
		$(obj).siblings().removeClass("monthselect");
		$("#updwondang").attr("data-hisdate",$(obj).find("div").html());
		var monthId="divhis"+$(obj).find("div").html();
		$(".pt-page .divhistory").velocity({opacity:0,filter:"alpha(opacity(0))"},{duration:1000,complete:function(){
			$("#"+monthId).velocity({opacity:1,filter:"alpha(opacity(100))"},{duration:1000});
		}});
	}							
	
	/*拼接底部表格div*/
	function tabeldataConnect(data){
		/*轮播参数*/
		 tableNum=data.length;
		/*拼接table2数据*/
		connectTable2(data);
		
		/*调整表格中的的数据 加载进度条*/
		$(".tabledata").css("height",30*tableNum);
	 	$(".tableshuju").niceScroll({
			cursorcolor : "#262d45",//滚动条显示的颜色
			cursorborderradius: "2px",//滚动条边角圆弧
			cursorwidth: "4px",//滚动条宽度
			cursorborder: "1px solid #293d60",//滚动条边线
			autohidemode:true//是否自动隐藏
		}); 
		
		/*加载进度条*/
		//progressBar();
		
		/*设置table轮播事件*/
		var sliderContent=$(".table2 tr");
		var id=sliderContent.eq(0).attr("id");
		var packname=sliderContent.find("td:eq(0)").find("div").html();
		$(".tableshuju").getNiceScroll(0).doScrollTop(0,2000);
		/*根据id获取上面的桑基图*/
		getechartsdata(id,packname);
		/*右上角table表格*/
		historydata(id,packname);
		/*设置升降档div套餐名称id*/
		$("#updwondang").attr("data-packid",id);
		iNow=0;
		 /*绑定鼠标悬浮事件*/
		$(".table2 tr").mouseover(function(){
				$(this).addClass("bgcolor2");
				$(this).find("td").each(function(){
					$(this).addClass("fotcolor1");
				});
			});
		$(".table2 tr").mouseout(function(){
				$(this).removeClass("bgcolor2");
				$(this).find("td").each(function(){
					$(this).removeClass("fotcolor1");
				});
			});
		
		/* if(state=="start"){
			beginlunbo();
		}; */
	}
	
	function clicktr(obj){
		/*隐藏气泡详情图片*/
		$("#echartsdesc").hide();
		$("#updwondang").show();
		clearInterval(timer);
		var index=$(obj).index();
		
		/*设置每一行的背景颜色*/
		$(obj).addClass("bgcolor1");
		$(obj).siblings().removeClass("bgcolor1");
				/*设置选中的每一行的左侧的红色色块*/
		$(".table2div").removeClass("table2red");
		$(obj).find(".table2div").addClass("table2red");
				/*选中的th设置白色的字体*/
		$(obj).find("td").each(function(){
				$(this).addClass("fotcolor2");
		});
		$(obj).siblings().find("td").each(function(){
				$(this).removeClass("fotcolor2");
		});
		iNow=index;
		var id=$(obj).attr("id");
		$("#updwondang").attr("data-packid",id);
		var packname=$(obj).find("td:eq(0)").find("div").html();
		if(lunbotime){
			lunbotime=false;
			changepage(0);
		};
		/*根据id获取上面的桑基图*/
		getechartsdata(id,packname);
				/*右上角table表格*/
		historydata(id,packname);
		$(".tableshuju").getNiceScroll(0).doScrollTop(40*iNow,2000);
		$("#flowpackhsitoty").hide();
		/* if(state=="start"){
			beginlunbo(false);
		}; */
	};
	/*象限图*/
	function toxiangxiantu(obj){
		
		var packid=$(obj).attr("data-packid");
		var hisdate=$(obj).attr("data-hisdate");
		getRadardata(packid,hisdate);
		/*停止底部表格轮播*/
		//clearInterval(timer);
		lunbotime=true;
	 	changepage(1);
	 	$("#updwondang").hide();
	}
	
	function fanhuitable(){
		lunbotime=false;
		changepage(0);
		/*隐藏气泡详情图片*/
		$("#echartsdesc").hide();
		$("#updwondang").show();
	};
	
	/*底部表格轮播事件*/
	/* function beginlunbo(istrue){
	  if(istrue){
	  	state="start";
	  };
	  timer=setInterval(function(){ //打开定时器
  			iNow++;    
  			if(iNow>tableNum-1){ 
  				iNow=0;
  			}
  			$(".table2 tr").eq(iNow).trigger("click"); //模拟触发数字按钮的click
 		},10000); 
	 }; */
	
	/*子页面停止轮播*/
/* 	function endlunbo(){
		state="stop";
		clearInterval(timer);
	} */
	
	
	/*拼接表格2的数据*/
	function connectTable2(data){
		var str="";
		for(var i=0;i<data.length;i++){
			var obj=data[i];
			if(i==0){
				str+="<tr id="+obj.id+" class='bgcolor1' onclick='clicktr(this)'>";
			}else if((i%2)!=0){
				str+="<tr id="+obj.id+" class='bcolor' onclick='clicktr(this)' >";
			}else{
				str+="<tr id="+obj.id+" onclick='clicktr(this)' >";
			};
			
			str+="<td> <div class='table2div' onmouseout='PackMouseOut()' onmousemove='PackMousemoveDesc(this)' data-desc='"+obj.MiaoShu+"'>"+obj.name+"</div></td>"+
					/* "<td><div class='dataNum'>"+obj.Num+"</div><div class='dataprogress' w="+Math.abs(obj.Num/20000)+"  w1="+(obj.Num/20000)+"></div></td>"+
					"<td><div class='dataNum'>"+obj.down+"</div><div class='dataprogress' w="+Math.abs(obj.down/20000)+" w1="+(obj.down/20000)+"></div></td>"+
					"<td><div class='dataNum'>"+obj.up+"</div><div class='dataprogress' w="+Math.abs(obj.up/20000)+" w1="+(obj.up/20000)+"></div></td>"+
					"<td><div class='dataNum'>"+obj.allNum+"</div><div class='dataprogress' w="+Math.abs(obj.allNum/20000)+"  w1="+(obj.allNum/20000)+"></div></td>"; */
					"<td class='tb2th'>"+obj.Num+"</td>"+
					"<td class='tb2th'>"+obj.Num_SD+"</td>"+
					"<td class='tb2th'>"+obj.Num_JD+"</td>"+
					"<td class='tb2th'>"+obj.Num_PQ+"</td>"+
					"<td class='tb2th'>"+obj.down+"</td>"+
					"<td class='tb2th'>"+obj.up+"</td>"+
					"<td class='tb2th'>"+obj.allNum+"</td>"+
					"<td class='tb2th'><div class='table2iconfont' data-packname='"+obj.name+"'	data-packid='"+obj.id+"' onclick='showPackHistory(this)'><i class='iconfont iconfont1'>&#xe62a;</i>&nbsp;趋势预测<div></td>"; 
		};
		$(".table2").html(str);
	};
	
	/*鼠标悬浮显示套餐详情*/
	function PackMousemoveDesc(obj){
		event = window.event||event;
		pageX=event.pageX;
		pageY=$(window).height()-event.pageY;
		$("#divpackdesc").css("bottom",pageY+20+"px");
		$("#divpackdesc").css("left",pageX-140+"px");
		$("#packdescr").html($(obj).attr("data-desc"));
		$("#divpackdesc").show();
	}
	/*套餐详情关闭*/
	function PackMouseOut(obj){
		$("#packdescr").html("");
		$("#divpackdesc").hide();
	}
	
	
	/*获取套餐升、降、平迁套餐*/
	function showPackHistory(obj){
		$.ajax({
				url:"getPackHistory.do",
				type:"post",
				dataType:"json",
				data:{"date":curdate.substring(0,6),"regionId":regionId,"packid":$(obj).attr("data-packid")},
				success:function(data){
					$(".historyname").html($(obj).attr("data-packname"));
					$(".packdaohang div").removeClass("packdaohang1");
					$(".packdaohang div").eq(0).addClass("packdaohang1");
					$("#flowpackhsitoty").show();
					alldata = data.alldata;
					num = data.num.max();
					lenged = data.lenged;
					packDangArray[0] =num;
					packDangArray[1] =lenged;
					packDangArray[2] =alldata;
					initHistoryLine("packhisdata",alldata[0],lenged,num);
				}
			});
		// js 阻止冒泡事件
		/* var oEvent = event || window.event;
		oEvent.stopPropagation(); */
	}
	
	function changePackDang(obj){
		$(obj).addClass("packdaohang1");
		$(obj).siblings().removeClass("packdaohang1");
		var theindex = $(obj).attr("data-index");
		initHistoryLine("packhisdata",packDangArray[2][theindex],packDangArray[1],packDangArray[0]);
	}
	
	/*主推流量包历史数据页面关闭*/
	function historyshutdown(){
		$("#flowpackhsitoty").hide();
	}
	
	
	/*查询历史数据*/
	function getRadardata(packid,hisdate){
			$.ajax({
					url:"getRadardata.do",
					type:"post",
					dataType:"json",
					data:{"date":hisdate,"zrid":packid,"regionId":regionId},
					success:function(data){
						if(data!=null){
							//initEchartRadar("radarechart",data);
							initScatter("radarechart",data);
						}else{
							$("#radarechart").html("暂无数据！！");
						};
					}
				});
	};
	
	/*div动态切换方法*/
	
	function changepage(curindex){
		if( isAnimating ) {
			return false;
		};
		if( animcursor > 67 ) {
			animcursor = 1;
		}; 
		
		nextPage(curindex,17);
		//++animcursor;
		/* if(curindex==0){
			$("#baseinfo").attr("src","situation4g.do?curdate="+curdate);
		}else if(curindex==1){
			$("#qipao").attr("src","packqipaocontroller.do?curdate="+curdate);
		}else if(curindex==2){
			$("#history").attr("src","historypackdatacontroller.do?curdate="+curdate);
		} */
		/* if($pages.eq(curindex).find(".ifr").attr("id")=="qipao"){
			$("#qipao").attr("src","packqipaocontroller.do?curdate="+curdate);
		}else{
			$("#baseinfo").attr("src","situation4g.do?curdate="+curdate);
		} */
	}
	
	function nextPage(curpageindex,animation) {
		if( isAnimating ) {
			return false;
		}

		isAnimating = true;
		
		var $currPage = $pages.eq( current );
		
		current = curpageindex;
		

		/* if( current < pagesCount - 1 ) {
			++current;
		}
		else {
			current = 0;
		} */

		var $nextPage = $pages.eq( curpageindex ).addClass( 'pt-page-current' ),
			outClass = '', inClass = '';

		switch( animation ) {

			case 1:
				outClass = 'pt-page-moveToLeft';
				inClass = 'pt-page-moveFromRight';
				break;
			case 2:
				outClass = 'pt-page-moveToRight';
				inClass = 'pt-page-moveFromLeft';
				break;
			case 3:
				outClass = 'pt-page-moveToTop';
				inClass = 'pt-page-moveFromBottom';
				break;
			case 4:
				outClass = 'pt-page-moveToBottom';
				inClass = 'pt-page-moveFromTop';
				break;
			case 5:
				outClass = 'pt-page-fade';
				inClass = 'pt-page-moveFromRight pt-page-ontop';
				break;
			case 6:
				outClass = 'pt-page-fade';
				inClass = 'pt-page-moveFromLeft pt-page-ontop';
				break;
			case 7:
				outClass = 'pt-page-fade';
				inClass = 'pt-page-moveFromBottom pt-page-ontop';
				break;
			case 8:
				outClass = 'pt-page-fade';
				inClass = 'pt-page-moveFromTop pt-page-ontop';
				break;
			case 9:
				outClass = 'pt-page-moveToLeftFade';
				inClass = 'pt-page-moveFromRightFade';
				break;
			case 10:
				outClass = 'pt-page-moveToRightFade';
				inClass = 'pt-page-moveFromLeftFade';
				break;
			case 11:
				outClass = 'pt-page-moveToTopFade';
				inClass = 'pt-page-moveFromBottomFade';
				break;
			case 12:
				outClass = 'pt-page-moveToBottomFade';
				inClass = 'pt-page-moveFromTopFade';
				break;
			case 13:
				outClass = 'pt-page-moveToLeftEasing pt-page-ontop';
				inClass = 'pt-page-moveFromRight';
				break;
			case 14:
				outClass = 'pt-page-moveToRightEasing pt-page-ontop';
				inClass = 'pt-page-moveFromLeft';
				break;
			case 15:
				outClass = 'pt-page-moveToTopEasing pt-page-ontop';
				inClass = 'pt-page-moveFromBottom';
				break;
			case 16:
				outClass = 'pt-page-moveToBottomEasing pt-page-ontop';
				inClass = 'pt-page-moveFromTop';
				break;
			case 17:
				outClass = 'pt-page-scaleDown';
				inClass = 'pt-page-moveFromRight pt-page-ontop';
				break;
			case 18:
				outClass = 'pt-page-scaleDown';
				inClass = 'pt-page-moveFromLeft pt-page-ontop';
				break;
			case 19:
				outClass = 'pt-page-scaleDown';
				inClass = 'pt-page-moveFromBottom pt-page-ontop';
				break;
			case 20:
				outClass = 'pt-page-scaleDown';
				inClass = 'pt-page-moveFromTop pt-page-ontop';
				break;
			case 21:
				outClass = 'pt-page-scaleDown';
				inClass = 'pt-page-scaleUpDown pt-page-delay300';
				break;
			case 22:
				outClass = 'pt-page-scaleDownUp';
				inClass = 'pt-page-scaleUp pt-page-delay300';
				break;
			case 23:
				outClass = 'pt-page-moveToLeft pt-page-ontop';
				inClass = 'pt-page-scaleUp';
				break;
			case 24:
				outClass = 'pt-page-moveToRight pt-page-ontop';
				inClass = 'pt-page-scaleUp';
				break;
			case 25:
				outClass = 'pt-page-moveToTop pt-page-ontop';
				inClass = 'pt-page-scaleUp';
				break;
			case 26:
				outClass = 'pt-page-moveToBottom pt-page-ontop';
				inClass = 'pt-page-scaleUp';
				break;
			case 27:
				outClass = 'pt-page-scaleDownCenter';
				inClass = 'pt-page-scaleUpCenter pt-page-delay400';
				break;
			case 28:
				outClass = 'pt-page-rotateRightSideFirst';
				inClass = 'pt-page-moveFromRight pt-page-delay200 pt-page-ontop';
				break;
			case 29:
				outClass = 'pt-page-rotateLeftSideFirst';
				inClass = 'pt-page-moveFromLeft pt-page-delay200 pt-page-ontop';
				break;
			case 30:
				outClass = 'pt-page-rotateTopSideFirst';
				inClass = 'pt-page-moveFromTop pt-page-delay200 pt-page-ontop';
				break;
			case 31:
				outClass = 'pt-page-rotateBottomSideFirst';
				inClass = 'pt-page-moveFromBottom pt-page-delay200 pt-page-ontop';
				break;
			case 32:
				outClass = 'pt-page-flipOutRight';
				inClass = 'pt-page-flipInLeft pt-page-delay500';
				break;
			case 33:
				outClass = 'pt-page-flipOutLeft';
				inClass = 'pt-page-flipInRight pt-page-delay500';
				break;
			case 34:
				outClass = 'pt-page-flipOutTop';
				inClass = 'pt-page-flipInBottom pt-page-delay500';
				break;
			case 35:
				outClass = 'pt-page-flipOutBottom';
				inClass = 'pt-page-flipInTop pt-page-delay500';
				break;
			case 36:
				outClass = 'pt-page-rotateFall pt-page-ontop';
				inClass = 'pt-page-scaleUp';
				break;
			case 37:
				outClass = 'pt-page-rotateOutNewspaper';
				inClass = 'pt-page-rotateInNewspaper pt-page-delay500';
				break;
			case 38:
				outClass = 'pt-page-rotatePushLeft';
				inClass = 'pt-page-moveFromRight';
				break;
			case 39:
				outClass = 'pt-page-rotatePushRight';
				inClass = 'pt-page-moveFromLeft';
				break;
			case 40:
				outClass = 'pt-page-rotatePushTop';
				inClass = 'pt-page-moveFromBottom';
				break;
			case 41:
				outClass = 'pt-page-rotatePushBottom';
				inClass = 'pt-page-moveFromTop';
				break;
			case 42:
				outClass = 'pt-page-rotatePushLeft';
				inClass = 'pt-page-rotatePullRight pt-page-delay180';
				break;
			case 43:
				outClass = 'pt-page-rotatePushRight';
				inClass = 'pt-page-rotatePullLeft pt-page-delay180';
				break;
			case 44:
				outClass = 'pt-page-rotatePushTop';
				inClass = 'pt-page-rotatePullBottom pt-page-delay180';
				break;
			case 45:
				outClass = 'pt-page-rotatePushBottom';
				inClass = 'pt-page-rotatePullTop pt-page-delay180';
				break;
			case 46:
				outClass = 'pt-page-rotateFoldLeft';
				inClass = 'pt-page-moveFromRightFade';
				break;
			case 47:
				outClass = 'pt-page-rotateFoldRight';
				inClass = 'pt-page-moveFromLeftFade';
				break;
			case 48:
				outClass = 'pt-page-rotateFoldTop';
				inClass = 'pt-page-moveFromBottomFade';
				break;
			case 49:
				outClass = 'pt-page-rotateFoldBottom';
				inClass = 'pt-page-moveFromTopFade';
				break;
			case 50:
				outClass = 'pt-page-moveToRightFade';
				inClass = 'pt-page-rotateUnfoldLeft';
				break;
			case 51:
				outClass = 'pt-page-moveToLeftFade';
				inClass = 'pt-page-rotateUnfoldRight';
				break;
			case 52:
				outClass = 'pt-page-moveToBottomFade';
				inClass = 'pt-page-rotateUnfoldTop';
				break;
			case 53:
				outClass = 'pt-page-moveToTopFade';
				inClass = 'pt-page-rotateUnfoldBottom';
				break;
			case 54:
				outClass = 'pt-page-rotateRoomLeftOut pt-page-ontop';
				inClass = 'pt-page-rotateRoomLeftIn';
				break;
			case 55:
				outClass = 'pt-page-rotateRoomRightOut pt-page-ontop';
				inClass = 'pt-page-rotateRoomRightIn';
				break;
			case 56:
				outClass = 'pt-page-rotateRoomTopOut pt-page-ontop';
				inClass = 'pt-page-rotateRoomTopIn';
				break;
			case 57:
				outClass = 'pt-page-rotateRoomBottomOut pt-page-ontop';
				inClass = 'pt-page-rotateRoomBottomIn';
				break;
			case 58:
				outClass = 'pt-page-rotateCubeLeftOut pt-page-ontop';
				inClass = 'pt-page-rotateCubeLeftIn';
				break;
			case 59:
				outClass = 'pt-page-rotateCubeRightOut pt-page-ontop';
				inClass = 'pt-page-rotateCubeRightIn';
				break;
			case 60:
				outClass = 'pt-page-rotateCubeTopOut pt-page-ontop';
				inClass = 'pt-page-rotateCubeTopIn';
				break;
			case 61:
				outClass = 'pt-page-rotateCubeBottomOut pt-page-ontop';
				inClass = 'pt-page-rotateCubeBottomIn';
				break;
			case 62:
				outClass = 'pt-page-rotateCarouselLeftOut pt-page-ontop';
				inClass = 'pt-page-rotateCarouselLeftIn';
				break;
			case 63:
				outClass = 'pt-page-rotateCarouselRightOut pt-page-ontop';
				inClass = 'pt-page-rotateCarouselRightIn';
				break;
			case 64:
				outClass = 'pt-page-rotateCarouselTopOut pt-page-ontop';
				inClass = 'pt-page-rotateCarouselTopIn';
				break;
			case 65:
				outClass = 'pt-page-rotateCarouselBottomOut pt-page-ontop';
				inClass = 'pt-page-rotateCarouselBottomIn';
				break;
			case 66:
				outClass = 'pt-page-rotateSidesOut';
				inClass = 'pt-page-rotateSidesIn pt-page-delay200';
				break;
			case 67:
				outClass = 'pt-page-rotateSlideOut';
				inClass = 'pt-page-rotateSlideIn';
				break;

		}

		$currPage.addClass( outClass ).on( animEndEventName, function() {
			$currPage.off( animEndEventName );
			endCurrPage = true;
			if( endNextPage ) {
				onEndAnimation( $currPage, $nextPage );
			}
		} );

		$nextPage.addClass( inClass ).on( animEndEventName, function() {
			$nextPage.off( animEndEventName );
			endNextPage = true;
			if( endCurrPage ) {
				onEndAnimation( $currPage, $nextPage );
			}
		} );

		if( !support ) {
			onEndAnimation( $currPage, $nextPage );
		}

	}

	function onEndAnimation( $outpage, $inpage ) {
		endCurrPage = false;
		endNextPage = false;
		resetPage( $outpage, $inpage );
		isAnimating = false;
	}

	function resetPage( $outpage, $inpage ) {
		$outpage.attr( 'class', $outpage.data( 'originalClassList' ) );
		$inpage.attr( 'class', $inpage.data( 'originalClassList' ) + ' pt-page-current' );
	}
	
</script>