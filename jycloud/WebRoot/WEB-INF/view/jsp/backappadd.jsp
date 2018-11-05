<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
     <base href="<%=basePath%>">
    <title>My JSP 'test1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
	<link rel="stylesheet" href="css/login.css" type="text/css"></link>
	<link rel="stylesheet" href="css/admin.css" type="text/css"></link>
	<script type="text/javascript" src="layui/lay/modules/element.js"></script>
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <link rel="stylesheet" href="css/rolemanage.css" type="text/css"></link></head>
  
  <body>
     <div id="" class="layui-layer-content">
		 <form class="layui-form" action="" lay-filter="example">
		  <div class="layui-form-item">
		    <label class="layui-form-label">帐号</label>
		    <div class="layui-input-block" style="width:500px;">
		      <input name="username" lay-verify="required" autocomplete="off" placeholder="请输入帐号" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">密码框</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" type="password">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">名称</label>
		    <div class="layui-input-block" style="width:500px;">
		      <input name="name" lay-verify="required" autocomplete="off" placeholder="请输入名称" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">身份证号</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="sfzzh" lay-verify="identity" autocomplete="off" placeholder="请输入身份证号" class="layui-input" type="text">
		    </div>
		  </div>
		 <div class="layui-form-item">
		    <label class="layui-form-label">手机号</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="phone" lay-verify="phone" autocomplete="off" placeholder="请输入手机号" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">电子邮箱</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="mail" lay-verify="email" autocomplete="off" placeholder="请输入邮箱" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">QQ帐号</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="QQ" lay-verify="" autocomplete="off" placeholder="请输入QQ帐号" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">微信帐号</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="VX" lay-verify="" autocomplete="off" placeholder="请输入微信帐号" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">微博帐号</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="WB" lay-verify="" autocomplete="off" placeholder="请输入微博帐号" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">单选框</label>
		    <div class="layui-input-block">
		      <input name="sex" value="男" title="男" checked="" type="radio">
		      <input name="sex" value="女" title="女" type="radio">
		    </div>
		  </div>
		  <div class="layui-form-item">           
		  <div class="layui-inline">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-block">
              <select id="sel" name="rolename">
              </select>
              <div class="layui-unselect layui-form-select">
	              <div class="layui-select-title roleselect">
	              <input placeholder="请选择" value="不限" readonly="readonly" class="layui-input layui-unselect  selecttitle" type="text">
	              <i class="icon1 layui-edge"></i>
	              </div>
	       <!--        <dl class="layui-anim layui-anim-upbit sexoption">
	              <dd value="0" class="layui-this">不限</dd>
	              <dd value="1" class="">系统管理员</dd>
	              <dd value="2" class="">摄影家管理员</dd>
	              <dd value="3" class="">摄影家审核员</dd>
	              </dl> -->
              </div>
            </div>
            
          </div>
          </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="demo2">立即提交</button>
		    </div>
		  </div>
		</form>
     </div>
  </body>
  <script type="text/html" id="barDemo">
  </script>
  <script type="text/javascript" src="layui/layui.all.js"></script>
  <script>
	  function renderForm(){
		  layui.use('form', function(){
		   var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
		   form.render();
		  });
		 }
	 	
  	$(function(){
 	init();
 	
 	});
 	
 	function init(){
 		 	$.ajax({
			url:"getinitbackrole.do",
			type:"post",
			dataType:"json",
			async: false,
			data:{"name":"name"},
			success:function(data){
			$("#sel").html("");
			for(var i=0;i<data.data.length;i++){
				$("#sel").append("<option value=\""+data.data[i].id+"\">"+data.data[i].rolename+"</option>");
				}
			renderForm();//
	 		}
		});
 	}
   layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //日期
  laydate.render({
    elem: '#date'
  });
  laydate.render({
    elem: '#date1'
  });
  var filepass=window.parent.document.getElementById("flag").value;
  $("#flag").val(filepass);
  form.on('submit(demo2)', function(data){
	     $.ajax({
			url:"insertbackuser.do",
			type:"post",
			dataType:"json",
			data:data.field,
			success:function(data){
	
			}
		});
		window.location.reload();
		window.parent.document.getElementById("layui-layer2").style.display="none";
		window.parent.reloadtable();
  });
  //创建一个编辑器
  var editIndex = layedit.build('LAY_demo_editor');

 		
 
  //自定义验证规则
  form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });
  
  //监听提交
      form.on('submit(demo3)', function(data){
	    $.ajax({
			url:" get3.do",
			type:"post",
			dataType:"json",
			data:data.field,
			success:function(data){
			}
		});
		window.location.reload();
		window.parent.document.getElementById("layui-layer4").style.display="none";
		window.parent.reloadtable();
  }); 
});
  </script>
</html>