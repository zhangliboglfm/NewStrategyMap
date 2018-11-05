function initAreaChart(chartId,data){
	var myChart = echarts.init(document.getElementById(chartId));
	 var option = {
		    	//图表的标题
		        /*title: {
		            text: '标准折线面积图'
		        },*/
		        //图表展示的类别，这里的data和series中得name对应
		        legend: {
		            data: ['上月(MB)','本月(MB)','同比增长(%)'],
		            textStyle:{
		            	color:'white',
		            	fontSize:18
		            }
		        },
		        //这里的是采用什么方式触发数据，这里显示的竖轴，跟随鼠标移动到节点上会显示内容
		        tooltip: {
		        	trigger:"axis",
		        	axisPointer:{
		        		type:''
		        	},
		        	backgroundColor:'#171725'
		        },	
		        grid:{
			    	left: '8%',
			        right: '5%',
			        
			        bottom: '25%',
			        top:'10%',
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
		            boundaryGap: true,//这里表示是否补齐空白
		            data:data.name,
		            axisLabel:{
			        	textStyle:{
				        	color:'white',
				        	fontSize:"14"
				        },
				        padding:0,
			        	rotate:45,
			        	align:'right',
			        },
			        axisLine:{
			        	lineStyle:{
			        		color:'#313247',
			        	}
			        },
			        splitLine:{
			        	show:true,
			        	lineStyle:{
			        		color:'#313247',
			        	}
			        },
			        splitArea:{
            			show:true,
            			areaStyle:{
            				color:['rgba(31,32,52,0)','rgba(0,0,0,0.1)']
            			}
            		}
		        },
		        //代表y轴，这里type类型代表num类型
		        yAxis:[{
		            type: 'value',
		            axisLabel:{
			        	textStyle:{
				        	color:'white',
				        	fontSize:"14"
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
		        },{
		        	show:false,
		            type: 'value',
		            /*name:'用户量',*/
		            min:-600,
		            max:100,
		            /*axisLabel:{
			        	textStyle:{
				        	color:'white',
				        	fontSize:"12"
				        }
			        },
			        axisTick:{
			        	lineStyle:{
			        		color:'#095b71',
			        	}
			        },
			        axisLine:{
			        	lineStyle:{
			        		color:'#095b71',
			        	}
			        },
			        splitLine:{
			        	show:true,
			        	lineStyle:{
			        		color:'#095b71',
			        	}
			        }*/
			        
		        }
		        ],
		        //图表类型，type表示按照什么类型图表显示，line代表折线，下面的内容与
		        //legend一一对应
		        series:[{
		        	name:'上月(MB)',
            		type: 'bar',
            		barWidth:18,
            		yAxisIndex:0,
            		barGap:'5%',
            		itemStyle:{
            			normal:{
            				color:'rgba(238,69,108,1)'/*{
            					type:"linear",
            					x:0,y:0,x2:0,y2:1,
            					colorStops:[
            					     {offset:0,color:'rgba(238,69,108,0.8)'},
            					     {offset:1,color:'rgba(238,69,108,0.3)'}
            					     ]
            				}*/
            			},
            			/*emphasis:{
            				show:false
            			}*/
            		},
            		data:data.data1
		        },{
        			name:'本月(MB)',
        			yAxisIndex:0,
            		type: 'bar',
            		barWidth:18,
            		barGap:'5%',
            		itemStyle:{
            			normal:{
            				color:'rgba(0,240,255,1)'
            					/*{
            					type:"linear",
            					x:0,y:0,x2:0,y2:1,
            					colorStops:[
            					     {offset:0,color:'rgba(0,240,255,0.8)'},
            					     {offset:1,color:'rgba(0,240,255,0.3)'}
            					     ]
            				}*/
            			},
            			/*emphasis:{
            				show:false
            			}*/
            		},
            		data:data.data
		        },{
		        	name:'同比增长(%)',
        			yAxisIndex:1,
            		type: 'line',
            		symbolSize:5,
            		itemStyle:{
            			normal: {
            	            color: '#ff0',
            	            borderWidth:0,
            	        }
            		},
            		data:data.data2,
            		markLine:{
            			data:[{yAxis:0}],
            			lineStyle:{
            				normal:{
            					color:'rgba(255,255,0,0.6)'
            				}
            			}
            		}
		        }]
		    };
		    //配置图表设置并读取
	  myChart.setOption(option);
}