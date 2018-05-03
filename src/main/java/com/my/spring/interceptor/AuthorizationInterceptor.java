package com.my.spring.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.spring.pojo.User;


public class AuthorizationInterceptor  extends HandlerInterceptorAdapter{
	
	  private String login;
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	            throws Exception {
	        String uri = request.getRequestURI(); 

	        if (!uri.endsWith("/user/login") && !uri.endsWith("/user/register.htm") && !uri.endsWith("/user/register" )
	        		&& !uri.endsWith("/user/validateemail.htm" )) {
	            User user = (User) request.getSession().getAttribute("user");

	            if (user == null){
	                response.sendRedirect(request.getContextPath());
	               // response.sendRedirect(login);
	                return false;
	            }

	        } if (uri.endsWith("/admin/view") && uri.endsWith("/admin/mostActive")) {
	            User user = (User) request.getSession().getAttribute("user");

	            if (user != null || !user.equals("admin")){
	                response.sendRedirect(request.getContextPath());
	               // response.sendRedirect(login);
	                return false;
	            }

	        }

	        return true;
	    }
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}

		
	}

