function initStackBarGraph(chartId,data){
	var myChart = echarts.init(document.getElementById(chartId));	
	option = {
		color: ['#FE3404','#7A14F3','#6EB6FF'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data: [ '平均办理时长','平均等待时长','平均停留时长'],
	        textStyle:{
	        	color:'#08C3CA',
	        	fontSize:"16"
	        }
	    
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis:{
	        type: 'value',
	        axisLabel:{
	        	textStyle:{
		        	color:'#08C3CA',
		        	fontSize:"16"
		        }
	        },
	       /* axisLine:{
	        	lineStyle:{
	        		color:"#00ffff",		//设置坐标轴的轴线
	        		width:3
	        	}
	        },*/
	        axisTick:{
	        	show:false
	        },
	        splitLine:{
	        	show:false,
	        }
	    },
	    yAxis: {
	        type: 'category',
	        data: data.regionname,
	        axisLabel:{
	        	textStyle:{
		        	color:'#08C3CA',
		        	fontSize:"16"
		        }
	        },
	        splitLine:{
	        	show:false,
	        }
	    },
	    series: [
	        {
	            name: '平均办理时长',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'insideRight'
	                }
	            },
	            data:data.accetime
	        },
	        {
	            name: '平均等待时长',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'insideRight'
	                }
	            },
	            data:data.waittime
	        },
	        {
	            name: '平均停留时长',
	            type: 'bar',
	            /*stack: '总量',*/
	            label: {
	                normal: {
	                    show: true,
	                    position: 'insideRight'
	                }
	            },
	            data:data.stoptime
	        },
	    ]
	};
	  myChart.setOption(option);
}


