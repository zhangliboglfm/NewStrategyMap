//画2个轴的图形
function draw2axisChart(list, dom, lengends, types, units, field1, field2, field3) {
	dom.html("");
	if (list !=null) {
		var categories = new Array();
		var data1 = new Array();
		var data2 = new Array();
		for ( var i = 0; i < list.length; i++) {
			var obj = list[i];
			categories.push(obj[field1]);
			data1.push(obj[field2]);
			data2.push(obj[field3]);
		}
		Highcharts.setOptions({
			colors : [ "#7cb5ec", "#f7a35c", "#ffffff", "#ffffff", "#ffffff" ]
		});
		dom.highcharts({
			chart : {
				backgroundColor : 'rgba(0,0,0,0)'
			},
			title : {
				text : ''
			},
			xAxis : [ {
				categories : categories,
				labels : {
					style : {
						color : "black"
					}
				}
			} ],
			yAxis : [ {
				labels : {
					formatter : function() {
						return this.value + units[0];
					},
					style : {
						color : "black"
					}
				},
				title : {
					text : ""
				}
			}, {
				labels : {
					formatter : function() {
						return this.value + units[1];
					},
					style : {
						color : "black"
					}
				},
				title : {
					text : ""
				},
				opposite : true
			} ],
			tooltip : {
				shared : true
			},
			legend : {
				verticalAlign : 'top'
			},
			series : [ {
				name : lengends[0],
				type : types[0],
				data : data1,
				 color:"#4798EA",
				tooptip : {
					valueSuffix : units[0]
				}
			}, {
				name : lengends[1],
				type : types[1],
				data : data2,
				yAxis : 1,
				 color:"#5dc800",
				tooptip : {
					valueSuffix : units[1]
				}
			} ]
		});
	} else {
		dom.html("<div style=\"width:100%;height:100%;text-align:center;\">图形无数据</div>");
	}
}
// 画单类型图形
//type:bar/column/pie/line
//例子：drawChart(data.list,$("#chart"),["适配客户数"],"column","户","REGION_NAME",["SUBS_CNT"]);
function drawChart(list, dom, lengends, type, unit, category, fields) {
	dom.html("");
	if (list.length > 0) {
		var categories = new Array();
		var series = new Array();
		for ( var j = 0; j < lengends.length; j++) {
			var data = new Array();
			for ( var i = 0; i < list.length; i++) {
				var obj = list[i];
				data.push(obj[fields[j]]);
			}
			var obj = {
				name : lengends[j],
				data : data
			};
			series.push(obj);
		}
		for ( var i = 0; i < list.length; i++) {
			var obj = list[i];
			categories.push(obj[category]);
		}

		Highcharts.setOptions({
			colors : [ "#4DBeff", "#f7a35c", "#ffffff", "#ffffff", "#ffffff" ]
		});
		dom.highcharts({
			chart : {
				type : type,
				backgroundColor : 'rgba(0,0,0,0)'
			},
			title : {
				text : ''
			},
			xAxis : {
				categories : categories,
				labels: {
					overflow: 'justify'
				}
			},
			yAxis : {
				labels : {
					formatter : function() {
						return this.value + unit;
					},
					style : {
						color : "black"
					},
					overflow: 'justify'
				},
				title : {
					text : ""
				}
			},
			tooltip : {
				valueSuffix : unit
			},
			legend : {
				verticalAlign : 'bottom',
				align: 'left'
			},
			series : series
		});
	} else {
		dom.html("<div style=\"width:100%;height:100%;text-align:center;\">图形无数据</div>");
	}
}
//饼图
//例子：drawPieChart1(data.list,$("#chart"),"适配客户数","户","LAYER_DESC","SUBS_CNT");
function drawPieChart1(list, dom,nametext,unit,key,value) {
	var length = list.length;
	var piedata = new Array();;
	if (length > 0) {
		for(var i=0;i<length;i++){
			var obj = list[i];
			var onedata = {name:obj[key],y:obj[value]};
			piedata.push(onedata);
		}
		dom.highcharts({
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false,
				backgroundColor : 'rgba(0,0,0,0)'
			},
			tooltip : {
				pointFormat : '{series.name}: {point.y}' + unit + ';占比: <b>{point.percentage:.1f}%</b>;'
			},		
			plotOptions : {
				pie : {
					size : "80%",
					allowPointSelect : true,
					cursor : 'pointer',
					colors : [ "#2EC8CA", "#FB654D", "#FBCE35", "#ce0400", "#ff8d5b", "#d22bd0", "#6d38da" ],
					borderColor : "white",
					showInLegend : true,
					dataLabels : {
						enabled : false,
						format : '<b>{point.name}</b>: {point.percentage:.1f} %',
						style : {
							color : "black"
						}
					}
				}
			},
			series : [ {
				type : 'pie',
				name : nametext,
				data : piedata
			} ]
		});
	}else{
		dom.html("<div style=\"width:100%;height:100%;text-align:center;\">图形无数据</div>");
	}
}
