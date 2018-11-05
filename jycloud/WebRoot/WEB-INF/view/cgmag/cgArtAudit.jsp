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
<title>文章主页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="layui/css/layui_ztf.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/template.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/admin.css" type="text/css"></link>
<link rel="stylesheet" href="css/cgArticle.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0;background: #FFF;}
#main{width: 100%;height: 100%;}
#downandupload{
	width: 100px;
	height: 40px;
	position: absolute;
	top: 0px;
	right: 10px;
	line-height: 40px;
	text-align: center;
}
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
#iptFile{
	display: none;
}
.layui-tab{
	margin: 0;
}
.cmdlist-container{
	border: 1px #EEE solid;
}
.cmdlist-text{
	padding: 10px;
}
.cmdlist-text .info{
	height: 20px;
}
.cmdlist-container img{
	height: 175px !important;
}
</style>
</head>
<body>
	<div class="layui-tab layui-tab-brief tabdiv" lay-filter="test">
		<ul class="layui-tab-title">
			<li id="look" class="layui-this" lay-id="look">文章查看</li>
		</ul>
		<div class="layui-tab-content">
			<div tabtype="look" class="layui-tab-item layui-show">
				<div id="main" class="main">
					<div class="searchDiv">
						<div class="layui-inline">
					      <label class="layui-form-label">审核状态</label>
					      <div class="layui-input-inline">
					      	<div class="layui-form">
					      		<select name="modules" lay-verify="required" lay-search="">
						          <option value="1">未审核</option>
						          <option value="5">已审核</option>
						        </select>
					      	</div>
					      </div>
					    </div>
						<div class="layui-inline">
					      <label class="layui-form-label">书法家名称</label>
					      <div class="layui-input-inline">
					        <input type="text" id="serchPhotogName" autocomplete="off" class="layui-input">
					      </div>
					    </div>
						<div class="layui-inline">
					      <div class="layui-input-inline">
					        <button class="layui-btn" onclick="cgNameSearch()" style="margin-top: 1px !important;">检索</button>
					      </div>
					    </div>
					</div>
					<div id="mainContent">
						<div id="showContent" class="layui-row layui-col-space30"></div>
					</div>
					<div id="paging" style="float:left;height:50px;width:99%;" align="middle"></div>
				</div>
			</div>
			<div tabtype="upload" class="layui-tab-item">
				<div class="top">
					<div class="filenamediv">
						<input id="iptFileName" type="text" name="filename" required lay-verify="required" title="选择文件" placeholder="请选择文件，上传文件仅限ZIP或RAR格式压缩包" autocomplete="off" class="layui-input" readonly="readonly" onclick="clickFileUpload()">
					</div>
					<div class="filediv">
						<button class="layui-btn layui-btn-normal bnt" onclick="clickFileUpload()">选择文件</button>
						<button class="layui-btn layui-btn-normal bnt" onclick="startUpload()">开始上传</button>
						<input id="iptFile" type="file" class="file" accept=".zip,.rar" onchange="getFileInfo()"/>
					</div>
					<div class="downloaddiv">
						<button class="layui-btn layui-btn-normal downloadbnt" onclick="downFilesRAR()">模板下载</button>
					</div>
				</div>
				<div id="divLogInfo" class="info"></div>
			</div>
		</div>
	</div> 
