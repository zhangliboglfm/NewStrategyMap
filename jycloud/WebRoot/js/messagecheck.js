
//添加标签
function addTag(){
	
	var str="<div style='width:390px;height:210px;margin:5px;'>" +
				"<div class='layui-input-inline'>" +
					"<input type='text' id='tagName' placeholder='标签名称' value='' class='layui-input '>" +
				"</div>"+
				"<div class='layui-input-inline'>" +
					"<button class='layui-btn' style='margin-left:5px;' onclick='getTabList()'>搜索</button>"+
				"</div>" +
				"<div id='divtags' class='divtags'></div>" +
				"<div class='userdefined' onclick='addOtherTag()'>自定义&nbsp;<i class='layui-icon'>&#xe642;</i></div>" +
			"</div>";
	layer.open({
		type:1,
		title:'标签选择',
		content:str,
		success:function(index,layero){//成功调用后的回调函数,该回调携带两个参数，分别为当前层索引、当前层DOM对象
			layer.style(layero, {
				  width: '400px',
				  height:'270px',
				  left:($(window).width()-400)/2,
				  top:($(window).height()-270)/2,
			});
			
			$(".divtags").niceScroll({
				 autohidemode: true,
				 cursorwidth: "2px", // 滚动条的宽度，单位：便素
				 //cursorborder: "1px solid #fff", // CSS方式定义滚动条边框
				 cursorcolor: "#009688"
			});
			getTabList();
			
			 //输入框绑定enter事件
			 $("#tagName").keydown(function(evet){
			 	if(event.keyCode==13){
			 		getTabList();
			 	};
			 });
		},
		yes:function(index,layero){
			layer.close(index);
		}
	});
};

//获取标签池中东西
function  getTabList(){
	var tagName = $("#tagName").val();
	$.ajax({
		url:"getLablist.do",
		type:"post",
		dataType:"json",
		data:{"PHOTOG_ID":operaPhotog,"tagName":tagName},
		success:function(data){
			if(data!=null&&data.length!=0){
				var str="<form class='layui-form' id='layui-form' action='' lay-filter='example'>";
				for(var i=0;i<data.length;i++){
					if(data[i].hasit){
						str+="<input type='checkbox' name='"+data[i].LABEL_ID+"' title='"+data[i].LABEL_NAME+"' checked>";
					}else{
						str+="<input type='checkbox' name='"+data[i].LABEL_ID+"' title='"+data[i].LABEL_NAME+"' >";
					};
				};
				str+="</form>";
				$("#divtags").html(str);
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
						 url:"operateLabel.do",
						 type:"post",
						 dataType:"text",
						 data:{"PHOTOG_ID":operaPhotog,"LABEL_ID":LABEL_ID,"operate":operate},
						 success:function(data){
							 if(data==1){
								 if(operate=="add"){
									 var str="<div class='phototag' data-tagid='"+LABEL_ID+"'><span>"+LABEL_NAME+"</span><i class='layui-icon close-icon' onclick='deleteTag(this)'>&#x1007;</i></div>";
									 $("#phototags").append(str);
								 }else{
									 $("#phototags").find("div[data-tagid='"+LABEL_ID+"']").remove(); 
								 }
								
							 }
						 }
					 });
				});
			};
		}
	});
};
	


//添加其他标签
function addOtherTag(){
	layer.prompt({title: '请输入标签名称', value:"example0",id:"definedTag",formType: 0}, function(text, index){
		var tagName = $("#definedTagName").val().trim();
		var tagDesc = $("#definedtagDesc").val().trim();
		if(tagName==""){
			layer.msg("标签名称不能为空");
			return;
		};
		$.ajax({
			url:"addDefinedTag.do",
			type:"post",
			dataType:"json",
			data:{"tagName":tagName,"tagDesc":tagDesc,"PHOTOG_ID":operaPhotog},
			success:function(data){
					if(data!=null&&data.success==true){
						$("#layui-form").append("<input type='checkbox' name='"+data.label_id+"' title='"+tagName+"' checked>");
						form.render();
						var str="<div class='phototag' data-tagid='"+data.label_id+"'><span>"+tagName+"</span><i class='layui-icon close-icon' onclick='deleteTag(this)'>&#x1007;</i></div>";
						 $("#phototags").append(str);
						 layer.msg("操作成功！！！");
						 layer.close(index);
					}else if(data.message!=null){
						layer.msg(data.message);
					}else{
						layer.msg("请重试");
					}
						
				
				},
			});
	});
	
	$("#definedTag").html("<input type='text' id='definedTagName' placeholder='标签名称' value='' class='layui-layer-input taginputclass'>" +
	"<input type='text' id='definedtagDesc' placeholder='标签描述' value='' class='layui-layer-input taginputclass'>");
}