/*气泡象限图*/
function initScatter(chartId,data){
	 var myChart = echarts.init(document.getElementById(chartId));
	 option = {
			    legend:{
			    	/*data:["总体","升档","降档"],*/
			    	data:["升档","降档"],
			    	textStyle:{
			    		color:'white',
			    		fontSize:18
			    	},
			    	/*selected:{
			    		"总体":false
			    	}*/
			    	/*selectedMode:'single'*/		//改变图例显示状态，默认开启，还可设置	single单选模式 	 multiple 多选模式
			    },
			    tooltip:{
			    	formatter:function(param){
			    		var str="";
			    		str+="<div class='divtips'>"+
			    				"<div class='title'>"+param.data[3]+"</div>"+
			    				"<div class='content'>DOU&nbsp;<span class='span1'>(MB)</span>&nbsp;:&nbsp;&nbsp;<span class='span2'>"+param.data[0]+"</span></div>"+
			    				"<div class='content'>ARPU&nbsp;<span class='span1'>(元)</span>&nbsp;:&nbsp;&nbsp;<span class='span2'>"+param.data[1]+"</span></div>"+
			    				"<div class='content'>人数&nbsp;<span class='span1'>(人)</span>&nbsp;:&nbsp;&nbsp;<span class='span2'>"+param.data[2]+"</span></div>"+
			    				"</div>"
			    		return str;
			    		/*param.data[3]+"<br>"
	    				+"DOU:"+param.data[0]+"MB<br>"
	    				+"ARPU:"+param.data[1]+"元<br>"
	    				+"人数:"+param.data[2]+"个";*/
			    	},
			    	backgroundColor:'#171725',
			    	borderWidth:0,
			    	textStyle:{
			    		fontSize:16
			    	}
			    },
			    xAxis: {
			    	name:"DOU(MB)",
			    	nameGap:5,
			    	axisLine:{
			    		lineStyle:{
			    			color:'rgba(255,255,255,0.3)'
			    		}
			    	},
			    	nameTextStyle:{
			    		color:'white',
			    		fontSize:16,
			    	},
			        splitLine: {
			            lineStyle: {
			               color:'#313247'
			            }
			        },
			        axisLabel:{
			        	color:'white',
			    		fontSize:14,
			        },
			    },
			    yAxis: {
			    	name:"ARPU(元)",
			    	nameGap:5,
			    	nameTextStyle:{
			    		color:'white',
			    		fontSize:16,
			    	},
			    	axisLine:{
			    		lineStyle:{
			    			color:'rgba(255,255,255,0.3)'
			    		}
			    	},
			        splitLine: {
			            lineStyle: {
			            	 color:'#313247'
			            }
			        },
			         axisLabel:{
			        	color:'white',
			    		fontSize:14,
			        },
			        scale: true
			    },
			    series: [
				/*{
					name:"总体",
				    data: data[2],
				    type: 'scatter',
				    symbolSize: function (data) {
				        return Math.sqrt(data[2])/5;
				    },
				    label: {
				        emphasis: {
				            show: true,
				            formatter: function (param) {
				                return param.data[3];
				            },
				            position: 'top',
				            fontSize:20,
			                color:'white'
				        }
				    },
				    itemStyle: {
				        normal: {
				            shadowBlur: 10,
				            shadowColor: 'rgba(240, 240, 144, 0.5)',
				            shadowOffsetY: 5,
				            color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
				                offset: 0,
				                color: 'rgb(255, 255, 40)'
				            }, {
				                offset: 1,
				                color: 'rgb(204, 174, 47)'
				            }])
				        }
				    }
				},*/
			   {
			        data: data[1],
			        name:"升档",
			        type: 'scatter',
			        symbolSize: function (data) {
			        	var r=Math.sqrt(data[2])*3;
			        	if(r<8){
		        			return 8;
		        		}else if(r>80){
		        			return 80;
		        		}else{
		        			return r;
		        		}
			        },
			        label: {
			            emphasis: {
			                show: false,
			                formatter: function (param) {
			                    return param.data[3];
			                },
			                position: 'top',
			                fontSize:20,
			                color:'white'
			            }
			        },
			        itemStyle: {
			            normal: {
			                shadowBlur: 10,
			                shadowColor: 'rgba(25, 100, 150, 0.5)',
			                shadowOffsetY: 5,
			                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
			                    offset: 0,
			                    color: 'rgb(129, 227, 238)'
			                }, {
			                    offset: 1,
			                    color: 'rgb(25, 183, 207)'
			                }])
			            }
			        }
			    },
			    {
			    	name:"降档",
			        data: data[0],
			        type: 'scatter',
			        symbolSize: function (data) {
			        	var r=Math.sqrt(data[2])*3;
			        	if(r<8){
		        			return 8;
		        		}else if(r>80){
		        			return 80;
		        		}else{
		        			return r;
		        		}
			        },
			        label: {
			            emphasis: {
			                show: false,
			                formatter: function (param) {
			                    return param.data[3];
			                },
			                position: 'top',
			                fontSize:20,
			                color:'white'
			            }
			        },
			        itemStyle: {
			            normal: {
			                shadowBlur: 10,
			                shadowColor: 'rgba(120, 36, 50, 0.5)',
			                shadowOffsetY: 5,
			                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
			                    offset: 0,
			                    color: 'rgb(251, 118, 123)'
			                }, {
			                    offset: 1,
			                    color: 'rgb(204, 46, 72)'
			                }])
			            }
			        }
			    }
			    ]
			};
	 
	 myChart.setOption(option);
	 myChart.on("click",function(param){
		//param.seriesId  0 升档   1降档
		pageX=window.event.pageX;
		pageY=window.event.pageY;
		$("#echartsdesc .title span").html(param.data[3]);
		if(pageX>360){
			$("#echartsdesc").css({"left":(pageX-360)+"px","top":(pageY-320)+"px"});
		}else{
			$("#echartsdesc").css({"left":(pageX)+"px","top":(pageY-320)+"px"});
		}
		$("#echartsdesc").show();
		initDescrScatter(param.data[4],param.data[5],param.seriesIndex,param.data[6],param.data[7])
	 });
}

