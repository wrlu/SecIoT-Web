package com.wrlus.iotsecp;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 功能：
 * Spring MVC中的访问权限过滤器
 * 
 * 修订日期：
 * 2018-02-04 首次编写
 * 
 * @author 路伟饶
 *
 */
public class AccessAllowedFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
//		此句意义是允许跨域访问
		response.addHeader("Access-Control-Allow-Origin", "*");
		if ("OPTIONS".equals(request.getMethod())) {
			String allowHeaders = request.getHeader("Access-Control-Allow-Headers");
			if(allowHeaders != null) {
				response.addHeader("Access-Control-Allow-Headers", allowHeaders);
			} else {
				response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
			}
        } else {
            chain.doFilter(request, response);
        }
	}
}
