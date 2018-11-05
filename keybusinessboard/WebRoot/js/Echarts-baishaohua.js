/* 加载套餐图表 */
function initPackCharts(domid,data){
	var mychart = echarts.init(document.getElementById(domid));
	var option = {
	    angleAxis: {
	        type: 'category',
	        data: data.datax,
	        z: 10,
	        axisLine:{
	        	lineStyle:{
	        		color:"#00ffff",
	        		width:3
	        	}
	        },
	        axisTick:{
	        	show:false
	        },
	        axisLabel:{
	        	textStyle:{
	        		color:"#fff",
	        		width:1
	        	}
	        }
	    },
	    radiusAxis: {
	    	axisLine:{
	    		lineStyle:{
	    			color:"yellow",
	    			width:1
	    		}
	    	},
	    	axisLabel:{
	    		show:false,
	        	textStyle:{
	        		color:"#fff",
	        		fontSize:10
	        	}
	        },
	    	z:10
	    },
	    polar: {
	    },
	    tooltip:{
	    	show:true,
	    	backgroundColor:'#085696',
	    	formatter:'{b}'+'{a}'+':'+'{c}%'
	    },
	    series: [{
	        type: 'bar',
	        data: data.datay.online,
	        coordinateSystem: 'polar',
	        name: '线上',
	        stack: 'a',
	        itemStyle:{
	        	normal:{
	        		color:'#D95B77',
	        		opacity:0.8
	        	}
	        },
	        z:5
	    }, {
	        type: 'bar',
	        data: data.datay.outline,
	        coordinateSystem: 'polar',
	        name: '线下',
	        stack: 'a',
	        itemStyle:{
	        	normal:{
	        		color:'#06D9E1',
	        		opacity:0.8
	        	}
	        },
	        z:5
	    }],
	    legend: {
	        show: false,
	        data: ['线上', '线下']
	    }
	};
	mychart.setOption(option);
}
/*重点业务占比*/
function initKeyBusCharts(domId,data){
	var mychart = echarts.init(document.getElementById(domId));
	var option = {
	    angleAxis: {
	        axisLine:{
	        	lineStyle:{
	        		color:"#68F4F1",
	        		width:1
	        	}
	        },
	        axisTick:{
	        	show:false
	        },
	        axisLabel:{
	        	show:false,
	        	textStyle:{
	        		color:"#fff"
	        	}
	        }
	    },
	    radiusAxis: {
	    	type:'category',
	    	data: data.datax,
	    	axisLine:{
	    		lineStyle:{
	    			color:"yellow"
	    		}
	    	},
	    	axisLabel:{
	        	textStyle:{
	        		color:"#fff",
	        		fontSize:4
	        	}
	        },
	    	z:10
	    },
	    polar: {
	    },
	    tooltip:{
	    	show:true,
	    	backgroundColor:'#085696',
	    	formatter:'{b}'+'{a}'+':'+'{c}%'
	    },
	    series: [{
	        type: 'bar',
	        data: data.datay.online,
	        coordinateSystem: 'polar',
	        name: '线上',
	        stack: 'a',
	        itemStyle:{
	        	normal:{
	        		color:'#D95B77',
	        		opacity:0.8
	        	}
	        },
	        z:5
	    }, {
	        type: 'bar',
	        data: data.datay.outline,
	        coordinateSystem: 'polar',
	        name: '线下',
	        stack: 'a',
	        itemStyle:{
	        	normal:{
	        		color:'#06D9E1',
	        		opacity:0.8
	        	}
	        },
	        z:5
	    }],
	    legend: {
	        show: true,
	        data: ['线上', '线下'],
	        orient:"vertical",
	        right:'right',
	        textStyle:{
	        	color:"#fff"
	        }	
	    }
	};
	mychart.setOption(option);
}

function initOtherBusChart(domId,data){
	var myChart = echarts.init(document.getElementById(chartId));
	var option = {
			tooltip : {
				trigger: 'axis',
				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			grid: {
				left: '3%',
				right: '4%',
				bottom: '3%',
				containLabel: true
			},
			xAxis : [
			         {
			        	 type : 'category',
			        	 data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
			        	 axisTick: {
			        		 alignWithLabel: true
			        	 }
			         }
			         ],
			         yAxis : [
			                  {
			                	  type : 'value'
			                  }
			                  ],
			                  series : [
			                            {
			                            	name:'直接访问',
			                            	type:'bar',
			                            	barWidth: '60%',
			                            	data:[10, 52, 200, 334, 390, 330, 220]
			                            }
			                            ]
	};
	myChart.setOption(option);
}