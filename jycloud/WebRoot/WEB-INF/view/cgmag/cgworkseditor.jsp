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

<title>作品图片编辑</title>

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
*{font-size: 12px;font-family: 微软雅黑;color: #1C1C1C}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0;background:#FFF;overflow: hidden;}
.main{
	width: 100%;height: 100%;
}
.standardimg{
    height: 145px;
    /* line-height: 126px; */
    width: 116px;
    float: left;
    margin: 2px;
    text-align: center;
    background:#FAFAFA;
    background:rgba(50,50,50,0.1);
    border: 0px #ccc solid;
    position: relative;
}
.ico_delpic{
	background-position: -25px -25px;
	cursor: pointer;
}
.ico_delpic:HOVER{
	/* background-position: -50px -25px; */
}
.standardimg img{ 
	 max-height:100px; 
	 max-width:110px; 
	 width:expression(this.width > 110 && this.height < this.width ? 110: true); 
	 height:expression(this.height > 100 ? 100: true);
	 margin-top: 2px;
	 cursor: pointer;
}
.standardimg .cgwname{ 
	 float:left;
	 width:100%;
	 height: 20px;
	 line-height: 20px;
}
.standardimg .cgimgresetdiv{ 
	 float:left;
	 width:100%;
	 height: 20px;
}
.cgimgreset{
	float: left;
	margin-left: 25%;
	margin-top:2px;
	width: 50%;
	height: 16px;
	line-height: 16px;
	color: #fff;
	text-align: center;
	background: #1E9FFF;
	cursor: pointer;
}
.addDiv{
	height: 116px;
    width: 116px;
    float: left;
    margin: 2px;
    text-align: center;
    border: none;
    position: relative;
    z-index: 1;
}
.addDiv .add{
    width: 84px;
    height: 84px;
    line-height: 70px;
    border: 2px dashed #ccc;
    background: none;
    float: left;
    position: relative;
    /* margin: 0 6px 6px 0; */
    margin: 15px;
    text-align: center;
    overflow: hidden;
}
.addDiv .add a {
    font-size: 60px;
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
	/* background: rgba(0,0,0,0.5);color: #FFF; */
	font-size: 12px;
	display: none !important;
}
.ico_delpic{
	position: absolute;top: 0;right: 0;display: inline-block;vertical-align: middle;
	background-image: url(image/ico_layer.png?id=20140918111800);background-repeat: no-repeat; 
	width: 20px;height: 20px;
}
.navdiv{width: 99%;height: 30px;margin-left: 1%;margin-top: 7px}
.retrans{float:right;margin-right: 10px;margin-top:2px;width:80px;height: 25px;line-height: 25px;text-align: center;background: #1E9FFF;color: #fff;cursor: pointer;}
.imginfordiv{float:left;margin-left:1%;width: 98%;/* height: 98%; */margin-top:1%;border-top: 1px solid #B2B2B2;}
/* .setimg{width:98%;height: 100%;margin-left:1%;margin-top:1%;border-top: 1px solid #B2B2B2;} */
.setimg-left{float:left;width:125px;height: 99%;border-right: 1px dashed rgb(186, 186, 186);overflow: hidden;}
.setimg-right{float:left;height: 100%;}
.showbigImg{width: 98%;height: 98%;margin-left: 1%;margin-top: 1%;}
.showbigImg img{
	width:500px;
	height:500px;
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
<body>
	<div class="main">
		<!-- <div class="searchdiv">	
      		<div class="layui-inline" style="float: right;">
		      <div class="layui-input-inline">
		        <button class="layui-btn" onclick="saveStandImgOrder()">重&nbsp;&nbsp;传</button>
		      </div>
		    </div>
		    <div style="clear: both;"></div>
		    <hr class="layui-bg-gray">
	    </div> -->
	    <div class="navdiv">
	    	<div id="retrans" class="retrans" onclick="deleteall()">重&nbsp;&nbsp;传</div>
	    </div>
		<div id="imginfordiv" class="imginfordiv">
			<div id="setimgleftList" class="setimg-left">
				<div id="leftImgList">
	<!-- 				 <div class="standardimg" >
						<img alt="" data-src="" src="image/nohead.jpg" onclick="showbigImg(this)">
						<div class="ico_order"></div>
						<a class="ico_delpic" data-src="" onclick="delpic(this)"></a>
						<div class="cgwname">test0001</div>
						<div class="cgimgresetdiv">
							<div id="cgimgreset" class="cgimgreset">替&nbsp;换</div>
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
			<div id="setimgrightList" class="setimg-right">
				<div id="showbigImg" class="showbigImg">
					<!-- <img alt="" data-src="" src="image/nohead.jpg"> -->
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript">
	var cgerId = "<%=request.getAttribute("cgerId")%>";
	var workId = "<%=request.getAttribute("workId")%>";
	var upload = layui.upload;
	var uploadInst2="";
	$(function(){
		adjust();
		$(window).resize(adjust);
		
		var el = document.getElementById("leftImgList");
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
		$("#setimgleftList,.showbigImg").niceScroll({
				cursorwidth : "6px",
				cursorminheight: 20,
				cursorcolor: "#009688",
				cursorborder: "0px",
				autohidemode : true
		});
		init();
	});

	function init(){
		selStandImgs(workId);
	}
	//按顺序获取作品图片
	function selStandImgs(Id){
		var url="getCGImgs.do";
		var parameter={"cgerId":cgerId,"workId":Id};
		ajaxRequest(url, parameter, function(data){
			var dataList = data.list;
			var html="";
			var html1="";
			for(var i=0;i<dataList.length;i++){
				var item = dataList[i];
				var wid=item.WORKS_ID;
				var picname=item.PIC_NAME;
				var showorder=item.SHOW_ORDER;
				var cuttype=item.CUT_TYPE;
				var picname2="image"+i;
				html+="<div class='standardimg' >";
				html+="	<img alt='' data-src='"+wid+"' data-name='"+picname+"' src='downCGworkImg.do?fileName="+picname+"' onclick='showbigImg(this)'>";
				html+="	<div class='ico_order'></div>";
				html+="	<a class='ico_delpic' data-src='"+wid+"' data-name='"+picname+"' data-num='"+showorder+"' onclick='delpic(this)'></a>";
				html+="	<div class='cgwname'>"+picname2+"</div>";
			 	html+="	<div class='cgimgresetdiv'>";
				html+="		<div id='cgimgreset' data-src='"+wid+"' data-name='"+picname+"' data-num='"+showorder+"' class='cgimgreset'>替&nbsp;换</div>";
				html+="	</div>";
				html+="</div>";
				if(cuttype=="X"){
				html1+="<img alt='' id='"+picname+"' data-src='' src='downCGworkImg.do?fileName="+picname+"'>";
				$(".showbigImg").css("white-space","nowrap");
				}else{
				$(".showbigImg").css("white-space","");
				html1+="<img alt='' id='"+picname+"' data-src='' src='downCGworkImg.do?fileName="+picname+"'>";
				}
				
			}
			$("#leftImgList").html(html);			
			$("#showbigImg").html(html1);
			addband();
		});
	}
	//添加替换绑定
function addband(){
		    //替换图片上传
  uploadInst2 = upload.render({
    elem: ".cgimgreset"
    ,url: "changeworksPic.do"
    ,accept:"images"//指定允许上传的文件类型
    ,multiple:false//支持多文件上传
    ,acceptMime:"image/*"//规定打开文件选择框时，筛选出的文件类型
    ,choose: function(obj,index,file){
    	var	item=this.item;
    	var fileName2=item.attr("data-name");
    	var workid=item.attr("data-src");
        this.data={"pgId": workid,"fileName2":fileName2};//携带额外的数据
    }
    ,done: function(res){
      
      if(res.code == 0){
      	var FILE_NAME=res.data.src;
      	var SHOW_ORDER=res.data.order;
        //上传成功
 /*        var html="";
				html+="<div class='standardimg' >";
				html+="	<img alt='' data-src='"+workId+"' data-name='"+FILE_NAME+"' src='downCGworkImg.do?fileName="+FILE_NAME+"' onclick='showbigImg(this)'>";
				html+="	<div class='ico_order'></div>";
				html+="	<a class='ico_delpic' data-src='"+workId+"' data-num='"+SHOW_ORDER+"' onclick='delpic(this)'></a>";
				html+="	<div class='cgwname'>"+FILE_NAME+"</div>";
				html+="	<div class='cgimgresetdiv'>";
				html+="		<div id='cgimgreset' data-src='"+workId+"' data-name='"+FILE_NAME+"' data-num='"+SHOW_ORDER+"' class='cgimgreset'>替&nbsp;换</div>";
				html+="	</div>";
				html+="</div>";
		$("#leftImgList").append(html);
		addband(); */
         layui.layer.msg('<span style="font-size:20px;color:white">'+res.msg+'</span>');
         window.parent.flush();
        
        
      }else{
      	//如果上传失败
      	return top.layer.msg(res.msg);
      }
      
    }
    ,error: function(){
    }
  });
	}
	//点击图片右侧显示相应图片
	function showbigImg(e){
		var picname=$(e).attr("data-name");
		location.href = "#"+picname; 
	/* 	var html1="<img alt='' data-src='' src='downCGworkImg.do?fileName="+picname+"'>";
		$("#showbigImg").html(html1); */
	}
	//保存图片顺序
	function saveStandImgOrder(){
		var length=$(".standardimg").length;
		var resultCnt=0;
		$.each($(".standardimg"),function(){
			var fileName=$(this).find("img").attr("data-name");
			var newOrder=$(this).index()+1;
			var url="updateworksImgOrder.do";
			var parameter={"pgId":workId,"fileName":fileName,"newOrder":newOrder};
			ajaxRequest(url, parameter, function(data){
				var code=data.code;
				if(code=="1000"){
					resultCnt++;
				}
				if(length==resultCnt){
					layer.msg("标准照顺序更新成功");
					//刷新标准照列表
					selStandImgs(workId);
					parent.flush();
				}
			});
		});
	}
	
  //普通图片上传
  var uploadInst = upload.render({
    elem: "#test20"
    ,url: "uploadworksPic.do"
    ,accept:"images"//指定允许上传的文件类型
    ,multiple:false//支持多文件上传
    ,acceptMime:"image/*"//规定打开文件选择框时，筛选出的文件类型
    ,choose: function(obj){
    	var fileName = $("#test20").val();
        this.data={"pgId": workId,"fileName":fileName};//携带额外的数据
    }
    ,done: function(res){
      if(res.code == 0){
      
      	var FILE_NAME=res.data.src;
      	var SHOW_ORDER=res.data.order;
      	var picname2="image"+(SHOW_ORDER-1);
        //上传成功
        var html="";
				html+="<div class='standardimg' >";
				html+="	<img alt='' data-src='"+workId+"' data-name='"+FILE_NAME+"' src='downCGworkImg.do?fileName="+FILE_NAME+"' onclick='showbigImg(this)'>";
				html+="	<div class='ico_order'></div>";
				html+="	<a class='ico_delpic' data-src='"+workId+"' data-name='"+FILE_NAME+"' data-num='"+SHOW_ORDER+"' onclick='delpic(this)'></a>";
				html+="	<div class='cgwname'>"+picname2+"</div>";
				html+="	<div class='cgimgresetdiv'>";
				html+="	 <div id='cgimgreset' data-src='"+workId+"' data-name='"+FILE_NAME+"' data-num='"+SHOW_ORDER+"' class='cgimgreset'>替&nbsp;换</div>";
				html+="	</div>";
				html+="</div>";
		$("#leftImgList").append(html);
         layui.layer.msg('<span style="font-size:20px;color:white">'+res.msg+'</span>');
        /*  window.setTimeout("window.location.reload()",1000); */
        window.parent.flush();
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
  	var fileName2=$(obj).attr("data-name");
	var url="delworksImg.do";
	var parameter={"workid":workId,"fileName":fileName2};
	ajaxRequest(url, parameter, function(data){
		var code=data.code;
		var text=data.text;
		if(code=="1000"){
			$(obj).parent(".standardimg").remove();
			saveStandImgOrder();
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
  //重传
  function deleteall(){
  var a=$(".standardimg").length;
  if(a!=0){
  layer.confirm('您正在进行删除，请确认操作', function(index) { 
  	var url="delallworksImg.do";
	var parameter={"workid":workId};
	ajaxRequest(url, parameter, function(data){
		var code=data.code;
		var text=data.text;
		if(code=="1000"){
			/* window.location.reload(); */
			layer.close(index);
		}
	});
	$("#test20").click();
	});	
	}else{
	$("#test20").click();	
	}
  }
	//页面布局调整
	function adjust(){
		$(".imginfordiv").height($(".main").height()-44);
		$(".setimg-right").width($(".imginfordiv").width()-$(".setimg-left").width()-2);
	}
</script>
</html>
