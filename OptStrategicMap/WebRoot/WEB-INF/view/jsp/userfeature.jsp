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

<title>客户特征分析</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- css引入 -->
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" href="css/userfeature.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/jedate/skin/jedate.lightblue.css" type="text/css"></link>

<!-- js引入 -->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
<script type="text/javascript" src="js/velocity.ui.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="plugin/jedate/jedate.js"></script>

<style type="text/css">
*{font-family: "Microsoft YaHei";}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0px;overflow: hidden;}
.main{width: 100%;height: 100%;background:#F2F6FF;}
.main .top .topnav .curnav:AFTER{content: "▲";color: #05FFF7;cursor: pointer;display:block;font-size: 10px;margin-top:5px;}
</style>
</head>


<body>
	<div class="main">
		<div class="top">
			<div class="maintitle"><i class="iconfont iconfont1">&#xe68c;</i><span class="keyeareName">北国商城</span><span class="userfeature">客户特征分析</span></div>
			<div class="topnav">
				<div class="onenav curnav" onclick="changeNavType(this)">升档</div>
				<div class="onenav" onclick="changeNavType(this)">降档</div>
				<div class="onenav" onclick="changeNavType(this)">潜在</div>
				<div class="onenav" onclick="changeNavType(this)">流失预警</div>
			</div>
			<div class="dateSelect">
				<div class="datetypediv">
					<div id="divDayType" datetype="D" class="typediv curdiv" onclick="ChangeDateType(this)">日</div>
					<div id="divMonthType" datetype="M" class="typediv" onclick="ChangeDateType(this)">月</div>			
				</div>
				<div class="datediv">
					<input id="datepikerStrat" type="text" readonly="readonly"/><i id="iconStratDatepiker" class="iconfont">&#xe621;</i>
				</div>
				<div class="splitdiv">-</div>
				<div class="datediv">
					<input id="datepikerEnd" type="text" readonly="readonly"/><i id="iconEndDatepiker" class="iconfont">&#xe621;</i>
				</div>
			</div>
		</div>
		<div class="bottom" id="bottom">
			<div class="basicSquare">
				<div class="title"><i class="iconfont" style="color:#2FC1FF;font-size: 26px;">&#xe62b;</i><span>性别比例</span></div>
				<div class="squareContent"></div>
			</div>
			<div class="basicSquare">
				<div class="title"><i class="iconfont" style="color:#FD9A28;font-size: 20px;">&#xe6fc;</i><span>年龄分布</span></div>
				<div class="squareContent"></div>
			</div>
			<div class="basicSquare">
				<div class="title"><i class="iconfont" style="color:#0EFFFD;font-size: 24px;">&#xe634;</i><span>本地/外地</span></div>
				<div class="squareContent"></div>
			</div>
			<div class="basicSquare">
				<div class="title"><i class="iconfont" style="color:#789BFD;font-size: 18px;">&#xe610;</i><span>用户状态</span></div>
				<div class="squareContent"></div>
			</div>
			<div class="basicRect">
				<div class="title"><i class="iconfont" style="color:#FE6F10;font-size: 20px;">&#xe609;</i><span>入网时长</span></div>
				<div class="rectContent"></div>
			</div>
			<div class="basicRect">
				<div class="title"><i class="iconfont" style="color:#EDAB41;font-size: 21px;">&#xe647;</i><span>归属地</span></div>
				<div class="rectContent"></div>
			</div>
			<div class="basicRect">
				<div class="title"><i class="iconfont" style="color:#F0514F;font-size: 20px;">&#xe65d;</i><span>号码等级</span></div>
				<div class="rectContent"></div>
			</div>
			<div class="basicSquare">
				<div class="title"><i class="iconfont" style="color:#2DA8FE;font-size: 20px;">&#xe642;</i><span>消费方式</span></div>
				<div class="squareContent"></div>
			</div>
			<div class="basicSquare">
				<div class="title"><i class="iconfont" style="color:#F0514F;font-size: 22px;">&#xe64c;</i><span>出行方式</span></div>
				<div class="squareContent"></div>
			</div>
			<div class="basicSquare">
				<div class="title"><i class="iconfont" style="color:#2DA8FE;font-size: 20px;">&#xe625;</i><span>流量费占比</span></div>
				<div class="squareContent"></div>
			</div>
			<div class="basicSquare">
				<div class="title"><i class="iconfont" style="color:#2FC1FF;font-size: 18px;">&#xe694;</i><span>网络</span></div>
				<div class="squareContent"></div>
			</div>
			<div class="basicRect">
				<div class="title"><i class="iconfont" style="color:#FE6F10;font-size: 22px;">&#xe63c;</i><span>消费</span></div>
				<div class="rectContent"></div>
			</div>
			<div class="basicRect">
				<div class="title"><i class="iconfont" style="color:#789BFD;font-size: 24px;">&#xe72d;</i><span>流量费</span></div>
				<div class="rectContent"></div>
			</div>
			<div class="basicRect">
				<div class="title"><i class="iconfont" style="color:#1CBCFF;font-size: 26px;">&#xe620;</i><span>计费流量</span></div>
				<div class="rectContent"></div>
			</div>
			<div class="theLast">
				<div class="title">
					<i class="iconfont" style="color:#0AADFF;font-size: 26px;">&#xe70c;</i>
					<span>客户详细信息</span>
					<div class="tableDownLoad">
						<i class="iconfont" style="font-size:10px;height:20px;line-height:20px;margin-left: 5px;">&#xe622;</i>
						<span style="font-size:10px;height:20px;line-height: 20px;margin-right: 8px;">下载</span>
					</div>
				</div>
				<div class="tabletitle">
					<table class="table1">
						<tr>
							<th><div>姓名</div></th>
							<th><div>号码</div></th>
							<th><div>归属地</div></th>
							<th><div>套餐</div></th>
							<th><div style="border: 0px;">资费</div></th>
						</tr>
					</table>
				</div>
				<div id="tablecontent" class="tablecontent">
					<table class="table2">
						<tr>
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr class="hascolor">
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr>
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr class="hascolor">
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr>
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr class="hascolor">
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr>
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr class="hascolor">
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr>
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr class="hascolor">
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr>
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr class="hascolor">
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr>
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
						<tr class="hascolor">
							<td>姓名</td>
							<td>号码</td>
							<td>归属地</td>
							<td>套餐</td>
							<td>资费</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
    
    var dateType="D";
	var dateStartD="2018-01-01";
	var dateStartM="2018-01";
	var dateEndD="2018-01-01";
	var dateEndM="2018-01";

	var DTypeFormat = "YYYY-MM-DD";
	var MTypeFormat = "YYYY-MM";
		
	/* 日期选择控件配置JSON */
	var jedateStartOption = {
		dateCell: "#datepikerStrat",
		format: DTypeFormat,
		choosefun: function(elem, datas) {
		}
	};
	var jedateEndOption = {
		dateCell: "#datepikerEnd",
		format: DTypeFormat,
		choosefun: function(elem, datas) {
		}
	};
	
	jeDate(jedateStartOption);
	jeDate(jedateEndOption);

	$("#datepikerStrat").val(dateStartD);
	$("#datepikerEnd").val(dateEndD);
	
	
	$(function(){
		adjust();
		
		$("#bottom").niceScroll({
			cursorcolor : "#C9E9FF",//滚动条显示的颜色
			cursorborderradius: "2px",//滚动条边角圆弧
			cursorwidth: "4px",//滚动条宽度
			cursorborder: "0px solid #293d60",//滚动条边线
			autohidemode:true//是否自动隐藏
		});
		
		$("#tablecontent").niceScroll({
			cursorcolor : "#C9E9FF",//滚动条显示的颜色
			cursorborderradius: "1px",//滚动条边角圆弧
			cursorwidth: "2px",//滚动条宽度
			cursorborder: "0px solid #293d60",//滚动条边线
			autohidemode:true//是否自动隐藏
		});
		
		$("#iconStratDatepiker").click(function(){
			jeDate(jedateStartOption);
		});
		$("#iconEndDatepiker").click(function(){
			jeDate(jedateEndOption);
		});
		
	});
	
	function adjust(){
		$(".topnav").css("left",($(".top").width()-364)/2);
		$(".main .bottom").height($(".main").height()-60);
		$(".main .bottom").css("max-height",$(".main").height()-50+"px");
		$(".basicSquare .squareContent").height($(".main .bottom").width()*0.13);
		$(".basicRect .rectContent").height($(".main .bottom").width()*0.15);
		$(".squareContent").height($(".basicSquare").height()-30);
	}
	
	/*升降流失预警切换*/
	function changeNavType(obj){
		$(".curnav").removeClass("curnav");
		$(obj).addClass("curnav");
	}
	
		/* 切换日期类型 */
	function ChangeDateType(e){
		$(".curdiv").removeClass("curdiv");
		$(e).addClass("curdiv");
		dateType=$(e).attr("datetype");
		if(dateType=="D"){
			jedateStartOption.format=DTypeFormat;
			jedateEndOption.format=DTypeFormat;
			$("#datepikerStrat").val(dateStartD);
			$("#datepikerEnd").val(dateEndD);
		}else if(dateType=="M"){
			jedateStartOption.format=MTypeFormat;
			jedateEndOption.format=MTypeFormat;
			$("#datepikerStrat").val(dateStartM);
			$("#datepikerEnd").val(dateEndM);
		}
	}
</script>
</html>
