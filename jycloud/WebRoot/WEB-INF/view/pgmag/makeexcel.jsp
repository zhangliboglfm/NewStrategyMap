<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生成excel</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="layui/css/layui_ztf.css" type="text/css" media="all"></link>
	<link rel="stylesheet" href="layui/css/template.css" type="text/css"></link>
	<link rel="stylesheet" href="layui/css/admin.css" type="text/css"></link>
	
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/jscommon.js"></script>
	<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
	<script type="text/javascript" src="layui/layui.js"></script>
	
	
	<style type="text/css">
		.userStyle{
			float: left;
			margin-left: 15px;
			margin-top: 30px;
		}
	</style>
	
	
	<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
	<!--[if lt IE 9]>
	  <script src="js/html5.min.js"></script>
	  <script src="js/respond.min.js"></script>
	<![endif]-->
	
  </head>
  
  <body class="layui-layout-body">
    <div class="maindiv">
    	<div class="layui-upload">
		  <button type="button" class="layui-btn layui-btn-normal userStyle" id="testList">选择作品</button> 
		  <button type="button" class="layui-btn layui-btn-normal userStyle" onclick="downExcel()">下载Excel</button>
		</div> 
    </div>
  </body>
  <script type="text/javascript">
  	  var fileName=[];
  	  var layer;
	  layui.use(['upload','layer'], function(){
		var $ = layui.jquery,
		upload = layui.upload;
		layer = layui.layer;
		var uploadListIns = upload.render({
		    elem: '#testList'
		    ,accept: 'file'
		    ,multiple: true
		    ,auto: false
		    ,choose: function(obj){   
		      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
		      var list=[];
		      for(var key in files){
			    list.push(files[key].name);
			  }
		      makeExcel(list);
		    },
		  });
		});
		function makeExcel(list) {
			if (list.length==0) {
				layer.msg("请选择文件");
				return;
			}
			$.ajax({
				 url:"downExcel.do",
				 type:"post",
				 data:{filename : JSON.stringify(list)},
				 success:function(data){
				 	
				 }
			 });
		}
	  	function downExcel() {
			window.open("downExcel2.do");
		}
	  
	  
  </script>
</html>
