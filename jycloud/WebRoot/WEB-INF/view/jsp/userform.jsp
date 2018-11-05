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
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/js/jquery.ztree.all.js"></script><link rel="stylesheet" href="css/css/zTreeStyle/zTreeStyle.css" type="text/css"></link><script type="text/javascript" src="js/js/jquery.ztree.core.js"></script><script type="text/javascript" src="js/js/jquery.ztree.excheck.js"></script></head>
  
  <body>
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>

  </body>
<script type="text/javascript">
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"can check 1", open:true},
			{ id:11, pId:1, name:"can check 1-1", open:true},
			{ id:111, pId:11, name:"can check 1-1-1"},
			{ id:112, pId:11, name:"can check 1-1-2"},
			{ id:12, pId:1, name:"can check 1-2", open:true},
			{ id:121, pId:12, name:"can check 1-2-1"},
			{ id:122, pId:12, name:"can check 1-2-2"},
			{ id:2, pId:0, name:"can check 2", checked:true, open:true},
			{ id:21, pId:2, name:"can check 2-1"},
			{ id:22, pId:2, name:"can check 2-2", open:true},
			{ id:221, pId:22, name:"can check 2-2-1", checked:true},
			{ id:222, pId:22, name:"can check 2-2-2"},
			{ id:23, pId:2, name:"0 check 2-3"}
		];

		var code;

		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			py = $("#py").attr("checked")? "p":"",
			sy = $("#sy").attr("checked")? "s":"",
			pn = $("#pn").attr("checked")? "p":"",
			sn = $("#sn").attr("checked")? "s":"",
			type = { "Y":py + sy, "N":pn + sn};
			zTree.setting.check.chkboxType = type;
			showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
		}
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			setCheck();
			$("#py").bind("change", setCheck);
			$("#sy").bind("change", setCheck);
			$("#pn").bind("change", setCheck);
			$("#sn").bind("change", setCheck);
		});
		//-->

</script>
</html>