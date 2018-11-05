<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>套餐转入转出</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link rel="stylesheet" href="css/packrollout/packrollout.css" type="text/css"></link>

<style type="text/css">
 @font-face {font-family: "iconfont";
  src: url('css/iconfont/iconfont.eot?t=1497250656883'); /* IE9*/
  src: url('css/iconfont/iconfont.eot?t=1497250656883#iefix') format('embedded-opentype'), /* IE6-IE8 */
  url('css/iconfont/iconfont.woff?t=1497250656883') format('woff'), /* chrome, firefox */
  url('css/iconfont/iconfont.ttf?t=1497250656883') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
  url('css/iconfont/iconfont.svg?t=1497250656883#iconfont') format('svg'); /* iOS 4.1- */
}

.iconfont {
  font-family:"iconfont" !important;
  font-size:16px;
  font-style:normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
	@font-face {font-family: "selIconfont";
	  src: url('plugin/selectlist/css/selectlist.iconfont.eot?t=1488875403519'); /* IE9*/
	  src: url('plugin/selectlist/css/selectlist.iconfont.eot?t=1488875403519#iefix') format('embedded-opentype'), /* IE6-IE8 */
	  url('plugin/selectlist/css/selectlist.iconfont.woff?t=1488875403519') format('woff'), /* chrome, firefox */
	  url('plugin/selectlist/css/selectlist.iconfont.ttf?t=1488875403519') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
	  url('plugin/selectlist/css/selectlist.iconfont.svg?t=1488875403519#selIconfont') format('svg'); /* iOS 4.1- */
	}
	.selIconfont {
	  font-family:"selIconfont" !important;
	  font-size:16px;
	  font-style:normal;
	  -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale;
	}
	*{font-family: Microsoft YaHei;font-size: 12px;}
	html{width: 100%;height: 100%;}
	body {width: 100%;height: 100%;overflow: hidden;margin: 0;padding: 0;text-align: center}
</style>



<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
</head>

<body >
	<div  id="packrollout" class="packrollout">
		<div class="divTable-left">
		</div> 
		<div class="divTable-right">
			<div class="top">
				<div class="MoneyUp">
					<div class="MoneyUpName">套餐提升金额:</div>
					<div class="MoneyUpNumbers">
						<div id="MoneyUp"class="MoneyUpNumber">
						</div>
					</div>
				</div>
				<div class="MoneyUp">
					<div class="MoneyUpName">套餐提升用户:</div>
					<div class="MoneyUpNumbers">
						<div id="userUp"class="MoneyUpNumber">
						</div>
					</div>
				</div>
				<!-- <div class="left"><div class="font1">套餐提升金额</div><div id="divMoney" class="font2"></div><div id="divMoneyAdd" class="font5"></div></div>
				<div class="right"><div class="font3">套餐提升用户</div><div id="divUser" class="font4"></div><div id="divUserAdd" class="font6"></div></div>-->
				<!-- <div class="left"><div class="font1">套餐提升金额</div><div id="divMoney" class="font2"></div><div id="divMoneyAdd" class="font5"></div></div>
				<div class="right"><div class="font3">套餐提升用户</div><div id="divUser" class="font4"></div><div id="divUserAdd" class="font6"></div></div> -->
			</div>
			<div class="indexdescr">
				<div class="MoneyUp">
					<div class="MoneyUpName">选中部分金额:</div>
					<div class="MoneyUpNumbers">
						<div id="apartMoney"class="MoneyUpNumber">
						</div>
					</div>
				</div>
			</div>	
			
			<div class="legend">
				<div class="tuli"><div class="one color1" rank="COL_LVL1"  onclick="changeshow(this);"></div><div id="COL_LVL1" num="0" class="fanwei">(0~500)</div><div class="two COL_LVL1_1 ">(0~500)</div></div>
				<div class="tuli"><div class="one color2" rank="COL_LVL2"  onclick="changeshow(this);"></div><div id="COL_LVL2" num="1" class="fanwei">[500~1,000)</div><div class="two COL_LVL2_1 ">[500~1,000)</div></div>
				<div class="tuli"><div class="one color3" rank="COL_LVL3"  onclick="changeshow(this);"></div><div id="COL_LVL3" num="2" class="fanwei">[1,000~1,500)</div><div class="two COL_LVL3_1 ">[1,000~1,500)</div></div>
				<div class="tuli"><div class="one color4" rank="COL_LVL4"  onclick="changeshow(this);"></div><div id="COL_LVL4" num="3" class="fanwei">[1,500~2,000)</div><div class="two COL_LVL4_1 ">[1,500~2,000)</div></div>
				<div class="tuli"><div class="one color5" rank="COL_LVL5"  onclick="changeshow(this);"></div><div id="COL_LVL5" num="4" class="fanwei">[2,000~2,500)</div><div class="two COL_LVL5_1 ">[2,000~2,500)</div></div>
				<div class="tuli"><div class="one color6" rank="COL_LVL6"  onclick="changeshow(this);"></div><div id="COL_LVL6" num="5" class="fanwei">[2,500~3,000)</div><div class="two COL_LVL6_1 ">[2,500~3,000)</div></div>
				<div class="tuli"><div class="one color7" rank="COL_LVL7"  onclick="changeshow(this);"></div><div id="COL_LVL7" num="6" class="fanwei">[3,000~3,500)</div><div class="two COL_LVL7_1 ">[3,000~3,500)</div></div>
				<div class="tuli"><div class="one color8" rank="COL_LVL8"  onclick="changeshow(this);"></div><div id="COL_LVL8" num="7" class="fanwei">[3,500~4,000)</div><div class="two COL_LVL8_1 ">[3,500~4,000)</div></div>
				<div class="tuli"><div class="one color9" rank="COL_LVL9"  onclick="changeshow(this);"></div><div id="COL_LVL9" num="8" class="fanwei">[4,000~4,500)</div><div class="two COL_LVL9_1 ">[4,000~4,500)</div></div>
				<div class="tuli"><div class="one color10" rank="COL_LVL10"  onclick="changeshow(this);"></div><div id="COL_LVL10" num="9" class="fanwei">[4,500~5,000)</div><div class="two COL_LVL10_1 ">[4,500~5,000)</div></div>
				<div class="tuli"><div class="one color11" rank="COL_LVL11"  onclick="changeshow(this);"></div><div id="COL_LVL11" num="10" class="fanwei">[5,000~5,500)</div><div class="two COL_LVL11_1 ">[5,000~5,500)</div></div>
				<div class="tuli"><div class="one color12" rank="COL_LVL12"  onclick="changeshow(this);"></div><div id="COL_LVL12" num="11" class="fanwei">[5,500~8,000)</div><div class="two COL_LVL12_1 ">[5,500~8,000)</div></div>
				<div class="tuli"><div class="one color13" rank="COL_LVL13"  onclick="changeshow(this);"></div><div id="COL_LVL13" num="12" class="fanwei">[8,000~10,000)</div><div class="two COL_LVL13_1 ">[8,000~10,000)</div></div>
				<div class="tuli"><div class="one color14" rank="COL_LVL14"  onclick="changeshow(this);"></div><div id="COL_LVL14" num="13" class="fanwei">[10,000~25,000)</div><div class="two COL_LVL14_1 ">[10,000~25,000)</div></div>
				<div class="tuli"><div class="one color15" rank="COL_LVL15"  onclick="changeshow(this);"></div><div id="COL_LVL15" num="14" class="fanwei">[25,000~50,000)</div><div class="two COL_LVL15_1 ">[25,000~50,000)</div></div>
				<div class="tuli"><div class="one color16" rank="COL_LVL16"  onclick="changeshow(this);"></div><div id="COL_LVL16" num="15" class="fanwei">[50,000~100,000)</div><div class="two COL_LVL16_1 ">[50,000~100,000)</div></div>
				<div class="tuli"><div class="one color17" rank="COL_LVL17"  onclick="changeshow(this);"></div><div id="COL_LVL17" num="16" class="fanwei">[100,000~250,000)</div><div class="two COL_LVL17_1 ">[100,000~250,000)</div></div>
				<div class="tuli"><div class="one color18" rank="COL_LVL18"  onclick="changeshow(this);"></div><div id="COL_LVL18" num="17" class="fanwei">[250,000~500,000)</div><div class="two COL_LVL18_1 ">[250,000~500,000)</div></div>
				<div class="tuli"><div class="one color19" rank="COL_LVL19"  onclick="changeshow(this);"></div><div id="COL_LVL19" num="18" class="fanwei">[500,000~1,000,000)</div><div class="two COL_LVL19_1 ">[500,000~1,000,000)</div></div>
				<div class="tuli"><div class="one color20" rank="COL_LVL20"  onclick="changeshow(this);"></div><div id="COL_LVL20" num="19" class="fanwei">[1,000,000~+∞)</div><div class="two COL_LVL20_1 ">[1,000,000~+∞)</div></div>
			</div>
			
			
		</div> 
		 	
	
		
		
	</div>
	<div id="popDivTitle" class="pop_titlediv"></div>
</body>
</html>

<script>
	var minDate="<%=request.getAttribute("minDate")%>";
	var curdate="<%=request.getAttribute("curdate")%>";
	var MoneyNum="";
	var UserNum="";
	var date="";
	$(function(){
		 
		/*调整页面的大小*/
		adjust();
		main(curdate)
	});
	
	 function main(date1){
	 	date=date1;
  		searchMoneyandUser(date1);
  		searchTabelshuju(date1);
  		searchPartMoneyAndUser();
	}
	
	/*加载表格中数据*/
	function searchTabelshuju(date1){
			$.ajax({
				url:"searchTabelshuju.do",
				type:"post",
				dataType:"json",
				data:{"date":date1},
				success:function(data){
					if(data!=null){
						pinjiebiaogeshuju(data);
					}else{
						alert("查询数据出错！！！");
					}
				}
			});
	}
	/*加载金额用户数据*/
	 function searchMoneyandUser(date1){
		if(date1==minDate){
			MoneyNum="";
			UserNum="";
		} 
		$.ajax({
				url:"searchMoneyandUser.do",
				type:"post",
				dataType:"json",
				data:{"date":date1},
				success:function(data){
					if(data!=null){
					initMonehAndUser(data);
					}else{
						alert("查询数据出错！！！");
					}
					
				}
			})
	}; 
	
	
	function pinjiebiaogeshuju(data){
		var str="";
		for(var i=0;i<data.length;i++){
				if(i==(data.length-1)){
					str+="<div class='row-last' >"
				}else{
					str+="<div class='row'>"
				}
				var obj=data[i];
				for(var j=0;j<obj.length;j++){
					 if(j==(obj.length-1)){
					 		if(i==j){
					 			str+="<div class='null-opacity-last'></div>"
					 		}else{
					 			if(obj[j].num==0){
					 				str+="<div class='null-opacity-last'></div>"
					 			}else{
					 				str+="<div class='shuju-last "+obj[j].rank+"' colorrank="+obj[j].rank+"  num="+obj[j].num+"  name1="+obj[j].name1+" name2="+obj[j].name2+" ></div>";
					 			}
					 			
					 		}
					}else{
						if(i==j){
							str+="<div class='null-opacity'></div>"
						}else{
							if(obj[j].num==0){
					 			str+="<div class='null-opacity'></div>"
					 		}else{
								str+="<div class='shuju "+obj[j].rank+"' colorrank="+obj[j].rank+"  num="+obj[j].num+"  name1="+obj[j].name1+" name2="+obj[j].name2+"  ></div>";
							}
						}
							
					}
				}
				str+="</div>"
			}
		$(".divTable-left").html(str);
		var width=($(".divTable-left").width()-41)/42;
		$(".shuju,.shuju-last,.null-opacity,.null-opacity-last").css({"width":width,"height":width});
		//$(".null-opacity,.null-opacity-last").css({"border-top":width+"px #48B7BC solid","border-left":width+"px #55E8F0 solid","width":"0px","height":"0px"});
		$(".shuju,.shuju-last").each(function(){
			var colorRank=$(this).attr("colorrank");
			if(colorRank=="COL_LVL1"){
				$(this).css("background","#6eb6ff")
			}else if(colorRank=="COL_LVL2"){
				$(this).css("background","#4ebdff");
			}else if(colorRank=="COL_LVL3"){
				$(this).css("background","#29beff");
			}else if(colorRank=="COL_LVL4"){
				$(this).css("background","#19c5ef");
			}else if(colorRank=="COL_LVL5"){
				$(this).css("background","#0dd4e6");
			}else if(colorRank=="COL_LVL6"){
				$(this).css("background","#09dcd5");
			}else if(colorRank=="COL_LVL7"){
				$(this).css("background","#0befc2");
			}else if(colorRank=="COL_LVL8"){
				$(this).css("background","#17fcab")
			}else if(colorRank=="COL_LVL9"){
				$(this).css("background","#27ff90");
			}else if(colorRank=="COL_LVL10"){
				$(this).css("background","#41ff6a");
			}else if(colorRank=="COL_LVL11"){
				$(this).css("background","#5aff48");
			}else if(colorRank=="COL_LVL12"){
				$(this).css("background","#78ff24");
			}else if(colorRank=="COL_LVL13"){
				$(this).css("background","#85ff14");
			}else if(colorRank=="COL_LVL14"){
				$(this).css("background","#98ff00")
			}else if(colorRank=="COL_LVL15"){
				$(this).css("background","#abe000");
			}else if(colorRank=="COL_LVL16"){
				$(this).css("background","#bab700");
			}else if(colorRank=="COL_LVL17"){
				$(this).css("background","#fff000");
			}else if(colorRank=="COL_LVL18"){
				$(this).css("background","#edb000");
			}else if(colorRank=="COL_LVL19"){
				$(this).css("background","#ed6300")
			}else if(colorRank=="COL_LVL20"){
				$(this).css("background","#ff0014");
			}
		
		})
	
		/*设置样式隐藏*/
		$(".fanwei:hidden").each(function(){
			var colorank =$(this).attr("id");
			$("."+colorank).toggleClass("grid-opacity");
		});
		
		
	 	$(".shuju,.shuju-last").mousemove(function(e){
			var rolldata={
				"num":$(this).attr("num"),
				"name1":$(this).attr("name1"),
				"name2":$(this).attr("name2"),
			};
			onMouseOver(e,rolldata);
		});
		$(".shuju,.shuju-last").mouseout(function(e){
			onMouseOut(e);
		});
		 
		
	};
	/*加载提升金额和用户的数据*/
	function initMonehAndUser(data){
		var money=data["money"].toString();
		var user=data["user"].toString();
		$("#MoneyUp").css("width",money.length*36);
		$("#userUp").css("width",user.length*36);
		
		var str1="";
		for(var i=0;i<money.length;i++){
			str1+="<div class='number'>"+money.substring(i,i+1)+"</div>";
		}
		$("#MoneyUp").html(str1);
		var str2="";
		for(var i=0;i<user.length;i++){
			str2+="<div class='number'>"+user.substring(i,i+1)+"</div>";
		}
		$("#userUp").html(str2);
		/* 	if(MoneyNum!=""){
				var moneyReduce=data["money"]-MoneyNum;
				var userReduce=data["user"]-UserNum;
				if(moneyReduce>=0){
					$("#divMoneyAdd").html("+"+formateNumber(moneyReduce));
					$("#divMoneyAdd").css("color","#00FF01");
				}else{
					$("#divMoneyAdd").html(formateNumber(moneyReduce));
					$("#divMoneyAdd").css("color","red");
				}
							
				if(userReduce>=0){
					$("#divUserAdd").html("+"+formateNumber(userReduce));
					$("#divUserAdd").css("color","#00FF01");
				}else{
					$("#divUserAdd").html(formateNumber(userReduce));
					$("#divUserAdd").css("color","red");
				}
				setTimeout(function(){
					$("#divMoney").html(formateNumber(data["money"]))
					$("#divUser").html(formateNumber(data["user"]));
				},3000);
							
				setTimeout(function(){
					$("#divUserAdd").html("")
					$("#divMoneyAdd").html("");
				},3000);
					MoneyNum=data["money"];
					UserNum=data["user"];
							
			}else{
				$("#divMoney").html(formateNumber(data["money"]))
				$("#divUser").html(formateNumber(data["user"]));
				MoneyNum=data["money"];
				UserNum=data["user"];
			} */
	
	}
	
	function initapartMoney(data){
		var money=data["SumMoney"].toString();
		$("#apartMoney").css("width",money.length*36);
		var str1="";
		for(var i=0;i<money.length;i++){
			str1+="<div class='number'>"+money.substring(i,i+1)+"</div>";
		}
		$("#apartMoney").html(str1);
	}
	
	/*调整页面布局*/
	function adjust(){
	    var height=$("#divIfream",window.parent.document).height()-10;
	    if(height>683){
			height=683;
		};
		$("#packrollout").height(height);
		$(".divTable-left").css({"width":height,"height":height});
		$(".divTable-right").css({"width":height-190,"height":height});
		
	}
	
	function onMouseOver(e,datajson){
		var pageX=e.pageX;
		var pageY=e.pageY;
		if(pageY<100){
			$("#popDivTitle").css("top",pageY+5+"px");
			$("#popDivTitle").css("left",pageX+20+"px");
		}else{
			$("#popDivTitle").css("top",pageY-90+"px");
			$("#popDivTitle").css("left",pageX+20+"px");
		}
		$("#popDivTitle").html(divConnect(datajson));
		$("#popDivTitle").show();
	}
	function onMouseOut(e){
		$("#popDivTitle").css("top","0px");
		$("#popDivTitle").css("left","0px");
		$("#popDivTitle").html("");
		$("#popDivTitle").hide();
	}
	function divConnect(datajson){
	
		var str="";
			 str+="<div class='divhover-title'>转入转出情况</div>"+
			 		"<div class='divhover'>"+
						"<div class='divhover-shuju'>"+
							"<div class='divhover-hang'>"+
								"<div class='divhover-shuju1'>"+datajson.name1+"</div><div class='divhover-shuju4'><i class='iconfont'>&#xe637;</i></div><div class='divhover-shuju2'>"+datajson.name2+":</div><div class='divhover-shuju3'>"+datajson.num+"</div>"+	
							"</div>"+
						"</div>"+
					"</div>";
		return str;
	}
	
	function changeshow(eve){
		var obj=$(eve);
		var colorank=obj.attr("rank");
		obj.toggleClass("colorgray");
		$("."+colorank+"_1").toggle();
		$("#"+colorank).toggle();
		$("."+colorank).toggleClass("grid-opacity");
		searchPartMoneyAndUser();
		
	}
	
	function  searchPartMoneyAndUser(){
		var date1=date;
		var rankNum=[];
		$(".fanwei").not(":hidden").each(function(){
			rankNum.push($(this).attr("num"));
		});
		var rankNum1= JSON.stringify(rankNum);
		 $.ajax({
				url:"searchPartMoneyAndUser.do",
				type:"post",
				dataType:"json",
				data:{"date":date1,"rankNum":rankNum1},
				success:function(data){
					if(data!=null){
						initapartMoney(data);
					}else{
						alert("查询数据出错！！！！");
					}
				}
			}) 
	}
	/*前台数字格式化*/
	function formateNumber(num){
		num=num.toString();
		/*判断是否有小数*/
		var xiaoshu="";
		if(num.indexOf(".")>=0){
			num1=Number(num).toFixed(3)
			xiaoshu="."+num1.substring(num1.indexOf(".")+1,num1.indexOf(".")+4)
			num=num.substring(0,num.indexOf("."));	
		}
		/*判断正负*/
		var jianhao="";
		if(num.substring(0,1)=="-"){
			num=num.substring(1,num.length);
			jianhao="-";
		}
		var num=(num||0).toString(),result="";
		while(num.length>3){
			result=","+num.slice(-3)+result;
			num=num.slice(0,num.length-3);
		}
		if(num){
			result=jianhao+num+result+xiaoshu;
		}
		return result;
	}
</script>
