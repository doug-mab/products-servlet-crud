package br.com.market.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/product")
public class AuthenticationFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest servletReq, ServletResponse servletResp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletReq;
		HttpServletResponse resp = (HttpServletResponse) servletResp;

		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("loggedUser") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		chain.doFilter(req, resp);
	}

}