</body>
<script type="text/javascript">
	//摄影家ID
	var isFrist=true;
	var MyFile = new Object();
	MyFile.file = null;
	MyFile.name = null;
	MyFile.size = 0;
	var ot;
	var oloaded;
	var layer;
	var element;
	layui.use(["layer","element"], function(){
		layer = layui.layer;
		element = layui.element;
		//一些事件监听
		element.on("tab(test)", function(data){
			console.log(data.index);
		});
	});
	var cur_auditstatus="1";
	$(function(){
		adjust();
		searchCGArtPage(cur_auditstatus);
		layui.use('form', function(){
		  	var form = layui.form;
		  	form.render('select'); //刷新select选择框渲染
			form.on('select', function(data){
			  cur_auditstatus=data.value;
			  searchCGArtPage(cur_auditstatus);
			});
		});
	});
	function artUpload() {
		$("#artUpload").click();
		isFrist=false;
	}
	$("#showContent").niceScroll({
			cursorwidth : "4px",
			cursorcolor: "#009688",
			cursorborder: "0px"
	});
	function cgNameSearch() {
		searchCGArtPage(cur_auditstatus);
	}
	//查询总页数及分页部分
	function searchCGArtPage(auditStatus) {
		var cgName = $("#serchPhotogName").val();
		$.ajax({
			url:"searchCGArtPage.do",
			type:"post",
			dataType:"text",
			data:{
				cgName : cgName,
				auditStatus : auditStatus
			},
			success:function(data){
				layui.use(['laypage'], function(){
					laypage = layui.laypage;
					 //总页数大于页码总数
					 laypage.render({
					   elem: 'paging',
					   count: data,//数据总数
					   limit:15,
					   layout: ['count', 'prev', 'page', 'next','refresh', 'skip'],//'refresh',
					   jump: function(obj){
						     showCGArtInfo(obj.curr,auditStatus);
						}
					});	
				});
			}	
		});
	}
	//数据展示
	function showCGArtInfo(curr,auditStatus){
		var cgName = $("#serchPhotogName").val();
		$.ajax({
			url:"showCGArtInfo.do",
			type:"post",
			dataType:"json",
			data:{
				cgName : cgName,
				curr : curr,
				auditStatus : auditStatus
			},
			success:function(data){
				var htmlCode="";
				for(var a=0;a<data.articleTitle.length;a++){
					htmlCode+="<div class='layui-col-md2 layui-col-sm4'>"+
								"<div class='cmdlist-container' data-artId='"+data.articleId[a]+"' onclick='showArtInfo(this)'>"+
								"<a><img src='getCGArtCover.do?imgUrl="+data.articleCover[a]+"'></a>"+
								"<a><div class='cmdlist-text'>"+
				                "<p class='info'>"+data.articleTitle[a]+"</p>"+
				              	"</div></a>"+
				            	"</div></div>";
				}
				if (data.articleTitle.length==0&&isFrist) {
					artUpload();
				}
				$("#showContent").html("");
				$("#showContent").html(htmlCode);
			}
		});
	}
	function showArtInfo(e) {
		var articleId=$(e).attr("data-artId");
		var cgName = $("#serchPhotogName").val();
		var that = e; 
        //多窗口模式，层叠置顶
        layui.use(['layer','form'], function(){
        	layer = layui.layer;
	        layer.open({
		        type: 2 //此处以iframe举例
		        ,title: '相关文章'
		        ,area: ['1000px', '700px']
		        ,shade: 0
		        ,maxmin: true
		        ,offset: 'auto'
		        ,content: 'cgArtdetailAudit.do?articleId='+articleId+'&cgName='+encodeURI(encodeURI(cgName))+'&auditStatus='+cur_auditstatus
		        ,zIndex: layer.zIndex //重点1 
		        ,success: function(layero){
		        	
		        }
	        });
        });
	}
	$("#look").click(function() {
		pagingSearch();	
	});
	//下载文件
	function downFilesRAR() {
		window.open("downloadAmodelfile.do");
	}
	/**
	 * 触发文件上传
	 */
	function clickFileUpload(){
		$("#iptFile").click();
	}
	/**
	 * 获取文件信息，并显示文件名
	 */
	function getFileInfo(){
		var filedom = document.getElementById("iptFile");
		var file = filedom.files["0"];//console.log(file.name);console.log(file);
		$("#iptFileName").val(file.name);
		MyFile.file = file;
		MyFile.name = file.name;
		MyFile.size = file.size;
	}
	/**
	 * 开始上传
	 */
	function startUpload(){
		if(MyFile.file == null){
			alert("请先选择文件！");
			return false;
		}
		uplaodFile();
	}
	/**
	 * 上传文件
	 */
	function uplaodFile(){
		var file = MyFile.file;
		var filename = encodeURIComponent(encodeURIComponent(MyFile.name));
		var filesize = MyFile.size;
		//alert(filename);
		//var form = new FormData();
		var url = "uploadCGArtZip.do?name="+filename+"&size="+filesize;
		var xhr;
		if (window.XMLHttpRequest){
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xhr=new XMLHttpRequest();
		}
		else{
			// code for IE6, IE5
			xhr=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xhr.upload.onprogress = uploadProgress;
		var repTextSize=0;
		xhr.onreadystatechange=function(){
			if(xhr.readyState>2){
				var tmpText = xhr.responseText.substring(repTextSize);
				repTextSize = xhr.responseText.length;
				console.log(repTextSize);
				console.log(tmpText);
				if(tmpText.length > 0 ){
					initUploadLog(tmpText);
				}
			}
			if(xhr.readyState==4 && xhr.status==200){
			}
		}
		xhr.open("post", url, true);
		xhr.send(file);
	}
	
	/**
	 * 上传进度展示
	 */
	function uploadProgress(evt) {
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
	function initUploadLog(str){
		$("#divLogInfo").append(str);
		var div = document.getElementById('divLogInfo');
		div.scrollTop = div.scrollHeight;
	}
	//调整页面布局
	function adjust(){
		//alert($("#main").height());
		$(".layui-tab-content").height($(".tabdiv").height()-92)
		$("#mainContent").height($("#main").height()-65);
	};	
</script>
</html>