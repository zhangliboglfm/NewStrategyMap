<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>作品</title>

<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/production.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin:0;padding:0}
.main{
	width: 100%;height: 100%;
}
#main1{
	
}
#main2{
	background-color: #0ff;
	display: none;
}
</style>
</head>
<body>
	<div id="main1" class="main">
		<div id="mainTitle">
			<div id="authorName">张腾飞的作品</div>
			<div id="adjustRank">调整作品展示顺序</div>
		</div>
		<div id="mainContent">
			<div id="fristImage" class="showDivSmall" data-flag="longtaizi">
				<div id="image1" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/longtaizi.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的龙太子。</div>
			</div>
			<div id="secondImage" class="showDivSmall" data-flag="wumaner">
				<div id="image2" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/wumaner.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的巫蛮儿。</div>
			</div>
			<div id="fristImage" class="showDivSmall">
				<div id="image3" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/xuancaie.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的玄彩娥。</div>
			</div>
			<div id="fristImage" class="showDivSmall">
				<div id="image4" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/yulingshen.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的羽灵神。</div>
			</div>
			<div id="fristImage" class="showDivSmall">
				<div id="image4" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/yulingshen.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的羽灵神。</div>
			</div>
			<div id="fristImage" class="showDivSmall" data-flag="longtaizi">
				<div id="image1" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/longtaizi.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的龙太子。</div>
			</div>
			<div id="secondImage" class="showDivSmall" data-flag="wumaner">
				<div id="image2" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/wumaner.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的巫蛮儿。</div>
			</div>
			<div id="fristImage" class="showDivSmall">
				<div id="image3" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/xuancaie.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的玄彩娥。</div>
			</div>
			<div id="fristImage" class="showDivSmall">
				<div id="image4" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/yulingshen.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的羽灵神。</div>
			</div>
			<div id="fristImage" class="showDivSmall">
				<div id="image4" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/yulingshen.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的羽灵神。</div>
			</div>
			<div id="fristImage" class="showDivSmall" data-flag="longtaizi">
				<div id="image1" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/longtaizi.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的龙太子。</div>
			</div>
			<div id="secondImage" class="showDivSmall" data-flag="wumaner">
				<div id="image2" class="zuopinImage"><img class="zuopinImage2" src="image/zuopinTest/wumaner.jpg"></img></div>
				<div class="zuopinDesc">这是梦幻西游里面的巫蛮儿。</div>
			</div>
		</div>
	</div>
	<div id="main2" class="main">
		<iframe id="myiframe" name="myiframe" src="" frameborder="0" style="float:left;width:100%;margin:0px;padding:0px;height: 100%;"></iframe>
	</div>
</body>
<script type="text/javascript">
	
	$(function () {
		$(window).resize(adjust);
		adjust();
		$("#mainContent").niceScroll({
			cursorwidth : "10px",
			cursorcolor: "#737543",
			cursorborder: "0px"
		});
		testYulan();
	});
	
	$(".zuopinImage").click(function () {
		
	})
	
	//测试在线阅读
	function testYulan() {
		$.ajax({
			url:"zaixianyulan.do",
			type:"post",
			dataType:"json",
			data:{},
			success:function(data){
				var url = data.path+data.file;
				$("#myiframe").attr("src",url);
			}	
				
		});
	}
	
	function adjust() {
		var height =$("#main1").height()-$("#mainTitle").height();
		$("#mainContent").css({"height":height+"px","max-height":height+"px"});
		$(".showDivSmall").width(($("#mainContent").width()-60)/5);
		$(".showDivSmall").height($("#mainContent").height()/2);
		$(".zuopinDesc").height($(".showDivSmall").height()-$(".zuopinImage").height()-15);
	}
</script>
</html>