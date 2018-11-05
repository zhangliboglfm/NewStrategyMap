/* miapsoft.regiontree
-- version 1.2 BETA2
-- copyright 2016-11-03 
-- auther 李杰
v1.1
新增初始化值方法
*/
(function(jQuery){
	var RegionBulder = function (ele,options){
		this.target = ele;
		this.defaults = {
			url : "",
			value : "",
			name : "",
			lvl : 0,
			maxlvl : 3,
			trigger : "",
			defval: "",
			titles : [ "当前选择区域", "选择地市", "选择县区" ],
			select: function(value){
				
			}
		};
		this.opts=$.extend({},this.defaults,options);
		var opts = this.opts;
		this.input = $("<div class=\"showText\"><label>"+opts.name+"</label></div>");
		if(this.opts.trigger==""){
			this.imgbtn = $("<span class=\"trgger_span\"></span>");
		}else{
			this.imgbtn = $("#"+opts.trigger);
		}		
		this.target.data("regiontree",{value:opts.value,name:opts.name,lvl:$.type(opts.lvl)=="string"?parseInt(opts.lvl):opts.lvl,defval:opts.defval==undefined||opts.defval==""?opts.value:opts.defval});
		var time = new Date().getTime();
		this.time = time;
		this.popwin = $("<div class='regiondetil' id='regiondetil"+time+"'></div>");
	};
	RegionBulder.prototype = {
		autohide:function(){//点击弹出层外，隐藏弹出层
			var popwin = this.popwin;
			function hidePopwin(){
				popwin.hide();		
			}
			$(document).bind("mousedown", hidePopwin);
			$("iframe").each(function(i,element){
				$(element.contentDocument).bind("mousedown",hidePopwin);
			});	
			popwin.mouseover(function(){
				$(document).unbind("mousedown");
				$("iframe").each(function(i,element){
					$(element.contentDocument).unbind("mousedown");		
				});	
			}).mouseout(function(){
				$(document).bind("mousedown", hidePopwin);
				$("iframe").each(function(i,element){
					$(element.contentDocument).bind("mousedown", hidePopwin);
				});						
			});
		},	
		bindEvent:function(){//绑定事件
			var imgbtn = this.imgbtn;
			var popwin = this.popwin;
			imgbtn.click(function(){	
				if(!popwin.is(":visible")){				
					popwin.show();
				}
			});
			this.autohide();
		},createTree:function (){
			var regionBulder = this;
			var opts = this.opts;
			var input = this.input;
			var target = this.target;
			var popwin = this.popwin;
			var regiondata = target.data("regiontree");
			
			var left = target.offset().left;
			var top = target.offset().top+26;
			$("body").append(popwin);
			popwin.css({"top":top+"px","left":left+"px"});	
			var con="<div class='backg01'></div>" +
				"<div class='backg02'>" +
				"<div class='regiondetil_selectclass'><div style='float:left;'>"+opts.titles[0]+"：</div>"+
				/*"<div id='regiondetil_close' class='regiondetil_close'>关闭</div></div>"+*/
				"<div class='regiondetil_selectclass'><div class='select_path' id='select_path"+regionBulder.time+"'>"+opts.name+"</div></div></div>";
			popwin.append(con);
			for(var i=opts.lvl;i<opts.maxlvl;i++){
				popwin.find(".backg02").append("<div class='regiondetil_selectclass' id='regiontree_"+regionBulder.time+i+"'></div>");
			}
			remoteQequstData("选择地市"+regiondata.lvl,"regiontree_"+regionBulder.time+regiondata.lvl);
			popwin.append("<div class='backg03'></div>");	
			if(opts.defval!=opts.value){
				var vals = regiondata.defval.split(",");
				for(var i=1;i<vals.length;i++){
					if(i==vals.length-1){
						popwin.find("div[regionId='"+vals[i]+"']").click();
					}else{
						regionStyle(popwin.find("div[regionId='"+vals[i]+"']"));
					}
				}
			}
			function remoteQequstData(desc,Idcity){// 获取远程数据
				var regiondata = target.data("regiontree");
				if(regiondata.name=="全部"){
					$("#"+Idcity).hide();
					return;
				}
				var url = opts.url;
				if(url==""){
					return;
				}
				var data = {
					regionId: regiondata.value,
					lvl: regiondata.lvl
				};
				var reqtype = "get";
				if(url.indexOf("?")==-1){
					reqtype = "post";
				}
				$.ajax({
					url: url,
					type: reqtype,
					data: data,
					dataType: "json",
					async: false,
					success:function(data){
						createregion(opts.titles[regiondata.lvl],Idcity,data.data);
					},
					error:function(error){
						
					}
				});
			}
			function createregion(desc,Idcity,regionlist){	//生成子tree	
				var regiondata = target.data("regiontree");
				var con="<div style='margin-top:4px;height:25px;'>"+desc+" </div><hr style=\"margin:0px;\"><div regionId='"+regiondata.value+"' lvl='"+(regiondata.lvl+1)+"' class='regionname1 regionname2'>全部</div>";
				for(var i=0;i<regionlist.length;i++){
					var obj=regionlist[i];
					con+="<div regionId='"+obj.id+"' lvl='"+obj.lvl+"' class='regionname1' title='"+obj.name+"' >"+obj.name+"</div>";
				}
				$("#"+Idcity).html(con).show();
				popwin.find(".regionname1").click(function(){
					$(this).siblings().removeClass("regionname2");
					$(this).addClass("regionname2");
					var lvl = parseInt($(this).attr("lvl"));
					var value = $(this).attr("regionId");
					var name = $(this).text();
					var path = opts.name;
					var defval = opts.value;
					for(var i=opts.lvl;i<lvl;i++){
						var text = popwin.find("div[lvl='"+(i+1)+"'].regionname2").text();
						var val = popwin.find("div[lvl='"+(i+1)+"'].regionname2").attr("regionId");
						if(i==lvl-1){
							if(text!="全部"){
								path += "--"+text;
								defval += ","+val;
							}
						}else{
							path += "--"+text;
							defval += ","+val;
						}			
					}
					$("#select_path"+regionBulder.time).text(path);
					var select = path.split("--").reverse()[0];
					input.find("label").text(path);
					target.data("regiontree",{value:value,name:name,lvl:lvl,defval:defval});
					if(lvl<opts.maxlvl){
						remoteQequstData("选择地市"+lvl,"regiontree_"+regionBulder.time+lvl);
					}
					//临时添加
					popwin.hide();
					opts.select(value);
				});
			}
			function regionStyle(e){//赋值样式选中
				var regiondata = target.data("regiontree");
				e.siblings().removeClass("regionname2");
				e.addClass("regionname2");
				var lvl = parseInt(e.attr("lvl"));
				var value = e.attr("regionId");
				var name = e.text();
				var path = opts.name;
				for(var i=opts.lvl;i<lvl;i++){
					var text = popwin.find("div[lvl='"+(i+1)+"'].regionname2").text();
					var val = popwin.find("div[lvl='"+(i+1)+"'].regionname2").attr("regionId");
					if(i==lvl-1){
						if(text!="全部"){
							path += "--"+text;
							defval += ","+val;
						}
					}else{
						path += "--"+text;
						defval += ","+val;
					}			
				}
				$("#select_path"+regionBulder.time).text(path);
				var select = path.split("--").reverse()[0];
				input.find("label").text(path);
				target.data("regiontree",{value:value,name:name,lvl:lvl,defval:defval});
				if(lvl<opts.maxlvl){
					remoteQequstData("选择地市"+lvl,"regiontree_"+regionBulder.time+lvl);
				}
			}
		}
	};
	$.fn.regiontree = function(options){
		var regionBulder = new RegionBulder($(this),options);
		regionBulder.target.append(regionBulder.input);
		regionBulder.input.append(regionBulder.imgbtn);
		regionBulder.createTree();
		regionBulder.bindEvent();
	};	
})( jQuery );