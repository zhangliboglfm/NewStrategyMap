//获取翻页
  	function getPages(nums){
  		layui.use(['laypage', 'layer','laydate','upload','form'], function(){
  			 laydate = layui.laydate;
  			 laypage = layui.laypage;
  			 layer = layui.layer;
  			 upload=layui.upload;
  			 form=layui.form;
  			//自定义样式
  			 laypage.render({
			    elem: 'demo2'
			    ,count: nums
			    ,theme: '#1E9FFF'
			    ,limit:8,
			    jump: function(obj, first){
			        if(!first){
			          layer.msg('第 '+ obj.curr +' 页');
			          getPic(8,obj.curr,lwsize,state1,state2);
			          pageSize=obj.curr;
			        }
			     }
			  });
			  //常规用法
			  laydate.render({
			    elem: '#test1'
			  });
  		});
  	}
//获取驳回原因
function getDismissal(){
	$.ajax({
		url:"getDismissal.do",
		type:"post",
		dataType:"json",
		success:function(data){
			var w="";
			if(data.length>0){
				for(var i=0;i<data.length;i++){
					w+="<li><span id='input'><input type='checkbox' data-id='"+data[i].REASON_ID+"' value='"+data[i].REASON_NAME+"' name='dismissalcheck'></span><span>"+data[i].REASON_NAME+"</span></li>";
				}
			}
			$("#reasonul").html("");
			$("#reasonul").html(w);
		}
 	});
}
//获取回退原因
function getRegresses(){
	$.ajax({
		url:"getRegresses.do",
		type:"post",
		dataType:"json",
		success:function(data){
			var w="";
			if(data.length>0){
				for(var i=0;i<data.length;i++){
					w+="<li><span id='input'><input  type='checkbox' data-id='"+data[i].REASON_ID+"' value='"+data[i].REASON_NAME+"' name='regressescheck'></span><span>"+data[i].REASON_NAME+"</span></li>";
				}
			}
			$("#reasonul").html("");
			$("#reasonul").html(w);
		}
 	});
}
//保存图片信息
function updateInfo(id,name,camera,film,lens,isblack){
	$.ajax({
         type: "POST",
         url: "updateInfo.do",
         dataType: "json",
         data:{"SAMP_PIC_ID":id,"SAMP_PIC_NAME":name,"FILM_PROD_ID":camera,"FILM_CAMERA_ID":film,"LENS_ID":lens,"IS_BLACK":isblack},
         success: function(data){
         	if(data=="1"){
         		alert("保存成功");
         	}else{
         		alert("保存失败");
         	}
         }
    });
}
//删除图片
function deletePic(id){
	$.ajax({
         type: "POST",
         url: "deletePic.do",
         dataType: "json",
         data:{"SAMP_PIC_ID":id},
         async:false,
         success: function(data){
         	if(data!="0"){
         		alert("成功删除了"+data+"个图片");
         	}
         }
    });
}
//筛选图片
function screenPic(id){
	$.ajax({
         type: "POST",
         url: "screenPic.do",
         dataType: "json",
         data:{"SAMP_PIC_ID":id},
         async:false,
         success: function(data){
         	if(data!="0"){
         		alert("成功筛选了"+data+"个图片");
         	}
         }
    });
}
//获取条数
function getNums(state1,state2){
	var long=$("#long").val().trim();
	var date=$("#test1").val().trim();
	var crux=$("#crux").val().trim();
	var color="2";
	for(var i=0;i<3;i++){
		if($("input:radio[name=color]").eq(i).is(':checked')){
			if(i==1){
				color='0';
			}
			if(i==2){
				color='1';
			}
		}
	}
	var label=[];
	var size=$("input[name='size']").eq(0).is(':checked');
	var camera=$("input[name='camera']").eq(0).is(':checked');
	var film=$("input[name='film']").eq(0).is(':checked');
	var lens=$("input[name='lens']").eq(0).is(':checked');
	$("input[name=label]:checked").each(function(){
		label+=$(this).val()+",";
		if($(this).val()=="全部"){
			label=[];
			return false;
		}
	});
	if(label.length>0){
		label=label.substring(0,label.length-1);
	}
	if(!camera){
		camera=$("#camera").val();
	}else{
		camera="0";
	}
	if(!film){
		film=$("#film").val();
	}else{
		film="0";
	}
	if(!lens){
		lens=$("#lens").val();
	}else{
		lens="0";
	}
	var long="";
	if(!size){
		long=$("#long").val().trim();
		size=lwsize;
	}else{
		size="0";
	}
	var n;
	$.ajax({
         type: "POST",
         url: "getNums.do",
         dataType: "json",
         async:false,
         data:{"SAMP_PIC_NAME":crux,"FILM_CAMERA_ID":camera,"DEAL_DATE":date,"IS_BLACK":color,"SAMP_PIC_LENGTH":long,"LABEL_NAME":label,"LENS_ID":lens,"FILM_PROD_ID":film,"size":size,"state1":state1,"state2":state2},
         success: function(data){
         	n=data[0].NUM;
         	var w="一共获取"+n+"张图片";
         	$("#pagenums").html("");
         	$("#pagenums").html(w);
         }
    });
    return n;
}
//获取图片
function getPic(pageNum,pageSize,lwsize,state1,state2){
	$("#imgul").html("");
    $("#imgul2").html("");
    $("#imgdiv").html("");
	$("#imginfo").html("");
	$("#cc").prop("checked",false);
	var long=$("#long").val().trim();
	var date=$("#test1").val().trim();
	var crux=$("#crux").val().trim();
	var color="2";
	for(var i=0;i<3;i++){
		if($("input:radio[name=color]").eq(i).is(':checked')){
			if(i==1){
				color='0';
			}
			if(i==2){
				color='1';
			}
		}
	}
	var label=[];
	var size=$("input[name='size']").eq(0).is(':checked');
	var camera=$("input[name='camera']").eq(0).is(':checked');
	var film=$("input[name='film']").eq(0).is(':checked');
	var lens=$("input[name='lens']").eq(0).is(':checked');
	$("input[name=label]:checked").each(function(){
		label+=$(this).val()+",";
		if($(this).val()=="全部"){
			label=[];
			return false;
		}
	});
	if(label.length>0){
		label=label.substring(0,label.length-1);
	}
	if(!camera){
		camera=$("#camera").val();
	}else{
		camera="0";
	}
	if(!film){
		film=$("#film").val();
	}else{
		film="0";
	}
	if(!lens){
		lens=$("#lens").val();
	}else{
		lens="0";
	}
	var long="";
	if(!size){
		long=$("#long").val().trim();
		size=lwsize;
	}else{
		size="0";
	}
	$.ajax({
         type: "POST",
         url: "getPic.do",
         dataType: "json",
         async:false,
         data:{"SAMP_PIC_NAME":crux,"FILM_CAMERA_ID":camera,"DEAL_DATE":date,"IS_BLACK":color,"SAMP_PIC_LENGTH":long,"LABEL_NAME":label,"LENS_ID":lens,"FILM_PROD_ID":film,"size":size,"pageNum":pageNum,"pageSize":pageSize,"state1":state1,"state2":state2},
         success: function(data){
         	var w="";
         	var w2="";
         	if(data.length>0){
         		for(var i=0;i<data.length;i++){
             		w+="<li>";
					w+="<input type='checkbox' value='0' name='pic'>";
					w+="<div class='getdiv' onclick='check(this)' id='"+data[i].SAMP_PIC_ID+"'><img src='getSampImage.do?imgUrl="+data[i].SAMP_PIC_SRC+"'></div>";
/*					w+="<div class='getdiv' onclick='check(this)' style='height:70%;background-image:url(thumbnail/"+data[i].SAMP_PIC_SRC+");background-size:cover;background-position: center;' id='"+data[i].SAMP_PIC_ID+"'></div>";*/
					w+="<div style='text-align: center;height:20%'>";
					w+="<table>";
					/* w+="<tr><td>名称:</td><td>"+data[i].SAMP_PIC_NAME+"</td></tr>"; */
					w+="<tr><td title='长:"+data[i].SAMP_PIC_LENGTH+"'>长:"+data[i].SAMP_PIC_LENGTH+"</td><td title='宽:"+data[i].SAMP_PIC_WIDTH+"'>宽:"+data[i].SAMP_PIC_WIDTH+"</td></tr>";
					w+="<tr><td>胶片型号:</td><td title='"+data[i].FILM_PROD_ID+"'>"+data[i].FILM_PROD_ID+"</td></tr>";
					w+="</table>";
					w+="</div>";
					w+="<div style='height:10%;'>";
					w+="<input type='hidden' value='"+data[i].SAMP_PIC_ID+"'>";
				    w+="<button class='layui-btn layui-btn-xs layui-btn-normal see' onclick='see(this)' >查看</button>";
				    w+="</div>";
					w+="</li>";
					if($("#screeninfo").is(':hidden')){
						w2+="<li><div class='lidiv' onclick='seeedit(this)' style='height:70%;' id='"+data[i].SAMP_PIC_ID+"'><img src=getSampImage.do?imgUrl="+data[i].SAMP_PIC_SRC+"></div></li>";
					}else{
						w2+="<li><div class='lidiv' onclick='seeedit(this)' style='height:25%;' id='"+data[i].SAMP_PIC_ID+"'><img src=getSampImage.do?imgUrl="+data[i].SAMP_PIC_SRC+"></div></li>";
					}
         		}
         		seeedit2(data[0].SAMP_PIC_ID);
         	}
         	$("#imgul").html(w);
         	$("#imgul2").html(w2);
         	if($(".screen2").is(':hidden')){
				$("#imgul li").css("height","45%");
         	}
         	checkedPic(data,checks);
         	form.render();
         }
    });
}
//根据id获取图片信息
function getOnePic(id){
	var data2;
	$.ajax({
         type: "POST",
         url: "getOnePic.do",
         dataType: "json",
         async:false,
         data:{"SAMP_PIC_ID":id},
         success: function(data){
         	if(data.length>0){
             	data2=data;
         	}
         }
    });
    return data2;
}
//获取标签
function getLabel(){
	$.ajax({
         type: "POST",
         url: "getLabel.do",
         dataType: "json",
         success: function(data){
         	var w="<form class='layui-form' action='' lay-filter='example'>";
         	if(data.length>0){
         		w+="<input type='checkbox' value='全部' name='label' title='全部'>";
             	for(var i=0;i<8;i++){
             		w+="<input type='checkbox' value='"+data[i].LABEL_NAME+"' name='label' title='"+data[i].LABEL_NAME+"'>";
             	}
         	}
         	w+="</form>";
         	$("#Label").html("");
         	$("#Label").html(w);
         	form.render();
         }
    });
}
//获取相机型号
function getCamera(keyword){
	$.ajax({
         type: "POST",
         url: "getCamera.do",
         dataType: "json",
         data:{"keyword":keyword},
         success: function(data){
         	if(data.length>0){
             	return data;
         	}
         	return null;
         }
    });
}
//获取胶片型号
function getFilm(keyword){
	$.ajax({
         type: "POST",
         url: "getFilm.do",
         dataType: "json",
         data:{"keyword":keyword},
         success: function(data){
         	if(data.length>0){
             	return data;
         	}
         	return null;
         }
    });
}
//获取镜头型号
function getLens(keyword){
	$.ajax({
         type: "POST",
         url: "getLens.do",
         dataType: "json",
         data:{"keyword":keyword},
         success: function(data){
         	if(data.length>0){
             	return data;
         	}
         	return null;
         }
    });
}
//获取这张图片的标签
function getPicLabel(id){
	$.ajax({
		url:"getPicLabel.do",
		type:"post",
		dataType:"json",
		data:{"SAMP_PIC_ID":id},
		success:function(data){
			if(data.length>0){
				var w="";
				for(var i=0;i<data.length;i++){
  					w+="<div class='pglable' data-tagid='"+data[i].LABEL_ID+"'><span>"+data[i].LABEL_NAME+"</span><i class='iconfont removelbbnt' id='"+id+"' style='color:#009688;cursor: pointer;font-size: 18px !important;' onclick='deleteTag(this)'>&#xe604;</i></div>";
				}
				$("#phototags").html("");
				$("#phototags").html(w);
			}else{
				$("#phototags").html("");
				$("#phototags").html("暂无标签");
			}
		}
 	});
}

