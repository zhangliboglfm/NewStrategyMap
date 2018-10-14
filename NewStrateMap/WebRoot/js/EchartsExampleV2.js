 var isIE8=false;
//判断浏览器是否为IE8
$(document).ready(function(){ 
    var browser = isIE8browser();
    if(browser==="IE8"){
        isIE8=true;
    }
    else{ isIE8=false;}
});
//河北地图着色
function heBeiMap(data,chartId){
	var data1=data["globalIndex"];
	var data2=data["apartIndex"];
	var splist =[
	             	{start:data1[0]["BLUE_COLOR_MIN"], color: "rgba(255,99,71,0.8)"},//'#58da71'rgba(0, 149, 203,1)
	             	{start:data1[0]["LIGHT_BLUE_MIN"], end:data1[0]["LIGHT_BLUE_MAX"], color: 'rgba(255,215,0,0.8)'},//'#ffd854'rgba(10, 69, 109,0.9)
	             	{start:data1[0]["LIGHT_COLOR_MIN"], end:data1[0]["LIGHT_COLOR_MAX"], color: 'rgba(154,205,50,0.8)'}//'#e5594c'rgba(10, 69, 109,0.3)
	             ];
	var listItem=[];
	if(data2!=null){
		for(var i=0;i<data2.length;i++){
			listItem.push({"name":data2[i]["REGION_NAME"],"value":data2[i]["MM_VAL"],"region_id":data2[i]["REGION_ID"],"indexValue":data2[i].indexValue})
		};
	};
    $.get('js/mapjson/hebei.json', function (hebeiJson) {
       var aaa= echarts.registerMap('hebei', hebeiJson);
        var myChart = echarts.init(document.getElementById(chartId));
        myChart.setOption({
        	tooltip: {
        		formatter:function (params) {
        			htmlcode="";
        			htmlcode+= "<div class='tooltips'>"+
        							"<div class='tooltips-first'>"+params.data.name+"</div>"+
        							"<div class='tooltips-second'>"+
        								"<div class='tooltips-second-first'>"+data1[0]["MM_NAME"]+":</div>"+
        								"<div class='tooltips-second-second'>"+params.data.indexValue+data1[0]["MM_UNIT"]+"</div>"+
        							"</div>"+
        						"</div>";
        			return htmlcode;
        		},
        		backgroundColor:"#fff",//背景颜色
        		borderColor:"#0F89C1",//边框颜色
        		padding:0,
        		borderWidth:0
        	},
    	    dataRange: {
    	        x: '500',
    	        y: '550',
    	        splitList: splist,
    	        borderColor:'#016bea',
    	        borderWidth:1,
    	        padding:10,
    	        show:false,
    	        textStyle:{
    	        	color:'#fff',
    	        	fontSize:16
    	        }
    	    },
    	    roam: true,
            label: {
                normal: {
                    show: true
                   ,textStyle:{
                    	color:'red',
        	        	fontSize:14,
        	        	//fontWeight:'bold'
                    }
                },
                emphasis: {
                    show: true
                }
            },
            series: [{
            	 
                type: 'map',
                mapType: 'hebei',
                showLegendSymbol:false,
                scaleLimit:{max:2,min:0.8},
                mapLocation:{width:400,top:50},
                itemStyle:{
	                normal:{
	                	borderColor:"#76dcf3"
	                	,borderWidth:1
                		,label:{
	                		show:true
	                		,textStyle:{
	                			color:'#000'
	                		}
	                	},
	                	areaColor:"#0491B2"
	                	/*borderColor:new echarts.graphic.LinearGradient(0,0,1,1,[{
                            offset:0.3,
                            color:'#03E3E9'
                        },{
                            offset:1,
                            color:'#016bea'
                        }]
                        ),
	                	borderWidth:2*/
	                	/*,label:{
	                		show:true,
	                		textStyle:{
	                			color:'#000000'
	                		}
	                	}*/
	                },
	                emphasis:{
	                	areaColor:false
	                	,borderColor:"#01C2ED"
	                	,borderWidth:3
	                	,label:{show:true,textStyle:{
                			color:'#000'
                		}}
	                }
	            },
                data:listItem
                
            }]
        });
        
       myChart.on("dblclick",function(param){
    	   initdishiMap(param.data.region_id,param.type);
        });
       
       
        myChart.on("click",function(param){
        	 initdishiMap(param.data.region_id,param.type);
        });
        
    });
                    
};


