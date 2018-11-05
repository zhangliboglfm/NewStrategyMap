<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>作品</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/common.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<!-- <link rel="stylesheet" href="layui/css/template.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/admin.css" type="text/css"></link> -->

<style type="text/css">
	*{font-size: 12px;font-family: 微软雅黑;color: #1C1C1C}
	html{width: 100%;}
	body{width: 100%;height: 100%;margin: 0;padding: 0;background-color: #fff;}
	.layui-tab-brief>.layui-tab-more li.layui-this:after, .layui-tab-brief>.layui-tab-title .layui-this:after{border-bottom-color: #B2B2B2}
	.maindiv{
		width: 100%;
	}
	.maindiv .tabdiv{
		width: 100%;
		height: 920px;
		position: relative;
	}
	.maindiv .tabdiv .layui-tab-title{}
	.layui-tab-item{height: 100% !important;}
	.layui-tab-item iframe{height: 100%;width: 100%;border: 0;}
	
	.maindiv .tabdiv .rightdiv{
		margin-top: 0px;
		width: 200px;
		height: 40px;
		text-align: right;
		line-height: 40px;
		position: absolute;
		z-index: 99999;
		right: 0px;
	}
	.maindiv .tabdiv .rightdiv i{color: #009688;font-size: 24px;cursor: pointer;}
	.layui-layout-body,.maindiv{
		min-width: 860px !important;
	}
	#workslist{
		/* margin:15px !important; */
	}
	#workslist .layui-col-md2{
		width: 240px;
		height: 300px;
		margin: 10px !important;
		float: left;
	}
	#workslist .cmdlist-container{
		width: 240px;
		height: 300px;
		border: 1px #eee solid !important;
		cursor: pointer;
	}
	#workslist .thumbImg{
		width: 240px;
		height: 240px;
		background-size: cover;background-position: center;background-origin:content-box;position: relative;display: inline-block;
	}
	#workslist .cmdlist-text{
		padding: 20px;
	}
	
	#footpage{
		margin-left:5px;
		margin-right:5px;
	}
	
	
	/*上传*/
	.top{
		/* background-color: red; */
		width: 100%;
		margin-top: 15px;
		height: 50px;
	}
	.top .filenamediv{
		float: left;
		width: 400px;
		height: 30px;
		margin-top: 5px;
	}
	.filenamediv input{
		border-color: #c3c3c3 !important;
		cursor: pointer !important;
	}
	.top .filediv{
		float: left;
		margin-left: 15px;
		margin-top: 5px;
	}
	.top .downloaddiv{
		float: right;
		margin-top: 5px;
	}
	.filediv .file{
		display: none;
	}
	.filediv .bnt{
		float: left;
	}
	.downloaddiv .downloadbnt{
		margin-right: 10px;
		float: right;
	}
	.info{
		width: 100%;
		margin-top: 15px;
		height: 600px;
		border: 1px solid #c3c3c3;
	}
	#divLogInfo a{
		color: #1E9FFF;
		text-decoration: underline;
	}
	
	/* 阴影 */
	.shadow{
		-webkit-box-shadow:0px 0px 3px #a0a0a0;
    	-moz-box-shadow:0px 0px 3px #a0a0a0;
    	box-shadow:0px 0px 3px #a0a0a0;
		/* 投影数字顺序依次是：右边 下边 投影模糊大小 */
		/* 内阴影数字顺序依次是：左边 上边 阴影模糊大小 */
	}
	.ico_delpic{
		background-position: -25px -25px;
		cursor: pointer;
	}
	.ico_delpic:HOVER{
		background-position: -50px -25px;
	}
	.ico_delpic{
		position: absolute;top: 2px;right: 0;display: inline-block;vertical-align: middle;
		background-image: url(image/ico_layer.png?id=20140918111800);background-repeat: no-repeat; 
		width: 20px;height: 20px;
		z-index: 99;
	}
	
