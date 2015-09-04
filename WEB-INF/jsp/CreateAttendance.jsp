<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>

<% int courseID = Integer.valueOf(request.getAttribute("courseID").toString()); %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Take Attendance</title>
    </head>
    <body>
		<jsp:include page="Header.jsp"/>
        <h1>Take attendance</h1>
        <h2>Your attendance was created successfully.</h2>
        <a href="/TestTomcat/ViewCourse?courseID=<%= courseID %>">Go back to course</a>
		<jsp:include page="Footer.jsp"/>
    </body>
</html>