//河北地市地图着色
function dishiMap(data,chartId){
	var data1=data["globalIndex"];
	var data2=data["apartIndex"];
	var splist =[
	             	{start:data1[0]["BLUE_COLOR_MIN"], color: "rgba(255,99,71,0.8)"},//'#58da71'rgba(0, 149, 203,1)
	             	{start:data1[0]["LIGHT_BLUE_MIN"], end:data1[0]["LIGHT_BLUE_MAX"], color: 'rgba(255,215,0,0.8)'},//'#ffd854'rgba(10, 69, 109,0.9)
	             	{start:data1[0]["LIGHT_COLOR_MIN"], end:data1[0]["LIGHT_COLOR_MAX"], color: 'rgba(154,205,50,0.8)'}//'#e5594c'rgba(10, 69, 109,0.3)
	            ];
	var listItem=[];
	if(data2!=null){
		for(var i=0;i<data2.length;i++){
			listItem.push({"name":data2[i]["REGION_NAME"],"value":data2[i]["MM_VAL"],"region_id":data2[i]["REGION_ID"],"indexValue":data2[i].indexValue})
		};
	}
	var name=$("#dishiselect").getSeletedText();
	var regionname="";
	if(name=="保定"){
		regionname="baoding";
	}else if(name=="沧州"){
		regionname="cangzhou";
	}else if(name=="承德"){
		regionname="chengde";
	}else if(name=="邯郸"){
		regionname="handan";
	}else if(name=="衡水"){
		regionname="hengshui";
	}else if(name=="廊坊"){
		regionname="langfang";
	}else if(name=="秦皇岛"){
		regionname="qinhuangdao";
	}else if(name=="石家庄"){
		regionname="shijiazhuang";
	}else if(name=="唐山"){
		regionname="tangshan";
	}else if(name=="邢台"){
		regionname="xingtai";
	}else if(name=="张家口"){
		regionname="zhangjiakou";
	}
	
    $.getJSON('js/mapjson/'+regionname+'.json', function (dishiJson) {
        echarts.registerMap('dishi', dishiJson);
        var myChart = echarts.init(document.getElementById(chartId));
        myChart.setOption({
        	
        	tooltip: {
        		formatter:function (params) {
        			htmlcode="";
        			htmlcode+= "<div class='tooltips'>"+
        							"<div class='tooltips-first'>"+params.data.name+"</div>"+
        							"<div class='tooltips-second'>"+
        								"<div class='tooltips-second-first'>"+data1[0]["MM_NAME"]+":</div>"+
        								"<div class='tooltips-second-second'>"+params.data.indexValue+data1[0]["MM_UNIT"]+"</div>"+
        							"</div>"+
        						"</div>";
        			return htmlcode;
        		},
        		backgroundColor:"#fff",//背景颜色
        		borderColor:"#0F89C1",//边框颜色
        		padding:0,
        		borderWidth:1
        	},
    	    dataRange: {
    	    	x: 'right',
		        y: 'bottom',
		        splitList: splist,
		        show:false,
		        backgroundColor:'rgba(0,116,255,0.2)',
		        borderColor:'#016bea',
		        borderWidth:1,
		        padding:10,
		        selectedMode:false,
		        hoverLink:false,
		        textStyle:{color: '#FFFFFF'}
    	    },
    	    roam: true,
    	    scaleLimit:{
    	    	max:6
    	    },
            label: {
                normal: {
                    show: true,
                    textStyle:{
                    	color:'white',
        	        	fontSize:14
                    }
                },
                emphasis: {
                    show: true
                }
            },
            series: [{
                type: 'map',
                mapType: 'dishi',
                showLegendSymbol:false,
                itemStyle:{
	                normal:{
	                	borderColor:"#76dcf3",
	                	borderWidth:1,
	                	areaColor:"#0491B2",
	                	color:'#000',
	                	label:{
	                		show:true,
	                		textStyle:{
	                			color:'#000'
	                		}
	                	}
	                },
	                emphasis:{
	                	areaColor:false
	                	,borderColor:"#01C2ED"
	                	,borderWidth:3
	                	,label:{show:true,textStyle:{
                			color:'#000'
                		}}
	                }
	            },
                data:listItem 
            }]
        });
        /*点击事件只切换指标数据，不加载地图*/
        myChart.on("click",function(param){
        	initxianquIndex(param.data.region_id);
         });
        
        /*切换地图到兴趣点*/
        myChart.on("dblclick",function(param){
        	changePage();
         });
    });     
};

//全国地图着色echarts
function chinaMap(listItem,chartId,paras){
    var myChart = echarts.init(document.getElementById(chartId));
    myChart.setOption({
    	tooltip: {
    		formatter:function (params) {
    			return params.value;
    		}
    	},
	    dataRange: {
	        x: '500',
	        y: '550',
	        splitList: splist,
	        borderColor:'#016bea',
	        borderWidth:1,
	        padding:10,
	        show:false,
	        textStyle:{
	        	color:'#fff',
	        	fontSize:16
	        }
	    },
	    roam: true,
        label: {
            normal: {
                show: true
                /*,textStyle:{
                	color:'white',
    	        	fontSize:16,
    	        	fontWeight:'bold'
                }*/
            },
            emphasis: {
                show: true
            }
        },
            series : [
                {
                    name: paras.lenName1,
                    type: 'map',
                    mapType: 'china',
                    //hoverable:false,
                    roam: false,
                    itemStyle:{
                        normal:{
                            borderColor:'rgba(120,120,120,1)'
                            ,borderWidth:1
                            //,areaStyle:{color:'#1b1b1b'}
                            ,label:{
                                show:true
                                ,textStyle:{
                                    color:'#000'
                                }
                            }
                        },
                        emphasis:{label:{show:true}}
                    },
                    data:listItem
                }
            ]
        });
                            
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
