 var isIE8=false;
//判断浏览器是否为IE8
$(document).ready(function(){ 
    var browser = isIE8browser();
    if(browser==="IE8"){
        isIE8=true;
    }
    else{ isIE8=false;}
});
//生成河北地市
function HbeiCityMap(data,chartId){
	var mapChart = echarts.getInstanceByDom(document.getElementById(chartId));
	if(mapChart){
		mapChart.clear();
	}
    $.get('js/mapjson/hebei.json', function (cityjson) {
    	var mapdata= echarts.registerMap('hebei', cityjson);
        var myChart = echarts.init(document.getElementById(chartId));
        myChart.setOption({
        	tooltip: {
        		formatter:function (params) {
        			var str = params.name+"<br/>"+params.seriesName+":"+params.value;
        			return str;
        		},
        		backgroundColor:"rgba(1,169,240,0.8)",//背景颜色
        		padding:[8,8,8,8],
        	},
        	visualMap:{
            	show:true,
            	left:'right',
            	min:300,
            	max:50,
            	inRange:{
            		color:["rgba(1,169,240,0.3)","rgba(1,169,240,0.6)","rgba(1,169,240,0.9)"]
            	},
            	calculable:true
            },
    	    roam: true,
            series: [{
                type: 'map',
                mapType: 'hebei',
                name:'总流量',
                top:'middle',
                left:'center',
                itemStyle:{
                	normal: {
                		borderWidth:3,
                		borderColor:"#fff"
                	},
                	emphasis: {
                		borderWidth:0,
                		borderColor:"#fff",
                		shadowOffsetX:5,
                		shadowOffsetY:5,
                		shadowBlur:2,
                		shadowColor:'rgba(0,0,0,0.3)'
	                }
	            },
	            label: {
	                normal: {
	                    show:true,
	                    padding:[8,8,8,8],
	                    backgroundColor:'rgba(0,0,0,0.5)',
	                    color:'#fff',
	                    fontSize:16
	                },
	                emphasis: {
	                	show:false,
	                    padding:[8,8,8,8],
	                    backgroundColor:'rgba(0,0,0,0.5)',
	                    color:'#fff',
	                    fontSize:18
	                }
	            },
                data:[
                      {name:'石家庄',value:200,regionid:'311350'},
                      {name:'保定',value:69,regionid:'311350'},
                      {name:'唐山',value:120,regionid:'311350'},
                      {name:'秦皇岛',value:210,regionid:'311350'},
                      {name:'廊坊',value:230,regionid:'311350'},
                      {name:'张家口',value:250,regionid:'311350'},
                      {name:'衡水',value:130,regionid:'311350'},
                      {name:'邢台',value:70,regionid:'311350'},
                      {name:'邯郸',value:90,regionid:'311350'},
                      {name:'沧州',value:98,regionid:'311350'},
                      {name:'承德',value:170,regionid:'311350'}
                      ]
                
            }]
        });
        
       /*myChart.on("dblclick",function(param){
    	   alert(2);
        });*/
        myChart.on("click",function(param){
        	changeRegionDiv(param.data.regionid,param.name,"河北");
        	HbeiCountyMap({},chartId,param.name);
        });
        
    });
};
//生成河北地市下属县区
function HbeiCountyMap(id1,id2,data,name){
	var dom = document.getElementById(id1);
	dom.style.zIndex = 99999;
	document.getElementById(id2).style.zIndex =-1;
	var mapChart = echarts.getInstanceByDom(dom);
	
	if(mapChart){
		mapChart.clear();
	}
	var regionname="";
	if(name=="保定市"||name=="保定"){
		regionname="baoding";
	}else if(name=="沧州市"||name=="沧州"){
		regionname="cangzhou";
	}else if(name=="承德市"||name=="承德"){
		regionname="chengde";
	}else if(name=="邯郸市"||name=="邯郸"){
		regionname="handan";
	}else if(name=="衡水市"||name=="衡水"){
		regionname="hengshui";
	}else if(name=="廊坊市"||name=="廊坊"){
		regionname="langfang";
	}else if(name=="秦皇岛市"||name=="秦皇岛"){
		regionname="qinhuangdao";
	}else if(name=="石家庄市"||name=="石家庄"){
		regionname="shijiazhuang";
	}else if(name=="唐山市"||name=="唐山"){
		regionname="tangshan";
	}else if(name=="邢台市"||name=="邢台"){
		regionname="xingtai";
	}else if(name=="张家口市"||name=="张家口"){
		regionname="zhangjiakou";
	}else if(name=="雄安市"||name=="雄安"){
		regionname="xiongan";
	}
	
    $.get('js/mapjson/'+regionname+'.json', function (countyjson) {
    	var mapdata= echarts.registerMap('hebei', countyjson);
        var myChart = echarts.init(document.getElementById(id1));
        myChart.setOption({
        	backgroundColor:"#FFFFFF",
        	tooltip: {
        		formatter:function (params) {
        			var str = params.name+"<br/>"+params.seriesName+":"+params.value;
        			return str;
        		},
        		backgroundColor:"rgba(1,169,240,0.8)",//背景颜色
        		padding:[8,8,8,8],
        		
        	},
        	visualMap:{
            	show:false,
            	left:'right',
            	min:300,
            	max:50,
            	inRange:{
            		color:["rgba(1,169,240,0.3)","rgba(1,169,240,0.6)","rgba(1,169,240,0.9)"]
            	},
            	calculable:true
            },
    	    roam: true,
            series: [{
                type: 'map',
                mapType: 'hebei',
                name:'总流量',
                zoom:1,
                top:'middle',
                left:'center',
                itemStyle:{
                	normal: {
                		borderWidth:3,
                		borderColor:"#fff"
                	},
                	emphasis: {
                		borderWidth:0,
                		borderColor:"#fff",
                		shadowOffsetX:5,
                		shadowOffsetY:5,
                		shadowBlur:2,
                		shadowColor:'rgba(0,0,0,0.3)'
	                }
	            },
	            label: {
	                normal: {
	                    show:true,
	                    padding:[4,4,4,4],
	                    backgroundColor:'rgba(0,0,0,0.5)',
	                    color:'#fff',
	                    fontSize:12
	                },
	                emphasis: {
	                	show:false,
	                    padding:[4,4,4,4],
	                    backgroundColor:'rgba(0,0,0,0.5)',
	                    color:'#fff',
	                    fontSize:12
	                }
	            },
                data:[]
                
            }]
        });
        
       /*myChart.on("dblclick",function(param){
    	   alert(2);
        });*/
        myChart.on("click",function(param){
        	window.location.href="keyarea.do";
        });
        
    });
}
//改变地域选框
function changeRegionDiv(id,name,pname){
	$("#divRegion").setmiapRegion(id,name,pname);
}
//判断浏览器类型 若为IE8 
function isIE8browser(){ 
     var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
     var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
     var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
     if (isIE) {
        var IE5 = IE55 = IE6 = IE7 = IE8 = false;
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        IE55 = fIEVersion == 5.5;
        IE6 = fIEVersion == 6.0;
        IE7 = fIEVersion == 7.0;
        IE8 = fIEVersion == 8.0;
        if (IE55) {
            return "IE55";
        }
        if (IE6) {
            return "IE6";
        }
        if (IE7) {
            return "IE7";
        }
        if (IE8) {
            return "IE8";
        }
    }else{ 
        return "notIE8";
    }

}
