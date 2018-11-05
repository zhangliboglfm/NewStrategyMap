<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<title>上传长图</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="layui/css/layui_ztf.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/template.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/admin.css" type="text/css"></link>
<link rel="stylesheet" href="css/upLongPic.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
<script type="text/javascript" src="js/velocity.ui.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
<script type="text/javascript" src="js/upLongPic.js"></script>
<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
#main{width: 100%;height: 100%;}
@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1533979646640'); /* IE9*/
  src: url('iconfont.eot?t=1533979646640#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
  url('iconfont.ttf?t=1533979646640') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1533979646640#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.thumbImg:AFTER{
	content:"";
	position: absolute;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	background: rgba(0,0,0,0.5);
}
.show:AFTER {
	background: rgba(0, 0, 0, 0) !important;
	border: 2px solid #60B878;
	box-sizing: border-box;
}
.show2:AFTER {
	background: rgba(0, 0, 0, 0) !important;
}

.placeSave{
visibility:hidden;
}
</style>
</head>
<body>
	<div id="main" class="main">
		<div id="mainContent">
			<div id="conLeft">
				<div class="theater-thumb">
					<div id="slideContainer" class="thumb-container"></div>
				</div>
			</div>
			<div id="conRight">
				<div id="showContent"></div>
				<div id="operDiv">
					<button id="reupPic" class="layui-btn layui-btn-new3 layui-btn-new4" data-int='1' onclick="upNewPic(this);">重新上传</button>
					<button id="deletePic" class="layui-btn layui-btn-new3" style="margin-left:10px;" onclick="deleteOper();">删<a class="placeSave">删除</a>除</button>
				</div>
			</div>
		</div>
		<div id="saveAndPreview">
			<button id="previewBtn" class="layui-btn layui-btn-new layui-btn-new2" onclick="previewPic();">预<a class="placeSave">预览</a>览</button>
			<button id="saveBtn" class="layui-btn layui-btn-new" style="margin-left:10px;" onclick="saveChange();">保<a class="placeSave">保存</a>存</button>
		</div>
		<form id='fileform2' action='addNewPic.do' enctype='multipart/form-data' method='post'>
			<input id='iptFile2' style="display: none;" type='file' name='file' class='file' onchange='getFileInfo2()'/>
		</form>
	</div>
