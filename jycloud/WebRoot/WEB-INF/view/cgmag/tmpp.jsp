<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tmpp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<div class="searchdiv">	
			<div class="layui-inline">
		      <label class="layui-form-label">审核状态</label>
		      <div class="layui-input-inline">
		      	<div class="layui-form">
		      		<select name="modules" lay-verify="required" lay-search="">
			          <option value="1">未审核</option>
			          <option value="5">已审核</option>
			        </select>
		      	</div>
		      </div>
		    </div>
			<div class="layui-inline">
				<label class="layui-form-label">书法家名称</label>
				<div class="layui-input-inline">
					<input type="text" id="serchCalligName" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<div class="layui-input-inline">
					<button class="layui-btn" onclick="searchCallig()" style="margin-top: 1px !important;">检索</button>
				</div>
			</div>
			<div class="layui-inline sortdiv">
				<div id="divBirthDaySortBnt" class="sortbnt" type="birthday" sort="0" onclick="sortBntBirthdayClick(this)"><i class="iconfont">&#xe63c;</i>出生日期</div>
				<div id="divChangeTimeSortBnt" class="sortbnt" type="changetime" sort="0" onclick="sortBntChangeTimeClick(this)"><i class="iconfont">&#xe63c;</i>变更时间</div>
				<div id="divWorksNumSortBnt" class="sortbnt" type="worksnum" sort="0" onclick="sortBntWorksNumClick(this)"><i class="iconfont">&#xe63c;</i>作品数量</div>
			</div>
			<hr class="layui-bg-green">
		</div>
		<div style="height: 10px;"></div>
		<div id="calliglist" class="layui-row layui-col-space30"></div>
		<div style="text-align: right;"><div id="footpage" style="background: #FFF;"></div></div>
  </body>
</html>
