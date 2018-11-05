<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
  <title>编辑模块信息</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  	
  	<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
	<link rel="stylesheet" href="css/login.css" type="text/css"></link>
	<link rel="stylesheet" href="css/admin.css" type="text/css"></link>
	<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
	<script type="text/javascript" src="layui/layui.all.js"></script>
	<script type="text/javascript" src="layui/lay/modules/element.js"></script>
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="layui/lay/modules/form.js"></script>
	<style type="text/css">
		html{overflow: hidden !important;}
		.layadmin-user-login{
			padding: 0 !important;
		}
		.layadmin-user-login-icon{
			color: #5D5D5D;
		}
	</style>
</head>
<body>
  <div class="layui-layer-content">
		<div class="layui-form-item" style="margin-top: 10px;">
			<label class="layui-form-label">模块ID</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="moduleId" name="accountId" lay-verify="" autocomplete="off" placeholder="请输入模块ID" onchange="changedInfo()" class="layui-input" type="text" >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">模块名称</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="moduleName" name="nickName" lay-verify="" autocomplete="off" placeholder="请输入模块名称" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">父模块ID</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="parentMId" name="name" lay-verify="" autocomplete="off" placeholder="父模块ID" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">模块级别</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="moduleLvl" name="name" lay-verify="" autocomplete="off" placeholder="模块级别" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">模块链接</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="moduleURL" name="sfzzh" lay-verify="" autocomplete="off" placeholder="请输入模块链接" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">模块类型</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="moduleType" name="phone" lay-verify="phone" autocomplete="off" placeholder="请输入模块类型" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">模块顺序</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="moduleOrder" name="mail" lay-verify="" autocomplete="off" placeholder="请输入模块顺序"  onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button id="sureBnt" class="layui-btn" onclick="reupBaseInfo(this)">更新信息</button>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var mouldeId = "<%=request.getAttribute("mouldeId")%>";
	var editFlag = "<%=request.getAttribute("editFlag")%>";
	var layer = layui.layer;
	var isChanged = false;
	$(function () {
		layui.use('form', function() {
			var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
			form.render();
		});
		if (editFlag==2) {
			$("#sureBnt").html("");
			$("#sureBnt").html("更新信息");
			$("#sureBnt").attr("addOrNew","2");
			$("#moduleId").attr("readonly","readonly");
			$("#moduleId").css("background-color","rgba(0,0,0,0.2)");
			searchMoudleInfo();
		} else {
			$("#sureBnt").html("");
			$("#sureBnt").html("新加模块");
			$("#sureBnt").attr("addOrNew","1");
		}
	});
	function searchMoudleInfo() {
		$.ajax({
			url:"searchMoudleInfo.do",
			type:"post",
			dataType:"json",
			data:{
				mouldeId : mouldeId
			},
			success:function(data){
				if(data.moudleList.length!=0){
					$("#moduleId").val(data.moudleList[0].MODULE_ID);
					$("#moduleName").val(data.moudleList[0].MODULE_NAME);
					$("#parentMId").val(data.moudleList[0].PARENT_MODULE_ID);
					$("#moduleLvl").val(data.moudleList[0].MODULE_LVL);
					$("#moduleURL").val(data.moudleList[0].MODULE_URL);
					$("#moduleType").val(data.moudleList[0].MODULE_TYPE);
					$("#moduleOrder").val(data.moudleList[0].MODULE_ORDER);
				}
			}
		});
	}
	//更新信息
	function reupBaseInfo(e) {
		var moduleId=$("#moduleId").val();
		var moduleName=$("#moduleName").val();
		var parentMId=$("#parentMId").val();
		var moduleLvl=$("#moduleLvl").val();
		var moduleType=$("#moduleType").val();
		var moduleOrder=$("#moduleOrder").val();
		var moduleURL=$("#moduleURL").val();
		var changeFlag = $(e).attr("addOrNew");
		if (moduleId==""||moduleName==""||parentMId==""||moduleLvl==""||moduleType==""||moduleOrder=="") {
			layer.msg("必要信息不能为空");
			return false;
		}
		if (!isChanged) {
			layer.msg("无信息改变");
			return false;
		}
		$.ajax({
			url:"upOrNewMoudle.do",
			type:"post",
			dataType:"text",
			data:{
				moduleId : moduleId,
				moduleName : moduleName,
				parentMId : parentMId,
				moduleLvl : moduleLvl,
				moduleType : moduleType,
				moduleOrder : moduleOrder,
				moduleURL : moduleURL,
				changeFlag : changeFlag
			},
			success:function(data){
				if (data=="yes") {
					if (changeFlag==1) {
						layer.msg("新加模块信息成功");
					} else {
						layer.msg("编辑模块信息成功");
					}
				} else {
					if (changeFlag==1) {
						layer.msg("新加模块信息失败,请联系管理员");
					} else {
						layer.msg("编辑模块信息失败,请联系管理员");
					}
				}
			}
		});
	}
	function changedInfo() {
		isChanged=true;
	}
</script>
</html>