//删除此标签
function deleteTag(obj){
	 $.ajax({
		 url:"operateLabel.do",
		 type:"post",
		 dataType:"text",
		 data:{"PHOTOG_ID":operaPhotog,"LABEL_ID":$(obj).parent().attr("data-tagid"),"operate":"delete"},
		 success:function(data){
			 if(data==1){
				 $(obj).parent().remove();
			 }
		 }
	 });
	
};


//摄影家详细信息关闭
function photogclose(){
	$("#photogEdit").hide();
		
};


function ChangePhotogList(pageSize,pageNum,toinit,photogName,dealTime){
	
	//获取摄影家信息
  	$.ajax({
		url:"getPhotoglist.do",
		type:"post",
		dataType:"json",
		data:{"pageSize":pageSize,"pageNum":pageNum,"photogName":photogName,"dealTime":dealTime},
		success:function(data){
			if(data!=null&&data.list.length!=null){
				//分页插件
			if(toinit){
				laypage.render({
			    elem: 'demo0'
			    ,count: data.countNum
			    ,limit:pageNum
			    ,layout: ['count', 'prev', 'page', 'next','skip']
			    ,jump: function(obj,first){
			    	if(!first){
			    		ChangePhotogList(obj.curr,obj.limit);
			    	};
			    	
			    }
			  });
			};
			  
			  /*循环拼接*/
			  var list = data.list;
			  var str ="";
			  for(var i=0;i<list.length;i++){
					str+=" <div class='onephotolist'  onclick='photogdesc(this)' data-photogid='"+list[i].PHOTOG_ID+"'> "+
				    	" <div class='photo_left'> "+
				    		" <img src='getPhtotogImg.do?filename="+list[i].FILE_NAME+"'></img> "+
				    	" </div> "+
				    	" <div class='photo_right'> "+
				    		" <div class='photoIntroduce' > "+
					    		" <h3 class='layadmin-title'><strong>"+list[i].PHOTOG_NAME+"</strong></h3> "+
					    		" <h4 class='linktotab'>"+(list[i].PHOTOG_GENDER=="M"?"男":"女")+"</h4> "+
					    		" <h4 class='linktotab'>"+list[i].COUNTRY_CHN_NAME+"</h4> "+
					    		" <h4 class='linktotab'>"+list[i].BORN_DATE+"</h4> "+
					    		" <h4 class='linktotab'>~"+(list[i].DEATH_DATE==null?"":list[i].DEATH_DATE)+"</h4> "+
					    		" <h4 class='linktotab' onclick='packagedata(this)' data-lay='111'  data-articleId='"+list[i].CORE_INTRO+"' data-photogid='"+list[i].PHOTOG_ID+"'><a href='javascript:void(0)'>核心介绍</a></h4> "+
					    		" <h4 class='linktotab' onclick='packagedata(this)' data-lay='222' data-photogid='"+list[i].PHOTOG_ID+"'><a href='javascript:void(0)'>相关作品</a></h4> "+
				    		" </div> "+
				    	" </div> "+
				    " </div> ";
			  };
			  $("#photolist").html(str);
			  $(".onephotolist .photo_right").height($(".onephotolist .photo_left").height());
			}else{
				if(toinit){
					laypage.render({
				    elem: 'demo0'
				    ,count: data.countNum
				    ,limit:pageNum
				    ,layout: ['count', 'prev', 'page', 'next','skip']
				    ,jump: function(obj,first){
				    	if(!first){
				    		ChangePhotogList(obj.curr,obj.limit);
				    	};
				    	
				    }
				  });
				};
			  $("#photolist").html("");
			};
		}
	});
};

