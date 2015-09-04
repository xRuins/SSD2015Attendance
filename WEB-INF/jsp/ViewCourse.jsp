<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="edu.scit.ssd2015.attendance.Course" %>
<%@ page import="edu.scit.ssd2015.attendance.Attendance" %>
<%@ page import="edu.scit.ssd2015.attendance.Subject" %>
<%@ page import="edu.scit.ssd2015.attendance.Division" %>

<%
Course course;
int courseID;
int divisionID;
int subjectID;
if (request.getAttribute("courseID") != null) {
	courseID = Integer.valueOf(request.getAttribute("courseID").toString());
	course = Course.getCourseByID(courseID);
} else {
	divisionID = Integer.valueOf(request.getAttribute("divisionID").toString());
	subjectID = Integer.valueOf(request.getAttribute("subjectID").toString());
	course = Course.getCourseByIDs(divisionID, subjectID);
	courseID = course.getCourseID();
}
ArrayList<Attendance> attendances = Attendance.getAttendanceByCourseID(courseID); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Course Information</title>
</head>
<body>
<h1>Course - <%= course.getSubject().getName() %>
: <%= course.getDivision().getName() %> </h1>
<%
if (attendances.isEmpty()) {
	out.println("<h4>No attendance has taken yet.</h4>");
} else {
	out.println("<ul>");
	for (Attendance attendance: attendances) {
    	String attendanceID = String.valueOf(attendance.getAttendanceID());
   		Date attendanceDate = attendance.getAttendanceDate();
    	String option = "<li><a href='/TestTomcat/ViewAttendance?attendanceID=" + attendanceID + "'>" + attendanceDate.toString() + "</a></li>";
    	out.println(option);
	}
	out.println("</ul>");
}
%>
<h4>Take New Attendance</h4>

<form action="TakeAttendance" method="get">
<input type="date" name="attendanceDate">
<input type="hidden" name="courseID" value="<%= courseID %>">
<input type="submit" value="Take New Attendance" />
</form>

</body>
</html>