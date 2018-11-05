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
<title>APP日程</title>
<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery1.10.3-ui.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="js/fullcallendar/moment.min.js"></script>
<script type="text/javascript" src="js/fullcallendar/fullcalendar.min.js"></script>
<script type="text/javascript" src="js/fullcallendar/zh-cn.js"></script>
<script type="text/javascript" src="layui/layui.all.js"></script>
<script type="text/javascript" src="js/jscommon.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin: 0;padding: 0}
#main{width: 96%;height: 100%;float: left;margin-left: 2%;}
@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1533979646640'); /* IE9*/
  src: url('iconfont.eot?t=1533979646640#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
  url('iconfont.ttf?t=1533979646640') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1533979646640#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>

<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>
<link rel="stylesheet" href="js/fullcallendar/fullcalendar.min.css" type="text/css"></link>
<link rel="stylesheet" href="css/appsetting.css" type="text/css"></link>
<link rel="stylesheet" href="layui/css/layui_ztf.css" type="text/css"></link>

</head>
<body>
	<div id="main"></div>
	<div id="shadow"></div>
	<div id="addWindow">
		<div id="settingTitle"></div>
		<div id="selectedList">
			<div class="selectTitle">已选摄影家列表 :&nbsp;<a id="selectPhg"></a></div>
			<div id="photogList" class="selectCont"></div>
			<div class="selectTitle">已选文章列表 :&nbsp;<a id="selectArt"></a></div>
			<div id="artList" class="selectCont"></div>
		</div>
		<div class="optionList">
			<div class="optionTitle">备选摄影家&nbsp;:&nbsp;</div>
			<div class="searchDiv">
				<label class="layui-form-label nameLabel">摄影家名称&nbsp;:&nbsp;</label>
				<div class="layui-input-inline">
			       <input type="text" id="serchPhotogName" autocomplete="off" class="layui-input">
			    </div>
			    <div class="layui-input-inline">
			       <button class="layui-btn layui-btn-normal" onclick="searchPhotog()" style="margin-top: 1px !important;">检索</button>
			    </div>
			</div>
			<div id="phgListMain" class="listMain"></div>
			<div id="phgPage" class="pageDiv"></div>
		</div>
		<div class="optionList">
			<div class="optionTitle">备选文章&nbsp;:&nbsp;</div>
			<div class="searchDiv">
				<label class="layui-form-label nameLabel">文章名称&nbsp;:&nbsp;</label>
				<div class="layui-input-inline">
			       <input type="text" id="serchArticleName" autocomplete="off" class="layui-input">
			    </div>
			    <div class="layui-input-inline">
			       <button class="layui-btn layui-btn-normal" onclick="searchArticle()" style="margin-top: 1px !important;">检索</button>
			    </div>
			</div>
			<div id="artListMain" class="listMain"></div>
			<div id="artPage" class="pageDiv"></div>
		</div>
		<div class="bntList">
			<button class="layui-btn layui-btn-normal bnt" onclick="sureOper()">确定</button>
			<button class="layui-btn layui-btn-normal bnt" onclick="cancleOper()">取消</button>
		</div>
	</div>
</body>
<script type="text/javascript">
	//点击的文章作品ID
	var appId = "<%=request.getAttribute("appId")%>";
	var form = layui.form,layer = layui.layer,laypage = layui.laypage;
	var events=[];
	var phgList=[];//摄影家ID集合
	var artList=[];//文章ID集合
	var nowDate="";
	var date1="";//点击的日期
	$(function () {
		adjust();
		getNowDate();
		buildCalendar();
	});
	function buildCalendar() {
		$("#main").fullCalendar({
            header:{
               left: 'prevYear,prev'
				,center: 'title'
				,right: 'next,nextYear'
            },
			buttonIcons: true, //
			weekNumbers: true,
			weekNumberCalculation:"",
			eventLimit: true, // allow "more" link when too many events
			firstDay:'1',
			handleWindowResize:'true',
			editable: true,
            height : window.innerHeight-20,  
		    windowResize: function(view) {  
		        $("#main").fullCalendar('option', 'height', window.innerHeight-20);  
		    },
		    dayClick: function(date, jsEvent, view) {
				date1=date.format();
				var j1=jsEvent;
				var v1=view;
				date1=date1.replace("-","").replace("-","");
				if (nowDate>=date1) {
					layer.msg("配置日期必须在今日之后");
				} else {
					$.ajax({
			        	url:"getlistbydate.do",
			        	data:{
			        		date : date1,
			        		appId : appId
			        	},
			        	dataType:"json",
			        	success:function(data){
			        		$("#shadow").show();
							$("#addWindow").show();
			        		var photogCode="";
			        		var artCode="";
			        		phgList=[];
			        		artList=[];
			        		for(var i=0;i<data.pgID.length;i++){
			        			phgList.push(data.pgID[i]);
			        			photogCode+="<div class='phglist'>"+data.pgName[i]+"&nbsp;&nbsp;&nbsp;<i class='iconfont iconClass' style='color: #525050;font-size: 14px;cursor: pointer;' data-id='"+data.pgID[i]+"' onclick='removePhg(this)'>&#xe635;</i>&nbsp;&nbsp;</div>";
			        		}
			        		if(data.artId.length!=0){
			        			artList.push(data.artId[0]);
			        			artCode+="<div class='phglist'>"+data.artName[0]+"&nbsp;&nbsp;&nbsp;<i class='iconfont iconClass' style='color: #525050;font-size: 14px;cursor: pointer;' data-id='"+data.artId[0]+"' onclick='removeImg(this)'>&#xe635;</i>&nbsp;&nbsp;</div>";
			        		}
			        		$("#photogList").html("");
			        		$("#photogList").html(photogCode);
			        		$("#artList").html("");
			        		$("#artList").html(artCode);
			        		$("#selectPhg").text(phgList.length+"/3");
			        		$("#selectArt").text(artList.length+"/1");
			        		searchPhotog();
							searchArticle();
			        	}
			        });
				}
			},
			events: function(start, end, timezone, callback) {
				$.ajax({
					 url:"makeCalendar.do",
					 type:"post",
					 dataType:"json",
					 data:{appId : appId,start : start.format(),end : end.format()},
					 success:function(data){
					 	var events=[];
					 	var cont=[];
					 	var showDate=[];
					 	for(var i=0;i<data.photogName.length;i++){
					 		events.push({
			                     title : data.photogName[i],  
			                     start : data.showDateP[i],
			                     backgroundColor : "#fcaf17",
			                     borderColor : "#fcaf17",
			                     color  :"#fcaf17",
			                     textColor : "#FFF",
			                     className : "photogStyle",
			                     order : data.showOrderP[i],
			                 });
			                 //cont.push(data.photogName[i]);
			                 //showDate.push(data.showDateP[i]);
					 	}
					 	for(var i=0;i<data.artTitle.length;i++){
					 		events.push({
			                     title : data.artTitle[i],  
			                     start : data.showDateA[i],
			                     backgroundColor : "#2f8d3d",
			                     borderColor : "#2f8d3d",
			                     color  :"#fcaf17",
			                     textColor : "#FFF",
			                     className : "artStyle",
			                     order : data.showOrderA[i]
			                 });
			                 //cont.push(data.artTitle[i]);
			                 //showDate.push(data.showDateA[i]);
					 	}
					 	/* for(var a=0;a<cont.length;a++){
					 		events.push({
			                     title : cont[a],  
			                     start : showDate[a],
			                     backgroundColor : "#2f8d3d",
			                     borderColor : "#2f8d3d",
			                     color  :"#fcaf17",
			                     textColor : "#FFF",
			                     className : "artStyle",
			                 });
					 	} */
					 	callback(events);
					 }
				 });
			},
        });
	}
	//获取当前系统时间
	function getNowDate() {
		var date=new Date();
		var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        nowDate=year+month+strDate;
        $("#settingTitle").html("APP配置 > "+ nowDate);
	}
	//查摄影家列表
	function searchPhotog(){
		var currPage = 1;
		var pageSize=6;
		loadphotoglist(currPage,pageSize);
	}
	function loadphotoglist(currPage,pageSize){
		var pgName=$("#serchPhotogName").val();
		var url="getAppPgList.do";
		var parameter={"pgName":pgName,"currPage":currPage,"pageSize":pageSize};
		ajaxRequest(url, parameter, function(data){
			var counts = data.counts;
			var dataList = data.dataList;
			var html="";
			for(var i=0;i<dataList.length;i++){
				var item = dataList[i];
				var id=item.id;
				var name=item.name;
				var order=item.order;
				html+="<div class='phgmainInfo' data-id='"+id+"' data-name='"+name+"' onclick='addphotog(this)'>"+
						"<div class='imageClass'><img src='selphotoghead.do?pgid="+id+"'></img></div>"+
						"<div class='imageTitle'>"+name+"</div>"+
						"</div>";
			}
			$("#phgListMain").html("");
			$("#phgListMain").html(html);
			laypage.render({
			    elem: 'phgPage'
			    ,count: counts
			    ,curr: currPage || 1 //当前页
			    ,limit: 6
			    //,limits: [12, 18, 24, 30, 36]
			    ,layout: ['count', 'prev', 'page', 'next', 'skip']
			    ,jump: function(obj, first){
			        if(!first){
			        	var currPage=obj.curr;
						loadphotoglist(obj.curr,obj.limit);
					}
			    }
			});
		});
	}
	//查文章列表
	function searchArticle(){
		var currPage = 1;
		var pageSize=6;
		loadartlist(currPage,pageSize);
	}
	function loadartlist(currPage,pageSize){
		var artName=$("#serchArticleName").val();
		var url="getAppArtList.do";
		var parameter={"artName":artName,"currPage":currPage,"pageSize":pageSize};
		ajaxRequest(url, parameter, function(data){
			var counts = data.counts;
			var dataList = data.dataList;
			var html="";
			for(var i=0;i<dataList.length;i++){
				var item = dataList[i];
				var id=item.id;
				var name=item.name;
				var cover=item.cover;
				html+="<div class='phgmainInfo' data-id='"+id+"' data-name='"+name+"' onclick='addarticle(this)'>"+
						"<div class='imageClass'><img src='getArtImage.do?imgUrl="+cover+"&imgFlag=1'></img></div>"+
						"<div class='imageTitle'>"+name+"</div>"+
						"</div>";
			}
			$("#artListMain").html("");
			$("#artListMain").html(html);
			laypage.render({
			    elem: 'artPage'
			    ,count: counts
			    ,curr: currPage || 1 //当前页
			    ,limit: 6
			    //,limits: [12, 18, 24, 30, 36]
			    ,layout: ['count', 'prev', 'page', 'next', 'skip']
			    ,jump: function(obj, first){
			        if(!first){
			        	var currPage=obj.curr;
						loadartlist(obj.curr,obj.limit);
					}
			    }
			});
		});
	}
	
	//移除已选摄影家
	function removePhg(e) {
		var deleteId=$(e).attr("data-id");
		phgList.remove(deleteId);
		$(e).parent().remove();
		$("#selectPhg").text(phgList.length+"/3");
	}
	//移除已选作品
	function removeImg(e) {
		var deleteId=$(e).attr("data-id");
		artList.remove(deleteId);
		$(e).parent().remove();
		$("#selectArt").text(artList.length+"/1");
	}
	//取消操作
	function cancleOper() {
		$("#shadow").hide();
		$("#addWindow").hide();
	}
	
	function addphotog(e) {
		var pgName=$(e).attr("data-name");
		var pgId=$(e).attr("data-id");
		if (phgList.length>=3) {
			layer.msg("只能选择3个摄影家");
		} else {
			phgList.push(pgId);
			var phgCode="<div class='phglist'>"+pgName+"&nbsp;&nbsp;&nbsp;<i class='iconfont iconClass' style='color: #525050;font-size: 14px;cursor: pointer;' data-id='"+pgId+"' onclick='removePhg(this)'>&#xe635;</i>&nbsp;&nbsp;</div>";
			$("#photogList").append(phgCode);
			$("#selectPhg").text(phgList.length+"/3");
		}
	}
	function addarticle(e) {
		var pgName=$(e).attr("data-name");
		var pgId=$(e).attr("data-id");
		if (artList.length>=1) {
			layer.msg("只能选择1个作品");
		} else {
			artList.push(pgId);
			var phgCode="<div class='phglist'>"+pgName+"&nbsp;&nbsp;&nbsp;<i class='iconfont iconClass' style='color: #525050;font-size: 14px;cursor: pointer;' data-id='"+pgId+"' onclick='removePhg(this)'>&#xe635;</i>&nbsp;&nbsp;</div>";
			$("#artList").append(phgCode);
		    $("#selectArt").text(artList.length+"/1");
		}
	}
	//数组移除某一个特定元素
	Array.prototype.indexOf = function (val) {
	    for (var i = 0; i < this.length; i++) {
	        if (this[i] == val) return i;
	    }
	    return -1;
	};
	
	Array.prototype.remove = function (val) {
	    var index = this.indexOf(val);
	    if (index > -1) {
	        this.splice(index, 1);
	    }
	};
	function sureOper() {
		if (phgList.length!=3) {
			layer.msg("请选择3个摄影家");
		} else if (artList.length!=1) {
			layer.msg("请选择1个文章");
		} else {
			$.ajax({
				 url:"sureOper.do",
				 type:"post",
				 dataType:"json",
				 data:{appId : appId,phgList : JSON.stringify(phgList),artList : JSON.stringify(artList),date1 : date1},
				 success:function(data){
				 	$("#shadow").hide();
					$("#addWindow").hide();
					buildCalendar();
				 }
			 });
		}
	}
	//生成日历
	function makeCalendar() {
		
	}
	function adjust() {
		$("#addWindow").css("left",($("#shadow").width()-$("#addWindow").width())/2);
		$("#addWindow").css("top",($("#shadow").height()-$("#addWindow").height())/2);
	}
</script>
</html>