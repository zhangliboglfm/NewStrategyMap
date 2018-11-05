<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>

<title>上传下载demo</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/miapsoft.orgchart.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
  	$(function(){
  	
  	});
  	function upload(){
  		var options={
			url:"upload.do",
			type:"post",
			success:function(data){
				alert(data);
			}
		};
		$("#uploadform").ajaxSubmit(options);
  	}
  	function download(e){
  		var name = $(e).attr("data-name");
  		window.open("download.do?path="+encodeURI(encodeURI(name)));
  	}
  </script>
</head>

<body>
	<form id="uploadform" method="post" enctype="multipart/form-data">
		<div
			style="float:left; height:6%;width:30%;text-align: center;line-height: 40px;color:black">&nbsp;图片预览：</div>
		<input id="value11"
			style="float:left; width:50%;height:6%;margin-top: 8px " type="file"
			name="image"> <input type="button"
			value="上&nbsp;&nbsp;&nbsp;&nbsp;传"
			style="float:left;width:15%;height:4%;margin-top: 7px;margin-left: 1px"
			onclick="upload()">
	</form>
	<a href="javascript:void()" data-name="jQuery插件开发.docx" onclick="download(this)">测试文档</a>
</body>
</html>
