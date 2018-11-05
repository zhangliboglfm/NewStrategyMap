<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>文章解读</title>
<link rel="stylesheet" href="layui/css/layui_ztf.css" type="text/css"></link>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
<style>
	html {
		width: 100%;
		height: 100%;
	}
	
	body {
		width: 100%;
		height: 100%;
		margin: 0;
		padding: 0
	}
	#main{
		width: 100%;
		height: 100%;
		margin: 0;
		padding: 0
	}
	#reuplodJiedu{
		width: 100px;
		height: 30px;
		position: absolute;
		right: 10px;
		top: 5px;
	}
	#iptFile2{
		display: none;
	}	
</style>
</head>
<body>
	<div id="main">
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
			<ul class="layui-tab-title">
				<li data-id="wordJD" class="layui-this">Word文档</li>
				<li data-id="imgJD">图片</li>
				<li data-id="h5JD">H5链接</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show scroll">
					<iframe id="myiframe1" name="myiframe" src="" frameborder="0" style="float:left;width:100%;margin:0px;padding:0px;height: 100%;"></iframe>
				</div>
				<div class="layui-tab-item scroll">
					<iframe id="myiframe2" name="myiframe" src="" frameborder="0" style="float:left;width:100%;margin:0px;padding:0px;height: 100%;">
					</iframe>
				</div>
				<div class="layui-tab-item scroll">
					<iframe id="myiframe3" name="myiframe" src="" frameborder="0" style="float:left;width:100%;margin:0px;padding:0px;height: 100%;"></iframe>
				</div>
			</div>
		</div>
		<div id="reuplodJiedu">
			<button class="layui-btn layui-btn2 layui-btn-normal" onclick="workAdjust();">重新上传</button>
		</div>
		<form id='fileform2' action='reuploadJDu_ART.do' enctype='multipart/form-data' method='post'>
			<input id='iptFile2' type='file' name='file' class='file' onchange='getFileInfo2()'/>
		</form>
	</div>
</body>
<script type="text/javascript">
	var articleId = "<%=request.getAttribute("articleId")%>";
	var layId = "<%=request.getAttribute("layId")%>";
	var h5path = "<%=request.getAttribute("h5path")%>";
	var MyFile2 = new Object();
	MyFile2.file = null;
	MyFile2.name = null;
	var xuanlei,fileSuffix;
	$(function () {
		adjust();
		//滚动条部分设置
		$(".scroll").niceScroll({
			cursorcolor : "#40C5C0",//滚动条显示的颜色
			cursorborderradius: "2px",//滚动条边角圆弧
			cursorwidth: "4px",//滚动条宽度
			cursorborder: "1px solid #40C5C0",//滚动条边线
			autohidemode:true//是否自动隐藏
		});		
		$("#myiframe1").attr("src","JieduWordART.do?articleId="+articleId+"&layId="+layId);
		$("#myiframe2").attr("src","JieduPicture.do?articleId="+articleId+"&layId="+layId);
		$("#myiframe3").attr("src",h5path);
	});
	layui.use('element', function(){
		  var $ = layui.jquery,
		  element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
		  console.log(element);
	})
	function workAdjust() {
		xuanlei=$(".layui-this").attr("data-id");
		/* if (xuanlei=="wordJD") {
			alert(xuanlei);
			$("#iptFile2").attr("accept","*.doc,*.docx");
		} else if(xuanlei=="wordJD"){
			$("#iptFile2").attr("accept","image/*");
		} */
		$("#iptFile2").click();
	}
	function getFileInfo2(){
		var filedom = document.getElementById("iptFile2");
		var file = filedom.files["0"];
		MyFile2.file = file;
		MyFile2.name = file.name;
		var filename=file.name;
		fileSuffix = filename.substring(filename.lastIndexOf(".") + 1);
		if(MyFile2.file == null){
			alert("请先选择文件！");
			return false;
		}
		if (xuanlei=="wordJD") {
			if(fileSuffix!="doc"&&fileSuffix!="docx"){
				alert("请上传Word文件！");
				return false;
			}
		} else if(xuanlei=="imgJD"){
			if(fileSuffix!="png"&&fileSuffix!="jpg"){
				alert("请上传png或jpg格式图片！");
				return false;
			}
		}
		uplaodFile2();
	}
	/**
	 * 上传文件
	 */
	function uplaodFile2(){
		var file = MyFile2.file;
		var filename = MyFile2.name;
		filename = encodeURI(filename);
		var options = {
			url:"reuploadJDu_ART.do",
			async:false,
			type:"post",
			dataType:"json",
			data:{
				filename : filename,
				articleId : articleId,
				xuanlei : xuanlei,
				layId:layId
			},
	        success: function (data) {
	        	if (data.flag[0]==1) {
					if (xuanlei=="wordJD") {
						$("#myiframe1").attr("src","JieduWordART.do?articleId="+articleId+"&layId="+layId);
					} else if(xuanlei=="imgJD"){
						$("#myiframe2").attr("src","JieduPicture.do?articleId="+articleId+"&layId="+layId);
					} else if(xuanlei=="h5JD"){
						
					}
				} else {
					alert("上传失败，请稍后再试！")
				}
	       	}
		};
	    $("#fileform2").ajaxSubmit(options);
	}	
	function adjust() {
		$(".layui-tab-content").width($(".layui-tab").width()-20);
		$(".layui-tab-content").height($("#main").height()-62);
	}
</script>
</html>