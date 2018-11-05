/* 加载地市图表 */
function initRegionCharts(domid,data){
	var mychart = echarts.init(document.getElementById(domid));
	var option = {
	    title: {
	        show:false
	    },
	    legend: {
	        show:false
	    },
	    grid:{
	    	left: '5%',
	        right: '20%',
	        bottom: '0%',
	        top:'5%',
	        containLabel: true
	    },	
	    xAxis: [
			{
			    type: 'value',
			    name:"办理量",
			    show:false
			},
			{
			    type: 'value',
			    name:"占比",
			    min:0,
			    max:80,
			    show:false
			},
	    ],
	    yAxis: {
	        type: 'category',
	        data: data.datax/*['石家庄', '秦皇岛', '张家口', '承德', '保定','沧州','廊坊','唐山','衡水','形台','邯郸']*/,
	        axisLabel:{
	        	margin:30,
	        	textStyle:{
	        		color:"#cccccc",
	        		fontSize:18
	        	}
	        },
	        axisLine:{
	        	show:false
	        },
	        axisTick:{
	        	show:false
	        }
	    },
	    series: [
	        {
	            name: '办理量',
	            type: 'bar',
	            data: data.datay.cnt/*[107000, 130000, 335000, 203000, 289000, 276000, 98000, 261000, 150000, 189000, 101000]*/,
	            barWidth:10,
	            label:{
	            	normal:{
	            		show:true,
	            		position:"right",
	            		textStyle:{
	            			color:"#6CB1EA",
	            			fontSize:20
	            		}
	            	},
	            	emphasis:{
	            		textStyle:{
	            			color:"#fff",
	            			fontWeight:"bold",
	            			fontSize:20
	            		}
	            	}
	            },
	            itemStyle:{
	            	normal:{
	            		barBorderRadius: 15,
	                    color: new echarts.graphic.LinearGradient(0,0,1,0, [{
	                        offset: 0,
	                        color: '#a826ff'
	                    }, {
	                        offset: 1,
	                        color: '#46befd'
	                    }])
	            	},
	            	emphasis:{
	            		barBorderRadius: 15,
	                    color: new echarts.graphic.LinearGradient(0,0,1,0, [{
	                        offset: 0,
	                        color: '#46befd'
	                    }, {
	                        offset: 1,
	                        color: '#a826ff'
	                    }])
	            	}
	            }
	        },
	        {
	            name: '占比',
	            xAxisIndex:1,
	            type: 'line',
	            data: data.datay.rate/*[5, 12, 10, 5, 11, 12, 13, 14, 23, 23, 5]*/,
	            label:{
	            	normal:{
	            		show:false,
	            		position:"left",
	            		formatter:'{c}'+'%',
	            		textStyle:{
	            			color:"#ffca00",
	            			fontSize:20
	            		}
	            	},
	            	emphasis:{
	            		show:true,
	            		position:"right",
	            		formatter:'{c}'+'%',
	            		textStyle:{
	            			color:"#ffca00",
	            			fontSize:20
	            				
	            		}
	            	}
	            		
	            },
	            symbol:"circle",
	            symbolSize:10,
	            lineStyle:{
	            	normal:{
	            		color:"#ffca00"
	            	}
	            },
	            itemStyle:{
	            	normal:{
	            		color: new echarts.graphic.RadialGradient(0.5,0.5,0.5, [{
	                        offset: 0,
	                        color: 'rgba(255,200,0,1)'
	                    }, {
	                        offset: 1,
	                        color: 'rgba(255,200,0,0.5)'
	                    }])
	            	}
	            },
	            animationEasing:"linear"
	        }
	    ]
	};
	mychart.setOption(option);
	return mychart;
}
/* 加载渠道图表 */
function initChannelCharts(domid,data){
	var mychart = echarts.init(document.getElementById(domid));
	var option = {
	    title: {
	        show:false
	    },
	    legend: {
	        show:false
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : '',        // 默认为直线，可选为：'line' | 'shadow'
	        },
	        background:'rgba(14,23,45,0.9)',
        	position:function(point,params,dom,rect,size){
 	        	if(point[1]>(size.viewSize[1]/2)){
 	        		return ['60%',(point[1]-size.contentSize[1]-10)];
 	        	}else{
 	        		return ['60%',point[1]+10];
 	        	}
 	        }
	    },
	    grid:{
	    	left: '5%',
	        right: '20%',
	        bottom: '0%',
	        top:'5%',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'value',
	        show:false
	    },
	    yAxis: {
	        type: 'category',
	        data: data.datax/*['石家庄', '秦皇岛', '张家口', '承德', '保定','沧州','廊坊','唐山','衡水','形台','邯郸']*/,
	        axisLabel:{
	        	margin:25,
	        	textStyle:{
	        		color:"white",
	        		fontSize:18
	        	}
	        },
	        axisLine:{
	        	show:false
	        },
	        axisTick:{
	        	show:false
	        }
	    },
	    series: [
	        {
	            name: '办理量',
	            type: 'bar',
	            data: data.datay/*[107, 130, 335, 203, 289,276,98,261,150,189,101]*/,
	            barWidth:15,
	            label:{
	            	normal:{
	            		show:true,
	            		position:"right",
	            		textStyle:{
	            			fontSize:16
	            		}
	            	},
	            	emphasis:{
	            		textStyle:{
	            			color:"#fff",
	            			fontWeight:"bold",
	            			fontSize:18
	            		}
	            	}
	            },
	            itemStyle:{
	            	normal:{
	                    color:'#0088D6'
	            	},
	            	emphasis:{
	            		color:'#005DD6'
	            	}
	            },
	            /*animationDuration:function(idx){
	    			return idx*100;
	    		},
	    		animationDurationUpdate:function(idx){
	    			return idx*500;
	    		},
	    		animationEasing:"sinusoidalln"*/
	        }
	    ]
	};
	mychart.setOption(option);
	return mychart;
}
/* 加载分类图表 */
function initKindCharts(domid,data){
	var mychart = echarts.init(document.getElementById(domid));
	var option = {
	    title: {
	        show:false
	    },
	    
	    legend: {
	        show:false
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	        	 type : '',        // 默认为直线，可选为：'line' | 'shadow'
		           lineStyle:{
		        	   color:'#CC653E',
		           }
	        },
	        background:'rgba(14,23,45,0.9)',
	        position:function(point,params,dom,rect,size){
	        	if(point[0]>(size.viewSize[0]/2)){
	        		return [(point[0]-size.contentSize[0]-10),'15%'];
	        	}else{
	        		return [point[0]+10,'15%'];
	        	}
	        }
	    },
	    grid:{
	    	left: '6%',
	        right: '1%',
	        bottom: '1%',
	        top:'5%',
	        containLabel: true
	    },
	    yAxis: {
	        type: 'value',
	        show:false
	    },
	    xAxis: {
	        type: 'category',
	        data: data.datax,
	        axisLabel:{
	        	textStyle:{
	        		color:"white",
	        		fontSize:18
	        	},
	        	padding:0,
	        	rotate:45,
	        	align:'right',
	        },
	        axisLine:{
	        	show:false
	        },
	        axisTick:{
	        	show:false
	        }
	    },
	    series: [
	        {
	            name: '办理量',
	            type: 'bar',
	            data: data.datay,
	            barWidth:10,
	            label:{
	            	normal:{
	            		show:true,
	            		position:"top",
	            		textStyle:{
	            			color:"#6CB1EA",
	            			fontSize:16
	            		}
	            	},
	            	emphasis:{
	            		textStyle:{
	            			color:"#fff",
	            			fontWeight:"bold",
	            			fontSize:18
	            		}
	            	}
	            },
	            itemStyle:{
	            	normal:{
	            		barBorderRadius: 10,
	                    color:'#5FC1DC'
	            	},
	            	emphasis:{
		                    color:'#0088D6'
	            	}
	            },
	           /* animationDuration:function(idx){
	    			return idx*100;
	    		},
	    		animationDurationUpdate:function(idx){
	    			return idx*500;
	    		},
	    		animationEasing:"sinusoidalln"*/
	        }
	    ]
	};
	mychart.setOption(option);
	
	return mychart;
}


