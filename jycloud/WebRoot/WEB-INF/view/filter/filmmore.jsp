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
  		.layui-inline{
			margin-top: 4px;
			float: left;
		}
		
		button{
			margin-top: 1px !important;
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
	<div id="filmmore">
    		<div style="width:100%;height:14%;">
		      		<div class="layui-input-inline" style="left:35%;top:10%">
		      			<input id="moreinput" value="">
		      		</div>
		      		<div class="layui-input-inline" style="left:40%;top:10%">
		      			<button id="cscreen2" onclick="cscreen2();" class="layui-btn layui-btn-xs layui-btn-normal" style="height: 32px;width: 50px">查询</button>
		      		</div>
    		</div>
    		<div style="width:80%;height:60%;margin-left: 50px;">
    			<ul id="moreul">
    				<li><input  type="checkbox" value="富士" name="cameracheck">富士</li>
    			</ul>
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
	
	
	var id = "<%=request.getAttribute("id")%>";
	/* 
	$("#moreinput").val("");
	var	mores=id;
	var w="";
	var data=[];
	var keyword="";
	var titlename = "";
	if(id=="camerab"){
			$("#titlediv").html("");
			//$("#titlediv").html("相机型号");
			titlename="相机型号";
			data=getCamera(keyword);
	}
	if(id=="filmb"){
		$("#titlediv").html("");
		//$("#titlediv").html("胶片型号");
		titlename="胶片型号";
		data=getFilm(keyword);
	}
	if(id=="lensb"){
		$("#titlediv").html("");
		//$("#titlediv").html("镜头型号");
		titlename="镜头型号";
		data=getLens(keyword);
	}
	if(data!=""&&data!=null){
		for(var i=0;i<data.length;i++){
			w+="<li><input  type='checkbox' value="+data[i].PROD_ID+" name='cameracheck'>"+data[i].PROD_ID+"</li>";
		}
	}
	$("#moreul").html("");
	$("#moreul").html(w);
		 //$("#background").show();
		//$("#more").show(); 
	
	 */
	
	function cscreen2(){
		var w="";
		var data=[];
		var keyword=$("#moreinput").val();
		var mores=id;
		if(mores=="camerab"){
			data=getCamera(keyword);
		}
		if(mores=="filmb"){
			data=getFilm(keyword);
		}
		if(mores=="lensb"){
			data=getLens(keyword);
		}
		for(var i=0;i<data.length;i++){
			w+="<li><input  type='checkbox' value="+data[i].PROD_ID+" name='cameracheck'>"+data[i].PROD_ID+"</li>";
		}
		$("#moreul").html("");
		$("#moreul").html(w);
	}
	
	function getstrenter(){
	 	var str="";
		$("#flimmore ul li input").each(function(){
			if($(this).is(':checked')){
				str+=$(this).val()+",";
			}
		});
		return str;
	}
	$(function(){
		$("body").niceScroll({
			cursorwidth : "10px",
			cursorcolor: "#B2B2B2",
			cursorborder: "0px",
			autohidemode: false
		});
		
	});
	
</script>
</html>
