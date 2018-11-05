function initbackgroundMap(divid,lvlid){
	   //地图缩放比例
	   var zoomNum=0;
	   //地图中心位置
	   var centerNum=[];
	   //地图中心区域外地图设置
	   var regionSetting=[];
	   //放大比例
	   var myChart = echarts.init(document.getElementById(divid));
		   regionSetting=[
		                   {	
		                    	name:'山西',
		                    	z:1,
		                    	itemStyle: {
			       	                 normal: {
			       	                     areaColor: '#393F4D',
			       	                     borderColor: '#00B9E9',
			       	                     width:1,
			       	                 },
			       	              emphasis: {
				       	            	 areaColor: '#393F4D',
			       	                     borderColor: '#00B9E9',
			       	                     width:1, 
			       	                 }
			       	             },
		                   label: {
				             	 normal:{
				             	 	show: false,
				             	 },
				                 emphasis: {
				                    show: false,
				                 }
				             }
		                   },
		                   {	
		                    	name:'山东',
		                    	z:1,
		                    	itemStyle: {
			       	                 normal: {
			       	                     areaColor: '#393F4D',
			       	                     borderColor: '#00B9E9',
			       	                     width: 11,
			       	                 },
			       	              emphasis: {
			       	            	 areaColor: '#393F4D',
		       	                     borderColor: '#00B9E9',
		       	                     width: 11,
			       	                 }
			       	             },
			       	             label: {
					             	 normal:{
						             	 	show: false,
						             	 },
						                 emphasis: {
						                    show: false,
						                 }
						             }
		                   },
		                   {	
		                    	name:'河南',
		                    	z:1,
		                    	itemStyle: {
			       	                 normal: {
			       	                     areaColor: '#393F4D',
			       	                     borderColor: '#00B9E9',
			       	                     width:1,
			       	                 },
			       	              emphasis: {
			       	            	 areaColor: '#393F4D',
		       	                     borderColor: '#00B9E9',
		       	                     width: 11,
			       	                 }
			       	             },
				       	          label: {
						             	 normal:{
						             	 	show: false,
						             	 },
						                 emphasis: {
						                    show: false,
						                 }
						             }
		                   },
		                   {	
		                    	name:'辽宁',
		                    	z:1,
		                    	itemStyle: {
			       	                 normal: {
			       	                     areaColor: '#393F4D',
			       	                     borderColor: '#00B9E9',
			       	                     width: 11,
			       	                 },
			       	              emphasis: {
			       	            	 areaColor: '#393F4D',
		       	                     borderColor: '#00B9E9',
		       	                     width: 11,
			       	                 }
			       	             }
		                   },
		                   {	
		                    	name:'内蒙古',
		                    	z:1,
		                    	itemStyle: {
			       	                 normal: {
			       	                     areaColor: '#393F4D',
			       	                     borderColor: '#00B9E9',
			       	                     width: 2,
			       	                 },
			       	              emphasis: {
				       	            	 areaColor: '#393F4D',
			       	                     borderColor: '#00B9E9',
			       	                     width: 11,
				       	            }
			       	             },
		                   },
		                   {	
		                    	name:'陕西',
		                    	z:1,
		                    	itemStyle: {
			       	                 normal: {
			       	                     areaColor: '#393F4D',
			       	                     borderColor: '#00B9E9',
			       	                     width: 11,
			       	                 },
			       	              emphasis: {
				       	            	 areaColor: '#393F4D',
			       	                     borderColor: '#00B9E9',
			       	                     width: 11,
				       	            }
			       	             },
		                   }
		                ];
		   
		   option={
			         //网页背景部分
			         backgroundColor: 'transparent',
			         series:[{
			             //
			        	 type:'map',
			             map: 'hebei',
			             zoom:0.63,
			             aspectScale:0.71,
			             center:[117.252334,38.969119],
			             label: {
			             	 normal:{
			             	 	show: true,
			             	 	textStyle: {
					                color: '#000',
					                fontSize: 14,
					            },
			             	 },
			                 emphasis: {
			                    show: true,
			             	 	 textStyle: {
					                color: '#000',
					                fontSize: 14,
					             },
			                 }
			             },
			             //拖动、滚轮缩放设置
			             roam: false,
			             selectMode:'single',
			             itemStyle: {
			                 normal: {
			                     areaColor: '#D1D1D4',
			                     borderColor: '#27354E',
			                     width:10,
			                 },
			                 emphasis: {
			                	 areaColor: '#D1D1D4',
			                     borderColor: '#27354E',
			                     width:10,
				                 }
			             },
			             data:[{
			            	 name:'石家庄市',
			            	 selected:true,
			            	 itemStyle: {
				                 normal: {
				                     areaColor: 'red',
				                     borderColor: '#27354E',
				                     width:10,
				                 },
				                 emphasis: {
				                	 areaColor: 'red',
				                     borderColor: '#27354E',
				                     width:10,
					                 }
				             },
			            
			             }]
			         }]
		   }
	    myChart.setOption(option);
		   
		if("1"==lvlid){
			myChart.on("click",function(param){
				if("石家庄"==param.name){
					window.parent.changedishi(311,param.name);
				}else if("秦皇岛"==param.name){
					window.parent.changedishi(335,param.name);
				}else if("张家口"==param.name){
					window.parent.changedishi(313,param.name);
				}else if("廊坊"==param.name){
					window.parent.changedishi(316,param.name);
				}else if("唐山"==param.name){
					window.parent.changedishi(315,param.name);
				}else if("衡水"==param.name){
					window.parent.changedishi(318,param.name);
				}else if("保定"==param.name){
					window.parent.changedishi(312,param.name);
				}else if("承德"==param.name){
					window.parent.changedishi(314,param.name);
				}else if("邢台"==param.name){
					window.parent.changedishi(319,param.name);
				}else if("邯郸"==param.name){
					window.parent.changedishi(310,param.name);
				}else if("沧州"==param.name){
					window.parent.changedishi(317,param.name);
				}
			});
		}
		return myChart;
}
