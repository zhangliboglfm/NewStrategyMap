<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<base href="<%=basePath%>">
<head>
<title>书法家详情</title>
   
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="layui/css/layui_ztf.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/cgjs/calligDetail.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>

<style type="text/css">
*{font-size: 12px;font-family: 微软雅黑;color: #1C1C1C}
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0;background-color: #fff;overflow: hidden;}

.layui-badge-rim, .layui-colla-content, .layui-colla-item, .layui-collapse, .layui-elem-field, .layui-form-pane .layui-form-item[pane], .layui-form-pane .layui-form-label
, .layui-input, .layui-layedit, .layui-layedit-tool, .layui-quote-nm, .layui-select, .layui-tab-bar, .layui-tab-card, .layui-tab-title, .layui-tab-title .layui-this:after
, .layui-textarea{
	border-color: #B2B2B2 !important;
}
.layui-tab-title .layui-this:after{
	border-bottom-color: #f2f2f2 !important;
}
.maindiv{
	width: 96%;
	margin-left: 2%;
	padding-top: 20px;
	/* background-color: #f2f2f2; */
}
.handlediv{
	float: left;
	width: 100%;
	height: 50px; 
	background-color: rgba(0,0,0,.3); 
	z-index: 999;
	display: none;
}
.handlediv button{margin-top: 6px;}
.line{
	float: left;
	width: 100%;
	height: 39px;
	border-bottom: 1px solid #B2B2B2;
	line-height: 39px;
	font-size: 16px;
	font-weight: bold;
	font-style: italic;
	text-indent: 2ex;
	cursor: default;
}
.line i{float: right;margin-right: 10px;color: #818181;cursor: pointer;font-size: 24px;}
.top{
	float: left;
	width: 100%;
	margin-top: 15px;
	border-bottom: 1px solid #c3c3c3;
}
.bottom{
	float: left;
	width: 100%;
	margin-top: 15px;
	height: 810px;
}
.layui-tab{
	height: 800px !important;
}
.layui-tab-content .layui-tab-item{height: 800px !important;}
.layui-tab-content .layui-tab-item iframe{height: 100%;width: 100%;border: 0;}
.top .info{
	float: left;
	width: 100%;
	height: 230px;
}
.top .other{
	float: left;
	width: 100%;
	margin-top: 10px;
}
#works .lb {
    float: left;
    width: 100px;
    height: 32px;
    line-height: 32px;
    text-align: center;
    font-weight: bold;
    font-size: 14px;
}
.top .info .imgdiv{
	float: left;
	width: 150px;
	height: 200px;
	padding: 5px;
	border: 2px solid #B2B2B2;
	margin-top: 4px;
	position: relative;
}
.top .info .imgdiv img{width: 100%;height: 100%;}
.top .info .imgdiv .imghandlediv{
	position: absolute;
	display: none;
	width: 150px;
	bottom: 5px;
	height: 30px;
	background-color: rgba(0,0,0,.7);
	cursor: pointer;
	color: #fff;
	line-height: 30px;
	text-align: center;
	font-size: 14px;
}
.top .info .imgdiv #iptHeadImg{display: none;}
.top .info .infodiv{
	float: left;
	margin-left: 15px;
	height: 218px;
}
.lb{cursor: default;}
.ul1{float: left;margin: 0;padding: 0;width: 200px;margin-left: 10px}
.ul2{float: left;margin: 0;padding: 0;width: 320px;margin-left: 10px}
.ul3{float: left;margin: 0;padding: 0;width: 420px;margin-left: 10px}
.top .info .infodiv ul li{list-style: none;}
.top .info .infodiv ul li .ezchinfodiv1{width: 200px;height: 30px;margin-left: 10px;}
.top .info .infodiv ul li .ezchinfodiv2{width: 320px;height: 30px;margin-left: 10px;}
.top .info .infodiv ul li .ezchinfodiv3{width: 420px;height: 30px;margin-left: 10px;}
.top .info .infodiv ul li .ezchinfodiv1 div.lb{float: left;line-height: 30px;font-size: 14px;width: 60px;text-align: right;font-weight: bold;}
.top .info .infodiv ul li .ezchinfodiv1 input{float: left;border: 0;width: 110px;height: 28px;margin-top: 1px;background-color: transparent;cursor: default;text-indent: 2ex}
.top .info .infodiv ul li .ezchinfodiv2 div.lb{float: left;line-height: 30px;font-size: 14px;width: 60px;text-align: right;font-weight: bold;}
.top .info .infodiv ul li .ezchinfodiv2 input{float: left;border: 0;width: 260px;height: 28px;margin-top: 1px;background-color: transparent;cursor: default;text-indent: 2ex}
.top .info .infodiv ul li .ezchinfodiv3 div.lb{float: left;line-height: 30px;font-size: 14px;width: 80px;text-align: right;font-weight: bold;}
.top .info .infodiv ul li .ezchinfodiv3 div.lb2{line-height: 15px;}
.top .info .infodiv ul li .ezchinfodiv3 input{float: left;border: 0;width: 220px;height: 28px;margin-top: 1px;background-color: transparent;cursor: default;text-indent: 2ex}

.top .other .lb{float: left;width: 194px;height: 40px;line-height: 40px;text-align: center;font-weight: bold;font-size: 14px;}
.top .other .lbdiv{float: left;}
.top .other .lbdiv .pglable{float: left;width: auto;height: 30px;text-align: center;border: 1px solid #B2B2B2;line-height: 30px;margin-left: 10px;cursor: default;padding:0 10px;}
.top .other .lbdiv .insertlb{display: none;float: left;width: 32px;height: 32px;text-align: center;line-height: 32px;cursor: pointer;}

.top .other .lbdiv .pglable .removelbbnt{display: none;color: #818181;font-size: 18px;float: right;margin-right: 5px;cursor: pointer;}
.top .other .lbdiv .insertlb .addlbbnt{color: #818181;font-size: 22px;cursor: pointer;}

.inputboder{border-bottom: 1px solid #B2B2B2 !important;cursor: text !important;}

body .layer-msg .layui-layer-content{background-color: #fff;color: #000;text-align: center;}

.seldiv{
	width: 400px;
	margin-left: 50px;
	margin-top: 15px;
}
.seldiv .layui-form-select dl{max-height: 200px;}

body .nation-layer .layui-layer-content {overflow: visible;height: 50px !important;}

.checkboxdiv{
	width: 450px;
	margin-left: 25px;
	margin-top: 15px;
	float: left;
	height: 250px;
}
.checkboxdiv .titlediv{
	width: 450px;
	height: 29px;
	text-align: left;
	font-size: 14px;
	border-bottom: 1px solid #b7b7b7;
	line-height: 29px;
	text-indent: 2ex;
	margin-bottom: 10px;
	font-weight: bold;
	float: left;
}
.checkboxdiv .choselbdiv{
	float: left;
	width: 450px;
	height: 380px;
}
.layui-form-checkbox {margin-top: 15px !important;}

.addnewlbdiv{
	float: left;
	width: 450px;
	margin-left: 25px;
	margin-top: 5px;
	height: 50px;
}
.addnewlbdiv .titlediv{
	width: 450px;
	height: 29px;
	text-align: left;
	font-size: 14px;
	border-bottom: 1px solid #b7b7b7;
	line-height: 29px;
	text-indent: 2ex;
	margin-bottom: 20px;
	font-weight: bold;
}
.addnewlbdiv input{
	margin-top: 10px;
	height: 40px;
}
.headerMain{
	width: 100%;
	height: 100%;
}
.radiodiv{width: 545px;height: 50px;margin-left: 25px;}
.auditdescdiv{width: 545px;height: auto;margin-left: 25px;margin-top: 10px;}
.auditdescdiv .title{width: 545px;height: 29px;margin-top: 10px;line-height: 29px;}
.auditdescdiv textarea {
	width: 100%;
	height: 150px;
}

#iptCgAuditState{
	text-decoration: underline;
	color: red;
}
.customReason{
	width: 100%;
	height: 100px;
}

.customDiv{
	width: 100px;
	height: 40px;
	float: left;
}
#tareadesc{
	width: 400px;
	height: 80px;
	float: left;
	margin-top: 10px;
}
.auditdescdiv .title{
	font-size: 16px;
	font-family: 微软雅黑;
}
.tipIcon{
	color: #f00;
}
.passTitle{
	height: 50px;
	font-size: 16px;
	font-family: 微软雅黑;
	line-height: 50px;
	background-color: #f2f2f2;
	margin-top: 5px;
	text-indent: 24px;	
}
.customCss{
	margin-top: 1px !important;
	float: right;
	margin-top: 5px !important;
	margin-right: 10px;
}
.customCss i{
	visibility:hidden;
}
</style>
</head>
<body>
	<div class="maindiv">
		<div class="line">书法家简介</div>
		<div class="top">
			<div class="info">
				<div class="imgdiv">
					<div id="headerMain" class="headerMain"></div>
				</div>
				<div class="infodiv">
					<div style="height: 180px;">
						<ul class="ul1">
							<li><div class="ezchinfodiv1"><div class="lb">姓名:</div><input id="iptCgName" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
							<li><div class="ezchinfodiv1"><div class="lb">字:</div><input id="iptCgSecName" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
							<li><div class="ezchinfodiv1"><div class="lb">号:</div><input id="iptCgNickName" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
							<li><div class="ezchinfodiv1"><div class="lb">性别:</div><input id="iptCgPhotogSex" type="text" name="pginfo" value="" readonly="readonly" onblur="checkSex(this)"/></div></li>
							<li><div class="ezchinfodiv1"><div class="lb">民族:</div><input id="iptCgNation" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
							<li><div class="ezchinfodiv1"><div class="lb">出生:</div><input id="iptCgBornTime" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						</ul>
						<ul class="ul2">
							<li><div class="ezchinfodiv2"><div class="lb">朝代:</div><input id="iptCgDynasty" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
							<li><div class="ezchinfodiv2"><div class="lb">地位:</div><input id="iptCgAgName" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
							<li><div class="ezchinfodiv2"><div class="lb">出生地:</div><input id="iptCgBirthPlace" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
							<li><div class="ezchinfodiv2"><div class="lb">祖籍 :</div><input id="iptCgDescent" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
							<li><div class="ezchinfodiv2"><div class="lb">别称:</div><input id="iptCgAlias" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
							<li><div class="ezchinfodiv2"><div class="lb">去世:</div><input id="iptCgDeathTime" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
						</ul>
						<ul class="ul3">
							<li style="height: auto;width: auto">
								<div class="ezchinfodiv3">
									<div class="lb lb2">重要事迹:</div>
									<textarea id="iptCgImptDeeds" rows="" cols="" style="width: 280px;height: 55px;resize:none;margin-left: 5px;" readonly="readonly"></textarea>
								</div>
							</li>
							<li><div class="ezchinfodiv3"></div></li>
							<li><div class="ezchinfodiv3"><div class="lb">修改时间:</div><input id="iptCgDealTime" type="text" name="pginfo" value="" readonly="readonly"/></div></li>
							<li><div class="ezchinfodiv3"><div class="lb">审核状态:</div><input id="iptCgAuditState" onclick="auditMessage()" type="text" name="pginfo" readonly="readonly" style="cursor: pointer;" title="点击审核"/></div></li>
							<li style="height: auto;width: auto">
								<div class="ezchinfodiv3">
									<div class="lb lb2">审核结果:</div>
									<textarea id="tareaAuditResult" rows="" cols="" style="width: 280px;height: 55px;resize:none;margin-left: 5px;" readonly="readonly"></textarea>
								</div>
							</li>
						</ul>
					</div>
					<div id="works" class="other">
						<div class="lb">重要作品:</div>
						<div id="divPgLable" class="lbdiv">
						</div>
					</div>
				</div>
			</div>
			<div></div>
		</div>
		<div class="bottom">
			<div class="layui-tab">
				<ul class="layui-tab-title">
					<li class="layui-this">生平<i class="iconfont tipIcon">&#xe60f;</i></li>
					<li>成就<i class="iconfont tipIcon">&#xe60f;</i></li>
					<li>风格<i class="iconfont tipIcon">&#xe60f;</i></li>
					<li>作品<i class="iconfont tipIcon">&#xe60f;</i></li>
					<li>合集<i class="iconfont tipIcon">&#xe60f;</i></li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
 						<iframe id="ifrLifeTime" src="cglifetime.do?cgId=<%=request.getAttribute("cgId")%>&jsptype=audit"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrAchievement" src="cgachievement.do?cgId=<%=request.getAttribute("cgId")%>&jsptype=audit"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrStyle" src="cgstyle.do?cgId=<%=request.getAttribute("cgId")%>&jsptype=audit"></iframe>
					</div>
					<div class="layui-tab-item">
 						<iframe id="ifrWorks" src="cgworksaudit.do?cgerId=<%=request.getAttribute("cgId")%>&jsptype=audit"></iframe>
					</div>
					<div class="layui-tab-item">
						<iframe id="ifrRelevantArticle" src="aggregationaudit.do?cgId=<%=request.getAttribute("cgId")%>&jsptype=audit"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var layer;
var form;
var MyFile2 = new Object();
	MyFile2.file = null;
	MyFile2.name = null;
var updatestatus = false;
var inputarr = ["iptCgName","iptCgSecName","iptCgNickName","iptCgPhotogSex","iptCgNation","iptCgBornTime","iptCgDynasty","iptCgAgName","iptCgDeathTime","iptCgDescent","iptCgAlias","iptCgBirthPlace","iptCgImptDeeds"];

var cgId = "<%=request.getAttribute("cgId")%>";
var userId = "<%=request.getAttribute("userId")%>";
var auditStatus = "<%=request.getAttribute("auditStatus")%>";
var auditSolid=auditStatus;
var lableList=[];//新重要作品ID集合.
var headImgP="";
var headChange=false;
var cStateCode="1";

layui.use(["layer","form"], function(){
	layer = layui.layer;
	form = layui.form;
});
/**
 * 获取书法家信息
 * @param cgId
 */
function getCgData(cgId){
	$.ajax({
		url:"getCgBaseinfo.do",
		type:"post",
		dataType:"json",
		data:{"cgId":cgId},
		success:function(data){
			var obj={"cName":data.cName,"cSex":data.cSex,"cNation":data.cNation,"cDynasty":data.cDynasty,"cSecName":data.cSecName,
					"cNickName":data.cNickName,"cDescent":data.cDescent,"cBirthPlace":data.cBirthPlace,"cAlias":data.cAlias,
					"cAgName":data.cAgName,"cImptDeeds":data.cImptDeeds,"cBornTime":data.cBornTime,"cDeathTime":data.cDeathTime,"cDealTime":data.cDealTime,
					"cDynastyCode":data.cDynastyCode,"cAuditResult":data.cAuditResult,"cAuditState":data.cAuditState,"agId":data.agId,"cStateCode":data.cStateCode};
			setPgData(obj);
			pgobj = obj;
			reloadCgHeadImage(data.cHeadImage);
			headImgP=data.cHeadImage;
			pgheadimgname = data.cHeadImage;
			reloadPgLable(data.pLable);
			pglable = data.pLable;
		}
	});
}
/**
 * 设置书法家信息
 * @param obj
 */
function setPgData(obj){
	var cgname = obj.cName;//名字
	var cgsex = obj.cSex;//性别
	var cgnationcode = obj.cNationCode;//民族id
	var cgnation = obj.cNation;//民族
	var cgdynastycode = obj.cDynastyCode;///朝代id
	var cgdynasty = obj.cDynasty;///朝代
	var cgsecname = obj.cSecName;//字
	var cgnickname = obj.cNickName;//号
	var cgdescent = obj.cDescent;//祖籍
	var cgbirthplace = obj.cBirthPlace;//出生地
	var cgalias = obj.cAlias;//别名
	var cgagname = obj.cAgName;//成就
	var agId = obj.agId;//成就Id
	var cgimptdeeds = obj.cImptDeeds;//重要事迹
	var cgborntime = obj.cBornTime;//出生
	var cgdeathtime = obj.cDeathTime;//去世
	var cgdealtime = obj.cDealTime;//修改时间
	var cAuditState = obj.cAuditState;//审核状态
	cStateCode = obj.cStateCode;//审核状态码
 	var cAuditResult = obj.cAuditResult;//审核描述
	$("#iptCgName").val(cgname);
 	$("#iptCgName").attr("defaultVal",cgname);
 	$("#iptCgPhotogSex").val(cgsex);
 	$("#iptCgPhotogSex").attr("defaultVal",cgsex);
 	$("#iptCgNation").val(cgnation);
 	$("#iptCgNation").attr("defaultVal",cgnation);
 	$("#iptCgNation").attr("pgnationid",cgnationcode);
 	$("#iptCgDynasty").val(cgdynasty);
 	$("#iptCgDynasty").attr("defaultVal",cgdynasty);
 	$("#iptCgDynasty").attr("cgDynastyid",cgdynastycode);
 	$("#iptCgSecName").val(cgsecname);
 	$("#iptCgSecName").attr("defaultVal",cgsecname);
 	$("#iptCgNickName").val(cgnickname);
 	$("#iptCgNickName").attr("defaultVal",cgnickname);
 	$("#iptCgDescent").val(cgdescent);
 	$("#iptCgDescent").attr("defaultVal",cgdescent);
 	$("#iptCgAlias").val(cgalias);
 	$("#iptCgAlias").attr("defaultVal",cgalias);
 	$("#iptCgBirthPlace").val(cgbirthplace);
 	$("#iptCgBirthPlace").attr("defaultVal",cgbirthplace);
 	$("#iptCgAgName").val(cgagname);
 	$("#iptCgAgName").attr("defaultVal",cgagname);
 	$("#iptCgAgName").attr("cgAgid",agId);
 	$("#iptCgImptDeeds").val(cgimptdeeds);
 	$("#iptCgImptDeeds").attr("defaultVal",cgimptdeeds);
 	$("#iptCgDealTime").val(cgdealtime);
 	$("#iptCgBornTime").val(cgborntime);
 	$("#iptCgBornTime").attr("defaultVal",cgborntime);
 	$("#iptCgDeathTime").val(cgdeathtime);
 	$("#iptCgDeathTime").attr("defaultVal",cgdeathtime);
 	$("#iptCgAuditState").val(cAuditState+"(点击审核)");
 	$("#iptCgAuditState").attr("codeFlag",cStateCode);
 	$("#tareaAuditResult").val(cAuditResult);
}
/**
 * 重新加载摄影家头像
 * @param imagename
 */
function reloadCgHeadImage(imagename){
	var headerImgCode="<img class='showImg' src='getCGImgStream.do?filename="+imagename+"'></img>";
	$("#headerMain").html("");
	$("#headerMain").html(headerImgCode);
}

//审核
function auditMessage() {
	layui.use(['layer','form'], function(){
		layer=layui.layer;
		form=layui.form;
		var auditstatus = $("#iptCgAuditState").attr("codeFlag");
		$.ajax({
			url:"getcgauditstatus.do",
			type:"post",
			dataType:"json",
			data:{auditStatus : auditStatus},
			success:function(data){
				/* var html = "<form class='layui-form'><div class='radiodiv'>";
				for(var i=0;i<data.auditId.length;i++){
					if(data.auditId[i]==auditstatus){
						html += "<input type='radio' name='auditstatus' value='"+data.auditId[i]+"' title='"+data.auditName[i]+"' checked>";
					}else{
						html += "<input type='radio' name='auditstatus' value='"+data.auditId[i]+"' title='"+data.auditName[i]+"'>";
					}
				}
				html += "</div></form>"; */
				var html = "<div class='passTitle'>内容无问题<button class='layui-btn customCss' bnt-type='1' onclick='passAudit(this)'>通<i>通过</i>过</button></div>";
				html += "<hr class='layui-bg-green' style='margin: 5px 0;'>";
				html += "<form class='layui-form'  lay-filter='test'><div id='auditdescdiv' class='auditdescdiv'><div class='title'>驳回原因：</div>";
				for(var i=0;i<data.reasonName.length;i++){
					if(i==0){
						html += "<input type='checkbox' name='rejectName' lay-skin='primary' value='"+data.reasonId[i]+"' title='"+data.reasonName[i]+"'>";
					}else{
						html += "<input type='checkbox' name='rejectName' lay-skin='primary' value='"+data.reasonId[i]+"' title='"+data.reasonName[i]+"'>";
					}
				}
				html += "<div class='customReason'><div class='customDiv'>"
						+"<input type='checkbox' name='rejectName' value='999' lay-skin='primary' title='自定义'></div>"
						+"<textarea id='tareadesc'></textarea>";
				html += "</div></div></form>";
				if (auditSolid==1) {
					html += "<div class='passTitle'><button class='layui-btn customCss' bnt-type='0' onclick='passAudit(this)'>驳<i>驳回</i>回</button></div>";
				} else {
					html += "<div class='passTitle'><button class='layui-btn customCss' bnt-type='0' onclick='passAudit(this)'>回<i>回退</i>退</button></div>";
				}
				layer.open({
					id:"divAuditPg",
					title:"审核书法家",
					type: 1,
					area: ["600px", "400px;"],
					shade:[0.5 , "#000" , true],
					resize:false,
					btnAlign: "c",
					tipsMore: true,
					content: html,
					yes:function(index, layero){
						/* var auditdesc = "";
						var auditStatus = $("input[name='auditstatus']:checked").val();
						var auditReason = $("input[name='rejectName']:checked").val();
						if (auditReason=="999") {
							auditdesc = $("#tareadesc").val();
						} else {
							auditdesc = $("input[name='rejectName']:checked").attr("title");
						}
						if (auditStatus==undefined||auditStatus=="undefined") {
							layuiLayerMsg("请选择审批意见");
							return false;
						}
						if(auditStatus==2 && auditdesc==""&& auditReason=="999"){
							layuiLayerMsg("驳回时，请填写驳回原因！");
							return false;
						}
						if (auditStatus==5) {
							auditdesc="";
						}
						$("#tareaAuditResult").val(auditdesc);
						$.ajax({
							 url:"auditCallig.do",
							 type:"post",
							 dataType:"text",
							 data:{
							 	cgId : cgId,
							 	auditStatus : auditStatus,
							 	auditDesc : auditdesc,
							 	auditPersn : userId
							 },
							 success:function(data){
							 	getCgData(cgId);
							 	layer.close(index);
							 	$(".tipIcon").remove();
							 }
						 }); */
					},
					success: function(layero, index){
						form.render();
					},
				});
			}
		});
	});
}
//通过审核
function passAudit(e) {
	var auditS = "1";
	var auditD = "";
	var type = $(e).attr("bnt-type");
	if (type==1) {
		auditS="5";
		auditD="审核已通过";
	} else {
		if (auditSolid==1) {
			auditS="2";
		} else {
			auditS="3";
		}
		var arrChk=$("input[name='rejectName']:checked");
		if (arrChk.length==0) {
			layuiLayerMsg("驳回时，请填写驳回原因！");
			return false;
		}
	    //遍历得到每个checkbox的value值
	    for (var i=0;i<arrChk.length;i++){
	    	if (arrChk[i].value=="999") {
				auditD += $("#tareadesc").val()+"、";
			} else {
				auditD += arrChk[i].title+"、";
			}
			
	    }
	    auditD = auditD.substring(0, auditD.length-1);
	}
	submitAudit(auditS,auditD);
}

//提交审核状态
function submitAudit(auditStatus,auditdesc) {
	$.ajax({
		 url:"auditCallig.do",
		 type:"post",
		 dataType:"text",
		 data:{
		 	cgId : cgId,
		 	auditStatus : auditStatus,
		 	auditDesc : auditdesc,
		 	auditPersn : userId
		 },
		 success:function(data){
		 	getCgData(cgId);
		 	layer.closeAll();
		 	$(".tipIcon").remove();
		 	$("#tareaAuditResult").val(auditdesc);
		 }
	 });
}

function layuiLayerMsg(msgstr){
	layer.msg(msgstr,{skin: "layer-msg",time:800,shade:[0.5 , "#000" , true]});
}
$(function(){
// 		adjust();
// 		$(window).resize(adjust);
		$("body").niceScroll({
			cursorwidth : "10px",
			cursorcolor: "#B2B2B2",
			cursorborder: "0px",
			autohidemode: false
		});
		if (auditStatus=="5") {
			$(".tipIcon").remove();
		}
		getCgData(cgId);
});

</script>
</html>
