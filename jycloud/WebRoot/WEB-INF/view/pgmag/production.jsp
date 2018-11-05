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
<title><%=request.getAttribute("photogName")%>作品集</title>

<link rel="stylesheet" href="layui/css/layui.css" type="text/css"></link>
<link rel="stylesheet" href="css/production.css" type="text/css"></link>
<link rel="stylesheet" href="css/iconfont/iconfont.css" type="text/css"></link>

<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/prodcution.js"></script>
<script type="text/javascript" src="js/velocity.min.js"></script>
<script type="text/javascript" src="js/velocity.ui.js"></script>

<style>
html{width: 100%;height: 100%;}
body{width: 100%;height: 100%;margin:0;padding:0}

@font-face {font-family: "iconfont";
  src: url('iconfont.eot?t=1533970907163'); /* IE9*/
  src: url('iconfont.eot?t=1533970907163#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('data:application/x-font-woff;charset=utf-8;base64,d09GRgABAAAAAAyUAAsAAAAAE9wAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAABHU1VCAAABCAAAADMAAABCsP6z7U9TLzIAAAE8AAAARAAAAFZW7kioY21hcAAAAYAAAADnAAACtmE2B/9nbHlmAAACaAAAB40AAAt0cGVJJmhlYWQAAAn4AAAALwAAADYSSJmzaGhlYQAACigAAAAcAAAAJAfeA5RobXR4AAAKRAAAABQAAABMS+kAAGxvY2EAAApYAAAAKAAAACgaTBzgbWF4cAAACoAAAAAfAAAAIAEjAIJuYW1lAAAKoAAAAUUAAAJtPlT+fXBvc3QAAAvoAAAAqgAAAPgXVv+reJxjYGRgYOBikGPQYWB0cfMJYeBgYGGAAJAMY05meiJQDMoDyrGAaQ4gZoOIAgCKIwNPAHicY2Bk/s84gYGVgYOpk+kMAwNDP4RmfM1gxMjBwMDEwMrMgBUEpLmmMDgwVDzbztzwv4EhhrmBoQEozAiSAwAzNg0qeJzFkj1Ow0AQhb+NTfgLBqJUKRGhiZsUqXKF5D6+RS7APXIFKo7xxC3CG88KCQlKxIw+Sztj7TzNW+ACaExvWijvFCLeXC1jveFmrLe8+rzkYawMatVpoZV6bbTVTgcddTqf/c8gam9de/uv3s9RfO+Sp5rPNV+c0es8b8otE+6sd2YlV1ZxzSWPzLkP/WX6691/HuX/Rn+P2fj9qKe5GSqWKBJvE5UknNUk8YZRk3jXqE2Ifpd4/2iRxMvRKonJWifxitQn9gltEjuGtom9Q7vELqJ9Yj/RIQn9Oib2GJ0Smk8W2Ut9AHic7Vbdb1THFZ8zc7939+7du7v37oft9e567xoBNt5r79KCvZD6AxI3doiJgnFqkEANIZBGtAJBQu2HSo7UGFQeqjZSi4orwlPzUiku1DYPqHX7kkqVUKkqg2gf+kCltFSVKjzumbs2dqQ+9A/o1dXcM3M+7syZ3/nNEJmQtUfsDkuROGknXaSfjBICynYomLQZ8uXuDrodknk56SZMVi6W82qx0MF6wS0oCadS7fZcRVWiYEIL+PlKtdxBy9DT3Uf3QMVpBkhnM6/apSabXQUjVW75Dn+R/gSSuWJTtG8nP7ijnqi0xrXzYdtO2/Z3NUWWNUqlqAlnXEeXdUPhc3I0k7yT20ZzEE6XM8NHIq1Z+/hM99nmkqsDTE1BPNtq3qzHMjF838s4cTutWhEtlYkU2xJw/s+hVDzc7D0m+FBc68/Zr9kwsUgTrrLSQhMmLXRQnC+ItRTzYlU+PJv8+L2DB9/7eOGm+NyEr+S6unJ8b873c2wYBxY21av/ytWbm+u5jfh32ALbh/F3/Lf42zE1XkFphqL4zx5IihT2Ajx7Y+7S4OCludtzFwcHL87BZLatLbtU6iktZUqljBDYvqGLc7dvXBwaungDrYY48XralrKlUnaprcdbzHheZpGAmAQbp++SCCElqHrlPG5OwgWnQsPQr9v26gXbkGToNwr7KLFaASw1pnESJUTBuf8K576XhIiLSPgSGSYTGKUDyn1QawHXBGaCigJ2O0AudECtD/yKk1CKOK6oBa+nu4pDLaCakDQBvE7wsN+wygEawi78YB5QscukDnzvyqIkLV65usjY4tUL1xm7fiFo+RLTKS2+84KiacqXT7RI7YrGGNXDCmUUVHq7kF7JGHJMC7fENTe0ktrF/0pXNNfSVpjhKvD950GvLNJTG1Gx/Zkcknfv00Ia7K6oZxG3ybCiMF0J22FFXX0M7ko2H3G0SEINr6T6d0ErW1EtB6PKroHJZZijaYmwKZLCSvkqIXKhjPnxusUyqxUXk+MkVJENVgg+iWCoEqi7A1OGgleIooQIyAkTp1IXNqin5M3JiQ/L7e3lDycmP9sU35wYO/R+EZ/3D419uil2LuvxtLG8bKTj+vIW+dvt5dmjk7+bPDor/J+L7F5b4fIrY/Njr1wutG0R+Z5lPRMXvvGMvvybTZkIPK1NsWZ2kpjBWnWo4n6ruthL+hcnxuNwP7Yjb/Hz8ONYYcqCv8F9K78zxi/wYzge1MN95JbtiKlvYbgt+GEVx23IATxc3/UbwMFBP9AlFATRduGTcFxHUYsq4swTSVyHG5JRMQAhhisXC0IVAHAPBENFr1rza34VVvuPUnq0P2jvhdNqyE+n/ZCaDqshhb5DY4YKOvR2SlFHpqouuVGpsxdHVCOGWj2b0kLdmVRXJNYk7M8E9hpsOGgaZakoRQ8cFC7nqEGLdGJgYIIGLRi6Fi4BLYYMHVg90m5Dxursg7TDavXd4KShr9PKgO1F6gxCRtijUDITAKx30zQp1fbW5HXTuBfZzxp7s8amGZAsVinuDMIKd0bACnlRwKqmB8XGzvN52K/HUwZf4vN62tZhkH+i22kDRmEI+30wBPuMVFzni9i30zqf558Ywm6U/wL7hEj4r9+yJeTNFGlDZusW3LYF7VuroLZ1k/0G6fkNWs1TMnJg4O3mXK757YEDP9oURxZWZXl1IWivz0disch8jP9bfEFh1rr14OkWfE4PrjvuRYfFZ7L8bHFhlZcb7L/BwVNBjYZIWpxkRHWIWyU1jzCcZc2t2jhFZDNKHvAVWYb8gweQl2W+srb3cuUHL8JPryqqM3CXTeHQgy0mz54On7Inr1lQGu85sTv1qPEvJIXX6FP8V/aL+I6bgIjEAcfNV7FPyfFpSqePH59mbHqkdOTEkdLg61rXJRi71KW9Tv+xocKW11taW1t2XhtHlGnj19bX9Cd2gxWJTmz8Tz6ZV91azI9BWY0VY/mePKvxGdjxdPjpaf51bOAx/z0rvPX3lz7n92GFp176HLbx/FvBdDHWp+weGwx43se69kgZ04NJUshWho8Xk2INvVAUdbUHTy4sfjcZMD7wu2sEE0Pu3gWQJL5299ZDSXp469ZDWX5IZ8FKWbNhywrPogBCOiADoC36rKEPX2MjwvqRJD1CHz7eMP6Cm5irwN0f2S9xyvvJC+QEOYlrb9R/Y56+g5DrwTsH8kTjnBKnaeO4CugFzYqCiZOCiANewDOReWVv/cpCq/WNM1iYV4JwnUD/mZ85/M0fygcmgZ4d6zd0PDINFpL7e0cpjPSGTHb5jQPfSGvmwa8BPTXaJAx01Ded/ICxs+N/kCU8qtgxSZJCEVP2raTpSyG8zTAZ9GP08MsTwD46Fzrz8mvv0iCu0NBX6/VD1AwlJqcYjA5YoJ8dOYz8g2oRns2enpyx+EOJ4SMdk81IiDHZfxIyo8YTXwQODI8FvPAR24X4N4MqRS4oYGkiM3RXIevE4AnfJjgbPuDHYwU2ZXGbbxOkDTNwXZA2+f9d4H+5CzTuWufoZ+KuhUkO6hwPcWRgeMrv4F2Lzoi7Fr9jFOgSf37ZoiT6H03iO7oAAAB4nGNgZGBgAOI/d/dOiue3+crAzcIAAtenrLqNoP83sDAwNwC5HAxMIFEAfgEMyQB4nGNgZGBgbvjfwBDDwgACQJKRARUIAwBHGQJ8eJxjYWBgYH7JwMDCQDkGAEyDATUAAAAAAHYApgDmAQQBlgIOAi4C3AMSA2gDngPSA/gETgToBQoFnAW6eJxjYGRgYBBmKGNgYwABJiDmAkIGhv9gPgMAFlsBpgB4nGWPTU7DMBCFX/oHpBKqqGCH5AViASj9EatuWFRq911036ZOmyqJI8et1ANwHo7ACTgC3IA78EgnmzaWx9+8eWNPANzgBx6O3y33kT1cMjtyDRe4F65TfxBukF+Em2jjVbhF/U3YxzOmwm10YXmD17hi9oR3YQ8dfAjXcI1P4Tr1L+EG+Vu4iTv8CrfQ8erCPuZeV7iNRy/2x1YvnF6p5UHFockikzm/gple75KFrdLqnGtbxCZTg6BfSVOdaVvdU+zXQ+ciFVmTqgmrOkmMyq3Z6tAFG+fyUa8XiR6EJuVYY/62xgKOcQWFJQ6MMUIYZIjK6Og7VWb0r7FDwl57Vj3N53RbFNT/c4UBAvTPXFO6stJ5Ok+BPV8bUnV0K27LnpQ0kV7NSRKyQl7WtlRC6gE2ZVeOEXpc0Yk/KGdI/wAJWm7IAAAAeJxtjt0OgjAMhXcUNwEVFV6DxPBG/MlmYEPGEvDpLXBnPBdt2q89LduxTQH7rxg77OHhAA6BI3wECHHCGRdEuOKGO2IkDJP3Urn2O6udSvOqisaB6qat08KMo+m4Nc46I7LG5bpQvpVEZzXX3Mr6IxXf+mJxkbnhlVOUgmebDrWeiUVvCr3SzUQTlhZUafQjC1ajUhIURBbHcDuVlqafk5831iZjX5YwQOoAAA==') format('woff'),
  url('iconfont.ttf?t=1533970907163') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('iconfont.svg?t=1533970907163#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}


</style>
</head>
<body>
	<div class="main">
		<!-- 基本信息修改 -->
		<div class="left">
			<div class="productIntroduce">
				<div class="title"><span class="productlabel">摄影家:</span><span class="photogName"><%=request.getAttribute("photogName")%></span></div>
				<div class="productFrom">
					<form class='layui-form' lay-filter="example" method="post">
						 <div class="form-item">
						    <label class="productlabel">作品名:</label>
		    				<input id="works_name" name="works_name"  class="productinput" type="text"  value="" autocomplete="off" placeholder="请输入作品名">
						  </div>
						 <div class="form-item">
						    <label class="productlabel">类型:</label>
		    				<input id="works_type" name="works_type" class="productinput" type="text"  value="" autocomplete="off" placeholder="请输入作品类型">
						 </div>
						 <div class="form-item">
						    <label class="productlabel">拍摄日期:</label>
		    				<input id="shoot_date" name="shoot_date"  class="productinput" type="text"  value="" autocomplete="off" placeholder="请输入拍摄日期">
						 </div>
						 <div class="form-item">
						    <label class="productlabel">是否代表作:</label>
						    <div class="radioDiv">
			    				<input type="radio" name="is_repre_works" value="1" title="是" checked="checked">
					      		<input type="radio" name="is_repre_works" value="0" title="否">
						    </div>
						 </div>
						 <div class="form-item1">
						    <label class="productlabel">拍摄过程:</label>
						    <div class="textAreaDiv">
						    	<textarea placeholder="拍摄过程" id="shoot_proc" name="shoot_proc" class="layui-textarea" style="resize:none" name="shootProcess"></textarea>
						    </div>
						 </div>
						 <div class="form-item1">
						    <label class="productlabel">作品解读:</label>
						    <div class="textAreaDiv">
						    	<textarea placeholder="作品解读" id="works_intro" name="works_intro" class="layui-textarea"  style="resize:none" name="worksIntepa" ></textarea>
						    </div>
						 </div>
						 <div class="form-item1">
						    <label class="productlabel">标签:</label>
						    <div class="worksLabels">
						    	<ul class="ulworksLabels" id="ulworksLabels">
						    	</ul>
						    	<div class="worksLabel1"><i class="layui-icon" style="color:#009688;cursor: pointer;font-size: 18px;" onclick="addLabel()">&#xe61f;</i></div>
						    </div>
						 </div>
						<div class="form-item2" style="text-align: center;" id="saveDesc">
						  	<button class="layui-btn  layui-btn-sm" lay-submit lay-filter="updateWorkDesc" style="background:#60B878;">保存信息</button>
						</div>
					</form>
					<!-- 审核状态 -->
					<div class="examineStatus">
						<div class="form-item">
						    <label class="productlabel">审核状态:</label>
						</div>	
						<div class="form-item" style="text-align: center;">
							<div class="onestatus">
								<span class="title">作品：</span><span class="textStatus" data-status="" id="workStatus"></span>
							</div>
							<div class="onestatus">
								<span class="title">解读：</span><span class="textStatus" data-status="" id="explanStatus"></span>
							</div>
						  
						</div>		
						<div class="form-item" style="text-align: center;">
							<div class="onestatus">
							  	<button class="layui-btn  layui-btn-sm"  style="background:#60B878;" data-type="W" onclick="changeExamineStatus(this)">作品审核</button>
							</div>
							<div class="onestatus">
							  	<button class="layui-btn  layui-btn-sm"  style="background:#60B878;" data-type="E" onclick="changeExamineStatus(this)">解读审核</button>
							</div  >
						</div>		
					</div>
				</div>
    		</div>
		</div>
		
		<!--顶部大图div -->
		<div class="top">
			<div class="bigImgTitle">
				<i class="iconfont fullScreen" title="全屏显示" onclick="showfullScreenDiv()">&#xe60e;</i>
				<div class="imgNum">
					<input type="text" class="curNum" id="curNum" value="0" lay-filter="skipNum" title="输入数字，跳转" onchange="changeImgNum(this)">
					<span class="SegmentingLine">/</span>
					<span class="allNum"><%=request.getAttribute("AllNum")%></span>
				</div>
				<div class="DivUploadImg"><i title="更新图片" id="iconUpload" class="iconfont iconUpload" style="font-size:30px;">&#xe68f;</i></div>
			</div>
			<div class="divImg">
				<img class="bigImg" id="bigImg" src="" onclick="showfullScreenDiv()">
			</div>
			<div class="DivPreImg"><i title="上一张" class="iconfont iconPre" style="font-size:24px;" onclick="preImg()">&#xe63b;</i></div>
			<div class="DivNextImg"><i title="下一张" class="iconfont iconNext" style="font-size:24px;" onclick="nextImg()">&#xe631;</i></div>
		</div>
		<div class="bottom">
			 <div class="theater-thumb">
			 	<div class="thumb-container" id="thumb-container">
			 	</div>
			 </div>		
		 </div>
		 
		<!-- 图片全屏显示 --> 
		<div class="fullScreenDiv">
			<img class="fullbigImg" id="fullbigImg" src=""><span></span>
			<div class="DivPreImg"><i title="上一张" class="iconfont iconPre" style="font-size:30px;" onclick="preImg()">&#xe63b;</i></div>
			<div class="DivNextImg"><i title="下一张" class="iconfont iconNext" style="font-size:30px;" onclick="nextImg()">&#xe631;</i></div>
			<div class="DivCloseImg"><i title="关闭" class="iconfont iconClose" style="font-size:30px;" onclick="closefullScreenDiv()">&#xe635;</i></div>
			<div class="DivZoomImg"><i title="缩小" zoomFlag="0" id="iconZoom" class="iconfont iconZoom" style="font-size:30px;" onclick="zoomFullPic()">&#xe822;</i></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var userId ="<%=request.getAttribute("userId")%>";
	var photogid ="<%=request.getAttribute("photogid")%>";
	var photogName ="<%=request.getAttribute("photogName")%>";
	var AllNum =<%=request.getAttribute("AllNum")%>;
	var worksData =<%=request.getAttribute("worksData")%>;
	//正在操作的作品顺序
	var operNum=<%=request.getAttribute("operNum")%>;
	var audit=<%=request.getAttribute("audit")%>;
	var canSwitch = true;
	var auditdesc1,auditdesc2;
	//正在操作的作品id
	var operWorkId;
	//偏移量px
	var startTransX;
	var form,layer,upload;
	
	$(function(){
		adjust();
		$(window).resize(adjust);
		
		if(audit==0){
			$(".onestatus button").remove();
		}else{
			$("input,textarea").attr("readonly","readonly");
			$(".close-icon,.worksLabel1").css("display","none");
			$("input:radio").remove();
			$("#saveDesc").remove();
		};
		$(".worksLabels,.productFrom").niceScroll({
			 autohidemode: true,
			 cursorwidth: "1px", // 滚动条的宽度，单位：便素
			 //cursorborder: "1px solid #fff", // CSS方式定义滚动条边框
			 cursorcolor: "#60B878"
		});
		
		$("#curNum").val(operNum+1);
		layui.use(["form","layer","upload"], function(){
			form = layui.form,layer=layui.layer,upload=layui.upload;
			
			form.verify({
			    shoot_date: function(value){
			      	var reg =/([\d]{4}(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-8])))))|((((([02468][048])|([13579][26]))00)|([0-9]{2}(([02468][048])|([13579][26]))))(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-9])))))/;
			      	if(!reg.test(value)){
			      		return "请输入正确的日期格式yyyyMMdd";
			      	};
			    }
		   });
			
			//form表格渲染
			form.render();
			
			form.on("submit(updateWorkDesc)", function(data){
				var labelId=[];
				$(".ulworksLabels li").each(function(i,e){
					labelId.push($(e).attr("data-labelId"));
				});
				data.field["Workid"]=operWorkId;
				data.field["labelId"]=JSON.stringify(labelId);
				  	//根据作品id获取作品的详细信息
					$.ajax({
						 url:"updateWorkDesc.do",
						 type:"post",
						 dataType:"text",
						 data:data.field, 
						 success:function(data){
						 	if(data=="true"){
						 		layuiLayerMsg("更新成功！！！");
						 	}else{
						 		layuiLayerMsg("请重试！！！");
						 	}
						 }
					 });
				  
				  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
				});
				
				form.on("submit(notSubmit)", function(data){
					  	//根据作品id获取作品的详细信息
						$.ajax({
							 url:"updateWorkDesc.do",
							 type:"post",
							 dataType:"text",
							 data:data.field, 
							 success:function(data){
							 	if(data=="true"){
							 		layuiLayerMsg("更新成功！！！");
							 	}else{
							 		layuiLayerMsg("请重试！！！");
							 	}
							 }
						 });
					  
					  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
					});
				
				
			//更新作品
		    var uploadInst = upload.render({
		      elem: '#iconUpload'
		      ,url: 'uploadWorkImg.do'
		      ,before: function(obj){
		        this.data={'Workid':operWorkId,"photogid":photogid};
		      }
		      ,done: function(res){
		        //如果上传失败
		        if(res.code == 0){
		            layuiLayerMsg('上传失败,请重试！');
		        }else{
		        	layuiLayerMsg('上传成功');
		          	$("#bigImg,#fullbigImg").attr("src","getPGImgStream.do?filename="+res.filename);
		          	$(".thumb-container .show").attr("data-url","getPGImgStream.do?filename="+res.filename);
		          	$(".thumb-container .show").css("background-image","url('getPGImgStream.do?filename="+res.filename+"')");
		        };
		      }
		    });
		    
		    var str="";
			for(var i=0;i<worksData.length;i++){
				if(i==operNum){
					str+="<div class='thumbImg lazyload show' data-workId='"+worksData[operNum].WORKS_ID+"' data-status='0' data-url='getPGImgStream.do?filename="+worksData[operNum].FILE_NAME+"' onclick='switchImgDiv(this)'></div>";
					operWorkId=worksData[operNum].WORKS_ID;
					$("#bigImg,#fullbigImg").attr("src","getPGImgStream.do?filename="+worksData[operNum].FILE_NAME);
					//获取作品详情
					getWorkDesc(false,null,worksData[operNum].WORKS_ID);
				}else{
					str+="<div class='thumbImg lazyload' data-workId='"+worksData[i].WORKS_ID+"' data-status='0' data-url='getPGImgStream.do?filename="+worksData[i].FILE_NAME+"' onclick='switchImgDiv(this)'></div>";
				}
			};
			$("#thumb-container").html(str);
			//图片懒加载
			lazyLoadImg();
		});
		$(document).keydown(function(event){
			if (event.keyCode==37) {
				preImg();
			} else if (event.keyCode==39) {
				nextImg();
			}
		});
	});
	
	
	//改变审核状态
	function changeExamineStatus(obj){
		var objtype=$(obj).attr("data-type");
		var title="";
		var auditstatus ="";
		var auditdesc ="";
		if(objtype=="W"){
			title="作品审核";
			auditdesc=auditdesc1;
			auditstatus=$("#workStatus").attr("data-status");  
		}else{
			title="解读审核";
			auditdesc=auditdesc2;
			auditstatus =$("#explanStatus").attr("data-status");  
		};
		$.post("getallauditstatus.do", {}, function(result){
			var data = JSON.parse(result);
			var html = "<form class='layui-form'><div class='radiodiv'>";
			if(data.length!=0){
				for(var i=0;i<data.length;i++){
					if(data[i].auditId==auditstatus){
						html += "<input type='radio' name='auditstatus' value='"+data[i].auditId+"' title='"+data[i].auditName+"' checked>";
					}else{
						html += "<input type='radio' name='auditstatus' value='"+data[i].auditId+"' title='"+data[i].auditName+"'>";
					}
				}
			}
			html += "</div></form><div class='auditdescdiv'><div class='title'>问题描述：</div><textarea id='tareadesc'>"+auditdesc+"</textarea></div>";
			layer.open({
				id:"divAuditPg",
				title:title,
				type: 1,
				area: ["500px", "350px;"],
				shade:[0.5 , "#000" , true],
				resize:false,
				btn: ["确定", "取消"],
				btnAlign: "c",
				tipsMore: true,
				content: html,
				yes:function(index, layero){
					var auditdesc = $("#tareadesc").val();
					if($("input[name='auditstatus']:checked").val()==2 && auditdesc==""){
						layuiLayerMsg("驳回时，请填写驳回原因！");
						return false;
					}
					var AUDIT_STATUS=$("input[name='auditstatus']:checked").val();
					 $.ajax({
						url:"changeExamineStatus.do",
						type:"post",
						dataType:"text",
						async:false,
						data:{"Workid":operWorkId,"AUDIT_STATUS":AUDIT_STATUS,"AUDIT_DESC":auditdesc,"AUDIT_PERSN":userId,"type":objtype}, //AUDIT_PERSN 审核人ID
						success:function(data){
							 if(data!=null&&data=="true"){
								 layuiLayerMsg("操作成功!!");
								 if(objtype=="W"){
									$("#workStatus").html($("input[name='auditstatus']:checked").attr("title"));
									$("#workStatus").attr("data-status",AUDIT_STATUS); 
									auditdesc1=auditdesc;
								 }else{
									$("#explanStatus").html($("input[name='auditstatus']:checked").attr("title"));
									$("#explanStatus").attr("data-status",AUDIT_STATUS);
									auditdesc2=auditdesc;
								 };
								 layer.close(index);
							 }else{
								 layuiLayerMsg("请重试!!");
							 };
							}
						});
				},
				success: function(layero, index){
					 form.render();
				},
			});
		});
 };
	
	/*老的审核状态修改*/
	function abc(){
		layer.open({
			  type:1
			  ,title:title	
			  ,skin: 'userdifined-btn'
			  ,id:"ExamineStatus"
			  ,content: "<textarea	id='statusReason' placeholder='请输入原因' class='layui-textarea' style='width:98%;margin-left:1%;'></textarea>"
			  ,btn: ['通过', '驳回',]
			  ,success:function(index,layero){
			  	layer.style(layero, {
					  width: '200px',
					  height:'200px',
					  left:50,
					  top:($(window).height()-200)/2,
				});
				
			  }
			  ,btn1: function(index, layero){
				  var str =$("#statusReason").val().trim();
				  $.ajax({
						url:"changeExamineStatus.do",
						type:"post",
						dataType:"text",
						async:false,
						data:{"Workid":operWorkId,"AUDIT_STATUS":3,"AUDIT_DESC":str,"AUDIT_PERSN":userId,"type":objtype}, //AUDIT_PERSN 审核人ID
						success:function(data){
							 if(data!=null&&data=="true"){
								 layuiLayerMsg("操作成功!!");
								  layer.close(index);
								  if(objtype=="W"){
								  	$("#workStatus").html("待发布");
								  }else{
								  	$("#explanStatus").html("待发布");
								  }
							 }else{
								 layuiLayerMsg("请重试!!");
							 };
							}
						});
				  return false;
			  }
			  ,btn2: function(index, layero){
				  var str =$("#statusReason").val().trim();
				  if(str==""){
					  layuiLayerMsg("驳回原因不能为空！！！");
					  return false;
				  }
				  $.ajax({
						url:"changeExamineStatus.do",
						type:"post",
						dataType:"text",
						async:false,
						data:{"Workid":operWorkId,"AUDIT_STATUS":2,"AUDIT_DESC":str,"AUDIT_PERSN":userId,"type":objtype}, //AUDIT_PERSN 审核人ID
						success:function(data){
							 if(data!=null&&data=="true"){
								 layuiLayerMsg("操作成功!!");
								  layer.close(index);
								  $("#textStatus").html("驳回");
								  if(objtype=="W"){
								  	$("#workStatus").html("驳回");
								  }else{
								  	$("#explanStatus").html("驳回");
								  }
							 }else{
								 layuiLayerMsg("请重试!!");
							 };
							}
						});
				  return false;
			  }
			});
	
	}
	
	function adjust(){
		startTransX=($(".theater-thumb").width()-180)/2;
		$(".theater-thumb .thumb-container").css("left",(startTransX-operNum*116));
	};
	
	
	//调整到对应的Img
	function changeImgNum(obj){
		var num = $(obj).val().trim();
		var reg =/^\d*$/;
      	if(!reg.test(num)){
      		layuiLayerMsg("请输入大于0的数字");
      		$(obj).val(operNum+1);
      		return;
      	};
      	if(num>AllNum){
      		num =AllNum;
      	};
      	$(".thumb-container .thumbImg").eq(num-1).trigger("click");
	};
	
	
	//根据作品id获取作品的详细信息
	function getWorkDesc(isSwitch,obj,curWorkId){
		$.ajax({
			 url:"getWorkDesc.do",
			 type:"post",
			 dataType:"json",
			 data:{"Workid":curWorkId}, 
			 success:function(data){
			 	operWorkId = curWorkId;
				if(data!=null){
					var data1=data.data1;
					var data2=data.data2;
					 //表单初始赋值
						if(audit==0){
						 	 form.val("example", {
							    "works_name": data1.WORKS_NAME 
							    ,"works_type": data1.WORKS_TYPE
							    ,"shoot_date": data1.SHOOT_DATE
							    ,"works_intro": data1.WORKS_INTRO
							    ,"shoot_proc":data1.SHOOT_PROC
							    ,"is_repre_works": data1.IS_REPRE_WORKS+""
							  });
						}else{
							form.val("example", {
							    "works_name": data1.WORKS_NAME 
							    ,"works_type": data1.WORKS_TYPE
							    ,"shoot_date": data1.SHOOT_DATE
							    ,"works_intro": data1.WORKS_INTRO
							    ,"shoot_proc":data1.SHOOT_PROC
							  });
							  
							  if(data1.IS_REPRE_WORKS=="1"){
							  	$(".radioDiv").html("是");
							  }else{
							  	$(".radioDiv").html("否");
							  }
						};
					  
					  var str="";
					  for(var i=0;i<data2.length;i++){
					  	str+="<li data-labelId='"+data2[i].LABEL_ID+"'><div class='worksLabel'><span>"+data2[i].LABEL_NAME+"</span><i class='layui-icon close-icon' onclick='deleteLabel(this)'>&#x1007;</i></div></li>";
					  };
					   $("#ulworksLabels").html(str);
					   if(audit==1){
					   		$(".close-icon").css("display","none");
					   };
					   
					   if(isSwitch){
					   		$(".thumb-container").velocity({ left:(startTransX-operNum*116)},{ duration: 1000,begin:function(){
								$(".show").removeClass("show");
								$("#bigImg").attr("src",$(obj).attr("data-url"));
								$("#fullbigImg").attr("src",$(obj).attr("data-url"));
								$(obj).addClass("show");
								$("#curNum").val(operNum+1);
								//
								lazyLoadImg();
								},
								complete:function(){
									//
									lazyLoadImg();
									canSwitch=true;
								},
							});
					   
					   };
					   //审核状态
					$("#workStatus").html(data.data3[0]);
					$("#workStatus").attr("data-status",data.data3[1]);
					$("#explanStatus").html(data.data4[0]);
					$("#explanStatus").attr("data-status",data.data4[1]);
					auditdesc1=data.data5[0];
					auditdesc2=data.data5[1];
				}else{
					if(audit==0){
						 	 form.val("example", {
							    "works_name": "" 
							    ,"works_type": ""
							    ,"shoot_date": ""
							    ,"works_intro": ""
							    ,"shoot_proc":""
							    ,"is_repre_works": null
							  });
						}else{
							form.val("example", {
							   "works_name": "" 
							    ,"works_type": ""
							    ,"shoot_date": ""
							    ,"works_intro": ""
							    ,"shoot_proc":""
							  });
							  $(".radioDiv").html("");
						};
					 $("#ulworksLabels").html("");
					 //审核状态
					$("#workStatus").html("");
					$("#workStatus").attr("data-status","");
					$("#explanStatus").html("");
					$("#explanStatus").attr("data-status","");
					auditdesc1="";
					auditdesc2="";
				};
			 }
		 });
	};
	
	//底部div切换
	function switchImgDiv(obj){
		if(!canSwitch){
			return;
		};
		//正在操作的图片顺序
		operNum = $(obj).index();
		if($(obj).hasClass("show")){
			return;
		};
		canSwitch = false;
		getWorkDesc(true,obj,$(obj).attr("data-workId"));
		setTimeout(function(){
			canSwitch = true;
		},5000);
	}
	
	//图片懒加载
	function lazyLoadImg(){
		var windowWidth= $(window).width();
		$(".thumb-container .lazyload").filter("div[data-status='0']").each(function(i){
			var left = $(this).offset().left;
			if((left>-300)&&(left<(windowWidth+200))){
				$(this).css("background-image","url("+$(this).attr("data-url")+")");
				$(this).attr("data-status","1");
			};
		});
	};
	
	//显示上一张图片
	function preImg(){
		if(operNum==0){
			return;
		};
		$(".thumb-container .thumbImg").eq(operNum-1).trigger("click");
	};
	
	//显示下一张图片
	function nextImg(){
		if(operNum==AllNum){
			return;
		};
		$(".thumb-container .thumbImg").eq(operNum+1).trigger("click");
	};
	
	
	//关闭全屏图片显示
	function closefullScreenDiv(){
		$(".fullScreenDiv").hide();
	};
	
	//全屏显示图片
	function showfullScreenDiv(){
		$(".fullScreenDiv").show();
		//$(".fullScreenDiv").css("display","table-cell");
	};
	
	function zoomFullPic() {
		var zoomFlag = $("#iconZoom").attr("zoomFlag");//0--缩小，1--放大
		if (zoomFlag==0) {
			$("#iconZoom").html("&#xe826;");
			$("#iconZoom").attr("title","放大");
			$("#iconZoom").attr("zoomFlag","1");
			$("#fullbigImg").addClass("fullbigImg2");
		} else {
			$("#iconZoom").html("&#xe822;");
			$("#iconZoom").attr("title","缩小");
			$("#iconZoom").attr("zoomFlag","0");
			$("#fullbigImg").removeClass("fullbigImg2");
		}
		
	}
</script>
</html>