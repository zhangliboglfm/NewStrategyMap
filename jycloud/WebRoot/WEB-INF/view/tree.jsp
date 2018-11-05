<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>菜单树</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/miapsoft.tree.css" type="text/css"></link>
  <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
  <script type="text/javascript" src="js/miapsoft.tree.js"></script>
 <style type="text/css">
 	.class2{
 		background-color: green;
 	}
 </style>
  <script type="text/javascript">
  	$(function(){
  		var datascource = [
	        { 'name': 'Bo MiaoBo MiaoBo MiaoBo Miao', 'title': 'department' },
	        { 'name': 'Su Miao', 'title': 'department manager',
	          'children': [
	            { 'name': 'Tie Hua', 'title': 'senior engineer', 'children': [
	            { 'name': 'Tie Hua', 'title': 'senior engineer', },
	            { 'name': 'Hei Hei', 'title': 'senior engineer',
	              'children': [
	                { 'name': 'Pang Pang', 'title': 'engineer' },
	                { 'name': 'Xiang Xiang', 'title': 'UE engineer' }
	              ]
	            }
	          ] },
	            { 'name': 'Hei Hei', 'title': 'senior engineer',
	              'children': [
	                { 'name': 'Pang Pang', 'title': 'engineer' },
	                { 'name': 'Xiang Xiang', 'title': 'UE engineer' }
	              ]
	            }
	          ]
	        },
	        { 'name': 'Yu Jie', 'title': 'department manager' },
	        { 'name': 'Yu Li', 'title': 'department manager' },
	        { 'name': 'Hong Miao', 'title': 'department manager' },
	        { 'name': 'Yu Wei', 'title': 'department manager' },
	        { 'name': 'Chun Miao', 'title': 'department manager' },
	        { 'name': 'Yu Tie', 'title': 'department manager' }
	      ];
	   	var option = {};	   	
	   	var mTree = $.fn.mTree.init($("#demo"),option,datascource);
  	});
  </script>
  </head>
  
  <body>
    <div id="demo" style="height: 500px;">
    </div>
  </body>
</html>