function initScatter1(chartId,data){
	 var myChart = echarts.init(document.getElementById(chartId));
	 option = {
			 tooltip:{
				 formatter:function(param){
			    		return "DOU&nbsp;(MB)&nbsp:"+param.data[0]+"<br>"
	    				+"ARPU&nbsp;(元)&nbsp:"+param.data[1]+"<br>"
	    				+"人数&nbsp;(个)&nbsp:"+param.data[2];
			    	},
			    	backgroundColor:'#171725',
			    	borderWidth:0,
			    	textStyle:{
			    		fontSize:12
			    	}
			 },
			    grid:{
			    	right:"18%"
			    },
			    xAxis: {
			    	name:"DOU(MB)",
			    	nameGap:0,
			    	axisLine:{
			    		lineStyle:{
			    			color:'rgba(255,255,255,0.3)'
			    		}
			    	},
			    	nameTextStyle:{
			    		color:'white',
			    		fontSize:12,
			    	},
			        splitLine: {
			            lineStyle: {
			               color:'#313247'
			            }
			        },
			        axisLabel:{
			        	color:'white',
			    		fontSize:12,
			        },
			    },
			    yAxis: {
			    	name:"ARPU(元)",
			    	nameGap:5,
			    	nameTextStyle:{
			    		color:'white',
			    		fontSize:12,
			    	},
			    	axisLine:{
			    		lineStyle:{
			    			color:'rgba(255,255,255,0.3)'
			    		}
			    	},
			        splitLine: {
			            lineStyle: {
			            	 color:'#313247'
			            }
			        },
			         axisLabel:{
			        	color:'white',
			    		fontSize:12,
			        },
			        scale: true
			    },
			    series: [{
			        data: data,
			        type: 'scatter',
			        symbolSize: function (data) {
			        	var r=Math.sqrt(data[2])*2;
			        	if(r<8){
		        			return 8;
		        		}else if(r>40){
		        			return 40;
		        		}else{
		        			return r;
		        		}
			        },
			        label: {
			            emphasis: {
			                show: false,
			                formatter: function (param) {
			                    return param.data[3];
			                },
			                position: 'top',
			                fontSize:14,
			                color:'white'
			            }
			        },
			        itemStyle: {
			        	 normal: {
				                shadowBlur: 10,
				                shadowColor: 'rgba(25, 100, 150, 0.5)',
				                shadowOffsetY: 5,
				                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
				                    offset: 0,
				                    color: 'rgb(129, 227, 238)'
				                }, {
				                    offset: 1,
				                    color: 'rgb(25, 183, 207)'
				                }])
				            }
			        }
			    }]
			};
	 
	 myChart.setOption(option);
}
function initScatter2(chartId,data){
	 var myChart = echarts.init(document.getElementById(chartId));
	 option = {
			 tooltip:{
				 formatter:function(param){
			    		return "DOU&nbsp;(MB)&nbsp:"+param.data[0]+"<br>"
	    				+"ARPU&nbsp;(元)&nbsp:"+param.data[1]+"<br>"
	    				+"人数&nbsp;(个)&nbsp:"+param.data[2];
			    	},
			    	backgroundColor:'#171725',
			    	borderWidth:0,
			    	textStyle:{
			    		fontSize:12
			    	}
			 },
			  grid:{
			    	right:"18%"
			    },
			 xAxis: {
				 name:"DOU(MB)",
				 nameGap:0,
				 axisLine:{
					 lineStyle:{
						 color:'rgba(255,255,255,0.3)'
					 }
				 },
				 nameTextStyle:{
					 color:'white',
					 fontSize:12,
				 },
				 splitLine: {
					 lineStyle: {
						 color:'#313247'
					 }
				 },
				 axisLabel:{
					 color:'white',
					 fontSize:12,
				 },
			 },
			 yAxis: {
				 name:"ARPU(元)",
				 nameGap:5,
				 nameTextStyle:{
					 color:'white',
					 fontSize:12,
				 },
				 axisLine:{
					 lineStyle:{
						 color:'rgba(255,255,255,0.3)'
					 }
				 },
				 splitLine: {
					 lineStyle: {
						 color:'#313247'
					 }
				 },
				 axisLabel:{
					 color:'white',
					 fontSize:12,
				 },
				 scale: true
			 },
			 series: [ {
			        data: data,
			        type: 'scatter',
			        symbolSize: function (data) {
			        	var r=Math.sqrt(data[2])*2;
			        	if(r<8){
		        			return 8;
		        		}else if(r>40){
		        			return 40;
		        		}else{
		        			return r;
		        		}
			        },
			        label: {
			            emphasis: {
			                show: false,
			                formatter: function (param) {
			                    return param.data[3];
			                },
			                position: 'top',
			                fontSize:14,
			                color:'white'
			            }
			        },
			        itemStyle: {
			            normal: {
			                shadowBlur: 10,
			                shadowColor: 'rgba(120, 36, 50, 0.5)',
			                shadowOffsetY: 5,
			                color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
			                    offset: 0,
			                    color: 'rgb(251, 118, 123)'
			                }, {
			                    offset: 1,
			                    color: 'rgb(204, 46, 72)'
			                }])
			            }
			        }
			    }]
	 };
	 
	 myChart.setOption(option);
}


