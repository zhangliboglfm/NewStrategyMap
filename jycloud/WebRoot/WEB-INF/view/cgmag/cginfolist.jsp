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

<title>书法家管理列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/common.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/template.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/admin.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" href="css/cginfolist.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>

<style type="text/css">
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}	
.maindiv{
	height: 100%;margin: 0;padding: 0;
}
.layui-bg-green{background-color: #C3C3C3 !important}
/* 阴影 */
.shadow{
	-webkit-box-shadow:0px 0px 3px #a0a0a0;
   	-moz-box-shadow:0px 0px 3px #a0a0a0;
   	box-shadow:0px 0px 3px #a0a0a0;
	/* 投影数字顺序依次是：右边 下边 投影模糊大小 */
	/* 内阴影数字顺序依次是：左边 上边 阴影模糊大小 */
}
.cmdlist-container img{
	height: 275px;
	width: 197px;
}
.customclass{
	width: 230px !important;
	margin: 0 5px;
}
.sortdiv2{
	height: 32px !important;
	width: 100px !important;
}
.layui-form-select{
	height: 32px !important;
}
.layui-select-title{
	height: 32px !important;
}
.layui-select-title input{
	height: 32px !important;
}
/*按时间排序时，朝代部分的添加*/
.dynastyTitle{
	width: 100%;
	height: 40px;
	float: left;
	color: #000;
	text-indent: 30px;
	font-size: 18px;
	font-family: 微软雅黑;
	font-weight: bold;
	line-height: 40px;
	padding: 0;
}
.layui-bg-green{
	background-color: #009688 !important;
	padding: 0;
	height: 2px;
}
#calliglist{
	width: 99% !important;
}
</style>
</head>

<body class="layui-layout-body">
	<div class="maindiv" id="maindiv">
		<div class="searchdiv">	
			<div class="layui-inline">
				<label class="layui-form-label">导入日期</label>
				<div class="layui-input-inline">
					<input type="text" name="date" id="date" lay-verify="date" placeholder="" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label">书法家名称</label>
				<div class="layui-input-inline">
					<input type="text" id="serchCalligName" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline">
					<button class="layui-btn" onclick="searchCallig()" style="margin-top: 1px !important;">检索</button>
				</div>
			</div>
			<div class="layui-inline sortdiv sortdiv2">
		      	<div class="layui-form sortdiv2">
		      		<select name="modules" lay-verify="required" lay-search="">
		      		  <option value="99">全部</option>
			          <option value="1">未审核</option>
			          <option value="5">已审核</option>
			        </select>
		      	</div>
		     </div>
			<div class="layui-inline sortdiv">
				<div id="divBirthDaySortBnt" class="sortbnt" type="birthday" sort="0" onclick="sortBntBirthdayClick(this)"><i class="iconfont">&#xe63c;</i>出生日期</div>
				<div id="divChangeTimeSortBnt" class="sortbnt" type="changetime" sort="0" onclick="sortBntChangeTimeClick(this)"><i class="iconfont">&#xe63c;</i>变更时间</div>
				<div id="divWorksNumSortBnt" class="sortbnt" type="worksnum" sort="0" onclick="sortBntWorksNumClick(this)"><i class="iconfont">&#xe63c;</i>作品数量</div>
				<!-- <div id="divAuditbnt" class="auditbnt" type="audit" status="5" sort="0" onclick="auditBntClick(this)">已审核</div> -->
			</div>
		</div>
		<div style="height: 10px;"></div>
		<div id="calliglist" class="layui-row layui-col-space30"></div>
		<div style="text-align: right;"><div id="footpage" style="background: #FFF;"></div></div>
	</div>
