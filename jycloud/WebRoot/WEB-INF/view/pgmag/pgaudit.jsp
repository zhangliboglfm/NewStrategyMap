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

<title>摄影家信息审核</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/pgaudit.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/pgaudit.js"></script>
<style type="text/css">
*{font-size: 12px;font-family: 微软雅黑;color: #1C1C1C}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0;background-color: #fff;overflow: hidden;}
</style>
</head>

<body>
	<div class="maindiv">
		<div class="line">摄影家简介<button class="layui-btn layui-btn-normal" onclick="savePgAuditStatus()">保存</button></div>
		<div class="top">
			<div class="info">
				<div class="imgdiv">
					<img id="imgPgHeadImage" alt="摄影家头像" src=""></img>
				</div>
				<div class="infodiv">
					<ul>
						<li><div class="ezchinfodiv"><div class="lb">姓名:</div><input id="iptPgName" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li><div class="ezchinfodiv"><div class="lb">国籍:</div><input id="iptPgNation" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li><div class="ezchinfodiv"><div class="lb">年代:</div><input id="iptPgYears" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li><div class="ezchinfodiv"><div class="lb">出生时间:</div><input id="iptPgBirthYears" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li><div class="ezchinfodiv"><div class="lb">去世时间:</div><input id="iptPgDeadYears" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
					</ul>
					<ul>
						<li><div class="ezchinfodiv"><div class="lb">性别:</div><input id="iptPgSex" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li><div class="ezchinfodiv"><div class="lb">修改时间:</div><input id="iptPgDealTime" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li>
							<div class="ezchinfodiv">
								<div class="lb">审核状态:</div>
								<input id="iptPgAuditState" class="inputboder" style="width: 70px;" type="text" name="pginfo" value="" readonly="readonly" onclick="changePgAuditStatus()"/>
								<div class="tips">（点击修改）</div>
							</div>
						</li>
						<li style="height: auto;width: auto">
							<div class="ezchinfodiv">
								<div class="lb">审核结果:</div>
								<textarea id="tareaAuditResult" rows="" cols="" style="width: 200px;height: 75px;resize:none" readonly="readonly"></textarea>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="other">
				<div class="lb">摄影家标签：</div>
				<div id="divPgLable" class="lbdiv"></div>
			</div>
			<div></div>
		</div>
		<div class="bottom">
			<div class="layui-tab">
				<ul class="layui-tab-title">
					<li class="layui-this">标准照</li>
					<li>生平</li>
					<li>成就</li>
					<li>风格</li>
					<li selectFlag="1">作品</li>
					<li selectFlag="2">相关文章</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<iframe id="ifrStandardImg" src="pgstandardimgaudit.do?pId=<%=request.getAttribute("pId")%>"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrLifeTime" src="pglifetime.do?pId=<%=request.getAttribute("pId")%>&jsptype=audit"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrAchievement" src="pgachievement.do?pId=<%=request.getAttribute("pId")%>&jsptype=audit"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrStyle" src="pgstyle.do?pId=<%=request.getAttribute("pId")%>&jsptype=audit"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrWorks" name="workIfr" src="pgworksaudit.do?pId=<%=request.getAttribute("pId")%>"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrRelevantArticle" name="artIfr" src="relateArticleAudit.do?photogId=<%=request.getAttribute("pId")%>"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var layer;
	var form;
	var MyFile;
	layui.use(["layer","form"], function(){
		layer = layui.layer;
		form = layui.form;
	});
	
	var updatestatus = false;
	
	var pId = "<%=request.getAttribute("pId")%>";
	var inputarr = ["iptPgName","iptPgSex","iptPgNation","iptPgYears","iptPgBirthYears","iptPgDeadYears"];
	
	var pgobj = {};
	var pgheadimgname = "";
	var pglable=[];
	
	var newpgobj={};
	var newpgheadimgname = "";
	var newpglable=[];
	
	var auditdesc="";
	$(function(){
		adjust();
		$(window).resize(adjust);
		$("body").niceScroll({
			cursorwidth : "10px",
			cursorcolor: "#B2B2B2",
			cursorborder: "0px",
			autohidemode: false
		});
		getPgData(pId);
		$(document).keydown(function(event){
			var selectFlag=$(".layui-this").attr("selectFlag");
			var topCounts = 0;
			var ifrDoc=null;
			if (selectFlag==1) {
				ifrDoc = document.getElementById("ifrWorks").contentWindow;
				topCounts = ifrDoc.topCounts;
			} else if (selectFlag==2) {
				ifrDoc = document.getElementById("ifrRelevantArticle").contentWindow;
				topCounts = ifrDoc.topCounts;
			} else {
				return
			}
			var currval = Number(ifrDoc.$(".layui-laypage-skip").find("input").val());
			if (event.ctrlKey&&event.keyCode==38) {//上
				if(currval!=1){
					ifrDoc.$(".layui-laypage-skip").find("input").val(currval-1);
				}else{
					layuiLayerMsg("已为第一页");
				}
			} else if (event.ctrlKey&&event.keyCode==40) {//下
				if(currval!=Math.ceil(Number(topCounts)/12)){
					ifrDoc.$(".layui-laypage-skip").find("input").val(currval+1);
				}else{
					layuiLayerMsg("已为最后一页");
				}
			}
			ifrDoc.$(".layui-laypage-btn").click();
		});
	});
	//页面布局调整
	function adjust(){
		var height = $(window).height();
		$(".layui-tab-content").height($(".layui-tab").height()-$(".layui-tab-title").height());
		$(".infodiv").width($(".info").width()-$(".imgdiv").width()-50);
		$(".lbdiv").width($(".other").width()-$(".other .lb").width());
	}
</script>
</html>
