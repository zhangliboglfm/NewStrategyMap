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
var xhr;
function uplaodFile(){
	var file = MyFile.file;
	var filename = MyFile.name;
	var filesize = MyFile.size;
	//var form = new FormData();
	var url = "uploadcginfo.do?name="+filename+"&size="+filesize;
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xhr=new XMLHttpRequest();
	}
	else{
		// code for IE6, IE5
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xhr.onreadystatechange = callback;
	xhr.open("post", url, true);
	xhr.send(file);
	/*var fd = new FormData();
	fd.append('fafafa', file);
    $.ajax({
        url:'uploadcginfo.do',
        type:'POST',
        data:{
        	"name":filename,
        	"size":filesize
        },
        processData:false,  //tell jQuery not to process the data
        contentType: false,  //tell jQuery not to set contentType
        success:function (data) {
        }
    })*/

}
var responsedata;
var data2;
/*function a(){
	var b=responseText;
	alert(b);
}*/
function callback() {
    //5。接收响应数据
	if(xhr.readyState>2){
		var tmpText = xhr.responseText;
		if(tmpText.length < 160 ){
			$("#divLogInfo2").html(tmpText);
		}
	}
    //判断对象的状态是交互完成
    if (xhr.readyState == 4) {
        //判断http的交互是否成功
        if (xhr.status == 200) {
            //获取服务漆器端返回的数据
            //获取服务器段输出的纯文本数据
        	data2=xhr.responseText;
        	var myArray=data2.split("数据如下:");
        	data2=myArray[1];
        	responsedata = eval(myArray[1]);
/*        	$("#divLogInfo2").append("<span class=\"opentable\">点击查看</span>");*/
        	getTable(responsedata);
        	/*        	
        	$(".opentable").click(function(){
        		layui.use(['form', 'layedit', 'laydate'], function(){
        			  var form = layui.form
        			  ,layer = layui.layer
              		layer.open({
	          			  type:2
	          			, content:'opencenter.do'
	          			,area: ["1400px", "800px;"],

          			  }); 
        			  });
        			getTable(responsedata);
            });*/
            
            //将数据显示在页面上
            //通过dom的方式找到div标签所对应的元素节点
            	/*if(typeof(responseText[0].data)=="undefined"){
            		getTable(responseText);
            	}else{
                	$("#divLogInfo2").html(responseText[0].data);
            	}*/
        } else {
            alert("出错了！！！");
        }
    }
}

/**
 * 获取表格
 */
var tableIns;
function getTable(data){
	layui.use('table', function(){
	var table = layui.table;
	//执行渲染
		tableIns = table.render({
		elem: '#table1' //指定原始表格元素选择器（推荐id选择器）
		,height: 315 //容器高度
		,data:data
		,cols:  [[ //标题栏
		{field: 'name', title: '姓名'}
		,{field: 'sex', title: '性别'}
		,{field: 'nation', title: '民族'}
		,{field: 'dynasty', title: '朝代'}
		,{field: 'birthday', title: '出生时间'}
		,{field: 'deathday', title: '去世时间'}
		,{field: 'word', title: '字'}
		,{field: 'number', title: '号'}
		,{field: 'ancestralhome', title: '祖籍'}
		,{field: 'birtharea', title: '出生地'}
		,{field: 'othername', title: '别称'}
		,{field: 'status', title: '地位'}
		,{field: 'importantworks', title: '重要作品'}
		,{field: 'importantdeeds', title: '重要事迹'}
		]]//设置表头
		//,…… //更多参数参考右侧目录：基本参数选项
	});
	});
}

/**
 * 记录上传日志
 */
/*function initUploadLog(str){
	$("#divLogInfo").append(str);
	var div = document.getElementById('divLogInfo');
	div.scrollTop = div.scrollHeight;
}*/
/**
 * 下载模板文件
 */
function downLoadModelFile(){
	window.open("downloadcgmodelfile.do");
}










