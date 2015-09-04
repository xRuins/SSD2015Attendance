<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="edu.scit.ssd2015.attendance.Course" %>
<%@ page import="edu.scit.ssd2015.attendance.Attendance" %>
<%@ page import="edu.scit.ssd2015.attendance.User" %>

<% int courseID = Integer.valueOf(request.getAttribute("courseID").toString()); %>
<% Course course = Course.getCourseByID(courseID); %>
<% ArrayList<User> students = course.getStudent(); %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Take Attendance</title>
    </head>
    <body>
	<jsp:include page="Header.jsp"/>
        <h1>Take attendance</h1>
        <h2>Subject Name:<%= course.getCourseName() %></h2>
        <form action="CreateAttendance" method="post">
        <input type="hidden" name="attendanceDate" value="<%= request.getAttribute("attendanceDate").toString() %>">
        <input type="hidden" name="courseID" value="<%= courseID %>">
<%       
for (User student: students) {
	System.out.println("Nam:" + student.getName());
	out.println("<p><input type='checkbox' name='students' value='" + student.getID() +
	"' checked>" + student.getName() + "</input></p>");
}
 %>
 		<input type="submit" name="Submit">
 		<input type="reset" name="Reset">
        </form>
	<jsp:include page="Footer.jsp"/>
    </body>
</html>