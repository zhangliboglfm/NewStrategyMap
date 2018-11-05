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
<title>文章详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="layui/css/layui_ztf.css" type="text/css"></link>
<link rel="stylesheet" href="css/articleDetail.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/template.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/admin.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
<script type="text/javascript" src="js/Sortable.min.js"></script>
<script type="text/javascript" src="js/velocity.ui.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
<script type="text/javascript" src="js/articleDetail.js"></script>
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
.userdefined {
    height: 30px;
    line-height: 30px;
    margin: 5px;
    width: 70px;
    color: white;
    background: #60B878;
    border-radius: 2px;
    text-align: center;
    cursor: pointer;
}

.cmdlist-text {
	padding: 10px
}

.cmdlist-text .info {
	height: 20px;
	font-size: 14px;
	line-height: 20px;
	width: 100%;
	overflow: hidden;
	color: #666;
}
.speacilImg{
	width: 160px !important;
	height: 160px !important;
}
#showImgD{
	width: 70%;
	height: 100%;
	float: left;
	margin-left: 15%;
}
.bnt{
	margin-top: 10px;	
}
.addNewInfo{
	display: none !important;
}
.deletePhotog{
	display: none !important;
}
.layui-col-sm46{
	width: 180px !important;
	padding: 15px;
}
.customCgImg{
	width: 148px !important;
}
.customCgImg img{
	height: 206px !important;
	width: 148px !important;
}
.radiodiv{width: 545px;height: 50px;margin-left: 25px;}
.auditdescdiv{width: 545px;height: auto;margin-left: 25px;margin-top: 10px;}
.auditdescdiv .title{width: 545px;height: 29px;margin-top: 10px;line-height: 29px;}
.customReason{
	width: 100%;
	height: 100px;
}

