/*兴趣点类型数据格式化*/
function FormateIPRType(data){
	var IPRTypedata = [];
	if(data.length != 0){
		
	}
}
/*转化地域数据*/
function regionDishiJsonTransform(data,isAll,isProvince){
	var jsondata = [];
		if(data==null){
			var regionobj = {"text":"全部","value":"All"};
			jsondata.push(regionobj);
		}else{
			if (isProvince) {
					jsondata.push({
						"text" : "全省",
						"value" : "All"
					});
				}
			if (isAll) {
					jsondata.push({
						"text" : "全部",
						"value" : "All"
					});
			}
			if (data.length != 0) {
				for ( var i = 0; i < data.length; i++) {
					var obj = data[i];
					var regionobj = {
							"text" : obj.regionName,
							"value" : obj.regionId
					};
					jsondata.push(regionobj);
				}
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

/*拼接当前指标信息*/
function mosaicCurIndexInfoCode(data){
	var htmlcode="";
	var curIndexDom=$("#divMapCurIndex").find(".main");
	if(data[0]["MM_VAL"]!=null){
		var iconcode="";
		var iconcolor="";
		if(data[0]["RING_RATE"]==0||data[0]["RING_RATE"]==999){
			iconcode = "";
			iconcolor = "";
		}else if(data[0]["RING_RATE"]>0){
			iconcode = "&#xe603;";
			iconcolor = "green";
		}else if(data[0]["RING_RATE"]<0){
			iconcode = "&#xe605;";
			iconcolor = "red";
		}
		htmlcode="<div class='data'>"+
				"<span id='spnNumber' class='number'>"+data[0]["MM_VAL"]+"&nbsp;"+data[0]["MM_UNIT"]+"</span>"+
				"<span id='spnScale' class='scale'><i class='iconfont' style='color:"+iconcolor+"'>"+iconcode+"</i>"+(data[0]["RING_RATE"]==999?"--":data[0]["RING_RATE"])+"%</span>"+
				"</div>"+
				"<div class='info'>"+
				"<span id='spnIndexName'  class='indexname'>"+data[0]["MM_NAME"]+"</span>"+
				"<i class='iconfont relicon' title='查看相关指标' onclick='initMapRelIndexInfo(\""+data[0]["MM_NAME"]+"\",\""+data[0]["MM_ID"]+"\")'>&#xe676;</i>"+
				"<i class='iconfont updateicon' title='编辑相关指标' onclick='showAddRelIndexPop(\""+data[0]["MM_ID"]+"\")'>&#xe7c8;</i>"+
				"</div>";
		$("#divMapCurIndex").attr("MM_ID",data[0]["MM_ID"])
	}else{
		htmlcode="<div class='data'>"+
			"<span id='spnNumber' class='number'>"+"--"+"&nbsp;"+data[0]["MM_UNIT"]+"</span>"+
			"<span id='spnScale' class='scale'>"+"&nbsp;&nbsp;&nbsp;&nbsp;--"+"%</span>"+
			"</div>"+
			"<div class='info'>"+
			"<span id='spnIndexName'  class='indexname'>"+data[0]["MM_NAME"]+"</span>"+
			"<i class='iconfont' onclick=''>&#xe676;</i>"+
			"<i class='iconfont updateicon' title='编辑相关指标' onclick='showAddRelIndexPop(\""+data[0]["MM_ID"]+"\")'>&#xe7c8;</i>"+
			"</div>";
		$("#divMapCurIndex").attr("MM_ID",data[0]["MM_ID"]);
	}
	curIndexDom.html(htmlcode);
}

/*拼接地图指标搜索结果代码*/
function mosaicIndexResultCode(data){
	var htmlcode="";
	if(data.length!=0){
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			if(i != (data.length-1)){
				htmlcode+="<div class='eachresult boder' indexid='"+obj.indexId+"' onclick='loadThisIndex(this,true)'><span class='name'>"+obj.indexName+"</span></div>";
			}else{
				htmlcode+="<div class='eachresult' indexid='"+obj.indexId+"' onclick='loadThisIndex(this,true)'><span class='name'>"+obj.indexName+"</span></div>";
			}
		}
	}else{
		htmlcode+="<div class='eachresult' indexid=''><span class='name' style='color:gray'>未找到相关指标</span></div>";
	}
	return htmlcode;
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
	$("#iptCurIndexId").val(id);
	$("#divQueryIndexResult").hide();
	$("#divSearchIndex").hide();
	inputOptimize($("#iptIndexKey"));
}

/*拼接相关指标的div*/
/*function relevantIndexConnect(obj,data){
	var str="";
	var str1=obj.html()+"";
	var str2="";
	
		str+="<div id='divIndexConnect'>";
			---------------------拼接内容----------------	
		if(str1.indexOf("全网用户数")>0){
			str2="全网用户数";
		}else if(str1.indexOf("4G用户数")>0){
			str2="4G用户数";
		}else if(str1.indexOf("4G户均流量")>0){
			str2="4G户均流量";
		}else if(str1.indexOf("4G套餐用户数")>0){
			str2="4G套餐用户数";
		}else if(str1.indexOf("家宽到达用户数")>0){
			str2="家宽到达用户数";
		}else if(str1.indexOf("投诉量")>0){
			str2="投诉量";
		};
		拼接相关影响指标
		str+="<div  class='indexConnect'>"+
				"<div class='indexConnect-one'><font color='#0DA7F1'><i class='iconfont font4'>&#xe645;</i></font>"+
					str2+":&nbsp;&nbsp;&nbsp;相关影响指标"+
				"</div>"+
				"<div class='indexConnect-two'><font color='#0DA7F1'><i class='iconfont'>&#xe776;</i></font>"+
					"&nbsp;&nbsp;<font color='#0DA7F1'><i class='iconfont font2 cutOut' onclick='cutOut()' >&#xe601;</i></font>"+
				"</div>"+
			  "</div>";
		
		for (var i=0;i<data.length;i++){
			var obj =data[i];
			if(obj["MM_VAL"]==null){
				str+="<div  class='indexConnect'>"+
						"<div class='indexConnect-one'>&nbsp;&nbsp;"+obj["MM_NAME"]+
							":&nbsp;&nbsp;&nbsp;--"+obj["MM_UNIT"]+
						"</div>"+
					"</div>";
			}else{
				if(obj["MM_VAL"]>=0){
					str+="<div  class='indexConnect'>"+
							"<div class='indexConnect-one'>&nbsp;&nbsp;"+obj["MM_NAME"]+
								":&nbsp;&nbsp;&nbsp;--"+obj["MM_UNIT"]+
							"</div>"+
							"<div class='indexConnect-two'>"+
								"<font color='red'><i class='iconfont'>&#xe603;</i></font><font color='#8AA1A7'>&nbsp;&nbsp;"+obj["RING_RATE"]+"%</font>"+
							"</div>"+
						"</div>";
				}else{
					str+="<div  class='indexConnect'>"+
							"<div class='indexConnect-one'>&nbsp;&nbsp;"+obj["MM_NAME"]+
								":&nbsp;&nbsp;&nbsp;--"+obj["MM_UNIT"]+
							"</div>"+
							"<div class='indexConnect-two'>"+
								"<font color='#20D0B9'><i class='iconfont'>&#xe605;</i></font><font color='#8AA1A7'>&nbsp;&nbsp;"+obj["RING_RATE"]+"%</font>"+
							"</div>"+
						 "</div>";
				}
			}
		}
		str+="</div>";
		return str;
}*/

/*相关指标收起*/
function cutOut(){
	$("#divIndexConnect").hide();
}

/*获取相关影响指标*/
function GetMore(object){
	var obj=$(object).closest("div[class=left-second-second]");
	var parent_mm_id=obj.attr("id");
	var date =$("#datepiker").val();
	var dishiRegionId=$("#dishiselect").getSeletedValue();
	var xianquRegionId=$("#xianquselect").getSeletedValue();

	$.ajax({
		url:"queryRelevantIndex.do",
		type:"post",
		dataType:"json",
		data:{"datetype":dateType,"date":date,"regionId":regionId,
			"dishiRegionId":dishiRegionId,"xianquRegionId":xianquRegionId,"parent_mm_id":parent_mm_id},
		success:function(data){
			if(data.length!=0){
				var str=relevantIndexConnect(obj,data);
				if(!$("#divIndexConnect").html()==""){
					$("#divIndexConnect").remove();
				}
				obj.after(str);	
			}
		}
	});
	
}

/*设置导航*/
function navigatorChange(obj){
	var id=$(obj).attr("id");
	/*在地市或全省状态下点击同样的状态，不响应*/
	if(id==MM_LEVEL){
		return;
	}
	if(id=="CITY"&&lvlId=="2"){
		alert("您的权限不足！！");
		return;
	}else if (id=="POINT"){
		changePage();
		return;
	}
	$(obj).addClass("setColor");
	$(obj).siblings().removeClass("setColor");
	MapChange(id);
}
/*地图放大显示*/
function bigger(){
	if(MM_LEVEL=="CITY"){
		return;
	}else if(lvlId=="2"&&MM_LEVEL=="COUNTY"){
		alert("您的没有权限不足！！");
		return;
	}else{
		//加载全省地图
		$("#dishiselect").setSeletedWithValue("All");
		//加载县区下拉
		var t=$("#dishiselect").getSeletedText();
		var cur_xinaqu=regionDishiJsonTransform(allxianquData[regionNameTransform(t)],true);
		$("#xianquselect").reloadListData(cur_xinaqu);
		$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+t);
		 //加载左侧指标
		initLeftIndex();
		//拼接兴趣点数量类型
		initInterstingPointNumber();
		//加载默认指标
		loadThisIndex(null,true);
	}
}

/*地图缩小显示*/
function smaller(){
	if(MM_LEVEL=="CITY"){
		//在全省地图下，点击全市按钮，默认加载石家庄地市地图
		$("#dishiselect").setSeletedWithValue("311350");
		//加载县区下拉
		var t=$("#dishiselect").getSeletedText();
		var cur_xinaqu=regionDishiJsonTransform(allxianquData[regionNameTransform(t)],true);
		$("#xianquselect").reloadListData(cur_xinaqu);
		$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+t);
		 //加载左侧指标
		initLeftIndex();
		//拼接兴趣点数量类型
		initInterstingPointNumber();
		//加载默认指标
		loadThisIndex(null,true);
	}else if(MM_LEVEL=="COUNTY"){
		changePage();
	};
}