/**
 * 添加标签
 */
/*function addLable(){
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
}*/
 //添加标签
function addTag(id){
	var str="<div class='addnewlbdiv'>" +
				"<div class='titlediv'>添加新标签</div>"+
				"<input id='iptNewLableName' type='text' name='title' placeholder='请输入标签名称' autocomplete='off' class='layui-input'/>"+
				"<input id='iptNewLableDesc' type='text' name='title' placeholder='请输入标签描述' autocomplete='off' class='layui-input'/>"+
				"<button class='layui-btn layui-btn-normal'  id='"+id+"' onclick='addOtherTag(id)'>添加</button>"+
			"</div>"+
			"<div class='addnewlbdiv'>" +
				"<div class='titlediv'>搜索</div>"+
				"<input id='tagName' type='text' placeholder='请输入标签名称' autocomplete='off' class='layui-input'/>"+
				"<button class='layui-btn layui-btn-normal'  onclick='getTabList()'>搜索</button>"+
			"</div>"+
			"<form class='layui-form'>"+
				"<div class='checkboxdiv'>"+
					"<div class='titlediv'>选择标签</div>"+
					"<div id='divChoseLable' class='choselbdiv'></div>"+
					"<div class='checkboxbottom'></div>"+
				"</div></form>";
			/*"<div style='width:99%;height:200px;margin:5px;'>" +
			"<div class='layui-input-inline'>" +
				"<input type='text' id='tagName' placeholder='标签名称' value='' class='layui-input '>" +
			"</div>"+
			"<div class='layui-input-inline'>" +
				"<button class='layui-btn layui-btn' style='margin-left:5px;' onclick='getTabList()'>搜索</button>"+
			"</div>" +
			"<div id='divtags' class='divtags'></div>" +
			"<div class='userdefined' id='"+id+"' onclick='addOtherTag(id)'>自定义&nbsp;<i class='layui-icon'>&#xe642;</i></div>" +
			"</div>";*/
	
	layer.open({
		type:1,
		title:'标签选择',
		content:str,
		area: ['500px', '480px'],
		offset: [5,0],
		success:function(index,layero){//成功调用后的回调函数,该回调携带两个参数，分别为当前层索引、当前层DOM对象
			/*layer.style(layero, {
				  width: '500px',
				  height:'600px',
				  left:($(window).width()-500)/2,
				  top:($(window).height()-300)/2,
			});*/
			/*$(".divtags").niceScroll({
				 autohidemode: true,
				 cursorwidth: "2px", // 滚动条的宽度，单位：便素
				 //cursorborder: "1px solid #fff", // CSS方式定义滚动条边框
				 cursorcolor: "#009688"
			});*/
			getTabList(id);
			 //输入框绑定enter事件
			 $("#tagName").keydown(function(evet){
			 	if(event.keyCode==13){
			 		getTabList(id);
			 	};
			 });
		},
		yes:function(index,layero){
			layer.close(index);
		}
	});
};
//获取标签池中东西
function  getTabList(id){
	var tagName = $("#tagName").val();
	$.ajax({
		url:"searchLabel.do",
		type:"post",
		dataType:"json",
		data:{"keyword":tagName,"SAMP_PIC_ID":id},
		success:function(data){
			if(data!=null&&data.length!=0){
				var str="<form class='layui-form' action='' lay-filter='example'>";
				for(var i=0;i<data.length;i++){
					if(data[i].is=="1"){
						str+="<input type='checkbox' thorder='"+data[i].SHOW_ORDER+"' name='"+data[i].LABEL_ID+"' title='"+data[i].LABEL_NAME+"' checked>";
					}else{
						str+="<input type='checkbox' thorder='"+data[i].SHOW_ORDER+"' name='"+data[i].LABEL_ID+"' title='"+data[i].LABEL_NAME+"'>";
					};
					
				};
				str+="</form>";
				$("#divChoseLable").html(str);
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
					 var order=$(data.elem).attr("thorder");
					 if(operate=="add"){
					 	$.ajax({
							url:"addPicLabel.do",
							type:"post",
							dataType:"json",
							data:{"SAMP_PIC_ID":id,"LABEL_ID":LABEL_ID,"order":order},
							success:function(data){
								if(data=="1"){
									if($("#phototags").html()=="暂无标签"){
										$("#phototags").html("");
									}
									var str="<div class='pglable' data-tagid='"+LABEL_ID+"'><span>"+LABEL_NAME+"</span><i class='iconfont removelbbnt' id='"+id+"'  style='color:#009688;cursor: pointer;font-size: 18px !important;' onclick='deleteTag(this)'>&#xe604;</i></div>";
											
									$("#phototags").append(str);
								}
							}
					 	});
					 }else{
					 	$.ajax({
							url:"deletePicLabel.do",
							type:"post",
							dataType:"json",
							data:{"SAMP_PIC_ID":id,"LABEL_ID":LABEL_ID},
							success:function(data){
								if(data=="1"){
									$("#phototags").find("div[data-tagid='"+LABEL_ID+"']").remove();
								}
							}
					 	});
					 }
				});
			};
		}
	});
};
//添加其他标签
function addOtherTag(id){
	/*layer.prompt({title: '请输入标签名称', value:"example0",id:"definedTag",formType: 0}, function(text, index){*/
	var tagName = $("#iptNewLableName").val().trim();
	var tagDesc = $("#iptNewLableDesc").val().trim();
	if(tagName==""){
		layer.msg("标签名称不能为空");
		return;
	};
	$.ajax({
		url:"addLabel.do",
		type:"post",
		dataType:"text",
		data:{"LABEL_NAME":tagName,"LABEL_DESC":tagDesc,"SAMP_PIC_ID":id},
		success:function(data){
				if(data!="0"){
					$("#layui-form").append("<input type='checkbox' name='"+data+"' title='"+tagName+"' checked>");
					form.render();
					var str="<div class='pglable' data-tagid='"+data+"'><span>"+tagName+"</span><i class='iconfont removelbbnt'  style='color:#009688;cursor: pointer;font-size: 18px !important;' onclick='deleteTag(this)'>&#xe604;</i></div>";
							
					 $("#phototags").append(str);
					 layer.msg("操作成功！");
					 getTabList(id);
					 alert("S");
				}else{
					layer.msg("标签名重复或者新增失败");
				}
		}
	});
	/*});
	$("#definedTag").html("<input type='text' id='definedTagName' placeholder='标签名称' value='' class='layui-layer-input taginputclass'>" +
	"<input type='text' id='definedtagDesc' placeholder='标签描述' value='' class='layui-layer-input taginputclass'>");*/
}

