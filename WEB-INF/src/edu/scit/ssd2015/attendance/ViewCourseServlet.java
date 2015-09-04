package edu.scit.ssd2015.attendance;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String JSP_BASE = "/WEB-INF/jsp/";
	
	public ViewCourseServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String courseID = req.getParameter("courseID");
		if ( courseID != null ) {
			req.setAttribute("courseID", courseID);
		} else {
			String divisionID = req.getParameter("divisionID");
			req.setAttribute("divisionID", divisionID);
			String subjectID = req.getParameter("subjectID");
			req.setAttribute("subjectID", subjectID);
		}
		String forward = JSP_BASE + "ViewCourse.jsp";

		RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
		dispatcher.forward(req, resp);
	}

}
