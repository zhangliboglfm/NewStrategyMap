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
<title>核心介绍</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>


<style type="text/css">
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
.main{width: 100%;height: 99%;overflow: hidden;background-color:#F2F2F2;border-top: 1px solid #E0E0E0;font-family: 微软雅黑; }
@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1532661539245'); /* IE9*/
  src: url('iconfont.eot?t=1532661539245#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
  url('iconfont.ttf?t=1532661539245') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1532661539245#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
input{outline:none;}  
.topSearch{float:left;margin-left:1%;margin-top:1%;width:98%;height: 30px;line-height:30px;border-bottom: 1px solid #000;font-size: 13px;}
.ryCounts{float:left;margin-left:15px;width:120px;height: 30px;line-height:30px;}
.serchModel{float:right;margin-right: 15px;width: 260px;height: 30px;border: 0px solid #fff;box-sizing: border-box;}
.explain{float: left;width:100px;height: 30px;line-height:30px;color: #000}
.serchKuang{float: left;margin-top:2px;width:130px;height: 24px;line-height:24px;border: 1px solid #fff;box-sizing: border-box;text-indent: 1ex;}
.searchIcon{float: left;margin-top:2px;width:30px;height: 24px;line-height:24px;background-color: #009688;cursor: pointer;color: #fff;text-align: center;}
.showContents{float:left;width:100%;}
.ry_photoWall{float:left;margin-left:25px;margin-top:25px;height:135px;background-color:#fff;position: relative;font-size: 13px;}
.ry_photo{float:left;width:100px;height:100%;}
.ry_photo img{width:100%;height:100%;}
.ry_name{float:left;margin-top:10px;width:70px;height: 30px;line-height: 30px;text-align:center;color: #009688;font-size: 16px;border-bottom: 1px solid #009688;box-sizing: border-box;}
.ry_profiles{float:left;margin-top: 50px;width:70px;height: 30px;line-height: 30px;color: #009688;border: 1px solid #009688;text-align:center;cursor: pointer;}
.personalBK{left:0;top:0;width:100%;height:100%;background:#182840;filter:alpha(opacity=70);opacity:0.70;position:absolute;z-index:999;display: none;}
.closeIcon{width: 100%;height: 40px;}
.closeicon{float: right;margin-top:10px;margin-right:10px;width:30px;height: 30px;line-height:30px;cursor: pointer;color: #009688;text-align: center;font-size: 25px;}
.personalInfor{float:left;margin-left: 20%;margin-top:5px;width: 60%;height: 90%;box-sizing: border-box;border: 1px solid red;}
</style>
</head>

<body>
	<div class="main">
		<div class="topSearch">
			<div id="ryCounts" class="ryCounts"></div>
			<div class="serchModel">
				<div class="explain">按摄影家搜索：</div>
				<input id="zz_serch" class="serchKuang" type="text" />
				<i id="au_icon" class="iconfont searchIcon">&#xe64c;</i>
			</div>
		</div>
		<div id="showContents" class="showContents scroll">
			<!-- <div class="ry_photoWall">
				<div class="ry_photo">
					<img src="image/01.jpg" alt="文章1"></img>
				</div>
				<div class="ry_name">某某某</div>
				<div class="ry_profiles">人物介绍</div>
			</div> -->
		</div>
		<div id="fenye" style="float:left;height:32px;width:99%;margin-top:15px;border: 0px solid red" align="right"></div>
		<div class="personalBK">
			<div class="closeIcon">
				<i id="closeicon" class="iconfont closeicon">&#xe602;</i>
			</div>
			<div class="personalInfor"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var articleName="<%=request.getAttribute("articleName")%>";
	var author="<%=request.getAttribute("author")%>";
	var laypage;
	var pageNum =15;
	$(function(){
		adjust();
		$(window).resize(adjust);
		//滚动条部分设置
		$(".scroll").niceScroll({
			cursorcolor : "#40C5C0",//滚动条显示的颜色
			cursorborderradius: "2px",//滚动条边角圆弧
			cursorwidth: "4px",//滚动条宽度
			cursorborder: "1px solid #40C5C0",//滚动条边线
			autohidemode:true//是否自动隐藏
		});	
		//获取文章总数
		ryCounts();	
	});
	//按作者搜索
	$("#au_icon").click(function(){
		ryCounts();
	});
	function ryCounts(){
		var zzName=$("#zz_serch").val();
		$.ajax({
			url:"getRyCounts.do",
			type:"post",
			dataType:"json",
			data:{zzName:zzName,pageNum:pageNum,pageSize:1},
			success:function(data){
				if (data!=null&&data.list.length!=null) {
					$("#ryCounts").html("共计人数"+data.countNum+"人");
					layui.use(['laypage'], function(){
						laypage = layui.laypage;
						 //总页数大于页码总数
						 laypage.render({
						   elem: 'fenye'
						   ,count: data.countNum//数据总数
						  	,limit:pageNum
						  	,layout: ['count', 'prev', 'page', 'next','skip']
						   ,jump: function(obj,first){
						     console.log(obj);
						     if (!first) {
						     	showdatasSYJ(obj.curr,pageNum,true,zzName);
							 }else{
							 	showdatasfirstSYJ(data.list,obj.curr);
							 }
							 }
							});	
						});
				}else {
					$("#ryCounts").html("共计人数0人");
					$("#showContents").html("");
					$("#showContents").html("暂无介绍");
				}
			}
		});
	}
	function showdatasfirstSYJ(data,curr){
		var html="";
		for(var i=0;i<data.length;i++){
			html+="<div id='"+data[i].PHOTOG_ID+"' class='ry_photoWall'>"+
				"<div class='ry_photo'>"+
				"<img src='getPhtotogImg.do?filename="+data[i].FILE_NAME+"' alt='"+data[i].PIC_TYPE+"'></img>"+
				"</div>"+
				"<div class='ry_name'>"+data[i].PHOTOG_NAME+"</div>"+
				"<div class='ry_profiles' onclick='resume(this)'>人物介绍</div>"+
				"</div>";
		}
		$("#showContents").html("");
		$("#showContents").html(html);
		adjust();
	}	
	//详细个人简介
	function resume(e){
		var photogId = $(e).parent().attr("id");
		var layId="789";
		var tableData={
			"title":"人物介绍",
			"lay-id":"789",
			"src":"reading.do?articleId="+photogId+"&layId="+layId
		};
		window.parent.addTable(tableData);
	}
	//调整页面布局
	function adjust(){
		var H = $(".main").height();
		var W = $(".main").width();
		$(".showContents").height(H-30-37-50);
		$(".ry_photoWall").width(($(".showContents").width()-25*6)/5);
		$(".ry_name").css("margin-left",($(".ry_photoWall").width()-100-70)/2);
		$(".ry_profiles").css("margin-left",($(".ry_photoWall").width()-100-70)/2);
	};	
	//分页摄影家查询
	function showdatasSYJ(pageSize,pageNum,toinit,zzName){
		$.ajax({
			url:"getRyCounts.do",
			type:"post",
			dataType:"json",
			data:{zzName:zzName,pageNum:pageNum,pageSize:pageSize},
			success:function(data){
				if (data!=null&&data.list.length!=null) {
					$("#ryCounts").html("共计人数"+data.countNum+"人");
					if(toinit){
						layui.use(['laypage'], function(){
							laypage = layui.laypage;
							 //总页数大于页码总数
							 laypage.render({
							   elem: 'fenye'
							   ,count: data.countNum//数据总数
							  	,limit:pageNum
							  	,layout: ['count', 'prev', 'page', 'next','skip']
							   ,jump: function(obj,first){
							     console.log(obj);
							     if (!first) {
							     	showdatasfirstSYJ(data.list,obj.curr);
								 }
								 }
								});	
							});
					}
				}else {
					$("#ryCounts").html("共计人数0人");
					$("#showContents").html("");
					$("#showContents").html("暂无介绍");
				}
			}
		});	
	
	
	
	
	
	

	}	
</script>
</html>