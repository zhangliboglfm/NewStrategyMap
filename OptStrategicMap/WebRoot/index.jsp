<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC “-//W3C//DTD HTML 4.0 Transitional//EN” >   

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<title>3d标签云</title>

<style type="text/css">
body {background: #24313d;margin: 0}

#tagsList {position: relative;width: 300px;height:300px;background:#F7FBFE;}

#tagsList a {
	position: absolute;
	top: 0px;
	left: 0px;
	font-family: Microsoft YaHei;
	color: #fff;
	font-weight: bold;
	text-decoration: none;
	color:#18F283;
	padding: 3px 6px;
}

#tagsList a:hover {
	color: #FF0000;
	letter-spacing: 2px;
}
.abc{
	width: 200px;
	height: 200px;
	border:1px solid red;
	margin-top:500px;
	padding: 10px;
	overflow:auto; 
}

</style>

</head>

<body>

<div id="tagsList"> 
    <a href="#" data-size="22" style="font-size:22px;">排名</a> 
    <a href="#" data-size="21" style="font-size:21px;">柒个我</a>  
    <a href="#" data-size="21" style="font-size:20px;">风筝</a>  
    <a href="#" data-size="19" style="font-size:19px;">手机</a>  
    <a href="#" data-size="18" style="font-size:18px;">芳华</a>  
    <a href="#" data-size="17" style="font-size:17px;">华为</a>  
    <a href="#" data-size="16" style="font-size:16px;">厦门</a>  
</div>
</body>
<script>
//半径
var radius =100;
//弧度
var dtr = Math.PI/180;

var d=300;
// 获取每个a标签的宽、高 ：offsetWidth、offsetHeight
var mcList = [];
var active = true;
var lasta = 1;
var lastb = 1;
var distr = true;
var tspeed=10;
var size=250;

var mouseX=0;
var mouseY=0;

var howElliptical=1;

var aA=null;
var oDiv=null;

window.onload=function (){
	var i=0;
	var oTag=null;
	oDiv=document.getElementById('tagsList');
	
	
	aA=oDiv.getElementsByTagName('a');
	
	for(i=0;i<aA.length;i++){
		oTag={};
		
		oTag.offsetWidth=aA[i].offsetWidth;
		oTag.offsetHeight=aA[i].offsetHeight;
		
		mcList.push(oTag);
	}
	
	sineCosine( 0,0,0 );
	
	positionAll();
	
	oDiv.onmouseover=function (){
		active=false;
	};
	
	oDiv.onmouseout=function (){
		active=true;
	};
	
	oDiv.onmousemove=function (ev){
		var oEvent=window.event || ev;

		mouseX=oEvent.clientX-(oDiv.offsetLeft+oDiv.offsetWidth/2);
		mouseY=oEvent.clientY-(oDiv.offsetTop+oDiv.offsetHeight/2);
		
		mouseX/=5;
		mouseY/=5;
	};
	setInterval(update, 30);
};

function update(){
	var a;
	var b;
	
	if(active){
		/* a = (-Math.min( Math.max( -mouseY, -size ), size ) / radius ) * tspeed;
		b = (Math.min( Math.max( -mouseX, -size ), size ) / radius ) * tspeed; */
		a= 0.02;
		b = -2.78;
	}else{
		a = lasta * 0.98;
		b = lastb * 0.98;
	}
	
	lasta=a;
	lastb=b;
	
	if(Math.abs(a)<=0.01 && Math.abs(b)<=0.01){
		return;
	}
	var c=0;
	sineCosine(a,b,c);
	for(var j=0;j<mcList.length;j++){
		var rx1=mcList[j].cx;
		var ry1=mcList[j].cy*ca+mcList[j].cz*(-sa);
		var rz1=mcList[j].cy*sa+mcList[j].cz*ca;
		
		var rx2=rx1*cb+rz1*sb;
		var ry2=ry1;
		var rz2=rx1*(-sb)+rz1*cb;
		
		var rx3=rx2*cc+ry2*(-sc);
		var ry3=rx2*sc+ry2*cc;
		var rz3=rz2;
		
		mcList[j].cx=rx3;
		mcList[j].cy=ry3;
		mcList[j].cz=rz3;
		
		per=d/(d+rz3);
		
		mcList[j].x=(howElliptical*rx3*per)-(howElliptical*2);
		mcList[j].y=ry3*per;
		mcList[j].scale=per;
		mcList[j].alpha=per;
		
		mcList[j].alpha=(mcList[j].alpha-0.6)*(10/6);
	}
	
	doPosition();
	depthSort();
}

