<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
 <head>
   
<title>作品</title>
   
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/cgworks.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/template.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>

<style type="text/css">
*{font-size: 14px;font-family: 微软雅黑;color: #1C1C1C}
html{width: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0;background-color: #fff;}
input{outline: none;}
main{
	overflow-y:auto;
}
.child2 .layui-cg-md2{
	float:left;
	/* background:#FF5722; */
	width:16.6666667%;
	height: 100%;
}
.child2  .cmdlist-container{
	float:left;
	margin-left:6%;
	margin-top:6%;
	width:88%;
	height:94%;
	border: 1px #eee solid !important;
	cursor: pointer;
}
.child2  .cmdlist-container Img{
	width: 100%;
	height: 86%;
	background-size: cover;background-position: center;background-origin:content-box;position: relative;display: inline-block;
}
.child2 .cmdlist-text{
	padding: 5px 5px 5px 20px;
}	
	
</style>
</head>
 
 <body>
   <div class="main">
   		<div class="divpath1">
   			<div class="worksCount"></div>
   			<div class="worksUpload" onclick="worksUpload()">上传新作品</div>
   			<div class="searchdiv">
   				<div class="search-div1">作品名称</div>
   				<input id="search-wkinput" class="search-wkinput" type="text"/>
   				<div id="retrieve" class="search-div2">检索</div>
   			</div>
   		</div>
   		<div class="divpath2">
   			<div class="divpath2-child child1">重要作品</div>
   			<div id="impworks" tabtype="look" class="divpath2-child child2 layui-row">
  				<!-- <div class="layui-cg-md2">
	    			<div class="cmdlist-container">
	    				<a><img src="image/character.jpg"></img></a>
		    			<a><div class='cmdlist-text'>
							<span class="layui-badge">1</span>
		    				<span>王羲之</span>
						</div></a>
	    			</div>
	    		</div> -->
   			</div>
   			<div id="impworks_feny" class="divpath2-child child3" align="middle"></div>
   		</div>
   		<div class="divpath2">
   			<div class="divpath2-child child1">其它作品</div>
   			<div id="othworks" class="divpath2-child child2  layui-row"></div>
   			<div id="othworks_feny" class="divpath2-child child3" align="middle"></div>
   		</div>
   </div>
 </body>
 <script type="text/javascript">
 	var layer,element,laypage,upload,form;
 	var cgerId="<%=request.getAttribute("cgerId")%>";
	var pageNum =6;
	layui.use(["layer","element"], function(){
		layer = layui.layer;
		element = layui.element;
		//一些事件监听
		element.on("tab(test)", function(data){
			console.log(data.index);
		});
	}); 	
 	$(function(){
 		adjust();
 		//获取作品页数
 		getPagenum();
 	});
 	function getPagenum(){
 		var workname=$("#search-wkinput").val();
 		$.ajax({
 			url:"getPagenum.do",
 			type:"post",
 			dataType:"json",
 			data:{cgerId:cgerId,workname:workname,pageNum:pageNum,pageSize:1},
 			success:function(data){
 				if(data.countNum!=0){
 					$(".worksCount").html("共"+data.countNum+"部作品");
	 				showImpworks(data);
	 				showOthworks(data);
 				}else {
					$(".worksCount").html("共0部作品");
					showImpworks(data);
	 				showOthworks(data);
				}
 			}
 		});
 	}
 	//重要作品
 	function showImpworks(data){
 		var workname=$("#search-wkinput").val();
 		if (data.countNum1!=0) {
			layui.use(['laypage'], function(){
				laypage = layui.laypage;
				 //总页数大于页码总数
				 laypage.render({
				   elem: 'impworks_feny'
				   ,count: data.countNum1//数据总数
				  	,limit:pageNum
				  	,layout: ['count', 'prev', 'page', 'next','skip']
				   ,jump: function(obj,first){
				     console.log(obj);
				     if (!first) {
				     	impworks_showdatas(obj.curr,pageNum,true,workname,cgerId);
					 }else{
					 	impworks_showdatasfirst(data.list3,obj.curr);
					 }
				   }
				});	
			});
		} else {
			$("#impworks").html("");
			layui.use(['laypage'], function(){
				laypage = layui.laypage;
				laypage.render({
				   elem: 'impworks_feny'
				   ,count: 0
				  	,limit:6
				  	,layout: ['count', 'prev', 'page', 'next','skip']
				   ,jump: function(obj,first){
				   }
				});
			});
		}
 	}
 	//其它作品
 	function showOthworks(data){
 		var workname=$("#search-wkinput").val();
 		if (data.countNum2!=0) {
			layui.use(['laypage'], function(){
				laypage = layui.laypage;
				 //总页数大于页码总数
				 laypage.render({
				   elem: 'othworks_feny'
				   ,count: data.countNum2//数据总数
				  	,limit:pageNum
				  	,layout: ['count', 'prev', 'page', 'next','skip']
				   ,jump: function(obj,first){
				     console.log(obj);
				     if (!first) {
				     	othworks_showdatas(obj.curr,pageNum,true,workname,cgerId);
					 }else{
					 	othworks_showdatasfirst(data.list4,obj.curr);
					 }
				   }
				});	
			});
		} else {
			$("#othworks").html("");
			layui.use(['laypage'], function(){
				laypage = layui.laypage;
				laypage.render({
				   elem: 'othworks_feny'
				   ,count: 0
				  	,limit:6
				  	,layout: ['count', 'prev', 'page', 'next','skip']
				   ,jump: function(obj,first){
				   }
				});
			});
		}
 	}
 	
	function impworks_showdatasfirst(data,curr){
		var html="";
		for ( var i = 0; i < data.length; i++) {
			var item = data[i];
			var workid=item.WORKS_ID;
			var workname=item.WORKS_NAME;
			var cgerid=item.CGER_ID;
			var order=item.SHOW_ORDER;
			var isimpt=item.IS_IMPT_WORKS;
			var filename=item.PIC_NAME;
			var status=item.AUDIT_STATUS;
			if(status==5){
			html+="<div id='"+workid+"' class='layui-cg-md2'> "+
			"	<div class='cmdlist-container' data-wid='"+workid+"' data-cgid='"+cgerid+"' data-wname='"+workname+"' data-impt='"+isimpt+"' onclick='showWorksInfo(this)'> "+
			"		<a><img style='width:230px;height:230px' src='selachievementimg.do?fileName="+filename+"'></img></a> "+
			"		<a><div class='cmdlist-text'> "+
			"		<span class='layui-badge'>"+order+"</span> "+
			"			<span>"+workname+"</span> "+
			"	</div></a> "+
			"	</div> "+
			"</div> ";
		}else{
			html+="<div id='"+workid+"' class='layui-cg-md2'> "+
			"	<div class='cmdlist-container' data-wid='"+workid+"' data-cgid='"+cgerid+"' data-wname='"+workname+"' data-impt='"+isimpt+"' onclick='showWorksInfo(this)'> "+
			"		<a><img style='width:230px;height:230px' src='selachievementimg.do?fileName="+filename+"'></img></a> "+
			"		<a><div class='cmdlist-text'> "+
			"		<span class='layui-badge'>"+order+"</span> "+
			"			<span>"+workname+"</span> "+
			"	</div></a> "+
			"	</div> "+
			"</div> ";		
		}
		
		}
		$("#impworks").html("");
		$("#impworks").html(html);
	} 	
	function othworks_showdatasfirst(data,curr){
		var html="";
		for ( var i = 0; i < data.length; i++) {
			var item = data[i];
			var workid=item.WORKS_ID;
			var workname=item.WORKS_NAME;
			var cgerid=item.CGER_ID;
			var order=item.SHOW_ORDER;
			var isimpt=item.IS_IMPT_WORKS;
			var filename=item.PIC_NAME;
			var status=item.AUDIT_STATUS;
			if(status==5){
			html+="<div id='"+workid+"' class='layui-cg-md2'> "+
			"	<div class='cmdlist-container' data-wid='"+workid+"' data-cgid='"+cgerid+"' data-wname='"+workname+"' data-impt='"+isimpt+"' onclick='showWorksInfo(this)'> "+
			"		<a><img style='width:230px;height:230px' src='selachievementimg.do?fileName="+filename+"'></img></a> "+
			"		<a><div class='cmdlist-text'> "+
			"		<span class='layui-badge'>"+order+"</span> "+
			"			<span>"+workname+"</span> "+
			"	</div></a> "+
			"	</div> "+
			"</div> ";
			}else{
			html+="<div id='"+workid+"' class='layui-cg-md2'> "+
			"	<div class='cmdlist-container' data-wid='"+workid+"' data-cgid='"+cgerid+"' data-wname='"+workname+"' data-impt='"+isimpt+"' onclick='showWorksInfo(this)'> "+
			"		<a><img style='width:230px;height:230px' src='selachievementimg.do?fileName="+filename+"'></img></a> "+
			"		<a><div class='cmdlist-text'> "+
			"		<span class='layui-badge'>"+order+"</span> "+
			"			<span>"+workname+"</span> "+
			"	</div></a> "+
			"	</div> "+
			"</div> ";			
			}
		}
		$("#othworks").html("");
		$("#othworks").html(html);
	}
	//作品信息信息
	function showWorksInfo(e) {
		var workId=$(e).attr("data-wid");
		var cgerId=$(e).attr("data-cgid");
		var workName=$(e).attr("data-wname");
		var isImpt=$(e).attr("data-impt");
		var that = e; 
        //多窗口模式，层叠置顶
        layui.use(['layer','form'], function(){
        	layer = layui.layer;
	        layer.open({
		        type: 2 //此处以iframe举例
		        ,title: workName
		        ,area: ['1000px', '700px']
		        ,shade: 0.3
		        ,maxmin: false
		        ,offset: 'auto'
		        ,content: 'worksDetail.do?workId='+workId+'&cgerId='+cgerId+'&isImpt='+isImpt
		        ,zIndex: layer.zIndex //重点1 
		        ,success: function(layero){
		        	
		        }
	        });
        });
	} 	
	
	//作品信息信息
	function showWorksInfo2(e) {
		var workId=$(e).attr("data-wid");
		var cgerId=$(e).attr("data-cgid");
		var workName=$(e).attr("data-wname");
		var isImpt=$(e).attr("data-impt");
		var that = e; 
        //多窗口模式，层叠置顶
        layui.use(['layer','form'], function(){
        	layer = layui.layer;
	        window.parent.window.parent.layer.open({
		        type: 2 //此处以iframe举例
		        ,title: workName
		        ,area: ['1000px', '600px']
		        ,shade: 0.3
		        ,maxmin: true
		        ,offset: 'auto'
		        ,content: 'worksDetailCPG.do?workId='+workId+'&cgerId='+cgerId+'&isImpt='+isImpt
		        ,zIndex: layer.zIndex //重点1 
		        ,success: function(layero){
		        	
		        }
	        });
        });
	} 	
 	//检索
 	$("#retrieve").click(function(){
 		getPagenum();
 	});
 	//作品上传
 	function worksUpload(){
 		var workName="新作品上传";
 		var workId="";
 		alert(cgerId);
 		 //多窗口模式，层叠置顶
        layui.use(['layer','form'], function(){
        	layer = layui.layer;
	        window.parent.window.parent.layer.open({
		        type: 2 //此处以iframe举例
		        ,title: workName
		        ,area: ['1000px', '600px']
		        ,shade: 0.3
		        ,maxmin: true
		        ,offset: 'auto'
		        ,content: 'cgworksupload.do?workId='+workId+'&cgerId='+cgerId
		        ,zIndex: layer.zIndex //重点1 
		        ,success: function(layero){
		        	
		        }
	        });
        });
 	}
 	
 	function adjust(){
/*  		var W  = $(window).width();
 		var H  = $(window).height();
 		$(".divpath2").height((H-40-5)/2);
 		$(".child2").height($(".divpath2").height()-85); */
 	}
 	function flush(){
 	getPagenum();
 	}
 </script>
</html>