//摄影家详细信息
function photogdesc(obj){
	var PHOTOG_ID =$(obj).attr("data-photogid");
	operaPhotog=PHOTOG_ID;
	photogObj=obj;
	var orgidata=[];
	orgidata.push($(obj).find("img").attr("src"));
	orgidata.push($(obj).find(".photoIntroduce h3 strong").html().trim());
	var h4 = $(obj).find(".photoIntroduce h4");
	for(var i=0;i<4;i++){
		orgidata.push(h4.eq(i).html().trim().replace("~",""));
	};
	
	//获取摄影家详情
	$.ajax({
		url:"getPhotogdesc.do",
		type:"post",
		dataType:"json",
		data:{"PHOTOG_ID":PHOTOG_ID},
		success:function(data){
				if(data!==null){
					$(".layui-upload-img").attr("src",orgidata[0]);
					$("#photogName").val(orgidata[1]);
					$("#photogGender").val(orgidata[2]);  
					$("#photogBorn").val(orgidata[4]);
					$("#photogDeath").val(orgidata[5]); 
					$("#photogNation").val(orgidata[3]);
					
					dataLab = data.dataLab;
					dataPic = data.dataPic;
					var strlab="";
					for(var i=0;i<dataLab.length;i++){
						strlab+="<div class='phototag' data-tagid='"+dataLab[i].LABEL_ID+"'><span>"+dataLab[i].LABEL_NAME+"</span><i class='layui-icon close-icon' onclick='deleteTag(this)'>&#x1007;</i></div>";
					};
					$("#phototags").html(strlab);
	           			
	           		var strPic="";	 	
					for(var j=0;j<dataPic.length;j++){
						strPic+="<li id='"+dataPic[j].FILE_NAME+"' class='drag'> "+
									"<i class='iconfont' style='position: absolute;color:#009688;top:-4px;left:-4px;cursor: pointer;' onclick='deleteThisStrandImg(this)'>&#xe602;</i>";
									if(dataPic[j].SHOW_FLAG){
										strPic+="<i class='iconfont icon-true show' onclick='changeShowStatus(this)'>&#xe603;</i>";
									}else{
										strPic+="<i class='iconfont icon-true' onclick='changeShowStatus(this)'>&#xe603;</i>";
									}
									
									strPic+="<img src='getPhtotogImg.do?filename="+dataPic[j].FILE_NAME+"'></img></li>";
					}
					$(".sortable").html(strPic);
					$("#auditExplain").html(data.eduit_status);
					$("#photogEdit").show();
					
					
				}
				
			}
		});
	
};

//改变照片的显示状态
function changeShowStatus(obj){
	var hasClass = $(obj).hasClass("show");
	var status;
	if(hasClass){
		status = 0;
	}else{
		status =1;
	};
	var imgname = $(obj).parent().attr("id");
	//改变此照片显示状态
	$.ajax({
		url:"changeShowStatus.do",
		type:"post",
		dataType:"text",
		data:{"imgname":imgname,"status":status},
		success:function(data){
			if(data=="true"){
				layer.msg("操作成功");
				if(status==0){
					 $(obj).removeClass("show");
				}else{
					 $(obj).addClass("show");
				};
			}else{
				layer.msg("请重试");
			}
			
			}	
		});
	
}


//删除此标准照
function deleteThisStrandImg(obj){
	var htmlobj = $(obj).parent();
	var imgname = htmlobj.attr("id");
	//获取摄影家详情
	$.ajax({
		url:"deleteThisStrandImg.do",
		type:"post",
		dataType:"text",
		data:{"imgname":imgname},
		success:function(data){
				if(data=="true"){
					layer.msg("删除成功");
					htmlobj.remove();
				}else{
					layer.msg("请重试");
				}
			}	
		});
};


