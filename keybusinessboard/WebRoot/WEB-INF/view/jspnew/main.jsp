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

<title>全渠道产品变更发展态势</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="plugin/jedate/skin/darkblue.css" type="text/css"></link>
<link rel="stylesheet" href="css/main/mainnew.css" type="text/css"></link>
<link rel="stylesheet" href="plugin/selectlist/css/selectlist.default.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="css/pagetransition/animations.css" />
<link href="css/cicular.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>

<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/pagetransition/modernizr.custom.js"></script>
<script type="text/javascript" src="js/miapsoft.date.js"></script>
<script type="text/javascript" src="plugin/jedate/jedate.js"></script>
<script type="text/javascript" src="plugin/selectlist/jquery.nicescroll.js"></script>
<script type="text/javascript" src="plugin/selectlist/miapsoft.autohide.js"></script>
<script type="text/javascript" src="plugin/selectlist/miapsoft.selectlist.core.js"></script>
<script type="text/javascript" src="js/circular.min.js"></script>

<style type="text/css">
@font-face {
	font-family: "LCD7";
	src: url('css/LCD7/Pixel LCD-7.ttf'); /* IE9*/
}

@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1511494928181'); /* IE9*/
  src: url('iconfont.eot?t=1511494928181#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
  url('iconfont.ttf?t=1511494928181') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1511494928181#iconfont') format('svg'); /* iOS 4.1- */
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
  font-size:20px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
*{font-family: Microsoft YaHei;}
html{width:1920px;height:1080px;}
body{width:1920px;height:1080px;margin: 0;padding: 0;}
.main{width: 100%;height: 100%;background:url('image/newImage/bg.png');background-size: 100% 100%;position:relative;}
.main .top{
width: 100%;
height: 100px;
background-size: 100% 100%;
position: absolute;
z-index:999 !important;
border-bottom: 1px solid #0E1F4A;
}
.main .top .companytitle{
top:16px;
left:20px;
width:527px;
height: 67px;
color:white;
font-size:40px;
line-height:40px;
text-indent:56px;
background:url('image/newImage/bg1.png');
background-size: 100% 100%;
position: absolute;
z-index:999 !important;
border-bottom: 1px solid #0E1F4A;
}
.pt-perspective {
	position: relative;
	width: 100%;
	height: 100%;
	-webkit-perspective: 1200px;
	-moz-perspective: 1200px;
	perspective: 1200px;
}
.pt-page {
	width: 100%;
	height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	visibility: hidden;
	overflow: hidden;
	color:#000;
	-webkit-backface-visibility: hidden;
	-moz-backface-visibility: hidden;
	backface-visibility: hidden;
	-webkit-transform: translate3d(0, 0, 0);
	-moz-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0);
	-webkit-transform-style: preserve-3d;
	-moz-transform-style: preserve-3d;
	transform-style: preserve-3d;
}
.pt-page-current,.no-js .pt-page {
	visibility: visible;
	z-index: 1;
}
/*扇形导航*/
.demoOut {
    width:350px;
    height:350px;
    position: absolute;
    left:-160px;
    pointer-events:none;
}
.demo {
    width:100%;
    height:100%;
    position: relative;
    margin-left: auto;
    margin-right: auto;
}

.demo a {
    text-decoration: none;
    color: white;
}

.demoOut .btn {
    position: absolute;
    height:30%;
    width: 30%;
    top:35%;
    left:30%;
    background-color: rgb(22,52,112);
    border:1px solid rgb(28,61,105);
    text-align: center;
    border-radius: 50%;
    text-align: right;
    color: white;
    font-size:40px;
    cursor: pointer;
    pointer-events:auto;
}
.onepage{
	background: rgba(14,23,45,0.9)  !important;
	line-height: 130px;
	font-size: 20px;
	cursor: pointer;
	overflow: hidden;
}
.onepage:hover{
	background: rgba(14,52,118,0.7) !important;
}
.hasselected{
	background:rgba(14,52,118,0.7) !important;
}
.sectornav1{
	transform:rotate(-9deg);
	padding-left: 60px;
}
.sectornav2{
	transform:rotate(-90deg);
	padding-right: 20px;
}
.sectornav3{
	transform:rotate(190deg);
	padding-left: 60px;
}
</style>
</head>

