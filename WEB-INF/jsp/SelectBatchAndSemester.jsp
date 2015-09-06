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

        
<link rel="stylesheet" type=text/css href="./css/header.css" >
<link rel="stylesheet" type=text/css href="./css/navi.css">
<link rel="stylesheet" type=text/css href="./css/table.css" >
<link rel="stylesheet" type=text/css href="./css/button3.css">
<link rel="stylesheet" type=text/css href="./css/font.css" >
</head>
<body bgcolor="#F3F1F1">
<jsp:include page="Header.jsp"/>
<form action="SelectDivisionAndSubject" method="GET">
<div id="contentsArea">

	<BR>
	<div style="text-align : center">
	<div style="background:#fdfdfd; width:580px; border: 1px solid #D3D3D3; height:100％;
	 padding-left:10px; padding-right:10px; padding-top:10px; padding-bottom:50px;
 	 margin-left : auto ; margin-right : auto ;">

 
	<ul id="breadcrumbs-one">
    <li><a >Programme</a></li>
    <li><a class="current">Batch and Semester</a></li>
    <li><a >Division and Subject</a></li>
	</ul>


	<div style="background:#fdfdfd; width:300px; height:100％;
	 padding-left:10px; padding-right:10px; padding-top:50px; padding-bottom:50px;
 	 margin-left : auto ; margin-right : auto ;">
		<table class="sample_05">
			<tbody>
			<tr>
			<th>Batch</th>
			<th>Semester</th>
			</tr>
			<tr>
			<td><select name="batchID">
<%
for (Batch batch: batches) {
    String batchID = String.valueOf(batch.getBatchID());
    String name = batch.getBatchName();
    String option = "<option value='" + batchID + "'>" + name + "</option>";
    out.println(option);
}
%>
			</select>
			</td>
			<td><select name="semesterID">
<%
for (Semester semester: semesters) {
    String semesterID = String.valueOf(semester.getSemesterID());
    String name = semester.getName();
    String option = "<option value='" + semesterID + "'>" + name + "</option>";
    out.println(option);
}
%>
			</select></td>
			</tr>
			</tbody>
		</table>
	</div>
	<input type="submit" class="button" vakue="Next" /><BR><BR>
	
	</div>	
	</div>
</div>
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>