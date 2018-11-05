//添加标签
function addTag(){
	
	var str="<div class='layui-input-inline'>" +
				"<input type='text' id='tagName' placeholder='标签名称' value='' class='layui-input '>" +
			"</div>"+
			"<div class='layui-input-inline'>" +
				"<button class='layui-btn layui-btn' style='margin-left:5px;' onclick='getTabList()'>搜索</button>"+
			"</div>" +
			"<div id='divtags' class='divtags'></div>";
	layer.open({
		type:0,
		title:'标签选择',
		content:str,
		success:function(index,layero){//成功调用后的回调函数,该回调携带两个参数，分别为当前层索引、当前层DOM对象
			layer.style(layero, {
				  width: '400px',
				  height:'300px',
				  left:($(window).width()-400)/2,
				  top:($(window).height()-300)/2,
			});
			
			$(".divtags").niceScroll({
				 autohidemode: true,
				 cursorwidth: "2px", // 滚动条的宽度，单位：便素
				 //cursorborder: "1px solid #fff", // CSS方式定义滚动条边框
				 cursorcolor: "#009688"
			});
			getTabList();
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
		url:"getLablist_Art.do",
		type:"post",
		dataType:"json",
		data:{"PHOTOG_ID":wzIdnet,"tagName":tagName},
		success:function(data){
			if(data!=null&&data.length!=0){
				var str="<form class='layui-form' action='' lay-filter='example'>";
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
						 url:"operateLabel_Art.do",
						 type:"post",
						 dataType:"text",
						 data:{"PHOTOG_ID":wzIdnet,"LABEL_ID":LABEL_ID,"operate":operate},
						 success:function(data){
							 if(data==1){
								 if(operate=="add"){
									 var str="<div id='"+LABEL_ID+"' class='img_class8' data-tagName='"+LABEL_NAME+"' data-tagid='"+LABEL_ID+"'>"+
										"<div id='"+LABEL_ID+"' class='img_class8_child1'>"+LABEL_NAME+"</div>"+
										"<div class='img_class8_child2' onclick='deleteTag(this)'><i id='delindex' class='layui-icon close-icon' style='font-size: 14px;color:#009688;'>&#x1007;</i></div>"+
										"</div>";
									 $("#reseat_tags").append(str);
								 }else{
									 $(".img_class8[data-tagid='"+LABEL_ID+"']").remove();
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
	layer.prompt({title: '请输入标签名称', formType: 0}, function(text, index){
		var tagName = $("#tagName").val().trim();
		var tagDesc = $("#tagDesc").val().trim();
		if(tagName==""){
			layer.msg("标签名称不能为空");
			return;
		};
		/*$.ajax({
			url:"getLablist.do",
			type:"post",
			dataType:"json",
			data:{"tagName":tagName,"tagDesc":tagDesc},
			succuess:function(data){
					
				
				}
			});*/
	});
	
	$(".layui-layer-content").find("input[type='text']").parent().
	html("<input type='text' id='tagName' placeholder='标签名称' value='' class='layui-layer-input taginputclass'>" +
		"<input type='text' id='tagDesc' placeholder='标签描述' value='' class='layui-layer-input taginputclass'>");
}

//删除此标签
function deleteTag(obj){
	 $.ajax({
		 url:"operateLabel_Art.do",
		 type:"post",
		 dataType:"text",
		 data:{"PHOTOG_ID":wzIdnet,"LABEL_ID":$(obj).parent().attr("data-tagid"),"operate":"delete"},
		 success:function(data){
			 if(data==1){
				 $(obj).parent().remove();
			 }
		 }
	 });
	
};

//按文章名称查找
function showdatas(pageSize,pageNum,toinit,wzName){
	$("#showContents").html("");
	$.ajax({
		url:"getWzCounts.do",
		type:"post",
		dataType:"json",
		data:{wzName:wzName,pageNum:pageNum,pageSize:pageSize},
		success:function(data){
			if (data!=null&&data.list.length!=null) {
				$("#wzCounts").html("文章总数"+data.countNum+"篇");
				if(toinit){
					layui.use(['laypage'], function(){
						laypage = layui.laypage;
						//总页数大于页码总数
						laypage.render({
							elem: 'fenye'
								,count: data.countNum//数据总数
								,limit:pageNum
								,layout: ['count', 'prev', 'page', 'next','skip']
						,jump: function(obj,first){
							/* console.log(obj); */
							showdatasfirst(data.list,obj.curr);
						}
						});	
					});
				}
			} else {
				$("#wzCounts").html("文章总数"+data.countNum+"篇");
				if(toinit){
					laypage.render({
						elem: 'demo0'
							,count: 0
							,limit:20
							,layout: ['count', 'prev', 'page', 'next','skip']
					,jump: function(obj){
						
					}
					});
				}
				  $("#showContents").html("");
			}
		}
	});
}