<body>
	<div class="main">
		<div id="divTop" class="top">
			<div class="companytitle">全渠道产品变更态势</div>
			<div id="divFilter" class="filter" align="center">
				<div class="timediv">
					<div class="div_datepiker">
						<input id="datepiker" class="inputdate" type="text" readonly="readonly"/>
						<i id="iconDatepiker" class="iconfont">&#xe762;</i>
					</div>
				</div>
				<div  id="dishiselect" class="div_select"></div>
				<!-- <div class="bnt">
					<i id="iStartBnt" state="start" class="iconfont">&#xe629;</i>
				</div> --> 
			</div>
		</div>
		<div id="divIfream" class="ifreamdiv">
			<div id="pt-main" class="pt-perspective">
				<div class="pt-page">
					<iframe id="ifrMain0" name="childifr0" class="ifr" frameborder="no" src=""></iframe>
				</div>
				<div class="pt-page">
					<iframe id="ifrMain1" name="childifr1" class="ifr" frameborder="no" src=""></iframe>
				</div>
				<div class="pt-page">
					<iframe id="ifrMain2" name="childifr2" class="ifr" frameborder="no" src=""></iframe>
				</div>
			</div>	
		</div>
		
		<!-- 扇形导航 -->
		<div id="demo01" class="demoOut">
	        <div class="demo">
	            <ul>
	                <li>
	                    <a class="onepage">
	                    </a>
	                </li>
	                <li>
	                    <a class="onepage hasselected" onclick="changepage(0,this)">
	                    		<div class="sectornav1"><i class="iconfont" style="font-size:20px;">&#xe618;</i>&nbsp;总体情况</div>
	                    </a>
	                </li>
	                <li>
	                    <a class="onepage" onclick="changepage(1,this)">
	                    		<div class="sectornav2"><i class="iconfont" style="font-size:20px;">&#xe6b8;</i>&nbsp;套餐升降</div>
	                    </a>
	                </li>
	                <li>
	                    <a class="onepage" onclick="changepage(2,this)">
	                    		<div class="sectornav3"><i class="iconfont" style="font-size:20px;">&#xe61a;</i>&nbsp;流量包</div>	
	                    </a>
	                </li>
	            </ul>
	        </div>
	        <div class="btn">+&nbsp;</div>
	    </div>
	</div>
</body>
<script type="text/javascript">
	var lvlId="<%=session.getAttribute("lvlId")%>";
	var regionId="<%=session.getAttribute("regionId")%>";
	var dishiData=<%=request.getAttribute("dishijson")%>;
	var maxDate="<%=request.getAttribute("maxDate")%>";
	var minDate="<%=request.getAttribute("minDate")%>";
	var selectedName="<%=session.getAttribute("regionName")%>";
	var curdate="";
	var timeintervl;
	var state="start";
	var circ;
	
