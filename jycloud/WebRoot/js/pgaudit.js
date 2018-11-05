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
			var obj={"pName":data.pName,"pSex":data.pSex,"pNation":data.pNation,"pNationId":data.pNationId,
					"pYears":data.pYears,"pBirthYears":data.pBirthYears,"pDeadYears":data.pDeadYears,
					"pDealTime":data.pDealTime,"pAuditState":data.pAuditState,"pAuditStateId":data.pAuditStateId,"pAuditResult":data.pAuditResult};
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
	var pgauditstateid = obj.pAuditStateId;
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
	$("#iptPgAuditState").attr("auditstateid",pgauditstateid);
	$("#tareaAuditResult").val(pgauditresult);
	auditdesc=pgauditresult;
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
 * 审核摄影家
 */
function changePgAuditStatus(){
	var auditstatus = $("#iptPgAuditState").attr("auditstateid");
	$.post("getallauditstatus.do", {}, function(result){
		var data = JSON.parse(result);
		var html = "<form class='layui-form'><div class='radiodiv'>";
		if(data.length!=0){
			for(var i=0;i<data.length;i++){
				if(data[i].auditId==auditstatus){
					html += "<input type='radio' name='auditstatus' value='"+data[i].auditId+"' title='"+data[i].auditName+"' checked>";
				}else{
					html += "<input type='radio' name='auditstatus' value='"+data[i].auditId+"' title='"+data[i].auditName+"'>";
				}
			}
		}
		html += "</div></form><div class='auditdescdiv'><div class='title'>问题描述：</div><textarea id='tareadesc'>"+auditdesc+"</textarea></div>";
		layer.open({
			id:"divAuditPg",
			title:"审核摄影家",
			type: 1,
			area: ["500px", "350px;"],
			shade:[0.5 , "#000" , true],
			resize:false,
			btn: ["确定", "取消"],
			btnAlign: "c",
			tipsMore: true,
			content: html,
			yes:function(index, layero){
				auditdesc = $("#tareadesc").val();
				$("#iptPgAuditState").val($("input[name='auditstatus']:checked").attr("title"));
				$("#iptPgAuditState").attr("auditstateid",$("input[name='auditstatus']:checked").val());
				if($("input[name='auditstatus']:checked").val()==2 && auditdesc==""){
					layuiLayerMsg("驳回时，请填写驳回原因！");
					return false;
				}
				$("#tareaAuditResult").val(auditdesc);
				layer.close(index);
			},
			success: function(layero, index){
				 form.render();
			},
		});
	});
}

/**
 * 保存摄影家审核状态
 */
function savePgAuditStatus(){
	var auditStatusId = $("#iptPgAuditState").attr("auditstateid");
	$.ajax({
		url:"saveauditstatus.do",
		type:"post",
		dataType:"json",
		data:{"pgId":pId,"auditStatusId":auditStatusId,"auditDesc":auditdesc},
		success:function(data){
			var msg = data.msg;
			if(msg=="success"){
				layuiLayerMsg("保存成功！");
			}else if(msg=="fail"){
				layuiLayerMsg("保存失败，请联系管理员！");
			}else if(msg=="null"){
				layuiLayerMsg("未找到相关摄影家状态信息！");
			}else if(msg=="excption"){
				layuiLayerMsg("系统出错,请联系管理员！");
			}else if(msg=="nologin"){
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






