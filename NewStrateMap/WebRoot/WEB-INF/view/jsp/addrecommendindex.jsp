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

<title>添加相关影响指标</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 引入css -->
<link rel="stylesheet" href="css/interestpointrank/addrelindex.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/selectlist/css/selectlist.default.css" type="text/css"></link>
<!-- 引入js -->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="plugin/selectlist/jquery.nicescroll.js"></script>
<script type="text/javascript" src="plugin/selectlist/miapsoft.autohide.js"></script>
<script type="text/javascript" src="plugin/selectlist/miapsoft.selectlist.core.js"></script>
<script type="text/javascript" src="js/miapsoft.input.core.js"></script>
<style type="text/css">
@font-face {font-family: "iconfont";
  src: url('css/iconfont/iconfont.eot?t=1497250656883'); /* IE9*/
  src: url('css/iconfont/iconfont.eot?t=1497250656883#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('css/iconfont/iconfont.woff?t=1497250656883') format('woff'), /* chrome, firefox */
  url('css/iconfont/iconfont.ttf?t=1497250656883') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('css/iconfont/iconfont.svg?t=1497250656883#iconfont') format('svg'); /* iOS 4.1- */
}
.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
@font-face {font-family: "selIconfont";
  src: url('plugin/selectlist/css/selectlist.iconfont.eot?t=1488875403519'); /* IE9*/
  src: url('plugin/selectlist/css/selectlist.iconfont.eot?t=1488875403519#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('plugin/selectlist/css/selectlist.iconfont.woff?t=1488875403519') format('woff'), /* chrome, firefox */
  url('plugin/selectlist/css/selectlist.iconfont.ttf?t=1488875403519') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('plugin/selectlist/css/selectlist.iconfont.svg?t=1488875403519#selIconfont') format('svg'); /* iOS 4.1- */
}
.selIconfont {
  font-family:"selIconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
*{font-family: Microsoft YaHei;font-size: 14px;}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0px;}
</style>
</head>

<body>
	<div class="main">
		<div class="top">
			<div class="curindexinfo">当前待关联指标：<span id="spnMainIndexName" class="mainindexname">--</span></div>
			<div id="divQueryType" class="querytype"></div>
			<div class="search">
				<input id="iptIndexSearch" type="text" title="指标搜索" defaulttxt="请输入关键字"/>
			</div>
			<div class="searchbnt" onclick="queryIndex()"><i class="iconfont">&#xe617;</i>查询</div>
		</div>
		<div class="grid">
			<div id="divTabThead" class="tab-title">
			</div>
			<div id="divTabTbody" class="tab-main">
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var userid="<%=session.getAttribute("userId")%>";
	var mainindexid="<%=request.getAttribute("indexId")%>";
	var mainindexname="<%=request.getAttribute("indexName")%>";
	var datetype="<%=request.getAttribute("dateType")%>";
	var mainindexlvl="<%=request.getAttribute("indexLVL")%>";
	if(mainindexname=="null"){
		mainindexname="--";
	}
	var querytype = "type";
	var tabwidtharr=[0.1,0.1,0.3,0.38,0.12];
	var tabcolumn=["indexBigType","indexSmallType","indexName","indexDesc","htmlcode"];
	var tabtitledata=["指标类型","指标细分","指标名称","指标口径","操作"];
	inputOptimize($("#iptIndexSearch"));
	$(function(){
		adjust();
		$("#spnMainIndexName").html(mainindexname);
		$("#divQueryType").miapSelect({
			"data":[{"text":"按指标类型搜索","value":"type"},{"text":"按指标关键字搜索","value":"key"}],
			"eachLiClick":function(i,t,v){
				querytype=v;
			}
		});
		$(document).keypress(function(e){
			if(e.which == 13){
				if($("input:focus").attr("id")=="iptIndexSearch"){
					queryIndex();
				}
			}
		});
		queryIndex();
		mosaicTableTitle(tabtitledata);
	});
	/* 调整布局 */
	function adjust(){
		$(".grid").height($(".main").height()-$(".top").height());
		$("#divTabTbody").height($(".grid").height()-$("#divTabThead").height());
	}
	/* 查询指标 */
	function queryIndex(){
		$("#divTabTbody").html("");
		var key=($("#iptIndexSearch").val()==$("#iptIndexSearch").attr("defaulttxt") ?  "" : $("#iptIndexSearch").val());
		$.ajax({
			url:"searchaddrelindex.do",
			type:"post",
			dataType:"json",
			data:{"keystr":key,"querytype":querytype,"datetype":datetype,"indexid":mainindexid,"indexlvl":mainindexlvl,"userId":userid},
			success:function(data){
				mosaicSearchResultCode(data);
			}
		});
	}
	/* 加载表头 */
	function mosaicTableTitle(tabtitledata){
		var theadDom = $("<table class='tab'><thead><tr></tr></thead></table>");
		var tr = $("#divTabThead").append(theadDom).find(".tab").find("thead").find("tr");
		var tabwidth=$("#divTabThead").width();
		var boderwidth = tabcolumn.length-1;
		var htmlcode="";
		for(var i=0;i<tabtitledata.length;i++){
			var obj = tabtitledata[i];
			var ws=tabwidtharr[i];
			var text=tabtitledata[i];
			if(i == (tabtitledata.length-1)){
				htmlcode+="<td class='noboder' style='width:"+(ws*(tabwidth-boderwidth))+"px'>"+text+"</td>";
			}else{
				htmlcode+="<td style='width:"+(ws*(tabwidth-boderwidth))+"px'>"+text+"</td>";
			}
		}
		tr.html(htmlcode);
	}
	/* 拼接查询结果 */
	function mosaicSearchResultCode(data){
		var theadDom = $("<table class='tab'><tbody></tbody></table>");
		var tabwidth=$("#divTabTbody").width();
		var boderwidth = tabcolumn.length-1;
		if(data.length!=0){
			var tbody = $("#divTabTbody").append(theadDom).find(".tab").find("tbody");
			for(var i=0;i<data.length;i++){
				var htmlcode="";
				var tr=$("<tr></tr>");
				var obj=data[i];
				var tdbg="";
				if(i%2){
					tdbg="#fff";
				}else{
					tdbg="#EBF3FF";
				}
				for(var j=0;j<tabcolumn.length;j++){
					var ws=tabwidtharr[j];
					if("htmlcode"==tabcolumn[j]){
						htmlcode+="<td style='width: "+(ws*(tabwidth-boderwidth))+"px;background-color: "+tdbg+";'>"+
								getIconBntCode(obj.indexRelType,obj.indexId)+
								"</td>";
					}else{
						htmlcode+="<td style='width: "+(ws*(tabwidth-boderwidth))+"px;background-color: "+tdbg+";'>"+obj[tabcolumn[j]]+"</td>";
					}
				}
				tr.html(htmlcode);
				tbody.append(tr);
			}
			//加载滚动条
			$(".tab-main").niceScroll({
				cursorcolor : "#9AC2FF",
				cursorborderradius: "0px",
				cursorwidth: "3px",
				cursorborder: "0px solid #fff",
				autohidemode:false
			});
		}else{
			$("#divTabTbody").html("未找到相关指标！");
		}
	}
	/* 关联指标 */
	function relationIndexWithMainIndex(theindexid){
		$.ajax({
			url:"relthisindexwithmainindex.do",
			type:"post",
			dataType:"json",
			data:{"thisindex":theindexid,"datetype":datetype,"indexid":mainindexid,"userId":userid,"updatetype":"addrel"},
			success:function(data){
				if(data.result=="success"){
					alert("操作成功！");
					queryIndex();
				}else if(data.result=="have"){
					alert("指标关联关系已存在！");
				}else if(data.result=="nohave"){
					alert("指标关联关系不存在！");
				}else if(data.result=="cannot"){
					alert("无法修改系统关联指标！");
				}else if(data.result=="error"){
					alert("出现异常，操作失败！");
				}
			}
		});
	}
	/* 取消关联指标 */
	function unrelationIndexWithMainIndex(theindexid){
			$.ajax({
			url:"relthisindexwithmainindex.do",
			type:"post",
			dataType:"json",
			data:{"thisindex":theindexid,"datetype":datetype,"indexid":mainindexid,"userId":userid,"updatetype":"updaterel"},
			success:function(data){
				if(data.result=="success"){
					alert("操作成功！");
					queryIndex();
				}else if(data.result=="have"){
					alert("指标关联关系已存在！");
				}else if(data.result=="nohave"){
					alert("指标关联关系不存在！");
				}else if(data.result=="cannot"){
					alert("无法修改系统关联指标！");
				}else if(data.result=="error"){
					alert("出现异常，操作失败！");
				}
			}
		});
	}
	/* 获取按钮代码 */
	function getIconBntCode(reltype,indexid){
		var htmlcode = "";
		if(reltype=="1"){
			htmlcode="<i style='color:gray'>不可操作</i>";
		}else if(reltype=="2"){
			htmlcode="<span title='取消关联' onclick='unrelationIndexWithMainIndex(\""+indexid+"\")'><i class='iconfont relation'>&#xe647;</i>取消关联</span>";
		}else if(reltype=="3"){
			htmlcode="<span title='关联' onclick='relationIndexWithMainIndex(\""+indexid+"\")'><i class='iconfont relation'>&#xe648;</i>关联</span>";
		}
		return htmlcode
	}
</script>
</html>
