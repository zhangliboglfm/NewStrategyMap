package com.miapsoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.miapsoft.util.ExcelUtil;
import com.miapsoft.manager.InterestingPointCompareManager;

/**
 * <p>Title: InterestingPointCompareController.java</p>
 * <p>Description: TODO</p>
 * @author: 张立保
 * @time: 2017-6-23
 * <p>Company: 精益有容（北京）科技有限公司</p>
 * <p>Copyright:Copyright (c) 2016</p>
 */

@Controller
public class InterestingPointCompareController {
	
	@Resource
	private InterestingPointCompareManager interestingPointCompareManager;

	@RequestMapping("interestingpointcompare.do")
	public String interestingpointcompare(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		JSONArray result = new JSONArray();
		String date = request.getParameter("date");
		String datetype = request.getParameter("datetype");
		String regionid = URLDecoder.decode(request.getParameter("regionid"), "UTF-8").replace("[", "").replace("]", "").replace("\"", "");
		String [] regionId =regionid.split(",");
		String indexid = request.getParameter("indexid");
		Object userIdObj = request.getSession().getAttribute("userId");
		String userId = "";
		if(userIdObj == null){
			return "";
		}else{
			userId = userIdObj.toString();
		}
		result = interestingPointCompareManager.queryRelIndex(date, datetype, regionId, indexid,userId);
		String maxDateD=interestingPointCompareManager.getMaxDate("D");
		String maxDateM=interestingPointCompareManager.getMaxDate("M");
		request.setAttribute("date", date);
		request.setAttribute("maxDateD", maxDateD);
		request.setAttribute("maxDateM", maxDateM);
		request.setAttribute("datetype", datetype);
		request.setAttribute("indexid", indexid);
		request.setAttribute("date", date);
		request.setAttribute("regionid", regionid );
		request.setAttribute("result", result);
		return "jsp/interestingpointcompare";
	}

	@ResponseBody
	@RequestMapping("switchdata.do")
	public String queryRelIndex(HttpServletRequest request,HttpServletResponse response){
		JSONArray result = new JSONArray();
		String date=request.getParameter("date");
		String regionid = request.getParameter("regionid");
		String datetype=request.getParameter("datetype");
		String [] regionId  =regionid.split(",");
		String indexid = request.getParameter("indexid");
		Object userIdObj = request.getSession().getAttribute("userId");
		String userId = "";
		if(userIdObj == null){
			return "";
		}else{
			userId = userIdObj.toString();
		}
		result = interestingPointCompareManager.queryRelIndex(date, datetype, regionId, indexid,userId);
		return 	result.toString();
	}

	@RequestMapping("interestingPointCompareDowload.do")
	public String interestingPointCompareDowload(HttpServletRequest request,HttpServletResponse response) throws IOException{
		JSONArray result = new JSONArray();
		String date=request.getParameter("date");
		String regionid = URLDecoder.decode(request.getParameter("regionid"), "UTF-8").replace("\"", "");
		String datetype=request.getParameter("datetype");
		String [] regionId  =regionid.split(",");
		String indexid = request.getParameter("indexid");
		Object userIdObj = request.getSession().getAttribute("userId");
		String userId = "";
		if(userIdObj == null){
			return "";
		}else{
			userId = userIdObj.toString();
		}
		result = interestingPointCompareManager.queryRelIndex(date, datetype, regionId, indexid,userId);
		
		/*设置表格导出*/
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(result.size()!=0){
			for (int j = 0; j < result.size(); j++) {
				JSONObject obj=(JSONObject)result.get(j);
				Map<String, Object> mapValue = new HashMap<String, Object>();
				String date1=null;
				if(datetype.equalsIgnoreCase("M")){
					 date1=date.substring(0, 4)+"年"+date.substring(4,6)+"月";
				}else {
					date1=date.substring(0, 4)+"年"+date.substring(4,6)+"月"+date.substring(6, 8)+"日";
				}
				mapValue.put("STATIS_MONTH", date1);
				mapValue.put("InterestingPointName", obj.get("name"));
				JSONArray indexValue=(JSONArray) obj.get("Indexvalue");
				for(int i=0;i<indexValue.size();i++){
					JSONObject obj2=(JSONObject) indexValue.get(i);
					mapValue.put("indexValue"+i, obj2.get("indexValue").toString());
				}
				list.add(mapValue);
			}
		}
		/*字节数组流，可以捕获内存缓冲区的数据，转换为字节数组*/
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		 String fileName="";
		 try {
			 fileName="兴趣点对比情况";
			 JSONArray obj3=(JSONArray) ((JSONObject)result.get(0)).get("Indexvalue");
			 String [] columnNames= new String [obj3.size()+2];
			 columnNames[0]="统计时间";
			 columnNames[1]="兴趣点名称";
			 for(int i1=2;i1<columnNames.length;i1++){
				 JSONObject obj4=(JSONObject) obj3.get(i1-2);
				 columnNames[i1]=obj4.getString("indexName")+"("+obj4.get("indexUnit").toString()+")";
			 }
			 String keys[]  =  new String [obj3.size()+2];
			 keys[0]="STATIS_MONTH";
			  	 keys[1]="InterestingPointName";
			 for(int i1=2;i1<keys.length;i1++){
				 keys[i1]="indexValue"+(i1-2);
			 }
			 String [] sheetName ={"兴趣点对比详情"};
			 ExcelUtil.createWorkBook(list,keys,columnNames,sheetName).write(os); 
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 byte[] content = os.toByteArray();
		 InputStream is = new ByteArrayInputStream(content);
		 // 设置response参数，可以打开下载页面
		 response.reset();
		 response.setContentType("application/vnd.ms-excel;charset=utf-8");
		 response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		 ServletOutputStream out = response.getOutputStream();
		 BufferedInputStream bis = null;
		 BufferedOutputStream bos = null;
		 try {
			 bis = new BufferedInputStream(is);
			 bos = new BufferedOutputStream(out);
			 byte[] buff = new byte[2048];
			 int bytesRead;
			 // Simple read/write loop.
			 while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				 bos.write(buff, 0, bytesRead);
			 }
		 } catch (final IOException e) {
			 throw e;
		 } finally {
			 if (bis != null)
				 bis.close();
			 if (bos != null)
				 bos.close();
		 }
		return null;
	}
		
	
	public InterestingPointCompareManager getInterestingPointCompareManager() {
		return interestingPointCompareManager;
	}

	public void setInterestingPointCompareManager(
			InterestingPointCompareManager interestingPointCompareManager) {
		this.interestingPointCompareManager = interestingPointCompareManager;
	}
	
	
}
