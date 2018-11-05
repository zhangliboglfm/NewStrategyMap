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
		    <label class="layui-form-label">手机号</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input id="phone" name="phone" lay-verify="phone" autocomplete="off" placeholder="请输入手机号" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">电子邮箱</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input id="mail" name="mail" lay-verify="email" autocomplete="off" placeholder="请输入邮箱" class="layui-input" type="text">
		    </div>
		  </div>
		    <div class="layui-form-item">
			    <label class="layui-form-label">状态</label>
			    <div class="layui-input-block">
			      <input name="close" type="checkbox" lay-skin="switch" lay-text="正常|锁定">
			    </div>
			 </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label" style="display:none">隐藏标识</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input id="flag" name="flag" lay-verify="WB" autocomplete="off" type="hidden">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block"  style="width:500px;">
		      <button class="layui-btn" lay-submit="" lay-filter="demo3">立即提交</button>
		    </div>
		  </div>
		</form>  
     </div>
  </div> 
  </body>
  <script type="text/html" id="barDemo">
  </script>
  <script type="text/javascript" src="layui/layui.all.js"></script>
  <script>
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
		 
				table.reload('idTest', {
				  url:'getallbackuser.do'
				  ,where: {
				  } //设定异步数据接口的额外参数
				  //,height: 300
				});
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