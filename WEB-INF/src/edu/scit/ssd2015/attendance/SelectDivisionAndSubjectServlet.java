package edu.scit.ssd2015.attendance;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectDivisionAndSubjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String JSP_BASE = "/WEB-INF/jsp/";
	
	public SelectDivisionAndSubjectServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String semesterID = req.getParameter("semesterID");
		req.setAttribute("semesterID", semesterID);

		String batchID = req.getParameter("batchID");
		req.setAttribute("batchID", batchID);
		
		
		String forward = JSP_BASE + "SelectDivisionAndSubject.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
		dispatcher.forward(req, resp);
	}

}
