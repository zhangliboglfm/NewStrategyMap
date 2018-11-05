<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>作品列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="layui/css/layui_ztf.css" type="text/css" media="all"></link>
	<link rel="stylesheet" href="layui/css/template.css" type="text/css"></link>
	<link rel="stylesheet" href="layui/css/admin.css" type="text/css"></link>
	<style type="text/css">
		.cmdlist-container{
			width: 160px;
			max-height: 360px;
			border: 1px #eee solid !important;
		}
		.cmdlist-container img{
			width: 160px;
			height: 160px;
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
		#photoglist .layui-col-md2{
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
		.layui-col-sm46{
			width: 33.33333%;
			float: left;
			min-width: 200px !important;
			max-width: 200px !important;
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
    	<div  class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
    		<ul id="layuiUl" class="layui-tab-title"></ul>
			<div class="layui-tab-content">
				<div class="searchdiv">	
		      		<div class="layui-inline">
				      <label class="layui-form-label">作品序号</label>
				      <div class="layui-input-inline">
				        <input type="text" id="serchPhotogName" autocomplete="off" class="layui-input">
				      </div>
				    </div>
		      		<div class="layui-inline">
				      <div class="layui-input-inline">
				        <button class="layui-btn" onclick="searchWorks()" style="margin-top: 1px !important;">检索</button>
				      </div>
				    </div>
				    <hr class="layui-bg-green">
			    </div>
			    <div style="height: 10px;"></div>
		    	<div id="photoglist" class="layui-row layui-col-space30"></div>
		    	<div style="height: 10px;"></div>
		    	<div style="text-align: center;"><div id="footpage" style="background: #FFF;"></div></div>
			</div>
    	</div>
    	
    </div>
  </body>
  <script type="text/javascript" src="layui/layui.all.js"></script>
  <script type="text/javascript">
  	//作品ID
  	var wkGroups = "<%=request.getAttribute("wkGroups")%>";
  	//摄影家ID
  	var pgGroups = "<%=request.getAttribute("pgGroups")%>";
  	var form = layui.form,laydate=layui.laydate,layer = layui.layer,laypage = layui.laypage;
  	
  	var pgId="";
  	$(function(){
		init();
	});
	
	function init(){
		$("html").niceScroll({});
		//常规用法
		laydate.render({
			elem: '#date'
		});
		selectRelatePg();
	}
	//查包含的摄影家
	function selectRelatePg() {
		$.ajax({
			 url:"selectRelateCg.do",
			 type:"post",
			 dataType:"json",
			 data:{pgGroups : pgGroups},
			 success:function(data){
			 	var photogCode = "";
			 	for(var i = 0;i<data.CGName.length;i++){
			 		pgId=data.CGId[0];
			 		if (i == 0) {
						photogCode+="<li data-id='"+data.CGId[i]+"' class='pgCate layui-this'>"+data.CGName[i]+"</li>";
					} else {
						photogCode+="<li data-id='"+data.CGId[i]+"' class='pgCate'>"+data.CGName[i]+"</li>";
					}
			 	}
			 	$("#layuiUl").html("");
			 	$("#layuiUl").html(photogCode);
			 	searchWorks();
			 	$(".pgCate").click(function () {
			 		pgId=$(this).attr("data-id");
			 		searchWorks();
				});
			 }
		 });
	}
	//查作品
	function searchWorks(){
		var currPage = 1;
		var pageSize=15;
		loadworkslist(currPage,pageSize);
	}
	function loadworkslist(currPage,pageSize){
		var pgOrder=$("#serchPhotogName").val();
		var url="selcgworksList.do";
		var parameter={"pgOrder":pgOrder,"currPage":currPage,"pageSize":pageSize,"wkGroups":wkGroups,"pgId":pgId};
		ajaxRequest(url, parameter, function(data){
			var counts = data.counts;
			var dataList = data.dataList;
			var html="";
			for(var i=0;i<dataList.length;i++){
				var item = dataList[i];
				var id=item.id;
				var name=item.name;
				var order=item.order;
				var filename=item.filename;
				html+='<div class="layui-col-md2 layui-col-sm4 layui-col-sm46" wkid="'+id+'" onclick="photogClick(this)">';
	    		html+='	<div class="cmdlist-container" onmouseover="shadowover(this)" onmouseout="shadowout(this)">';
	    		html+='		<a><img src="getCGArtImage.do?imgUrl='+filename+'&imgFlag=3"></img></a>';
	    		html+='		<a><div class="cmdlist-text">';
	    		html+='			<span>'+order+"-"+name+'</span>';
	    		html+='			</div>';
	    		html+='		</a>';
	    		html+='	</div>';
	    		html+='</div>';
			}
			$("#photoglist").html(html);
			laypage.render({
			    elem: 'footpage'
			    ,count: counts
			    ,curr: currPage || 1 //当前页
			    ,limit: 15
			    //,limits: [12, 18, 24, 30, 36]
			    ,layout: ['count', 'prev', 'page', 'next']
			    ,jump: function(obj, first){
			        if(!first){
			        	var currPage=obj.curr;
						loadworkslist(obj.curr,obj.limit);
					}
			    }
			});
		});
	}
	
	function shadowover(obj){
		$(obj).addClass("shadow");
	}
	function shadowout(obj){
		$(obj).removeClass("shadow");
	}
	
	function photogClick(obj){
		var ev = window.event || arguments.callee.caller.arguments[0] || event;  // 对应分别为谷歌、火狐、IE
		//阻止冒泡事件
		ev.stopPropagation();
		var wkid = $(obj).attr("wkid");
		window.parent.addNewWorksF(wkid);
		$(obj).remove();
		wkGroups=wkGroups.substring(0, wkGroups.length-1)+",'"+wkid+"')";
	}
  </script>
</html>