</style>

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jscommon.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="layui/lay/modules/element.js"></script>
<script type="text/javascript" src="js/Sortable.min.js"></script>
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
  <script src="js/html5.min.js"></script>
  <script src="js/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<div class="maindiv">
		<div class="layui-tab layui-tab-brief tabdiv" lay-filter="test">
			<ul class="layui-tab-title">
				<li class="layui-this" lay-id="look">作品查看</li>
				<li lay-id="upload">作品录入</li>
			</ul>
			<div class="layui-tab-content">
				<div tabtype="look" class="layui-tab-item layui-show">
					<div style="text-align: right;"><div id="footpage" style="background: #FFF;"></div></div>
					<!-- <iframe id="ifrWorksLook" src=""></iframe> -->
					<div id="workslist" class="layui-row" style="width: 100%;">
			    		<!-- <div class="layui-col-md2">
			    			<div class="cmdlist-container">
			    				<a><div class="thumbImg" style="background-image: url('image/character.jpg');"></div></a>
			    				<a>
				    				<div class='cmdlist-text'>
				    					<span class="layui-badge">1</span>
				    					<span>阿尔弗雷德·艾森斯塔特</span>
				    				</div>
			    				</a>
			    			</div>
			    		</div> -->
			    	</div>
				</div>
				<div tabtype="upload" class="layui-tab-item">
					<!-- <iframe id="ifrWorksUpload" src="messageimport.do"></iframe> -->
					<!-- <button type="button" class="layui-btn" id="test2">多图片上传</button> -->
					<!-- <div class="layui-upload" style="height:500px;flow:auto;">
					  <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button> 
					  <div class="layui-upload-list">
					    <table class="layui-table">
					      <thead>
					        <tr><th>文件名</th>
					        <th>大小</th>
					        <th>状态</th>
					        <th>操作</th>
					      </tr></thead>
					      <tbody id="demoList"></tbody>
					    </table>
					  </div>
					  <button type="button" class="layui-btn" id="testListAction">开始上传</button>
					</div> -->
					<div class="top">
						<div class="filenamediv">
							<input id="iptFileName" type="text" name="filename" required lay-verify="required" title="选择文件" placeholder="请选择文件，上传文件仅限ZIP或RAR格式压缩包" autocomplete="off" class="layui-input" readonly="readonly" onclick="clickFileUpload()">
						</div>
						<div class="filediv">
							<button class="layui-btn layui-btn-normal bnt" onclick="clickFileUpload()">选择文件</button>
							<button class="layui-btn layui-btn-normal bnt" onclick="startUpload()">开始上传</button>
							<input id="iptFile" type="file" class="file" accept=".zip,.rar" onchange="getFileInfo()"/>
						</div>
						<div class="downloaddiv">
							<button class="layui-btn layui-btn-normal downloadbnt" onclick="downLoadModelFile()">模板下载</button>
							<button class="layui-btn layui-btn-normal downloadbnt" onclick="downLoadList()">生成list</button>
						</div>
					</div>
					<div id="divLogInfo" class="info">
					</div>
				</div>
			</div>
		</div> 
	</div>
