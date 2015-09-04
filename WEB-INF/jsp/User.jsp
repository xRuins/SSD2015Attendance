<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="edu.scit.ssd2015.attendance.User" %>
<% int id = Integer.valueOf(request.getAttribute("id").toString()); %>
<% User user = User.getUserByID(id); %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Take Attendance</title>
    </head>
    <body>
    	<%= user.getID() %>
    	<%= request.getAttribute("id").toString() %>
    	<% if (user != null)  { %>
        <h1>User Info - <%= user.getName() %></h1>
        <ul>
        	<li>Name: <%= user.getName()%></li>
        	<li>UserID: <%= user.getUserID() %></li>
        	<li>Password: <%= user.getPassword() %></li>
        	<li>Email: <%= user.getEmail() %></li>
        </ul>
        <% } else { %>
        <h1>Such user doesn't exist.</h1>
        <% } %>
    </body>
</html>