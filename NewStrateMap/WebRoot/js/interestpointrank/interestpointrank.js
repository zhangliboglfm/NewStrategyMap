/*加载兴趣点类型选择框*/
function LoadIPRTypeDiv(DomId,jsondata,selstr){
	var typedom = $("#"+DomId);
	typedom.html("");
	if(jsondata.length!=0){
		var data = FormatePointTypeData(jsondata);
		var max = Math.ceil(data.length/3);
		for(var i=0;i<data.length;i++){
			var eachdom;
			var code;
			var obj=data[i];
			if(i==0){
				code="<div class='eachtype noleftboder' type='"+obj.type+"'><i class='iconfont' fcolor='"+obj.color+"' style='color:"+obj.color+"'>"+obj.code+"</i>"+obj.name+"("+obj.count+")</div>";
			}else{
				code="<div class='eachtype' type='"+obj.type+"'><i class='iconfont' fcolor='"+obj.color+"' style='color:"+obj.color+"'>"+obj.code+"</i>"+obj.name+"("+obj.count+")</div>";
			}
			eachdom=$(code);
			eachdom.bind("click",ChangeIPRType);
			typedom.append(eachdom);
		}
	}
	/* 判断根据下标或者兴趣点类型进行选择 */
	if(typedom.find(".eachtype").length>0){
		if(!isNaN(selstr)){
			typedom.find(".eachtype").eq(selstr).addClass("cur_eachtype");
		}else{
			typedom.find(".eachtype[type='"+selstr+"']").addClass("cur_eachtype");
		}
	}
}
/*加载兴趣点类型数据*/
function LoadIPRTypeCount(jsondata){
	$("#divIprtype").find("div").each(function(i,e){
		$(e).find("span").html(0);
	});
	if(jsondata.length!=0){
		for(var i=0;i<jsondata.length;i++){
			var obj = jsondata[i];
			$("#"+obj.type).find("span").html(obj.count);
		}
	}
}
/*格式化兴趣点数据*/
function FormatePointTypeData(data){
	for(var i=0;i<data.length;i++){
		var obj = data[i];
		if(obj.type=="EDUCATION"){
			obj.name="学校";
			obj.code="&#xe61f;";
			obj.color="red";
		}else if(obj.type=="GOVERNMENT"){
			obj.name="机关";
			obj.code="&#xe691;";
			obj.color="red";
		}else if(obj.type=="HEALTH_CARE"){
			obj.name="医院";
			obj.code="&#xe63d;";
			obj.color="red";
		}else if(obj.type=="HOME"){
			obj.name="住宅";
			obj.code="&#xe60d;";
			obj.color="red";
		}else if(obj.type=="ORTHER"){
			obj.name="其他";
			obj.code="&#xe642;";
			obj.color="red";
		}else if(obj.type=="SHOP"){
			obj.name="商圈";
			obj.code="&#xe65e;";
			obj.color="red";
		}else if(obj.type=="TRADING_ESTATE"){
			obj.name="企业";
			obj.code="&#xe659;";
			obj.color="red";
		}
	}
	return data;
}
/*拼接当前指标信息*/
function mosaicCurIndexInfoCode(data){
	var curIndexDom=$("#divMapCurIndex").find(".main");
	var iconcode="";
	var iconcolor="";
	if(data.indexRateType=="up"){
		iconcode = "&#xe603;";
		iconcolor = "green";
	}else if(data.indexRateType=="down"){
		iconcode = "&#xe605;";
		iconcolor = "red";
	}
	var htmlcode="";
	htmlcode="<div class='data'>"+
			"<span id='spnNumber' class='number'>"+data.value+"&nbsp;"+data.unit+"</span>"+
			"<span id='spnScale' class='scale'><i class='iconfont' style='color:"+iconcolor+"'>"+iconcode+"</i>"+(data.rate==999?"--":data.rate)+"%</span>"+
			"</div>"+
			"<div class='info'>"+
			"<span id='spnIndexName' class='indexname'>"+data.name+"</span>"+
			"<i class='iconfont relicon' title='查看相关指标' onclick='initMapRelIndexInfo(\""+data.name+"\",\""+data.id+"\")'>&#xe676;</i>"+
			"<i class='iconfont updateicon' title='编辑相关指标' onclick='showAddRelIndexPop(\""+data.id+"\")'>&#xe7c8;</i>"+
			"</div>";
	curIndexDom.html(htmlcode);
}
/*拼接地图指标搜索结果代码*/
function mosaicIndexResultCode(data){
	var htmlcode="";
	if(data.length!=0){
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			if(i != (data.length-1)){
				htmlcode+="<div class='eachresult boder' indexid='"+obj.indexId+"' onclick='loadThisIndex(this)'><span class='name'>"+obj.indexName+"</span></div>";
			}else{
				htmlcode+="<div class='eachresult' indexid='"+obj.indexId+"' onclick='loadThisIndex(this)'><span class='name'>"+obj.indexName+"</span></div>";
			}
		}
	}else{
		htmlcode+="<div class='eachresult' indexid=''><span class='name' style='color:gray'>未找到相关指标</span></div>";
	}
	return htmlcode;
}
/*拼接场景列表代码*/
function mosaicSecneList(scenetype,data){
	var htmlcode = "";
	if(scenetype=="R"){
		if(data.length != 0){
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				htmlcode+="<div class='eachscene' sceneid='"+obj.sceneId+"' scenetype='"+obj.sceneType+"' scindexid='"+obj.sceneFIndexId+"' scindexname='"+obj.sceneFIndexName+"' " +
						"title='"+obj.sceneDesc+"'><span>"+obj.sceneName+"</span>" +
						"<i class='iconfont check' onclick='changeScene(this)' title='加载场景'>&#xe7b0;</i>" +
						"</div>";
			}
		}
	}else if(scenetype=="M"){
		if(data.length != 0){
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				htmlcode+="<div class='eachscene' sceneid='"+obj.sceneId+"' scenetype='"+obj.sceneType+"' scindexid='"+obj.sceneFIndexId+"' scindexname='"+obj.sceneFIndexName+"' " +
						"title='"+obj.sceneDesc+"'><span>"+obj.sceneName+"</span>" +
						"<i class='iconfont update' title='修改场景' onclick='updateScene(this)'>&#xe77d;</i>" +
						"<i class='iconfont  check' onclick='changeScene(this)' title='加载场景'>&#xe7b0;</i>" +
						"</div>";
			}
		}
	}
	return htmlcode;
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
/*设置导航*/
function navigatorChange(e){
	MM_LEVEL=$(e).attr("id");
	var dishiid = $("#dishiselect").getSeletedValue();
	var xianquid = $("#xianquselect").getSeletedValue();
	var date=$("#datepiker").val().replace(".","").replace(".","");
	if(MM_LEVEL=="CITY" || MM_LEVEL=="COUNTY"){
		if(MM_LEVEL=="CITY"){
			dishiid = "All";
		}
		if(lvlId=="3"){
			alert("您的权限不足！");
			return false;
		}else if(lvlId=="2"){
			if(MM_LEVEL=="CITY"){
				alert("您的权限不足！");
				return false;
			}else{
				$(e).addClass("setColor");
				$(e).siblings().removeClass("setColor");
				window.location.href("provincecityrank.do?mm_leve="+MM_LEVEL+"&dishiId="+dishiid+"&xianquId="+xianquid+"&indexId="+cur_index+"&date="+date+"&dateType="+dateType);
			}
		}else if(lvlId=="1"){
			$(e).addClass("setColor");
			$(e).siblings().removeClass("setColor");
			window.location.href("provincecityrank.do?mm_leve="+MM_LEVEL+"&dishiId="+dishiid+"&xianquId="+xianquid+"&indexId="+cur_index+"&date="+date+"&dateType="+dateType);
		}
	}else if(MM_LEVEL=="POINT"){
		$(e).addClass("setColor");
		$(e).siblings().removeClass("setColor");
		window.location.href("interestpointrank.do");
	}
}
/*地图放大显示*/
function bigger(){
	var dishiid = $("#dishiselect").getSeletedValue();
	var xianquid = $("#xianquselect").getSeletedValue();
	var date=$("#datepiker").val().replace(".","").replace(".","");
	if(MM_LEVEL=="CITY"){
		return;
	}else if(MM_LEVEL=="COUNTY"){
		MM_LEVEL="CITY";
	}else if(MM_LEVEL=="POINT"){
		MM_LEVEL="COUNTY";
	}
	var dishiid = $("#dishiselect").getSeletedValue();
	if(MM_LEVEL=="CITY" || MM_LEVEL=="COUNTY"){
		if(MM_LEVEL=="CITY"){
			dishiid = "All";
		}
		if(lvlId=="3"){
			alert("您的权限不足！");
			MM_LEVEL = "POINT";
			return false;
		}else if(lvlId=="2"){
			if(MM_LEVEL=="CITY"){
				alert("您的权限不足！");
				MM_LEVEL = "POINT";
				return false;
			}else{
				window.location.href("provincecityrank.do?mm_leve="+MM_LEVEL+"&dishiId="+dishiid+"&xianquId="+xianquid+"&indexId="+cur_index+"&date="+date+"&dateType="+dateType);
			}
		}else if(lvlId=="1"){
			window.location.href("provincecityrank.do?mm_leve="+MM_LEVEL+"&dishiId="+dishiid+"&xianquId="+xianquid+"&indexId="+cur_index+"&date="+date+"&dateType="+dateType);
		}
	}else if(MM_LEVEL=="POINT"){
		window.location.href("interestpointrank.do");
	}
	$("#"+MM_LEVEL).addClass("setColor");
	$("#"+MM_LEVEL).siblings().removeClass("setColor");
}