</body>
<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript">
	//书法家或摄影家ID
	var cgId = "<%=request.getAttribute("cgId")%>";
	//生平、成就、风格、文章 类型
	var bigTage = "<%=request.getAttribute("bigTage")%>";
	//传过来的ID，摄影家或者文章Id
	var flagId = "<%=request.getAttribute("flagId")%>";
	//从书法家还是摄影家跳转过来的标识
	var pgorcg = "<%=request.getAttribute("pgorcg")%>";
	//文章左侧滚动部分
	var startTransX;
	var operNum=0;
	var canSwitch = true;
	
	var layer = layui.layer;
	
	var addSlCode="<div class='thumbImg addNewPic show2' data-int='0' onclick='upNewPic(this)'>"+
						"<div id='addNewPic'><i class='iconfont' style='color:#009688;cursor: pointer;font-size: 40px;' title='新加图片' onclick='addNewPic(this)'>&#xe600;</i></div>"+
			 		"</div> "; 
	var fristShowId="";
	//作品的所有路径集合
	var strArr=new Array();
	
	//点击展示图片部分URL、Flag
	var operUrl="";//操作的图片url
	var imgUrl="";
	var imgFlagT="";//
	var imgName="";
	var showFlag="";//
	//重新上传部分长图截段
	var MyFile2 = new Object();
	MyFile2.file = null;
	MyFile2.name = null;
	//0--新加，1--重传
	var addorreup="0";
	
	$(function(){
		adjust();
		$(window).resize(function() {
			adjust();
		});
		getAllPicPath();
	});
	
	function getAllPicPath() {
		$.ajax({
			 url:"getAllPicPath.do",
			 type:"post",
			 dataType:"json",
			 data:{bigTage : bigTage,flagId : flagId,pgorcg : pgorcg},
			 success:function(data){
			 	var slideCode="";
			 	for(var i = 0;i<data.picPath.length;i++){
			 		imgName = data.artTitle[0];
			 		showFlag = data.flag[0];
			 		var strs=data.picPath[0];
					strArr=strs.split("#");
					for(var a=0;a<strArr.length;a++){
						fristShowId=flagId+"1";
						operUrl=strArr[0];
						slideCode+="<div id='"+flagId+(a+1)+"' data-url='"+strArr[a]+"' data-flag='"+showFlag+"' class='thumbImg' onclick='leftSlide(this)'>"+
						 			"<div class='slideImg'><img src='getLongImage.do?imgUrl="+strArr[a]+"&imgFlag="+showFlag+"&pgorcg="+pgorcg+"'></img></div>"+
						 			"<div class='slideText' title='"+imgName+"-"+(a+1)+"'>"+imgName+"-"+(a+1)+"</div>"+
						 		"</div> ";
					} 
			 	}
			 	slideCode+=addSlCode;
			 	$("#slideContainer").html("");
			 	$("#slideContainer").html(slideCode);
			 	$("#"+fristShowId).click();
			 }
		 });
	}
	//数组移除某一个特定元素
	Array.prototype.indexOf = function (val) {
	    for (var i = 0; i < this.length; i++) {
	        if (this[i] == val) return i;
	    }
	    return -1;
	};
	
	Array.prototype.remove = function (val) {
	    var index = this.indexOf(val);
	    if (index > -1) {
	        this.splice(index, 1);
	    }
	};
	//左侧滚动部分
	function leftSlide(obj) {
		if(!canSwitch){
			return;
		};
		isFrist=false;
		//正在操作的图片顺序
		operNum = $(obj).index();
		imgUrl=$(obj).attr("data-url");
		imgFlagT=$(obj).attr("data-flag");
		operUrl=$(obj).attr("data-url");
		if($(obj).hasClass("show")){
			return;
		};
		showBigPic(imgUrl,imgFlagT);
		$(".thumb-container").velocity({ top:(startTransX-operNum*130)},{ 
			duration: 1000,
			begin:function(){
				canSwitch = false;
				$(".show").removeClass("show");
				$(obj).addClass("show");
			},
			complete:function(){
				canSwitch=true;
			},
		});
	}
	function showBigPic(url,flag) {
		$("#showContent").html("");
		$("#showContent").html("<img class='showImg' src='getLongImage.do?imgUrl="+url+"&imgFlag="+flag+"&pgorcg="+pgorcg+"'></img>");
		$(".showImg").css("height",$("#showContent").height());	
		$(".showImg").css("width",$("#showContent").width());	
	}
	//预览拼接图
	function previewPic() {
		var str="";
		for (var a=0;a<strArr.length;a++) {
			str+="<img class='showImg' src='getLongImage.do?imgUrl="+strArr[a]+"&imgFlag="+imgFlagT+"&pgorcg="+pgorcg+"'></img>";
		}
		layui.use(['layer','form'], function(){
        	layer = layui.layer;
	        layer.open({
		        type: 1 //此处以iframe举例
		        ,title: '长图预览'
		        ,area: ['1000px', '700px']
		        ,shade: 0
		        ,maxmin: true
		        ,offset: 'auto'
		        ,content: str
		        ,zIndex: layer.zIndex //重点1 
		        ,success: function(layero){
		        	
		        }
	        });
        });
	}
	function saveChange() {
		var finalPicPath="";
		for (var a=0;a<strArr.length;a++) {
			finalPicPath+=strArr[a]+"#";
		}
		finalPicPath=finalPicPath.substring(0,finalPicPath.length-1);
		$.ajax({
			 url:"saveChange.do",
			 type:"post",
			 dataType:"text",
			 data:{bigTage : bigTage,flagId : flagId,finalPicPath : finalPicPath,pgorcg : pgorcg},
			 success:function(data){
			 		if (data>0) {
						layer.msg("保存成功");
					} else {
						layer.msg("出现未知错误,请联系维护人员");
					}
			 }
		 });
	}
	function deleteOper() {
		$("#"+flagId+(operNum+1)).remove();
		strArr.remove(operUrl);
		if (operNum==0) {
			$("#"+flagId+(operNum+2)).click();
		} else {
			$("#"+flagId+(operNum)).click();
		}
	}
	//调整页面布局	
	function adjust(){
		$("#conRight").width($("#mainContent").width()-$("#conLeft").width()-50);
		startTransX=($(".theater-thumb").height()-180)/2;
		$(".theater-thumb .thumb-container").css("top",(startTransX-operNum*116));
		$(".layui-btn-new2").css("margin-left",($("#saveAndPreview").width()-$(".layui-btn-new").width()*2-10)/2);
		$(".layui-btn-new3").css("margin-left",($("#operDiv").width()-$(".layui-btn-new3").width())/2);
		$(".layui-btn-new3").css("margin-top","10px");
		$(".layui-btn-new4").css("margin-top",($("#operDiv").height()-$(".layui-btn-new4").height()*2-20)/2);
		
	};	
</script>
</html>