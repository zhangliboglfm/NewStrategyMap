<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>My JSP 'test1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/miapsoft.orgchart.css" type="text/css"></link>
  <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
  <script type="text/javascript" src="js/es5-shim.js"></script>
  <script type="text/javascript" src="js/jquery.mousewheel.js"></script>
  <script type="text/javascript" src="js/miapsoft.orgchart.js"></script>
 <style type="text/css">
 	.class2{
 		background-color: green;
 	}
 </style>
  <script type="text/javascript">
  	$(function(){
  		var datascource = {
	      'name': 'Lao Lao',
	      'title': 'general manager',
	      'children': [
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
	      ]
	    };
	    $("#demo").orgchart({
	    	data:datascource,
	    	nodeContent: 'title',
	    	nodeContent: function(data){//可以是函数和字符串
	    		var html = '<div style="color:red;cursor:pointer;" onclick="aa(this)">'+data.name+'</div><div style="color:red">'+data.title+'</div>';
	    		return html;
	    	},
	    	depth:3,//展开几层
	    	pan: true,//支持平移IE8效果不好
	    	zoom: true,//支持缩放IE8效果不好
	    	click: function(event){//绑定click
	    		//alert($(event.target).text());
	    	}
	    });
	    $.ajax({
	    	url:"test3.do",
	    	type:"post",
	    	data:{
	    		userId: "jyyr_lj"
	    	},
	    	dataType:"json",
	    	success:function(data){
	    		if(data.success==false){
	    			alert(data.errorMsg);
	    		}else{
	    			alert(data.length);
	    		}
	    	}
	    });
  	});
	    function aa(a){
	    	alert($(a).html());
	    }
  </script>
  </head>
  
  <body>
    <div id="demo" style="height: 500px;">
    </div>
    <div id="demo2" style="height: 500px;">
    </div>
  </body>
</html>
