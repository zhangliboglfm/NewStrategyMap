/*柱状图*/
function singlebar(chartId){
	var myChart = echarts.init(document.getElementById(chartId));
	var option = {
			color: ['#3398DB'],
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





/*饼图*/
function singlepie(data,chartId){
	var myChart = echarts.init(document.getElementById(chartId));
	var option = {
			tooltip : {
				trigger: 'item',
				position:function(point){
					return {top:point[1],left:point[0]}
				},
				formatter: formatterdata,
				padding:0
			},
			color: ['#01adff','#822dc8','#ed496b','#FFb400'],
			series : [
			          {
			        	  name:'访问来源',
			        	  type:'pie',
			        	  radius : '70%',
			        	  center: ['50%', '50%'],
			        	  data:data.datax,
			        	  itemStyle:{
			        		  normal:{
			        			  label:{
			        				  show:true,
			        				  formatter:'{b}:{c}%',
			        				  textStyle:{
			        					  color:'#FFF'
			        				  }
			        			  },
			        			  labelLine:{show:true,
			        				  lineStyle:{
			        					  color:"#FFF"
			        				  }
			        				  }  
			        		  },
			        	  } 
			          }
			          ]
	};
	myChart.setOption(option);
	
	function formatterdata(param){
		var valuesFormatter = [];
		var obj="";
		if(param.name=="4G飞享类"){
			obj=eval(data.datay["4G"]);
		}else if(param.name=="其他业务类"){
			obj=eval(data.datay["其他"]);
			
		}else if(param.name=="宽带业务类"){
			obj=eval(data.datay["宽带业务"]);
		}else if(param.name=="流量包类"){
			obj=eval(data.datay["流量包"]);
		}
		valuesFormatter.push(
		      	'<div style="height:90px;width:240px;border: 1px solid #1b7c91; "> '+
				 ' <div style="height:15px;background:#c34364;line-height:15px;font-size:11px; text-indent: 2px;">TOP3<span style="float:right">'+param.data.value+'%</span></div>' +
				 ' <div style="height:20px;margin-top:3px;width:100%;">'+
				        ' <div style="float:left;height:12px;width:12px;background:#ed4a6b;text-align:center;line-height:10px;margin-top:4px;margin-left:3px;"> 1 </div>'+
				        ' <div style="float:left;height:15px;width:50px;text-align:center;line-height:15px;margin-top:2.5px">'+obj.one+'</div>'+
				 '</div>'+
				 ' <div style="height:20px;margin-top:3px;width:100%;">'+
				      ' <div style="float:left;height:12px;width:12px;background:#eabc0a;text-align:center;line-height:10px;margin-top:4px;margin-left:3px;"> 2 </div>'+
				      ' <div style="float:left;height:15px;width:50px;text-align:center;line-height:15px;margin-top:2.5px"> '+obj.two+'</div>'+
				 ' </div>'+
				 ' <div style="height:20px;margin-top:3px;width:100%;">'+
				       ' <div style="float:left;height:12px;width:12px;background:#00db6c;text-align:center;line-height:10px;margin-top:4px;margin-left:3px;"> 3 </div>'+
				       ' <div style="float:left;height:15px;width:50px;text-align:center;line-height:15px;margin-top:2.5px"> '+obj.three+' </div>'+
				 '</div>'+
				'</div>'
		
		);
	return valuesFormatter;
	}
	
}
   
/*饼图*/
function outlinesinglepie(data,chartId){
	var myChart = echarts.init(document.getElementById(chartId));
	var option = {
			tooltip : {
				trigger: 'item',
				position:function(point){
					return {top:point[1],left:point[0]}
				},
				formatter: formatterdata,
				padding:0
			},
			color: ['#5848e9','#00fedc','#ffea01','#ec49b2'],
			series : [
			          {
			        	  name:'访问来源',
			        	  type:'pie',
			        	  radius : '70%',
			        	  center: ['50%', '50%'],
			        	  data:data.datax,
			        	  label:{
			        		  normal:{
			        			  show:true
			        		  }
			        	  },
			        	  itemStyle:{
			        		  normal:{
			        			  label:{
			        				  show:true,
			        				  formatter:'{b}:{c}%',
			        				  textStyle:{
			        					  color:'#FFF'
			        				  }
			        			  },
			        			  labelLine:{show:true,
			        				  lineStyle:{
			        					  color:"#FFF"
			        				  }  
			        			  }  
			        		  },
			        	  }       
			          }
			          ]
	};
	myChart.setOption(option);
	
	function formatterdata(param){
		var valuesFormatter = [];
		var obj="";
		if(param.name=="4G飞享类"){
			obj=eval(data.datay["4G"]);
		}else if(param.name=="其他业务类"){
			obj=eval(data.datay["其他"]);
			
		}else if(param.name=="宽带业务类"){
			obj=eval(data.datay["宽带业务"]);
		}else if(param.name=="流量包类"){
			obj=eval(data.datay["流量包"]);
		}
		valuesFormatter.push(
				'<div style="height:90px;width:240px;border: 1px solid #1b7c91; "> '+
				' <div style="height:15px;background:#c34364;line-height:15px;font-size:11px; text-indent: 2px;">TOP3<span style="float:right">'+param.data.value+'%</span></div>' +
				' <div style="height:20px;margin-top:3px;width:100%;">'+
				' <div style="float:left;height:12px;width:12px;background:#ed4a6b;text-align:center;line-height:10px;margin-top:4px;margin-left:3px;"> 1 </div>'+
				' <div style="float:left;height:15px;width:50px;text-align:center;line-height:15px;margin-top:2.5px">'+obj.one+'</div>'+
				'</div>'+
				' <div style="height:20px;margin-top:3px;width:100%;">'+
				' <div style="float:left;height:12px;width:12px;background:#eabc0a;text-align:center;line-height:10px;margin-top:4px;margin-left:3px;"> 2 </div>'+
				' <div style="float:left;height:15px;width:50px;text-align:center;line-height:15px;margin-top:2.5px"> '+obj.two+'</div>'+
				' </div>'+
				' <div style="height:20px;margin-top:3px;width:100%;">'+
				' <div style="float:left;height:12px;width:12px;background:#00db6c;text-align:center;line-height:10px;margin-top:4px;margin-left:3px;"> 3 </div>'+
				' <div style="float:left;height:15px;width:50px;text-align:center;line-height:15px;margin-top:2.5px"> '+obj.three+' </div>'+
				'</div>'+
				'</div>'
				
		);
		return valuesFormatter;
	}
	
}

/*极地柱状图*/

function polarmap(chartId){
/*	function tooltipFormatter(params) {
		
		var valuesFormatter = [];
		
		if (params.componentSubType == 'pie') {
			
			valuesFormatter.push(
					
					'<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">' +
					
					option.angleAxis.data[weekDay].value + '<br>' + '</div>' +
					
					'<span style="color:' + params.color + '">' + params.name + '</span>: ' + params.value
					
			);
			
		} else {
			
			valuesFormatter.push(
					
					'<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">' +
					
					params.seriesName +
					
					'</div>' +
					
					'<span style="color:' + params.color + '">' + params.name + '</span>: ' + params.value + '<br>');
			
		}
		
		
		return valuesFormatter;
		
	}  */               
	
}



/*极地柱状图*/
function polarother(chartId,data){
	var mychart = echarts.init(document.getElementById(chartId));
	var option = {
	    angleAxis: {
	        type: 'category',
	        data: data.datax,
	        z: 10,
	        axisLine:{
	        	lineStyle:{
	        		color:"#68F4F1",
	        		width:2
	        	}
	        },
	        axisTick:{
	        	show:false
	        },
	        axisLabel:{
	        	textStyle:{
	        		color:"#fff"
	        	}
	        }
	    },
		polar: {
			center: ['50%', '50%'],
			radius: '50%',
		},
	    radiusAxis: {
	    	axisLine:{
	    		lineStyle:{
	    			color:"yellow"
	    		}
	    	},
	    	axisLabel:{
	    		show:false,
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
	        		color:'#06D9E1',
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
	        		color:'#D95B77',
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
/*极地柱状图*/
function oulinepolarother(chartId,data){
	var mychart = echarts.init(document.getElementById(chartId));
	var option = {
			angleAxis: {
				type: 'category',
				data: data.datax,
				z: 10,
				axisLine:{
					lineStyle:{
						color:"#68F4F1",
						width:2
					}
				},
				axisTick:{
					show:false
				},
				axisLabel:{
					textStyle:{
						color:"#fff"
					}
				}
			},
			polar: {
				center: ['50%', '50%'],
				radius: '50%',
			},
			radiusAxis: {
				axisLine:{
					lineStyle:{
						color:"yellow"
					}
				},
				axisLabel:{
					show:false,
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
						color:'#c65478',
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
						color:'#D95B77',
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
//柱状图
function createcolumn(id,data){
	 $('#'+id).highcharts({
	        chart: {
	            backgroundColor:'rgba(0,0,0,0)'
	        },
	        xAxis: {
	            categories: ['石家庄', '秦皇岛', '张家口', '廊坊','唐山', '衡水', '保定', '承德','邢台','邯郸','沧州'],
	            gridLineWidth:0,
	            lineWidth:0,
	            tickLength:0,
	             labels:{
			         style:{"color":"#FFFFFF", "fontSize":"12px","fontFamily":"微软雅黑"}
			       }
	        },
	        yAxis: [{ 
	        	gridLineWidth:0,
	            labels: {
	            	formatter: function() {
		                //return this.value;	
		            },
	                style: {
	                    "color": "black",
	                    "fontSize":"11px"
	                }
	            },
	            title: {
	                text: null,
	                style: {
	                    color: "black"
	                }
	            }/*,
	            tickInterval:1000*/
	        }],
	        tooltip: {
	           // shared: true
	            formatter:function(){
	            	return this.x +":  "+"<b>"+this.y+"</b> %" ;
	            },
	            backgroundColor:'#085696',
                style:{color:"#fff"},
                borderWidth:0
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'top',
	            floating: true,
	            y: -10,
	            x:10,
	            itemStyle:{fontSize:"12px",lineHeight:"12px",color:"black"},
	            enabled:false
	        },
	        plotOptions:{
	        	column:{
	        		pointPadding:0.3,
	        		borderWidth:0,
	        	}
	        },
	        series: [{
	            type: 'column',
	            data: data,
	            color: '#00a7ed',
	            tooltip: {
	                valueSuffix: "%",
	            }
	        }]
	    });
}
//柱状图
function outlinecreatecolumn(id,data){
	$('#'+id).highcharts({
		chart: {
			backgroundColor:'rgba(0,0,0,0)'
		},
		xAxis: {
			categories: ['石家庄', '秦皇岛', '张家口', '廊坊','唐山', '衡水', '保定', '承德','邢台','邯郸','沧州'],
			gridLineWidth:0,
			lineWidth:0,
			tickLength:0,
			labels:{
				style:{"color":"#FFFFFF", "fontSize":"12px","fontFamily":"微软雅黑"}
			}
		},
		yAxis: [{ 
			gridLineWidth:0,
			labels: {
				formatter: function() {
					//return this.value;	
				},
				style: {
					"color": "black",
					"fontSize":"11px"
				}
			},
			title: {
				text: null,
				style: {
					color: "black"
				}
			}/*,
	            tickInterval:1000*/
		}],
		tooltip: {
			// shared: true
			formatter:function(){
				return this.x +":  "+"<b>"+this.y+"</b> %" ;
			},
			 backgroundColor:'#085696',
	         style:{color:"#fff"},
	         borderWidth:0
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'top',
			floating: true,
			y: -10,
			x:10,
			itemStyle:{fontSize:"12px",lineHeight:"12px",color:"black"},
			enabled:false
		},
		plotOptions:{
			column:{
				pointPadding:0.3,
				borderWidth:0,
			}
		},
		series: [{
			type: 'column',
			data: data,
			color: '#cc4263',
			tooltip: {
				valueSuffix: "%",
			}
		}]
	});
}

