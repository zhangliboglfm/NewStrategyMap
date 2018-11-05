package com.miapsoft.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miapsoft.manager.ProductionManager;
import com.miapsoft.util.FileUtil;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;


@Controller
public class ProductionController {
	
	private static String PGIMGPATH=ServerFilePath.getPhotogdir()+"/";//摄影家头像,作品使用
	
	@Resource
	public ProductionManager productionManager;
	
	public static String linshiPath=ServerFilePath.getPhotogdir();
	
	@RequestMapping(value="production.do")
	public String Main(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String userId=session.getAttribute("userId")+"";
		String photogid=request.getParameter("photogid");
		String workNum = request.getParameter("workNum");
		String audit = request.getParameter("audit");
		if("".equals(workNum)||workNum==null){
			request.setAttribute("operNum", 0);
		}else {
			request.setAttribute("operNum", Integer.parseInt(workNum)-1);
		};
		String photogName = productionManager.getPhotogName(photogid);
		request.setAttribute("userId", userId);
		request.setAttribute("photogid", photogid);
		request.setAttribute("photogName", photogName);
		request.setAttribute("audit", audit);
		int workAllNum = productionManager.getWorkNumByPhotogId(photogid);
		JSONArray worksData = productionManager.getWorksDataByPhotogID(photogid);
		request.setAttribute("AllNum", workAllNum);
		request.setAttribute("worksData", worksData);
		return "pgmag/production";
	}
	
	
	
	
	
	/**
	 * 获取标签信息
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("getLabelList.do")
	public String getLablist(HttpServletRequest request,HttpServletResponse response){
		String Workid = request.getParameter("Workid");
		JSONArray result = productionManager.getLabelList(Workid);
		return result.toString();
	}
	
	/**
	 * 改变图片审核状态
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("changeExamineStatus.do")
	public String changeExamineStatus(HttpServletRequest request,HttpServletResponse response){
		String Workid = request.getParameter("Workid");
		String AUDIT_STATUS = request.getParameter("AUDIT_STATUS");
		String AUDIT_DESC = request.getParameter("AUDIT_DESC");
		String AUDIT_PERSN = request.getParameter("AUDIT_PERSN");
		String type = request.getParameter("type");
		String result = productionManager.changeExamineStatus(Workid,AUDIT_STATUS,AUDIT_DESC,AUDIT_PERSN,type);
		return result.toString();
	}
	
	/**
	 * 对作品标签进行增，删
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("operateWorkLabel.do")
	public String operateLabel(HttpServletRequest request,HttpServletResponse response){
		String Workid = request.getParameter("Workid");
		String LABEL_ID = request.getParameter("LABEL_ID");
		String operate = request.getParameter("operate");
		String result = productionManager.operateWorkLabel(Workid,LABEL_ID,operate);
		return result;
	}
	
	
	/**
	 * 添加自定义标签
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addDefinedLabel.do")
	public String addDefinedLabel(HttpServletRequest request,HttpServletResponse response){
		String LabelName = request.getParameter("LabelName");
		String LabelDesc = request.getParameter("LabelDesc");
		JSONObject result = productionManager.addDefinedLabel(LabelName,LabelDesc);
		return result.toString();
	}
	
	/**
	 * 根据作品Id获取作品详细信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getWorkDesc.do")
	public String getWorkDesc(HttpServletRequest request,HttpServletResponse response){
		String Workid = request.getParameter("Workid");
		JSONObject result = productionManager.getWorkDesc(Workid);
		return result.toString();
	}
	
	/**
	 * 更新作品信息
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateWorkDesc.do")
	public String updateWorkDesc(HttpServletRequest request,HttpServletResponse response){
		String Workid = request.getParameter("Workid");
		String works_name = request.getParameter("works_name");
		String works_type = request.getParameter("works_type");
		String shoot_date = request.getParameter("shoot_date");
		String works_intro = request.getParameter("works_intro");
		String shoot_proc = request.getParameter("shoot_proc");
		String is_repre_works = request.getParameter("is_repre_works");
		String labelId = request.getParameter("labelId").replace("[", "").replace("]", "").replace("\"", "");
		String [] labelIds = labelId.split(",");
		String result = productionManager.updateWorkDesc(Workid,works_name,works_type,shoot_date,works_intro,shoot_proc,is_repre_works,labelIds);
		return result;
	}
	
	/**
	 * 更新作品
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("uploadWorkImg.do")
	public String  uploadStrandImg(HttpServletRequest request,HttpServletResponse response){
		JSONObject result =new JSONObject();
		String Workid =request.getParameter("Workid");
		String photogid =request.getParameter("photogid");
		JSONObject saveResult = FileUtil.SaveImgEncrypt(request,PGIMGPATH+photogid+"/works");
		if("false".equals(saveResult.get("istrue"))){
			result.put("code", 0);
			return result.toString();
		};
		String filename = photogid+"/works/"+saveResult.getString("filename");
		boolean istrue =productionManager.uploadWorkImg(Workid,filename);
		if(!istrue){
			result.put("code", 0);
			return result.toString();
		}else{
			result.put("code", 1);
			result.put("filename",filename);
			return result.toString();
		}
	};
}
