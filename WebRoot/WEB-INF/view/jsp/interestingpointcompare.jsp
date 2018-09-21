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

<title>2017河北移动战略地图兴趣点对比</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!-- js引入 -->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="plugin/jedate/jedate.js"></script>
<script type="text/javascript" src="plugin/selectlist/jquery.nicescroll.js"></script>


<!-- CSS引入 -->
<link rel="stylesheet" href="css/interestingpointcompare/interestingpointcompare.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/jedate/skin/jedate-common.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/jedate/skin/jedate.css" type="text/css"></link>


<style type="text/css">
@font-face {font-family: "iconfont";
  src: url('css/iconfont/iconfont.eot?t=1497250656883'); /* IE9*/
  src: url('css/iconfont/iconfont.eot?t=1497250656883#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('css/iconfont/iconfont.woff?t=1497250656883') format('woff'), /* chrome, firefox */
  url('css/iconfont/iconfont.ttf?t=1497250656883') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('css/iconfont/iconfont.svg?t=1497250656883#iconfont') format('svg'); /* iOS 4.1- */
}
.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
@font-face {font-family: "selIconfont";
  src: url('plugin/selectlist/css/selectlist.iconfont.eot?t=1488875403519'); /* IE9*/
  src: url('plugin/selectlist/css/selectlist.iconfont.eot?t=1488875403519#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('plugin/selectlist/css/selectlist.iconfont.woff?t=1488875403519') format('woff'), /* chrome, firefox */
  url('plugin/selectlist/css/selectlist.iconfont.ttf?t=1488875403519') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('plugin/selectlist/css/selectlist.iconfont.svg?t=1488875403519#selIconfont') format('svg'); /* iOS 4.1- */
}
.selIconfont {
  font-family:"selIconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; 
}
*{font-family: Microsoft YaHei;font-size: 14px;}
html{width: 100%;height: 100%;}
body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0;}
</style>
</head>

<body>
	<div class="topnav">
		<div class="title">兴趣点对比</div>
	</div>
	<div class="content">
		 <div  class="first">
			<div class="datetype">
				<div id="divDayType" class="eachdatetype cur_eachdatetype" datetype="D" onclick="ChangeDateType(this)">日</div>
				<div id="divMonthType" class="eachdatetype" datetype="M" onclick="ChangeDateType(this)">月</div>
			</div>
			<div class="div_datepiker1">
				<input id="datepiker" class="inputdate" type="text" readonly="readonly"/>
				<i id="iconDatepiker" class="iconfont">&#xe610;</i>
			</div>
		</div>
		 <div class="second">
			<div class="second-one" onclick="exportdata()"><i class="iconfont" style="color:#00AFFF">&#xe74c;</i>&nbsp;导出</div>
			<div class="divwidthnice">
		 				<table class="table1">
		 				</table>
			</div>

		</div>
	 </div> 
</body>
<script type="text/javascript">
	var date="<%=request.getAttribute("date")%>"
	var datetype="<%=request.getAttribute("datetype")%>"
	var indexid="<%=request.getAttribute("indexid")%>"
	var regionid="<%=request.getAttribute("regionid")%>"
	var result=<%=request.getAttribute("result")%>
	var maxDateD="<%=request.getAttribute("maxDateD")%>"
	var maxDateM="<%=request.getAttribute("maxDateM")%>"
	/*日期类型  日周期为D  月周期 为 M*/
	var dateD="";
	var dateM="";
	if(datetype=="M"){
		dateM=date.substring(0,4)+"."+date.substring(4,6)
		dateD=maxDateD.substring(0,4)+"."+maxDateD.substring(4,6)+"."+maxDateD.substring(6,8);
	}else{
		dateD=date.substring(0,4)+"."+date.substring(4,6)+"."+date.substring(6,8);
		dateM=maxDateM.substring(0,4)+"."+maxDateM.substring(4,6);
	}
	/* 日期选择控件配置JSON */
	var jedateoption = {
		dateCell:"#datepiker",
		format:"YYYY.MM.DD",
		choosefun:function(elem, datas) {
		switchdata();
		}
	};
	/* 日期控件初始化 */
	if(datetype=="M"){
		$("#datepiker").val(dateM);
		var obj=$(".cur_eachdatetype");
		obj.removeClass("cur_eachdatetype");
		obj.siblings().addClass("cur_eachdatetype");
	}else{
		$("#datepiker").val(dateD);
	}
	jeDate(jedateoption);
		
		/*延迟加载---------------------------------------------------*/
	$(function(){
		//调整页面布局
		$("#iconDatepiker").click(function(){
				jeDate(jedateoption);
			});
		  $(".divwidthnice").niceScroll({
			cursorcolor : "#00AFFF",//滚动条显示的颜色
			cursorborderradius: "0px",//滚动条边角圆弧
			cursorwidth: "3px",//滚动条宽度
			cursorborder: "0px solid #fff",//滚动条边线
			autohidemode:false//是否自动隐藏
		}); 
		var indexLength=result[0].Indexvalue.length
		/*调整表格的宽度*/
		$(".table1").css("width",253*indexLength+254);
		$(".table1").html(tabledata(result));
		
	});
	
	
	/* 切换日期类型 */
	function ChangeDateType(e){
		$(".cur_eachdatetype").removeClass("cur_eachdatetype");
		$(e).addClass("cur_eachdatetype");
		datetype=$(e).attr("datetype");
		if(datetype=="D"){
			jedateoption.format="YYYY.MM.DD";
			$("#datepiker").val(dateD);
			
		}else if(datetype=="M"){
			jedateoption.format="YYYY.MM";
			$("#datepiker").val(dateM);
		}
		switchdata();
	}
	
	/*动态拼接表格内容*/
	
	function tabledata(data){
		var str="";
		/*拼接表头*/
			str+="<tr class='biaotou'>"+
		 			"<td></td>";
		 	var obj=data[0].Indexvalue;
		 	for (var i=0;i<obj.length;i++){
		 		if(i==(obj.length-1)){
		 			str+="<td class='thelast'>"+obj[i].indexName+"</td>"
		 		}else{
		 			str+="<td >"+obj[i].indexName+"</td>"
		 		}
		 	};
		 	 /*拼接表格数据*/
		 	 for (var i=0;i<data.length;i++){
		 		if((i%2)==0){
		 			str+="<tr class='biaoge hasback'>";
		 		}else{
		 			str+="<tr class='biaoge'>";
		 		};
		 		 str+="<td  class='hascolor'>"+data[i].name+"</td>";
		 		var obj1=data[i].Indexvalue;
		 		 for(j=0;j<obj1.length;j++){
		 		 	if(j==(obj1.length-1)){
		 		 		str+="<td class='thelast'>";
		 		 	}else{
		 		 		str+="<td>";
		 		 	}
		 			str+=obj1[j].indexValue+obj1[j].indexUnit+"<br>"+
		 			"<font color='#6C6C6C'> 环比为："+(obj1[j].indexRate==999 ? "--":obj1[j].indexRate)+"%</font>";
		 			if(obj1[j].indexRateType=="up"){
		 				str+="<i class='iconfont' style='color:green'>&nbsp&#xe603;</i>";
		 			}else if(obj1[j].indexRateType=="down"){
		 				str+="<i class='iconfont' style='color:red'>&nbsp&#xe605;</i>";
		 			}
		 			str+="</td>";
		 		}  
		 		str+="</tr>";
		 	}
		 	return str; 
	}
	
	function switchdata(){
			$.ajax({
				url:"switchdata.do",
				type:"post",
				dataType:"json",
				data:{"datetype":datetype,"date":$("#datepiker").val().replace(".","").replace(".",""),"regionid":regionid,
			"indexid":indexid},
				success:function(data){
					if(data.length!=0){
						$(".table1").html(tabledata(data));
					}
					
				}
			});
	}
	
	function exportdata(){
		var pointId=encodeURI(JSON.stringify(regionid));
		var date=$("#datepiker").val().replace(".","").replace(".","");
		var date1=encodeURI(date);
		window.open("interestingPointCompareDowload.do?regionid="+pointId+"&datetype="+datetype+"&date="+date1+"&indexid="+indexid);
	
	}
</script>
</html>