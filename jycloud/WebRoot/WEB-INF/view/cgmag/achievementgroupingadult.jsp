<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>成就分组审核</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="layui/css/layui.css" type="text/css" media="all"></link>
	<link rel="stylesheet" href="layui/css/template.css" type="text/css"></link>
	<link rel="stylesheet" href="layui/css/admin.css" type="text/css"></link>
	<link rel="stylesheet" href="css/login.css" type="text/css"></link>
	<link rel="stylesheet" href="css/admin.css" type="text/css"></link>
	<style type="text/css">
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
		.layui-layout-body,.maindiv{
			min-width: 860px !important;
		}
		.cmdlist-container{
			max-width: 300px;
			max-height: 360px;
			border: 1px #eee solid !important;
		}
		.cmdlist-container img{
			min-width: 120px;
			min-height: 120px;
			max-width: 300px;
			max-height: 300px;
		}
		.searchdiv{
			height: 60px;
			background:#fff;
			z-index:1;
		}
		.searchdiv .layui-inline{
			margin-top: 10px;
		}
		.searchdiv .layui-inline .layui-form-label{
			width: auto !important;
		}
		#photoglist .cmdlist-container{
			cursor: pointer;
		}
		
		#photoglist,#footpage{
			margin-left:5px;
			margin-right:5px;
		}
		
		/* 阴影 */
		.shadow{
			-webkit-box-shadow:0px 0px 3px #a0a0a0;
	    	-moz-box-shadow:0px 0px 3px #a0a0a0;
	    	box-shadow:0px 0px 3px #a0a0a0;
			/* 投影数字顺序依次是：右边 下边 投影模糊大小 */
			/* 内阴影数字顺序依次是：左边 上边 阴影模糊大小 */
		}
		.iconadd{
			text-align:center;
			font-size:120px;
			color:#BDD7EE;
			border:1px solid grey;
			padding-left:10px;
			padding-right:10px;
			padding-bottom:35px;
		}
		.add{
		}
		.img{
			width:115px;
			height:160px;
		}
		.img2{
			width:100%;
		}
		
		.option{
			height:410px;
			border:2px solid #C6C9C9;
			margin-left:3%;
			margin-left:5%;
			margin-top:2%;
			margin-bottom:2%;
			padding:1%;
			position:relative;
			
		}
		.first{
			font-weight:bold;
			color:black;
		}
		.cginfo{
			margin-bottom:1%;
			padding-left:2%;
		}
		.cgds{
			color:black;
			margin-top:1%;
			padding-left:1%;
		}
		.bottompart{
			padding-left:4%;
		}
		.addicon{
			height:190px;
			text-align:center;
			background:#D8E5F0;
			margin-left:10px;
			position:relative;
			width:115px;
			float:left;
			border:2px solid #C6C9C9;
		}
		.image2{
			line-height:190px;
		}
		.cgimagename{
			line-height:30px;
			text-align:center;
			font-size:15px;
			color:black;
		}
		.add{
			line-height:340px;
			text-align:center;
		}
		.deletePhotog{
			position:absolute;right:10px;top:10px;
		}
		.aginfopart{
			height:130px;
		}
	</style>
	
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/jscommon.js"></script>
	<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
	<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
	<!--[if lt IE 9]>
	  <script src="js/html5.min.js"></script>
	  <script src="js/respond.min.js"></script>
	<![endif]-->
  </head>
  
  <body class="layui-layout-body">
    <div class="maindiv">
    	<div class="searchdiv">	
    		<div class="layui-inline"> <!-- 注意：这一层元素并不是必须的 -->
		    	<label class="layui-form-label">成就分组管理</label>
			</div>
      		<div class="layui-inline" style="float:right;margin-left:1%;margin-right:2%">
		      <div class="layui-input-inline">
		        <button class="layui-btn" onclick="searchachievement()" style="margin-top: 1px !important;">检索</button>
		      </div>
		    </div>
		    <div class="layui-inline" style="float:right">
		      <div class="layui-input-inline">
		        <input type="text" id="serchachievementName" autocomplete="off" class="layui-input">
		      </div>
		    </div>
      		<div class="layui-inline" style="float:right;margin-left:2%;margin-right:2%">
		      <label class="layui-form-label">审核状态</label>
		      <div class="layui-input-inline">
		      	<div class="layui-form">
		      		<select id="select" lay-verify="required" onchange="selchange()">
			          <option value="1">未审核</option>
			          <option value="5">已审核</option>
			        </select>
		      	</div>
		      </div>
		    </div>		    
		    <hr class="layui-bg-grey">
	    </div>
		<div class="layui-row layui-col-space40" id="middle">		