/*地图缩小显示*/
function smaller(){
	var dishiid = $("#dishiselect").getSeletedValue();
	var xianquid = $("#xianquselect").getSeletedValue();
	var date=$("#datepiker").val().replace(".","").replace(".","");
	if(MM_LEVEL=="CITY"){
		MM_LEVEL="COUNTY";
	}else if(MM_LEVEL=="COUNTY"){
		MM_LEVEL="POINT";
	};
	var dishiid = $("#dishiselect").getSeletedValue();
	if(MM_LEVEL=="CITY" || MM_LEVEL=="COUNTY"){
		if(MM_LEVEL=="CITY"){
			dishiid = "All";
		}
		if(lvlId=="3"){
			alert("您的权限不足！");
			MM_LEVEL = "POINT";
			return false;
		}else if(lvlId=="2"){
			if(MM_LEVEL=="CITY"){
				alert("您的权限不足！");
				MM_LEVEL = "POINT";
				return false;
			}else{
				window.location.href("provincecityrank.do?mm_leve="+MM_LEVEL+"&dishiId="+dishiid+"&xianquId="+xianquid+"&indexId="+cur_index+"&date="+date+"&dateType="+dateType);
			}
		}else if(lvlId=="1"){
			window.location.href("provincecityrank.do?mm_leve="+MM_LEVEL+"&dishiId="+dishiid+"&xianquId="+xianquid+"&indexId="+cur_index+"&date="+date+"&dateType="+dateType);
		}
	}else if(MM_LEVEL=="POINT"){
		//window.location.href("interestpointrank.do");
	}
	$("#"+MM_LEVEL).addClass("setColor");
	$("#"+MM_LEVEL).siblings().removeClass("setColor");
}
/*下载当前地域下当前指标数据*/
function downloadData(url){
	window.open(url);
}
/*兴趣点类型数据格式化*/
function FormateIPRType(data){
	var IPRTypedata = [];
	if(data.length != 0){
		
	}
}
/*转化地域数据*/
function regionDishiJsonTransform(data,isAll){
	var jsondata = [];
	if(isAll){
		jsondata.push({"text":"全部","value":"All"});
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
/*转化地域名称*/
function regionNameTransform(rname){
	if(rname.indexOf("石家庄")==0){
		return "SJZ";
	}else if(rname.indexOf("保定")==0){
		return "BD";
	}else if(rname.indexOf("张家口")==0){
		return "ZJK";
	}else if(rname.indexOf("承德")==0){
		return "CD";
	}else if(rname.indexOf("唐山")==0){
		return "TS";
	}else if(rname.indexOf("廊坊")==0){
		return "LF";
	}else if(rname.indexOf("沧州")==0){
		return "CZ";
	}else if(rname.indexOf("衡水")==0){
		return "HS";
	}else if(rname.indexOf("邢台")==0){
		return "XT";
	}else if(rname.indexOf("邯郸")==0){
		return "HD";
	}else if(rname.indexOf("秦皇岛")==0){
		return "QHD";
	}else{
		return "";
	}
}
/*关闭搜索指标弹出框*/
function CloseSearchDiv(){
	$("#divQueryIndexResult").hide();
	$("#divSearchIndex").hide();
	inputOptimize($("#iptIndexKey"));
}
/*关闭搜索指标弹出框*/
function closeIndexSearchDiv(name,id){
	$("#spnIndexName").html(name);
	$("#spnCurSceneIndexName").html(name);
	$("#iptCurIndexId").val(id);
	$("#divQueryIndexResult").hide();
	$("#divSearchIndex").hide();
	inputOptimize($("#iptIndexKey"));
}
/*展开收起场景列表框*/
function switchSceneList(e){
	var iconDom = $(e);
	$("#divSceneList").toggle();
	if($("#divSceneList").is(":hidden")){
		iconDom.html("&#xe666;");
	}else{
		iconDom.html("&#xe601;");
	}
	if($("#divSceneList").is(":hidden")){
		$("#divRSList").getNiceScroll().hide();
		$("#divMSList").getNiceScroll().hide();
	}else{
		$("#divRSList").getNiceScroll().resize().show();
		$("#divMSList").getNiceScroll().resize().show();
	}
}
function updateScene(e){
	var sceneid = $(e).parent().attr("sceneid");
	var scenetype = $(e).parent().attr("scenetype");
	var type="edit";
	window.open("scenecustomization.do?type="+type+"&sceneid="+sceneid+"&scenetype="+scenetype);
		
}
/* 场景切换 */
function changeScene(e){
	var scenename =$(e).parent().find("span").text();
	var sceneid = $(e).parent().attr("sceneid");
	var scindexid = $(e).parent().attr("scindexid");
	var scindexname = $(e).parent().attr("scindexname");
	var scenetype = $(e).parent().attr("scenetype");
	if(scindexid!="null"){
		cur_index=scindexid;
	}
	isOrNotScene="Y";
	cur_SceneId=sceneid;
	cur_SceneName=scenename;
	cur_SceneType=scenetype;
	$("#divMapCurIndex").hide();
	$("#divCurSceneInfo").show();
	$("#divSearchIndex").hide();
	$("#divQueryIndexResult").hide();
	$("#divCurMapRelIndexInfo").hide();
	$("#divMapTypeSwicth").hide();
	$("#spnCurSceneName").html(scenename);
	if(scindexname!="null"){
		$("#spnCurSceneIndexName").html(scindexname);
	}else{
		$("#spnCurSceneIndexName").html($("#iptCurIndexId").attr("defaultindexname"));
	}
	//加载场景
	CurPointGraphicsLayer.clear();
	initDefaultInfo();
	initPointType();
	getPointPolygonData();
	if(cur_SceneType=="check"){
		var fristGraphic=PointGraphicsLayer.graphics[0];
		if(fristGraphic!=null){
			var fristLong=fristGraphic.attributes.pointlong;
			var fristLat=fristGraphic.attributes.pointlat;
			mapCenterAtPoint(fristLong, fristLat);
		}
	}
}
/*退出场景*/
function quitScene(){
	$("#cur_scene").html("");
	isOrNotScene="N";
	cur_SceneId="";
	cur_SceneName="";
	cur_SceneType="";
	$("#divMapCurIndex").show();
	$("#divCurSceneInfo").hide();
	$("#divMapTypeSwicth").show();
	//重新查询兴趣点类型数据
	initPointType();
	//重新查询兴趣点轮廓
	getPointPolygonData();
	//重新查询地图当前指标数据
	initMapCurIndex(cur_index, dateType, $("#datepiker").val(), cur_regionid);
}
//拼接地图当前指标相关影响指标
function mosaicMapRelIndexDiv(data){
	var htmlcode = "";
	if(data.length !=0){
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			var rateobj = getRateIconCode(obj.indexRateType);
			htmlcode+="<div class='eachrelindex'>"+
					"<span class='indexname'>"+obj.indexName+":</span>"+
					"<span class='indexvalue'>"+obj.indexValue+"&nbsp;"+obj.indexUnit+"</span>"+
					"<span class='indexrate'>"+(obj.indexRate==999?"--":obj.indexRate)+"%</span>"+
					"<i class='iconfont rateicon' style='color:"+rateobj.color+"'>"+rateobj.code+"</i>"+
					"</div>";
		}
	}
	return htmlcode;
}
/*拼接地域或兴趣点指标信息*/
function mosaicRegionIndexDiv(data){
	var htmlcode = ""; 
	if(data.length !=0){
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			var indexobj = getIndexIconCode(obj.indexName);
			var rateobj = getRateIconCode(obj.indexRateType);
			htmlcode+="<div class='eachindexdiv' moredivid='div"+i+"'>"+
					"<i class='iconfont signicon' style='color:"+indexobj.color+"'>"+indexobj.code+"</i>"+
					"<span class='indexname'>"+obj.indexName+":</span>"+
					"<span class='indexvalue'>"+obj.indexValue+"&nbsp;"+obj.indexUnit+"</span>"+
					"<i class='iconfont moreicon' " +
					"indexid='"+obj.indexId+"' " +
					"indexname='"+obj.indexName+"' " +
					"regionid='"+obj.regionId+"' onclick='getmoreinfo(this)' title='查看相关影响指标'>&#xe666;</i>"+
					"<span class='indexrate'>"+(obj.indexRate==999?"--":obj.indexRate)+"%</span>"+
					"<i class='iconfont rateicon' style='color:"+rateobj.color+"'>"+rateobj.code+"</i>"+
					"</div>"+
					"<div id='div"+i+"' class='moreinfodiv'>"+
					"<div class='reltitle'>"+
					"<span class='curindexname'></span><span>--相关影响指标:</span>"+
					"<i class='iconfont addindex' title='编辑相关指标' onclick='showAddRelIndexPop(\""+obj.indexId+"\")'>&#xe7c8;</i>"+
					"</div>"+
					"<div class='relmain'></div>"+
					"</div>";
		}
	}
	$("#divRegionIndex").html(htmlcode);
	$("#divRegionIndex").niceScroll({
		cursorcolor : "#00AFFF",
		cursorborderradius: "0px",
		cursorwidth: "3px",
		cursorborder: "0px solid #fff",
	});
}
/*拼接网格指标信息*/
function mosaicGirdIndexDiv(data){
	var htmlcode = ""; 
	if(data.length !=0){
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			var indexobj = getIndexIconCode(obj.indexName);
			var rateobj = getRateIconCode(obj.indexRateType);
			htmlcode+="<div class='eachindexdiv' moredivid='divGrid"+i+"'>"+
					"<i class='iconfont signicon' style='color:"+indexobj.color+"'>"+indexobj.code+"</i>"+
					"<span class='indexname'>"+obj.indexName+":</span>"+
					"<span class='indexvalue'>"+obj.indexValue+"&nbsp;"+obj.indexUnit+"</span>"+
					"<i class='iconfont moreicon' " +
					"indexid='"+obj.indexId+"' " +
					"indexname='"+obj.indexName+"' " +
					"regionid='"+obj.gridId+"' onclick='getmoreinfo(this)' title='查看相关影响指标'>&#xe666;</i>"+
					"<span class='indexrate'>"+(obj.indexRate==999?"--":obj.indexRate)+"%</span>"+
					"<i class='iconfont rateicon' style='color:"+rateobj.color+"'>"+rateobj.code+"</i>"+
					"</div>"+
					"<div id='divGrid"+i+"' class='moreinfodiv'>"+
					"<div class='reltitle'>"+
					"<span class='curindexname'></span><span>--相关影响指标:</span>"+
					"<i class='iconfont addindex' title='编辑相关指标' onclick='showAddRelIndexPop(\""+obj.indexId+"\")'>&#xe7c8;</i>"+
					"</div>"+
					"<div class='relmain'></div>"+
					"</div>";
		}
	}
	$("#divGridIndexInfo").html(htmlcode);
	$("#divGridIndexInfo").niceScroll({
		cursorcolor : "#00AFFF",
		cursorborderradius: "0px",
		cursorwidth: "3px",
		cursorborder: "0px solid #fff",
	});
	
}
/*拼接关联指标*/
function mosaicRelIndexDiv(data){
	var htmlcode = ""; 
	if(data.length !=0){
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			var rateobj = getRateIconCode(obj.indexRateType);
			htmlcode+="<div class='eachrelindexdiv'>"+
					"<span class='indexname'>"+obj.indexName+":</span>"+
					"<span class='indexvalue'>"+obj.indexValue+"&nbsp;"+obj.indexUnit+"</span>"+
					"<span class='indexrate'>"+(obj.indexRate==999?"--":obj.indexRate)+"%</span>"+
					"<i class='iconfont rateicon' style='color:"+rateobj.color+"'>"+rateobj.code+"</i>"+
					"</div>";
		}
	}
	return htmlcode;
}
/*获取比例的上升下降*/
function getIndexIconCode(str){
	var result;
	if("移动用户数" == str){
		result = {"code":"&#xe614;","color":"#41B9EE"};
	}else if("4G用户数" == str){
		result = {"code":"&#xe639;","color":"#EC8E36"};
	}else if("4G套餐用户数" == str){
		result = {"code":"&#xe6b8;","color":"#5CD6EB"};
	}else if("家宽到达用户数" == str){
		result = {"code":"&#xe620;","color":"#FF7228"};
	}else if("投诉量" == str){
		result = {"code":"&#xe61a;","color":"#78D0F8"};
	}else if("4G户均流量" == str){
		result = {"code":"&#xe61d;","color":"#EB7372"};
	}else{
		result = {"code":"","color":""};
	}
	return result;
}
/*获取比例的上升下降*/
function getRateIconCode(str){
	var result;
	if(str=="up"){
		result={"code":"&#xe603;","color":"green"};
	}else if(str=="down"){
		result={"code":"&#xe605;","color":"red"};
	}else{
		result={"code":"","color":""};
	}
	return result;
}