function initParallelCoordinate(chartId,data){
	var myChart = echarts.init(document.getElementById(chartId));	
	
	// Schema:
	// date,AQIindex,PM2.5,PM10,CO,NO2,SO2
	

	var schema = [
	    {name: 'regionId', index: 0, text: '地域'},
	    {name: 'AQIindex', index: 1, text: '产品变更'},
	    {name: 'PM25', index: 2, text: '开户'},
	    {name: 'PM10', index: 3, text: '宽带'},
	];

	var lineStyle = {
	    normal: {
	        width: 2,
	        opacity: 1
	    }
	};

	option = {
	    /*legend: {
	        bottom: 30,
	        data: ['北京', '上海', '广州'],
	        itemGap: 20,
	        textStyle: {
	            color: '#fff',
	            fontSize: 14
	        }
	    },*/
	 
	    // dataZoom: {
	    //     show: true,
	    //     orient: 'vertical',
	    //     parallelAxisIndex: [0]
	    // },
	    parallelAxis: [
	        {dim: 0, name: schema[0].text, type: 'category', data: ['石家庄', '秦皇岛', '张家口', '廊坊', '唐山', '衡水','保定','承德','邢台','邯郸','沧州']},
	        {dim: 1, name: schema[1].text},
	        {dim: 2, name: schema[2].text},
	        {dim: 3, name: schema[3].text},
	    ],
	    visualMap: {
	        show: false,
	        min: 0,
	        max: 150,
	        dimension: 2,
	        inRange: {
	            color: ['#d94e5d','#eac736','#50a3ba'].reverse(),
	            // colorAlpha: [0, 1]
	       }
	    },
	    parallel: {
	        left: '5%',
	        right: '18%',
	        bottom: 100,
	        parallelAxisDefault: {
	            type: 'value',
	            name: 'AQI指数',
	            nameLocation: 'end',
	            nameGap: 20,
	            nameTextStyle: {
	                color: '#fff',
	                fontSize: 12
	            },
	            axisLine: {
	                lineStyle: {
	                    color: '#aaa'
	                }
	            },
	            axisTick: {
	                lineStyle: {
	                    color: '#777'
	                }
	            },
	            splitLine: {
	                show: false
	            },
	            axisLabel: {
	                textStyle: {
	                    color: '#fff'
	                }
	            }
	        }
	    },
	    series: [
	        {
	            /*name: '北京',*/
	            type: 'parallel',
	            lineStyle: lineStyle,
	            data: data
	        }
	    ]
	};
	
	myChart.setOption(option);
}


