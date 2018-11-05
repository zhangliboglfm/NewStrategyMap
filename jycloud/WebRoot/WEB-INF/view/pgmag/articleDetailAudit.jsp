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
.deletePhotog,#downandupload,.removelbbnt,.insertlb{
	display: none !important;
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
						<div id="artCover" onclick="reuploadFile()"><img id="coverImg" style="width: 100%;height: 100%;"></img></div>
						<div id="artDetail">
							<div class="titleName titleFlag">
								<div class="titleDesc">文章标题&nbsp;:</div>
								<div id="artTitle" class="articleInfo" contenteditable="true"></div>
							</div>
							<div class="titleName titleName3">
								<div class="stateP">审核状态&nbsp;:</div>
								<div id="artState" class="stateP stateP2"></div>
							</div>
							<div class="titleName titleName2 titleFlag">
								<div class="titleDesc">标<span>标签</span>签&nbsp;:</div>
								<div id="divPgLable" class="articleInfo articleInfo2"></div>
							</div>
						</div>
					</div>
					<div id="artPhotog" class="photogWorks">
						<div class="photogWorksTitle">摄影家&nbsp;:</div>
						<div id="photogList"></div><!-- class="phoWorksInfo layui-row" -->
						<div class='addNewInfo'>
							<i class='iconfont' style='color:#009688;cursor: pointer;font-size: 40px;' data-flag='photog' title='新加摄影家关联' onclick='addNewWorks(this)'>&#xe600;</i>
					   </div>
					</div>
					<div id="artWorks" class="photogWorks">
						<div class="photogWorksTitle">作品&nbsp;:</div>
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
								<iframe id="myiframe2" name="myiframe" src="" frameborder="0" style="float:left;width:100%;margin:0px;padding:0px;height: 100%;"></iframe>
							</div>
							<div class="layui-tab-item" style="height: 100%;">
								<iframe id="myiframe3" name="myiframe" src="" frameborder="0" style="float:left;width:100%;margin:0px;padding:0px;height: 100%;"></iframe>
							</div>
						</div>
						<div id="downandupload">
							<i class="iconfont" style="color:#009688;cursor: pointer;font-size: 30px;" onclick="reuploadFile(this)" title="上传文件">&#xe658;</i>
							&nbsp;&nbsp;&nbsp;
							<i class="iconfont" style="color:#009688;cursor: pointer;font-size: 30px;" onclick="downFiles(this)" title="下载文件">&#xe659;</i>
						</div>
					</div>
					<div id="saveExit">
						<button id="saveBtn" class="layui-btn layui-btn-new" onclick="saveMessage();">保存</button>
						<button id="AuditBtn" class="layui-btn layui-btn-new" style="margin-left:10px;" onclick="auditMessage();">审核</button>
					</div>
					<form id='fileform2' action='reuploadImg.do' enctype='multipart/form-data' method='post'>
						<input id='iptFile2' type='file' name='file' class='file' onchange='getFileInfo2()'/>
					</form>
				</div>
			</div>
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
	
	//摄影家ID
	var photogId = "<%=request.getAttribute("photogId")%>";
	//点击的文章作品ID
	var articleId = "<%=request.getAttribute("articleId")%>";
	var userId= "<%=request.getAttribute("userId")%>";
	//新加标签部分的Html
	var labelCode="<div class='phototag phototagNo' ><i class='iconfont' style='color:#009688;cursor: pointer;font-size: 20px;' onclick='addTag(this)'>&#xe600;</i></div>";
	//新加摄影家、作品部分的Html
	var addWorkCode="<div class='addNewInfo'>"+
						"<i class='iconfont' style='color:#009688;cursor: pointer;font-size: 40px;' data-flag='works' title='新加作品关联' onclick='addNewWorks(this)'>&#xe600;</i>"+
				   "</div>";
	var addPhotogCode="<div class='addNewInfo'>"+
						"<i class='iconfont' style='color:#009688;cursor: pointer;font-size: 40px;' data-flag='photog' title='新加摄影家关联' onclick='addNewWorks(this)'>&#xe600;</i>"+
				   "</div>";
	var layer2,form;
	//重新上传文章封面
	var MyFile2 = new Object();
	MyFile2.file = null;
	MyFile2.name = null;
	//图片排序部分
	var photogOrder = [];
	var worksOrder = [];
	//文章左侧滚动部分
	var startTransX;
	var operNum=0;
	var canSwitch = true;
	var pgGroups=[];
	var wkGroup=[];
	var isSave=true;
	var isFrist=true;
	var workIndex=1;
	//储存
	$(function(){
		adjust();
		$(window).resize(function() {
			adjust();
		});
		if (userId=="admin") {
			$("#artTitle").attr("contenteditable","false");
			$("#saveBtn").hide();
			$("#AuditBtn").show();
			$(".addNewInfo").hide();
		} else {
			$("#saveBtn").show();
			$("#AuditBtn").hide();
			$("#artTitle").attr("contenteditable","true");
			$(".addNewInfo").show();
		}
		layui.use('element', function(){
			  var $ = layui.jquery,
			  element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
		});
		getAllArticle();
		//作品部分的拖动排序
		var el = document.getElementById("worksList");
		var sortable = Sortable.create(el,{
			animation:500,
			onEnd: function (evt) {
		        worksOrder = sortable.toArray();
		        reOrderWorks();
		    },
		    toArray: function () {
	             var order = [],
	                 el,
	                 children = this.el.children,  //获取所有子节点
	                 i = 0,
	                 n = children.length,  //获取子节点的长度
	                 options = this.options;
	             for (var i = 0; i < n; i++) {
	                 el = children[i];
	                   if (_closest(el, options.draggable, this.el)) {
	                    		//getAttribute获取  data-id 的属性
	                           //order.push 如果没有data-id 属性获取不到值，则会调用_generateId函数生成唯一表示符
	                     order.push(el.getAttribute(options.dataIdAttr) || _generateId(el));
	                 }
	             }
	             //返回唯一标识符id 数组 类型
	             return order;
	        },
		});
		//摄影家部分的拖动排序
		var el2 = document.getElementById("photogList");
		var sortable2 = Sortable.create(el2,{
			animation:500,
			onEnd: function (evt) {
		        orderArray = sortable2.toArray();
		        reOrderPhotog();
		    },
		    toArray: function () {
	             var order = [],
	                 el,
	                 children = this.el.children,  //获取所有子节点
	                 i = 0,
	                 n = children.length,  //获取子节点的长度
	                 options = this.options;
	             for (var i = 0; i < n; i++) {
	                 el = children[i];
	                   if (_closest(el, options.draggable, this.el)) {
	                    		//getAttribute获取  data-id 的属性
	                           //order.push 如果没有data-id 属性获取不到值，则会调用_generateId函数生成唯一表示符
	                     order.push(el.getAttribute(options.dataIdAttr) || _generateId(el));
	                 }
	             }
	             //返回唯一标识符id 数组 类型
	             return order;
	        },
		});
	});
	//摄影家重新排序
	function reOrderPhotog() {
		$.ajax({
			 url:"reOrderPhotog.do",
			 type:"post",
			 dataType:"json",
			 data:{
			 	articleId : articleId,
			 	reOrder : JSON.stringify(orderArray),
			 	opFlag : "photog"
			 },
			 success:function(data){
			 	addAuditing();
			 }
		 });
	}
	//相关作品重新排序
	function reOrderWorks() {
		$.ajax({
			 url:"reOrderPhotog.do",
			 type:"post",
			 dataType:"json",
			 data:{
			 	articleId : articleId,
			 	reOrder : JSON.stringify(worksOrder),
			 	opFlag : "works"
			 },
			 success:function(data){
			 	addAuditing();
			 }
		 });
	}
	$("#conDetail").niceScroll({
			cursorwidth : "6px",
			cursorcolor: "#009688",
			cursorborder: "0px",
			autohidemode : false
	});
	function initWindosShow() {
		getWordAndPic();
		getArticlLabel();
		getArtTitle();
		searchPhotogInfo();
		searchWorksInfo();
	}
	function getAllArticle() {
		$.ajax({
			 url:"getAllArticle.do",
			 type:"post",
			 dataType:"json",
			 data:{photogId : photogId},
			 success:function(data){
			 	var slideCode="";
			 	for(var i = 0;i<data.artId.length;i++){
			 		slideCode+="<div id='"+data.artId[i]+"' data-id='"+data.artId[i]+"' class='thumbImg' onclick='leftSlide(this)'>"+
						 			"<div class='slideImg'><img src='getArtImage.do?imgUrl="+data.artCover[i]+"&imgFlag=1'></img></div>"+
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
	function getArtTitle() {
		$.ajax({
			 url:"getArtTitle.do",
			 type:"post",
			 dataType:"json",
			 data:{articleId : articleId},
			 success:function(data){
			 	if (data.artName.length!=0) {
					$("#artTitle").html("");
			 		$("#artTitle").html(data.artName[0]);
			 		$("#coverImg").attr("src","getArtImage.do?imgUrl="+data.artCover[0]+"&imgFlag=1");
			 		$("#artState").html("");
			 		$("#artState").html(data.audState[0]+","+data.auditDesc[0]);
			 		$("#artState").attr("data-flag",data.audStaId[0]);
			 		//addAuditing();
				}
			 }
		 });
	}
	//查询文章所有标签
	function getArticlLabel() {
		$.ajax({
			 url:"getArticlLabel.do",
			 type:"post",
			 dataType:"json",
			 data:{articleId : articleId},
			 success:function(data){
			 	reloadPgLable(data.pLable);
			 	//addAuditing();
			 }
		 });
	}
	//添加标签
	function addTag(e){
		workId=$(e).attr("data-id");
		layui.use(['layer','form'], function(){
			layer2 = layui.layer;
			form=layui.form;
			var str="<div style='width:390px;height:210px;margin:5px;'>" +
				"<div class='layui-input-inline'>" +
					"<input type='text' id='tagName' placeholder='标签名称' value='' class='layui-input '>" +
				"</div>"+
				"<div class='layui-input-inline'>" +
					"<button class='layui-btn layui' style='margin-left:5px;background:#60B878;' onclick='getTabList()'>搜索</button>"+
				"</div>" +
				"<div id='divtags2' class='divtags2'></div>"+
				"<div class='userdefined' onclick='addOtherLabel()'>自定义&nbsp;<i class='layui-icon'>&#xe642;</i></div>" +
			"</div>";
			layer2.open({
				type:1,
				title:'标签选择',
				content:str,
				success:function(index,layero){//成功调用后的回调函数,该回调携带两个参数，分别为当前层索引、当前层DOM对象
					layer.style(layero, {
						  width: '400px',
						  height:'300px',
						  left:($(window).width()-400)/2,
						  top:($(window).height()-300)/2,
					});
					
					$(".divtags2").niceScroll({
						 autohidemode: true,
						 cursorwidth: "2px", // 滚动条的宽度，单位：便素
						 //cursorborder: "1px solid #fff", // CSS方式定义滚动条边框
						 cursorcolor: "#009688"
					});
					getTabList();
					
					 //输入框绑定enter事件
					 $("#tagName").keydown(function(evet){
					 	if(event.keyCode==13){
					 		getTabList();
					 	};
					 });
				},
				yes:function(index,layero){
					layer.close(index);
				}
			});
		});
	};
	//获取标签池中东西
	function  getTabList(){
		var keyWord = $("#tagName").val();
		$.ajax({
			url:"getAllArtLabel.do",
			type:"post",
			dataType:"json",
			data:{
				articleId : articleId,
				keyWord : keyWord
			},
			success:function(data){
				if(data!=null&&data.length!=0){
					var str="<form id='layui-form' class='layui-form' action='' lay-filter='example'>";
					for(var i=0;i<data.labelName.length;i++){
						if(data.labelCun[i]){
							str+="<input type='checkbox' name='"+data.labelId[i]+"' title='"+data.labelName[i]+"' checked>";
						}else{
							str+="<input type='checkbox' name='"+data.labelId[i]+"' title='"+data.labelName[i]+"' >";
						};
					};
					str+="</form>";
					$("#divtags2").html(str);
					addAuditing();
					form.render();
					
					form.on('checkbox', function(data){
						var operate;
						 if(data.elem.checked){
							 operate="add";
						 }else{
							 operate="delete";
						 };
						 var LABEL_NAME=data.elem.title;
						 var labelId=data.elem.name;
						 $.ajax({
							 url:"opArticleLabel.do",
							 type:"post",
							 dataType:"json",
							 data:{
							 	articleId : articleId,
							 	labelId : labelId,
							 	operate : operate
							 },
							 success:function(data){
								 if(data.flag[0]==1){
									 if(operate=="add"){
									 	 $("#artLabels").find(".phototagNo").remove();
										 var str="<div class='phototag' data-tagid='"+labelId+"'><span>"+LABEL_NAME+"</span><i class='layui-icon close-icon' onclick='deleteTag(this)'>&#x1007;</i></div>";
										 $("#artLabels").append(str);
										 $("#artLabels").append(labelCode);
									 }else{
										 $("#artLabels").find("div[data-tagid='"+labelId+"']").remove(); 
									 }
									 addAuditing();
								 }
							 }
						 });
					});
				};
			}
		});
	};
	//删除某标签
	function deleteTag(obj){
		var opWorkId=$(obj).parent().parent().attr("data-id");
		$.ajax({
			 url:"opArticleLabel.do",
			 type:"post",
			 dataType:"json",
			 data:{articleId : articleId,labelId : $(obj).parent().attr("data-tagid"),operate:"delete"},
			 success:function(data){
				 if(data.flag[0]==1){
					 $(obj).parent().remove();
					 addAuditing();
				 }
			 }
		 });
	};
	//添加其他标签
	function addOtherLabel(){
		layer2.prompt({title: '请输入标签名称',offset: [(($(window).height()-270)/2+40)+"px", (($(window).width()-270)/2)+"px"],value:"example0",id:"definedLabel",formType: 0}, function(text, index){
			var LabelName = $("#definedLabelName").val().trim();
			var LabelDesc = $("#definedLabelDesc").val().trim();
			if(LabelName==""){
				layer.msg("标签名称不能为空");
				return;
			};
			$.ajax({
				url:"addCustomLabel.do",
				type:"post",
				dataType:"json",
				data:{LabelName:LabelName,LabelDesc:LabelDesc,articleId:articleId},
				success:function(data){
						if(data!=null&&data.success==true){
							$("#layui-form").append("<input type='checkbox' name='"+data.label_id+"' title='"+LabelName+"' checked>");
							form.render();
							$("#artLabels").find(".phototagNo").remove();
							var str="<div class='phototag' data-tagid='"+data.label_id+"'><span>"+LabelName+"</span><i class='layui-icon close-icon' onclick='deleteTag(this)'>&#x1007;</i></div>";
							$("#artLabels").append(str);
							$("#artLabels").append(labelCode);
							layer.msg("操作成功！！！");
							layer.close(index);
						}else if(data.message!=null){
							layer.msg(data.message);
						}else{
							layer.msg("请重试");
						}
							
					
					},
				});
		});
		
		$("#definedLabel").html("<input type='text' id='definedLabelName' placeholder='标签名称' value='' class='layui-layer-input taginputclass'>" +
		"<input type='text' id='definedLabelDesc' placeholder='标签描述' value='' class='layui-layer-input taginputclass'>");
	}
	//查相关摄影家
	function searchPhotogInfo(){
		$.ajax({
			 url:"searchPhotogInfo.do",
			 type:"post",
			 dataType:"json",
			 data:{articleId : articleId},
			 success:function(data){
			 	var photogCode = "";
			 	for(var i = 0;i<data.photogName.length;i++){
			 		pgGroups.push(data.photogId[i]);
			 		photogCode+="<div class='layui-col-md2 layui-col-sm4 layui-col-sm46' data-id='"+data.photogId[i]+"'>"+
							 	"<div class='deletePhotog' data-flag='photog' onclick='deleteOper(this)'><i class='iconfont' style='color:#000000;cursor: pointer;font-size: 16px;'>&#xe635;</i></div>"+
						        "<div class='cmdlist-container'>"+
						            "<a href='javascript:;'>"+
						              "<img src='getArtImage.do?imgUrl="+data.fileName[i]+"&imgFlag=2'>"+
						              //"<div class='workImg' background-image: url(\'getArtImage.do?imgUrl="+data.fileName[i]+"&imgFlag=2'\');></div>"+
						            "</a>"+
						            "<a href='javascript:;'>"+
						              "<div class='cmdlist-text'>"+
						                "<p class='info'>"+data.photogName[i]+"</p>"+
						              "</div>"+
						            "</a>"+
						        "</div>"+
						    "</div>";
			 	}
			 	//photogCode+=addPhotogCode;
			 	$("#photogList").html("");
			 	$("#photogList").html(photogCode);
			 	//addAuditing();
			 }
		 });
	};
	//查相关作品
	function searchWorksInfo(){
		$.ajax({
			 url:"searchWorksInfo.do",
			 type:"post",
			 dataType:"json",
			 data:{articleId : articleId},
			 success:function(data){
			 	var photogCode = "";
			 	for(var i = 0;i<data.worksName.length;i++){
			 		wkGroup.push(data.worksId[i]);
			 		photogCode+="<div class='layui-col-md2 layui-col-sm4 layui-col-sm46' data-id='"+data.worksId[i]+"'>"+
							 	"<div class='deletePhotog' data-flag='works' onclick='deleteOper(this)'><i class='iconfont' style='color:#000000;cursor: pointer;font-size: 16px;'>&#xe635;</i></div>"+
						        "<div class='cmdlist-container'>"+
						            "<a href='javascript:;'>"+
						              "<img src='getArtImage.do?imgUrl="+data.fileName[i]+"&imgFlag=3'>"+
						              //"<div class='workImg' background-image: url('getArtImage.do?imgUrl="+data.fileName[i]+"&imgFlag=3');></div>"+
						            "</a>"+
						            "<a href='javascript:;'>"+
						              "<div class='cmdlist-text'>"+
						                "<p class='info'>"+data.worksName[i]+"</p>"+
						              "</div>"+
						            "</a>"+
						        "</div>"+
						    "</div>";
					workIndex=(i+1);
			 	}
			 	//photogCode+=addWorkCode;
			 	$("#worksList").html("");
			 	$("#worksList").html(photogCode);
			 	//addAuditing();
			 }
		 });
	};
	//删除作品或摄影家信息
	function deleteOper(e) {
		var deleteId=$(e).parent().attr("data-id");
		var deleteFlag = $(e).attr("data-flag");
		$.ajax({
			 url:"deleteOper.do",
			 type:"post",
			 dataType:"json",
			 data:{articleId : articleId,labelId : deleteId,deleteFlag : deleteFlag},
			 success:function(data){
				 if(data.flag[0]==1){
					 $(e).parent().remove();
					 if ("works"==deleteFlag) {
						wkGroup.remove(deleteId);
					} else {
						pgGroups.remove(deleteId);
					}
					addAuditing();
				 }
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
		/* if (!isFrist) {
			if (!isSave) {
				layui.use(['layer','form'], function(){
					layer6 = layui.layer;
					layer6.confirm('是否保存？', {
					  btn: ['保存','取消'] //按钮
					}, function(){
						saveMessage();
						isFrist=true;
						layer6.closeAll();
					}, function(){
						isFrist=true;
						return;
					});
				});
			}
		} */
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
	function reuploadFile() {
		$("#iptFile2").click();
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
		var fileType=$(".layui-this").attr("data-id");
		var file = MyFile2.file;
		var filename = MyFile2.name;
		filename = encodeURI(encodeURI(filename));
		var leixing = filename.substring(filename.lastIndexOf(".") + 1);
		var options = {
			url:"reuploadImg.do",
			async:false,
			type:"post",
			dataType:"json",
			data:{
				leixing : leixing,
				filename : filename,
				articleId : articleId,
				fileType : fileType
			},
	        success: function (data) {
	        	if (data.flag[0]!=1) {
					alert("重新上传图片失败");
				} else {
					$("#coverImg").attr("src","getArtImage.do?imgUrl="+data.imgPath[0]+"&imgFlag=1");
				}
	       	}
		};
	    $("#fileform2").ajaxSubmit(options);
	}
	//添加摄影家
	function addNewWorks(e) {
		var contentFlag = $(e).attr("data-flag");
		var pgGr = pgGroups.join("','");
		pgGr="('"+pgGr+"')";
		var wkGr = wkGroup.join("','");
		wkGr="('"+wkGr+"')";
		if (contentFlag=="photog") {
			layui.use(['layer','form'], function(){
				layer3 = layui.layer;
				form=layui.form;
				layer3.open({ 
					type:2,
					title:'添加摄影家',
					content:"artAddPhotog.do?pgGroups="+pgGr,
					success:function(index,layero){//成功调用后的回调函数,该回调携带两个参数，分别为当前层索引、当前层DOM对象
						layer.style(layero, {
							  width: '600px',
							  height:'400px',
							  left:($(window).width()-600)/2,
							  top:($(window).height()-400)/2,
						});
					},
				});
			});
		} else if(contentFlag=="works"){
			layui.use(['layer','form'], function(){
				layer4 = layui.layer;
				form=layui.form;
				layer4.open({
					type:2,
					title:'添加相关作品',
					content:"artAddWorks.do?wkGroups="+wkGr+"&pgGroups="+pgGr,
					success:function(index,layero){//成功调用后的回调函数,该回调携带两个参数，分别为当前层索引、当前层DOM对象
						layer.style(layero, {
							  width: '600px',
							  height:'400px',
							  left:($(window).width()-600)/2,
							  top:($(window).height()-400)/2,
						});
					},
				});
			});
		}
	}
	//添加新的摄影家关联
	function addNewPhotogF(pgid) {
		$.ajax({
			 url:"addNewPhotogF.do",
			 type:"post",
			 dataType:"json",
			 data:{pgid : pgid,articleId : articleId},
			 success:function(data){
			 	var photogCode = "";
			 	for(var i = 0;i<data.photogName.length;i++){
			 		pgGroups.push(pgid);
			 		photogCode+="<div class='layui-col-md2 layui-col-sm4 layui-col-sm46' data-id='"+pgid+"'>"+
							 	"<div class='deletePhotog' data-flag='photog' onclick='deleteOper(this)'><i class='iconfont' style='color:#000000;cursor: pointer;font-size: 16px;'>&#xe635;</i></div>"+
						        "<div class='cmdlist-container'>"+
						            "<a href='javascript:;'>"+
						              "<img src='getArtImage.do?imgUrl="+data.fileName[i]+"&imgFlag=2'>"+
						            "</a>"+
						            "<a href='javascript:;'>"+
						              "<div class='cmdlist-text'>"+
						                "<p class='info'>"+data.photogName[i]+"</p>"+
						              "</div>"+
						            "</a>"+
						        "</div>"+
						    "</div>";
			 	}
			 	$("#photogList").find(".addNewInfo").remove();
			 	$("#photogList").append(photogCode);
			 	//$("#photogList").append(addPhotogCode);
			 	addAuditing();
			 }
		 });
	}
	//添加新的作品关联
	function addNewWorksF(wkid) {
		workIndex+=1;
		$.ajax({
			 url:"addNewWorksF.do",
			 type:"post",
			 dataType:"json",
			 data:{wkid : wkid,articleId : articleId},
			 success:function(data){
			 	var photogCode = "";
			 	for(var i = 0;i<data.worksName.length;i++){
			 		pgGroups.push(wkid);
			 		photogCode+="<div class='layui-col-md2 layui-col-sm4 layui-col-sm46' data-id='"+wkid+"'>"+
							 	"<div class='deletePhotog' data-flag='works' onclick='deleteOper(this)'><i class='iconfont' style='color:#000000;cursor: pointer;font-size: 16px;'>&#xe635;</i></div>"+
						        "<div class='cmdlist-container'>"+
						            "<a href='javascript:;'>"+
						              "<img src='getArtImage.do?imgUrl="+data.fileName[0]+"&imgFlag=3'>"+
						            "</a>"+
						            "<a href='javascript:;'>"+
						              "<div class='cmdlist-text'>"+
						                "<p class='info'>"+data.worksName[0]+"</p>"+
						              "</div>"+
						            "</a>"+
						        "</div>"+
						    "</div>";
			 	}
			 	$("#worksList").find(".addNewInfo").remove();
			 	$("#worksList").append(photogCode);
			 	//$("#worksList").append(addWorkCode);
			 	addAuditing();
			 }
		 });
	}
	//下载文件
	function downFiles() {
		var fileType=$(".layui-this").attr("data-id");
		window.open("downFiles.do?fileType="+fileType+"&articleId="+articleId);
	}
	//添加审核状态
	function addAuditing() {
		$.ajax({
			 url:"addAuditing.do",
			 type:"post",
			 data:{articleId : articleId,"articleTile":$("#artTitle").text()},
			 success:function(data){
			 	//alert("123");
			 	isSave=false;
			 	layer.msg("保存成功！");
			 	
			 }
		 });
	}
	function saveMessage() {
		addAuditing();
		isSave=true;
	}
	//审核
	function auditMessage() {
		layui.use(['layer','form'], function(){
			layer=layui.layer;
			form=layui.form;
			var auditstatus = $("#artState").attr("data-flag");
			$.post("getallauditstatus.do", {}, function(result){
				var data = JSON.parse(result);
				var html = "<form class='layui-form'><div class='radiodiv'>";
				if(data.length!=0){
					for(var i=0;i<data.length;i++){
						if(data[i].auditId==auditstatus){
							html += "<input type='radio' name='auditstatus' value='"+data[i].auditId+"' title='"+data[i].auditName+"' checked>";
						}else{
							html += "<input type='radio' name='auditstatus' value='"+data[i].auditId+"' title='"+data[i].auditName+"'>";
						}
					}
				}
				html += "</div></form><div class='auditdescdiv'><div class='title'>问题描述：</div><textarea id='tareadesc'></textarea></div>";
				layer.open({
					id:"divAuditPg",
					title:"审核文章",
					type: 1,
					area: ["500px", "350px;"],
					shade:[0.5 , "#000" , true],
					resize:false,
					btn: ["确定", "取消"],
					btnAlign: "c",
					tipsMore: true,
					content: html,
					yes:function(index, layero){
						var auditdesc = $("#tareadesc").val();
						$("#artState").val($("input[name='auditstatus']:checked").attr("title"));
						$("#artState").attr("data-flag",$("input[name='auditstatus']:checked").val());
						if($("input[name='auditstatus']:checked").val()==2 && auditdesc==""){
							layuiLayerMsg("驳回时，请填写驳回原因！");
							return false;
						}
						$("#tareaAuditResult").val(auditdesc);
						$.ajax({
							 url:"changeAuditStatus.do",
							 type:"post",
							 dataType:"json",
							 data:{
							 	articleId : articleId,
							 	auditStatus : $("input[name='auditstatus']:checked").val(),
							 	auditDesc : auditdesc,
							 	auditPersn : userId
							 },
							 success:function(data){
							 	getArtTitle();
							 	layer.close(index);
							 }
						 });
						
					},
					success: function(layero, index){
						 form.render();
					},
				});
			});
		});
	}
	function getWordAndPic() {
			$.ajax({
					url:"getWordAndPic.do",
					type:"post",
					dataType:"json",
					async:false,
					data:{"articleId":articleId},
					success:function(data){
						if(data.articleDoc[0]!="" && data.articleDoc[0]!="null" && data.articleDoc[0]!="undefined"){
							$("#myiframe1").attr("src","artWordShow.do?articleId="+articleId+"&flagId=TA");
						}
						if(data.articlePic[0]!="" && data.articlePic[0]!="null" && data.articlePic[0]!="undefined"){
							$("#myiframe2").attr("src","getArtImage.do?imgUrl="+data.articlePic[0]+"&imgFlag=1");
						}
						if(data.articleUrl[0]!="" && data.articleUrl[0]!="null" && data.articleUrl[0]!="undefined"){
							$("#myiframe3").attr("src",data.articleUrl[0]);
						}
					}
			});
	}
	function auditState() {
		$.ajax({
			url:"auditState.do",
			type:"post",
			dataType:"json",
			async:false,
			data:{"articleId":articleId},
			success:function(data){
				var str="待审核";
				if (data.statusDesc.length!=0) {
					 str=data.statusDesc[0]+",   原因："+data.audit_Desc[0];
				}
				layui.use(['layer','form'], function(){
					layer=layui.layer;
					layer.open({
					  type: 1,
					  skin: 'layui-layer-rim', //加上边框
					  area: ['420px', '240px'], //宽高
					  content: str
					});
				})
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
		
		$("#saveExit").width($("#conRight").width());
		$(".layui-btn-new").css("margin-left",($("#saveExit").width()-$(".layui-btn-new").width()-10)/2);
	};	
</script>
</html>