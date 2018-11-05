<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>

<title>作品详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
<script type="text/javascript" src="js/Sortable.min.js"></script>
<script type="text/javascript" src="js/velocity.ui.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
<script type="text/javascript" src="js/Sortable.min.js"></script>

<style type="text/css">
.layui-badge-rim, .layui-colla-content, .layui-colla-item, .layui-collapse, .layui-elem-field, .layui-form-pane .layui-form-item[pane], .layui-form-pane .layui-form-label
, .layui-input, .layui-layedit, .layui-layedit-tool, .layui-quote-nm, .layui-select, .layui-tab-bar, .layui-tab-card, .layui-tab-title, .layui-tab-title .layui-this:after
, .layui-textarea{
	border-color: #B2B2B2 !important;
}
.seldiv .layui-form-select dl{max-height: 200px;}
*{font-size: 14px;font-family: 微软雅黑;color: #1C1C1C}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
.main{width: 100%;height: 99%;border-top: 1px solid #B2B2B2;}
.leftpath{float:left;width:200px;height: 100%;border-right:1px solid #B2B2B2;box-sizing: border-box;}
.rightpath{float:left;height: 100%;}
.isimptBox{float:left;margin-left:12px;margin-top:12px;width:22px;height:170px;border: 1px solid #eee;border-right: 0px solid #eee;font-size: 13px;}
.workslist{float:left;height:100%;border-left: 1px solid #eee;box-sizing: border-box;}
.samestyle{width:100%;height: 85px;line-height:20px;margin:0 auto;cursor: pointer;text-align: center;}
.classfiy{background: #1E9FFF;color: #fff;}
.listcont{width:100%;height:180px;/* background:#FF5722; */}
.listcont-child{float:left;margin-left:5%;margin-top:5%;width:90%;height: 90%;border: 1px #eee solid !important;}
.listcont-child Img{
	width: 100%;
	height: 84%;
	background-size: cover;background-position: center;background-origin:content-box;position: relative;display: inline-block;
}
.listcont-text{padding: 2.2px 5px 5px 5px;text-align: center;font-size: 12px;}
/* 阴影 */
.shadow{
	-webkit-box-shadow:0px 0px 3px #a0a0a0;
   	-moz-box-shadow:0px 0px 3px #a0a0a0;
   	box-shadow:0px 0px 3px #a0a0a0;
	/* 投影数字顺序依次是：右边 下边 投影模糊大小 */
	/* 内阴影数字顺序依次是：左边 上边 阴影模糊大小 */
}
.seldiv{
	width: 400px;
	margin-left: 50px;
	margin-top: 15px;
}
.seldiv .layui-form-select dl{max-height: 200px;}

.workinfordiv{float:left;width:99%;height:80px;border-bottom:1px solid #B2B2B2;/* background: #FF5722; */}
.imginfordiv{float:left;width: 99%;/* background: #FF5722; */}
.imginfordiv iframe{height: 100%;width: 100%;border: 0;}
.workinfordiv ul{float: left;margin: 0;padding: 0;list-style:none;width:100%;}
.workinfordiv ul li{float: left;width:20%;}
.workinfordiv ul li .eachinfor{height:28px;margin-left: 10px;margin-top: 9px; /* background: #FF5722; */}
.workinfordiv ul li .eachinfor .lab{float: left;line-height: 28px;font-size: 14px;width: 45px;text-align: left;font-weight: bold;}
.workinfordiv ul li .eachinfor input{float: left;border: 0;height: 27px;background-color: transparent;margin-left: 0px;/* cursor: default; */text-indent: 1ex;font-size: 12px;}
.inputboder{border-bottom: 1px solid #B2B2B2 !important;cursor: text !important;}
.workinfordiv ul li .saveconfig{width:100%;height: 25px;line-height: 25px;text-align: center;background: #1E9FFF;color: #fff;cursor: pointer;}
.navdiv{width: 99%;height: 30px;margin-left: 1%;margin-top: 7px}
.retrans{float:right;margin-right: 10px;margin-top:2px;width:80px;height: 25px;line-height: 25px;text-align: center;background: #1E9FFF;color: #fff;cursor: pointer;}
.setimg{width:98%;margin-left:1%;margin-top:1%;border-top: 1px solid #B2B2B2;}
.setimg-left{width:150px;height: 100%;border-right: 1px dashed rgb(186, 186, 186);}
</style>
</head>

<body>
	<div id="main" class="main">
		<div class="leftpath">
			<div class="isimptBox" id="isimptBox">
			<!-- 	<div id="isimport" class="samestyle">重要作品</div>
				<div id="notimport" class="samestyle">其它作品</div> -->
			</div>
			<div class="workslist">
				<div id="listcont" class="listcont">
					<!-- <div class="listcont-child shadow">
						<a><img src="image/character.jpg"></img></a>
		    			<a><div class='listcont-text'>
		    				<span>王羲之</span>
						</div></a> 
					</div> -->
				</div>
			</div>
		</div>
		<div class="rightpath">
			<div id="workinfordiv" class="workinfordiv">
				<ul>
					<li><div class="eachinfor"><div class="lab">名称：</div><input id="WorkName" name="cgworkinfor" value="" type="text" readonly="readonly"/></div></li>
					<li><div class="eachinfor"><div class="lab">年代：</div><input id="Years" name="cgworkinfor" value="" type="text" readonly="readonly"/></div></li>
					<li><div class="eachinfor"><div class="lab" style="width: 75px;">书法类型：</div><input id="CgType" name="cgworkinfor" value="" type="text" readonly="readonly"/></div></li>
					<li><div class="eachinfor"><div class="lab">字数：</div><input id="WorkNun" name="cgworkinfor" value="" type="text" readonly="readonly"/></div></li>
					<li><div class="eachinfor"><div class="lab">规格：</div><input id="Spec" name="cgworkinfor" value="" type="text" readonly="readonly"/></div></li>
					<li style="width: 30%"><div class="eachinfor"><div class="lab" style="width: 75px;">现收藏地：</div><input id="Pstcoll" name="cgworkinfor" value="" type="text" readonly="readonly"/></div></li>
					<li style="width: 30%"><div class="eachinfor"><div class="lab">全称：</div><input id="WholeName" name="cgworkinfor" style="" value="" type="text" readonly="readonly"/></div></li>
					<li style="float:right;margin-top:10px;margin-right:10px;width: 80px"><div class="saveconfig" onclick="saveinfors()">回&nbsp;&nbsp;退</div></li>
				</ul>
			</div>
			<div class="imginfordiv">
				<!-- <div class="navdiv"><div id="retrans" class="retrans">重&nbsp;&nbsp;传</div></div>
				<div class="setimg">
					<div id="setimg-left" class="setimg-left">
						
					</div>
					<div class="setimg-right"></div>
				</div> -->
				<iframe id="ifrcgworkImg" src=""></iframe>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var form = layui.form,laydate=layui.laydate,layer = layui.layer,laypage = layui.laypage,upload = layui.upload;
	var layer;
	var element;
	var iseditor=true;
	var ischange=false;
	var flag="CGW";
	layui.use(["layer","element"], function(){
		layer = layui.layer;
		element = layui.element;
		//一些事件监听
		element.on("tab(test)", function(data){
			console.log(data.index);
		});
	});
	
	layui.use(["layer","form"], function(){
		layer = layui.layer;
		form = layui.form;
	});
	var cgerId = "<%=request.getAttribute("cgerId")%>";
	var workId = "<%=request.getAttribute("workId")%>";
	var isImpt = "<%=request.getAttribute("isImpt")%>";
	var workstatus = "<%=request.getAttribute("workstatus")%>";
	var sondata="";
	function init(){
	if(isImpt==0){
	$("#isimptBox").html("<div id='notimport' class='samestyle'>其它作品</div>");
	}else{
	$("#isimptBox").html("<div id='isimport' class='samestyle'>重要作品</div>");
	}				
	}
	$(function(){
		init();
		adjust();
		$(window).resize(function() {
			adjust();
		});
		//判断重要与其它高亮
		heightlight(isImpt);
		//获取相关作品
		getworkslist(isImpt);
		//重要与其它切换
		$(".samestyle").click(function(){
			$(this).addClass("classfiy");
			$(this).siblings().removeClass("classfiy");
			var childid = $(this).attr("id");
			if (childid=="isimport") {
				childid=1;
			} else {
				childid=0;
			}
			//获取作品列表
			getworkslist(childid);
		});
		$(".workslist").niceScroll({
				cursorwidth : "6px",
				cursorminheight: 20,
				cursorcolor: "#009688",
				cursorborder: "0px",
				autohidemode : "hidden"
		});		
	});
	
	function heightlight(data){
		if (data=="1") {
			$("#isimport").addClass("classfiy");
			$("#isimport").siblings().removeClass("classfiy");
		} else {
			$("#notimport").addClass("classfiy");
			$("#notimport").siblings().removeClass("classfiy");
		}	
	}
	function getworkslist(data){
		$.ajax({
			url:"getauditworkslist2.do",
			type:"post",
			dataType:"json",
			data:{isimport:data,cgerId:cgerId},
			success:function(data){
				var datalist=data.list;
				var datalist2=data.list2;
				if (datalist.length!=0) {
					showworksList(datalist,workId,datalist2);
				} else {
					var html="	<div class='listcont-child shadow'> "+
							"		<a><img src='image/nohead.jpg'></img></a> "+
							"			<a><div class='listcont-text'> "+
							"				<span></span> "+
							"		</div></a>  "+
							"	</div> ";
					$("#listcont").html("");
					$("#listcont").html(html);
				}
			}
		});
	}
	function flushdata(data1,data2){
		var worksname=$("#WorkName").val();//作品名称
		var wholename=$("#WholeName").val();//作品全称
		var years=$("#Years").val();//年代
		var cgtype=$("#CgType").val();//类型
		var worknum=$("#WorkNun").val();//字数
		var spec=$("#Spec").val();//规格
		var pstcoll=$("#Pstcoll").val();//收藏地 		
		$.ajax({
			url:"updateworkinfors.do",
			type:"post",
			data:{
			"workid":data1,
			"cgid":data2,
			"worksname":worksname,
			"wholename":wholename,
			"years":years,
			"cgtype":cgtype,
			"worknum":worknum,
			"spec":spec,
			"pstcoll":pstcoll
			},
			success:function(data){
		layui.layer.msg('<span style="font-size:20px;color:white">'+data+'</span>');
			ischange=false;
			}
			});	
	}	
	function showworksList(data,data2,data3){
		var html="";
		for ( var i = 0; i < data.length; i++) {
			var workid=data[i].WORKS_ID;//作品标识
			var cgid=data[i].CGER_ID;//书法家标识
			var workname=data[i].WORKS_NAME;//作品名称
			var picname=data3[i].PIC_NAME;
			html="	<div class='listcont-child shadow' id='"+workid+"' data-cgid='"+cgid+"' onclick='worksBaseInfo(this)'> "+
				"		<a><img src='selachievementimg.do?fileName="+picname+"'></img></a> "+
				"			<a><div class='listcont-text' id='"+workid+"'> "+
				"				<span>"+workname+"</span> "+
				"		</div></a>  "+
				"	</div> ";
		}
		$("#listcont").html("");
		$("#listcont").html(html);
		if (workid==data2) {
			$(".listcont-text[id="+data2+"]").addClass("classfiy");
			$(".listcont-text[id="+data2+"]").siblings().removeClass("classfiy");
			//获取作品相关信息
			workinfors(workid,cgid);
		}
	}
	//得到作品图片信息
	function getImageinfo(data){
		var workid=data;
			$.ajax({
			url:"getworksimage.do",
			type:"post",
			dataType:"json",
			data:{"workid":workid},
			success:function(data){
				for(var i=0;i<2;i++){
					/* showImage("2"); */
				}
			}
			});
	}
	//展示作品图片
	function showImage(data){
		var code="";
		code+="";
		$("#imginfordiv").html(code);
	}
	//作品相关信息
	function worksBaseInfo(e){
		var workid=$(e).attr("id");
		var cgid=$(e).attr("data-cgid");
		workinfors(workid,cgid);
	}
	function workinfors(data1,data2){
		$.ajax({
			url:"getworkinfors.do",
			type:"post",
			dataType:"json",
			data:{workid:data1,cgid:data2},
			success:function(data){
				var html="";
				var datalist=data.list;
				$("#WorkName").val(datalist[0]);//作品名称
				$("#WholeName").val(datalist[1]);//作品全称
				$("#Years").val(datalist[2]);//年代
				$("#CgType").val(datalist[3]);//类型
				$("#WorkNun").val(datalist[4]);//字数
				$("#Spec").val(datalist[5]);//规格
				$("#Pstcoll").val(datalist[6]);//收藏地 
				/* $("#CgType").width(50);
				$("#Pstcoll").width($(".workinfordiv ul").width()*0.3-90);
				$("#WholeName").width($(".workinfordiv ul").width()*0.3-60); */
				if (iseditor) {
					$(".eachinfor input").addClass("inputboder");
					$(".eachinfor input").removeAttr("readonly");
				}
				$(".eachinfor input").attr("readonly","readonly");
				$(".eachinfor input").removeClass("inputboder");
				//跳转作品图片编辑
				$("#ifrcgworkImg").attr("src","cgworkseditoraduit.do?workid="+data1+"&cgid="+data2);
				sondata=data1;
			}
		});
	}
	//进入审核页面
	function saveinfors(){
			layui.use(['form', 'layedit', 'laydate'], function(){
        			var form = layui.form
        			,layer = layui.layer
              		layer.open({
              			title: '内容审核'
	          			,type:2
	          			, content:'regresses2.do'
	          			,area: ["650px", "280px;"],
          			}); 
        	});
	}
 	function adjust(){
 		var W  = $(window).width();
 		var H  = $(window).height();
 		$(".rightpath").width($(".main").width()-202);
 		$(".workslist").width($(".leftpath").width()-39);
 		$(".imginfordiv").height($(".rightpath").height()-82);
 		$(".eachinfor input").width($(".eachinfor").width()-$(".lab").width()-2);
 		$(".setimg").height($(".imginfordiv").height()-50);
 		$("#CgType").width(50);
		$("#Pstcoll").width($(".workinfordiv ul").width()*0.3-90);
		$("#WholeName").width($(".workinfordiv ul").width()*0.3-60);
 	}	
	$("input").bind('input propertychange',function(){
		ischange=true;
	 });
	 /**
 * 修改朝代
 */
function changeDynasty(){
	ischange=true;
	var cur_nationid = $("#Years").val();
	$.post("getDynastys.do", {}, function(result){
		var data = JSON.parse(result);
		var html = "<form class='layui-form'><div class='seldiv'><select id='selNation' name='nation' lay-verify='' lay-search>";
		if(data.length!=0){
			for(var i=0;i<data.length;i++){
				if(data[i].nationName==cur_nationid){	
					html += "<option value='"+data[i].nationCode+"' selected='selected'>"+data[i].nationName+"</option>";
				}else{
					html += "<option value='"+data[i].nationCode+"'>"+data[i].nationName+"</option>";
				}
			}
		}
		html += "</select></div></form>";
		layer.open({
			id:"divChangeNation",
			title:"选择朝代",
			type: 1,
			area: ["300px", "180px"],
			shade:[0.5 , "#000" , true],
			skin: "nation-layer",
			btn: ["确定", "取消"],
			btnAlign: "c",
			resize:false,
			content: html,
			yes:function(index, layero){
			 	$("#Years").val($("#selNation option:selected").text());
				$("#Years").attr("cgDynastyid",$("#selNation").val());
				layer.close(index);
			},
			success: function(layero, index){
			    form.render();
			    form.render("select");
			},
		});
	});
}
</script>
</html>