/*加载仪表图*/
function initgauge(chartId,data){
	var myChart = echarts.init(document.getElementById(chartId));
	option = {
		    tooltip : {
		        formatter: "{b}:{c}% "
		    },
		    series : [
		        {
		            type: 'gauge',
		            z: 3,
		            min: 0,
		            max: 100,
		            splitNumber: 10,
		            radius: '100%',
		            axisLine: {            // 坐标轴线
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    width:13,
		                    color: [[0.1, '#1DAE2E'], [0.9, '#197AE3'], [1, '#CC653E']],
		                    shadowBlur:10,
		                }
		            },
		            axisTick: {            // 坐标轴小标记
		                length: 0,        // 属性length控制线长
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    color: 'auto'
		                }
		            },
		            splitLine: {           // 分隔线
		                length:20,         // 属性length控制线长
		                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
		                    color: 'white'
		                }
		            },
		            axisLabel: {
		                color: '#4990DE',
		                fontSize:16
		            },
		            title : {
		                fontSize: 18,
		                color:'white',
		                offsetCenter:[0,'15%']
		            },
		            pointer:{
		            	length:'65%',
		            	width:5
		            },
		            detail : {
		                // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                formatter: function (value) {
		                   return value+"%";
		                },
		                backgroundColor: '#2265F1',
		                width:35,
		                fontSize:14,
		                color: 'white',
		                offsetCenter:[0,'70%']
		            },
		            data:[data],
		            animationDuration:function(idx){
		    			return idx*100;
		    		},
		    		animationDurationUpdate:function(idx){
		    			return idx*500;
		    		},
		    		animationEasing:"sinusoidalln"
		        }
		    ]
		};
	
	myChart.setOption(option);
}

