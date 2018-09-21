/*柱状图*/
app.title = '坐标轴刻度与标签对齐';

option = {
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



/*标准柱状图*/

option = {
    title : {
        text: '某地区蒸发量和降水量',
        subtext: '纯属虚构'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['蒸发量','降水量']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'蒸发量',
            type:'bar',
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'降水量',
            type:'bar',
            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
            markPoint : {
                data : [
                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
};


/*饼图*/

option = {
    title : {
        text: '某站点用户访问来源',
        subtext: '纯属虚构',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true, 
                type: ['pie', 'funnel'],
                option: {
                    funnel: {
                        x: '25%',
                        width: '50%',
                        funnelAlign: 'left',
                        max: 1548
                    }
                }
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:335, name:'直接访问'},
                {value:310, name:'邮件营销'},
                {value:234, name:'联盟广告'},
                {value:135, name:'视频广告'},
                {value:1548, name:'搜索引擎'}
            ]
        }
    ]
};
   
/*极地柱状图*/
var weekDay = 0;
var data = [{
    name: '轻度严重',
    value: [1, 2, 5, 7, 6, 7, 1]
}, {
    name: '中度严重',
    value: [3, 4, 5, 8, 2, 9, 10]
}, {
    name: '重度严重',
    value: [2, 4, 5, 7, 3, 9, 11]
}]
option = {
      title: {
        
                text: '事故分布以及严重情况',
       
                subtext: '点击种类出现分布情况',
      
                textAlign: 'left'
    },

      tooltip: {
       
                trigger: 'item',
        
                padding: 10,
       
                backgroundColor: '#222',
        
                borderColor: '#777',
        
                borderWidth: 1,
        
                formatter: tooltipFormatter,

    },
      angleAxis: {
        
                 type: 'category',
       
                  data: [{
            
                        value: '事故类型零',
           
                        textStyle: {
                
                                    fontSize: 15,
               
                                    color: 'blue'
           
                                    }
       
                          }, '事故类型一', '事故类型二', '事故类型三', '事故类型四', '事故类型五', '事故类型六'],
       
                  z: 10
   
                 },
        polar: {
        
               center: ['50%', '50%'],
      
               radius: 260,
    
              },
       radiusAxis: { z: 10},
        series: [{
       
             type: 'bar',
        
             data: [
           
                   data["0"].value["0"],
           
                   data["0"].value["1"],
           
                   data["0"].value["2"],
            
                   data["0"].value["3"],
            
                   data["0"].value["4"],
            
                   data["0"].value["5"],
           
                   data["0"].value["6"],
       
                  ],
             coordinateSystem: 'polar',
             name: data["0"].name,
             stack: 'a',
             itemStyle: {
       
                 normal: {
                b
                      orderWidth: 4,
  
                      borderColor: '#ffffff'
     
                        },
     
                 emphasis: {
          
                       borderWidth: 0,
      
                       shadowBlur: 10,
      
                      shadowOffsetX: 0,
    
                     shadowColor: 'rgba(0, 0, 0, 0.5)'
     
                          }
     
                        }
   
             }]
}

function tooltipFormatter(params) {
    
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

}                 