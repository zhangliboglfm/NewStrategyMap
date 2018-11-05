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
<title>修改密码</title>
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
</head>

  <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>精益云--修改密码</h2>
      </div>
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
      		<div class="layui-form-item">
              <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-user"></label>
              <input id="userAccount" type="password" name="user" id="LAY-user-login-password" lay-verify="pass" placeholder="原密码" class="layui-input">
            </div>
            <div class="layui-form-item">
              <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
              <input id="newPass" type="password" name="password" id="LAY-user-login-password" lay-verify="pass" placeholder="新密码" class="layui-input">
            </div>
            <div class="layui-form-item">
              <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-repass"></label>
              <input id="renewPass" type="password" name="repass" id="LAY-user-login-repass" lay-verify="repass" placeholder="确认密码" class="layui-input">
            </div>
            <div class="layui-form-item">
              <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-forget-resetpass" onclick="resetBnt();">重置新密码</button>
            </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
	var layer = layui.layer;
	function resetBnt() {
		var userAccount=$("#userAccount").val();
		var newPassword=$("#newPass").val();
		var renewPass=$("#renewPass").val();
		if (userAccount.trim()=="") {
			layer.msg("请输入原密码");
		} else if(newPassword==""||renewPass==""){
			layer.msg("密码不能为空");
		} else if(newPassword!=renewPass){
			layer.msg("两次输入的密码不一致");
		} else {
			$.ajax({
				url:"resetPass.do",
				type:"post",
				dataType:"text",
				data:{
					userAccount : userAccount,
					newPassword : newPassword,
					renewPass : renewPass
				},
				success:function(data){
					if (data=="1") {
						layer.msg("密码修改成功");
					} else if(data=="2"){
						layer.msg("原密码错误，请重新输入");
					} else {
						layer.msg("密码修改失败,请联系管理员");
					}
				}
			});
		}
	}
</script>
</html>