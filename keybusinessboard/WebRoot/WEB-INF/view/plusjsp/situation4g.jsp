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
<script type="text/javascript" src="js/echartsplus-zlb.js"></script>
<script type="text/javascript" src="js/unslider.min.js"></script>
<script type="text/javascript" src="js/pagetransition/modernizr.custom.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="js/jquery.ui.draggable.js"></script>

<link rel="stylesheet" href="css/4Gpageplus/4Gpage.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="css/pagetransition/animations.css" />

<style>

@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1501735747207'); /* IE9*/
  src: url('iconfont.eot?t=1501735747207#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('iconfont.woff?t=1501735747207') format('woff'), /* chrome, firefox */
  url('iconfont.ttf?t=1501735747207') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1501735747207#iconfont') format('svg'); /* iOS 4.1- */
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

</style>
</head>

<body>
	<div class="maindiv">
		<!-- echarts桑葚图 -->
			<div class="topleft">
				<div class="title">
					<div class="packinfo" id="divPackName"></div>
					<div class="sildertop"></div>
				</div>
				<div class="main">
					<div class="echartsdiv" id="echartsdiv"></div>
					
				</div>
			</div>
			
				<!-- 底部表格部分 -->
		<div class="mainbottom">
			<div class="title">
					<div class="packinfo">
						<span>各套餐详细情况</span>
					</div>
					
					<div class="allsum" id="allsum">
						<span class="sumName">总降档收入（元）:</span>
							<span class="sumNum"></span>
						<span class="sumName">总升档收入（元）:</span>
							<span class="sumNum"></span>
						<span class="sumName">金额（元）:</span>
							<span class="sumNum"></span>
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
									<th><div class="thright">办理量（户）</div></th>
									<th><div class="thright">降档收入（元）</div></th>
									<th><div class="thright">升档收入（元）</div></th>
									<th>金额（元）</th>
								</tr>
							</table>
						<!-- </div> -->
					</div>
					<div class="tableshuju">
						<!-- <div class="tabledata"> -->
							<table class="table2">
								
							</table>
						<!-- </div> -->
					</div>
			</div>	
		</div>
		
			<div class="topright" >
				<div class="title">
					<div class="packinfo" id="radarpackname"></div>
				</div>
				<div id="pt-main" class="pt-perspective">
						<div class="pt-page" inid="1">
							<div class="tablename">
								<table>
									<tr>
										<th rowspan="2">转入月份</th>
										<th rowspan="2">群体标识</th>
										<th colspan="2">DOU(MB)</th>
										<th colspan="2">ARPU(元)</th>
									</tr>
									<tr>
										<th>产转前</th>
										<th>产转后</th>
										<th>产转前</th>
										<th>产转后</th>
									</tr>
								</table>
							</div>
							<div class="tabledata">
							</div>
						</div>
						<div class="pt-page" inid="2" >
							<div class="divradar">
								<div id="radarechart" class="radarechart"></div>
								<div class="divfanhui" id="divfanhui" onclick="fanhuitable()"><i class="iconfont font3">&#xe6d6;</i></div>
							</div>
							
						</div>
				</div>
			</div>
			<div id="echartsdesc" class="echartsdesc">
				<div class="title"><span></span><i class="iconfont closeicon" onclick="closeechdesc()">&#xe92a;</i></div>
				<div class="content" id="echartsdesc1"></div>
			</div>
	</div>
</body>
</html>


<script type="text/javascript">
	var state="<%=request.getAttribute("state")%>";
	var iNow;
	var timer;
	var tableNum;
	var curdate="<%=request.getAttribute("curdate")%>";
	var regionId="<%=request.getAttribute("regionId")%>";
	var date1="";
	var lunbotime=false;
	
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
		$("#echartsdesc").draggable({
			cursor:"pointer"
		});
		/*动态切换开始时显示第一个div*/
		$pages.each( function() {
			var $page = $( this );
			$page.data( 'originalClassList', $page.attr( 'class' ) );
		} );
		$pages.eq(current).addClass( 'pt-page-current' );
		
	});
	
	/*详细气泡图*/
	function initDescrScatter(zcid,zrid,seriesIndex,date,regionid){
		$.ajax({
				url:"initDescrScatterPlus.do",
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
	/*关闭echarts详情套餐页面*/
	function closeechdesc(){
		$("#echartsdesc").hide();
	};
	
	/*调用气泡图页面*/
	function toQiPaoPage(){
		window.parent.openqipaodiv();
	};
	
	/*调整页面布局*/
	function adjust(){
		$(".topleft").find(".title").find(".packinfo").css("margin-top",($(".topleft").find(".title").height()*0.5)-($(".topleft").find(".title").find(".packinfo").height()*0.5));
		$(".topright").find(".title").find(".packinfo").css("margin-top",($(".topright").find(".title").height()*0.5)-($(".topright").find(".title").find(".packinfo").height()*0.5));
		$(".mainbottom").find(".title").find(".packinfo").css("margin-top",($(".mainbottom").find(".title").height()*0.5)-($(".mainbottom").find(".title").find(".packinfo").height()*0.5));
		$(".mainbottom").find(".title").find(".qipaochangediv").css("margin-top",($(".mainbottom").find(".title").height()*0.5)-($(".mainbottom").find(".title").find(".qipaochangediv").height()*0.5));
		$(".mainbottom").find(".title").find(".allsum").css("margin-top",($(".mainbottom").find(".title").height()*0.5)-($(".mainbottom").find(".title").find(".allsum").height()*0.5));
		$(".mainbottom").find(".title").find(".allsum").css("line-height",$(".mainbottom").find(".title").find(".allsum").height()+"px");
		$(".sildertop").css("margin-top",($(".topleft").find(".title").height()*0.5)-($(".topleft").find(".title").find(".sildertop").height()*0.5));
		$(".chartmaindiv").find(".title").find(".areacharttitle").css("margin-top",($(".chartmaindiv").find(".title").height()*0.5)-($(".chartmaindiv").find(".title").find(".areacharttitle").height()*0.5));
	};
	
	function main(date,regionId1){
		/*清除计时事件*/
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
	};
	
	/*查询表格中的数据*/
	function getTabledata(date1){

		$.ajax({
				url:"getdatafortablePlus.do",
				type:"post",
				dataType:"json",
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
				url:"getechartsdataPlus.do",
				type:"post",
				dataType:"json",
				async:true,
				data:{"date":date1,"id":id,"regionId":regionId},
				success:function(data){
					if(data!=null&&data.links.length!=0){
						initSankey("echartsdiv",data);
					}else{
						$("#echartsdiv").html("");
					};
				}
			});
	}
	
	function historydata(id,packname){
		$("#radarpackname").html("历史转入  "+packname+" 数据追踪");
		/*请求数据查询*/
		$.ajax({
					url:"gethistorydataPlus.do",
					type:"post",
					dataType:"json",
					data:{"zrid":id,"regionId":regionId},
					success:function(data){
						if(data!=null&&data.length!=0){
							historytableconnect(id,data);
						}else{
							$(".tabledata").html("");
						};
					}
				});
		
	}
	
	function historytableconnect(id,data){
		/*DANG 1降2升3整体*/
		var str="";
		var cishu=0;
		var j=0;
		for(var i=0;i<data.length;i++){
		/* if(data[i].date=="201705"||data[i].date=="201706"){
				if(j%2==0){
					str+="<div class='divhistory01' packid="+id+" hisdate="+data[i].date+">"+
					"<div class='divhsi1'>"+data[i].date+"</div>"+
					"<div class='divhsi2'style='color:white !important'>降档</div>"+
					"<div class='divhsi2' >"+data[i].GPLA+"</div>";
					if(data[i].GPCOM==1){
						str+= "<div class='divhsi2' style='text-align:right' ><span>"+data[i].GPNE+"</span>&nbsp;&nbsp;<i class='iconfont font1'>&#xe6db;</i>&nbsp;</div>";
					}else{
						str+= "<div class='divhsi2' style='text-align:right'><span>"+data[i].GPNE+"</span>&nbsp;&nbsp;<i class='iconfont font2'>&#xe6da;</i>&nbsp;</div>";
					};
					
					str+= "<div class='divhsi2' >"+data[i].FELA+"</div>";
					if(data[i].FECOM==1){
						str+= "<div class='divhsi2' style='text-align:right'><span>"+data[i].FENE+"</span>&nbsp;&nbsp;<i class='iconfont font1'>&#xe6db;</i>&nbsp;</div>";
					}else{
						str+= "<div class='divhsi2' style='text-align:right'><span>"+data[i].FENE+"</span>&nbsp;&nbsp;<i class='iconfont font2'>&#xe6da;</i>&nbsp;</div>";
					}; 
					j+=1;	
				}else{
					 str+="<div class='divhistory02' packid="+id+" hisdate="+data[i].date+">"+
					"<div class='divhsi1'>"+data[i].date+"</div>"+
					"<div class='divhsi2'style='color:white !important'>降档</div>"+
					"<div class='divhsi2' >"+data[i].GPLA+"</div>";
					if(data[i].GPCOM==1){
						str+= "<div class='divhsi2' style='text-align:right' ><span>"+data[i].GPNE+"</span>&nbsp;&nbsp;<i class='iconfont font1'>&#xe6db;</i>&nbsp;</div>";
					}else{
						str+= "<div class='divhsi2' style='text-align:right'><span>"+data[i].GPNE+"</span>&nbsp;&nbsp;<i class='iconfont font2'>&#xe6da;</i>&nbsp;</div>";
					};
					str+= "<div class='divhsi2' >"+data[i].FELA+"</div>";
					if(data[i].FECOM==1){
						str+= "<div class='divhsi2'style='text-align:right' ><span>"+data[i].FENE+"</span>&nbsp;&nbsp;<i class='iconfont font1'>&#xe6db;</i>&nbsp;</div>";
					}else{
						str+= "<div class='divhsi2' style='text-align:right' ><span>"+data[i].FENE+"</span>&nbsp;&nbsp;<i class='iconfont font2'>&#xe6da;</i>&nbsp;</div>";
					}; 
					j+=1;	
				}
			}else{ */
				if(j%2==0){
					str+="<div class='divhistory03' packid="+id+" hisdate="+data[i].date+">";
					str+="<div class='divhis3'>"+data[i].date+"</div>";
					for(var k=0;k<3;k++){
						if(k==0||k==2){
							str+="<div class='divhis5'>";
						}else{
							str+="<div class='divhis5' >";
						};
						if(data[i].DANG==1){
							str+="<div  class='divhis4'>降档</div>";
						}else if(data[i].DANG==2){
							str+="<div  class='divhis4'>升档</div>";
						}else{
							str+="<div  class='divhis4'>总体</div>";
						};
						str+="<div class='divhsi6'style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;'>"+data[i].GPLA+"</div>";
						if(data[i].GPCOM==1){
							str+= "<div class='divhsi6' style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;text-align:right'><span>"+data[i].GPNE+"</span>&nbsp;&nbsp;<i class='iconfont font1'>&#xe6db;</i>&nbsp;&nbsp;&nbsp;</div>";
						}else{
							str+= "<div class='divhsi6' style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;text-align:right'><span>"+data[i].GPNE+"</span>&nbsp;&nbsp;<i class='iconfont font2'>&#xe6da;</i>&nbsp;&nbsp;&nbsp;</div>";
						};
						str+= "<div class='divhsi6' style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;'>"+data[i].FELA+"</div>";
						if(data[i].FECOM==1){
							str+= "<div class='divhsi6'style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;text-align:right' ><span>"+data[i].FENE+"</span>&nbsp;&nbsp;<i class='iconfont font1'>&#xe6db;</i>&nbsp;&nbsp;&nbsp;</div>";
						}else{
							str+= "<div class='divhsi6'style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;text-align:right' ><span>"+data[i].FENE+"</span>&nbsp;&nbsp;<i class='iconfont font2'>&#xe6da;</i>&nbsp;&nbsp;&nbsp;</div>";
						}; 
						i=i+1;
						str+="</div>";
					}
					j+=1;	
					i=i-1;
				}else{
					str+="<div class='divhistory04' packid="+id+" hisdate="+data[i].date+">";
					str+="<div class='divhis3' '>"+data[i].date+"</div>";
					for(var k=0;k<3;k++){
						if(k==0||k==3){
							str+="<div class='divhis5'>";
						}else{
							str+="<div class='divhis5'>";
						};
						
						if(data[i].DANG==1){
							str+="<div  class='divhis4'>降档</div>";
						}else if(data[i].DANG==2){
							str+="<div  class='divhis4'>升档</div>";
						}else{
							str+="<div  class='divhis4'>总体</div>";
						};
						str+="<div class='divhsi6'style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;'>"+data[i].GPLA+"</div>";
						if(data[i].GPCOM==1){
							str+= "<div class='divhsi6' style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;text-align:right'><span>"+data[i].GPNE+"</span>&nbsp;&nbsp;<i class='iconfont font1'>&#xe6db;</i>&nbsp;&nbsp;&nbsp;</div>";
						}else{
							str+= "<div class='divhsi6' style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;text-align:right'><span>"+data[i].GPNE+"</span>&nbsp;&nbsp;<i class='iconfont font2'>&#xe6da;</i>&nbsp;&nbsp;&nbsp;</div>";
						};
						str+= "<div class='divhsi6' style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;'>"+data[i].FELA+"</div>";
						if(data[i].FECOM==1){
							str+= "<div class='divhsi6'style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;text-align:right' ><span>"+data[i].FENE+"</span>&nbsp;&nbsp;<i class='iconfont font1'>&#xe6db;</i>&nbsp;&nbsp;&nbsp;</div>";
						}else{
							str+= "<div class='divhsi6'style='float:left;height:40px;line-height: 40px;color:#0f9aa2;width:20%;text-align:right' ><span>"+data[i].FENE+"</span>&nbsp;&nbsp;<i class='iconfont font2'>&#xe6da;</i>&nbsp;&nbsp;&nbsp;</div>";
						}; 
						i=i+1;
						str+="</div>";
					}
					j+=1;
					i=i-1;	
				}
			
			/* } */
			str+="</div>";
		/* 	if(i%2==0){
				str+="<tr class='bcolor' packid="+id+" hisdate="+data[i].date+">";
			}else{
				str+="<tr packid="+id+" hisdate="+data[i].date+">";
			};
			if(data[i].date=="201705"||data[i].date=="201706"){
				str+="<td><div style='color:white'>"+data[i].date+"<div></td>";
			}else if(cishu==0||cishu==i){
				str+="<td rowspan='3'><div style='color:white'>"+data[i].date+"<div></td>";
				cishu=i+3;
			}; 
			if(data[i].DANG==0){
				str+="<td><span style='color:white'>降档</span></td>"+
				"<td class='tb2th' >"+data[i].GPLA+"</td>";
			}else if(data[i].DANG==1){
				str+="<td><span style='color:white'>升档</span></td>"+
				"<td class='tb2th' >"+data[i].GPLA+"</td>";
			}else{
				str+="<td><span style='color:white'>总体</span></td>"+
				"<td class='tb2th' >"+data[i].GPLA+"</td>";
			};
			if(data[i].GPCOM==1){
				str+= "<td class='tb2th' style='text-align:right' ><span>"+data[i].GPNE+"</span>&nbsp;&nbsp;<i class='iconfont font1'>&#xe6db;</i>&nbsp;</td>";
			}else{
				str+= "<td class='tb2th' style='text-align:right' ><span>"+data[i].GPNE+"</span>&nbsp;&nbsp;<i class='iconfont font2'>&#xe6da;</i>&nbsp;</td>";
			};
				str+= "<td class='tb2th' >"+data[i].FELA+"</td>";
			if(data[i].FECOM==1){
				str+= "<td class='tb2th' style='text-align:right' ><span>"+data[i].FENE+"</span>&nbsp;&nbsp;<i class='iconfont font1'>&#xe6db;</i>&nbsp;</td>";
			}else{
				str+= "<td class='tb2th' style='text-align:right' ><span>"+data[i].FENE+"</span>&nbsp;&nbsp;<i class='iconfont font2'>&#xe6da;</i>&nbsp;</td>";
			};
			str+="</tr>"; */
		};
		$(".tabledata").html(str);
		
		
		 /*绑定鼠标悬浮事件*/
		$(".divhistory01,.divhistory02,.divhistory03,.divhistory04").mouseover(function(){
				$(this).addClass("bgcolor2");
				$(this).find("div").each(function(){
					$(this).addClass("fotcolor1");
				});
			});
		$(".divhistory01,.divhistory02,.divhistory03,.divhistory04").mouseout(function(){
				$(this).removeClass("bgcolor2");
				$(this).find("div").each(function(){
					$(this).removeClass("fotcolor1");
				});
			});
			
		$(".divhistory01,.divhistory02,.divhistory03,.divhistory04").click(function(){
			var packid=$(this).attr("packid");
			var hisdate=$(this).attr("hisdate");
				getRadardata(packid,hisdate);
				/*停止底部表格轮播*/
				//clearInterval(timer);
				lunbotime=true;
			 	changepage(1);
			});
			
		$(".tabledata").niceScroll({
			cursorcolor : "#262d45",//滚动条显示的颜色
			cursorborderradius: "2px",//滚动条边角圆弧
			cursorwidth: "4px",//滚动条宽度
			cursorborder: "1px solid #293d60",//滚动条边线
			autohidemode:true//是否自动隐藏
		}); 
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
		if(state=="start"){
			beginlunbo(false);
		}else{
			endlunbo();
		};
		
	}
	
	function clicktr(obj){
		$("#echartsdesc").hide();
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
		var packname=$(obj).find("td:eq(0)").find("div").html();
		if(lunbotime){
			lunbotime=false;
			changepage(0);
		};
		/*根据id获取上面的桑基图*/
		getechartsdata(id,packname);
				/*右上角table表格*/
		historydata(id,packname);
		$(".tableshuju").getNiceScroll(0).doScrollTop(50*iNow,2000);
		if(state=="start"){
			beginlunbo(false);
		}else{
			endlunbo();
		};
	};
	
	function fanhuitable(){
		lunbotime=false;
		changepage(0);
		/*隐藏气泡详情图片*/
		$("#echartsdesc").hide();
	};
	
	/*底部表格轮播事件*/
	function beginlunbo(istrue){
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
	 };
	
		/*子页面停止轮播*/
	function endlunbo(){
		state="stop";
		clearInterval(timer);
	};
	
	
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
			str+="<td> <div class='table2div' title= "+obj.name+">"+obj.name+"</div></td>"+
					/* "<td><div class='dataNum'>"+obj.Num+"</div><div class='dataprogress' w="+Math.abs(obj.Num/20000)+"  w1="+(obj.Num/20000)+"></div></td>"+
					"<td><div class='dataNum'>"+obj.down+"</div><div class='dataprogress' w="+Math.abs(obj.down/20000)+" w1="+(obj.down/20000)+"></div></td>"+
					"<td><div class='dataNum'>"+obj.up+"</div><div class='dataprogress' w="+Math.abs(obj.up/20000)+" w1="+(obj.up/20000)+"></div></td>"+
					"<td><div class='dataNum'>"+obj.allNum+"</div><div class='dataprogress' w="+Math.abs(obj.allNum/20000)+"  w1="+(obj.allNum/20000)+"></div></td>"; */
					"<td class='tb2th'>"+obj.Num+"</td>"+
					"<td class='tb2th'>"+obj.down+"</td>"+
					"<td class='tb2th'>"+obj.up+"</td>"+
					"<td class='tb2th'>"+obj.allNum+"</td>"; 
		};
		$(".table2").html(str);
	};
	
	
	/*进度条方法*/
	function progressBar(){
			var a="#39bbff";//进度条颜色
			var b="#141422";//背景颜色
			var w=$(".dataprogress").width()/2;
			var h="5px";
			var div=$(".dataprogress");
			var barb=function(){
				div.each(function(){
					var width=$(this).attr('w');
					var barbox;
					if($(this).attr('w1')>0){
						barbox='<div class="barbox"><div class="barline"><div w="'+width+'" class="chart1" style="width:0px"><d></d></div></div></div>';
					}else{
						barbox='<div class="barbox"><div class="barline"><div w="'+width+'" class="chart2" style="width:0px"><d></d></div></div></div>';
					};
					
					$(this).append(barbox);
				});
			};
			
			var amimeat=function(){
				$(".chart1").each(function(i,item){
					var wi=parseInt($(this).attr("w"));
					$(item).animate({width: wi+"%"},1000,function(){
						//$(this).children('d').html(wi+"%"); //添加文字部分
					});
				});
					$(".chart2").each(function(i,item){
					var wi=parseInt($(this).attr("w"));
					$(item).animate({width: wi+"%"},1000,function(){
						//$(this).children('d').html(wi+"%"); //添加文字部分
					});
				});
			};
			var barbCss=function(a,b){
				$(".barbox").css({
					"height":h,
					"line-height":h,
					"color":"#fff",
					"margin":"auto 0",
   					"left":"0px",
				    "top":"0px",
				    "bottom":"0px",
				    "right":"0px",
				    "position": "absolute"
				});
				$(".barbox>div").css({
					"float":"left"
				});	
				$(".barline").css({
					"width":w,
					"background":b,
					"height":h,
					"overflow":"hidden",
					"display":"inline",
					"position":"relative",
					"border-radius":"4px",
				});
				$(".barline>d").css({
					"position":"absolute",
					"top":"0px",
				});
				$(".chart1").css({
					"background":a,
					"height":h,
					"width":"0px",
					"overflow":"hidden",
					"border-radius":"4px"
				});
					$(".chart2").css({
					"background":'red',
					"height":h,
					"width":"0px",
					"overflow":"hidden",
					"border-radius":"4px"
				});
			};
				barb();
				amimeat();
				barbCss(a,b);
	
	}
	
	/*查询历史数据雷达图*/
	function getRadardata(packid,hisdate){
			$.ajax({
					url:"getRadardataPlus.do",
					type:"post",
					dataType:"json",
					data:{"date":hisdate,"zrid":packid,"regionId":regionId},
					success:function(data){
						if(data!=null){
							/* initEchartRadar("radarechart",data); */
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