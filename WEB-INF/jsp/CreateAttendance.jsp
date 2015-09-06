<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>

<% int courseID = Integer.valueOf(request.getAttribute("courseID").toString()); %>
<% int attendanceID = Integer.valueOf(request.getAttribute("attendanceID").toString()); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Attendance</title>
        
<link rel="stylesheet" type=text/css href="./css/header.css" >
<link rel="stylesheet" type=text/css href="./css/navi.css">
<link rel="stylesheet" type=text/css href="./css/table.css" >
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

		
		<h1 class="emboss">Take Attendance</h1>
		<BR>
        Your attendance was created successfully.
		
		<BR><BR><BR>
		<a href="${pageContext.request.contextPath}/ViewCourse?courseID=<%= courseID %>" class="button">Go to back course</a><BR><BR>
		<a href="${pageContext.request.contextPath}/ViewAttendance?attendanceID=<%= attendanceID %>" class="button">Confirm attendance</a>
		</div>	
	</div>
</div>

<jsp:include page="Footer.jsp"/>

</font>
</body>
</html>
</html>