function initAreaChart(chartId,data){
	var myChart = echarts.init(document.getElementById(chartId));
	 var option = {
		    	//图表的标题
		        /*title: {
		            text: '标准折线面积图'
		        },*/
		        //图表展示的类别，这里的data和series中得name对应
		       /* legend: {
		            data: ['意向', '预购', '成交']
		        },*/
		        //这里的是采用什么方式触发数据，这里显示的竖轴，跟随鼠标移动到节点上会显示内容
		        tooltip: {
		            trigger: 'axis',
		            axisPointer:{
		            	type:'cross',
		            	crossStyle:{
		            		color:'#ee456c',
		            	}
		            },
		        },
		      /*  color: new echarts.graphic.LinearGradient(0,0,1,0, [{
                    offset: 0,
                    color: '#a826ff'
                }, {
                    offset: 1,
                    color: '#46befd'
                }]),*/
		        //代表x轴，这里type类型代表字符
		        xAxis:{
		            type: 'category',
		            boundaryGap: false,//这里表示是否补齐空白
		            data:data.name,
		            axisLabel:{
			        	textStyle:{
				        	color:'white',
				        	fontSize:"12"
				        },
				        formatter:function(params,index){
				        	return params;
				        }
			        },
			        axisLine:{
			        	lineStyle:{
			        		color:'#313247',
			        	}
			        },
			        splitLine:{
			        	lineStyle:{
			        		color:'#313247',
			        	}
			        }
		        },
		        //代表y轴，这里type类型代表num类型
		        yAxis: {
		            type: 'value',
		            axisLabel:{
			        	textStyle:{
				        	color:'white',
				        	fontSize:"12"
				        }
			        },
			        axisLine:{
			        	lineStyle:{
			        		color:'#313247',
			        	}
			        },
			        splitLine:{
			        	lineStyle:{
			        		color:'#313247',
			        	}
			        }
		        },
		        //图表类型，type表示按照什么类型图表显示，line代表折线，下面的内容与
		        //legend一一对应
		        series:[{
		            		type: 'line',
		            		smooth: true,//设置是否平滑曲线显示
		            		smoothMonotone:'x',//折线平滑后是否在一个维度上面保持单调性
		            		symbol:'circle',	//标记的图形
		            		symbolSize:6,
		            		sampling:'average',//去过滤点的平均值，可以优化绘图效率
		            		itemStyle: {//折线拐点的样式
		            			normal: {
		            	            color: '#00f0ff',
		            	            borderWidth:0,
		            	        }
		            		},
		            		areaStyle: {//区域填充样式
		            	        normal: {
		            	            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		            	                offset: 0,
		            	                color: 'rgba(0,240,255,1)'
		            	            }, {
		            	                offset: 1,
		            	                color: 'rgba(0,240,255,0.1)'
		            	            }])
		            	        }
		            	    },
		            		data:data.data
		        }]
		    };
		    //配置图表设置并读取
	  myChart.setOption(option);
}function initAreaChart(chartId,data){
	var myChart = echarts.init(document.getElementById(chartId));
	 var option = {
		    	//图表的标题
		        /*title: {
		            text: '标准折线面积图'
		        },*/
		        //图表展示的类别，这里的data和series中得name对应
		       /* legend: {
		            data: ['意向', '预购', '成交']
		        },*/
		        //这里的是采用什么方式触发数据，这里显示的竖轴，跟随鼠标移动到节点上会显示内容
		        tooltip: {
		            trigger: 'axis',
		            axisPointer:{
		            	type:'cross',
		            	crossStyle:{
		            		color:'#ee456c',
		            	}
		            },
		        },
		      /*  color: new echarts.graphic.LinearGradient(0,0,1,0, [{
                   offset: 0,
                   color: '#a826ff'
               }, {
                   offset: 1,
                   color: '#46befd'
               }]),*/
		        //代表x轴，这里type类型代表字符
		        xAxis:{
		            type: 'category',
		            boundaryGap: false,//这里表示是否补齐空白
		            data:data.name,
		            axisLabel:{
			        	textStyle:{
				        	color:'white',
				        	fontSize:"12"
				        },
				        formatter:function(params,index){
				        	return params;
				        }
			        },
			        axisLine:{
			        	lineStyle:{
			        		color:'#313247',
			        	}
			        },
			        splitLine:{
			        	lineStyle:{
			        		color:'#313247',
			        	}
			        }
		        },
		        //代表y轴，这里type类型代表num类型
		        yAxis: {
		            type: 'value',
		            axisLabel:{
			        	textStyle:{
				        	color:'white',
				        	fontSize:"12"
				        }
			        },
			        axisLine:{
			        	lineStyle:{
			        		color:'#313247',
			        	}
			        },
			        splitLine:{
			        	lineStyle:{
			        		color:'#313247',
			        	}
			        }
		        },
		        //图表类型，type表示按照什么类型图表显示，line代表折线，下面的内容与
		        //legend一一对应
		        series:[{
		            		type: 'line',
		            		smooth: true,//设置是否平滑曲线显示
		            		smoothMonotone:'x',//折线平滑后是否在一个维度上面保持单调性
		            		symbol:'circle',	//标记的图形
		            		symbolSize:6,
		            		sampling:'average',//去过滤点的平均值，可以优化绘图效率
		            		itemStyle: {//折线拐点的样式
		            			normal: {
		            	            color: '#00f0ff',
		            	            borderWidth:0,
		            	        }
		            		},
		            		areaStyle: {//区域填充样式
		            	        normal: {
		            	            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		            	                offset: 0,
		            	                color: 'rgba(0,240,255,0.3)'
		            	            }, {
		            	                offset: 1,
		            	                color: 'rgba(0,240,255,0)'
		            	            }])
		            	        }
		            	    },
		            		data:data.data
		        }]
		    };
		    //配置图表设置并读取
	  myChart.setOption(option);
}


