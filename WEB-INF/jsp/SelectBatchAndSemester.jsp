<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.scit.ssd2015.attendance.Batch" %>
<%@ page import="edu.scit.ssd2015.attendance.Semester" %>

<% System.out.println(request.getAttribute("programmeID")); %>
<% int programmeID = Integer.valueOf(request.getAttribute("programmeID").toString()); %>
<% ArrayList<Batch> batches = Batch.getBatchByProgrammeID(programmeID); %>
<% ArrayList<Semester> semesters = Semester.getSemesterByProgrammeID(programmeID); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Batch and Semester Select</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<h1>Please select batch and semester</h1>
<form action="SelectDivisionAndSubject" method="GET">
Batch : 
<select name="batchID">
<%
for (Batch batch: batches) {
    String batchID = String.valueOf(batch.getBatchID());
    String name = batch.getBatchName();
    String option = "<option value='" + batchID + "'>" + name + "</option>";
    out.println(option);
}
%>
</select>

Semester : 
<select name="semesterID">
<%
for (Semester semester: semesters) {
    String semesterID = String.valueOf(semester.getSemesterID());
    String name = semester.getName();
    String option = "<option value='" + semesterID + "'>" + name + "</option>";
    out.println(option);
}
%>
</select>
<input type="submit" value="next" />
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>