.customDiv{
	width: 100px;
	height: 40px;
	float: left;
}
#tareadesc{
	width: 400px;
	height: 80px;
	float: left;
	margin-top: 10px;
}
.auditdescdiv .title{
	font-size: 16px;
	font-family: 微软雅黑;
}
.passTitle{
	height: 50px;
	font-size: 16px;
	font-family: 微软雅黑;
	line-height: 50px;
	background-color: #f2f2f2;
	margin-top: 5px;
	text-indent: 24px;	
}
.customCss{
	margin-top: 1px !important;
	float: right;
	margin-top: 5px !important;
	margin-right: 10px;
}
.customCss i{
	visibility:hidden;
}
.cusDiv{
	width: 192px !important;
	height: 240px;
	padding: 15px;
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
				<div id="conDetail">
					<div id="artileTitle">
						<div id="artCover" onclick="reuploadFile(this)" data-flag="artTitle"><img id="coverImg" style="width: 100%;height: 100%;"></img></div>
						<div id="artDetail">
							<div class="titleName titleFlag">
								<div class="titleDesc">文章标题&nbsp;:</div>
								<div id="artTitle" class="articleInfo" contenteditable="true"></div>
							</div>
							<div class="titleName titleName3">
								<div class="stateP">审核状态&nbsp;:</div>
								<div id="artState" class="stateP stateP2"></div>
							</div>
						</div>
					</div>
					<div id="artPhotog" class="photogWorks">
						<div class="photogWorksTitle">书法家&nbsp;:</div>
						<div id="photogList"></div><!-- class="phoWorksInfo layui-row" -->
						<div class='addNewInfo'>
							<i class='iconfont' style='color:#009688;cursor: pointer;font-size: 40px;' data-flag='photog' title='新加摄影家关联' onclick='addNewWorks(this)'>&#xe600;</i>
					   </div>
					</div>
					<div id="artWorks" class="photogWorks">
						<div class="photogWorksTitle">书法作品&nbsp;:</div>
						<div id="worksList"></div> 
						<div class='addNewInfo'>
							<i class='iconfont' style='color:#009688;cursor: pointer;font-size: 40px;' data-flag='works' title='新加作家关联' onclick='addNewWorks(this)'>&#xe600;</i>
					   </div>
					</div>
					<div id="artExplain" class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
						<ul class="layui-tab-title">
							<li data-id="wordReading" class="layui-this">文档</li>
							<li data-id="imgReading">图片</li>
							<li data-id="h5Reading">H5</li>
						</ul>
						<div class="layui-tab-content">
							<div class="layui-tab-item layui-show"  style="height: 100%;">
								<iframe id="myiframe1" name="myiframe" src="" frameborder="0" style="float:left;width:100%;margin:0px;padding:0px;height: 100%;"></iframe>
							</div>
							<div class="layui-tab-item" style="height: 100%;">
								<div id="showImgD"></div>
							</div>
							<div class="layui-tab-item" style="height: 100%;">
								<iframe id="myiframe3" name="myiframe" src="" frameborder="0" style="float:left;width:100%;margin:0px;padding:0px;height: 100%;"></iframe>
							</div>
						</div>
					</div>
					<form id='fileform2' action='reuploadImg.do' enctype='multipart/form-data' method='post'>
						<input id='iptFile2' type='file' name='file' class='file' onchange='getFileInfo2()'/>
					</form>
				</div>
			</div>
		</div>
		<div id="saveExit">
			<!-- <button id="saveBtn" class="layui-btn layui-btn-new layui-btn-new2" onclick="saveMessage();">保存</button>
			<button id="AuditBtn" class="layui-btn layui-btn-new" style="margin-left:10px;" onclick="auditMessage();">审核</button> -->
			<button id="AuditBtn" class="layui-btn layui-btn-normal bnt" onclick="auditMessage()">审核</button>
		</div>
	</div>
</body>
<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript">
	var form = layui.form,laydate=layui.laydate,layer = layui.layer,laypage = layui.laypage,upload = layui.upload;
	
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
	
	//点击的文章作品ID
	var articleId = "<%=request.getAttribute("articleId")%>";
	var userId= "<%=request.getAttribute("userId")%>";
	//书法家名称
	var cgName = "<%=request.getAttribute("cgName")%>";
	//是否已审核
	var auditStatus = "<%=request.getAttribute("auditStatus")%>";
	var auditSolid = auditStatus;
	//新加标签部分的Html
	var labelCode="<div class='phototag phototagNo' ><i class='iconfont' style='color:#009688;cursor: pointer;font-size: 20px;' onclick='addTag(this)'>&#xe600;</i></div>";
	//新加摄影家、作品部分的Html
	var addWorkCode="<div class='addNewInfo'>"+
						"<i class='iconfont' style='color:#009688;cursor: pointer;font-size: 40px;' data-flag='works' title='新加作品关联' onclick='addNewWorks(this)'>&#xe600;</i>"+
				   "</div>";
	var addPhotogCode="<div class='addNewInfo'>"+
						"<i class='iconfont' style='color:#009688;cursor: pointer;font-size: 40px;' data-flag='photog' title='新加书法家关联' onclick='addNewWorks(this)'>&#xe600;</i>"+
				   "</div>";
	var layer2,form;
	//重新上传文章封面
	var MyFile2 = new Object();
	MyFile2.file = null;
	MyFile2.name = null;
	//图片排序部分
	var cgOrder = [];//书法家顺序
	var worksOrder = [];//作品顺序
	//文章左侧滚动部分
	var startTransX;
	var operNum=0;
	var canSwitch = true;
	var pgGroups=[];
	var wkGroup=[];
	var isSave=true;
	var isFrist=true;
	var workIndex=1;
	var coverOrfile;
	var fileType="wordReading";
	var strArr=new Array();
	var auditStateDesc="";
	//储存
	$(function(){
		adjust();
		$(window).resize(function() {
			adjust();
		});
		$("#artTitle").attr("contenteditable","false");
		layui.use('element', function(){
			  var $ = layui.jquery,
			  element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
		});
		getAllCGArt();
	});
	$("#conDetail").niceScroll({
			cursorwidth : "6px",
			cursorcolor: "#009688",
			cursorborder: "0px",
			autohidemode : false
	});
	function initWindosShow() {
		getCGArtTit();
		searchCGInfo();
		searchCGWorksInfo();
		getCGWordAndPic();
	}
	function getAllCGArt() {
		$.ajax({
			 url:"getAllCGArt.do",
			 type:"post",
			 dataType:"json",
			 data:{cgName:cgName,auditStatus:auditStatus},
			 success:function(data){
			 	var slideCode="";
			 	for(var i = 0;i<data.artId.length;i++){
			 		slideCode+="<div id='"+data.artId[i]+"' data-id='"+data.artId[i]+"' class='thumbImg' onclick='leftSlide(this)'>"+
						 			"<div class='slideImg'><img src='getCGArtImage.do?imgUrl="+data.artCover[i]+"&imgFlag=1'></img></div>"+
						 			"<div class='slideText' title='"+data.artName[i]+"'>"+data.artName[i]+"</div>"+
						 		"</div> ";
			 	}
			 	$("#slideContainer").html("");
			 	$("#slideContainer").html(slideCode);
			 	$("#"+articleId).click();
			 }
		 });
	}
	//获取文章标题
	function getCGArtTit() {
		$.ajax({
			 url:"getCGArtTit.do",
			 type:"post",
			 dataType:"json",
			 data:{articleId : articleId},
			 success:function(data){
			 	if (data.artName.length!=0) {
					$("#artTitle").html("");
			 		$("#artTitle").html(data.artName[0]);
			 		$("#coverImg").attr("src","getCGArtImage.do?imgUrl="+data.artCover[0]+"&imgFlag=1");
			 		$("#artState").html("");
			 		$("#artState").html(data.audState[0]+","+data.auditDesc[0]);
			 		$("#artState").attr("data-flag",data.audStaId[0]);
			 		auditStateDesc=data.audState[0];
			 		if ("已审核"!=auditStateDesc) {
						$("#saveExit").show();
					} else {
						$("#saveExit").hide();
					}
				}
			 }
		 });
	}
	//查相关书法家
	function searchCGInfo(){
		$.ajax({
			 url:"searchCGInfo.do",
			 type:"post",
			 dataType:"json",
			 data:{articleId : articleId},
			 success:function(data){
			 	var photogCode = "";
			 	for(var i = 0;i<data.photogName.length;i++){
			 		cgOrder.push(data.photogId[i]);
			 		pgGroups.push(data.photogId[i]);
			 		photogCode+="<div class='layui-col-md2 layui-col-sm4 layui-col-sm46' data-id='"+data.photogId[i]+"'>"+
							 	"<div class='deletePhotog' data-flag='photog' onclick='deleteOper(this)'><i class='iconfont' style='color:#000000;cursor: pointer;font-size: 16px;'>&#xe635;</i></div>"+
						        "<div class='cmdlist-container customCgImg'>"+
						            "<a href='javascript:;'>"+
						              "<img src='getCGArtImage.do?imgUrl="+data.fileName[i]+"&imgFlag=2'>"+
						            "</a>"+
						            "<a href='javascript:;'>"+
						              "<div class='cmdlist-text'>"+
						                "<p class='info'>"+data.photogName[i]+"</p>"+
						              "</div>"+
						            "</a>"+
						        "</div>"+
						    "</div>";
			 	}
			 	$("#photogList").html("");
			 	$("#photogList").html(photogCode);
			 }
		 });
	};
	//查相关书法作品
	function searchCGWorksInfo(){
		$.ajax({
			 url:"searchCGWorksInfo.do",
			 type:"post",
			 dataType:"json",
			 data:{articleId : articleId},
			 success:function(data){
			 	var photogCode = "";
			 	for(var i = 0;i<data.worksName.length;i++){
			 		worksOrder.push(data.worksId[i]);
			 		wkGroup.push(data.worksId[i]);
			 		photogCode+="<div class='layui-col-md2 layui-col-sm4 cusDiv' data-id='"+data.worksId[i]+"'>"+
							 	"<div class='deletePhotog' data-flag='works' onclick='deleteOper(this)'><i class='iconfont' style='color:#000000;cursor: pointer;font-size: 16px;'>&#xe635;</i></div>"+
						        "<div class='cmdlist-container cmdlist-container2'>"+
						              "<img class='speacilImg' src='getCGArtImage.do?imgUrl="+data.fileName[i]+"&imgFlag=3'>"+
						              "<div class='cmdlist-text'>"+
						                "<p class='info'>"+data.worksName[i]+"</p>"+
						              "</div>"+
						        "</div>"+
						    "</div>";
					workIndex=(i+1);
			 	}
			 	//photogCode+=addWorkCode;
			 	$("#worksList").html("");
			 	$("#worksList").html(photogCode);
			 }
		 });
	};
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
		if($(obj).hasClass("show")){
			return;
		};
		articleId=$(obj).attr("data-id");
		initWindosShow();
		pgGroups=[];
		wkGroup=[];
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
	function reuploadFile(e) {
		fileType=$(".layui-this").attr("data-id");
		if ("wordReading"==fileType) {
			$("#iptFile2").click();
			coverOrfile=$(e).attr("data-flag");
		} else if ("imgReading"==fileType){
			if (strArr.length==0) {
				$("#iptFile2").click();
				coverOrfile=$(e).attr("data-flag");
			} else {
				layui.use(['layer','form'], function(){
		        	layer = layui.layer;
			        window.parent.layer.open({
				        type: 2 //此处以iframe举例
				        ,title: '重新上传图片'
				        ,area: ['1000px', '700px']
				        ,shade: 0
				        ,maxmin: true
				        ,offset: 'auto'
				        ,content: 'upLongPic.do?bigTage=article&flagId='+articleId+'&pgorcg=cg'
				        ,zIndex: layer.zIndex //重点1 
				        ,success: function(layero){
				        	
				        }
			        });
		        });
	        }
		}
		/*  */
	}
	function getFileInfo2(){
		var filedom = document.getElementById("iptFile2");
		var file = filedom.files["0"];
		MyFile2.file = file;
		MyFile2.name = file.name;
		if(MyFile2.file == null){
			alert("请先选择文件！");
			return false;
		}
		uplaodFile2();
	}
	function uplaodFile2(){
		fileType=$(".layui-this").attr("data-id");
		var file = MyFile2.file;
		var filename = MyFile2.name;
		filename = encodeURI(encodeURI(filename));
		var leixing = filename.substring(filename.lastIndexOf(".") + 1);
		var options = {
			url:"reupCGImg.do",
			async:false,
			type:"post",
			dataType:"json",
			data:{
				leixing : leixing,
				filename : filename,
				articleId : articleId,
				fileType : fileType,
				coverOrfile : coverOrfile
			},
	        success: function (data) {
	        	if (data.flag[0]!=1) {
					layer.msg("重新上传图片失败");
				} else {
					if ("artTitle"==coverOrfile) {
						$("#coverImg").attr("src","getCGArtImage.do?imgUrl="+data.imgPath[0]+"&imgFlag=1");
					} else {
						if ("wordReading"==fileType) {
							$("#myiframe1").attr("src","cgArtWdShow.do?articleId="+articleId+"&flagId=TA");
						} else if ("imgReading"==fileType){
							$("#myiframe2").attr("src","getCGArtImage.do?imgUrl="+data.imgPath[0]+"&imgFlag=1");
						}
					}
				}
	       	}
		};
	    $("#fileform2").ajaxSubmit(options);
	}
	//审核
	function auditMessage() {
		layui.use(['layer','form'], function(){
			layer=layui.layer;
			form=layui.form;
			var auditstatus = $("#artState").attr("data-flag");
			$.ajax({
				url:"getcgauditstatus.do",
				type:"post",
				dataType:"json",
				data:{auditStatus : auditStatus},
				success:function(data){
					/* var html = "<form class='layui-form'><div class='radiodiv'>";
					for(var i=0;i<data.auditId.length;i++){
						if(data.auditId[i]==auditstatus){
							html += "<input type='radio' name='auditstatus' value='"+data.auditId[i]+"' title='"+data.auditName[i]+"' checked>";
						}else{
							html += "<input type='radio' name='auditstatus' value='"+data.auditId[i]+"' title='"+data.auditName[i]+"'>";
						}
					}
					html += "</div></form>"; */
					var html = "<div class='passTitle'>内容无问题<button class='layui-btn customCss' bnt-type='1' onclick='passAudit(this)'>通<i>通过</i>过</button></div>";
					html += "<hr class='layui-bg-green' style='margin: 5px 0;'>";
					html += "<form class='layui-form'  lay-filter='test'><div id='auditdescdiv' class='auditdescdiv'><div class='title'>驳回原因：</div>";
					for(var i=0;i<data.reasonName.length;i++){
						if(i==0){
							html += "<input type='checkbox' name='rejectName' lay-skin='primary' value='"+data.reasonId[i]+"' title='"+data.reasonName[i]+"'>";
						}else{
							html += "<input type='checkbox' name='rejectName' lay-skin='primary' value='"+data.reasonId[i]+"' title='"+data.reasonName[i]+"'>";
						}
					}
					html += "<div class='customReason'><div class='customDiv'>"
							+"<input type='checkbox' name='rejectName' value='999' lay-skin='primary' title='自定义'></div>"
							+"<textarea id='tareadesc'></textarea>";
					html += "</div></div></form>";
					if (auditSolid==1) {
						html += "<div class='passTitle'><button class='layui-btn customCss' bnt-type='0' onclick='passAudit(this)'>驳<i>驳回</i>回</button></div>";
					} else {
						html += "<div class='passTitle'><button class='layui-btn customCss' bnt-type='0' onclick='passAudit(this)'>回<i>回退</i>退</button></div>";
					}
					layer.open({
						id:"divAuditPg",
						title:"审核文章",
						type: 1,
						area: ["600px", "400px;"],
						shade:[0.5 , "#000" , true],
						resize:false,
						btnAlign: "c",
						tipsMore: true,
						content: html,
						yes:function(index, layero){
						},
						success: function(layero, index){
							form.render();
						},
					});
				}
			});
		});
	}
	//通过审核
	function passAudit(e) {
		var auditS = "1";
		var auditD = "";
		var type = $(e).attr("bnt-type");
		if (type==1) {
			auditS="5";
			auditD="审核已通过";
		} else {
			if (auditSolid==1) {
				auditS="2";
			} else {
				auditS="3";
			}
			var arrChk=$("input[name='rejectName']:checked");
			if (arrChk.length==0) {
				if (auditS=="2") {
					layer.msg("驳回时，请填写驳回原因！");
				} else {
					layer.msg("回退时，请填写回退原因！");
				}
				return false;
			}
		    //遍历得到每个checkbox的value值
		    for (var i=0;i<arrChk.length;i++){
		    	if (arrChk[i].value=="999") {
					auditD += $("#tareadesc").val()+"、";
				} else {
					auditD += arrChk[i].title+"、";
				}
				
		    }
		    auditD = auditD.substring(0, auditD.length-1);
		}
		submitAudit(auditS,auditD);
	}
	
	//提交审核状态
	function submitAudit(auditStatus,auditdesc) {
		if (auditStatus=="5") {
			$("#artState").html("");
			$("#artState").html("已审核,"+auditdesc);
		} else if (auditStatus=="2") {
			$("#artState").html("");
			$("#artState").html("驳回,"+auditdesc);
		} else if (auditStatus=="3") {
			$("#artState").html("");
			$("#artState").html("回退,"+auditdesc);
		}
		$.ajax({
			 url:"changeCGAudit.do",
			 type:"post",
			 dataType:"text",
			 data:{
			 	articleId : articleId,
			 	auditStatus : auditStatus,
			 	auditDesc : auditdesc,
			 	auditPersn : userId
			 },
			 success:function(data){
			 	layer.msg("审核成功");
			 	$("#saveExit").hide();
			 	layer.closeAll();
			 }
		 });
	}
	
	function getCGWordAndPic() {
			$.ajax({
					url:"getCGWordAndPic.do",
					type:"post",
					dataType:"json",
					async:false,
					data:{"articleId":articleId},
					success:function(data){
						if(data.articleDoc[0]!="" && data.articleDoc[0]!="null" && data.articleDoc[0]!="undefined"){
							$("#myiframe1").attr("src","cgArtWdShow.do?articleId="+articleId+"&flagId=TA");
						}
						if(data.articlePic[0]!="" && data.articlePic[0]!="null" && data.articlePic[0]!="undefined"){
							strArr=data.articlePic[0].split("#");
							var str="";
							for (var a=0;a<strArr.length;a++) {
								str+="<img class='showImg' src='getCGArtImage.do?imgUrl="+strArr[a]+"&imgFlag=1'></img>";
							}
							$("#showImgD").html("");
							$("#showImgD").html(str);
							$(".showImg").css("width","100%");	
							//$("#myiframe2").attr("src","getCGArtImage.do?imgUrl="+data.articlePic[0]+"&imgFlag=1");
						}
						if(data.articleUrl[0]!="" && data.articleUrl[0]!="null" && data.articleUrl[0]!="undefined"){
							$("#myiframe3").attr("src",data.articleUrl[0]);
						}
					}
			});
	}
	//调整页面布局
	function adjust(){
		$("#conRight").width($("#mainContent").width()-$("#conLeft").width()-35);
		$("#artDetail").width($("#conRight").width()-$("#artCover").width()-25);
		$(".articleInfo2").css("max-width",$(".titleName2").width()-100);
		$(".layui-tab-content").height($("#artExplain").height()-62);
		
		startTransX=($(".theater-thumb").height()-180)/2;
		$(".theater-thumb .thumb-container").css("top",(startTransX-operNum*116));
		
		$("#saveExit").width($("#main").width());
		$("#AuditBtn").css("margin-left",($("#saveExit").width()-$(".bnt").width()-10)/2);
	};	
</script>
</html>