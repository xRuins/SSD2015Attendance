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
<link rel="stylesheet" type=text/css href="./css/header.css" >
<link rel="stylesheet" type=text/css href="./css/navi.css">
<link rel="stylesheet" type=text/css href="./css/table.css" >
<link rel="stylesheet" type=text/css href="./css/button3.css">
<link rel="stylesheet" type=text/css href="./css/font.css" >
</head>
<body bgcolor="#F3F1F1">
<jsp:include page="Header.jsp"/>

<div id="contentsArea">
	<form action="ViewCourse" method="GET">
	<BR>
	<div style="text-align : center">
	<div style="background:#fdfdfd; width:580px; border: 1px solid #D3D3D3; height:100％;
	 padding-left:10px; padding-right:10px; padding-top:10px; padding-bottom:50px;
 	 margin-left : auto ; margin-right : auto ;">

 
	<ul id="breadcrumbs-one">
    <li><a >Programme</a></li>
    <li><a >Batch and Semester</a></li>
    <li><a class="current">Division and Subject</a></li>
	</ul>


	<div style="background:#fdfdfd; width:300px; height:100％;
	 padding-left:10px; padding-right:10px; padding-top:50px; padding-bottom:50px;
 	 margin-left : auto ; margin-right : auto ;">
		<table class="sample_05">
			<tbody>
			<tr>
			<th>Subject</th>
			<th>Division</th>
			</tr>
			<tr>
			<td><select name="subjectID">
			<%
for (Subject subject: subjects) {
    String subjectID = String.valueOf(subject.getSubjectID());
    String name = subject.getName();
    String option = "<option value='" + subjectID + "'>" + name + "</option>";
    out.println(option);
}
%>
			</select></td>
			<td><select name="divisionID">

<%
for (Division division: divisions) {
    String divisionID = String.valueOf(division.getDivisionID());
    String name = division.getName();
    String option = "<option value='" + divisionID + "'>" + name + "</option>";
    out.println(option);
}
%>
		</select></td>
			</tr>
			</tbody>
		</table>
	</div>
	<input class="button" value="Next" type="submit" /><BR><BR>
	
	</div>	
	</div>
	</form>
</div>
<jsp:include page="Footer.jsp"/>
</body>
</html>