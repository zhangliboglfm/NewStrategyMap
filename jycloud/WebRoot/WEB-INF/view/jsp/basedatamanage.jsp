<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>模块管理</title>
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

  <link rel="stylesheet" href="css/backstage.css" type="text/css"></link></head>
  
  <body>
  
  <div class="layui-fluid">   
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
          <div class="layui-inline">
            <label class="layui-form-label">模块ID</label>
            <div class="layui-input-block">
              <input type="text" id="moduleid" name="moduleid" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">父模块ID</label>
            <div class="layui-input-block">
              <input type="text" id="moduleid2" name="fathermoduleid" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">模块名</label>
            <div class="layui-input-block">
              <input type="text" id="modulename" name="modulename" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-user-back-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
        <div class="layui-btn-group demoTable">
		  <button class="layui-btn" data-type="getCheckData">删除</button>
		  <button class="layui-btn" data-type="getCheckLength">添加</button>
	    </div>
        <div id="table1" class="table">
        	<table class="layui-table" lay-data="{height:500,url:'getallmodule.do', page:true,limit:20,id:'idTest'}" lay-filter="demo">
			  <thead>
			    <tr>
			      <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
			      <th lay-data="{field:'moduleid', sort: true, fixed: true}">模块ID</th>
			      <th lay-data="{field:'fathermoduleid'}">父模块ID</th>
			      <th lay-data="{field:'modulename'}">模块名</th>
			      <th lay-data="{field:'modulelevel'}">模块级别</th>
			      <th lay-data="{field:'modulelink'}">模块链接</th>
			      <th lay-data="{field:'ismenu'}">是否目录</th>
			      <th lay-data="{field:'moduleby'}">模块顺序</th>
			      <th lay-data="{fixed: 'right', width:278, align:'center', toolbar: '#barDemo'}">操作</th>
			    </tr>
			  </thead>
			</table>
        </div>
      </div>
    </div>
  </div>
