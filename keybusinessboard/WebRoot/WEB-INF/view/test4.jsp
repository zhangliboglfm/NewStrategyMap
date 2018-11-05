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
<!-- 	<link rel="stylesheet" href="css/miapsoft.orgchart.css" type="text/css"></link> -->
  <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
  <script type="text/javascript" src="js/es5-shim.js"></script>
  <script type="text/javascript" src="js/jquery.mousewheel.js"></script>
  <script type="text/javascript" src="js/miapsoft.orgchart.l2r.js"></script>
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
  		var datascource = {
	      'name': '和包',
	      'title': 'general manager',
	      'children': [
	        { 'name': '用户', 'title': 'department',children:[
	        	{ 'name': '使用', 'title': 'department manager' ,
	        	children:[
		        	{ 'name': '和包到达用户数', 'title': '（100户，同比23%，环比25%）',leaf:true },
			        { 'name': '现场交易', 'title': '（100户，同比23%，环比25%）'} 
		        ]},
		        { 'name': '活跃', 'title': 'department manager' ,
		        children:[
		        	{ 'name': '和包活跃用户数', 'title': '（100户，同比23%，环比25%）' },
			        { 'name': '和包刷卡用户数', 'title': '（100户，同比23%，环比25%）'} 
		        ]},
		        { 'name': '新增', 'title': 'department manager' }
	        ] },
	        { 'name': '交易', 'title': 'department manager' ,children:[
	        	{ 'name': '远程交易', 'title': 'department manager' , children:[
		        	{ 'name': '笔数', 'title': '（100户，同比23%，环比25%）' },
			        { 'name': '金额', 'title': '（100户，同比23%，环比25%）'} 
		        ]},
		        { 'name': '现场交易', 'title': 'department manager' } 
	        ] },
	        { 'name': '充值', 'title': 'department manager' ,children:[
	        	{ 'name': '充值', 'title': 'department manager' },
		        { 'name': '充话费', 'title': 'department manager' }
	        ] },
	        { 'name': '账务', 'title': 'department manager' ,children:[
	        	{ 'name': '提现', 'title': 'department manager' },
		        { 'name': '转账', 'title': 'department manager' }
	        ] }
	      ]
	    };
	    $("#demo").orgchart({
	    	data:datascource,
	    	/* nodeContent: 'title', */
	    	nodeTitle: function(data){//可以是函数和字符串
	    		var html="";
	    		if(data.leaf){
		    		html = '<div  >'+data.name+data.title+'<label onclick="aa(this)" style="color:red;cursor:pointer;">详情</label></div>';
	    		}else{
	    			html = data.name;
	    		}
	    		return html;
	    	},
	    	depth:10,//展开几层
	    	pan: false,//支持平移IE8效果不好
	    	zoom: false,//支持缩放IE8效果不好
	    	showIcon:false,
	    	click: function(event){//绑定click
	    		alert($(event.target).text());
	    	}
	    });
  	});
	    function aa(a){
	    	alert($(a).html());
	    }
  </script>
  <style type="text/css">
  	.orgchart{
  		width: 100%;
  		margin: 0 auto;
  	}
  	.orgchart table{
  		border-collapse: separate;
  		border-spacing: 0px;
  	}
  	.orgchart table td{
  		text-align: center;
  	}
  	.orgchart td.bottom{
  		border-bottom: 1px dashed #4CBE59;
  		width: 40px;
  	}
  	.orgchart td.left{
  		border-left: 2px solid #4CBE59;
  		width: 40px;
  	}
  	.orgchart td.top{
  		border-top: 1px dashed #4CBE59;
  		width: 40px;
  	}
  	.orgchart td .down{
  		width: 40px;
  		height: 0px;
  		border: 1px dashed #4CBE59;
  	}
  	.orgchart .node{
  		/* border: 1px solid #4CBE59; */
  		text-align: center;
  		/* width: 130px; */
  		position: relative;
  	}
  	.orgchart .hidden{
  		display: none;
  	}
  	.orgchart .edge{
  		width: 20px;
		height: 20px;
		background-color: #ffffff;
  		position: absolute;
  		bottom: -15px;
  		right: -10px;
  		cursor: pointer;
  	}
  </style>
  </head>
  
  <body>
    <div id="demo" style="height: 500px;width: 100%;">
    </div>
  </body>
</html>
