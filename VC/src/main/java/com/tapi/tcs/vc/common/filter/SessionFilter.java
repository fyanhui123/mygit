package com.tapi.tcs.vc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.common.schema.vo.UserInfo;

/**
 * SessionFilter
 * <p>
 * Date 2013-03-18
 * </p>
 * <p>
 * Module：
 * </p>
 * <p>
 * Description：用于校验Session的Filter
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public class SessionFilter implements Filter {

	public void init(FilterConfig arg0) throws ServletException {
	}
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		checkSession((HttpServletRequest)request,(HttpServletResponse)response);
		chain.doFilter(request, response);
	}
	
	/**
	 * 校验session是否有效，主要是校验session里面是否有user
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean checkSession(HttpServletRequest request,HttpServletResponse response) {
		//如果session失效，返回null
		HttpSession session = request.getSession(false);
		String URI = request.getRequestURI();
		// 如果是在登陆，则不进行检查
		if (URI.contains("login.jsp")){
			return true;
		}
		if(request.getParameter("userCode")!=null 
				&& request.getParameter("password")!=null){
			return true;
		}
		try{
			//如果session为空，返回登录界面
			if(session==null){
				response.sendRedirect("/visa");
				return false;
			}else{
				String currentUser = (String)session.getAttribute("CURRENT_USER");
				//如果session中的用户信息为空，返回登录界面
				if(currentUser==null){
					response.sendRedirect("/visa");
					return false;
				}
			}
		}catch(Exception e){
			try {
				response.sendRedirect("/visa");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
