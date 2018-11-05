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
  <title>编辑角色信息</title>
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
		#roleDesc{
			width: 200px;
			height: 120px;
		}
	</style>
</head>
<body>
  <div class="layui-layer-content">
		<div class="layui-form-item" style="margin-top: 10px;">
			<label class="layui-form-label">角色ID</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="roleId" name="roleId" lay-verify="" autocomplete="off" placeholder="请输入角色ID" onchange="changedInfo()" onblur="checkRoleId()" class="layui-input" type="text" >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色名称</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="roleName" name="nickName" lay-verify="" autocomplete="off" placeholder="请输入角色名称" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">角色描述</label>
			<div class="layui-input-block" style="width:200px;">
				<textarea placeholder="请输入内容" id="roleDesc" style="resize:none;" onchange="changedInfo()" ></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button id="sureBnt" class="layui-btn" onclick="reupBaseInfo(this)"></button>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var roleId = "<%=request.getAttribute("roleId")%>";
	var editFlag = "<%=request.getAttribute("editFlag")%>";
	var layer = layui.layer;
	var isChanged = false;
	var isAvailable = true;
	$(function () {
		layui.use('form', function() {
			var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
			form.render();
		});
		if (editFlag==2) {
			$("#sureBnt").html("");
			$("#sureBnt").html("更新角色");
			$("#sureBnt").attr("addOrNew","2");
			$("#roleId").attr("readonly","readonly");
			$("#roleId").css("background-color","rgba(0,0,0,0.2)");
			searchRoleInfo();
		} else {
			$("#sureBnt").html("");
			$("#sureBnt").html("新加角色");
			$("#sureBnt").attr("addOrNew","1");
			
		}
	});
	function searchRoleInfo() {
		$.ajax({
			url:"searchRoleInfo.do",
			type:"post",
			dataType:"json",
			data:{
				roleId : roleId
			},
			success:function(data){
				if(data.roleList.length!=0){
					$("#roleId").val(data.roleList[0].ROLE_ID);
					$("#roleName").val(data.roleList[0].ROLE_NAME);
					$("#roleDesc").val(data.roleList[0].ROLE_DESC);
				}
			}
		});
	}
	//更新信息
	function reupBaseInfo(e) {
		var roleId=$("#roleId").val();
		var roleName=$("#roleName").val();
		var roleDesc=$("#roleDesc").val();
		var changeFlag = $(e).attr("addOrNew");
		if (changeFlag==1) {
			if (!isAvailable) {
				layer.msg("已有此ID，请重新命名");
				return false;
			}
		}
		if (roleId==""||roleName==""||roleDesc=="") {
			layer.msg("必要信息不能为空");
			return false;
		}
		if (!isChanged) {
			layer.msg("无信息改变");
			return false;
		}
		$.ajax({
			url:"upOrNewRole.do",
			type:"post",
			dataType:"text",
			data:{
				roleId : roleId,
				roleName : roleName,
				roleDesc : roleDesc,
				changeFlag : changeFlag
			},
			success:function(data){
				if (data=="yes") {
					if (changeFlag==1) {
						layer.msg("新加角色信息成功");
					} else {
						layer.msg("编辑角色信息成功");
					}
				} else {
					if (changeFlag==1) {
						layer.msg("新加角色信息失败,请联系管理员");
					} else {
						layer.msg("编辑角色信息失败,请联系管理员");
					}
				}
			}
		});
	}
	
	function changedInfo() {
		isChanged=true;
	}
	//查roleId是否可用
	function checkRoleId() {
		var roleIdC=$("#roleId").val();
		$.ajax({
			url:"checkRoleId.do",
			type:"post",
			dataType:"text",
			data:{
				roleId : roleIdC
			},
			success:function(data){
				if (data>0) {
					layer.msg("已有此ID，请重新命名");
					isAvailable=false;
				}
			}
		});
	}
</script>
</html>