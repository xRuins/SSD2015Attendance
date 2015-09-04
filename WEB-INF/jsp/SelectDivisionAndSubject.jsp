<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.scit.ssd2015.attendance.Division" %>
<%@ page import="edu.scit.ssd2015.attendance.Subject" %>

<% int batchID = Integer.valueOf(request.getAttribute("batchID").toString()); %>
<% int semesterID = Integer.valueOf(request.getAttribute("semesterID").toString()); %>
<% ArrayList<Division> divisions = Division.getDivisionByBatchID(batchID); %>
<% ArrayList<Subject> subjects = Subject.getSubjectBySemesterID(semesterID); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Division and Subject Select</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<h1>Please select Division and Subject</h1>
<form action="ViewCourse" method="GET">
Subject : 
<select name="subjectID">
<%
for (Subject subject: subjects) {
    String subjectID = String.valueOf(subject.getSubjectID());
    String name = subject.getName();
    String option = "<option value='" + subjectID + "'>" + name + "</option>";
    out.println(option);
}
%>
</select>

Division : 
<select name="divisionID">
<%
for (Division division: divisions) {
    String divisionID = String.valueOf(division.getDivisionID());
    String name = division.getName();
    String option = "<option value='" + divisionID + "'>" + name + "</option>";
    out.println(option);
}
%>
</select>
<input type="submit" value="next" />
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>