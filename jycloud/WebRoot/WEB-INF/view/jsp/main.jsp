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
<title>精益云后台管理</title>
<script type="text/javascript" src="layui/layui.js"></script>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin:0;padding:0}
.ifr{width: 100%;height: 100%;border: 0px;}
.layui-layout-admin .layui-body{
	bottom:0px !important;
	overflow: hidden !important;
}
.layui-side{
	position:absolute !important;
}
.iconfont{
	display: block;
	position: absolute;
	top:50%;
	left: 200px;
    width: 20px;
    height: 50px;
    background: rgba(57,61,73,0.3);
    font-size: 16px;
    line-height: 50px;
    z-index: 999999;
    cursor: pointer;
}
</style>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
</head>
<body class="layui-layout-body">
<i class=" layui-icon iconfont" id="ifont" data-status="0" onclick="sliderDiv(this)">&#xe65a;</i> 
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">精益云后台管理</div>
    <ul id="topNav" class="layui-nav layui-layout-left">
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a id="userName" href="javascript:void(0)"></a>
        <dl class="layui-nav-child">
          <dd><a href="resetPassword.do" target="_blank">修改密码</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="login.do">退出</a></li>
    </ul>
  </div>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul id="leftNav" class="layui-nav layui-nav-tree"  lay-filter="test">
      	 
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
  	<iframe id="iframe" name="childifr0" class="ifr"  src=""></iframe>
  </div>
  
</div>
</body>
<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript">
	var topNav = <%=request.getAttribute("topNav")%>;
	var userId = "<%=request.getAttribute("userId")%>";
	var userName = "<%=request.getAttribute("userName")%>";
	var element;
	var layer = layui.layer;
	$(function(){
		$("#userName").text(userName);
		adjust();
		$(window).resize(adjust);
		layui.use('element', function(){
			element=layui.element;
		});
		montageTopNav(topNav);
		
	});
	// 根据查询数据获取相应权限信息
	function montageTopNav(data){
		var str="";
		for(var i =0;i<data.length;i++){
			if(i==0){
				str+="<li class='layui-nav-item layui-this' data_id='"+data[0].module_id+"' onclick='changeNav(this)'><a href='javascript:void(0)'>"+data[0].module_name+"</a></li>";
				montageLeftNav(data[0].module_id);
			}else{
				str+="<li class='layui-nav-item' data_id='"+data[i].module_id+"' onclick='changeNav(this)'><a href='javascript:void(0)'>"+data[i].module_name+"</a></li>";
			}
		};
		$("#topNav").html(str);
		
	};
	
	//调整页面布局
	function adjust(){
		$(".bottom").height($("body").height()-60);
	};
	
	//左侧导航展开、收缩
	function sliderDiv(obj){
		if($(obj).attr("data-status")==0){
			$(obj).html("&#xe65b;");
			$(".layui-side").velocity({"left":-200},{duration:1000,begin:function(){
				$(obj).velocity({"left":0},{duration:1000});
				$(".layui-body").velocity({"left":0},{duration:1000});
			}});
			$(obj).attr("data-status","1");
		}else{
			$(obj).html("&#xe65a;");
			$(".layui-side").velocity({"left":0},{duration:1000,begin:function(){
				$(obj).velocity({"left":200},{duration:1000});
				$(".layui-body").velocity({"left":200},{duration:1000});
			}});
			$(obj).attr("data-status","0");
		};
	};
	
	//切换顶部导航导航
	function changeNav(obj){
		$(".layui-this").removeClass("layui-this");
		$(obj).addClass("layui-this");
		montageLeftNav($(obj).attr("data_id"));
	};
	
	// 拼接左侧导航
	function montageLeftNav(module_Id){
		if($("#ifont").attr("data-status")==1){
				$("#ifont").trigger("click");
		};
		$.ajax({
			url:"getLeftNav.do",
			type:"post",
			dataType:"json",
			data:{"module_id":module_Id},
			success:function(data){
				if(data!=null&&data.length!=0){
					var str="";
					for(var i=0;i<data.length;i++){
						if(i==0){
							str+="<li class='layui-nav-item layui-nav-itemed'>";
						}else{
							str+="<li class='layui-nav-item'>";
						};
				          str+="<a class='' href='javascript:void(0)'>"+data[i].module_name+"</a>"+
				          "<dl class='layui-nav-child'>";
				           var childlist = data[i].childList;
				           for(var j=0;j<childlist.length;j++){
				           		if(i==0&&j==0){
					           		str+="<dd class='layui-this' onclick='changeIframe(\""+childlist[j].module_url+"\")'><a href='javascript:void(0)'>"+childlist[j].module_name+"</a></dd>";
					           		$("#iframe").attr("src",childlist[j].module_url);
				           		}else{
				           			str+="<dd><a href='javascript:void(0)' onclick='changeIframe(\""+childlist[j].module_url+"\")' >"+childlist[j].module_name+"</a></dd>";
				           		};
				           };
				          str+="</dl></li>";
					};
					$("#leftNav").html(str);
					element.init();
				}else{
					$("#leftNav").html("");
					$("#iframe").attr("src","");
				}
			}		
		});
	};
	
	function changeIframe(str){
		$("#iframe").attr("src",str);
	};
	$("#safeSetting").click(function () {
		 window.open("resetPassword.do");
	});
</script>
</html>