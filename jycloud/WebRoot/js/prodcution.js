//删除作品标签
function deleteLabel(obj){
	$(obj).parents("li").remove();
	/*var LABEL_ID = $(obj).parents("li").attr("data-labelId");
	 $.ajax({
		 url:"operateWorkLabel.do",
		 type:"post",
		 dataType:"text",
		 data:{"Workid":operWorkId,"LABEL_ID":LABEL_ID,"operate":"delete"}, 
		 success:function(data){
			 if(data==1){
				 $(obj).parents("li").remove();
			 };
		 }
	 });*/
	
}

//添加作品标签
function addLabel(){
	if(audit==1){
		return;
	};
	//var lbarr = getNowLable();
	$.post("getLabelList.do",{"Workid":operWorkId}, function(result){
		var data = JSON.parse(result);
		var inputhtml = "<div class='addnewlbdiv'>" +
						"<div class='titlediv'>添加新标签</div>"+
						"<input id='iptNewLableName' type='text' name='title' placeholder='请输入标签名称' autocomplete='off' class='layui-input'/>"+
						"<input id='iptNewLableDesc' type='text' name='title' placeholder='请输入标签描述' autocomplete='off' class='layui-input'/>"+
						"<button class='layui-btn layui-btn-normal'  onclick='addNewLable()'>添加</button>"+
						"</div>";
		var html = "<form class='layui-form'><div class='checkboxdiv'><div class='titlediv'>选择标签</div><div id='divChoseLable' class='choselbdiv'>";
		if(data.length!=0){
			for(var i=0;i<data.length;i++){   
				if(data[i].hasit){
					html += "<input type='checkbox' name='checkpglable' value='"+data[i].LABEL_ID+"' title='"+data[i].LABEL_NAME+"' checked>";
				}else{
					html += "<input type='checkbox' name='checkpglable' value='"+data[i].LABEL_ID+"' title='"+data[i].LABEL_NAME+"'>";
				}
			};
		};
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
				$("input[name='checkpglable']:checked").each(function(i,e){
					var obj = {"LABEL_ID":$(e).val(),"LABEL_NAME":$(e).attr("title")};
					arr.push(obj);
				});
				reloadPgLable(arr);
				layer.close(index);
			},
			success: function(layero, index){
			    form.render();
			},
		});
	});
	
};


function reloadPgLable(data){
  var str="";
  for(var i=0;i<data.length;i++){
  	 str+="<li data-labelId='"+data[i].LABEL_ID+"'><div class='worksLabel'><span>"+data[i].LABEL_NAME+"</span><i class='layui-icon close-icon' onclick='deleteLabel(this)'>&#x1007;</i></div></li>";
  };
  $("#ulworksLabels").html(str); 
};

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
 * 添加新标签
 */
function addNewLable(){
	var LabelName = $("#iptNewLableName").val();  
	var LabelDesc = $("#iptNewLableDesc").val();
	$.ajax({
		url:"addDefinedLabel.do",
		type:"post",
		dataType:"json",
		data:{"LabelName":LabelName,"LabelDesc":LabelDesc},
		success:function(data){
			if(data.success == false){
				layuiLayerMsg(data.message);
			}else if(data.success == true){
				layuiLayerMsg("保存成功！");
				var label_id = data.label_id;
				var newipthtml = "<input type='checkbox' name='checkpglable' value='"+label_id+"' title='"+LabelName+"' checked>";
				$("#divChoseLable").append(newipthtml);
				form.render();
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) { 
			layuiLayerMsg("服务器出现错误,错误信息:"+XMLHttpRequest.responseText);
		} 
	});
}


//获取作品标签
function getLabelList(){
	var LabelName = $("#LabelName").val();
	$.ajax({
		url:"getLabelList.do",
		type:"post",
		dataType:"json",
		data:{"Workid":operWorkId,"LabelName":LabelName},
		success:function(data){
			if(data!=null&&data.length!=0){
				var str="<form class='layui-form' id='layui-form' action=''>";
				for(var i=0;i<data.length;i++){
					if(data[i].hasit){
						str+="<input type='checkbox' name='"+data[i].LABEL_ID+"' title='"+data[i].LABEL_NAME+"' checked>";
					}else{
						str+="<input type='checkbox' name='"+data[i].LABEL_ID+"' title='"+data[i].LABEL_NAME+"' >";
					};
				};
				str+="</form>";
				$("#divLabels").html(str);
				form.render();
				
				form.on('checkbox', function(data){
					var operate;
					 if(data.elem.checked){
						 operate="add";
					 }else{
						 operate="delete";
					 };
					 var LABEL_NAME=data.elem.title;
					 var LABEL_ID=data.elem.name;
					 $.ajax({
						 url:"operateWorkLabel.do",
						 type:"post",
						 dataType:"text",
						 data:{"Workid":operWorkId,"LABEL_ID":LABEL_ID,"operate":operate}, 
						 success:function(data){
							 if(data==1){
								 if(operate=="add"){
									 var str="<li data-labelId='"+LABEL_ID+"'><div class='worksLabel'><span>"+LABEL_NAME+"</span><i class='layui-icon close-icon' onclick='deleteLabel(this)'>&#x1007;</i></div></li>";
									 $("#ulworksLabels").append(str);
								 }else{
									 $("#ulworksLabels").find("li[data-labelId='"+LABEL_ID+"']").remove(); 
								 }
							 };
						 }
					 });
				});
			};
		}
	});
}


//添加其他标签
function addOtherLabel(){
	layer.prompt({title: '请输入标签名称',offset: [(($(window).height()-270)/2+40)+"px", '100px'],value:"example0",id:"definedLabel",formType: 0}, function(text, index){
		var LabelName = $("#definedLabelName").val().trim();
		var LabelDesc = $("#definedLabelName").val().trim();
		if(LabelName==""){
			layuiLayerMsg("标签名称不能为空");
			return;
		};
		$.ajax({ 
			url:"addDefinedLabel.do", 
			type:"post",
			dataType:"json",
			data:{"LabelName":LabelName,"LabelDesc":LabelDesc,"Workid":operWorkId},
			success:function(data){
					if(data!=null&&data.success==true){
						$("#layui-form").append("<input type='checkbox' name='"+data.label_id+"' title='"+LabelName+"' checked>");
						form.render();
						var str="<li data-labelId='"+data.label_id+"'><div class='worksLabel'><span>"+LabelName+"</span><i class='layui-icon close-icon' onclick='deleteLabel(this)'>&#x1007;</i></div></li>";
						$("#ulworksLabels").append(str);
						 layuiLayerMsg("操作成功！！！");
						 layer.close(index);
					}else if(data.message!=null){
						layuiLayerMsg(data.message);
					}else{
						layuiLayerMsg("请重试");
					}
				},
			});
	});
	
	$("#definedLabel").html("<input type='text' id='definedLabelName' placeholder='标签名称' value='' class='layui-layer-input taginputclass'>" +
	"<input type='text' id='definedLabelDesc' placeholder='标签描述' value='' class='layui-layer-input taginputclass'>");
}



/**
 * 弹出提示
 * @param msgstr
 */
function layuiLayerMsg(msgstr){
	layer.msg(msgstr,{skin: "layer-msg",time:800,shade:[0.5 , "#000" , true]});
}
