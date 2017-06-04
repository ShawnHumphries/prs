<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,prs.business.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Product List</title>
<link rel="stylesheet" href="styles/main.css">
<script type="text/javascript" src="main.js"></script>
</head>
<body>
	<h1>List of Products</h1>
	<table>
		<tr><th>Name</th><th>Part Number</th><th>Price</th><th>Unit</th><th>Image</th></tr>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:forEach var="product" items="${products}" >
		<tr>
		<td>${product.name}</td>
		<td>${product.partNumber}</td>
		<td>${product.formattedPrice}</td>
		<td>${product.unit}</td>
		<td><img src='${product.photoPath}' width="100" height="100"></td>
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