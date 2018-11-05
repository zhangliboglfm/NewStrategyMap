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
<title>作品</title>

<link rel="stylesheet" href="layui/css/layui_ztf.css" type="text/css"></link>
<link rel="stylesheet" href="css/productionNew.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin:0;padding:0}
.main{
	width: 100%;height: 100%;
}
#main1{
	
}
#main2{
	background-color: #0ff;
	display: none;
}
@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1532939700896'); /* IE9*/
  src: url('iconfont.eot?t=1532939700896#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
  url('iconfont.ttf?t=1532939700896') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1532939700896#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
</head>
<body>
	<div id="main1" class="main">
		<div id="mainTitle">
			<div id="authorName">张腾飞的作品</div>
			<div id="adjustRank">
				<button class="layui-btn layui-btn2 layui-btn-normal" onclick="workAdjust();">作品调序</button>
				<!-- <button type="button" class="layui-btn layui-btn2 layui-btn-normal bnt" id="test-upload-type1">上传文件</button> -->
			</div>
		</div>
		<div id="mainContent">
			<div class="zuopinInfo"></div>
		</div>
		<div id="fenye" style="float:left;height:30px;width:99%;margin-top:15px;" align="middle"></div>
		<div id="shandow"></div>
		<div id="searchKuang">
			<div id="kuangTitle">
				<div id="titleText">请输入标签名称：</div>
				<div id="titleClose"><i class="iconfont" style="color:#000000;cursor: pointer;font-size: 16px;" onclick="closeKuang(this)">&#xe635;</i></div>
			</div>
			<div id="kuangBody">
				<input id="keyWord" placeholder="请输入标签名称" type="text" onkeyup="searchlabel()"/>
				<div id="labelList"></div>
			</div>
			<div id="kuangBottom">
				<div id="cancleAdd" class="clickButton" onclick="closeKuang(this)">取&nbsp;消</div>
				<div id="sureAdd" class="clickButton clickButton2" onclick="updateTag(this)">确&nbsp;定</div>
			</div>
		</div>
		<form id='fileform2' action='reuploadImg.do' enctype='multipart/form-data' method='post'>
			<input id='iptFile2' type='file' name='file' class='file' onchange='getFileInfo2()'/>
		</form>
	</div>
</body>
<script type="text/javascript">
	var userId = "<%=request.getAttribute("userId")%>";
	var photogid = "<%=request.getAttribute("photogid")%>";
	var photogName = "<%=request.getAttribute("photogName")%>";
	var workId="";
	var tagName="";
	var layer;
	var layer2;
	var form;
	var MyFile = new Object();
	MyFile.file = null;
	MyFile.name = null;
	var workIdR;
	var MyFile2 = new Object();
	MyFile2.file = null;
	MyFile2.name = null;
	var ourl="";
	
	$(function () {
		$(window).resize(adjust);
		adjust();
		$("#authorName").html("");
		$("#authorName").html(photogName+"的作品");
		$("#mainContent").niceScroll({
			cursorwidth : "10px",
			cursorcolor: "#009688",
			cursorborder: "0px"
		});
		fenyeSearch();
	});
	
	$("#labelList").niceScroll({
			cursorwidth : "4px",
			cursorcolor: "#009688",
			cursorborder: "0px"
	});
	
	//添加标签
	function addTag(e){
		/* $("#shandow").show();
		$("#searchKuang").show(); */
		workId=$(e).attr("data-id");
		layui.use(['layer','form'], function(){
			layer2 = layui.layer;
			form=layui.form;
			var str="<div class='layui-input-inline'>" +
						"<input type='text' id='tagName' placeholder='标签名称' value='' class='layui-input '>" +
					"</div>"+
					"<div class='layui-input-inline'>" +
						"<button class='layui-btn layui-btn' style='margin-left:5px;' onclick='getTabList()'>搜索</button>"+
					"</div>" +
					"<div id='divtags2' class='divtags2'></div>";
			layer2.open({
				type:0,
				title:'标签选择',
				content:str,
				success:function(index,layero){//成功调用后的回调函数,该回调携带两个参数，分别为当前层索引、当前层DOM对象
					layer.style(layero, {
						  width: '400px',
						  height:'300px',
						  left:($(window).width()-400)/2,
						  top:($(window).height()-300)/2,
					});
					
					$(".divtags2").niceScroll({
						 autohidemode: true,
						 cursorwidth: "2px", // 滚动条的宽度，单位：便素
						 //cursorborder: "1px solid #fff", // CSS方式定义滚动条边框
						 cursorcolor: "#009688"
					});
					getTabList();
					
					 //输入框绑定enter事件
					 $("#tagName").keydown(function(evet){
					 	if(event.keyCode==13){
					 		getTabList();
					 	};
					 });
				},
				yes:function(index,layero){
					layer.close(index);
				}
			});
		});
	};
	//获取标签池中东西
	function  getTabList(){
		var keyWord = $("#tagName").val();
		$.ajax({
			url:"searchlabel.do",
			type:"post",
			dataType:"json",
			data:{
				workId : workId,
				keyWord : keyWord
			},
			success:function(data){
				if(data!=null&&data.length!=0){
					var str="<form class='layui-form' action='' lay-filter='example'>";
					for(var i=0;i<data.labelName.length;i++){
						if(data.labelCun[i]){
							str+="<input type='checkbox' name='"+data.labelId[i]+"' title='"+data.labelName[i]+"' checked>";
						}else{
							str+="<input type='checkbox' name='"+data.labelId[i]+"' title='"+data.labelName[i]+"' >";
						};
					};
					str+="</form>";
					$("#divtags2").html(str);
					form.render();
					
					form.on('checkbox', function(data){
						var operate;
						 if(data.elem.checked){
							 operate="add";
						 }else{
							 operate="delete";
						 };
						 var LABEL_NAME=data.elem.title;
						 var labelId=data.elem.name;
						 $.ajax({
							 url:"opWorkLabel.do",
							 type:"post",
							 dataType:"json",
							 data:{workId : workId,labelId : labelId,operate:operate},
							 success:function(data){
								 if(data.flag[0]==1){
									 if(operate=="add"){
										 var str="<div class='phototag' data-tagid='"+labelId+"'><span>"+LABEL_NAME+"</span><i class='layui-icon close-icon' onclick='deleteTag(this)'>&#x1007;</i></div>";
										 $("#tagDiv"+workId).append(str);
									 }else{
										 $("#tagDiv"+workId).find("div[data-tagid='"+labelId+"']").remove(); 
									 }
								 }
							 }
						 });
					});
				};
			}
		});
	};
	//删除次标签
	function deleteTag(obj){
		var opWorkId=$(obj).parent().parent().attr("data-id");
		$.ajax({
			 url:"opWorkLabel.do",
			 type:"post",
			 dataType:"json",
			 data:{workId : opWorkId,labelId : $(obj).parent().attr("data-tagid"),operate:"delete"},
			 success:function(data){
				 if(data.flag[0]==1){
					 $(obj).parent().remove();
				 }
			 }
		 });
	};
	//关闭搜索框
	function closeKuang(e) {
		$("#keyWord").val("");
		$("#shandow").hide();
		$("#searchKuang").hide();
	}
	//添加标签
	function updateTag(e) {
		tagName=$("#keyWord").val();
		$("#tagDiv"+workId).append("<div class='phototag'><span>"+tagName+"</span><i class='layui-icon close-icon' onclick='deleteTag(this)'>&#x1007;</i></div>");
		$("#shandow").hide();
		$("#searchKuang").hide();
	}
	//查询总页数及分页部分
	function fenyeSearch() {
		$.ajax({
			url:"searchFenye.do",
			type:"post",
			dataType:"json",
			data:{
				photogid : photogid
			},
			success:function(data){
				layui.use(['laypage'], function(){
					laypage = layui.laypage;
					 //总页数大于页码总数
					 laypage.render({
					   elem: 'fenye',
					   count: data,//数据总数
					   limit:12,
					   jump: function(obj){
						     showdatas(obj.curr);
						}
					});	
				});
			}	
		});
	}
	//数据展示
	function showdatas(curr){
		$.ajax({
			url:"searchImageInfo.do",
			type:"post",
			dataType:"json",
			data:{
				photogid : photogid,
				curr : curr
			},
			success:function(data){
				var htmlCode="";
				for(var a = 0;a<data.workId.length;a++){
					var isorno = "否";
					if (data.isRepre[a]==1) {
						isorno="是";
					}
					htmlCode+="<div class='zuopinInfo'><div class='zuopinLeft'>"+
							"<div class='imageDiv' data-id='"+data.workId[a]+"' onclick='reuploadImg(this);'><img id='img"+data.workId[a]+"' class='zuopinImage2' src='getImage.do?imgUrl="+data.fileName[a]+"'></img>"+
							"</div>"+
							"<div class='tagDiv'>"+
							"<label class='photolabel'>&nbsp;&nbsp;&nbsp;标签:</label><i class='iconfont' style='color:#009688;cursor: pointer;font-size: 16px;' data-id='"+data.workId[a]+"' onclick='addTag(this)'>&#xe600;</i>"+
							"<div class='phototags' data-id="+data.workId[a]+" id='tagDiv"+data.workId[a]+"'>";
					for(var t = 0;t<data.workLabel[a].labelName.length;t++){
						htmlCode+="<div class='phototag' data-tagid='"+data.workLabel[a].labelId[t]+"'><span>"+data.workLabel[a].labelName[t]+"</span><i class='layui-icon close-icon' onclick='deleteTag(this)'>&#x1007;</i></div>";
					}
					htmlCode+="</div></div></div>";
					htmlCode+="<div class='zuopinRight'>"+
							"<div class='zpDetail'>"+
							"<label class='photolabel'>名称:</label><input class='photoinput' type='text' id='name"+data.workId[a]+"'  value="+data.workName[a]+" autocomplete='off' placeholder='请输入名称'>"+
							"</div>"+
							"<div class='zpDetail'>"+
							"<label class='photolabel'>类型:</label><input class='photoinput' type='text' id='type"+data.workId[a]+"'  value="+data.workType[a]+" autocomplete='off' placeholder='请输入类型'>"+
							"</div>"+
							"<div class='zpDetail'>"+
							"<label class='photolabel'>日期:</label><input class='photoinput' type='text' id='time"+data.workId[a]+"'  value="+data.shootTime[a]+" autocomplete='off' placeholder='YYYYMMDD'>"+
							"</div>"+
							"<div class='zpDetail'>"+
							"<label class='photolabel'>代表作:</label><input class='photoinput' type='text' id='daibz"+data.workId[a]+"'   value="+isorno+" autocomplete='off' placeholder='是或否'>"+
							"</div>"+
							"<div class='zpDetail'>"+
							"<label class='photolabel'>解读:</label><input class='photoinput' type='text' id='jiedu"+data.workId[a]+"'  value="+data.workJiedu[a]+" autocomplete='off' placeholder='解读文章名称'>"+
							"</div>";
					htmlCode+="<div class='zpDetail zuopinjiedu' onclick='jieduFF(this)' data-aName='"+data.workName[a]+"' data-aId='"+data.articleId[a]+"'>作品解读</div>"+
								"<div class='shenheTijiao'>"+
								"<div id='tijiao' class='shtjClass tijiaoshenhe'>"+
								"<button class='layui-btn layui-btn-normal' onclick='commitInfo(this)'  data-id='"+data.workId[a]+"'>提交审核</button>"+
								"</div>"+
								"<div id='shenhe' class='shtjClass shtjClass2 shenheworks'>"+
								"<div id='yijianDiv'><textarea id='yijian' name='desc' placeholder='请输入审核意见' class='layui-textarea'></textarea></div>"+
								"<button class='layui-btn layui-btn-normal' onclick='sureNot()' data-flag='1'  data-id='"+data.workId[a]+"'>通过</button>"+
								"<button class='layui-btn layui-btn-normal' onclick='sureNot()' data-flag='2'  data-id='"+data.workId[a]+"'>驳回</button>"+
								"</div></div></div></div>";
				}
				$("#mainContent").html("");
				$("#mainContent").html(htmlCode);
				if (userId=="admin") {
					$(".shenheworks").show();
					$(".tijiaoshenhe").hide();
				} else {
					$(".shenheworks").hide();
					$(".tijiaoshenhe").show();
				}
				adjust();
				$(".phototags").niceScroll({
					cursorwidth : "10px",
					cursorcolor: "#009688",
					cursorborder: "0px"
				});
			}	
		});
	}
	//搜索标签名称
	function searchlabel() {
		$("#labelList").show();
		var keyWord=$("#keyWord").val();
		$("#searchKuang").css("height","300px");
		$("#kuangBody").css("height","220px");
		$.ajax({
			url:"searchlabel.do",
			type:"post",
			dataType:"json",
			data:{
				keyWord : keyWord
			},
			success:function(data){
				var labelCode = "";
				for(var a = 0;a<data.labelName.length;a++){
					labelCode+="<span class='classify'>"+data.labelName[a]+"</span>";
				}
				$("#labelList").html("");
				$("#labelList").html(labelCode);
				$(".classify").click(function () {
					tagName=$(this).text();
					$("#keyWord").val(tagName);
					$("#labelList").hide();
					$("#searchKuang").css("height","140px");
					$("#kuangBody").css("height","60px");
				});
			}	
				
		});
	}
	//跳转作品解读
	function jieduFF(e) {
		//data-aId='"+data.articleId[a]
		var articleId = $(e).attr("data-aId");
		var articleName = $(e).attr("data-aName");
		var tableData={
			"title":articleName+"作品解读",
			"lay-id":"productJiedu",
			"src":"productionJiedu.do?articleId="+articleId+"&photogid="+photogid+"&flagId=WI"
		};
		window.parent.addTable(tableData);
	}
	//提交审核
	function commitInfo(e) {
		var workId=$(e).attr("data-id");
		var changeName=$("#name"+workId).val();
		var changeType=$("#type"+workId).val();
		var changeTime=$("#time"+workId).val();
		var changeDaibz=$("#daibz"+workId).val();
		var changeJiedu=$("#jiedu"+workId).val();
		$.ajax({
			url:"updateShenhe.do",
			type:"post",
			dataType:"json",
			data:{
				workId : workId,
				changeName : changeName,
				changeType : changeType,
				changeTime : changeTime,
				changeDaibz : changeDaibz,
				changeJiedu : changeJiedu
			},
			success:function(data){
				if (data.flag[0]==1) {
					alert("提交审核成功！");
				}
			}	
				
		});
	}
	//审核通过
	function sureNot(e) {
		var flag=$(e).attr("data-flag");
		var yijian = $("#yijian").val();
		if (flag==1) {
		} else {
			if (yijian.trim()=="") {
				alert("请输入审批意见！")
			} else {
				alert("审核已驳回");
			}
		}
	}
	//作品调序
	function workAdjust() {
		var str="<div id='inputDiv' class='layui-input-inline'>" +
						"<input type='text' id='tagName' placeholder='请选择调序文件' value='' class='layui-input ' readonly='readonly' onclick='uploadExcel()'>" +
				"</div>"+
				"<div id='bntDiv'><button id='uploadBnt' class='layui-btn layui-btn-normal' style='margin-left:5px;' onclick='uploadExcel()'>选择文件</button></div>"+
				"<div id='divtags' class='divtags'>" +
					"<button class='layui-btn layui-btn-normal bnt' style='margin-left:5px;' onclick='downloadExcel()'>下载顺序文件</button>" +
					"<button class='layui-btn layui-btn-normal bnt' style='margin-left:5px;' onclick='startUpload()'>上传顺序文件</button>" +
					"<form id='fileform1' action='uploadExcel.do' enctype='multipart/form-data' method='post'>"+
						"<input id='iptFile' type='file' name='file' class='file' accept='.xls,.xlsx' onchange='getFileInfo()'/>"+
				"</form></div>";
		layui.use(['layer'], function(){
			layer = layui.layer;
			layer.open({
				type:1,
				title:'标签选择',
				content:str,
				success:function(index,layero){//成功调用后的回调函数,该回调携带两个参数，分别为当前层索引、当前层DOM对象
					layer.style(layero, {
						  width: '320px',
						  height:'160px',
						  left:($(window).width()-320)/2,
						  top:($(window).height()-160)/2,
					});
				},
				yes:function(index,layero){
					layer.close(index);
				}
			});
		});
	}
	function downloadExcel() {
		window.open("downLoad1.do?photogid="+photogid);
	}
	function uploadExcel() {
		$("#iptFile").click();
	}
	//显示文件名称
	function getFileInfo(){
		var filedom = document.getElementById("iptFile");
		var file = filedom.files["0"];
		$("#tagName").val(file.name);
		MyFile.file = file;
		MyFile.name = file.name;
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
		var filename = MyFile.name;
		var leixing = filename.substring(filename.lastIndexOf(".") + 1);
		var options = {
			url:"uploadExcel.do",
			async:false,
			type:"post",
			dataType:"json",
			data:{
				leixing:leixing
			},
	        success: function (data) {
	        	alert("已上传最新作品排序！");
	        	layer.closeAll();
	       	}
		};
	    $("#fileform1").ajaxSubmit(options);
	}
	//重新上传作品图片
	function reuploadImg(e) {
		workIdR=$(e).attr("data-id");
		$("#iptFile2").click();
	}
	function getFileInfo2(){
		var filedom = document.getElementById("iptFile2");
		var file = filedom.files["0"];
		MyFile2.file = file;
		MyFile2.name = file.name;
		if(MyFile2.file == null){
			alert("请先选择文件！");
			return false;
		}
		uplaodFile2();
	}
	/**
	 * 上传文件
	 */
	function uplaodFile2(){
		var file = MyFile2.file;
		var filename = MyFile2.name;
		filename = encodeURI(encodeURI(filename));
		var leixing = filename.substring(filename.lastIndexOf(".") + 1);
		var options = {
			url:"reuploadImg.do",
			async:false,
			type:"post",
			dataType:"json",
			data:{
				leixing:leixing,
				filename : filename,
				workIdR : workIdR,
				photogid:photogid
			},
	        success: function (data) {
	        	if (data.flag[0]!=1) {
					alert("重新上传图片失败");
				} else {
					//document.location.reload();
					$("#img"+workIdR).attr("src","getImage.do?imgUrl="+data.imgPath[0]);
				}
	       	}
		};
	    $("#fileform2").ajaxSubmit(options);
	}
	function adjust() {
		var winWidth=document.body.clientWidth;
		var winHeight=document.body.clientHeight;
		var height =$("#main1").height()-$("#mainTitle").height()-60;
		$("#mainContent").css({"height":height+"px","max-height":height+"px"});
		$(".zuopinInfo").width(($("#mainContent").width()-40)/4);
		$(".zuopinInfo").height($("#mainContent").height()*2/3);
		$(".zuopinRight").width($(".zuopinInfo").width()*0.4-12);
		$(".imageDiv").width($(".zuopinLeft").width()-10);
		$(".imageDesc").width($(".imageDiv").width());
		$(".tagDiv").width($(".imageDiv").width());
		$(".tagDiv").height($(".zuopinLeft").height()-$(".imageDiv").height()-22);
		$("#searchKuang").css("left",(winWidth-250)/2);
		$("#searchKuang").css("top",(winHeight-150)/2);
		$(".shenheTijiao").height($(".zuopinRight").height()-$(".zpDetail").height()*6-40);
		$("#commitInfo").css("margin-left",($("#tijiao").width()-100)/2);
		$(".shenheB").css("margin-left",($("#tijiao").width()-100)/2);
	}
</script>
</html>