<!--   <div class="layui-layer layui-layer-iframe" id="layui-layer1" type="iframe" times="1" showtime="0" contype="string" style="z-index: 19891015; width: 700px; height: 650px; top: 1px; left: 350px;display:none;">
     <div class="layui-layer-title" style="cursor: move;">权限分配</div>
     <div id="" class="layui-layer-content">
         <iframe scrolling="auto" allowtransparency="true" id="layui-layer-iframe1" name="layui-layer-iframe1" src="rolefenpei_module.do" style="height: 580px;" frameborder="0">
         </iframe>
     </div>
     <span class="layui-layer-setwin">
        <a id="close1" class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;">
        </a>
     </span>
  </div> -->
 <div class="layui-layer layui-layer-iframe" id="layui-layer2" type="iframe" times="1" showtime="0" contype="string" style="z-index: 19891015; width: 650px; height: 500px; top: 30px; left: 350px;display:none;">
     <div class="layui-layer-title" style="cursor: move;">添加角色</div>
     <div id="" class="layui-layer-content">
		<form class="layui-form" action="" lay-filter="example">      
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块ID</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input  name="moduleid" lay-verify="" autocomplete="off" placeholder="请输入角色标识" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">父模块ID</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="fathermoduleid" lay-verify="mail" autocomplete="off" placeholder="请输入角色名称" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块名</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="modulename" lay-verify="WB" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块级别</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="modulelevel" lay-verify="WB" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块链接</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input name="modulelink" lay-verify="WB" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">是否目录</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input name="ismenu" lay-verify="WB" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块顺序</label>
		    <div class="layui-input-block"  style="width:500px;">
		      <input name="moduleby" lay-verify="WB" autocomplete="off" placeholder="请输入描述" class="layui-input" type="text">
		    </div>
		  </div>  
		  <div class="layui-form-item">
		    <div class="layui-input-block"  style="width:500px;">
		      <button class="layui-btn" id="btn1"  lay-submit="" lay-filter="demo2">立即提交</button>
		    </div>
		  </div>
		</form>  
     </div>
     <span class="layui-layer-setwin">
     <a id="close2" class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;">
     </a>
     </span>
  </div>      
 <div class="layui-layer layui-layer-iframe" id="layui-layer3" type="iframe" times="1" showtime="0" contype="string" style="z-index: 19891015; width: 650px; height: 250px; top: 60px; left: 350px;display:none;">
     <div class="layui-layer-title" style="cursor: move;">编辑模块</div>
     <div id="" class="layui-layer-content">
		<form class="layui-form" action="" lay-filter="example">      
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块名</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="modulename" name="modulename" lay-verify="WB" autocomplete="off" placeholder="请输入要修改的描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">模块链接</label>
		    <div class="layui-input-block"   style="width:500px;">
		      <input id="modulelink" name="modulelink" lay-verify="WB" autocomplete="off" placeholder="请输入要修改的描述" class="layui-input" type="text">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label" style="display:none">隐藏标识</label>
		    <div class="layui-input-block">
		      <input id="flag" name="flag" lay-verify="WB" autocomplete="off" type="hidden">
		    </div>
		  </div>	  		  		  
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" id="btn1"  lay-submit="" lay-filter="demo3">立即提交</button>
		    </div>
		  </div>
		</form>  
     </div>
     <span class="layui-layer-setwin">
     <a id="close3" class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;">
     </a>
     </span>
  </div>
  </body>
  <script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
  <script type="text/javascript" src="layui/layui.all.js"></script>
  <script>
     var editflag;
	$("#close1").click(function(){
		$("#layui-layer1").css("display","none");
	});
	$("#close2").click(function(){
		$("#layui-layer2").css("display","none");
	});
	$("#close3").click(function(){
		$("#layui-layer3").css("display","none");
	});
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
			url:"insertmodule.do",
			type:"post",
			dataType:"json",
			data:data.field,
			success:function(data){
				table.reload('idTest', {
				  url:'getallmodule.do'
				  ,where: {
				  } //设定异步数据接口的额外参数
				  //,height: 300
				});
			}
		});
  });
       form.on('submit(demo3)', function(data){
	    $.ajax({
			url:"editmodule.do",
			type:"post",
			dataType:"json",
			data:data.field,
			success:function(data){
				table.reload('idTest', {
				  url:'getallbackuser.do'
				  ,where: {
				  } //设定异步数据接口的额外参数
				  //,height: 300
				});
			}
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
});
layui.use('table', function(){
  var table = layui.table;
  //监听表格复选框选择
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  //监听工具条
  table.on('tool(demo)', function(obj){
    var data = obj.data;
     if(obj.event === 'del'){
     $("#flag").val($(this).parent().parent().parent().find("td").eq(1).find("div").html());
        editflag=$("#flag").val();
	    	$.ajax({
			url:"deletemodule.do",
			type:"post",
			dataType:"json",
			data:{flag:editflag},
			success:function(data){
				
			}
		});   
		table.reload('idTest', {
				  url:'getallmodule.do'
				  ,where: {
				  } //设定异步数据接口的额外参数
				  //,height: 300
				});  
    /*   layer.confirm('真的删除行么', function(index){
        obj.del();
        layer.close(index);
      }); */
    } else if(obj.event === 'edit'){
     $("#layui-layer3").css("display","block");
     $("#flag").val($(this).parent().parent().parent().find("td").eq(1).find("div").html());
     
    }/* else if(obj.event === 'rolefenpei'){
     $("#layui-layer1").css("display","block");
    } */
  });
  var $ = layui.$, active = {
        getCheckData: function(){ //获取选中数据
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
     
      if(data.length === 0){
          return layer.msg('请选择数据');
        }
        for(var i=0;i<data.length;i++){
        $.ajax({
			url:"deleteselectmodule.do",
			type:"post",
			dataType:"json",
			async: false,
			data:{"moduleid":data[i].moduleid},
			success:function(data){

			}
		});
        }
     		table.reload('idTest', {
				  url:'getallmodule.do'
				  ,where: {
				  } //设定异步数据接口的额外参数
				  //,height: 300
			});
      }
    ,getCheckLength: function(){ //获取选中数目
    /*   var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.msg('选中了：'+ data.length + ' 个'); */
      $("#layui-layer2").css("display","block");
    }
  };
  

  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
    $(".layui-btn.layuiadmin-btn-admin").click(function(){
	 var moduleid=$("#moduleid").val();
	 var fathermoduleid=$("#moduleid2").val();
	 var modulename=$("#modulename").val();
/* 	 alert(condition1+"----"+condition2); */
	      //执行重载
	         table.reload('idTest', {
					  url:'selectmodule.do'
					  ,where: {
					  moduleid:moduleid,
					  fathermoduleid:fathermoduleid,
					  modulename:encodeURI(modulename)
					  } //设定异步数据接口的额外参数
					  //,height: 300
					});
	 }); 
});

  </script>
</html>