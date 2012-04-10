package org.jboss.jbug.cz01.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "GreetingServlet", urlPatterns = { "/hello", "/hi" })
public class GreetingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String name = req.getParameter("name");

		if (name != null) {
			resp.getWriter().append(
					"Please to meet you " + StringUtils.stripAccents(name)
							+ "!");
		} else {
			resp.getWriter().append("What's your name?");
		}
	}

}