/*切换省市页面到jsp兴趣点页面*/
function changePage(){
	var curdishiid=$("#dishiselect").getSeletedValue();
	var curxianquid=$("#xianquselect").getSeletedValue();
	var curindexid=$("#divMapCurIndex").attr("MM_ID");
	var date=$("#datepiker").val().replace(".","").replace(".","");
	var parmater="curdishiid="+curdishiid+"&curxianquid="+curxianquid+"&curindexid="+curindexid+"&curdate="+date+"&curdatetype="+dateType+"&random="+Math.random();
	window.location.href("interestpointrank.do?"+parmater);
}
/* 地图切换 */
function MapChange(id){
	if(id=="CITY"){
		//加载全省地图
		$("#dishiselect").setSeletedWithValue("All");
		//加载县区下拉
		var t=$("#dishiselect").getSeletedText();
		var cur_xinaqu=regionDishiJsonTransform(allxianquData[regionNameTransform(t)],true);
		$("#xianquselect").reloadListData(cur_xinaqu);
		$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+t);
	}else if(id=="COUNTY"){
		//在全省地图下，点击全市按钮，默认加载石家庄地市地图
		$("#dishiselect").setSeletedWithValue("311350");
		//加载县区下拉
		var t=$("#dishiselect").getSeletedText();
		var cur_xinaqu=regionDishiJsonTransform(allxianquData[regionNameTransform(t)],true);
		$("#xianquselect").reloadListData(cur_xinaqu);
		$("#divRegionName").html("<i class='iconfont font1'>&#xe634;</i>&nbsp;"+t);
	}
	
	 //加载左侧指标
	initLeftIndex();
	//拼接兴趣点数量类型
	initInterstingPointNumber();
	//加载默认指标
	loadThisIndex(null,true);
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
	$("#ifrAddRelIndex").attr("src",url+"?indexlvl="+MM_LEVEL+"&indexId="+indexid+"&dateType="+dateType);
}
//关闭地图当前指标的相关指标
function closeMapCurRelIndexInfo(){
	$("#divCurMapRelIndexInfo").hide();
	var dom = $("#divCurMapRelIndexInfo").find(".main");
	dom.getNiceScroll().hide();
}
function closeAddRelIndexPop(){
	$("#pop_divAddRelIndex").hide();
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
	}
	//加载相关指标数据
	queryRelIndex(targetdivid,indexid,regionid);
	$("#divRegionIndex").niceScroll({
		cursorcolor : "#00AFFF",
		cursorborderradius: "0px",
		cursorwidth: "3px",
		cursorborder: "1px solid #fff",
		autohidemode:false
	});
}

/*加载兴趣点类型数据*/
function LoadIPRTypeCount(jsondata){
	if(jsondata.length!=0){
		for(var i=0;i<jsondata.length;i++){
			var obj = jsondata[i];
			$("#"+obj.type).find("span").html(obj.count);
		}
	}
}
