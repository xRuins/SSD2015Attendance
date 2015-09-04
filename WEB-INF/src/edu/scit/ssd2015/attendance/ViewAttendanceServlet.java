package edu.scit.ssd2015.attendance;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAttendanceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String JSP_BASE = "/WEB-INF/jsp/";
	
	public ViewAttendanceServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String attendanceID = req.getParameter("attendanceID");
		req.setAttribute("attendanceID", attendanceID);
		
		String forward = null;
//		if(attendance_id != null) {
			forward = JSP_BASE + "ViewAttendance.jsp";
//		}else{
//			throw new ServletException("Failed");
//		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
		dispatcher.forward(req, resp);
	}

}
