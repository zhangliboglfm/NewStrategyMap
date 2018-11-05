<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>APP用户管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/login.css" type="text/css"></link>
<link rel="stylesheet" href="css/admin.css" type="text/css"></link>
<link rel="stylesheet" href="css/backstage.css" type="text/css"></link>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/miapsoft.autohide.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-block">
							<input id="username1" type="text" name="username"
								placeholder="请输入" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">手机号</label>
						<div class="layui-input-block">
							<input id="telphone1" type="text" name="telphone"
								placeholder="请输入" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">性别</label>
						<div class="layui-input-block">
							<select id="selectoption" name="sex">
								<option value="0" selected="selected">不限</option>
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
							<div class="layui-unselect layui-form-select">
								<div class="layui-select-title sexselect">
									<input placeholder="请选择" value="不限" readonly="readonly" class="layui-input layui-unselect  selecttitle" type="text">
									<i class="icon1 layui-edge"></i>
								</div>
								<dl class="layui-anim layui-anim-upbit sexoption">
									<dd value="0" class="layui-this">不限</dd>
									<dd value="1" class="">男</dd>
									<dd value="2" class="">女</dd>
								</dl>
							</div>
						</div>
					</div>

					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-useradmin" lay-submit
							lay-filter="LAY-user-front-search">
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
					<table class="layui-table"
						lay-data="{height:500,url:'getallappuser.do', page:true, id:'idTest'}"
						lay-filter="demo">
						<thead>
							<tr>
								<th lay-data="{type:'checkbox', fixed:'left'}"></th>
								<th lay-data="{field:'id', sort: true, fixed: true}">ID</th>
								<th lay-data="{field:'username'}">用户名</th>
								<!-- <th lay-data="{field:'headimg', width:120}">头像</th> -->
								<th lay-data="{field:'sex', sort: true}">性别</th>
								<th lay-data="{field:'sfzzh'}">身份证号</th>
								<th lay-data="{field:'phone'}">手机号</th>
								<th lay-data="{field:'mail'}">电子邮箱</th>
								<th lay-data="{field:'qqzh'}">QQ帐号</th>
								<th lay-data="{field:'wxzh'}">微信帐号</th>
								<th lay-data="{field:'wbzh'}">微博帐号</th>
								<th
									lay-data="{fixed: 'right', width:278, align:'center', toolbar: '#barDemo'}">操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

</script>
<script type="text/javascript" src="layui/layui.all.js"></script>
<script>
    var editflag;
	function add(){
	}
	
	$("#layui-layer-close1").click(function(){
	$("#layui-layer1").css("display","none");
	});
	$("#layui-layer-close4").click(function(){
	$("#layui-layer4").css("display","none");
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
			url:"deleteappuser.do",
			type:"post",
			dataType:"json",
			data:{flag:editflag},
			success:function(data){

			}
		});
				table.reload('idTest', {
				  url:'getallappuser.do'
				  ,where: {
				  } //设定异步数据接口的额外参数
				  //,height: 300
				});
   /*    layer.confirm('真的删除行么', function(index){
        obj.del();
        layer.close(index);
      }); */
    } else if(obj.event === 'edit'){
   /*   $("#layui-layer-iframe1").attr("src","appuseredit.do"); */
     $("#flag").val($(this).parent().parent().parent().find("td").eq(1).find("div").html());
     $("#layui-layer1").css("display","block");
     $("#layui-layer-iframe1").attr("src","appuseredit.do");
    }else if(obj.event === 'lock'){
     $(this).parent().find(".unlock").addClass("layui-btn-disabled");
     $(this).parent().find(".unlock").removeClass("layui-btn-normal");
     $(this).parent().find(".unlock").removeClass("unlock");
     $(this).parent().find(".lock").removeClass("layui-btn-disabled");
     $(this).parent().find(".lock").addClass("layui-btn-normal");
     $(this).parent().find(".lock").addClass("unlock");
     $(this).parent().find(".layui-btn-disabled").addClass("lock");
     $(this).parent().find(".unlock").removeClass("lock");
    }else if(obj.event === 'unlock'){
     $(this).parent().find(".unlock").addClass("layui-btn-disabled");
     $(this).parent().find(".unlock").removeClass("layui-btn-normal");
     $(this).parent().find(".unlock").removeClass("unlock");
     $(this).parent().find(".lock").removeClass("layui-btn-disabled");
     $(this).parent().find(".lock").addClass("layui-btn-normal");
     $(this).parent().find(".lock").addClass("unlock");
     $(this).parent().find(".layui-btn-disabled").addClass("lock");
     $(this).parent().find(".unlock").removeClass("lock");
    }
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
			url:"deleteselectappuser.do",
			type:"post",
			dataType:"json",
			async: false,
			data:{"flag":data[i].id},
			success:function(data){
				
			}
		});
		table.reload('idTest', {
				  url:'getallappuser.do'
				  ,where: {
				  } //设定异步数据接口的额外参数
				  //,height: 300
				});
        }
        
     	
      }
    ,getCheckLength: function(){ //获取选中数目
       $("#layui-layer-iframe4").attr("src","appuseradd.do");
      $("#layui-layer4").css("display","block");
     
    }
  };
  

  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  $(".layui-btn.layuiadmin-btn-useradmin").click(function(){
	 var phone=$("#telphone1").val();
	 var username=$("#username1").val();
	 var sex=$("#selectoption option:selected").text();
/* 	 alert(condition1+"----"+condition2); */
	      //执行重载
	         table.reload('idTest', {
					  url:'getselectappuser.do'
					  ,where: {
					  phone:phone,
					  username:username,
					  sex:encodeURI(sex)
					  } //设定异步数据接口的额外参数
					  //,height: 300
					});
	 }); 
});
function reloadtable(){
layui.use('table', function(){
  var table = layui.table;
	table.reload('idTest', {
				  url:'getallappuser.do'
				  ,where: {
				  } //设定异步数据接口的额外参数
				  //,height: 300
				});
				});
}
  </script>
</html>