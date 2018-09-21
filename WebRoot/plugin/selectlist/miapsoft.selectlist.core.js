/**
 * <p>Title:miapsoft.selectlist.core.js</p>
 * <p>Description: 下拉框插件</p>
 * @author:白少华
 * @version:1.0
 * @time:2017-03-17
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2017</p>
 */
(function($){
	var option;
	var parElementW;
	var parElementH;
	var _fn_click;
	var iptid;
	var iconid;
	var ulid;
	$.fn.miapSelect=function(options){
		var id=guid();
		iptid="sel_ipt"+id;
		iconid="sel_icon"+id;
		ulid="sel_ul"+id;
		$(this).attr("pid",id);
		parElementW=($(this).width()<80?80:$(this).width());
		parElementH=$(this).height();
		option = $.extend({
			listwidth:"auto",
			listheight:"auto",
			listlineheight:25,
			data:[],
			selindex:0,
			eachLiClick:function(index,text,value){}
		},options);
		$(this).data("option",option);
		$(this).selectDomCode().setDomStyle().initListData().initSeletedPlug();
		$("body").click(function(e){
			if(e.target.id.indexOf("sel_icon")== -1 && e.target.id.indexOf("sel_ipt")== -1){
				$(".selIconfont").html("&#xe601;");
				$(".selUl").hide();
			}
		});
		return this;
	};
	$.fn.selectDomCode=function(){
		var inputDom = $("<input id='"+iptid+"' class='selectInput' ulid='"+ulid+"' type='text' readonly/>");
		var iconDom = $("<i id='"+iconid+"' ulid='"+ulid+"' class='selIconfont ipticon'>&#xe601;</i>");
		var ulDom = $("<ul id='"+ulid+"' iptid='"+iptid+"' iconid='"+iconid+"' class='selUl autohide'></ul>");
		$(this).append(inputDom);
		$(this).append(iconDom);
		$("body").append(ulDom);
		return this;
	}
	
	$.fn.setDomStyle=function(){
		var inputDom = $(this).find(".selectInput");
		var iconDom = $(this).find(".selIconfont");
		var ulDom = $("body").find("#"+ulid);
		
		var iptwidth=parElementW-30;
		var iptheight=parElementH-10;
		inputDom.css("width",iptwidth+"px");
		inputDom.css("height",iptheight+"px");
		inputDom.css("margin-left","5px");
		inputDom.css("margin-top","5px");
		
		iconDom.css("margin-right","5px");
		iconDom.css("height",parElementH+"px");
		iconDom.css("line-height",parElementH+"px");
		
		var pos=$(this).offset();
		var ultop=pos.top+parElementH;
		var ulleft=pos.left;
		ulDom.css("min-width",parElementW);
		ulDom.css("width",option.listwidth);
		ulDom.css("max-height","200px");
		ulDom.css("height",option.listheight);
		ulDom.css("top",ultop+"px");
		ulDom.css("left",ulleft+"px");
		return this;
	}
	$.fn.initListData=function(){
		var listdata = $(this).data("option").data;
		var UlDom=$("body").find("#"+ulid);
		UlDom.html("");
		var html = "";
		if(listdata.length!=0){
			for(var i=0;i<listdata.length;i++){
				html+="<li data-value='"+listdata[i].value+"' style='height:"+option.listlineheight+"px;line-height: "+option.listlineheight+"px'>"+listdata[i].text+"</li>";
			}
		}
		UlDom.html(html);
		//须引入niceScroll
		UlDom.niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "5px",
			cursorborder: "0px solid #fff",
			autohidemode:false
		});
		return this;
	}
	$.fn.initSeletedPlug=function(){
		var divDom = $(this);
		var IptDom=$(this).find(".selectInput");
		var IconDom=$(this).find(".selIconfont");
		var UlDom=$("body").find("#"+ulid);
		IptDom.bind("click",Method["showOrHide"]);
		IconDom.bind("click",Method["showOrHide"]);
		UlDom.find("li").bind("click",function(){
			var ipt=$("#"+$(this).parent().attr("iptid"));
			var icon=$("#"+$(this).parent().attr("iconid"));
			ipt.val($(this).text()).attr("data-selvalue",$(this).attr("data-value"));
			$(this).parent().hide();
			icon.html("&#xe601;");
			$(this).addClass("curentdata");
			$(this).siblings(".curentdata").removeClass("curentdata");
			divDom.data("option").eachLiClick($(this).index(),$(this).text(),$(this).attr("data-value"));
		});
		//设置初值
		var index=option.selindex;
		if(index<0||index>UlDom.find("li").length){
			index=0;
		}
		selectFirstSel(UlDom,index);
		return this;
	}
	//获取已选中的下拉框值
	$.fn.getSeletedValue=function(){
		return $(this).find("input.selectInput").attr("data-selvalue");
	}
	//获取已选中的下拉框文本
	$.fn.getSeletedText=function(){
		return $(this).find("input.selectInput").val();
	}
	//根据所传下标锁定选中项
	$.fn.setSeletedWithIndex=function(index){
		var pid = $(this).attr("pid");
		var UlDom=$("body").find("#sel_ul"+pid);
		var i=index;
		if(i<0||i>UlDom.find("li").length){
			i=0;
		}
		selectFirstSel(UlDom,i);
	}
	//根据所传值锁定选中项
	$.fn.setSeletedWithValue=function(selvalue){
		var pid = $(this).attr("pid");
		var UlDom=$("body").find("#sel_ul"+pid);
		var i=UlDom.find("li[data-value='"+selvalue+"']").index();
		if(i<0||i>UlDom.find("li").length){
			i=0;
		}
		selectFirstSel(UlDom,i);
	}
	//重新加载下拉列表数据
	$.fn.reloadListData=function(data){
		var divDom = $(this);
		var UlDom=$("body").find("#"+ulid);
		UlDom.html("");
		var html = "";
		var listdata = data;
		if(listdata.length!=0){
			for(var i=0;i<listdata.length;i++){
				html+="<li data-value='"+listdata[i].value+"' style='height:"+option.listlineheight+"px;line-height: "+option.listlineheight+"px'>"+listdata[i].text+"</li>";
			}
		}
		UlDom.html(html);
		//须引入niceScroll
		UlDom.niceScroll({
			cursorcolor : "#00AFFF",
			cursorborderradius: "0px",
			cursorwidth: "5px",
			cursorborder: "0px solid #fff",
			autohidemode:false
		});
		//重新绑定事件
		UlDom.find("li").bind("click",function(){
			var ipt=$("#"+$(this).parent().attr("iptid"));
			var icon=$("#"+$(this).parent().attr("iconid"));
			ipt.val($(this).text()).attr("data-selvalue",$(this).attr("data-value"));
			$(this).parent().hide();
			icon.html("&#xe601;");
			$(this).addClass("curentdata");
			$(this).siblings(".curentdata").removeClass("curentdata");
			divDom.data("option").eachLiClick($(this).index(),$(this).text(),$(this).attr("data-value"));
		});
		selectFirstSel(UlDom,0);
		return this;
	}
	var Method={
		"showOrHide":function(){
			var uldom=$("body").find("#"+$(this).attr("ulid"));
			if(uldom.is(":hidden")){
				if(uldom.find("li").length>0&&option.data.length>0){
					$(this).parent().find("i").html("&#xe600;");
					uldom.show();
				}
			}else{
				$(this).parent().find("i").html("&#xe601;");
				uldom.hide();
			}
		}
	};
	//选中初值
	var selectFirstSel=function(ele,index){
		var this_sel=ele.find("li").eq(index);
		var ipt=$("#"+ele.attr("iptid"));
		ipt.val(this_sel.text()).attr("data-selvalue",this_sel.attr("data-value"));
		ele.hide();
		this_sel.addClass("curentdata");
		this_sel.siblings(".curentdata").removeClass("curentdata");
	}
	function S4(){
		return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
	}
	function guid(){
		return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
	}
})(jQuery);