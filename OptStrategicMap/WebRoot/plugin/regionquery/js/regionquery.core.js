/**
 * <p>Title:miapsoft.selectlist.core.js</p>
 * <p>Description: 地域插件</p>
 * @author:白少华
 * @version:1.3
 * @time:2018-03-20
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2017</p>
 */
(function($){
	var $url;
	var option;
	var $rbox;
	var $rboxLeft;
	var $rboxTop;
	var thisdom;
	$.fn.miapRegion=function(options){
		option = $.extend({
			'queryurl':'',
			'lvl':1,
			'cur_region':'1',
			'cur_regionname':'',
			'clickfn':function(arg){}
		},options);
		if(option.queryurl==''||option.queryurl==undefined||option.queryurl==null){
			throw new Error("未配置后台查询链接，请检查。");
			return false;
		}else{
			$url=option.queryurl;
		}
		$rboxLeft=$(this).offset().left;
		$rboxTop=$(this).offset().top+$(this).height()+20;
		thisdom =$(this);
		$(this).data("option",option);
		$(document).click(function(){
			$(".regionbox").remove();
		});
		if(option.cur_region !=3)
		$(this).bind('click',function(evt){
			LoadRegionBox({});
			var event=evt||window.event;
			/*event.stopPropagation();*/
			if(event.stopPropagation){
				event.stopPropagation();//针对Mozilla和Opera
			}else if(window.event){
				window.event.cancelBubble=true;//针对IE
			}
		});
		if(option.cur_region!=undefined && option.cur_region!=null && option.cur_region!=""){
			if(option.cur_region=="1"){
				bindDomData(option.cur_region,"河北", "");
			}else{
				bindDomData(option.cur_region,option.cur_regionname , "");
			};
		}
	};
	$.fn.setmiapRegion=function(id,name,pname){
		bindDomData(id, name, pname);
	};
	//加载插件弹出框
	var LoadRegionBox=function(opt){
		var bdy=$(document.body);
		if(bdy.find(".regionbox").length>0){
			$(".regionbox").remove();
			$rbox="";
		}
		$rbox=$('<div id="miap_region" class="regionbox"></div>');
		$rbox.css("top",$rboxTop);
		$rbox.css("left",$rboxLeft);
		if(option.lvl!="1"&&option.cur_region!="1"&&option.cur_region!=undefined&&option.cur_region!=null){
			querycountydata($rbox,option.cur_region,option.cur_regionname);
		}else{
			querycitydata($rbox);
		}
		bdy.append($rbox);
		$rbox.show();
	};
	//查询地市
	function querycitydata(dom){
		var citybox=$('<div class="citybox"></div>');
		var cityp=$('<p>地级市</p>');
		citybox.append(cityp);
		var cityul=$('<ul></ul>');
		$.getJSON($url,function(data){
			if(data==undefined||data==null||data==""){
				throw new Error("未查询到数据或数据格式不正确，请检查后台链接。");
				return false;
			}
			var arraydata=data.city; 
			if(arraydata.length>0){
				var aDomLi = $("<li>全部</li>");
				aDomLi.attr("type","all");
				aDomLi.attr("all_name","河北");
				aDomLi.data("regionid",1);
				aDomLi.data("regionlvl",1);
				cityul.append(aDomLi);
				for(var i=0;i<arraydata.length;i++){
					var jsonobj=arraydata[i];
					var li=$('<li>'+jsonobj.regionname+'</li>');
					li.data("regionid",jsonobj.regionid);
					li.data("regionlvl",jsonobj.regionlvl);
					cityul.append(li);
				} 
			}else{
				throw new Error("数据格式不正确，请检查后台链接。");
				return false;
			}
			cityul.find("li").bind('click',function(evt){
				if($(this).attr("type")=="all"){
					initregion($(this),$(this).attr("all_name"),$(this).data("regionid"));
				}else{
					querycountydata(dom,$(this).data("regionid"),$(this).html());
					var event=evt||window.event;
					if(event.stopPropagation){
						event.stopPropagation();//针对Mozilla和Opera
					}else if(window.event){
						window.event.cancelBubble=true;//针对IE
					}
				}
			});
		});
		citybox.append(cityul);
		dom.append(citybox);
	}
	//查询县区
	var querycountydata=function(dom,regionid,parentregionname){
		var countybox;
		var countyul;
		if(dom.find(".countybox").length!=0 && dom.find(".countybox").length ==1){
			countybox = $(".countybox");
			$(".countybox").find("ul").html("");
			countyul = $(".countybox").find("ul").eq(0);
			$.getJSON($url,{'regionId':regionid},function(data){
				if(data==undefined||data==null||data==""){
					throw new Error("未查询到数据或数据格式不正确，请检查后台链接。");
					return false;
				}
				var arraydata=data.county;
				if(arraydata.length>0){
					var aDomLi = $("<li>全部</li>");
					aDomLi.attr("type","all");
					aDomLi.attr("all_name",parentregionname);
					aDomLi.data("regionid",regionid);
					aDomLi.data("regionlvl",2);
					aDomLi.data("parentregionid",1);
					aDomLi.data("parentregionname","河北");
					countyul.append(aDomLi);
					for(var i=0;i<arraydata.length;i++){
						var jsonobj=arraydata[i];
						var li=$('<li>'+jsonobj.regionname+'</li>');
						li.data("regionid",jsonobj.regionid);
						li.data("regionlvl",jsonobj.regionlvl);
						li.data("parentregionid",regionid);
						li.data("parentregionname",parentregionname);
						countyul.append(li);
					}
				}else{
					throw new Error("未查询到数据或数据格式不正确，请检查后台链接。");
					return false;
				}
				countybox.find("ul").find("li").bind('click',function(){
					if($(this).attr("type")=="all"){
						initregion($(this),$(this).attr("all_name"),$(this).data("regionid"));
					}else{
						initregion($(this),$(this).html(),$(this).data("regionid"));
					}
				});
			});
		}else{
			countybox=$('<div class="countybox"></div>');
			var countyp=$('<p>各县区</p>');
			countybox.append(countyp);
			countyul=$('<ul></ul>');
			$.getJSON($url,{'regionId':regionid},function(data){
				var arraydata=data.county;
				if(arraydata.length>0){
					var aDomLi = $("<li>全部</li>");
					aDomLi.attr("type","all");
					aDomLi.attr("all_name",parentregionname);
					aDomLi.data("regionid",regionid);
					aDomLi.data("regionlvl",2);
					aDomLi.data("parentregionid",1);
					aDomLi.data("parentregionname","河北");
					countyul.append(aDomLi);
					for(var i=0;i<arraydata.length;i++){
						var jsonobj=arraydata[i];
						var li=$('<li>'+jsonobj.regionname+'</li>');
						li.data("regionid",jsonobj.regionid);
						li.data("regionlvl",jsonobj.regionlvl);
						li.data("parentregionid",regionid);
						li.data("parentregionname",parentregionname);
						countyul.append(li);
					}
				}
				countybox.find("ul").find("li").bind('click',function(){
					if($(this).attr("type")=="all"){
						initregion($(this),$(this).attr("all_name"),$(this).data("regionid"));
					}else{
						initregion($(this),$(this).html(),$(this).data("regionid"));
					}
				});
			});
			countybox.append(countyul);
		}
		dom.append(countybox);
	};
	//点击地域触发事件
	function initregion(e,regionname,regionid){
		var data={"regionId":regionid,"regionName":regionname,"regionLvl":e.data("regionlvl"),"parent_regionId":e.data("parentregionid"),"parent_regionName":e.data("parentregionname")};
		bindDomData(regionid,regionname,e.data("parentregionname"));
		var clickfn = option["clickfn"];
		clickfn(data);
	}
	//绑定DOM主体数据
	function bindDomData(regionid,regionname,parentregionname){
		if(parentregionname!=undefined && parentregionname!=null && parentregionname!="" && parentregionname!="河北"){
			thisdom.html(parentregionname+"-"+regionname);
		}else{
			thisdom.html(regionname);
		}
		thisdom.data("regionid",regionid);
		thisdom.data("regionname",regionname);
		//thisdom.data("regionData",data);
	}
})(jQuery);