/* 日期选择控件配置JSON */
	dateD=minDate.substring(0,4)+"-"+minDate.substring(4,6)+"-"+minDate.substring(6,8);
	datemax=maxDate.substring(0,4)+"-"+maxDate.substring(4,6)+"-"+maxDate.substring(6,8);
	var jedateoption = {
		dateCell:"#datepiker",
		format:"YYYY-MM-DD",
		minDate:dateD,
		maxDate:datemax, 
		choosefun:function(elem, datas) {
		 	//clearInterval(timeintervl);
			//window.childifr.main(datas.replace("-","").replace("-",""));
			/* if(state=="start"){
				timeintervl=setInterval(IntervalFun,5000);
			}else if(state=="stop"){
				clearInterval(timeintervl);
			} */
			curdate=datas.replace("-","").replace("-","");
			window.childifr0.main(datas.replace("-","").replace("-",""),regionId,selectedName);
			window.childifr1.main(datas.replace("-","").replace("-",""),regionId);
			window.childifr2.main(datas.replace("-","").replace("-",""),regionId);
		}
	};
	/* 日期控件初始化 */
	$("#datepiker").val(datemax);
	jeDate(jedateoption);
	
	var $main = $( '#pt-main' ),
		$pages = $main.children( 'div.pt-page' ),
		animcursor = 54,
		pagesCount = $pages.length,
		current = 0,
		isAnimating = false,
		endCurrPage = false,
		endNextPage = false,
		animEndEventNames = {
			'WebkitAnimation' : 'webkitAnimationEnd',
			'OAnimation' : 'oAnimationEnd',
			'msAnimation' : 'MSAnimationEnd',
			'animation' : 'animationend'
		},
		// animation end event name
		animEndEventName = animEndEventNames[ Modernizr.prefixed( 'animation' ) ],
		// support css animations
		support = Modernizr.cssanimations;
	
	
	$(function(){
		adjust();
		
		/*扇形导航*/
		circ =$("#demo01 .demo").circular({
	        centerDeg: 140,//扇形中心线角度，单位：度，默认：90
	        allDeg:320,//整个扇形角度，单位：度，默认：180
	        inner:20,//内部圆形百分比，默认：50
	        hidden: false,//开始时是否隐藏，默认：false
	        animation: "clockwise",//动画类型，默认：zoom，其他：clockwise：顺时针展开，counterclockwise：逆时针展开，bothside：两侧展开
	        spacing:1,//间距，单位：度，默认：0
	        time: 1//动画时间，单位：秒，默认：0.5
	    });
	
		circ.toHidden();//隐藏方法
	   	//$("#demo01 .btn").removeClass("open");
		$("#demo01 .btn").click(function(){
	        if($(this).hasClass("open")){
	            circ.toHidden();//隐藏方法
	            $(this).removeClass("open");
	        }else{
		
	            circ.toShow();//显示方法
	            $(this).addClass("open");
	        }
		});
		/*加载地市下拉框*/
		if(lvlId=="1"){
			initDishiSelect(regionDishiJsonTransform(dishiData,true));
		}else if(lvlId=="2"){
			initDishiSelect(regionDishiJsonTransform(dishiData,false));
		}
		$("#iconDatepiker").click(function(){
				jeDate(jedateoption);
			});
		curdate=$("#datepiker").val().replace("-","").replace("-","");
		$("#ifrMain0").attr("src","generalsituation.do?curdate="+curdate+"&regionId="+regionId+"&state="+state);
		$("#ifrMain1").attr("src","situation4gmain.do?curdate="+curdate+"&regionId="+regionId+"&state="+state);
		$("#ifrMain2").attr("src","flowandpack.do?curdate="+curdate+"&regionId="+regionId+"&state="+state);
		
		//设置轮播开始结束
		/* $("#iStartBnt").click(function(){
		
			if(state=="start"){
				$(this).html("&#xe7fa;");
				state="stop";
				//子页面停止轮播
				window.childifr0.endlunbo();
				window.childifr1.endlunbo();
				window.childifr2.endlunbo();
			}else if(state=="stop"){
				$(this).html("&#xe629;");
				state="start";
				//子页面开始轮播
				window.childifr0.beginlunbo(true);
				window.childifr1.beginlunbo(true);
				window.childifr2.beginlunbo(true);
			}
		}); */
		
		$pages.each( function() {
			var $page = $( this );
			$page.data( 'originalClassList', $page.attr( 'class' ) );
		} );
		$pages.eq(current).addClass( 'pt-page-current' );
		 
	});
	
	function toQiPaoPage(index){
		window.childifr1.tochangepage1(index)
	}
	
/* 	 var IntervalFun = function(){
			var date=$("#datepiker").val();
			var newdate = getNextDateStr(date,1);
			if(newdate==getNextDateStr(maxDate.substring(0,4)+"-"+maxDate.substring(4,6)+"-"+maxDate.substring(6,8),1)){
				$("#datepiker").val(minDate.substring(0,4)+"-"+minDate.substring(4,6)+"-"+minDate.substring(6,8));
				newdate = minDate;
			}else{
				$("#datepiker").val(newdate.substring(0,4)+"-"+newdate.substring(4,6)+"-"+newdate.substring(6,8));
			}
			curdate=newdate;
			//console.log(curdate+";"+regionId);
			window.childifr.main(newdate,regionId);
		};  */
	/* 调整页面布局 */
	function adjust(){
		var domheight =$(".main").height();
		var domwidth =$(".main").width();
		$(".demoOut").css("top",(domheight-$(".demoOut").height())/2+"px");
		$(".btn").css("line-height",$(".btn").height()+"px");
		$("#divIfream").height(domheight-100);
	}
	/* 切换子页面 */
	/* function changeIfreamSrc(e){
		var url = $(e).attr("targetURL");
		curdate=$("#datepiker").val().replace("-","").replace("-","");
		$("#ifrMain").attr("src",url+"?curdate="+curdate+"&regionId="+regionId+"&state="+state);
		changeNavCss(e);
	} */
	/* 切换导航样式 */
	function changeNavCss(e){
		$(".onepage").removeClass("hasselected");
		$(e).addClass("hasselected");
		circ.toHidden();//隐藏方法
	    $("#demo01 .btn").removeClass("open");
	}
	
	