</body>
<script type="text/javascript">
	var form = layui.form,laydate=layui.laydate,layer = layui.layer,laypage = layui.laypage;
	var cur_sorttype = "";
	var cur_sortcolumn = "";
	var cur_auditstatus = "";
	$(function(){
		//常规用法
		laydate.render({
			elem: "#date"
		});
		form.render('select'); //刷新select选择框渲染
		form.on('select', function(data){
		  cur_auditstatus=data.value;
		  searchCallig();
		});
		searchCallig();
		$("#maindiv").niceScroll({
			cursorwidth : "6px",
			cursorcolor: "#009688",
			cursorborder: "0px",
			autohidemode : true
		});
	});
	function searchCallig(){
		var currPage = 1;
		var pageSize=21;
		if (cur_sortcolumn=="birthday") {
			loadcalliglist2(currPage,pageSize,cur_sortcolumn,cur_sorttype,cur_auditstatus);
		} else {
			loadcalliglist(currPage,pageSize,cur_sortcolumn,cur_sorttype,cur_auditstatus);
		}
	}
	function loadcalliglist(currPage,pageSize,sortcolumn,sorttype,auditstatus){
		var importDate=$("#date").val();
		var pgName=$("#serchCalligName").val();
		var url="selcalliglist.do";
		var parameter={"importDate":importDate,"cgName":pgName,"currPage":currPage,"pageSize":pageSize,"sortColumn":sortcolumn,"sortType":sorttype,"auditStatus":auditstatus};
		$.ajax({
			url:url,
			type:"post",
			dataType:"json",
			data:parameter,
			success:function(data){
				var counts = data.counts;
				var dataList = data.dataList;
				var html="";
				if(dataList.length!=0){
					for(var i=0;i<dataList.length;i++){
						var item = dataList[i];
						var id=item.id;
						var name=item.name;
						var wnum=item.wnum;
						var order=item.order;
						html+='<div class="layui-col-md3 layui-col-sm4 customclass">';
			    		html+='	<div class="cmdlist-container" cgid="'+id+'" cgname="'+name+'" onmouseover="shadowover(this)" onmouseout="shadowout(this)" onclick="calligClick(this)">';
			    		html+='		<a><img src="selcallighead.do?cgid='+id+'"></img></a>';
			    		html+='		<a><div class="cmdlist-text">';
			    		html+='			<span class="layui-badge">'+order+'</span>';
			    		html+='			<span>'+name+'</span>';
			    		html+='			<span class="cgworksnum layui-bg-orange">作品：'+wnum+'</span>';
			    		html+='			</div>';
			    		html+='		</a>';
			    		html+='	</div>';
			    		html+='</div>';
					}
				}
				$("#calliglist").html("");
				$("#calliglist").html(html);
				laypage.render({
				    elem: 'footpage'
				    ,count: counts
				    ,curr: currPage || 1 //当前页
				    ,limit: 21
				    ,layout: ['count', 'prev', 'page', 'next', 'skip']
				    ,jump: function(obj, first){
				        if(!first){
				        	var currPage=obj.curr;
							loadcalliglist(obj.curr,obj.limit,sortcolumn,sorttype,auditstatus);
						}
				    }
				});
			}
		});
	}
	function shadowover(obj){
		$(obj).addClass("shadow");
	}
	function shadowout(obj){
		$(obj).removeClass("shadow");
	}
	function calligClick(e){
		var name = $(e).attr("cgname");
		var id = $(e).attr("cgid");
		var ev = window.event || arguments.callee.caller.arguments[0] || event;  // 对应分别为谷歌、火狐、IE
		//阻止冒泡事件
		ev.stopPropagation();
		var data = {"title":name,"lay-id":id,"src":"calligrapherDetailed.do?cgId="+id+"&auditStatus="+cur_auditstatus};
		window.parent.addTable(data);
	}
	//出生时间排序按钮
	function sortBntBirthdayClick(e){
		resetSortBnt($("#divChangeTimeSortBnt"));
		resetSortBnt($("#divWorksNumSortBnt"));
		cur_sortcolumn = "";
		cur_sorttype = "";
		if($(e).attr("sort")=="0"){
			cur_sortcolumn = $(e).attr("type");
			cur_sorttype = "Ascending";
		}else if($(e).attr("sort")=="1"){
			cur_sortcolumn = $(e).attr("type");
			cur_sorttype = "Descending";
		}else if($(e).attr("sort")=="2"){
			cur_sortcolumn = "";
			cur_sorttype = "";
		}
		changeSortIcon(e);
		searchCallig();
	}
	//变更时间排序按钮
	function sortBntChangeTimeClick(e){
		resetSortBnt($("#divBirthDaySortBnt"));
		resetSortBnt($("#divWorksNumSortBnt"));
		cur_sortcolumn = "";
		cur_sorttype = "";
		if($(e).attr("sort")=="0"){
			cur_sortcolumn = $(e).attr("type");
			cur_sorttype = "Ascending";
		}else if($(e).attr("sort")=="1"){
			cur_sortcolumn = $(e).attr("type");
			cur_sorttype = "Descending";
		}else if($(e).attr("sort")=="2"){
			cur_sortcolumn = "";
			cur_sorttype = "";
		}
		changeSortIcon(e);
		searchCallig();
	}
	//作品数量排序按钮
	function sortBntWorksNumClick(e){
		resetSortBnt($("#divBirthDaySortBnt"));
		resetSortBnt($("#divChangeTimeSortBnt"));
		cur_sortcolumn = "";
		cur_sorttype = "";
		if($(e).attr("sort")=="0"){
			cur_sortcolumn = $(e).attr("type");
			cur_sorttype = "Ascending";
		}else if($(e).attr("sort")=="1"){
			cur_sortcolumn = $(e).attr("type");
			cur_sorttype = "Descending";
		}else if($(e).attr("sort")=="2"){
			cur_sortcolumn = "";
			cur_sorttype = "";
		}
		changeSortIcon(e);
		searchCallig();
	}
	function changeSortIcon(e){
		if($(e).attr("sort")=="0"){
			$(e).attr("sort","1");
			$(e).addClass("curbg");
		}else if($(e).attr("sort")=="1"){
			$(e).attr("sort","2");
			$(e).find("i").html("&#xe63a;");
		}else if($(e).attr("sort")=="2"){
			$(e).attr("sort","0");
			$(e).find("i").html("&#xe63c;");
			$(e).removeClass("curbg");
		}
	}
	function resetSortBnt(e){
		$(e).find("i").html("&#xe63c;");
		$(e).removeClass("curbg");
		$(e).attr("sort",0);
	}
	
	function loadcalliglist2(currPage,pageSize,sortcolumn,sorttype,auditstatus){
		var importDate=$("#date").val();
		var pgName=$("#serchCalligName").val();
		var url="selcalliglist.do";
		var parameter={"importDate":importDate,"cgName":pgName,"currPage":currPage,"pageSize":pageSize,"sortColumn":sortcolumn,"sortType":sorttype,"auditStatus":auditstatus};
		$.ajax({
			url:url,
			type:"post",
			dataType:"json",
			data:parameter,
			success:function(data){
				var counts = data.counts;
				var dataList = data.dataList;
				var html="";
				if(dataList.length!=0){
					var dynastyF=dataList[0].dynasty;
					html+=appendDynasty(dynastyF);
					for(var i=0;i<dataList.length;i++){
						var item = dataList[i];
						var id=item.id;
						var name=item.name;
						var wnum=item.wnum;
						var order=item.order;
						var dynasty=item.dynasty;
						if (dynasty!=dynastyF) {
							html+="</div>";
							html+=appendDynasty(dynasty);
							dynastyF=dynasty;
						}
						html+='<div class="layui-col-md3 layui-col-sm4 customclass">';
			    		html+='	<div class="cmdlist-container" cgid="'+id+'" cgname="'+name+'" onmouseover="shadowover(this)" onmouseout="shadowout(this)" onclick="calligClick(this)">';
			    		html+='		<a><img src="selcallighead.do?cgid='+id+'"></img></a>';
			    		html+='		<a><div class="cmdlist-text">';
			    		html+='			<span class="layui-badge">'+order+'</span>';
			    		html+='			<span>'+name+'</span>';
			    		html+='			<span class="cgworksnum layui-bg-orange">作品：'+wnum+'</span>';
			    		html+='			</div>';
			    		html+='		</a>';
			    		html+='	</div>';
			    		html+='</div>';
					}
					html+="</div>";
				}
				$("#calliglist").html("");
				$("#calliglist").html(html);
				$("#maindiv").niceScroll({
					cursorwidth : "6px",
					cursorcolor: "#009688",
					cursorborder: "0px",
					autohidemode : true
				});
				laypage.render({
				    elem: 'footpage'
				    ,count: counts
				    ,curr: currPage || 1 //当前页
				    ,limit: 21
				    ,layout: ['count', 'prev', 'page', 'next', 'skip']
				    ,jump: function(obj, first){
				        if(!first){
				        	var currPage=obj.curr;
							loadcalliglist2(obj.curr,obj.limit,sortcolumn,sorttype,auditstatus);
						}
				    }
				});
			}
		});
	}
	function appendDynasty(dynastyName) {
		var html = "<div class='dynastyTitle'>"+dynastyName+"</div>"+
					"<hr class='layui-bg-green'>"+
					"<div class='layui-row layui-col-space30'>";
		return html;
	}
</script>
</html>
