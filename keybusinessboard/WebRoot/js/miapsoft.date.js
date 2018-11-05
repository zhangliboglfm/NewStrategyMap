/*计算日期加N天*/
function getNextDateStr(curdatestr,addnum){
	var nextdatestr="";
	var date = new Date(curdatestr);
  	var ms = date.getTime()+addnum*24*60*60*1000;
  	var curdate = new Date(ms);
  	var year = curdate.getFullYear();
  	var month = curdate.getMonth()+1;
  	var date = curdate.getDate();
  	if(month<10){
  		month="0"+month;
  	}
  	if(date<10){
  		date="0"+date;
  	}
	nextdatestr = year.toString()+month.toString()+date.toString();
  	return nextdatestr;
}
/*计算日期减N天*/
function getPrevDateStr(curdatestr,prevnum){
	var prevdatestr="";
	var date = new Date(curdatestr);
  	var ms = date.getTime()-prevnum*24*60*60*1000;
  	var curdate = new Date(ms);
  	var year = curdate.getFullYear();
  	var month = curdate.getMonth()+1;
  	var date = curdate.getDate();
  	if(month<10){
  		month="0"+month;
  	}
  	if(date<10){
  		date="0"+date;
  	}
  	prevdatestr = year.toString()+month.toString()+date.toString();
  	return prevdatestr;
}
/*计算日期加N天*/
function getNextDateStrWithSplit(curyear,curmonth,curdate,addnum){
	var nextdatestr="";
	var date = new Date(curyear,curmonth-1,curdate);
  	var ms = date.getTime()+addnum*24*60*60*1000;
  	var curdate = new Date(ms);
  	var year = curdate.getFullYear();
  	var month = curdate.getMonth()+1;
  	var date = curdate.getDate();
  	if(month<10){
  		month="0"+month;
  	}
  	if(date<10){
  		date="0"+date;
  	}
	nextdatestr = year.toString()+month.toString()+date.toString();
  	return nextdatestr;
}
/*计算日期减N天*/
function getPrevDateStrWithSplit(curyear,curmonth,curdate,prevnum){
	var nextdatestr="";
	var date = new Date(curyear,curmonth-1,curdate);
  	var ms = date.getTime()-prevnum*24*60*60*1000;
  	var curdate = new Date(ms);
  	var year = curdate.getFullYear();
  	var month = curdate.getMonth()+1;
  	var date = curdate.getDate();
  	if(month<10){
  		month="0"+month;
  	}
  	if(date<10){
  		date="0"+date;
  	}
	nextdatestr = year.toString()+month.toString()+date.toString();
  	return nextdatestr;
}
/*格式化时间*/
function FormateDate(date){
	var datestr="";
	var year = date.getFullYear();
  	var month = date.getMonth()+1;
  	var date = date.getDate();
  	if(month<10){
  		month="0"+month;
  	}
  	if(date<10){
  		date="0"+date;
  	}
  	datestr = year.toString()+month.toString()+date.toString();
  	return datestr;
}