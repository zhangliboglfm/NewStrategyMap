/**
 * 重新加载摄影家标签
 * @param data
 */
function reloadPgLable(data){
	$("#divPgLable").html("");
	if(data.lenght!=0){
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			var lbdom = $("<div id='"+obj.lbId+"' lbname='"+obj.lbName+"' class='pglable'>"+obj.lbName+"<i class='iconfont removelbbnt' title='去掉' onclick='removeLable(this)'>&#xe604;</i></div>");
			$("#divPgLable").append(lbdom);
		}
	}
	$("#divPgLable").append($("<div class='insertlb'><i class='iconfont addlbbnt' title='添加' onclick='addLable()'>&#xe6b7;</i></div>"));
}
/**
 * 移除标签
 */
function removeLable(e){
	$(e).parent().remove();
}
/**
 * 添加标签
 */
function addLable(){
	var lbarr = getNowLable();
	layui.use(['layer','form'], function(){
		layer=layui.layer;
		form=layui.form;
		$.post("getAllArtLabel.do", {}, function(result){
			var data = JSON.parse(result);
			data=data.pLable;
			var inputhtml = "<div class='addnewlbdiv'>" +
							"<div class='titlediv'>添加新标签</div>"+
							"<input id='iptNewLableName' type='text' name='title' placeholder='请输入标签名称' autocomplete='off' class='layui-input'/>"+
							"<input id='iptNewLableDesc' type='text' name='title' placeholder='请输入标签描述' autocomplete='off' class='layui-input'/>"+
							"<button class='layui-btn layui-btn-normal'  onclick='addNewLable()'>添加</button>"+
							"</div>";
			var html = "<form class='layui-form'><div class='checkboxdiv'><div class='titlediv'>选择标签</div><div id='divChoseLable' class='choselbdiv'>";
			if(data.length!=0){
				for(var i=0;i<data.length;i++){
					if(getLableExist(lbarr,data[i].lbName)){
						html += "<input type='checkbox' name='checkpglable' value='"+data[i].lbId+"' title='"+data[i].lbName+"' checked>";
					}else{
						html += "<input type='checkbox' name='checkpglable' value='"+data[i].lbId+"' title='"+data[i].lbName+"'>";
					}
				}
			}
			html += "</div></div></form>";
			layer.open({
				id:"divAddLable",
				title:"添加标签",
				type: 1,
				area: ["500px", "600px;"],
				shade:[0.5 , "#000" , true],
				resize:false,
				btn: ["确定", "取消"],
				btnAlign: "c",
				tipsMore: true,
				content: inputhtml+html,
				yes:function(index, layero){
					var arr = [];
					var lableList=[];
					$("input[name='checkpglable']:checked").each(function(i,e){
						var obj = {"lbId":$(e).val(),"lbName":$(e).attr("title")};
						arr.push(obj);
						lableList.push($(e).val());
					});
					reloadPgLable(arr);
					layer.close(index);
					$.ajax({
						 url:"updateLabel.do",
						 type:"post",
						 dataType:"json",
						 data:{articleId : articleId,arr:JSON.stringify(lableList)},
						 success:function(data){
							 $("#divPgLable").append($("<div class='insertlb'><i class='iconfont addlbbnt' title='添加' onclick='addLable()'>&#xe6b7;</i></div>"));
						 }
					 });
				},
				success: function(layero, index){
				    form.render();
				},
			});
		});
	});
}

/**
 * 添加新标签
 */
function addNewLable(){
	var lablename = $("#iptNewLableName").val();
	var labledesc = $("#iptNewLableDesc").val();
	$.ajax({
		url:"addCustomLabel.do",
		type:"post",
		dataType:"json",
		data:{"labelName":lablename,"labeldesc":labledesc,"articleId":articleId},
		success:function(data){
			console.log(data);
			if(data.result == "exception"){
				layuiLayerMsg("保存失败，请联系管理员！");
			}else if(data.result == "fail"){
				layuiLayerMsg("保存失败，请联系管理员！");
			}else if(data.result == "repeat"){
				layuiLayerMsg("数据重复，请勿重复添加！");
			}else if(data.result == "success"){
				layuiLayerMsg("保存成功！");
				var lbId = data.state;
				var newipthtml = "<input type='checkbox' name='checkpglable' value='"+lbId+"' title='"+lablename+"' checked>";
				$("#divChoseLable").append(newipthtml);
				form.render();
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) { 
			layuiLayerMsg("服务器出现错误,错误信息:"+XMLHttpRequest.responseText);
		} 
	});
}

/**
 * 检查标签是否存在
 * @param String
 * @returns
 */
function getLableExist(lbdata,lbname){
	var lbarr = [];
	if(lbdata.length!=0){
		for(var i=0;i<lbdata.length;i++){
			lbarr.push(lbdata[i].lbName);
		}
	}
	if($.inArray(lbname,lbarr)==-1){
		return false;
	}else{
		return true;
	}
}
/**
 * 获取目前所有标签
 */
function getNowLable(){
	var arr = [];
	$("#divPgLable").find(".pglable").each(function(i,e){
		var obj = {"lbId":$(e).attr("id"),"lbName":$(e).attr("lbname")};
		arr.push(obj);
	});
	return arr;
}
/**
 * 弹出提示
 * @param msgstr
 */
function layuiLayerMsg(msgstr){
	layui.use(['layer','form'], function(){
		layer=layui.layer;
		layer.msg(msgstr,{skin: "layer-msg",time:800,shade:[0.5 , "#000" , true]});
	});
}



