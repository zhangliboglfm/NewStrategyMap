/*输入框默认显示文字优化*/
function inputOptimize(e){
	var defaultTxT=$(e).attr("defaulttxt");
	var defaultColor=$(e).css("color");
	$(e).css("color","gray");
	$(e).val(defaultTxT);
	$(e).bind("focus",function(){
		if($(this).val()==defaultTxT){
			$(e).css("color",defaultColor);
			$(e).val("");
		}
	});
	$(e).bind("blur",function(){
		if($(this).val()==""){
			$(e).css("color","gray");
			$(e).val(defaultTxT);
		}
	});
}