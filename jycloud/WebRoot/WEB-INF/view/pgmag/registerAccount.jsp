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
  <title>注册</title>
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
		.layadmin-user-login{
			padding: 0 !important;
		}
		.layadmin-user-login-icon{
			color: #5D5D5D;
		}
	</style>
</head>
<body>
  <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>精益云--注册</h2>
      </div>
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-user" for="LAY-user-login-cellphone"></label>
          <input id="accountId" type="text" name="user" id="LAY-user-login-user" lay-verify="required" placeholder="帐号" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
          <input id="accPassword" type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-repass"></label>
          <input id="reAccPass" type="password" name="repass" id="LAY-user-login-repass" lay-verify="required" placeholder="确认密码" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-nickname"></label>
          <input id="nickName" type="text" name="nickname" id="LAY-user-login-nickname" lay-verify="required" placeholder="昵称" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-survey" for="LAY-user-login-usertype"></label>
          <input id="accType" type="text" name="usertype" id="LAY-user-login-usertype" lay-verify="required" placeholder="用户类型" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-survey" for="LAY-user-login-usersex"></label>
          <input id="accountSex" type="text" name="usersex" id="LAY-user-login-usersex" lay-verify="usersex" placeholder="性别" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-survey" for="LAY-user-login-idcard"></label>
          <input id="idCard" type="text" name="idcard" id="LAY-user-login-idcard" lay-verify="idcard" placeholder="身份证号" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone" for="LAY-user-login-cellphone"></label>
          <input id="userPhone" type="text" name="cellphone" id="LAY-user-login-cellphone" lay-verify="cellphone" placeholder="手机号码" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-survey" for="LAY-user-login-Email"></label>
          <input id="userEmail" type="text" name="Email" id="LAY-user-login-nickname" lay-verify="Email" placeholder="电子邮箱" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-login-qq" for="LAY-user-login-qq"></label>
          <input id="userQQ" type="text" name="qq" id="LAY-user-login-qq" lay-verify="qq" placeholder="QQ账号" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-login-wechat" for="LAY-user-login-wechat"></label>
          <input id="userWechat" type="text" name="wechat" id="LAY-user-login-wechat" lay-verify="wechat" placeholder="微信账号" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-login-weibo" for="LAY-user-login-weibo"></label>
          <input id="userWeibo" type="text" name="weibo" id="LAY-user-login-weibo" lay-verify="weibo" placeholder="微博账号" class="layui-input">
        </div>
        <div class="layui-form-item">
          <input type="checkbox" name="agreement" lay-skin="primary" title="同意用户协议" checked>
        </div>
        <div class="layui-form-item">
          <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-reg-submit" onclick="submitRegist()">注 册</button>
        </div>
        <div class="layui-trans layui-form-item layadmin-user-login-other" id="loginJump">
          <a href="login.do" class="layadmin-user-jump-change layadmin-link layui-hide-xs">用已有帐号登入</a>
        </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
	var jumpFlag = "<%=request.getAttribute("jumpFlag")%>"
	var layer = layui.layer;
	var accAvai = true;
	$(function () {
		if (jumpFlag==1||jumpFlag==2) {
			$("#loginJump").hide();
		} else {
			$("#loginJump").show();
		}
	});
	//验证帐号是否已存在
	$("#accountId").blur(function(){
		var accountId=$("#accountId").val();
		$.ajax({
			url:"verifyAccount.do",
			type:"post",
			dataType:"text",
			data:{
				accountId : accountId
			},
			success:function(data){
				if (data=="0") {
					layer.msg("帐号已存在");
					accAvai=false;
				} else {
					accAvai = true;
				}
			}
		});
	});
	function submitRegist() {
		var accountId=$("#accountId").val();
		var accPassword=$("#accPassword").val();
		var reaccPass=$("#reAccPass").val();
		var nickName=$("#nickName").val();
		var accType=$("#accType").val();
		var accountSex=$("#accountSex").val();
		var idCard=$("#idCard").val();
		var userPhone=$("#userPhone").val();
		var userEmail=$("#userEmail").val();
		var userQQ=$("#userQQ").val();
		var userWechat=$("#userWechat").val();
		var userWeibo=$("#userWeibo").val();
		if (accPassword!=reaccPass) {
			layer.msg("两次输入的密码不一致");
		} else {
			if(accAvai){
				$.ajax({
					url:"submitRegist.do",
					type:"post",
					dataType:"text",
					data:{
						accountId : accountId,
						accPassword : accPassword,
						nickName : nickName,
						accType : accType,
						accountSex : accountSex,
						idCard : idCard,
						userPhone : userPhone,
						userEmail : userEmail,
						userQQ : userQQ,
						userWechat : userWechat,
						userWeibo : userWeibo,
						jumpFlag : jumpFlag
					},
					success:function(data){
						if (data=="1") {
							layer.msg("注册成功");
							location.href ='login.do';
						} else {
							layer.msg("注册失败,请联系管理员");
						}
					}
				});
			} else {
				layer.msg("帐号已存在,请重新输入");
			}
		}
	}
</script>
</html>