</body>
<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript">
	var form = layui.form,laydate=layui.laydate,layer = layui.layer,laypage = layui.laypage,upload = layui.upload;
	var pgId = "<%=request.getAttribute("pgId")%>";
	var layer;
	var element;
	var topCounts=0;
	layui.use(["layer","element"], function(){
		layer = layui.layer;
		element = layui.element;
		//一些事件监听
		element.on("tab(test)", function(data){
			console.log(data.index);
		});
	});

	$(function(){
		init();
		$(window).resize(adjust);
	});
	function init(){
		adjust();
		
		//$("#workslist").niceScroll({});
		/* $(".cmdlist-container").mouseover(function(){
			$(this).addClass("shadow");
		}).mouseout(function(){
			$(this).removeClass("shadow");
		}); */
		
		var currPage = 1;
		var pageSize=12;
		loadWorkslist(currPage,pageSize);
		
		$(document).keydown(function(event){
			//e.ctrlKey & e.KeyCode ==   ctrl+...
			var currval = Number($(".layui-laypage-skip").find("input").val());
			if (event.ctrlKey&&event.keyCode==38) {//上
				if(currval!=1){
					$(".layui-laypage-skip").find("input").val(currval-1);
				}else{
					window.parent.layuiLayerMsg("已为第一页");
				}
			} else if (event.ctrlKey&&event.keyCode==40) {//下
				if(currval!=Math.ceil(Number(topCounts)/12)){
					$(".layui-laypage-skip").find("input").val(currval+1);
				}else{
					window.parent.layuiLayerMsg("已为最后一页");
				}
			}
			$(".layui-laypage-btn").click();
		});
	}
	
	
	//页面布局调整
	function adjust(){
		var height = $(window).height();
		$(".layui-tab-content").height($(".tabdiv").height()-$(".layui-tab-title").height());
	}
	
	function loadWorkslist(currPage,pageSize){
		var url="selphgworks.do";
		var parameter={"pgId":pgId,"currPage":currPage,"pageSize":pageSize};
		ajaxRequest(url, parameter, function(data){
			var counts = data.counts;
			var dataList = data.dataList;
			var html="";
			for(var i=0;i<dataList.length;i++){
				var item = dataList[i];
				var id=item.id;
				var name=item.name;
				var order=item.order;
				//alert(name);
				/* html+='<div class="layui-col-md2 layui-col-sm4" >';
	    		html+='	<div class="cmdlist-container" onmouseover="shadowover(this)" onmouseout="shadowout(this)" pgid="'+id+'" onclick="photogClick(this)">';
	    		html+='		<a><img src="selphgworksimg.do?wId='+id+'"></img></a>';
	    		html+='		<a><div class="cmdlist-text">';
	    		html+='			<span class="layui-badge">'+order+'</span>';
	    		html+='			<span>'+name+'</span>';
	    		html+='			</div>';
	    		html+='		</a>';
	    		html+='	</div>';
	    		html+='</div>'; */
	    		
	    		html+='<div class="layui-col-md2">';
	    		html+='<a class="ico_delpic" data-id="'+id+'" onclick="delpic(this)"></a>';	
	    		html+='	<div class="cmdlist-container" onmouseover="shadowover(this)" onmouseout="shadowout(this)" wid="'+id+'" order="'+order+'" onclick="phgWorksClick(this)">';
	    		html+='		<a><div class="thumbImg" style="background-image: url(\'selphgworksimg.do?wId='+id+'\');"></div></a>';
	    		html+='		<a>';
		    	html+='			<div class="cmdlist-text">';
		    	html+='				<span class="layui-badge">'+order+'</span>';
		    	html+='				<span>'+name+'</span>';
		    	html+='			</div>';
	    		html+='		</a>';
	    		html+='	</div>';
	    		html+='</div>';
	    		
			}
			$("#workslist").html(html);
			laypage.render({
			    elem: 'footpage'
			    ,count: counts
			    ,curr: currPage || 1 //当前页
			    ,limit: 12
			    //,limits: [12, 18, 24, 30, 36]
			    ,layout: ['count', 'prev', 'page', 'next','refresh', 'skip']//'refresh',
			    ,jump: function(obj, first){
			        if(!first){
			        	var currPage=obj.curr;
						loadWorkslist(obj.curr,obj.limit);
					}
			    }
			});
			
			if(Number(counts)==0){
				element.tabChange('test', 'upload');
			}else{
				element.tabChange('test', 'look');
			}
			topCounts=counts;
			
		});
	}
	
	function shadowover(obj){
		$(obj).addClass("shadow");
	}
	function shadowout(obj){
		$(obj).removeClass("shadow");
	}
	function phgWorksClick(obj){
		var workNum=$(obj).attr("order");
		//多窗口模式，层叠置顶
	      window.parent.parent.parent.parent.layer.open({
	        type: 2 //此处以iframe举例
	        ,title: window.parent.$("#iptPgName").val()+'的作品'
	        ,area: ['1000px', '600px']
	        ,shade: 0.3
	        ,maxmin: true
	        ,offset:  'auto'
	        ,content: 'production.do?photogid='+pgId+'&workNum='+workNum+'&audit=0'
	        ,zIndex: layer.zIndex //重点1
	        ,success: function(layero){
	          //layer.setTop(layero); //重点2
	        }
	      });
	}
	
	
	/////下载模板
	function downLoadModelFile(){
		window.open("selphgworksmod.do");
	}	
	////上传
	var MyFile = new Object();
	MyFile.file = null;
	MyFile.name = null;
	MyFile.size = 0;
	var ot;
	var oloaded;
	
	/**
	 * 触发文件上传
	 */
	function clickFileUpload(){
		$("#iptFile").click();
	}
	/**
	 * 获取文件信息，并显示文件名
	 */
	function getFileInfo(){
		var filedom = document.getElementById("iptFile");
		var file = filedom.files["0"];//console.log(file.name);console.log(file.size);
		$("#iptFileName").val(file.name);
		MyFile.file = file;
		MyFile.name = file.name;
		MyFile.size = file.size;
	}
	/**
	 * 开始上传
	 */
	function startUpload(){
		if(MyFile.file == null){
			alert("请先选择文件！");
			return false;
		}
		uplaodFile();
	}
	/**
	 * 上传文件
	 */
	function uplaodFile(){
		var file = MyFile.file;
		var filename = encodeURIComponent(encodeURIComponent(MyFile.name));
		var filesize = MyFile.size;
		//alert(filename);
		//var form = new FormData();
		var url = "uploadphgworks.do?name="+filename+"&size="+filesize+"&pgId="+pgId;
		var xhr;
		if (window.XMLHttpRequest){
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xhr=new XMLHttpRequest();
		}
		else{
			// code for IE6, IE5
			xhr=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xhr.upload.onprogress = uploadProgress;
		var repTextSize=0;
		xhr.onreadystatechange=function(){
			if(xhr.readyState>2){
				var tmpText = xhr.responseText.substring(repTextSize);
				repTextSize = xhr.responseText.length;
				console.log(repTextSize);
				console.log(tmpText);
				if(tmpText.length > 0 ){
					initUploadLog(tmpText);
				}
			}
			if(xhr.readyState==4 && xhr.status==200){
			}
		}
		xhr.open("post", url, true);
		xhr.send(file);
	}
	
	/**
	 * 上传进度展示
	 */
	function uploadProgress(evt) {
		if (evt.lengthComputable) {
			var percentComplete = Math.round(evt.loaded * 100.0 / evt.total);
			$("#divLogInfo").html("文件已上传："+percentComplete+"%"+"<br>");
			if(percentComplete == 100){
				$("#divLogInfo").append("文件上传完毕，开始校验"+"<br>");
			}
		} else {
		}
	}
	/**
	 * 记录上传日志
	 */
	function initUploadLog(str){
		$("#divLogInfo").append(str);
		var div = document.getElementById('divLogInfo');
		div.scrollTop = div.scrollHeight;
	}
	
	function downLoadList(){
		layui.use(["layer","element"], function(){
			layer.open({
			  type: 2,
			  title: false,
			  shadeClose: true,
			  shade: 0.8,
			  area: ['250px', '100px'],
			  content: 'makeExcel.do' //iframe的url
			});
		});
	}
	

	//删除作品
	function delpic(e) {
		var deleteId=$(e).attr("data-id");
		window.parent.layer.confirm('确认删除？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
		  $.ajax({
			url:"deletePgWorks.do",
			type:"post",
			dataType:"text",
			data:{
				deleteId : deleteId,
				pgId : pgId
			},
			success:function(data){
				if (data=="yes") {
					$(e).parent().remove();
					reOrderWork();
					window.parent.layer.closeAll();
				} else {
					layer.msg("删除失败,请联系管理员");
				}
			},
			error : function () {
				layer.msg("删除失败,请联系管理员");
			}
		}); 
		}, function(){
		  layer.closeAll();
		});
	}
	function reOrderWork() {
		$.ajax({
			url:"reOrderWork.do",
			type:"post",
			dataType:"text",
			data:{
				pgId : pgId
			},
			success:function(data){
				if (data=="yes") {
					var currPage = 1;
					var pageSize=12;
					loadWorkslist(currPage,pageSize);
				}
			}
		});
	}
	/* //多图片上传
	  upload.render({
	    elem: '#test2'
	    ,url: '/upload/'
	    ,multiple: true
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        ///$('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
	      });
	    }
	    ,done: function(res){
	      //上传完毕
	    }
	  }); */
	  /* var demoListView = $('#demoList')
	  ,uploadListIns = upload.render({
	    elem: '#testList'
	    ,url: '/upload/'
	    ,accept: 'file'
	    ,multiple: true
	    ,auto: false
	    ,bindAction: '#testListAction'
	    ,choose: function(obj){   
	      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
	      //读取本地文件
	      obj.preview(function(index, file, result){
	        var tr = $(['<tr id="upload-'+ index +'">'
	          ,'<td>'+ file.name +'</td>'
	          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
	          ,'<td>等待上传</td>'
	          ,'<td>'
	            ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
	            ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
	          ,'</td>'
	        ,'</tr>'].join(''));
	        
	        //单个重传
	        tr.find('.demo-reload').on('click', function(){
	          obj.upload(index, file);
	        });
	        
	        //删除
	        tr.find('.demo-delete').on('click', function(){
	          delete files[index]; //删除对应的文件
	          tr.remove();
	          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
	        });
	        
	        demoListView.append(tr);
	      });
	    }
	    ,done: function(res, index, upload){
	      if(res.code == 0){ //上传成功
	        var tr = demoListView.find('tr#upload-'+ index)
	        ,tds = tr.children();
	        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
	        tds.eq(3).html(''); //清空操作
	        return delete this.files[index]; //删除文件队列已经上传成功的文件
	      }
	      this.error(index, upload);
	    }
	    ,error: function(index, upload){
	      var tr = demoListView.find('tr#upload-'+ index)
	      ,tds = tr.children();
	      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
	      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
	    }
	  }); */
</script>
</html>
