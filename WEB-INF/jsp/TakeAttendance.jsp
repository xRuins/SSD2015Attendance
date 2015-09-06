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
		        
		<link rel="stylesheet" type=text/css href="./css/header.css" >
		<link rel="stylesheet" type=text/css href="./css/navi.css">
		<link rel="stylesheet" type=text/css href="./css/table2.css" >
		<link rel="stylesheet" type=text/css href="./css/button.css" >
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
		
			<h1 class="emboss">Take Attendance</h1>
		
		<form action="CreateAttendance" method="POST">
		<input type="hidden" name="courseID" value="<%= courseID %>">
        <input type="hidden" name="attendanceDate" value="<%= request.getAttribute("attendanceDate").toString() %>">
		<table id="mytable" >
		<table align="center">
			<tr>
			</tr>
			<tr>
				<td>
				<table id="mytable">
					<th scope="col" abbr="Dual 1.8">Name</th>
					<th scope="col" abbr="Dual 1.8">Presence</th>
					</tr>
		<%       
for (User student: students) {
	out.println("<tr><td>" + student.getName() + "</td><td><input type='checkbox' name='students' value='" + student.getID() +
	"' checked></input></td></tr>");
}
 %>
				</table>
				</td>
			</tr>
		</table>
		</table>
	
		<BR><BR>
			
			<input type="submit" class="button" href="#" value="Submit">
			<input type="reset" class="button2" href="#" value="Reset">
	</form>
			
			</div>	
			</div>
		</div>
	<jsp:include page="Footer.jsp"/>
    </body>
</html>