//保存摄影家基本信息
function savePhotoDesc(){
	var param =[
		$("#photogName").val().trim(),
		$("#photogGender").val().trim(), 
		$("#photogBorn").val().trim(),
		$("#photogDeath").val().trim(),
		$("#photogNation").val().trim()
	];
	
	$.ajax({
		url:"savePhotoDesc.do",
		type:"post",
		dataType:"text",
		traditional:true,
		data:{"PHOTOG_ID":operaPhotog,"param":JSON.stringify(param)},
		success:function(data){
			if(data==0){
				layer.msg('国家名称有误'); 
			}else if(data==1){
				layer.msg('输入性别有误');
			}else if(data==2){
				layer.msg('保存成功');
				changeOrigdata(param);
			}else if(data==3){
				layer.msg('未保存成功，请重试');
			};
			
		}
	});
}

//修改基本信息保存成功，改变摄影家list相关显示
function changeOrigdata(data){
	$(photogObj).find("h3 strong").html(data[0]);
	var h4obj=$(photogObj).find("h4");
	h4obj.eq(0).html(data[1]);
	h4obj.eq(1).html(data[4]);
	h4obj.eq(2).html(data[2]);
	h4obj.eq(3).html("~"+data[3]);
}

//改变审核状态
function changePhotogStatus(){
	layer.open({
		  type:1,
		  content: "<textarea	id='statusReason' placeholder='请输入原因' class='layui-textarea' style='width:98%;margin-left:1%;'></textarea>"
		  ,btn: ['待发布', '驳回', '发布']
		  ,btn1: function(index, layero){
			  var str =$("#statusReason").val().trim();
			  $.ajax({
					url:"changeStatusData.do",
					type:"post",
					dataType:"text",
					async:false,
					data:{"PHOTOG_ID":operaPhotog,"AUDIT_STATUS":3,"AUDIT_DESC":str,"AUDIT_PERSN":""}, //AUDIT_PERSN 审核人ID
					success:function(data){
						 if(data!=null&&data=="true"){
							 layer.msg("操作成功!!");
							  layer.close(index);
							  $("#auditExplain").html("待发布");
						 }else{
							 layer.msg("请重试!!");
						 };
					}
			 });
			  return false;
		  }
		  ,btn2: function(index, layero){
			  var str =$("#statusReason").val().trim();
			  if(str==""){
				  layer.msg("驳回原因不能为空！！！")
				  return false;
			  }
			  $.ajax({
					url:"changeStatusData.do",
					type:"post",
					dataType:"text",
					async:false,
					data:{"PHOTOG_ID":operaPhotog,"AUDIT_STATUS":2,"AUDIT_DESC":str,"AUDIT_PERSN":""}, //AUDIT_PERSN 审核人ID
					success:function(data){
						 if(data!=null&&data=="true"){
							 layer.msg("操作成功!!");
							  layer.close(index);
							  $("#auditExplain").html("驳回");
						 }else{
							 layer.msg("请重试!!");
						 };
						}
					});
			  return false;
		  }
		  ,btn3: function(index, layero){
			  var str =$("#statusReason").val().trim();
			  $.ajax({
					url:"changeStatusData.do",
					type:"post",
					dataType:"text",
					async:false,
					data:{"PHOTOG_ID":operaPhotog,"AUDIT_STATUS":4,"AUDIT_DESC":str,"AUDIT_PERSN":""}, //AUDIT_PERSN 审核人ID
					success:function(data){
						 if(data!=null&&data=="true"){
							 layer.msg("操作成功!!");
							  layer.close(index);
							  $("#auditExplain").html("已发布");
						 }else{
							 layer.msg("请重试!!");
						 };
						}
					});
			  return false;
		  }
		});
};


//直接发布状态
function changephotogStatusTo4(){
	$.ajax({
		url:"changeStatusData.do",
		type:"post",
		dataType:"text",
		data:{"PHOTOG_ID":operaPhotog,"AUDIT_STATUS":4,"AUDIT_DESC":"","AUDIT_PERSN":""}, //AUDIT_PERSN 审核人ID
		success:function(data){
			if(data!=null&&data=="true"){
				layer.msg("操作成功!!");
			    $("#auditExplain").html("已发布");
			}else{
				layer.msg("请重试！！");
			}
		}
	})
}
