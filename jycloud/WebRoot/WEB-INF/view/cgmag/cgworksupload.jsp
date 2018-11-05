<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>书法家作品上传</title>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/importphotographer.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/importphotographer.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
</style>
</head>
<body class="layui-layout-body">
	<div class="top">
		<div class="filenamediv">
			<input id="iptFileName" type="text" name="filename" required lay-verify="required" title="选择文件" placeholder="请选择文件，上传文件仅限ZIP或RAR格式压缩包" autocomplete="off" class="layui-input" readonly="readonly" onclick="clickFileUploadCG()">
		</div>
		<div class="filediv">
			<button class="layui-btn layui-btn-normal bnt" onclick="clickFileUploadCG()">选择文件</button>
			<button class="layui-btn layui-btn-normal bnt" onclick="startUploadCG()">开始上传</button>
			<input id="iptFile" type="file" class="file" accept=".zip,.rar" onchange="getFileInfoCG()"/>
			
		</div>
		<div class="downloaddiv">
			<button class="layui-btn layui-btn-normal downloadbnt" onclick="downLoadModelFileCG()">模板下载</button>
		</div>
	</div>
	<div id="divLogInfo" class="info" style="height: 450px;">
	</div>
</body>
<script type="text/javascript">
	var cgerId = "<%=request.getAttribute("cgerId")%>";
	var MyFile = new Object();
	MyFile.file = null;
	MyFile.name = null;
	MyFile.size = 0;
	var ot;
	var oloaded;
	$(function(){
		adjust();
		$(window).resize(adjust);
		$("#divLogInfo").niceScroll({
			cursorwidth : "5px",
			cursorcolor: "#1E9FFF",
			cursorborder: "0px",
			autohidemode:true
		});
	});
	//点击上传
	function clickFileUploadCG(){
		$("#iptFile").click();
	}
	/**
	 * 获取文件信息，并显示文件名
	 */
	function getFileInfoCG(){
		var filedom = document.getElementById("iptFile");
		var file = filedom.files["0"];//console.log(file.name);console.log(file.size);
		$("#iptFileName").val(file.name);
		MyFile.file = file;
		MyFile.name = file.name;
		MyFile.size = file.size;
	}	
	/**
	 * 开始上传
	 */
	function startUploadCG(){
		if(MyFile.file == null){
			alert("请先选择文件！");
			return false;
		}
		$("#shadow").show();
		uplaodFileCG();
	}
	/**
	 * 上传文件
	 */
	function uplaodFileCG(){
		var file = MyFile.file;
		var filename = MyFile.name;
		var filesize = MyFile.size;
		//var form = new FormData();
		var url = "uploadcgworkmessage.do?name="+encodeURI(encodeURI(filename))+"&size="+filesize+"&cgerId="+cgerId;
		var xhr;
		if (window.XMLHttpRequest){
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xhr=new XMLHttpRequest();
		}
		else{
			// code for IE6, IE5
			xhr=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xhr.upload.onprogress = uploadProgressCG;
		var repTextSize=0;
		xhr.onreadystatechange=function(){
			if(xhr.readyState>2){
				var tmpText = xhr.responseText.substring(repTextSize);
				repTextSize = xhr.responseText.length;
				console.log(repTextSize);
				console.log(tmpText);
				if(tmpText.length > 0 ){
					initUploadLogCG(tmpText);
				}
			}
			if(xhr.readyState==4 && xhr.status==200){
				$("#shadow").hide();
			}
		}
		xhr.open("post", url, true);
		xhr.send(file);
	}
	/**
	 * 上传进度展示
	 */
	function uploadProgressCG(evt) {
		if (evt.lengthComputable) {
			var percentComplete = Math.round(evt.loaded * 100.0 / evt.total);
			$("#divLogInfo").html("文件已上传："+percentComplete+"%"+"<br>");
			if(percentComplete == 100){
				$("#divLogInfo").append("文件上传完毕，开始校验"+"<br>");
			}
		} else {
		}
	}
	/**
	 * 记录上传日志
	 */
	function initUploadLogCG(str){
		$("#divLogInfo").append(str);
		var div = document.getElementById('divLogInfo');
		div.scrollTop = div.scrollHeight;
	}
	/**
	 * 下载模板文件
	 */
	function downLoadModelFileCG(){
		window.open("downloadCGWorksfile.do");
	}	
	//调整页面布局
	function adjust(){
	};
</script>
</html>