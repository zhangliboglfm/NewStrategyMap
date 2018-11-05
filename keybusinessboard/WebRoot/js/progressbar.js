
$(function(){
	var a="red";//进度条颜色
	var b="blue";//背景颜色
	var w="200px";
	var h="15px";
	var div=$(".div");
	var barb=function(){
		div.each(function(){
			var width=$(this).attr('w');
			var barbox='<dl class="barbox"><dd class="barline"><div w="'+width+'" class="charts" style="width:0px"><d></d></div></dd></dl>';
			$(this).append(barbox);
		})
	}
	
	var amimeat=function(){
		$(".charts").each(function(i,item){
			var wi=parseInt($(this).attr("w"));
			$(item).animate({width: wi+"%"},1000,function(){//width部分进度条长度
				$(this).children('d').html(wi+"%");//添加文字(80%)
			});
		});
	}
	var barbCss=function(a,b){
		$(".barbox").css({
			"height":h,
			"width":w,
			"line-height":h,
			"text-align":"center",
			"color":"#fff",
		})
		$(".barbox>dd").css({
			"float":"left"
		})	
		$(".barline").css({
			"width":w,
			"background":b,
			"height":h,
			"overflow":"hidden",
			"display":"inline",
			"position":"relative",
			"border-radius":"8px",
		})
		$(".barline>d").css({
			"position":"absolute",
			"top":"0px",
		})
		$(".charts").css({
			"background":a,
			"height":h,
			"width":"0px",
			"overflow":"hidden",
			"border-radius":"8px"
		})
	}
	barb();
	amimeat();
	barbCss(a,b);
})

