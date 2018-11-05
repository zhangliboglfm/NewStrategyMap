/**
 * 获取摄影家信息
 * @param pgid
 */
function getPgData(pgid){
	$.ajax({
		url:"getpgbaseinfo.do",
		type:"post",
		dataType:"json",
		data:{"pgId":pgid},
		success:function(data){
			var obj={"pName":data.pName,"pSex":data.pSex,"pNation":data.pNation,"pNationId":data.pNationId,"pYears":data.pYears,
					"pBirthYears":data.pBirthYears,"pDeadYears":data.pDeadYears,"pDealTime":data.pDealTime,"pAuditState":data.pAuditState,
					"pAuditResult":data.pAuditResult};
			setPgData(obj);
			pgobj = obj;
			reloadPgHeadImage(data.pHeadImage);
			pgheadimgname = data.pHeadImage;
			reloadPgLable(data.pLable);
			pglable = data.pLable;
		}
	});
}

/**
 * 设置摄影家信息
 * @param obj
 */
function setPgData(obj){
	var pgname = obj.pName;
	var pgsex = obj.pSex;
	var pgnation = obj.pNation;
	var pgnationid = obj.pNationId;
	var pgyears = obj.pYears;
	var pgbirthyears = obj.pBirthYears;
	var pgdeadyears = obj.pDeadYears;
	var pgdealtime = obj.pDealTime;
	var pgauditstate = obj.pAuditState;
	var pgauditresult = obj.pAuditResult;
	$("#iptPgName").val(pgname);
	$("#iptPgName").attr("defaultVal",pgname);
	$("#iptPgSex").val(pgsex);
	$("#iptPgSex").attr("defaultVal",pgsex);
	$("#iptPgNation").val(pgnation);
	$("#iptPgNation").attr("defaultVal",pgnation);
	$("#iptPgNation").attr("pgnationid",pgnationid);
	$("#iptPgYears").val(pgyears);
	$("#iptPgYears").attr("defaultVal",pgyears);
	$("#iptPgBirthYears").val(pgbirthyears);
	$("#iptPgBirthYears").attr("defaultVal",pgbirthyears);
	$("#iptPgDeadYears").val(pgdeadyears);
	$("#iptPgDeadYears").attr("defaultVal",pgdeadyears);
	$("#iptPgDealTime").val(pgdealtime);
	$("#iptPgDealTime").attr("defaultVal",pgdealtime);
	$("#iptPgAuditState").val(pgauditstate);
	$("#iptPgAuditState").attr("defaultVal",pgauditstate);
	$("#tareaAuditResult").val(pgauditresult);
}

/**
 * 恢复原来的值
 */
function recoveryPgData(){
	$("#iptPgName").val($("#iptPgName").attr("defaultVal"));
	$("iptPgSex").val($("#iptPgSex").attr("defaultVal"));
	$("#iptPgNation").val($("#iptPgNation").attr("defaultVal"));
	$("#iptPgYears").val($("#iptPgYears").attr("defaultVal"));
	$("#iptPgBirthYears").val($("#iptPgBirthYears").attr("defaultVal"));
	$("#iptPgDeadYears").val($("#iptPgDeadYears").attr("defaultVal"));
	$("#iptPgDealTime").val($("#iptPgDealTime").attr("defaultVal"));
	$("#iptPgAuditState").val($("#iptPgAuditState").attr("defaultVal"));
}

/**
 * 重新加载摄影家头像
 * @param imagename
 */
function reloadPgHeadImage(imagename){
	$("#imgPgHeadImage").attr("src","getPGImgStream.do?filename="+imagename+"&&date="+new Date());
}

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
	if(updatestatus){
		$(".removelbbnt").show();
		$(".insertlb").show();
	}
}

/**
 * 修改摄影家数据
 */
function openUpdate(){
	for(var i=0;i<inputarr.length;i++){
		if(inputarr[i]=="iptPgNation"){
			$("#"+inputarr[i]).addClass("inputboder");
			$("#iptPgNation").bind("click",changeNation);
		}else{
			$("#"+inputarr[i]).removeAttr("readonly");
			$("#"+inputarr[i]).addClass("inputboder");
		}
	}
	$("#iptHeadImg").val("");
	$(".pglable .removelbbnt").show();//显示去除标签功能按钮
	$("#divPgLable .insertlb").show();//显示添加标签功能按钮
	$("#divHandle").show();
	$("#divChangeHeadImg").show();
	updatestatus = true;
}

/**
 * 关闭修改功能
 */
