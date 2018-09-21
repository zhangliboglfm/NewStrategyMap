
function IcontRadio(e){
	$(e).siblings().find("i").html("&#xe658;");
	$(e).find("i").html("&#xe632;");
	$(e).attr("data-seledcted","true");
	$(e).siblings().attr("data-seledcted","false");
}

function IcontRadio1(e){
	$(e).siblings().find("i").html("&#xe646;");
	$(e).find("i").html("&#xe630;");
	$(e).attr("data-seledcted","true");
	$(e).siblings().attr("data-seledcted","false");
}

function IcontCheck(e){
	var checktype = $(e).attr("data-checktype");
	if(checktype == "all"){
		if($(e).attr("data-checked") == "true"){
			$(e).find("i").html("&#xe658;");
			$("."+$(e).attr("data-check")).find("i").html("&#xe658;");
			$("."+$(e).attr("data-check")).attr("data-checked","false");
			$(e).attr("data-checked","false");
		}else if($(e).attr("data-checked") == "false"){
			$(e).find("i").html("&#xe632;;");
			$("."+$(e).attr("data-check")).find("i").html("&#xe632;");
			$("."+$(e).attr("data-check")).attr("data-checked","true");
			$(e).attr("data-checked","true");
		}
	}else if(checktype == "one"){
		if($(e).attr("data-checked") == "true"){
			$(e).find("i").html("&#xe658;");
			$(e).attr("data-checked","false");
		}else if($(e).attr("data-checked") == "false"){
			$(e).find("i").html("&#xe632;");
			$(e).attr("data-checked","true");
		}
		if($("."+$(e).attr("class")+"[data-checked='true']").length == $("."+$(e).attr("class")).length){
			$(".all_"+$(e).attr("class")).attr("data-checked","true");
			$(".all_"+$(e).attr("class")).find("i").html("&#xe632;");
		}else{
			$(".all_"+$(e).attr("class")).attr("data-checked","false");
			$(".all_"+$(e).attr("class")).find("i").html("&#xe658;");
		}
	}
}