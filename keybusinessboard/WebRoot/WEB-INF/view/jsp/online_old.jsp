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

<title>线上</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- CSS引入 -->
<link rel="stylesheet" href="css/line/page.css" type="text/css"></link>
<link rel="stylesheet" href="css/progress.css" type="text/css"></link>
<!-- JS引入 -->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="plugin/selectlist/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/Echarts-zhoulei.js"></script>
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
/* 	.main{
	   width:100%;
	   height:100%;
	}
	
	.rightpart{
	  width:270px;
	  height:99.5%;
	  float: left;
	}
	.rightcontent{
	  width:250px;
	  height:100%;
	  float:left;
	  border: 1px solid #1b7c91;
	  margin-left: 9px;
	}
	.middlepart{
	  height:99.5%;
	  float: left;
	  margin-left: 6px;
	}
	.topclass{
	  height:30%;
	  float:left;
      width:100%;
       border: 1px solid #1b7c91;
	}
	.middleclass{
	  height:45%;
	  float:left;
      width:100%;
	}
	.middlefont{
	  line-height:18px;
	  width:100%;
	  height:18px;
	  float:left;
	  border-left: 2px solid #ed496b;
	  font-size: 15px;
	  text-indent: 1em;
	  margin-top: 2px;
	  margin-bottom: 2px;
	  color:#FFFFFF
	}
	.middleonedata{
	  width:100%;
	  float:left;
	  border: 1px solid #1b7c91;
	  margin-top: 3px;
	}
	.bottomclass{
	  height:25%;
	  float:left;
      width:100%;
	}
	.bottomdata{
	  width:100%;
	  float:left;
	  border: 1px solid #1b7c91;
	  margin-top: 3px;
	}
	.leftpart{
	  width:270px;
	  height:99.5%;
	  float: right;
	}
	.leftcontent{
	  width	:250px;
	  height:100%;
	  float:left;
	  border: 1px solid #1b7c91;
	  margin-left: 9px;
	}
  */
.floweverystyle{
	border: 1px solid #109DD3;
	/* For IF9 Or opera */
	box-shadow:0px 0px 10px #109DD3 inset;
	/* For FireFox */
	-moz-box-shadow:0px 0px 10px #109DD3 inset;
	/* For Safari Or Chrome */
	-webkit-box-shadow:0px 0px 10px #109DD3 inset;
}

</style>

</head>