function depthSort(){

	var i = 0;
	var aTmp = [];
	for (i = 0; i < aA.length; i++) {
		aTmp.push(aA[i]);
	}

		/* aTmp.sort(
			function (vItem1, vItem2){
				if(vItem1.cz>vItem2.cz){
					return -1;
				}else if(vItem1.cz<vItem2.cz){
					return 1;
				}else{
					return 0;
				}
			}
		); */

		for (i = 0; i < aTmp.length; i++) {
			aTmp[i].style.zIndex = i;
		}
	}

	function positionAll() {
		var phi = 0;
		var theta = 0;
		var max = mcList.length;
		var i = 0;

		var aTmp = [];

		// 创建文档碎片
		var oFragment = document.createDocumentFragment();

		//随机排序
		for (i = 0; i < aA.length; i++) {
			aTmp.push(aA[i]);
		}
		/* aTmp.sort(
			function (){
				return Math.random()<0.5?1:-1;
			}
		); */

		for (i = 0; i < aTmp.length; i++) {
			// 把创建的元素附加到文档碎片中
			oFragment.appendChild(aTmp[i]);
		}

		//最后一次性的添加的domcument中
		oDiv.appendChild(oFragment);

		for ( var i = 1; i < max + 1; i++) {
			if (distr) {
				phi = Math.acos(-1 + (2 * i - 1) / max);
				theta = Math.sqrt(max * Math.PI) * phi;
			} else {
				phi = Math.random() * (Math.PI);
				theta = Math.random() * (2 * Math.PI);
			}
			//坐标变换
			mcList[i - 1].cx = radius * Math.cos(theta) * Math.sin(phi);
			mcList[i - 1].cy = radius * Math.sin(theta) * Math.sin(phi);
			mcList[i - 1].cz = radius * Math.cos(phi);

			aA[i - 1].style.left = mcList[i - 1].cx + oDiv.offsetWidth / 2
					- mcList[i - 1].offsetWidth / 2 + 'px';
			aA[i - 1].style.top = mcList[i - 1].cy + oDiv.offsetHeight / 2
					- mcList[i - 1].offsetHeight / 2 + 'px';
		}
	}

	function doPosition() {
		var l = oDiv.offsetWidth / 2;
		var t = oDiv.offsetHeight / 2;
		for ( var i = 0; i < mcList.length; i++) {
			aA[i].style.left = mcList[i].cx + l - mcList[i].offsetWidth / 2
					+ 'px';
			aA[i].style.top = mcList[i].cy + t - mcList[i].offsetHeight / 2
					+ 'px';
			/* 	aA[i].style.fontSize=Math.ceil(aA[i].dataset.size*mcList[i].scale/3*2)+'px'; */
			aA[i].style.filter = "alpha(opacity=" + 100 * mcList[i].alpha + ")";
			aA[i].style.opacity = mcList[i].alpha;
		}
	}

	function sineCosine(a, b, c) {
		sa = Math.sin(a * dtr);
		ca = Math.cos(a * dtr);
		sb = Math.sin(b * dtr);
		cb = Math.cos(b * dtr);
		sc = Math.sin(c * dtr);
		cc = Math.cos(c * dtr);
	}
</script>
</html>

