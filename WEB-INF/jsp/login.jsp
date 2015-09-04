<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Login</title>
        <link rel="STYLESHEET" href="todo.css" type="text/css">
        <script type="text/javascript" src="login.js">
        </script>
    </head>
    <body onload="fieldChanged();">
        <h1>Take attendance of (CourseName, Division, Date)</h1>
		<form method="POST" name="attendance">
        <ul>
        	<li>
        		Koichiro FUKASAWA
				<input type="radio" value="[1]1" name="1" checked>Present</input>
				<input type="radio" value="[1]1" name="0" checked>Absent</input>
			</li>
        	<li>
        		Emi TAKEMOTO
				<input type="radio" value="[1]1" name="1" checked>Present</input>
				<input type="radio" value="[1]1" name="0" checked>Absent</input>
			</li>
		</ul>
		</form>
    </body>
</html>