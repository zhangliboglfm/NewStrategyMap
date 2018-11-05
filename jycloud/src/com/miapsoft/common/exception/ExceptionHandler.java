package com.miapsoft.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * <p>Title: ExceptionHandler.java</p>
 * <p>Description: 异常处理类</p>
 * @author: 李杰
 * @time: 2017-3-29
 */
public class ExceptionHandler implements HandlerExceptionResolver {
    	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	map.put("success", false);
	    	//判断异常类型
		if (ex instanceof BussinessException) {
			BussinessException be = (BussinessException)ex;
			map.put("errorMsg", be.getMyMessage());
		} else {
			map.put("errorMsg", "出现未知系统错误，请联系管理员！");
		}
		//打印错误信息
		ex.printStackTrace();
		//判断是否ajax请求
		String xRequestedWith = request.getHeader("X-Requested-With");
		String accept = request.getHeader("accept");
		if (accept.indexOf("application/json")>-1||(xRequestedWith!=null && xRequestedWith.indexOf("XMLHttpRequest")>-1)) {
		    try {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(JSONObject.fromObject(map).toString());
			out.flush();
			out.close();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}else{
		    return new ModelAndView("errors/biz-error",map);
		}
		return null;
	}

}
