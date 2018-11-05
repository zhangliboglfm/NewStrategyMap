<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
 
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="layui/lay/modules/element.js"></script>
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
	<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
	<script type="text/javascript" src="js/jquery1.10.3-ui.js"></script>
	<script type="text/javascript" src="js/picmanage.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/picmanage.css"></link>
  	<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
  	<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
  	<style type="text/css">
  		.layui-inline{
			margin-top: 4px;
			float: left;
		}
		
		button{
			margin-top: 1px !important;
		}
		
		input:not([type="checkbox" i]){
			height: 32px !important;
			/* margin-top: 2px !important; */
			padding-left: 10px;
			
			border-color: #e6e6e6 !important;
			border-width: 1px;
     		border-style: solid ;
    
    		border-radius: 2px;
    
    		border-color: #e6e6e6;
			
		}
  	</style>
  </head>
  <body>
    <div id="picmanage" style="height:100%;width:100%;">
    <!-- <div id="picmanage" style="height:575px;width:1165px;"> -->
    	<div id="background"></div>
    	<div id="state">
    		<div class='state2' style="width:100%;height:5%;border-bottom: 1px solid">
    			<div class="state3" style="height:100%;width:4%">样片</div>
    			<div class="state3" style="text-align: right;height:100%;width:95%"><button  class="layui-btn layui-btn-xs layui-btn-normal close">关闭</button></div>
    		</div>
    		<div id="statepic" style="width:100%;height:95%;text-align: center">
    			<!-- <div class="state3" style="height:100%;width:60%;margin-top: 20px;"><img src="SpiderImg/TUCHONG/PROVIA 100F/31093966.jpg"></div>
    			<div class="state3" style="height:100%;width:39%;border-left: 1px solid;">
    				<div style="height:40%;width:100%;">
	    				<table>
	    					<tr><td>名称:</td><td><input type="text" value="集市"/></td></tr>
	    					<tr><td>大小:</td><td>1234</td></tr>
	    					<tr><td>ISO:</td><td>MM</td></tr>
	    					<tr><td>光圈:</td><td>暂无</td></tr>
	    					<tr><td>快门:</td><td>暂无</td></tr>
	    					<tr><td>相机型号:</td><td><input type="text" value=""/></td></tr>
	    					<tr><td>胶片型号:</td><td><input type="text" value=""/></td></tr>
	    					<tr><td>镜头类型:</td><td><input type="text" value=""/></td></tr>
	    					<tr><td>回退原因:</td><td>无</td></tr>
	    				</table>
    				</div>
    				<div id="labelhide">
		    			<label class="photolabel">标签:</label><i class="layui-icon" style="color:#009688;cursor: pointer;font-size: 18px;" onclick="addTag(id)">&#xe61f;</i>
		    			<div class="phototags" id="phototags">
		    				<div class="phototag"><span>风景</span><i class="layui-icon close-icon" onclick="deleteTag(this)">&#x1007;</i></div>
		    			</div>
		    		</div>
    				<div style="text-align: center;height:40%;width:100%;">
    					<div class="state4"><button class="layui-btn layui-btn-sm layui-btn-normal">保存</button></div>
    					<div class="state4"><button class="layui-btn layui-btn-sm layui-btn-normal">保存并筛选</button></div>
    				</div>
    			</div> -->
    		</div>
    	</div>
    	<div id="more">
    		<div style="width:100%;height:6%;border-bottom: 1px solid">
    			<div id="titlediv" class="state3" style="height:100%;width:50%"></div>
    			<div class="state3" style="text-align: right;height:100%;width:50%"><button class="layui-btn layui-btn-xs layui-btn-normal close">关闭</button></div>
    		</div>
    		<div style="width:100%;height:14%;">
    			<div style="position: absolute;left:30%;top:10%"><input id="moreinput" value=""></div>
    			<div style="position: absolute;left:61%;top:10%"><button id="cscreen2" class="layui-btn layui-btn-xs layui-btn-normal">查询</button></div>
    		</div>
    		<div style="width:80%;height:60%;margin-left: 50px;">
    			<ul id="moreul">
    				<li><input  type="checkbox" value="富士" name="cameracheck">富士</li>
    			</ul>
    		</div>
    		<div style="width:100%;height:20%;text-align: center;">
    			<button id="enter" class="layui-btn layui-btn-xs layui-btn-normal">确认</button>
    		</div>
    	</div>
    	<div id="reason">
    		<div style="width:100%;height:8%;border-bottom: 1px solid">
    			<div id="titlediv" class="state3" style="height:100%;width:50%"></div>
    			<div class="state3" style="text-align: right;height:100%;width:50%"><button class="layui-btn layui-btn-xs layui-btn-normal close">关闭</button></div>
    		</div>
    		<div style="width:80%;height:60%;margin-left: 50px;">
    			<ul id="reasonul">
    				<li><input  type="checkbox" value="尺寸不匹配" name="cameracheck">尺寸不匹配</li>
    				<li><input  type="checkbox" value="信息较少" name="cameracheck">信息较少</li>
    				<li><input  type="checkbox" value="风格不匹配" name="cameracheck">风格不匹配</li>
    				<li><input  type="checkbox" value="无特点" name="cameracheck">无特点</li>
    				<li><input  type="checkbox" value="作者不明" name="cameracheck">作者不明</li>
    				<li><input  type="checkbox" value="无意义" name="cameracheck">无意义</li>
    			</ul>
    		</div>
    		<div style="width:100%;height:20%;text-align: center;">自定义原因:<input id="reasonPut" type="text"></div>
    		<div style="width:100%;height:20%;text-align: center;">
    			<button id="enter2" class="layui-btn layui-btn-xs layui-btn-normal">确认</button>
    		</div>
    	</div>
    	<div class="screen">
    		<div class="screen2">
    			<div class="layui-inline">
		      		<label class="layui-form-label">爬取日期 :</label>
		      		<div class="layui-input-inline">
		        		<input type="text" name="date" id="test1" lay-verify="date" placeholder="全部" autocomplete="off" class="layui-input" style="margin-top: 2px;">
		      		</div>
		    	</div>
    			
    			<div class="layui-inline">
		      		<label class="layui-form-label">关键字 :</label>
		      		<div class="layui-input-inline">
		        		<input type="text" id="crux" autocomplete="off" class="layui-input"  style="margin-top: 2px;">
		      		</div>
		    	</div>
    			
    			<div class="screen3" style="width:40%;">
    				<!-- <div class="screen4" style="margin-right:3px;margin-top: 6px">图片类型:</div>
    				<select id="select" class="screen4" style="margin-top:6px;">
					  <option value ="0">全部</option>
					  <option value ="1">新增</option>
					  <option value="2">审核回退</option>
					</select> -->
    			</div>
    			<div class="screen3" style="width:10%;">
    				<div class="screen4"><button class="layui-btn layui-btn-sm layui-btn-normal" onclick="recovery()">恢复默认条件</button></div>
    			</div>
    		</div>
    		<div class="screen2">
    			<div class="layui-inline">
		      		<label class="layui-form-label">颜色类型 :</label>
		      		<div class="layui-input-inline">
		        		<form class='layui-form' action='' lay-filter='example'>
			    			<input id=r1 type="radio"  name="color" title="全部" checked="checked">
			    			<input id=r2 type="radio"  name="color" title="彩色" >
			    			<input id=r3 type="radio"  name="color" title="黑白" >
			    		</form>
		      		</div>
		    	</div>
    			
    		</div>
    		<div class="screen2">
    			<div class="layui-inline">
		      		<label class="layui-form-label">胶片型号 :</label>
		      		<div class="layui-input-inline">
		        		<form class='layui-form' action='' lay-filter='example'>
		    				<input id=f1 type="radio" name="film" title='全部' checked="checked">
		    				<input id=f2 type="radio" name="film" title='自定义 :'>
		    			</form>
		      		</div>
		      		<div class="layui-input-inline">
		      			<input id="film"/>
		      		</div>
		      		<div class="layui-input-inline">
		      			<button id="filmb" class="layui-btn layui-btn-sm layui-btn-normal" onclick="more(id)">更多</button>
		      		</div>
		    	</div>
    			
    		</div>
    		<div class="screen2">
    			<div class="layui-inline">
		      		<label class="layui-form-label">照片尺寸 :</label>
		      		<div class="layui-input-inline">
		        		<form class='layui-form' action='' lay-filter='example'>
	    					<input id=s1 type="radio"  name="size" title='全部' checked="checked">
	    					<input id=s2 type="radio"  name="size" title='自定义 :'>
    					</form>
		      		</div>
		      		<div class="layui-input-inline" style="margin-top: 3px;">
		      			短边长: <input class="size" id="long" value="" />
		      		</div>
		      		
		    	</div>
		    	<div class="layui-btn layui-btn-primary layui-btn-sm" style="margin-top:7.5px; width:7%;padding:0 0;display: block;float: left;" onclick="switchbt()">
		    			<div id="less" class="bsbutton" style="background-color: #009688;">≤</div>
		    			<div id="greater" class="bsbutton">≥</div>
	    			</div>
    		</div>
    		
    		<div class="screen2">
    			<div class="layui-inline">
		      		<label class="layui-form-label">标签类型 :</label>
		      		<div id="Label" class="screen4" style="margin-top: 3px">
	    				<div class="screen4"><input type="checkbox" value="风景" name="label">风景</div>
	    				<div class="screen4"><input type="checkbox" value="黑白" name="label">黑白</div>
	    				<div class="screen4"><input type="checkbox" value="人文" name="label">人文</div>
    				</div>
		      		
		    	</div>
    		</div>
    		<div class="screen2">
    			<div class="layui-inline">
		      		<label class="layui-form-label">相机型号 :</label>
		      		<div class="layui-input-inline">
		        		<form class='layui-form' action='' lay-filter='example'>
	    					<input id=c1 type="radio" name="camera" checked="checked" title='全部'>
	    					<input id=c2 type="radio" name="camera" title='自定义 :'>
	    				</form>
		      		</div>
		      		<div class="layui-input-inline">
		      			<input id="camera"/>
		      		</div>
		      		<div class="layui-input-inline">
		      			<button id="camerab" class="layui-btn layui-btn-sm layui-btn-normal" onclick="more(id)">更多</button>
		      		</div>
		      		
		    	</div>
    		</div>
    		<div class="screen2">
    			<div class="layui-inline">
		      		<label class="layui-form-label">镜头型号 :</label>
		      		<div class="layui-input-inline">
		        		<form class='layui-form' action='' lay-filter='example'>
	    					<input id=le1 type="radio" name="lens" checked="checked" title='全部'>
	    					<input id=le2 type="radio" name="lens" title='自定义 :'>
	    				</form>
		      		</div>
		      		<div class="layui-input-inline">
		      			<input id="lens"/>
		      		</div>
		      		<div class="layui-input-inline">
		      			<button id="lensb" class="layui-btn layui-btn-sm layui-btn-normal" onclick="more(id)">更多</button>
		      		</div>
		      		
		    	</div>
    		</div>
    		<div class="screens" style="width:100%;height:14%;">
    			<div class="screen3" style="width:100%;height:100%;text-align:center">
    				<div class="screen4" style="margin-left:0px;width:96%">
    					<button id="cscreen" class="layui-btn layui-btn-sm layui-btn-normal">查询</button>
    					<div id="screeninfo" style="width:100%;display: none;"></div>
    				</div>
    				<div class="screen4" style="width:4%;background:rgba(0,0,0,0.2);margin-left: 0">
    					<button id="up" class="screen4bt"><i class="layui-icon layui-icon-up" style="font-size: 20px; color: black;"></i></button>
    					<button id="down" class="screen4bt" style="display: none;"><i class="layui-icon layui-icon-down" style="font-size: 20px; color: black;"></i></button>
    				</div>
    			</div>
    		</div>
    	</div>
    	<div class="content">
    		<div class="content2" id="ct1" style="width:100%;height:12%;margin-top:3px;border-bottom: 1px solid black;">
    			<div id="pagenums" class="content3" style="height:100%;width:10%;"></div>
    			<div class="content3" style="height:100%;width:76%;">
    				<button id="pattern1" class="layui-btn layui-btn-sm layui-btn-normal pattern" style="width:80.6px;">编辑模式</button>
    				<button id="pattern2" class="layui-btn layui-btn-sm layui-btn-normal pattern" style="display: none;margin-left: 0px;width:80.6px;">筛选模式</button>
    			</div>
    			<div class="content3" style="height:100%;width:9%;">
    			</div>
    			<div class="content3" id="all" style="height:100%;width:5%;">
    				<input id=cc type="checkbox" value="0" name="check">全选
    			</div>
    		</div>
    		<div class="content2" id="cts" style="width:100%;height:75%;border-bottom: 1px solid black;overflow: hidden;display: none;">
    			<div id="picsee" style="width:15%;height:100%;float: left;border-right: 1px solid;overflow-y:auto;">
    				<ul id="imgul2">
    				</ul>
    			</div>
    			<div style="width:50%;height:100%;float: left;border-right: 1px solid;">
    				<div id="imgdiv"></div>
    			</div>
    			<div id="imginfo">
    				<!-- <table>
    					<tr><td>名称:</td><td><input type="text" value="集市"/></td></tr>
    					<tr class="ctstr"><td>大小:</td><td>1234</td></tr>
    					<tr class="ctstr"><td>ISO:</td><td>MM</td></tr>
    					<tr class="ctstr"><td>光圈:</td><td>暂无</td></tr>
    					<tr class="ctstr"><td>快门:</td><td>暂无</td></tr>
    					<tr><td>相机型号:</td><td><input type="text" value=""/></td></tr>
    					<tr><td>胶片型号:</td><td><input type="text" value=""/></td></tr>
    					<tr><td>镜头类型:</td><td><input type="text" value=""/></td></tr>
    					<tr><td>回退原因:</td><td>无</td></tr>
    				</table>
    				<div style="text-align: center;height:40%;width:100%;margin-top: 25px;">
    					<div class="state4"><button class="layui-btn layui-btn-sm layui-btn-normal savepic">保存</button></div>
    					<div class="state4"><button class="layui-btn layui-btn-sm layui-btn-normal deletepic">删除</button></div>
    					<div><button class="layui-btn layui-btn-sm layui-btn-normal screenpic">保存并筛选</button></div>
    				</div> -->
    			</div>
    		</div>
    		<div class="content2" id="ct2" style="width:100%;height:75%;border-bottom: 1px solid black;overflow-y: scroll;">
    			<div class="bottom">
					<div class="imgcontent">
						<ul id ="imgul">
							<!-- <li>
								<input type="checkbox" value="0" name="pic">
								<div  onclick="check(this)"><img src="thumbnail/SpiderImg/TUCHONG/PROVIA 100F/20493479.jpg"></div>
								<div style="text-align: center;height:40%">
									<table>
										<tr><td>名称:</td><td>集市</td></tr>
										<tr><td>相机型号:</td><td>暂无</td></tr>
										<tr><td>胶片型号:</td><td>PROVIA 100F</td></tr>
										<tr><td>ISO:</td><td>暂无</td></tr>
									</table>
								</div>
								<div style="height:10%;">
						    		<button class="layui-btn layui-btn-xs layui-btn-normal" onclick="see(this)">查看</button>
						    	</div>
							</li>
							<li>
								<input type="checkbox" value="1" name="pic">
								<div onclick="check(this)"><img src="thumbnail/SpiderImg/TUCHONG/PROVIA 100F/31093966.jpg"></div>
								<div style="text-align: center;height:40%">
									<table>
										<tr><td>名称:</td><td>集市</td></tr>
										<tr><td>相机型号:</td><td>暂无</td></tr>
										<tr><td>胶片型号:</td><td>PROVIA 100F</td></tr>
										<tr><td>ISO:</td><td>暂无</td></tr>
									</table>
								</div>
								<div style="height:10%;">
						    		<button class="layui-btn layui-btn-xs layui-btn-normal" onclick="see(this)">查看</button>
						    	</div>
							</li>
							<li>
								<input type="checkbox" value="" name="pic">
								<div onclick="check(this)"><img src="thumbnail/SpiderImg/TUCHONG/PROVIA 100F/31093967.jpg"></div>
								<div style="text-align: center;height:40%">
									<table>
										<tr><td>名称:</td><td>集市</td></tr>
										<tr><td>相机型号:</td><td>暂无</td></tr>
										<tr><td>胶片型号:</td><td>PROVIA 100F</td></tr>
										<tr><td>ISO:</td><td>暂无</td></tr>
									</table>
								</div>
								<div style="height:10%;">
						    		<button class="layui-btn layui-btn-xs layui-btn-normal">查看</button>
						    	</div>
							</li>
							<li>
								<input type="checkbox" value="" name="pic">
								<div onclick="check(this)"><img src="thumbnail/SpiderImg/TUCHONG/PROVIA 100F/31093970.jpg"></div>
								<div style="text-align: center;height:40%">
									<table>
										<tr><td>名称:</td><td>集市</td></tr>
										<tr><td>相机型号:</td><td>暂无</td></tr>
										<tr><td>胶片型号:</td><td>PROVIA 100F</td></tr>
										<tr><td>ISO:</td><td>暂无</td></tr>
									</table>
								</div>
								<div style="height:10%;">
						    		<button class="layui-btn layui-btn-xs layui-btn-normal">查看</button>
						    	</div>
							</li>
							<li>
								<input type="checkbox" value="" name="pic">
								<div onclick="check(this)"><img src="thumbnail/SpiderImg/TUCHONG/PROVIA 100F/20493480.jpg"></div>
								<div style="text-align: center;height:40%">
									<table>
										<tr><td>名称:</td><td>集市</td></tr>
										<tr><td>相机型号:</td><td>暂无</td></tr>
										<tr><td>胶片型号:</td><td>PROVIA 100F</td></tr>
										<tr><td>ISO:</td><td>暂无</td></tr>
									</table>
								</div>
								<div style="height:10%;">
						    		<button class="layui-btn layui-btn-xs layui-btn-normal">查看</button>
						    	</div>
							</li>
							<li>
								<input type="checkbox" value="" name="pic">
								<div onclick="check(this)"><img src="thumbnail/SpiderImg/TUCHONG/PROVIA 100F/31093960.jpg"></div>
								<div style="text-align: center;height:40%">
									<table>
										<tr><td>名称:</td><td>集市</td></tr>
										<tr><td>相机型号:</td><td>暂无</td></tr>
										<tr><td>胶片型号:</td><td>PROVIA 100F</td></tr>
										<tr><td>ISO:</td><td>暂无</td></tr>
									</table>
								</div>
								<div style="height:10%;">
						    		<button class="layui-btn layui-btn-xs layui-btn-normal">查看</button> 
						    	</div>
							</li>-->
						</ul>
					</div>
				</div>
    		</div>
    		<div class="content2" id="ct3" style="width:100%;height:13%;">
    			<div class="content3" style="width:100%;height:100%">
    				<div class="content4" id="demo2" style="width:60%;text-align: right;"></div>
    				<div class="content4 buff" style="width:20%;text-align: right;"><button class="layui-btn layui-btn-sm layui-btn-normal" onclick="passed()">通过</button></div>
    				<div class="content4 buff" style="width:20%;text-align: center;"><button class="layui-btn layui-btn-sm layui-btn-normal" onclick="rejects()">驳回</button></div>
    			</div>
    		</div>
    	</div>
    </div>
  </body>
  <script type="text/javascript">
  	//图片状态
  	var state1=3;
  	var state2=3;
  	//判断尺寸筛选方式
  	var mores="";
  	var lwsize=1;//判断尺寸的范围
  	var laydate,laypage,layer,element,upload,form;//插件
  	var pageNum=8;//每页条数
  	var pageSize=1;//页数
  	var checks=[];//已选中的图片
  	var regressesId="";//批量回退时添加原因的图片id
  	$(function(){
  		getLabel();
  		getPages(getNums(state1,state2));
  		getPic(8,1,lwsize,state1,state2);
  	});
  	function roll(){
  		$(".imginfodiv,#ct2,#picsee").niceScroll({
			 autohidemode: true,
			 cursorwidth: "5px", // 滚动条的宽度，单位：便素
			 //cursorborder: "1px solid #fff", // CSS方式定义滚动条边框
			 cursorcolor: "#60B878"
		});
  	}
  	//获取翻页
  	function getPages(nums){
  		layui.use(['laypage', 'layer','laydate','upload','form'], function(){
  			 laydate = layui.laydate;
  			 laypage = layui.laypage;
  			 layer = layui.layer;
  			 upload=layui.upload;
  			 form=layui.form;
  			//自定义样式
  			 laypage.render({
			    elem: 'demo2'
			    ,count: nums
			    ,theme: '#1E9FFF'
			    ,limit:8,
			    jump: function(obj, first){
			        if(!first){
			          layer.msg('第 '+ obj.curr +' 页');
			          getPic(8,obj.curr,lwsize,state1,state2);
			          pageSize=obj.curr;
			        }
			     }
			  });
			  //常规用法
			  laydate.render({
			    elem: '#test1'
			  });
  		});
  	}
  	//大小点击事件
  	function switchbt(){
  		if($("#less").css("background-color")=="rgb(255, 255, 255)"){
  			$("#less").css("background-color","#009688");
			$("#greater").css("background-color","#FFFFFF");
			lwsize=1;
  		}else{
  			$("#greater").css("background-color","#009688");
			$("#less").css("background-color","#FFFFFF");
			lwsize=2;
  		}
  	}
	//筛选框收缩
	$("#up").click(function(){
		$("#screeninfo").show();
		$("#cscreen").hide();
		$("#up").hide();
		$(".screen2").hide();
		$("#down").show();
		$(".screen").css("height","7%");
		$(".content").css("height","93%");
		$("#ct1").css("height","6.6%");
		$("#ct2").css("height","86.4%");
		$("#cts").css("height","86.4%");
		$("#ct3").css("height","7%");
		$(".screens").css("height","100%");
		$("#imgul li").css("height","45%");
		$("#cts #imgul2 li div").css("height","30%");
		$(".ctstr").show();
	});
	//筛选框展示
	$("#down").click(function(){
		$("#screeninfo").hide();
		$("#cscreen").show();
		$(".screen").css("height","50%");
		$(".content").css("height","50%");
		$("#down").hide();
		$(".screen2").show();
		$("#up").show();
		$("#ct1").css("height","12%");
		$("#ct2").css("height","75%");
		$("#cts").css("height","75%");
		$("#ct3").css("height","13%");
		$(".screens").css("height","14%");
		$("#imgul li").css("height","100%");
		$("#cts #imgul2 li div").css("height","70%");
		$(".ctstr").hide();
	});
	//查询
	$("#cscreen").click(function(){
		var size=$("input[name='size']").eq(0).is(':checked');
		if(!size){
			if($("#long").val().trim()==""||$("#long").val().trim()==null){
				alert("请填写短边长");
				return;
			}
		}
		getPages(getNums(state1,state2));
		getPic(8,1,lwsize,state1,state2);
		info();
	});
	//全选
	$("#cc").change(function(){
		var c=$(this).is(':checked');
		if(c){
			$(".getdiv").each(function(){
				if(!$(this).parent().find("input").eq(0).is(':checked')){
					checks.push($(this).attr("id"));
				}
			});
			$("input[name=pic]").prop("checked",true);
		}else{
			$(".getdiv").each(function(){
				for(var i=0;i<checks.length;i++){
					if(checks[i]==$(this).attr("id")){
						checks.splice(i, 1);
					}
				}
			});
			$("input[name=pic]").prop("checked",false);
		}
	});
	//编辑模式和筛选模式的切换
	$(".pattern").click(function(){
		if($("#cts").is(':hidden')){
			if($(".screen2").is(':hidden')){
				$(".labelhide").show();
			}else{
				$(".labelhide").hide();
			}
			$("#cts").show();
			$("#ct2").hide();
			$("#pattern1").hide();
			$("#pattern2").show();
			$("#all").hide();
			$(".buff").hide();
			$("#demo2").css("text-align","center");
		}else{
			if($(".screen2").is(':hidden')){
				$(".labelhide").show();
			}else{
				$(".labelhide").hide();
			}
			$("#ct2").show();
			$("#cts").hide();
			$("#pattern2").hide();
			$("#pattern1").show();
			$("#all").show();
			$(".buff").show();
			$("#demo2").css("text-align","right");
		}
	});
	//选择标签
	$("#enter").click(function(){
		var str="";
		$("#more ul li input").each(function(){
			if($(this).is(':checked')){
				str+=$(this).val()+",";
			}
		});
		if(str.length>0){
			str=str.substring(0,str.length-1);
		}
		if(mores=="camerab"){
			$("#camera").val(str);
		}
		if(mores=="filmb"){
			$("#film").val(str);
		}
		if(mores=="lensb"){
			$("#lens").val(str);
		}
		$("#more").hide();
		$("#background").hide();
	});
	//恢复默认条件
	function recovery(){
		$("#greater").css("background-color","#FFFFFF");
		$("#less").css("background-color","#009688");
		$("#crux").val("");
		$("#r1").prop("checked",true);
		$("#le1").prop("checked",true);
		$("#s1").prop("checked",true);
		$("#c1").prop("checked",true);
		$("#f1").prop("checked",true);
		$("input[name=label]").prop("checked",false);
		getLabel();
		$(".screen input").val("");
	}
	//获取筛选信息
	function info(){
		var w="";
		var long=$("#long").val().trim();
		var date=$("#test1").val().trim();
		var crux=$("#crux").val().trim();
		var color="0";
		for(var i=0;i<3;i++){
			if($("input:radio[name=color]").eq(i).is(':checked')){
				color=i+"";
			}
		}
		var label=[];
		var size=$("input[name='size']").eq(0).is(':checked');
		var camera=$("input[name='camera']").eq(0).is(':checked');
		var film=$("input[name='film']").eq(0).is(':checked');
		var lens=$("input[name='lens']").eq(0).is(':checked');
		$('input[name="label"]:checked').each(function(){
			label.push($(this).val());
		});
		if(date==""||date==null){
			date="全部";
		}
		if(date==""||date==null){
			w+="<a class='ath'>爬取时间:全部</a>";
		}else{
			w+="<a class='ath'>爬取时间:"+date+"</a>";
		}
		if(crux!=""){
			w+="<a class='ath'>关键字:"+crux+"</a>";
		}
		if(color=="0"){
			w+="<a class='ath'>图片类型:全部</a>";
		}
		if(color=="1"){
			w+="<a class='ath'>图片类型:彩色</a>";
		}
		if(color=="2"){
			w+="<a class='ath'>图片类型:黑白</a>";
		}
		if(size){
			w+="<a class='ath'>图片尺寸:全部</a>";
		}else if(long!=""&&long!=null){
			if(lwsize==1){
				w+="<a class='ath'>图片尺寸(小于):短边长:"+long+"</a>";
			}
			if(lwsize==2){
				w+="<a class='ath'>图片尺寸(大于):短边长:"+long+"</a>";
			}
		}
		if(camera){
			w+="<a class='ath'>相机型号:全部</a>";
		}else{
			w+="<a class='ath'>相机型号:"+$("#camera").val()+"</a>";
		}
		if(film){
			w+="<a class='ath'>胶卷型号:全部</a>";
		}else{
			w+="<a class='ath'>胶卷型号:"+$("#film").val()+"</a>";
		}
		if(lens){
			w+="<a class='ath'>镜头型号:全部</a>";
		}else{
			w+="<a class='ath'>镜头型号:"+$("#lens").val()+"</a>";
		}
		if(label.length>0){
			var labels="";
			for(var i=0;i<label.length;i++){
				labels+="#"+label[i];
			}
			w+="<a class='ath'>标签类型:"+labels+"</a>";
		}
		$("#screeninfo").html("");
		$("#screeninfo").html(w);
	}
	//选中图片
	function check(obj){
		var check=$(obj).parent().find("input").eq(0);
		var id=$(obj).attr("id");
		if(!check.is(':checked')){
			checks.push(id);
			check.prop("checked",true);
		}else{
			for(var i=0;i<checks.length;i++){
				if(checks[i]==id){
					checks.splice(i, 1);
				}
			}
			check.prop("checked",false);
		}
		/* if(!check.is(':checked')){
			check.prop("checked",true);
		} */
	}
	//编辑模式点击图片展示内容
	function seeedit(id){
		id=id.id;
		var data=getOnePic(id);
		var w="<img src=getSampImage.do?imgUrl="+data[0].SAMP_PIC_SRC+">";
		var w3="";
		w3+="<div class='imginfodiv'>";
		w3+="<ul style='height:180px;'>";
		w3+="<li><div class='ezchinfodiv'><div class='lb'>名称:</div><div class='lb2'>"+data[0].SAMP_PIC_NAME+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>大小:</div><div class='lb2'>长:"+data[0].SAMP_PIC_LENGTH+"宽:"+data[0].SAMP_PIC_WIDTH+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>ISO:</div><div class='lb2'>"+data[0].ISO+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>光圈:</div><div class='lb2'>"+data[0].APER+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>快门:</div><div class='lb2'>"+data[0].SHUT+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>曝光模式:</div><div class='lb2'>"+data[0].EXPS_MOD+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>测光模式:</div><div class='lb2'>"+data[0].METRY_MOD+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>曝光补偿:</div><div class='lb2'>"+data[0].EXPS_EV+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>焦距:</div><div class='lb2'>"+data[0].FOC+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>视角:</div><div class='lb2'>"+data[0].VIEW+"</div></div></li>";
		
		
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>白平衡:</div><div class='lb2'>"+data[0].WHITE_BLA+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>色彩空间:</div><div class='lb2'>"+data[0].COLOR_SPACE+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>发布时间:</div><div class='lb2'>"+data[0].SAMP_PIC_SOURCE_PUB_TIME+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>拍摄日期:</div><div class='lb2'>"+data[0].SHOOT_DATE+"</div></div></li>";
		if(data[0].IS_BLACK=="1"){
			w3+="<li><div class='ezchinfodiv'><div class='lb'>是否黑白:</div><div class='lb2' style='margin-top:3px;'><div style='height:100%;width:100%;display:inline;'><form id='isblack' class='layui-form' action='' lay-filter='example' style='display:inline;'><input type='checkbox' name='isblack' lay-skin='switch' lay-text='彩色|黑白'></form></div></div></div></li>";
		}else{
			w3+="<li><div class='ezchinfodiv'><div class='lb'>是否黑白:</div><div class='lb2' style='margin-top:3px;'><div style='height:100%;width:100%;display:inline;'><form id='isblack' class='layui-form' action='' lay-filter='example' style='display:inline;'><input type='checkbox' name='isblack' lay-skin='switch' lay-text='彩色|黑白' checked></form></div></div></div></li>";
		}
		w3+="<li><div class='ezchinfodiv'><div class='lb'>相机型号:</div><div class='lb2'><input id='inputcamera2' style='height:28px !important;' type='text' value='"+data[0].FILM_CAMERA_ID+"'/></div></div></li>";
		w3+="<li><div class='ezchinfodiv'><div class='lb'>胶片型号:</div><div class='lb2'><input id='inputfilm2' style='height:28px !important;' type='text' value='"+data[0].FILM_PROD_ID+"'/></div></div></li>";
		w3+="<li><div class='ezchinfodiv'><div class='lb'>镜头类型:</div><div class='lb2'><input id='inputlens2' style='height:28px !important;' type='text' value='"+data[0].LENS_ID+"'/></div></div></li>";
		w3+="<li><div class='ezchinfodiv'><div class='lb'>回退原因:</div><div class='lb2'><textarea rows='' cols='' style='padding-left: 10px;' readonly='readonly'>"+data[0].REASON_NAME+"</textarea></div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv' style='margin-top:10px;'>"+
				"<div class='lb'>标签:</div>"+
				"<div class='lb2'>"+
					"<div class='phototags' style='float:left;width:300px;margin-left: -10px;' id='phototags'></div>"+
					"<div style='float:left;margin-left: 10px;width:32px;height:32px'>"+
						"<i id='"+id+"' class='iconfont addlbbnt' style='color:#009688;cursor: pointer;font-size: 18px !important;width:22px;height:22px' onclick='addTag(id)'>"+
							"&#xe6b7;"+
						"</i>"+
					"</div>"+
				"</div>"+
				"</div></li>";
		w3+="</ul>"; 
		w3+="</div>"; 
		w3+="<div class='sedit'>";
		w3+="<div class='state4'><button class='layui-btn layui-btn-sm layui-btn-normal buttonedit' id='"+id+"' onclick='pass(this)'>通过</button></div>";
		w3+="<div class='state4'><button class='layui-btn layui-btn-sm layui-btn-normal buttonedit' id='"+id+"' onclick='rejectPic(this)'>驳回</button></div>";
/* 		w3+="<div><button class='layui-btn layui-btn-sm layui-btn-normal ' id='"+id+"' onclick='screenpics(this)'>保存并筛选</button></div>"; */
		w3+="</div>";
		$("#imgdiv").html("");
		$("#imginfo").html("");
		$("#imgdiv").html(w);
		$("#imginfo").html(w3);
		if($(".screen2").is(':hidden')){
			$(".ctstr").show();
			$(".labelhide").show();
		}
		getPicLabel(id);
		form.render();
	}
	//加载图片时第一张图片的信息
	function seeedit2(id){
		var data=getOnePic(id);
		var w="<img src=getSampImage.do?imgUrl="+data[0].SAMP_PIC_SRC+">";
		var w3="";
		w3+="<div class='imginfodiv'>";
		w3+="<ul style='height:180px;'>";
		w3+="<li><div class='ezchinfodiv'><div class='lb'>名称:</div><div class='lb2'>"+data[0].SAMP_PIC_NAME+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>大小:</div><div class='lb2'>长:"+data[0].SAMP_PIC_LENGTH+"宽:"+data[0].SAMP_PIC_WIDTH+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>ISO:</div><div class='lb2'>"+data[0].ISO+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>光圈:</div><div class='lb2'>"+data[0].APER+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>快门:</div><div class='lb2'>"+data[0].SHUT+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>曝光模式:</div><div class='lb2'>"+data[0].EXPS_MOD+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>测光模式:</div><div class='lb2'>"+data[0].METRY_MOD+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>曝光补偿:</div><div class='lb2'>"+data[0].EXPS_EV+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>焦距:</div><div class='lb2'>"+data[0].FOC+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>视角:</div><div class='lb2'>"+data[0].VIEW+"</div></div></li>";
		
		
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>白平衡:</div><div class='lb2'>"+data[0].WHITE_BLA+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>色彩空间:</div><div class='lb2'>"+data[0].COLOR_SPACE+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>发布时间:</div><div class='lb2'>"+data[0].SAMP_PIC_SOURCE_PUB_TIME+"</div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv'><div class='lb'>拍摄日期:</div><div class='lb2'>"+data[0].SHOOT_DATE+"</div></div></li>";
		if(data[0].IS_BLACK=="1"){
			w3+="<li><div class='ezchinfodiv'><div class='lb'>是否黑白:</div><div class='lb2' style='margin-top:3px;'><div style='height:100%;width:100%;display:inline;'><form id='isblack' class='layui-form' action='' lay-filter='example' style='display:inline;'><input type='checkbox' name='isblack' lay-skin='switch' lay-text='彩色|黑白'></form></div></div></div></li>";
		}else{
			w3+="<li><div class='ezchinfodiv'><div class='lb'>是否黑白:</div><div class='lb2' style='margin-top:3px;'><div style='height:100%;width:100%;display:inline;'><form id='isblack' class='layui-form' action='' lay-filter='example' style='display:inline;'><input type='checkbox' name='isblack' lay-skin='switch' lay-text='彩色|黑白' checked></form></div></div></div></li>";
		}
		w3+="<li><div class='ezchinfodiv'><div class='lb'>相机型号:</div><div class='lb2'><input id='inputcamera2' style='height:28px !important;' type='text' value='"+data[0].FILM_CAMERA_ID+"'/></div></div></li>";
		w3+="<li><div class='ezchinfodiv'><div class='lb'>胶片型号:</div><div class='lb2'><input id='inputfilm2' style='height:28px !important;' type='text' value='"+data[0].FILM_PROD_ID+"'/></div></div></li>";
		w3+="<li><div class='ezchinfodiv'><div class='lb'>镜头类型:</div><div class='lb2'><input id='inputlens2' style='height:28px !important;' type='text' value='"+data[0].LENS_ID+"'/></div></div></li>";
		w3+="<li><div class='ezchinfodiv'><div class='lb'>回退原因:</div><div class='lb2'><textarea rows='' cols='' style='padding-left: 10px;' readonly='readonly'>"+data[0].REASON_NAME+"</textarea></div></div></li>";
		w3+="<li class='ctstr'><div class='ezchinfodiv' style='margin-top:10px;'>"+
				"<div class='lb'>标签:</div>"+
				"<div class='lb2'>"+
					"<div class='phototags' style='float:left;width:300px;margin-left: -10px;' id='phototags'></div>"+
					"<div style='float:left;margin-left: 10px;width:32px;height:32px'>"+
						"<i id='"+id+"' class='iconfont addlbbnt' style='color:#009688;cursor: pointer;font-size: 18px !important;width:22px;height:22px' onclick='addTag(id)'>"+
							"&#xe6b7;"+
						"</i>"+
					"</div>"+
				"</div>"+
				"</div></li>";
		w3+="</ul>"; 
		w3+="</div>"; 
		w3+="<div class='sedit'>";
		w3+="<div class='state4'><button class='layui-btn layui-btn-sm layui-btn-normal buttonedit' id='"+id+"' onclick='pass(this)'>通过</button></div>";
		w3+="<div class='state4'><button class='layui-btn layui-btn-sm layui-btn-normal buttonedit' id='"+id+"' onclick='rejectPic(this)'>驳回</button></div>";
/* 		w3+="<div><button class='layui-btn layui-btn-sm layui-btn-normal ' id='"+id+"' onclick='screenpics(this)'>保存并筛选</button></div>"; */
		w3+="</div>";
		$("#imgdiv").html("");
		$("#imginfo").html("");
		$("#imgdiv").html(w);
		$("#imginfo").html(w3);
		if($(".screen2").is(':hidden')){
			$(".ctstr").show();
			$(".labelhide").show();
		}
		getPicLabel(id);
	}
	//显示查看窗口
	function see(obj){
		var id=$(obj).parent().find("input").eq(0).val();
		var data=getOnePic(id);
		layer.open({
	        type: 2 //此处以iframe举例
	        //,title: window.parent.$("#iptPgName").val()+'的作品'
	        ,title: '样片'
	        ,area: ['1000px', '600px']
	        ,shade: 0.3
	        ,maxmin: true
	        ,offset: [ //为了演示，随机坐标
	          0,0
	        ] 
	        ,content: 'reviewfilmaudit.do?id='+id
	        ,btn: ['通过', '驳回']
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	          //layer.setTop(layero); //重点2
	          	layero.find('.layui-layer-btn').css('text-align', 'center');
	        }
  			,yes: function(index, layero){
    		//按钮【按钮一】的回调
    			pass2(data[0].SAMP_PIC_ID);
    			layer.close(index);
  			},btn2: function(index, layero){
    		//按钮【按钮二】的回调
    			rejectPic2(data[0].SAMP_PIC_ID);
  			}
	        ,cancel: function(){ 
    		//右上角关闭回调
				/* getPages(getNums(state1,state2));
				getPic(8,pageSize,lwsize,state1,state2); */
  			}
	    }); 
		/* var data=getOnePic(id);
		var w="";
		w+="<div class='state3' style='height:100%;width:60%;display: table-cell;vertical-align: middle;text-align: center;'><img src='SpiderImg/"+data[0].SAMP_PIC_SRC+"'></div>";
		w+="<div class='state3' style='height:100%;width:39%;border-left: 1px solid #DDDDDD;'>";
		w+="<div class='imginfodiv'>";
		w+="<table>";
		w+="<tr><td>名称:</td><td>"+data[0].SAMP_PIC_NAME+"</td></tr>";
		w+="<tr><td>大小:</td><td>长:"+data[0].SAMP_PIC_LENGTH+"宽:"+data[0].SAMP_PIC_WIDTH+"</td></tr>";
		w+="<tr><td>ISO:</td><td>"+data[0].ISO+"</td></tr>";
		w+="<tr><td>光圈:</td><td>"+data[0].APER+"</td></tr>";
		w+="<tr><td>快门:</td><td>"+data[0].SHUT+"</td></tr>";
		w+="<tr><td>曝光模式:</td><td>"+data[0].EXPS_MOD+"</td></tr>";
		w+="<tr><td>测光模式:</td><td>"+data[0].METRY_MOD+"</td></tr>";
		w+="<tr><td>曝光补偿:</td><td>"+data[0].EXPS_EV+"</td></tr>";
		w+="<tr><td>焦距:</td><td>"+data[0].FOC+"</td></tr>";
		w+="<tr><td>视角:</td><td>"+data[0].VIEW+"</td></tr>";
		w+="<tr><td>白平衡:</td><td>"+data[0].WHITE_BLA+"</td></tr>";
		w+="<tr><td>色彩空间:</td><td>"+data[0].COLOR_SPACE+"</td></tr>";
		w+="<tr><td>发布时间:</td><td>"+data[0].SAMP_PIC_SOURCE_PUB_TIME+"</td></tr>";
		w+="<tr><td>拍摄日期:</td><td>"+data[0].SHOOT_DATE+"</td></tr>";//"+(data[0].IS_BLACK=="1"?"黑白":"彩色")+"
		if(data[0].IS_BLACK=="1"){
			w+="<tr><td>是否黑白:</td><td>黑白</td></tr>";
		}else{
			w+="<tr><td>是否黑白:</td><td>彩色</td></tr>";
		}
		w+="<tr><td>相机型号:</td><td>"+data[0].FILM_CAMERA_ID+"</td></tr>";
		w+="<tr><td>胶片型号:</td><td>"+data[0].FILM_PROD_ID+"</td></tr>";
		w+="<tr><td>镜头类型:</td><td>"+data[0].LENS_ID+"</td></tr>";
		w+="<tr><td>回退原因:</td><td>"+data[0].REASON_NAME+"</td></tr>";
		w+="</table>";
		w+="<div class='labelhide ctstr'>";
		w+="<label class='photolabel'>标签:</label><i id='"+id+"' class='layui-icon' style='color:#009688;cursor: pointer;font-size: 18px;' onclick='addTag(id)'>&#xe61f;</i>";
		w+="<div class='phototags' id='phototags'>";
		w+="</div>";
		w+="</div>";
		w+="</div>";
		w+="<div style='text-align: center;height:10%;width:100%;'>";
		w+="<div class='state4'><button class='layui-btn layui-btn-sm layui-btn-normal buttonedit' onclick='pass2(this)' id='"+data[0].SAMP_PIC_ID+"'>通过</button></div>";
		w+="<div class='state4'><button class='layui-btn layui-btn-sm layui-btn-normal buttonedit' onclick='rejectPic2(this)' id='"+data[0].SAMP_PIC_ID+"'>驳回</button></div>";
		w+="</div>";
		w+="</div>";
		$("#statepic").html("");
		$("#statepic").html(w);
		getPicLabel(id);
		$("#background").show();
		$("#state").show();
		$(".labelhide").show();
		form.render();  */
	}
	//关闭查看的窗口
	$(".close").click(function(){
		$("#background").hide();
		$(".labelhide").hide();
		$("#state").hide();
		$("#reason").hide();
		$("#more").hide();
	});
	function close2(){
		$("#background").hide();
		$(".labelhide").hide();
		$("#state").hide();
		$("#more").hide();
	}
	//查看更多
	function more(id){
		$("#moreinput").val("");
		mores=id;
		var w="";
		var data=[];
		var keyword="";
		if(id=="camerab"){
			$("#titlediv").html("");
			$("#titlediv").html("相机型号");
			data=getCamera(keyword);
		}
		if(id=="filmb"){
			$("#titlediv").html("");
			$("#titlediv").html("胶片型号");
			data=getFilm(keyword);
		}
		if(id=="lensb"){
			$("#titlediv").html("");
			$("#titlediv").html("镜头型号");
			data=getLens(keyword);
		}
		if(data!=""&&data!=null){
			for(var i=0;i<data.length;i++){
				w+="<li><input  type='checkbox' value="+data[i].PROD_ID+" name='cameracheck'>"+data[i].PROD_ID+"</li>";
			}
		}
		$("#moreul").html("");
		$("#moreul").html(w);
		$("#background").show();
		$("#more").show();
	}
	//查询标签
	$("#cscreen2").click(function(){
		var w="";
		var data=[];
		var keyword=$("#moreinput").val();;
		if(mores=="camerab"){
			data=getCamera(keyword);
		}
		if(mores=="filmb"){
			data=getFilm(keyword);
		}
		if(mores=="lensb"){
			data=getLens(keyword);
		}
		for(var i=0;i<data.length;i++){
			w+="<li><input  type='checkbox' value="+data[i].PROD_ID+" name='cameracheck'>"+data[i].PROD_ID+"</li>";
		}
		$("#moreul").html("");
		$("#moreul").html(w);
	});
	//已选中的图片进入选中状态
	function checkedPic(data,checks){
		if(checks.length>0){
			for(var j=0;j<checks.length;j++){
				for(var i=0;i<data.length;i++){
					if(data[i].SAMP_PIC_ID==checks[j]){
						$("#imgul div[id="+data[i].SAMP_PIC_ID+"]").parent().find("input").eq(0).prop("checked",true);
					}
				}
			}
		}
	}
	//已删除和已筛选的图片移除选中状态
	function chekedremove(id){
		regressesId=id;
		var ids=id.split(",");
		if(ids.length>0){
			for(var i=0;i<checks.length;i++){
				for(var j=0;j<ids.length;j++){
					if(checks[i]==ids[j]){
						checks.splice(i, 1);
					}
				}
			}
		}
	}
	
	function rejectReason(id){
		getDismissal();
		layer.open({
	        type: 2 //此处以iframe举例
	        //,title: window.parent.$("#iptPgName").val()+'的作品'
	        ,title: '驳回原因'
	        ,area: ['1000px', '600px']
	        ,shade: 0.3
	        ,maxmin: true
	        ,offset: [ //为了演示，随机坐标
	          0,0
	        ] 
	        ,content: 'rejectreason.do?id='+id
	        ,btn: ['确定', '取消']
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	          //layer.setTop(layero); //重点2
	          	layero.find('.layui-layer-btn').css('text-align', 'center');
	          	
	        }
  			,yes: function(index, layero){
    		//按钮【按钮一】的回调
    			var content = $(layero).find("iframe")[0].contentWindow.getcontent();
    			var reId = $(layero).find("iframe")[0].contentWindow.getId();
    			enterrejectreason(content,reId,id);
    			layer.close(index);
  			},btn2: function(index, layero){
    		//按钮【按钮二】的回调
    			
  			}
	        ,cancel: function(){ 
    		//右上角关闭回调
				/* getPages(getNums(state1,state2));
				getPic(8,pageSize,lwsize,state1,state2); */
  			}
	    }); 
		/* $("#background").show();
		$("#reason").show(); */
	}
	$("#enter2").click(function(){
		/* var content=$("#reasonPut").val().trim();
		var reId="";
		$("#reasonul input[name=dismissalcheck]:checked").each(function(){
			reId+=$(this).attr("data-id")+",";
		}); */
		if(reId.length>0){
			reId=reId.substring(0,reId.length-1);
		}
		if(reId.length>0){
			addRegresses(regressesId,"胶卷","2","无","jyyr",reId);
		}
		if(content!=""){
			reId+=",0010";
			addReason("0010","驳回",content,content,"0");
		}
		$("#reason").hide();
		$("#background").hide();
	});
	function photogclose(){
		$("#photogEdit").hide();
	};
	function enterrejectreason(content,reId,id){
		/* var content=$("#reasonPut").val().trim();
		var reId="";
		$("#reasonul input[name=dismissalcheck]:checked").each(function(){
			reId+=$(this).attr("data-id")+",";
		}); */
		if(reId.length>0){
			reId=reId.substring(0,reId.length-1);
		}
		if(reId.length>0){
			addRegresses(id,"胶卷","2","无","jyyr",reId);
		}
		if(content!=""){
			reId+=",0010";
			addReason("0010","驳回",content,content,"0");
		}
		/*$("#reason").hide();
		$("#background").hide();*/
	
	
	
	}
  </script>
  
</html>