/*桑基图*/
function initSankey(chartId,data){
	//sliderAllData = data.links;
	//sliderAllnum = data.links.length;
	//sildertop(data.links);
	var myChart = echarts.init(document.getElementById(chartId));
	$.each(data.links,function(i,e){
		e.lineStyle={
			normal:{
				color:"#1b6372"
			},
			emphasis:{
            	color:"#248ba0"
            }
		};
	});
	//console.log(data.links);
	    var option = {
	        tooltip: {
	        	show:false,
	            trigger: 'item',
	            alwaysShowContent:false,
	            /*position:['50%','2%'],*/
	            backgroudColor:'#313244',
	            extraCssText:'box-shadow:0 0 10px rgba(28,29,47,0.8)',
	            formatter:function(params){
	            	if(params.dataType=="edge"){
	            		return params.data.source+"  转入     "+params.data.target+":"+params.data.value2+"元";
	            	}else{
	            		if(params.data.value != undefined){
	            			return params.data.name+":"+params.data.value+"户";
	            		}
	            	}
	            }
	        },
	        series: [
	            {
	                type: 'sankey',
	                layout:'none',
	                left: '25%',
	    	        right: '5%',
	    	        bottom: '10%',
	    	        top:'10%',
	                nodeWidth:30,
	    	    	nodeGap:30,
	                data:data.data,
	                links:data.links,
	                itemStyle: {
	                    normal: {
	                    	color:"#2BF4FE",
	                        borderWidth: 0
	                    }
	                },
	                label:{
	                	normal:{	//平常显示的文字样式
	                		position:'left',
	                		textStyle:{
		                		color:'#ffffff',
		                		fontSize:'14'
		                	}
	                	}
	                	
	                },
	                lineStyle: {
	                    normal: {
	                        curveness: 0.5,
	                        color:'#464662',
	                    },
	                    emphasis:{
	                    	color:"#1a2857"
	                    }
	                }
	            }
	        ]
	    };
	myChart.setOption(option);
	myChart.on("mouseover",function(parms){
		if(parms.dataType=="edge"){
			var height=$(".silder1").height();
			$(".sildertop").html(divConnect(parms.data));
			$(".silder1").css("line-height",height+"px");
			
			/*雷达图所需数据*/
			/*var zcid = parms.data.zcid;
			var zrid = parms.data.zrid;
			var text =parms.data.source+"&nbsp;&nbsp;转入&nbsp;&nbsp;"+ parms.data.target+"&nbsp;&nbsp;"+"升降档情况";*/
			/*initFeeData(zcid,zrid,text+"用户消费情况");
			initFluxData(zcid,zrid,text+"用户流量使用情况");*/
			
			/*雷达图数据*/
			/*getRadardata(zcid,zrid,text);*/
			
			/*clearInterval(timer1);
			timerout=setTimeout(function(){
				sildertop(sliderAllData);
			},5000);*/
			//sildertop(parms.data);
		}
		//console.log(parms);
	});
	myChart.on("mouseout",function(parms){
		$(".sildertop").html("");
	});
	
}

function sildertop(data){
	//clearInterval(timer1);
	var num = 0;
	var n=data.length;
	$(".sildertop").html(divConnect(data[num]))
	var height=$(".silder1").height();
	$(".silder1").css("line-height",height+"px");
	//clearInterval(timer1);
/*	timer1=setInterval(function(){ //打开定时器
			num++;    
			if(num>n-1){
				iNow++;    
	  			if(iNow>tableNum-1){ 
	  				iNow=0;
	  			}
	  			tablecontent.eq(iNow).trigger("click"); //模拟触发数字按钮的click
	  			num = 0;
			}else{
				$(".sildertop").html(divConnect(data[num])); //模拟触发数字按钮的click
				var zcid = data[num].zcid;
				var zrid = data[num].zrid;
				var text = data[num].source+" 转至 "+data[num].target+" ";
				//console.log(num+"   "+text);
				initFeeData(zcid,zrid,text+"用户消费情况");
				initFluxData(zcid,zrid,text+"用户流量使用情况");
				雷达图数据
				getRadardata(zcid,zrid);
				
				$(".silder1").css("line-height",height+"px");
			}
		},8000); */
}

