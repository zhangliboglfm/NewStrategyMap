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

<title>线上下对比</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- CSS引入 -->
<link rel="stylesheet" href="css/line/page.css" type="text/css"></link>
<link rel="stylesheet" href="css/progress.css" type="text/css"></link>
<link rel="stylesheet" href="css/jquery.fullPage.css" type="text/css"></link>
<!-- JS引入 -->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/miapsoft.date.js"></script>
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/Echarts-baishaohua.js"></script>
<script type="text/javascript" src="js/jquery.easings.min.js"></script>
<script type="text/javascript" src="js/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="js/jquery.fullPage.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
<script type="text/javascript" src="js/velocity.ui.js"></script>

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
*{font-family: Microsoft YaHei;font-size: 12px;}
html{width: 100%;height: 100%;}
body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0;position: relative;}
.rightcontentdata .title{width: 100%;height: 30px;background-color: #1E566F}
.rightcontentdata .title .span1{float:left;width: 64px;font-size: 14px;height: 20px;margin-top: 5px;line-height: 20px;text-align: center;color: #fff}
.rightcontentdata .title .span2{float:left;width: 92px;font-size: 14px;height: 20px;margin-top: 5px;line-height: 20px;text-align: center;color: #fff;border-left: 1px solid #00FFFF}
.rightcontentdata .title .span3{float:left;width: 92px;font-size: 14px;height: 20px;margin-top: 5px;line-height: 20px;text-align: center;color: #fff;border-left: 1px solid #00FFFF}

.leftcontentdata .title{width: 100%;height: 30px;background-color: #1E566F}
.leftcontentdata .title .span1{float:left;width: 64px;font-size: 14px;height: 20px;margin-top: 5px;line-height: 20px;text-align: center;color: #fff}
.leftcontentdata .title .span2{float:left;width: 92px;font-size: 14px;height: 20px;margin-top: 5px;line-height: 20px;text-align: center;color: #fff;border-left: 1px solid #00FFFF}
.leftcontentdata .title .span3{float:left;width: 92px;font-size: 14px;height: 20px;margin-top: 5px;line-height: 20px;text-align: center;color: #fff;border-left: 1px solid #00FFFF}

.rightcontentdata .main{width: 100%;}
.leftcontentdata .main{width: 100%;}

.eachprogress{
	width: 100%;
	margin-top: 5px;
}
.eachprogress .div1{float:left;width: 64px;font-size: 14px;height: 100%;margin-top: 5px;text-align: center;color: #fff;}
.eachprogress .div2{float:left;width: 92px;font-size: 14px;height: 100%;margin-top: 5px;text-align: center;color: #fff;margin-left: 1px}
.eachprogress .div3{float:left;width: 92px;font-size: 14px;height: 100%;margin-top: 5px;text-align: center;color: #fff;margin-left: 1px}

.onlinefludiv{width: 88px;height: 50%;margin-top: 0%;border: 1px solid #109DD3;
	/* For IF9 Or opera */
	box-shadow:0px 0px 10px #109DD3 inset;
	/* For FireFox */
	-moz-box-shadow:0px 0px 10px #109DD3 inset;
	/* For Safari Or Chrome */
	-webkit-box-shadow:0px 0px 10px #109DD3 inset;
}
.outlinefludiv{width: 88px;height: 50%;margin-top: 0%;border: 1px solid #4BC284;
	border: 1px solid #4BC284;
	/* For IF9 Or opera */
	box-shadow:0px 0px 10px #4BC284 inset;
	/* For FireFox */
	-moz-box-shadow:0px 0px 10px #4BC284 inset;
	/* For Safari Or Chrome */
	-webkit-box-shadow:0px 0px 10px #4BC284 inset;
}

.onlinefludiv .progress{float: left;margin-left: 3px;height: 5px;margin-top: 11%;background-color: #01FFFF;max-width: 50px}
.onlinefludiv .number{float: right;width: 30px;height: 100%;line-height: 200%;margin-right: 2px;color: #01FFFF}

.outlinefludiv .progress{float: left;margin-left: 3px;height: 5px;margin-top: 11%;background-color: #71FF39;max-width: 50px}
.outlinefludiv .number{float: right;width: 30px;height: 100%;line-height: 200%;margin-right: 2px;color: #71FF39}

.rightmain{width: 100%;border:1px solid #0ac1cb;margin-top: 2px}
.leftmain{width: 100%;border: 1px solid #0ac1cb;margin-top: 2px}
</style>

</head>

<body id="body" class="scoll">
   <div class="rightpart">
        <div class="rightcontent">
             <div class="rightfont">宽带业务</div>
             <div class="rightmain">
             	<div class="rightcontentdata" >
	             	<div class="title">
	             		<div class="span1">地市</div>
	             		<div class="span2">线上</div>
	             		<div class="span3">线下</div>
	             	</div>
	             	<div id="divKuandaiProgressData" class="main">
	             		<div class="eachprogress">
	             			<div class="div1">石家庄</div>
	             			<div id="divSJZOnline" class="div2"></div>
	             			<div id="divSJZOutline" class="div3"></div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">秦皇岛</div>
	             			<div id="divQHDOnline" class="div2"></div>
	             			<div id="divQHDOutline" class="div3"></div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">张家口</div>
	             			<div id="divZJKOnline" class="div2"></div>
	             			<div id="divZJKOutline" class="div3"></div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">廊坊</div>
	             			<div id="divLFOnline" class="div2"></div>
	             			<div id="divLFOutline" class="div3"></div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">唐山</div>
	             			<div id="divTSOnline" class="div2"></div>
	             			<div id="divTSOutline" class="div3"></div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">衡水</div>
	             			<div id="divHSOnline" class="div2"></div>
	             			<div id="divHSOutline" class="div3"></div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">保定</div>
	             			<div id="divBDOnline" class="div2"></div>
	             			<div id="divBDOutline" class="div3"></div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">承德</div>
	             			<div id="divCDOnline" class="div2"></div>
	             			<div id="divCDOutline" class="div3"></div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">邢台</div>
	             			<div id="divXTOnline" class="div2"></div>
	             			<div id="divXTOutline" class="div3"></div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">邯郸</div>
	             			<div id="divHDOnline" class="div2"></div>
	             			<div id="divHDOutline" class="div3"></div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">沧州</div>
	             			<div id="divCZOnline" class="div2"></div>
	             			<div id="divCZOutline" class="div3"></div>
	             		</div>
	             	</div>
	             </div>
	             <div class="rightchage" style="background-color: #19283D;">
	                  <div class="righteachchage" data-type="pack_gl.g.mobileWBAND"   title="手机宽带产品包"  style="border-bottom:3px solid #ed496b "><i class="iconfont">&#xe602;</i></div>
	                  <div class="righteachchage" data-type="pack_gl.g.WBAND" title="家庭宽带产品包"><i class="iconfont" style="font-size: 24px;">&#xe603;</i></div>
	                  <div class="righteachchage" data-type="pack_gl.g.iptv" title="IPTV产品包">IPTV</div>
	                  <div class="righteachchage" data-type="pri_lc.bd.tjkddsz20hf_sh"  title="社会渠道推荐宽带电视赠20元话费优惠"><i class="iconfont">&#xe605;</i></div>
	                  <div class="righteachchage" style="border-right:0px;" data-type="inr_gl.g.ydsjkd1n_50M_new" title="邢台移动手机宽带1年产品_50M新"><i class="iconfont">&#xe60f;</i></div>
	             </div>
             </div>
        </div>
   </div>
   <div class="middlepart">
        <div class="topclass">
             <div class="leftdiv">
				<div class="title">重点业务办理</div>
				<div id="divKeyBus" class="info"></div>
			</div>        
			<div class="rightdiv">
				<div class="title">业务办理量</div>
				<div id="divBusHan" class="info">
					<div class="businessvolume">
				          <div class="businessleft">
				              
				               <div class="businesstitle">
				                    <div class="titleleft"><i class="iconfont" style="color: #00FFFC">&#xe8a4;</i>办理量</div>
				               </div>
				               <div class="businesstitle">
				                    <div class="titleleft"><i class="iconfont" style="color: #FEB501">&#xe61f;</i>办理用户</div>
				               </div>
				          </div>
				          <div class="businessright">
				                <div id="divPropNum" class="alleachnumber">
				                    <!-- <div class="eachnumberleft">4</div>
				                    <div class="eachnumberleft">6</div>
				                    <div class="eachnumberleft">7</div>
				                    <div class="eachnumberleft">8</div>
				                    <div class="eachnumberleft">9</div> -->
				               </div>
				               <div id="divUserNum" class="alleachnumber">
				                    <!-- <div class="eachnumberright">4</div>
				                    <div class="eachnumberright">6</div>
				                    <div class="eachnumberright">7</div>
				                    <div class="eachnumberright">8</div>
				                    <div class="eachnumberright">9</div> -->
				               </div>
				               
				          </div>
				     </div>
				</div>
			</div>  
        </div> 
        <div class="middleclass">
             <div class="middlefont">4G飞享类办理情况</div>
             <div id="divMiddleMain" class="middleonedata scoll">
             	<div class="eachsection">
	                  <div class="middleevery">
	                       <div id="divPackChart6" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">6元&nbsp;&nbsp;<span id="divPackRate6" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart8" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">8元&nbsp;&nbsp;<span id="divPackRate8" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart15" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">18元&nbsp;&nbsp;<span id="divPackRate15" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
             	</div>
             	<div class="eachsection">
             		<div class="middleevery">
	                       <div id="divPackChart18" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">18元&nbsp;&nbsp;<span id="divPackRate18" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart28" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">28元&nbsp;&nbsp;<span id="divPackRate28" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart38" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">38元&nbsp;&nbsp;<span id="divPackRate38" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
             	</div>
             	<div class="eachsection">
             		<div class="middleevery">
	                       <div id="divPackChart45" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">48元&nbsp;&nbsp;<span id="divPackRate45" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart58" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">58元&nbsp;&nbsp;<span id="divPackRate58" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart65" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">68元&nbsp;&nbsp;<span id="divPackRate65" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
             	</div>
             	<div class="eachsection">
             		<div class="middleevery">
	                       <div id="divPackChart88" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">88元&nbsp;&nbsp;<span id="divPackRate88" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart95" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">98元&nbsp;&nbsp;<span id="divPackRate95" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart138" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">138元&nbsp;&nbsp;<span id="divPackRate138" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
             	</div>
             </div>
        </div>
        <div class="bottomclass">
             <div class="middlefont" style="margin-top:2.5px;">其他业务</div>
             <div class="bottomdata">
                 <div class="chart" id="otherbusiness"></div>
             	 <div class="nav">
             		<div class="eachnav1 cureachbg"  data-type="PRIV_1300000083_hlyty"  title="来电管家优惠包"  onclick="changeBusType(this)">
             			<div class="name">来电管家优惠包</div>
             			<div class="boder curboderbg"></div>
             		</div>
             		<div class="eachnav1"  data-type="inr_gl.g.mgdmhyb"  title="咪咕动漫会员包"  onclick="changeBusType(this)">
             			<div class="name">咪咕动漫</div>
             			<div class="boder"></div>
             		</div>
             		<div class="eachnav1" data-type="VOLTE"  title="VOLTE"   onclick="changeBusType(this)">
             			<div class="name">VOLTE</div>
             			<div class="boder"></div>
             		</div>
             		<div class="eachnav1" data-type="pri_gl.g.fxhymfyh"  title="飞信会员、139邮箱5元版" onclick="changeBusType(this)">
             			<div class="name">飞信会员</div>
             			<div class="boder"></div>
             		</div>
             		<div class="eachnav2 noboder" data-type="InternetProd"  title="互联网基础产品" onclick="changeBusType(this)">
             			<div class="name">互联网基础产品</div>
             			<div class="boder"></div>
             		</div>
             	</div>
             </div>
        </div>
   </div>
   <div class="leftpart">
        <div class="leftcontent">
             <div class="rightfont">流量包</div>
             <div class="leftmain">
             	<div class="leftcontentdata">
	             	<div class="title">
	             		<div class="span1">地市</div>
	             		<div class="span2">线上</div>
	             		<div class="span3">线下</div>
	             	</div>
	             	<div id="divLiuliangProgressData" class="main">
	             		<div class="eachprogress">
	             			<div class="div1">石家庄</div>
	             			<div id="divSJZOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divSJZOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">秦皇岛</div>
	             			<div id="divQHDOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divQHDOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">张家口</div>
	             			<div id="divZJKOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divZJKOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">廊坊</div>
	             			<div id="divLFOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divLFOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">唐山</div>
	             			<div id="divTSOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divTSOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">衡水</div>
	             			<div id="divHSOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divHSOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">保定</div>
	             			<div id="divBDOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divBDOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">承德</div>
	             			<div id="divCDOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divCDOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">邢台</div>
	             			<div id="divXTOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divXTOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">邯郸</div>
	             			<div id="divHDOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divHDOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             		<div class="eachprogress">
	             			<div class="div1">沧州</div>
	             			<div id="divCZOnlineFluxData" class="div2">
	             				<div class="onlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             			<div id="divCZOutlineFluxData" class="div3">
	             				<div class="outlinefludiv">
	             					<div class="progress"></div>
	             					<div class="number"></div>
	             				</div>
	             			</div>
	             		</div>
	             	</div>
	             </div>
	             <div class="rightchage">
                  <div class="leftachchage" data-type="pri_gl.g.4G.10y100mjjb"  title="10元100M流量加油包(10元1000M)"  style="border-bottom:3px solid #ed496b"><i class="iconfont">&#xe60d;</i></div>
                  <div class="leftachchage" data-type="pri_gl.g.4G.10y10btyjyb" title="10元10倍套外加油包"><i class="iconfont" style="font-size:25px;" >&#xe604;</i></div>
                  <div class="leftachchage" data-type="pri_gl.g.20yllzb_2G" title="流量周包20元2G"><i class="iconfont">&#xe60e;</i></div>
                  <div class="leftachchage" data-type="pri_gl.g.15yllzb_1G" title="流量周包15元1G"><i class="iconfont">&#xe669;</i></div>
                  <div class="leftachchage" style="border-right:0px;" data-type="pri_gl.g.cjllb_30y_2G" title="超级流量包30元2G"><i class="iconfont">&#xe60c;</i></div>
             	</div>
             </div>
        </div> 
   </div>
   <div id="popDivTitle" class="pop_titlediv"></div>
</body>
<script type="text/javascript">
	var dateMax="<%=request.getAttribute("maxdate")%>";
	var dateMin="<%=request.getAttribute("mindate")%>";
	var curdate="<%=request.getAttribute("curdate")%>";
	
	
	var broadband="pack_gl.g.mobileWBAND";//宽带业务的类型
    var otherbusiness="PRIV_1300000083_hlyty";//其他业务的类型
    var flowtype="pri_gl.g.4G.10y100mjjb";//流量包的类型
    
    //initProgress*();
    var highchart;
  $(function(){
     adjust();
       //滚动条
    $(".scoll").niceScroll({
	 cursorcolor : "#00AFFF",
	 cursorborderradius: "3px",
	 cursorwidth: "5px",
	 cursorborder: "0px solid #fff",
	 autohidemode:false
	 });
	 initKey();
	 initBusiness();
	 initKuandai();
	 initPack();
	 initFlux();
	 initOther();
  })
  function main(date){
  	curdate=date;
  	changeKey();
  	changeKudandaiData();
  	changePack();
  	initFlux();
  	changeHighChart();
  	initBusiness();
  }
  
  function initKuandai(){
  		$.ajax({
	  		url:"getkuandaidata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate,type:broadband},
	  		success:function(data){
	  			 initKuandaiData(data);
	  		}
	  	});
  }
  function initFlux(){
  		$.ajax({
	  		url:"getfluxdata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate,type:flowtype},
	  		success:function(data){
	  			 initFluxProgress(data);
	  		}
	  	});
  }
  function initKey(){
  		$.ajax({
	  		url:"getkeydata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate},
	  		success:function(data){
		  		initKeyBusCharts("divKeyBus",data[0]);
		  		console.log(data[0].datax);
	  		}
	  	});
  }
  function initBusiness(){
  		$.ajax({
	  		url:"getbusinessdata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate},
	  		success:function(data){
	  			 initNumberBoard(data);
	  		}
	  	});
  }
  function changeKey(){
  		$.ajax({
	  		url:"getkeydata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate},
	  		success:function(data){
	  			var keychart = echarts.getInstanceByDom(document.getElementById("divKeyBus"));
	  			if(data.length==0){
	  				var option={radiusAxis:{data: data[0].datax},series: [{data:[]},{data:[]}]};
		  			keychart.setOption(option);
	  			}else{
	  				console.log(data[0].datax);
		  			var option={radiusAxis:{data: data[0].datax},series: [{data:data[0].datay.online},{data:data[0].datay.outline}]};
		  			keychart.setOption(option);
	  			}
	  		}
	  	});
  }
  function initOther(){
  		$.ajax({
	  		url:"getotherdata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate,type:otherbusiness},
	  		success:function(data){
	  			createcolumn("otherbusiness",data);
	  		}
	  	});
  }
  function initPack(){
	  	$.ajax({
	  		url:"getpackdata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate},
	  		success:function(data){
	  			initPackCharts("divPackChart6",data["fxtc6y"]);
	  			initPackCharts("divPackChart8",data["fxtc8y"]);
	  			initPackCharts("divPackChart15",data["fxtc15y"]);
	  			initPackCharts("divPackChart18",data["fxtc18y"]);
	  			initPackCharts("divPackChart28",data["fxtc28y"]);
	  			initPackCharts("divPackChart38",data["fxtc38y"]);
	  			initPackCharts("divPackChart45",data["fxtc45y"]);
	  			initPackCharts("divPackChart58",data["fxtc58y"]);
	  			initPackCharts("divPackChart65",data["fxtc65y"]);
	  			initPackCharts("divPackChart88",data["fxtc88y"]);
	  			initPackCharts("divPackChart95",data["fxtc95y"]);
	  			initPackCharts("divPackChart138",data["fxtc138y"]);
	  			$("#divPackRate6").html(data["fxtc6yrate"]+"%");
	  			$("#divPackRate8").html(data["fxtc8yrate"]+"%");
	  			$("#divPackRate15").html(data["fxtc15yrate"]+"%");
	  			$("#divPackRate18").html(data["fxtc18yrate"]+"%");
	  			$("#divPackRate28").html(data["fxtc28yrate"]+"%");
	  			$("#divPackRate38").html(data["fxtc38yrate"]+"%");
	  			$("#divPackRate45").html(data["fxtc45yrate"]+"%");
	  			$("#divPackRate58").html(data["fxtc58yrate"]+"%");
	  			$("#divPackRate65").html(data["fxtc65yrate"]+"%");
	  			$("#divPackRate88").html(data["fxtc88yrate"]+"%");
	  			$("#divPackRate95").html(data["fxtc95yrate"]+"%");
	  			$("#divPackRate138").html(data["fxtc138yrate"]+"%");
	  		}
	  	});
  }
  /* 改变图表数据 */
  function changePack(){
  		$.ajax({
	  		url:"getpackdata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate},
	  		success:function(data){
	  			var packchart6 = echarts.getInstanceByDom(document.getElementById("divPackChart6"));
	  			var packchart8 = echarts.getInstanceByDom(document.getElementById("divPackChart8"));
	  			var packchart15 = echarts.getInstanceByDom(document.getElementById("divPackChart15"));
	  			var packchart18 = echarts.getInstanceByDom(document.getElementById("divPackChart18"));
	  			var packchart28 = echarts.getInstanceByDom(document.getElementById("divPackChart28"));
	  			var packchart38 = echarts.getInstanceByDom(document.getElementById("divPackChart38"));
	  			var packchart45 = echarts.getInstanceByDom(document.getElementById("divPackChart45"));
	  			var packchart58 = echarts.getInstanceByDom(document.getElementById("divPackChart58"));
	  			var packchart65 = echarts.getInstanceByDom(document.getElementById("divPackChart65"));
	  			var packchart88 = echarts.getInstanceByDom(document.getElementById("divPackChart88"));
	  			var packchart95 = echarts.getInstanceByDom(document.getElementById("divPackChart95"));
	  			var packchart138 = echarts.getInstanceByDom(document.getElementById("divPackChart138"));
			  	var option6 ={angleAxis:{data: data["fxtc6y"].datax},series: [{data:data["fxtc6y"].datay.online},{data:data["fxtc6y"].datay.outline}]};
			  	var option8 ={angleAxis:{data: data["fxtc8y"].datax},series: [{data:data["fxtc8y"].datay.online},{data:data["fxtc8y"].datay.outline}]};
			  	var option15 ={angleAxis:{data: data["fxtc15y"].datax},series: [{data:data["fxtc15y"].datay.online},{data:data["fxtc15y"].datay.outline}]};
			  	var option18 ={angleAxis:{data: data["fxtc18y"].datax},series: [{data:data["fxtc18y"].datay.online},{data:data["fxtc18y"].datay.outline}]};
			  	var option28 ={angleAxis:{data: data["fxtc28y"].datax},series: [{data:data["fxtc28y"].datay.online},{data:data["fxtc28y"].datay.outline}]};
			  	var option38 ={angleAxis:{data: data["fxtc38y"].datax},series: [{data:data["fxtc38y"].datay.online},{data:data["fxtc38y"].datay.outline}]};
			  	var option45 ={angleAxis:{data: data["fxtc45y"].datax},series: [{data:data["fxtc45y"].datay.online},{data:data["fxtc45y"].datay.outline}]};
			  	var option58 ={angleAxis:{data: data["fxtc58y"].datax},series: [{data:data["fxtc58y"].datay.online},{data:data["fxtc58y"].datay.outline}]};
			  	var option65 ={angleAxis:{data: data["fxtc65y"].datax},series: [{data:data["fxtc65y"].datay.online},{data:data["fxtc65y"].datay.outline}]};
			  	var option88 ={angleAxis:{data: data["fxtc88y"].datax},series: [{data:data["fxtc88y"].datay.online},{data:data["fxtc88y"].datay.outline}]};
			  	var option95 ={angleAxis:{data: data["fxtc95y"].datax},series: [{data:data["fxtc95y"].datay.online},{data:data["fxtc95y"].datay.outline}]};
			  	var option138 ={angleAxis:{data: data["fxtc138y"].datax},series: [{data:data["fxtc138y"].datay.online},{data:data["fxtc138y"].datay.outline}]};
			  	packchart6.setOption(option6);
			  	packchart8.setOption(option8);
			  	packchart15.setOption(option15);
			  	packchart18.setOption(option18);
			  	packchart28.setOption(option28);
			  	packchart38.setOption(option38);
			  	packchart45.setOption(option45);
			  	packchart58.setOption(option58);
			  	packchart65.setOption(option65);
			  	packchart88.setOption(option88);
			  	packchart95.setOption(option95);
			  	packchart138.setOption(option138);
			  	$("#divPackRate6").html(data["fxtc6yrate"]+"%");
	  			$("#divPackRate8").html(data["fxtc8yrate"]+"%");
	  			$("#divPackRate15").html(data["fxtc15yrate"]+"%");
	  			$("#divPackRate18").html(data["fxtc18yrate"]+"%");
	  			$("#divPackRate28").html(data["fxtc28yrate"]+"%");
	  			$("#divPackRate38").html(data["fxtc38yrate"]+"%");
	  			$("#divPackRate45").html(data["fxtc45yrate"]+"%");
	  			$("#divPackRate58").html(data["fxtc58yrate"]+"%");
	  			$("#divPackRate65").html(data["fxtc65yrate"]+"%");
	  			$("#divPackRate88").html(data["fxtc88yrate"]+"%");
	  			$("#divPackRate95").html(data["fxtc95yrate"]+"%");
	  			$("#divPackRate138").html(data["fxtc138yrate"]+"%");
	  		}
	  	});
  }
  
  
  //宽带业务的标签点击事件
   $(".righteachchage").click(function(){
      $(".righteachchage").css("border-bottom","3px solid #173742");
      $(this).css("border-bottom","3px solid #ed496b");
      broadband=$(this).attr("data-type");
      changeKudandaiData();
   })
  //流量包的标签点击事件
   $(".leftachchage").click(function(){
      $(".leftachchage").css("border-bottom","3px solid #173742");
      $(this).css("border-bottom","3px solid #ed496b");
      flowtype=$(this).attr("data-type");
      initFlux();
   })
  //切换其他业务类型 
  function changeBusType(e){
  	  $(".cureachbg").removeClass("cureachbg");
  	  $(".curboderbg").removeClass("curboderbg");
  	  $(e).addClass("cureachbg");
  	  $(e).find(".boder").addClass("curboderbg");
  	  otherbusiness = $(e).attr("data-type");
  	  initOther();
  }
  	//业务办理量的数字格式化
	function formatternumber(data){
	        var numarry=[];
	        data="998764";
	        for(i=0;i<data.length;i++){
	            var num=data.substring(i, i+1);
	            numarry.push(num);
	        }
	        return numarry ;
	}
  function adjust(){
      var w=$(document).width();
      var h=$(document).height();
      $(".middlepart").width(w-555);
      $(".middleonedata").height($(".middleclass").height()-35);
      $(".middleonedata").find(".eachsection").height($(".middleclass").height()-46);
      $(".bottomdata").height($(".bottomclass").height()-26);
      
      $(".rightcontent").find(".rightmain").height($(".rightcontent").height()-$(".rightcontent").find(".rightfont").height()-3);
      $(".rightcontentdata").height($(".rightcontent").find(".rightmain").height()-$(".rightcontent").find(".rightchage").height()-4);
      
      $(".leftcontent").find(".leftmain").height($(".leftcontent").height()-$(".leftcontent").find(".rightfont").height()-3);
      $(".leftcontentdata").height($(".leftcontent").find(".leftmain").height()-$(".leftcontent").find(".rightchage").height()-4);
      
      $("#divKeyBus").height($(".leftdiv").height()-$(".leftdiv").find(".title").height());
	  $("#divBusHan").height($(".rightdiv").height()-$(".rightdiv").find(".title").height());
	  $(".bottomdata").find(".chart").height($(".bottomdata").height()-$(".bottomdata").find(".nav").height()-1);
	  $(".bottomdata").find(".nav").find(".eachnav1").width(($(".bottomdata").width()/5)-1);
	  $(".bottomdata").find(".nav").find(".eachnav2").width($(".bottomdata").width()/5);
	  $(".rightcontentdata").find(".main").height($(".rightcontentdata").height()-$(".rightcontentdata").find(".title").height());
	  $(".leftcontentdata").find(".main").height($(".leftcontentdata").height()-$(".leftcontentdata").find(".title").height());
	  var eachprogressH = ($(".rightcontentdata").find(".main").height()-12*5)/11;
	  $(".eachprogress").height(eachprogressH);
	  $(".eachprogress").find(".div1").css("line-height",(eachprogressH-17)+"px");
	  $(".eachnumberleft").css("line-height",($(".eachnumberleft").height())+"px");
      $(".eachnumberright").css("line-height",($(".eachnumberleft").height())+"px");
      $(".titleleft").css("line-height",($(".titleleft").height()+15)+"px");
      $(".everyinternetdata").css("margin-top",($(".rightcontentdata").height()*0.02)+"px");
      
      var height = $(".eachprogress").find(".div2").height()-17;
      
      $("#divKuandaiProgressData").find(".eachprogress").each(function(i,e){
    	$(e).find(".div2").append(initProgress(1, 80, height, "blue", "small"));
    	$(e).find(".div3").append(initProgress(1, 85, height, "red", "small"));
      });
      $(".kuandaiprodiv").mousemove(function(e){
		  onMouseOver(e,$(this).attr("datatitle"));
	  });
	  $(".kuandaiprodiv").mouseout(function(e){
		  onMouseOut(e);
	  });
  } 
  /* 加载宽带 */
  function initKuandaiData(data){
  	var height = $(".eachprogress").find(".div2").height()-17;
  	if(data.length !=0){
	  	for(var i=0;i<data.length;i++){
	  		var obj = data[i];
	  		$("#div"+obj.regionId+"Online").find(".kuandaiprodiv").find(".prodiv").velocity({
				width:obj.onlineData*100+"%"
			},{
				duration:800
			});
			$("#div"+obj.regionId+"Outline").find(".kuandaiprodiv").find(".prodiv").velocity({
				width:obj.outlineData*100+"%"
			},{
				duration:800
			});
			$("#div"+obj.regionId+"Online").find(".kuandaiprodiv").attr("datatitle",(obj.onlineData*100).toFixed(2)+"%");
			$("#div"+obj.regionId+"Outline").find(".kuandaiprodiv").attr("datatitle",(obj.outlineData*100).toFixed(2)+"%");
	  	}
  	}
  }
  /* 改变宽带 */
  function changeKudandaiData(){
  		$.ajax({
	  		url:"getkuandaidata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate,type:broadband},
	  		success:function(data){
	  			var height = $(".eachprogress").find(".div2").height()-17;
			  	if(data.length !=0){
				  	for(var i=0;i<data.length;i++){
				  		var obj = data[i];
				  		$("#div"+obj.regionId+"Online").find(".kuandaiprodiv").find(".prodiv").velocity({
							width:obj.onlineData*100+"%"
						},{
							duration:800
						});
						$("#div"+obj.regionId+"Outline").find(".kuandaiprodiv").find(".prodiv").velocity({
							width:obj.outlineData*100+"%"
						},{
							duration:800
						});
						$("#div"+obj.regionId+"Online").find(".kuandaiprodiv").attr("datatitle",(obj.onlineData*100).toFixed(2)+"%");
						$("#div"+obj.regionId+"Outline").find(".kuandaiprodiv").attr("datatitle",(obj.outlineData*100).toFixed(2)+"%");
				  	}
			  	}
	  		}
	  	});
  }
  function initNumberBoard(data){
  	$("#divPropNum").html("");
  	$("#divUserNum").html("");
  	var propnum=data.prop.toString();
  	var usernum=data.user.toString();
  	var propstr=propnum.toString();
  	var userstr=usernum.toString();
  	var propcode = "";
  	var usercode = "";
  	if(propstr!=0&&propstr!=null){
  	      propcode="<div class='businissleft' style='width:auto;height:100%;margin-left:auto;margin-right:auto;'> ";
  		for(var i=propstr.length;i>0;i--){
  			propcode+="<div class='eachnumberleft'>"+propstr.substring(i-1,i)+"</div>";
  		}
  		 propcode+="</div>";	
  	}
  	if(userstr!=0&&userstr!=null){
  	    usercode="<div class='businissleft' style='width:auto;height:100%;margin-left:auto;margin-right:auto;'> ";
  		for(var i=userstr.length;i>0;i--){
  			usercode+="<div class='eachnumberright'>"+userstr.substring(i-1,i)+"</div>";
  		}
  		usercode+="</div>";
  	}
  	$("#divPropNum").html(propcode);
  	$("#divUserNum").html(usercode);
  	$(".eachnumberleft").css("width",($(".alleachnumber").width()*0.08)+"px");
  	$(".eachnumberright").css("width",($(".alleachnumber").width()*0.08)+"px");
  	$(".eachnumberleft").css("line-height",($(".eachnumberleft").height())+"px");
    $(".eachnumberright").css("line-height",($(".eachnumberleft").height())+"px");
     $(".titleleft").css("line-height",($(".alleachnumber").height())+"px");
  	$(".businissleft").css("width",(($(".eachnumberleft").width())*(propstr.length)+(propstr.length)*4+4)+"px");
  }
  /* 加载进度条 */
	function initProgress(percent,divwidth,height,style,type){
		var classname="bluestyle";
		var width = divwidth-5;
		if(style=="red"){
			classname="redstyle";
		}
	  	var porgressDom=$("<div style='width:1px;height: "+height+"px;border:1px solid #A5ABB7;float:left'></div><div class='"+classname+" kuandaiprodiv' style='width: "+width+"px;height: "+height+"px;' datatitle='0.00%' ></div><div style='width:1px;height: "+height+"px;border:1px solid #A5ABB7;float:left'></div>");
		var progressNum = Math.floor(percent*10);
		if(percent!=0 && progressNum==0){
			progressNum=1;
		}
		var eachwidth="";
		var eachheight="";
		var eachleftpand="";
		var eachtoppand=2;
		if(type=="big"){
			eachleftpand=2;
		}else if(type=="small"){
			eachleftpand=1;
		}else{
			eachleftpand=1;
		}
		eachheight=height-eachtoppand*2;
		eachwidth=(width/10)-eachleftpand*2;
		var prodivdom = $("<div class='prodiv'></div>");
		for(var i=0;i<progressNum;i++){
			var htmlcode="<div style='float:left;width: "+eachwidth+"px;margin-left: "+eachleftpand+"px;margin-right: "+eachleftpand+"px; "+
						"height: "+eachheight+"px;margin-top: "+eachtoppand+"px'  class='progress"+i+"'></div>";
			var eachdom=$(htmlcode);
			prodivdom.append(eachdom);
		}
		porgressDom.append(prodivdom);
		return porgressDom;
	}
	/* 加载内部进度条 */
	function initEachProgress(percent,divwidth,height,style,type){
		var htmlcode="";
		var classname="bluestyle";
		var width = divwidth-5;
		if(style=="red"){
			classname="redstyle";
		}
		var progressNum = Math.floor(percent*10);
		if(percent!=0 && progressNum==0){
			progressNum=1;
		}
		var eachwidth="";
		var eachheight="";
		var eachleftpand="";
		var eachtoppand=2;
		if(type=="big"){
			eachleftpand=2;
		}else if(type=="small"){
			eachleftpand=1;
		}else{
			eachleftpand=1;
		}
		eachheight=height-eachtoppand*2;
		eachwidth=(width/10)-eachleftpand*2;
		for(var i=0;i<progressNum;i++){
			htmlcode+="<div style='float:left;width: "+eachwidth+"px;margin-left: "+eachleftpand+"px;margin-right: "+eachleftpand+"px; "+
						"height: "+eachheight+"px;margin-top: "+eachtoppand+"px'  class='progress"+i+"'></div>";
		}
		return htmlcode;
	}
	/* 加载流量进度条 */
	function initFluxProgress(data){
		var basewidth = 50;
		if(data.length==0){
			$(".onlinefludiv").each(function(i,e){
				$(e).find(".progress").width(0);
				$(e).find(".number").html("0%");
			});
			$(".outlinefludiv").each(function(i,e){
				$(e).find(".progress").width(0);
				$(e).find(".number").html("0%");
			});
		}else{
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				$("#div"+obj.regionId+"OnlineFluxData").find(".onlinefludiv").find(".progress").velocity({
					width:basewidth*obj.onlineData+"px"
				},{
					duration:800,
					progress:function(ele){
						$(ele).parent().find(".number").html(Math.round(($(ele).width()/50)*100)+"%");
					}
				});
				$("#div"+obj.regionId+"OutlineFluxData").find(".outlinefludiv").find(".progress").velocity({
					width:basewidth*obj.outlineData+"px"
				},{
					duration:800,
					progress:function(ele){
						$(ele).parent().find(".number").html(Math.round(($(ele).width()/50)*100)+"%");
					}
				});
			}
		}
	}
	/* 切换套餐显示 */
	function changesection(index,e){
		$(".cur_a").removeClass("cur_a");
		$(e).addClass("cur_a");
		$(".eachsection").hide();
  		$(".eachsection").eq(index).show();
	}
	//柱状图
	function createcolumn(id,data){
		highchart = new Highcharts.Chart({
		        chart: {
		            backgroundColor:'rgba(0,0,0,0)',
		            renderTo:id
		        },
		        xAxis: {
		            categories: data.datax,
		            gridLineWidth:0,
		            lineWidth:0,
		            tickLength:0,
		             labels:{
				         style:{"color":"#FFFFFF", "fontSize":"12px","fontFamily":"微软雅黑"}
				       }
		        },
		        yAxis: [{ 
		        	gridLineWidth:0,
		            labels: {
		            	formatter: function() {
			                //return this.value;	
			            },
		                style: {
		                    "color": "black",
		                    "fontSize":"11px"
		                }
		            },
		            title: {
		                text: null,
		                style: {
		                    color: "black"
		                }
		            }/*,
		            tickInterval:1000*/
		        }],
		        tooltip: {
		            formatter:function(){
		            	var code = "";
		            	code = this.x+"<br/>";
		            	$.each(this.points,function(){
			            	code+="<br/>"+this.series.name+":"+this.y+"%";
		            	});
		            	return code;
		            },
		            shared:true,
		            backgroundColor:'#085696',
		            style:{color:"#fff"},
		            borderWidth:0
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'top',
		            floating: true,
		            y: -10,
		            x:10,
		            itemStyle:{fontSize:"12px",lineHeight:"12px",color:"black"},
		            enabled:false
		        },
		        plotOptions:{
		        	column:{
		        		stacking:'normal',
		        		pointPadding:0.3,
		        		borderWidth:0
		        	}
		        },
		        series: [{
		            type: 'column',
		            name:'线上',
		            data: data.datay.onlinedata,
		            color: '#D95B77',
		            tooltip: {
		                valueSuffix: "%",
		            }
		        },
		        {
		            type: 'column',
		            name:'线下',
		            data: data.datay.outlinedata,
		            color: '#06D9E1',
		            tooltip: {
		                valueSuffix: "%",
		            }
		        }]
		    });
	}
	function changeHighChart(){
		$.ajax({
	  		url:"getotherdata.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{date:curdate,type:otherbusiness},
	  		success:function(data){
	  			highchart.series[0].setData(data.datay.onlinedata);
				highchart.series[1].setData(data.datay.outlinedata);
	  		}
	  	});
	}
	function onMouseOver(e,data){
		var pageX=e.pageX;
		var pageY=e.pageY;
		$("#popDivTitle").css("top",pageY+5+"px");
		$("#popDivTitle").css("left",pageX+20+"px");
		$("#popDivTitle").html(data);
		$("#popDivTitle").show();
	}
	function onMouseOut(e){
		$("#popDivTitle").css("top","0px");
		$("#popDivTitle").css("left","0px");
		$("#popDivTitle").html("");
		$("#popDivTitle").hide();
	}
</script>
</html>