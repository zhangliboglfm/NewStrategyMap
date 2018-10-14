<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>场景定制</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- CSS引入 -->
	<link rel="stylesheet" href="css/interestpointrank/interestpointrank.css" type="text/css"></link>
	<link rel="stylesheet" href="plugin/jedate/skin/jedate-common.css" type="text/css"></link>
	<link rel="stylesheet" href="plugin/jedate/skin/jedate.css" type="text/css"></link>
	<link rel="stylesheet" href="plugin/selectlist/css/selectlist.default.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="http://www.hebmcbass.com/service/arcgis_js_api/library/3.14/3.14/esri/css/esri.css">
	<!-- JS引入 -->
	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="plugin/jedate/jedate.js"></script>
	<script type="text/javascript" src="plugin/selectlist/jquery.nicescroll.js"></script>
	<script type="text/javascript" src="plugin/selectlist/miapsoft.autohide.js"></script>
	<script type="text/javascript" src="plugin/selectlist/miapsoft.selectlist.core.js"></script>
	<script type="text/javascript" src="js/interestpointrank/interestpointrank.js"></script>
	<script type="text/javascript" src="js/miapsoft.input.core.js"></script>
	<script type="text/javascript" src="http://www.hebmcbass.com/service/arcgis_js_api/library/3.14/3.14/init.js"></script>
	<script type="text/javascript" src="js/baish.check.js"></script>
	<script type="text/javascript" src="js/jquery.ui.core.js"></script>
    <script type="text/javascript" src="js/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="js/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="js/scenecustomization/scenecustomization.js"></script>
	<script type="text/javascript" src="js/miapsoft.ceil.gismap.js"></script>
	<script type="text/javascript" src="js/miapsoft.input.core.js"></script>
	
		
	
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
	.main{
	   width:100%;
	   height:100%;
	} 
	.topdiv{
	  width:100%;
	  height:55px;
	  background: #00AFFF;
	  float:left;
	}
	.customizedinconfont{
	  width:50px;
	  height:55px;
	  color:#FFFEFD;
	  float:left;
	  margin-left: 30px;
	}
	.customizedfont{
	  width:160px;
	  height:55px;
	  color:#FFFEFD;
	  float:left;
	  line-height: 55px;
	  text-align: center;
	  font-size: 18px;
	}
	.bottomiframe{
	  width:100%;
	  float:left;
	}
	.leftpart{
	  height:100%;
	  width:280px;
	  background: #E3F1F2;
	  position: absolute;
	  top:55px;
	  left:0px;
	  z-index: 9999;
	}
	.leftcontent{
	  height:100%;
	  width:230px;
	  margin-left: 15px;
	  float:left;
	  
	}
	.contentstyle{
	  height:35px;
	  width:100%;
	  float:left;
	  margin-top: 5px;
	  font-size: 18px;
	  line-height: 35px;
	}
	.inputstyle{
	  width:99%;
	  height:95%;
	  border: 0px;
	  font-size: 22px;
	}
	.textarerstyle{
	  width:222px;
	  height:222px;
	  float:left;
	  background: #FFFFFF;
	  
	}
	.typechoice{
	  width:100%;
	  height:20px;
	  float:left;
	  font-size: 15px;
	  margin-top: 7px;
	}
	
	.middlepart{
	  height:100%;
	  width:100%;
	  float:left;
	}
	.regionchoice{
	  position: absolute;
	  top:65px;
	  left:290px;
	  width:300px;
	  height:30px;
	  z-index: 9999;
	}
	#scalebarstyle{
	  position: absolute;
	  bottom:40px;
	  left:290px;
	  width:300px;
	  height:15px;
	  z-index: 9999;
	}
	
	.rightpart{
	  height:100%;
	  width:280px;
	  background: #E3F1F2;
	  position:absolute;
	  top:55px;
	  right:0px;
	  z-index:9999;
	  display: none;
	}
	.rightcontent{
	  height:100%;
	  width:260px;
	  margin-left: 15px;
	  float:left;
	  
	}
	.inputboder{
	  width:70px;
	  height:23px;
	  border: 1px solid #9dc3f7;
	  float:left;
	  font-size: 1px;
	  margin-top: 6px;
	 
	}
	.rightinputstyle{
	  width:68px;
	  height:20px;
	  border:0px;
	  font-size: 15px;
	  margin-bottom: 1px;
	 
	}
	.allbutton{
	  border-radius:5px;
	  cursor:pointer;
	  float:left;
	  background:#00afff;
	  height:30px;
	  width:65px;
	  font-size: 16px;
	  color:#FFFFFF;
	  text-align: center;
	  line-height: 30px;
	  margin-left: 20px;
	}
    .conditioinput{
	  width:70px;
	  height:20px;
	  border: 1px solid #9dc3f7;
	  float:left;
	  font-size: 1px;
	}
   .conditioinputstyle{
	  width:68px;
	  height:18px;
	  border:0px;
	  font-size: 13px;
	  margin-bottom: 1px;
	 
	}
	.datetypestyle{
	  line-height:20px;
	  height:100%;
	  width:30%;
	  cursor:pointer;
	  float:left;
	  color:#50d1f2;
	  font-size:20px;
	
	}
	.piontchagestyle{
	  line-height:20px;
	  height:20px;
	  width:50%;
	  cursor:pointer;
	  float:left;
	  color:#50d1f2;
	  font-size:15px;
	}
	/* 指标池弹出框 */
	.indexpooldiv{
	   position: absolute;
	   right: 280px;
	   top: 120px;
	   width: 850px;
	   height: 500px;
	   background-color: #fff;
	   border: 1px solid #cae0ee;
	   display: none;
	   z-index: 9999;
	}
	.indexpooldiv .title{width: 100%;height: 30px;line-height: 30px;text-indent: 1ex;background-color: #00AFFF;color: #fff;font-size: 14px;}
    .indexpooldiv .ifr{width: 100%;height: 100%;border: 0;}
    .indexpooldiv .title i{float: right;margin-right: 10px;font-size: 18px;cursor: pointer;}
    
    /* 地图部分样式 */
	.pointinfowindow{width: 200px;height: auto;overflow: hidden;}
	.pointinfowindow .eachindexinfo{width: 100%;height: 20px;line-height: 20px;}
	.pointinfowindow .eachindexinfo span.text{float: left;}
	.pointinfowindow .eachindexinfo span.value{float: right;}
	.pointinfowindow .eachindexinfo span.dosome{float: right;color:#3588d8;cursor:pointer;}
	/* 地图悬浮框样式 */
   .esriPopup .titlePane{background-color: #00AFFF !important;}
   .esriPopup  .maximize.titleButton{display: none !important;}
   /* 地图搜索 */
	.mapSearch{
		position: absolute;
		left: 550px;
		top:71px;
		height: 30px;
		width: 400px;
		background-color: #FFF;
		z-index: 99999;
		/* For IF9 Or opera */
		box-shadow:5px 5px 5px #BFD2D6;
		/* For FireFox */
		-moz-box-shadow:5px 5px 5px #BFD2D6;
		/* For Safari Or Chrome */
		-webkit-box-shadow:5px 5px 5px #BFD2D6;
		/* For IE8 */
		-ms-filter: "progid:DXImageTransform.Microsoft.Shadow(Strength=5,Direction=135,Color='#BFD2D6')";
	}
	.mapSearch .inputdiv{width: 370px;height: 100%;float: left;}
	.mapSearch .bntdiv{width: 30px;height: 100%;float: left;background-color: #00AFFE;line-height: 34px;text-align: center;cursor: pointer;}
	.mapSearch .inputdiv input{float: left;width: 325px;height: 26px;margin-top: 1px;margin-left:5px;;border: 0;color: #272727;line-height: 26px;}
	.mapSearch .inputdiv i{
		float: right;
		width: 30px;
		height: 24px;
		margin-right: 3px;
		margin-left: 2px;
		margin-top:3px;
		border-left: 1px solid #D8D8D8;
		font-size: 18px;
		cursor: pointer;
		text-align: center;
		color: #C9C9C9;
	}
	.mapSearch .bntdiv i{color: #fff;font-size: 22px;}
	/* 地图搜索结果 */
	.mapsearchresult{
		position: absolute;
		left: 0px;
		top: 0px;
		z-index: 99999;
		width: 400px;
		max-height: 120px;
		background-color: #fff;
		/* For IF9 Or opera */
		box-shadow:5px 5px 5px #BFD2D6;
		/* For FireFox */
		-moz-box-shadow:5px 5px 5px #BFD2D6;
		/* For Safari Or Chrome */
		-webkit-box-shadow:5px 5px 5px #BFD2D6;
		/* For IE8 */
		-ms-filter: "progid:DXImageTransform.Microsoft.Shadow(Strength=5,Direction=135,Color='#BFD2D6')";
		padding-bottom: 3px;
		display: none;
	}
	.mapsearchresult .eachmapsearchresult{width: 100%;height: 30px;line-height: 30px;text-indent: 5px;cursor: pointer;/* border-bottom: 1px solid #cae0ee */}
	.mapsearchresult .eachmapsearchresult i{float: left;color: #00AFFE;}
	.mapsearchresult .eachmapsearchresult .text{float: left;}
	.mapsearchresult .eachmapsearchresult .type{float: right;margin-right: 10px;}
	.mapsearchresult .eachmapsearchresult:HOVER{background-color: #98E0FF}
	</style>

  </head>
  
  <body id="body" >
      <div class="topdiv">
         <div class="customizedinconfont"><i style="font-size: 40px;text-align: center;line-height: 55px;" class="iconfont">&#xe7c8;</i></div>
         <div class="customizedfont">战略地图场景定制 </div>
      </div>
      <div class="bottomiframe">
          <div class="leftpart">
              <div class="leftcontent">
                <div id="fitlepoint1" style="height:auto;width:100%;float:left;">
                 <div class="contentstyle">场景名称:</div>
                 <div   class="contentstyle"  style="border: 1px solid #9dc3f7">
                  <input id ="secenname" class="inputstyle">
                 </div>
                 <div class="contentstyle" style="margin-top: 15px;">创建人联系电话:</div>
                 <div class="contentstyle"  style="border: 1px solid #9dc3f7">
                   <input id ="creatphone" class="inputstyle" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')">
                 </div>
                 <div class="contentstyle" style="margin-top: 15px;">场景描述:</div>
                 <div id="descritesecen"  class="textarerstyle"  style="border: 1px solid #9dc3f7;z-index:6666">
                    <textarea id="descritesecen1" rows="11" cols="28" style="font-size: 15px;overflow: auto;border: 0px;"> </textarea>
                 </div>
                </div>
                <div id="fitlepoint2" style="height:auto;width:100%;float:left;display: none">
                 <div class="contentstyle">场景名称:</div>
                 <div   class="contentstyle"  style="border: 1px solid #9dc3f7">
                  <input id ="secenname2" class="inputstyle">
                 </div>
                 <div class="contentstyle" style="margin-top: 15px;">创建人联系电话:</div>
                 <div class="contentstyle"  style="border: 1px solid #9dc3f7">
                   <input id ="creatphone2" class="inputstyle" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')">
                 </div>
                 <div class="contentstyle" style="margin-top: 15px;">场景描述:</div>
                 <div id="descritesecen2"  class="textarerstyle"  style="border: 1px solid #9dc3f7;">
                    <textarea id="descritesecen3" rows="11" cols="29" style="font-size: 15px;overflow: auto;border: 0px;"> </textarea>
                 </div>
                </div>
                <div id="typehidden" style="height:auto;width:100%;float:left">
                 <div class="contentstyle" style="margin-top: 15px;">场景配置模式:</div>
                  <div class="typechoice scenetype" data-type="pointchoice"  data-checked="false" data-checktype="one" onclick="IcontRadio(this)"><i class="iconfont" style="cursor: pointer;color:#00afff;margin-right: 5px">&#xe632;</i>兴趣点条件筛选</div>
                  <div class="typechoice scenetype" style="margin-top: 7px;" data-type="intereschoce"  data-checked="false" data-checktype="one" onclick="IcontRadio(this)"><i class="iconfont" style="cursor: pointer;color:#00afff;margin-right: 5px">&#xe658;</i>兴趣勾选点</div>
                 <div id="startscreen" style="cursor:pointer;margin-left:50px;float:left;background:#00afff;height:30px;width:130px;margin-top: 30px;font-size: 16px;color:#FFFFFF;text-align: center;line-height: 30px;">开始配置条件</div>
                </div>
              </div>
          </div>
          
          <div id="innermap" class="middlepart">
               <div class="regionchoice">
                    <div id="dishiselect" class="div_select"></div>
		            <div id="xianquselect" class="div_select"></div>
               </div> 
               <div id="scalebarstyle"></div>
          </div>
          <div id="rightpart1" class="rightpart" >
             <div class="rightcontent">
           <!--    <div class="contentstyle">兴趣点移动用户数:</div>
              <div class="contentstyle" >
                  <div class="inputboder">
                  <input id="numberid1" class="rightinputstyle"  onkeyup="this.value=this.value.replace(/[^0-9]/g,'')">
                  </div>
                  <span style="float:left;display:black;margin-left: 15px;margin-right: 15px;font-size: 17px;line-height:35px;margin-bottom: 10px; ">~</span>
                  <div class="inputboder">
                  <input id="numberid2" class="rightinputstyle" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')">
                  </div>
                  <span style="float:left;display:black;margin-left: 15px;margin-right: 15px;font-size: 17px;line-height:35px;margin-bottom: 10px; ">户</span>
              </div> -->
              <div class="contentstyle">兴趣点类型选择:</div>   
              <div id="includeinterest"  class="scoll" style="width:100%;height:130px;float: left"></div>
              <div class="contentstyle">条件时间维度:</div>   
                 <div class="typechoice">
                      <div class="datetypestyle monthtype" data-type="D"  data-checked="false" data-checktype="one" onclick="IcontRadio1(this)"><i class="iconfont" style="margin-right:8px;font-size:16px;float:left;color:#000FFF">&#xe630;</i>日</div>
                      <div class="datetypestyle monthtype" data-type="M"  data-checked="false" data-checktype="one" onclick="IcontRadio1(this)"><i class="iconfont" style="margin-right:8px;font-size:16px;float:left;color:#000FFF">&#xe646;</i>月</div>
                 </div>
              <div class="contentstyle" style="margin-top: 15px;">筛选条件配置:<span id="addbutton" style="cursor:pointer;float:right;color:#50d1f2;font-size:16px;"><i class="iconfont" style="margin-right:4px;font-size:17px;float:left">&#xe776;</i>添加</span></div>  
              <div id="eachindexdiv"  class="scoll"  style="width:100%;height:300px;float: left"></div>
              <div class="contentstyle" style="margin-top: 8px;">
                   <div id="previewid" class="allbutton" style="margin-left: 0px;">预览</div>
                   <div id="saveid" class="allbutton">保存</div>
                   <div id="cancelid" class="allbutton">取消</div>
              </div>  
             </div>
          </div>
          <div id="rightpart2" class="rightpart" >
             <div class="rightcontent">
              <div class="contentstyle">兴趣点切换:</div>
              <div id="includeinterest1"  class="scoll" style="width:100%;height:130px;float: left"></div>
              <div class="contentstyle">已选兴趣点:</div>   
              <div id="includeinterest2"  class="scoll" style="width:100%;height:360px;float: left">
              </div>
              <div class="contentstyle" style="margin-top: 10px;">
                   <div id="saveid1" class="allbutton">保存</div>
                   <div id="cancelid1" class="allbutton">取消</div>
              </div>  
             </div>
         </div>
    </div>     
    <div id="pop_divIndexPool" class="indexpooldiv draggable">
		<div class="title">指标选择<i class="iconfont" onclick="closeIndexPool()" title="关闭">&#xe63f;</i></div>
		<iframe id="ifrIndexPool" class="ifr" src="" frameborder="no"></iframe>
	</div>
   <div id="divMapSearch" class="mapSearch" style="display:none">
		<div class="inputdiv">
			<input id="iptMapSearch" type="text"  defaulttxt="请输入兴趣点关键字"/>
			<i class="iconfont" onclick="clearinput()" title="取消">&#xe640;</i>
		</div>
		<div class="bntdiv"><i class="iconfont" onclick="queryForPoint()">&#xe606;</i></div>
  </div>
  <div id="divMapSearchResult" class="mapsearchresult"></div>
     
  </body>
  <script type="text/javascript">
   var lvlId="<%=request.getAttribute("userRegionlvl")%>";
   var userId="<%=request.getAttribute("userId")%>";
   var dishiData=<%=request.getAttribute("dishijson")%>;//地市的数据
   var xianquData=<%=request.getAttribute("xianqujson")%>;//县区的数据
   var allxinquData=<%=request.getAttribute("allxinqujson")%>;
   var map;//地图全局变量
   var interestarry=[];//兴趣点的多选
   var dateType="";//日期类型
   var conditionid=[];//筛选条件配置
   var indexcondition=[];//添加指标的判断是否存在
   var dateType="D" ;//时间维度类型
   var dishiregionId="";//地市的Id
   var xianquregionId="";//县区的Id
   var cellgraphicsLayer;//轮廓图层
   var textgraphicsLayer;//文字图层
   var graphiclistdata=[];//图形集合
   var conditionid1=[];//保存时的数组
   var indexcondition1=[];//保存添加指标的判断是否存在
   var interestarry1=[];//保存时兴趣点的多选
   var cur_mapRingJson=[]; //当前的可视范围
   var interestarrylength=[];//判断是否是地图的调用
   var conditionidlist=[];//判断是否是地图的调用
   var secenname="";//场景的名字
   var phone="";//手机号
   var scencdesc="";//场景的描述
   var interestpoin="pointchoice";//兴趣点类型
   var pointertype="";//选中的兴趣点
   var pointerarry=[];//关注点数组保存数据
   var pointereach=[];//关注点数组判断数据
   var interestnumber1="";//兴趣点移动用户数
   var interestnumber2="";//兴趣点移动用户数
   var creatoreditor="<%=request.getAttribute("creatoredit")%>";//创建还是编辑
   var editortype="<%=request.getAttribute("editortype")%>";//场景配置模式标识符
   var senceid="<%=request.getAttribute("senceid")%>";//场景Id
   var cur_pointtype="";//兴趣点类型
   	/* 模块化加载地图插件 */
	dojo.require("esri.map");
	dojo.require("dojo._base.event");
	dojo.require("esri.geometry.Point");
    dojo.require("esri.geometry.Polyline");
    dojo.require("esri.geometry.Polygon");
    dojo.require("esri.geometry.Extent");
    dojo.require("esri.geometry.Circle");
    dojo.require("esri.graphic");
    dojo.require("esri.symbols.SimpleMarkerSymbol");
    dojo.require("esri.symbols.SimpleLineSymbol");
    dojo.require("esri.symbols.SimpleFillSymbol");
    dojo.require("esri.SpatialReference");
    dojo.require("esri.layers.GraphicsLayer");
    inputOptimize($("#iptMapSearch"));
   $(function(){
     adjust();
     interestindex();
     /*加载省市 县区 下拉框*/
	 initProvinceCityOption();
	//指标的选择框支持拖拽
	$("#pop_divIndexPool").draggable({
	   cursor:"pointer"
     });
   //滚动条
    $(".scoll").niceScroll({
	 cursorcolor : "#00AFFF",
	 cursorborderradius: "0px",
	 cursorwidth: "3px",
	 cursorborder: "0px solid #fff",
	 autohidemode:true
	 });
     if(creatoreditor=="edit") {
      $("#typehidden").hide();
	 }
	 if(interestpoin=="intereschoce"||editortype=="check"){
	     $("#divMapSearch").show();
	 }
     if(lvlId=="1"){
        switchRegionMapCenterAt11("dishi","石家庄","");
     }else if(lvlId=="2"){
        switchRegionMapCenterAt11("dishi",dishiData[0].regionName,"");
     }else if(lvlId=="3"){
        var xianqudishi=regionNameTransform(dishiData[0].regionName);
        switchRegionMapCenterAt11("dishi",dishiData[0].regionName,allxinquData[xianqudishi][0].regionName);
     }
    })
    
 	/*---------------------------辅助方法---------------------------------------*/
	/*加载 省市 县区 下拉框*/
	function initProvinceCityOption(){
		if(lvlId != "null"){
			if(lvlId=="1"){
				//加载地市下拉
				initDishiSelect(regionDishiJsonTransform(dishiData,false,true));
				//获取地市Key值
				var dishipid=regionNameTransform($("#dishiselect").getSeletedText());
				//加载县区下拉
				initXianquSelect(regionDishiJsonTransform(allxinquData[dishipid],true,false));
			}else if(lvlId=="2"){
				//加载地市下拉
				initDishiSelect(regionDishiJsonTransform(dishiData,false,false));
				//获取地市Key值
				var dishipid=regionNameTransform($("#dishiselect").getSeletedText());
				//加载县区下拉
				initXianquSelect(regionDishiJsonTransform(allxinquData[dishipid],true,false));
			}else if(lvlId=="3"){
				//加载地市下拉
				initDishiSelect(regionDishiJsonTransform(dishiData,false,false));
				//获取地市Key值
				var dishipid=regionNameTransform($("#dishiselect").getSeletedText());
				//加载县区下拉
				initXianquSelect(regionDishiJsonTransform(allxinquData[dishipid],false,false));
			}
		}
	}
	/* 加载地市 */
	function initDishiSelect(data,firstval){
		//加载地市地域选框
		$("#dishiselect").miapSelect({
			"data":data,
			"eachLiClick":function(i,t,v){
				var b=true;
				if(lvlId=="3"){
					b=false;
				}
				var cur_xinaqu=regionDishiJsonTransform(allxinquData[regionNameTransform(t)],b,false);
				$("#xianquselect").reloadListData(cur_xinaqu);
				//地图定位
				switchRegionMapCenterAt("dishi",t,$("#xianquselect").getSeletedText());
				$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+t);
			}
		});
		if(firstval!=undefined&&firstval!=null){
			$("#dishiselect").setSeletedWithIndex(firstval);
		}
	};
	
	/* 加载县区 */
	function initXianquSelect(data,firstval){
		//加载县区地域选框
		$("#xianquselect").miapSelect({
			"listwidth":100,
			"data":data,
			"eachLiClick":function(i,t,v){
			if(t=="全部"){
				t=$("#dishiselect").getSeletedText();
				switchRegionMapCenterAt("dishi",$("#dishiselect").getSeletedText(),t);
				}else{
			    switchRegionMapCenterAt("xianqu",$("#dishiselect").getSeletedText(),t);
				};
				$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+t);
			}
			
		});
		if(firstval!=undefined&&firstval!=null){
			$("#xianquselect").setSeletedWithIndex(firstval);
		}
	};
/*---------------------------辅助方法结束---------------------------------------*/
   /* 点击开始配置条件的时候 */
   $("#startscreen").click(function(){
      if(interestpoin=="pointchoice"){
        $("#rightpart1").show();
        $("#rightpart2").hide();
        $("#fitlepoint1").show();//信息的输入框
        $("#fitlepoint2").hide();//信息的数据框
        $("#divMapSearch").hide();
      }else{
        $("#rightpart1").hide();
        $("#rightpart2").show();
        $("#fitlepoint2").show();
        $("#fitlepoint1").hide();
        $("#divMapSearch").show();
      }
      CurPointGraphicsLayer.clear();
      if((interestpoin=="pointchoice"&&conditionidlist.length<=0)||(pointertype==""&&interestpoin=="intereschoce")){
          allmapdata();
      }else if(interestpoin=="pointchoice"&&conditionidlist.length>0){
          mapdata("tranfermap");
      }else if(pointertype!=""&&interestpoin=="intereschoce"){
         pointmapdata();
      }
   });
  /***************   地图加载 和方法              *****************/ 
    function InitMap(defaultpoint){
      map = new esri.Map("innermap",{"center":defaultpoint,"zoom":11,slider : false,logo : false});
      //加载比例尺
	  require(["esri/dijit/Scalebar"],function() {
			var scalebar = new esri.dijit.Scalebar({ map: map, scalebarStyle:"line",scalebarUnit: "metric"},dojo.byId("scalebarstyle"));
       });
      var MapServiceLayer = new esri.layers.ArcGISTiledMapServiceLayer("http://www.hebmcbass.com/arcgis/rest/services/HBMAP_NEW2016/MapServer");
      MapServiceLayer.id="MapServiceLayer";
	  map.addLayer(MapServiceLayer);
	  
	  
	  /* 兴趣点图层 */
		cellgraphicsLayer = new esri.layers.GraphicsLayer();
		map.addLayer(cellgraphicsLayer);
		map.reorderLayer(cellgraphicsLayer,15);
		
	 /* 当前兴趣点标识图层 */
		CurPointGraphicsLayer = new esri.layers.GraphicsLayer();
		map.addLayer(CurPointGraphicsLayer);
		map.reorderLayer(CurPointGraphicsLayer,2);
	  
	  
	  //绑定事件
	 dojo.connect(map, "onExtentChange",function(evt){
		var lvl = map.getLevel();
		if(lvl>=11){
		    if(creatoreditor=="creat"){
		      if((interestpoin=="pointchoice"&&conditionidlist.length<=0)||(pointertype==""&&interestpoin=="intereschoce")){
	               allmapdata();
		      }else if(interestpoin=="pointchoice"){
		          mapdata("tranfermap");
		      }else if(interestpoin=="intereschoce"){
		          pointmapdata();
		      }
		    }else if(interestpoin=="pointchoice"){
			   mapdata("tranfermap");
		    }else if(interestpoin=="intereschoce"){
		        pointmapdata();
		    }
		}
	 });
	 dojo.connect(map, "onZoomEnd", function(){
			var lvl = map.getLevel();
			if(lvl>=11){
			    CurPointGraphicsLayer.show();
				cellgraphicsLayer.show();
			}else{
			    CurPointGraphicsLayer.hide();
				cellgraphicsLayer.hide();
			}
		});
	 /* ------------------ */
	dojo.connect(cellgraphicsLayer,"onMouseOver",showPointInfo);
	   /*  dojo.connect(cellgraphicsLayer,"onMouseOut",hidePointInfo()); */ 
	   /* dojo.connect(PointGraphicsLayer,"onClick",mapPointClickInitInfoData); */
      if(creatoreditor=="edit") {
       editdatasearch();
	   }
	 }	
    //地图上绘制区域
	 function drawMapAreaLayer(data) {
	    cellgraphicsLayer.clear();//调用之前先清空
		if(data.length != 0){
			for(var i=0;i<data.length;i++){
		      	var mygraphic="";
				var obj = data[i];
				var rings = obj.ringarry;
				var pointsymbol=""; 
				if(rings.length != 0){
				//循环遍历被关注的兴趣点然后赋予不同的颜色前提必须是勾选的行去点	
			    if(pointereach.length>0&&interestpoin=="intereschoce"){
				   for(var k=0;k<pointereach.length;k++){
				       if(obj.CELL_ID==pointereach[k]){
				           pointsymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, 
				           new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([255,255,255,0.8]), 1), new dojo.Color([255, 99, 71,0.7]));
				           break; 
				       }else{
				           pointsymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, 
				           new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([255,255,255,0.8]), 1), new dojo.Color([154, 205, 50,0.7]));
				       }
			     }
			   }else{
			         pointsymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, 
			          new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([255,255,255,0.8]), 1), new dojo.Color([154, 205, 50,0.7]));
			   }
		       var str="{\"rings\":[[";
		       for(var j=0;j<rings.length;j++){
			      str+="["+rings[j][0]+", "+rings[j][1]+"]";
			      if(j!=rings.length-1){
  		 			 str+=",";
  		 		  } 
		       }
	 		   str+="]],\"spatialreference\":{\"wkid\":4326}}";
	 		   var polygonjson=eval('('+str+')');
	 		   var graphicattr={"pointid":obj.CELL_ID,"pointname":obj.CELL_NAME,"pointlong":obj.CELL_LONG,"pointlat":obj.CELL_LAT,"celltype":obj.CELL_TYPE_NAME,"cellname":obj.CELL_TYPE};
			   mygraphic=new esri.Graphic(new esri.geometry.Polygon(polygonjson),pointsymbol,graphicattr);
			   cellgraphicsLayer.add(mygraphic);
		    }
		 }
	 }
	}
     //获取边界值并染色
  	   function getringsanddraw(){
  		  $.each(graphiclistdata,function(i,mygraphic){
  			 try {
		  		cellgraphicsLayer.add(mygraphic);
		  		var graphicattr = mygraphic.attributes;
			  	var mm = mygraphic._extent;
				textgraphicsLayer.add(drawtext(mm.getCenter(),graphicattr.pointname,graphicattr));
			  } catch (e) {
				 console.log(mygraphic.attributes.id);
			   }
  		    });
  	       }
  	  //绘制区域的名称
  	      function drawtext(mapPoint, text ,attr) {
			var font = new esri.symbol.Font("9pt", esri.symbol.Font.STYLE_NORMAL,esri.symbol.Font.VARIANT_NORMAL,esri.symbol.Font.WEIGHT_BOLD,"微软雅黑");
			var textSymbol = new esri.symbol.TextSymbol(text, font, new dojo.Color("black"));
			textSymbol.setAlign(esri.symbol.TextSymbol.ALIGN_MIDDLE);
			var graphictext = new esri.Graphic();
			graphictext.setGeometry(new esri.geometry.Point(mapPoint));
			graphictext.setSymbol(textSymbol);
			graphictext.setAttributes(attr);
			return graphictext;
	     }
	  /* 显示兴趣点信息框 */
		function showPointInfo(evt){
			var infoWindow = map.infoWindow;
			var graphicAttr = evt.graphic.attributes;
			var pointname = graphicAttr.pointname;
			evt.graphic.id=graphicAttr.pointid;
			var htmlcode="";
			htmlcode = "<div class='pointinfowindow'><div class='eachindexinfo'><span class='text'>"+pointname+"</span><span onclick='addconcern(this)' data-lat='"+graphicAttr.pointlat+"' data-long='"+graphicAttr.pointlong+"' data-id='"+graphicAttr.pointid+"' data-name='"+graphicAttr.pointname+"' type-id='"+graphicAttr.cellname+"'  type-name='"+graphicAttr.celltype+"' id='addconcern' class='dosome' style='float:right'>添加关注</span></div></div>";
			 if(pointereach.length>0){
			   for(var i=0;i<pointereach.length;i++){
			       if(graphicAttr.pointid==pointereach[i]){
			         htmlcode = "<div class='pointinfowindow'><div class='eachindexinfo'><span class='text'>"+pointname+"</span><span onclick='cancelconcern(this)' data-lat='"+graphicAttr.pointlat+"' data-long='"+graphicAttr.pointlong+"' data-id='"+graphicAttr.pointid+"' data-name='"+graphicAttr.pointname+"' type-id='"+graphicAttr.cellname+"' type-name='"+graphicAttr.celltype+"' id='cancelconcern' class='dosome' style='float:right'>取消关注</span></div></div>";
			       		break;
			       }
			   }
			    infoWindow.setContent(htmlcode); 
			 }else{
			    htmlcode = "<div class='pointinfowindow'><div class='eachindexinfo'><span class='text'>"+pointname+"</span><span onclick='addconcern(this)' data-lat='"+graphicAttr.pointlat+"' data-long='"+graphicAttr.pointlong+"' data-id='"+graphicAttr.pointid+"' data-name='"+graphicAttr.pointname+"' type-id='"+graphicAttr.cellname+"' type-name='"+graphicAttr.celltype+"' id='addconcern' class='dosome' style='float:right;display:none'>添加关注</span></div></div>";
			    infoWindow.setContent(htmlcode);
			    if(interestpoin=="intereschoce"){
		         $("#addconcern").show();
		      }
			 }
			infoWindow.setTitle("基本信息");			
			infoWindow.resize(220,100);
			infoWindow.show(evt.screenPoint, map.getInfoWindowAnchor(evt.screenPoint));
		}
	/* 隐藏兴趣点信息框 */
		function hidePointInfo(){
			var infoWindow = map.infoWindow;
			infoWindow.hide();
		}
	
		/* 地图定位置选中的兴趣点 */
	  function mapCenterToThisPoint(long,lat){
		CurPointGraphicsLayer.clear();
		var pointSymbolRed = new esri.symbol.PictureMarkerSymbol("image/location.png",40,40);
		var point = esri.geometry.Point(new esri.geometry.Point({"x": long,"y": lat,"spatialReference": {"wkid": 4326}}));
		var attrdata = {"pointlong":long,"pointlat":lat};
		var cur_graphic = new esri.Graphic(point,pointSymbolRed,attrdata);
		CurPointGraphicsLayer.add(cur_graphic);
		map.centerAt(point);
	}	
		
   /* 地图定位 */
	  function mapCenterAtPoint(long,lat){
		var centerPoint = new esri.geometry.Point({"x": long,"y": lat,"spatialReference": {"wkid": 4326}});
		map.setZoom(11);
		map.centerAt(centerPoint);
	 }
	 //地图定位
	function Maptopoint(result){
		var features = result.features;
		if(features.length>0){
			var gra=features[0];
			defaultpoint = [gra.geometry.x, gra.geometry.y];
		    map.centerAt(new esri.geometry.Point(gra.geometry.x, gra.geometry.y));
		}
		else{
			alert("定位失败!");
		}
	}
	/* 切换地市后地图定位 */
	function switchRegionMapCenterAt(lvl,dishiname,xianquname){
		var queryTask;
		var query = new esri.tasks.Query();
		query.returnGeometry = true;
		if(lvl=="dishi"){
			queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/13");
			query.where = "data='" + dishiname+"市"+ "'";
			queryTask.execute(query, Maptopoint);
		}else if(lvl=="xianqu"){
			queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/10");
			query.where = "CITY='"+dishiname+"市"+"' and data like '%"+xianquname+"%'";
			queryTask.execute(query, function(result){
				var features = result.features;
				if(features.length>0){
					var gra=features[0];
					map.centerAt(new esri.geometry.Point(gra.geometry.x, gra.geometry.y));
				}
				else{
					var point = getUnKonwnCenterPoint(dishiname,xianquname);
					map.setZoom(11);
					map.centerAt(point);
				}
			});
		}
	}		  		
  
	/* 初加载地图定位 */
	function switchRegionMapCenterAt11(lvl,dishiname,xianquname){
		var queryTask;
		var query = new esri.tasks.Query();
		query.returnGeometry = true;
		if(lvl=="dishi"){
			queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/13");
			query.where = "data='" + dishiname+"市"+ "'";
			queryTask.execute(query, Maptopoint1);
		}else if(lvl=="xianqu"){
			queryTask = new esri.tasks.QueryTask("http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEWZL/MapServer/10");
			query.where = "CITY='"+dishiname+"市"+"' and data like '%"+xianquname+"%'";
			queryTask.execute(query, function(result){
				var features = result.features;
				if(features.length>0){
					var gra=features[0];
					map.setZoom(11);
					map.centerAt(new esri.geometry.Point(gra.geometry.x, gra.geometry.y));
				}
				else{
					var point = getUnKonwnCenterPoint(dishiname,xianquname);
					map.centerAt(point);
				}
			});
		}
	}
	 //地图定位
	function Maptopoint1(result){
		var features = result.features;
		if(features.length>0){
			var gra=features[0];
			defaultpoint = [gra.geometry.x, gra.geometry.y];
			InitMap(defaultpoint);
		    /* map.centerAt(new esri.geometry.Point(gra.geometry.x, gra.geometry.y)); */
		}
		else{
			alert("定位失败!");
		}
	}
			  		
  
  /***************   地图加载完毕               *****************/ 
  
  /*******************初加载加载全部的地图数据****************************/
  function allmapdata(){ 
         cellgraphicsLayer.clear();//调用之前先清空
         var dishiregionId=$("#dishiselect").getSeletedValue();
		 var xianquregionId=$("#xianquselect").getSeletedValue();
         var extent = map.extent;
         var mapRingJson = {"xMax":extent.xmax,"xMin":extent.xmin,"yMax":extent.ymax,"yMin":extent.ymin};
         var data={dishiregionId:dishiregionId,xianquregionId:xianquregionId,dateType:dateType,extentjson:JSON.stringify(mapRingJson),pointertype:"allpoint"};
         cellgraphicsLayer.clear();//调用之前先清空
           $.ajax({
             url:"pointmapdata.do",
             type:"post",
             dataType:"json",
             data:data,
             success:function(data){
               var data=eval(data);
               if(data.length<=0){
                 alert("此区域暂无数据!!!");
               }else{
                 drawMapAreaLayer(data);//地图上绘制区域
               }
             }
           })
  }
  
  
  /***************   兴趣点条件筛选的逻辑部分  多选           *****************/ 
     //兴趣点的类型的选择指标的查询
      function interestindex(){
       $.ajax({
           url:"interestindex.do",
           datatType:"json",
           data:"",
           type:"post",
           success:function(data){
                 var data=eval(data);
                 interestindexdata(data);//兴趣点的筛选条件
                 piontexchage(data);//兴趣点的勾选
           }
       })
      }
      function interestindexdata(data){
          var html="";
          for(var i=0;i<data.length;i++){
            html+="<div class='typechoice interesttype' data-type='"+data[i].CELL_TYPE+"'  data-checked='false' data-checktype='one' onclick='IcontCheck(this);'><i class='iconfont' style='cursor: pointer;color:#00afff;margin-right: 5px'>&#xe658;</i>"+data[i].CELL_TYPE_NAME+"</div>";
            if(i==0){
              cur_pointtype=data[0].CELL_TYPE;
            }
          }
          $("#includeinterest").html();
          $("#includeinterest").html(html);
      } 
      //遍历多选的 预览和保存按钮调用
      function mulitiselect(){
	      $(".interesttype[data-checked=true]").each(function(i,e){
	            var eachinterestid= $(e).attr("data-type");
	            interestarry.push(eachinterestid);
	      });
      }
  /***************   兴趣点条件筛选的逻辑部分完毕                *****************/ 
     //兴趣点的类型的判断
      $(".scenetype").click(function(){
          interestpoin=$(this).attr("data-type");
       })
  
     //日期类型
     $(".monthtype").click(function(){
         dateType=$(this).attr("data-type");
         $("#eachindexdiv").empty();//日月切换的时候筛选条件配置清空
         conditionid=[];//筛选条件的数组清空
         indexcondition=[];//指标的判断条件数组清空
     });
   /*************筛选条件配置**********************/
     // 点击添加
      $("#addbutton").click(function(){
           showIndexPool(dateType);//指标iframe加载
      });
    //点击指标后面的添加
    function addeachindex(indexid,indexname,indexunit){
        if( $.inArray(indexid,indexcondition)==-1){
           indexcondition.push(indexid);//指标不存在的话放到在数组中
	       $("#eachindexdiv").append(
	           "<div id='"+indexid+"' class='indexideach' name-value='"+indexname+"'  style='width:100%;height:58px;'>"+ 
	           " <div  class='typechoice'>"+indexname+"</div>"+
	                " <div class='typechoice'>"+
                         " <div class='conditioinput'>"+
		                 " <input id='"+indexid+"1'  class='conditioinputstyle' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\">"+
		                 " </div>"+
		                 " <span style='float:left;display:black;margin-left: 5px;margin-right: 5px;font-size: 17px;line-height:20px;margin-bottom: 10px; '>~</span>"+
		                 " <div class='conditioinput'>"+
		                 " <input id='"+indexid+"2'  class='conditioinputstyle' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\">"+
		                 " </div>"+
		                 " <span style='font-size:15px;float:left;display:black;margin-left: 5px;line-height:20px; '>"+indexunit+"</span>"+
		                 " <span onclick='removeindexdiv(\""+indexid+"\");' style='cursor:pointer;text-decoration:underline;color:#50d1f2;font-size:15px;float:right;display:black;margin-left: 5px;line-height:20px; '>清除</span>"+
			   " </div>"+
			   "</div>"
	       )
       }else{
             alert("该指标已存在!!!");
       }
    }
  //遍历筛选条件配置里所有的input 预览和保存按钮调用和查询地图数据之前先判断数据的正确性  
    function coditinmultiselect(type){
       mulitiselect();//多选的遍历 
      if(interestarry.length<=0){
        alert("请选择兴趣点的类型");//判断兴趣点的类型是否选择了
      }else if($("div[class='indexideach']").length>0){//当选择了筛选条件配置之后
        $(".indexideach").each(function(i,e){
          var coditionid=$(e).attr("id");//获取id值
          var coditionname=$(e).attr("name-value");
          addindexarry(coditionid,coditionname,type);
        });
        if(type!="save"){
          mapdata("preview");//条件判断完以后可以执行查询地图数据的方法
        }else if(creatoreditor=="edit"&&editortype=="filter"){
          editpointdelete(secenname,phone,scencdesc);//是编辑模式并且编辑模式为兴趣点勾选的
        }else{
          savedata(secenname,phone,scencdesc);//保存数据
        }
     }else if($("div[class='indexideach']").length<=0){
        alert("请配置条件!!!");//判断兴趣点的类型是否选择了
     }else if(type=="preview"){
         mapdata("preview");//条件判断完以后可以执行查询地图数据的方法
     }else if(type=="save"){
          //先判断是创建还是编辑再保存
         if(creatoreditor=="edit"&&editortype=="filter"){
         editpointdelete(secenname,phone,scencdesc);//是编辑模式并且编辑模式为兴趣点勾选的
         }else{
         savedata(secenname,phone,scencdesc);//保存数据
         }
     }
    }
   //添加之后把指标的id放到数组中
   function addindexarry(indexid,coditionname,type){
       var indexid=indexid;
       var inputindexid1=$("#"+indexid+"1").val();
       var inputindexid2=$("#"+indexid+"2").val();
       if(interestarry.length<=0){
          alert("请选择兴趣点类型");
       }else if(parseFloat(inputindexid1)>parseFloat(inputindexid2)||inputindexid1==""||inputindexid2==""){
         alert("指标为\""+coditionname+"\"的指标值有误!");
       }else {
        var stringdata={
         "id":indexid,
         "input1":inputindexid1,
         "input2":inputindexid2
        };
        conditionid.push(stringdata) ;
      }
    }
  //点击指标后面的删除
   function removeindexdiv(indexid){
     $("#"+indexid).remove();
     var index=$.inArray(indexid,indexcondition);
      indexcondition.splice(index,1);//删除该指标在数组中的值
   }
  /***************   兴趣点条件筛选的三个按钮事件              *****************/ 
    // 取消按钮
    $("#cancelid").click(function(){
	      if($("#secenname").val()!=""&&$("#creatphone").val()!=""){//判断是否编辑过
		      if(confirm("确定要退出编辑吗？")){
	             $(".rightpart").hide();
		         //执行清除方法
		         clearalldata();
		      }
	      }else{
	             $(".rightpart").hide();
		 }
    })
   //预览按钮
   $("#previewid").click(function(){
          coditinmultiselect("preview");
          conditionid=[];//点击之后清空数组
          interestarry=[];
   })
   //保存按钮
   $("#saveid").click(function(){
          secenname=$.trim($("#secenname").val());
          phone=$("#creatphone").val();
          scencdesc=$.trim($("#descritesecen").text());
         if(secenname==""){
           alert("请输入场景名称!!!");
          } else if(!(/^1[345678]\d{9}$/.test(phone))){
           alert("手机号有误,请重填");
          }else{
	        coditinmultiselect("save");
        }
   });
   //需要保存的数据
   function savedata(secenname,phone,scencdesc){
        var interestnumber1= $("#numberid1").val();//兴趣点移动用户数
        var interestnumber2= $("#numberid2").val();//兴趣点移动用户数
        var data={userId:userId,phone:phone,scencname:secenname,scencdesc:scencdesc,dataType:dateType,celltype:JSON.stringify(interestarry),cellattru:JSON.stringify(conditionid),interestnumber1:interestnumber1,interestnumber2:interestnumber2} ;
      /*   if(interestnumber1==""||interestnumber2==""||parseFloat(interestnumber1)>parseFloat(interestnumber2)){
           alert("请输入正确的兴趣点移动用户数!!!");
        }else{ */
	       $.ajax({
	           url:"savedata.do",
	           type:"post",
	           dataType:"json",
	           data:data,
	           success:function(data){
	              if(data=="2"){
	               alert("保存成功");
	               clearalldata();
	              }else{
	               alert("保存失败");
	              }
	           }
	       })
        
         conditionid=[];//点击之后清空数组
	     interestarry=[];
   }   
   
  
  /***************   兴趣点条件筛选的三个按钮事件 完毕               *****************/ 
  
  
  /***************   地图数据的查询            *****************/ 
      function mapdata(type){
        cellgraphicsLayer.clear();
        var dishiregionId=$("#dishiselect").getSeletedValue();
		var xianquregionId=$("#xianquselect").getSeletedValue();
         if(type=="preview"){
	          interestarrylength=interestarry;
	          conditionidlist=conditionid;
          }
         var extent = map.extent;
         var mapRingJson = {"xMax":extent.xmax,"xMin":extent.xmin,"yMax":extent.ymax,"yMin":extent.ymin};
		 cur_mapRingJson = mapRingJson;  
         var data={dishiregionId:dishiregionId,xianquregionId:xianquregionId,interestnumber1:interestnumber1,interestnumber2:interestnumber2,interestarry:JSON.stringify(interestarrylength),conditionid:JSON.stringify(conditionidlist),dateType:dateType,extentjson:JSON.stringify(cur_mapRingJson)};
         if(interestarrylength.length>0){
	         $.ajax({
	          url:"mapdatajson.do",
	          type:"post",
	          dataType:"json",
	          data:data,
	          success:function(data){
	              var datalist=eval(data);
	              if(datalist.length<=0){
	                alert("此区域暂无数据!!!");
	              }else{
	                //加载可视范围内的数据
		               drawMapAreaLayer(datalist);//地图上绘制区域
	                }
	               } 
	           }) 
         }
       }

 /***************   兴趣点勾选的判断逻辑的            *****************/   
    //先查询兴趣点
      function piontexchage(data){
            html="";
            if(data.length>0){
            for (var i=0;i<data.length;i++){
               html+="<div class='piontchagestyle pointexchage' data-type='"+data[i].CELL_TYPE+"'  data-checked='false' data-checktype='one' onclick='IcontRadio1(this)'><i class='iconfont' style='margin-right:8px;font-size:16px;float:left;color:#000FFF'>&#xe646;</i>"+data[i].CELL_TYPE_NAME+"</div>";
            }
            $("#includeinterest1").html(html);
            $(".pointexchage").click(function(){
                 pointertype=$(this).attr("data-type");
                 cellgraphicsLayer.clear();//调用之前先清空
                 testdata();//兴趣点切换调用地图数据
                 cur_pointtype=pointertype;//搜索框使用的参数
                 CurPointGraphicsLayer.clear();//指标切换清空定点图层
            })
            }
      }
    //查询地图数据之前先判断数据的正确性  
      function testdata(){
          if(pointertype==""){
           alert("请选择兴趣点!!!");
         }else{
           cellgraphicsLayer.clear();//调用之前先清空
           pointmapdata();//兴趣点切换调用地图数据
         }
      }
   //查询地图的数据
      function  pointmapdata(){
         if(pointertype!=""){
             cellgraphicsLayer.clear();//调用之前先清空
             /* CurPointGraphicsLayer.clear(); */
         }
         var dishiregionId=$("#dishiselect").getSeletedValue();
		 var xianquregionId=$("#xianquselect").getSeletedValue();
         var extent = map.extent;
         var mapRingJson = {"xMax":extent.xmax,"xMin":extent.xmin,"yMax":extent.ymax,"yMin":extent.ymin};
         var data={dishiregionId:dishiregionId,xianquregionId:xianquregionId,dateType:dateType,extentjson:JSON.stringify(mapRingJson),pointertype:pointertype};
         if(pointertype!=""){
           cellgraphicsLayer.clear();//调用之前先清空
           $.ajax({
             url:"pointmapdata.do",
             type:"post",
             dataType:"json",
             data:data,
             success:function(data){
               var data=eval(data);
               if(data.length<=0){
                 alert("此区域暂无数据!!!");
               }else{
                 drawMapAreaLayer(data);//地图上绘制区域
               }
             }
           })
         }
      }
    //兴趣点添加关注功能
    function addconcern(e){
       hidePointInfo();//点击关注之后信息框消失
       var pointid=$(e).attr("data-id");
       var pointname=$(e).attr("data-name");
       var typename=$(e).attr("type-name");
       var datalat=$(e).attr("data-lat");
       var datalong=$(e).attr("data-long");
       var typeid=$(e).attr("type-id");
       var pointdata={
         "pointid":pointid,
         "pointname":pointname,
         "typeid":typeid
       };
       var graphicArray = cellgraphicsLayer.graphics;
        if(graphicArray.length>0){
           for(var i=0;i<graphicArray.length;i++){
               if(graphicArray[i].id==pointid){
                  var cur_graphic = graphicArray[i];
                  var pointsymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, 
			           new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([255,255,255,0.8]), 1), new dojo.Color([255, 99, 71,0.7]));
			      cur_graphic.setSymbol(pointsymbol);
				  cellgraphicsLayer.remove(cur_graphic);
				  cellgraphicsLayer.add(cur_graphic);
				  break;     
               }
           }
        }
       pointereach.push(pointid);//选中兴趣点放到数组中
       pointerarry.push(pointdata);//兴趣点的数据放到数据中
       $("#includeinterest2").append(
                       "<div id='"+pointid+"' onclick='mapCenterToThisPoint(\""+datalong+"\",\""+datalat+"\")' style='cursor:pointer;margin-top:5px;width:100%;height:25px;float:left'>"+
                       "<span style='font-size: 15px;float:left'>"+pointname+"</span>"+
                       "<span style='font-size: 15px;float:right'>"+typename+"</span>"+
                   "</div>")
    }  
    
    //取消关注
    function cancelconcern(e){
       hidePointInfo();//点击取消关注之后信息框消失
       var pointid=$(e).attr("data-id");
       var pointname=$(e).attr("data-name");
       var typeid=$(e).attr("type-id");
       /* var typename=$(e).attr("type-name");
       var datalat=$(e).attr("data-lat");
       var datalong=$(e).attr("data-long"); */
       var pointdata={
         "pointid":pointid,
         "pointname":pointname,
         "typeid":typeid
       };
       var graphicArray = cellgraphicsLayer.graphics;
        if(graphicArray.length>0){
           for(var i=0;i<graphicArray.length;i++){
               if(graphicArray[i].id==pointid){
                  var cur_graphic = graphicArray[i];
                  var pointsymbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, 
			           new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([255,255,255,0.8]), 1), new dojo.Color([154, 205, 50,0.7]));
			      cur_graphic.setSymbol(pointsymbol);
				  cellgraphicsLayer.remove(cur_graphic);
				  cellgraphicsLayer.add(cur_graphic);
				  break;     
               }
           }
        }
       var index=$.inArray(pointid,pointereach);
       pointereach.splice(index,1);//删除该指标在数组中的值
       var indexarry=$.inArray(pointdata,pointerarry);
       pointerarry.splice(indexarry,1);//删除该指标信息在数组中的数据
       $("#"+pointid).remove();    
      }
  /**********兴趣点勾选部分两个按钮的 保存取消***********/
    //点击保存
      $("#saveid1").click(function(){
          pleasechosepoint();
      })
    //保存数据检查数据的正确性之前
      function pleasechosepoint(){
         var pointsencename=$("#secenname2").val();
         var pointphone=$("#creatphone2").val();
         var pointscencdesc=$.trim($("#descritesecen2").text());
         if(pointsencename==""){
           alert("场景的名称不能为空!!!");
         }else if(!(/^1[345678]\d{9}$/.test(pointphone))){
           alert("手机号有误!!!");
         }else if(pointertype==""&&pointerarry.length<=0){
           alert("请选择兴趣点!!!");
         }else if(pointerarry.length<=0){
           alert("请关注兴趣点!!!");
         }else{
           if(creatoreditor=="edit"&&editortype=="check"){//编辑模式并且是编辑模式为勾选点
           editpointdelete(pointsencename,pointphone,pointscencdesc);//删除成功以后再进行保存 
           }else{
           savepointdata(pointsencename,pointphone,pointscencdesc);
           }
         }
      }
    
    //保存方法
    function savepointdata(pointsencename,pointphone,pointscencdesc){
           data={useId:userId,pointsencename:pointsencename,pointphone:pointphone,pointscencdesc:pointscencdesc,pointertype:pointertype,pointerarry:JSON.stringify(pointerarry)};
           $.ajax({
             url:"savepointdata.do",
             type:"post",
             dataType:"json",
             data:data,
             success:function(data){
                if(data=="2"){
                   alert("添加成功!!!");
                   clearalldata();
                }else{
                   alert("添加失败!!!");
                }
             }
           })
    }
    $("#cancelid1").click(function(){
      cancelcheckpoint();
    });
    
    //点击取消按钮
    function cancelcheckpoint(){
          if(pointereach.length>0){//if确定是否编辑过
              if(confirm("确定要退出编辑吗？")){
		         /*  var ww=$(document).width();
			      $(".middlepart").width(ww-280); */
			      $(".rightpart").hide();
                  //执行清除方法
                  clearalldata();
              }
          }else{
                  /* var ww=$(document).width();
			      $(".middlepart").width(ww-280); */
			      $(".rightpart").hide();
          }
    }
    
    
 /********************************************编辑部分*****************************************/  
     //传过来一个标识判断是兴趣点的筛选还是勾选
     function editdatasearch(){
         $.ajax({
            url:"editdatasearch.do",
            type:"post",
            dataType:"json",
            data:{senceid:senceid,editortype:editortype},
            success:function(data){
                var data=eval(data);
                if(data.length>0){
                    if(editortype=="filter"){
                     /*   var ww=$(document).width();
                       $(".middlepart").width(ww-560); */
                       $("#rightpart1").show();//编辑之前先把地图范围改变
                       interestpoin=="pointchoice";
                       filterpiontdata(data);
                       $("#divMapSearch").hide();
                    }else if(editortype=="check"){
                     /*   var ww=$(document).width();
                       $(".middlepart").width(ww-560); */
                       $("#rightpart2").show();//编辑之前先把地图范围改变
                       interestpoin="intereschoce";//场景配置模式为兴趣点勾选
                       $("#divMapSearch").show();
                       $("#fitlepoint2").show();
                       $("#fitlepoint1").hide();
                       $(".scenetype").each(function(i,e){
                          var checktype=$(e).attr("data-type");
                          if(checktype==interestpoin){
                            $(e).find("i").html("&#xe632;");
                          }else{
                            $(e).find("i").html("&#xe658;");
                          }
                       })
                       checkpointdata(data);
                    }
                
                }
            }
         });
     }
     
  /***********************兴趣点的筛选编辑**************************/    
     function filterpiontdata(data){
          $("#rightpart1").show();//编辑之前先把地图范围改变
          $("#secenname").val(data[0].SCENE_NAME);
          $("#creatphone").val(data[0].USER_PHONE);
          $("#descritesecen1").text(data[0].SCENE_DESC);
          if(data[0].DATE_TYPE=="D"){
            dateType="D";
            $(".monthtype[data-type='D']").find("i").html("&#xe630;");
            $(".monthtype[data-type='M']").find("i").html("&#xe646;");
          }else{
            dateType="M";
            $(".monthtype[data-type='M']").find("i").html("&#xe630;");
             $(".monthtype[data-type='D']").find("i").html("&#xe646;");
          }
          //给兴趣点类型选择赋值
          for(var i=0;i<data[0].CELL_TYPE.length;i++){
             var CELLTYPE=data[0].CELL_TYPE[i];
             $(".interesttype").each(function(i,e){
	         var eachinterestid= $(e).attr("data-type");
	         if(eachinterestid==CELLTYPE){
	            $(e).attr("data-checked",true);
	            $(e).find("i").html("&#xe632;") ;
	          }
	         /*   interestarry.push(eachinterestid); */
	        }); 
          }
          if(data.length>0){
             for(var i=0;i<data.length;i++){
               $("#eachindexdiv").append(
	           "<div id='"+data[i].MM_ID+"' class='indexideach' name-value='"+data[i].MM_NAME+"'  style='width:100%;height:58px;'>"+ 
	           " <div  class='typechoice'>"+data[i].MM_NAME+"</div>"+
	                " <div class='typechoice'>"+
                         " <div class='conditioinput'>"+
		                 " <input id='"+data[i].MM_ID+"1' value='"+data[i].MM_MIN+"' class='conditioinputstyle' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\">"+
		                 " </div>"+
		                 " <span style='float:left;display:black;margin-left: 5px;margin-right: 5px;font-size: 17px;line-height:20px;margin-bottom: 10px; '>~</span>"+
		                 " <div class='conditioinput'>"+
		                 " <input id='"+data[i].MM_ID+"2' value='"+data[i].MM_MAX+"' class='conditioinputstyle' onkeyup=\"this.value=this.value.replace(/[^\\d.]/g,'')\">"+
		                 " </div>"+
		                 " <span style='font-size:15px;float:left;display:black;margin-left: 5px;line-height:20px; '>"+data[i].MM_UNIT+"</span>"+
		                 " <span onclick='removeindexdiv(\""+data[i].MM_ID+"\");' style='cursor:pointer;text-decoration:underline;color:#50d1f2;font-size:15px;float:right;display:black;margin-left: 5px;line-height:20px; '>清除</span>"+
			   " </div>"+
			   "</div>"
	       )
             }
          
          }
        $("#previewid").click();//赋值好以后点击预览 
     }
 /*******************兴趣点的勾选********************/
      function checkpointdata(data){
         $("#secenname2").val(data[0].SCENE_NAME);
         $("#creatphone2").val(data[0].USER_PHONE);
         $("#descritesecen3").text(data[0].SCENE_DESC);
         if(data.length==1&&(data[0].RINGS==null||data[0].RINGS==undefined)){
              $(".pointexchage").each(function(i,e){
                 var datatype=$(e).attr("data-type");
                 if(data[0].CELL_TYPE==datatype){
                   $(e).find("i").html("&#xe630;");
                   pointertype=data[0].CELL_TYPE;
                   //调用地图数据的查询
                 }
              })
         }else{
            for(var i=0;i<data.length;i++){
              var obj=data[i];
              $("#includeinterest2").append(
                       "<div id='"+obj.CELL_ID+"' onclick='mapCenterToThisPoint(\""+obj.CELL_LONG+"\",\""+obj.CELL_LAT+"\")' style='cursor:pointer;margin-top:5px;width:100%;height:25px;float:left'>"+
                       "<span style='font-size: 15px;float:left'>"+obj.CELL_NAME+"</span>"+
                       "<span style='font-size: 15px;float:right'>"+obj.CELL_TYPE_NAME+"</span>"+
                   "</div>")
              pointereach.push(obj.CELL_ID);//选中的兴趣点放到数组判断
              //把关注的点放到数组中
               var pointdata={
                  "pointid":obj.CELL_ID,
                  "pointname":obj.CELL_NAME,
                  "typeid":obj.CELL_TYPE
                  };
              pointerarry.push(pointdata);//兴趣点的数据放到数据中    
            }
            mapCenterToThisPoint(data[0].CELL_LONG,data[0].CELL_LAT);//对第一个关注点定位
            drawMapAreaLayer(data);
         }
      }
   /*****************编辑部分保存之前先删除之前的数据再插入新的数据*************************/ 
         function editpointdelete(pointsencename,pointphone,pointscencdesc){
            $.ajax({
              url:"editpointdelete.do",
              type:"post",
              dataType:"json",
              data:{senceid:senceid},
              success:function(data){
                if(data=="0"||data=="2"){
                  if(editortype=="filter"){
                     savedata(pointsencename,pointphone,pointscencdesc);//筛选的保存
                  }else{
                     savepointdata(pointsencename,pointphone,pointscencdesc);//勾选的保存
                  }
                }
              }
            })
         }
   /*******清除方法*********/
     function clearalldata(){
       /*   //如果点击取消之后退出编辑界面改为创建界面
         creatoreditor="creat"; */
         if(interestpoin=="pointchoice"){
             $("#secenname").val("");
             $("#creatphone").val("");
             $("#descritesecen1").text("");
           /*   $("#numberid1").val("");//兴趣点移动用户数
             $("#numberid2").val(""); */
             $(".interesttype[data-checked=true]").each(function(i,e){//多选框清空
	            $(e).attr("data-checked",false);
	            $(e).find("i").html("&#xe658;");
	          });
	        dateType="D";
            $(".monthtype[data-type='D']").find("i").html("&#xe630;");
            $(".monthtype[data-type='M']").find("i").html("&#xe646;");
	        interestarry=[];
	        conditionid=[];
            $("#eachindexdiv").empty();
            mapdata("preview")
         }else{
            $("#secenname2").val("");
            $("#creatphone2").val("");
            $("#descritesecen3").text("");
            pointertype="";//兴趣点为空
            $(".pointexchage[data-checked='true']").find("i").html("&#xe646;");
            $("#includeinterest2").empty();
            pointereach=[];//选中兴趣点放到数组清空
            pointerarry=[];
         }
        if(creatoreditor=="edit"){
          window.close();
        } 
     }
   window.onresize=function(){
     var wh=$(document).height();
     var ww=$(document).width();
     $(".bottomiframe").height(wh-60);
     $("#ifrIndexPool").height($("#pop_divIndexPool").height()-$("#pop_divIndexPool .title").height());
   }
  /***********兴趣点的查询************/ 
    /* 查询兴趣点 */
	function queryForPoint(){
		var indexKey=$("#iptMapSearch").val();
		if(indexKey==""){
			return false;
		}
		if(indexKey == $("#iptMapSearch").attr("defaulttxt")){
			indexKey="";
			return false;
		}
		var top=$("#divMapSearch").offset().top+$("#divMapSearch").height()+3;
		var left=$("#divMapSearch").offset().left;
		$("#divMapSearchResult").css("top",top).css("left",left);
		//查询数据
		queryMapPointByKey(indexKey, "divMapSearchResult");
		//加载滚动条
		$("#divMapSearchResult").niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "3px",
			cursorborder: "0px solid #fff",
			autohidemode:false
		});
		$("#divMapSearchResult").show();
		$("#divRegionInfo").hide();
		
	}
	/* 查询地图兴趣点 */
	function queryMapPointByKey(key,domid){
		$("#"+domid).html("");
		$.ajax({
			url:"querymappointbykey.do",
			type:"post",
			dataType:"json",
			data:{"keystr":key,"pointtype":cur_pointtype,"dishiid":$("#dishiselect").getSeletedValue(),"xianquid":$("#xianquselect").getSeletedValue()},
			success:function(data){
				if(data!=null&&data!=undefined){
					$("#"+domid).html(mosaicPointSearchResultCode(data));
				}
			}
		});	
	}
	/*清空搜索框*/
	function clearinput(){
		initDefaultInfo();
		CurPointGraphicsLayer.clear();
		/* iptdefaultInit($("#iptMapSearch")); */
		/* $("#iptMapSearch").focus(); */
	}
	//加载左侧兴趣点默认信息
	function initDefaultInfo(){
		$("#iptMapSearch").val("");
		$("#divMapSearchResult").hide();
		if(CurPointGraphicsLayer!=null){
			CurPointGraphicsLayer.clear();
		}
	}
   /*拼接兴趣点查询结果代码*/
  function mosaicPointSearchResultCode(data){
	var htmlcode="";
	if(data.length!=0){
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			var pointCenter = [obj.pointLong,obj.pointLat];
			htmlcode+="<div class='eachmapsearchresult' " +
					"pointid='"+obj.pointId+"' " +
					"pointlong='"+obj.pointLong+"' " +
					"pointlat='"+obj.pointLat+"' " +
					"onclick='loadThisPointInfo(\""+obj.pointId+"\",\""+obj.pointName+"\",this)'>" +
					"<i class='iconfont'>&#xe606;</i><span class='text'>"+obj.pointName+"</span></div>";
		}
	}else{
		htmlcode+="<div class='eachmapsearchresult' style='color:gray'>未找到相关兴趣点</div>";
	}
	return htmlcode;
}
   /* 加载兴趣点数据 */
	function loadThisPointInfo(id,name,e){
		//地图定位
		if(e!=undefined){
			mapCenterAtPoint($(e).attr("pointlong"),$(e).attr("pointlat"));
			mapCenterToThisPoint($(e).attr("pointlong"),$(e).attr("pointlat"));
		}
		$("#divMapSearchResult").hide();
	}
  
  $("#divMapSearchResult").autohide();
  
   /* 界面的布局 */
   function adjust(){
     var wh=$(document).height();
     var ww=$(document).width();
     $(".bottomiframe").height(wh-60);
     /* $(".middlepart").width(ww-280); */
     $("#ifrIndexPool").height($("#pop_divIndexPool").height()-$("#pop_divIndexPool .title").height());
   }   
  </script>
</html>
