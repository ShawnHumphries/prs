<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Approve Requests</title>
<link rel="stylesheet" href="styles/main.css">
<script type="text/javascript" src="main.js"></script>
</head>
<body>
	<h2>The following purchase requests are pending for approval:</h2>
	<p>Please note: requests that are left unchecked will be considered 'Rejected'.</p>
	<form name="updateRequests" action="updateRequests" method="post">
		<table>
		<tr><th>Approve?</th><th>User Name</th><th>Date Submitted</th><th>Description</th><th>Justification</th><th>Date Needed By</th><th>Delivery Mode</th><th>Doc Attached?</th><th>Status</th><th>Total</th></tr>
		<c:forEach var="pr" items="${purchaseRequests}" varStatus="status">
			<tr>
				<td>
					<input type=hidden name="requestid" value="${pr.id}" />
					<input type=checkbox name="approved_${pr.id}" />
				</td>
				<td>${pr.userName}</td>
				<td>${pr.submittedDate}</td>
				<td>${pr.description}</td>
				<td>${pr.justification}</td>
				<td>${pr.dateNeeded}</td>
				<td>${pr.deliveryMode}</td>
				<td>${pr.convertDocAttachedToString()}</td>
				<td>${pr.status}</td>
				<td>${pr.formattedTotal}</td>
			</tr>
		</c:forEach>
		</table>
		<br>
		<input type="hidden" name="action" value="updateRequests">
		<button type="submit">Submit</button>
	</form>
	<br>
	<button onclick="goBack()">Go Back</button><br>
	<br>
	<footer>
	  Copyright &copy; 2017 Shawn Humphries <a href="mailto:sahump1@gmail.com?Subject=PRS Web Application" target="_top">Contact</a>  
	</footer>
</body>
</html>