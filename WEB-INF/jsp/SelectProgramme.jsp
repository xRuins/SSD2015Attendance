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

<link rel="stylesheet" type=text/css href="./css/header.css" >
<link rel="stylesheet" type=text/css href="./css/navi.css">
<link rel="stylesheet" type=text/css href="./css/table2.css" >
<link rel="stylesheet" type=text/css href="./css/button.css" >
<link rel="stylesheet" type=text/css href="./css/font.css" >

<title>Programme Select</title>
</head>
<body bgcolor="#F3F1F1">
<jsp:include page="Header.jsp"/>


<div id="contentsArea">

	<BR>
	<div style="text-align : center">
	<div style="background:#fdfdfd; width:580px; border: 1px solid #D3D3D3; height:100％;
	 padding-left:10px; padding-right:10px; padding-top:10px; padding-bottom:50px;
 	 margin-left : auto ; margin-right : auto ;">

 
	<ul id="breadcrumbs-one">
    <li><a class="current">Programme</a></li>
    <li><a >Batch and Semester</a></li>
    <li><a >Division and Subject</a></li>
	</ul>


<form action="SelectBatchAndSemester" method="GET">
	<div style="background:#fdfdfd; width:300px; height:100％;
	 padding-left:10px; padding-right:10px; padding-top:50px; padding-bottom:50px;
 	 margin-left : auto ; margin-right : auto ;">
		<table class="sample_05" align="center">
			<tbody>
			<tr>
			<th>Programme</th>
			</tr>
			<tr>
			<td><select name="programmeID">
<%
for (Programme programme: programmes) {
    String programmeID = String.valueOf(programme.getProgrammeID());
    String name = programme.getName();
    String option = "<option value='" + programmeID + "'>" + name + "</option>";
    out.println(option);
}
%>
</select>
</td>
			</tr>
			</tbody>
		</table>
	</div>
	<input class="button" type="submit" value="Next" />
	<BR><BR>
</form>
	
	</div>	
	</div>
</div>

<jsp:include page="Footer.jsp"/>
</body>
</html>