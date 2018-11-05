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

<title>成就</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" href="css/pgachievement.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/pgachievement.js"></script>

<style type="text/css">
*{font-size: 12px;font-family: 微软雅黑;color: #1C1C1C}
html{width: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0;background-color: #fff;}
#showImgD{
	width: 70%;
	height: 100%;
	float: left;
	margin-left: 15%;
}
#shadow{
	width: 100%;
	height: 100%;
	position: absolute;
	background-color: rgba(0,0,0,0.5);
	z-index: 99;
	top: 0px;
	display: none;
}

#loadIcon{
	width: 80px;
	height: 80px;
	position: absolute;
}

#loadIcon img{
	width: 100%;
	height: 100%;
}
</style>
</head>

<body>
	<div class="maindiv">
		<div class="layui-tab layui-tab-brief tabdiv" lay-filter="test">
			<div class="rightdiv">
				<i class="iconfont" onclick="download()">&#xe659;</i>
				<i class="iconfont upClass" style="margin-left: 20px;" onclick="uplaod()">&#xe658;</i>
				<input type="file" id="iptWordFile" accept=".doc" onchange="uploadWord()"/>
				<input type="file" id="iptPicFile" accept=".jpg,.png,.bmp" onchange="uploadPic()"/>
			</div>
			<ul class="layui-tab-title">
				<li class="layui-this">文档</li>
				<li>图片</li>
				<li>H5</li>
			</ul>
			<div class="layui-tab-content">
				<div tabtype="word" class="layui-tab-item layui-show">
					<iframe id="ifrWord" src=""></iframe>
				</div>
				<div tabtype="img" class="layui-tab-item">
					<div id="showImgD"></div>
				</div>
				<div tabtype="h5" class="layui-tab-item">
					<iframe id="ifrH5" src=""></iframe>
				</div>
			</div>
		</div> 
		<div id="shadow">
			<div id="loadIcon">
				<img src="image/loading.gif"></img>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var layer;
	var element;
	var cur_sel = 0;
	var jsptype = "<%=request.getAttribute("jsptype")%>";
	var pId = "<%=request.getAttribute("pId")%>";
	var aId = "<%=request.getAttribute("artid")%>";
	var artname = "<%=request.getAttribute("artname")%>";
	var arttitle = "<%=request.getAttribute("arttitle")%>";
	var artpic = "<%=request.getAttribute("artpic")%>";
	var arturl = "<%=request.getAttribute("arturl")%>";
	layui.use(["layer","element"], function(){
		layer = layui.layer;
		element = layui.element;
		//一些事件监听
		element.on("tab(test)", function(data){
			if(data.index != cur_sel){
				ReFlushInfo();
				cur_sel = data.index;
			}
		});
	});
	$(function(){
		adjust();
		$(window).resize(adjust);
		if(jsptype=="audit"){
			$(".rightdiv").find(".upClass").remove();
		}
		if(artname!="" && artname!="null" && artname!="undefined"){
			$("#ifrWord").attr("src","showpgachievementword.do?pId="+pId+"&wordName="+artname);
		}
		if(artpic!="" && artpic!="null" && artpic!="undefined"){
			var strArr=new Array();
			strArr=artpic.split("#");
			var str="";
			for (var a=0;a<strArr.length;a++) {
				str+="<img class='showImg' src='getPGImgStream.do?filename="+strArr[a]+"'></img>";
			}
			$("#showImgD").html("");
			$("#showImgD").html(str);
			$(".showImg").css("width","100%");	
			//$("#imgPic").attr("src","getPGImgStream.do?filename="+artpic);
		}
		if(arturl!="" && arturl!="null" && arturl!="undefined"){
			$("#ifrH5").attr("src",arturl);
		}
	});
	//页面布局调整
	function adjust(){
		var height = $(window).height();
		$(".layui-tab-content").height($(".tabdiv").height()-$(".layui-tab-title").height());
		$("#loadIcon").css("left",($("#shadow").width()-80)/2);
		$("#loadIcon").css("top",($("#shadow").height()-80)/2);
	}
</script>
</html>
