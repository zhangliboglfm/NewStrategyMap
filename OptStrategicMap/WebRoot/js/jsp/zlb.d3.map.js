function drawHeBeiMap(id1,id2,data){
		var svg;//svg画布
		//颜色渐变
		var colorScale=d3.scale.linear()
		.domain([50,150,300])
		.range(["rgba(1,169,240,0.3)","rgba(1,169,240,0.6)","rgba(1,169,240,0.9)"]);
		var data=[
					  {name:"石家庄",value:200000,regionid:"311350"},
                      {name:"保定",value:69,regionid:"311350"},
                      {name:"唐山",value:120,regionid:"311350"},
                      {name:"秦皇岛",value:210,regionid:"311350"},
                      {name:"廊坊",value:230,regionid:"311350"},
                      {name:"张家口",value:250,regionid:"311350"},
                      {name:"衡水",value:130,regionid:"311350"},
                      {name:"邢台",value:70,regionid:"311350"},
                      {name:"邯郸",value:90,regionid:"311350"},
                      {name:"沧州",value:98,regionid:"311350"},
                      {name:"承德",value:170,regionid:"311350"},
                      {name:"雄安",value:150,regionid:"311350"}
                   ];
		// 获取轮廓信息
		d3.json("js/mapjson/HeBeiMap.json",function(pathdata){
				var dom = document.getElementById(id1);
				dom.innerHTML= "";
				dom.style.zIndex = 99999;
				document.getElementById(id2).style.zIndex =-1;
				var height =dom.offsetHeight;
				var width = dom.offsetWidth;
				dom.getElementsByClassName("");
				var margin ={left:10,right:10,top:20,bottom:20};
				
				//生成svg画布
				svg = d3.selectAll("#"+id1)
				.append("svg")
				.attr("class","svgMap")
				.attr("width",width)
				.attr("height",height)
				.attr("viewBox","35 0 400 350")
				.attr("xmlns","http://www.w3.org/2000/svg")
				.attr("transform","translate(0,0)");
				
				//高斯模糊滤镜
				svg.append("defs")
				.append("filter")
				.attr("id","shadow")
				.append("feColorMatrix")				
				.attr("type","matrix")
				.attr("result","color")
				.attr("values","0 0 0 0.5 0 0 0 0 0.5 0 0 0 0 0.5 0 0 0 0 0.5 0");
				
				d3.selectAll("#shadow")
				.append("feGaussianBlur")
				.attr("in","color")
				.attr("stdDeviation","3")
				.attr("result","blur");
				
				d3.selectAll("#shadow")
				.append("feOffset")
				.attr("in","color")
				.attr("dx","3")
				.attr("dy","3")
				.attr("result","offset");
				
				d3.selectAll("#shadow")
				.append("feMerge")
				.append("feMergeNode")
				.attr("in","offset");
				
				d3.selectAll("#shadow")
				.select("feMerge")
				.append("feMergeNode")
				.attr("in","SourceGraphic");

				
				//地市地图分组
				svg.selectAll(".group")
				.data(data)
				.enter()
			 	.append("g")
			 	.attr("class","group")
			 	.on("mousemove",tooltipsShow)
				.on("mouseout",tooltipsHide)
				.on("click",changMap);
				
			 	//12个地市轮廓
			 	d3.selectAll(".group")
			 	.append("path")
			 	.attr("class","dishiPath")
			 	.attr("d",function(d){
			 			return pathdata[d.name]["path1"];
			 	})
			 	.attr("fill",function(d){
			 		return colorScale(d.value);
			 	});
			 	
			 	//12个地市名字边框
			 	d3.selectAll(".group")
			 	.append("rect")
			 	.attr("class","textRect")
			 	.attr("width",function(d){
			 			return d.name.length*10+6;
			 	})
			 	.attr("height","16")
			 	.attr("transform",function(d){
			 			return pathdata[d.name]["path2"]+" translate(-3,-12)";
			 	});
			 	
			 	//12各地市名字
			 	d3.selectAll(".group")
			 	.append("text")
			 	.attr("class","dishiName")
			 	.attr("transform",function(d){
			 			return pathdata[d.name]["path2"];
			 	})
			 	.text(function(d){
			 			return d.name;
			 	});
			 	
			//tips文字
			var tips = svg.append("g").attr("class","tips");
			tips.append("rect")
			  .attr("class","tips-border")
			  .attr("width",30)
			  .attr("height",35)
			  .attr("fill","rgba(1,169,240,0.8)")
			  .attr("rx",1)
			  .attr("ry",1);
			  
			var wording1 = tips.append("text")
			  .attr("class","tips-text")
			  .attr("x",5)
			  .attr("y",14)
			  .attr("fill","white")
			  .attr("font-size",10)
			  .text("");
			var wording2 = tips.append("text")
			  .attr("class","tips-text1")
			  .attr("x",5)
			  .attr("y",28)
			  .attr("fill","white")
			  .attr("font-size",10)
			  .text("");
			  
			
			function tooltipsShow(d){
				d3.select(this).select(".dishiPath").attr("fill","#EBCB1F").attr("filter","url(#shadow)");
				
				var m = d3.mouse(this);
     			wording1.text(d.name);
     			wording2.text("总流量:"+d.value);
     			var tipswidth = document.getElementsByClassName("tips-text1")[0].getBoundingClientRect().width;
				d3.select(".tips")
				.select("rect")
				.attr("width",tipswidth*0.73);
				d3.select(".tips")
      			.attr("transform","translate("+ (m[0]+10)+ ","+(m[1]+10) + ")");
    			d3.select(".tips").style("display","block");
			}
			
			function tooltipsHide(d){
				d3.select(this).select(".dishiPath").attr("fill",function(d){
					return colorScale(d.value);
				}).attr("filter","none");
				d3.select(".tips").style("display","none");
			}
			
			
			function changMap(d){
				changeRegionDiv(d.regionid,d.name,"河北");
	        	HbeiCountyMap(id2,id1,{},d.name);
			} 
		});
		
}