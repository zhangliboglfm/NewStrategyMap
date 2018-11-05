<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>信息查看</title>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="js/jquery1.10.3-ui.js"></script>
<script type="text/javascript" src="js/messagecheck.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

</style>

<link rel="stylesheet" href="css/messagecheck.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>

</head>
<body class="layui-layout-body">
    <div class="layui-tab layui-tab-card" lay-filter="demo" lay-allowclose="true">
      <ul class="layui-tab-title">
        <li class="layui-this cantdele">摄影家</li>
        <li class="cantdele">文章</li>
      </ul>
      <div class="layui-tab-content"  style="margin-top:2px;position: relative;">
        <div class="layui-tab-item layui-show" >
        	<div class="photogdiv"  style="z-index:1;">	
	       		<div class="layui-inline">
			      <label class="layui-form-label">入库时间</label>
			      <div class="layui-input-inline">
			        <input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
			      </div>
			    </div>
	       		<div class="layui-inline">
			      <label class="layui-form-label">摄影家名称</label>
			      <div class="layui-input-inline">
			        <input type="text" id="serchPhotogName" autocomplete="off" class="layui-input">
			      </div>
			    </div>
	       		<div class="layui-inline">
			      <div class="layui-input-inline">
			        <button class="layui-btn layui-btn" onclick="getPhotogList()">检索</button>
			      </div>
			    </div>
			    <div class="photolaypage">
				    <div class="layui-fluid layadmin-maillist-fluid">
						  <div id="photolist">
						    
						  </div>
					</div>
				    <div id="demo0"></div>
			   	</div>
			   </div>
			   <div id="photogEdit" class="photogdiv" style="z-index:8;background: rgba(0,0,0,0.5);display: none;">
		   			<!-- 摄影家基本信息设置 -->
		   			<div class="editcontent">
		   				<i class="layui-icon" style="position: absolute;font-size: 26px;color:#009688;top:0px;right:0px;cursor: pointer;" onclick="photogclose()">&#x1006;</i>
		   				<div class="photog_baseinfo">
		   					<div class="photo_left">
					    		<div class="layui-upload">
					              <div class="layui-upload-list">
					                <img class="layui-upload-img" id="test-upload-normal-img" src="">
					                <p id="test-upload-demoText"></p>
					              </div>
					              <button type="button" class="layui-btn layui-btn-sm" id="test-upload-normal">修改图片</button>
					            </div>
					    	</div>
					    	<div class="photo_right">
					    		<div class="photogIntroduce" >
						    		<div>
						    			<label class="photolabel">姓名:</label><input id="photogName" class="photoinput" type="text"  value="" autocomplete="off" placeholder="请输入名字">
						    			<label class="photolabel">性别:</label><input id="photogGender" class="photoinput" type="text"  value="" autocomplete="off" placeholder="请输入名字">
						    		</div>
						    		<div>
						    			<label class="photolabel">生:</label><input id="photogBorn" class="photoinput" type="text"  value="" autocomplete="off" placeholder="请输入日期">
						    			<label class="photolabel">卒:</label><input id="photogDeath" class="photoinput" type="text"  value="" autocomplete="off" placeholder="">
						    		</div>
						    		<div>
						    			<label class="photolabel">国籍:</label><input id="photogNation" class="photoinput" type="text"  value="" autocomplete="off" placeholder="请输入国籍">
						    		</div>
						    		<div>
						    			<label class="photolabel">标签:</label><i class="layui-icon" style="color:#009688;cursor: pointer;font-size: 18px;" onclick="addTag()">&#xe61f;</i>
						    			<div class="phototags" id="phototags">
						    				<!-- <div class="phototag"><span>风景</span><i class="layui-icon close-icon" onclick="deleteTag(this)">&#x1007;</i></div> -->
						    			</div>
						    		</div>
						    		<button type="button" class="layui-btn layui-btn-sm savebuttom" onclick="savePhotoDesc()">保存基本信息</button>
						    		<label class="moreworks" onclick="packagedata(this)" data-lay="222">作品查看</label>
					    		</div>
					    		
					    	</div>
		   				</div>
		   				<!-- 摄影家标准照片 -->
			   			<div class="standphoto">
			   				<div class="standphotoTitle">
			   					<label class="photolabel1">标准照:</label>
			   					<i class="layui-icon" id="strandImg-upload-normal" style="color:#009688;cursor: pointer;font-size: 18px;" onclick="add">&#xe61f;</i>
								<button type="button" class="layui-btn confirmbutton">确认排序</button>
			   				</div>
			   				<div class="divphotos">
				   				<ul class="sortable">
		           				 	<!-- <li id="draggable1" class="drag">
		           				 		<img src="image/test/152636714922.jpg"></img>
		           				 	</li> -->
		           				</ul>
	           				</div>
			   			</div>
		   				<!--审核状态 -->
			   			<div class="auditStatus">
			   				<div class="auditStatusTitle">
			   					<label>审核状态:</label><label id="auditExplain" class="auditExplain"></label>
			   				</div>
			   				<div class="auditStatusButtons">
			   					 <button class="layui-btn auditStatusbutton" onclick="changePhotogStatus()">审核</button>
			   					 <button class="layui-btn auditStatusbutton" onclick="changephotogStatusTo4()">发布</button>
			   				</div>
			   			</div>
		   			</div>
			   </div>
        </div>
	       
        <!-- 下一个table切换页面 -->
        <div class="layui-tab-item">
        	<div class="layui-main">
				<div class="layui-breadcrumb">
				  <div id="ARTICLE" class="tagClass tagClass_bk" onclick="zhuanti()">专题文章</div>
				  <div id="KEY" class="tagClass" onclick="hexin()">核心介绍</div>
				</div>
				<div class="layui-content">
					<iframe class="layui-ifr" id="myifr" src="" frameborder="no"></iframe>
				</div>
			</div>
        </div>
      </div>
    </div>
	
