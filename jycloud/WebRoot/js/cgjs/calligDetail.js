/**
 * 重新加载摄影家标签
 * @param data
 */
function reloadPgLable(data){
	$("#divPgLable").html("");
	if(data.lenght!=0){
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			var lbdom = $("<div id='"+obj.worksId+"' lbname='"+obj.worksName+"' class='pglable'>《"+obj.worksName+"》<i class='iconfont removelbbnt' title='删除' onclick='removeLable(this)'>&#xe604;</i></div>");
			$("#divPgLable").append(lbdom);
		}
	}
	$("#divPgLable").append($("<div class='insertlb'><i class='iconfont addlbbnt' title='添加' onclick='addLable()'>&#xe6b7;</i></div>"));
}

function reloadWorks(data) {
	$("#divPgLable").html("");
	if(data.lenght!=0){
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			var lbdom = $("<div id='"+obj.worksId+"' lbname='"+obj.worksName+"' class='pglable'>"+obj.worksName+"<i class='iconfont removelbbnt' title='删除' onclick='removeLable(this)'>&#xe604;</i></div>");
			$("#divPgLable").append(lbdom);
		}
	}
	$("#divPgLable").append($("<div class='insertlb'><i class='iconfont addlbbnt' title='添加' onclick='addLable()'>&#xe6b7;</i></div>"));
	$(".pglable .removelbbnt").show();//显示去除标签功能按钮
	$("#divPgLable .insertlb").show();//显示添加标签功能按钮
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
	var cgWkName=$("#iptNewLableName").val();
	
	layui.use(['layer','form'], function(){
		layer=layui.layer;
		form=layui.form;
		$.post("getAllCGWorks.do", {cgId : cgId,cgWkName : cgWkName}, function(result){
			var data = JSON.parse(result);
			data=data.cgWorks;
			var inputhtml = "<div class='addnewlbdiv'>" +
							"<input id='iptNewLableName' style='width: 80%;' onkeyup='vagueSelect()' type='text' name='title' placeholder='请输入作品名称' autocomplete='off' class='layui-input'/>"+
							"</div>";
			var html = "<form class='layui-form'><div class='checkboxdiv'><div class='titlediv'>选择作品</div><div id='divChoseLable' class='choselbdiv'>";
			if(data.length!=0){
				for(var i=0;i<data.length;i++){
					if(getLableExist(lbarr,data[i].worksName)){
						html += "<input type='checkbox' name='checkpglable' value='"+data[i].workID+"' title='《"+data[i].worksName+"》' checked>";
					}else{
						html += "<input type='checkbox' name='checkpglable' value='"+data[i].workID+"' title='《"+data[i].worksName+"》'>";
					}
				}
			}
			html += "</div></div></form>";
			layer.open({
				id:"importantWorks",
				title:"添加重要作品",
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
					lableList=[];
					$("input[name='checkpglable']:checked").each(function(i,e){
						var obj = {"worksId":$(e).val(),"worksName":$(e).attr("title")};
						arr.push(obj);
						lableList.push($(e).val());
					});
					reloadWorks(arr);
					layer.close(index);
				},
				success: function(layero, index){
				    form.render();
				},
			});
		});
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
			lbarr.push(lbdata[i].worksName);
		}
	}
	if($.inArray(lbname,lbarr)==-1){
		return false;
	}else{
		return true;
	}
}
/**
 * 获取目前所有重要文章
 */
function getNowLable(){
	var arr = [];
	$("#divPgLable").find(".pglable").each(function(i,e){
		var obj = {"worksId":$(e).attr("id"),"worksName":$(e).attr("lbname")};
		arr.push(obj);
	});
	return arr;
}
function getNowImArt(){
	var arr = [];
	$("#divPgLable").find(".pglable").each(function(i,e){
		arr.push($(e).attr("id"));
	});
	return arr;
}

//模糊查询部分
function vagueSelect() {
	//addLable();
	var lbarr = getNowLable();
	var cgWkName=$("#iptNewLableName").val();
	$.ajax({
		url:"getAllCGWorks.do",
		type:"post",
		dataType:"json",
		data:{cgId : cgId,cgWkName : cgWkName},
		success:function(data){
			var resultData = data.cgWorks;
			var htmlCode="";
			if(resultData.length!=0){
				for(var i=0;i<resultData.length;i++){
					if(getLableExist(lbarr,resultData[i].worksName)){
						htmlCode += "<input type='checkbox' name='checkpglable' value='"+resultData[i].workID+"' title='《"+resultData[i].worksName+"》' checked>";
					}else{
						htmlCode += "<input type='checkbox' name='checkpglable' value='"+resultData[i].workID+"' title='《"+resultData[i].worksName+"》'>";
					}
				}
			} 
			$("#divChoseLable").html("");
			$("#divChoseLable").html(htmlCode);
			form.render();
		}
	});
}
/*
 * 重新上传头像部分
 * */
function getFileInfo2() {
	var filedom = document.getElementById("iptFile2");
	var file = filedom.files["0"];
	MyFile2.file = file;
	MyFile2.name = file.name;
	if(MyFile2.file == null){
		alert("请先选择文件！");
		return false;
	}
	uplaodFile2();
}
function uplaodFile2(){
	var file = MyFile2.file;
	var filename = MyFile2.name;
	filename = encodeURI(encodeURI(filename));
	var leixing = filename.substring(filename.lastIndexOf(".") + 1);
	var options = {
			url:"reupCgHead.do",
			async:false,
			type:"post",
			dataType:"text",
			data:{
				leixing : leixing,
				filename : filename,
				cgId : cgId,
			},
			success: function (data) {
				headImgP=data;
				headChange=true;
				reloadCgHeadImage(data);
			}
	};
	$("#fileform2").ajaxSubmit(options);
}
/**
 * 修改地位/成就
 */
function changeAgState(){
	var cur_agid = $("#iptCgAgName").attr("cgAgid");
	$.post("getAgName.do", {}, function(result){
		var data = JSON.parse(result);
		var html = "<form class='layui-form'><div class='seldiv'><select id='selAg' name='nation' lay-verify='' lay-search>";
		if(data.length!=0){
			for(var i=0;i<data.length;i++){
				if(data[i].agid==cur_agid){	
					html += "<option value='"+data[i].agid+"' selected='selected'>"+data[i].agName+"</option>";
				}else{
					html += "<option value='"+data[i].agid+"'>"+data[i].agName+"</option>";
				}
			}
		}
		html += "</select></div></form>";
		layer.open({
			id:"divChangeNation",
			title:"选择地位",
			type: 1,
			area: ["300px", "180px"],
			shade:[0.5 , "#000" , true],
			skin: "nation-layer",
			btn: ["确定", "取消"],
			btnAlign: "c",
			resize:false,
			content:  html,
			yes:function(index, layero){
				$("#iptCgAgName").val($("#selAg option:selected").text());
				$("#iptCgAgName").attr("cgAgid",$("#selAg").val());
				layer.close(index);
			},
			success: function(layero, index){
			    form.render();
			    form.render("select");
			},
		});
	});
}
