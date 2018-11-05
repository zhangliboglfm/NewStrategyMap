function initAreaChart(chartId,data){
	var myChart = echarts.init(document.getElementById(chartId));
	 var option = {
		    	//图表的标题
		        /*title: {
		            text: '标准折线面积图'
		        },*/
		        //图表展示的类别，这里的data和series中得name对应
		        legend: {
		            data: ['上月(人次)', '本月(人次)','同比增长(%)'],
		            textStyle:{
		            	color:'white',
		            	fontSize:20
		            },
		            right:'5%'
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
			        bottom: '20%',
			        top:'10%',
			    },
		        xAxis:{
		            type: 'category',
		            boundaryGap: true,//这里表示是否补齐空白
		            data:data.name,
		            axisLabel:{
			        	textStyle:{
				        	color:'white',
				        	fontSize:"14"
				        },
				        formatter:function(params,index){
				        	return params;
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
				        	fontSize:"16"
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
		            min:-1000,
		            max:200,
		        }],
		        //图表类型，type表示按照什么类型图表显示，line代表折线，下面的内容与
		        //legend一一对应
		        series:[{
		        	name:'上月(人次)',
            		type: 'bar',
            		barWidth:20,
            		barGap:'5%',
            		yAxisIndex:0,
            		itemStyle:{
            			normal:{
            				color:'rgba(111,193,221,1)'
            			},
            			emphasis:{
            				color:'rgba(0,255,255,1)'
            			}
            		},
            		data:data.data1
		        },{
		        			name:'本月(人次)',
		            		type: 'bar',
		            		barWidth:20,
		            		barGap:'5%',
		            		yAxisIndex:0,
		            		itemStyle:{
		            			normal:{
		            				color:'rgba(48,139,220,1)'
		            			},
		            			emphasis:{
		            				color:'rgba(34,101,241,1)'
		            			}
		            		},
		            		data:data.data
		        },
		        {
        			name:'同比增长(%)',
        			yAxisIndex:1,
            		type: 'line',
            		symbolSize:1,
            		itemStyle:{
            			normal: {
            	            color: '#ff0',
            	            borderWidth:0,
            	        },
            	        emphasis:{}
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
	  
	  myChart.on("click",function(params){
			inithistory(params.name);
		});
	  
	  /*显示tooltips*/
		myChart.dispatchAction({
			type:'showTip',
			seriesIndex:0,
			dataIndex:0
			
		});
		/*高亮显示图形*/
		myChart.dispatchAction({
			type:'highlight',
			seriesIndex:0,
			dataIndex:0
		});
		var i=0;
		setInterval(function(){
			i++;
			if(i>data.data1.length-1){
				i=0
			}
			/*显示tooltips*/
			myChart.dispatchAction({
				type:'showTip',
				seriesIndex:0,
				dataIndex:i
				
			});
			/*高亮显示图形*/
			myChart.dispatchAction({
				type:'highlight',
				seriesIndex:0,
				dataIndex:i
			});
		},5000)
		
}


function initHistoryLine(chartId,alldata,lenged,num){
	var myChart = echarts.init(document.getElementById(chartId));
	var datax =[];
	for(var i=1;i<=num;i++){
		datax.push(i);
	};
	var option = {
			//图表的标题
			/*title: {
		            text: '标准折线面积图'
		        },*/
			//图表展示的类别，这里的data和series中得name对应
			legend: {
				data: lenged,
				textStyle:{
					color:'white',
					fontSize:20
				},
				right:'5%'
			},
			color:['#B3454A','#EC790A','#05B6AB'],
			//这里的是采用什么方式触发数据，这里显示的竖轴，跟随鼠标移动到节点上会显示内容
			tooltip: {
				trigger:"axis",
				axisPointer:{
					type:''
				},
				backgroundColor:'#171725'
			},
			grid:{
				left: '10%',
				right: '5%',
				bottom: '15%',
				top:'10%',
			},
			xAxis:{
				type: 'category',
				data:datax,
				axisLabel:{
					textStyle:{
						color:'white',
						fontSize:"14"
					},
					formatter:function(params,index){
						return params;
					}
				},
				axisLine:{
					lineStyle:{
						color:'#0180CA',
					}
				},
				splitLine:{
					show:false,
					lineStyle:{
						color:'#313247',
					}
				},
				splitArea:{
					show:false,
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
						fontSize:"16"
					}
				},
				axisLine:{
					lineStyle:{
						color:'#0180CA',
					}
				},
				splitLine:{
					show:false,
					lineStyle:{
						color:'#313247',
					}
				}
			}],
			//图表类型，type表示按照什么类型图表显示，line代表折线，下面的内容与
			//legend一一对应
			series:[
			        {
					name:lenged[0],
					type: 'line',
					  symbol:'circle',
					  symbolSize:5,
					  itemStyle:{
						  normal: {
							  borderWidth:1,
							  shadowBlur:8,
							  shadowColor:'#B3454A',
						  },
						  emphasis:{}
					  },
					data:alldata[0],
				  },
				  {
					  name:lenged[1],
					  type: 'line',
					  symbol:'circle',
					  symbolSize:5,
					  itemStyle:{
						  normal: {
							  borderWidth:1,
							  shadowBlur:8,
							  shadowColor:'#EC790A'
						  },
						  emphasis:{}
					  },
					  data:alldata[1],
				  },
				  {
					  name:lenged[2],
					  type: 'line',
					  symbol:'circle',
					  symbolSize:5,
					  itemStyle:{
						  normal: {
							  borderWidth:1,
							  shadowBlur:8,
							  shadowColor:'#05B6AB'
						  },
						  emphasis:{}
					  },
					  data:alldata[2],
				  }]
			};
	//配置图表设置并读取
	myChart.setOption(option);
}