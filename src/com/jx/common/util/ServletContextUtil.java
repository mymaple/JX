package com.jx.common.util;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class ServletContextUtil {
	

	private static ServletContext getServletContext() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
        ServletContext servletContext = webApplicationContext.getServletContext();  
		return servletContext;
	}

    
	public static Object getBean(String beanName) throws BeansException{
		XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(getServletContext());                
        if(cxt != null)
        	return cxt.getBean(beanName);
        else
        	return null;
    }
}
