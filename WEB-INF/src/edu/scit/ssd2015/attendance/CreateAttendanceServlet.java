package edu.scit.ssd2015.attendance;


import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAttendanceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String JSP_BASE = "/WEB-INF/jsp/";
	
	public CreateAttendanceServlet() {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// prepare parameters of request
		String attendanceDate = req.getParameter("attendanceDate");
		String courseIDStr = req.getParameter("courseID").toString();
		req.setAttribute("courseID", courseIDStr); // set courseID to go back to course
		
		int courseID = Integer.valueOf(courseIDStr);
		String[] students = req.getParameterValues("students");
		
		// create Attendance
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Date _attendanceDate = null;
		try {
			_attendanceDate = sdf.parse(attendanceDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Attendance attendance = new Attendance(_attendanceDate, courseID, 1);
		attendance.save();
		int attendanceID = attendance.getAttendanceID();
		System.out.println("[CreateAttendanceServlet] atndID: " + attendanceID);
		// create AttendanceStudents
		for (String student: students) {
			int studentID = Integer.valueOf(student);
			System.out.println("[CrtAtndSvlt] studentid: "+ studentID);
			AttendanceStudent as = new AttendanceStudent(attendanceID, studentID, 1);
			as.save();
			System.out.println("[CrtAtndSvlt] as : "+ as.getAttendanceID() + "," + as.getStudentID());
		}
		
		String forward = JSP_BASE + "CreateAttendance.jsp";
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
		dispatcher.forward(req, resp);
	}

}
