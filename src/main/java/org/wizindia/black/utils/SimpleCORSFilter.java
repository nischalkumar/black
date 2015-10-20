package org.wizindia.black.utils;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by hari_om on 3/8/15.
 */
@Component
public class SimpleCORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	
        HttpServletResponse httpServletResponse =(HttpServletResponse) servletResponse;
 	    HttpServletRequest request = (HttpServletRequest) servletRequest;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, Authorization");
	if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
        	httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	} else {
        	filterChain.doFilter(servletRequest, servletResponse);
	}
    }

    @Override
    public void destroy() {

    }
}
