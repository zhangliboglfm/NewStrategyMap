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
<title>App查看</title>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="js/jquery1.10.3-ui.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

</style>

<link rel="stylesheet" href="css/messagecheck.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" href="css/appsetting.css" type="text/css"></link>

</head>
<body class="layui-layout-body">
    <div class="layui-tab layui-tab-card" lay-filter="demo" lay-allowclose="true">
      <ul class="layui-tab-title">
        <li class="layui-this cantdele">App列表</li>
      </ul>
      <div class="layui-tab-content"  style="margin-top:2px;position: relative;">
        <div class="layui-tab-item layui-show" >
        	<div class="layui-main">
				<div class="layui-content">
					<iframe class="layui-ifr" id="myifr" src="" frameborder="no"></iframe>
				</div>
			</div>
        </div>
      </div>
    </div>
	
</body>
<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript">
	//var element,laydate,laypage,layer,upload,form;
	var form = layui.form,laydate=layui.laydate,layer = layui.layer,laypage = layui.laypage,element=layui.element;
	//正在操作的摄影家div和摄影家id
	var photogObj,operaPhotog;
	$(function(){
		adjust();
		$(window).resize(adjust);
		$("#myifr").attr("src","applist.do");
	});
	
	//添加tab切换页面
	function addTable(data){
		if($("li[lay-id='"+data["lay-id"]+"']").size()<=0){
			element.tabAdd('demo', {
		        title: data["title"] //用于演示
		        ,content:"<iframe frameborder='no' class='ifr'   src='"+data["src"]+"'></iframe>"
		        ,id:data["lay-id"] //实际使用一般是规定好的id，这里以时间戳模拟下
		      });
		}
		$(".layui-show").removeClass("layui-show");
		$(".layui-this").removeClass("layui-this");
		var index =$("li[lay-id='"+data["lay-id"]+"']").index();
		$(".layui-tab-content .layui-tab-item").eq(index).addClass("layui-show");
		$("li[lay-id='"+data["lay-id"]+"']").addClass("layui-this");
	};
	
	//调整页面布局
	function adjust(){
		$(".layui-tab-content").height($(".layui-tab").height()-45);
		var height = $(".layui-tab-content").height()-40;
		//文章面板tab
		var H = $(".layui-tab-item").height();
		$(".layui-content").height(H-$(".layui-breadcrumb").height());
		
	};
</script>
</html>