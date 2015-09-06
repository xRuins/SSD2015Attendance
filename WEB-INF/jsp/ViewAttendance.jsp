<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="edu.scit.ssd2015.attendance.User" %>
<%@ page import="edu.scit.ssd2015.attendance.Attendance" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Select Batch and Semester</title>

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

	<h1 class="emboss">View Attendance</h1>



<BR><BR>

<table id="mytable">
<table align="center">
	<tr>
	</tr>
	<tr>
		<td>
		<table id="mytable">
			<th scope="col" abbr="Dual 1.8">Name</th>
			</tr>
<%
int attendanceID = Integer.valueOf(request.getAttribute("attendanceID").toString());
System.out.println("AtndID:" + attendanceID);
Attendance attendance = Attendance.getAttendanceByID(attendanceID);
System.out.println("Atndnc:" + attendance);
ArrayList<User> students = attendance.getStudent();
for (User student: students) {
	out.println("<tr><td>" + student.getName() + "</td></tr>");
	
}

int total = attendance.getCourse().getStudent().size();
int present = attendance.getStudent().size();
int absent = total - present;

out.println("<tr><th>");
out.println("Total: " + total);
out.println("Present: " + present);
out.println("Absent: " + absent);
out.println("</th></tr>");
%>
			

		</table>
		</td>
	</tr>
</table>
</table>

<BR><BR>
	
	<a class="button" href="#" >Back</a><BR><BR>
	
	</div>	
	</div>
</div>

<jsp:include page="Footer.jsp"/>

</body>
</html>        