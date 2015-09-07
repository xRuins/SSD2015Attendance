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
<link rel="stylesheet" type=text/css href="./css/header.css" >
<link rel="stylesheet" type=text/css href="./css/navi.css">
<link rel="stylesheet" type=text/css href="./css/table.css" >
<link rel="stylesheet" type=text/css href="./css/table2.css" >
<link rel="stylesheet" type=text/css href="./css/button3.css">
<link rel="stylesheet" type=text/css href="./css/font.css" >
</head>
<body bgcolor="#F3F1F1">
<jsp:include page="Header.jsp"/>
<div id="contentsArea">

	<BR>
	<div style="text-align : center">
	<div style="background:#fdfdfd; width:580px; border: 1px solid #D3D3D3; height:100％;
	 padding-left:10px; padding-right:10px; padding-top:10px; padding-bottom:50px;
 	 margin-left : auto ; margin-right : auto ;">

	<h1 class="emboss">Course - <%= course.getSubject().getName() %>
: <%= course.getDivision().getName() %> </h1>


<table id="mytable">
<table align="center">
	<tr>
	</tr>
	<tr>
		<td>
		<table id="mytable">
			<tr>
				<th scope="col" abbr="Dual 1.8">Date</th>
			</tr>
			<%
if (attendances.isEmpty()) {
	out.println("<tr><td>No attendance has taken yet.</td></tr>");
} else {
	for (Attendance attendance: attendances) {
    	String attendanceID = String.valueOf(attendance.getAttendanceID());
   		Date attendanceDate = attendance.getAttendanceDate();
    	String option = "<tr><td><a href='/TestTomcat/ViewAttendance?attendanceID=" +
   		attendanceID + "'>" + attendanceDate.toString() + "</a></td></tr>";
    	out.println(option);
	}
}
%>
</table>
</td></tr></table>
</table>

<form action="TakeAttendance" method="get">
<input type="date" name="attendanceDate">
<input type="hidden" name="courseID" value="<%= courseID %>">
<input type="submit" value="Take New Attendance" class="button" />
</form>

<jsp:include page="Footer.jsp"/>
</body>
</html>