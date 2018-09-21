
//柱状图
function createcolumn(data,id){
	 $('#'+id).highcharts({
	        chart: {
	            backgroundColor:'rgba(0,0,0,0)'
	        },
	        title: {
	            style:{"color":"black","fontSize":"14px"}
	        },
	        subtitle: {
	        },
	        xAxis: {
	            categories: data.jsonx1,
	            crosshair: true,
	             labels:{
	            	 formatter: function() {
			              if(this.value.length>4){
			            	  return this.value.substring(0,2); 
			              }
			            },
			         style:{"color":"black", "fontSize":"13px","fontFamily":"宋体"}
			       }
	        },
	        yAxis: [{ // Primary yAxis
	        	//gridLineColor:'black',
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
	            	return this.x +":  "+"<b>"+this.y+"</b> 个" ;
	            }
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
	        plotOptions: {
		      column:{
			        /*borderColor:"#00ffff",*/
				    color:"#4DBeff"
		      }
		    },
	        series: [{
	            type: 'column',
	            data: data.data1,
	            color: '#FCA76D',
	            tooltip: {
	                valueSuffix: "个",
	            }
	        }]
	    });
}


//饼图
function createpiequan(data,divid){
	$('#'+divid).highcharts({
		  chart: {
			  plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false,
				backgroundColor:'rgba(0,0,0,0)'
				//marginTop:30
	        },
	        title: {
	            style:{"color":"black","fontSize":"14px"}
	        },
	        subtitle: {
	        },
	        legend: {//设置图例位置
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'bottom',
				itemStyle:{fontSize:"10px",color:"black"},
				floating: false, 
				borderWidth: 0
			},
	        plotOptions: {
	            pie: {
	            	size:"110%", //设置显示饼图大小
					allowPointSelect: true,
					cursor: 'pointer',
					colors:["#E45B49","#0DCDC0"],
					borderColor:"white",
					showInLegend: true,
	                dataLabels: {
	                	distance:-25,
						enabled : true,
						format : '{point.percentage:.1f}%',
						style : {
							"textShadow":"none",
							"color":"#FFF",
							"fontSize":"16px",
							"fontFamily":"微软雅黑"
						}},
	            }
	        },
	        series: [{
	            type: 'pie',
	            innerSize: '30%',
	            data:data,
	            tooltip:{
	            	pointFormat: '占比: <b>{point.percentage:.1f}%</b>;'
	            }
	        }]
	});
}
function createLine(data,id){

	 $('#'+id).highcharts({
		 chart: {
			  plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false,
				backgroundColor:'rgba(0,0,0,0)'
				//marginTop:30
	        },
			xAxis: {
	            categories: data.datax,
	            crosshair: true,
	            labels:{
	            	 formatter: function() {
			            
			            	  return this.value.substring(3,7);
			            },
			         style:{"color":"black", "fontSize":"13px","fontFamily":"宋体"}
			       }
	        },
	        yAxis: { // Primary yAxis
	        	//gridLineColor:'black',
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
	        },
	        legend: {//设置图例位置
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'top',
				itemStyle:{fontSize:"10px",color:"black"},
				floating: false, 
				borderWidth: 0
			},
	        series:data.datashuju
	    });

}