/*转化地域数据*/
function regionDishiJsonTransform(data,isProvince){
	var jsondata = [];
	if(isProvince){
		jsondata.push({"text":"全部","value":"1"});
	}
	if(data.length != 0){
		for ( var i = 0; i < data.length; i++) {
			var obj = data[i];
			var regionobj = {"text":obj.regionName,"value":obj.regionId};
			jsondata.push(regionobj);
		}
	}
	return jsondata;
}

	/* 加载地市 */
	function initDishiSelect(data,firstval){
		//加载地市地域选框
		$("#dishiselect").miapSelect({
			"data":data,
			"eachLiClick":function(i,t,v){
				regionId=v;
				/* clearInterval(timeintervl);
				//window.childifr.main(datas.replace("-","").replace("-",""));
				if(state=="start"){
					timeintervl=setInterval(IntervalFun,5000);
				}else if(state=="stop"){
					clearInterval(timeintervl);
				} */
				selectedName=t;
				window.childifr0.main(curdate,regionId,t);
				window.childifr1.main(curdate,regionId);
				window.childifr2.main(curdate,regionId);
				//console.log(curdate+";"+regionId);
			}
		});
		if(firstval!=undefined&&firstval!=null){
		
			$("#dishiselect").setSeletedWithIndex(firstval);
		}
	};
	
	function changedishi(datavalue,name){
		$("#dishiselect").setSeletedWithValue(datavalue);
		selectedName=name;
		regionId=datavalue;
		window.childifr0.main(curdate,regionId,name);
		window.childifr1.main(curdate,regionId);
		window.childifr2.main(curdate,regionId);
	}
	
	function changepage(curindex,obj){
		if( isAnimating ) {
			return false;
		}
		if( animcursor > 67 ) {
			animcursor = 1;
		}
		nextPage(curindex,animcursor);
		++animcursor;
		changeNavCss(obj);
	}
	
	
	function nextPage(curpageindex,animation) {
		if( isAnimating ) {
			return false;
		}
		//animation = animation>67?1:animation;
		isAnimating = true;
		
		var $currPage = $pages.eq( current );
		
		current = curpageindex;
		

		/* if( current < pagesCount - 1 ) {
			++current;
		}
		else {
			current = 0;
		} */

		var $nextPage = $pages.eq( curpageindex ).addClass( 'pt-page-current' ),
			outClass = '', inClass = '';

		switch( animation ) {

			case 1:
				outClass = 'pt-page-moveToLeft';
				inClass = 'pt-page-moveFromRight';
				break;
			case 2:
				outClass = 'pt-page-moveToRight';
				inClass = 'pt-page-moveFromLeft';
				break;
			case 3:
				outClass = 'pt-page-moveToTop';
				inClass = 'pt-page-moveFromBottom';
				break;
			case 4:
				outClass = 'pt-page-moveToBottom';
				inClass = 'pt-page-moveFromTop';
				break;
			case 5:
				outClass = 'pt-page-fade';
				inClass = 'pt-page-moveFromRight pt-page-ontop';
				break;
			case 6:
				outClass = 'pt-page-fade';
				inClass = 'pt-page-moveFromLeft pt-page-ontop';
				break;
			case 7:
				outClass = 'pt-page-fade';
				inClass = 'pt-page-moveFromBottom pt-page-ontop';
				break;
			case 8:
				outClass = 'pt-page-fade';
				inClass = 'pt-page-moveFromTop pt-page-ontop';
				break;
			case 9:
				outClass = 'pt-page-moveToLeftFade';
				inClass = 'pt-page-moveFromRightFade';
				break;
			case 10:
				outClass = 'pt-page-moveToRightFade';
				inClass = 'pt-page-moveFromLeftFade';
				break;
			case 11:
				outClass = 'pt-page-moveToTopFade';
				inClass = 'pt-page-moveFromBottomFade';
				break;
			case 12:
				outClass = 'pt-page-moveToBottomFade';
				inClass = 'pt-page-moveFromTopFade';
				break;
			case 13:
				outClass = 'pt-page-moveToLeftEasing pt-page-ontop';
				inClass = 'pt-page-moveFromRight';
				break;
			case 14:
				outClass = 'pt-page-moveToRightEasing pt-page-ontop';
				inClass = 'pt-page-moveFromLeft';
				break;
			case 15:
				outClass = 'pt-page-moveToTopEasing pt-page-ontop';
				inClass = 'pt-page-moveFromBottom';
				break;
			case 16:
				outClass = 'pt-page-moveToBottomEasing pt-page-ontop';
				inClass = 'pt-page-moveFromTop';
				break;
			case 17:
				outClass = 'pt-page-scaleDown';
				inClass = 'pt-page-moveFromRight pt-page-ontop';
				break;
			case 18:
				outClass = 'pt-page-scaleDown';
				inClass = 'pt-page-moveFromLeft pt-page-ontop';
				break;
			case 19:
				outClass = 'pt-page-scaleDown';
				inClass = 'pt-page-moveFromBottom pt-page-ontop';
				break;
			case 20:
				outClass = 'pt-page-scaleDown';
				inClass = 'pt-page-moveFromTop pt-page-ontop';
				break;
			case 21:
				outClass = 'pt-page-scaleDown';
				inClass = 'pt-page-scaleUpDown pt-page-delay300';
				break;
			case 22:
				outClass = 'pt-page-scaleDownUp';
				inClass = 'pt-page-scaleUp pt-page-delay300';
				break;
			case 23:
				outClass = 'pt-page-moveToLeft pt-page-ontop';
				inClass = 'pt-page-scaleUp';
				break;
			case 24:
				outClass = 'pt-page-moveToRight pt-page-ontop';
				inClass = 'pt-page-scaleUp';
				break;
			case 25:
				outClass = 'pt-page-moveToTop pt-page-ontop';
				inClass = 'pt-page-scaleUp';
				break;
			case 26:
				outClass = 'pt-page-moveToBottom pt-page-ontop';
				inClass = 'pt-page-scaleUp';
				break;
			case 27:
				outClass = 'pt-page-scaleDownCenter';
				inClass = 'pt-page-scaleUpCenter pt-page-delay400';
				break;
			case 28:
				outClass = 'pt-page-rotateRightSideFirst';
				inClass = 'pt-page-moveFromRight pt-page-delay200 pt-page-ontop';
				break;
			case 29:
				outClass = 'pt-page-rotateLeftSideFirst';
				inClass = 'pt-page-moveFromLeft pt-page-delay200 pt-page-ontop';
				break;
			case 30:
				outClass = 'pt-page-rotateTopSideFirst';
				inClass = 'pt-page-moveFromTop pt-page-delay200 pt-page-ontop';
				break;
			case 31:
				outClass = 'pt-page-rotateBottomSideFirst';
				inClass = 'pt-page-moveFromBottom pt-page-delay200 pt-page-ontop';
				break;
			case 32:
				outClass = 'pt-page-flipOutRight';
				inClass = 'pt-page-flipInLeft pt-page-delay500';
				break;
			case 33:
				outClass = 'pt-page-flipOutLeft';
				inClass = 'pt-page-flipInRight pt-page-delay500';
				break;
			case 34:
				outClass = 'pt-page-flipOutTop';
				inClass = 'pt-page-flipInBottom pt-page-delay500';
				break;
			case 35:
				outClass = 'pt-page-flipOutBottom';
				inClass = 'pt-page-flipInTop pt-page-delay500';
				break;
			case 36:
				outClass = 'pt-page-rotateFall pt-page-ontop';
				inClass = 'pt-page-scaleUp';
				break;
			case 37:
				outClass = 'pt-page-rotateOutNewspaper';
				inClass = 'pt-page-rotateInNewspaper pt-page-delay500';
				break;
			case 38:
				outClass = 'pt-page-rotatePushLeft';
				inClass = 'pt-page-moveFromRight';
				break;
			case 39:
				outClass = 'pt-page-rotatePushRight';
				inClass = 'pt-page-moveFromLeft';
				break;
			case 40:
				outClass = 'pt-page-rotatePushTop';
				inClass = 'pt-page-moveFromBottom';
				break;
			case 41:
				outClass = 'pt-page-rotatePushBottom';
				inClass = 'pt-page-moveFromTop';
				break;
			case 42:
				outClass = 'pt-page-rotatePushLeft';
				inClass = 'pt-page-rotatePullRight pt-page-delay180';
				break;
			case 43:
				outClass = 'pt-page-rotatePushRight';
				inClass = 'pt-page-rotatePullLeft pt-page-delay180';
				break;
			case 44:
				outClass = 'pt-page-rotatePushTop';
				inClass = 'pt-page-rotatePullBottom pt-page-delay180';
				break;
			case 45:
				outClass = 'pt-page-rotatePushBottom';
				inClass = 'pt-page-rotatePullTop pt-page-delay180';
				break;
			case 46:
				outClass = 'pt-page-rotateFoldLeft';
				inClass = 'pt-page-moveFromRightFade';
				break;
			case 47:
				outClass = 'pt-page-rotateFoldRight';
				inClass = 'pt-page-moveFromLeftFade';
				break;
			case 48:
				outClass = 'pt-page-rotateFoldTop';
				inClass = 'pt-page-moveFromBottomFade';
				break;
			case 49:
				outClass = 'pt-page-rotateFoldBottom';
				inClass = 'pt-page-moveFromTopFade';
				break;
			case 50:
				outClass = 'pt-page-moveToRightFade';
				inClass = 'pt-page-rotateUnfoldLeft';
				break;
			case 51:
				outClass = 'pt-page-moveToLeftFade';
				inClass = 'pt-page-rotateUnfoldRight';
				break;
			case 52:
				outClass = 'pt-page-moveToBottomFade';
				inClass = 'pt-page-rotateUnfoldTop';
				break;
			case 53:
				outClass = 'pt-page-moveToTopFade';
				inClass = 'pt-page-rotateUnfoldBottom';
				break;
			case 54:
				outClass = 'pt-page-rotateRoomLeftOut pt-page-ontop';
				inClass = 'pt-page-rotateRoomLeftIn';
				break;
			case 55:
				outClass = 'pt-page-rotateRoomRightOut pt-page-ontop';
				inClass = 'pt-page-rotateRoomRightIn';
				break;
			case 56:
				outClass = 'pt-page-rotateRoomTopOut pt-page-ontop';
				inClass = 'pt-page-rotateRoomTopIn';
				break;
			case 57:
				outClass = 'pt-page-rotateRoomBottomOut pt-page-ontop';
				inClass = 'pt-page-rotateRoomBottomIn';
				break;
			case 58:
				outClass = 'pt-page-rotateCubeLeftOut pt-page-ontop';
				inClass = 'pt-page-rotateCubeLeftIn';
				break;
			case 59:
				outClass = 'pt-page-rotateCubeRightOut pt-page-ontop';
				inClass = 'pt-page-rotateCubeRightIn';
				break;
			case 60:
				outClass = 'pt-page-rotateCubeTopOut pt-page-ontop';
				inClass = 'pt-page-rotateCubeTopIn';
				break;
			case 61:
				outClass = 'pt-page-rotateCubeBottomOut pt-page-ontop';
				inClass = 'pt-page-rotateCubeBottomIn';
				break;
			case 62:
				outClass = 'pt-page-rotateCarouselLeftOut pt-page-ontop';
				inClass = 'pt-page-rotateCarouselLeftIn';
				break;
			case 63:
				outClass = 'pt-page-rotateCarouselRightOut pt-page-ontop';
				inClass = 'pt-page-rotateCarouselRightIn';
				break;
			case 64:
				outClass = 'pt-page-rotateCarouselTopOut pt-page-ontop';
				inClass = 'pt-page-rotateCarouselTopIn';
				break;
			case 65:
				outClass = 'pt-page-rotateCarouselBottomOut pt-page-ontop';
				inClass = 'pt-page-rotateCarouselBottomIn';
				break;
			case 66:
				outClass = 'pt-page-rotateSidesOut';
				inClass = 'pt-page-rotateSidesIn pt-page-delay200';
				break;
			case 67:
				outClass = 'pt-page-rotateSlideOut';
				inClass = 'pt-page-rotateSlideIn';
				break;

		}

		$currPage.addClass( outClass ).on( animEndEventName, function() {
			$currPage.off( animEndEventName );
			endCurrPage = true;
			if( endNextPage ) {
				onEndAnimation( $currPage, $nextPage );
			}
		} );

		$nextPage.addClass( inClass ).on( animEndEventName, function() {
			$nextPage.off( animEndEventName );
			endNextPage = true;
			if( endCurrPage ) {
				onEndAnimation( $currPage, $nextPage );
			}
		} );

		if( !support ) {
			onEndAnimation( $currPage, $nextPage );
		}

	}

	function onEndAnimation( $outpage, $inpage ) {
		endCurrPage = false;
		endNextPage = false;
		resetPage( $outpage, $inpage );
		isAnimating = false;
	}

	function resetPage( $outpage, $inpage ) {
		$outpage.attr( 'class', $outpage.data( 'originalClassList' ) );
		$inpage.attr( 'class', $inpage.data( 'originalClassList' ) + ' pt-page-current' );
	}
</script>
</html>
