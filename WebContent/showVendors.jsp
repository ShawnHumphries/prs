<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,prs.business.Vendor" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Vendor List</title>
<link rel="stylesheet" href="styles/main.css">
<script type="text/javascript" src="main.js"></script>
</head>
<body>
	<h1>List of Vendors</h1>
	<p>Please note:  PreApproved vendors are preferred.</p>
	<table>
		<tr><th>&nbsp;</th><th>Name</th><th>Code</th><th>Address</th><th>City</th><th>State</th><th>Zip</th><th>Phone Number</th><th>EMail</th><th>PreApproved?</th></tr>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:forEach var="vendor" items="${vendors}" >
		<tr>
		<td><a href="<c:url value='/showProducts?action=showProducts&vendorID=${vendor.id}&preapproved=${vendor.preapproved}' />">Select</a></td>
		<td>${vendor.name}</td>
		<td>${vendor.code}</td>
		<td>${vendor.address}</td>
		<td>${vendor.city}</td>
		<td>${vendor.state}</td>
		<td>${vendor.zip}</td>
		<td>${vendor.phone}</td>
		<td>${vendor.email}</td>
		<td>${vendor.convertPreApprovedToString()}</td></tr>
		</c:forEach>
	</table>
	<p>Clicking the Select link will bring up a list of products for the vendor.</p>
	<button onclick="goBack()">Go Back</button><br>
	<br>
	<footer>
	  Copyright &copy; 2017 Shawn Humphries <a href="mailto:sahump1@gmail.com?Subject=PRS Web Application" target="_top">Contact</a>  
	</footer>
</body>
</html>