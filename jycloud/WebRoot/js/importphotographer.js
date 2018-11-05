/**
 * 触发文件上传
 */
function clickFileUpload(){
	$("#iptFile").click();
}
/**
 * 获取文件信息，并显示文件名
 */
function getFileInfo(){
	var filedom = document.getElementById("iptFile");
	var file = filedom.files["0"];//console.log(file.name);console.log(file.size);
	$("#iptFileName").val(file.name);
	MyFile.file = file;
	MyFile.name = file.name;
	MyFile.size = file.size;
}
/**
 * 开始上传
 */
function startUpload(){
	if(MyFile.file == null){
		alert("请先选择文件！");
		return false;
	}
	$("#shadow").show();
	uplaodFile();
}
/**
 * 上传文件
 */
function uplaodFile(){
	var file = MyFile.file;
	var filename = MyFile.name;
	var filesize = MyFile.size;
	//var form = new FormData();
	var url = "uploadphotographer.do?name="+filename+"&size="+filesize;
	var xhr;
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xhr=new XMLHttpRequest();
	}
	else{
		// code for IE6, IE5
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhr.upload.onprogress = uploadProgress;
	var repTextSize=0;
	xhr.onreadystatechange=function(){
		if(xhr.readyState>2){
			var tmpText = xhr.responseText.substring(repTextSize);
			repTextSize = xhr.responseText.length;
			console.log(repTextSize);
			console.log(tmpText);
			if(tmpText.length > 0 ){
				initUploadLog(tmpText);
			}
		}
		if(xhr.readyState==4 && xhr.status==200){
			$("#shadow").hide();
		}
	}
	xhr.open("post", url, true);
	xhr.send(file);
}

/**
 * 上传进度展示
 */
function uploadProgress(evt) {
	if (evt.lengthComputable) {
		var percentComplete = Math.round(evt.loaded * 100.0 / evt.total);
		$("#divLogInfo").html("文件已上传："+percentComplete+"%"+"<br>");
		if(percentComplete == 100){
			$("#divLogInfo").append("文件上传完毕，开始校验"+"<br>");
		}
	} else {
	}
}
/**
 * 记录上传日志
 */
function initUploadLog(str){
	$("#divLogInfo").append(str);
	var div = document.getElementById('divLogInfo');
	div.scrollTop = div.scrollHeight;
}
/**
 * 下载模板文件
 */
function downLoadModelFile(){
	window.open("downloadPmodelfile.do");
}








