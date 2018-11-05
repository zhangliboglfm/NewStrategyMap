<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
	<script type="text/javascript" src="layui/layui.js"></script>
</head>
<body>
          
<form class='layui-form' action='' lay-filter='example'>
  
  <div class='layui-form-item'>
    <label class='layui-form-label'>复选框</label>
    <div class='layui-input-block'>
      <input type='checkbox' name='like[write]' title='写作'>
      <input type='checkbox' name='like[read]' title='阅读'>
      <input type='checkbox' name='like[daze]' title='发呆'>
    </div>
  </div>
  
  
 
</form>
 


<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use(['form'], function(){
  var form = layui.form;
  
  
 
  
  
});
</script>

</body>
</html>