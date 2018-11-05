<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<title>专题文章</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="js/jquery1.10.3-ui.js"></script>
<script type="text/javascript" src="js/articleMain.js"></script>
<link rel="stylesheet" href="css/messagecheck.css" type="text/css"></link>

<style type="text/css">
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
.main{width: 100%;height: 99%;overflow: hidden;background-color:#F2F2F2;border-top: 1px solid #E0E0E0;font-family: 微软雅黑; }
@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1532661539245'); /* IE9*/
  src: url('iconfont.eot?t=1532661539245#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
  url('iconfont.ttf?t=1532661539245') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1532661539245#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
input{outline:none;}  
.topSearch{float:left;margin-left:1%;margin-top:1%;width:98%;height: 30px;line-height:30px;border-bottom: 1px solid #000;font-size: 13px;}
.wzCounts{float:left;margin-left:15px;width:120px;height: 30px;line-height:30px;}
.serchModel{float:right;margin-right: 15px;width: 240px;height: 30px;border: 0px solid #fff;box-sizing: border-box;}
.explain{float: left;width:80px;height: 30px;line-height:30px;color: #000}
.serchKuang{float: left;margin-top:2px;width:130px;height: 24px;line-height:24px;border: 1px solid #fff;box-sizing: border-box;text-indent: 1ex;}
.searchIcon{float: left;margin-top:2px;width:30px;height: 24px;line-height:24px;background-color: #009688;cursor: pointer;color: #fff;text-align: center;}
.showContents{float:left;width:100%;}
.wzClass{float:left;margin-left:15px;margin-top:15px;height:200px;background-color:#fff;position: relative;font-size: 13px;}
.wzClass1{width:100%;height:130px;}
.wzClass2{width:100%;height:70px;}
.wz_image{float:left;width: 40%;height: 100%;}
.wz_image img{width: 100%;height: 100%;}
.rank{position:absolute;top:72px;left:0px;width:auto;min-width:20px;height:20px;line-height:18px;background-color: red;color: #fff;text-align: center;z-index: 10;cursor: pointer;}
.wz_name{float:left;margin-left: 10px;height: 30px;line-height: 30px;font-size: 16px;}
.brief{float:left;margin-top:0px;margin-left: 10px;line-height:25px;overflow: hidden;}
.brief{display: -webkit-box;
-webkit-box-orient: vertical;
-webkit-line-clamp: 3;
overflow: hidden;}
.jiedu{float:left;margin-top:0px;margin-left: 0px;height:25px;}
.jdicon{float:left;margin-left: 10px;height:25px;line-height: 25px;text-align: center;text-decoration: underline;color: #00f;cursor: pointer;font-size: 12px;}
.detail1{float:left;margin-left: 0px;width:100%;height: 100%;border: 0px solid #F2F2F2;font-size: 12px;}
.detail2{float:left;margin-left: 10px;margin-top:7px;/* width: 60px; */height: 25px;line-height: 25px;text-align: center;border: 1px solid #C7CCD8;box-sizing: border-box;padding: 0px 5px;}
.resetArt{width:100%;height:99%;background:rgba(0,0,0,0.5);position:absolute;z-index:88;font-size: 13px;display: none;}
.tagClass_bk{color: #009688}
.Artmain{float:left;margin-top: 4%;width: 700px;height:400px;background:rgb(255,255,255);z-index:99;}
.img_path2{float:left;height:200px;}
.img_path3{float:left;margin-top:10px;width:100%;height:30px;}
.img_path4{float:left;width:100%;height:131px;}
.img_bor{float:left;margin-left:4px;margin-top: 5px;width: 90px;height: 92px;border: 1px solid red;}
.resctButton{float:left;margin-left:20px;margin-top: 10px;width: 80px;height: 28px;line-height: 28px;text-align:center;;background:#009688;color:#fff;cursor: pointer;}
.img_class1{width: 60px;height:30px;line-height:30px;margin-top:5px;display: inline-block;text-align: left;}
.img_class2{width:100px;height:20px;line-height:30px;margin-top:5px;display: inline-block;text-align: center;border:0px;border-bottom:1px solid gray;}
.img_class3{float:left;width: 60px;height:90px;margin-top:5px;text-align: left;}
.img_class4{float:left;width:82%;height:90px;margin-top:5px;border:1px solid gray;resize:none}
.img_class5{float:left;width:90%;height:60px;margin-top:10px;}
.img_class6{float:left;width: 60px;height:25px;line-height:25px;text-align: left;}
.img_class6_i{float:left;height:25px;line-height:25px;}
.img_class7{float:left;height:35px;width: auto;}
.img_class8{float:left;margin-left: 10px;margin-top:7px;height: 22px;border: 1px solid #009688;padding: 0px 5px;}
.img_class8_child1{float:left;height: 22px;line-height: 22px;text-align: center;font-size: 12px;}
.img_class8_child2{float:right;margin-right:-11px;margin-top:-8px;width:14px;height: 14px;cursor: pointer;}
.img_search{float:left;width:50px;height:21px;line-height: 21px;text-align: center;background: #009688;cursor: pointer;color: #fff;}
.img_search_k{position: absolute;left:0px;top:22px;width: 140px;height: 110px;border: 1px solid gray;display: none;}
.img_searchk_Child{width: 100%;height: 22px;line-height:22px;text-align:left;font-size:12px;border: 0px solid gray;text-indent: 1ex;cursor: pointer;}
.img_searchk_Child:HOVER{color: #009688}
.saveMessages{float:left;margin-top:2px;width:80px;height: 26px;line-height: 26px;text-align: center;background: #009688;cursor: pointer;color: #fff;}
.zptitle{float:left;margin-left:1%;width:98%;height: 30px;border-bottom: 1px solid #000;}
.zptitleChild1{float:left;margin-left:5px;width:80px;height: 30px;line-height: 30px;text-align: center;}
.zptitleChild2{float:right;margin-right:20px;margin-top:2px;width:80px;height: 26px;line-height: 26px;text-align: center;color: #fff;background: #009688;cursor: pointer;}
.zpshow{float:left;margin-left:1%;width:98%;height: 100px;}
.sortable{margin-top:10px;height:100px;white-space:nowrap;}
.sortable .drag{float: left;}
.zpshow ul li {height:100%;list-style: none;margin-left:10px;display: inline-block;}
.zpshow ul li img{height:90%;}
</style>
</head>

<body>
	<div class="main">
		<div class="topSearch">
			<div id="wzCounts" class="wzCounts"></div>
			<div class="serchModel">
				<div class="explain">按文章搜索：</div>
				<input id="wz_serch" class="serchKuang" type="text" />
				<i id="wz_icon" class="iconfont searchIcon">&#xe64c;</i>
			</div>
		</div>
		<div id="showContents" class="showContents scroll">
			<!-- <div id="wz_1" class="wzClass">
				<div class="wzClass1">
					<div id="wz_image" class="wz_image">
						<img src="image/01.jpg" alt="文章1"></img>
					</div>
					<div id="rank" class="rank" onclick="reviseRank()">10</div>
					<div id="wz_name" class="wz_name" onclick="resetArt(this)">文章标题</div>
					<div class="brief" onclick="resetArt(this)">内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容</div>
					<div class="jiedu">
						<div class="jdicon">查看文章</div>
						<div class="jdicon">信息修改</div>
					</div>
				</div>
				<div class="wzClass2">
					<div class="detail1" style="border: 0px solid #F2F2F2;">
						<div class="detail2">标签1</div>
						<div class="detail2">标签标签2</div>
						<div class="detail2">标签标4</div>
					</div>
				</div>
			</div> -->
		</div>
		<div id="fenye" style="float:left;height:32px;width:99%;margin-top:15px;" align="right"></div>
		<div class="resetArt">
			<div class="Artmain">
				<div class="img_path1" style="float:left;width:150px;height:200px;">
					<div class="layui-upload">
		              <div class="layui-upload-list">
		                <img class="layui-upload-img" id="test-upload-normal-img" src="" alt="" style="float:left;margin-left:14px;margin-top: 5px;width: 125px;height: 140px;">
		                <p id="test-upload-demoText"></p>
		              </div>
		              <button type="button" class="layui-btn layui-btn-sm" id="test-upload-normal" style="float:left;margin-left:30px;margin-top: 10px;	">修改图片</button>
		            </div>
				</div>
				<div class="img_path2">
					<div>
						<label class="img_class1">文章名称:</label><input id="reset_wzName" class="img_class2" type="text"  value="文章名称" autocomplete="off" placeholder="请输入文章名称">
						<i class="iconfont" style="float:right;font-size: 26px;color:#009688;margin-top:0px;margin-right:1px;cursor: pointer;" onclick="imgclose()">&#xe602;</i>
					</div>
					<div class="img_class3">内容简介:</div><textarea id="reset_wzContent" class="img_class4"  maxlength="50" autocomplete="off" placeholder="请输入内容简介"></textarea>
					<div class="img_class5">
						<div class="img_class6">标签编辑:</div><div class="img_class6_i"><i class="iconfont" style="color:#009688;cursor: pointer;font-size: 18px;" onclick="addTag()">&#xe600;</i></div>
						<div id="reseat_tags" class="img_class7">
							<!-- <div class="img_class8">
								<div class="img_class8_child1">标签1</div>
								<div class="img_class8_child2"><i id='delindex' class='layui-icon close-icon' style='font-size: 14px;color:#009688;'>&#x1007;</i></div>
							</div> -->
						</div>
					</div>
				</div>
				<div class="img_path3">
					<div class="saveMessages">保存信息</div>
				</div>
				<div class="img_path4">
					<div class="zptitle">
						<div class="zptitleChild1">相关作品</div>
						<!-- <div class="zptitleChild2">确认排序</div> -->
					</div>
					<div class="zpshow">
						<ul id="ZPShow" class="sortable scroll" style="overflow: x-hidden;">
           				 	
           				</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var element,laypage,upload,form;
	var operaPhotog;//标签
	var pageNum =12;
	var wzIdnet="";//专题文章标识
	var allTag;//所显示的标签
	var reseatARtsrc="";//更改后图片路径
	$(function(){
		adjust();
		$(window).resize(adjust);
		//滚动条部分设置
		$(".scroll").niceScroll({
			cursorcolor : "#40C5C0",//滚动条显示的颜色
			cursorborderradius: "2px",//滚动条边角圆弧
			cursorwidth: "4px",//滚动条宽度
			cursorborder: "1px solid #40C5C0",//滚动条边线
			autohidemode:true//是否自动隐藏
		});		
		//获取文章总数
		wzCounts();
		var sort = $(".sortable").sortable({
            handle: "img",
            delay: 0,
            cursor: 'move',
            revert: true,
            scroll :true,
            stop: $(".zptitleChild2").click(function (event) {
                //记录sort后的id顺序数组
                var arr = $(".sortable").sortable('toArray');
                console.log(arr);
            })
        });			
		//图片上传
		layui.use(['element','upload','form'], function(){
			element = layui.element,upload=layui.upload,form=layui.form;

			   //上传文章头像
		    var uploadInst = upload.render({
		      elem: '#test-upload-normal'
		      ,url: 'headImg_Art.do'
		      ,before: function(obj){
		        //预读本地文件示例，不支持ie8
		        obj.preview(function(index, file, result){
		          $('#test-upload-normal-img').attr('src', result); //图片链接（base64）
		        });
		        this.data={'PHOTOG_ID':wzIdnet};
		      }
		      ,done: function(res){
		        //如果上传失败
		        if(res.code == 0){
		            layer.msg('上传失败');
		        }else{
		          	layer.msg('上传成功');
		          	reseatARtsrc="getPhtotogImg_Art.do?filename="+res.filename;
		          	
		          	//$(".wz_image").find("img").attr("src",reseatARtsrc); 	
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


	     //作品
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
	           				"<img src=getPhtotogImg_Art.do?filename="+res.filename+"></img>"+
          				 	"</li>";
          			$(".sortable").append(str);
	        };
	      }
	    });	
		 });		
	});
	function wzCounts(){
		$("#showContents").html("");
		var wzName=$("#wz_serch").val();
		$.ajax({
			url:"getWzCounts.do",
			type:"post",
			dataType:"json",
			data:{wzName:wzName,pageNum:pageNum,pageSize:1},
			success:function(data){
				if (data!=null&&data.list.length!=null) {
					$("#wzCounts").html("文章总数"+data.countNum+"篇");
					layui.use(['laypage'], function(){
						laypage = layui.laypage;
						 //总页数大于页码总数
						 laypage.render({
						   elem: 'fenye'
						   ,count: data.countNum//数据总数
						  	,limit:pageNum
						  	,layout: ['count', 'prev', 'page', 'next','skip']
						   ,jump: function(obj,first){
						     console.log(obj);
						     //showdatas(data.list,obj.curr);
						     if (!first) {
						     	showdatas(obj.curr,pageNum,true,wzName);
							 }else{
							 	showdatasfirst(data.list,obj.curr);
							 }
						   }
						});	
					});
				} else {
					$("#wzCounts").html("文章总数0篇");
					laypage.render({
					    elem: 'demo0'
					    ,count: 0
					    ,limit:20
					    ,layout: ['count', 'prev', 'page', 'next','skip']
					    ,jump: function(obj){
					    	
					    }
					  });
					  $("#showContents").html("");
				}
			}
		});
	}
	function showdatasfirst(data,curr){
		for(var i=0;i<data.length;i++){
			var wzname=data[i].ARTICLE_TITLE;
			wzTags(wzname,data,i);//获取文章标签
		}
	}	
	//查找文章的标签
	function wzTags(data1,data2,data3){
		var html="";
		$.ajax({
			url:"getWzTags.do",
			type:"post",
			dataType:"json",
			data:{wzName:data1},
			success:function(data){
				var tags=data.listtag;
					html="<div id='wz_1' class='wzClass'>"+
					"<div class='wzClass1' data-ident='"+data2[data3].ARTICLE_ID+"' data-wzImageSRC='' data-wzImageAlt='"+data2[data3].ARTICLE_COVER+"' data-wzName='"+data2[data3].ARTICLE_TITLE+"' data-wzContent='"+data2[data3].ARTICLE_CONTENT+"'>"+
					"<div id='wz_image' class='wz_image'>"+
					"<img id='reseatImg' src='getPhtotogImg_Art.do?filename="+data2[data3].ARTICLE_COVER+"' alt='' ></img>"+
					"</div>"+
					"<div id='wz_name' class='wz_name'>"+data2[data3].ARTICLE_TITLE+"</div>"+
					"<div class='brief'>"+data2[data3].ARTICLE_CONTENT+"</div>"+
					"<div class='jiedu' data-ident='"+data2[data3].ARTICLE_ID+"' data-wzImageSRC='getPhtotogImg_Art.do?filename="+data2[data3].ARTICLE_COVER+"' data-wzImageAlt='' data-wzName='"+data2[data3].ARTICLE_TITLE+"' data-wzContent='"+data2[data3].ARTICLE_CONTENT+"'>"+
					"<div class='jdicon' onclick='areInterpret(this)'>查看文章</div>"+
					"<div class='jdicon' onclick='resetArt(this)'>信息修改</div>"+
					"</div>"+
					"</div>"+
					"<div class='wzClass2'>"+
					"<div class='detail1' style='border: 0px solid #F2F2F2;'>";
					for(var j=0;j<tags.length;j++){
						html+="<div class='detail2' attr-tagId='"+tags[j].LABEL_ID+"'>"+tags[j].LABEL_NAME+"</div>";
					}
					html+="</div>"+
					"</div>"+
					"</div>";
				$("#showContents").append(html);
				adjust();
			}
		});
	}
	//按文章搜索
	$("#wz_icon").click(function(){
		var wzName=$("#wz_serch").val();
		showdatas(1,pageNum,true,wzName);
	});	
	//编辑文章信息
	function resetArt(e){
		$(".resetArt").show();
		adjust();
		wzIdnet=$(e).parent().attr("data-ident");
		var wzImageSrc=$(e).parent().attr("data-wzImageSRC");
		var wzImageAlt=$(e).parent().attr("data-wzImageAlt");
		var wzName=$(e).parent().attr("data-wzName");
		var wzContent=$(e).parent().attr("data-wzContent");
		/* var para="wzImageSrc="+wzImageSrc+"&wzName="+wzName+"&wzContent="+wzContent;
		alert(para); */
		$("#test-upload-normal-img").attr("alt",wzImageAlt);
		$("#test-upload-normal-img").attr("src",wzImageSrc);
		$("#reset_wzName").val(wzName);
		$("#reset_wzContent").val(wzContent);
		$.ajax({
			url:"getWzTags.do",
			type:"post",
			dataType:"json",
			data:{wzName:wzName},
			success:function(data){
				var tags=data.listtag;
				var html="";
				for(var j=0;j<tags.length;j++){
					html+="<div id='"+tags[j].LABEL_ID+"' class='img_class8' data-tagName='"+tags[j].LABEL_NAME+"' data-tagid='"+tags[j].LABEL_ID+"'>"+
				"<div id='"+tags[j].LABEL_ID+"' class='img_class8_child1'>"+tags[j].LABEL_NAME+"</div>"+
				"<div class='img_class8_child2' onclick='deleteTag(this)'><i id='delindex' class='layui-icon close-icon' style='font-size: 14px;color:#009688;'>&#x1007;</i></div>"+
				"</div>";
				}
				$("#reseat_tags").html("");
				$("#reseat_tags").html(html);
			}
		});
		getZP(wzIdnet);
	}
	function getZP(data){
	$("#reseat_tags").html("");
		$.ajax({
			url:"getZP.do",
			type:"post",
			dataType:"json",
			data:{wzIdnet:data},
			success:function(data){
				if(data.length!=0){
					var zp=data.list;
					var html="";
					for ( var i = 0; i < zp.length; i++) {
						html+="<li id='"+zp[i].WORKS_ID+"' class='drag'>"+	
		           				"<img src=getPhtotogImg_Art.do?filename="+zp[i].FILE_NAME+" alt='"+zp[i].WORKS_NAME+"' onclick='packagedataART(this)'></img>"+
           				 	"</li>";
					}
					$("#ZPShow").html(html);
				}
			}
		});
	}
	//删除标签
	/* function delindex(e){
		var delTagid=$(e).parent().attr("id");
		$(".img_class8[id='"+delTagid+"']").remove();
	} */
	//跳转作品解读
	/* function zptag(e){
	$(".resetArt").hide();
	var articleId=$(e).parent().attr("id");
	window.location.href = "productionJiedu.do?articleId="+articleId; 
	window.location.replace("productionJiedu.do?articleId="+articleId);
	} */
	//跳转核心介绍
	/* function hxtag(){
		var wzName=$("#wz_name").text();
		var author=$("#author").text();
		var userId="pxtest";
		var para="userId="+userId+"&wzName="+encodeURI(encodeURI(wzName))+"&author="+encodeURI(encodeURI(author));
		window.location.href = "hexin.do?"+para;
		$(window.parent.document).find("#KEY").addClass("tagClass_bk");
		$(window.parent.document).find("#KEY").siblings().removeClass("tagClass_bk");
	} */
	//关闭文章信息编辑
	function imgclose(){
		$(".resetArt").hide();
		wzCounts();
	};
	//编辑标签-1
	function img_search(){
		$(".img_search_k").show();
		//标签查询
	}
	//编辑标签-2
	function resettag(e){
		var tag_name=$(e).attr("data-tagname");
		var tag_id=$(e).attr("data-tagid");
		var para="tag_name="+tag_name+"&tag_id="+tag_id;
		$(".img_search_k").hide();
	}
	//保存基本信息
	$(".saveMessages").click(function(){
		//wzIdnet;//专题文章标识
		var wzImageSrc=$("#test-upload-normal-img").attr("src");
		var wzImageAlt=$("#test-upload-normal-img").attr("alt");
		var wzName=$("#reset_wzName").val();
		var wzContent=$("#reset_wzContent").val();
		var tagId=new Array();
		var tagName=new Array();
		allTag=$("div.img_class8");
		for(var i=0;i<allTag.length;i++){
			var tagid=allTag.eq(i).attr("id");
			var tagname=allTag.eq(i).attr("data-tagName");
			tagId=tagId.concat(tagid);
			tagName=tagName.concat(tagname); 
		}
		/* var para="wzImageAlt="+wzImageAlt+"&wzName="+wzName+"&wzContent="+wzContent+"&wzImageSrc="+wzImageSrc+"&tagid="+JSON.stringify(tagId)+"&tagname="+JSON.stringify(tagName);
		alert(para); */
		$.ajax({
			url:"saveMessages.do",
			type:"post",
			dataType:"json",
			data:{wzImageSrc:wzImageSrc,wzImageAlt:wzImageAlt,wzName:wzName,wzContent:wzContent,tagid:JSON.stringify(tagId),tagname:JSON.stringify(tagName),wzIdnet:wzIdnet},
			success:function(data){
				
			}
		});
	});
	//跳转文章解读
	function areInterpret(obj){
		var wzid=$(obj).parent().attr("data-ident");
		var layId="777";
		var tableData={
			"title":"文章解读",
			"lay-id":"777",
			"src":"reading.do?articleId="+wzid+"&layId="+layId
		};
		window.parent.addTable(tableData);
	}	
	//跳转作品解读
	function packagedataART(obj){
		var articleId = $(obj).parent().attr("id");
		var layId="999";
		var tableData={
			"title":"作品解读",
			"lay-id":"999",
			"src":"reading.do?articleId="+articleId+"&layId="+layId
		};
		window.parent.addTable(tableData);
	}	
	
	//调整页面布局
	function adjust(){
		var H = $(".main").height();
		var W = $(".main").width();
		$(".showContents").height(H-30-37-50);
		$(".wzClass").width(($(".showContents").width()-15*5)/4);
		$(".wz_name").width($(".wzClass").width()-$(".wz_image").width()-15);
		$(".brief").width($(".wzClass").width()-$(".wz_image").width()-15);
		$(".brief").height($(".wzClass1").height()-$(".wz_name").height()-25);
		$(".jdicon").width(($(".wzClass").width()-$(".wz_image").width()-15-20)/2);
		$(".Artmain").css("margin-left",($(".resetArt").width()-$(".Artmain").width())/2);
		$(".img_path2").width($(".Artmain").width()-$(".img_path1").width());
		$(".img_class6_i").width($(".img_class5").width()-$(".img_class6").width()-10);
		$(".saveMessages").css("margin-left",($(".img_path3").width()-$(".saveMessages").width())/2);
	};		
</script>
</html>