package edu.scit.ssd2015.attendance;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TakeAttendanceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String JSP_BASE = "/WEB-INF/jsp/";
	
	public TakeAttendanceServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String courseID = req.getParameter("courseID");
		req.setAttribute("courseID", courseID);
		

		String attendanceDate = req.getParameter("attendanceDate");
		req.setAttribute("attendanceDate", attendanceDate);
		
		String forward = JSP_BASE + "TakeAttendance.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
		dispatcher.forward(req, resp);
	}

}
