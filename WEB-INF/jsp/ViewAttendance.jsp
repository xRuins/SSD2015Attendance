<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="edu.scit.ssd2015.attendance.User" %>
<%@ page import="edu.scit.ssd2015.attendance.Attendance" %>
<%@ page import="java.util.ArrayList" %>

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Take Attendance</title>
    </head>
    <body>
        <h1>View attendance</h1>
        <h2>Following students were present in class.</h2>
        <ul>
<%
int attendanceID = Integer.valueOf(request.getAttribute("attendanceID").toString());
System.out.println("AtndID:" + attendanceID);
Attendance attendance = Attendance.getAttendanceByID(attendanceID);
System.out.println("Atndnc:" + attendance);
ArrayList<User> students = attendance.getStudent();
for (User student: students) {
	System.out.println(student.getID());
	out.println("<li>" + student.getName() + "</li>");
}        
%>
       	</ul>
    </body>
</html>