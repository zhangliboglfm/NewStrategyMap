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
	        	margin:25,
	        	textStyle:{
	        		color:"#cccccc",
	        		fontSize:14
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
	            barWidth:15,
	            label:{
	            	normal:{
	            		show:true,
	            		position:"right",
	            		textStyle:{
	            			color:"#6CB1EA",
	            			fontSize:14
	            		}
	            	},
	            	emphasis:{
	            		textStyle:{
	            			color:"#fff",
	            			fontWeight:"bold"
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
	            /*label:{
	            	normal:{
	            		show:false,
	            		position:"left",
	            		formatter:'{c}'+'%',
	            		textStyle:{
	            			color:"#ffca00"
	            		}
	            	}
	            },*/
	            symbol:"circle",
	            symbolSize:13,
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
	        		color:"#cccccc",
	        		fontSize:14
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
	            			color:"#6CB1EA",
	            			fontSize:14
	            		}
	            	},
	            	emphasis:{
	            		textStyle:{
	            			color:"#fff",
	            			fontWeight:"bold"
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
	            },
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
	        		color:"#cccccc",
	        		fontSize:14
	        	}/*,
	        	formatter:function(){
	        		return "123";
	        	}*/
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
	            			color:"#6CB1EA",
	            			fontSize:14
	            		}
	            	},
	            	emphasis:{
	            		textStyle:{
	            			color:"#fff",
	            			fontWeight:"bold"
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
	            },
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
	mychart.setOption(option);
	return mychart;
}