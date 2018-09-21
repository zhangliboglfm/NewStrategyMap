/*显示指标池*/
function showIndexPool(){
	var domheight = $(window).height();
	var domwidth = $(window).width();
	var popTop = domheight*0.5-$("#pop_divIndexPool").height()*0.5;
	var popLeft = domwidth*0.5-$("#pop_divIndexPool").width()*0.5;
	$("#pop_divIndexPool").css("top",popTop).css("left",popLeft);
	$("#pop_divIndexPool").show();
	$("#ifrIndexPool").attr("src","indexpool.do?random="+Math.random());
}
/*显示指标池*/
function closeIndexPool(){
	$("#pop_divIndexPool").hide();
}