<body id="body" class="scoll">
   <div class="rightpart">
        <div class="rightcontent">
             <div class="rightfont">宽带业务</div>
             <div class="newleftdiv"  style="float:left;margin-top: 2px;width:100%;border: 1px solid #0ac1cb;">
               <div id="divKuandaiProgressData" class="rightcontentdata" >
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">石家庄</div>
                     <div id="divSJZOnline" class="everyinternetdatabart"></div>
                  </div>
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">秦皇岛</div>
                     <div id="divQHDOnline"" class="everyinternetdatabart"></div>
                  </div>
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">张家口</div>
                     <div id="divZJKOnline" class="everyinternetdatabart"></div>
                  </div>
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">廊坊</div>
                     <div id="divLFOnline" class="everyinternetdatabart"></div>
                  </div>
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">唐山</div>
                     <div id="divTSOnline" class="everyinternetdatabart"></div>
                  </div>
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">衡水</div>
                     <div id="divHSOnline" class="everyinternetdatabart"></div>
                  </div>
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">保定</div>
                     <div id="divBDOnline" class="everyinternetdatabart"></div>
                  </div>
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">承德</div>
                     <div id="divCDOnline" class="everyinternetdatabart"></div>
                  </div>
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">邢台</div>
                     <div id="divXTOnline" class="everyinternetdatabart"></div>
                  </div>
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">邯郸</div>
                     <div id="divHDOnline" class="everyinternetdatabart"></div>
                  </div>
                  <div class="everyinternetdata">
                     <div class="everyinternetfont">沧州</div>
                     <div id="divCZOnline" class="everyinternetdatabart"></div>
                  </div>
             </div>
             <div class="rightchage" style="background-color: #19283D;">
                  <div class="righteachchage" data-type="pack_gl.g.WBAND"   title="家庭宽带产品包"  style="border-bottom:3px solid #ed496b "><i class="iconfont">&#xe602;</i></div>
                  <div class="righteachchage" data-type="pack_gl.g.mobileWBAND" title="手机宽带产品包"><i class="iconfont"  style="font-size:25px;">&#xe603;</i></div>
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
			<div class="rightdiv" style="float:right">
				<div class="title">业务办理量</div>
				<div id="divBusHan" class="info">
				     <div class="businessvolume">
				          <div  class="businessleft">
				             
				               <div class="businesstitle">
				                    <div class="titleleft"><i class="iconfont" style="color: #00FFFC">&#xe8a4;</i>办理量</div>
				               </div>
				               <div class="businesstitle">
				                    <div class="titleleft"><i class="iconfont" style="color: #FEB501">&#xe61f;</i>办理用户</div>
				               </div>
				          </div>
				          <div  class="businessright">
				               <div id= "banliliang" class="alleachnumber">
				               </div>
				               <div id= "banliyonghu" class="alleachnumber">
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
	                       <div id="divPackChart8" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">8元&nbsp;&nbsp;<span id="divPackRate8" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
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
             	</div>
             	<div class="eachsection">
             		<div class="middleevery">
	                       <div id="divPackChart38" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">38元&nbsp;&nbsp;<span id="divPackRate38" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart48" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">48元&nbsp;&nbsp;<span id="divPackRate48" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart58" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">58元&nbsp;&nbsp;<span id="divPackRate58" style="font-size: 16px;font-weight: bold;">--</span></div>
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
	                       <div id="divPackChart138" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">138元&nbsp;&nbsp;<span id="divPackRate138" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart158" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">158元&nbsp;&nbsp;<span id="divPackRate158" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
             	</div>
             	<div class="eachsection">
             		<div class="middleevery">
	                       <div id="divPackChart238" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">238元&nbsp;&nbsp;<span id="divPackRate238" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart268" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">268元&nbsp;&nbsp;<span id="divPackRate268" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
	                  <div class="middleevery">
	                       <div id="divPackChart338" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">338元&nbsp;&nbsp;<span id="divPackRate338" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
             	</div>
             	<div class="eachsection">
             		<div class="middleevery">
	                       <div id="divPackChart588" class="middleeverydpicture"></div>
	                       <div class="middleeveryddata">
	                            <div class="middleeverybuttom">588元&nbsp;&nbsp;<span id="divPackRate588" style="font-size: 16px;font-weight: bold;">--</span></div>
	                       </div>
	                  </div>
             	</div>
              <!--    <div class="navgition">
             		<a class="cur_a" href="javascript:void(0);" onclick="changesection(0,this)">●</a>
             		<a href="javascript:void(0);" onclick="changesection(1,this)">●</a>
             		<a href="javascript:void(0);" onclick="changesection(2,this)">●</a>
             		<a href="javascript:void(0);" onclick="changesection(3,this)">●</a>
             		<a href="javascript:void(0);" onclick="changesection(4,this)">●</a>
            	 </div> -->
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
             			<div class="name">飞信</div>
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
             <div class="newleftdiv"  style="float:left;margin-top: 2px;width:100%;border: 1px solid #0ac1cb;">
               <div class="leftcontentdata">
                  <div id="divSJZOnlineFluxData" class="floweverystyle">
                       <div class="floweverystylefont">石家庄</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
                  <div id="divQHDOnlineFluxData" class="floweverystyle">
                       <div class="floweverystylefont">秦皇岛</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
                  <div id="divZJKOnlineFluxData" class="floweverystyle">
                       <div class="floweverystylefont">张家口</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
                  <div id="divLFOnlineFluxData"  class="floweverystyle">
                       <div class="floweverystylefont">廊坊</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
                  <div id="divTSOnlineFluxData"  class="floweverystyle">
                       <div class="floweverystylefont">唐山</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
                  <div id="divHSOnlineFluxData"  class="floweverystyle">
                       <div class="floweverystylefont">衡水</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
                  <div id="divBDOnlineFluxData"  class="floweverystyle">
                       <div class="floweverystylefont">保定</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
                  <div id="divCDOnlineFluxData"  class="floweverystyle">
                       <div class="floweverystylefont">承德</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
                  <div id="divXTOnlineFluxData"  class="floweverystyle">
                       <div class="floweverystylefont">邢台</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
                  <div id="divHDOnlineFluxData"  class="floweverystyle">
                       <div class="floweverystylefont">邯郸</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
                  <div id="divCZOnlineFluxData" class="floweverystyle">
                       <div class="floweverystylefont">沧州</div>
                       <div class="floweverybar"></div>
                       <div class="floweverystylenum"></div>
                  </div>
              </div>
              <div class="rightchage">
                  <div class="leftachchage" data-type="pri_gl.g.llchb.10y1000m"  title="流量超惠包(10元1000M)"  style="border-bottom:3px solid #ed496b"><i class="iconfont">&#xe60d;</i></div>
                  <div class="leftachchage" data-type="pri_gl.g.2017ty4Gs6Gll" title="2017体验移动4G送6个G省内通用流量"><i class="iconfont" style="font-size:25px;" >&#xe604;</i></div>
                  <div class="leftachchage" data-type="pri_gl.g.cjllb_30y_2G" title="超级流量包30元2G"><i class="iconfont">&#xe60e;</i></div>
                  <div class="leftachchage" data-type="pri_gl.g.gndxspllb9_app1" title="国内定向视频流量包9元_优酷"><i class="iconfont">&#xe669;</i></div>
                  <div class="leftachchage" style="border-right:0px;" data-type="pri_gl.g.zdll.10y100mjjb" title="流量安心包"><i class="iconfont">&#xe60c;</i></div>
             </div>
           </div> 
        </div> 
   </div>
     <div id="popDivTitle" class="pop_titlediv"></div>
