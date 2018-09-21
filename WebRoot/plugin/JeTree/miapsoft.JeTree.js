/*插件名称：树插件--仅支持2级菜单
依赖：JQuery IConFont图标
作者：白少华
版本：V1.0*/
(function(jQuery){
	
	var opts;
	var target,isIconFont,IconFontCode,IconFontStyle;
	var hoverstyle,parstyle,chnstyle,clfun;
	var opensetting;
	var tdata,isdiy;
	
	$.fn.JeTree = function(options){
		opts = $.extend({},$.fn.JeTree.defaults,options);
		target = $(this).attr("id") != undefined ? $(this).attr("id") : $.fn.JeTree.defaults.target;
		opensetting = opts.selectjson != undefined ? opts.selectjson : $.fn.JeTree.defaults.selectjson;;
		isIconFont = opts.isIconFont.isopen != undefined ? opts.isIconFont.isopen : $.fn.JeTree.defaults.isIconFont.isopen;
		IconFontCode = opts.isIconFont.iconfontcode != undefined ? opts.isIconFont.iconfontcode : $.fn.JeTree.defaults.isIconFont.iconfontcode;
		IconFontStyle = opts.isIconFont.iconstyle != undefined ? opts.isIconFont.iconstyle : $.fn.JeTree.defaults.isIconFont.iconstyle;
		tdata = opts.treedata;
		hoverstyle = opts.hovercolor != undefined ? opts.hovercolor : $.fn.JeTree.defaults.hovercolor;
		parstyle = opts.style.parstyle != undefined ? opts.style.parstyle : $.fn.JeTree.defaults.style.parstyle;
		chnstyle = opts.style.chnstyle != undefined ? opts.style.chnstyle : $.fn.JeTree.defaults.style.chnstyle;
		clfun = opts.clickfun;
		isdiy = opts.isdiyattribute;
		inittree(tdata);
	};
	
	var inittree = function(treedata){
		for ( var i = 0; i < treedata.length; i++) {
			var ptreeobj = treedata[i];
			var navdiv = $("<div id='parentree"+i+"' class='pTree'></div>");
			//设置自定义样式
			setstyle(navdiv,parstyle);
			navdiv.append("<span>"+ptreeobj.titlename+"</span>");
			$("#"+target).append(navdiv);
			binddiydata(navdiv,ptreeobj.diydata);
			//加载子菜单
			if(ptreeobj.treetype == "catalog"){
				navdiv.bind("click",opentree);
				var chndiv = $("<div id='childtree"+i+"' class='cTree'></div>");
				navdiv.attr("data-childid","childtree"+i+"");
				if(isIconFont){
					var iconobj = $("<i class='iconfont icon'>"+IconFontCode.down+"</i>");
					iconobj.css("color",IconFontStyle.color);
					iconobj.css("font-size",IconFontStyle.fontsize+"px");
					navdiv.append(iconobj);
				}
				var cdata = ptreeobj.childdata;
				for ( var j = 0; j < cdata.length; j++) {
					var ctreeobj = cdata[j];
					var detail = $("<div class='detail'><span>"+ctreeobj.titlename+"</span></div>")
					detail.attr("url",ctreeobj.treeurl);
					binddiydata(detail,ctreeobj.diydata);
					detail.bind("click",clicktree);
					//设置自定义样式
					setstyle(detail,chnstyle);
					chndiv.append(detail);
				}
				$("#"+target).append(chndiv);
			}else{
				navdiv.attr("url",ptreeobj.treeurl);
				navdiv.bind("click",clicktree);
			}
		}
		if(opensetting.isselect){
			if(opensetting.parentindex != null){
				$("#parentree"+opensetting.parentindex).next().show();
				$("#parentree"+opensetting.parentindex).find(".icon").html(IconFontCode.up);
				if(opensetting.chlidrenindex != null){
					var curtreeobj = $("#parentree"+opensetting.parentindex).next().find(".detail:eq("+opensetting.chlidrenindex+")");
					/*curtreeobj.css("background-color",hoverstyle[1]);*/
					sethoverstyle(curtreeobj,hoverstyle[1]);
				}
			}
		}
	};
	//展开事件
	var opentree = function(){
		var childid = $(this).attr("data-childid");
		if(childid!=undefined){
			if($("#"+childid).is(":hidden")){
				$("#"+childid).show();
				$(this).find(".icon").html(IconFontCode.up);
			}else{
				$("#"+childid).hide();
				$(this).find(".icon").html(IconFontCode.down);
			}
		}
	}
	//点击事件
	var clicktree = function(){
		clfun($(this));
	}
	//设置样式
	function setstyle(object,styledata){
		object.css("width",styledata.width+"px");
		object.css("height",styledata.height+"px");
		object.css("line-height",styledata.lineheight+"px");
		object.css("font-family",styledata.fontfamily);
		object.css("font-size",styledata.fontsize+"px");
		if(styledata.fontstyle == "bold"){
			object.css("font-weight",styledata.fontstyle);
		}else{
			object.css("font-style",styledata.fontstyle);
		}
		object.css("text-indent",styledata.textindent);
		object.css("background-color",styledata.backgroundcolor);
		object.css("color",styledata.color);
		object.hover(function(){
			sethoverstyle($(this),hoverstyle[1]);
		},function(){
			sethoverstyle($(this),hoverstyle[0]);
		});
	}
	//设置鼠标移动样式
	function sethoverstyle(object,styledata){
		object.css("background-color",styledata.backgroundcolor);
		object.css("color",styledata.color);
	}
	//绑定自定义属性
	function binddiydata(object,DIYdata){
		if(isdiy){
			var data = DIYdata;
			if(data != undefined && data != null && data != ""){
				for ( var atbname in data) {
					object.attr(atbname,data[atbname]);
				}
			}
		}
	}
	$.fn.JeTree.defaults = {
		target:"NavBar",
		selectjson:{
			isselect:false,
			parentindex:null,
			chlidrenindex:null
		},
		isIconFont:{
			isopen:false,
			iconfontcode:{
				down:"",
				up:""
			},
			iconstyle:{
				color:"",
				fontsize:""
			}
		},
		isdiyattribute:false,
		hovercolor:[{color:"",backgroundcolor:""},{color:"",backgroundcolor:""}],
		style:{
			parstyle:{
				width:"",
				height:"",
				lineheight:"",
				fontsize:14,
				fontfamily:"",
				fontstyle:"",
				textindent:"",
				backgroundcolor:"",
				color:""
			},
			chnstyle:{
				width:"",
				height:"",
				lineheight:"",
				fontsize:12,
				fontfamily:"",
				fontstyle:"",
				textindent:"",
				backgroundcolor:"",
				color:""
			}
		},
		data:[{
				titlename:"",
				treeurl:"",
				treetype:"",
				childdata:[{
					titlename:"",
					treeurl:"",
					treetype:"",
					diydata:{}
				}],
				diydata:{}
			}],
		clickfun:function(e){}
	};
})(jQuery);