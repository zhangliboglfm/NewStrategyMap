<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>摄影家列表</title>
    
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
      		<div class="layui-inline">
		      <label class="layui-form-label">摄影家名称</label>
		      <div class="layui-input-inline">
		        <input type="text" id="serchPhotogName" autocomplete="off" class="layui-input">
		      </div>
		    </div>
      		<div class="layui-inline">
		      <div class="layui-input-inline">
		        <button class="layui-btn" onclick="searchPhotog()" style="margin-top: 1px !important;">检索</button>
		      </div>
		    </div>
		    <hr class="layui-bg-green">
	    </div>
	    <div style="height: 10px;"></div>
    	<div id="photoglist" class="layui-row layui-col-space30"></div>
    	<div style="height: 10px;"></div>
    	<div style="text-align: center;"><div id="footpage" style="background: #FFF;"></div></div>
    </div>
  </body>
  <script type="text/javascript" src="layui/layui.all.js"></script>
  <script type="text/javascript">
  	var pgGroups = "<%=request.getAttribute("pgGroups")%>";
  	var form = layui.form,laydate=layui.laydate,layer = layui.layer,laypage = layui.laypage;
  	
  	$(function(){
		init();
	});
	
	function init(){
		$("html").niceScroll({});
		
		//常规用法
		laydate.render({
			elem: '#date'
		});
		searchPhotog();
	}
	function searchPhotog(){
		var currPage = 1;
		var pageSize=18;
		loadphotoglist(currPage,pageSize);
	}
	function loadphotoglist(currPage,pageSize){
		var importDate=$("#date").val();
		var pgName=$("#serchPhotogName").val();
		var url="getPhotogInfo.do";
		var parameter={"importdate":importDate,"pgName":pgName,"currPage":currPage,"pageSize":pageSize,"pgGroups":pgGroups};
		ajaxRequest(url, parameter, function(data){
			var counts = data.counts;
			var dataList = data.dataList;
			var html="";
			for(var i=0;i<dataList.length;i++){
				var item = dataList[i];
				var id=item.id;
				var name=item.name;
				var order=item.order;
				html+='<div class="layui-col-md3 layui-col-sm4" pgid="'+id+'" onclick="photogClick(this)">';
	    		html+='	<div class="cmdlist-container" onmouseover="shadowover(this)" onmouseout="shadowout(this)">';
	    		html+='		<a><img src="selphotoghead.do?pgid='+id+'"></img></a>';
	    		html+='		<a><div class="cmdlist-text">';
	    		html+='			<span class="layui-badge">'+order+'</span>';
	    		html+='			<span>'+name+'</span>';
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
			    ,limit: 18
			    //,limits: [12, 18, 24, 30, 36]
			    ,layout: ['count', 'prev', 'page', 'next', 'skip']
			    ,jump: function(obj, first){
			        if(!first){
			        	var currPage=obj.curr;
						loadphotoglist(obj.curr,obj.limit);
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
		var pgid = $(obj).attr("pgid");
		window.parent.addNewPhotogF(pgid);
		$(obj).remove();
	}
  </script>
</html>