/*展开并获取指标相关信息下拉框*/
function getmoreinfo(e){
	var indexname = $(e).attr("indexname");
	var indexid = $(e).attr("indexid");
	var regionid = $(e).attr("regionid");
	var targetdivid = $(e).parent().attr("moredivid");
	$("#"+targetdivid).find(".reltitle").find(".curindexname").html(indexname);
	$("#"+targetdivid).toggle();
	if($("#"+targetdivid).is(":hidden")){
		$(e).html("&#xe666;");
	}else{
		$(e).html("&#xe601;");
		//加载相关指标数据
		queryRelIndex(targetdivid,indexid,regionid);
	}
	$("#divRegionIndex").getNiceScroll().resize();
}
/*加载兴趣点基础数据*/
function initpointBaseInfo(data){
	var nameDom = $("#divPointTitle").find(".baseinfo").find(".name");
	var detailDom = $("#divPointTitle").find(".baseinfo").find(".detail");
	var contratsDom = $("#divPointTitle").find(".baseinfo").find(".contrats");
	var starDom = $("#divPointTitle").find(".baseinfo").find(".star");
	var typeDom = $("#divPointTitle").find(".baseinfo").find(".type");
	var textDom = $("#divPointTitle").find(".baseinfo").find(".data").find(".text");
	var valueDom = $("#divPointTitle").find(".baseinfo").find(".data").find(".value");
	/*var btsbnt=$("#divPointTitle").find(".otherbntdiv").find(".left_bnt");
	var yytbnt=$("#divPointTitle").find(".otherbntdiv").find(".right_bnt");
	btsbnt.attr("pointid",data.pointid);
	yytbnt.attr("pointid",pointid);*/
	detailDom.data("pointjson",data);
	contratsDom.data("pointjson",data);
	$("#divPointTitle").find(".otherbntdiv").attr("pointid",data.pointid);
	nameDom.html(data.pointname);
	nameDom.attr("title",data.pointname);
	if(data.pointtype == "HOME"){
		typeDom.html("住宅");
	}else if(data.pointtype == "EDUCATION"){
		typeDom.html("学校");
	}else if(data.pointtype == "GOVERNMENT"){
		typeDom.html("机关");
	}else if(data.pointtype == "HEALTH_CARE"){
		typeDom.html("医院");
	}else if(data.pointtype == "SHOP"){
		typeDom.html("商圈");
	}else if(data.pointtype == "ORTHER"){
		typeDom.html("其他");
	}else if(data.pointtype == "TRADING_ESTATE"){
		typeDom.html("企业");
	}else{
		typeDom.html("--");
	}
	var allstar = 5;
	var ystar = data.pointstar;
	var starcode="";
	if(ystar>0){
		for(var i=0;i<ystar;i++){
			starcode+="<i class='iconfont' style='color:yellow'>&#xe60e;</i>";
		}
		for(var i=0;i<(allstar-ystar);i++){
			starcode+="<i class='iconfont'>&#xe60e;</i>";
		}
	}else{
		for(var i=0;i<allstar;i++){
			starcode+="<i class='iconfont'>&#xe60e;</i>";
		}
	}
	starDom.html(starcode);
	textDom.html(data.pointcnttext+"：");
	valueDom.html(data.pointcnt+"人");
}
/*显示或隐藏兴趣点详情*/
function showPointDetailPop(e){
	var domheight = $(window).height();
	var domwidth = $(window).width();
	var popTop = domheight*0.5-$("#pop_divPointDetailInfo").height()*0.5;
	var popLeft = domwidth*0.5-$("#pop_divPointDetailInfo").width()*0.5;
	$("#pop_divPointDetailInfo").css("top",popTop).css("left",popLeft);
	var pointjson = $(e).data("pointjson");
	var pointid=pointjson.pointid;
	var pointtype=pointjson.pointtype;
	var pointname=pointjson.pointname;
	var date = $("#datepiker").val().replace(".","").replace(".","");
	$("#pop_divAddRelIndex").hide();
	$("#pop_divPointDetailInfo").find(".title").find("span").html(pointname+"--详细分析");
	$("#pop_divPointDetailInfo").show();
	var url = "pointdetailanalysis.do";
	$("#ifrPointDetail").attr("src",url+"?pointId="+pointid+"&pointType="+pointtype+"&dateType="+dateType+"&date="+date);
}
/*显示或隐藏添加相关指标*/
function showAddRelIndexPop(indexid){
	var domheight = $(window).height();
	var domwidth = $(window).width();
	var popTop = domheight*0.5-$("#pop_divAddRelIndex").height()*0.5;
	var popLeft = domwidth*0.5-$("#pop_divAddRelIndex").width()*0.5;
	$("#pop_divAddRelIndex").css("top",popTop).css("left",popLeft);
	var date = $("#datepiker").val().replace(".","").replace(".","");
	$("#pop_divPointDetailInfo").hide();
	$("#pop_divAddRelIndex").find(".title").find("span").html("编辑相关影响指标");
	$("#pop_divAddRelIndex").show();
	var url = "addrelationindex.do";
	$("#ifrAddRelIndex").attr("src",url+"?indexlvl=POINT&indexId="+indexid+"&dateType="+dateType);
}
/*关闭兴趣点详情弹出层*/
function closePointDetailPop(){
	$("#pop_divPointDetailInfo").hide();
}
function closeAddRelIndexPop(){
	$("#pop_divAddRelIndex").hide();
}
/*显示或关闭已选对比框*/
function showContratsList(e){
	//$("#divSelPointList").toggle();
	var dom=$("#divSelPointList").find(".list");
	if($("#divSelPointList").is(":hidden")){
		$(e).find("i").html("&#xe666;");
		$("#divSelPointList").show();
		if(dom.getNiceScroll() != null){
			dom.getNiceScroll().resize().show();
		}
	}else{
		$(e).find("i").html("&#xe601;");
		$("#divSelPointList").hide();
		if(dom.getNiceScroll() != null){
			dom.getNiceScroll().hide();
		}
	}
}
/*添加至兴趣点对比框*/
function addToContratsDiv(e){
	var pointjson = $(e).data("pointjson");
	var pointid=pointjson.pointid;
	var pointtype=pointjson.pointtype;
	var pointname=pointjson.pointname;
	var isHave=false;
	if($("#divSelPointList").find(".list").find(".eachpoint").length<5){
		$("#divSelPointList").find(".list").find(".eachpoint").each(function(i,e){
			if($(e).attr("pointid")==pointid){
				isHave = true;
				return false;
			}
		});
	}else{
		isHave=true;
		alert("对不起，最多只能选择5个兴趣点！");
		return false;
	}
	var htmlcode="";
	if(!isHave){
		htmlcode="<div class='eachpoint' pointid='"+pointid+"' pointtype='"+pointtype+"'><span title='"+pointname+"'>"+pointname+"</span><i class='iconfont' title='清除' onclick='removeThisPoint(this)'>&#xe62b;</i></div>";
		$("#divSelPointList").find(".list").append($(htmlcode));
	}else{
		alert("兴趣点已存在！请勿重新选择！");
		return false;
	}
	var selnum = $("#divSelPointList").find(".list").find(".eachpoint").length;
	$("#divSelPointList").find(".title").find("span.num").html(selnum);
	$("#divSelContratsBnt").find("span.num").html(selnum);
}
/*移除对比中的兴趣点*/
function removeThisPoint(e){
	$(e).parent().remove();
	var selnum = $("#divSelPointList").find(".list").find(".eachpoint").length;
	$("#divSelPointList").find(".title").find("span.num").html(selnum);
	$("#divSelContratsBnt").find("span.num").html(selnum);
}
/*开始对比*/
function startPointContrats(){
	var selnum = $("#divSelPointList").find(".list").find(".eachpoint").length;
	if(selnum<2){
		alert("请至少选择两个兴趣点！");
		return false;
	}
	var pointjson = encodeURI(getPointId());
	var date = $("#datepiker").val().replace(".","").replace(".","");
	window.open("interestingpointcompare.do?regionid="+pointjson+"&datetype="+dateType+"&date="+date+"&indexid="+cur_index);
}
/*获取兴趣点对比中兴趣点ID*/
function getPointId(){
	var json=[];
	$("#divSelPointList").find(".list").find(".eachpoint").each(function(i,e){
		var pointid=$(e).attr("pointid");
		json.push(pointid);
	});
	return JSON.stringify(json);
}
/*获取地市中心点*/
function getDishiCenterPoint(dishiname){
	var point;
	switch (dishiname) {
	case "石家庄":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 114.50880162626058,"y": 38.041926488807206,"spatialReference": {"wkid": 4326}}));
		break;
	case "秦皇岛":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 119.6,"y": 39.93,"spatialReference": {"wkid": 4326}}));
		break;
	case "张家口":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 114.88,"y": 40.82,"spatialReference": {"wkid": 4326}}));
		break;
	case "邯郸":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 114.48,"y": 36.62,"spatialReference": {"wkid": 4326}}));
		break;
	case "承德":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 117.93,"y": 40.97,"spatialReference": {"wkid": 4326}}));
		break;
	case "廊坊":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 116.7,"y": 39.52,"spatialReference": {"wkid": 4326}}));
		break;
	case "唐山":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 118.2,"y": 39.63,"spatialReference": {"wkid": 4326}}));
		break;
	case "衡水":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 115.68,"y": 37.73,"spatialReference": {"wkid": 4326}}));
		break;
	case "保定":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 115.47,"y": 38.87,"spatialReference": {"wkid": 4326}}));
		break;
	case "沧州":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 116.83,"y": 38.3,"spatialReference": {"wkid": 4326}}));
		break;
	case "邢台":
		point = new esri.geometry.Point(new esri.geometry.Point({"x": 114.48,"y": 37.07,"spatialReference": {"wkid": 4326}}));
		break;
	}
	return point;
}
/*定位不匹配地域*/
function getUnKonwnCenterPoint(dishiname,xianquname){
	var point = new esri.geometry.Point(new esri.geometry.Point({"x": 114.50874900026633  ,"y": 38.04169000039337,"spatialReference": {"wkid": 4326}}));
	if(dishiname=="沧州"){
		if(xianquname=="沧州市区"){
			point = new esri.geometry.Point(new esri.geometry.Point({"x": 116.86208853629841 ,"y": 38.30799897990286,"spatialReference": {"wkid": 4326}}));
		}else if(xianquname=="港口"){
			point = new esri.geometry.Point(new esri.geometry.Point({"x": 117.7766320979939 ,"y": 38.27430369603268,"spatialReference": {"wkid": 4326}}));
		}
	}else if(dishiname=="保定"){
		if(xianquname=="白沟县"){
			point = new esri.geometry.Point(new esri.geometry.Point({"x": 116.03405379057631 ,"y": 39.129240558907504,"spatialReference": {"wkid": 4326}}));
		}else if(xianquname=="保定市区"){
			point = new esri.geometry.Point(new esri.geometry.Point({"x": 115.45230099959167 ,"y": 38.876603999974805 ,"spatialReference": {"wkid": 4326}}));
		}else if(xianquname=="市西区"){
			point = new esri.geometry.Point(new esri.geometry.Point({"x": 115.52308296979373 ,"y": 38.85419818881032 ,"spatialReference": {"wkid": 4326}}));
		}
	}else if(dishiname=="张家口"){
		if(xianquname=="张家口市区"){
			point = new esri.geometry.Point(new esri.geometry.Point({"x": 114.88172899989365  ,"y": 40.822941999873535,"spatialReference": {"wkid": 4326}}));
		}
	}else if(dishiname=="承德"){
		if(xianquname=="承德市区"){
			point = new esri.geometry.Point(new esri.geometry.Point({"x": 117.95555335791546 ,"y": 40.947023726023055,"spatialReference": {"wkid": 4326}}));
		}
	}else if(dishiname=="唐山"){
		if(xianquname=="唐山市区"){
			point = new esri.geometry.Point(new esri.geometry.Point({"x": 118.17143232423383,"y": 39.632548567206385,"spatialReference": {"wkid": 4326}}));
		}else if(xianquname=="曹妃甸区"){
			point = new esri.geometry.Point(new esri.geometry.Point({"x": 118.40519700000004,"y": 39.27422000000007 ,"spatialReference": {"wkid": 4326}}));
		}
	}else if(dishiname=="秦皇岛"){
		if(xianquname=="秦皇岛市区"){
			point = new esri.geometry.Point(new esri.geometry.Point({"x": 119.60494900321582,"y": 39.932937501597564 ,"spatialReference": {"wkid": 4326}}));
		}
	}
	return point;
}
//输入框回复默认
function iptdefaultInit(e){
	$(e).css("color","gray");
	$(e).val($(e).attr("defaulttxt"));
}
//关闭地图当前指标的相关指标
function closeMapCurRelIndexInfo(){
	var dom = $("#divCurMapRelIndexInfo").find(".main");
	dom.getNiceScroll().hide();
	$("#divCurMapRelIndexInfo").hide();
}
/*显示网格指标信息框*/
function showGridInfoPopDiv(gridid){
	var domheight = $(window).height();
	var domwidth = $(window).width();
	var popTop = domheight*0.5-$("#pop_divGridIndexInfo").height()*0.5;
	var popLeft = domwidth*0.5-$("#pop_divGridIndexInfo").width()*0.5;
	$("#pop_divGridIndexInfo").css("top",popTop).css("left",popLeft);
	$("#pop_divGridIndexInfo").show();
	$("#spnGridId").html("网格ID:"+gridid);
	queryGridIndexInfoData(gridid);
	$("#divGridIndexInfo").getNiceScroll().show();
}
/*隐藏网格指标信息框*/
function hideGridInfoPopDiv(){
	$("#pop_divGridIndexInfo").hide();
	$("#divGridIndexInfo").getNiceScroll().hide();
}