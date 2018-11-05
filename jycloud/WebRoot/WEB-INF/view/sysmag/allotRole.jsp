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
  <title>重新分配角色</title>
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
		.allotRole{
			margin-top: 20px;
		}
		#roleDesc{
			width: 210px;
			height: 120px;
		}
		.layui-unselect dl { max-height:160px; }
	</style>
</head>
<body>
	<div class="layui-inline allotRole">
      <label class="layui-form-label">角色列表</label>
      <div class="layui-input-inline">
      	<div class="layui-form">
      		<select name="modules" lay-verify="required" id="roleList">
      		  <!-- <option value="99">全部</option>
	          <option value="1">未审核</option>
	          <option value="5">已审核</option> -->
	        </select>
      	</div>
      </div>
    </div>
    <div class="layui-inline allotRole">
      <label class="layui-form-label">角色描述</label>
       <div class="layui-input-block">
	      <textarea placeholder="请输入内容" id="roleDesc" style="resize:none;" readonly></textarea>
	   </div>
    </div>
</body>
<script type="text/javascript">
	var accUserId = "<%=request.getAttribute("accUserId")%>"
	var layer = layui.layer,form = layui.form;
	var userRole = "0";
	$(function () {
		layui.use('form', function() {
			var form = layui.form;
		  	form.render('select'); //刷新select选择框渲染
			form.on('select', function(data){
				//console.log(data);
				userRole = data.value;
				searchRoleDesc(data.value);
			});
		});
		searchUserRole();
	});
	//查用户所拥有的角色及所有角色
	function searchUserRole() {
		$.ajax({
			url:"searchUserRole.do",
			type:"post",
			dataType:"json",
			data:{
				accUserId : accUserId
			},
			success:function(data){
				var htmlCode = "<option value='0' title='无' selected >无角色</option>";
				if (data.userRole.length!=0) {
					userRole = data.userRole[0].ROLE_ID;
				}
				//console.log(userRole)
				for(var a=0;a<data.allRole.length;a++){
					if (data.allRole[a].ROLE_ID==userRole) {
						htmlCode+="<option value='"+data.allRole[a].ROLE_ID+"' title='"+data.allRole[a].ROLE_DESC+"' selected >"+data.allRole[a].ROLE_NAME+"</option>";
						searchRoleDesc(data.allRole[a].ROLE_ID);
					} else {
						htmlCode+="<option value='"+data.allRole[a].ROLE_ID+"' title='"+data.allRole[a].ROLE_DESC+"'>"+data.allRole[a].ROLE_NAME+"</option>";
					}
				}
				$("#roleList").html("");
				$("#roleList").html(htmlCode);
				form.render();
			}
		});
	}
	function searchRoleDesc(roleId) {
		$.ajax({
			url:"searchRoleDesc.do",
			type:"post",
			dataType:"json",
			data:{
				roleId : roleId
			},
			success:function(data){
				if ( data.roleDesc.length!=0) {
					$("#roleDesc").val(data.roleDesc[0].ROLE_DESC);
				}
			}
		});
	}
	//更新角色信息
	function saveUserRole() {
		$.ajax({
			url:"saveUserRole.do",
			type:"post",
			dataType:"text",
			data:{
				userRole : userRole,
				accUserId : accUserId
			},
			success:function(data){
				if (data=="yes") {
					layer.msg("修改角色信息成功");
				} else {
					layer.msg("修改角色信息失败,请联系管理员");
				}
			}
		});
	}
</script>
</html>