function upNewPic(e) {
	addorreup=$(e).attr("data-int");
	$("#iptFile2").click();
}
function getFileInfo2(){
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
			url:"addNewPic.do",
			async:false,
			type:"post",
			dataType:"text",
			data:{
				leixing : leixing,
				filename : filename,
				bigTage : bigTage,
				flagId : flagId,
				pgorcg : pgorcg,
				cgId : cgId
			},
			success: function (data) {
				if (addorreup==0) {
					strArr.push(data);
					addNewPic(data);
				} else {
					showBigPic(data,imgFlagT);
					strArr[operNum]=data;
				}
			}
	};
	$("#fileform2").ajaxSubmit(options);
}


function addNewPic(urlPath) {
	var addCode="<div id='"+flagId+(strArr.length)+"' data-url='"+urlPath+"' data-flag='"+showFlag+"' class='thumbImg' onclick='leftSlide(this)'>"+
			"<div class='slideImg'><img src='getLongImage.do?imgUrl="+urlPath+"&imgFlag="+showFlag+"'></img></div>"+
			"<div class='slideText' title='"+imgName+"-"+(strArr.length)+"'>"+imgName+"-"+(strArr.length)+"</div>"+
		"</div> ";
	$(".show2").remove();
 	$("#slideContainer").append(addCode);
 	$("#slideContainer").append(addSlCode);
 	$("#"+flagId+(strArr.length)).click();
}