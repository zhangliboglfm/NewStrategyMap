<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
     <base href="<%=basePath%>">
    <title>成就审核</title>
    
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
  		.top{
  			position:relative;
  		}
  		.deletePhotog{
			position:absolute;right:10px;top:8px;
			text-align:center;
		}
		.deletePhotog2{
			position:absolute; right:10px; bottom:10px; 
		}
		.bottomcheck{
			margin-top:2%;
		}
		.top{
			height:46px;
		}
  </style>
  <body>
     <div id="" class="layui-layer-content">
		 <form class="layui-form" action="" lay-filter="example">
		  <div class="layui-form-item top">
		    <label class="layui-form-label top">内容无问题</label>
		    <div class="layui-input-block deletePhotog">
		      <button  id="btn1" class="layui-btn" lay-submit="" onclick="adopt(this)" lay-filter="demo1">通过</button>
		    </div>
		  </div>
		  <hr class='layui-bg-grey'>
		  <div class="layui-form-item layui-row" style="margin-left:3%">
		    <div class="layui-col-md12">
		    <div id="checkbox">
			      <input type="checkbox" name="simple" lay-skin="primary" title="尺寸不符合">	
			      <input type="checkbox" name="simple" lay-skin="primary" title="信息错误">	
			      <input type="checkbox" name="simple" lay-skin="primary" title="标签错误">
			</div>
			<div class="bottomcheck"> 
				<div style="width:14%;float:left">      	
			      	<input type="checkbox" name="custom" lay-skin="primary" title="自定义">   
				</div>
				<div  style="width:83%;float:left">     	
				    <textarea id="customdata" placeholder="请输入内容" style="width:70%" class="layui-textarea"></textarea>	    
				</div>				
			</div>
		    </div>	
		    <div class="layui-input-block deletePhotog2">
		      <button  id="btn1" class="layui-btn" lay-submit="" onclick="reject(this)" lay-filter="demo1">驳回</button>
		    </div> 	    
		  </div>		
		</form>
     </div> 
  </body>
<script src="layui/layui.all.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
	var userId = "<%=request.getAttribute("userId")%>";
	var sondata=window.parent.sondata;
	var flag=window.parent.flag;
	var type="驳回";
	$(function(){
 	getdata(); 
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

	});
	
	function getdata(){
	     $.ajax({
			url:"getauditreasondata.do",
			type:"post",
			data:{"type":type},
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
/* 	code+="<input title='123' lay-skin='primary'  type='checkbox'>"; */
	code+="<input title='"+data.reasonname+"' name='simple' lay-skin='primary' type='checkbox'>";
	$("#checkbox").append(code);
	}
	//通过
	function adopt(){
			 $.ajax({
				url:"adoptachievement.do",
				type:"post",
				async: false, 
				data:{"agid":sondata,"userId":userId,"flag":flag},
				success:function(data){	
					layui.layer.msg('<span style="font-size:20px;color:white">'+data+'</span>');
				}
			});	
			if(flag=="AG"){
				window.parent.flush();
				parent.layer.closeAll(); 
			}else{
				parent.parent.layer.closeAll();
				parent.parent.flush();
			}		
	};
	//驳回
	function reject(){
			var data0="";	
 			$('input:checkbox[name="simple"]:checked').each(function(){
				data0 += $(this).attr("title")+",";
				});
				$('input:checkbox[name="custom"]:checked').each(function(){
				data0 += $("#customdata").val();
			});
			  $.ajax({
				url:"rejectachievement.do",
				type:"post",
				async: false, 
				data:{"agid":sondata,"reason":data0,"userId":userId,"flag":flag},
				success:function(data){
					layui.layer.msg('<span style="font-size:20px;color:white">'+data+'</span>');
				}
			});
			if(flag=="AG"){
				window.parent.flush();
				parent.layer.closeAll(); 
			}else{
				parent.parent.flush();
				parent.parent.layer.closeAll();
				
			}
			
				
	};
</script>
	
</body>
</html>