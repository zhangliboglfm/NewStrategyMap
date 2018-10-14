//Echarts柱状图加载
function InitEchartsColumn(id,data){
	var myChart = echarts.init(document.getElementById(id));
	option = {
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type : 'shadow',
	            z:0,
	            shadowStyle:{
	            	color:'',
	            	opacity:0
	            }
	        },
	        formatter:'{b}<br/>{a1}:{c1}'
	    },
	    grid: {
	    	top: '6%',
	        left: '0.5%',
	        right: '8%',
	        bottom: '6%',
	        containLabel: true
	    },
	    xAxis:{
	        type: 'category',
	        data:["石家庄","保定","唐山","秦皇岛","廊坊","张家口","衡水","邢台","邯郸","沧州","承德"],
	        axisLabel:{
	        	interval:0,
	        	rotate:30,
	        	textStyle:{
		        	color:'#000',
		        	fontSize:"10"
		        }
	        },
	        axisLine:{
	        	lineStyle:{
	        		color:"#F0F3F8",		//设置坐标轴的轴线
	        	}
	        },
	        axisTick:{
	        	show:false
	        },
	        splitLine:{
	        	show:false,
	        }
	    },
	    yAxis: {
	        type: 'value',
	        axisLabel:{
	        	textStyle:{
		        	color:'#000',
		        	fontSize:"8"
		        }
	        },
	        axisLine:{
	        	show:false
	        },
	        axisTick:{
	        	show:false
	        },
	        splitLine:{
	        	show:true,
	        	lineStyle:{
	        		color:"#F2F3F8"
	        	}
	        }
	    },
	    series: [
			{
				type: 'bar',
				itemStyle:{
					normal:{color: '#EBF2FA'},emphasis:{color: '#EBF2FA'}
				},
				barWidth:'15',
				barGap:'-100%',
				barCategoryGap:'40%',
				data:[15000,15000,15000,15000,15000,15000,15000,15000,15000,15000,15000],
				animation: false,
				z:10
			},    
			{
				type: 'bar',
				name: '总流量',
				barWidth:'15',
				silent:true,
				data:[10000,9000,6000,2000,3000,1500,3500,12000,6900,5600,8700],
				itemStyle:{
					normal:{
						 color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
         	                offset: 0,
         	                color: '#08A9F1'
         	            }, {
         	                offset: 1,
         	                color: '#01E7B8'
         	            }])
					},
					emphasis:{
						color: '#08A9F1'
					}
				},
				z:11
			}
	    ]
	};
	myChart.setOption(option);
}
//Echarts折线图加载
function InitEchartsLine(id,data){
	var myChart = echarts.init(document.getElementById(id));
	option = {
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type : 'shadow',
	            shadowStyle:{
	            	color:'rgba(0,0,0,0)'
	            }
	        },
	        formatter:'{b}<br/>{a0}:{c0}'
	    },
	    grid: {
	    	top: '6%',
	        left: '0.5%',
	        right: '8%',
	        bottom: '6%',
	        containLabel: true
	    },
	    xAxis:{
	        type: 'category',
	        data:["1月1日","1月2日","1月3日","1月4日","1月5日","1月6日","1月7日"],
	        boundaryGap:false,
	        axisLabel:{
	        	interval:0,
	        	rotate:30,
	        	textStyle:{
		        	color:'#000',
		        	fontSize:"10"
		        }
	        },
	        axisLine:{
	        	lineStyle:{
	        		color:"#F0F3F8",		//设置坐标轴的轴线
	        	}
	        },
	        axisTick:{
	        	show:false
	        },
	        splitLine:{
	        	show:true,
	        	lineStyle:{
	        		color:"#F2F3F8"
	        	}
	        }
	    },
	    yAxis: {
	        type: 'value',
	        axisLabel:{
	        	textStyle:{
		        	color:'#000',
		        	fontSize:"8"
		        }
	        },
	        axisLine:{
	        	show:false
	        },
	        axisTick:{
	        	show:false
	        },
	        splitLine:{
	        	show:true,
	        	lineStyle:{
	        		color:"#F2F3F8"
	        	}
	        }
	    },
	    series: [
			{
				type: 'line',
				smooth:true,
				symbol:'circle',
				symbolSize:8,
				name: '总流量',
				barWidth:'10',
				data:[10000,9000,6000,2000,3000,1500,3500],
				itemStyle:{
					normal:{
						 color: new echarts.graphic.RadialGradient(0.5,0.5,0.5,[{
	         	                offset: 0,
	         	                color: 'rgba(5,231,253,0.8)'
	         	            }, {
	         	                offset: 1,
	         	                color: 'rgba(5,231,253,0.3)'
	         	            }])
					}
				},
				lineStyle:{
					normal:{
						 color:'#02E7FA'
					}
				},
				areaStyle:{
					normal:{
						color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
         	                offset: 0,
         	                color: 'rgba(1,169,240,0.3)'
         	            }, {
         	                offset: 1,
         	                color: 'rgba(1,169,240,0)'
         	            }])
					}
				}
			}
	    ]
	};
	myChart.setOption(option);
}
//Echarts条形图加载
function InitEchartsBar(id,data){
	var myChart = echarts.init(document.getElementById(id));
	option = {
	    tooltip : {
	        trigger: 'axis',
	        axisPointer: {
	            type : 'shadow',
	            z:0,
	            shadowStyle:{
	            	color:'',
	            	opacity:0
	            }
	        },
	        formatter:'{b}<br/>{a}:{c}'
	    },
	    grid: {
	    	top: '6%',
	        left: '0.5%',
	        right: '8%',
	        bottom: '6%',
	        containLabel: true
	    },
	    xAxis:{
	        type: 'value',
	        axisLabel:{
	        	textStyle:{
		        	color:'#000',
		        	fontSize:"10"
		        }
	        },
	        axisLine:{
	        	lineStyle:{
	        		color:"#F0F3F8",		//设置坐标轴的轴线
	        	}
	        },
	        axisTick:{
	        	show:false
	        },
	        splitLine:{
	        	show:true,
	        	lineStyle:{
	        		color:"#F2F3F8"
	        	}
	        }
	    },
	    yAxis: {
	        type: 'category',
	        data:["石家庄","保定","唐山","秦皇岛","廊坊","张家口","衡水","邢台","邯郸","沧州","承德"],
	        axisLabel:{
	        	interval:0,
	        	 rotate:30,
	        	textStyle:{
		        	color:'#000',
		        	fontSize:"10"
		        }
	        },
	        axisLine:{
	        	show:false
	        },
	        axisTick:{
	        	show:false
	        },
	        splitLine:{
	        	show:true,
	        	lineStyle:{
	        		color:"#F2F3F8"
	        	}
	        }
	    },
	    series: [
			{
				type: 'bar',
				name: '新增用户数',
				barWidth:'10',
				silent:true,
				data:[10000,9000,6000,2000,3000,1500,3500,12000,6900,5600,8700],
				itemStyle:{
					normal:{
						barBorderRadius:[0,5,5,0],
						color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
						    offset: 0,
						    color: 'rgba(192,148,237,0.6)'
						}, {
						    offset: 1,
						    color: 'rgba(192,148,237,0.8)'
						}])
					},
					emphasis:{
						barBorderRadius:[0,5,5,0],
						color: 'rgba(192,148,237,1)'					}
				},
				z:11
			}
	    ]
	};
	myChart.setOption(option);
}
//Echarts饼图加载
function InitEchartsPie(id,data){
	var myChart = echarts.init(document.getElementById(id));
	option = {
		color:['#4ACDF9','#00FFAF'],
	    tooltip : {
	        trigger: 'item',
	        formatter:'{b}<br/>{c}(占比：{d})'
	    },
	    legend:{
	    	show:false
	    },
	    grid: {
	    	top: '6%',
	        left: '0.5%',
	        right: '8%',
	        bottom: '6%',
	        containLabel: true
	    },
	    series: [
			{
				type: 'pie',
				name: '高低流量用户数占比',
				data:[{value:10000,name:'高流量用户数'},{value:4300,name:'低流量用户数'}],
				radius:[60,80],
				center:['50%','50%'],
				roseType:'radius'
			}
	    ]
	};
	myChart.setOption(option);
}
//Echarts仪表盘加载
function InitEchartsGauge(id,data){
	var myChart = echarts.init(document.getElementById(id));
	option = {
	    tooltip : {
	        
	    },
	    series: [
			{
				type: 'gauge',
				name: '4G渗透率',
				stratAngle:225,
				endAngle:-45,
				radius: '100%',
				data:[{value:69.3,name:'渗透率'}],
				detail:{formatter:'{value}%'},
				itemStyle:{
					
				},
				axisLine:{
					lineStyle:{
						color:[[0.2,'#01DF6E'],[0.8,'#04AAFF'],[1,'#FF6501']],
						width:10
					}
				},
				axisTick:{
				},
				splitLine:{
					length:15,
					lineStyle:{
						color:'#00AAFF',
						width:1
					}
				},
				axisLable:{
					show:true,
					distance:3
				},
				detail:{
					color:'#000',
					fontSize:20,
					offsetCenter:[0,'60%'],
					formatter:function(value){
						return value+"%"
					}
				},
				pointer:{
					width:5,
					length:'70%'
				}
			}
	    ]
	};
	myChart.setOption(option);
}
//加载图标数据区域
function InitIconDivData(id,data){
	var width = $("#"+id).width();
	var height = $("#"+id).height();
	var idivWidth = width*0.45;
	var idivHeight = idivWidth*0.3;
	var cdivdom1 = $("<div class='icondatadiv' style='width: "+idivWidth+"px;height: "+idivHeight+"px;'></div>");
	var cdivdom2 = $("<div class='icondatadiv' style='width: "+idivWidth+"px;height: "+idivHeight+"px;'></div>");
	var cdivdom3 = $("<div class='icondatadiv' style='width: "+idivWidth+"px;height: "+idivHeight+"px;'></div>");
	
	cdivdom1.append(
			$(
					"<div class='icondiv' style='" +
							"width: "+idivHeight+"px;" +
							"line-height: "+idivHeight+"px;" +
							"background-color: #00AAFF;'>" +
					"<i class='iconfont'>&#xe628</i>" +
					"</div>" +
					"<div class='datadiv' style='width: "+idivWidth*0.7+"px;'>" +
					"<div class='tdiv' style='height: "+idivHeight*0.5+"px;line-height: "+idivHeight*0.5+"px;color: #00AAFF;'>99999999G</div>" +
					"<div class='bdiv' style='height: "+idivHeight*0.5+"px;line-height: "+idivHeight*0.5+"px;'>" +
					"<span class='name'>4G流量</span>" +
					"<span class='rate'>20%</span>" +
					"<i class='iconfont' style='color: #21FF38'>&#xe7f1</i>" +
					"</div>" +
					"</div>"
			)
	);
	cdivdom2.append(
			$(
					"<div class='icondiv' style='" +
							"width: "+idivHeight+"px;" +
							"line-height: "+idivHeight+"px;" +
							"background-color: #FF664C;'>" +
					"<i class='iconfont'>&#xe84d</i>" +
					"</div>" +
					"<div class='datadiv' style='width: "+idivWidth*0.7+"px;'>" +
					"<div class='tdiv' style='height: "+idivHeight*0.5+"px;line-height: "+idivHeight*0.5+"px;color: #FF664C;'>99999999G</div>" +
					"<div class='bdiv' style='height: "+idivHeight*0.5+"px;line-height: "+idivHeight*0.5+"px;'>" +
					"<span class='name'>4G户均流量</span>" +
					"<span class='rate'>20%</span>" +
					"<i class='iconfont' style='color: #21FF38'>&#xe7f1</i>" +
					"</div>" +
					"</div>"
			)
	);
	cdivdom3.append(
			$(
					"<div class='icondiv' style='" +
							"width: "+idivHeight+"px;" +
							"line-height: "+idivHeight+"px;" +
							"background-color: #00D5FF;'>" +
					"<i class='iconfont'>&#xe72d</i>" +
					"</div>" +
					"<div class='datadiv' style='width: "+idivWidth*0.7+"px;'>" +
					"<div class='tdiv' style='height: "+idivHeight*0.5+"px;line-height: "+idivHeight*0.5+"px;color: #00D5FF;'>99999999G</div>" +
					"<div class='bdiv' style='height: "+idivHeight*0.5+"px;line-height: "+idivHeight*0.5+"px;'>" +
					"<span class='name'>2G/3G流量</span>" +
					"<span class='rate'>20%</span>" +
					"<i class='iconfont' style='color: #FF5522'>&#xe755</i>" +
					"</div>" +
					"</div>"
			)
	);
	$("#"+id).append(cdivdom1);
	$("#"+id).append(cdivdom2);
	$("#"+id).append(cdivdom3);
	
}