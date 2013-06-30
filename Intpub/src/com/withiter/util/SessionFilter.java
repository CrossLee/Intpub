/*
 * Created on Mar 1, 2012
 *
 * SessionFilter.java
 *
 * Copyright (C) 2012 by Citicorp Software &amp; Technology Services (Shanghai) Limited.
 * All rights reserved. Citicorp Software &amp; Technology Services (Shanghai) Limited
 * claims copyright in this computer program as an unpublished work. Claim of copyright
 * does not imply waiver of other rights.
 *
 * NOTICE OF PROPRIETARY RIGHTS
 *
 * This program is a confidential trade secret and the property of
 * Citicorp Software &amp; Technology Services (Shanghai) Limited. Use, examination,
 * reproduction, disassembly, decompiling, transfer and/or disclosure to others of
 * all or any part of this software program are strictly prohibited except by express
 * written agreement with Citicorp Software &amp; Technology Services (Shanghai) Limited.
 */
/*
 * ---------------------------------------------------------------------------------
 * Modification History
 * Date               Author                     Version     Description
 * Mar 1, 2012       gl65293                    1.0         New
 * ---------------------------------------------------------------------------------
 */
/**
 *
 */
package com.withiter.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.withiter.dto.UserDTO;

/**
 * @author gl65293
 * 
 */
public class SessionFilter extends HttpServlet implements Filter {

	/**
     *
     */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		System.out.println(request.getRequestURI());
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("userDTO");
		
		if("/Intpub/".equals(request.getRequestURI()) || "/Intpub/login.jsp".equals(request.getRequestURI())
				||"/Intpub/userspace/loginUser.action".equals(request.getRequestURI()))
		{
			chain.doFilter(request, response);
		}
		else
		{
			if (user == null)
			{
				response.sendRedirect("login.jsp");
			}
			else
			{
				chain.doFilter(request, response);
			}
			
		}
		
//		if (user == null) {
//			response.sendRedirect("login.jsp?usernull=yes");
//			if(!"/Intpub".equals(request.getContextPath()))
//			{
//				response.sendRedirect("login.jsp");
//			}
//			
//			chain.doFilter(request, response);
//			
//		} else {
//			chain.doFilter(request, response);
//		}
//		chain.doFilter(req, res);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}