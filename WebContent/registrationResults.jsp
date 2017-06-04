<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Registration Success</title>
	<link rel="stylesheet" href="styles/main.css">
</head>
<body>
	<h1>User ${user.username} successfully registered!</h1>
	<h2>Details</h2>
	<table>
		<tr><td>User Name:</td><td>${user.username}</td></tr>
		<tr><td>Password:</td><td>${user.password}</td></tr>
		<tr><td>First Name:</td><td>${user.firstName}</td></tr>
		<tr><td>Last Name:</td><td>${user.lastName}</td></tr>
		<tr><td>Phone Number:</td><td>${user.phone}</td></tr>
		<tr><td>Email Address:</td><td>${user.email}</td></tr>
		<tr><td>Manager?</td><td>${user.manager}</td></tr>
	</table>
	<br>
	<footer>
	  Copyright &copy; 2017 Shawn Humphries <a href="mailto:sahump1@gmail.com?Subject=PRS Web Application" target="_top">Contact</a>  
	</footer>
</body>
</html>