/*面积图*/
function areaChartTest(domId,data) {
	var myChart= echarts.init(document.getElementById(domId));
		option = {
		    tooltip : {
		        trigger: 'axis',
		        formatter: "{a} : {c}",
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : '',        // 默认为直线，可选为：'line' | 'shadow'
		           lineStyle:{
		        	   color:'red'
		           }
		        },
		        backgroundColor:'rgba(236,236,4,0.2)',
		        position:function(point,params,dom,rect,size){
		        		return [point[0]-(size.contentSize[0]/2),point[1]-size.contentSize[1]-10];
		        }
		    },
		    grid: {
		    	top:'8%',
		        left: '8%',
		        right: '8%',
		        bottom: '2%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		        	show:true,
		            type : 'category',
		            data : data.dataxName,
		            axisLabel:{
		            	formatter:function(param){
		            		return param.substring(4,6)+"月";
		            	},
			        	textStyle:{
				        	color:'#fff',
				        	fontSize:18, 
				       },
			        },
			        axisLine:{
			        	show:true
			        },
			        splitLine:{
			        	show:false,
			        	lineStyle:{	
			        		color:'#2C3673'
			        	}
			        },
			        axisTick:{
			        	show:false,
			        },
		        }
		    ],
		    yAxis :[{ 
		    	show:false,
		        type : 'value',
		        axisLabel:{
			        	textStyle:{
				        	color:'#00ffff',
				        	fontSize:"18", 
				       },
		        	},
		        splitLine:{
			        	show:false,
			        	lineStyle:{
			        		color:'#2C3673'
			        	}
		        	},
		        axisTick:{
		        		show:false,
		        	},
		        }],
		    series : [
			              {
			            	name:'办理量',
			            	type:'line',
			            	smooth:true,
			            	symbol: 'emptyCircle',
			            	symbolSize:5,
			            	data:data.datayNum,
			            	itemStyle:{
		            			normal: {
		            	            color: '#ff0',
		            	            borderWidth:0,
		            	            shadowBlur:10,
		            	            shadowColor:'yellow'
		            	        },
		            	        emphasis:{
		            	        	color: '#ff0',
		            	            borderWidth:0,
		            	            shadowBlur:10,
		            	            shadowColor:'yellow'
		            	        }
		            		},
		            		/*label:{
		            			normal:{
		            				show:true,
		            				position:'top',
		            				distance:5,
		            				color:'white',
		            				fontSize:18
		            			}
		            		},*/
			            	areaStyle:{
			              		normal:{
			            			  color: new echarts.graphic.LinearGradient(0,0,0,1,[
			            					   {offset:0,color:'rgba(236,236,4,0.3)'},
			            					   {offset:1,color:'rgba(236,236,4,0.1)'}
			            					  ]),
			            		  		}
			             	}
			              }]
		};
  myChart.setOption(option);
  return myChart;
}