package edu.scit.ssd2015.attendance;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String JSP_BASE = "/WEB-INF/jsp/";
	
	
	
	/**
	 * �\�z���܂��B
	 */
	public Test() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// �v������action�p�����[�^���擾
		String action = req.getParameter("action");

		System.out.println(action);
		String forward = null;
		if("test".equals(action)) {
			forward = JSP_BASE + "TakeAttendance.jsp";
		}else{
			throw new ServletException("Failed");
		}
		
		// JSP�ւ̃t�H���[�h
		RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
		dispatcher.forward(req, resp);
	}

}
