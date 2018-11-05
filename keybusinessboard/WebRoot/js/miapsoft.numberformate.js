/*前台数字格式化*/
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