</body>
<script type="text/javascript">
    var broadband="pack_gl.g.WBAND";//宽带业务的类型
    var otherbusiness="PRIV_1300000083_hlyty";//其他业务的类型
    var flowtype="pri_gl.g.llchb.10y1000m";//流量包的类型
    var dateMax="<%=request.getAttribute("maxdate")%>"
	var dateMin="<%=request.getAttribute("mindate")%>"
	var curdate="<%=request.getAttribute("curdate")%>";
	var mydata="";//饼图的全局变量
	var highchart;
  $(function(){
     adjust();
       //滚动条
    $(".scoll").niceScroll({
	 cursorcolor : "#00AFFF",
	 cursorborderradius: "0px",
	 cursorwidth: "3px",
	 cursorborder: "0px solid #fff",
	  autohidemode:false
	 });
     broadbanddata();//宽带的数据
     flowpacket();//流量包
     otherbusiness1();//其他业务
     keykeymanagement();//重点业务
     businessvloume();//办理用户数
     flyclass();//4g飞享套餐
  })
  function main(date){
     curdate=date;
     changeKudandaiData();//加载宽带
     flowpacket();//流量包
     changeHighChart();//其他业务
     mainkeykeykeymanagement();//重点业务
     businessvloume();//办理用户数
     minflyclass();//4g飞享套餐
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
      flowpacket();
   })
  //切换其他业务类型 
  function changeBusType(e){
  	  $(".cureachbg").removeClass("cureachbg");
  	  $(".curboderbg").removeClass("curboderbg");
  	  $(e).addClass("cureachbg");
  	  $(e).find(".boder").addClass("curboderbg");
  	  otherbusiness = $(e).attr("data-type");
  	  otherbusiness1();
  	}
   //宽带业务的数据查询
    function broadbanddata(){
         $.ajax({
                url:"broadbanddata.do",
                type:"post",
                dataType:"json",
                data:{onlineoroutline:"Online",
                      curdate:curdate,
                      brandtype:broadband
                     },
                success:function(data){
                  initKuandaiData(data);
                }     
         })
    }
   //加载宽带 
  function initKuandaiData(data){
  	if(data.length !=0){
	  	for(var i=0;i<data.length;i++){
	  		var obj = data[i];
	  		$("#div"+obj.regionId+"Online").find(".kuandaiprodiv").find(".prodiv").velocity({
				width:obj.onlineData*100+"%"
			},{
				duration:800
			});
	  		$("#div"+obj.regionId+"Online").find(".kuandaiprodiv").attr("datatitle",(obj.onlineData*100).toFixed(2)+"%");
	  		
	  	}
  	}
  
  }
   /* 改变宽带 */
  function changeKudandaiData(){
  		$.ajax({
	  		url:"broadbanddata.do",
	  		type:"post",
	  		dataType:"json",
	  		 data:{onlineoroutline:"Online",
                      curdate:curdate,
                      brandtype:broadband
                   },
	  		   success:function(data){
	  		    var height = $(".everyinternetdatabart").height();
             	var width=$(".everyinternetdatabart").width();
			  	if(data.length !=0){
				  	for(var i=0;i<data.length;i++){
				  		var obj = data[i];
				  		/* var onlinecode = initEachProgress(obj.onlineData, width, height, "blue", "big"); */
				  		$("#div"+obj.regionId+"Online").find(".kuandaiprodiv").find(".prodiv").velocity({
							width:obj.onlineData*100+"%"
						},{
							duration:800
						});
				  		/* $("#div"+obj.regionId+"Online").find(".prodiv").html(onlinecode); */
				  		$("#div"+obj.regionId+"Online").find(".kuandaiprodiv").attr("datatitle",(obj.onlineData*100).toFixed(2)+"%");
				  	}
			  	}
	  		}
	  	});
  }
  /* 加载进度条 */
	function initProgress(percent,divwidth,height,style,type){
		var classname="bluestyle";
		var width = divwidth-6;
		if(style=="red"){
			classname="redstyle";
		}
	  	var porgressDom=$("<div style='width:1px;height: "+height+"px;border:1px solid #A5ABB7;float:left'></div><div class='"+classname+" kuandaiprodiv' style='width: "+width+"px;height: "+height+"px;' datatitle='"+(percent*100).toFixed(2)+"%' ></div><div style='width:1px;height: "+height+"px;border:1px solid #A5ABB7;float:left'></div>");
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
		var width = divwidth-6;
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
	
	
	
	//流量包数据
	function flowpacket(){
	      $.ajax({
                url:"flowpacket.do",
                type:"post",
                dataType:"json",
                data:{onlineoroutline:"Online",
                      curdate:curdate,
                      flowtype:flowtype
                     },
                success:function(data){
                  initFluxProgress(data);
                }     
         })
	}
  	/* 加载流量进度条 */
/* 	function initFluxProgress(data){
		var basewidth = 135;
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			$("#div"+obj.regionId+"OnlineFluxData").find(".floweverybar").width(basewidth*obj.onlineData+"px");
			$("#div"+obj.regionId+"OnlineFluxData").find(".floweverystylenum").html((obj.onlineData*100).toFixed(2)+"%");
		}
	} */
	
	/* 加载流量进度条 */
	function initFluxProgress(data){
		var basewidth = 135;
		if(data.length==0){
			$(".floweverystylefont").each(function(i,e){
				$(e).find(".floweverybar").width(0);
				$(e).find(".floweverystylenum").html("0%");
			});
		}else{
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				$("#div"+obj.regionId+"OnlineFluxData").find(".floweverybar").velocity({
					width:basewidth*obj.onlineData+"px"
				},{
					duration:800,
					progress:function(ele){
						$(ele).parent().find(".floweverystylenum").html(((($(ele).width())/basewidth)*100).toFixed(2)+"%");
					}
				});
			}
		}
	}
	
  function adjust(){
      var w=$(document).width();
      var h=$(document).height();
      $(".middlepart").width(w-555);
      $(".middleonedata").height($(".middleclass").height()-35);
      $(".middleonedata").find(".eachsection").height($(".middleclass").height()-46);
      $(".bottomdata").height($(".bottomclass").height()-26);
      
      $(".newleftdiv").height($(".rightcontent").height()-23);
      $(".rightcontentdata").height($(".newleftdiv").height()-30);
      $(".leftcontentdata").height($(".newleftdiv").height()-30);
      
      
      
      $("#divKeyBus").height($(".leftdiv").height()-$(".leftdiv").find(".title").height());
	  $("#divBusHan").height($(".rightdiv").height()-$(".rightdiv").find(".title").height());
	  $(".bottomdata").find(".chart").height($(".bottomdata").height()-$(".bottomdata").find(".nav").height()-1);
	  $(".bottomdata").find(".nav").find(".eachnav1").width(($(".bottomdata").width()/5)-1);
	  $(".bottomdata").find(".nav").find(".eachnav2").width($(".bottomdata").width()/5);
      $(".floweverystylefont").css("line-height",$(".floweverystylefont").height()+"px");
      $(".floweverystylenum").css("line-height",$(".floweverystylenum").height()+"px");
      $(".floweverybar").css("margin-top",($(".floweverystyle").height()-6)/2+"px");
      $(".eachnumberleft").css("line-height",($(".eachnumberleft").height())+"px");
      $(".eachnumberright").css("line-height",($(".eachnumberleft").height())+"px");
      $(".titleleft").css("line-height",($(".titleleft").height()+15)+"px");
      $(".everyinternetdata").css("margin-top",($(".rightcontentdata").height()*0.02)+"px");
      $(".floweverystyle").css("margin-top",($(".leftcontentdata").height()*0.035)+"px");
      
      
      
       var height = $(".everyinternetdatabart").height();
       var width=$(".everyinternetdatabart").width();
      $("#divKuandaiProgressData").find(".everyinternetdata").each(function(i,e){
    	$(e).find(".everyinternetdatabart").append(initProgress(1, width, height, "blue", "big"));
      });
      $(".kuandaiprodiv").mousemove(function(e){
		  onMouseOver(e,$(this).attr("datatitle"));
	  });
	  $(".kuandaiprodiv").mouseout(function(e){
		  onMouseOut(e);
	  });
  } 
  
  
  	//其他业务的数据查询
	function otherbusiness1(){
	     $.ajax({
                url:"otherbusiness.do",
                type:"post",
                dataType:"json",
                data:{onlineoroutline:"Online",
                      curdate:curdate,
                      otherbusiness:otherbusiness
                     },
                success:function(data){
                  createcolumn("otherbusiness",data);
                }     
         })
	}
 function changeHighChart(){
		$.ajax({
	  		url:"otherbusiness.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{onlineoroutline:"Online",
                  curdate:curdate,
                  otherbusiness:otherbusiness
                  },
	  		success:function(data){
	  			highchart.series[0].setData(data);
	  		}
	  	});
	}
	
	
	//重点业务的办理
	function keykeymanagement(){
	      $.ajax({
                url:"keymanagement.do",
                type:"post",
                dataType:"json",
                data:{onlineoroutline:"Online",
                      curdate:curdate,
                     },
                success:function(data){
                  if(data.length>0){
                    singlepie(data[0],"divKeyBus");
                     mydata=data[0].datay;
                  }else{
                    $("#divKeyBus").html("");
                    mydata=0;
                  }
                }     
         })
	
	}
	function mainkeykeykeymanagement(){
	        $.ajax({
                url:"keymanagement.do",
                type:"post",
                dataType:"json",
                data:{onlineoroutline:"Offline",
                      curdate:curdate,
                     },
                success:function(data){
                  if(data.length>0){
                    outlinesinglepie(data[0],"divKeyBus");
                  }else{
                    var keypie = echarts.getInstanceByDom(document.getElementById("divKeyBus"));
                    var keypiedata={series: [{data:data.datax}]};
                    keypie.setOption(keypiedata);
                  }
                }     
         })
	}
   //业务办理量
   function businessvloume(){
          $.ajax({
                url:"businessvloume.do",
                type:"post",
                dataType:"json",
                data:{onlineoroutline:"Online",
                      curdate:curdate,
                     },
                success:function(data){
                     initNumberBoard(data);
                }     
         })
   }
 function initNumberBoard(data){
  	$("#banliliang").html("");
  	$("#banliyonghu").html("");
  	var propnum=data[0].prop.toString();
  	var usernum=data[0].user.toString();
  	var propstr=propnum.toString();
  	var userstr=usernum.toString();
  	var propcode = "";
  	var usercode = "";
  	if(propstr!=0&&propstr!=null){
  	    propcode="<div class='businissleft' style='width:auto;height:100%;margin-left:auto;margin-right:auto;'> ";
  		for(var i=propstr.length;i>0;i--){
  			propcode+="<div class='eachnumberleft'>"+propstr.substring(i-1,i)+"</div>" ;
  		}
  	   propcode+="</div>";	
  	}
  	if(userstr!=0&&userstr!=null){
  	    usercode="<div class='businissleft' style='width:auto;height:100%;margin-left:auto;margin-right:auto;'> ";
  		for(var i=userstr.length;i>0;i--){
  			usercode+="<div class='eachnumberright' >"+userstr.substring(i-1,i)+"</div>";
  		}
  		 usercode+="</div>";	
  	}
  	$("#banliliang").html(propcode);
  	$("#banliyonghu").html(usercode);
    $(".titleleft").css("line-height",($(".alleachnumber").height())+"px");
  	$(".eachnumberleft").css("width",($(".alleachnumber").width()*0.09)+"px");
  	$(".eachnumberright").css("width",($(".alleachnumber").width()*0.09)+"px");
  	$(".eachnumberleft").css("line-height",($(".eachnumberleft").height())+"px");
    $(".eachnumberright").css("line-height",($(".eachnumberleft").height())+"px");
  	$(".businissleft").css("width",(($(".eachnumberleft").width())*(propstr.length)+(propstr.length)*4+4)+"px");
  }	
  
  //4g飞享类办理情况
    function flyclass(){
         $.ajax({
                url:"flyclass.do",
                type:"post",
                dataType:"json",
                data:{onlineoroutline:"Online",
                      curdate:curdate,
                     },
                success:function(data){
			    polarother("divPackChart8",data["fxtc8y"]);
	  			polarother("divPackChart18",data["fxtc18y"]);
	  			polarother("divPackChart28",data["fxtc28y"]);
	  			polarother("divPackChart38",data["fxtc38y"]);
	  			polarother("divPackChart48",data["fxtc48y"]);
	  			polarother("divPackChart58",data["fxtc58y"]);
	  			polarother("divPackChart88",data["fxtc88y"]);
	  			polarother("divPackChart138",data["fxtc138y"]);
	  			polarother("divPackChart158",data["fxtc158y"]);
	  			polarother("divPackChart238",data["fxtc238y"]);
	  			polarother("divPackChart268",data["fxtc268y"]);
	  			polarother("divPackChart338",data["fxtc338y"]);
	  			polarother("divPackChart588",data["fxtc588y"]);
	  			$("#divPackRate8").html(data["fxtc8yrate"]+"%");
	  			$("#divPackRate18").html(data["fxtc18yrate"]+"%");
	  			$("#divPackRate28").html(data["fxtc28yrate"]+"%");
	  			$("#divPackRate38").html(data["fxtc38yrate"]+"%");
	  			$("#divPackRate48").html(data["fxtc48yrate"]+"%");
	  			$("#divPackRate58").html(data["fxtc58yrate"]+"%");
	  			$("#divPackRate88").html(data["fxtc88yrate"]+"%");
	  			$("#divPackRate138").html(data["fxtc138yrate"]+"%");
	  			$("#divPackRate158").html(data["fxtc158yrate"]+"%");
	  			$("#divPackRate238").html(data["fxtc238yrate"]+"%");
	  			$("#divPackRate268").html(data["fxtc268yrate"]+"%");
	  			$("#divPackRate338").html(data["fxtc338yrate"]+"%");
	  			$("#divPackRate588").html(data["fxtc588yrate"]+"%");
                }     
         })
    }
  //min方法调用
    function minflyclass(){
            $.ajax({
	  		url:"flyclass.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{onlineoroutline:"Online",curdate:curdate},
	  		success:function(data){
	  			var packchart8 = echarts.getInstanceByDom(document.getElementById("divPackChart8"));
	  			var packchart18 = echarts.getInstanceByDom(document.getElementById("divPackChart18"));
	  			var packchart28 = echarts.getInstanceByDom(document.getElementById("divPackChart28"));
	  			var packchart38 = echarts.getInstanceByDom(document.getElementById("divPackChart38"));
	  			var packchart48 = echarts.getInstanceByDom(document.getElementById("divPackChart48"));
	  			var packchart58 = echarts.getInstanceByDom(document.getElementById("divPackChart58"));
	  			var packchart88 = echarts.getInstanceByDom(document.getElementById("divPackChart88"));
	  			var packchart138 = echarts.getInstanceByDom(document.getElementById("divPackChart138"));
	  			var packchart158 = echarts.getInstanceByDom(document.getElementById("divPackChart158"));
	  			var packchart238 = echarts.getInstanceByDom(document.getElementById("divPackChart238"));
	  			var packchart268 = echarts.getInstanceByDom(document.getElementById("divPackChart268"));
	  			var packchart338 = echarts.getInstanceByDom(document.getElementById("divPackChart338"));
	  			var packchart588 = echarts.getInstanceByDom(document.getElementById("divPackChart588"));
			  	var option8 ={series: [{data:data["fxtc8y"].datay.online}]};
			  	var option18 ={series: [{data:data["fxtc18y"].datay.online}]};
			  	var option28 ={series: [{data:data["fxtc28y"].datay.online}]};
			  	var option38 ={series: [{data:data["fxtc38y"].datay.online}]};
			  	var option48 ={series: [{data:data["fxtc48y"].datay.online}]};
			  	var option58 ={series: [{data:data["fxtc58y"].datay.online}]};
			  	var option88 ={series: [{data:data["fxtc88y"].datay.online}]};
			  	var option138 ={series: [{data:data["fxtc138y"].datay.online}]};
			  	var option158 ={series: [{data:data["fxtc158y"].datay.online}]};
			  	var option238 ={series: [{data:data["fxtc238y"].datay.online}]};
			  	var option268 ={series: [{data:data["fxtc268y"].datay.online}]};
			  	var option338 ={series: [{data:data["fxtc338y"].datay.online}]};
			  	var option588 ={series: [{data:data["fxtc588y"].datay.online}]};
			  	packchart8.setOption(option8);
			  	packchart18.setOption(option18);
			  	packchart28.setOption(option28);
			  	packchart38.setOption(option38);
			  	packchart48.setOption(option48);
			  	packchart58.setOption(option58);
			  	packchart88.setOption(option88);
			  	packchart138.setOption(option138);
			  	packchart158.setOption(option158);
			  	packchart238.setOption(option238);
			  	packchart268.setOption(option268);
			  	packchart338.setOption(option338);
			  	packchart588.setOption(option588);
			  	$("#divPackRate8").html(data["fxtc8yrate"]+"%");
	  			$("#divPackRate18").html(data["fxtc18yrate"]+"%");
	  			$("#divPackRate28").html(data["fxtc28yrate"]+"%");
	  			$("#divPackRate38").html(data["fxtc38yrate"]+"%");
	  			$("#divPackRate48").html(data["fxtc48yrate"]+"%");
	  			$("#divPackRate58").html(data["fxtc58yrate"]+"%");
	  			$("#divPackRate88").html(data["fxtc88yrate"]+"%");
	  			$("#divPackRate138").html(data["fxtc138yrate"]+"%");
	  			$("#divPackRate158").html(data["fxtc158yrate"]+"%");
	  			$("#divPackRate238").html(data["fxtc238yrate"]+"%");
	  			$("#divPackRate268").html(data["fxtc268yrate"]+"%");
	  			$("#divPackRate338").html(data["fxtc338yrate"]+"%");
	  			$("#divPackRate588").html(data["fxtc588yrate"]+"%");
	  		}
	  	});
    
    }
window.onresize=function(){
       adjust();
};  

//title的的样式
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
	//柱状图
function createcolumn(id,data){
     highchart=new Highcharts.Chart({
		chart: {
			backgroundColor:'rgba(0,0,0,0)',
			renderTo:id
		},
		xAxis: {
			categories: ['石家庄', '秦皇岛', '张家口', '廊坊','唐山', '衡水', '保定', '承德','邢台','邯郸','沧州'],
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
			// shared: true
			formatter:function(){
				return this.x +":  "+"<b>"+this.y+"</b> %" ;
			},
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
				pointPadding:0.3,
				borderWidth:0,
			}
		},
		series: [{
			type: 'column',
			data: data,
			color: '#00a7ed',
			tooltip: {
				valueSuffix: "%",
			}
		}]
     });
}
	
	
	
</script>


</html>