//删除此标签
function deleteTag(obj){
	 $.ajax({
		 url:"deletePicLabel.do",
		 type:"post",
		 dataType:"text",
		 data:{"SAMP_PIC_ID":$(obj).attr("id"),"LABEL_ID":$(obj).parent().attr("data-tagid")},
		 success:function(data){
			 if(data==1){
				 $(obj).parent().remove();
			 }
		 }
	 });
};
/**
 * 胶片管理页面方法
 */
//保存点击(编辑状态)
function savepic(obj){
	var id=obj.id;
	var name=$("#inputname").val();
	var camera=$("#inputcamera").val();
	var film=$("#inputfilm").val();
	var lens=$("#inputlens").val();
	var isblack="0";
	if($("#isblack2 em").text()=="黑白"){
		isblack="1";
	}
	updateInfo(id,name,camera,film,lens,isblack);
}
//保存点击(查看窗口)
function savepic2(id){
	//var id=obj.id;
	var name=$("#inputname2").val();
	var camera=$("#inputcamera2").val();
	var film=$("#inputfilm2").val();
	var lens=$("#inputlens2").val();
	var isblack="0";
	if($("#isblack em").text()=="黑白"){
		isblack="1";
	}
	updateInfo(id,name,camera,film,lens,isblack);
	getPic(8,pageSize,lwsize,state1,state2);
	$("#background").hide();
	$("#state").hide();
}
/* //保存并筛选(编辑状态)
function screenpics(obj){
	var id=obj.id;
	var name=$("#inputname").val();
	var camera=$("#inputcamera").val();
	var film=$("#inputfilm").val();
	var lens=$("#inputlens").val();
	updateInfo(id,name,camera,film,lens);
	screenPic(id);
	getPic(8,pageSize,lwsize,state1,state2);
} */
/* //保存并筛选(查看窗口)
function screenpic2(obj){
	var id=obj.id;
	var name=$("#inputname2").val();
	var camera=$("#inputcamera2").val();
	var film=$("#inputfilm2").val();
	var lens=$("#inputlens2").val();
	updateInfo(id,name,camera,film,lens);
	screenPic(id);
	getPic(8,pageSize,lwsize,state1,state2);
	$("#background").hide();
	$("#state").hide();
} */
//删除图片
function deletepics(obj){
	id=obj.id;
	deletePic(id);
	getPages(getNums(1,2));
	getPic(8,pageSize,lwsize,state1,state2);
}
//批量筛选
function screened(){
	var id="";
	$("input[name='pic']:checked").each(function(){
		id+=$(this).parent().find(".getdiv").eq(0).attr("id")+",";
	});
	if(id.length>0){
	id=id.substring(0,id.length-1);
	}else{
		alert("并没有选择可操作图片");
		return;
	}
	screenPic(id);
	getPages(getNums(1,2));
	getPic(8,pageSize,lwsize,state1,state2);
	chekedremove(id);
}
//批量删除
function deletes(){
	var id="";
	$("input[name='pic']:checked").each(function(){
		id+=$(this).parent().find(".getdiv").eq(0).attr("id")+",";
	});
	if(id.length>0){
	id=id.substring(0,id.length-1);
	}else{
		alert("并没有选择可操作图片");
		return;
	}
	deletePic(id);
	getPages(getNums(1,2));
	getPic(8,pageSize,lwsize,state1,state2);
	chekedremove(id);
}
/**
 * 删除页面方法
 */
