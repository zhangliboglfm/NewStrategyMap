<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>登录页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
	<link rel="stylesheet" href="css/login.css" type="text/css"></link>
	<link rel="stylesheet" href="css/admin.css" type="text/css"></link>
	<link rel="stylesheet" href="css/global.css" type="text/css"></link>
	<script type="text/javascript" src="layui/layui.all.js"></script>
	<script type="text/javascript" src="layui/lay/modules/element.js"></script>
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>精益云</h2>
      </div>
     <!--  <form action="loginyz.do" method="post"> -->
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username"></label>
          <input type="text" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password"></label>
          <input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
        </div>
        <div class="layui-form-item">
          <div class="layui-row">
            <div class="layui-col-xs7">
              <label class="layadmin-user-login-icon layui-icon layui-icon-vercode"></label>
              <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">
            </div>
            <div class="layui-col-xs5">
              <div style="margin-left: 10px;">
                <img src="getyz.do" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">
              </div>
            </div>
          </div>
        </div>
        <!-- <div class="layui-form-item" style="margin-bottom: 20px;">
          <a href="registAcc.do" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;margin-left:7px;">注册帐号</a>
        </div> -->
        <div>
          <button id="submit" class="layui-btn layui-btn-fluid">登 入</button>
        </div>
      </div>
     <!--  </form> -->
    </div>
  </div>
  </body>
   <script src="layui/layui.js" charset="utf-8"></script>
  <script>
  		$(function(){
  			adjust();
  			$(document).keydown(function(event){
				if (event.keyCode==13) {
					$("#submit").click();
				}
			});
  		})
  		$("#LAY-user-get-vercode").click(function(){
  			$("#LAY-user-get-vercode").attr("src","getyz.do?flag="+Math.random());
  		});
  		function adjust(){
  		}
  		$("#submit").click(function(){
  		verificate();
  		/* var username=$("#LAY-user-login-username").val();
  		var password=$("#LAY-user-login-password").val(); */
  		/* if(username!=""&&password!=""){
  			window.location.href="main.do";
  		}else{
  			alert("帐号密码不能为空");
  		} */
  		});
  		function verificate(){	
  		  var yanzheng=$("#LAY-user-login-vercode").val();
  		   $.ajax({
			url:"yz.do",
			type:"post",
			data:{"yanzhengma":yanzheng},
			success:function(data){
				if(data=="ok"){
				 	var username=$("#LAY-user-login-username").val();
  		 	 		var password=$("#LAY-user-login-password").val();
					$.ajax({
						url:"yz2.do",
						type:"post",
						data:{"username":username,"password":password,"yanzhengma":yanzheng},
						success:function(data){
							if(data=="ok"){
							 	layer.msg('登录成功');
								 window.setTimeout("window.location='main.do'",0);
							}
							else if(data=="false"){
								layer.msg('密码错误');
							}
				 		},
				 		error:function(data){
				 		 	
				 		}
			 		});
	 		}
			else if(data=="false"){
				layer.msg('验证码错误');
				$("#LAY-user-login-vercode").val("");
				$("#LAY-user-get-vercode").click();
			}
	 		},error:function(data){
	 		 layer.msg('请求错误');
	 		}
	 });
  		  /*  $.ajax({
			url:"loginyz.do",
			type:"post",
			dataType:"json",
			data:{"username":username,"password":password,"yanzhengma":yanzheng},
			success:function(data){
			
	 		},error:function(data){
	 		 alert("请求错误");
	 		}
	 }); */
     }
  </script>
</html>