<!-- 		  <div class="option layui-col-md5" >
		   	<div class="layui-row layui-col-space20">
		   		<div  class="layui-col-md12" style="text-align:center">楷书四大家(共4位)</div>
		   		<div>
		   			<div class="layui-col-md6 cginfo">
		   			<span class="first">名称:</span><span class="second">四大楷书</span>
		   			</div>
		   			<div class="layui-col-md6 cginfo">
		   			<span class="first">别称:</span><span class="second">四大楷书</span>
		   			</div>
		   			<div class="layui-col-md6 cginfo">
		   			<span class="first">简述:</span><span class="second">简述简述简述</span>
		   			</div>
		   			<div class="layui-col-md12 cgds">
		   			<div>成员</div>
		   			</div>
		   			<hr class="layui-bg-grey">
		   			<div class="layui-col-md12 bottompart">
			   			<div class="layui-col-md2 addicon">
							<div>
								<img class="img" src="image/cgimage1.png">						
							</div>																
			   			</div>	
			   			<div class="layui-col-md2 addicon">
							<div>
								<img class="img"  src="image/cgimage1.png">						
							</div>
							<div class="cgimagename">
								颜真卿
							</div>								
			   			</div>	
			   			<div class="layui-col-md2 addicon">
							<div>
								<img class="img"  src="image/cgimage1.png">						
							</div>								
			   			</div>			   					   			
			   			<div class="layui-col-md2 addicon">
							<div>
								<img class="img"  src="image/cgimage1.png">						
							</div>								
			   			</div>
			   			<div class="layui-col-md2 addicon" style="border:2px solid #4F7A9E;">
							<div class="image2">
							<img class="img2"  src="image/cgimage2.png">	
							</div>		   			
			   			</div>				   					   					   			   					   					   			
		   		</div>
		   	</div>
		  </div>
		</div>	 -->
