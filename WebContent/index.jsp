<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Purchase Request System</title>
	<link rel="stylesheet" href="styles/main.css">
</head>
<body>
	<h1>Welcome to the Purchase Request System Login Page!</h1>
	
    <section>
		<form action="login" method="post">
		    <label class="pad_top">User Name: </label>
		    <input type="text" name="userName" required><br>
		    <label class="pad_top">Password: </label>
		    <input type="password" name="password" required><br>
		    <!--  <label>&nbsp;</label>-->
		    <button type="submit">Login</button><br>
		</form>
       <br>
		<form name="registerUser" method="POST" action="registerUser">
			<input type="hidden" name="action" value="registerUser">
			<button type="submit">Register New User</button><br>
		</form>
	</section>
	<br>	
	<footer>
	  Copyright &copy; 2017 Shawn Humphries <a href="mailto:sahump1@gmail.com?Subject=PRS Web Application" target="_top">Contact</a>  
	</footer>
	
</body>
</html>