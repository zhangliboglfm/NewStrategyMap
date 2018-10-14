/*左侧导航隐藏显示*/
	function switchleftnav(obj){
		var status =$(obj).attr("data-status");
		if(status == 1){
			$(obj).attr("data-status","0");
			$(obj).find("i").html("&#xe658;");
			$(".left").velocity({left:-120},{duration:1000,begin:function(){
				$(".main .bottom").velocity({width:($(".main").width()-2)},{duration:1000});
				$(".switchBottom").velocity({right:($(".main").width()-102)/2},{duration:1000});
				$(".queryresultdiv").velocity({left:22},{duration:1000});
			}});
		}else{
			$(obj).attr("data-status","1");
			$(obj).find("i").html("&#xe659;");
			$(".left").velocity({left:0},{duration:1000,begin:function(){
				$(".main .bottom").velocity({width:($(".main").width()-122)},{duration:1000});
				$(".switchBottom").velocity({right:($(".main").width()-222)/2},{duration:1000});
				$(".queryresultdiv").velocity({left:142},{duration:1000});
			}});
		}
	}
	/*底部指标栏隐藏显示*/
	function switchBottom(obj){
		var status =$(obj).attr("data-status");
		if(status == 1){
			$(obj).attr("data-status","0");
			$(obj).find("i").html("&#xe62d;");
			$(".bottom").velocity({bottom:-250},{duration:1000});
		}else{
			$(obj).attr("data-status","1");
			$(obj).find("i").html("&#xe62e;");
			$(".bottom").velocity({bottom:0},{duration:1000});
		}
	}
	
	/*切换不同的专题*/
	function ChangeSubject(obj){
		$(".leftnavcur").removeClass("leftnavcur");
		console.log($(this));
		$(obj).addClass("leftnavcur");
	}
	
	/*切换不同的网格类型*/
	function changeGridType(obj){
		$(".spanpoint").removeClass("curspanpoint");
		$(obj).find(".spanpoint").addClass("curspanpoint");
		/*获取网格类型*/
		var gridType =$(obj).attr("data-gridType");
	}