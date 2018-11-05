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
  <title>编辑基本信息</title>
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
 <!--  <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
        <div class="layui-form-item" title="帐号">
          <label class="layadmin-user-login-icon layui-icon layui-icon-user" for="LAY-user-login-cellphone"></label>
          <input id="accountId" type="text" name="user" id="LAY-user-login-user" lay-verify="required" placeholder="帐号" class="layui-input" readonly="readonly" style="background-color: rgba(0,0,0,0.2)">
        </div>
        <div class="layui-form-item" title="用户名">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-nickname"></label>
          <input id="nickName" type="text" name="nickname" id="LAY-user-login-nickname" lay-verify="required" placeholder="用户名" onchange="changedInfo()" class="layui-input">
        </div>
        <div class="layui-form-item" title="用户类型">
          <label class="layadmin-user-login-icon layui-icon layui-icon-survey" for="LAY-user-login-usertype"></label>
          <input id="accType" type="text" name="usertype" id="LAY-user-login-usertype" lay-verify="required" placeholder="后台用户或APP用户" onchange="changedInfo()" class="layui-input">
        </div>
        <div class="layui-form-item" title="用户性别">
          <label class="layadmin-user-login-icon layui-icon layui-icon-survey" for="LAY-user-login-usersex"></label>
          <input id="accountSex" type="text" name="usersex" id="LAY-user-login-usersex" lay-verify="usersex" placeholder="性别" onchange="changedInfo()" class="layui-input">
        </div>
        <div class="layui-form-item" title="身份证号">
          <label class="layadmin-user-login-icon layui-icon layui-icon-survey" for="LAY-user-login-idcard"></label>
          <input id="idCard" type="text" name="idcard" id="LAY-user-login-idcard" lay-verify="idcard" placeholder="身份证号" onchange="changedInfo()" class="layui-input">
        </div>
        <div class="layui-form-item" title="手机号码">
          <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone" for="LAY-user-login-cellphone"></label>
          <input id="userPhone" type="text" name="cellphone" id="LAY-user-login-cellphone" lay-verify="cellphone" placeholder="手机号码" onchange="changedInfo()" class="layui-input">
        </div>
        <div class="layui-form-item" title="电子邮箱">
          <label class="layadmin-user-login-icon layui-icon layui-icon-survey" for="LAY-user-login-Email"></label>
          <input id="userEmail" type="text" name="Email" id="LAY-user-login-nickname" lay-verify="Email" placeholder="电子邮箱" onchange="changedInfo()" class="layui-input">
        </div>
        <div class="layui-form-item" title="QQ号">
          <label class="layadmin-user-login-icon layui-icon layui-icon-login-qq" for="LAY-user-login-qq"></label>
          <input id="userQQ" type="text" name="qq" id="LAY-user-login-qq" lay-verify="qq" placeholder="QQ账号" onchange="changedInfo()" class="layui-input">
        </div>
        <div class="layui-form-item" title="微信号">
          <label class="layadmin-user-login-icon layui-icon layui-icon-login-wechat" for="LAY-user-login-wechat"></label>
          <input id="userWechat" type="text" name="wechat" id="LAY-user-login-wechat" lay-verify="wechat" placeholder="微信账号" onchange="changedInfo()" class="layui-input">
        </div>
        <div class="layui-form-item" title="微博号">
          <label class="layadmin-user-login-icon layui-icon layui-icon-login-weibo" for="LAY-user-login-weibo"></label>
          <input id="userWeibo" type="text" name="weibo" id="LAY-user-login-weibo" lay-verify="weibo" placeholder="微博账号" onchange="changedInfo()" class="layui-input">
        </div>
        <div class="layui-form-item">
          <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-reg-submit" onclick="reupBaseInfo()">更新信息</button>
        </div>
      </div>
    </div>
  </div> -->
  <div  class="layui-layer-content">
		<div class="layui-form-item" style="margin-top: 10px;">
			<label class="layui-form-label">帐号</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="accountId" name="accountId" lay-verify="" autocomplete="off" placeholder="请输入帐号" class="layui-input" type="text" readonly="readonly" style="background-color: rgba(0,0,0,0.2)">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="nickName" name="nickName" lay-verify="" autocomplete="off" placeholder="请输入用户名" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户类型</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="accType" name="name" lay-verify="" autocomplete="off" placeholder="后台用户或APP用户" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户性别</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="accountSex" name="name" lay-verify="" autocomplete="off" placeholder="男或女" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">身份证号</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="idCard" name="sfzzh" lay-verify="" autocomplete="off" placeholder="请输入身份证号" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">手机号</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="userPhone" name="phone" lay-verify="phone" autocomplete="off" placeholder="请输入手机号" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">电子邮箱</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="userEmail" name="mail" lay-verify="" autocomplete="off" placeholder="请输入邮箱"  onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">QQ帐号</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="userQQ" name="QQ" lay-verify="" autocomplete="off" placeholder="请输入QQ帐号" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">微信帐号</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="userWechat" name="VX" lay-verify="" autocomplete="off" placeholder="请输入微信帐号" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">微博帐号</label>
			<div class="layui-input-block" style="width:200px;">
				<input id="userWeibo" name="WB" lay-verify="" autocomplete="off" placeholder="请输入微博帐号" onchange="changedInfo()" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="demo2" onclick="reupBaseInfo()">更新信息</button>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var accUserId = "<%=request.getAttribute("accUserId")%>"
	var layer = layui.layer;
	var isChanged = false;
	$(function () {
		layui.use('form', function() {
			var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
			form.render();
		});
		searchBaseInfo();
	});
	function searchBaseInfo() {
		$.ajax({
			url:"searchBaseInfo.do",
			type:"post",
			dataType:"json",
			data:{
				accUserId : accUserId
			},
			success:function(data){
				if(data.list.length!=0){
					$("#accountId").val(accUserId);
					$("#nickName").val(data.list[0].USER_NAME);
					$("#accType").val(data.list[0].USER_TYPE);
					$("#accountSex").val(data.list[0].USER_GENDER=="M"?"男":"女");
					$("#idCard").val(data.list[0].IDCARD_NO);
					$("#userPhone").val(data.list[0].PHONE_NO);
					$("#userEmail").val(data.list[0].EMAIL);
					$("#userQQ").val(data.list[0].QQ_ACCT_ID);
					$("#userWechat").val(data.list[0].MMSG_ACCT_ID);
					$("#userWeibo").val(data.list[0].MBLOG_ACCT_ID);
				}
			}
		});
	}
	//更新信息
	function reupBaseInfo() {
		var accountId=accUserId;
		var nickName=$("#nickName").val();
		var accType=$("#accType").val();
		var accountSex=$("#accountSex").val();
		var idCard=$("#idCard").val();
		var userPhone=$("#userPhone").val();
		var userEmail=$("#userEmail").val();
		var userQQ=$("#userQQ").val();
		var userWechat=$("#userWechat").val();
		var userWeibo=$("#userWeibo").val();
		if (!isChanged) {
			layer.msg("无信息改变");
			return false;
		}
		if (accountSex!="男"&&accountSex!="女") {
			layer.msg("用户性别有误");
			return false;
		} else {
			$.ajax({
				url:"reupBaseInfo.do",
				type:"post",
				dataType:"text",
				data:{
					accountId : accountId,
					nickName : nickName,
					accType : accType,
					accountSex : accountSex,
					idCard : idCard,
					userPhone : userPhone,
					userEmail : userEmail,
					userQQ : userQQ,
					userWechat : userWechat,
					userWeibo : userWeibo
				},
				success:function(data){
					if (data=="yes") {
						layer.msg("修改基本信息成功");
					} else {
						layer.msg("修改基本信息失败,请联系管理员");
					}
				}
			});
		}
	}
	function changedInfo() {
		isChanged=true;
	}
</script>
</html>