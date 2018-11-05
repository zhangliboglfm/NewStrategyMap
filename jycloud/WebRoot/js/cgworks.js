//按照页码查找重要作品
function impworks_showdatas(pageSize,pageNum,toinit,,workname,cgerId){
	$("#impworks").html("");
	$.ajax({
		url:"getimpworks.do",
		type:"post",
		dataType:"json",
		data:{cgerId:cgerId,workname:workname,pageNum:pageNum,pageSize:pageSize},
		success:function(data){
			if (data!=null&&data.list.length!=null) {
				if(toinit){
					layui.use(['laypage'], function(){
						laypage = layui.laypage;
						//总页数大于页码总数
						laypage.render({
						elem: 'impworks_feny'
						,count: data.countNum//数据总数
						,limit:pageNum
						,layout: ['count', 'prev', 'page', 'next','skip']
						,jump: function(obj,first){
							impworks_showdatasfirst(data.list,obj.curr);
						}
						});	
					});
				}
			} else {
				/*$("#wzCounts").html("文章总数"+data.countNum+"篇");
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
				  $("#showContents").html("");*/
			}
		}
	});	
}
//按照页码查找其它作品
function othworks_showdatas(pageSize,pageNum,toinit,,workname,cgerId){
	$("#othworks").html("");
	$.ajax({
		url:"getothworks.do",
		type:"post",
		dataType:"json",
		data:{cgerId:cgerId,workname:workname,pageNum:pageNum,pageSize:pageSize},
		success:function(data){
			if (data!=null&&data.list.length!=null) {
				if(toinit){
					layui.use(['laypage'], function(){
						laypage = layui.laypage;
						//总页数大于页码总数
						laypage.render({
							elem: 'othworks_feny'
								,count: data.countNum//数据总数
								,limit:pageNum
								,layout: ['count', 'prev', 'page', 'next','skip']
						,jump: function(obj,first){
							othworks_showdatasfirst(data.list,obj.curr);
						}
						});	
					});
				}
			} else {
				/*$("#wzCounts").html("文章总数"+data.countNum+"篇");
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
				  $("#showContents").html("");*/
			}
		}
	});	
}

