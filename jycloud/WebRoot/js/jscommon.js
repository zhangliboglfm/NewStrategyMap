//ajax调取方法
function ajaxRequest(url, parameter, callback) {
	jQuery.support.cors = true;
	$.ajax({
		url : url,
		type : "post",
		data : parameter,
		dataType : "json",
		success : function(data) {
			callback(data);
		}
	});
}

function ajaxRequestByMethod(url, parameter, dataType, callback) {
	jQuery.support.cors = true;
	$.ajax({
		url : url,
		type : "post",
		data : parameter,
		dataType : dataType,
		success : function(data) {
			callback(data);
		}
	});
}

function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null; 
}

function GetRequest() { 
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
	} 
	return theRequest; 
}
function GetRequest(url) { 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
		var str = url.substr(1); 
		strs = str.split("&"); 
		for(var i = 0; i < strs.length; i ++) { 
			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
		} 
	} 
	return theRequest; 
}

function cuttingword(src,length){
	if(src.length>length){
		src=src.substr(0,length)+"...";
	}
	return src;
}

//添加或者修改json数据
function setJson(jsonStr,name,value)
{
    if(!jsonStr)jsonStr="{}";
    var jsonObj = JSON.parse(jsonStr);
    jsonObj[name] = value;
    return JSON.stringify(jsonObj);
}

//加载页面
function loadhtml(url,targetId){
	$.ajax({
		type:"post",
		url:url,
		success:function(html){
			$("#"+targetId).html(html);
		}
	});
}
//更多跳转
function gotomore(url){
	$.each($(".nav"),function(){
		var thisUrl = $(this).attr("o-url");
		if(url.indexOf(thisUrl)!=-1){
			$(this).click();
			return false;
		}
	});
}

function mouseover(obj){
	$(obj).addClass("mouseover");
}
function mouseout(obj){
	$(obj).removeClass("mouseover");
}

//写cookies 

function setCookie(name,value) 
{ 
    var Days = 30; 
    var exp = new Date(); 
    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
} 

//读取cookies 
function getCookie(name) 
{ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg))
 
        return unescape(arr[2]); 
    else 
        return ""; 
} 

//删除cookies 
function delCookie(name) 
{ 
    var exp = new Date(); 
    exp.setTime(exp.getTime() - 1); 
    var cval=getCookie(name); 
    if(cval!=null) 
        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
} 
//使用示例 
//setCookie("name","hayden"); 
//alert(getCookie("name"));