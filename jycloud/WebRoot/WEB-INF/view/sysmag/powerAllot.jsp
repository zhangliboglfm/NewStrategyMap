<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
  <title>权限分配</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  	
  	<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
	<link rel="stylesheet" href="css/login.css" type="text/css"></link>
	<link rel="stylesheet" href="css/admin.css" type="text/css"></link>
	<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
	<script type="text/javascript" src="layui/layui.all.js"></script>
	<script type="text/javascript" src="layui/lay/modules/element.js"></script>
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="layui/lay/modules/form.js"></script>
	<style type="text/css">
	</style>
</head>
<body>
	<div class="layui-card-body">
 		<table id="table1" class="layui-table" lay-filter="table1"></table>
	    <div class="layui-btn-group demoTable">
	 	  <button class="layui-btn" data-type="getCheckData">确认分配</button>
	    </div>
      </div>
</body>
<script type="text/javascript">
	var roleId="<%=request.getAttribute("roleId")%>";
	$(function () {
		createTree();
	});
	function createTree(){
		//新加组件treetable
		layui.config({
		    base: 'module/'
		}).extend({
		    treetable:'treetable-lay/treetable'
		}).use(['treetable'], function () {
		    var treetable = layui.treetable;
		});
		layui.use(['treetable'], function () {
		    var treetable = layui.treetable;
		    // 渲染表格
		    treetable.render({
		    	id:'idTest',
		        treeColIndex: 1,          // 
		        treeSpid:"M_000000",             // 最上层的模块ID
		        treeIdName: 'moduleId',       // 结构树的子模块ID
		        treePidName: 'pMoudleId',     // 结构树的父模块ID
		        treeDefaultClose: true,   // 数据树是否展开
		        treeLinkage: true,        // treetable新增参数
		        elem: '#table1',
		        cols: [[
		        	{type:'checkbox'}, 
		        	{field: 'moduleId', title: '模块ID'},
		        	{field: 'moduleName', title: '模块名称'},
		        	{field: 'pMoudleId', title: '父模块ID'},
		            {field: 'moduleLvl', title: '模块级别'}
		        ]],
		        url:"createMTree.do"
		        ,where:{
		        	roleId : roleId
		        }
		    });
		});
	}
	layui.use('table', function(){
	  var table = layui.table;
	  var $ = layui.$, active = {
		    getCheckData: function(){ 
			   var checkStatus = table.checkStatus('idTest'),
			   data = checkStatus.data;
			   var moudleList=[];
			   for(var a=0;a<data.length;a++){
			      moudleList.push(data[a].moduleId);
			   }
		       $.ajax({
					url : "upRMRelate.do",
					type : "post",
					dataType : "text",
					async : false,
					data : {
						roleId : roleId,
						moudleId : JSON.stringify(moudleList)
					},
					success : function(data) {
						if (data==1) {
							layer.msg("赋权成功");
						}
					},
					error : function () {
						layer.msg("赋权失败,请联系管理员");
					}
				});
		    }
	  };
	  $('.demoTable .layui-btn').on('click', function(){
	    var type = $(this).data('type');
	    active[type] ? active[type].call(this) : '';
	  });
	})
</script>
</html>