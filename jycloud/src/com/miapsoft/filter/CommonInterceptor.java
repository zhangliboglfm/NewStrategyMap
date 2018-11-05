package com.miapsoft.filter;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.miapsoft.common.exception.BussinessException;
import com.miapsoft.util.ConfigInfo;
import com.miapsoft.util.SecurityUtil;
/**
 * springMVC 拦截器
 * <p>Title: CommonInterceptor.java</p>
 * <p>Description: TODO</p>
 * @author: 李杰
 * @time: 2017-3-29
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
	/** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     *     
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/**
		 * 1、判断登录信息
		 */
		if(!SecurityUtil.userIsLogin(request)){
			if(ConfigInfo.LOGIN_URL .startsWith("http://")){
				response.sendRedirect(ConfigInfo.LOGIN_URL);
			}else{
				request.getRequestDispatcher(ConfigInfo.LOGIN_URL).forward(request, response);
			}
			return false;
		}
		/**
		 * 2、判断SQL注入
		 */
		Map parameters = request.getParameterMap();
		Set<String> keys = parameters.keySet();
		if(keys.size()>0){//有参数
			for (String key : keys) {// 遍历键值对的集合
				String param = request.getParameter(key);									
				//验证参数是否存在SQL注入
				if(!SecurityUtil.isValid(param)){//验证不通过
				    throw new BussinessException("请求参数存在非法字符");
				}
			}			
		}	
		return true;
	}
	 /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作   
     * 可在modelAndView中加入数据，比如当前时间
     */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	 /** 
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等    
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */ 
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}
