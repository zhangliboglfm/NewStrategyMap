/**
 * 下载
 */
function download(){
	if(cur_sel==0&&artname == "null"){
		return false;
	}else if(cur_sel == 1&&artpic == "null"){
		return false;
	}
	artpic = artpic.replace(/#/g,"]");
	window.open("downloadachievement.do?sel="+cur_sel+"&artname="+artname+"&artpic="+artpic+"&arttitle="+encodeURI(encodeURI(arttitle)));
}
/**
 * 上传
 */
function uplaod(){
	if(cur_sel==0){
		$("#iptWordFile").click();
	}else if(cur_sel==1){
		if (artpic==null||artpic=="null"||artpic=="") {
			$("#iptPicFile").click();
		} else {
			layui.use(['layer','form'], function(){
	        	layer = layui.layer;
		        window.parent.layer.open({
			        type: 2 //此处以iframe举例
			        ,title: '重新上传图片'
			        ,area: ['1000px', '700px']
			        ,shade: 0
			        ,maxmin: true
			        ,offset: 'auto'
			        ,content: 'upLongPic.do?bigTage=achievement&flagId='+aId+'&cgId='+pId
			        ,zIndex: layer.zIndex //重点1 
			        ,success: function(layero){
			        	
			        }
		        });
	        });
		}
	}
}
/**
 * 刷新生平数据
 */
function ReFlushInfo(){
	$.ajax({
		url:"reflushachievementdata.do",
		type:"post",
		dataType:"json",
		data:{"pId":pId},
		async:false,
		success:function(data){
			if(data!=null&&data!="null"){
				aId = data.artId;
				artname = data.artName;
				arttitle = data.artTitle;
				artpic = data.artPic;
				arturl = data.artUrl;
			}
		}
	});
}
/**
 * 上传文档
 */
function uploadWord(){
	var filedom = document.getElementById("iptWordFile");
	var file = filedom.files["0"];
	var filename = file.name;
	var filesize = file.size;
	var url = "uploadpgachievement.do?name="+encodeURI(encodeURI(filename))+"&size="+filesize+"&pId="+pId+"&aId="+aId;
	var xhr;
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xhr=new XMLHttpRequest();
	}else{
		// code for IE6, IE5
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			var tmpText = xhr.responseText;
			$("#ifrWord").attr("src","showpgachievementword.do?wordName="+tmpText+"&pId="+pId+"&date="+new Date());
			ReFlushInfo();
		}
	}
	xhr.open("post", url, true);
	xhr.send(file);
	$("#iptFile").val("");
}

/**
 * 上传图片
 */
function uploadPic(){
	$("#shadow").show();
	var filedom = document.getElementById("iptPicFile");
	var file = filedom.files["0"];
	var filename = file.name;
	var filesize = file.size;
	var url = "uploadpgachievementpic.do?name="+encodeURI(encodeURI(filename))+"&size="+filesize+"&pId="+pId+"&aId="+aId;
	var xhr;
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xhr=new XMLHttpRequest();
	}else{
		// code for IE6, IE5
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			var tmpText = xhr.responseText;
			console.log(tmpText);
			var str="<img class='showImg' src='getPGImgStream.do?filename="+tmpText+"'></img>";
			$("#showImgD").html("");
			$("#showImgD").html(str);
			$("#shadow").hide();
			ReFlushInfo();
			
		}
	}
	xhr.open("post", url, true);
	xhr.send(file);
	$("#iptFile").val("");
}



