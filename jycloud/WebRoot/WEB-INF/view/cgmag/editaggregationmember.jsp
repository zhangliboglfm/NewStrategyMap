<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
     <base href="<%=basePath%>">
    <title>编辑中添加</title>
    
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
		   <hr class='layui-bg-blue'>
		  <div class="layui-form-item" pane="">
		    <div class="layui-input-block" id="checkbox" style="height:300px;margin-right:110px;index-y:auto">
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
 var compid=window.parent.compid;
 var cgId = "<%=request.getAttribute("cgerId")%>";
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
		getsearchagdata();
});
function getsearchagdata(){
		var cgname=$("#name").val();
	     $.ajax({
			url:"searchnewaggregationagdata.do",
			type:"post",
			data:{"cgId":cgId,"cgname":cgname},
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
			url:"getnewaggregationagdata.do",
			type:"post",
			data:{"cgId":cgId},
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
	code+="<input title='"+data.worksname+"' value='"+data.worksid+"' type='checkbox'>";
	$("#checkbox").append(code);
}
	function reject(){
		$('input:checkbox:checked').each(function(){
			var	worksname= $(this).attr("title");
			var worksid=$(this).val();
			window.parent.getImageInfo2(worksid);
		});
		window.parent.layer.closeAll();
	};
</script>

</body>
</html>