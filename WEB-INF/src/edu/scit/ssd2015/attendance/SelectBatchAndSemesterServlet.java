package edu.scit.ssd2015.attendance;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectBatchAndSemesterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String JSP_BASE = "/WEB-INF/jsp/";
	
	public SelectBatchAndSemesterServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String programmeID = req.getParameter("programmeID");
		req.setAttribute("programmeID", programmeID);
		
		String forward = JSP_BASE + "SelectBatchAndSemester.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
		dispatcher.forward(req, resp);
	}

}
