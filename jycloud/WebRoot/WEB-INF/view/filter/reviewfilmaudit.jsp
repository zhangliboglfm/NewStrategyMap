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

<title>样片详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/filmaudit.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>


<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/pgaudit.js"></script>
<script type="text/javascript" src="js/picmanage.js"></script>
<style type="text/css">
*{font-size: 14px !important;font-family: 微软雅黑  !important;color: #1C1C1C}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0;background-color: #fff;}
input:not([type="checkbox"]){
			height: 25px !important;
			/* margin-top: 2px !important; */
			padding-left: 10px;
			
			border-color: #e6e6e6 !important;
			border-width: 1px;
     		border-style: solid;
    
    		border-radius: 2px;
    
    		border-color: #e6e6e6;
			
		}
textarea {
	width: 160px;height: 65px;resize:none;margin-top: 5px;border-color: #e6e6e6;padding-left: 10px;
}
</style>
</head>

<body>
	<div class="maindiv">
		
		<div class="top">
			<div class="info">
				<div class="imgdiv" id="imgdiv">
					<img id="imgPgHeadImage" alt="摄影家头像" src=""></img>
				</div>
				<div class="infodiv" id="infodiv">
					<ul>
						<li><div class="ezchinfodiv"><div class="lb">姓名:</div><input id="iptPgName" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li><div class="ezchinfodiv"><div class="lb">国籍:</div><input id="iptPgNation" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li><div class="ezchinfodiv"><div class="lb">年代:</div><input id="iptPgYears" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li><div class="ezchinfodiv"><div class="lb">出生时间:</div><input id="iptPgBirthYears" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li><div class="ezchinfodiv"><div class="lb">去世时间:</div><input id="iptPgDeadYears" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
					</ul>
					<ul>
						<li><div class="ezchinfodiv"><div class="lb">性别:</div><input id="iptPgSex" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li><div class="ezchinfodiv"><div class="lb">修改时间:</div><input id="iptPgDealTime" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						<li>
							<div class="ezchinfodiv">
								<div class="lb">审核状态:</div>
								<input id="iptPgAuditState" class="inputboder" style="width: 70px;" type="text" name="pginfo" value="" readonly="readonly" onclick="changePgAuditStatus()"/>
								<div class="tips">（点击修改）</div>
							</div>
						</li>
						<li style="height: auto;width: auto">
							<div class="ezchinfodiv">
								<div class="lb">审核结果:</div>
								<textarea id="tareaAuditResult" rows="" cols="" style="width: 200px;height: 75px;resize:none" readonly="readonly"></textarea>
							</div>
						</li>
					</ul>
				</div>
			</div>
			
			<div></div>
		</div>
		<%--  <div class="bottom">
			<div class="layui-tab">
				<ul class="layui-tab-title">
					<li class="layui-this">标准照</li>
					<li>生平</li>
					<li>成就</li>
					<li>风格</li>
					<li>作品</li>
					<li>相关文章</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<iframe id="ifrStandardImg" src="pgstandardimgaudit.do?pId=<%=request.getAttribute("pId")%>"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrLifeTime" src="pglifetime.do?pId=<%=request.getAttribute("pId")%>&jsptype=audit"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrAchievement" src="pgachievement.do?pId=<%=request.getAttribute("pId")%>&jsptype=audit"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrStyle" src="pgstyle.do?pId=<%=request.getAttribute("pId")%>&jsptype=audit"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrWorks" src="pgworksaudit.do?pId=<%=request.getAttribute("pId")%>"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrRelevantArticle" src="relateArticleAudit.do?photogId=<%=request.getAttribute("pId")%>"></iframe>
					</div>
				</div>
			</div>
		</div> --%>
		
		<!-- <div id="state">
    		<div class="state2">
    			<div class="state3" style="height:100%;width:4%">样片</div>
    			<div class="state3" style="text-align: right;height:100%;width:95%"><button  class="layui-btn layui-btn-xs layui-btn-normal close">关闭</button></div>
    		</div>
    		<div id="statepic" style="width:100%;height:95%;text-align: center">
    			<div class="state3" style="height:100%;width:60%;margin-top: 20px;"><img src="SpiderImg/TUCHONG/PROVIA 100F/31093966.jpg"></div>
    			<div class="state3" style="height:100%;width:39%;border-left: 1px solid;">
    				<div style="height:40%;width:100%;">
	    				<table>
	    					<tr><td>名称:</td><td><input type="text" value="集市"/></td></tr>
	    					<tr><td>大小:</td><td>1234</td></tr>
	    					<tr><td>ISO:</td><td>MM</td></tr>
	    					<tr><td>光圈:</td><td>暂无</td></tr>
	    					<tr><td>快门:</td><td>暂无</td></tr>
	    					<tr><td>相机型号:</td><td><input type="text" value=""/></td></tr>
	    					<tr><td>胶片型号:</td><td><input type="text" value=""/></td></tr>
	    					<tr><td>镜头类型:</td><td><input type="text" value=""/></td></tr>
	    					<tr><td>回退原因:</td><td>无</td></tr>
	    				</table>
    				</div>
    				<div id="labelhide">
		    			<label class="photolabel">标签:</label><i class="layui-icon" style="color:#009688;cursor: pointer;font-size: 18px;" onclick="addTag(id)">&#xe61f;</i>
		    			<div class="phototags" id="phototags">
		    				<div class="phototag"><span>风景</span><i class="layui-icon close-icon" onclick="deleteTag(this)">&#x1007;</i></div>
		    			</div>
		    		</div>
    				<div style="text-align: center;height:40%;width:100%;">
    					<div class="state4"><button class="layui-btn layui-btn-sm layui-btn-normal">保存</button></div>
    					<div class="state4"><button class="layui-btn layui-btn-sm layui-btn-normal">保存并筛选</button></div>
    				</div>
    			</div>
    		</div>
    	</div> -->
	</div>
</body>
<script type="text/javascript">
	var layer;
	var form;
	var MyFile;
	layui.use(["layer","form"], function(){
		layer = layui.layer;
		form = layui.form;
	});
	document.oncontextmenu = function(){
           event.returnValue = false;
    };
	var updatestatus = false;
	
	var id = "<%=request.getAttribute("id")%>";
	var data=getOnePic(id);
	
	var imghtml="";
	imghtml="<img style='height:100%;width:100%;' src='SpiderImg/"+data[0].SAMP_PIC_SRC+"'>";
	$("#imgdiv").html("");
	$("#imgdiv").html(imghtml);
	var w="";	
	w+="<ul>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>名称:</div><div class='lb2'>"+data[0].SAMP_PIC_NAME+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>大小:</div><div class='lb2'>长:"+data[0].SAMP_PIC_LENGTH+"宽:"+data[0].SAMP_PIC_WIDTH+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>ISO:</div><div class='lb2'>"+data[0].ISO+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>光圈:</div><div class='lb2'>"+data[0].APER+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>快门:</div><div class='lb2'>"+data[0].SHUT+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>曝光模式:</div><div class='lb2'>"+data[0].EXPS_MOD+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>测光模式:</div><div class='lb2'>"+data[0].METRY_MOD+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>曝光补偿:</div><div class='lb2'>"+data[0].EXPS_EV+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>焦距:</div><div class='lb2'>"+data[0].FOC+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>视角:</div><div class='lb2'>"+data[0].VIEW+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>回退原因:</div><div class='lb2'><textarea rows='' cols='' readonly='readonly'>"+data[0].REASON_NAME+"</textarea></div></div></li>";
		w+="</ul>";
		w+="<ul>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>白平衡:</div><div class='lb2'>"+data[0].WHITE_BLA+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>色彩空间:</div><div class='lb2'>"+data[0].COLOR_SPACE+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>发布时间:</div><div class='lb2'>"+data[0].SAMP_PIC_SOURCE_PUB_TIME+"</div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>拍摄日期:</div><div class='lb2'>"+data[0].SHOOT_DATE+"</div></div></li>";
		if(data[0].IS_BLACK=="1"){
			w+="<li><div class='ezchinfodiv'><div class='lb'>是否黑白:</div><div class='lb2'><div style='height:100%;width:100%;display:inline;'><form id='isblack' class='layui-form' action='' lay-filter='example' style='display:inline;'><input type='checkbox' name='isblack' lay-skin='switch' lay-text='彩色|黑白'></form></div></div></div></li>";
		}else{
			w+="<li><div class='ezchinfodiv'><div class='lb'>是否黑白:</div><div class='lb2'><div style='height:100%;width:100%;display:inline;'><form id='isblack' class='layui-form' action='' lay-filter='example' style='display:inline;'><input type='checkbox' name='isblack' lay-skin='switch' lay-text='彩色|黑白' checked></form></div></div></div></li>";
		}
		w+="<li><div class='ezchinfodiv'><div class='lb'>相机型号:</div><div class='lb2'><input id='inputcamera2' type='text' value='"+data[0].FILM_CAMERA_ID+"'/></div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>胶片型号:</div><div class='lb2'><input id='inputfilm2' type='text' value='"+data[0].FILM_PROD_ID+"'/></div></div></li>";
		w+="<li><div class='ezchinfodiv'><div class='lb'>镜头类型:</div><div class='lb2'><input id='inputlens2' type='text' value='"+data[0].LENS_ID+"'/></div></div></li>";
		w+="<li><div class='ezchinfodiv'>"+
				"<div class='lb'>标签:</div>"+
				"<div class='lb2'>"+
					"<div class='phototags' style='float:left;width:300px;margin-left: -10px;' id='phototags'></div>"+
					"<div style='float:left;margin-left: 10px;width:32px;height:32px'>"+
						"<i id='"+id+"' class='iconfont addlbbnt' style='color:#009688;cursor: pointer;font-size: 18px !important;width:22px;height:22px' onclick='addTag(id)'>"+
							"&#xe6b7;"+
						"</i>"+
					"</div>"+
				"</div>"+
				"</div></li>";
		w+="</ul>";
		
	$("#infodiv").html("");
	$("#infodiv").html(w);
		
		getPicLabel(id);
	form.render();  
	
	$(function(){
		adjust();
		$(window).resize(adjust);
		$("body").niceScroll({
			cursorwidth : "10px",
			cursorcolor: "#B2B2B2",
			cursorborder: "0px",
			autohidemode: false
		});
		
	});
	//页面布局调整
	function adjust(){
		var height = $(window).height();
		$(".layui-tab-content").height($(".layui-tab").height()-$(".layui-tab-title").height());
		$(".infodiv").width($(".info").width()-$(".imgdiv").width()-150);
		$(".other").width($(".info").width()-$(".imgdiv").width()-150);
		$(".lbdiv").width($(".other").width()-$(".other .lb").width());
	}
</script>
</html>
