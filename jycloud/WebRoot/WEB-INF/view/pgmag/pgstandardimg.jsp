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

<title>标准照</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/common.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>

<style type="text/css">
/* *{font-size: 12px;font-family: 微软雅黑;color: #1C1C1C}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0;background-color: #fff;overflow: hidden;} */
.layui-layout-body,.maindiv{
	min-width: 860px !important;
}
/*
body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, code, form, fieldset, legend, input, textarea, p, blockquote, th, td {
    margin: 0;
    padding: 0;
}
.drag_pic_list li.pic {
    cursor: all-scroll;
    display: block;
    text-decoration: none;
}
.drag_pic_list li {
    float: left;
    width: 80px;
    height: 80px;
    position: relative;
    margin: 0 6px 6px 0;
    background: url(image/img_default.png?id=201505071104) no-repeat 0 bottom #f3f3f3;
    text-align: center;
    overflow: hidden;
}
.drag_pic_list .add {
    width: 76px;
    height: 76px;
    border: 2px dashed #ccc;
    background: none;
}
.drag_pic_list .add:HOVER{
	border: 2px dashed #eb7350;
	color: #ccc;
}
.drag_pic_list .add a {
    display: block;
    font-size: 40px;
    font-weight: bold;
    color: #ccc;
    margin-top: 11px;
}
li {
    display: list-item;
    text-align: -webkit-match-parent;
}
ul {
    list-style: none;
}
ul, menu, dir {
    display: block;
    list-style-type: disc;
    -webkit-margin-before: 1em;
    -webkit-margin-after: 1em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
    -webkit-padding-start: 40px;
}
.W_layer a, .W_layer .S_link1, .W_layer a.S_txt1:hover, .W_layer a.current .S_txt1, .W_layer a.S_txt2:hover, .W_layer .SW_fun:hover .S_func1 {
    color: #eb7350;
}
.ico_delpic {
    display: inline-block;
    vertical-align: middle;
    background-image: url(image/ico_layer.png?id=20140918111800);
    background-repeat: no-repeat;
    width: 20px;
    height: 20px;
}
.drag_pic_list .ico_delpic {
    right: 0;
}
.drag_pic_list .ico_editpic, .drag_pic_list .ico_delpic {
    display: none;
    position: absolute;
    top: 0;
}
.ico_delpic {
    background-position: -25px -25px;
}
.drag_pic_list .icon_taged_pic {
    position: absolute;
    right: 0;
    bottom: 0;
}
.icon_taged_pic {
    width: 20px;
    height: 20px;
    background-position: -150px -175px;
}
.W_icon_tag_story, .W_app_level, .W_app_level em, .W_icon_live .l_yizhibo, .W_icon {
    display: inline-block;
    background-image: url(image/icon.png?id=201805241141);
    background-repeat: no-repeat;
} */
body{width: 100%;height: 100%;margin: 0;padding: 0;background-color: #fff;overflow: hidden;}
.standardimg{
    height: 206px;
    line-height: 206px;
    width: 206px;
    float: left;
    margin: 2px;
    text-align: center;
    background:#FAFAFA;
    /* background:rgba(50,50,50,0.1); */
    border: 3px #ccc solid;
    position: relative;
	cursor: move;
}
.ico_delpic{
	background-position: -25px -25px;
	cursor: pointer;
}
.ico_delpic:HOVER{
	background-position: -50px -25px;
}
img{ 
	 max-height:200px; 
	 max-width:200px; 
	 width:expression(this.width > 200 && this.height < this.width ? 200: true); 
	 height:expression(this.height > 200 ? 200: true);
}

.addDiv{
	height: 209px;
    width: 209px;
    float: left;
    margin: 2px;
    text-align: center;
    border: none;
    position: relative;
    z-index: 1;
}
.addDiv .add{
    width: 77px;
    height: 77px;
    line-height: 70px;
    border: 2px dashed #ccc;
    background: none;
    float: left;
    position: relative;
    /* margin: 0 6px 6px 0; */
    margin: 66px;
    text-align: center;
    overflow: hidden;
}
.addDiv .add a {
    font-size: 40px;
    font-weight: bold;
    color: #ccc;
}
.addDiv .add .addUpDiv{
	position: absolute; left: 0px; top: 0px; display: block; overflow: hidden; background-color: rgb(0, 0, 0); opacity: 0; width: 80px; height: 80px;cursor: pointer;
}
.addDiv .add .addUpDiv input{
	cursor:pointer;width:1000px;height:1000px;position:absolute;bottom:0;right:0;font-size:200px;
}
.ico_order{
	position: absolute;top: 0;left: 0;display: inline-block;vertical-align: middle; width: 20px;height: 20px;line-height: 20px;
	background: rgba(0,0,0,0.5);color: #FFF;
	font-size: 12px;
	display: none !important;
}
.ico_delpic{
	position: absolute;top: 0;right: 0;display: inline-block;vertical-align: middle;
	background-image: url(image/ico_layer.png?id=20140918111800);background-repeat: no-repeat; 
	width: 20px;height: 20px;
}
</style>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jscommon.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/Sortable.min.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
  <script src="js/html5.min.js"></script>
  <script src="js/respond.min.js"></script>
<![endif]-->
</head>
<body class="layui-layout-body">
	<div class="maindiv">
		<div class="searchdiv">	
      		<div class="layui-inline" style="float: right;">
		      <div class="layui-input-inline">
		        <button class="layui-btn" onclick="saveStandImgOrder()">保存顺序</button>
		      </div>
		    </div>
		    <div style="clear: both;"></div>
		    <hr class="layui-bg-gray">
	    </div>
		<div id="standardPic" class="standardPic">
			<div id="standardPicList">
			<!-- <div class="standardimg" >
				<img alt="" src="https://wx2.sinaimg.cn/square/878ebaafly1fu4lbdv2xqj21hc0xc4qq.jpg">
				<div class="ico_order">1</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="standardimg" >
				<img alt="" src="image/01.jpg">
				<div class="ico_order">2</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="standardimg" >
				<img alt="" src="image/test/22714445.jpg">
				<div class="ico_order">3</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="standardimg" >
				<img alt="" src="thumbnail/500PX/DELTA 100/537f80c15abd73ee014ecf87cac940e1050aa6d28fe59ba43a334e5d373ecfa6.jpg">
				<div class="ico_order">4</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="standardimg" >
				<img alt="" src="thumbnail/500PX/DELTA 400/dac8cf4be2edc08b97c5459ba0946abc975fcbc5f8d3fb82db30b1bf3f275f1d.jpg">
				<div class="ico_order">5</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="standardimg" >
				<img alt="" src="thumbnail/500PX/DELTA 400/dac8cf4be2edc08b97c5459ba0946abc975fcbc5f8d3fb82db30b1bf3f275f1d.jpg">
				<div class="ico_order">6</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="standardimg" >
				<img alt="" src="image/test/2620975.jpg">
				<div class="ico_order">7</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="standardimg" >
				<img alt="" src="image/test/4748110.jpg">
				<div class="ico_order">8</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="standardimg" >
				<img alt="" src="image/test/2620978.jpg">
				<div class="ico_order">9</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="standardimg" >
				<img alt="" src="image/test/22714443.jpg">
				<div class="ico_order">10</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="standardimg" >
				<img alt="" src="image/test/22714445.jpg">
				<div class="ico_order">11</div>
				<a class="ico_delpic"></a>
			</div>
			<div class="addDiv">
				<div class="add" node-type="uploadBtn">
					<a href="javascript:;" title="">+</a>
					<div style="position: absolute; left: 0px; top: 0px; display: block; overflow: hidden; background-color: rgb(0, 0, 0); opacity: 0; width: 80px; height: 80px;cursor: pointer;">
						<input type="file" name="file" id="test20" style="cursor:pointer;width:1000px;height:1000px;position:absolute;bottom:0;right:0;font-size:200px;">
					</div>
				</div>
			</div> -->
			</div>
			<div class="addDiv">
				<div class="add" node-type="uploadBtn">
					<a href="javascript:;" title="">+</a>
					<div class="addUpDiv">
						<input type="file" name="file" id="test20">
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript">
	var upload = layui.upload;
	var pgId = "<%=request.getAttribute("pgId")%>";
	$(function(){
		adjust();
		$(window).resize(adjust);
		
		var el = document.getElementById("standardPicList");
		var sortable = Sortable.create(el,{
			animation:500
		});
		
		
		$(".add").mouseover(function(){
			$(this).css("border-color","#009688");
			$(this).find("a").css("color","#009688");
		}).mouseout(function(){
			$(this).css("border-color","#ccc");
			$(this).find("a").css("color","#ccc");
		});
		
		init();
	});
	//页面布局调整
	function adjust(){
		var height = $(window).height();
	}
	function init(){
		selStandImgs(pgId);
	}
	//按顺序获取标准照
	function selStandImgs(pgId){
		var url="selStandImgs.do";
		var parameter={"pgId":pgId};
		ajaxRequest(url, parameter, function(data){
			var dataList = data.dataList;
			var html="";
			for(var i=0;i<dataList.length;i++){
				var item = dataList[i];
				var id=item.id;
				var FILE_NAME=item.FILE_NAME;
				var SHOW_ORDER=item.SHOW_ORDER;
				
				html+='<div class="standardimg" >';
				html+='<img alt="" data-src="'+FILE_NAME+'" src="downStandImg.do?fileName='+FILE_NAME+'">';
				html+='<div class="ico_order">'+SHOW_ORDER+'</div>';
				html+='<a class="ico_delpic" data-src="'+FILE_NAME+'" onclick="delpic(this)"></a>';
				html+='</div>';
			}
			$("#standardPicList").html(html);
		});
	}
	
	//保存图片顺序
	function saveStandImgOrder(){
		var length=$(".standardimg").length;
		var resultCnt=0;
		$.each($(".standardimg"),function(){
			var fileName=$(this).find("img").attr("data-src");
			var newOrder=$(this).index()+1;
			var url="updateStandImgOrder.do";
			var parameter={"pgId":pgId,"fileName":fileName,"newOrder":newOrder};
			ajaxRequest(url, parameter, function(data){
				var code=data.code;
				if(code=="1000"){
					resultCnt++;
				}
				if(length==resultCnt){
					layer.msg("标准照顺序更新成功");
					//刷新标准照列表
					selStandImgs(pgId);
				}
			});
		});
	}
	
  //普通图片上传
  var uploadInst = upload.render({
    elem: "#test20"
    ,url: "uploadStandPic.do"
    ,accept:"images"//指定允许上传的文件类型
    ,multiple:false//支持多文件上传
    ,acceptMime:"image/*"//规定打开文件选择框时，筛选出的文件类型
    ,choose: function(obj){
    	var fileName = $("#test20").val();
        this.data={"pgId": pgId,"fileName":fileName};//携带额外的数据
    }
    ,done: function(res){
      
      if(res.code == 0){
      
      	var FILE_NAME=res.data.src;
      	var SHOW_ORDER=res.data.order;
      
        //上传成功
        var html="";
        html+='<div class="standardimg">';
		html+='<img alt="" data-src="'+FILE_NAME+'" src="downStandImg.do?fileName='+FILE_NAME+'">';
		html+='<div class="ico_order">'+SHOW_ORDER+'</div>';
		html+='<a class="ico_delpic" data-src="'+FILE_NAME+'" onclick="delpic(this)"></a>';
		html+='</div>';
		$("#standardPicList").append(html);
        return top.layer.msg(res.msg);
      }else{
      	//如果上传失败
      	return top.layer.msg(res.msg);
      }
      
    }
    ,error: function(){
    }
  });
  //删除标准照
  function delpic(obj){
  	var fileName=$(obj).attr("data-src");
	var url="delStandImg.do";
	var parameter={"pgId":pgId,"fileName":fileName};
	ajaxRequest(url, parameter, function(data){
		var code=data.code;
		var text=data.text;
		if(code=="1000"){
			$(obj).parent(".standardimg").remove();
			saveStandImgOrder();
			//layer.msg(text);
		}
	});
	/* layer.confirm('确认删除标准照？', {icon: 3, title:'提示'},function(index){
        var fileName=$(obj).attr("data-src");
		var url="delStandImg.do";
		var parameter={"pgId":pgId,"fileName":fileName};
		ajaxRequest(url, parameter, function(data){
			var code=data.code;
			var text=data.text;
			if(code=="1000"){
				$(obj).parent(".standardimg").remove();
				saveStandImgOrder();
				//layer.msg(text);
			}
		});
        layer.close(index);
    },function(){
    }); */
  }
</script>
</html>
