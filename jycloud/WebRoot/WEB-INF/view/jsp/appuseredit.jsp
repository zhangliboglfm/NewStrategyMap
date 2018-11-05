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

  <link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link><script type="text/javascript" src="../../../layui/lay/modules/laypage.js"></script></head>
  
  <body>
     <div id="" class="layui-layer-content">
		<form class="layui-form" action="" lay-filter="example">      
		  <div class="layui-form-item">
		    <label class="layui-form-label">手机号</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="phone" name="phone" lay-verify="" autocomplete="off" placeholder="请输入手机号" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">电子邮箱</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="mail" name="mail" lay-verify="" autocomplete="off" placeholder="请输入邮箱" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">QQ帐号</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="QQ" name="QQ" lay-verify="" autocomplete="off" placeholder="请输入QQ帐号" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">微信帐号</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="VX" name="VX" lay-verify="" autocomplete="off" placeholder="请输入微信帐号" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">微博帐号</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="WB" name="WB" lay-verify="" autocomplete="off" placeholder="请输入微博帐号" class="layui-input" type="text">
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
		    <div class="layui-input-block">
		      <input id="flag" name="flag" lay-verify="" autocomplete="off" type="hidden">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" id="btn2" lay-submit="" lay-filter="demo3">立即提交</button>
		    </div>
		  </div>
		</form>  
     </div>
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
  var filepass=window.parent.document.getElementById("flag").value;
  $("#flag").val(filepass);
  form.on('submit(demo3)', function(data){
	    $.ajax({
			url:"editappuser.do",
			type:"post",
			dataType:"json",
			data:data.field,
			success:function(data){
				table.reload('idTest', {
				  url:'getallappuser.do'
				  ,where: {
				  } //设定异步数据接口的额外参数
				  //,height: 300
				});
			}
		});
			window.location.reload();
		window.parent.document.getElementById("layui-layer1").style.display="none";
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
});
</script>

</body>
</html>