<!-- 		<div class="option layui-col-md5" >
			<div class="add">
			<img class="img3"  src="image/cgimage2.png">
			<div>
		</div> -->			
						
    </div>
  </body>
  <script type="text/javascript" src="layui/layui.all.js"></script>
  <script type="text/javascript">
  	var myDate = new Date();
  	var sondata="";
  	var flag="AG";
	layui.use('laydate', function(){
	 	var laydate = layui.laydate;
		//执行一个laydate实例
		laydate.render({
		elem:'#test1' //指定元素
		,value:myDate
		});
	});
	$(function(){
	/* alert($("#select option:selected").val()); */
		getAgInfo();
	});
	function getAgInfo(){
	var status=$("#select option:selected").val();
		$.ajax({
			url:"getWaitAchievementinfo.do",
			type:"post",
			dataType:"json",
			async: false,
			data:{"status":status},
			success:function(data){
				createPart(data);
				for(var i=0;i<data.length;i++){
					getImageInfo(data[i].agid,data[i]);
				}
			}
		});
	}
	function getImageInfo(data1,data2){
		var str1=data2.agid;
		$.ajax({
			url:"getAchievementaginfo.do",
			type:"post",
			dataType:"json",
			async: false,
			data:{"data":str1},
			success:function(data){
				if(data[0].cgername!=null&&data.length!=0){
					for(var i=0;i<data.length;i++){
						createImage(data[i],data2);
					}
				}

			}
		});	
	}

	function searchAchievementaginfo(){
		var status=$("#select option:selected").val();
		var data=$("#serchachievementName").val();
		$.ajax({
			url:"searchWaitAchievementaginfo.do",
			type:"post",
			dataType:"json",
			data:{"data":data,"status":status},
			success:function(data){
				createPart(data);
				for(var i=0;i<data.length;i++){
					searchAchievementagimageinfo(data[i].agid,data[i]);
				}
				
			}
		});	
		$("#serchachievementName").val("");
	}
	function searchAchievementagimageinfo(data1,data2){
	var str1=data2.agid;
		$.ajax({
			url:"searchAchievementagimageinfo.do",
			type:"post",
			dataType:"json",
			data:{"data":str1},
			success:function(data){
				if(data[0].cgername!=null&&data.length!=0){
				for(var i=0;i<data.length;i++){
					createImage(data[i],data2);
				}
				}
			}
		});	
	}
	function searchachievement(){
	searchAchievementaginfo();
	}
	function createPart(data){
	var code="";
	for(var i=0;i<data.length;i++){
		if(data[i].status==1){
		code+="<div class='option layui-col-md5' id='"+data[i].agid+"'>"+
				"<div class='deletePhotog'>"+
				"<button class='layui-btn layui-btn-normal' onclick='Regresses(this)'>审核</button>"+
				"</div>"+	
		   	"<div class='layui-row layui-col-space20'>"+
		   		"<div  class='layui-col-md8 layui-col-md-offset2' style='text-align:center'>"+data[i].agname+"</div>"+
		   		"<div>"+
		   		"<div class='aginfopart'>"+
		   			"<div class='layui-col-md6 cginfo'>"+
		   			"<span class='first'>名称:</span><span class='second'>"+data[i].agname+"</span>"+
		   			"</div>"+
		   			"<div class='layui-col-md6 cginfo'>"+
		   			"<span class='first'>别称:</span><span class='second'>"+data[i].agothername+"</span>"+
		   			"</div>"+
		   			"<div class='layui-col-md12 cginfo'>"+
		   			"<span class='first'>简述:</span><span class='second'>"+data[i].agdesc+"</span>"+
		   			"</div>"+
		   		"</div>"+	
		   			"<div class='layui-col-md12 cgds'>"+
		   			"<div>成员</div>"+
		   			"</div>"+
		   			"<hr class='layui-bg-grey'>"+
		   			"<div class='layui-col-md12 bottompart' id='bottompart'>"+
		   			"</div>"+
		   	"</div>"+
		  "</div>"+
		"</div>";	
		}else if(data[i].status==5){
		code+="<div class='option layui-col-md5' id='"+data[i].agid+"'>"+
				"<div class='deletePhotog'>"+
				"<button class='layui-btn layui-btn-normal' onclick='Regresses22(this)'>回退</button>"+
				"</div>"+	
		   	"<div class='layui-row layui-col-space20'>"+
		   		"<div  class='layui-col-md8 layui-col-md-offset2' style='text-align:center'>"+data[i].agname+"</div>"+
		   		"<div>"+
		   		"<div class='aginfopart'>"+
		   			"<div class='layui-col-md6 cginfo'>"+
		   			"<span class='first'>名称:</span><span class='second'>"+data[i].agname+"</span>"+
		   			"</div>"+
		   			"<div class='layui-col-md6 cginfo'>"+
		   			"<span class='first'>别称:</span><span class='second'>"+data[i].agothername+"</span>"+
		   			"</div>"+
		   			"<div class='layui-col-md12 cginfo'>"+
		   			"<span class='first'>简述:</span><span class='second'>"+data[i].agdesc+"</span>"+
		   			"</div>"+
		   		"</div>"+	
		   			"<div class='layui-col-md12 cgds'>"+
		   			"<div>成员</div>"+
		   			"</div>"+
		   			"<hr class='layui-bg-grey'>"+
		   			"<div class='layui-col-md12 bottompart' id='bottompart'>"+
		   			"</div>"+
		   	"</div>"+
		  "</div>"+
		"</div>";			
		}
		}
		$("#middle").html(code);
	}
	function createImage(data0,data){
		var code2="";
			code2+="<div class='addicon'>"+	
							"<div>"+	
								"<img class='img'  src='selachievementimg.do?fileName="+data0.filename+"'>"+							
							"</div>"+
							"<div class='cgimagename'>"+data0.cgername+"</div>"+																										
			   	   "</div>";
		$("#"+data.agid).append(code2);
	}
	function Regresses(e){
			sondata=$(e).parent().parent().attr("id");
			layui.use(['form', 'layedit', 'laydate'], function(){
        			var form = layui.form
        			,layer = layui.layer
              		layer.open({
              			title: '内容审核'
	          			,type:2
	          			, content:'achievementadult.do'
	          			,area: ["650px", "330px;"],
          			}); 
        	});
	}
	
	function Regresses22(e){
			sondata=$(e).parent().parent().attr("id");
			layui.use(['form', 'layedit', 'laydate'], function(){
        			var form = layui.form
        			,layer = layui.layer
              		layer.open({
              			title: '已发布回退'
	          			,type:2
	          			, content:'regresses2.do'
	          			,area: ["650px", "280px;"],
          			}); 
        	});
	}	
	function selchange(){
	searchAchievementaginfo();
	}
	function flush(){
		$("#serchachievementName").val("");
		searchachievement();
	}
  </script>
</html>