</body>
<script type="text/javascript">
	var element,laydate,laypage,layer,upload,form;
	var pageNum =20;
	//正在操作的摄影家div和摄影家id
	var photogObj,operaPhotog;
	$(function(){
		
		adjust();
		$(window).resize(adjust);
		
		//文章tab
		$("#myifr").attr("src","zhuanti.do");
		
		$(".photolaypage,#photogEdit,#phototags").niceScroll({
				 autohidemode: true,
				 cursorwidth: "1px", // 滚动条的宽度，单位：便素
				 //cursorborder: "1px solid #fff", // CSS方式定义滚动条边框
				 cursorcolor: "#009688"
		});
		$(".divphotos").niceScroll({
				 autohidemode: true,
				 cursorwidth: "1px", // 滚动条的宽度，单位：便素
				 //cursorborder: "1px solid #fff", // CSS方式定义滚动条边框
				 cursorcolor: "#009688"
		});
			
		var sort = $(".sortable").sortable({
            handle: "img",
            delay:0,
            cursor: 'move',
            revert: true,
            scroll :true,
            stop: $(".confirmbutton").click(function (event) {
                //记录sort后的id顺序数组
                var arr = $(".sortable").sortable('toArray');
                //更新标准照显示顺序
                $.ajax({
					url:"updateStrandOrder.do",
					type:"post",
					dataType:"text",
					data:{"imgnames":JSON.stringify(arr)},
					success:function(data){
							if(data=="true"){
								layer.msg("操作成功");
							}else{
								layer.msg("请重试");
							}
						}	
					});
                
                
            })
        });	
		layui.use(['element','laydate','laypage', 'layer','upload','form'], function(){
			
			 element = layui.element,laydate = layui.laydate,laypage = layui.laypage,layer = layui.layer,upload=layui.upload,form=layui.form;
			  
			  //常规用法
			  laydate.render({
			    elem: '#date'
			  });
			  
			  //获取摄影家信息
			  	$.ajax({
				url:"getPhotoglist.do",
				type:"post",
				dataType:"json",
				data:{"pageSize":1,"pageNum":pageNum},
				success:function(data){
					if(data!=null&&data.list.length!=null){
						//分页插件
					  laypage.render({
					    elem: 'demo0'
					    ,count: data.countNum
					    ,limit:pageNum
					    ,layout: ['count', 'prev', 'page', 'next','skip']
					    ,jump: function(obj,first){
					    	if(!first){
					    		ChangePhotogList(obj.curr,obj.limit);
					    	};
					    }
					  });
					  /*循环拼接*/
					  var list = data.list;
					  var str ="";
					  for(var i=0;i<list.length;i++){
							str+=" <div class='onephotolist'  onclick='photogdesc(this)' data-photogid='"+list[i].PHOTOG_ID+"'> "+
						    	" <div class='photo_left'> "+
						    		" <img src='getPhtotogImg.do?filename="+list[i].FILE_NAME+"'></img> "+
						    	" </div> "+
						    	" <div class='photo_right'> "+
						    		" <div class='photoIntroduce' > "+
							    		" <h3 class='layadmin-title'><strong>"+list[i].PHOTOG_NAME+"</strong></h3> "+
							    		" <h4 class='linktotab'>"+(list[i].PHOTOG_GENDER=="M"?"男":"女")+"</h4> "+
							    		" <h4 class='linktotab'>"+list[i].COUNTRY_CHN_NAME+"</h4> "+
							    		" <h4 class='linktotab'>"+list[i].BORN_DATE+"</h4> "+
							    		" <h4 class='linktotab'>~"+(list[i].DEATH_DATE==null?"":list[i].DEATH_DATE)+"</h4> "+
							    		" <h4 class='linktotab' onclick='packagedata(this)' data-lay='111' data-articleId='"+list[i].CORE_INTRO+"' data-photogid='"+list[i].PHOTOG_ID+"'><a href='javascript:void(0)'>核心介绍</a></h4> "+
							    		" <h4 class='linktotab' onclick='packagedata(this)' data-lay='222' data-photogid='"+list[i].PHOTOG_ID+"'><a href='javascript:void(0)'>相关作品</a></h4> "+
						    		" </div> "+
						    	" </div> "+
						    " </div> ";
					  };
					  $("#photolist").html(str);
					  $(".onephotolist .photo_right").height($(".onephotolist .photo_left").height());
					}else{
						laypage.render({
					    elem: 'demo0'
					    ,count: 0
					    ,limit:20
					    ,layout: ['count', 'prev', 'page', 'next','skip']
					    ,jump: function(obj){
					    	
					    }
					  });
					  $("#photolist").html("");
					};
				}
			});
			  
			  
			  
			   //上传头像
		    var uploadInst = upload.render({
		      elem: '#test-upload-normal'
		      ,url: 'headImg.do'
		      ,before: function(obj){
		        //预读本地文件示例，不支持ie8
		        obj.preview(function(index, file, result){
		          $('#test-upload-normal-img').attr('src', result); //图片链接（base64）
		        });
		        this.data={'PHOTOG_ID':operaPhotog};
		      }
		      ,done: function(res){
		        //如果上传失败
		        if(res.code == 0){
		            layer.msg('上传失败');
		        }else{
		          	layer.msg('上传成功');
		          	var str="getPhtotogImg.do?filename="+res.filename;
		          	
		          	$(photogObj).find("img").attr("src",str); 	
		        };
		      }
		      ,error: function(){
		        //演示失败状态，并实现重传
		        var demoText = $('#test-upload-demoText');
		        demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-sm">重试</a>');
		        demoText.find('.demo-reload').on('click', function(){
		          uploadInst.upload();
		        });
		      }
		    });
		    
		    
		    
		     //上传标准照
		    var uploadInst = upload.render({
		      elem: '#strandImg-upload-normal'
		      ,url: 'uploadStrandImg.do'
		      ,before: function(obj){
		        this.data={'PHOTOG_ID':operaPhotog};
		      }
		      ,done: function(res){
		        //如果上传失败
		        if(res.code == 0){
		            layer.msg('上传失败,请重试！');
		        }else{
		        	layer.msg('上传成功');
		          	var str="<li id='"+res.filename+"' class='drag'>"+
		          				"<i class='iconfont' style='position: absolute;color:#009688;top:-4px;left:-4px;cursor: pointer;'>&#xe602;</i>"+
       							"<i class='iconfont icon-true show' onclick='changeShowStatus(obj)'>&#xe603;</i>"+	
		           				"<img src=getPhtotogImg.do?filename="+res.filename+"></img>"+
           				 	"</li>";
           			$(".sortable").append(str);
		        };
		      }
		    });
		 });
		 
		 //输入框绑定enter事件
		 $("#serchPhotogName").keydown(function(evet){
		 	if(event.keyCode==13){
		 		getPhotogList();
		 	};
		 });
		 
		 
	});
	
	function getPhotogList(){
		var photogName =$("#serchPhotogName").val().trim(); 
		var dealTime = $("#date").val().trim();
		ChangePhotogList(1,pageNum,true,photogName,dealTime);
	};
	
	

	
	//封装数据，添加tab切换页面
	function packagedata(obj){
		var ev = window.event || arguments.callee.caller.arguments[0] || event;  // 对应分别为谷歌、火狐、IE
		//阻止冒泡事件
		ev.stopPropagation();
		var data;
		var layid=$(obj).attr("data-lay");
		var photogid = $(obj).attr("data-photogid");
		if(photogid==""||photogid==undefined||photogid==null){
			photogid=operaPhotog;
		}
		if(layid=="111"){
			var articleId =$(obj).attr("data-articleId");
			data={
				"title":"核心介绍",
				"lay-id":layid,
				"src":"productionJiedu.do?photogid="+photogid+"&articleId="+articleId+"&flagId=CI"
			};
		}else if(layid=="222"){
			data={
				"title":"相关作品",
				"lay-id":layid,
				"src":"production.do?photogid="+photogid
			};
		};
		
		addTable(data);
		
	};
	
	
	//添加tab切换页面
	function addTable(data){
		if($("li[lay-id='"+data["lay-id"]+"']").size()<=0){
			layui.element.tabAdd('demo', {
		        title: data["title"] //用于演示
		        ,content:"<iframe frameborder='no' class='ifr'   src='"+data["src"]+"'></iframe>"
		        ,id:data["lay-id"] //实际使用一般是规定好的id，这里以时间戳模拟下
		      });
		}
		$(".layui-show").removeClass("layui-show");
		$(".layui-this").removeClass("layui-this");
		var index =$("li[lay-id='"+data["lay-id"]+"']").index();
		$(".layui-tab-content .layui-tab-item").eq(index).addClass("layui-show");
		$("li[lay-id='"+data["lay-id"]+"']").addClass("layui-this");
		//$(".layui-tab-content .layui-tab-item .ifr").attr("src",data["src"]);
	};
	
	//调整页面布局
	function adjust(){
		$(".layui-tab-content").height($(".layui-tab").height()-45);
		var height = $(".layui-tab-content").height()-40;
		$(".photolaypage").css({"height":height+"px","max-height":height+"px"});
		$(".onephotolist .photo_right").height($(".onephotolist .photo_left").height());
		//文章面板tab
		var H = $(".layui-tab-item").height();
		$(".layui-content").height(H-$(".layui-breadcrumb").height());
		
	};
	
	
	$(".tagClass").click(function(){
		$(this).addClass("tagClass_bk");
		$(this).siblings().removeClass("tagClass_bk");
	});
	function zhuanti(){
		$("#myifr").attr("src","zhuanti.do");
	}
	function hexin(){
		$("#myifr").attr("src","hexin.do");
	}
	
	
</script>
</html>