function divConnect(data){
	var str="";
		 str+="<span class='silder1'>"+data.source+"&nbsp;&nbsp;转入&nbsp;&nbsp"+data.target+"&nbsp;&nbsp;总共&nbsp;&nbsp;<font color='#2cf3ff'>"+data.value1+"</font>&nbsp;&nbsp;户,&nbsp;&nbsp<font color='#2cf3ff'>"+data.value2+"</font>&nbsp;&nbsp;元</span>";
	 return str;
}
function init4GAreaChart(id,titletext,type,data){
	var xinterval = 10;
	var namestr = "";
	if(type=="fee"){
		xinterval=20;
		namestr = "月消费（元）";
	}else if(type=="flux"){
		xinterval=200;
		namestr = "月流量（MB）";
	}
	function getTooltip(type){
		var tstr;
		if(type=="fee"){
			tstr = '月消费{b}元:'+'{c}'+'人';
		}else if(type=="flux"){
			tstr = '月使用流量{b}M:'+'{c}'+'户';
		}
		return tstr;
	}
	var myChart = echarts.init(document.getElementById(id));
/*	var base = +new Date(1968, 9, 3);
	var oneDay = 24 * 3600 * 1000;
	var date = [];

	var data = [Math.random() * 300];

	for (var i = 1; i < 10000; i++) {
	    var now = new Date(base += oneDay);
	    date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
	    data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]));
	}*/

	option = {
	    tooltip: {
	        trigger: 'axis',
	        confine:true,
	        formatter:function(params){
	        	var toolstr = "";
	        	if(type=="fee"){
	        		if(params[0].data!=undefined){
	        			toolstr += params[0].seriesName+"消费"+params[0].name+"元:"+params[0].data+"户";
	        		}
	        		if(params[1].data!=undefined){
	        			toolstr += ","+params[1].seriesName+"消费"+params[1].name+"元:"+params[1].data+"户";
	        		}
	    		}else if(type=="flux"){
	    			if(params[0].data!=undefined){
	        			toolstr += params[0].seriesName+"使用流量"+params[0].name+"MB:"+params[0].data+"户";
	        		}
	        		if(params[1].data!=undefined){
	        			toolstr += ","+params[1].seriesName+"使用流量"+params[1].name+"MB:"+params[1].data+"户";
	        		}
	    		}
	        	return toolstr;
	        }
	    },
	    grid:{
	    	left: '5%',
	        right: '15%',
	        bottom: '5%',
	        top:'15%',
	        containLabel: true
	    },
	    /*toolbox: {
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            restore: {},
	            saveAsImage: {}
	        }
	    },*/
	    title:{
	    	show:false,
	    	text:titletext,
	    	left: 'center',
	    	top:'2%',
	    	textStyle:{
	    		color:"#fff",
	    		fontSize:16
	    	}
	    },
	    xAxis: {
	    	name:namestr,
	    	nameTextStyle:{
	    		color:"#cccccc"
	    	},
	        type: 'category',
	        boundaryGap: false,
	        splitLine:{
	        	lineStyle:{
	        		color:"#313247"
	        	}
	        },
	        data: [],
	        axisLabel:{
	        	textStyle:{
	        		color:"#cccccc",
	        	},
	        	interval:xinterval
	        }
	    },
	    yAxis: {
	    	name:"用户数（户）",
	    	nameTextStyle:{
	    		color:"#cccccc"
	    	},
	        type: 'value',
	        minInterval:1,
	       /* boundaryGap: [0, '100%'],*/				//非类目轴，数组中数值表示最大值和最小值得延伸范围
	        splitLine:{
	        	lineStyle:{
	        		color:"#313247"
	        	}
	        },
	        axisLabel:{
	        	textStyle:{
	        		color:"#cccccc",
	        	}
	        },
	        axisTick:{
	        	show:false,
	        }
	    },
	    series: [
	        {
	        	name:"本月",
	            type:'line',
	            smooth:true,
	            smoothMonotone:'x',
	            symbol: 'none',
	            sampling: 'average',
	            itemStyle: {
	                normal: {
	                    color: 'rgba(27, 159, 206,0.6)',
	                    borderWidth:0
	                }
	            },
	            areaStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                        offset: 0,
	                        color: 'rgba(7,109,227,0.25)'
	                    }, {
	                        offset: 1,
	                        color: 'rgba(34,36,79,0.25)'
	                    }])
	                }
	            },
	            data: []
	        },
	        {
	        	name:"上月",
	            type:'line',
	            smooth:true,
	            smoothMonotone:'x',
	            symbol: 'none',
	            sampling: 'average',
	            itemStyle: {
	                normal: {
	                    color: 'rgba(200,62, 98, 206,0.6)',
	                    borderWidth:0
	                }
	            },
	            areaStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                        offset: 0,
	                        color: 'rgba(174,48,97,0.25)'
	                    }, {
	                        offset: 1,
	                        color: 'rgba(51,34,59,0.25)'
	                    }])
	                }
	            },
	            data: []
	        }
	    ],
	    animation:true,
		animationDelayUpdate:function(idx){
			return idx*100;
		},
		animationEasingUpdate:"sinusoidalln"
	};
	myChart.setOption(option);
	return myChart;
}