function cancelUpdate(){
	updatestatus = false;
	for(var i=0;i<inputarr.length;i++){
		if(inputarr[i]=="iptPgNation"){
			$("#iptPgNation").unbind("click",changeNation);
		}
		$("#"+inputarr[i]).attr("readonly","readonly");
		$("#"+inputarr[i]).removeClass("inputboder");
	}
	$(".pglable .removelbbnt").hide();//显示去除标签功能按钮
	$("#divPgLable .insertlb").hide();//显示添加标签功能按钮
	reloadPgLable(pglable);
	setPgData(pgobj);
	reloadPgHeadImage(pgheadimgname);
	$("#divHandle").hide();
	$("#divChangeHeadImg").hide();
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
	$.post("getpgalllable.do", {}, function(result){
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
				$("input[name='checkpglable']:checked").each(function(i,e){
					var obj = {"lbId":$(e).val(),"lbName":$(e).attr("title")};
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
}

/**
 * 添加新标签
 */
function addNewLable(){
	var lablename = $("#iptNewLableName").val();
	var labledesc = $("#iptNewLableDesc").val();
	$.ajax({
		url:"addnewlabledata.do",
		type:"post",
		dataType:"json",
		data:{"newLbName":lablename,"newLbDesc":labledesc},
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
				var lbId = data.lbId;
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
 * 修改国家
 */
function changeNation(){
	var cur_nationid = $("#iptPgNation").attr("pgnationid");
	$.post("getpgallnation.do", {}, function(result){
		var data = JSON.parse(result);
		var html = "<form class='layui-form'><div class='seldiv'><select id='selNation' name='nation' lay-verify='' lay-search>";
		if(data.length!=0){
			for(var i=0;i<data.length;i++){
				if(data[i].nationId==cur_nationid){
					html += "<option value='"+data[i].nationId+"' selected='selected'>"+data[i].nationName+"</option>";
				}else{
					html += "<option value='"+data[i].nationId+"'>"+data[i].nationName+"</option>";
				}
			}
		}
		html += "</select></div></form>";
		layer.open({
			id:"divChangeNation",
			title:"选择国家",
			type: 1,
			area: ["500px", "180px"],
			shade:[0.5 , "#000" , true],
			skin: "nation-layer",
			btn: ["确定", "取消"],
			btnAlign: "c",
			resize:false,
			content:  html,
			yes:function(index, layero){
				$("#iptPgNation").val($("#selNation option:selected").text());
				$("#iptPgNation").attr("pgnationid",$("#selNation").val());
				layer.close(index);
			},
			success: function(layero, index){
			    form.render();
			    form.render("select");
			},
		});
	});
}
/**
 * 检查性别填写是否符合规范
 * @param e
 */
function checkSex(e){
	if($(e).val()==""){
		layuiLayerMsg("摄影家性别不能为空！");
		$(e).focus();
	}else{
		if($(e).val()!= "男" && $(e).val()!="女"){
			layuiLayerMsg("摄影家性别只能填写 男 或 女！");
			$(e).focus();
			$(e).val("");
		}
	}
}
/**
 * 更换图片片
 */
function changeHeadImg(){
	$("#iptHeadImg").click();
}
/**
 * 上传图片
 */
function uploadHeadImg(){
	var filedom = document.getElementById("iptHeadImg");
	var file = filedom.files["0"];
	var filename = file.name;
	var filesize = file.size;
	var url = "uploadpgheadimage.do?name="+filename+"&size="+filesize;
	var xhr;
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xhr=new XMLHttpRequest();
	}
	else{
		// code for IE6, IE5
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			$("#imgPgHeadImage").attr("src","showtmpheadimage.do?name="+filename+"&&date="+new Date());
			newpgheadimgname = filename;
		}
	}
	xhr.open("post", url, true);
	xhr.send(file);
}

/**
 * 获取摄影家出生及去世年月
 * @returns {String}
 */
function getPgBirthYears(){
	var obj = {};
	var birthyears = $("#iptPgBirthYears").val();
	var deadyears = $("#iptPgDeadYears").val();
	if(birthyears!=""){
		birthyears = birthyears.replace("年","");
	}
	if(deadyears!=""){
		deadyears = deadyears.replace("年","");
	}
	obj.birthyears = birthyears;
	obj.deadyears = deadyears;
	return obj;
}
/**
 * 获取摄影家名字
 */
function getPgName(){
	var pgname = $("#iptPgName").val();
	if(pgname==""){
		layuiLayerMsg("摄影家姓名不能为空！");
	}
	return pgname;
}
/**
 * 获取摄影家性别
 */
function getPgSex(){
	var pgsex = $("#iptPgSex").val();
	if(pgsex!=""){
		if(pgsex=="男"){
			pgsex = "M";
		}else if(pgsex == "女"){
			pgsex = "F";
		}
	}
	return pgsex;
}

/**
 * 保存摄影家信息
 */
function saveNewPgInfo(){
	var pgName = getPgName();
	var pgSex = getPgSex();
	var pgNationId = $("#iptPgNation").attr("pgnationid");
	var pgYears = $("#iptPgYears").val();
	var BirDeadObj = getPgBirthYears();
	var pgBirthYears = BirDeadObj.birthyears;
	var pgDeadYears = BirDeadObj.deadyears;
	var pgHeadImgName = newpgheadimgname;
	var pgLable = getNowLable();
	if(pgBirthYears.length>4){
		layuiLayerMsg("摄影家出生时间长度不能超过4！");
		return false;
	}
	if(pgDeadYears.length>4){
		layuiLayerMsg("摄影家去世时间长度不能超过4！");
		return false;
	}
	$.ajax({
		url:"updatepgbaseinfo.do",
		type:"post",
		dataType:"json",
		data:{"pgId":pId,"pgName":pgName,"pgSex":pgSex,"pgNationId":pgNationId,"pgYears":pgYears,"pgBirthYears":pgBirthYears,"pgDeadYears":pgDeadYears,"pgHeadImgName":pgHeadImgName,"pgLable":JSON.stringify(pgLable)},
		success:function(data){
			if(data.result == "success"){
				layuiLayerMsg("保存成功！");
				getPgData(pId);
			}else if(data.result == "exception"){
				layuiLayerMsg("保存失败，请联系管理员！");
			}else if(data.result == "nologin"){
				layuiLayerMsg("请先登录！");		
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) { 
			layuiLayerMsg("服务器出现错误,错误信息:"+XMLHttpRequest.responseText);
		} 
	});
}


/**
 * 弹出提示
 * @param msgstr
 */
function layuiLayerMsg(msgstr){
	layer.msg(msgstr,{skin: "layer-msg",time:800,shade:[0.5 , "#000" , true]});
}






