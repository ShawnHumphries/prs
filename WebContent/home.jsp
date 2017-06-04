<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Home Page</title>
<link rel="stylesheet" href="styles/main.css">
</head>
<body>
	<h1>PRS - Home Page</h1>
	<h2>${message}</h2>
	<br>
	<form name="createRequest" method="POST" action="createRequest">
		<input type="hidden" name="action" value="createRequest">
		<input type="hidden" name="userID" value="${userid}">
		<input type="hidden" name="userName" value="${username}">
		<button type="submit">New Request</button><br>
	</form>
	<br>
	<form name="reviewRequests" method="POST" action="reviewRequests">
		<input type="hidden" name="action" value="reviewRequests">
		<button type="submit">Review Your Requests</button><br>
	</form>
	<br>
	<c:if test="${user.isManager() == true}">
	<form name="approveRequests" method="POST" action="approveRequests">
		<input type="hidden" name="action" value="approveRequests">
		<button type="submit">Approve Requests</button><br>
	</form>
	<br>
	</c:if>
	<form name="listVendors" method="POST" action="listVendors">
		<input type="hidden" name="action" value="listVendors">
		<button type="submit">List Vendors</button><br>
	</form>
	<br>
	<form name="logout" method="POST" action="logout">
		<input type="hidden" name="action" value="logout">
		<button type="submit">Logout</button><br>
	</form>
	<br>
	<footer>
	  Copyright &copy; 2017 Shawn Humphries <a href="mailto:sahump1@gmail.com?Subject=PRS Web Application" target="_top">Contact</a>  
	</footer>
</body>
</html>