function initEchartRadar(chartId,data){
	var myChart = echarts.init(document.getElementById(chartId));	
	var option = {
			tooltip: {
				formatter:function(param){
					var htmlcode="";
					htmlcode+="<div class='tooltipdiv'>" +
								"<div class='tooltiptitle'><span class='tooltiptitle1'>"+param.name+"</span></div>"+
								"<div class='tooltipdata'>流量上升,费用上升:  "+param.value[0]+"%</div>"+
								"<div class='tooltipdata'>流量下降,费用下降:  "+param.value[1]+"%</div>"+
								"<div class='tooltipdata'>流量上升,费用下降:  "+param.value[2]+"%</div>"+
								"<div class='tooltipdata'>流量下降,费用上升:  "+param.value[3]+"%</div>"+
								"<div class='tooltipdata-last'>流量不变,费用不变:  "+param.value[4]+"%</div>"+
								"</div>";
					return htmlcode;
				},
				backgroundColor:"#2B2C3E",//背景颜色
        		borderColor:"#0F89C1",//边框颜色
        		padding:0,
        		borderWidth:0
			},
		    legend: {
		        right: '80',
		        bottom:'5',
		        orient:'vertical',
		        itemHeight:16,
		        itemWidth:30,
		        data:data.lenged,
		        textStyle:{
		        	color:'white',
		        	fontSize:14
		        }
		    },
		    color:['#C23531','#FFF000','#E81F7E','#D48265','#03FD93'],
		    radar:{
		    			indicator: [
					           { name: '流量上升,费用上升', max: 100},
					           { name: '流量下降,费用下降', max: 100},
					           { name: '流量上升,费用下降', max: 100},
					           { name: '流量下降,费用上升', max: 100},
					           { name: '流量不变,费用不变', max: 100},
					        ],
					        center: ['35%','57%'],
					        radius:'85%',
					        name:{
					        	textStyle:{
					        		color:'white',
					        		fontSize:14
					        	}
					        },
					        splitLine:{
					        	lineStyle:{
					        		color:'rgba(0,200,255,0.5)'
					        	}
					        },
					        splitArea:{
					        	areaStyle:{
					        		color:['#125686','#144f7c','#16456d','#193a5c','1c2e49']
					        	}
					        },
					        axisLine:{
					        	lineStyle:{
					        		color:"rgba(0,200,255,0.5)"
					        	}
					        }
		            
		        },
		    series: [{
		            type: 'radar',
		            symbol:'circle',	//标记的图形
            		symbolSize:5,
		            itemStyle: {
		            	normal:{
		            		lineStyle:{
		            			width:1,
		            		}
		            	},
		            	emphasis:{
		            		lineStyle:{
		            			width:2,
		            		}
		            	}
		        
		            },
		            
		            data: (function (){
		                var res = [];
		                for (var i = 0; i <= data.ydata.length; i++) {
		                    res.push({name:data.lenged[i],value:data.ydata[i]});
		                }
		                return res;
		            })(),
		        }]
		    
		};
	  myChart.setOption(option);
}
