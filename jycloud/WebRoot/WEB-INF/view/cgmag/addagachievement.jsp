<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
     <base href="<%=basePath%>">
    <title>添加</title>
    
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

  <link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link><script type="text/javascript" src="../../../layui/lay/modules/laypage.js"></script></head>
  <style type="text/css">
  </style>
  <body style="height:300px">
     <div id="" class="layui-layer-content">
		 <form class="layui-form" action="" lay-filter="example">
		  <div class="layui-form-item" style="margin-top:2%">
		    <label class="layui-form-label">名称</label>
		    <div class="layui-input-block"   style="width:450px;">
		      <input name="name" lay-verify="required" autocomplete="off" placeholder="请输入名称" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">别称</label>
		    <div class="layui-input-block"   style="width:450px;">
		      <input name="othername" lay-verify="required" placeholder="请输入别称" autocomplete="off" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">描述</label>
		    <div class="layui-input-block" style="width:450px;">
<!-- 		      <input name="desc" lay-verify="required" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
 -->		      <textarea id="desc" name="desc" lay-verify="required" autocomplete="off"  style="placeholder="请输入描述" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div id="btn1" class="layui-input-block" style="width:450px;height:50px;text-align:center">
		      <button class="layui-btn" lay-submit="" lay-filter="demo2">立即提交</button>
		    </div>
		  </div>
		</form>
     </div> 
  </body>
<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
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
  form.on('submit(demo2)', function(data){
	     $.ajax({
			url:"addAchievementag.do",
			type:"post",
			data:data.field,
			async: false,
			success:function(data){
				layui.layer.msg('<span style="font-size:20px;color:white">'+data+'</span>');
				window.parent.flush();
			}
		});

  });

});
</script>

</body>
</html>