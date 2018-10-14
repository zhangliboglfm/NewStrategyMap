/*前台数字格式化,添加","分隔符*/
	function formateNumber(num){
		num=num.toString();
		/*判断是否有小数*/
		var xiaoshu="";
		if(num.indexOf(".")>=0){
			num1=Number(num).toFixed(3)
			xiaoshu="."+num1.substring(num1.indexOf(".")+1,num1.indexOf(".")+4)
			num=num.substring(0,num.indexOf("."));	
		}
		/*判断正负*/
		var jianhao="";
		if(num.substring(0,1)=="-"){
			num=num.substring(1,num.length);
			jianhao="-";
		}
		var num=(num||0).toString(),result="";
		while(num.length>3){
			result=","+num.slice(-3)+result;
			num=num.slice(0,num.length-3);
		}
		if(num){
			result=jianhao+num+result+xiaoshu;
		}
		return result;
	}
	
	
/*格式化显示数字 */
	function formateNumber1(classname){
		/*if (IntegerNum==null||IntegerNum==undefined||IntegerNum==""){
			IntegerNum = 3;
		}
		if (Decimal==null||Decimal==undefined||Decimal==""){
			Decimal = 2;
		}*/
		var dom = document.getElementsByClassName(classname);
		$(dom).each(function(){
			$(this).blur(function(){
				var html=$.trim($(this).html());
				if (html == null ||html==""|| html==undefined){
					$(this).attr("data-num","");
					$(this).html("");
					return;
				}
				var patten = /^\d+$/;
				if (!patten.test(html)){
					alert("请输入正整数");
					$(this).html($(this).attr("data-num"));
					/*停止冒泡*/
					if(event.stopPropagation){
						event.stopPropagation();//针对Mozilla和Opera
					}else if(window.event){
						window.event.cancelBubble=true;//针对IE
					}
					return ;
				}else{
					if (html<=9999){
						$(this).html(html);
						$(this).attr("data-num",html);
					}else if(html>9999&&html<99999999999){
						var str = (parseInt(html)).toExponential(2);
						var str1 = str.substring(6,str.length);
						if(str1==4){
							$(this).html(str.substring(0,4)+"万");
						}else if (str1 == 5){
							$(this).html(str.substring(0,4)*10+"万");
						}else if(str1==6){
							$(this).html(str.substring(0,4)*100+"万");
						}else if(str1==7){
							$(this).html(str.substring(0,4)*1000+"万");
						}else if(str1==8){
							$(this).html(str.substring(0,4)+"亿");
						}else if(str1==9){
							$(this).html(str.substring(0,4)*10+"亿");
						}else if(str1==10){
							$(this).html(str.substring(0,4)*100+"亿");
						}
						$(this).attr("data-num",html);
						
					}else{
						alert("输入数据过大，请重新输入");
						$(this).html($(this).attr("data-num"));
					}
				}
				
			});
			
			$(this).focus(function(){
				$(this).html($(this).attr("data-num"));
			})
			
		});
		
		
	}
	