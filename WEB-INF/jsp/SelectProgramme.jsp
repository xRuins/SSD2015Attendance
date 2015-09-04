<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.scit.ssd2015.attendance.Programme" %>
<%@ page import="java.io.PrintWriter" %>

<% ArrayList<Programme> programmes = Programme.getAllProgramme(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Programme Select</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<h1>Please select programme</h1>
<form action="SelectBatchAndSemester" method="GET">
<select name="programmeID">
<%
for (Programme programme: programmes) {
    String programmeID = String.valueOf(programme.getProgrammeID());
    String name = programme.getName();
    String option = "<option value='" + programmeID + "'>" + name + "</option>";
    out.println(option);
}
%>
</select>
<input type="submit" value="next" />
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>