//恢复图片
function recoveryImg(id){
	$.ajax({
         type: "POST",
         url: "recoveryPic.do",
         dataType: "json",
         data:{"SAMP_PIC_ID":id},
         async:false,
         success: function(data){
         	if(data!="0"){
         		alert("成功恢复了"+data+"个图片");
         	}
         }
    });
}
//恢复点击(编辑状态)
function recoveryPic(obj){
	var id=obj.id;
	recoveryImg(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
}
//恢复点击(查看窗口)
function recoveryPic2(id){
	//var id=obj.id;
	recoveryImg(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
	$("#background").hide();
	$("#state").hide();
}
//批量恢复
function recoverys(){
	var id="";
	$("input[name='pic']:checked").each(function(){
		id+=$(this).parent().find(".getdiv").eq(0).attr("id")+",";
	});
	if(id.length>0){
		id=id.substring(0,id.length-1);
	}else{
		alert("并没有选择可操作图片");
		return;
	}
	recoveryImg(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
	chekedremove(id);
}
/**
 * 待审阅页面方法
 */
//驳回图片
function rejectPics(id){
	$.ajax({
         type: "POST",
         url: "rejectPic.do",
         dataType: "json",
         data:{"SAMP_PIC_ID":id},
         async:false,
         success: function(data){
         	if(data!="0"){
         		alert("成功驳回了"+data+"个图片");
         	}
         }
    });
}
//通过
function passPic(id){
	$.ajax({
         type: "POST",
         url: "pass.do",
         dataType: "json",
         data:{"SAMP_PIC_ID":id},
         async:false,
         success: function(data){
         	if(data!="0"){
         		alert("成功通过了"+data+"个图片");
         	}
         }
    });
}
//通过(编辑状态)
function pass(obj){
	var id=obj.id;
	passPic(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
}
//通过(查看窗口)
function pass2(id){
	/*var id=obj.id;*/
	passPic(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
	$("#background").hide();
	$("#state").hide();
}
//驳回图片(编辑状态)
function rejectPic(obj){
	id=obj.id;
	rejectPics(id);
	rejectReason(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
}
//驳回图片(查看窗口)
function rejectPic2(id){
	//var id=obj.id;
	rejectPics(id);
	rejectReason(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
	$("#background").hide();
	$("#state").hide();
}
//批量通过
function passed(){
	var id="";
	$("input[name='pic']:checked").each(function(){
		id+=$(this).parent().find(".getdiv").eq(0).attr("id")+",";
	});
	if(id.length>0){
	id=id.substring(0,id.length-1);
	}else{
		alert("并没有选择可操作图片");
		return;
	}
	passPic(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
	chekedremove(id);
}
//批量驳回
function rejects(){
	var id="";
	$("input[name='pic']:checked").each(function(){
		id+=$(this).parent().find(".getdiv").eq(0).attr("id")+",";
	});
	if(id.length>0){
		id=id.substring(0,id.length-1);
	}else{
		alert("并没有选择可操作图片");
		return;
	}
	rejectReason(id);
	rejectPics(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
	chekedremove(id);
}
/**
 * 已审阅页面方法
 */
//回退图片
function regressesImg(id){
	$.ajax({
         type: "POST",
         url: "screenPic2.do",
         dataType: "json",
         data:{"SAMP_PIC_ID":id},
         async:false,
         success: function(data){
         	if(data!="0"){
         		alert("成功回退了"+data+"个图片");
         	}
         }
    });
}
//回退点击(编辑状态)
function regressesPic(obj){
	var id=obj.id;
	regressesImg(id);
	rejectReason(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
}
//回退点击(查看窗口)
function regressesPic2(id){
	//var id=obj.id;
	regressesImg(id);
	rejectReason(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
	$("#background").hide();
	$("#state").hide();
}
//批量回退
function regresses(){
	var id="";
	$("input[name='pic']:checked").each(function(){
		id+=$(this).parent().find(".getdiv").eq(0).attr("id")+",";
	});
	if(id.length>0){
		id=id.substring(0,id.length-1);
	}else{
		alert("并没有选择可操作图片");
		return;
	}
	rejectReason(id);
	regressesImg(id);
	getPages(getNums(state1,state2));
	getPic(8,pageSize,lwsize,state1,state2);
	chekedremove(id);
}
//增加图片回退原因
function addRegresses(SAMP_PIC_ID,SAMP_PIC_TYPE,STATUS_ID,AUDIT_DESC,AUDIT_PERSN_ACCT_ID,BACK_REASON_ID){
	$.ajax({
        type: "POST",
        url: "addRegresses.do",
        dataType: "json",
        data:{"SAMP_PIC_ID":SAMP_PIC_ID,
        	"SAMP_PIC_TYPE":SAMP_PIC_TYPE,
        	"STATUS_ID":STATUS_ID,
        	"AUDIT_DESC":AUDIT_DESC,
        	"AUDIT_PERSN_ACCT_ID":AUDIT_PERSN_ACCT_ID,
        	"BACK_REASON_ID":BACK_REASON_ID
        },
        success: function(data){
        	if(data!="0"){
        		alert("回退成功");
        	}else{
        		alert("回退失败");
        	}
        }
   });
}
//添加自定义回退原因
function addReason(REASON_ID,REASON_TYPE,REASON_NAME,REASON_DESC,SHOW_ORDER){
	$.ajax({
        type: "POST",
        url: "addReason.do",
        dataType: "json",
        data:{"REASON_ID":REASON_ID,
        	"REASON_TYPE":REASON_TYPE,
        	"REASON_NAME":REASON_NAME,
        	"REASON_DESC":REASON_DESC,
        	"SHOW_ORDER":SHOW_ORDER,
        },
        success: function(data){
        	if(data=="0"){
        		alert("添加自定义原因失败");
        	}
        }
   });
}


