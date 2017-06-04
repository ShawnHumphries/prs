<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS New User Registration</title>
<link rel="stylesheet" href="styles/main.css">
</head>
<body>
	<h1>Welcome to the Purchase Request App New User Registration Page!</h1>
	
	<h2>New User Registration</h2>

    <section>

        <form name="register" method="POST" action="register">

			<input type="hidden" name="action" value="register">
		    <label class="pad_top">User Name: </label>
			<input type="text" name="userName" required><br><br>
		    <label class="pad_top">Password: </label>
			<input type="password" name="password" required><br><br>
		    <label class="pad_top">First Name: </label>
			<input type="text" name="firstName" required><br><br>
		    <label class="pad_top">Last Name: </label>
			<input type="text" name="lastName" required><br><br>
		    <label class="pad_top">Phone #: </label>
			<input type="tel" name="phoneNumber" required><br><br>
		    <label class="pad_top">Email Address: </label>
			<input type="email" name="email" required><br><br>
		    <label class="pad_top">Manager? </label>
			<input type="checkbox" name="manager"><br><br>
			<button type="submit">Submit</button>
			<button type="reset">Cancel</button>

        </form>

    </section>
	<br>
	<footer>
	  Copyright &copy; 2017 Shawn Humphries <a href="mailto:sahump1@gmail.com?Subject=PRS Web Application" target="_top">Contact</a>  
	</footer>
</body>
</html>