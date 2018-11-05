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

<title>线下</title>

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
.floweverystyle{
	border: 1px solid #4BC284;
	/* For IF9 Or opera */
	box-shadow:0px 0px 10px #4BC284 inset;
	/* For FireFox */
	-moz-box-shadow:0px 0px 10px #4BC284 inset;
	/* For Safari Or Chrome */
	-webkit-box-shadow:0px 0px 10px #4BC284 inset;
}

</style>

</head>

<body id="body">
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
			<div class="rightdiv" style="float:right">
				<div class="title">业务办理量</div>
				<div id="divBusHan" class="info">
				     <div class="businessvolume">
				          <div  class="businessleft">
				               
				               <div class="businesstitle">
				                    <div class="titleleft"><i class="iconfont" style="color: #00E77D">&#xe8a4;</i>办理量</div>
				               </div>
				                <div class="businesstitle">
				                    <div class="titleleft"><i class="iconfont" style="color: #FD59C9">&#xe61f;</i>办理用户</div>
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
             <div class="middlefont" style="margin-top:2.5px; ">其他业务</div>
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
                  <div id="divSJZOnlineFluxData" class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">石家庄</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
                  <div id="divQHDOnlineFluxData" class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">秦皇岛</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
                  <div id="divZJKOnlineFluxData" class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">张家口</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
                  <div id="divLFOnlineFluxData"  class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">廊坊</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
                  <div id="divTSOnlineFluxData"  class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">唐山</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
                  <div id="divHSOnlineFluxData"  class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">衡水</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
                  <div id="divBDOnlineFluxData"  class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">保定</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
                  <div id="divCDOnlineFluxData"  class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">承德</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
                  <div id="divXTOnlineFluxData"  class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">邢台</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
                  <div id="divHDOnlineFluxData"  class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">邯郸</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
                  <div id="divCZOnlineFluxData" class="floweverystyle" style="border:1px solid #3bb681;">
                       <div class="floweverystylefont">沧州</div>
                       <div class="floweverybar" style="background:#71FF38"></div>
                       <div class="floweverystylenum" style="color:#3bb681;"></div>
                  </div>
              </div>
              <div class="rightchage">
                  <div class="leftachchage" data-type="pri_gl.g.4G.10y10btyjyb"  title="10元10倍套外加油包"  style="border-bottom:3px solid #ed496b"><i class="iconfont">&#xe60d;</i></div>
                  <div class="leftachchage" data-type="pri_gl.g.4G.10y100mjjb" title="10元100M流量加油包(10元1000M)"><i class="iconfont" style="font-size:25px;" >&#xe604;</i></div>
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
    var broadband="pack_gl.g.mobileWBAND";//宽带业务的类型
    var otherbusiness="PRIV_1300000083_hlyty";//其他业务的类型
    var flowtype="pri_gl.g.4G.10y100mjjb";//流量包的类型
    var dateMax="<%=request.getAttribute("maxdate")%>"
	var dateMin="<%=request.getAttribute("mindate")%>"
	var curdate="<%=request.getAttribute("curdate")%>";
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
     changeKudandaiData();//宽带的数据
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
                data:{onlineoroutline:"Offline",
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
  	var height = $(".everyinternetdatabart").height();
  	var width=$(".everyinternetdatabart").width();
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
	 $(".kuandaiprodiv").mousemove(function(e){
		onMouseOver(e,$(this).attr("datatitle"));
	  });
	 $(".kuandaiprodiv").mouseout(function(e){
		onMouseOut(e);
	  });
  }
   /* 改变宽带 */
  function changeKudandaiData(){
  		$.ajax({
	  		url:"broadbanddata.do",
	  		type:"post",
	  		dataType:"json",
	  		 data:{onlineoroutline:"Offline",
                      curdate:curdate,
                      brandtype:broadband
                   },
	  		   success:function(data){
	  		    var height = $(".everyinternetdatabart").height();
             	var width=$(".everyinternetdatabart").width();
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
	  	});
  }
  /* 加载进度条 */
	function initProgress(percent,divwidth,height,style,type){
		var classname="bluestyle";
		var width = divwidth-7;
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
		var width = divwidth-7;
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
		var prodivdom = $("<div class='prodiv'></div>");
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
                data:{onlineoroutline:"Offline",
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
    	$(e).find(".everyinternetdatabart").append(initProgress(1, width, height, "red", "big"));
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
                data:{onlineoroutline:"Offline",
                      curdate:curdate,
                      otherbusiness:otherbusiness
                     },
                success:function(data){
                  outlinecreatecolumn("otherbusiness",data);
                }     
         })
	}
	function changeHighChart(){
		$.ajax({
	  		url:"otherbusiness.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{onlineoroutline:"Offline",
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
                data:{onlineoroutline:"Offline",
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
  			propcode+="<div class='eachnumberleft' >"+propstr.substring(i-1,i)+"</div>";
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
  	$(".eachnumberleft").css("width",($(".alleachnumber").width()*0.09)+"px");
  	$(".eachnumberright").css("width",($(".alleachnumber").width()*0.09)+"px");
  	$(".eachnumberleft").css("line-height",($(".eachnumberleft").height())+"px");
    $(".eachnumberright").css("line-height",($(".eachnumberleft").height())+"px");
    $(".titleleft").css("line-height",($(".alleachnumber").height())+"px");
  	$(".businissleft").css("width",(($(".eachnumberleft").width())*(propstr.length)+(propstr.length)*4+4)+"px");
  }	
  
  //4g飞享类办理情况
    function flyclass(){
         $.ajax({
                url:"flyclass.do",
                type:"post",
                dataType:"json",
                data:{onlineoroutline:"Offline",
                      curdate:curdate,
                     },
                success:function(data){
			    oulinepolarother("divPackChart6",data["fxtc6y"]);
			    oulinepolarother("divPackChart8",data["fxtc8y"]);
			    oulinepolarother("divPackChart15",data["fxtc15y"]);
	  			oulinepolarother("divPackChart18",data["fxtc18y"]);
	  			oulinepolarother("divPackChart28",data["fxtc28y"]);
	  			oulinepolarother("divPackChart38",data["fxtc38y"]);
	  			oulinepolarother("divPackChart45",data["fxtc45y"]);
	  			oulinepolarother("divPackChart58",data["fxtc58y"]);
	  			oulinepolarother("divPackChart65",data["fxtc65y"]);
	  			oulinepolarother("divPackChart88",data["fxtc88y"]);
	  			oulinepolarother("divPackChart95",data["fxtc95y"]);
	  			oulinepolarother("divPackChart138",data["fxtc138y"]);
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
         })
    }
  //min方法调用
    function minflyclass(){
            $.ajax({
	  		url:"flyclass.do",
	  		type:"post",
	  		dataType:"json",
	  		data:{onlineoroutline:"Offline",curdate:curdate},
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
function outlinecreatecolumn(id,data){
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
			color: '#cc4263',
			tooltip: {
				valueSuffix: "%",
			}
		}]
    });
}
  
</script>


</html>
