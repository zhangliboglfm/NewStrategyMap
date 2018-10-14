/* miapsoft.autohide
 * description 点击弹出层的区域外，隐藏弹出层
 * version 1.0.0 BETA1
 * copyright 2016-7-15
 * auther 李杰
*/
(function(jQuery){
	var _target,_opts;
	$.fn.autohide = function(options){
		_opts=$.extend({},$.fn.autohide.defaults,options);	
		_target = $(this);
		$(document).bind("mousedown", hidePopwin);
		$("iframe").each(function(i,element){
			$(element.contentDocument).bind("mousedown", hidePopwin);
		});	
		$(this).mouseover(function(){
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
	};
	function hidePopwin(){
		_target.hide();	
		_opts.hide();
	};	
	//  插件的defaults    
	$.fn.autohide.defaults = {
		hide : $.noop
	};
})( jQuery );