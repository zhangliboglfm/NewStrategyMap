<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
     <base href="<%=basePath%>">
    <title>添加成就成员</title>
    
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
  <body>
     <div id="" class="layui-layer-content">
		 <form class="layui-form" action="" lay-filter="example">
		 	<div class="layui-form-item">
		    <div style="margin-left:30%;margin-right:30%;">
		      <input id="name" name="name" placeholder="搜索" autocomplete="off" class="layui-input" type="text">
		    </div>
		   <hr class='layui-bg-grey'>
		  <div class="layui-form-item" pane="">
		    <div class="layui-input-block" id="checkbox" style="height:300px">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div id="btn1" class="layui-input-block">
		      <button class="layui-btn" onclick="reject()">确定</button>
		    </div>
		  </div>
		</form>
     </div> 
  </body>
<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
 var agid=window.parent.sondata;
$(function(){
	getagdata();
});
 	function renderForm(){
	  layui.use('form', function(){
	   var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
	   form.render();
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

});
$("input").bind('input propertychange',function(){
		$("#checkbox").html("");
		searchagdata();
});
function searchagdata(){
		var cgname=$("#name").val();
	     $.ajax({
			url:"getsearchachievementagdata.do",
			type:"post",
			data:{"cgname":cgname},
			dataType:"json",
			success:function(data){
				for(var i=0;i<data.length;i++){
					createCheckbox(data[i]);
				}
				renderForm();
			}
		});	
}
function getagdata(){
	     $.ajax({
			url:"getachievementagdata.do",
			type:"post",
			data:{"data":"data"},
			dataType:"json",
			success:function(data){
				for(var i=0;i<data.length;i++){
					createCheckbox(data[i]);
				}
				renderForm();
			}
		});	
}
function createCheckbox(data){
	var code="";
	code+="<input title='"+data.cgername+"' value='"+data.cgerid+"' type='checkbox'>";
	$("#checkbox").append(code);
}
	function reject(){
			$('input:checkbox:checked').each(function(){
			var	cgername= $(this).attr("title");
			var cgerid=$(this).val();
 			$.ajax({
				url:"addachievementmemberdata.do",
				type:"post",
				async: false,
				data:{"agid":agid,"cgername":cgername,"cgerid":cgerid},
				success:function(data){
					layui.layer.msg('<span style="font-size:20px;color:white">'+data+'</span>');
				}
			});
				parent.layer.closeAll();
				parent.location.reload();
		});
 
	};
</script>

</body>
</html>