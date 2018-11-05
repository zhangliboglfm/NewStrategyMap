package com.miapsoft.filter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.miapsoft.util.SecurityUtil;
/**
 * 过滤httprequest封装类
 * <p>Title: AntiXssRequest.java</p>
 * <p>Description: TODO</p>
 * @author: 李杰
 * @time: 2017-3-29
 */
public class AntiXssRequest extends HttpServletRequestWrapper {
    public AntiXssRequest(HttpServletRequest servletRequest) {
        super(servletRequest);
    }
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }
        return encodedValues;
    }
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return stripXSS(value);
    }
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }
    /**
     * 
     * @Title:stripXSS
     * @Description: 过滤掉参数中存在xss攻击的参数
     * @param value
     * @return 过滤后的参数
     * @author:李杰
     * @date:2017-3-29
     */
    private String stripXSS(String value) {
        if (value != null) {
        	value = SecurityUtil.stripXSS(value);
        }
        return value;
    }
}