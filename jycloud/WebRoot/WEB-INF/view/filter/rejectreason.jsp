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

<title>样片详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/pgaudit.js"></script>
<script type="text/javascript" src="js/picmanage.js"></script>

<link rel="stylesheet" type="text/css" href="css/picmanage.css"></link>
  	<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
  	<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
  	<style type="text/css">
  		li{
  			margin: 8px;
  			margin-top: 15px;
  			
  		}
  		#input{
  			zoom: 110%;
    		vertical-align: middle;
  		}
  		.layui-inline{
			margin-top: 4px;
			float: left;
		}
		
		
		
		input:not([type="checkbox" i]){
			height: 32px !important;
			/* margin-top: 2px !important; */
			padding-left: 10px;
			
			border-color: #e6e6e6 !important;
			border-width: 1px;
     		border-style: solid ;
    
    		border-radius: 2px;
    
    		border-color: #e6e6e6;
			
		}
  	</style>
</head>

<body>
	<div style="width:80%;height:60%;margin-left: 20%;">
    			<ul id="reasonul">
    				<li><span><input  type="checkbox" value="尺寸不匹配" name="cameracheck"></span>尺寸不匹配</li>
    				<li><input  type="checkbox" value="信息较少" name="cameracheck">信息较少</li>
    				<li><input  type="checkbox" value="风格不匹配" name="cameracheck">风格不匹配</li>
    				<li><input  type="checkbox" value="无特点" name="cameracheck">无特点</li>
    				<li><input  type="checkbox" value="作者不明" name="cameracheck">作者不明</li>
    				<li><input  type="checkbox" value="无意义" name="cameracheck">无意义</li>
    			</ul>
    		</div>
    		<div style="width:100%;height:20%;margin-top: 20px;text-align: center;">自定义原因:<input style="margin-left: 10px" id="reasonPut" type="text"></div>
    		
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
	
	var id = "<%=request.getAttribute("id")%>";
	var data=getOnePic(id);
	
	getDismissal();
	
	$(function(){
		$("body").niceScroll({
			cursorwidth : "10px",
			cursorcolor: "#B2B2B2",
			cursorborder: "0px",
			autohidemode: false
		});
		
	});
	
	function getId() {
		var reId="";
		$("#reasonul input[name=dismissalcheck]:checked").each(function(){
			reId+=$(this).attr("data-id")+",";
		});
		return reId;
	}
	function getcontent() {
		var content=$("#reasonPut").val().trim();
		return content;
	}
	
</script>
</html>
