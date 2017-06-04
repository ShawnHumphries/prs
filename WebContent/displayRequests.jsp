<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Purchase Requests</title>
<link rel="stylesheet" href="styles/main.css">
<script type="text/javascript" src="main.js"></script>
</head>
<body>
	<h2>Submitted Purchase Requests For ${username}</h2>
	<table>
	<tr><th>Date Submitted</th><th>Description</th><th>Justification</th><th>Date Needed By</th><th>Delivery Mode</th><th>Doc Attached?</th><th>Status</th><th>Total</th></tr>
	<c:forEach var="pr" items="${purchaseRequests}" >
	<tr>
		<td>${pr.submittedDate}</td>
		<td>${pr.description}</td>
		<td>${pr.justification}</td>
		<td>${pr.dateNeeded}</td>
		<td>${pr.deliveryMode}</td>
		<td>${pr.convertDocAttachedToString()}</td>
		<c:choose>
			<c:when test="${pr.status == 'Rejected'}">
				<td><span style="color:red">${pr.status}</span></td>
			</c:when>
			<c:otherwise>
				<td>${pr.status}</td>
			</c:otherwise>
		</c:choose>
		<td>${pr.formattedTotal}</td>
	</tr>
	</c:forEach>
	</table>
	<br>
	<button onclick="goBack()">Go Back</button><br>
	<br>
	<footer>
	  Copyright &copy; 2017 Shawn Humphries <a href="mailto:sahump1@gmail.com?Subject=PRS Web Application" target="_top">Contact</a>  
	</footer>
</body>
</html>