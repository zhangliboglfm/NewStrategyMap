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
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <link rel="stylesheet" href="css/rolemanage.css" type="text/css"></link></head>
  
  <body>
      <div class="layui-card-body">
        	<table class="layui-table" lay-data="{width: 500, height:400, page:true, id:'idTest'}" lay-filter="demo">
			  <thead>
			    <tr>
			      <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
			      <th lay-data="{field:'id', width:80, sort: true, fixed: true}">ID</th>
			      <th lay-data="{field:'rolename', width:80}">角色名</th>
			      <th lay-data="{field:'describe', width:280}">角色描述</th>
			    </tr>
			  </thead>
			</table>
			    <!-- <div class="layui-input-block" style="margin-top:30px;margin-left:20px;">
			      <button class="layui-btn" id="btn1" data-type="getCheckData">提交</button>
			    </div> -->
			    <div class="layui-btn-group demoTable">
			 	  <button class="layui-btn" data-type="getCheckData">分配</button>
				  <!-- <button class="layui-btn" data-type="getCheckLength" onclick="add()">添加</button> -->
			    </div>
      </div>
  </body>
  <script type="text/html" id="barDemo">
  </script>
  <script type="text/javascript" src="layui/layui.all.js"></script>
  <script>
$(function(){
init();
});
function init(){
	var filepass=window.parent.document.getElementById("flag").value;
	layui.use('table', function(){
  	var table = layui.table;
  	table.reload('idTest', {
	  url: 'getweifenpeirole.do'
	  ,where: {
	  filepass:filepass
	  } //设定异步数据接口的额外参数
	  //,height: 300
	});
  	});
};
layui.use('table', function(){
  var table = layui.table;
  //监听表格复选框选择
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  //监听工具条
/*   table.on('tool(demo)', function(obj){
    var data = obj.data;
     if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
        obj.del();
        layer.close(index);
      });
    } else if(obj.event === 'edit'){
     
    }else if(obj.event === 'powerfenpei'){
     
    }else if(obj.event === 'quanxianfenpei'){
   
    }
  }); */

  var $ = layui.$, active = {
    getCheckData: function(){ //获取选中数据
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      var id2="";
		if(data.length!=0){
		id2=data[0].id;
		}
		
		var filepass=window.parent.document.getElementById("flag").value;
		      	$.ajax({
			url:"rolefenpei22.do",
			type:"post",
			dataType:"json",
			async: false,
			data:{"fatherid":filepass},
			success:function(data){
			alert("成功");
	 		}
		});
	 for(var a=0;a<data.length;a++){
      	$.ajax({
			url:"rolefenpei222.do",
			type:"post",
			dataType:"json",
			async: false,
			data:{"id":data[a].id,"fatherid":filepass},
			success:function(data){
			alert("成功");
	 		}
		});
       }
       	window.location.reload();
		window.parent.document.getElementById("layui-layer1").style.display="none";
      }
    ,getCheckLength: function(){ //获取选中数目
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.msg('选中了：'+ data.length + ' 个');
    }
  };
  

  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
});


  </script>
</html>